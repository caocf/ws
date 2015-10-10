package com.cplatform.b2c.welfare.util;

import java.io.UnsupportedEncodingException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

/**
 * 分页组件. <br>
 * Description.
 * <p>
 * Copyright: Copyright (c) May 11, 2010 2:19:54 PM
 * <p>
 * Company: 北京宽连十方数字技术有限公司
 * <p>
 * Author: guolt@c-platform.com
 * <p>
 * Version: 1.0
 * <p>
 */
@SuppressWarnings("unchecked")
public class Pagination {

	/** 总记录数 */
	private final int count;

	private List list = null;

	/** 当前页，即获取当前页的具体页号。 */
	private final int nowPage;

	/** 总页数 */
	private final int pageCount;

	/** 每页显示的记录总数 */
	private final int pageSize;

	/** URL参数，用来保存查询条件 */
	private final HashMap params = new HashMap();

	/** 当前的地址栏的URL */
	private final String uri;

	/**
	 * 初始化分页组件类的几个参数
	 * 
	 * @param request
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页显示的记录总数
	 * @param currentPage
	 *            当前页，即获取当前页的具体页号。
	 */
	public Pagination(HttpServletRequest request, int count, int pageSize,
			int currentPage, Collection collection,String host) {
		this.uri =host+ request.getRequestURI().toString();
		this.count = count;
		this.pageSize = pageSize;
		this.nowPage = currentPage;

		this.pageCount = (int) Math.ceil(count * 1.0 / this.pageSize);
		
		String strs = request.getQueryString();
		if(strs != null ){
		String[] params = strs.split("&");
		String key = "";
		String value = "";
			for (String str : params) {
		        String[] ms = str.split("=");
		        if(ms.length == 2){
		        	value =ms[1];
		        }else{
		        	value="";
		        }
		        
		        key = ms[0];
		        addParam(key, value);
			}
        }
		

		this.list = getSubList(collection);
	}

	/**
	 * 初始化分页组件类的几个参数
	 * 
	 * @param uri
	 * @param count
	 *            总记录数
	 * @param pageSize
	 *            每页显示的记录总数
	 * @param currentPage
	 *            当前页，即获取当前页的具体页号。
	 */
	public Pagination(String uri, int count, int pageSize, int currentPage,
			Collection collection) {
		this.uri = uri;
		this.count = count;
		this.pageSize = pageSize;
		this.nowPage = currentPage;

		this.pageCount = (int) Math.ceil(count * 1.0 / this.pageSize);

		this.list = getSubList(collection);
	}

	/** 添加URL参数 */
	public void addParam(String key, String value) {
		params.put(key, value);
	}

	public int getCount() {
		return count;
	}

	// 分页栏函数。
	public String getFooter() {
		String str = "";
		int next, prev;
		prev = nowPage - 1;
		next = nowPage + 1;

		String uriParam = this.getParams().equals("") ? "" : "?"
				+ this.getParams().substring(1, this.getParams().length());
		str += "<form action=" + uri + uriParam + " method=post>";
		str += count + "&nbsp;条信息&nbsp;";
		str += "&nbsp;共&nbsp;" + pageCount + "&nbsp;页&nbsp;";
		if (nowPage > 1) {
			str += "&nbsp;<a   href=" + uri + "?page=1" + this.getParams()
					+ ">最前页</a>   ";
			str += "&nbsp;|&nbsp; <a   href=" + uri + "?page=" + (nowPage - 1)
					+ this.getParams() + ">上一页</a>   ";
		} else {
			str += "&nbsp;最前页  ";
			str += "&nbsp;|&nbsp;上一页   ";
		}
		str += "&nbsp;&nbsp;<span style='font-weight: bold; color: #5858E6'>"
				+ nowPage + "</span>&nbsp;&nbsp;";

		if (nowPage < pageCount) {
			str += " &nbsp; <a   href=" + uri + "?page=" + (nowPage + 1)
					+ this.getParams() + ">下一页  </a>&nbsp;|&nbsp;";
			str += "   <a   href=" + uri + "?page=" + pageCount
					+ this.getParams() + ">最后页</a>&nbsp;";
		} else {
			str += "   下一页&nbsp;    |";
			str += "   &nbsp;最后页&nbsp;   </font>";
		}
		str += "     <select id='page' name='page' onchange='submit()'>";

		// for (int i = 1; i <= pageCount; i++)
		// {
		// if (i != nowPage)
		// str += "<option value='" + i + "'>" + i + "</option>";
		// else
		// str += "<option value='" + i + "' selected>" + i + "</option>";
		// }
		// 调整为当前页的前后500页
		int start = 1;
		int end = pageCount;
		if (pageCount > 1000) {
			if (nowPage + 500 < 1000)
				end = 1000;
			else if (nowPage + 500 > pageCount) {
				start = pageCount - 1000;
				end = pageCount;
			} else {
				start = nowPage - 500;
				end = nowPage + 500;
				if (start < 1)
					start = 1;
				if (end > pageCount)
					end = pageCount;
			}
		}
		for (int i = start; i <= end; i++) {
			if (i != nowPage)
				str += "<option value='" + i + "'>" + i + "</option>";
			else
				str += "<option value='" + i + "' selected>" + i + "</option>";
		}
		str += "</select></form>";
		return str;
	}

