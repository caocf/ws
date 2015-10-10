package com.cplatform.mall.back.websys.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.mall.back.websys.dao.ChannelTypeDao;
import com.cplatform.mall.back.websys.entity.ChannelType;

@Service
public class ChannelTypeService {

	@Autowired
	DbHelper dbHelper;

	@Autowired
	ChannelTypeDao channelTypeDao;

	public Page<ChannelType> findChannelType(ChannelType type, int pageNo, int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select ct.*, st.name typeName, r.region_name ");
		sql.append("  from T_CHANNEL_TYPE ct join  t_sys_type st on ct.type_id = st.id   ");
		sql.append(" left join t_sys_region r on ct.region_code = r.region_code");
		sql.append("  where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (type != null) {
			if (StringUtils.isNotEmpty(type.getRegionName())) {
				sql.append(" and r.region_name like ? ");
				paramsList.add("%" + type.getRegionName().trim() + "%");
			}
			if (type.getChannel() != null) {
				sql.append(" and ct.channel = ? ");
				paramsList.add(type.getChannel());
			}
			if (StringUtils.isNotEmpty(type.getTypeName())) {
				sql.append(" and st.name like  ? ");
				paramsList.add("%" + type.getTypeName().trim() + "%");
			}

		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_CHANNELTYPE));
		return dbHelper.getPage(sql.toString(), ChannelType.class, pageNo, pageSize, paramsList.toArray());
	}

	public ChannelType saveType(ChannelType channelType) {
		return channelTypeDao.save(channelType);
	}

	public ChannelType updateType(ChannelType channelType) {
		return channelTypeDao.save(channelType);
	}

}
