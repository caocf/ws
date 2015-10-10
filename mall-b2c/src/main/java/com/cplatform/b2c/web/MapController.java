package com.cplatform.b2c.web;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.cplatform.b2c.model.TShop;
import com.cplatform.b2c.service.MapService;

/**
 * 地图展示操作.
 * <p>
 * Copyright: Copyright (c) 2013-6-9 上午9:34:00
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author 季霁
 */
@Controller
public class MapController {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	@Autowired
	private MapService mapService;

	// 根据获取到的地图长宽，id，查询出地图信息并展示
	@RequestMapping(value = "/map/getMap")
	public String getMap(HttpServletRequest request, HttpServletResponse response, Model model) throws IOException {
		// width
		String width = request.getParameter("width");
		if (StringUtils.isNotBlank(width) && StringUtils.isNumeric(width) && Integer.parseInt(width) > 0) {
			model.addAttribute("width", width);
		} else {
			model.addAttribute("width", 320);
		}

		// height
		String height = request.getParameter("height");
		if (StringUtils.isNotBlank(height) && StringUtils.isNumeric(height) && Integer.parseInt(height) > 0) {
			model.addAttribute("height", height);
		} else {
			model.addAttribute("height", 170);
		}

		// id
		String id = request.getParameter("id");
		logger.info("~~~~~~~~~~~~~width:" + width + " height:" + height + " id:" + id);
		if (StringUtils.isNotBlank(id)) {
			String[] ids = id.split(",");
			Integer[] idInt = new Integer[ids.length];
			// 转化成Integer
			for (int i = 0; i < ids.length; i++) {
				if (StringUtils.isNotBlank(ids[i])) {
					idInt[i] = Integer.parseInt(ids[i]);
				}
			}

			// 查询指定id shop信息
			List<TShop> mapInfo = mapService.getMapInfo(idInt);
			// StringBuffer
			StringBuffer sb = new StringBuffer();
			if (!mapInfo.isEmpty()) {
				for (TShop sh : mapInfo) {
					sb.append(sh.getMapLong() + ",");
					sb.append(sh.getMapDim() + ",");
					sb.append(sh.getName() + "|");
					// sb.append(sh.getId() + ",");
					// sb.append(sh.getRemark() + "|");
				}
			}
			model.addAttribute("mapInfo", sb.toString());
		}
		return "map/getMap";
	}

}
