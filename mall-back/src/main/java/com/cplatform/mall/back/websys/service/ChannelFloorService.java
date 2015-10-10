package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.sys.dao.SysTypeDao;
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.entity.SysType;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.mall.back.websys.dao.ChannelFloorDao;
import com.cplatform.mall.back.websys.entity.ChannelCatalogRcmdConfig;
import com.cplatform.mall.back.websys.entity.ChannelFloor;

@Service
public class ChannelFloorService {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	ChannelFloorDao channelFloorDao;
	
	@Autowired
	SysRegionService regionService;
	
	@Autowired
	SysTypeDao sysTypeDao;
	
	@Autowired
	private SysRegionService sysRegionService;
	

	public Page<ChannelFloor> findChannelFloor(ChannelFloor channelfloor, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cf.*,cf.title floorTitle ,st.name typeName from T_CHANNEL_FLOOR_CONF cf ");
		sql.append(" left join  t_sys_type st on cf.type_id = st.id   ");
		sql.append("  where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (channelfloor != null) {
			if (StringUtils.isNotEmpty(channelfloor.getFloorTitle())) {
				sql.append(" and cf.title like ? ");
				paramsList.add("%" + channelfloor.getFloorTitle().trim() + "%");
			}
			if (channelfloor.getFloorId() != null) {
				sql.append(" and cf.floor_id  = ? ");
				paramsList.add(channelfloor.getFloorId());
			}
			if (channelfloor.getChannel() != null) {
				sql.append(" and cf.channel=?");
				paramsList.add(channelfloor.getChannel());
			}
			
			if (StringUtils.isNotEmpty(channelfloor.getRegionName())) {
				sql.append(" AND r.region_name like ? ");
				paramsList.add("%" + channelfloor.getRegionName().trim() + "%");
			}

		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_FLOOR));

		return dbHelper.getPage(sql.toString(), ChannelFloor.class, pageNo, pageSize, paramsList.toArray());
	}
	public List<ChannelFloor> findChannelFloorByrf(ChannelFloor channelfloor, Integer integer, String string) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cf.*  from T_CHANNEL_FLOOR_CONF cf where cf.CHANNEL = ? and cf.REGION_CODE = ? order by cf.FLOOR_ID");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(integer);
		paramsList.add(string);
		return  dbHelper.getBeanList(sql.toString(), ChannelFloor.class,paramsList.toArray());
	}
	
	public void preEdit(Long id, Model model) {
		ChannelFloor channelfloor = channelFloorDao.findOne(id);
		model.addAttribute("groupMap", ChannelFloor.groupMap);
		model.addAttribute("channelMap", ChannelFloor.channelMap);
		model.addAttribute("channelFloor", channelfloor);
		String[] regionCodes = channelfloor.getRegionCode().split(",");
		String regionCodeName = "";
		for (int i = 0; i < regionCodes.length; i++) {
			if (i == regionCodes.length - 1) {
				regionCodeName += sysRegionService.findByRegionCode(regionCodes[i]).getRegionName();
			} else {
				regionCodeName += sysRegionService.findByRegionCode(regionCodes[i]).getRegionName() + ",";
			}
		}
		model.addAttribute("regionCodeName",regionCodeName );
		model.addAttribute("sysTypeName", sysTypeDao.findOne(channelfloor.getTypeId()).getName());
		model.addAttribute("method", "edit");

	}
	
	public int getCount(Integer floorId,Integer channel) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select count(*) from t_channel_floor_conf t");
		sqlBuff.append(" where t.FLOOR_ID = ? ");
		sqlBuff.append("   and t.CHANNEL = ?");
		List<Object> params = new ArrayList<Object>();
		params.add(floorId);
		params.add(channel);
		String count = dbHelper.queryScalar(sqlBuff.toString(), params.toArray());
		return Integer.valueOf(count);
	}

	public ChannelFloor saveChannelFloor(ChannelFloor channelfloor) {
		return channelFloorDao.save(channelfloor);
	}

	public ChannelFloor updateChannelFloor(ChannelFloor channelfloor) {
		return channelFloorDao.save(channelfloor);
	}

}
