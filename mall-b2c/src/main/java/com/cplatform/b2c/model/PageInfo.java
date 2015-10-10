package com.cplatform.b2c.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * 分页相关属性类 标题、简要说明. <br>
 * 类详细说明.
 * <p>
 * Copyright: Copyright (c) May 31, 2013 2:26:09 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * 
 * @author zhangyinf@c-platform.com
 * @version 1.0.0
 */
public class PageInfo {

	private Integer pageRows = 10; // 每页显示内容数

	private Integer curPage; // 当前页数

	private Integer pageTotal; // 页面总数

	private Integer recordCount;// 总记录数

	public PageInfo() {
	}

	public PageInfo(Integer curPage, Integer recordCount, Integer pageRows) {
		this.curPage = curPage == null ? 1 : curPage;
		this.recordCount = recordCount == null ? 0 : recordCount;
		this.pageRows = pageRows == null ? 10 : pageRows;
		this.setPageTotals();
	}

	public Integer getPageRows() {
		return pageRows;
	}

	public void setPageRows(Integer pageRows) {
		this.pageRows = pageRows;
	}

	public Integer getCurPage() {
		return curPage;
	}

	public void setCurPage(Integer curPage) {
		this.curPage = curPage;
	}

	public Integer getPageTotal() {
		return pageTotal;
	}

	public void setPageTotal(Integer pageTotal) {
		this.pageTotal = pageTotal;
	}

	/**
	 * 获取实际查询数据总页数
	 * 
	 * @param rowTotal
	 */
	public void setPageTotals() {
		int pageTotal = recordCount / this.getPageRows();
		if (recordCount % this.getPageRows() > 0) {
			pageTotal++;
		}
		this.setPageTotal(pageTotal);
	}

