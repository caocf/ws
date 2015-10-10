package com.cplatform.b2c.repository;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import com.cplatform.b2c.dto.ItemSaleDataDTO;
import com.cplatform.b2c.util.PathUtil;
import com.cplatform.dbhelp.DbHelper;

/**
 * 商品信息查询. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-3-18 下午5:32:34
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Repository
public class ItemSaleDao {

	@Autowired
	private DbHelper dbHelper;

	@Autowired
	private ThirdInterDao thirdInterDao;

	@Autowired
	private PathUtil pathUtil;

	/**
	 * 根据现有的商品编号查询出与其关联的商品信息
	 * 
	 * @param id
	 * @return
	 * @throws SQLException
	 */
	public Map<String, String> findRelationItemSaleById(Long id) throws SQLException {
		StringBuilder sqlBuilder = new StringBuilder();
		sqlBuilder.setLength(0);
		sqlBuilder.append("SELECT oldSale.id g_id,");
		sqlBuilder.append("       oldSale.cash_idgoods g_payment_cash,");
		sqlBuilder.append("       oldSale.coin_idgoods g_payment_coin,");
		sqlBuilder.append("       oldSale.score_idgoods g_payment_score,");
		sqlBuilder.append("       oldSale.is_valid g_is_valid,");
		sqlBuilder.append("       oldSale.status,");
		sqlBuilder.append("       oldSale.iseckill g_iseckill,");
		sqlBuilder.append("       oldSale.iseckill_price g_iseckill_price,");
		sqlBuilder.append("       oldSale.org_id g_org_id,");
		sqlBuilder.append("       oldSale.name g_name,");
		sqlBuilder.append("       oldSale.short_name g_short_name,");
		sqlBuilder.append("       oldSale.type_id g_type_id,");
		sqlBuilder.append("       oldSale.brand g_brand,");
		sqlBuilder.append("       oldSale.create_time g_create_time,");
		sqlBuilder.append("       oldSale.update_time g_update_time,");
		sqlBuilder.append("       oldSale.market_content g_market_content,");
		sqlBuilder.append("       oldSale.img_path g_web_path,");
		sqlBuilder.append("       oldSale.source g_source,");
		sqlBuilder.append("       to_char(nvl(oldSale.market_price, 0), 'fm999999990.00') g_market_price,");
		sqlBuilder.append("       to_char(nvl(oldSale.shop_price, 0), 'fm999999990.00') g_shop_price,");
		sqlBuilder.append("       oldSale.remark g_remark,");
		sqlBuilder.append("       oldSale.post_flag g_post_flag,");
		sqlBuilder.append("       oldSale.item_mode g_item_mode,");
		sqlBuilder.append("       oldSale.sale_start_time g_start_time,");
		sqlBuilder.append("       oldSale.sale_stop_time g_stop_time,");
		sqlBuilder.append("       oldSale.is_view g_is_view,");
		sqlBuilder.append("       oldSale.warm_prompt g_warm_prompt,");
		sqlBuilder.append("       oldSale.group_flag g_group_flag,");
		sqlBuilder.append("       oldSale.store_id g_store_id,");
		sqlBuilder.append("       to_char(nvl(oldSale.logistics_fee, 0), 'fm999999990.00') g_logistics_fee,");
		sqlBuilder.append("       nvl(oldSale.logistics_fee_type, 0) g_logistics_fee_type,");
		sqlBuilder.append("       nvl(oldSale.stock_num, 0) g_stock_num,");
		sqlBuilder.append("       n.userperbuyn");
		sqlBuilder.append("  FROM T_ITEM_SALE oldSale,");
		sqlBuilder.append("       (SELECT goods.orginal_item_id orginalItemId,");
		sqlBuilder.append("               sale.user_per_buy_num userPerBuyN");
		sqlBuilder.append("          FROM T_ITEM_SALE sale, T_MARKET_PROM_GOODS goods");
		sqlBuilder.append("         WHERE sale.id = goods.item_id");
		sqlBuilder.append("           AND sale.id = ?) n");
		sqlBuilder.append(" WHERE oldSale.Id = n.orginalItemId");
		return dbHelper.getMap(sqlBuilder.toString(), id);
	}

	/**
	 * 查询当前商品支持的会员购买
	 * 
	 * @param itemId
	 * @return
	 * @throws SQLException
	 */
	public List<String[]> findPriceTypeCodeByItemId(String itemId) throws SQLException {
		String sql = "SELECT PRICE_TYPE_CODE FROM T_ITEM_PRICE p WHERE p.ITEM_ID = ? ";
		return dbHelper.getArrayList(sql, itemId);
	}

	/**
	 * 获取商品浏览记录
	 * 
	 * @param itemIds
	 * @return
	 */
	public List<ItemSaleDataDTO> findHistoryItems(String[] itemIds) {
		List<ItemSaleDataDTO> list = null;
		if (null != itemIds && itemIds.length > 0) {
			list = new ArrayList<ItemSaleDataDTO>();
			for (String itemId : itemIds) {
				ItemSaleDataDTO itemSaleDataDTO = thirdInterDao.getItemById(itemId);
				if (null != itemSaleDataDTO && null != itemSaleDataDTO.getItem()) {
					itemSaleDataDTO.getItem().setItemPath(pathUtil.getPathById(PathUtil.TYPE_ITEM, itemSaleDataDTO.getItem().getId()));
					itemSaleDataDTO.getItem().setImgPath(pathUtil.getPathById(PathUtil.TYPE_ITEM_PIC, itemSaleDataDTO.getItem().getId()));
					list.add(itemSaleDataDTO);
				}
			}
		}
		return list;
	}
}
