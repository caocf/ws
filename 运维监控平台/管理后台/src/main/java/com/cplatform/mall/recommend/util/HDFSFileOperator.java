package com.cplatform.mall.recommend.util;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.URI;
import java.util.Enumeration;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileStatus;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.log4j.Logger;

/**
 * HDFS文件操作类. <br> .
 * <p>
 * Copyright: Copyright (c) 2011-12-16 1:44:48
 * <p>
 * Company: 宽连十方数字技术有限公司
 * <p>
 * 
 * @author xudb@c-platform.com
 * @version 1.0.0
 */
public class HDFSFileOperator implements IFileOperator {

	public String[] listFiles(String path) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			FileStatus fileStatus[] = fs.listStatus(new Path(path));
			if (fileStatus == null)
				return new String[] {};
			String filsName[] = new String[fileStatus.length];
			for (int i = 0; i < fileStatus.length; i++)
				filsName[i] = fileStatus[i].getPath().getName();
			return filsName;
		}
		catch (Exception e) {
			log.error("HDFSFileOperator listFiles error!" + e.getMessage(), e);
			return new String[] {};
		}
	}

	public String getTextFileContent(String pathFile) {
		BufferedReader is = null;
		try {
			FileSystem fs = FileSystem.get(getConfiguration());

			is = new BufferedReader(new InputStreamReader(fs.open(new Path(pathFile))));
			String line = is.readLine();
			StringBuilder sb = new StringBuilder();
			while (line != null) {
				sb.append(line).append("\n");
				line = is.readLine();
			}
			return sb.toString();
		}
		catch (IOException e) {
			log.error("HDFSFileOperator getTextFileContent(" + pathFile + ") error!" + e.getMessage(), e);
			return null;
		}
		finally {
			if (is != null)
				try {
					is.close();
				}
				catch (Exception e) {
				}
		}

	}

	public long fileSize(String pathFile) {
		try {
			FileSystem fs = FileSystem.get(URI.create(pathFile), getConfiguration());
			return fs.getFileStatus(new Path(pathFile)).getLen();
		}
		catch (IOException e) {
			log.error("HDFSFileOperator fileSize error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return -1;
	}

	public boolean makeDirs(String path) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			Path p = new Path(path);
			if (fs.exists(p))
				return true;
			return fs.mkdirs(p);
		}
		catch (IOException e) {
			log.error("HDFSFileOperator makeDirs error!" + path + "!!" + e.getMessage(), e);
		}
		return false;
	}

	public boolean isFile(String pathFile) {
		try {
			FileSystem fs = FileSystem.get(URI.create(pathFile), getConfiguration());
			return fs.isFile(new Path(pathFile));
		}
		catch (IOException e) {
			log.error("HDFSFileOperator isExsist error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return false;

	}

	public boolean isDirectory(String pathFile) {

		return !isFile(pathFile);
	}

	private static Configuration conf = null;

	public synchronized Configuration getConfiguration() {

		if (conf == null) {

			conf = new Configuration(true);

			java.util.Properties prop = new java.util.Properties();

			try {
				prop.load(this.getClass().getResourceAsStream("/hdfs.properties"));
			}
			catch (Exception e) {
				log.error("HDFSFileOperator getConfiguration error!" + e.getMessage(), e);
			}

			Enumeration<Object> enum1 = prop.keys();
			while (enum1.hasMoreElements()) {
				String key = enum1.nextElement().toString();
				String[] value = prop.getProperty(key).toString().split(",");

				if (value.length == 2)
					conf.setStrings(key, value[0], value[1]);
				else
					conf.setStrings(key, value[0]);
			}

		}
		return conf;
	}

	public boolean createNewFile(String pathFile) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			return fs.createNewFile(new Path(pathFile));
		}
		catch (IOException e) {
			log.error("HDFSFileOperator createNewFile error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return false;
	}

	public InputStream openInputStream(String pathFile) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			return fs.open(new Path(pathFile));
		}
		catch (IOException e) {
			log.error("HDFSFileOperator openInputStream error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return null;
	}

	public OutputStream openOutputStream(String pathFile, long blockSize) {
		try {

			// io.bytes.per.checksum
			/* blockSize = ((int) (blockSize / 512) + 1) * 512; */

			Configuration con = getConfiguration();
			// con.set("dfs.block.size", String.valueOf(blockSize));
			FileSystem fs = FileSystem.get(con);

			// String s = new Path(pathFile).toString();
			// return fs.create(new Path(pathFile), true);
			return fs.create(new Path(pathFile), true, 2048, fs.getDefaultReplication(), fs.getDefaultBlockSize());
			// return fs.create(new Path(pathFile), true, 2048,
			// fs.getDefaultReplication(), blockSize);
		}
		catch (IOException e) {
			log.error("HDFSFileOperator openInputStream error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return null;
	}

	public OutputStream openOutputStream(String pathFile) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			return fs.create(new Path(pathFile), true);
		}
		catch (IOException e) {
			log.error("HDFSFileOperator openInputStream error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return null;
	}

	public OutputStream openOutputStreamForAppend(String pathFile) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			return fs.create(new Path(pathFile), false);
		}
		catch (IOException e) {
			log.error("HDFSFileOperator openInputStream error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return null;
	}

	public boolean isExsist(String pathFile) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			return fs.exists(new Path(pathFile));
		}
		catch (IOException e) {
			log.error("HDFSFileOperator isExsist error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return false;
	}

	public long getModificationTime(String pathFile) {

		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			return fs.getFileStatus(new Path(pathFile)).getModificationTime();
		}
		catch (IOException e) {
			log.error("HDFSFileOperator isExsist error!" + pathFile + "!!" + e.getMessage(), e);
		}
		return -1;
	}

	public boolean delete(String pathFile) {

		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			return fs.delete(new Path(pathFile), true);
		}
		catch (IOException e) {
			log.error("delete file from hdfs error!" + pathFile + "!" + e.getMessage(), e);
		}
		return false;
	}

	public boolean renameTo(String sourceFileName, String targetFileName) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			return fs.rename(new Path(sourceFileName), new Path(targetFileName));
		}
		catch (IOException e) {
			log.error("rename file from " + sourceFileName + " to " + targetFileName + " error!" + e.getMessage(), e);
		}
		return false;
	}

	protected static Logger log = Logger.getLogger(HDFSFileOperator.class);

	public void setLastModified(String path, long time) {
		try {
			FileSystem fs = FileSystem.get(getConfiguration());
			fs.setTimes(new Path(path), time, time);
		}
		catch (IOException e) {
			log.error("setLastModified file error!" + e.getMessage(), e);
		}
	}
}
