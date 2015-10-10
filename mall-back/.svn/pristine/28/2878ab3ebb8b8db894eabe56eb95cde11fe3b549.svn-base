package com.cplatform.mall.back.giftcard.service;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.cont.mms.util.TimeUtil;
import com.cplatform.mall.back.giftcard.dao.GiftCardDao;
import com.cplatform.mall.back.giftcard.dao.GiftCardStorageDao;
import com.cplatform.mall.back.giftcard.dao.GiftCardStorageExtDao;
import com.cplatform.mall.back.giftcard.dao.GiftRequiredDao;
import com.cplatform.mall.back.giftcard.entity.GiftCard;
import com.cplatform.mall.back.giftcard.entity.GiftCardStorage;
import com.cplatform.mall.back.giftcard.entity.GiftCardStorageExt;
import com.cplatform.mall.back.giftcard.entity.GiftRequired;
import com.cplatform.mall.back.giftcard.entity.GiftRequiredItem;
import com.cplatform.mall.back.item.dao.ItemSaleDao;
import com.cplatform.mall.back.item.entity.ItemSale;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.utils.ExcelExportUtil;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
/**
 * 
 * @author mudeng
 * 礼品卡库存管理Service
 *
 */
@Service
public class GiftStockService {

	@Autowired
	DbHelper dbHelper;

	
	@Autowired
	GiftRequiredDao giftRequiredDao;
	
	@Autowired
	GiftCardStorageDao giftCardStorageDao;
	
	@Autowired
	GiftCardStorageExtDao giftCardStorageExtDao;
	
	@Autowired
	GiftCardDao giftCardDao;
	
	@Autowired
	private ItemSaleDao itemSaleDao;
	
	public Page<GiftRequired> findGiftRequired(GiftRequired giftRequired, int pageNo, int pageSize) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select giftRequired.*,s.stocks ,s.remark sremark,s.STOCKS_IN stocksIn,s.STOCKS_OUT stocksOut from t_gift_required giftRequired left join t_gift_card_storage s on giftRequired.batch_no = s.batch_no");
		sql.append("  where 1=1 and giftRequired.status = 2 and giftRequired.print_face_status = 2 and giftRequired.bind_item_status = 1 and giftRequired.make_card_status = 1");
		List<Object> paramsList = new ArrayList<Object>();
		if (giftRequired != null) {
			if (giftRequired.getBatchNo() != null) {
				sql.append(" and giftRequired.batch_no  = ? ");
				paramsList.add(giftRequired.getBatchNo());
			}

		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODUE_GIFT_REQUIRED));

		return dbHelper.getPage(sql.toString(), GiftRequired.class, pageNo, pageSize, paramsList.toArray());
	}
	public Page<GiftRequired> selectBatchNolist(GiftRequired giftRequired, int pageNo, int pageSize) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select giftRequired.*,s.stocks ,s.remark sremark,s.STOCKS_IN stocksIn,s.STOCKS_OUT stocksOut from t_gift_required giftRequired left join t_gift_card_storage s on giftRequired.batch_no = s.batch_no");
		sql.append("  where 1=1 and giftRequired.status = 2 and giftRequired.print_face_status = 2 and giftRequired.bind_item_status = 1 and giftRequired.make_card_status = 1 and s.stocks > 0");
		List<Object> paramsList = new ArrayList<Object>();
		if (giftRequired != null) {
			if (giftRequired.getBatchNo() != null) {
				sql.append(" and giftRequired.batch_no  = ? ");
				paramsList.add(giftRequired.getBatchNo());
			}
			
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODUE_GIFT_REQUIRED));
		
		return dbHelper.getPage(sql.toString(), GiftRequired.class, pageNo, pageSize, paramsList.toArray());
	}
	public Page<GiftRequired> linkList(GiftRequired giftRequired, int pageNo, int pageSize) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select giftRequired.* from t_gift_required giftRequired ");
		sql.append("  where 1=1 and giftRequired.status = 2 and giftRequired.print_face_status = 2 and giftRequired.bind_item_status = 1 and giftRequired.make_card_status = 1 ");
