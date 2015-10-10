//import com.cplatform.b2c.cart.CartBox;
//import com.cplatform.b2c.cart.CartItem;
//import com.cplatform.b2c.cart.CartShopItem;
//import com.cplatform.dbhelp.DbHelper;
//import com.google.common.collect.Lists;
//import net.sf.json.JSONObject;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.sql.SQLException;
//import java.util.HashMap;
//import java.util.Map;
//
///**
// * Title. <br>
// * Description.
// * <p/>
// * Copyright: Copyright (c) 13-6-14 下午3:41
// * <p/>
// * Company: 北京宽连十方数字技术有限公司
// * <p/>
// * Author: nicky
// * <p/>
// * Version: 1.0
// * <p/>
// */
//@Transactional
//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = { "/spring-configuration/*.xml"})
//public class TesTest {
//
//    @Autowired
//    DbHelper dbHelper;
//
//
//    @Test
//    public void x () throws SQLException {
//        CartBox cb = new CartBox();
//
//        CartItem ci = new CartItem("1", "2", 3, null);
//        CartItem ci2 = new CartItem("2", "3", 4, null);
//
//        CartShopItem csi = new CartShopItem("9");
//        csi.setCartItems(Lists.newArrayList(ci, ci2));
//        cb.setShopItems(Lists.newArrayList(csi));
//
//        String y = cb.toString();
//
//        Map<String, Class> m = new HashMap<String, Class>();
//        m.put("shopItems", CartShopItem.class);
//        m.put("cartItems", CartItem.class);
//
//        CartBox z = (CartBox) JSONObject.toBean(JSONObject.fromObject(y), CartBox.class, m);
//        for (CartShopItem cartShopItem : z.getShopItems()) {
//            System.out.println(cartShopItem);
//            for (CartItem cartItem : cartShopItem.getCartItems()) {
//                System.out.println(cartItem);
//            }
//        }
//
//
///*
//        ResultCode rc = tairManager.put(0, "base1", z);
//        if (rc.isSuccess()) {
//            System.out.println("成功");
//        } else if (ResultCode.VERERROR.equals(rc)) {
//            System.out.println("版本错误的处理代码");
//        } else {
//            System.out.println("其他失败的处理代码");
//        }
//
//
//        Result<DataEntry> result = tairManager.get(0, "base1");
//        if (result.isSuccess()) {
//            DataEntry entry = result.getValue();
//            if(entry != null) {
//                System.out.println((CartBox) entry.getValue());
//                // 数据存在
//            } else {
//                System.out.println("not exist");
//                // 数据不存在
//            }
//        } else {
//            System.out.println("error");
//            // 异常处理
//        }*/
//    }
//
//
//}
