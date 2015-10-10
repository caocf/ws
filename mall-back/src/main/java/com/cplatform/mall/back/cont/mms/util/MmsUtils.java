package com.cplatform.mall.back.cont.mms.util;

import java.awt.Image;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.imageio.ImageIO;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.math.NumberUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.input.SAXBuilder;

import com.cplatform.mall.back.cont.entity.ContMms;
import com.cplatform.mall.back.cont.mms.bean.Frame;
import com.cplatform.mall.back.cont.mms.bean.Pic;
import com.cplatform.mall.back.cont.mms.bean.Sound;
import com.cplatform.mall.back.cont.mms.bean.Text;

/**
 * 彩信保存读取工具类
 * <p>
 * Copyright: Copyright (c) Aug 11, 2009 2:33:26 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: chengyao
 * <p>
 * Version: 1.0
 * <p>
 * 
 * @History: Jan 5, 2010 10:34:47 AM luxl@c-platform.com <br>
 *           修改方法getMmsPathPartOfTime： 路径改为yyyymmdd/hhmm/
 */
public class MmsUtils {

	private static final Log logger = LogFactory.getLog(MmsUtils.class);

	/** 单个图片文件允许上传的最大尺寸 */
	public static final int MAX_PIC_FILE_SIZE = 2 * 1024 * 1024;

	/** 单个声音文件允许上传的最大尺寸 */
	public static final int MAX_SOUND_FILE_SIZE = 80 * 1024;

	public static final String SEPARATOR = "/";

	/** 单个文件临时保存目录: singleupload/ */
	public static String UPLOAD_TEMP_PATH = "singleupload/";

	/**
	 * 清除目录中的所有文件
	 * 
	 * @param dir
	 *            目录
	 * @throws IOException
	 *             任何文件操作异常
	 */
	private static void cleanDir(File dir) throws IOException {
		File[] files = dir.listFiles();
		if (files != null) {
			for (int i = 0; i < files.length; i++) {
				File file = files[i];
				FileUtils.forceDelete(file);
			}
		}
	}

	/**
	 * 拷贝中转目录中的所有文件到彩信目录下
	 * 
	 * @param hubDir
	 *            中转目录
	 * @param dstDir
	 *            最终存放彩信的目录
	 * @throws IOException
	 *             任何文件操作异常
	 */
	private static void copyMmsFileToDst(File hubDir, File dstDir) throws IOException {
		FileUtils.forceMkdir(dstDir);
		cleanDir(dstDir);
		FileUtils.copyDirectory(hubDir, dstDir);
	}

	/**
	 * 拷贝帧对象中所指文件到中转地址，并生成smil文件
	 * 
	 * @param frames
	 *            帧对象
	 * @param hubPath
	 *            中转地址
	 * @param smilName
	 *            smil文件名
	 * @return 文件总大小
	 * @throws IOException
	 *             任何操作异常
	 */
	private static int copyMmsFileToHub(Collection<Frame> frames, String hubPath, String mmsPath, String smilName) throws IOException {
		FileUtils.forceMkdir(new File(hubPath));
		int size = 0;
		for (Frame frame : frames) {
			File src = null;
			File dst = null;
			if (frame.getPic() != null) {
				src = new File(mmsPath + frame.getPic().getPath());
				dst = new File(hubPath + frame.getPic().getName());
				FileUtils.copyFile(src, dst);
				size += dst.length();
			}
			if (frame.getSound() != null) {
				src = new File(mmsPath + frame.getSound().getPath());
				dst = new File(hubPath + frame.getSound().getName());
				FileUtils.copyFile(src, dst);
				size += dst.length();
			}
			if (frame.getText() != null && StringUtils.isNotBlank(frame.getText().getValue())) {
				src = new File(mmsPath + frame.getText().getPath());
				dst = new File(hubPath + frame.getText().getName());
				FileUtils.copyFile(src, dst);
				size += dst.length();
			}
		}
		FileUtils.writeStringToFile(new File(hubPath + smilName), SmilHelper.makeSmilContent(frames), "gbk");
		return size;
	}

