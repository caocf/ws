package com.cplatform.b2c.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.commons.lang.ArrayUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.b2c.model.TChannelCatalogConf;
import com.cplatform.b2c.model.TChannelCatalogRcmdConf;
import com.cplatform.b2c.model.TChannelFloorConf;
import com.cplatform.b2c.model.TChannelPicConf;
import com.cplatform.b2c.model.TSysType;
import com.cplatform.b2c.model.VSearchGood;
import com.cplatform.b2c.repository.TChannelCatalogConfDao;
import com.cplatform.b2c.repository.TChannelCatalogRcmdConfDao;
import com.cplatform.b2c.repository.TChannelFloorConfDao;
import com.cplatform.b2c.repository.TChannelPicConfDao;
import com.cplatform.b2c.repository.TSysTypeDao;
import com.cplatform.b2c.repository.VSearchGoodDao;

/**
 * 
 * 商户信息相关类
 * <p>
 * Copyright: Copyright (c) 2013-6-1 上午10:12:16
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zouyl@c-platform.com
 * @version 1.0.0
 */
@Service
@Transactional
public class MallService {

	@Autowired
	private TChannelPicConfDao tChannelPicConfDao;
	
	@Autowired
	private TChannelCatalogConfDao tChannelCatalogConfDao;
	
	@Autowired
	private VSearchGoodDao vSearchGoodDao;
	
	@Autowired
	private TSysTypeDao tSysTypeDao;
	
	@Autowired
	private TChannelCatalogRcmdConfDao tChannelCatalogRcmdConfDao;
	
	@Autowired
	private TChannelFloorConfDao tChannelFloorConfDao;
	
	
	public List<TChannelPicConf> getPicList(String channel, String position){
		return tChannelPicConfDao.getList(channel, position);
	}
	
	public List<TChannelPicConf> getPicList(String channel){
		return tChannelPicConfDao.getList(channel);
	}
	
	public List<TChannelPicConf> getPicListByCode(String channel, String regionCode){
		return tChannelPicConfDao.getListByCode(channel, regionCode);
	}
	
	public List<TChannelPicConf> getPicListByCode(String channel){
		return tChannelPicConfDao.getListByCode(channel);
	}
	
	public List<TChannelCatalogConf> getCatalogList(String channel, String groupId){
		return tChannelCatalogConfDao.getList(channel, groupId);
	}
	
	public List<TChannelCatalogRcmdConf> getCatalogRcmdList(String channel){
		return tChannelCatalogRcmdConfDao.getList(Integer.parseInt(channel));
	}
	
	public List<TChannelCatalogRcmdConf> getCatalogRcmdList(String groupId, String channel, String type){
		return tChannelCatalogRcmdConfDao.getList(Integer.parseInt(groupId), Integer.parseInt(channel), Integer.parseInt(type));
	}
	
	public List<TChannelCatalogRcmdConf> getCatalogRcmdListByCode(String channel, String regionCode){
		return tChannelCatalogRcmdConfDao.getListByCode(Integer.parseInt(channel), regionCode);
	}
	
	public List<TChannelCatalogRcmdConf> getCatalogRcmdListByCode(String channel){
		return tChannelCatalogRcmdConfDao.getListByCode(Integer.parseInt(channel));
	}
	
	public Integer[] getCatalogListItemIds(String channel, String groupId){
		List<TChannelCatalogConf> list = tChannelCatalogConfDao.getList(channel, groupId);
		Integer[] ids =  new Integer[] {};
		if( list != null && list.size() > 0 ){
			for(TChannelCatalogConf conf : list){
				ids =  (Integer[]) ArrayUtils.add(ids, conf.getItemId());
			}
		}
		return ids;
	}
	
	public Map<String, Integer[]> getCatalogMapItemIds(String channel, String regionCode){
		List<TChannelCatalogConf> list = tChannelCatalogConfDao.getValidList(channel, regionCode);
		Map<String, Integer[]> mapItemIds = new HashMap<String, Integer[]>();
		Set<String> keySet = new HashSet<String>();
		
		if(list != null && list.size() > 0){
			for(TChannelCatalogConf conf : list){
				String tmpKey = conf.getGroupId().toString();
				
				if(keySet.contains(tmpKey)){
					Integer[] ids = mapItemIds.get(tmpKey);
					ids = (Integer[]) ArrayUtils.add(ids, conf.getItemId());
					mapItemIds.put(tmpKey, ids);
				}else{
					Integer[] ids =  new Integer[] {};
					ids =  (Integer[]) ArrayUtils.add(ids, conf.getItemId());
					mapItemIds.put(tmpKey, ids);
					keySet.add(tmpKey);
				}		
			}
		}
		return mapItemIds;
	}
	
