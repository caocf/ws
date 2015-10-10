//package com.cplatform.b2c.web;
//
//import java.io.File;
//import java.sql.SQLException;
//import java.util.ArrayList;
//import java.util.Arrays;
//import java.util.HashMap;
//import java.util.HashSet;
//import java.util.List;
//import java.util.Map;
//import java.util.Set;
//
//import net.rubyeye.xmemcached.MemcachedClient;
//
//import org.apache.commons.lang.ArrayUtils;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.scheduling.annotation.Scheduled;
//import org.springframework.stereotype.Controller;
//import org.springframework.ui.Model;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.ResponseBody;
//
//import com.alibaba.druid.util.StringUtils;
//import com.cplatform.b2c.model.TChannelCatalogRcmdConf;
//import com.cplatform.b2c.model.TChannelFloorConf;
//import com.cplatform.b2c.model.TChannelPicConf;
//import com.cplatform.b2c.model.TChannelTypeDTO;
//import com.cplatform.b2c.model.VSearchGood;
//import com.cplatform.b2c.service.AdService;
//import com.cplatform.b2c.service.CommonCacheService;
//import com.cplatform.b2c.service.MallService;
//import com.cplatform.b2c.util.AppConfig;
//import com.cplatform.b2c.util.SysTypeUtil;
//import com.cplatform.chead.MallHeader;
//
///**
// * 商户展示Controller
// * <p>
// * Copyright: Copyright (c) 2013-5-31 下午05:04:46
// * <p>
// * Company: 北京宽连十方数字技术有限公司
// * <p>
// * 
// * @author zouyl@c-platform.com
// * @version 1.0.0
// */
//@Controller
//public class GenAllNetIndex extends BaseController {
//
//	@Autowired
//	private MallService mallService;
//
//	@Autowired
//	private SysTypeUtil sysTypeUtil;
//
//	@Autowired
//	private AppConfig appConfig;
//
//	@Autowired
//	private CommonCacheService commonCacheService;
//
//	@Autowired
//	private AdService adService;
//
//	private final String channel = "1";
//
//	private final Integer typeChannel = 2;
//
//	private String defRegionCode = "320000";// 江苏省
//
//	private String defAllRegionCode = "0";// 全网
//
//	private final Integer maxFloorNum = 8;
//
//	private final Logger logger = Logger.getLogger(GenAllNetIndex.class);
//	
//	@Autowired
//	MemcachedClient memcachedClient;
//	
//	/** MEMCACHE缓存有效时间，默认为1小时 * */
//	private static final int MEMCACHE_REFRESH_TIME = 1 * 3600;
//
//	@Scheduled(fixedDelay = 1200000)
//	// 每20秒执行一次
//	public void crontab() throws SQLException {
//		long t1 = System.currentTimeMillis();
//		logger.info("全网首页生成静态文件:>>begin>>");
//		String parentRegion = "0";
//		List<String[]> provinceCodeList = commonCacheService.getProvinceRegionList(parentRegion);
//		for (String[] provinceCode : provinceCodeList) {
//			if (StringUtils.equals("110000", provinceCode[0]) || StringUtils.equals("120000", provinceCode[0])
//			        || StringUtils.equals("310000", provinceCode[0]) || StringUtils.equals("500000", provinceCode[0])) {
//				logger.info("生成静态文件:>>" + provinceCode[0]);
//				try {
//					genIndex(provinceCode[0], null);
//				}
//				catch (Exception e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//
//			} else {
//				List<String[]> regionCodeList = commonCacheService.getChildRegionList(provinceCode[0]);
//				for (String[] t : regionCodeList) {
//
//					// if(!StringUtils.equals("330200", t[0])) { continue; }
//
//					logger.info("生成静态文件:>>" + t[0]);
//					try {
//						genIndex(t[0], null);
//					}
//					catch (Exception e) {
//						// TODO Auto-generated catch block
//						e.printStackTrace();
//					}
//				}
//			}
//		}
//		long t2 = System.currentTimeMillis();
//		logger.info("静态化执行时间:" + (t2 - t1));
//	}
//	
//	/**
//	 * 从memcached获得头部信息
//	 * 
//	 * @param regionCode
//	 * @return
//	 */
//	private String getHeaderHtmFromMem(String regionCode) {
//		try {
//			Object obj = memcachedClient.get("headhtml_gsc_" + regionCode);
//			if (obj != null) {
//				return (String) obj;
//			} else {
//				return "";
//			}
//		} catch (Exception e1) {
//			logger.info("memcached获取头部信息失败" + e1.getMessage());
//			return "";
//		}
//	}
//
//	/**
//	 * 头部信息放入memcached
//	 * 
//	 * @param regionCode
//	 * @param headhtml
//	 */
//	private void setHeaderHtmToMem(String regionCode, String headhtml) {
//		try {
//			memcachedClient.set("headhtml_gsc_" + regionCode,
//					MEMCACHE_REFRESH_TIME, headhtml);
//		} catch (Exception e) {
//			logger.info("memcached存取头部信息失败" + e.getMessage());
//		}
//	}
//
//	/**
//	 * 从接口获得头部信息
//	 * 
//	 * @param regionCode
//	 * @return
//	 */
//	private String getHeaderHtmlFromInterface(String regionCode) {
//		return MallHeader.getHtml(appConfig.getHeaderUrl(), regionCode, "QW-WWW", "qw-gsc");
//	}
//
//	@RequestMapping(value = "/index/genAllNet")
//	@ResponseBody
//	public String genIndex(@RequestParam("regionCode") String regionCode, Model model) throws SQLException {
//		String headhtml = getHeaderHtmFromMem(regionCode);
//
//		//如果mem中没有头部信息，则从接口中取
//		if (StringUtils.isEmpty(headhtml)) {
//			logger.info("调用头部code1："+regionCode); 
//			headhtml = getHeaderHtmlFromInterface(regionCode);
//			logger.info("调用头部code2："+regionCode);
//		}
//
//		if (StringUtils.isEmpty(headhtml)) {
//			logger.info("头部应用未初始化,稍后再静态化 regionCode:" + regionCode);
//			return "头部应用未初始化,稍后再静态化";
//		}
//
//		String regionSpell = commonCacheService.getRegionSpell(regionCode);
//		if (regionSpell == null || "".equals(regionSpell)) {
//			logger.info("传入区域参数有误:" + regionSpell);
//			return "传入区域参数有误";
//		}
//		defRegionCode = regionCode.substring(0, 2) + "0000";
//
//		// 判断是否有对应的regionCode.ftl, 如果没有则取对应默认的 省.ftl
//		String ftlParam = "";
//		File ftlFile = new File(appConfig.getB2c_Index_RFPath() + "allnet.ftl");
//		if (ftlFile.exists()) {
//			ftlParam = "allnet";
//		}
//		/*
//		 * File ftlFile = new File(appConfig.getB2c_Index_RFPath() + regionCode
//		 * + ".ftl"); if (ftlFile.exists()) { ftlParam = regionCode; } else {
//		 * ftlFile = new File(appConfig.getB2c_Index_RFPath() + defRegionCode +
//		 * ".ftl"); if (ftlFile.exists()) { ftlParam = defRegionCode; } }
//		 */
//		if ("".equals(ftlParam)) {
//			logger.info("未找到模板文件,路径:" + appConfig.getB2c_Index_RFPath() + "allnet.ftl");
//			return "未找到模板文件";
//		}
//
//		// 设置模版路径 生成htm路径
//		logger.info("模版路径 生成htm路径:" + appConfig.getB2c_Item_SaveRootPath() + appConfig.getB2c_Index_RSPath() + regionSpell + ".htm");
//		this.setPath(ftlParam, appConfig.getB2c_Item_SaveRootPath() + appConfig.getB2c_Index_RSPath() + regionSpell + ".htm");
//
//		HashMap<String, Object> map = new HashMap<String, Object>();
//		// 分类数据
//		List<TChannelTypeDTO> displayTypeList = sysTypeUtil.getDisplayTypeList(Integer.parseInt(channel), typeChannel, regionCode);
//		if (displayTypeList == null || displayTypeList.size() == 0) {
//			// 如果没有取全国
//			displayTypeList = sysTypeUtil.getDisplayTypeList(Integer.parseInt(channel), typeChannel, defAllRegionCode);
//		}
//
//		List<TChannelFloorConf> floorList = mallService.getFloorList(Integer.parseInt(channel), regionCode);
//		if (floorList == null || floorList.size() == 0) {
//			// 如果没有取全国
//			floorList = mallService.getFloorList(Integer.parseInt(channel));
//		}
//
//		if (floorList == null) {
//			floorList = new ArrayList<TChannelFloorConf>();
//		}
//
//		// 轮换图片数据
//		List<TChannelPicConf> picList = mallService.getPicListByCode(channel, regionCode);
//		if (picList == null || picList.size() == 0) {
//			// 如果没有取全国
//			picList = mallService.getPicListByCode(channel);
//		}
//
//		Map<String, List<TChannelPicConf>> picMap = new HashMap<String, List<TChannelPicConf>>();
//		Set<String> picKeySet = new HashSet<String>();
//
//		if (picList != null && picList.size() > 0) {
//			for (TChannelPicConf conf : picList) {
//				String tmpKey = conf.getPostion().toString();
//				if (picKeySet.contains(tmpKey)) {
//					picMap.get(tmpKey).add(conf);
//				} else {
//					List<TChannelPicConf> tmpList = new ArrayList<TChannelPicConf>();
//					tmpList.add(conf);
//					picMap.put(tmpKey, tmpList);
//					picKeySet.add(tmpKey);
//				}
//			}
//		}
//
//		// 楼层商品数据
//		Map<String, Integer[]> mapIds = mallService.getCatalogMapItemIds(channel, regionCode);
//		if (mapIds == null || mapIds.size() == 0) {
//			mapIds = mallService.getCatalogMapItemIds(channel);
//		}
//
//		Map<String, Integer[]> idMap = new HashMap<String, Integer[]>();
//		Integer[] allIds = new Integer[] {};
//		for (TChannelFloorConf floor : floorList) {
//			Integer[] tmpIds = getDefIds(mapIds, defAllRegionCode, floor.getFloorId().toString(), floor.getTypeId());
//			idMap.put(floor.getFloorId().toString(), tmpIds);
//
//			allIds = concatAll(allIds, tmpIds);
//		}
//
//		Map<String, VSearchGood> productMap = mallService.getProductMap(allIds);
//
//		// 获取热销榜数据
//		Map<String, List<VSearchGood>> hotMap = new HashMap<String, List<VSearchGood>>();
//		for (TChannelFloorConf floor : floorList) {
//			hotMap.put(floor.getFloorId().toString(), mallService.getHotList(floor.getTypeId(), typeChannel, 3));
//		}
//
//		// 获取各楼层关键字
//		List<TChannelCatalogRcmdConf> rcmdList = mallService.getCatalogRcmdListByCode(channel, regionCode);
//		if (rcmdList == null || rcmdList.size() == 0) {
//			// 如果没有取全国
//			rcmdList = mallService.getCatalogRcmdListByCode(channel);
//		}
//		Map<String, List<TChannelCatalogRcmdConf>> rcmdMap = new HashMap<String, List<TChannelCatalogRcmdConf>>();
//		Set<String> rcmdKeySet = new HashSet<String>();
//
//		if (rcmdList != null && rcmdList.size() > 0) {
//			for (TChannelCatalogRcmdConf conf : rcmdList) {
//				String tmpKey = conf.getType().toString() + "_" + conf.getGroupId().toString();
//				if (rcmdKeySet.contains(tmpKey)) {
//					rcmdMap.get(tmpKey).add(conf);
//				} else {
//					List<TChannelCatalogRcmdConf> tmpList = new ArrayList<TChannelCatalogRcmdConf>();
//					tmpList.add(conf);
//					rcmdMap.put(tmpKey, tmpList);
//					rcmdKeySet.add(tmpKey);
//				}
//			}
//		}
//		Map<String, List<String[]>> adMap = adService.getHomeAdList("home", regionCode);
//		if (adMap == null || adMap.size() == 0) {
//			// 如果没有取全国
//			adMap = adService.getHomeAdList("home", defAllRegionCode);
//		}
//
//		map.put("picMap", picMap);
//		map.put("idMap", idMap);
//		map.put("productMap", productMap);
//		map.put("hotMap", hotMap);
//
//		map.put("rcmdMap", rcmdMap);
//		map.put("floorList", floorList);
//
//		map.put("typeList", displayTypeList);
//
//		map.put("areaCode", commonCacheService.getRegionAreaCode(regionCode));
//		map.put("regionCode", regionCode);
//		map.put("adMap", adMap);
//
//		// map.put("headerHtml",
//		// MallHeader.getHtml("http://mall.12580life.com",regionCode,"QW-UNION","qw-gsc"));
//		map.put("headerHtml", headhtml);
//
//		// 设置输出数据
//		try {
//			this.setResponseData(map);
//		}
//		catch (Exception e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//		return "生成成功！";
//	}
//
//	/**
//	 * 判断配置的产品ID,是否在产品表中
//	 * 
//	 * @param ids
//	 * @param map
//	 * @param defTypeId
//	 * @return
//	 */
//	private Integer[] getDefIds(Map<String, Integer[]> mapIds, String regionCode, String groupId, Integer typeId) {
//		Integer[] ids = mapIds.get(groupId);
//		if ((ids == null || ids.length == 0) && typeId != null) {
//			return mallService.getDefFloorIds(typeId, typeChannel, maxFloorNum);
//		} else {
//			if (ids == null) {
//				return new Integer[] {};
//			}
//			return ids;
//		}
//	}
//
//	private <T> T[] concatAll(T[] first, T[]... rest) {
//		int totalLength = first.length;
//		for (T[] array : rest) {
//			totalLength += array.length;
//		}
//		T[] result = Arrays.copyOf(first, totalLength);
//		int offset = first.length;
//		for (T[] array : rest) {
//			System.arraycopy(array, 0, result, offset, array.length);
//			offset += array.length;
//		}
//		return result;
//	}
//
//}