	/**
	 * 生成彩信文件至中转目录
	 * 
	 * @param frames
	 * @param mmsFileAttr
	 * @return
	 * @throws IOException
	 */
	private static int copyMmsFileToHub(Collection<Frame> frames, Map<String, String> mmsFileAttr) throws IOException {
		String mmsPath = mmsFileAttr.get("mmsPath");// 彩信目录
		String hubPath = mmsFileAttr.get("hubPath");// 中转目录
		String bakPath = mmsFileAttr.get("bakPath");// 备份目录
		String smilName = mmsFileAttr.get("smilName");// 彩信文件名

		FileUtils.forceMkdir(new File(hubPath));
		int size = 0;
		int frameIdx = 1;// 帧序号
		int fileIdx = 1;// 帧文件序号
		String fileFormatName = "";
		for (Frame frame : frames) {
			File src = null;
			File dst = null;
			if (frame.getPic() != null) {
				src = new File(mmsPath + frame.getPic().getPath());
				fileFormatName = "I_" + frameIdx + "_" + (fileIdx++) + getFileExt(frame.getPic().getName());
				dst = new File(hubPath + fileFormatName);
				frame.getPic().setName(fileFormatName);
				if (src.exists()) {
					// 新传文件在临时目录找
					FileUtils.copyFile(src, dst);
				} else {
					// 原文件在备份目录找
					src = new File(bakPath + frame.getPic().getName());
					FileUtils.copyFile(src, dst);
				}
				size += dst.length();
			}
			if (frame.getSound() != null) {
				src = new File(mmsPath + frame.getSound().getPath());
				fileFormatName = "A_" + frameIdx + "_" + (fileIdx++) + getFileExt(frame.getSound().getName());
				dst = new File(hubPath + fileFormatName);
				frame.getSound().setName(fileFormatName);
				if (src.exists()) {
					// 新传文件在临时目录找
					FileUtils.copyFile(src, dst);
				} else {
					// 原文件在备份目录找
					src = new File(bakPath + frame.getSound().getName());
					FileUtils.copyFile(src, dst);
				}
				size += dst.length();
			}
			if (frame.getText() != null && StringUtils.isNotBlank(frame.getText().getValue())) {
				src = new File(mmsPath + frame.getText().getPath());
				fileFormatName = "T_" + frameIdx + "_" + (fileIdx++) + getFileExt(frame.getText().getName());
				dst = new File(hubPath + fileFormatName);
				frame.getText().setName(fileFormatName);
				FileUtils.copyFile(src, dst);
				size += dst.length();
			}
			frameIdx++;
			fileIdx = 1;
		}
		FileUtils.writeStringToFile(new File(hubPath + smilName), SmilHelper.makeSmilContent(frames), "gbk");
		return size;
	}

	/**
	 * 根据Collection<Frame>对象生成其中的text文件,并更新对象本身
	 * 
	 * @param frame
	 *            彩信帧列表对象
	 * @throws IOException
	 *             文件不能正常写入
	 */
	public static void createFrameTextFile(Collection<Frame> frames, Map mmsFileAttribute) throws IOException {
		String relativePath = UPLOAD_TEMP_PATH + DateUtils.getDateByPattern("yyMMdd") + "/";
		String path = mmsFileAttribute.get("mmsPath") + relativePath;
		FileUtils.forceMkdir(new File(path));

		for (Frame frame : frames) {
			if (frame.getText() == null) {
				continue;
			}

			if (StringUtils.isBlank(frame.getText().getValue())) {
				continue;
			}

			String name = RandomStringUtils.randomAlphabetic(10) + ".txt";
			File file = new File(path + name);

			FileUtils.writeStringToFile(file, frame.getText().getValue(), "gbk");
			frame.getText().setName(name);
			frame.getText().setPath(relativePath + name);
		}
	}

	/**
	 * 获得彩信中包含的首张图片，用于保存在彩信的content字段中
	 * 
	 * @param frames
	 *            彩信帧列表对象
	 * @return 如果不存在图片返回空字符
	 */
	public static String getFirstPic(Collection<Frame> frames) {
		for (Frame frame : frames) {
			if (frame.getPic() != null) {
				return frame.getPic().getName();
			}
		}
		return "--------";
	}

	/**
	 * 获得彩信附件类型 type 0-无附件/1－只有图片/2－只有声音/3-有图片和声音
	 * 
	 * @param frames
	 * @return
	 */
	public static int getAttachmentType(Collection<Frame> frames) {
		int type = 0;
		for (Frame frame : frames) {
			if (frame.getPic() != null) {
				if (type == 0) {
					type = 1;
				} else if (type == 2) {
					type = 3;
					break;
				}
			}
			if (frame.getSound() != null) {
				if (type == 0) {
					type = 2;
				} else if (type == 1) {
					type = 3;
					break;
				}
			}
		}
		return type;
	}

