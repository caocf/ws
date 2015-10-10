package com.cplatform.mall.dc.view;

import java.util.ArrayList;
import java.util.List;

import com.cplatform.mall.dc.view.ExcelData.DataType;

/**
 * excel文档输出的sheet对象。
 * 标题栏、内容数据、列宽、列类型的数量必须相等
 * @author chengyao
 *
 */
public class ExcelSheet {

	/** sheet 名 */
	private String name;

	/** 标题栏数据 */
    private String[] titles;

    /** 内容数据 */
    private List<Object[]> contents = new ArrayList<Object[]>();

    /** 列宽，可以不设置 */
    private int[] widths;
    
    /** 列的数据类型，可以不设置 */
    private DataType[] dataTypes;
    
	public int[] getWidths() {
		return widths.clone();
	}

	public void setWidths(int[] widths) {
		this.widths = widths.clone();
	}

	public DataType[] getDataTypes() {
		return dataTypes.clone();
	}

	public void setDataTypes(DataType[] dataTypes) {
		this.dataTypes = dataTypes.clone();
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String[] getTitles() {
		return titles.clone();
	}

	public void setTitles(String[] titles) {
		this.titles = titles.clone();
	}

	public List<Object[]> getContents() {
		return contents;
	}

	public void setContents(List<Object[]> contents) {
		this.contents = contents;
	}

	
    
}
