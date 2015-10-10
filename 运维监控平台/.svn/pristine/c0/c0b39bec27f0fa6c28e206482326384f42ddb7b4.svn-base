package com.cplatform.mall.recommend.util;

import java.io.InputStream;
import java.io.OutputStream;

/**
 * �?��文件操作的接�? <br>
 * 方便切换hdfs或�?本地硬盘.
 * <p>
 * Copyright: Copyright (c) 2011-12-19 上午10:10:58
 * <p>
 * Company: 北京宽连十方数字�?��有限公司
 * <p>
 * 
 * @author xudb@c-platform.com
 * @version 1.0.0
 */
public interface IFileOperator {

	// 文件是否存在
	public boolean isExsist(String pathFile);

	// 文件修改时间
	public long getModificationTime(String pathFile);

	// 删除文件
	public boolean delete(String pathFile);

	// 重新命名文件
	public boolean renameTo(String sourceFileName, String targetFileName);

	// 创建文件
	public boolean createNewFile(String pathFile);

	// 打开读文件输入流
	public InputStream openInputStream(String pathFile);

	// 打开写文件输出流
	public OutputStream openOutputStream(String pathFile);

	// 打开写文件输出流
	public OutputStream openOutputStreamForAppend(String pathFile);

	// 是否是文�?
	public boolean isFile(String pathFile);

	// 是否是目�?
	public boolean isDirectory(String pathFile);

	// 创建目录
	public boolean makeDirs(String path);

	// 文件大小
	public long fileSize(String pathFile);

	// 读文本文件内�?
	public String getTextFileContent(String pathFile);

	// 列出目录下文件列�?返回文件�?�?路径
	public String[] listFiles(String path);

	// 设置文件�?��修改时间
	public void setLastModified(String path, long time);
}
