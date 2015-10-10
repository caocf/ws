package com.cplatform.mall.back.utils;

import java.io.IOException;
import java.io.InputStream;
import java.math.BigDecimal;
import java.net.URLEncoder;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.io.IOUtils;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.net.ftp.FTP;
import org.apache.commons.net.ftp.FTPClient;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Component;

import com.cplatform.mall.back.store.entity.StoreFee;

/**
 * <p>
 * 工具类 Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: liujun
 * <p>
 * Version: 1.0
 */
@Component
public class MallUtils {

	private static final Logger logger = Logger.getLogger(MallUtils.class);

	public static StoreFee dealMultiStoreFeeName(StoreFee fee) {

		if (fee != null) {
			BigDecimal ratio = new BigDecimal(100);
			if (fee.getMaxfeeamount() != null
					&& !"".equals(fee.getMaxfeeamount())) {
				BigDecimal maxFeeAmount = new BigDecimal(fee.getMaxfeeamount());

				// // b.setScale(2, BigDecimal.ROUND_HALF_UP).
				fee.setMaxfeeamount(maxFeeAmount.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference1() != null
					&& !"".equals(fee.getUpreference1())) {
				BigDecimal upreFerence1 = new BigDecimal(fee.getUpreference1());
				fee.setUpreference1(upreFerence1.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference2() != null
					&& !"".equals(fee.getUpreference2())) {
				BigDecimal upreFerence2 = new BigDecimal(fee.getUpreference2());
				fee.setUpreference2(upreFerence2.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference3() != null
					&& !"".equals(fee.getUpreference3())) {
				BigDecimal upreFerence3 = new BigDecimal(fee.getUpreference3());
				fee.setUpreference3(upreFerence3.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference4() != null
					&& !"".equals(fee.getUpreference4())) {
				BigDecimal upreFerence4 = new BigDecimal(fee.getUpreference4());
				fee.setUpreference4(upreFerence4.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference5() != null
					&& !"".equals(fee.getUpreference5())) {
				BigDecimal upreFerence5 = new BigDecimal(fee.getUpreference5());
				fee.setUpreference5(upreFerence5.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}
			// /////////////////

			if (fee.getFixfeeamount1() != null
					&& !"".equals(fee.getFixfeeamount1())) {
				BigDecimal fixFeeAmount1 = new BigDecimal(fee
						.getFixfeeamount1());
				fee.setFixfeeamount1(fixFeeAmount1.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getFixfeeamount2() != null
					&& !"".equals(fee.getFixfeeamount2())) {
				BigDecimal fixFeeAmount2 = new BigDecimal(fee
						.getFixfeeamount2());
				fee.setFixfeeamount2(fixFeeAmount2.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getFixfeeamount3() != null
					&& !"".equals(fee.getFixfeeamount3())) {
				BigDecimal fixFeeAmount3 = new BigDecimal(fee
						.getFixfeeamount3());
				fee.setFixfeeamount3(fixFeeAmount3.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getFixfeeamount4() != null
					&& !"".equals(fee.getFixfeeamount4())) {
				BigDecimal fixFeeAmount4 = new BigDecimal(fee
						.getFixfeeamount4());
				fee.setFixfeeamount4(fixFeeAmount4.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getFixfeeamount5() != null
					&& !"".equals(fee.getFixfeeamount5())) {
				BigDecimal fixFeeAmount5 = new BigDecimal(fee
						.getFixfeeamount5());
				fee.setFixfeeamount5(fixFeeAmount5.multiply(ratio).setScale(0,
						BigDecimal.ROUND_DOWN).toString());
			}

		}

		return fee;

	}

	public static StoreFee dealDiviStoreFeeName(StoreFee fee) {

		if (fee != null) {

			BigDecimal ratio = new BigDecimal(100);
			// divide(b2,scale,BigDecimal.ROUND_HALF_UP)
			if (fee.getMaxfeeamount() != null
					&& !"".equals(fee.getMaxfeeamount())) {
				BigDecimal maxFeeAmount = new BigDecimal(fee.getMaxfeeamount());
				fee.setMaxfeeamount(maxFeeAmount.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference1() != null
					&& !"".equals(fee.getUpreference1())) {
				BigDecimal upreFerence1 = new BigDecimal(fee.getUpreference1());
				fee.setUpreference1(upreFerence1.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference2() != null
					&& !"".equals(fee.getUpreference2())) {
				BigDecimal upreFerence2 = new BigDecimal(fee.getUpreference2());
				fee.setUpreference2(upreFerence2.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference3() != null
					&& !"".equals(fee.getUpreference3())) {
				BigDecimal upreFerence3 = new BigDecimal(fee.getUpreference3());
				fee.setUpreference3(upreFerence3.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference4() != null
					&& !"".equals(fee.getUpreference4())) {
				BigDecimal upreFerence4 = new BigDecimal(fee.getUpreference4());
				fee.setUpreference4(upreFerence4.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getUpreference5() != null
					&& !"".equals(fee.getUpreference5())) {
				BigDecimal upreFerence5 = new BigDecimal(fee.getUpreference5());
				fee.setUpreference5(upreFerence5.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}
			// /////////////////

			if (fee.getFixfeeamount1() != null
					&& !"".equals(fee.getFixfeeamount1())) {
				BigDecimal fixFeeAmount1 = new BigDecimal(fee
						.getFixfeeamount1());
				fee.setFixfeeamount1(fixFeeAmount1.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getFixfeeamount2() != null
					&& !"".equals(fee.getFixfeeamount2())) {
				BigDecimal fixFeeAmount2 = new BigDecimal(fee
						.getFixfeeamount2());
				fee.setFixfeeamount2(fixFeeAmount2.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getFixfeeamount3() != null
					&& !"".equals(fee.getFixfeeamount3())) {
				BigDecimal fixFeeAmount3 = new BigDecimal(fee
						.getFixfeeamount3());
				fee.setFixfeeamount3(fixFeeAmount3.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getFixfeeamount4() != null
					&& !"".equals(fee.getFixfeeamount4())) {
				BigDecimal fixFeeAmount4 = new BigDecimal(fee
						.getFixfeeamount4());
				fee.setFixfeeamount4(fixFeeAmount4.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

			if (fee.getFixfeeamount5() != null
					&& !"".equals(fee.getFixfeeamount5())) {
				BigDecimal fixFeeAmount5 = new BigDecimal(fee
						.getFixfeeamount5());
				fee.setFixfeeamount5(fixFeeAmount5.divide(ratio, 2,
						BigDecimal.ROUND_DOWN).toString());
			}

		}

		return fee;

	}

	/**
	 * @param host
	 *            ftp服务器ip
	 * @param port
	 *            ftp服务器端口号
	 * @param username
	 *            ftp帐号
	 * @param password
	 *            ftp密码
	 * @param remotepath
	 *            ftp切换目录
	 * @param filename
	 *            文件
	 * @param input
	 *            文件流 return 1表示上传成功，-1表示上传失败或异常
	 */
	public static String uploadFileFtp(String host, int port, String username,
			String password, String remotepath, String filename,
			InputStream input) {

		FTPClient ftp = null;
		try {
			logger.info("ftp信息：host:" + host + ";port:" + port + ";username:"
					+ username + ";password:" + password + ";remotepath:"
					+ remotepath + ";filename:" + filename);
			ftp = getFTPConn(host, port, username, password);// 登录ftp服务器

			if (ftp.changeWorkingDirectory(remotepath)) {
				logger.info("更改目录成功");
			} else {

				logger.error("更改目录失败");
				return "-1";
			}
			ftp.setBufferSize(1024);
			ftp.setControlEncoding("GBK");
			ftp.changeWorkingDirectory(remotepath);
			ftp.enterLocalPassiveMode(); // 没有这个 ftp.storeFile(filename,
											// ftpIn)返回false
			ftp.setFileType(FTP.BINARY_FILE_TYPE);// 如果缺省该句 传输txt正常
													// 但图片和其他格式的文件传输出现乱码
			logger.info("ftp信息：host:");
			boolean flag = ftp.storeFile(filename, input);
			input.close();
			if (flag) {
				logger.info("上传成功");
				return "1";
			} else {
				logger.error("上传失败");
				return "-1";
			}

		} catch (IOException e) {
			logger.error(e.getMessage());
			return "-1";
		} finally {
			closeFtp(ftp);
		}

	}

	public static FTPClient getFTPConn(String host, int port, String user,
			String pwd) {

		int reply = 0;
		FTPClient ftp = new FTPClient();
		try {
			ftp.connect(host, port);
			ftp.login(user, pwd);// 登录
			reply = ftp.getReplyCode();
		} catch (Exception e) {
			logger.error(e.getMessage());
		}
		// 连接FTP服务器
		// 如果采用默认端口，可以使用ftp.connect(url)的方式直接连接FTP服务器
		// ftp.login("onlinebarcode", "onlinebarcode");// 登录

		return ftp;
	}

	public static void closeFtp(FTPClient ftpClient) {
		if (ftpClient != null && ftpClient.isConnected()) {
			try {
				boolean isLogOut = ftpClient.logout();
				if (isLogOut) {
					System.out.println("成功关闭ftp连接");
					logger.info("成功关闭ftp连接");
				}
			} catch (IOException e) {
				logger.error("关闭FTP服务器异常");
			} finally {
				try {
					ftpClient.disconnect();
				} catch (IOException e) {
					logger.error("关闭服务器连接异常");

				}
			}

		}
	}

	/**
	 * 导出流。
	 * 
	 * @param request
	 * 
	 * @param response
	 *            响应
	 * @param is
	 *            文件流
	 * @param contentType
	 *            内容类型
	 * @param fileName
	 *            输出文件名
	 */
	public static void downLodaFileOutputStream(HttpServletRequest request,
			HttpServletResponse response, InputStream is, String contentType,
			String fileName) {

		// 设置响应头信息
		response.setContentType(contentType);
		response.addHeader("Pragma", "no-cache");
		response.addHeader("Cache-Control", "no-cache");

		response.setDateHeader("Expires", 0);
		response.setHeader("Cache-Control",
				"no-store, no-cache, must-revalidate");
		response.addHeader("Cache-Control", "post-check=0, pre-check=0");
		// response.setHeader("Pragma", "no-cache");

		try {
			String agent = request.getHeader("USER-AGENT");
			if (null != agent && -1 != agent.indexOf("Mozilla")
					&& -1 != agent.indexOf("Firefox")) { // 针对Firefox
				String codedFileName = "=?UTF-8?B?"
						+ (new String(Base64.encodeBase64(fileName
								.getBytes("UTF-8")))) + "?=";
				response.addHeader("Content-Disposition",
						"attachment; filename=\"" + codedFileName);
			} else if (null != agent) { // IE,360,谷歌
				response.addHeader("Content-Disposition",
						"attachment; filename=\""
								+ URLEncoder.encode(fileName, "UTF-8") + "\"");
			}
			// response.setHeader("Content-Disposition",
			// "attachment; filename=\"" + URLEncoder.encode(fileName,
			// QjsConstants.CHARSET_UTF8) + "\"");
		} catch (Exception e) {
			logger.error(e.getMessage());

		}

		// 导出流
		try {
			IOUtils.copy(is, response.getOutputStream());
		} catch (IOException e) {
			logger.error(e.getMessage());

		}
	}

}
