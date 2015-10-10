package com.cplatform.mall.back.store.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.store.dao.StoreCodeDao;
import com.cplatform.mall.back.store.entity.StoreCode;
import com.cplatform.mall.back.store.entity.StoreCodeConfig;

@Service
public class StoreCodeService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private StoreCodeDao storeCodeDao;

	/**
	 * 获取商户码配置信息
	 */
	public List<StoreCodeConfig> getCodeConfig() throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_store_code_config  order by id");
		return dbHelper.getBeanList(sql.toString(), StoreCodeConfig.class);
	}

	/**
	 * 添加商户码信息
	 */
	public StoreCode addStoreCode(StoreCode code) throws SQLException {
		return storeCodeDao.save(code);
	}

	/**
	 * 商户码管理
	 * 
	 * @param fee
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<StoreCode> PageStoreList(StoreCode code, int pageNo) throws SQLException {
		StringBuilder sql = new StringBuilder(100);
		sql.append(" select t.*, s.name storeName,");
		sql.append("  r.region_name areaName");
		sql.append("     from t_store_code t ");
		sql.append("  left join t_store s on t.store_id = s.id");
		sql.append("  left join t_sys_region r on s.area_code = r.region_code");
		sql.append("  where t.id > 0 ");
		List params = new ArrayList();
		if (null != code) {
			if (StringUtils.isNotEmpty(code.getStoreName())) {
				sql.append(" and s.name like ? ");
				params.add("%" + code.getStoreName().trim() + "%");
			}
			if (null != code.getStoreId()) {
				sql.append(" and t.STORE_ID = ? ");
				params.add(code.getStoreId());
			}
			if (StringUtils.isNotEmpty(code.getStartTime())) {
				sql.append(" and t.Starttime>= ?");
				params.add(code.getStartTime());
			}
			if (StringUtils.isNotEmpty(code.getStopTime())) {
				sql.append(" and t.Starttime<= ?");
				params.add(code.getStopTime());
			}
			if (code.getSendChannelId() != null) {
				sql.append(" and t.SEND_CHANNEL_ID = ?");
				params.add(code.getSendChannelId());
			}
			if (code.getSendChannelId() != null) {
				sql.append(" and t.SEND_CHANNEL_ID = ?");
				params.add(code.getSendChannelId());
			}
			// 保证每个商户都有方正码
			if (StringUtils.isNotEmpty(code.getFZFlag()) && "true".equals(code.getFZFlag())) {
				sql.append(" or t.STORE_ID = -1 ");
			}
		}
		sql.append(" order by t.id desc");
		return dbHelper.getPage(sql.toString(), StoreCode.class, pageNo, Page.DEFAULT_PAGESIZE, params.toArray());
	}

	/**
	 * 查询商户码信息
	 */
	public StoreCode findStoreCodeById(Long id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*, s.name storeName, r.region_name areaName");
		sql.append(" from t_store_code t, t_store s, t_sys_region r");
		sql.append(" where t.store_id = s.id");
		sql.append(" and s.area_code = r.region_code");
		sql.append(" and t.id =" + id);
		return dbHelper.getBean(sql.toString(), StoreCode.class);
	}

	/**
	 * 查询商户码是否已存在
	 */
	public StoreCode findStoreCodeIsExit(Long storeId, Long sendChannelId, Long sendTypeId, Long id) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_store_code t");
		sql.append(" where t.store_id = " + storeId);
		sql.append(" and t.send_channel_id = " + sendChannelId);
		sql.append(" and t.send_type_id =" + sendTypeId);
		if (id != null) {
			sql.append(" and t.id !=" + id);
		}
		return dbHelper.getBean(sql.toString(), StoreCode.class);
	}

	/**
	 * 删除商户码信息
	 */
	public void delStoreCode(Long id) throws SQLException {
		storeCodeDao.delete(id);
	}

}
