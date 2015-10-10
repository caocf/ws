package com.cplatform.b2c.service;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jodd.util.StringUtil;

import org.apache.commons.io.FilenameUtils;
import org.apache.commons.lang.text.StrSubstitutor;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import com.cplatform.b2c.dto.UserCenterObj;
import com.cplatform.b2c.filter.WebStringUtils;
import com.cplatform.b2c.model.TMember;
import com.cplatform.b2c.repository.TMemberDao;
import com.cplatform.b2c.util.AppConfig;
import com.cplatform.b2c.util.Constants;
import com.cplatform.b2c.util.MemCacheUserCenterUtil;
import com.cplatform.b2c.util.RandomUtil;
import com.cplatform.b2c.util.TimeUtil;
import com.cplatform.csso.agent.http.SSOAgent;
import com.cplatform.soa.muc.SoaMemberService;
import com.cplatform.soa.muc.model.Member;
import com.cplatform.sso.lmsh.bean.LoginUserBean;
import com.cplatform.util2.image.ImageUtils;
import com.cplatform.util2.image.ImageUtils.FitType;
import com.cplatform.util2.image.image_magic.ImageResize;

/**
 * 人中心-基本信息 个人中心. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) 2014-2-20 上午10:27:05
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhangdong-ca@c-platform.com
 * @version 1.0.0
 */
@Service
public class TMemberService {

	private final Logger logger = Logger.getLogger(TMemberService.class);

	@Autowired
	private TMemberDao tMemberDao;

	@Autowired
	private AppConfig appConfig;

	@Autowired
	private SoaMemberService soaMemberService;

	@Autowired
	private EmailMessageService emailMessageService;

	@Autowired
	private MemCacheUserCenterUtil memCacheUserCenterUtil;

	@Autowired
	private CommonCacheService commonCacheService;

	private static Map<String, String> mailMap = new HashMap<String, String>();
	static {
		mailMap.put("163", "http://mail.163.com/");
		mailMap.put("126", "http://mail.126.com/");
		mailMap.put("sina", "http://mail.sina.com.cn/");
		mailMap.put("yahoo", "http://mail.cn.yahoo.com/");
		mailMap.put("sohu", "http://mail.sohu.com/");
		mailMap.put("yeah", "http://www.yeah.net/");
		mailMap.put("gmail", "http://gmail.google.com/");
		mailMap.put("hotmail", "http://www.hotmail.com/");
		mailMap.put("live", "http://www.hotmail.com/");
		mailMap.put("qq", "https://mail.qq.com/");
		mailMap.put("139", "http://mail.10086.cn/");
	}

	/**
	 * 通过用户的编号获取用户信息
	 * 
	 * @param id
	 *            用户编号
	 * @return
	 * @throws SQLException
	 */
	public TMember getTMemberById(Long id) throws SQLException {
		return tMemberDao.getTMemberById(id);
	}

	/**
	 * 判断是否操作的是当前登录的用户
	 * 
	 * @param id
	 * @param request
	 * @param response
	 * @return
	 */
	public boolean isCurrentUser(Long id, HttpServletRequest request, HttpServletResponse response) {
		LoginUserBean userinfo = new SSOAgent(request, response).loginUserInfo("mall");
		if (userinfo != null && userinfo.getId() != null) {
			if (userinfo.getId().equals(id)) {
				return true;
			}
		}
		return false;
	}

	/**
	 * 判断手机号是否被绑定
	 * 
	 * @param terminalId
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistMobile(String terminalId) throws SQLException {
		return tMemberDao.isExistMobile(terminalId);
	}

	/**
	 * 判断邮箱号是否被绑定
	 * 
	 * @param terminalId
	 * @return
	 * @throws SQLException
	 */
	public boolean isExistEmail(String email) throws SQLException {
		return tMemberDao.isExistEmail(email);
	}

	/**
	 * 获取文件的后缀名
	 * 
	 * @param filePathName
	 * @return
	 */
	public static String getNameExt(String filePathName) {
		return FilenameUtils.getExtension(filePathName);
	}

