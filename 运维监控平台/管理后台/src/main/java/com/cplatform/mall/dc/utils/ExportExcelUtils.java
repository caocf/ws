package com.cplatform.mall.dc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;

/**
 * 导出Excel工具类 <br>
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午6:51:22
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
public class ExportExcelUtils {

	/**
	 * @param fileName
	 *            EXCLE文件名
	 * @param headers
	 *            标题行单元格显示的内容
	 * @param fields
	 *            字段名
	 * @param datas
	 *            数据
	 * @param response
	 *            HttpServletResponse
	 * @throws IOException
	 */
	public static void export(String fileName, String[] headers, String[] fields, List<Map<String, Object>> datas, HttpServletResponse response)
	        throws IOException {
		try {
			// 验证参数
			verifyParams(fileName, headers, fields, datas, response);

			// 创建工作薄
			HSSFWorkbook workbook = createWorkbook(headers, fields, datas);

			// 导出EXCEL
			export(fileName, response, workbook);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * @param fileName
	 *            EXCLE文件名
	 * @param fields
	 *            字段名
	 * @param datas
	 *            数据
	 * @param response
	 *            HttpServletResponse
	 * @throws IOException
	 */
	public static void export(String fileName, String[] fields, List<Map<String, Object>> datas, HttpServletResponse response) throws IOException {
		try {
			// 验证参数
			verifyParams(fileName, fields, datas, response);

			// 创建工作薄
			HSSFWorkbook workbook = createWorkbook(fields, datas);

			// 导出EXCEL
			export(fileName, response, workbook);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}

	}

	/**
	 * @param fileName
	 *            EXCLE文件名
	 * @param headers
	 *            标题行单元格显示的内容
	 * @param fields
	 *            字段名
	 * @param datas
	 *            数据
	 * @param response
	 *            HttpServletResponse
	 */
	private static void verifyParams(String fileName, String[] headers, String[] fields, List<Map<String, Object>> datas, HttpServletResponse response) {
		if (fileName == null) {
			throw new IllegalArgumentException("参数：fileName为空！");
		}

		if (headers == null || headers.length <= 0) {
			throw new IllegalArgumentException("参数：headers为空！");
		}

		if (fields == null || fields.length <= 0) {
			throw new IllegalArgumentException("参数：fields为空！");
		}

		if (datas == null) {
			throw new IllegalArgumentException("参数：datas为空！");
		}

		if (response == null) {
			throw new IllegalArgumentException("参数：response为空！");
		}

		if (headers.length != fields.length) {
			throw new IllegalArgumentException("参数headers与fields长度必须一致！");
		}

	}

	/**
	 * @param fileName
	 *            EXCLE文件名
	 * @param fields
	 *            字段名
	 * @param datas
	 *            数据
	 * @param response
	 *            HttpServletResponse
	 */
	private static void verifyParams(String fileName, String[] fields, List<Map<String, Object>> datas, HttpServletResponse response) {
		if (fileName == null) {
			throw new IllegalArgumentException("参数：fileName为空！");
		}

		if (fields == null || fields.length <= 0) {
			throw new IllegalArgumentException("参数：fields为空！");
		}

		if (datas == null) {
			throw new IllegalArgumentException("参数：datas为空！");
		}

		if (response == null) {
			throw new IllegalArgumentException("参数：response为空！");
		}
	}

	/**
	 * 创建工作薄
	 * 
	 * @param headers
	 *            标题行单元格显示的内容
	 * @param fields
	 *            字段名
	 * @param datas
	 *            数据
	 * @return 工作薄
	 */
	private static HSSFWorkbook createWorkbook(String[] headers, String[] fields, List<Map<String, Object>> datas) {
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 创建表格
		HSSFSheet sheet = workbook.createSheet("Sheet1");

		// 创建标题行
		HSSFRow header = sheet.createRow(0);
		for (int i = 0; i < headers.length; i++) {
			HSSFCell cell = header.createCell(i);
			cell.setCellValue(headers[i]);
		}

		// 创建数据行
		for (int i = 0; i < datas.size(); i++) {
			HSSFRow row = sheet.createRow(i + 1);
			Map<String, Object> data = datas.get(i);
			for (int j = 0; j < fields.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(StringUtil.getString(data.get(fields[j])));
			}
		}
		return workbook;
	}

	/**
	 * 创建工作薄
	 * 
	 * @param fields
	 *            字段名
	 * @param datas
	 *            数据
	 * @return 工作薄
	 */
	private static HSSFWorkbook createWorkbook(String[] fields, List<Map<String, Object>> datas) {
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 创建表格
		HSSFSheet sheet = workbook.createSheet("Sheet1");

		// 创建数据行
		for (int i = 0; i < datas.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			Map<String, Object> data = datas.get(i);
			for (int j = 0; j < fields.length; j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(StringUtil.getString(data.get(fields[j])));
			}
		}
		return workbook;
	}

	/**
	 * 导出EXCEL
	 * 
	 * @param fileName
	 *            EXCLE文件名
	 * @param response
	 *            HttpServletResponse
	 * @param workbook
	 *            工作薄
	 * @throws FileNotFoundException
	 * @throws IOException
	 * @throws UnsupportedEncodingException
	 */
	private static void export(String fileName, HttpServletResponse response, HSSFWorkbook workbook) throws FileNotFoundException, IOException,
	        UnsupportedEncodingException {
		File file = null;
		FileOutputStream os = null;
		InputStream is = null;
		try {
			// 创建临时文件
			file = new File(System.getProperty("java.io.tmpdir"), RandomStringUtils.randomAlphabetic(32));

			// 创建输出流
			os = new FileOutputStream(file);

			// 将workbook写入输出流
			workbook.write(os);

			// 清空输出流
			os.flush();

			// 创建输入流
			is = FileUtils.openInputStream(file);

			// 设置响应头信息
			response.setContentType("application/vnd.ms-excel");
			response.addHeader("Pragma", "no-cache");
			response.addHeader("Cache-Control", "no-cache");
			response.setHeader("Content-Disposition", "attachment; filename=\"" + new String(fileName.getBytes("gb2312"), "ISO8859-1") + "\"");

			// 导出
			IOUtils.copy(is, response.getOutputStream());
		}
		finally {
			// 关闭输出流
			if (os != null) {
				os.close();
			}
		}
	}

	/**
	 * 导出到EXCEL文件
	 * 
	 * @param fileName
	 *            EXCLE文件名
	 * @param datas
	 *            数据
	 * @param response
	 *            HttpServletResponse
	 */
	public static void export(String fileName, List<List<String>> datas, HttpServletResponse response) throws IOException {
		try {
			// 验证参数
			verifyParams(fileName, datas, response);

			// 创建工作薄
			HSSFWorkbook workbook = createWorkbook(datas);

			// 导出EXCEL
			export(fileName, response, workbook);
		}
		catch (IOException e) {
			e.printStackTrace();
			throw e;
		}
	}

	/**
	 * 验证参数
	 * 
	 * @param fileName
	 *            EXCLE文件名
	 * @param datas
	 *            数据
	 * @param response
	 *            HttpServletResponse
	 */
	private static void verifyParams(String fileName, List<List<String>> datas, HttpServletResponse response) {
		if (fileName == null) {
			throw new IllegalArgumentException("参数：fileName为空！");
		}

		if (datas == null) {
			throw new IllegalArgumentException("参数：datas为空！");
		}

		if (response == null) {
			throw new IllegalArgumentException("参数：response为空！");
		}
	}

	/**
	 * 创建工作薄
	 * 
	 * @param datas
	 *            数据
	 * @return 工作薄
	 */
	private static HSSFWorkbook createWorkbook(List<List<String>> datas) {
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 创建表格
		HSSFSheet sheet = workbook.createSheet("Sheet1");

		// 创建数据行
		for (int i = 0; i < datas.size(); i++) {
			HSSFRow row = sheet.createRow(i);
			for (int j = 0; j < datas.get(i).size(); j++) {
				HSSFCell cell = row.createCell(j);
				cell.setCellValue(StringUtil.getString(datas.get(i).get(j)));
			}
		}
		return workbook;
	}
}