	public String getScript(String type) {
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"page_numbers\"><ul>");
		if (pageTotal >= 2) {
			Set<Integer> set = new HashSet<Integer>();
			set.add(Integer.valueOf(1));
			set.add(Integer.valueOf(2));
			set.add(Integer.valueOf(pageTotal));
			set.add(Integer.valueOf(curPage));
			if (curPage > 3) {
				set.add(Integer.valueOf(curPage - 1));
			}
			if (curPage > 4) {
				set.add(Integer.valueOf(curPage - 2));
			}
			if (curPage < pageTotal - 2) {
				set.add(Integer.valueOf(curPage + 1));
			}
			if (curPage < pageTotal - 3) {
				set.add(Integer.valueOf(curPage + 2));
			}
			if (pageTotal > 3) {
				set.add(Integer.valueOf(pageTotal - 1));
			}
			List<Integer> list = new ArrayList<Integer>(set);
			Collections.sort(list);

			if (curPage > 1) {
				html.append("<li><a href=\"javascript:NagivatePage(").append(curPage - 1).append(",").append(type).append(");\">&lt;</a></li>");
			} else {
				html.append("<li class=\"no_link\">&lt;</li>");
			}

			for (int i = 0; i < list.size(); i++) {
				Integer num = (Integer) list.get(i);
				if (html.length() > 0) {
					html.append(" ");
				}
				if (i > 1 && ((Integer) list.get(i)).intValue() - ((Integer) list.get(i - 1)).intValue() > 1) {
					html.append("<li class=\"space\">...</li>");
				}
				html.append(renderSingleLink(num.intValue(), curPage, type));
			}

			if (curPage < pageTotal) {
				html.append("<li><a href=\"javascript:NagivatePage(").append(curPage + 1).append(",").append(type).append(");\">&gt;</a></li>");
			} else {
				html.append("<li class=\"no_link\">&gt;</li>");
			}
		}
		// html.append("<li class=\"page_info\">共
		// ").append(recordCount).append(" 条记录</li>");
		html.append("</ul><div style=\"float: none; clear: both;\"></div></div>");
		html.append("<input type=\"hidden\" name=\"curpage\" id=\"curpage\" value=\"").append(curPage).append("\"/>");
		return html.toString();
	}

	public String getScript() {
		StringBuffer html = new StringBuffer();
		html.append("<div class=\"page_numbers\"><ul>");
		if (pageTotal >= 2) {
			Set<Integer> set = new HashSet<Integer>();
			set.add(Integer.valueOf(1));
			set.add(Integer.valueOf(2));
			set.add(Integer.valueOf(pageTotal));
			set.add(Integer.valueOf(curPage));
			if (curPage > 3) {
				set.add(Integer.valueOf(curPage - 1));
			}
			if (curPage > 4) {
				set.add(Integer.valueOf(curPage - 2));
			}
			if (curPage < pageTotal - 2) {
				set.add(Integer.valueOf(curPage + 1));
			}
			if (curPage < pageTotal - 3) {
				set.add(Integer.valueOf(curPage + 2));
			}
			if (pageTotal > 3) {
				set.add(Integer.valueOf(pageTotal - 1));
			}
			List<Integer> list = new ArrayList<Integer>(set);
			Collections.sort(list);

			if (curPage > 1) {
				html.append("<li><a href=\"javascript:NagivatePage(").append(curPage - 1).append(");\">&lt;</a></li>");
			} else {
				html.append("<li class=\"no_link\">&lt;</li>");
			}

			for (int i = 0; i < list.size(); i++) {
				Integer num = (Integer) list.get(i);
				if (html.length() > 0) {
					html.append(" ");
				}
				if (i > 1 && ((Integer) list.get(i)).intValue() - ((Integer) list.get(i - 1)).intValue() > 1) {
					html.append("<li class=\"space\">...</li>");
				}
				html.append(renderSingleLink(num.intValue(), curPage));
			}

			if (curPage < pageTotal) {
				html.append("<li><a href=\"javascript:NagivatePage(").append(curPage + 1).append(");\">&gt;</a></li>");
			} else {
				html.append("<li class=\"no_link\">&gt;</li>");
			}
		}
		// html.append("<li class=\"page_info\">共
		// ").append(recordCount).append(" 条记录</li>");
		html.append("</ul><div style=\"float: none; clear: both;\"></div></div>");
		html.append("<input type=\"hidden\" name=\"curpage\" id=\"curpage\" value=\"").append(curPage).append("\"/>");
		return html.toString();
	}
	
	/**
	 * 个人中心分页信息
	 * @param pageInfo
	 * @return
	 */
	public  String getMemPageInfo() {
		StringBuilder htmlBuff = new StringBuilder(1000);
		htmlBuff.append("<div class=\"cb\" id=\"pagenav\">");
		if (curPage!=1) {
			// 首页
			htmlBuff.append("<a href=\"")
			        .append("javascript:NagivatePage(").append(1).append(");\">首页</a>");
			// 上一页
			htmlBuff.append("<a href=\"")
			        .append("javascript:NagivatePage(").append(curPage-1).append(");\">上一页</a>");
		}
		//滚动操作控制
		int size = 9;//显示的个数
		int first = 0;//当前显示的第一个页数
		int last  = 0;//当前显示的最后一个页数
		if(size-pageTotal>0){ //判断是不是一次全部显示
			first = 1;
			last = pageTotal;
		}else if(curPage+size/2>=pageTotal){//显示末页的9页
			first = pageTotal-size+1;
			last = pageTotal;
		}else{
			if(curPage<=5){
				first = 1;
				last = size;
			}else{	
				first = curPage-size/2;
				last = curPage+size/2;
			}
		}	
		for (int i = first; i <= last; i++) {
			htmlBuff.append("<a href=\"")
			        .append("javascript:NagivatePage(").append(i).append(");\"");
			if (curPage == i) {
				htmlBuff.append(" class=\"hover\">").append(i).append("</a>");
			} else {
				htmlBuff.append(">").append(i).append("</a>");
			}
		}		
		if (pageTotal-curPage!=0&&pageTotal!=0) {
			// 下一页
			htmlBuff.append("<a href=\"")
			        .append("javascript:NagivatePage(").append(curPage+1).append(");\">下一页</a>");
			// 末页
			htmlBuff.append("<a href=\"")
			        .append("javascript:NagivatePage(").append(pageTotal).append(");\">末页</a>");
		}
		htmlBuff.append("<br/>共 ").append(recordCount).append(" 条,每页 ").append(pageRows).append(" 条");
		htmlBuff.append("</div>");
		return htmlBuff.toString();
	}

	private static String renderSingleLink(int num, int curPage) {
		StringBuffer sb = new StringBuffer();
		if (num == curPage) {
			sb.append("<li class=\"active_page\">").append(num).append("</li>");
		} else {
			sb.append("<li><a href=\"javascript:NagivatePage(").append(num).append(");\">").append(num).append("</a></li>");
		}
		return sb.toString();
	}

	private static String renderSingleLink(int num, int curPage, String type) {
		StringBuffer sb = new StringBuffer();
		if (num == curPage) {
			sb.append("<li class=\"active_page\">").append(num).append("</li>");
		} else {
			sb.append("<li><a href=\"javascript:NagivatePage(").append(num).append(",").append(type).append(");\">").append(num).append("</a></li>");
		}
		return sb.toString();
	}

	public Integer getRecordCount() {
		return recordCount;
	}

	public void setRecordCount(Integer recordCount) {
		this.recordCount = recordCount;
	}
}
