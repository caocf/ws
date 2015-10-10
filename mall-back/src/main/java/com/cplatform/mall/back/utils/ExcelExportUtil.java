package com.cplatform.mall.back.utils;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import jxl.Workbook;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;

/**
 * 导出excel数据
 * */
public class ExcelExportUtil {
	
	
	/**
	 * @param conn 数据库连接
	 * @param head excel文件头（第一行中文）
	 * @param filename 文件名头
	 * @param sql 查询语句
	 * @param request
	 * @param response
	 * @throws Exception 
	 */
	public static void exportExcel(Connection conn,String[] head,String filename, String sql,HttpServletRequest request,HttpServletResponse response) throws Exception {
		WritableWorkbook book = null;
		BufferedInputStream bis = null;
		BufferedOutputStream bos = null;
		Date date = new Date();
		SimpleDateFormat format = new SimpleDateFormat("yyyyMMddHHmmss");
		String time = format.format(date);
		String dir = request.getSession().getServletContext().getRealPath("") +File.separator;
		String fileName =filename + time + ".xls";
		File file = new File(dir,fileName);
		try {
			PreparedStatement preStmt = conn.prepareStatement(sql);
			ResultSet rs = preStmt.executeQuery();
			book = Workbook.createWorkbook(file);
			WritableSheet sheet = book.createSheet("第一页", 0);
			for (int i = 0; i < head.length; i++) {
				Label label = new Label(i, 0, head[i]);
				sheet.addCell(label);
			}
			int j = 1;
			while (rs.next()) {
				for (int i = 0; i < head.length; i++) {
					Label label = new Label(i, j, rs.getString(i + 1));
					sheet.addCell(label);
				}
				j++;
			}
			book.write();
			book.close();
		       response.setContentType("text/plain; charset=utf-8");  
		        response.setHeader("Content-disposition", "attachment; filename="  
		                + new String(fileName.getBytes("utf-8"), "ISO8859-1"));  
			bis = new BufferedInputStream(new FileInputStream(dir+fileName));
			bos = new BufferedOutputStream(response.getOutputStream());
			byte[] buff = new byte[1024];
			int bytesRead;
			while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
				bos.write(buff, 0, bytesRead);
			}
		} catch (Exception e) {
			throw e;

		} finally {
			try {
				if(null != conn){
					conn.close();
				}
				if(null != bis){
					bis.close();
				}
				if(null != bos){
					bos.close();
				}
				if (file.exists()) {
					file.delete();
				}
			} catch (Exception e) {
				throw e;

			}
		}
	}
}
