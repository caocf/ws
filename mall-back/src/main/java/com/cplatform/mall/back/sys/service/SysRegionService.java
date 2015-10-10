package com.cplatform.mall.back.sys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.dao.SysRegionDao;
import com.cplatform.mall.back.sys.entity.SysRegion;

/**
 * 区域管理服务类 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-27 下午8:05:28
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class SysRegionService {

	@Autowired
	private SysRegionDao regonDao;

	@Autowired
	private DbHelper dbHelper;

	/** 全国 */
	private SysRegion CHINA_REGION;

	public SysRegionService() {
		CHINA_REGION = new SysRegion();
		CHINA_REGION.setId(0L);
		CHINA_REGION.setParentRegion("0");
		CHINA_REGION.setRegionCode("0");
		CHINA_REGION.setRegionName("全国");
	}

	public SysRegion addOrUpdate(SysRegion regon) {
		return regonDao.save(regon);
	}

	public void del(Long id) {
		regonDao.delete(id);
	}

	/**
	 * 获取页面显示数据
	 * 
	 * @param sysRegon
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<SysRegion> findRegion(SysRegion sysRegion, int pageNo,
			int pageSize) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_sys_region t where 1=1");
		List<Object> params = new ArrayList<Object>();
		if (sysRegion != null) {
			if (sysRegion.getParentRegion() != null) {
				sql.append(" and parent_region = ?");
				params.add(sysRegion.getParentRegion());
			}
		}
		return dbHelper.getPage(sql.toString(), SysRegion.class, pageNo,
				pageSize, params.toArray());
	}

	public SysRegion findById(Long Id) {
		return regonDao.findById(Id);
	}

	public SysRegion findByRegionCode(String regionCode) {
		if ("0".equals(regionCode)) {
			return CHINA_REGION;
		}
		return regonDao.findByRegionCode(regionCode);
	}

	public List<SysRegion> findByRegionLevel(Long regionLevel) {
		return regonDao.findByRegionLevel(regionLevel);
	}

	public List<SysRegion> findByParentRegion(String parentRegon) {
		return regonDao.findByParentRegion(parentRegon);
	}

	/**
	 * 获得摸个父区域下，必须符合某个级别下
	 * 
	 * @param parentRegon
	 *            父区域编码
	 * @param level
	 *            等级 省-0 市-1 区县-2
	 * @return
	 * @throws SQLException
	 */
	public List<SysRegion> findRegions(String parentRegon, Integer level)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql
				.append("select r.* from T_SYS_REGION r where r.parent_Region = ? and region_Level <= ? and is_Show =1 order by sort_Num  ");
		return dbHelper.getBeanList(sql.toString(), SysRegion.class,
				new Object[] { parentRegon, level });
	}

	/**
	 * 只获得有地市值的子区域
	 * 
	 * @param parentRegon
	 * @return
	 */
	public List<SysRegion> findAreaByParentRegion(String parentRegon) {
		return regonDao.findAreaByParentRegion(parentRegon);
	}

	/**
	 * 查询上级区域。
	 * 
	 * @param areaCodes
	 * @return
	 * @throws SQLException
	 */
	public List<SysRegion> getByChildRegionCodes(String childRegionCodes)
			throws SQLException {
		String sql = ("select r.* from t_sys_region r where r.region_code in (select rr.parent_region from t_sys_region rr where rr.region_code in ("
				+ childRegionCodes + ")) ");
		return dbHelper.getBeanList(sql, SysRegion.class, null);
	}
}
