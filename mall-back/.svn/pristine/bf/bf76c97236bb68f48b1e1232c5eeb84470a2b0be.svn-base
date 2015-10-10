package com.cplatform.mall.dataif.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.comment.dao.ItemCommentDao;
import com.cplatform.mall.back.comment.entity.ItemComment;
import com.cplatform.mall.back.item.dao.ItemPriceDao;
import com.cplatform.mall.back.item.dao.ItemPriceTypeDao;
import com.cplatform.mall.back.item.entity.ItemPrice;
import com.cplatform.mall.back.item.entity.ItemPriceType;
import com.cplatform.mall.dataif.model.CommentInfo;
import com.cplatform.mall.dataif.model.ItemGroupInfo;
import com.cplatform.mall.dataif.model.ItemInfo;
import com.cplatform.mall.dataif.model.ItemPriceInfo;
import com.cplatform.mall.dataif.model.ItemProperty;
import com.cplatform.mall.dataif.model.QuestionInfo;
import com.cplatform.util2.TimeStamp;

@Service
public class ItemInterfaceService {

	@Autowired
	ItemCommentDao itemCommentDao;

	@Autowired
	DbHelper dbHelper;

	/**
	 * 评论是否有用
	 * 
	 * @param commentId
	 *            评论id
	 * @param isUseful
	 *            是否有用
	 * @return
	 * @throws SQLException
	 */
	public boolean itemUse(Long commentId, boolean isUseful)
			throws SQLException {
		String sql = "update t_item_comment set useful_Num = nvl(useful_Num,0) + ? ,useless_Num= nvl(useless_Num,0)+? where id=?";
		return dbHelper.execute(sql, new Object[] { isUseful ? 1 : 0,
				isUseful ? 0 : 1, commentId }) > 0;
	}

