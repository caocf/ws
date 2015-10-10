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
import com.cplatform.mall.back.sys.entity.SysRegion;
import com.cplatform.mall.back.sys.service.SysRegionService;
import com.cplatform.mall.back.websys.dao.ChannelNaviOperDao;
import com.cplatform.mall.back.websys.dao.ChannelNaviPageDao;
import com.cplatform.mall.back.websys.entity.ChannelNaviOper;

@Service
public class ChannelNaviOperService {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	ChannelNaviOperDao channelNaviOperDao;
//	
	@Autowired
	SysRegionService regionService;
	
	@Autowired
	ChannelNaviPageDao channelNaviPageDao;
//	
//	@Autowired
//	SysTypeDao sysTypeDao;

	public Page<ChannelNaviOper> findChannelNaviOper(ChannelNaviOper channelNaviOper, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cf.id,cf.page_id,cf.code,cf.title,cf.region_code,cf.href,cf.sort_no,cf.create_user createUser,cf.create_time createTime,cf.update_user updateUser,cf.update_time updateTime,r.region_name regionName,p.title pageCodeTitle from T_NAV_ITEM cf ");
		sql.append(" left join t_sys_region r on cf.region_code = r.region_code  ");
		sql.append(" left join T_WEB_PAGE p on cf.page_code = p.code  ");
		sql.append("  where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (channelNaviOper != null) {
			if (StringUtils.isNotEmpty(channelNaviOper.getTitle())) {
				sql.append(" and cf.title like ? ");
				paramsList.add("%" + channelNaviOper.getTitle().trim() + "%");
			}

		}
		sql.append(" order by cf.sort_no ");
		//sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_FLOOR));

		return dbHelper.getPage(sql.toString(), ChannelNaviOper.class, pageNo, pageSize, paramsList.toArray());
	}
	
	public void preEdit(Long id, Model model) {
		ChannelNaviOper channelNaviOper = channelNaviOperDao.findOne(id);

		//code前台只展示code
//        if (StringUtils.isNotBlank(channelNaviOper.getCode()))
//        {
//            String[] st = channelNaviOper.getCode().split("_");
//            
//            channelNaviOper.setCode(st[0]);
//        }
		
		if ("0".equals(channelNaviOper.getRegionCode()))
		{
			channelNaviOper.setRegionName("全国");
		}
		else
		{
			SysRegion region = regionService.findByRegionCode(channelNaviOper.getRegionCode());
			if (region != null) {
				channelNaviOper.setRegionName(region.getRegionName());
			}
		}
		
//		model.addAttribute("groupMap", ChannelCatalogRcmdConfig.groupMap);
		model.addAttribute("channelMap", channelNaviOper.channelMap);
		model.addAttribute("channelNaviOper", channelNaviOper);
		model.addAttribute("method", "edit");
		
		//添加pagecode选项
		model.addAttribute("pageCodeList",channelNaviPageDao.findChannelNaviPage());

	}
	public ChannelNaviOper saveChannelNaviOper(ChannelNaviOper channelNaviOper) {
		return channelNaviOperDao.save(channelNaviOper);
	}
//
//	public ChannelFloor updateChannelFloor(ChannelFloor channelfloor) {
//		return channelFloorDao.save(channelfloor);
//	}

}
