package com.cplatform.b2c.service;

import java.sql.SQLException;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;

/**
 * 演出票数据. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-1-13 下午9:57:06
 * <p>
 * Company: 宽连信息（苏州）技术有限公司
 * <p>
 * 
 * @author caowen@c-platform.com
 * @version 1.0.0
 */
@Service
public class PerformService {

	private static final Log logger = LogFactory.getLog(PerformService.class);

	@Autowired
	DbHelper dbHelper;

	/**
	 * 根据商城商品ID获取演出票信息
	 * 
	 * @param itemId
	 * @return
	 */
	public Map<String, String> getPerformTicketInfo(String itemId) {

		try {
			StringBuilder sqlBuffer = new StringBuilder(200);
			sqlBuffer.setLength(0);
			sqlBuffer.append("select 'http://mall.12580life.com/perform-detail/' || info.id url,");
			sqlBuffer.append("       info.play_name,");
			sqlBuffer.append("       'http://mall.12580life.com/newmovie/ticketsimg/' ||");
			sqlBuffer.append("       info.product_picture img_path,");
			sqlBuffer.append("       tInfo.ticket_time,");
			sqlBuffer.append("       info.product_id,");
			sqlBuffer.append("       pInfo.product_play_id,");
			sqlBuffer.append("       info.id");
			sqlBuffer.append("  from movie.t_play_ticket_price pInfo,");
			sqlBuffer.append("       movie.t_play_ticket_time  tInfo,");
			sqlBuffer.append("       movie.t_play_ticket       info");
			sqlBuffer.append(" where pInfo.ticket_time_id = tInfo.id");
			sqlBuffer.append("   and tInfo.ticket_id = info.id");
			sqlBuffer.append("   and pInfo.b2c_Good_id =?");
			Map<String, String> infoMap = dbHelper.getMap(sqlBuffer.toString(), itemId);
			logger.info("根据商城商品ID获取演出票信息" + itemId + ":" + infoMap);
			return infoMap;
		}
		catch (SQLException e) {
			logger.error("根据商城商品ID获取演出票信息异常", e);
		}
		return null;
	}
}
