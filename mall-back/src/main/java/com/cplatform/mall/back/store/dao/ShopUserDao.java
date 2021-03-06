package com.cplatform.mall.back.store.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.ShopUser;

/**
 * 渠道商、结算商户、业务门店账号 Title. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 下午1:31:21
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: baibo@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
public interface ShopUserDao extends PagingAndSortingRepository<ShopUser, Long> {
	/**
	 * 根据用户账户匹配密码
	 * @param UserId
	 * @param UserPwd
	 * @return
	 */
	public ShopUser findByCodeAndPwd(String UserId,String UserPwd);
	
	
	public ShopUser findByCode(String code);
	
	

}
