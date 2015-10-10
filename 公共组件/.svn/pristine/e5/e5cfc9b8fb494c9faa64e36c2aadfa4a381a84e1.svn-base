package com.cplatform.log;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.apache.log4j.FileAppender;
import org.apache.log4j.helpers.LogLog;
import org.apache.log4j.spi.LoggingEvent;

/**
 * 支持含时间通配符文件名的日志记录器.
 * <p>
 * 支持含时间通配符文件名的日志记录器。<br>
 * %yyyy:4位的年<br>
 * %yy:2位的年<br>
 * %mm:2位的月<br>
 * %dd:2位的日<br>
 * %hh:2位的小时<br>
 * <p>
 * Log4J配置（xml)
 * 
 * <pre>
 * 	&lt;appender name="INFO" class="<b>com.cplatform.log.DatedFileAppender</b>"&gt;
 * 		&lt;param name="File" value="log/%yyyy%mm/%dd/info" /&gt;
 * 		&lt;param name="Append" value="true" /&gt;
 * 		&lt;param name="BufferedIO" value="false" /&gt;
 * 		&lt;layout class="org.apache.log4j.PatternLayout"&gt;
 * 			&lt;param name="ConversionPattern" value="%d %p %c %t %m%n" /&gt;
 * 		&lt;/layout&gt;
 * 	&lt;/appender&gt;
 * </pre>
 * <p>
 * Copyright: Copyright (c) 2007-2-25 上午7:19:51
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author chengfan@c-platform.com
 * @version 1.0.0
 */
public class DatedFileAppender extends FileAppender {

	/** CHECK_INTERVAL */
	private static final int CHECK_INTERVAL = 1000 * 2;

	/** 下一次检测文件的时间(毫秒) */
	private long nextCheck = 0;

	/** 初始的文件名 */
	private String originFilename = null;

	/**
	 * @return 替换时间变量的文件名
	 */
	private String getDatedFilename() {
		String str = originFilename;
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time14 = format.format(new Date());
		// CHECKSTYLE:OFF
		str = str.replace("%yyyy", time14.substring(0, 4));
		str = str.replace("%yy", time14.substring(2, 4));
		str = str.replace("%mm", time14.substring(4, 6));
		str = str.replace("%dd", time14.substring(6, 8));
		str = str.replace("%hh", time14.substring(8, 10));
		str = str.replace("%nn", time14.substring(10, 12));
		str = str.replace("%ss", time14.substring(12, 14));
		// CHECKSTYLE:ON
		return str;

	}

	@Override
	public void setFile(String file) {
		originFilename = file;
		super.setFile(getDatedFilename());
	}

	@Override
	protected synchronized void subAppend(LoggingEvent event) {
		long n = System.currentTimeMillis();
		if (n >= nextCheck) {
			nextCheck = n + CHECK_INTERVAL;
			try {
				switchFilename();
			}
			catch (IOException ioe) {
				LogLog.error("switchFilename() failed.", ioe);
				ioe.printStackTrace();
			}
		}
		super.subAppend(event);
	}

	/**
	 * 切换文件
	 * 
	 * @throws IOException
	 *             IOException
	 */
	synchronized void switchFilename() throws IOException {
		String datedFilename = getDatedFilename();
		if (!datedFilename.equals(fileName)) {
			try {
				File dir = new File(datedFilename).getParentFile();
				if (!dir.exists()) {
					dir.mkdirs();
				}
				if (!dir.exists()) {
					return;
				}
				setFile(datedFilename, getAppend(), getBufferedIO(), getBufferSize());
			}
			catch (IOException ex) {
				errorHandler.error("setFile(" + datedFilename + ", false) call failed.");
				ex.printStackTrace();
			}
			this.activateOptions();
			fileName = datedFilename;
		}
	}
}
