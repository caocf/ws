package com.cplatform.mall.back.store.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.store.dao.ShopSettingsDao;
import com.cplatform.mall.back.store.entity.ShopSettings;
/**
 * 门店管理相关. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-31 下午08:06:27
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author luyidi@c-platform.com
 * @version 1.0.0
 */
@Service
public class ShopSettingsService {

	@Autowired
	private ShopSettingsDao shopSettingsDao;

	@Autowired
	private DbHelper dbHelper;
	
	
	/**
	 * 查询
	 * 
	 * @param vo
	 * @param pageNo
	 * @param pagesize
	 * @return
	 * @throws SQLException
	 */

	public Page<ShopSettings> findShopSettings(ShopSettings vo, int pageNo, int pageSize)
			throws SQLException {
		          
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.append("select t.*,s.name  from t_shop_settings t");
		sql.append(" inner join t_store s ");
		sql.append(" on  t.shop_id = s.id ");
		//查询所有数据（包含已审核过的）,modify by chen 2013-09-16 start <<<
//		sql.append(" where  t.pub_tag = 0");
		//>>>end
		if(null!=vo.getId()){
			sql.append(" and  t.id = ?");
			params.add(vo.getId());
		}
		
		if(null!=vo.getShopId())
		{
			sql.append(" and t.shop_id = ?");
			params.add(vo.getShopId());
			
		}
		if(null!=vo.getPubTag())
		{
			sql.append(" and t.pub_tag = ?");
			params.add(vo.getPubTag());
			
		}
		if(StringUtils.isNotEmpty(vo.getName()))
		{
			sql.append(" and  s.name like ?");
			params.add("%"+vo.getName().trim()+"%");
		}
	
		sql.append(" and  s.status = ? ");
		params.add(3);
	
		Page<ShopSettings>  pageData =  dbHelper.getPage(sql.toString(), ShopSettings.class, pageNo, pageSize,
				params.toArray());
		List<ShopSettings> page= pageData.getData();
		if(page!=null && page.size()>0){
			for(int i=0;i<page.size();i++){
				ShopSettings bean = page.get(i);
				if(bean.getHomePageWord()!=null && bean.getHomePageWord().length()>=20){
					bean.setHomePageWord(bean.getHomePageWord().substring(0,20)+"...");
				}
			}
		}
		 return pageData;

	}
	
	  public ShopSettings findByShopSettingId(Long id) throws SQLException
	  {  
		  ShopSettings settings = new ShopSettings();
		  StringBuilder sql = new StringBuilder();
			List<Object> params = new ArrayList<Object>();
			sql.append("select t.*,s.name  from t_shop_settings t");
			sql.append(" inner join t_store s ");
			sql.append(" on  t.shop_id = s.id ");
			if(null!=id){
				sql.append(" and  t.id = ?");
				params.add(id);
			}
		
			settings = dbHelper.getBean(sql.toString(), ShopSettings.class,params.toArray());
		   return settings;
		  
	  }
	  
	   public  ShopSettings updatePubTag(ShopSettings settings)
	   {
		   
		 return  shopSettingsDao.save(settings);
	   }
}
