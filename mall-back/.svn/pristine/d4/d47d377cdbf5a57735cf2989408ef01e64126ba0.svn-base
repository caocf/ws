package com.cplatform.mall.back.order.entity;

import java.io.Serializable;
import java.util.List;

/**
 * @Title	订单物流追踪分页对象
 * @Description
 * @Copyright: Copyright (c) 2013-8-13
 * @Company: 北京宽连十方数字技术有限公司
 * @Author chencheng
 * @Version: 1.0
 *
 */
public class Pagination implements Serializable{
	private static final long serialVersionUID = -4432313787289899159L;
	// 当前页码
    private int pageIndex=1;
    // 每页记录数
    private int pageSize=10;
    // 总记录数
    private int totalRecordCnt;
    // 查询结果数据列表
    private List dataList;
    // 翻页时访问的URL
    private String url;
    // 翻页执行的动作
    private String turnAction;

    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int pageIndex) {
        this.pageIndex = pageIndex;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    /**
     * 获得总记录数
     * 
     * @return
     */
    public int getTotalRecordCnt() {
        return totalRecordCnt;
    }

    public void setTotalRecordCnt(int totalRecordCnt) {
        this.totalRecordCnt = totalRecordCnt;
    }

    /**
     * 获得总页数
     * 
     * @return
     */
    public int getTotalPageCnt() {
        return (totalRecordCnt + pageSize - 1) / pageSize;
    }

    /**
     * 获得数据列表
     * 
     * @return
     */
    public List getDataList() {
        return dataList;
    }

    public void setDataList(List dataList) {
        this.dataList = dataList;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    public String getUrl() {
        return this.url;
    }

    public String getTurnAction() {
        return turnAction;
    }

    public void setTurnAction(String turnAction) {
        this.turnAction = turnAction;
    }


}
