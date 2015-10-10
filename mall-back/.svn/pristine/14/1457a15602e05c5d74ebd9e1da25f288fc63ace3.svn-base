package com.cplatform.mall.back.item.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.item.entity.ThirdCodeImport;
import com.cplatform.mall.back.item.entity.VerifyCardCode;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-11-5
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
public interface VerifyCardCodeDao extends PagingAndSortingRepository<VerifyCardCode, Long>{

	@Query("select t from VerifyCardCode t where t.cardId = ?1 ")
	public VerifyCardCode findCardCodeByCardId(String cardId);
	
}
