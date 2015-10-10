package com.cplatform.b2c.web;

import java.io.File;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.alibaba.druid.util.StringUtils;
import com.cplatform.b2c.model.TChannelCatalogRcmdConf;
import com.cplatform.b2c.model.TChannelFloorConf;
import com.cplatform.b2c.model.TChannelPicConf;
import com.cplatform.b2c.model.TChannelTypeDTO;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.service.AdService;
import com.cplatform.b2c.service.CommonCacheService;
import com.cplatform.b2c.service.MallService;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.SysTypeUtil;
import com.cplatform.chead.MallHeader;

/**
 * 商户展示Controller
 * <p>
 * Copyright: Copyright (c) 2013-5-31 下午05:04:46
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Controller
public class GenIndex extends BaseController {

	@Autowired
	private MallService mallService;

	@Autowired
	private SysTypeUtil sysTypeUtil;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private CommonCacheService commonCacheService;

	@Autowired
	private AdService adService;

	private final String channel = "1";

	private final Integer typeChannel = 2;

	private String defRegionCode = "320000";// 江苏省

	private final Integer maxFloorNum = 8;

	@Scheduled(fixedDelay = 600000)
	// 每20秒执行一次
	public void crontab() throws SQLException {
		String parentRegion = defRegionCode;
		List<String[]> regionCodeList = commonCacheService.getChildRegionList(parentRegion);
		for (String[] t : regionCodeList) {
			genIndex(t[0], null);
		}
	}

	@RequestMapping(value = "/index/gen")
	@ResponseBody
	public String genIndex(@RequestParam("regionCode") String regionCode, Model model) throws SQLException {
		if (StringUtils.isEmpty(MallHeader.getHtml(appConfig.getHeaderUrl(), regionCode, "UNION-WWW", "gsc"))) {
			return "头部应用未初始化,稍后再静态化";
		}

		String regionSpell = commonCacheService.getRegionSpell(regionCode);
		if (regionSpell == null || "".equals(regionSpell)) {
			return "传入区域参数有误";
		}
		defRegionCode = regionCode.substring(0, 2) + "0000";

		// 判断是否有对应的regionCode.ftl, 如果没有则取对应默认的 省.ftl
		String ftlParam = "";
		File ftlFile = new File(appConfig.getB2c_Index_RFPath() + regionCode + ".ftl");
		if (ftlFile.exists()) {
			ftlParam = regionCode;
		} else {
			ftlFile = new File(appConfig.getB2c_Index_RFPath() + defRegionCode + ".ftl");
			if (ftlFile.exists()) {
				ftlParam = defRegionCode;
			}
		}
		if ("".equals(ftlParam)) {
			return "未找到模板文件";
		}

		// 设置模版路径 生成htm路径
		this.setPath(ftlParam, appConfig.getB2c_Item_SaveRootPath() + appConfig.getB2c_Index_RSPath() + regionSpell + ".htm");

		HashMap<String, Object> map = new HashMap<String, Object>();
		// 分类数据
		List<TChannelTypeDTO> displayTypeList = sysTypeUtil.getDisplayTypeList(Integer.parseInt(channel), typeChannel, regionCode);

		List<TChannelFloorConf> floorList = mallService.getFloorList(Integer.parseInt(channel), regionCode);
		if (floorList == null || floorList.size() == 0) {
			floorList = mallService.getFloorList(Integer.parseInt(channel), defRegionCode);
		}

		if (floorList == null) {
			floorList = new ArrayList<TChannelFloorConf>();
		}

		// 轮换图片数据
		List<TChannelPicConf> picList = mallService.getPicListByCode(channel, regionCode);
		Map<String, List<TChannelPicConf>> picMap = new HashMap<String, List<TChannelPicConf>>();
		Set<String> picKeySet = new HashSet<String>();

		if (picList != null && picList.size() > 0) {
			for (TChannelPicConf conf : picList) {
				String tmpKey = conf.getPostion().toString();
				if (picKeySet.contains(tmpKey)) {
					picMap.get(tmpKey).add(conf);
				} else {
					List<TChannelPicConf> tmpList = new ArrayList<TChannelPicConf>();
					tmpList.add(conf);
					picMap.put(tmpKey, tmpList);
					picKeySet.add(tmpKey);
				}
			}
		}

		// 楼层商品数据
		Map<String, Integer[]> mapIds = mallService.getCatalogMapItemIds(channel, regionCode);

		Map<String, Integer[]> idMap = new HashMap<String, Integer[]>();
		Integer[] allIds = new Integer[] {};
		for (TChannelFloorConf floor : floorList) {
			Integer[] tmpIds = getDefIds(mapIds, regionCode, floor.getFloorId().toString(), floor.getTypeId());
			idMap.put(floor.getFloorId().toString(), tmpIds);

			allIds = concatAll(allIds, tmpIds);
		}

		Map<String, VSearchGood> productMap = mallService.getProductMap(allIds);

		// 获取热销榜数据
		Map<String, List<VSearchGood>> hotMap = new HashMap<String, List<VSearchGood>>();
		for (TChannelFloorConf floor : floorList) {
			hotMap.put(floor.getFloorId().toString(), mallService.getHotList(floor.getTypeId(), typeChannel, 3));
		}

		// 获取各楼层关键字
		List<TChannelCatalogRcmdConf> rcmdList = mallService.getCatalogRcmdListByCode(channel, regionCode);
		Map<String, List<TChannelCatalogRcmdConf>> rcmdMap = new HashMap<String, List<TChannelCatalogRcmdConf>>();
		Set<String> rcmdKeySet = new HashSet<String>();

		if (rcmdList != null && rcmdList.size() > 0) {
			for (TChannelCatalogRcmdConf conf : rcmdList) {
				String tmpKey = conf.getType().toString() + "_" + conf.getGroupId().toString();
				if (rcmdKeySet.contains(tmpKey)) {
					rcmdMap.get(tmpKey).add(conf);
				} else {
					List<TChannelCatalogRcmdConf> tmpList = new ArrayList<TChannelCatalogRcmdConf>();
					tmpList.add(conf);
					rcmdMap.put(tmpKey, tmpList);
					rcmdKeySet.add(tmpKey);
				}
			}
		}
		Map<String, List<String[]>> adMap = adService.getHomeAdList("home", regionCode);

		map.put("picMap", picMap);
		map.put("idMap", idMap);
		map.put("productMap", productMap);
		map.put("hotMap", hotMap);

		map.put("rcmdMap", rcmdMap);
		map.put("floorList", floorList);

		map.put("typeList", displayTypeList);

		map.put("areaCode", commonCacheService.getRegionAreaCode(regionCode));
		map.put("regionCode", regionCode);
		map.put("adMap", adMap);

		map.put("headerHtml", MallHeader.getHtml(appConfig.getHeaderUrl(), regionCode, "UNION-WWW", "gsc"));

		// 设置输出数据
		try {
			this.setResponseData(map);
		}
		catch (Exception e) {
			e.printStackTrace();
		}
		return "生成成功！";
	}

	/**
	 * 判断配置的产品ID,是否在产品表中
	 * 
	 * @param ids
	 * @param map
	 * @param defTypeId
	 * @return
	 */
	private Integer[] getDefIds(Map<String, Integer[]> mapIds, String regionCode, String groupId, Integer typeId) {
		Integer[] ids = mapIds.get(groupId);
		if ((ids == null || ids.length == 0) && typeId != null) {
			return mallService.getDefFloorIds(typeId, typeChannel, maxFloorNum);
		} else {
			if (ids == null) {
				return new Integer[] {};
			}
			return ids;
		}
	}

	private <T> T[] concatAll(T[] first, T[]... rest) {
		int totalLength = first.length;
		for (T[] array : rest) {
			totalLength += array.length;
		}
		T[] result = Arrays.copyOf(first, totalLength);
		int offset = first.length;
		for (T[] array : rest) {
			System.arraycopy(array, 0, result, offset, array.length);
			offset += array.length;
		}
		return result;
	}

}
