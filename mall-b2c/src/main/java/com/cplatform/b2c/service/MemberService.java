package com.cplatform.b2c.service;

import com.cplatform.b2c.model.Member;
import com.cplatform.dbhelp.DbHelper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLException;

/**
 * Title. <br>
 * Description.
 * <p/>
 * Copyright: Copyright (c) 13-5-27 涓嬪崍3:23
 * <p/>
 * Company: 鍖椾含瀹借繛鍗佹柟鏁板瓧鎶�湳鏈夐檺鍏徃
 * <p/>
 * Author: nicky
 * <p/>
 * Version: 1.0
 * <p/>
 */
@Service
public class MemberService {

	@Autowired
	DbHelper dbHelper;

	public Member findByUserName(String username) throws SQLException {
		return dbHelper.getBean("select * from t_member where user_name = ?",
				Member.class, username);
	}

	public Member findByUserId(long id) throws SQLException {
		return dbHelper.getBean("select * from t_member where id = ?",
				Member.class, id);
	}

}
