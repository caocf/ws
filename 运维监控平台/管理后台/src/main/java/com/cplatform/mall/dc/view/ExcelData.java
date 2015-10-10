package com.cplatform.mall.dc.view;

import org.springframework.web.servlet.ModelAndView;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 包装的excel输出对象
 * @author chengyao
 *
 */
public class ExcelData {
	
	public enum DataType {
	    STRING, LONG, DOUBLE, DATE14, DATE8, DATE4 
	}

    private static final String SIMPLE_VIEW_NAME = "simpleExcelView";

    /** 输出的文件名 */
    private String fileName;

    /** sheet对象 */
    private List<ExcelSheet> sheets = new ArrayList<ExcelSheet>();
    
    public List<ExcelSheet> getSheets() {
		return sheets;
	}

	public void setSheets(List<ExcelSheet> sheets) {
		this.sheets = sheets;
	}

	public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    /**
     * 输出ModelAndView
     * @return
     */
    public ModelAndView toModel() {
        Map<String, Object> map = new HashMap<String, Object>();
        map.put(SimpleExcelView.KEY_DATA, this);
        return new ModelAndView(SIMPLE_VIEW_NAME, map);
    }

}