	public List getList() {
		return list;
	}

	/** 获取URL参数 */
	public String getParams() {
		String ret = "";
		if (params.size() == 0)
			return ret;
		for (Iterator it = params.keySet().iterator(); it.hasNext();) {
			String key = (String) it.next();
			String value = (String) params.get(key);
			// 超链接乱码bug
			if (java.nio.charset.Charset.forName("ISO-8859-1").newEncoder()
					.canEncode(value)) {
				try {
					value = new String(value.getBytes("ISO-8859-1"), "GB18030");
				} catch (UnsupportedEncodingException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			ret += ("&" + key + "=" + value);
		}
		return ret;
	}

	/**
	 * @return List 结果
	 */
	public List getSubList(Collection collection) {
		if (collection == null)
			return null;
		List alist = new ArrayList(collection);

		return alist;
	}

	public String[] getWapFooter() {

		String[] result = new String[2];
		String str = "";
		int next, prev;
		prev = nowPage - 1;
		next = nowPage + 1;

		str += "共<font color='red'>" + count + "</font>条&nbsp;";
		str += "每页<font color='red'>" + pageSize + "</font>条&nbsp;";
		str += "共<font color='red'>" + pageCount + "</font>页&nbsp;";
		str += "当前第<font color='red'>" + nowPage + "</font>页&nbsp;";
		result[0] = str + "<br/>";
		str = "";
		if (nowPage > 1)
			str += "   <a   href='" + uri + "?page=1" + this.getParams()
					+ "'>第一页</a>   ";
		else
			str += "   第一页   ";

		if (nowPage > 1)
			str += "   <a   href='" + uri + "?page=" + prev + this.getParams()
					+ "'>上一页</a>   ";
		else
			str += "   上一页   ";

		if (nowPage < pageCount)
			str += "   <a   href='" + uri + "?page=" + next + this.getParams()
					+ "'>下一页</a>   ";
		else
			str += "   下一页   ";

		if (nowPage < pageCount)
			str += "   <a   href='" + uri + "?page=" + pageCount
					+ this.getParams() + "'>最后页</a>";
		else
			str += "   最后页   ";

		result[1] = str + "<br/>";
		return result;
	}

	/**
	 * 获取 nowPage
	 * 
	 * @return nowPage
	 */
	public int getNowPage() {
		return nowPage;
	}

	/**
	 * 获取 pageCount
	 * 
	 * @return pageCount
	 */
	public int getPageCount() {
		return pageCount;
	}

	/**
	 * 获取 pageSize
	 * 
	 * @return pageSize
	 */
	public int getPageSize() {
		return pageSize;
	}

	/**
	 * 获取 uri
	 * 
	 * @return uri
	 */
	public String getUri() {
		return uri;
	}

}
