package com.cplatform.mall.back.comment.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.comment.dao.ShopCommentDao;
import com.cplatform.mall.back.comment.entity.ShopComment;
import com.cplatform.mall.back.utils.data.RoleDataUtils;

/**
 * Title. 门店评论操作<br>
 * Description.
 * <p>
 * Copyright: Copyright (c) 2013-6-4 上午10:31:33
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: LiuDong@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@Service
public class ShopCommentService {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private ShopCommentDao shopCommentDao;

	public ShopComment addOrUpdateShopComment(ShopComment po) {
		return this.shopCommentDao.save(po);
	}

	public ShopComment findShopCommentById(Long id) {
		return this.shopCommentDao.findOne(id);
	}

	public void delShopCommentById(Long id) {
		this.shopCommentDao.delete(id);
	}

	/**
	 * 查询评论内容
	 * 
	 * @param vo
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<ShopComment> findShopComment(ShopComment vo, int pageNo, int pageSize) throws SQLException {
		StringBuilder sqlBuff = new StringBuilder();
		List params = new ArrayList();
		sqlBuff.setLength(0);
		sqlBuff.append("select t1.*, t2.name ,t2.area_code");
		sqlBuff.append("  from t_shop_comment t1, t_shop t2 ");
		sqlBuff.append(" where t1.shop_id = t2.id  and  t1.shop_class = ?");
		params.add(vo.getShopClass());
		if (StringUtils.isNotBlank(vo.getName())) {
			sqlBuff.append("  and t2.name like ? ");
			params.add("%" + vo.getName().trim() + "%");
		}

		if (vo.getStatus() != null) {
			sqlBuff.append("   and t1.status = ? ");
			params.add(vo.getStatus());
		}
		sqlBuff.append(RoleDataUtils.getRoleData(RoleDataUtils.MODULE_SHOPCOMMENT));
		sqlBuff.append(" order by t1.update_time desc");
		return dbHelper.getPage(sqlBuff.toString(), ShopComment.class, pageNo, pageSize, params.toArray());
	}

}