	/**
	 * 根据彩信对象生成帧列表对象
	 * 
	 * @param mms
	 *            彩信po对象
	 * @return 帧列表
	 * @throws Exception
	 */
	public static List<Frame> getFrames(ContMms mms, String mmsPath, String defaultMmsSize) throws Exception {
		String resultPath = mms.getContentPath() + "/" + defaultMmsSize + "/";
		String fullPath = mmsPath + resultPath;

		Collection<File> smils = FileUtils.listFiles(new File(fullPath), new String[] { "smil" }, false);

		File smilFile;
		if (smils.isEmpty()) {
			throw new ParseFrameException("smil文件未找到");
		} else {
			smilFile = smils.iterator().next();
		}

		// 解析smil文件
		Document doc = null;
		try {
			doc = new SAXBuilder().build(smilFile);
		}
		catch (Exception ex) {
			throw new ParseFrameException("smil文件解析出错");
		}

		Element root = doc.getRootElement();
		Element body = doc.getRootElement().getChild("body", root.getNamespace());

		// 帧列表
		List<Element> pars = body.getChildren("par", body.getNamespace());

		List<Frame> frames = new ArrayList<Frame>();
		for (Element par : pars) {
			Frame frame = new Frame();

			// 设置帧显示的时间
			String dur = par.getAttributeValue("dur");
			dur = dur.replaceAll("s", "");
			frame.setShowtime(NumberUtils.toInt(dur, 10));

			List<Element> elms = par.getChildren();
			for (Element elm : elms) {

				// 帧中文本元素的获取
				if (elm.getName().equalsIgnoreCase("text")) {
					String path = fullPath + elm.getAttributeValue("src");
					String value = FileUtils.readFileToString(new File(path), "gbk");
					Text text = new Text();
					text.setName(elm.getAttributeValue("src"));
					text.setPath(resultPath + elm.getAttributeValue("src"));
					text.setValue(value);
					frame.setText(text);
				}

				// 帧中图片元素的获取
				if (elm.getName().equalsIgnoreCase("img")) {
					String path = fullPath + elm.getAttributeValue("src");
					Pic pic = new Pic();
					int[] wh = MmsUtils.getWh(path);
					pic.setName(elm.getAttributeValue("src"));
					pic.setPath(resultPath + elm.getAttributeValue("src"));
					pic.setSize(Long.valueOf(new File(path).length()).intValue());
					pic.setW(wh[0]);
					pic.setH(wh[1]);
					frame.setPic(pic);
				}

				// 帧中声音元素的获取
				if (elm.getName().equalsIgnoreCase("audio")) {
					String path = fullPath + elm.getAttributeValue("src");
					Sound sound = new Sound();
					sound.setName(elm.getAttributeValue("src"));
					sound.setPath(resultPath + elm.getAttributeValue("src"));
					sound.setSize(Long.valueOf(new File(path).length()).intValue());
					frame.setSound(sound);
				}
			}
			frames.add(frame);
		}
		if (frames.size() == 0) {
			throw new ParseFrameException("空的彩信文件");
		}
		return frames;
	}

	/**
	 * 获取彩信保存路径中时间部分的字符串
	 * 
	 * @History: Jan 5, 2010 10:34:19 AM luxl@c-platform.com <br>
	 *           路径改为yyyymmdd/hhmm/
	 */
	public static String getMmsPathPartOfTime() {
		Date date = new Date();
		String str = DateUtils.getDateByPattern("yyyyMMdd", date) + SEPARATOR + DateUtils.getDateByPattern("HHmm", date) + SEPARATOR;
		return str;
	}

	/**
	 * 获得给定文件的宽高
	 * 
	 * @param file
	 *            文件全路径
	 * @return 宽高数组[width, height]
	 */
	public static int[] getWh(String file) {
		int width = -1;
		int height = -1;
		try {
			Image src = ImageIO.read(new File(file));
			if (src != null) {
				width = src.getWidth(null);
				height = src.getHeight(null);
			}
		}
		catch (IOException e) {
			logger.debug("file not found or not a valid image : " + file);
		}
		return new int[] { width, height };
	}

