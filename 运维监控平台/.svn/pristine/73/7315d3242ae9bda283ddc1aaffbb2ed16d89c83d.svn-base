package com.cplatform.mall.dc.utils;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
public class AppConfig {

	/** token cookie 的名称 */
	public static final String COOKIE_TOKEN = "SHOW";

	/** 门店类 */
	public static final int SHOP_CLASS_SHOP = 1;

	/** 商户类 */
	public static final int SHOP_CLASS_STORE = 2;

	/** 渠道商类 */
	public static final int SHOP_CLASS_AGENT = 3;

	/** 帐号类型 管理员 */
	public static final int SHOP_USER_ADMIN = 1;

	/** 帐号类型 普通用户 */
	public static final int SHOP_USER_ORDINARY = 2;

	/** 帐号类型 操作员 */
	public static final int SHOP_USER_OPERATOR = 3;

	@Value("${cookies.key}")
	private String cookiesKey;

	@Value("${cookies.maxAge}")
	private int cookiesMaxAge;

	@Value("${site.url}")
	private String siteUrl;

	@Value("${site.name}")
	private String siteName;

	@Value("${upload.img.dir}")
	private String uploadImgDir;

    @Value("${upload.img.path}")
    private String uploadImgPath;

	@Value("${upload.pic.ext}")
	private String uploadPicExt;

	@Value("${upload.apppic.maxsize}")
	private String uploadApppicMaxsize;

	@Value("${xheditor.upload.ext}")
	private String[] xheditorExts;

	@Value("${xheditor.upload.ext}")
	private String xheditorExt;

	@Value("${xheditor.upload.img.ext}")
	private String[] xheditorImageExts;

	@Value("${xheditor.upload.img.ext}")
	private String xheditorImageExt;

	@Value("${xheditor.upload.maxsize}")
	private long xheditorMaxSize;

	@Value("${xheditor.upload.dir}")
	private String xheditorUploadDir;

	@Value("${xheditor.upload.path}")
	private String xheditorUploadPath;

	@Value("${xheditor.domain}")
	private String xheditorDomain;

	/**
	 * 页面静态化调用接口url
	 */
	@Value("${PAGE_STATIC_URL}")
	private String pageStaticUrl;

	@Value("${ordergoods.img.server}")
	private String ordergoodsImgServer;

    @Value("${ordergoods.host.server}")
    private String ordergoodsHostServer;
    
    public String getOrdergoodsHostServer() {
        return ordergoodsHostServer;
    }

	public String getOrdergoodsImgServer() {
		return ordergoodsImgServer;
	}

	public void setOrdergoodsImgServer(String ordergoodsImgServer) {
		this.ordergoodsImgServer = ordergoodsImgServer;
	}

	public String getXheditorDomain() {
		return xheditorDomain;
	}

	public void setXheditorDomain(String xheditorDomain) {
		this.xheditorDomain = xheditorDomain;
	}

	public String[] getXheditorExts() {
		return xheditorExts.clone();
	}

	public void setXheditorExts(String[] xheditorExts) {
		this.xheditorExts = xheditorExts.clone();
	}

	public String getXheditorExt() {
		return xheditorExt;
	}

	public void setXheditorExt(String xheditorExt) {
		this.xheditorExt = xheditorExt;
	}

	public String[] getXheditorImageExts() {
		return xheditorImageExts.clone();
	}

	public void setXheditorImageExts(String[] xheditorImageExts) {
		this.xheditorImageExts = xheditorImageExts.clone();
	}

	public String getXheditorImageExt() {
		return xheditorImageExt;
	}

	public void setXheditorImageExt(String xheditorImageExt) {
		this.xheditorImageExt = xheditorImageExt;
	}

	public long getXheditorMaxSize() {
		return xheditorMaxSize;
	}

	public void setXheditorMaxSize(long xheditorMaxSize) {
		this.xheditorMaxSize = xheditorMaxSize;
	}

	public String getXheditorUploadDir() {
		return xheditorUploadDir;
	}

	public void setXheditorUploadDir(String xheditorUploadDir) {
		this.xheditorUploadDir = xheditorUploadDir;
	}

	public String getXheditorUploadPath() {
		return xheditorUploadPath;
	}

	public void setXheditorUploadPath(String xheditorUploadPath) {
		this.xheditorUploadPath = xheditorUploadPath;
	}

	public String getCookiesKey() {
		return cookiesKey;
	}

	public int getCookiesMaxAge() {
		return cookiesMaxAge;
	}

	public String getUploadImgDir() {
		return uploadImgDir;
	}

    public String getUploadImgPath() {
        return uploadImgPath;
    }

	public String getSiteName() {
		return siteName;
	}

	public String getSiteUrl() {
		return siteUrl;
	}

	public String getUploadPicExt() {
		return uploadPicExt;
	}

	public String getUploadApppicMaxsize() {
		return uploadApppicMaxsize;
	}

	public String getPageStaticUrl() {
		return pageStaticUrl;
	}

	/**查询数据库用户*/
	@Value("${db_query.user}")
	public String dbQueryUser;

	public String getDbQueryUser() {
		return dbQueryUser;
	}

    @Value("${web.show.num}")
    private int webshownum;

	public int getWebShowNum() {
		return webshownum;
	}
	
    @Value("${web.main.show}")
    private String webmainshow;

	public String getWebMainShow() {
		return webmainshow;
	}	
	
	@Value("${monitor.role}")
	private String monitorRole;

	public String getMonitorRole() {
		return monitorRole;
	}		

	/**
	 * 邮件服务器相关
	 */
	@Value("${mail.host}")
	private String mailHost;
	public String getMailHost(){
		return mailHost;
	}
	@Value("${mail.user}")
	private String mailUser;
	public String getMailUser(){
		return mailUser;
	}
	@Value("${mail.pwd}")
	private String mailPwd;
	public String getMailPwd(){
		return mailPwd;
	}
	@Value("${mail.obj}")
	private String mailOjb;
	public String getMailObj(){
		return mailOjb;
	}
	@Value("${mail.cc}")
	private String mailCC;
	public String getMailCC(){
		return mailCC;
	}
	
}
