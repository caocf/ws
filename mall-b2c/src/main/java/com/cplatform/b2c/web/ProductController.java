package com.cplatform.b2c.web;

import static com.cplatform.b2c.util.Constants.PANIC_BUYING;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.dto.MarketGoodsDTO;
import com.cplatform.b2c.model.SessionUser;
import com.cplatform.b2c.model.TSysType;
import com.cplatform.b2c.repository.ThirdInterDao;
import com.cplatform.b2c.service.AdService;
import com.cplatform.b2c.service.ItemService;
import com.cplatform.b2c.service.MapService;
import com.cplatform.b2c.service.ProductService;
import com.cplatform.b2c.service.ShopService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.JsonMapper;
import com.cplatform.b2c.util.MediaTypes;
import com.cplatform.b2c.util.PathUtil;
import com.cplatform.b2c.util.TimeUtil;
import com.google.common.collect.Maps;

/**
 * User: cuikai Date: 13-12-9 Time: 下午2:33
 */
@Controller
@RequestMapping(value = "/item")
public class ProductController {

	private final Logger logger = Logger.getLogger(ProductController.class);

	private final static String HENAN_ITEM_SOURCE = "4";// 河南普通商品SOURCE

	private final static String HENAN_COUPON_SOURCE = "5";// 河南优惠券SOURCE

	private final static String DJQ_ISECKILL = "5";// 代金券iseckill=5

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private ItemService itemService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private MapService mapService;

	@Autowired
	private ShopService shopService;

	@Autowired
	private ProductService productService;

	@Autowired
	private PathUtil pathUtil;

