package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.item.dao.ItemManageDao;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.item.service.ItemManageService;
import com.cplatform.mall.back.store.dao.StoreDao;
import com.cplatform.mall.back.store.entity.Store;
import com.cplatform.mall.back.utils.Constants;
import com.cplatform.mall.back.utils.PageStaticInterface;
import com.cplatform.mall.back.websys.dao.PageStaticManageDao;
import com.cplatform.mall.back.websys.entity.PageStaticInfo;

/**
 * Title.页面静态化管理服务类 <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-7-18 上午10:02:31
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: macl@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class PageStaticManageService {

	private static final Logger log = Logger.getLogger(PageStaticManageService.class);

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private PageStaticManageDao pageStaticManageDao;

	@Autowired
	private ItemManageService itemManageService;

	@Autowired
	private ItemManageDao itemManageDao;

	@Autowired
	private StoreDao storeDao;

	@Autowired
	private PageStaticInterface pageStaticInterface;

	/**
	 * 添加静态化失败的商品（商户）
	 * 
	 * @param info
	 */
	@Transactional
	public void saveOrUpdate(PageStaticInfo info) {
		pageStaticManageDao.save(info);
	}

	public Page<PageStaticInfo> list(PageStaticInfo staticInfo, int page, int pageSize) throws SQLException {

		StringBuilder sql = new StringBuilder();
		sql
		        .append(
		                "select  s.*, store.name storeName ,store.area_code from t_page_static_info s inner join t_store store  on store.id = s.resource_id ")
		        .append(" inner join t_Sys_Region region ").append(" on store.Area_Code = region.region_code ").append(" where type =1");
		List<Object> params = new ArrayList<Object>();

		if (StringUtils.isNotEmpty(staticInfo.getStoreName())) {
			sql.append(" and store.name like ? ");
			params.add("%" + staticInfo.getStoreName().trim() + "%");
		}

		if (staticInfo.getResourceId() != null) {
			sql.append(" and store.id = ? ");
			params.add(staticInfo.getResourceId());
		}

		if (StringUtils.isNotEmpty(staticInfo.getStartTime())) {
			sql.append(" and s.create_time >= ? ");
			params.add(staticInfo.getStartTime().replaceAll("-", ""));
		}

		if (StringUtils.isNotEmpty(staticInfo.getStopTime())) {
			sql.append(" and s.create_time <= ? ");
			params.add(staticInfo.getStopTime().replaceAll("-", ""));
		}

		sql.append(" ORDER BY s.create_time desc ");
		return dbHelper.getPage(sql.toString(), PageStaticInfo.class, page, pageSize, params.toArray());
	}

	public Page<PageStaticInfo> listItme(PageStaticInfo staticInfo, int page, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select s.*,i.name itemName from t_page_static_info s inner join t_Item_Sale i on i.id = s.resource_id  where type =0  ");

		List<Object> params = new ArrayList<Object>();

		if (StringUtils.isNotEmpty(staticInfo.getItemName())) {
			sql.append(" and i.name like ? ");
			params.add("%" + staticInfo.getItemName().trim() + "%");
		}

		if (staticInfo.getResourceId() != null) {
			sql.append(" and i.id = ? ");
			params.add(staticInfo.getResourceId());
		}

		if (StringUtils.isNotEmpty(staticInfo.getStartTime())) {
			sql.append(" and s.create_time >= ? ");
			params.add(staticInfo.getStartTime().replaceAll("-", ""));
		}

		if (StringUtils.isNotEmpty(staticInfo.getStopTime())) {
			sql.append(" and s.create_time <= ? ");
			params.add(staticInfo.getStopTime().replaceAll("-", ""));
		}

		sql.append("  ORDER BY s.create_time desc ");
		return dbHelper.getPage(sql.toString(), PageStaticInfo.class, page, pageSize, params.toArray());
	}

	/**
	 * 调用静态化接口
	 * 
	 * @param id
	 * @throws Exception
	 */

	public boolean doStatic(Long[] ids) throws Exception {
		boolean result = true;
		for (Long id : ids) {
			try {
				PageStaticInfo staticInfo = pageStaticManageDao.findById(id);
				if (staticInfo.getType() == PageStaticInfo.TYPE_ITEM) {
					ItemSale sale = itemManageDao.findOne(staticInfo.getResourceId());
					if (null == sale) {
						throw new Exception("商品ID为" + staticInfo.getResourceId() + "的数据不存在");
					}
					String msg = itemManageService.pageStatic(sale);
					if (Constants.STATIC_FAIL.equals(msg)) {
						result = false;
					}
					// 如果操作失败，会在静态化接口中直接插入一条数据，
					// 所以这里直接置为成功后删除该数据
					staticInfo.setStatus(PageStaticInfo.STATUS_SUCCESS);
					saveOrUpdate(staticInfo);
				} else if (staticInfo.getType() == PageStaticInfo.TYPE_SHOP) {
					// 调用商户静态化接口
					Store store = storeDao.findOne(staticInfo.getResourceId());
					if (null == store) {
						throw new Exception("商户ID为" + staticInfo.getResourceId() + "的数据不存在");
					}

					String msg = pageStaticInterface.makeStoreShop(store.getId());
					if (Constants.STATIC_FAIL.equals(msg)) {
						result = false;
					} else {
						staticInfo.setStatus(PageStaticInfo.STATUS_SUCCESS);
						saveOrUpdate(staticInfo);
					}
				}
			}
			catch (Exception e) {
				result = false;
				log.error("静态化失败： " + e.getMessage());
			}
			finally {
				deleteAllStatusIsSuccess();
			}
		}// ~end for
		return result;
	}

	/**
	 * 删除所有状态为“静态化成功”的数据。
	 */

	public void deleteAllStatusIsSuccess() {
		String sql = "DELETE FROM t_page_static_info WHERE status=" + PageStaticInfo.STATUS_SUCCESS;
		try {
			dbHelper.execute(sql, null);
		}
		catch (SQLException e) {
			log.error(e.getMessage());
		}
	}

	@Deprecated
	public void batchStaticAll() {
		Iterable<PageStaticInfo> infoList = this.pageStaticManageDao.findAll();
		if (infoList != null) {
			try {
				Iterator<PageStaticInfo> iterator = infoList.iterator();
				while (iterator.hasNext()) {
					PageStaticInfo staticInfo = iterator.next();
					if (staticInfo.getType() == PageStaticInfo.TYPE_ITEM) {
						ItemSale sale = itemManageDao.findOne(staticInfo.getResourceId());
						if (null == sale) {
							continue;
						}
						// 调用商品静态化接口
						itemManageService.pageStatic(sale);
						staticInfo.setStatus(PageStaticInfo.STATUS_SUCCESS);
						saveOrUpdate(staticInfo);
					} else if (staticInfo.getType() == PageStaticInfo.TYPE_SHOP) {

						// 调用商户静态化接口
						Store store = storeDao.findOne(staticInfo.getResourceId());
						if (null == store) {
							continue;
						}
						try {
							pageStaticInterface.makeStoreShop(store.getId());
							staticInfo.setStatus(PageStaticInfo.STATUS_SUCCESS);
						}
						catch (Exception e) {
							log.error(e.getMessage());
							staticInfo.setStatus(PageStaticInfo.STATUS_FAIL);
						}

						saveOrUpdate(staticInfo);
					}
				}
			}
			catch (Exception e) {
				log.error(e.getMessage());
			}
			finally {
				deleteAllStatusIsSuccess();
			}
		}
	}

}
