package com.cplatform.b2c.util;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import net.sf.json.JSONObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.b2c.model.TChannelType;
import com.cplatform.b2c.model.TChannelTypeDTO;
import com.cplatform.b2c.model.TSysType;
import com.cplatform.b2c.model.TSysTypeDTO;
import com.cplatform.b2c.service.ChannelTypeService;
import com.cplatform.b2c.service.SysTypeService;
import com.cplatform.dbhelp.DbHelper;

@Component
public class SysTypeUtil {

	private Integer channel = 2;

	// private Integer displayChannel = 1;//用于显示

	@Autowired
	private SysTypeService sysTypeService;

	@Autowired
	private ChannelTypeService channelTypeService;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	DbHelper dbHelper;

	private List<TSysType> typeList;

	private List<TChannelType> channelTypeList;

	private SysTypeUtil() {
	}

	@PostConstruct
	public void init() {
		typeList = sysTypeService.getAllList(channel);
	}

	public List<TChannelTypeDTO> getDisplayTypeList(Integer displayChannel, Integer channel, String regionCode) {
		setChannel(channel);
		List<TChannelTypeDTO> dtoList = new ArrayList<TChannelTypeDTO>();
		channelTypeList = channelTypeService.getList(displayChannel, regionCode);
		if (channelTypeList != null && channelTypeList.size() > 0) {
			for (TChannelType channelType : channelTypeList) {
				TChannelTypeDTO tChannelTypeDTO = new TChannelTypeDTO();
				tChannelTypeDTO.setId(channelType.getId());
				tChannelTypeDTO.setChannel(channelType.getChannel());
				tChannelTypeDTO.setDisplayName(channelType.getDisplayName());
				tChannelTypeDTO.setRegionCode(channelType.getRegionCode());
				tChannelTypeDTO.settSysTypeDTOList(getChildSort(channelType.getTypeId()));
				tChannelTypeDTO.setTypeId(channelType.getTypeId());
				dtoList.add(tChannelTypeDTO);
			}
		}
		return dtoList;
	}

	public List<TSysTypeDTO> getChildSort(Integer pId) {
		List<TSysTypeDTO> list = new ArrayList<TSysTypeDTO>();
		for (TSysType type : typeList) {
			if (type.getPId().intValue() == pId.intValue() && type.getIsValid() == 1) {
				TSysTypeDTO dto = new TSysTypeDTO();
				dto.setId(type.getId());
				dto.setPId(type.getPId());
				dto.setName(type.getName());
				dto.setCount(0);// 计数暂时为0
				List<TSysTypeDTO> childList = getChildSort(type.getId());
				if (childList != null && childList.size() > 0) {
					dto.setTypeList(childList);
				}
				list.add(dto);
			}
		}
		return list;
	}

	public List<TSysType> getParentTypeList(Integer typeId) {
		return sysTypeService.getParentTypeList(typeId, channel);
	}

	public String getMenuTypeList(String webroot, String region_code, String type_id, String sort, String searchSource, String curpage) {
		List<TSysType> typesList = this.getParentTypeList(Integer.parseInt(type_id));
		String menuTab = "{'menu':[";
		String menuString = "";
		if (typesList != null && typesList.size() > 0) {
			for (int i = 0; i < typesList.size(); i++) {
				JSONObject menuMap = new JSONObject();
				menuMap.put("id", typesList.get(i).getId());
				menuMap.put("name", typesList.get(i).getName());
				menuMap.put("href", webroot + appConfig.getMenu_Type_Search_Url() + "?" + "sort=" + sort + "&region_code=" + region_code
				        + "&type_id=" + typesList.get(i).getId() + "&searchSource=" + searchSource + "&curpage=1");
				if (i == 0) {
					menuString += menuMap.toString();
				} else if (i > 0) {
					menuString += "," + menuMap.toString();
				}

			}
			menuTab += menuString;
			menuTab += "],'flag':'ok'}";
		} else {
			menuTab += "],'flag':'no'}";
		}

		return menuTab;
	}

	public Integer getChannel() {
		return channel;
	}

	public void setChannel(Integer channel) {
		// if(channel != null && !channel.equals(this.channel)){
		typeList = sysTypeService.getAllList(channel);
		// }
		this.channel = channel;
	}

	/**
	 * 通过 商品父类型 查出 所有子类型
	 * 
	 * @param pid
	 * @return
	 * @throws SQLException
	 */
	public List<Map<String, Object>> findLaoBaoTypeByPid(String pid) throws SQLException {
		String sql = "select id,name from T_SYS_TYPE t where type =2 and is_valid = 1 and p_id =? order by id ";
		return dbHelper.getNativeMapList(sql, pid);
	}

}