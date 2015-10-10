package com.cplatform.mall.dc.service;

import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.mall.dc.dao.DcUserDao;
import com.cplatform.mall.dc.entity.DcArea;

/**
 * 地区服务类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-7-15
 */
@Service
public class DcAreaService {
	@Autowired
	DbHelper dbHelper;

	@Autowired
	DcUserDao dcUserDao;

	/**
	 * 获取地区列表
	 * 
	 * @return 地区列表
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findArea() throws SQLException {
		String sql = "select * from t_dc_area";
		return dbHelper.getNativeMapList(sql);
	}

	public List<DcArea> getAreasByUser(Long userId) throws SQLException {
		String area = dcUserDao.getAreaByUserId(userId);
		if (!StringUtils.isNotBlank(area)) {
			return null;
		}
		String sql = "SELECT da.* FROM t_dc_area da where da.AREA_CODE IN (" + area + ") ORDER BY da.AREA_CODE  ASC ";
		return dbHelper.getBeanList(sql, DcArea.class);
	}
	
	/**
	 * 根据用户列出其能查看的所有地区
	 * @param model
	 * @return
	 * @throws SQLException 
	 */
	public void getAreaByUser(Long userId, Model model) throws SQLException{
		List<DcArea> dcAreas = getAreasByUser(userId);
		model.addAttribute("areas", dcAreas);
	}

}
