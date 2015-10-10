package com.cplatform.mall.dc.utils.fusionChart;

import java.util.ArrayList;
import java.util.List;

import net.sf.json.JSONObject;

import org.apache.commons.lang3.ArrayUtils;

import com.cplatform.mall.dc.utils.fusionChart.pie3d.Pie3Chart;
import com.cplatform.mall.dc.utils.fusionChart.pie3d.Pie3Data;
import com.cplatform.mall.dc.utils.fusionChart.pie3d.Pie3Fusion;

/**
 * JSON创建工具类<br>
 * <p>
 * Copyright: Copyright (c) 2013-12-26 下午6:51:57
 * <p>
 * Company: 苏州宽连信息技术有限公司
 * <p>
 * 
 * @author zhuangxx@c-platform.com
 * @version 1.0.0
 */
public class JsonCreater {

	/**
	 * 创建fusionChart所需的JSON对象
	 * 
	 * @param datas
	 *            数据列表
	 * @return JSON数组
	 */
	public static List<String> toCharts(List<List<String>> datas) {
		List<String> chars = new ArrayList<String>();

		List<String> titles = new ArrayList<String>();
		List<Category> categories = new ArrayList<Category>();
		List<List<Data>> listDatas = new ArrayList<List<Data>>();

		for (int i = 0; i < datas.size() - 1; i++) {
			List<String> data = datas.get(i);
			for (int j = 0; j < data.size(); j++) {
				String item = data.get(j);
				if (i == 0) {
					if (j != 0) {
						titles.add(item);
					}
				} else {
					if (listDatas.size() <= j) {
						listDatas.add(new ArrayList<Data>());
					}
					if (j == 0) {
						categories.add(new Category(item));
					} else {
						listDatas.get(j).add(new Data(item));
					}
				}
			}
		}

		for (int i = 0; i < titles.size(); i++) {
			FusionChart fusionChart = new FusionChart();

			Chart chart = new Chart();
			chart.setCaption(titles.get(i));
			fusionChart.setChart(chart);

			Categories cates = new Categories();
			cates.setCategory(categories);
			fusionChart.setCategories(cates);

			List<Dataset> listDataset = new ArrayList<Dataset>();
			Dataset dataset = new Dataset();
			dataset.setSeriesname("");
			dataset.setData(listDatas.get(i + 1));
			listDataset.add(dataset);
			fusionChart.setDataset(listDataset);

			JSONObject json = JSONObject.fromObject(fusionChart);

			chars.add(json.toString());
		}

		return chars;
	}

	/**
	 * 创建fusionChart所需的JSON对象
	 * 
	 * @param datas
	 *            数据列表
	 * @return JSON数组
	 */
	public static List<String> toChartsPie(List<List<String>> datas) {
		List<String> chars = new ArrayList<String>();

		String[] titles = {};
		for (int i = 0; i < datas.get(0).size(); i++) {
			titles = ArrayUtils.add(titles, datas.get(0).get(i));
		}

		for (int i = 1; i < datas.size(); i++) {
			Pie3Fusion fusionChart = new Pie3Fusion();

			List<String> data = datas.get(i);
			List<Pie3Data> listData = new ArrayList<Pie3Data>();
			for (int j = 0; j < data.size(); j++) {
				String value = data.get(j);
				if (j == 0) {
					Pie3Chart chart = new Pie3Chart(value);
					fusionChart.setChart(chart);
				} else {
					listData.add(new Pie3Data(titles[j], value));
				}
			}
			fusionChart.setData(listData);
			JSONObject json = JSONObject.fromObject(fusionChart);
			chars.add(json.toString());
		}

		return chars;
	}
}
