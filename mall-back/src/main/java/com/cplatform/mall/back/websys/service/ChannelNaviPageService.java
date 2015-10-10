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
import com.cplatform.mall.back.sys.entity.SysType;
import com.cplatform.mall.back.websys.dao.ChannelNaviPageDao;
import com.cplatform.mall.back.websys.entity.ChannelCatalogRcmdConfig;
import com.cplatform.mall.back.websys.entity.ChannelNaviPage;

@Service
public class ChannelNaviPageService {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	ChannelNaviPageDao channelNaviPageDao;
//	
//	@Autowired
//	SysRegionService regionService;
//	
//	@Autowired
//	SysTypeDao sysTypeDao;

	public Page<ChannelNaviPage> findChannelNaviPage(ChannelNaviPage channelNaviPage, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select cf.id ,cf.code,cf.title,cf.des,cf.create_user createUser,cf.create_time createTime,cf.update_user updateUser,cf.update_time updateTime  from T_WEB_PAGE cf ");
		sql.append(" where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (channelNaviPage != null) {
			if (StringUtils.isNotEmpty(channelNaviPage.getTitle())) {
				sql.append(" and cf.title like ? ");
				paramsList.add("%" + channelNaviPage.getTitle().trim() + "%");
			}

		}

		return dbHelper.getPage(sql.toString(), ChannelNaviPage.class, pageNo, pageSize, paramsList.toArray());
	}
	
	public void preEdit(Long id, Model model) {
		ChannelNaviPage channelNaviPage = channelNaviPageDao.findOne(id);

		//code前台只展示code
//        if (StringUtils.isNotBlank(channelNaviPage.getCode()))
//        {
//            String[] st = channelNaviPage.getCode().split("_");
//            
//            channelNaviPage.setCode(st[0]);
//        }
		
		model.addAttribute("channelMap", channelNaviPage.channelMap);
		model.addAttribute("channelNaviPage", channelNaviPage);
		model.addAttribute("method", "edit");

	}
	public ChannelNaviPage saveChannelNaviPage(ChannelNaviPage channelNaviPage) {
		return channelNaviPageDao.save(channelNaviPage);
	}
//
//	public ChannelFloor updateChannelFloor(ChannelFloor channelfloor) {
//		return channelFloorDao.save(channelfloor);
//	}

}
