package com.cplatform.mall.back.websys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.SysFileImgThumb;

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
public interface SysFileImgThumbDao extends PagingAndSortingRepository<SysFileImgThumb, Long> {

	/**
	 * 根据业务key 和id删除缩略图表
	 * 
	 * @param bsKey
	 *            业务key
	 * @param bsId
	 *            业务id
	 */
	@Query(value = "delete from T_SYS_FILE_IMG_THUMB where file_id in (select file_id from T_SYS_FILE_IMG_LINK where BS_KEY=?1 and BS_ID=?2)", nativeQuery = true)
	public void deleteThumbByBsKeyAndBsId(String bsKey, Long bsId);

	/**
	 * 删除按照原始文件id，删除对应的缩略图
	 * 
	 * @param fileId
	 */
	@Query("delete from SysFileImgThumb where fileId = ?1")
	public void deleteThumbByFileId(Long fileId);

	/**
	 * 根据文件编号，获得对应的缩略图文件
	 * 
	 * @param fileId
	 *            原文件编号
	 * @return
	 */
	public List<SysFileImgThumb> findByFileId(Long fileId);

}