//		and giftRequired.SALE_ID is null
		List<Object> paramsList = new ArrayList<Object>();
		if (giftRequired != null) {
			if (giftRequired.getBatchNo() != null) {
				sql.append(" and giftRequired.batch_no  = ? ");
				paramsList.add(giftRequired.getBatchNo());
			}
			
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODUE_GIFT_REQUIRED));
		
		return dbHelper.getPage(sql.toString(), GiftRequired.class, pageNo, pageSize, paramsList.toArray());
	}
	public List<GiftRequired> giftRequiredList(GiftRequired giftRequired) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select giftRequired.* from t_gift_required giftRequired ");
		sql.append("  where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (giftRequired != null) {
			if (giftRequired.getBatchNo() != null) {
				sql.append(" and giftRequired.batch_no  = ? ");
				paramsList.add(giftRequired.getBatchNo());
			}
			if (giftRequired.getSaleId() != null) {
				sql.append(" and giftRequired.sale_id  = ? ");
				paramsList.add(giftRequired.getSaleId());
			}
		}
		
		return dbHelper.getBeanList(sql.toString(), GiftRequired.class, paramsList.toArray());
	}
	
	public Page<GiftCard> findGiftCardPage(GiftCard giftCard, int pageNo, int pageSize,String flag) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select * from t_gift_card t");
		sql.append("  where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (giftCard != null) {
			if (giftCard.getBatchNo() != null && !"".equals(giftCard.getBatchNo())) {
				sql.append(" and t.batch_no  = ? ");
				paramsList.add(giftCard.getBatchNo());
			}
			if (giftCard.getSerialNo() != null && !"".equals(giftCard.getSerialNo())) {
				sql.append(" and t.serial_no  = ? ");
				paramsList.add(giftCard.getSerialNo().trim());
			}
			if (flag != null && !"".equals(flag)) {
				if("1".equals(flag)){
					sql.append(" and t.storage_status  != ? ");
					paramsList.add(1);
				}else{
					sql.append(" and t.storage_status  = ? ");
					paramsList.add(1);
				}
				
			}

		}

		return dbHelper.getPage(sql.toString(), GiftCard.class, pageNo, pageSize, paramsList.toArray());
	}
	
	public Page<GiftCardStorageExt> findGiftExtPage(GiftCardStorageExt giftCardStorageExt, int pageNo, int pageSize) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append("select t.*,u.user_name username from T_GIFT_CARD_STORAGE_EXT t,T_SYS_USER u where t.OPERATED_USER_ID = u.id ");
		List<Object> paramsList = new ArrayList<Object>();
		if (giftCardStorageExt != null) {
			if (giftCardStorageExt.getBatchNo() != null && !"".equals(giftCardStorageExt.getBatchNo())) {
				sql.append(" and t.batch_no  = ? ");
				paramsList.add(giftCardStorageExt.getBatchNo());
			}
			if (giftCardStorageExt.getApplyId() != null && !"".equals(giftCardStorageExt.getApplyId())) {
				sql.append(" and t.apply_id  = ? ");
				paramsList.add(giftCardStorageExt.getApplyId());
			}
			if (giftCardStorageExt.getSerialNo() != null && !"".equals(giftCardStorageExt.getSerialNo())) {
				sql.append(" and t.serial_no  = ? ");
				paramsList.add(giftCardStorageExt.getSerialNo().trim());
			}
			if (giftCardStorageExt.getStorageStatus() != null && !"".equals(giftCardStorageExt.getStorageStatus())) {
				sql.append(" and t.STORAGE_STATUS  = ? ");
				paramsList.add(giftCardStorageExt.getStorageStatus());
			}

		}
		sql.append("order by operated_time desc");

		return dbHelper.getPage(sql.toString(), GiftCardStorageExt.class, pageNo, pageSize, paramsList.toArray());
	}
	
	@Transactional
	public String linkItem(Long batchNo,Long itemId) throws Exception{
		//ItemSale itemSale = itemSaleDao.findOne(itemId);
		//GiftCardStorage giftCardStorage = giftCardStorageDao.findByBatchNo(batchNo);
		//itemSale.setStockNum(giftCardStorage.getStocks());
		//itemSaleDao.save(itemSale);
		GiftRequired giftRequired = giftRequiredDao.findOne(batchNo);
		giftRequired.setSaleId(itemId);
		giftRequiredDao.save(giftRequired);
		return "";
	}
	
	@Transactional
	public String stockIn(Long batchNo,HttpServletRequest request) throws Exception{
		String msg = "";
		int scount = 0;
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
//		GiftRequired giftRequired =  giftRequiredDao.findOne(batchNo);
		List<GiftCard> listGiftCard = findGiftCardList(batchNo);
		if(listGiftCard.size()==0){
			return "没有可入库礼品卡";
		}
		for(GiftCard giftCard:listGiftCard){
			String temp = checkCard(giftCard,"1");
			if("0".equals(temp)){
				GiftCardStorageExt giftCardStorageExt = new GiftCardStorageExt();
				setData(giftCard,giftCardStorageExt,sessionUser,"1");
				giftCardStorageExtDao.save(giftCardStorageExt);
				giftCardDao.save(giftCard);
				scount ++;
			}else{
				msg += temp;
			}
		}
		GiftCardStorage giftCardStorage = getGiftCardStorage(batchNo,scount,"1");
		giftCardStorageDao.save(giftCardStorage);
		return "操作成功,本次入库成功"+scount+"个"+msg;
	}
	
	@Transactional
	public String cardStockOut(String[] serialNos,HttpServletRequest request,String flag) throws Exception{
		String msg = "";
		int scount = 0;
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		for(String serialNo:serialNos){
			if(StringUtils.isEmpty(serialNo)){
				continue;
			}
			GiftCard giftCard = findGiftCard(serialNo);
			if(giftCard==null){
				msg +=","+serialNo+"出库失败,原因:无此礼品卡";
				continue;
			}
			String temp = checkCard(giftCard,"0");
			if("0".equals(temp)){
				GiftCardStorageExt giftCardStorageExt = new GiftCardStorageExt();
				setData(giftCard,giftCardStorageExt,sessionUser,"0");
				giftCardStorageExtDao.save(giftCardStorageExt);
				giftCardDao.save(giftCard);
				scount ++;
				GiftCardStorage giftCardStorage = getGiftCardStorage(giftCard.getBatchNo(),1,"0");
				giftCardStorageDao.save(giftCardStorage);
			}else{
				msg += temp;
			}
		}
		return "操作成功,本次出库成功"+scount+"个"+msg;
	}
	
	@Transactional
	public String cardStockOutBySum(HttpServletRequest request,int cardSum,String batchNo) throws Exception{
		String msg = "";
		List<GiftCard> listGiftCard = findGiftCardList(Long.valueOf(batchNo));
		if(listGiftCard.size()<=0){
			return "没有可出库礼品卡";
		}
		if(cardSum>listGiftCard.size()){
			cardSum = listGiftCard.size();
		}
		int scount = 0;
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		for(int i=0;i<cardSum;i++){
			GiftCard giftCard = listGiftCard.get(i);
			String temp = checkCard(giftCard,"0");
			if("0".equals(temp)){
				GiftCardStorageExt giftCardStorageExt = new GiftCardStorageExt();
				setData(giftCard,giftCardStorageExt,sessionUser,"0");
				giftCardStorageExtDao.save(giftCardStorageExt);
				giftCardDao.save(giftCard);
				scount ++;
			}else{
				msg += temp;
			}
		}
		GiftCardStorage giftCardStorage = getGiftCardStorage(Long.valueOf(batchNo),scount,"0");
		giftCardStorageDao.save(giftCardStorage);
		return "操作成功,本次出库成功"+scount+"个"+msg;
	}
	public GiftCardStorage getGiftCardStorage(Long batchNo,int scount,String inOut){
		GiftCardStorage giftCardStorage = giftCardStorageDao.findByBatchNo(batchNo);
		if(giftCardStorage==null){
			giftCardStorage = new GiftCardStorage();
			giftCardStorage.setStocks(0L);
			giftCardStorage.setStocksIn(0L);
			giftCardStorage.setStocksOut(0L);
		}
		if("0".equals(inOut)){
			giftCardStorage.setBatchNo(batchNo);
			giftCardStorage.setRemark("出库");
			giftCardStorage.setStocks(giftCardStorage.getStocks()-Long.valueOf(scount));
			giftCardStorage.setStocksIn(giftCardStorage.getStocksIn());
			giftCardStorage.setStocksOut(giftCardStorage.getStocksOut()+Long.valueOf(scount));
		}else{
			giftCardStorage.setBatchNo(batchNo);
			giftCardStorage.setRemark("入库");
			giftCardStorage.setStocks(giftCardStorage.getStocks()+Long.valueOf(scount));
			giftCardStorage.setStocksIn(giftCardStorage.getStocksIn()+Long.valueOf(scount));
			giftCardStorage.setStocksOut(giftCardStorage.getStocksOut());
		}
		return giftCardStorage;
	}
	public String checkCard(GiftCard giftCard,String inOut) throws SQLException{
		//返回"0"表示验证通过
		//0出1入
		GiftRequired giftRequired =  giftRequiredDao.findOne(giftCard.getBatchNo());
		if(giftRequired==null){
			return ","+giftCard.getSerialNo()+"失败,原因：卡需求未发现";
		}
		if(giftRequired.getPrintFaceStatus()!=2L){
			return ","+giftCard.getSerialNo()+"失败,原因：卡需求未印刷完成";
		}
		if(giftRequired.getBindItemStatus()!=1L){
			return ","+giftCard.getSerialNo()+"失败,原因：卡需求未绑定商品";
		}
		if(findNoBindRequired(giftCard.getBatchNo()).size()>0){
			return ","+giftCard.getSerialNo()+"失败,原因：卡需求绑定商品未审核通过";
		}
		if(findNoDimCodeRequired(giftCard.getBatchNo()).size()>0){
			return ","+giftCard.getSerialNo()+"失败,原因：卡需求绑定商品未生成二维码";
		}
		if("0".equals(inOut)){
			if(giftCard.getStorageStatus() == 0L){
				return ","+giftCard.getSerialNo()+"失败,原因：未入库";
			}
			if(giftCard.getStorageStatus() == 2L){
				return ","+giftCard.getSerialNo()+"失败,原因：已出库";
			}
		}else{
			if(giftCard.getStorageStatus() == 1L){
				return ","+giftCard.getSerialNo()+"失败,原因：已入库";
			}
			if(giftCard.getStorageStatus() == 2L){
				return ","+giftCard.getSerialNo()+"失败,原因：已出库";
			}
		}
		return "0";
	}
	
	public List<GiftRequiredItem> findNoBindRequired(Long batchNo) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from t_gift_required_item i where i.batch_no = ? and status <> 2 ");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(batchNo);
		return dbHelper.getBeanList(sql.toString(), GiftRequiredItem.class, paramsList.toArray());
	}
	
	public List<GiftRequiredItem> findNoDimCodeRequired(Long batchNo) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from t_gift_required_item i where i.batch_no = ? and dimcode_status <> 1 ");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(batchNo);
		return dbHelper.getBeanList(sql.toString(), GiftRequiredItem.class, paramsList.toArray());
	}
	public void setData(GiftCard giftCard,GiftCardStorageExt giftCardStorageExt,SessionUser sessionUser,String inOut){
		giftCardStorageExt.setBatchNo(giftCard.getBatchNo());
		giftCardStorageExt.setSerialNo(giftCard.getSerialNo());
		giftCardStorageExt.setOperatedTime(TimeUtil.now());
		giftCardStorageExt.setOperatedUserId(sessionUser.getId());
		if("0".equals(inOut)){
			giftCardStorageExt.setStorageStatus(0L);
			giftCard.setReceiveTime(TimeUtil.now());
			giftCard.setStorageStatus(2L);
		}else{
			giftCardStorageExt.setStorageStatus(1L);
			giftCard.setReceiveTime("");
			giftCard.setStorageStatus(1L);
		}
	}
	
	
	@Transactional
	public String cardStockIn(String[] serialNos,HttpServletRequest request,String flag) throws Exception{
		String msg = "";
		int scount = 0;
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
		for(String serialNo:serialNos){
			if(StringUtils.isEmpty(serialNo)){
				continue;
			}
			GiftCard giftCard = findGiftCard(serialNo);
			if(giftCard==null){
				msg +=","+serialNo+"入库失败,原因:无此礼品卡";
				continue;
			}
			String temp = checkCard(giftCard,"1");
			if("0".equals(temp)){
				GiftCardStorageExt giftCardStorageExt = new GiftCardStorageExt();
				setData(giftCard,giftCardStorageExt,sessionUser,"1");
				giftCardStorageExtDao.save(giftCardStorageExt);
				giftCardDao.save(giftCard);
				scount ++;
				GiftCardStorage giftCardStorage = getGiftCardStorage(giftCard.getBatchNo(),1,"1");
				giftCardStorageDao.save(giftCardStorage);
			}else{
				msg += temp;
			}
		}
		return "操作成功,本次入库成功"+scount+"个"+msg;
	}
	
	@Transactional
	public String stockOut(Long batchNo,HttpServletRequest request) throws Exception{
		String msg = "";
		int scount = 0;
		SessionUser sessionUser = (SessionUser) request.getSession().getAttribute(SessionUser.SESSION_USER_KEY);
//		GiftRequired giftRequired =  giftRequiredDao.findOne(batchNo);
		List<GiftCard> listGiftCard = findGiftCardList(batchNo);
		if(listGiftCard.size()==0){
			return "没有可出库礼品卡";
		}
		for(GiftCard giftCard:listGiftCard){
			String temp = checkCard(giftCard,"0");
			if("0".equals(temp)){
				GiftCardStorageExt giftCardStorageExt = new GiftCardStorageExt();
				setData(giftCard,giftCardStorageExt,sessionUser,"0");
				giftCardStorageExtDao.save(giftCardStorageExt);
				giftCardDao.save(giftCard);
				scount ++;
			}else{
				msg += temp;
			}
		}
		GiftCardStorage giftCardStorage = getGiftCardStorage(batchNo,scount,"0");
		giftCardStorageDao.save(giftCardStorage);
		return "操作成功,本次出库成功"+scount+"个"+msg;
	}
	public List<GiftCard> findGiftCardList(Long batchNo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from t_gift_card c ");
		sql.append("  where 1=1 and BATCH_NO = ? ");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(batchNo);
//		if (giftCard != null) {
//			if (giftRequired.getBatchNo() != null) {
//				sql.append(" and r.batch_no  = ? ");
//				paramsList.add(giftRequired.getBatchNo());
//			}

//		}

		return dbHelper.getBeanList(sql.toString(), GiftCard.class, paramsList.toArray());
	}
	public GiftCard findGiftCard(String serialNo) throws Exception {
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from t_gift_card c ");
		sql.append("  where 1=1 ");
		List<Object> paramsList = new ArrayList<Object>();
		if (serialNo != null && serialNo!="") {
				sql.append(" and c.SERIAL_NO  = ? ");
				paramsList.add(serialNo.trim());
		}
		
		return dbHelper.getBean(sql.toString(), GiftCard.class, paramsList.toArray());
	}
	
	public boolean checkStorageStatus(String serialNo, String storageStatus) throws Exception{
		StringBuilder sql = new StringBuilder();
		sql.append(" select * from (select * from t_gift_card_storage_ext t where t.serial_no = ?  order by t.operated_time desc ) where rownum = 1 ");
		List<Object> paramsList = new ArrayList<Object>();
		paramsList.add(serialNo);

		GiftCardStorageExt giftCardStorageExt = dbHelper.getBean(sql.toString(), GiftCardStorageExt.class, paramsList.toArray());
		if(giftCardStorageExt==null){
			return true;
		}else{
			if(storageStatus.equals(giftCardStorageExt.getStorageStatus().toString())){
				return false;
			}else{
				return true;
			}
		}
	}
	
	public void exportExcel(HttpServletRequest request,HttpServletResponse response) throws Exception{
		StringBuilder sqlBuff = new StringBuilder();
		sqlBuff.setLength(0);
		sqlBuff.append("select t.batch_no,t.serial_no,decode(t.storage_status,0,'出库',1,'入库') status  from t_gift_card_storage_ext t order by t.operated_time desc  ");
		String[] head = {"批次号","序列号","出入库状态"};
		ExcelExportUtil.exportExcel(dbHelper.getConn(),head,"STORAGE_EXT_", sqlBuff.toString(),request,response);
	}

	public void editorItemSaleStocks(GiftCardStorage giftCardStorage){
		GiftRequired giftRequired = giftRequiredDao.findOne(giftCardStorage.getBatchNo());
		if(giftRequired.getSaleId()!=null){
			ItemSale itemSale = itemSaleDao.findOne(giftRequired.getSaleId());
			itemSale.setStockNum(giftCardStorage.getStocks());
			itemSaleDao.save(itemSale);
		}
	}
}
