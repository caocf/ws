package com.cplatform.mall.back.dao;

import com.cplatform.mall.back.entity.SysMenu;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-21 下午1:31
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
public interface MenuDao extends PagingAndSortingRepository<SysMenu, String> {

    @Query("select m from SysMenu m where m.sysType=0 order by m.menuCode asc")
    List<SysMenu> findAllSysMenu();

}
