package com.cplatform.mall.dc.utils;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang.RandomStringUtils;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.ss.usermodel.CellStyle;

public class ExportExcelUtil {

	/**
	 * 以Excel文件形似下载文件
	 * 
	 * @param fileName
	 *            文件名
	 * @param header
	 *            文件内容标题
	 * @param firFields
	 *            文件内容最外一层标题
	 * @param secFields
	 *            文件内容第二层标题
	 * @param datas
	 *            数据
	 * @param response
	 * @throws IOException
	 */
	public static void export(String fileName, String header,
			List<String> firFields, List<String> secFields,
			List<List<String>> datas, HttpServletResponse response)
			throws IOException {
		try {
			// 验证参数
			verifyParams(fileName, datas, response);

			// 创建工作薄
			HSSFWorkbook workbook = createWorkbook(header, firFields,
					secFields, datas);

			// 导出EXCEL
			export(fileName, response, workbook);
		} catch (IOException e) {
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
	private static void verifyParams(String fileName, List<List<String>> datas,
			HttpServletResponse response) {
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
	 * 判断标题存在
	 * @param firFields
	 * @param secFields
	 * @return
	 */
	private static int verifyList(List<String> firFields, List<String> secFields) {
		if (firFields == null || firFields.size() <= 0) {
			if (secFields == null || secFields.size() <= 0) {
				return 1;
			} else {
				return 0;
			}
		} else {
			if (secFields == null || secFields.size() <= 0) {
				return 0;
			} else {
				return -1;
			}
		}
	}
	
	public static void writeTitle(HSSFWorkbook workbook, HSSFSheet sheet , HSSFCell cell, HSSFCell titleCell, String headerTitle, List<String> fields,
			List<List<String>> datas, Map<String, Integer> map, int i, int j, boolean flag){
		int count = 0;
		for (int k = 0; k < fields.size(); k++) {
			if (count > i) {
				break;
			} else {
				if (i == 1 || ((count + 1) == i && i != 0)) {
					int col = Integer.parseInt(fields.get(k).split("_&&_")[1]);
					titleCell.setCellValue(new HSSFRichTextString(StringUtil.getString(fields.get(k).split("_&&_")[0])));
					HSSFCellStyle cellStyle = workbook.createCellStyle();
					cellStyle.setAlignment(HSSFCellStyle.ALIGN_CENTER); // 指定单元格居中对齐
					cellStyle.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);// 指定单元格垂直居中对齐
					cellStyle.setWrapText(true);// 指定单元格自动换行
					titleCell.setCellStyle(cellStyle);
					int lastcol = 0;
					if(flag){
						if(col == 1){
							lastcol = 2;
						}else{
							lastcol = 1;
						}
					}else{
						if(col == 1){
							lastcol = 1;
						}else{
							lastcol = 0;
						}
					}
					// 合并相关单元格
					if (i == 1) {
						if (headerTitle != null && !"".equals(headerTitle)) {
							sheet.addMergedRegion(new CellRangeAddress(2, (1 + Integer.parseInt(fields.get(0).split("_&&_")[1])), (flag?1:0), lastcol));
						} else {
							sheet.addMergedRegion(new CellRangeAddress(1, (Integer.parseInt(fields.get(0).split("_&&_")[1])), (flag?1:0), lastcol));
						}
					} else {
						if (headerTitle != null && !"".equals(headerTitle)) {
							sheet.addMergedRegion(new CellRangeAddress((count + 2), (count + 1 + Integer.parseInt(fields.get(k).split("_&&_")[1])), (flag?1:0), lastcol));
						} else {
							sheet.addMergedRegion(new CellRangeAddress((count + 1), (count + Integer.parseInt(fields.get(k).split("_&&_")[1])), (flag?1:0), lastcol));
						}
					}
					map.put(StringUtil.getString(fields.get(
							k).split("_&&_")[0]), Integer
							.valueOf(fields.get(k).split(
									"_&&_")[1]));
					break;
				}
			}

			count += Integer.parseInt(fields.get(k).split("_&&_")[1]);
		}
		if (cell.getColumnIndex() == 1
				&& !map.isEmpty()
				&& map.get(StringUtil.getString(datas.get(i)
						.get(j))) != null
				&& map.get(StringUtil.getString(datas.get(i)
						.get(j))) == 1) {
		} else {
			cell.setCellValue(StringUtil.getString(datas.get(i)
					.get(j)));
		}
	}

	/**
	 * 创建工作薄
	 * 
	 * @param datas
	 *            数据
	 * @return 工作薄
	 */
	private static HSSFWorkbook createWorkbook(String headerTitle,
			List<String> firFields, List<String> secFields,
			List<List<String>> datas) {
		// 创建工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();

		// 创建表格
		HSSFSheet sheet = workbook.createSheet("Sheet1");

		Map<String, Integer> map = new HashMap<String, Integer>();

		// 创建标题行
		if (headerTitle != null && !"".equals(headerTitle)) {
			int num = verifyList(firFields, secFields);
			sheet.addMergedRegion(new CellRangeAddress(0, 0, 0, (datas.get(0)
					.size() - num)));

			HSSFRow header = sheet.createRow(0);
			HSSFCell cell = header.createCell(0);
			cell.setCellValue(headerTitle);
			// 合并相关单元格
			CellStyle style = workbook.createCellStyle();
			style.setAlignment(CellStyle.ALIGN_CENTER);
			cell.setCellStyle(style);
		}

		// 创建数据行
		int i_index = 0;
		//列开始标识
		int startindex = 0;
		boolean flag = false;
		for (int i = 0; i < datas.size(); i++) {
			if (headerTitle != null && !"".equals(headerTitle)) {
				i_index = i_index + 1;
			}
			HSSFRow row = sheet.createRow(i_index);
			if (headerTitle == null || "".equals(headerTitle)) {
				i_index = i_index + 1;
			}
			if(firFields != null && firFields.size() > 0){
				startindex = 1;
				flag = true;
			}
			if (secFields != null && secFields.size() > 0) {
				HSSFCell secCell = row.createCell(startindex);

				for (int j = 0; j < datas.get(i).size(); j++) {
					HSSFCell cell = row.createCell(j + 1 + startindex);
					if (i == 0 && j == 0) {
						cell.setCellValue("");
					} else {
						if(firFields != null && firFields.size() > 0){
							//第一节标题
							HSSFCell firCell = row.createCell(0);
							writeTitle(workbook, sheet, cell, firCell, headerTitle, firFields, datas, map, i, j, false);
						}
						writeTitle(workbook, sheet, cell, secCell, headerTitle, secFields, datas, map, i, j, flag);
					}
				}
			} else {
				for (int j = 0; j < datas.get(i).size(); j++) {
					HSSFCell cell = row.createCell(j);
					if (i == 0 && j == 0) {
						cell.setCellValue("");
					} else {
						cell.setCellValue(StringUtil.getString(datas.get(i)
								.get(j)));
					}
				}
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
	private static void export(String fileName, HttpServletResponse response,
			HSSFWorkbook workbook) throws FileNotFoundException, IOException,
			UnsupportedEncodingException {
		File file = null;
		FileOutputStream os = null;
		InputStream is = null;
		try {
			// 创建临时文件
			file = new File(System.getProperty("java.io.tmpdir"),
					RandomStringUtils.randomAlphabetic(32));

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
			response.setHeader("Content-Disposition", "attachment; filename=\""
					+ new String(fileName.getBytes("gb2312"), "ISO8859-1")
					+ "\"");

			// 导出
			IOUtils.copy(is, response.getOutputStream());
		} finally {
			// 关闭输出流
			if (os != null) {
				os.close();
			}
		}
	}
}