	/**
	 * 咨询列表
	 * 
	 * @param saleId
	 *            销售商品id
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<QuestionInfo> getItemQuestionPage(Long saleId, int pageNo,
			int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select ic.nickname , ");
		sql.append("       ic.content, ");
		sql.append("       ic.update_time, ");
		sql.append("       nvl(icr.content, '') replyContent, ");
		sql.append("       nvl(icr.update_time, '') replyTime ");
		sql.append("  from t_item_comment ic ");
		sql.append("  left join t_item_comment_reply icr ");
		sql.append("    on ic.id = icr.comment_id ");
		sql.append(" where type = 2 and status = 1  ");
		sql.append(" and sale_id = ? ");

		return dbHelper.getPage(sql.toString(), QuestionInfo.class, pageNo,
				pageSize, saleId);
	}

	/**
	 * 商品评论列表
	 * 
	 * @param saleId
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<CommentInfo> getItemCommentPage(Long saleId, int pageNo,
			int pageSize) throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select ic.nickname , ");
		sql.append("       ic.content, ");
		sql.append("       ic.update_time, ");
		sql.append("       nvl(ic.rank,0) rank, ");
		sql.append("       nvl(ic.useful_num,0) useful_num, ");
		sql.append("       nvl(ic.useless_num,0) useless_num ");
		sql.append("  from t_item_comment ic ");
		sql.append(" where type = 2 and status = 1  ");
		sql.append(" and sale_id = ? ");
		return dbHelper.getPage(sql.toString(), CommentInfo.class, pageNo,
				pageSize, saleId);
	}

	/**
	 * 获得商品属性列表
	 * 
	 * @param saleId
	 *            销售商品id
	 * @return
	 * @throws SQLException
	 */
	public Map<String, ItemProperty> getItemProperty(Long saleId)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select ip.id, ");
		sql.append("       item_id, ");
		sql.append("       property_id, ");
		sql.append("       sp.content  as property_name, ");
		sql.append("       ip.content  as property_value ");
		sql.append("  from t_item_property ip, t_sys_property sp ");
		sql.append(" where ip.property_id = sp.id ");
		sql.append(" and ip.item_id = ?");
		List<ItemProperty> propertList = dbHelper.getBeanList(sql.toString(),
				ItemProperty.class, saleId);
		Map<String, ItemProperty> ret = new HashMap<String, ItemProperty>();
		for (ItemProperty property : propertList) {
			ret.put(property.getId(), property);
		}
		return ret;
	}

	/**
	 * 保存咨询
	 * 
	 * @param saleId
	 * @param type
	 * @param content
	 * @param userId
	 * @return
	 */
	public boolean saveQuestionInfo(Long saleId, int type, String content,
			Long userId) {
		ItemComment ic = new ItemComment();
		ic.setSaleId(saleId);
		ic.setType(2);
		ic.setStatus(0);
		ic.setQuestionType(type);
		ic.setContent(content);
		ic.setUsefulNum(0L);
		ic.setUselessNum(0L);
		ic.setUpdateTime(TimeStamp.getTime(14));
		ic.setUserId(String.valueOf(userId));
		return itemCommentDao.save(ic) != null;
	}

	/**
	 * 获得历史记录
	 * 
	 * @param saleIds
	 * @return
	 * @throws SQLException
	 */
	public List<ItemInfo> getHistoryItem(String saleIds) throws SQLException {
		if (StringUtils.isEmpty(saleIds)) {
			return null;
		}
		StringBuilder sql = new StringBuilder();
		String[] datas = saleIds.split(",");
		List<String> params = new ArrayList<String>(datas.length);
		sql.append("select * from v_search_good where  g_id in ( ");
		for (int i = 0; i < datas.length; i++) {
			if (i == datas.length - 1) {
				sql.append("?");
			} else {
				sql.append("?,");
			}
			params.add(datas[i]);
		}

		sql.append(")");
		return dbHelper.getBeanList(sql.toString(), ItemInfo.class, params
				.toArray());
	}

	/**
	 * 单个商品
	 * 
	 * @param saleId
	 * @return
	 * @throws SQLException
	 */
	public ItemInfo getItemInfo(Long saleId) throws SQLException {
		String sql = "select * from v_search_good where g_id = ?";
		return dbHelper.getBean(sql, ItemInfo.class, saleId);
	}

	/**
	 * 获得套餐商品
	 * 
	 * @param saleId
	 * @return
	 * @throws SQLException
	 */
	public List<ItemGroupInfo> getItemGroup(Long saleId) throws SQLException {
		StringBuilder sql = new StringBuilder();
		// 获得查询商品对应的原始商品对应的发布商品的价格信息。
		sql.append("select  sg.* ");
		sql.append("  from t_item_group_link igl, v_search_good sg ");
		sql.append(" where igl.item_sale_id= ? ");
		sql.append("   and igl.item_org_id  = sg.G_ID");
		List<ItemInfo> groupItemList = dbHelper.getBeanList(sql.toString(),
				ItemInfo.class, saleId);
		List<ItemGroupInfo> ret = new ArrayList<ItemGroupInfo>();
		if (groupItemList != null && !groupItemList.isEmpty()) {
			for (ItemInfo info : groupItemList) {
				ItemGroupInfo ig = new ItemGroupInfo();
				// 获得原始商品关联的套餐商品的信息
				ig.setItems(getGroupItemSales(info.getgId()));
				ig.setGroupItem(info);
				ret.add(ig);
			}
		}
		return ret;
	}

	/**
	 * 获得原始商品关联套餐商品的详细信息
	 * 
	 * @param itemOrgId
	 * @return
	 * @throws SQLException
	 */
	public List<ItemInfo> getGroupItemSales(String itemSaleId)
			throws SQLException {
		StringBuilder sql = new StringBuilder();
		sql.append("select sg.* ");
		sql.append("  from v_search_good sg, t_item_group_link igl ");
		sql.append(" where igl.item_org_id = ? ");
		sql.append("   and sg.G_ID = igl.item_sale_id");
		return dbHelper.getBeanList(sql.toString(), ItemInfo.class, itemSaleId);

	}

	/**
	 * 商品会员价
	 * @param saleId
	 * @return
	 * @throws SQLException
	 */
	public List<ItemPriceInfo> getItemPrice(Long saleId)
			throws SQLException {
		String sql = " select price.sale_id, type.price_type_code , type.price_type ,price.price "
				+ "        from t_item_price price "
				+ "               left join t_item_price_type type on type.price_type_code=price.price_type_code "
				+ "     where price.sale_id=? ";
		List<String> params = new ArrayList<String>();
		params.add(saleId.toString());
		return dbHelper.getBeanList(sql, ItemPriceInfo.class, params.toArray());
	}

}
