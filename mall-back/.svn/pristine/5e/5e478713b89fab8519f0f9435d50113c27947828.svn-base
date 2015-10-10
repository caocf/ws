package com.cplatform.mall.back.cont.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.cplatform.mall.back.cont.mms.util.DateUtils;
import com.cplatform.mall.back.cont.mms.util.MmsUtils;
import com.cplatform.mall.back.cont.mms.util.TimeUtil;
import com.cplatform.mall.back.utils.AppConfig;
import com.cplatform.mall.back.utils.JsonRespWrapper;
import com.cplatform.util2.image.ImageUtils;
import com.cplatform.util2.image.gif.GifDecoder;
import com.google.common.collect.Lists;

/**
 * 内容查询service类
 * <p>
 * Copyright: Copyright (c) 2012-11-15 上午11:14:52
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author jisn@c-platform.com
 * @version 1.0.0
 */
@Component
public class ContentService {

	class UploadException extends RuntimeException {

		private static final long serialVersionUID = 1072880924133226101L;

		public UploadException(String message) {
			super(message);
		}
	}

	static final Log logger = LogFactory.getLog(ContentService.class);

	@Autowired
	AppConfig appConfig;

	/**
	 * 格式化文件size
	 * 
	 * @param size
	 * @return
	 */
	public String getFormatSize(double size) {
		double kiloByte = size / 1024;
		if (kiloByte < 1) {
			return size + "字节";
		}

		double megaByte = kiloByte / 1024;
		if (megaByte < 1) {
			BigDecimal result1 = new BigDecimal(Double.toString(kiloByte));
			return result1.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "KB";
		}

		double gigaByte = megaByte / 1024;
		if (gigaByte < 1) {
			BigDecimal result2 = new BigDecimal(Double.toString(megaByte));
			return result2.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "MB";
		}

		double teraBytes = gigaByte / 1024;
		if (teraBytes < 1) {
			BigDecimal result3 = new BigDecimal(Double.toString(gigaByte));
			return result3.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "GB";
		}
		BigDecimal result4 = new BigDecimal(teraBytes);
		return result4.setScale(1, BigDecimal.ROUND_HALF_UP).toPlainString() + "TB";
	}

	/**
	 * 上传文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param type
	 *            文件类型 pic-图片 sound-铃声
	 * @param width
	 *            长传图片宽度
	 * @param height
	 *            长传图片高度
	 * @param is
	 * @return
	 * @throws IOException
	 */
	public Object uploadFile(String fileName, String type, InputStream is) throws IOException {
		// TODO: 图片大小判断
		try {
			// checkImageFileExtention(fileName, type);
			String today = DateUtils.getDateByPattern("yyMMdd");
			String dir = FilenameUtils.normalizeNoEndSeparator(appConfig.getMmsPath()) + File.separatorChar + appConfig.getMmsTmp()
			        + File.separatorChar + today + File.separatorChar;
			String tmpPath = appConfig.getMmsTmp() + "/" + today + "/";

			String filePart = TimeUtil.format(TimeUtil.now(), "yyyyMMddHHmmss") + RandomStringUtils.randomAlphanumeric(5) + "."
			        + FilenameUtils.getExtension(fileName);

			if (!new File(dir).exists()) {
				new File(dir).mkdirs();
			}

			long fullMixedFileSize = saveToFile(fileName, is, new File(dir + filePart), type);

			// realname-原来上传的文件名, 未使用

			// path - 相对于 /data/mms 的全路径文件名

			// name - 保存的文件名

			// fullFilename - 完整文件名 绝对路径+文件名

			// size-如果是图片返回它的宽、高
			if ("pic".equals(type)) {
				int[] wh = MmsUtils.getWh(dir + filePart);

				// 生成客户端的返回数据(json格式)
				return JsonRespWrapper.success(Lists.newArrayList("realname", "path", "name", "fullFilename", "size", "w", "h"), Lists
				        .newArrayList(FilenameUtils.getName(fileName), tmpPath + filePart, filePart, dir + filePart,
				                      String.valueOf(fullMixedFileSize), String.valueOf(wh[0]), String.valueOf(wh[1])));
			}

			// 生成客户端的返回数据(json格式)
			return JsonRespWrapper.success(Lists.newArrayList("realname", "path", "name", "fullFilename", "size"),
			                               Lists.newArrayList(FilenameUtils.getName(fileName), tmpPath + filePart, filePart, dir + filePart,
			                                                  String.valueOf(fullMixedFileSize)));
		}
		catch (UploadException ex) {
			return JsonRespWrapper.error(ex.getMessage());
			// return JsonRespWrapper.success(ex.getMessage(),
			// "/cont/mms/list");
		}
	}