	/**
	 * 保存个人修改信息
	 * 
	 * @param request
	 *            用户请求
	 * @return 1:正常 2：昵称重复 3:更新用户信息发生错误 4：上传图片大于2M
	 * @throws SQLException
	 */
	public String updateInfo(TMember tMember, Long userId, HttpServletRequest request, HttpServletResponse response) throws SQLException {
		logger.info("开始修改个人信息....");
		// 用户信息
		Member member = soaMemberService.info(String.valueOf(userId), SoaMemberService.TYPE_ID);

		logger.info("获取修改前的个人信息....");

		if (member != null) {
			// 处理上传信息
			// 头像保存路径(100*100)
			String photoDir = appConfig.getPhotoDir();

			String prefix = "";
			boolean isHasImg = false;
			String suffix = "";
			String photoName = "";
			File uploadedFile = null;
			Map<String, String> formFields = new HashMap<String, String>();
			try {
				// 获取上传头像图片文件
				MultipartHttpServletRequest multiRequest = (MultipartHttpServletRequest) request;
				MultipartFile multFile = multiRequest.getFile("userPhoto");
				if (null != multFile && !multFile.isEmpty()) {
					// 取得上传的文件名
					String fileName = multFile.getOriginalFilename();
					if (StringUtils.isNotBlank(fileName)) {
						if (multFile.getSize() <= 2 * 1024 * 1024) {

							// 获得上传文件的类型
							suffix = getNameExt(fileName);
							logger.info("上传的图片后缀为：" + suffix);

							// 定义允许上传的文件类型
							List<String> suffixTypes = new ArrayList<String>();
							suffixTypes.add("jpg");
							suffixTypes.add("gif");
							if (suffixTypes.contains(suffix.toLowerCase())) {
								String rand = RandomUtil.randomAlphabetic(4);
								prefix = "userphoto/" + member.getId() + "/" + rand + "/";
								File prefixFile = new File(photoDir + prefix);
								if (!prefixFile.exists()) {
									prefixFile.mkdirs();
								}

								photoName = TimeUtil.now() + "_" + rand + ".jpg";
								String wholeFileName = prefix + photoName;
								logger.info("上传的图片路径为：" + photoDir + prefix + photoName);
								uploadedFile = new File(photoDir + prefix + photoName);

								multFile.transferTo(uploadedFile);
								logger.info("向服务器上写文件结束......");
								// 保存头像信息
								member.setAvatar(wholeFileName);
								// 上传了图片
								isHasImg = true;

							} else {
								logger.info("文件的格式不对(不是.gif或.jpg文件)");
								return Constants.USER_CENTER_INFO_FAIL_SUFFIX;
							}
						} else {// 如果上传的图片超过2M
							logger.info("上传的图片的大小超过2M......");
							return Constants.USER_CENTER_INFO_FAIL_SIZE;
						}
					}
				}

				if (tMember.getNickName().equals(member.getNickName())) {

					// 保存基础信息
					member.setNickName(formFields.get("nickName"));
					logger.info("昵称不需要修改");
				} else {
					// 昵称重复
					if (tMemberDao.repeatNickname(member.getId(), tMember.getNickName())) {
						return Constants.USER_CENTER_INFO_FAIL_NICKNAME;
					} else {
						// 保存基础信息
						member.setNickName(tMember.getNickName());
						logger.info("昵称需要修改");
					}
				}
				member.setSex(tMember.getSex() != null ? tMember.getSex().toString() : null);
				member.setBirthday(tMember.getBirthday());
				String qq = tMember.getQq();
				if (StringUtils.isNumeric(qq)) {
					member.setQq(qq);
				}
				member.setSignature(WebStringUtils.filterHtmlInput(tMember.getSignature()));

				// 修改用户信息，操作数据库
				soaMemberService.updateOther(member);
				logger.info("跟新用户信息成功!");
				logger.info("个人中心更新信息成功，认证中心更新信息开始....!");
				SSOAgent agent = new SSOAgent(request, response);
				agent.refreshUserInfo(String.valueOf(member.getId()), "-1", "www");
				return Constants.USER_CENTER_INFO_UPDATE_SUCCESS;
			}
			catch (Exception e) {
				logger.error(e.getMessage());
				logger.error("更新用户信息出错", e);
				return Constants.USER_CENTER_INFO_UPDATE_FAIL;
			}
		}
		return Constants.USER_CENTER_INFO_UPDATE_FAIL;
	}

	/**
	 * 处理传进来的坐标及宽度高度
	 * 
	 * @param str
	 *            原始坐标或者宽度，高度
	 * @param def
	 *            默认值
	 * @return
	 */
	public String changePlace(String str, String def) {
		if (StringUtil.isNotBlank(str)) {
			str = def;
		} else if (str.indexOf("px") >= 0) {
			str = str.substring(0, str.indexOf("px"));
		}
		return str;
	}

