package com.cplatform.mall.dc.view;

import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.codec.binary.Base64;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.DataFormat;
import org.apache.poi.ss.usermodel.Font;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.view.document.AbstractExcelView;

import com.cplatform.mall.dc.view.ExcelData.DataType;

/**
 * spring excel输出视图
 * @author chengyao
 *
 */
public class SimpleExcelView extends AbstractExcelView {

    public static final String KEY_DATA = "data";
    
    @Override
    protected void buildExcelDocument(Map<String, Object> model, HSSFWorkbook workbook, HttpServletRequest request, HttpServletResponse response) throws Exception {

        ExcelData excel = (ExcelData) model.get(KEY_DATA);

        response.setCharacterEncoding("utf-8");
        Map<String, CellStyle> styles = createStyles(workbook);
        
        String fileName = StringUtils.trimWhitespace(excel.getFileName());
        
        if (StringUtils.hasLength(fileName)) {
            response.addHeader("Content-Disposition",
                               "attachment; filename=\"" + encodeFileName(request, fileName + ".xls") + "\"");
        }
        
        // sheet loop
        for (int i = 0; i < excel.getSheets().size(); i++) {
        	ExcelSheet sheetData = excel.getSheets().get(i);        
        	
        	// sheet name set;
        	String sheetName = sheetData.getName();
        	if (!StringUtils.hasLength(sheetName)) {
                sheetName = "sheet" + (i + 1);
            }
        	
        	Sheet sheet = workbook.createSheet(sheetName);
        	// sheet global set;
            sheet.setFitToPage(true);
            sheet.setHorizontallyCenter(true);
            sheet.setAutobreaks(true);	
                        
        	int rowline = 0;
        	// titles set;
        	String[] titles = sheetData.getTitles();
            if (titles != null) {
                for (int j = 0; j < titles.length; j++) {
                    HSSFCell cell = getCell((HSSFSheet) sheet, rowline, j);
                    setText(cell, titles[j]);
                    cell.setCellStyle(styles.get("header"));
                }
                rowline++;
                
                //freeze the first row
                sheet.createFreezePane(0, 1);                
            }

            // column width set;
            int[] widths = sheetData.getWidths();
            if (widths != null) {
            	for (int j = 0; j < widths.length; j++) {
            		sheet.setColumnWidth(j, 1024*widths[j]);
            	}
            	
            }
            
            DataType[] dataTypes = sheetData.getDataTypes();
            
            for (int j = 0; j < sheetData.getContents().size(); j++) {
                Object[] row = sheetData.getContents().get(j);
                if (row == null) continue;
                for (int k = 0; k < row.length; k++) {
                    HSSFCell cell = getCell((HSSFSheet) sheet, j + rowline, k);
                    Object val = row[k];
                    if (val == null) {
                    	cell.setCellStyle(styles.get("cell_normal"));
                    	continue;                    
                    }
                    try {
                        if (dataTypes == null) {
                            setCellByDefault(val, cell, styles);
                        } else {
                            setCellBySetting(val, cell, styles, dataTypes[k]);
                        }
                    } catch (Exception ex) {
                        cell.setCellStyle(styles.get("cell_normal"));
                    }

                }
            }
        }
    }

    private String encodeFileName(HttpServletRequest request, String fileName) throws UnsupportedEncodingException {
        String agent = request.getHeader("USER-AGENT");
        if (null != agent && -1 != agent.indexOf("MSIE")) {
            return java.net.URLEncoder.encode(fileName, "utf-8");
        } else if (null != agent && -1 != agent.indexOf("Mozilla")) {
            return "=?UTF-8?B?"+(new String(Base64.encodeBase64(fileName.getBytes("utf-8"))))+"?=";
        } else {
            return fileName;
        }
    }

