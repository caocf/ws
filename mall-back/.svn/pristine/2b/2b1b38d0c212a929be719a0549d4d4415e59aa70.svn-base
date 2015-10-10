package com.cplatform.mall.back.giftcard.service;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Hashtable;
import java.util.List;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.commons.net.ftp.FTPReply;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.cplatform.dbhelp.DbHelper;
import com.cplatform.dbhelp.page.Page;
import com.cplatform.mall.back.giftcard.dao.GiftCardDao;
import com.cplatform.mall.back.giftcard.dao.GiftRequiredDao;
import com.cplatform.mall.back.giftcard.dao.GiftRequiredItemDao;
import com.cplatform.mall.back.giftcard.entity.GiftCard;
import com.cplatform.mall.back.giftcard.entity.GiftRequired;
import com.cplatform.mall.back.giftcard.entity.GiftRequiredItem;
import com.cplatform.mall.back.model.SessionUser;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.PathUtil;
import com.cplatform.mall.back.utils.PathUtil.PathInfo;
import com.cplatform.mall.back.utils.data.RoleDataUtils;
import com.cplatform.mall.back.websys.dao.SysFileImgDao;
import com.cplatform.mall.back.websys.entity.SysFileImg;
import com.cplatform.mall.back.websys.service.BsFileService;
import com.cplatform.util2.TimeStamp;
import com.google.zxing.BarcodeFormat;
import com.google.zxing.EncodeHintType;
import com.google.zxing.MultiFormatWriter;
import com.google.zxing.client.j2se.MatrixToImageWriter;
import com.google.zxing.common.BitMatrix;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;

