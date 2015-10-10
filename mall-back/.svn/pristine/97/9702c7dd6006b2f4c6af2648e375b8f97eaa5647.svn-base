package com.cplatform.mall.back.smsbuy.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.smsbuy.entity.SmsbuyItemRouter;

public interface SmsbuyItemRouterDao extends PagingAndSortingRepository<SmsbuyItemRouter, Long> {

	List<SmsbuyItemRouter> findBySpCodeAndCommand(String spCode, String command);

	@Query("from SmsbuyItemRouter a where a.spCode = ?1 and a.command = ?2 and a.id != ?3")
	List<SmsbuyItemRouter> findBySpCodeAndCommand(String spCode, String command, Long id);

	List<SmsbuyItemRouter> findByActId(Long actId);

	/**
	 * @param actId
	 * @param itemId
	 * @return
	 * @author macl@c-platform.com
	 * @date 2013-7-13
	 */
	List<SmsbuyItemRouter> findByActIdAndItemIdAndPayType(Long actId, String itemId, Integer payType);

}