	/**
	 * 对上传的图像进行处理
	 * 
	 * @param x
	 *            截取开始的坐标x
	 * @param y
	 *            截取开始的坐标y
	 * @param width
	 *            截取的宽度
	 * @param height
	 *            截取的高度
	 * @param uploadedFile
	 *            处理的文件
	 * @param nameExt
	 *            上传文件的类型
	 */
	public void resizeImage(String x, String y, String width, String height, File uploadedFile, String nameExt, String prefix, String fileName,
	        String terminalId) {
		// 对坐标x进行处理
		x = changePlace(x, "0");
		// 对y坐标进行处理
		y = changePlace(y, "0");
		// 对传进来的宽度进行处理
		width = changePlace(width, "0");
		// 对传进来的高度度进行处理
		height = changePlace(height, "0");
		try {
			// 小型图片的存储路径(50*50)
			String littlePhotoDir = appConfig.getPhotoDirLittle();

			BufferedImage uploadImg = null;
			if ("gif".equals(nameExt) || "GIF".equals(nameExt)) {
				// 取gif图片的第一帧
				uploadImg = ImageUtils.getGifImageList(uploadedFile).get(0);
			} else {
				uploadImg = ImageUtils.openImage(uploadedFile);
			}
			int imgWidth = uploadImg.getWidth();
			String scaleWidth = new BigDecimal(imgWidth).divide(new BigDecimal(300), 2, BigDecimal.ROUND_HALF_EVEN).toString();
			int imgHeight = uploadImg.getHeight();
			String scaleHeight = new BigDecimal(imgHeight).divide(new BigDecimal(300), 2, BigDecimal.ROUND_HALF_EVEN).toString();
			double scale = 1.00;
			if (imgHeight >= imgWidth) {
				scale = Double.valueOf(scaleHeight);
			} else {
				scale = Double.valueOf(scaleWidth);
			}
			if (!"0".equals(x)) {
				x = new BigDecimal(scale).multiply(new BigDecimal(x)).toString();
				if (x.indexOf(".") >= 0) {
					x = x.substring(0, x.indexOf("."));
				}
			}
			if (!"0".equals(y)) {
				y = new BigDecimal(scale).multiply(new BigDecimal(y)).toString();
				if (y.indexOf(".") >= 0) {
					y = y.substring(0, y.indexOf("."));
				}
			}
			String nowWidth = new BigDecimal(scale).multiply(new BigDecimal(width)).toString();
			if (nowWidth.indexOf(".") >= 0) {
				nowWidth = nowWidth.substring(0, nowWidth.indexOf("."));
			}
			String nowHeight = new BigDecimal(scale).multiply(new BigDecimal(height)).toString();
			if (nowHeight.indexOf(".") >= 0) {
				nowHeight = nowHeight.substring(0, nowHeight.indexOf("."));
			}
			// 存成尺寸大小为100*100的图片
			BufferedImage newImg = ImageUtils.crop(uploadImg, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(nowWidth),
			                                       Integer.parseInt(nowHeight));
			newImg = ImageUtils.resize(newImg, 100, 100, FitType.SCALE_TO_NEWSIZE);
			ImageUtils.saveImageAsJPEG(newImg, uploadedFile, 15 * 1024);
			// 存成尺寸大小为50*50的图片
			BufferedImage newImgLittle = ImageUtils.crop(uploadImg, Integer.parseInt(x), Integer.parseInt(y), Integer.parseInt(nowWidth),
			                                             Integer.parseInt(nowHeight));
			newImgLittle = ImageUtils.resize(newImgLittle, 50, 50, FitType.SCALE_TO_NEWSIZE);
			File littleUploadFile = new File(littlePhotoDir + prefix + fileName);
			ImageUtils.saveImageAsJPEG(newImgLittle, littleUploadFile, 5 * 1024);

			// 如果压缩的jpg图片大于15KB
			if (uploadedFile.length() > 15 * 1024) {
				ImageResize imageResize = new ImageResize();
				imageResize.setColorNumber(16);
				imageResize.resize(uploadedFile.getName(), uploadedFile.getName());
			}
			if (littleUploadFile.length() > 5 * 1024) {
				ImageResize imageResize = new ImageResize();
				imageResize.setColorNumber(16);
				imageResize.resize(littleUploadFile.getName(), littleUploadFile.getName());

			}
			File dirFile = new File(appConfig.getPhotoDir() + "temp/" + terminalId);
			if (dirFile.isDirectory()) {
				dirFile.delete();
			}
		}
		catch (Exception ex) {
			logger.error(ex.getMessage());
		}
	}

	/**
	 * 获取修改用户信息提示信息
	 * 
	 * @param flag
	 * @return
	 */
	public String getAlert(String flag) {
		if (Constants.USER_CENTER_INFO_UPDATE_SUCCESS.equals(flag)) {
			// 修改信息过后更新认证中心数据
			return "<script>alert('基本信息更新成功');eval(parent.location='info.chtml');</script>";
		} else if (Constants.USER_CENTER_INFO_FAIL_NICKNAME.equals(flag)) {
			return "<script>alert('对不起，您的昵称已经重复，请重新填写！');eval(parent.location='info.chtml');</script>";
		} else if (Constants.USER_CENTER_INFO_FAIL_SIZE.equals(flag)) {
			return "<script>alert('对不起，您上传的图片大于2MB,请重新上传！');eval(parent.location='info.chtml');</script>";
		} else if (Constants.USER_CENTER_INFO_FAIL_SUFFIX.equals(flag)) {
			return "<script>alert('您上传的头像格式不符,请重新上传！');eval(parent.location='info.chtml');</script>";
		} else {
			return "<script>alert('基本信息更新失败，请重新提交 ！');eval(parent.location='info.chtml');</script>";
		}
	}

