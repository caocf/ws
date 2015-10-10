package com.cplatform.b2c.service;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;

import javax.xml.transform.Result;
import javax.xml.transform.Source;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.stream.StreamSource;

import org.apache.commons.httpclient.Header;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.HttpException;
import org.apache.commons.httpclient.HttpStatus;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * 物流订单查询操作.
 * <p>
 * Copyright: Copyright (c) 2013-6-1 上午10:12:16
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author 季霁
 * @version 1.0.0
 */
@Service
@Transactional
public class WayBillService {

	/** 日志记录器 */
	private final Logger logger = Logger.getLogger(getClass());

	/**
	 * 通过第三方接口获取查询结果
	 * 
	 * @param nu
	 * @param companyName
	 * @throws IOException
	 * @throws HttpException
	 */
	public InputStream getResult(String nu, String companyName) throws IOException, HttpException {
		HttpClient httpClient = new HttpClient();
		// String url =
		// "http://api.kuaidi100.com/api?id=524afc31dbf23d67&com=huitongkuaidi&nu=210090644209&show=1&muti=1&order=desc";
		String url = "http://api.kuaidi100.com/api?id=524afc31dbf23d67&com=" + companyName + "&nu=" + nu + "&show=1&muti=1&order=desc";
		PostMethod postMethod = new PostMethod(url);
		// 执行postMethod
		int statusCode = httpClient.executeMethod(postMethod);

		logger.info("~~~~~~~~~~~~~statusCode:" + statusCode);
		if (statusCode == HttpStatus.SC_OK) {
			InputStream result = postMethod.getResponseBodyAsStream();
			return result;
		} else if (statusCode == HttpStatus.SC_MOVED_PERMANENTLY || statusCode == HttpStatus.SC_MOVED_TEMPORARILY) {
			// 从头中取出转向的地址
			Header locationHeader = postMethod.getResponseHeader("location");
			String location = null;
			if (locationHeader != null) {
				location = locationHeader.getValue();
				logger.error("The page was redirected to:" + location);
			} else {
				logger.error("Location field value is null.");
			}
		}
		return null;
	}

	/**
	 * @param args
	 */
	public Result transform(InputStream xmlFileStream, String xslFileName, PrintWriter htmlFileStream) {

		Result result = null;
		try {
			TransformerFactory tFac = TransformerFactory.newInstance();
			Source xslSource = new StreamSource(xslFileName);
			Transformer t = tFac.newTransformer(xslSource);
			Source source = new StreamSource(xmlFileStream);
			result = new StreamResult(htmlFileStream);
			t.transform(source, result);
		}
		catch (TransformerConfigurationException e) {
			e.printStackTrace();
		}
		catch (TransformerException e) {
			e.printStackTrace();
		}
		finally {
			try {
				htmlFileStream.close();
			}
			catch (Exception e) {
				logger.info("PrintWriter out close error" + e);
			}
		}
		return result;
	}

}