	@RequestMapping(value = "basicInfo", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public String detail(@RequestParam("saleId") String saleId, @CookieValue(value = "areaCode", required = false) String areaCode,
	        HttpServletResponse response) throws Exception {

		JsonMapper mapper = JsonMapper.buildNormalMapper();

		SessionUser user = SessionUser.getSessionUser(response);
		String level = (user != null && user.getRedMember() == 1) ? Constants.RED_USER_LEVELS : "L0";

		Map<String, String> map = Maps.newHashMap();

		ItemSaleDataDTO itemSale = thirdInterDao.getItemById(saleId);
		if (itemSale != null && itemSale.getItem() != null) {

			ItemSaleDataDTO.Item item = itemSale.getItem();

			map.put("marketContent", item.getMarketContent());
			map.put("clicknum", String.valueOf(item.getClicknum()));
			map.put("commentnum", String.valueOf(item.getCommentnum()));
			map.put("isValid", item.getIsvalid());
			map.put("usernum", String.valueOf(item.getUsernum()));
			map.put("collectnum", String.valueOf(item.getCollectnum()));
			map.put("stocknum", "库存" + getStockNum(itemSale));
			map.put("marketPrice", String.valueOf(item.getMarketPrice()));
			map.put("shopPrice", String.valueOf(item.getShopPrice()));
			map.put("logisticsFee", String.valueOf(item.getLogisticsFee()));
			map.put("logisticsFeeType", String.valueOf(item.getLogisticsFeeType()));
			map.put("postFlag", String.valueOf(item.getPostFlag()));
			map.put("itemMode", String.valueOf(item.getItemMode()));
			map.put("imgPath", item.getWebPath());
			map.put("startTime", TimeUtil.convertStringFormat(item.getStartTime()));
			map.put("stopTime", TimeUtil.convertStringFormat(item.getStopTime()));
			map.put("iseckill", item.getIseckill());
			map.put("iseckillPrice", String.valueOf(item.getIseckillPrice()));
			map.put("userLevels", item.getUserLevels());
			map.put("share", getJiaThisButton());
			map.put("userPerBuyNum", String.valueOf(item.getUserPerBuyNum()));
			// 判断是河南的还是江苏的商品价格（河南普通商品）
			if (HENAN_ITEM_SOURCE.equals(item.getSource())) {
				map.put("memPrice", getHeNanPrice(itemSale));
			} else if (HENAN_COUPON_SOURCE.equals(item.getSource())) {
				map.put("memPrice", "");
			} else {
				// 添加限时抢购商品判断
				if (Constants.PROMPT_BUYING.equals(item.getIseckill())) {
					map.put("memPrice", productService.getPromptPrice(level, itemSale));
				} else {
					map.put("memPrice", getPrice(level, itemSale));
				}
			}
			map.put("isRedMember", level);
			map.put("salenum", String.valueOf(getSaleNum(item) + "件"));
			map.put("nav", getNav(item, areaCode, item.getSource(), item.getIseckill()));
			map.put("promotionEndTime", getPromotionEndTimeMills(itemSale));
			// 限时抢购商品
			if (Constants.PROMPT_BUYING.equals(item.getIseckill())) {
				map.put("promotionStartTime", getPromotionStartTimeMills(itemSale));
			}
			map.put("source", item.getSource());

			// 设置代金券详情页面改商店其他商品信息
			if (DJQ_ISECKILL.equals(item.getIseckill())) {
				// 默认取agent_store_id
				long storeId = item.getAgentStoreId();
				if (storeId <= 0) {
					map.put("othersItems", getOtherItemsByShopId(item.getStoreId(), item.getId(), 1));
					map.put("shop_url", pathUtil.getPathById(PathUtil.TYPE_SHOP, Long.valueOf(item.getStoreId())));
				} else {
					map.put("othersItems", getOtherItemsByShopId(storeId, item.getId(), 2));
					map.put("shop_url", pathUtil.getPathById(PathUtil.TYPE_SHOP, storeId));
				}

			}

			// 判断该商品有没有添加验证门店
			List<Map<String, String>> list = mapService.verifyShop(Long.parseLong(saleId));
			map.put("verify_shop", (list != null && list.size() > 0) ? "true" : "false");

			return mapper.toJson(map);
		} else {
			return null;
		}
	}

	/**
	 * 设置限时抢购开始时间
	 * 
	 * @param item
	 * @return
	 */
	private String getPromotionStartTimeMills(ItemSaleDataDTO item) {
		if (Constants.PROMPT_BUYING.equals(item.getItem().getIseckill())) {
			return String.valueOf(TimeUtil.fromToDate(TimeUtil.format(item.getItem().getStartTime(), TimeUtil.TARGET_1)).getTime());
		}
		return item.getItem().getStartTime();
	}

	private String getPromotionEndTimeMills(ItemSaleDataDTO item) {
		if (PANIC_BUYING.equals(item.getItem().getIseckill())) {
			MarketGoodsDTO marketGoods = item.getMarketGoodsProperty();
			if (marketGoods != null) {
				return String.valueOf(TimeUtil.fromToDate(marketGoods.getMarketPromotion().getPromotionEndTime()).getTime());
			}
		} else if (Constants.PROMPT_BUYING.equals(item.getItem().getIseckill())) {
			return String.valueOf(TimeUtil.fromToDate(TimeUtil.format(item.getItem().getStopTime(), TimeUtil.TARGET_1)).getTime());
		}
		return item.getItem().getStopTime();

	}

	@RequestMapping(value = "json/now", produces = MediaTypes.JSON_UTF_8)
	@ResponseBody
	public String now() {

		return "{\"now\":" + System.currentTimeMillis() + "}";
	}

	private int getSaleNum(ItemSaleDataDTO.Item item) {
		int saleNum = item.getSalenum();
		// if (item.getItemMode() == 1) { // 虚拟商品
		// saleNum += appConfig.getSaleNumDefault();
		// }
		return saleNum;
	}

	private int getStockNum(ItemSaleDataDTO item) {
		// MarketGoodsDTO marketGoodsDTO = item.getMarketGoodsProperty();
		// if (PANIC_BUYING.equals(item.getItem().getIseckill()) &&
		// marketGoodsDTO != null) {
		//
		// return item.getMarketGoodsProperty().getPromotionStock();
		//
		// } else {
		return item.getItem().getStocknum();
		// }
	}

	private String getPrice(String level, ItemSaleDataDTO item) {
		StringBuffer memPrice = new StringBuffer();

		BigDecimal market_price = item.getItem().getMarketPrice();
		BigDecimal shop_price = item.getItem().getShopPrice();
		if (market_price.compareTo(shop_price) > 0) {
			memPrice.append("<li id=\"summary-market-price\">").append("<div class=\"dt\">市场价：</div>")
			        .append("<div class=\"dd\"><span class=\"breaktext\">￥");
			memPrice.append(market_price);
			memPrice.append("</span></div></li>");
		}
		memPrice.append("<li id=\"summary-price\">").append("<div class=\"dt\">商城价：</div>").append("<div class=\"dd\"><strong class=\"p-price\">")
		        .append("<span class=\"col_link\">").append("￥").append(shop_price).append(StringUtils.equals("L0", level) ? "</span>" : "")
		        .append("</strong><em id=\"a-tips\">&nbsp;</em></div>").append("</li>");
		// 注销限时抢购功能代码
		// if (PANIC_BUYING.equals(item.getItem().getIseckill())) {
		// MarketGoodsDTO marketGoods = item.getMarketGoodsProperty();
		// if (marketGoods != null) {
		// memPrice.append("<li>")
		// .append("<div class=\"two-col-price\">")
		// .append("<div class=\"price-widght\">")
		// .append("<label>限时特价：</label>")
		// .append("<span style=\"margin-right:10px;\">￥" +
		// String.format("%.2f", Float.valueOf(marketGoods.getPromotionPrice()))
		// + "</span>");
		// if (marketGoods.getBuyLimit() != null &&
		// marketGoods.getBuyLimit().contains("2")) {
		// memPrice.append("<a class=\"for_vip\" href=\"../static/vip/index.htm\">成为红钻会员</a>");
		// }
		// memPrice.append("</div>").append("</div>").append("</li>");
		// }
		// } else {

		List<ItemSaleDataDTO.ItemPrice> itemPrice = item.getItemPrice();
		for (ItemSaleDataDTO.ItemPrice price : itemPrice) {
			String priceTypeCode = price.getPriceTypeCode();
			boolean flag = StringUtils.equals(priceTypeCode, level);
			if (shop_price.compareTo(price.getPrice()) == 1) {
				if (priceTypeCode.equals("L1")) {
					memPrice.append("<li id=\"red_price\" style=\"display: list-item;\">");
				} else {
					memPrice.append("<li style=\"display: list-item;\">");
				}

				memPrice.append("<div class=\"dt\">").append(price.getPriceType()).append("：</div>")
				        .append("<div class=\"dd\"><strong class=\"p-price\">").append("￥").append(price.getPrice()).append(flag ? "</span>" : "");
				if (priceTypeCode.equals("L1")) {
					memPrice.append("</strong>&nbsp;<b style='color:#999999;font-weight: normal;'>(商盟会员、红钻会员仅支持江苏移动用户)</b>").append("</div>")
					        .append("</li>");
				} else {
					memPrice.append("</strong></div>").append("</li>");
				}

			}

		}
		// }
		return memPrice.toString();
	}

	/**
	 * 获取河南普通商品的原价和积分价格
	 * 
	 * @param item
	 *            商品详情内容
	 * @return 返回河南积分商品的价格
	 */
	public String getHeNanPrice(ItemSaleDataDTO item) {
		StringBuffer memPrice = new StringBuffer();
		if (item.getItem().getSource().equals("4")) {
			memPrice.append("<li id=\"summary-price\">").append("<div class=\"dt\">原&nbsp;&nbsp;价：</div>")
			        .append("<div class=\"dd\"><strong class=\"p-price\">").append("<span class=\"col_link\">").append("￥")
			        .append(item.getItem().getMarketPrice()).append("</span>").append("</strong><em id=\"a-tips\">&nbsp;</em></div>").append("</li>");
			// 计算积分价格, 后期改为存储为积分价格
			int score = 0;
			if (item.getItem().getShopPrice() != null) {
				score = item.getItem().getShopPrice().multiply(new BigDecimal(100)).intValue();
			}
			memPrice.append("<li id=\"summary-vipprice\"><div class=\"dt\">积&nbsp;分&nbsp;价：</div>")
			        .append("<div class=\"dd\"><strong class=\"p-price\" id=\"jd-price\">").append(score).append("</strong> 积分/M值 </div> </li>");

		}
		return memPrice.toString();
	}

	private String getNav(ItemSaleDataDTO.Item item, String areaCode, String source, String iseckill) {
		List<TSysType> types = itemService.makeMenuTab(String.valueOf(item.getTypeId()));
		String regionCode = AdService.AREA_REGION.get(areaCode);
		int i = 0;
		StringBuilder nav = new StringBuilder();
		nav.append("<span>");

		String baseHref = "<a href=\"../search/gotoSearch.chtml?sort=0&keyword=";
		// 代金券商品
		if ("5".equals(iseckill)) {
			baseHref = "<a href=\"http://quan.12580life.com/goods/goodsList.chtml?title=&subRegionCode=&price=&sort=0&isNewOrder=&allowScore=&allowCoin=&allowBill=";
		}
		// 代金券顶级分类
		String djqGoodTypeId = "";
		String tmpGt = "";
		for (TSysType type : types) {
			if (i > 0) {
				tmpGt = "&nbsp;&gt;&nbsp;";
			}
			if ("5".equals(iseckill)) {
				String typeName = type.getName();
				String typeParam = "&goodsType=&subGoodsType=";
				if (i == 0) {
					typeName = "代金券";
				} else if (i == 1) {
					djqGoodTypeId = type.getId() == null ? "" : type.getId().toString();
					typeParam = "&goodsType=" + djqGoodTypeId + "&subGoodsType=";
				} else if (i == 2) {
					typeParam = "&goodsType=" + djqGoodTypeId + "&subGoodsType=" + type.getId();
				} else {
					break;// 代金券只需要两级分类
				}
				nav.append(tmpGt).append(baseHref).append(typeParam).append("&regionCode=" + regionCode).append("&curpage=1\">").append(typeName)
				        .append("</a>");
			} else {
				nav.append(tmpGt).append(baseHref).append(type.getName()).append("&type_id=").append(type.getId())
				        .append("&region_code=" + regionCode).append("&curpage=1\">").append(type.getName()).append("</a>");
			}
			i++;
		}
		nav.append("&nbsp;&gt;&nbsp;商品详情</span>");
		return nav.toString();
	}

	private String getJiaThisButton() {
		return new StringBuilder()
		        .append(" <li style=\"margin-top:5px;\"><div class=\"jiathis_style\">\n")
		        .append("                        <span class=\"jiathis_txt\">分享到：</span>\n")
		        .append("                        <a class=\"jiathis_button_qzone\">QQ空间</a>\n")
		        .append("                        <a class=\"jiathis_button_tsina\">新浪微博</a>\n")
		        .append("                        <a class=\"jiathis_button_tqq\">腾讯微博</a>\n")
		        .append("                        <a class=\"jiathis_button_renren\">人人网</a>\n")
		        .append("                        <a class=\"jiathis_button_douban\">豆瓣</a>\n")
		        .append("                        <a class=\"jiathis_button_copy\">复制网址</a>\n")
		        .append("                        <a href=\"http://www.jiathis.com/share\" class=\"jiathis jiathis_txt jiathis_separator jtico jtico_jiathis\" target=\"_blank\">更多</a>\n")
		        .append("                        <a class=\"jiathis_counter_style\"></a>\n")
		        .append("                    </div>\n")
		        .append("                    <script type=\"text/javascript\" >\n")
		        .append("                        var jiathis_config={\n")
		        .append("                            siteNum:6,\n")
		        .append("                            sm:\"qzone,tsina,tqq,renren,douban\",\n")
		        .append("                            shortUrl:true,\n")
		        .append("                            hideMore:false\n")
		        .append("                        }\n")
		        .append("                    </script>\n")
		        .append("                    <script type=\"text/javascript\" src=\"http://v3.jiathis.com/code_mini/jia.js\" charset=\"utf-8\"></script></li>")
		        .toString();
	}

	public String getOtherItemsByShopId(Long storeId, Long saleId, int type) {
		List<Map<String, Object>> itemsList = new ArrayList<Map<String, Object>>();
		try {
			if (type == 1) {
				itemsList = shopService.getOtherItemsByShopId(storeId, saleId);
			} else {
				itemsList = shopService.getOtherAgentItemsByShopId(storeId, saleId);
			}
			StringBuffer html = new StringBuffer();
			if (itemsList != null && itemsList.size() > 0) {
				html.append("<ul>");
				for (int i = 0; i < itemsList.size(); i++) {
					Map<String, Object> itemMap = itemsList.get(i);
					html.append("<li>");
					html.append("<div class=\"djq_prod_new_price fl\">");
					html.append("<span>￥</span>" + ((BigDecimal) itemMap.get("shop_price")).setScale(2, BigDecimal.ROUND_UNNECESSARY) + "</div>");
					html.append("<div class=\"djq_prod_old_price fl\">￥"
					        + ((BigDecimal) itemMap.get("market_price")).setScale(2, BigDecimal.ROUND_UNNECESSARY) + "</div>");

					html.append("<div class=\"djq_prod_new_price fl\">");
					if (null != itemMap.get("vipprice") && "" != itemMap.get("vipprice")) {
						html.append("<span>VIP&nbsp;￥</span>");
						html.append(((BigDecimal) itemMap.get("vipprice")).setScale(2, BigDecimal.ROUND_UNNECESSARY));
					} else {
						html.append("&nbsp;");
					}
					html.append("</div>");
					html.append("<div class=\"djq_prod_title fl\">");
					String url = "../tools/item.chtml?saleId=" + itemMap.get("id");
					html.append("<a href=\"" + url + "\">" + itemMap.get("name") + "</a></div>");
					html.append("<div class=\"djq_detail_btn fr\">");
					html.append("<a href=\"" + url + "\">查看详情</a></div>");
					html.append("<div class=\"djq_saled_num fr\"><i></i>已售<span>" + itemMap.get("sale_num") + "</span>份</div>");
					html.append("<div class=\"djq_exc_methods fr\">");
					if (((BigDecimal) itemMap.get("cash_idgoods")).compareTo(new BigDecimal(1)) == 0) {
						html.append("<i class=\"icon1\"></i>");
					}
					if (((BigDecimal) itemMap.get("score_idgoods")).compareTo(new BigDecimal(1)) == 0) {
						html.append("<i class=\"icon2\"></i>");
					}
					if (((BigDecimal) itemMap.get("coin_idgoods")).compareTo(new BigDecimal(1)) == 0) {
						html.append("<i class=\"icon3\"></i>");
					}
					if (((BigDecimal) itemMap.get("bill_pay")).compareTo(new BigDecimal(1)) == 0) {
						html.append("<i class=\"icon4\"></i>");
					}

					html.append("</div>");
					html.append(" <div class=\"cb\"></div>");
					html.append("</li>");
				}
				html.append("</ul>");
				return html.toString();
			}
		}
		catch (Exception ex) {
			logger.error("静态化页面代金券详情页获取该商家其他代金券商品错误==" + ex.getMessage());
		}

		return null;
	}
}