/**
 * @Title
 * @Description
 * @Copyright: Copyright (c) 2013-9-22
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
@Service
public class GiftRequiredItemService {
	@Autowired
	private DbHelper dbHelper;
	@Autowired
	private GiftRequiredItemDao giftRequiredItemDao;
	@Autowired
	private GiftRequiredService giftRequiredService;
	@Autowired
	private PathUtil pathUtil;
	@Autowired
	private BsFileService bsFileService;
	@Autowired
	private SysFileImgDao sysFileImgDao;
	@Autowired
	private AppConfig congig;
	@Autowired
	private GiftCardDao giftCardDao;
	@Autowired
	private GiftRequiredDao giftRequiredDao;
	
	
	public void save(GiftRequiredItem giftRequiredItem){
		giftRequiredItemDao.save(giftRequiredItem);
	}
	
	public GiftRequiredItem findById(Long id){
		return giftRequiredItemDao.findOne(id);
	}
	public Page<GiftRequiredItem> findByBatchNo(GiftRequiredItem giftRequiredItem, int pageNo, int pageSize) throws SQLException{
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.setLength(0);
		sql.append(" select t.*,item.name itemName,store.name storeName from t_gift_required_item t ");
		sql.append(" join t_item_sale item on item.id=t.item_id");
		sql.append(" join t_store store on item.store_id=store.id");
		sql.append(" where 1 = 1 ");
		if(giftRequiredItem.getBatchNo()!=null){
			sql.append("and t.batch_no=?");
			params.add(giftRequiredItem.getBatchNo());
		}
		sql.append("order by t.id desc  ");
		Page<GiftRequiredItem> pageData = dbHelper.getPage(sql.toString(), GiftRequiredItem.class, pageNo, pageSize, params.toArray());
		return pageData;
	}
	
	public List<GiftRequiredItem> listByBatchNo(Long batchNo) throws SQLException{
		StringBuilder sql = new StringBuilder();
		sql.append(" select t.*,item.name itemName,store.name storeName from t_gift_required_item t ");
		sql.append(" join t_item_sale item on item.id=t.item_id");
		sql.append(" join t_store store on item.store_id=store.id");
		sql.append(" where t.batch_no=?  ");
		List<Object> params = new ArrayList<Object>();
		params.add(batchNo);
		sql.append("order by t.id asc  ");
		return dbHelper.getBeanList(sql.toString(), GiftRequiredItem.class, params.toArray());
	}
	
	@Transactional
	public void saveBindItems(Long[] storeId,Long[] itemId,Long batchNo,SessionUser sessionUser){
		for(int i=0;i<storeId.length;i++){
			GiftRequiredItem giftRequiredItem=new GiftRequiredItem();
			giftRequiredItem.setBatchNo(batchNo);
			giftRequiredItem.setStoreId(storeId[i]);
			giftRequiredItem.setItemId(itemId[i]);
			giftRequiredItem.setStatus(0);
			giftRequiredItem.setCreatedUserId(sessionUser.getId());
			giftRequiredItem.setCreatedTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
			giftRequiredItem.setDimcodeStatus(0L);
			this.save(giftRequiredItem);
		}
		GiftRequired giftRequired=giftRequiredService.findById(batchNo);
		giftRequired.setBindItemStatus(1L);
		giftRequiredService.save(giftRequired);
	}
	
	@Transactional
	public void updateBindItems(Long[] id,Long[] storeId,Long[] itemId,Long batchNo,SessionUser sessionUser){
		for(int i=0;i<storeId.length;i++){
			GiftRequiredItem giftRequiredItem=this.findById(id[i]);
			giftRequiredItem.setStoreId(storeId[i]);
			giftRequiredItem.setItemId(itemId[i]);
			giftRequiredItem.setStatus(0);
			this.save(giftRequiredItem);
		}
	}
	
	@Transactional
	public void deleteBinds(Long batchNo) throws SQLException{
		List<GiftRequiredItem> giftRequiredItems=this.listByBatchNo(batchNo);
		for(GiftRequiredItem item:giftRequiredItems){
			giftRequiredItemDao.delete(item);
		}
		GiftRequired giftRequired=giftRequiredService.findById(batchNo);
		giftRequired.setBindItemStatus(0L);
		giftRequiredService.save(giftRequired);
	}
	
	/**
	 * 送审
	 * @param batchNo
	 * @throws SQLException
	 */
	@Transactional
	public void firstAudit(Long batchNo) throws SQLException{
		List<GiftRequiredItem> giftRequiredItems=this.listByBatchNo(batchNo);
		for(GiftRequiredItem item:giftRequiredItems){
			item.setStatus(1);
			this.save(item);
		}
	}
	
	/**
	 * 审核
	 * @param sessionUser
	 * @param batchNo
	 * @param status
	 * @param auditMsg
	 * @throws SQLException
	 */
	@Transactional
	public void audit(SessionUser sessionUser,Long batchNo,Integer status,String auditMsg) throws SQLException{
		List<GiftRequiredItem> giftRequiredItems=this.listByBatchNo(batchNo);
		for(GiftRequiredItem item:giftRequiredItems){
			item.setAuditUserId(sessionUser.getId());
			item.setAuditTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
			item.setStatus(status);
			item.setAuditMsg(auditMsg);
			this.save(item);
		}
	}
	
	public Page<GiftRequired> getAuditBindItem(Page<GiftRequired> giftRequiredPage) throws SQLException{
		if(null!=giftRequiredPage && giftRequiredPage.getData().size()>0){
			for(int i=0;i<giftRequiredPage.getData().size();i++){
				GiftRequired giftRequired=giftRequiredPage.getData().get(i);
				List<GiftRequiredItem> giftRequiredItems=this.listByBatchNo(giftRequired.getBatchNo());
				if(null!=giftRequiredItems && giftRequiredItems.size()>0){
					giftRequired.setBindStatus(giftRequiredItems.get(0).getStatus());
				}
			}
		}
		return giftRequiredPage;
	}
	
	/**
	 * 查询绑定商品状态为待审核的卡需求列表
	 * @param giftRequired
	 * @param pageNo
	 * @param pageSize
	 * @return
	 * @throws SQLException
	 */
	public Page<GiftRequired> getAuditRequiredList(GiftRequired giftRequired, int pageNo, int pageSize) throws SQLException{
		StringBuilder sql = new StringBuilder();
		List<Object> params = new ArrayList<Object>();
		sql.setLength(0);
		sql.append(" select distinct giftRequired.*,reqitem.status bindStatus,reqitem.dimcode_status dimcodeStatus from t_gift_required giftRequired ");
		sql.append(" left join t_gift_required_item reqitem ");
		sql.append(" on giftRequired.batch_no =reqitem.batch_no  ");
		sql.append(" where 1 = 1 ");
		if(null!=giftRequired.getBindStatus()){
			sql.append(" and reqitem.status = ? ");
			params.add(giftRequired.getBindStatus());
		}
		if (null != giftRequired.getStatus()) {
			sql.append(" and giftRequired.status = ? ");
			params.add(giftRequired.getStatus());
		}else{
			sql.append(" and giftRequired.status <> -1 ");
		}
		if(null != giftRequired.getBatchNo()){
			sql.append(" and giftRequired.batch_no = ? ");
			params.add(giftRequired.getBatchNo());
		}
		if(null != giftRequired.getBindItemStatus()){
			sql.append(" and giftRequired.BIND_ITEM_STATUS = ? ");
			params.add(giftRequired.getBindItemStatus());
		}
		if (StringUtils.isNotEmpty(giftRequired.getModelNo())) {
			sql.append(" and giftRequired.model_no like ? ");
			params.add("%" + giftRequired.getModelNo().trim() + "%");
		}
		if (StringUtils.isNotEmpty(giftRequired.getRequiredUser())) {
			sql.append(" and giftRequired.required_user like ? ");
			params.add("%" + giftRequired.getRequiredUser().trim() + "%");
		}
		if(null != giftRequired.getCardNum()){
			sql.append(" and giftRequired.card_num = ? ");
			params.add(giftRequired.getCardNum());
		}
		if (StringUtils.isNotEmpty(giftRequired.getBeginTime())) {
			sql.append(" and giftRequired.issuing_time > ?  ");
			params.add(giftRequired.getBeginTime()+"000000");
		}
		if (StringUtils.isNotEmpty(giftRequired.getEndTime())) {
			sql.append(" and giftRequired.issuing_time < ?  ");
			params.add(giftRequired.getEndTime()+"235959");
		}
		if (null != giftRequired.getMakeCardStatus()) {
			sql.append(" and giftRequired.make_card_status = ? ");
			params.add(giftRequired.getMakeCardStatus());
		}
		if (null != giftRequired.getPrintFaceStatus()) {
			sql.append(" and giftRequired.print_face_status = ? ");
			params.add(giftRequired.getPrintFaceStatus());
		}
		if (null != giftRequired.getDimcodeStatus()) {
			sql.append(" and reqitem.dimcode_status = ? ");
			params.add(giftRequired.getDimcodeStatus());
		}
		sql.append(RoleDataUtils.getRoleData(RoleDataUtils.MODUE_GIFT_REQUIRED));// 控制数据访问
		sql.append(" order by giftRequired.batch_no desc");
		Page<GiftRequired> pageData = dbHelper.getPage(sql.toString(), GiftRequired.class, pageNo, pageSize, params.toArray());
		return pageData;
	}
	
	
	/**
	 * 判断数组是否有重复元素，false表示无，true表示有
	 * @param objects
	 * @return
	 */
	public boolean hasRepeatElement(Object[] objects){
		boolean flag=false;
		List<Object> objlist=Arrays.asList(objects);
		ArrayList<Object> alist=new ArrayList<Object>(objlist);
		Set<Object> objSet=new HashSet<Object>(objlist);
		if(alist.size()!=objSet.size()){
			flag=true;
		}
		return flag;
	}
	
	/**
	 * 生成二维码
	 * @throws Exception 
	 * */
	@Transactional
	public void makeQrCode(Long id,HttpServletRequest request ) throws Exception{
		List<GiftRequiredItem> giftRequiredItems = findByBatchNo(id);
		for(int i=0;i<giftRequiredItems.size();i++){
			GiftRequiredItem giftRequiredItem = giftRequiredItems.get(i);
			String dimcodeWapUrl =congig.getQrcodeLink()+giftRequiredItem.getItemId();
			giftRequiredItem.setDimcodeWapUrl(dimcodeWapUrl);
			PathInfo pathInfo = pathUtil.getPathById(BsFileService.GIFT_CARD_QRCODE_KEY, id,"png");
			pathInfo.setSavepath(pathInfo.getSavepath()+giftRequiredItem.getId()+"/");
			pathInfo.setWebPath(pathInfo.getWebPath()+giftRequiredItem.getId()+"/");
			File file = new File(pathInfo.getSavepath());
			file.mkdirs();
			String realPath = pathInfo.getRealPath();
			SysFileImg sysFileImg = bsFileService.saveBsImgFile(realPath, pathInfo.getRealWebPath(""), BsFileService.GIFT_CARD_QRCODE_KEY, giftRequiredItem.getId());
			giftRequiredItem.setDimcodeImg(sysFileImg.getFileName());
			encode(dimcodeWapUrl,BsFileService.GIFT_CARD_QRCODE_SIZE,BsFileService.GIFT_CARD_QRCODE_SIZE,pathInfo.getRealPath());
			giftRequiredItem.setDimcodeStatus(1L);
			giftRequiredItem.setDimcdeTime(TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss));
			save(giftRequiredItem);
		}
	}
	/** 
     * 编码 
     * @param contents 编码内容
     * @param width 生成文件宽度
     * @param height 生成文件高度
     * @param imgPath 生成图片路径
	 * @throws Exception 
     */
    private void encode(String contents, int width, int height, String imgPath) throws Exception {
        Hashtable<Object, Object> hints = new Hashtable<Object, Object>();
        // 指定纠错等级  
        hints.put(EncodeHintType.ERROR_CORRECTION, ErrorCorrectionLevel.L);
        // 指定编码格式  
        hints.put(EncodeHintType.CHARACTER_SET, "GBK");
        BitMatrix bitMatrix = new MultiFormatWriter().encode(contents, BarcodeFormat.QR_CODE, width, height, hints);
        MatrixToImageWriter.writeToFile(bitMatrix, "png", new File(imgPath));
    }
    public List<GiftRequiredItem> findByBatchNo(Long batchNo){
    	return giftRequiredItemDao.findByBatchNo(batchNo);
    }
    public SysFileImg getQrCode(Long bsId,String bsKey){
    	return sysFileImgDao.findByBsIdAndBsKey(bsId, bsKey);
    }
    public boolean backUpload( String url,int port,String username,String password,String remotePath,String localPath,Long batchNo){
    	GiftRequired giftRequired = giftRequiredDao.findOne(batchNo);
    	List<GiftCard> gfs = giftCardDao.findGiftCardsByBatchNo(batchNo);
    	if(gfs.size() == 0 ){
    		return false;
    	}
    	String fileName = "gift_card_bind_"+giftRequired.getCutoffDay()+"_"+batchNo+".txt.tmp";
//    	String fileName = "gift_card_bind_" + gfs.get(0).getPaymentDay()+"_"+TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmss)+".txt.tmp";
    	GiftRequired gf = giftRequiredDao.findOne(batchNo);
    	File file = new File(localPath,fileName);
    	BufferedWriter bw = null;
    	try {
			file.createNewFile();
			bw =  new BufferedWriter(new FileWriter(file));
			for(int i=0;i<gfs.size();i++){
				String str =  gfs.get(i).getPaymentDay() + "|" +"01"+"|"+gfs.get(i).getSerialNo()+"|"+gf.getEffortDate()+"|"+gf.getExpiryDate()+"\r";
				bw.write(str);
				bw.flush();
			}
		} catch (IOException e) {
			e.printStackTrace();
			return false;
		}finally{
			if(null != bw){
				try {
					bw.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
    	boolean success = false;    
        FTPClient ftp = new FTPClient();
        FileInputStream in = null;
        try {    
            int reply;    
            ftp.connect(url, port);    
            ftp.login(username, password);//登录  
            reply = ftp.getReplyCode();    
            if (!FTPReply.isPositiveCompletion(reply)) {    
                ftp.disconnect();    
                return success;    
            }    
            ftp.changeWorkingDirectory(remotePath);//转移到FTP服务器目录
            ftp.enterLocalPassiveMode();
            in = new FileInputStream(file);
            ftp.storeFile(fileName, in);
            ftp.rename(fileName, fileName.substring(0, fileName.lastIndexOf(".")));
            ftp.logout();    
            success = true;    
        } catch (IOException e) {    
            e.printStackTrace();
            return false;
        } finally {    
            if (ftp.isConnected()) {    
                try {    
                    ftp.disconnect();    
                } catch (IOException e) {  
                	e.printStackTrace();
                }    
            } 
            if(null != in){
            	try {
					in.close();
				} catch (IOException e) {
					e.printStackTrace();
				}
            }
            if(file.exists()){
            	file.delete();
            }
        }    
        return success;    
    	
    }

}