    private void setCellBySetting(Object val, HSSFCell cell, Map<String, CellStyle> styles, DataType dataType) {
    	switch (dataType) {
		case STRING:
			setText(cell, String.valueOf(val));
    		cell.setCellStyle(styles.get("cell_normal"));
			break;
		case LONG:
			cell.setCellValue(Long.parseLong(val.toString()));
    		cell.setCellStyle(styles.get("cell_normal"));
			break;
		case DOUBLE:
			cell.setCellValue(Double.parseDouble(val.toString()));
    		cell.setCellStyle(styles.get("cell_normal"));
			break;
		case DATE14:
			cell.setCellValue(toDate(val, "yyyyMMddHHmmss"));
    		cell.setCellStyle(styles.get("cell_date_14"));
			break;
		case DATE8:
			cell.setCellValue(toDate(val, "yyyyMMdd"));
    		cell.setCellStyle(styles.get("cell_date_8"));
			break;
		case DATE4:
			cell.setCellValue(toDate(val, "MMdd"));
    		cell.setCellStyle(styles.get("cell_date_4"));
			break;
		default:
			break;
		}
    }
    
    
    public Date toDate(Object source, String sfmt) {
        try {
            if (source == null) {
                return null;
            } else if (source instanceof String) {
                SimpleDateFormat parseformat = new SimpleDateFormat(sfmt);
                return parseformat.parse((String) source);
            } else {
                return null;
            }
        } catch (ParseException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Source Parse Exception: source=" + source + ", sfmt=" + sfmt, e);
            }
        } catch (IllegalArgumentException e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Illegal Source Exception: source=" + source + ", sfmt=" + sfmt, e);
            }
        } catch (Exception e) {
            if (logger.isDebugEnabled()) {
                logger.debug("Exception: source=" + source + ", sfmt=" + sfmt, e);
            }
        }
        return null;
    }
    
    private void setCellByDefault(Object val, HSSFCell cell, Map<String, CellStyle> styles) {
    	if (val instanceof String) {
        	setText(cell, (String) val);
            cell.setCellStyle(styles.get("cell_normal"));
        } else if (val instanceof Integer) {
        	cell.setCellValue((Integer) val);
            cell.setCellStyle(styles.get("cell_normal"));
        } else if (val instanceof Long) {
        	cell.setCellValue((Long) val);
            cell.setCellStyle(styles.get("cell_normal"));
        } else if (val instanceof Double) {
        	cell.setCellValue((Double) val);
            cell.setCellStyle(styles.get("cell_normal"));
        } else if (val instanceof Date) {
        	cell.setCellValue((Date) val);
        	cell.setCellStyle(styles.get("cell_date_14"));
        } else {
        	setText(cell, String.valueOf(val));
        	cell.setCellStyle(styles.get("cell_normal"));
        }
    }
    
    /**
     * create a library of cell styles
     */
    private static Map<String, CellStyle> createStyles(Workbook wb){
        Map<String, CellStyle> styles = new HashMap<String, CellStyle>();
        DataFormat df = wb.createDataFormat();

        CellStyle style;
        Font headerFont = wb.createFont();
        headerFont.setFontHeightInPoints((short)12);
        headerFont.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        styles.put("header", style);
        
        Font normalFont = wb.createFont();
        normalFont.setFontHeightInPoints((short)11);
        style = createBorderedStyle(wb);
        style.setFont(normalFont);
        styles.put("cell_normal", style);
        
        style = createBorderedStyle(wb);
        style.setDataFormat(df.getFormat("yyyy-MM-dd HH:mm:ss"));
        style.setFont(normalFont);
        styles.put("cell_date_14", style);
        
        style = createBorderedStyle(wb);
        style.setDataFormat(df.getFormat("yyyy-MM-dd"));
        style.setFont(normalFont);
        styles.put("cell_date_8", style);
        
        style = createBorderedStyle(wb);
        style.setDataFormat(df.getFormat("MM-dd"));
        style.setFont(normalFont);
        styles.put("cell_date_4", style);
        /*
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFillForegroundColor(IndexedColors.LIGHT_CORNFLOWER_BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setFont(headerFont);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("header_date", style);

        Font font1 = wb.createFont();
        font1.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font1);
        styles.put("cell_b", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setFont(font1);
        styles.put("cell_b_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_b_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_g", style);

        Font font2 = wb.createFont();
        font2.setColor(IndexedColors.BLUE.getIndex());
        font2.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font2);
        styles.put("cell_bb", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setFont(font1);
        style.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_bg", style);

        Font font3 = wb.createFont();
        font3.setFontHeightInPoints((short)14);
        font3.setColor(IndexedColors.DARK_BLUE.getIndex());
        font3.setBoldweight(Font.BOLDWEIGHT_BOLD);
        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setFont(font3);
        style.setWrapText(true);
        styles.put("cell_h", style);

        

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_CENTER);
        style.setWrapText(true);
        styles.put("cell_normal_centered", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_RIGHT);
        style.setWrapText(true);
        style.setDataFormat(df.getFormat("d-mmm"));
        styles.put("cell_normal_date", style);

        style = createBorderedStyle(wb);
        style.setAlignment(CellStyle.ALIGN_LEFT);
        style.setIndention((short)1);
        style.setWrapText(true);
        styles.put("cell_indented", style);

        style = createBorderedStyle(wb);
        style.setFillForegroundColor(IndexedColors.BLUE.getIndex());
        style.setFillPattern(CellStyle.SOLID_FOREGROUND);
        styles.put("cell_blue", style);
         */
        return styles;
    }
    
    private static CellStyle createBorderedStyle(Workbook wb){
        CellStyle style = wb.createCellStyle();
        style.setBorderRight(CellStyle.BORDER_THIN);
        style.setRightBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderBottom(CellStyle.BORDER_THIN);
        style.setBottomBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderLeft(CellStyle.BORDER_THIN);
        style.setLeftBorderColor(IndexedColors.BLACK.getIndex());
        style.setBorderTop(CellStyle.BORDER_THIN);
        style.setTopBorderColor(IndexedColors.BLACK.getIndex());
        return style;
    }
}