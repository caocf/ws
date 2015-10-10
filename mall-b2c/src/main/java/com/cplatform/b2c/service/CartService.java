package com.cplatform.b2c.service;

import com.cplatform.act.ActServiceClient;
import com.cplatform.act.CartExamineRequest;
import com.cplatform.act.CartExamineResponse;
import com.cplatform.b2c.cart.*;
import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CookieUtils;
import com.cplatform.dbhelp.DbHelper;
import com.google.common.collect.Maps;
import net.sf.json.JSONObject;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletResponse;
import java.sql.SQLException;
import java.util.*;
import java.util.regex.Pattern;

/**
 * 购物车服务类
 * <p/>
 * Copyright: Copyright (c) 13-6-8 上午11:14
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class CartService {

    /**
     * 保存购物车id的 cookie key 字段
     */
    public static final String CART_UUID = "cartuuid";

    private static final Pattern UUID_REGX = Pattern.compile("^([0-9a-f]{8})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{4})-([0-9a-f]{12})$");

    static final Log logger = LogFactory.getLog(CartService.class);

    @Autowired
    MemberService memberService;

    @Autowired
    CartDataManager cartDataManager;

    @Autowired
    DbHelper dbHelper;

    @Autowired
    AppConfig appConfig;

    @Autowired
    private ActServiceClient actBusinessClient;

    @Autowired
    OrderService orderService;

    @Autowired
    private ThirdInterDao thirdInterDao;

    /**
     * 查询接口，判断是否可以加入购物车
     *
     * @param itemId   商品id
     * @param shopId   商户id
     * @param quantity 数量
     * @param userId   购买人
     * @return 是否可以加入购物车
     */
    public void canAddToCart(String itemId, String shopId, int quantity, Long userId) throws CartOpException {
        // if (true) return true;
        // 构造请求
        CartExamineRequest request = new CartExamineRequest();
        request.setCount(quantity);
        request.setGoodsId(Long.parseLong(itemId));
        request.setShopId(Long.parseLong(shopId));
        request.setUserId(userId);
        // 加入购物车、获取加入结果
        CartExamineResponse response = actBusinessClient.cartExamine(request);
        // logger.debug(response);
        if (response.getStatus() != CartExamineResponse.STATUS_OK) {
            // if (logger.isDebugEnabled()) logger.debug("无法加入购物车: " + response.getStatusText());
            throw new CartOpException(response.getStatusText());
        }
    }

    /**
     * 根据uuid获取购物车，如果用户已登录则把uuid包含购物车数据则合并到登录用户的购物车中
     *
     * @param cartuuid 购物车id
     * @return 购物车数据对象，如果找不到返回null
     * @throws SQLException
     */
    public CartBox getCartBox(String cartuuid, HttpServletResponse response) throws SQLException {
        String identity = findIdentity(cartuuid, response);
        CartBox box = cartDataManager.getBox(identity);
        if (isUserCartIdentity(identity)) {
            // 合并cookie中的已有数据，并删除cookie
            CartBox cookieCartBox = cartDataManager.getBox(cartuuid);
            if (cookieCartBox != null) {
                if (box == null) {
                    box = new CartBox();
                }
                box.addAll(cookieCartBox);
                cartDataManager.setBox(identity, box);
                CookieUtils.removeCookieFromRoot(response, CartService.CART_UUID);
            }
        } else {
            logger.debug("add cookie: " + identity);
            // 添加cookie
            CookieUtils.addCookieToRoot(response, cartDataManager.getExpireDay() * 24 * 60 * 60, CART_UUID, identity);
        }
        boolean needUpdate = checkAndInjectItemInfo(box);
        if (needUpdate) {
            if (logger.isDebugEnabled()) {
                logger.debug("new box object set: box: " + box.toString());
            }
            cartDataManager.setBox(identity, box);
        }
        return box;
    }


    /**
     * 商品加入购物车
     *
     * @param cartItem
     * @param cartuuid
     * @param response
     * @return
     * @throws SQLException
     */
    public CartBox addCartItem(CartItem cartItem, final String cartuuid, HttpServletResponse response) throws SQLException {
        String identity = findIdentity(cartuuid, response);

        // 加入购物车
        CartBox box = cartDataManager.addItem(identity, cartItem);

        if (isUserCartIdentity(identity)) {
            // 合并cookie中的已有数据，并删除cookie
            CartBox cookieCartBox = cartDataManager.getBox(cartuuid);
            if (cookieCartBox != null) {
                box.addAll(cookieCartBox);
                cartDataManager.setBox(identity, box);
                CookieUtils.removeCookieFromRoot(response, CartService.CART_UUID);
            }
        } else {
            // 添加cookie
            CookieUtils.addCookieToRoot(response, cartDataManager.getExpireDay() * 24 * 60 * 60, CART_UUID, identity);
        }
        return box;
    }


    /**
     * 删除购物车数据
     *
     * @param cartuuid
     * @param response
     * @throws SQLException
     */
    public void delCartBox(String cartuuid, HttpServletResponse response) throws SQLException {
        String identity = findIdentity(cartuuid, response);
        cartDataManager.delBox(identity);
    }


    /**
     * 用于在下订单后删除已加入订单的购物车商品
     *
     * @param itemKeys
     * @param userId
     */
    public void delCartItems(List<String> itemKeys, Long userId) {
        String identity = getUserCartIdentity(userId);
        CartBox cartBox = cartDataManager.getBox(identity);
        if (cartBox == null) return;

        for (String itemKey : itemKeys) {
            CartItem cartItem = cartBox.find(itemKey);
            cartBox.del(cartItem);
        }

        if (cartBox.getCartGroups().size() == 0) {
            cartDataManager.delBox(identity);
        } else {
            cartDataManager.setBox(identity, cartBox);
        }
    }


    /**
     * 检查项目是否都在购物车中
     *
     * @param itemKeys
     * @param userId
     * @return
     */
    public boolean checkCartItems(List<String> itemKeys, Long userId) {
        String identity = getUserCartIdentity(userId);
        CartBox cartBox = cartDataManager.getBox(identity);
        if (cartBox == null) return false;
        for (String itemKey : itemKeys) {
            CartItem cartItem = cartBox.find(itemKey);
            if (cartItem == null) return false;
        }
        return true;
    }


    /**
     * 删除购物车中某个商品
     *
     * @param itemKey
     * @param cartuuid
     * @param response
     * @throws SQLException
     */
    public void delCartItem(String itemKey, String cartuuid, HttpServletResponse response) throws SQLException {
        String identity = findIdentity(cartuuid, response);

        CartBox cartBox = cartDataManager.getBox(identity);
        if (cartBox == null) throw new CartOpException("购物车数据不存在！");

        CartItem cartItem = cartBox.find(itemKey);
        cartBox.del(cartItem);

        if (cartBox.getCartGroups().size() == 0) {
            cartDataManager.delBox(identity);
        } else {
            cartDataManager.setBox(identity, cartBox);
        }

        // 更新cookie时间
        if (!isUserCartIdentity(identity)) {
            CookieUtils.addCookieToRoot(response, cartDataManager.getExpireDay() * 24 * 60 * 60, CART_UUID, identity);
        }
    }

    /**
     * 修改购物车的某商品数量
     *
     * @param itemKey
     * @param count
     * @param cartuuid
     * @param response
     * @throws SQLException
     */
    public void changeCartItemCount(String itemKey, int count, String cartuuid, HttpServletResponse response) throws SQLException {
        String identity = findIdentity(cartuuid, response);

        CartBox cartBox = cartDataManager.getBox(identity);
        if (cartBox == null) throw new CartOpException("购物车数据不存在！");

        CartItem cartItem = cartBox.find(itemKey);

        // 获取商品信息
        JSONObject item = orderService.getItemFromInterface(cartItem.getItemId());
        if (item == null) {
            throw new CartOpException("商品不存在");
        }

        int stockNum = item.getJSONObject("item").getInt("stocknum");
        if (stockNum > 0 && count > stockNum) {
            throw new CartOpException("购买的数量超出库存数");
        }
        cartItem.setQuantity(count);
        cartBox.replace(cartItem);

        cartDataManager.setBox(identity, cartBox);

        // 更新cookie时间
        if (!isUserCartIdentity(identity)) {
            CookieUtils.addCookieToRoot(response, cartDataManager.getExpireDay() * 24 * 60 * 60, CART_UUID, identity);
        }
    }


    /**
     * 从购物车中挑选出给定的商品
     *
     * @param cartuuid
     * @param itemKeys 要选出的商品信息唯一值数组
     * @return
     * @throws SQLException
     */
    public List<CartItem> findConfirmItems(String cartuuid, String[] itemKeys, HttpServletResponse response) throws SQLException {
        List<CartItem> cartItems = new ArrayList<CartItem>();

        if (itemKeys == null || itemKeys.length == 0) {
            return cartItems;
        }

        String identity = findIdentity(cartuuid, response);
        CartBox cartBox = cartDataManager.getBox(identity);
        boolean needUpdate = checkAndInjectItemInfo(cartBox);
        if (needUpdate) {
            cartDataManager.setBox(identity, cartBox);
        }

        if (cartBox == null) return cartItems;

        GroupKey groupKey = null;
        for (String itemKey : itemKeys) {
            CartItem cartItem = cartBox.find(itemKey);
            if (cartItem == null) {
                continue;
            }
            if (groupKey == null) {
                groupKey = cartItem.getGroupKey();
            }
            // 只返回一家商铺的商品
            if (!groupKey.equals(cartItem.getGroupKey())) {
                continue;
            }
            cartItems.add(cartItem);
        }
        return cartItems;
    }


    /**
     * 检查并注入item信息
     *
     * @param cartBox
     * @return 是否修改了cartbox
     */
    private boolean checkAndInjectItemInfo(CartBox cartBox) {
        if (cartBox == null) return false;
        boolean boxChanged = false;

        Map<String, ItemSaleDataDTO> itemInfo = Maps.newHashMap();

        List<CartGroup> cartGroupItems = cartBox.getCartGroups();
        Iterator<CartGroup> iterCartGroupItem = cartGroupItems.iterator();
        // shop iter;
        while (iterCartGroupItem.hasNext()) {
            CartGroup cartGroup = iterCartGroupItem.next();
            List<CartItem> cartItems = cartGroup.getCartItems();
            Iterator<CartItem> iterCartItem = cartItems.iterator();
            // item iter;
            while (iterCartItem.hasNext()) {
                CartItem cartItem = iterCartItem.next();
                String itemId = cartItem.getItemId();
                ItemSaleDataDTO itemSale;
                if (!itemInfo.containsKey(itemId)) {
//                    obj = orderService.getItemFromInterface(itemId);
                    itemSale = thirdInterDao.getItemById(itemId);
                    itemInfo.put(itemId, itemSale);
                } else {
                    itemSale = itemInfo.get(itemId);
                }
                if (itemSale == null) {
                    if (logger.isDebugEnabled()) {
                        logger.debug("itemId: " + itemId + " cannot get item info. remove from cartbox.");
                    }
                    iterCartItem.remove();
                    boxChanged = true;
                } else {
                    cartItem.setItemInfo(itemSale);
                }
            }

            if (cartGroup.getCartItems().size() == 0) {
                iterCartGroupItem.remove();
            } else {
//                JSONObject firstItemInfo = cartShopItem.getCartItems().get(0).getItemInfo();
                ItemSaleDataDTO firstItemInfo = cartGroup.getCartItems().get(0).getItemInfo();
//                String shopName = firstItemInfo.getJSONObject("item").getString("storeName");
                String shopName = firstItemInfo.getItem().getStoreName();
                cartGroup.setShopName(shopName);
            }
        }
        return boxChanged;
    }


    private String findIdentity(String cartuuid, HttpServletResponse response) {
        SessionUser sessionUser = SessionUser.getSessionUser(response);
        String identity;
        // 未登录用户，返回指定的购物车标识或随机生成标识
        if (sessionUser == null) {
            // 检查购物车标识，如果不存在或非法则创建一个新的标识
            identity = cartuuid;
            if (!matchUUID(identity)) {
                identity = UUID.randomUUID().toString();
            }
        } else {
            // 已登录用户使用用户id生成标识
            identity = getUserCartIdentity(sessionUser.getId());
        }
        return identity;
    }

    /**
     * 根据用户id生成购物车的key。
     * @param userId
     * @return 标识以u开头，后面跟用户id。
     */
    private String getUserCartIdentity(Long userId) {
        if (userId == null) throw new IllegalArgumentException("empty userId");
        return "u".concat(userId.toString());
    }

    /**
     * 判断标识是否是用户id的购物车标识
     * @param identity 购物车标识
     * @return
     */
    private boolean isUserCartIdentity(String identity) {
        if (identity == null) return false;
        if (identity.charAt(0) == 'u') return true;
        return false;
    }


    /**
     * 购物车id有效判断
     *
     * @param uuid 购物车id
     * @return 是否有效
     */
    private boolean matchUUID(String uuid) {
        return uuid != null && UUID_REGX.matcher(uuid).matches();
    }


}
