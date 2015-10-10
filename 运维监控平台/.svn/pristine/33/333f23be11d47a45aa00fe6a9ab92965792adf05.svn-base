package com.cplatform.mall.recommend.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.apache.commons.logging.LogFactory;



import sun.misc.BASE64Decoder;

@SuppressWarnings("restriction")
public class DownHdfsUtils extends HttpServlet{

	/** serialVersionUID */
	private static final long serialVersionUID = 1L;

	private static final String CONTENT_TYPE = "text/html; charset=UTF-8";


	// Clean up resources
	@Override
	public void destroy() {
	}

	// Process the HTTP Get request
	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String filePath = request.getParameter("filePath"); // 取得文件路径,hdfs的绝对路径，如：/user/hive/warehouse/keyword_search_result
		String saveName = null; // 文件保存在本地的名字
		String symbol = request.getParameter("symbol");// 区别客户筛选和定向跟踪的标识；
		if(symbol!=null&&symbol.trim().equals("1")){
			saveName = "商户分析用户导出";
		}else if(symbol!=null&&symbol.trim().equals("2")){
			saveName = "商品分析用户导出";
		}else if(symbol!=null&&symbol.trim().equals("3")){
			saveName = "用户筛选导出";
		}else if(symbol!=null&&symbol.trim().equals("4")){
			saveName = "非电商用户筛选导出";
		}else if(symbol!=null&&symbol.trim().equals("9")){
			saveName = "位置分析用户筛选导出";
		}
		
		String title="";
		if(symbol.equals("3")){
			title = "任务ID,手机号码";
			
		}else if(symbol.equals("9")){
			title = "手机号码,商城币,积分";
		}else{
			title = "手机号码";
		}
			
		
		//String splitChar = "\001";

		if (filePath != null) {


			HDFSFileOperator hdo = new HDFSFileOperator();
			String[] file = hdo.listFiles(filePath);

			if (file.length > 0) {
				response.reset();

				response.setContentType("application/x-msdownload"); // 这里设成
				// */*
				// 是因为可以让浏览器根据文件的后辍来自动选择一种文件类型

				String fn = URLEncoder.encode(saveName, "UTF-8") + ".csv";
				if (fn.length() > 150)// 解决IE 6.0 bug
					fn = new String(saveName.getBytes("GBK"), "ISO-8859-1") + ".csv";

				response.setHeader("Content-disposition", "attachment; filename=" + fn);

				BufferedWriter bw = null;

				
				java.io.BufferedReader br = null;
				try {
					for (String fileName : file) {
						bw = new BufferedWriter(new OutputStreamWriter(response.getOutputStream(), "GBK"));
						br = new BufferedReader(new InputStreamReader(hdo.openInputStream(filePath + "/" + fileName), "UTF-8"), 5 * 1024 * 1024);
						bw.write(title);
						bw.newLine();
						String line = br.readLine();
						if(!symbol.equals("3")&&!symbol.equals("9")){
							while (line != null) {
								bw.write(line+"\n");
								bw.newLine();
								line = br.readLine();
							}
						}else{
							while (line != null) {
								String row[] = StringUtils.splitPreserveAllTokens(line, "\t");
								for (int i = 0; i < row.length; i++) {
									if(i<row.length-1){
										bw.write(row[i]+",");
									}else{
										bw.write(row[i]);
									}
									
								}
								bw.newLine();
								line = br.readLine();
							}
						}
						bw.flush();
					}
				}
				catch (Exception e) {
//					if (log.isInfoEnabled()) {
//						log.info("下载文件出错：" + e.getMessage());
//					}
				}
				finally {
					bw.close();
					br.close();
				}

			} else {
				response.setContentType(CONTENT_TYPE);
				PrintWriter out = response.getWriter();
				out.println("<html>");
				out.println("<head><title>FileDown</title></head>");
				out.println("<body bgcolor=\"#ffffff\">");
				out.println("<script>alert('文件不存在，下载失败！');window.close();</script>");
				out.println("</body></html>");
			}
			// file.delete();//下载后删除源文件
		} else {

			response.setContentType(CONTENT_TYPE);
			PrintWriter out = response.getWriter();
			out.println("<html>");
			out.println("<head><title>FileDown</title></head>");
			out.println("<body bgcolor=\"#ffffff\">");
			out.println("<script>alert('文件参数不全，下载失败！');window.close();</script>");
			out.println("</body></html>");
		}
		
	}

	@Override
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

	// Initialize global variables
	@Override
	public void init() throws ServletException {
	}


}
