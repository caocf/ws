package com.cplatform.mall.dc.utils;

import org.springframework.stereotype.Service;

/**
 * 标题、简要说明.
 * <p>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2013-6-6 下午3:15:22
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhengmz@c-platform.com
 * @version 1.0.0
 */
@Service
public class PathUtil {
/*
	public static final int TYPE_ITEM = 1;

	public static final int TYPE_ITEM_PIC = 2;

	public static final int TYPE_SHOP = 3;

	public static final int TYPE_SHOP_2 = 4;

	@Autowired
	private AppConfig appConfig;

	public String getPathById(int type, long id) {

		
		 * 算法： 第一层 = ID/(2000*2000) 第二层= (ID mod （2000×2000））/2000 第三层 = ID mod
		 * 2000
		 
		String path = "";
		long l1 = id / (2000 * 2000);

		long l2 = (id % (2000 * 2000)) / 2000;

		long l3 = id % 2000;

		switch (type) {

			case TYPE_ITEM: {
				path = String.format("%s/item/i_%d_%d_%d.htm", appConfig.getOrdergoodsHostServer(), l1, l2, l3);
				break;
			}

			case TYPE_SHOP: {

				path = String.format("%s/shop/s_%d_%d_%d.htm", appConfig.getOrdergoodsHostServer(), l1, l2, l3);
				break;

			}

			case TYPE_SHOP_2: {

				path = String.format("%s/shop/s2_%d_%d_%d.htm", appConfig.getOrdergoodsHostServer(), l1, l2, l3);
				break;

			}

			case TYPE_ITEM_PIC: {

				path = String.format("%s/img/item/%d_%d_%d/", appConfig.getOrdergoodsImgServer(), l1, l2, l3);
				break;
			}

		}

		return path;
	}

	public String getPathById(String type, String id) {
		if (type == null || id == null) {
			return "";
		}
		return getPathById(Integer.parseInt(type), Long.parseLong(id));
	}

	public String getSavePathById(int type, long id) {

		
		 * 算法： 第一层 = ID/(2000*2000) 第二层= (ID mod （2000×2000））/2000 第三层 = ID mod
		 * 2000
		 
		String path = "";
		long l1 = id / (2000 * 2000);

		long l2 = (id % (2000 * 2000)) / 2000;

		long l3 = id % 2000;

		switch (type) {

			case TYPE_ITEM: {
				path = String.format("/item/%d/%d/i_%d_%d_%d.htm", l1, l2, l1, l2, l3);
				break;
			}

			case TYPE_SHOP: {

				path = String.format("/shop/%d/%d/s_%d_%d_%d.htm", l1, l2, l1, l2, l3);
				break;

			}

			case TYPE_SHOP_2: {

				path = String.format("/shop/%d/%d/s2_%d_%d_%d.htm", l1, l2, l1, l2, l3);
				break;

			}

			case TYPE_ITEM_PIC: {

				path = String.format("/img/item/%d/%d/%d/", l1, l2, l3, l1, l2, l3);
				break;
			}

		}

		return path;
	}

	*//**
	 * 获取不同业务类型的文件生成路径
	 * 
	 * @param key
	 *            业务key
	 * @param id
	 *            业务id
	 * @param ext
	 *            扩展名
	 * @return
	 *//*
	public PathInfo getPathById(String key, long id, String ext) {

		String webpath = "";
		String savepath = "";// 保存路径
		String filename = "";// 文件名称
		ext = ext.toLowerCase();

		
		 * 算法： 第一层 = ID/(2000*2000) 第二层= (ID mod （2000×2000））/2000 第三层 = ID mod
		 * 2000
		 
		long l1 = id / (2000 * 2000);

		long l2 = (id % (2000 * 2000)) / 2000;

		long l3 = id % 2000;

		if (BsFileService.BS_ITEM_COVER_PIC_KEY.equals(key)) {
			// 商品封面
			webpath = String.format("/img/item/%d_%d_%d/", l1, l2, l3);
			savepath = String.format("/img/item/%d/%d/%d/N0", l1, l2, l3);
			filename = String.format("p_%d%d%d.%s", l1, l2, l3, ext);

			return new PathInfo(appConfig.getUploadImgDir() + savepath, webpath, filename);
		}
		if (BsFileService.BS_ITEM_PIC_KEY.equals(key)) {// 商品图集
			webpath = String.format("/img/item/%d_%d_%d/", l1, l2, l3);
			savepath = String.format("/img/item/%d/%d/%d/N0", l1, l2, l3);
			filename = String.format("p_%d%d%d_%s.%s", l1, l2, l3, TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmssxxx), ext);
			return new PathInfo(appConfig.getUploadImgDir() + savepath, webpath, filename);
		}
		if (BsFileService.BS_PROPERTY_PIC_KEY.equals(key)) {// 属性相关图片
			webpath = String.format("/img/item/p/%d_%d_%d/", l1, l2, l3);
			savepath = String.format("/img/item/p/%d/%d/%d/", l1, l2, l3);
			filename = String.format("p_%d%d%d.%s", l1, l2, l3, ext);
			return new PathInfo(appConfig.getUploadImgDir() + savepath, webpath, filename);
		}

		if (BsFileService.BS_SHOP_MAIN_IMG_KEY.equals(key)) {// 门店logo
			webpath = String.format("/img/shop/%d_%d_%d/", l1, l2, l3);
			savepath = String.format("/img/shop/%d/%d/%d/", l1, l2, l3);
			filename = String.format("s_%d%d%d.%s", l1, l2, l3, ext);
			return new PathInfo(appConfig.getUploadImgDir() + savepath, webpath, filename);
		}
		if (BsFileService.BS_SHOP_OTHER_IMG_KEY.equals(key)) {// 门店其他图片
			webpath = String.format("/img/shop/%d_%d_%d/", l1, l2, l3);
			savepath = String.format("/img/shop/%d/%d/%d/", l1, l2, l3);
			filename = String.format("s_%d%d%d_%s.%s", l1, l2, l3, TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmssxxx), ext);
			return new PathInfo(appConfig.getUploadImgDir() + savepath, webpath, filename);
		}

		// 其他情况
		webpath = String.format("/file/other/%d_%d_%d/", l1, l2, l3);
		savepath = String.format("/file/other/%d/%d/%d/", l1, l2, l3);
		filename = String.format("o_%d%d%d_%s.%s", l1, l2, l3, TimeStamp.getTime(TimeStamp.YYYYMMDDhhmmssxxx), ext);
		return new PathInfo(appConfig.getUploadImgDir() + savepath, webpath, filename);
	}

	public static class PathInfo {

		public PathInfo(String savepath, String webpath, String filename) {
			this.savepath = savepath;
			this.webPath = webpath;
			this.filename = filename;
		}

		private String savepath;

		private String filename;

		private String webPath;

		public String getRealPath() {
			return this.getSavepath() + this.getFilename();
		}

		*//**
		 * 获得真实的web访问领
		 * 
		 * @param contextPath
		 *            是否有前置
		 * @return
		 *//*
		public String getRealWebPath(String contextPath) {
			return contextPath + this.getWebPath() + this.getFilename();
		}

		public String getFilename() {
			return filename;
		}

		public void setFilename(String filename) {
			this.filename = filename;
		}

		public String getWebPath() {
			return webPath;
		}

		public void setWebPath(String webPath) {
			this.webPath = webPath;
		}

		public String getSavepath() {
			return savepath;
		}

		public void setSavepath(String savepath) {
			this.savepath = savepath;
		}

	}

	public static void main(String[] args) {
		// long number = 2000;

	}*/
}
