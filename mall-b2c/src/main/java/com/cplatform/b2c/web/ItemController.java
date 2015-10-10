package com.cplatform.b2c.web;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.model.ItemGroupInfo;
import com.cplatform.b2c.model.ItemInfo;
import com.cplatform.b2c.model.PageInfo;
import com.cplatform.b2c.model.TItemComment;
import com.cplatform.b2c.model.TMemberFavorite;
import com.cplatform.b2c.model.TSysType;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.service.AdService;
import com.cplatform.b2c.service.ItemService;
import com.cplatform.b2c.service.OrderService;
import com.cplatform.b2c.service.VSearchGoodService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.CTime;
import com.cplatform.b2c.util.CommonUtils;
import com.cplatform.b2c.util.PathUtil;
import com.cplatform.b2c.util.TimeUtil;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.sso.lmsh.bean.LoginUserBean;

/**
 * 商品评论控制层类 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) May 29, 2013 4:24:17 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
@Controller
public class ItemController {

	@Autowired
	private ItemService itemService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private PathUtil pathUtil;

	@Autowired
	private VSearchGoodService vSearchGoodService;

	@Autowired
	private AppConfig appConfig;

	@SuppressWarnings("unused")
	private final Log log = LogFactory.getLog(ItemController.class);

	/**
	 * 获取商品评论
	 * 
	 * @param response
	 * @param saleId
	 */

	@RequestMapping(value = "/item/getRemark")
	@ResponseBody
	public String getReMark(@RequestParam("saleId") String saleId, HttpServletResponse response) {

		String html = "<html><head></head><body>%s</body></html>";
		if (!StringUtils.isEmpty(saleId)) {
			VSearchGood vSearchGood = vSearchGoodService.get(saleId);

			if (vSearchGood != null)
				return String.format(html, vSearchGood.getGRemark());
			else
				return String.format(html, "");
		} else {

			return String.format(html, "");
		}
	}

	@RequestMapping(value = "/item/comments")
	public void getGoodsComments(
	//
	        HttpServletResponse response, //
	        @RequestParam("saleId") String saleId, @RequestParam("curpage") String curpage) {
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		response.setContentType("text/json; charset=GBK");

		List<Object[]> comments = itemService.getItemComments(pageInfo, saleId);
		for (Object[] cmt : comments) {
			if (cmt[2] == null || StringUtil.isEmpty(cmt[2].toString().trim())) {
				cmt[2] = CommonUtils.hideTerminalid((String) cmt[11], 5, 3, "*");
			}
			if (cmt[9] == null || StringUtil.isEmpty(cmt[9].toString().trim())) {
				if (cmt[12] != null && !StringUtil.isEmpty(cmt[12].toString().trim())) {
					cmt[9] = CommonUtils.hideTerminalid((String) cmt[12], 5, 3, "*");
				} else {
					cmt[9] = "";
				}
			}
		}

		List<Object[]> cc = new ArrayList<Object[]>();
		JSONObject obj = orderService.getItemFromInterface(saleId);
		String rank = "0";
		String star = "0";
		if (obj != null) {
			JSONObject item = (JSONObject) obj.get("item");
			rank = item.getString("rank");
			if (rank.indexOf(".") > -1) {
				String dot = rank.substring(rank.indexOf(".") + 1);
				if (Integer.valueOf(dot) >= 5) {
					star = rank.substring(0, rank.indexOf(".")) + "5";
				} else {
					star = rank.substring(0, rank.indexOf("."));
				}
			}
		}
		String total = itemService.getTotalComment(saleId);
		cc.add(new Object[] { total, rank, star });
		Map<String, List<Object[]>> map = new HashMap<String, List<Object[]>>();
		map.put("co", comments);
		map.put("item", cc);
		map.put("scrip", itemService.getItemCommentsScript(pageInfo, saleId));
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取商品物流等信息
	 * 
	 * @param response
	 * @param saleId
	 * @deprecated
	 * @see com.cplatform.b2c.web.ProductController detail
	 */
	@Deprecated
	@RequestMapping(value = "/item/old_basicInfo")
	public void getBasicInfo(HttpServletRequest request, HttpServletResponse response, @RequestParam("saleId") String saleId,
	        @CookieValue(value = "areaCode", required = false) String areaCode) throws Exception {
		response.setContentType("text/json; charset=GBK");
		Map<String, String> map = new HashMap<String, String>();

		JSONObject obj = orderService.getItemFromInterface(saleId);

		if (obj != null) {
			JSONObject item = (JSONObject) obj.get("item");

			map.put("postarea", "江苏省苏州市吴中区");
			map.put("marketContent", item.getString("marketContent"));

			map.put("clicknum", item.getString("clicknum"));

			map.put("commentnum", item.getString("commentnum"));
			map.put("isValid", item.getString("isValid"));

			map.put("usernum", item.getString("usernum"));
			map.put("collectnum", item.getString("collectnum"));
			map.put("stocknum", "库存" + item.getString("stocknum"));
			map.put("marketPrice", item.getString("marketPrice"));
			map.put("shopPrice", item.getString("shopPrice"));
			map.put("logisticsFee", item.getString("logisticsFee"));
			map.put("logisticsFeeType", item.getString("logisticsFeeType"));
			map.put("postFlag", item.getString("postFlag"));
			map.put("itemMode", item.getString("itemMode"));
			map.put("imgPath", item.getString("webPath"));

			int saleNum = NumberUtils.toInt(item.getString("salenum"), 0);
			// if(map.get("itemMode").equals("1")){ //虚拟商品
			// saleNum+=appConfig.getSaleNumDefault();
			// }
			map.put("salenum", String.valueOf(saleNum));

			map.put("startTime", TimeUtil.convertStringFormat(item.getString("startTime")));
			map.put("stopTime", TimeUtil.convertStringFormat(item.getString("stopTime")));

			String now = TimeUtil.now();
			if (Long.valueOf(now) < Long.valueOf(TimeUtil.formatDateString(item.getString("startTime")))
			        || Long.valueOf(now) > Long.valueOf(TimeUtil.formatDateString(item.getString("stopTime")))) {
				map.put("isSaleValid", "false");
			}
			map.put("iseckill", item.getString("iseckill"));
			map.put("iseckillPrice", item.getString("iseckillPrice"));

			map.put("userLevels", item.getString("userLevels"));

			StringBuffer memPrice = new StringBuffer();
			JSONArray itemPrice = obj.getJSONArray("itemPrice");
			LoginUserBean userinfo = null;
			userinfo = new SSOAgent(request, response).loginUserInfo("mall");
			String level = "L0";
			if (null != userinfo) {

				log.debug("----userinfo------:" + userinfo.getTerminalId());
				log.debug("----userinfo------:" + userinfo.getRedMember());
				level = itemService.getMemberLevel(userinfo.getRedMember());
			}
			memPrice.append("<li id=\"summary-price\">");
			memPrice.append("<div class=\"dt\">商城价：</div>");
			memPrice.append("<div class=\"dd\"><strong class=\"p-price\">").append("<span class=\"col_link\">");
			memPrice.append("￥").append(item.getString("shopPrice")).append(StringUtils.equals("L0", level) ? "</span>" : "")
			        .append("</strong><em id=\"a-tips\">&nbsp;</em></div>");
			memPrice.append("</li>");
			for (int i = 0; i < itemPrice.size(); i++) {
				JSONObject price = itemPrice.getJSONObject(i);
				String priceTypeCode = price.getString("priceTypeCode");
				boolean flag = StringUtils.equals(priceTypeCode, level);
				if (priceTypeCode.equals("L1")) {
					memPrice.append("<li id=\"red_price\" style=\"display: list-item;\">");
				} else {
					memPrice.append("<li style=\"display: list-item;\">");
				}

				memPrice.append("<div class=\"dt\">").append(price.getString("priceType")).append("：</div>");
				memPrice.append("<div class=\"dd\"><strong class=\"p-price\">")
				// .append(flag ? "<span class=\"col_link\">" : "")
				        .append("￥").append(price.getString("price")).append(flag ? "</span>" : "").append("</strong></div>");
				memPrice.append("</li>");
			}
			map.put("memPrice", memPrice.toString());
			map.put("isRedMember", level);
			List<TSysType> types = itemService.makeMenuTab(item.getString("typeId"));
			int i = 0;
			StringBuffer nav = new StringBuffer();
			nav.append("<span>");
			for (TSysType type : types) {
				if (i > 0) {
					nav.append("&nbsp;&gt;&nbsp;");
				}
				nav.append("<a href=\"../search/gotoSearch.chtml?sort=0&keyword=").append(type.getName()).append("&type_id=").append(type.getId())
				        .append("&region_code=" + AdService.AREA_REGION.get(areaCode)).append("&curpage=1\">").append(type.getName()).append("</a>");
				i++;
			}
			nav.append("&nbsp;&gt;&nbsp;商品详情</span>");
			map.put("nav", nav.toString());

			map.put("saleNum", "10件");
			// 价格类型priceTypeCode;

			// 价格类型中文名priceType;

			// 价格price;

			PrintWriter out;
			try {
				out = response.getWriter();
				JSONObject json = JSONObject.fromObject(map);
				if (json != null) {
					out.print(json);
				}
				out.flush();
			}
			catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	/**
	 * 获取商品浏览历史记录
	 * 
	 * @param response
	 * @param saleIds
	 * @modify by zhangdong 2014/04/15
	 */
	@RequestMapping(value = "/item/historyItem")
	@ResponseBody
	public String getHistoryItem(@RequestParam("saleIds") String saleIds) {
		List<ItemSaleDataDTO> list = itemService.getHistoryItems(saleIds);
		JSONArray json = JSONArray.fromObject(list);
		return json.toString();
	}

	/**
	 * 获取商品成交记录
	 * 
	 * @param response
	 * @param saleId
	 * @param curpage
	 */
	@RequestMapping(value = "/item/purchaseRecords")
	public void getPurchaseRecords(HttpServletResponse response, @RequestParam("saleId") String saleId, @RequestParam("curpage") String curpage) {
		response.setContentType("text/json; charset=GBK");
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		Map<String, List<Object[]>> map = new HashMap<String, List<Object[]>>();
		List<Object[]> records = itemService.getPurchaseRecords(pageInfo, saleId);
		map.put("purchases", records);
		map.put("scrip", itemService.getPurchaseRecordsScript(pageInfo, saleId));
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取该商品的优惠套餐
	 * 
	 * @param response
	 * @param saleId
	 * @return
	 */
	@RequestMapping(value = "/item/distountItems")
	public void getDistountItems(HttpServletResponse response, @RequestParam("saleId") String saleId) {
		response.setContentType("text/json; charset=GBK");
		JSONObject obj = itemService.getGroupFromInterface(saleId);
		Map<String, List<ItemGroupInfo>> map = new HashMap<String, List<ItemGroupInfo>>();
		if (obj != null) {
			JSONArray groups = obj.getJSONArray("group");

			DecimalFormat df = new DecimalFormat("#0.00");
			List<ItemGroupInfo> groupInfos = new ArrayList<ItemGroupInfo>();
			for (int i = 0; i < groups.size(); i++) {
				JSONObject group = groups.getJSONObject(i);
				JSONArray items = group.getJSONArray("items");
				float totalPrice = 0;
				ItemGroupInfo ig = new ItemGroupInfo();
				List<ItemInfo> infos = new ArrayList<ItemInfo>();
				for (int j = 0; j < items.size(); j++) {
					ItemInfo info = new ItemInfo();
					JSONObject item = items.getJSONObject(j);
					String id = item.getString("id");
					info.setId(id);
					info.setShortName(item.getString("shortName"));
					info.setImgPath(pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, Long.valueOf(id)) + "N5/" + item.getString("webPath"));
					info.setItemPath(pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(id)));
					String price = item.getString("shopPrice");
					info.setShopPrice(price);
					totalPrice += Float.valueOf(price);
					infos.add(info);
				}

				JSONObject info = group.getJSONObject("info");
				ItemInfo groupInfo = new ItemInfo();
				groupInfo.setId(info.getString("id"));
				String groupPrice = info.getString("shopPrice");
				groupInfo.setShopPrice(groupPrice);
				float discountPrice = totalPrice - Float.valueOf(groupPrice);
				groupInfo.setDiscountPrice(df.format(discountPrice));
				groupInfo.setImgPath(pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, Long.valueOf(saleId)));
				groupInfo.setItemPath(pathUtil.getPathById(PathUtil.TYPE_ITEM, Long.valueOf(saleId)));
				ig.setGroupItem(groupInfo);
				ig.setItems(infos);
				groupInfos.add(ig);
			}
			map.put("group", groupInfos);
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 获取咨询
	 * 
	 * @param response
	 * @param saleId
	 * @param curpage
	 */
	@RequestMapping(value = "/item/consults")
	public void getConsults(HttpServletResponse response, @RequestParam("saleId") String saleId, @RequestParam("curpage") String curpage) {
		response.setContentType("text/json; charset=GBK");
		PageInfo pageInfo = new PageInfo();
		pageInfo.setCurPage(Integer.valueOf(curpage));
		Map<String, List<Object[]>> map = new HashMap<String, List<Object[]>>();
		List<Object[]> a = itemService.getItemConsults(pageInfo, saleId);
		map.put("consults", a);
		map.put("scrip", itemService.getItemConsultsScript(pageInfo, saleId));
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存评价是否有用
	 * 
	 * @param response
	 * @param isUse
	 */
	@RequestMapping(value = "/item/isUse")
	public void isUse(HttpServletResponse response, @RequestParam("commentId") String commentId, @RequestParam("isUse") String isUse) {
		response.setContentType("text/json; charset=GBK");

		Map<String, String> map = new HashMap<String, String>();
		map.put("flag", itemService.saveIsUse(commentId, isUse) ? "1" : "0");
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 保存咨询
	 * 
	 * @param response
	 * @param saleId
	 * @param post
	 * @param content
	 */
	@RequestMapping(value = "/item/saveConsults")
	public void saveConsults(HttpServletRequest request, HttpServletResponse response, @RequestParam("saleId") int saleId,
	        @RequestParam("post") int post, @RequestParam("content") String content, @RequestParam("shopId") String shopId) {
		response.setContentType("text/json; charset=GBK");
		TItemComment comment = new TItemComment();
		comment.setSaleId(saleId);
		comment.setType(2);
		comment.setStatus(0);
		comment.setQuestionType(post);
		comment.setContent(content);
		comment.setStoreId(Long.valueOf(shopId));
		LoginUserBean userinfo = null;
		Map<String, String> map = new HashMap<String, String>();
		userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (null != userinfo && userinfo.getId() != null) {
			// comment.setUserId(150530615);
			comment.setUserId(Integer.valueOf(userinfo.getId().toString()));
			comment.setNickname(StringUtils.isEmpty(userinfo.getNickName()) ? userinfo.getId().toString() : userinfo.getNickName());
			comment.setUpdateTime(CTime.getTime(14));
			map.put("flag", itemService.saveComment(comment) ? "1" : "0");
		} else {
			map.put("flag", "2");
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新商品收藏数量
	 * 
	 * @param response
	 * @param saleId
	 */
	@RequestMapping(value = "/item/updateCollect")
	public void updateCollectNum(HttpServletRequest request, HttpServletResponse response, @RequestParam("saleId") String saleId) {
		LoginUserBean userinfo = null;
		userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		Map<String, String> map = new HashMap<String, String>();
		response.setContentType("text/json; charset=GBK");
		TMemberFavorite favorite = new TMemberFavorite();
		favorite.setFavoriteId(Integer.valueOf(saleId));
		favorite.setFavoriteType(1);
		favorite.setUpdateTime(CTime.getTime(14));
		if (null != userinfo && userinfo.getId() != null) {
			favorite.setUserId(Integer.valueOf(userinfo.getId().toString()));
			if (itemService.isCollect(favorite)) {
				map.put("flag", "2");
			} else {
				boolean flag = itemService.updateCollectNum(saleId, favorite);
				map.put("flag", flag ? "1" : "0");
			}
		} else {
			map.put("flag", "3");
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

	/**
	 * 更新商品收藏数量
	 * 
	 * @param response
	 */
	@RequestMapping(value = "/item/favStore")
	public void saveFavorite(HttpServletRequest request, HttpServletResponse response, @RequestParam("shopId") String shopId) {
		response.setContentType("text/json; charset=GBK");
		TMemberFavorite favorite = new TMemberFavorite();
		favorite.setFavoriteId(Integer.valueOf(shopId));
		favorite.setFavoriteType(2);
		favorite.setUpdateTime(CTime.getTime(14));
		LoginUserBean userinfo = null;
		userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		Map<String, String> map = new HashMap<String, String>();
		if (null != userinfo && userinfo.getId() != null) {
			favorite.setUserId(Integer.valueOf(userinfo.getId().toString()));
			// favorite.setUserId(150530615);
			if (itemService.isCollect(favorite)) {
				map.put("flag", "2");
			} else {
				boolean flag = itemService.updateCollectNum(shopId, favorite);
				map.put("flag", flag ? "1" : "0");
			}
		} else {
			map.put("flag", "3");
		}
		PrintWriter out;
		try {
			out = response.getWriter();
			JSONObject json = JSONObject.fromObject(map);
			if (json != null) {
				out.print(json);
			}
			out.flush();
		}
		catch (IOException e) {
			e.printStackTrace();
		}
	}

}
