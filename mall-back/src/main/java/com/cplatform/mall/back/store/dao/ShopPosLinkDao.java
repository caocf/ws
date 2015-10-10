package com.cplatform.mall.back.store.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.store.entity.ShopPosLink;

/**
 * 
 * 业务门店终端配置管理jpa数据访问接口. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-5-28 上午11:08:32
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * @author zhaowei@c-platform.com
 * @version 1.0.0
 */
public interface ShopPosLinkDao extends PagingAndSortingRepository<ShopPosLink, Long> {
	/**
	 * 根据终端ID查询列表
	 * @param posId
	 * @param posNo
	 * @param terminalId
	 * @return
	 */
	@Query("select t from ShopPosLink t where t.posId = ?1 and t.posNo = ?2 and t.terminalId = ?3 order by t.id asc")
	public List<ShopPosLink> findPosLink(Long posId, String posNo, String terminalId);
	/**
	 * 门店终端设备号唯一
	 * @param posNo
	 * @return
	 */
	@Query("select t from ShopPosLink t where t.posNo = ?1 order by t.id asc")
	public List<ShopPosLink> findOnePosNo(String posNo);
	/**
	 * 门店终端标识唯一
	 * @param terminalId
	 * @return
	 */
	@Query("select t from ShopPosLink t where t.terminalId = ?1 order by t.id asc")
	public List<ShopPosLink> findOneTerminalId(String terminalId);
	/**
	 * 根据门店ID查询列表
	 * 
	 * @param shopId
	 *            
	 * @return
	 */
	public List<ShopPosLink> findByShopId(Long shopId);
	/**
	 * 根据Pos_ID查询列表
	 * 
	 * @param posId
	 *            
	 * @return
	 */
	public List<ShopPosLink> findByPosId(Long posId);
}
