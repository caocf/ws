package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.item.dao.ItemSaleDao;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.mall.back.websys.dao.ChannelFloorDao;
import com.cplatform.mall.back.websys.dao.FloorCatalogDao;
import com.cplatform.mall.back.websys.entity.ChannelCatalogConfig;
import com.cplatform.mall.back.websys.entity.ChannelCatalogRcmdConfig;
import com.cplatform.mall.back.websys.entity.ChannelFloor;
import com.cplatform.util2.TimeStamp;

/**
 * Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-14 上午11:14:48
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: wuyn@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class FloorCatalogService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private FloorCatalogDao floorCatalogDao;

	@Autowired
	private ItemSaleDao itemSaleDao;

	@Autowired
	SysRegionService regionService;
	
	@Autowired
	ChannelFloorService channelfloorService;
	
	@Autowired
	private SysRegionService sysRegionService;
	
	@Autowired
	private ChannelFloorDao channelFloorDao;


	/**
	 * 频道分类列表
	 * 
	 * @param channelCatalog
	 * @param page
	 * @param defaultPagesize
	 * @param model
	 * @return
	 * @throws SQLException
	 */
	public Page<ChannelCatalogConfig> list(ChannelCatalogConfig channelCatalog,
			int page, int pagesize) throws SQLException {

		StringBuilder sql = new StringBuilder(100);
		sql.append("SELECT cc.*,tis.name itemOrgName, tis.is_valid isValid FROM");
		sql.append(" T_CHANNEL_CATALOG_CONF cc inner join");
		sql.append(" t_item_sale tis on cc.item_id =tis.id");
		sql.append("     WHERE 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (channelCatalog != null) {
			if (channelCatalog.getChannel() != null) {
				sql.append(" AND cc.CHANNEL=?");
				paramsList.add(channelCatalog.getChannel());
			}
			if (channelCatalog.getGroupId() != null) {
				sql.append(" AND cc.GROUP_ID=?");
				paramsList.add(channelCatalog.getGroupId());
			}

			if (channelCatalog.getStatus() != null) {
				sql.append(" AND cc.STATUS=?");
				paramsList.add(channelCatalog.getStatus());
			}

			if (StringUtils.isNotEmpty(channelCatalog.getRegionName())) {
				sql.append(" AND r.region_name like ? ");
				paramsList.add("%" + channelCatalog.getRegionName().trim() + "%");
			}
			
			if (StringUtils.isNotEmpty(channelCatalog.getItemOrgName())) {
				sql.append(" AND tis.name like ? ");
				paramsList.add("%" + channelCatalog.getItemOrgName().trim() + "%");
			}
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_CHANNELCATALOGCONFIG));
		
		sql.append(" ORDER BY cc.ID DESC");
		return dbHelper.getPage(sql.toString(), ChannelCatalogConfig.class,
				page, pagesize, paramsList.toArray());
	}

	/**
	 * 保存
	 * 
	 * @param channelCatalog
	 */
	public void save(ChannelCatalogConfig channelCatalog) {

		channelCatalog.setStatus(0);
//		channelCatalog.setOrderIndex(99);
		channelCatalog.setUpdateTime(TimeStamp
				.getTime(TimeStamp.YYYYMMDDhhmmss));
		floorCatalogDao.save(channelCatalog);
	}

	/**
	 * 修改预处理
	 * 
	 * @param id
	 * @param model
	 * @throws SQLException 
	 */
	public void preEdit(Long id, Model model) throws SQLException {
		ChannelCatalogConfig channelCatalog = floorCatalogDao.findOne(id);

		ItemSale itemSale = itemSaleDao.findOne(channelCatalog.getItemId());
		channelCatalog.setItemOrgName(itemSale.getName());
		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("channelMap", ChannelCatalogRcmdConfig.channelMap);
		model.addAttribute("channelCatalog", channelCatalog);
		model.addAttribute("method", "edit");
		
		ChannelFloor  channelFloor = channelFloorDao.findChannelFloorByChannelAndFloor(channelCatalog.getChannel(), channelCatalog.getGroupId());
		String[] areas = channelFloor.getRegionCode().split(",");
		
		String regionCode = channelCatalog.getRegionCode();
		String[] regionCodes = regionCode.split(",");
		
		List<String> regionAll = Arrays.asList(regionCodes);
		List<String> regionAll2 = Arrays.asList(areas);
		
		String htmlStr = "";
		for(int i=0;i<regionCodes.length;i++){
			if(regionAll2.contains(regionCodes[i])){
				htmlStr += "<input type='checkbox' name='regionCode' checked='checked' value='"+regionCodes[i]+"'/>" +"<label>"+sysRegionService.findByRegionCode(regionCodes[i]).getRegionName()+"</label>";
			}
		}
		for(int i=0;i<areas.length;i++){
			if(!regionAll.contains(areas[i])){
				htmlStr += "<input type='checkbox' name='regionCode'  value='"+areas[i]+"'/>" +"<label>"+sysRegionService.findByRegionCode(areas[i]).getRegionName()+"</label>";
			}
		}
		model.addAttribute("htmlStr", htmlStr);
	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		floorCatalogDao.delete(id);
	}
	
	 public ChannelCatalogConfig findById(Long id){
		 
		  return floorCatalogDao.findOne(id);
	 }

	/**
	 * 开启禁用
	 * 
	 * @param id
	 * @param i
	 */
	public void setFloorCatalogStatus(Long id, Integer status) {
		ChannelCatalogConfig channelCatalog = floorCatalogDao.findOne(id);
		channelCatalog.setStatus(status);
		floorCatalogDao.save(channelCatalog);
	}

	public void setFloorCatalogOrder(Long id, Integer orderIndex) {
		ChannelCatalogConfig channelCatalog = floorCatalogDao.findOne(id);
		channelCatalog.setOrderIndex(orderIndex);
		floorCatalogDao.save(channelCatalog);
	}

	/**
	 * 根据item_id更新状态
	 * 
	 * @param itemId
	 * @param status
	 * @author macl@c-platform.com
	 * @throws SQLException
	 */
	public void updateStatusByItem(Long itemId, Integer status)
			throws SQLException {
		String sql = " update  T_CHANNEL_CATALOG_CONF set status = ? where item_id = ? ";
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(status);
		paramsList.add(itemId);
		dbHelper.execute(sql, paramsList.toArray());
	}

}
