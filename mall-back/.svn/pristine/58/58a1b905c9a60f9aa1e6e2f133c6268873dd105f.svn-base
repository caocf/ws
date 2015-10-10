package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.mall.back.websys.dao.ChannelFloorDao;
import com.cplatform.mall.back.websys.dao.FloorCatalogRcmdDao;
import com.cplatform.mall.back.websys.entity.ChannelCatalogRcmdConfig;
import com.cplatform.mall.back.websys.entity.ChannelFloor;
import com.cplatform.mall.back.websys.entity.SysChannelPicConf;

@Service
public class FloorCatalogRcmdService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private FloorCatalogRcmdDao floorCatalogRcmdDao;

	@Autowired
	SysRegionService regionService;

	@Autowired
	ChannelFloorService channelfloorService;
	

	@Autowired
	private ChannelFloorDao channelFloorDao;
	
	@Autowired
	private SysRegionService sysRegionService;

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
	public Page<ChannelCatalogRcmdConfig> list(ChannelCatalogRcmdConfig channelCatalog, int page, int pagesize) throws SQLException {

		StringBuilder sql = new StringBuilder(100);
		sql
		        .append("SELECT cc.* from T_CHANNEL_CATALOG_RCMD_CONF cc  where 1=1  ");
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

			if (StringUtils.isNotEmpty(channelCatalog.getDisplayName())) {
				sql.append(" AND DISPLAY_NAME LIKE ?");
				paramsList.add("%" + channelCatalog.getDisplayName().trim() + "%");
			}

			if (StringUtils.isNotEmpty(channelCatalog.getRegionName())) {
				sql.append(" AND r.region_name like ? ");
				paramsList.add("%" + channelCatalog.getRegionName().trim() + "%");
			}
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_CHANNELCATALOGCONFIG));
		sql.append(" ORDER BY cc.channel asc, cc.GROUP_ID asc, cc.ORDER_INDEX asc");
		return dbHelper.getPage(sql.toString(), ChannelCatalogRcmdConfig.class, page, pagesize, paramsList.toArray());
	}

	/**
	 * 查询同一频道 同一楼层 是否已经设置过图片
	 * 
	 * @param channelCatalog
	 */
	public List<ChannelCatalogRcmdConfig> findChannelIsExit(Integer groupId, Integer channel, Integer type) {
		return floorCatalogRcmdDao.findChannelIsExit(groupId, channel, type);
	}

	/**
	 * 保存
	 * 
	 * @param channelCatalog
	 */
	public void save(ChannelCatalogRcmdConfig channelCatalog) {
		floorCatalogRcmdDao.save(channelCatalog);
	}

	/**
	 * 修改预处理
	 * 
	 * @param id
	 * @param model
	 * @throws SQLException
	 */
	public void preEdit(Long id, Model model) throws SQLException {
		ChannelCatalogRcmdConfig channelCatalogRcmd = floorCatalogRcmdDao.findOne(id);

		ChannelFloor  channelFloor = channelFloorDao.findChannelFloorByChannelAndFloor(channelCatalogRcmd.getChannel(), channelCatalogRcmd.getGroupId());
		String[] areas = channelFloor.getRegionCode().split(",");
		
		String regionCode = channelCatalogRcmd.getRegionCode();
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
		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("channelMap", ChannelCatalogRcmdConfig.channelMap);
		model.addAttribute("channelCatalogRcmd", channelCatalogRcmd);
		model.addAttribute("method", "edit");

	}

	/**
	 * 删除
	 * 
	 * @param id
	 */
	public void delete(Long id) {
		floorCatalogRcmdDao.delete(id);
	}
	/**
	 * add by xq
	 * */
	public ChannelCatalogRcmdConfig findChannelCatalogRcmdConfigById(Long id){
		return floorCatalogRcmdDao.findOne(id);
	}

}