	/**
	 * @param frames
	 *            帧列表对象
	 * @param mmsFileAttr
	 *            彩信文件相关属性
	 * @param json
	 *            彩信编辑结果
	 * @return 彩信编辑结果
	 * @throws IOException
	 *             任何文件操作异常
	 */
	public static int saveAllMmsFile(Collection<Frame> frames, Map<String, String> mmsFileAttr, Map<String, Object> json) throws IOException {
		/** 文件状态 true-编辑状态 false-新增状态 */
		boolean flag = Boolean.getBoolean(mmsFileAttr.get("flag"));

		char separatorChar = File.separatorChar;// 路径分割符

		/** 以下是彩信最后目录信息 */
		String mmsPath = mmsFileAttr.get("mmsPath");// 彩信根目录
		String contentPath = mmsFileAttr.get("contentPath");// 彩信相对目录
		String mmsDefaultSize = mmsFileAttr.get("mmsDefaultSize");// 彩信规格
		long mmsMaxSize = Long.parseLong(mmsFileAttr.get("mmsMaxSize")); // 彩信默认大小上限
		File dstDir = new File(getDstPath(mmsPath, contentPath, mmsDefaultSize, separatorChar));// 彩信最后保存目录

		/** 以下是中转目录信息 */
		String t = "_" + RandomStringUtils.randomAlphabetic(10) + "/";// 中转随机文件夹
		String today = DateUtils.getDateByPattern("yyMMdd");// 系统当前日期yyMMdd
		// /data/mms/singleupload/yyMMdd/_<random>/
		String hubPath = mmsPath + UPLOAD_TEMP_PATH + today + "/" + t;// 中转目录
		mmsFileAttr.put("hubPath", hubPath);

		int size = 0;// 彩信文件大小
		// 如果是编辑彩信
		File bakDir = null;
		if (flag) {
			/** 以下是彩信文件备份目录信息 */
			String bakPath = getBakPath(mmsPath, contentPath, mmsDefaultSize, today, separatorChar);
			mmsFileAttr.put("bakPath", bakPath);
			bakDir = new File(bakPath);

			// 第一步先备份彩信内容
			FileUtils.copyDirectory(dstDir, bakDir);

			// 第二步删除文件
			cleanDir(dstDir);
		}

		// 将新上传文件和原来未删除的文件考入中转目录
		size = copyMmsFileToHub(frames, mmsFileAttr);

		if (mmsMaxSize >= size) {
			// 上传彩信小于最大值,将中转目录中的彩信生成文件拷入彩信最后目录
			copyMmsFileToDst(new File(hubPath), dstDir);

			json.put("success", Boolean.TRUE);
			json.put("frames", frames);
		} else {
			// 上传彩信大于最大值
			json.put("success", Boolean.FALSE);
			json.put("error_msg", "彩信文件大小总和超过最大值");

			// 如果是编辑彩信
			if (flag) {
				copyMmsFileToDst(bakDir, dstDir);// 将备份文件重写拷入原目录中
			}
		}
		return size;
	}

	/**
	 * 获得彩信备份目录
	 * 
	 * @param mmsPath
	 * @param contentPath
	 * @param mmsDefaultSize
	 * @param today
	 * @param separatorChar
	 * @return
	 */
	private static String getBakPath(String mmsPath, String contentPath, String mmsDefaultSize, String today, char separatorChar) {
		return mmsPath + separatorChar + "bak" + File.separatorChar + today + File.separatorChar + contentPath + File.separatorChar + mmsDefaultSize
		        + separatorChar;
	}

	/**
	 * 获得彩信最后保存目录
	 * 
	 * @param mmsPath
	 *            根目录
	 * @param contentPath
	 *            相对目录
	 * @param mmsDefaultSize
	 *            默认规格
	 * @param separatorChar
	 *            文件分隔符
	 * @return
	 */
	private static String getDstPath(String mmsPath, String contentPath, String mmsDefaultSize, char separatorChar) {
		return mmsPath + contentPath + separatorChar + mmsDefaultSize + separatorChar;
	}

	/**
	 * 获得文件名后缀
	 * 
	 * @param fileName
	 * @return
	 */
	private static String getFileExt(String fileName) {
		return fileName.substring(fileName.lastIndexOf("."));
	}
}
