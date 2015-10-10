package com.cplatform.mall.storeuc.service;

import com.cplatform.dbhelp.DbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;
import java.util.Map;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-27 下午3:23
 * <p/>
 * Company: 北京宽连十方数字技术有限公司
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class DataAccessService {

    @Autowired
    DbHelper dbHelper;

    public Map<String, String> findByCode(String code) throws SQLException {

        return dbHelper.getMap("select * from t_shop_user where code = ? and (status = 1 or status = 0)", code);
    }

}
