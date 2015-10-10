package com.cplatform.mall.back.websys.dao;

import java.util.List;

import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.SysFileImgLink;

/**
 * Title. 通用文件存储<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-5-29 下午5:52:31
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: BaiJie
 * <p>
 * Version: 1.0
 * <p>
 */
public interface SysFileImgLinkDao extends PagingAndSortingRepository<SysFileImgLink, Long> {

	/**
	 * 获得该业务对应的文件
	 * 
	 * @param bsKey
	 *            业务key
	 * @param bsId
	 *            业务id
	 * @return
	 */
	public List<SysFileImgLink> findByBsKeyAndBsId(String bsKey, Long bsId);

	/**
	 * 获得该业务对应的文件（判断排序是否重复）
	 * 
	 * @param bsId
	 *            业务id
	 * @param sort
	 *            排序
	 * @return
	 */
	public List<SysFileImgLink> findByBsIdAndSort(Long bsId, Integer sort);

	public List<SysFileImgLink> findByBsId(Long bsId);
	
	public SysFileImgLink findByBsIdAndBsKey(Long bsId, String bsKey);

	public SysFileImgLink findByBsKeyAndBsIdAndFileId(String bsKey, Long bsId, Long fileId);

	public SysFileImgLink findOneByFileId(Long fileid);
}
