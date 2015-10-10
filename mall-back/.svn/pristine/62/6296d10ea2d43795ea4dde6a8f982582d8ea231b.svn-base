package com.cplatform.mall.back.member.dao;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.member.entity.Member;

/**
 * 
 * 会员管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:08:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface MemberDao extends PagingAndSortingRepository<Member, Long> {
	/**
	 * 根据账号查询（查询账号是否唯一）
	 * @param userName    用户账号
	 * @return
	 */
	Member findByUserName(String userName);

}