	public Object singleCrop(HttpServletRequest request) {
		String today = DateUtils.getDateByPattern("yyMMdd");
		String dir = FilenameUtils.normalizeNoEndSeparator(appConfig.getMmsPath()) + File.separatorChar + appConfig.getMmsTmp() + File.separatorChar
		        + today + File.separatorChar;
		try {

			// 强制创建文件目录
			FileUtils.forceMkdir(new File(dir));

			// 原来上传的文件名
			String realName = new String(request.getParameter("realname").getBytes("GBK"), "UTF-8");

			// 上传的文件名称
			String orgName = request.getParameter("name");

			// 文件路径
			String orgPath = appConfig.getMmsPath() + request.getParameter("path");

			// 待剪裁的x坐标值
			int cropx = 0;
			if (request.getParameter("cropx") != null && !"".equals(request.getParameter("cropx"))) {
				cropx = Integer.valueOf(request.getParameter("cropx"));
			}

			// 待剪裁的y坐标值
			int cropy = 0;
			if (request.getParameter("cropy") != null && !"".equals(request.getParameter("cropy"))) {
				cropy = Integer.valueOf(request.getParameter("cropy"));
			}
			// 裁剪框的width
			int cropw = 0;
			if (request.getParameter("cropw") != null && !"".equals(request.getParameter("cropw"))) {
				cropw = Integer.valueOf(request.getParameter("cropw"));
			}
			// 裁剪框的height
			int croph = 0;
			if (request.getParameter("croph") != null && !"".equals(request.getParameter("croph"))) {
				croph = Integer.valueOf(request.getParameter("croph"));
			}

			// 大头贴背景图 或 彩信图片 原始宽度
			int originWidth = 0;
			if (request.getParameter("w") != null && !"".equals(request.getParameter("w"))) {
				originWidth = Integer.valueOf(request.getParameter("w"));
			}
			// 大头贴背景图 或 彩信图片 原始高度
			int originHeight = 0;
			if (request.getParameter("h") != null && !"".equals(request.getParameter("h"))) {
				originHeight = Integer.valueOf(request.getParameter("h"));
			}
			// 大头贴背景图 或 彩信图片 缩放后的 宽度
			int scaledWidth = 0;
			if (request.getParameter("width") != null && !"".equals(request.getParameter("width"))) {
				scaledWidth = Integer.valueOf(request.getParameter("width"));
			}
			// 大头贴背景图 或 彩信图片 缩放后的 高度
			int scaledHeight = 0;
			if (request.getParameter("height") != null && !"".equals(request.getParameter("height"))) {
				scaledHeight = Integer.valueOf(request.getParameter("height"));
			}
			// 大头贴框 的url
			String sticker = "";
			if (request.getParameter("url") != null && !"".equals(request.getParameter("url"))) {
				sticker = request.getParameter("url");
			}

			// 生成上传的文件的file对象
			File orgFile = new File(orgPath);
			if (!orgFile.exists()) {
				return JsonRespWrapper.error("裁剪文件失败");
			}

			BufferedImage originImageBuffer = ImageUtils.openImage(orgFile);
			BufferedImage cropedandscaledImageBuffer = originImageBuffer;
			// 有缩放
			if (scaledWidth != 0) {
				cropedandscaledImageBuffer = ImageUtils.resize(cropedandscaledImageBuffer, scaledWidth, scaledHeight);
			}
			// 有裁剪
			if (cropw != 0) {
				cropedandscaledImageBuffer = ImageUtils.crop(cropedandscaledImageBuffer, cropx, cropy, cropw, croph);
			}

			List<BufferedImage> gifNewFrameList = new ArrayList<BufferedImage>();
			// 上传文件的扩展名,
			String fileExt = FilenameUtils.getExtension(orgName).toLowerCase();
			// 保存的随机文件名
			String randomPart = RandomStringUtils.randomAlphabetic(10);
			String filename = "";
			String fullFilename = "";
			// 要合成大头贴
			if (!"".equals(sticker)) {
				String mmsWebpath = appConfig.getMmsWebPath();
				sticker = sticker.substring(sticker.indexOf(mmsWebpath));
				sticker = sticker.replace(mmsWebpath, appConfig.getMmsPath());
				GifDecoder decoder = new GifDecoder();
				decoder.read(sticker);
				int n = decoder.getFrameCount(); // 得到frame的个数
				File stickerFile = new File(sticker);
				List<Integer> gifFrameDelayList = ImageUtils.getGifImageDelayList(stickerFile);
				// n=0;
				if (n != 0) {
					for (int i = 0; i < n; i++) {
						BufferedImage frame = decoder.getFrame(i); // 得到帧
						System.out.println("::::::::: 得到一个帧：  " + frame);
						// 通过getGraphics().drawImage的方式叠加图片试试---开始---
						BufferedImage toCompose = ImageUtils.createImage(cropedandscaledImageBuffer);
						toCompose.getGraphics().drawImage(frame, 0, 0, null);
						gifNewFrameList.add(toCompose);
					}
				} else {
					BufferedImage toCompose = ImageUtils.createImage(cropedandscaledImageBuffer);
					BufferedImage stickerImageBuffer = ImageUtils.openImage(sticker);
					toCompose.getGraphics().drawImage(stickerImageBuffer, 0, 0, null);
					gifNewFrameList.add(toCompose);
				}

				// 合成大头贴的话，强制扩展名为gif
				fileExt = "gif";
				filename = randomPart + "." + fileExt;
				fullFilename = dir + filename;
				ImageUtils.saveImageAsGIF(gifNewFrameList, new File(fullFilename), gifFrameDelayList);
			} else {
				filename = randomPart + "." + fileExt;
				fullFilename = dir + filename;
				ImageUtils.saveImage(cropedandscaledImageBuffer, ImageUtils.ImageFormat.JPEG, new File(fullFilename));
			}
			//
			long fileSize = new File(fullFilename).length();

			// 生成客户端的返回数据(json格式)
			// realname-原来上传的文件名, 未使用
			// path- 相对于 /data/mms 的全路径文件名
			// name-保存的文件名
			// size-文件大小
			// w h 返回宽、高
			return JsonRespWrapper.success(Lists.newArrayList("realname", "path", "name", "fullFilename", "size", "w", "h"), Lists
			        .newArrayList(realName, appConfig.getMmsTmp() + File.separatorChar + today + File.separatorChar + filename, filename,
			                      String.valueOf(fileSize), String.valueOf(cropw == 0 ? scaledWidth : cropw),
			                      String.valueOf(croph == 0 ? scaledHeight : croph)));
		}
		catch (Exception ex) {
			return JsonRespWrapper.error("裁剪文件失败");
		}

	}

	// 保存文件
	@SuppressWarnings("resource")
	private long saveToFile(String fileName, InputStream input, File outfile, String type) {
		long maxSize = 0;
		if (type.equals("pic")) {
			// 彩信图片
			maxSize = appConfig.getMmsPicMaxSize();
		} else if (type.equals("sound")) {
			// 彩信铃声
			maxSize = appConfig.getMmsSoundMaxSize();
		}

		FileOutputStream output = null;
		long count = 0;
		try {
			output = new FileOutputStream(outfile);
			byte[] buffer = new byte[4096];

			int n = 0;
			while (-1 != (n = input.read(buffer))) {
				output.write(buffer, 0, n);
				count += n;
				if (count > maxSize) {
					String message = String.format("上传的文件 (%s) 超过上传文件大小限制, 最大值为  %s.", fileName, getFormatSize(maxSize));
					throw new UploadException(message);
				}
			}
		}
		catch (IOException ex) {
			logger.warn(ex.getMessage(), ex);
			throw new UploadException(String.format("上传的文件 (%s) 上传时发生错误：" + ex.getMessage(), fileName));
		}
		finally {
			IOUtils.closeQuietly(output);
		}
		return count;
	}
}
