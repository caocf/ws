package com.cplatform.mall.back.websys.dao;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.cplatform.mall.back.websys.entity.SysFile;

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
public interface SysFileDao extends PagingAndSortingRepository<SysFile, Long> {

	@Query("select f.fileType,f.remark,f.updateTime,f.fileAbsPath,f.fileWebPath,f.createUser   from SysFile f,SysFileLink fl where f.id=fl.fileId and fl.bsId=?1 ")
	public List<Object[]> findFileByBsid(Long bsid);

	/**
	 * 获得该业务对应的文件
	 * 
	 * @param bsKey
	 *            业务key
	 * @param bsId
	 *            业务id
	 * @return
	 */
	public List<SysFile> findByBsKeyAndBsId(String bsKey, Long bsId);

}