	public Map<String, Integer[]> getCatalogMapItemIds(String channel){
		List<TChannelCatalogConf> list = tChannelCatalogConfDao.getValidList(channel);
		Map<String, Integer[]> mapItemIds = new HashMap<String, Integer[]>();
		Set<String> keySet = new HashSet<String>();
		
		if(list != null && list.size() > 0){
			for(TChannelCatalogConf conf : list){
				String tmpKey = conf.getGroupId().toString();
				
				if(keySet.contains(tmpKey)){
					Integer[] ids = mapItemIds.get(tmpKey);
					ids = (Integer[]) ArrayUtils.add(ids, conf.getItemId());
					mapItemIds.put(tmpKey, ids);
				}else{
					Integer[] ids =  new Integer[] {};
					ids =  (Integer[]) ArrayUtils.add(ids, conf.getItemId());
					mapItemIds.put(tmpKey, ids);
					keySet.add(tmpKey);
				}		
			}
		}
		return mapItemIds;
	}
	
	public List<VSearchGood> getProductList(Integer[] ids){
		if(ids == null || ids.length == 0){
			return null;
		}
		return vSearchGoodDao.getList(ids);
	}
	
	public Map<String, VSearchGood> getProductMap(Integer[] ids){
		if(ids == null || ids.length == 0){
			return null;
		}
		List<VSearchGood> list = vSearchGoodDao.getListBy(ids, 0);
		if(list == null || list.size() == 0){
			return null;
		}
		
		Map<String, VSearchGood> map = new HashMap<String, VSearchGood>();
		
		for(VSearchGood v : list){
			map.put(v.getId().getGId().toString(), v);
		}
		return map;
	}
	
	public List<VSearchGood> getHotList(Integer typeId, Integer channel, int maxResults){
		if(typeId == null){
			return null;
		}
		Integer[] ids =  new Integer[] {};
		List<TSysType> typeList = new ArrayList<TSysType>();
		if(typeId.intValue() == 0){
			typeList = tSysTypeDao.getTopTypeList(channel);
		}else{
			typeList = tSysTypeDao.getTypeListByParent(typeId, channel);
		}
		
		if( typeList != null && typeList.size() > 0 ){
			for(TSysType tt : typeList){
				ids =  (Integer[]) ArrayUtils.add(ids, tt.getId());
			}
		}
		if(ids.length == 0){
			return null;
		}
		return vSearchGoodDao.getListByType(ids, maxResults);
	}
	
	public Integer[] getDefFloorIds(Integer typeId, Integer channel, int maxResults){
		if(typeId == null){
			return null;
		}
		Integer[] ids =  new Integer[] {};
		List<TSysType> typeList = new ArrayList<TSysType>();
		if(typeId.intValue() == 0){
			typeList = tSysTypeDao.getTopTypeList(channel);
		}else{
			typeList = tSysTypeDao.getTypeListByParent(typeId, channel);
		}
		
		if( typeList != null && typeList.size() > 0 ){
			for(TSysType tt : typeList){
				ids =  (Integer[]) ArrayUtils.add(ids, tt.getId());
			}
		}
		if(ids.length == 0){
			return ids;
		}
		List<VSearchGood> list =  vSearchGoodDao.getDefListByType(ids, maxResults);
		
		Integer[] ids2 =  new Integer[] {};
		if( list != null && list.size() > 0 ){
			for(VSearchGood good : list){
				ids2 =  (Integer[]) ArrayUtils.add(ids2, good.getId().getGId());
			}
		}
		return ids2;
	}
	
	public List<TChannelFloorConf> getFloorList(Integer channel, String regionCode){
		return tChannelFloorConfDao.getList(channel, regionCode);
	}
	
	public List<TChannelFloorConf> getFloorList(Integer channel){
		return tChannelFloorConfDao.getList(channel);
	}
	
}