	/**
	 * 邮箱跳转到相应的登录页面进行登录
	 * 
	 * @param email
	 * @return
	 */
	public String mailWebAddress(String email) {
		if (email == null || email.length() == 0) {
			return null;
		}
		try {
			email = email.toLowerCase();
			String mainAddress = email.substring(email.lastIndexOf("@") + 1);
			String domainAddress = mainAddress.substring(0, mainAddress.lastIndexOf("."));
			return mailMap.containsKey(domainAddress) ? mailMap.get(domainAddress) : null;
		}
		catch (Exception e) {
			return null;
		}
	}

	/**
	 * 根据传入的值查找用户信息
	 * 
	 * @param userName
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	@SuppressWarnings("unused")
	private Member findMember(String userName, String type) throws SQLException {
		Member member;
		if ("email".equals(type)) {
			member = tMemberDao.findByEmail(userName);
		} else {
			member = tMemberDao.findByTerminalId(userName);
		}
		return member;
	}

	/**
	 * 获取绑定邮箱时，发送邮箱的内容
	 * 
	 * @param userId
	 * @param userName
	 * @param code
	 * @return
	 * @throws IOException
	 */
	private String genBindEmailContent(String userId, String userName, String code) throws IOException {
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put("userId", userId);
		valuesMap.put("userName", userName);
		valuesMap.put("localServer", appConfig.getServer_host());
		valuesMap.put("code", code);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		return sub.replace(commonCacheService.getBindEmailTemplateText());
	}

	/**
	 * 获取修改邮箱时，发送邮箱的内容
	 * 
	 * @param userId
	 * @param userName
	 * @param code
	 * @return
	 * @throws IOException
	 */
	private String genEditEmailContent(String userId, String userName, String code) throws IOException {
		Map<String, String> valuesMap = new HashMap<String, String>();
		valuesMap.put("userId", userId);
		valuesMap.put("userName", userName);
		valuesMap.put("localServer", appConfig.getServer_host());
		valuesMap.put("code", code);
		StrSubstitutor sub = new StrSubstitutor(valuesMap);
		return sub.replace(commonCacheService.getEditEmailTemplateText());
	}

	/**
	 * 获取发送邮件中的变量内容
	 * 
	 * @param emailOrMobile
	 * @param type
	 * @return
	 * @throws SQLException
	 */
	private String[] getRelaceContent(String email, String type, String userId) throws SQLException {
		Member member = soaMemberService.info(userId, SoaMemberService.TYPE_ID);
		String userName = "";
		if (StringUtils.isNotBlank(member.getRealName())) {
			userName = member.getRealName();
		} else if (StringUtils.isNotBlank(member.getNickName())) {
			userName = member.getNickName();
		} else if (StringUtils.isNotBlank(member.getTerminalId())) {
			userName = member.getTerminalId();
		} else if (StringUtils.isNotBlank(member.getEmail())) {
			userName = member.getEmail();
		}
		String code = memCacheUserCenterUtil.genCode(userId, userName, type, email);
		return new String[] { userId, userName, code };
	}

	/**
	 * 发送邮件
	 * 
	 * @param email
	 *            邮箱地址
	 * @param type
	 *            操作邮箱类型：绑定邮箱、修改邮箱
	 * @param userId
	 *            用户编号
	 * @throws IOException
	 * @throws SQLException
	 */
	public void sendMail(String email, String type, String userId) throws IOException, SQLException {
		String[] data = getRelaceContent(email, type, userId);
		String content = "";
		if (UserCenterObj.BIND_EMAIL.equals(type)) {
			content = genBindEmailContent(data[0], data[1], data[2]);
		} else if (UserCenterObj.EDIT_EMAIL.equals(type)) {
			content = genEditEmailContent(data[0], data[1], data[2]);
		}
		emailMessageService.send(email, content);
	}

	/**
	 * 根据key值获取当前UserCenterObj信息
	 * 
	 * @param userId
	 * @param code
	 * @param type
	 * @return
	 */
	public UserCenterObj findByKey(String userId, String code, String type) {
		return memCacheUserCenterUtil.find(userId, code, type);
	}

}
