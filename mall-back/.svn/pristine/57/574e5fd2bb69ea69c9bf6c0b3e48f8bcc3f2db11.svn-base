package com.cplatform.mall.back.sys.dao;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.sys.entity.SysFilterWord;

/**
 * Title. 系统过滤字管理dao<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-21 下午5:15:38
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
public interface SysFilterWordDao extends PagingAndSortingRepository<SysFilterWord, Long> {
	/**
	 * 内容敏感字符检查
	 * @param word
	 * @param unitId
	 * @return
	 */
	@Query("select count(id) from SysFilterWord where instr(?1,word)>0 ")
	public long countFilterwordNum(String word);

	/**
	 * 检查过滤字是否存在
	 * @param word
	 * @param unitId
	 * @return
	 */
	@Query(value = "select count(id) from SysFilterWord where word = ?1 and unitId = ?2")
	public Long checkFilterWord(String word, String unitId);


}
