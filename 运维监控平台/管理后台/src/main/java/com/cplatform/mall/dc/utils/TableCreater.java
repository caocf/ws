
package com.cplatform.mall.dc.utils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 页面表格生成类
 * 
 * @author zhuangxx@c-platform.com
 * @since 2013-8-2
 */
public class TableCreater {
	/**
	 * 转换成生成页面表格的数据列表
	 * 
	 * @param listData 数据列表
	 * @param titles 标题行数据
	 * @param titleField 用于纵向标题的字段
	 * @return 页面表格
	 */
	public static List<List<String>> toTableList(List<Map<String, String>> listData, String[] titles, byte[] types, String titleField,
			boolean hasTotal) {
		try {

			List<List<String>> listNew = new ArrayList<List<String>>();

			if (listData != null && titles != null && titleField != null) {
				// 增加标题行
				List<String> listTitle = new ArrayList<String>();
				listTitle.add("");
				for (int i = 0; i < titles.length; i++) {
					listTitle.add(titles[i]);
				}
				listNew.add(listTitle);

				// 增加数据行
				for (int i = 0; i < listData.size(); i++) {
					Map<String, String> data = listData.get(i);
					List<String> listIn = new ArrayList<String>();
					listIn.add(data.get(titleField));
					for (int j = 0; j < titles.length; j++) {
						String dataValue = DataUtil.getRound(data.get("CNT" + (j + 1)), types[j]);
						listIn.add(dataValue);
					}
					listNew.add(listIn);
				}
			}

			return listNew;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		}
	}
}
