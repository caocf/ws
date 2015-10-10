<%@ page language="java" pageEncoding="utf-8"%>

</div>
<!-- End .container-fluid  -->
</div>
<!-- End .wrapper  -->
</section>
</div>
<!-- End .main  -->

<script>
	/**
	 * 显示或隐藏div
	 */
	function blockDivs(cnt, index, divName, btnName) {
		for ( var i = 1; i <= cnt; i++) {
			var divId = '#' + divName + i;
			var btnId = '#' + btnName + i;
			if (i == index) {
				$(divId).show();
				$(btnId).removeClass("btn").addClass("btn btn-primary");
				createChart(i, divName);
			} else {
				$(divId).hide();
				$(btnId).removeClass("btn btn-primary").addClass("btn");
			}
		}
	}

	/**
	 * 生成图表
	 */
	function createChart(index, divName) {
		var jsonName = 'jsonData' + divName + index;
		var divName = divName + index;

		myChart.setJSONData(eval(jsonName));
		myChart.render(divName);
	}

	/**
	 * 渲染图表
	 */
	function renderCharts(i, charts) {
		var myChart = new FusionCharts({
			type : 'StackedColumn3D',
			width : '512',
			height : 342
		});
		myChart.setJSONData(charts[i - 1]);
		myChart.render('divCharts');
	}

	/**
	 * 渲染图表（饼图）
	 */
	function renderChartsPie3D(i, charts) {
		var myChart = new FusionCharts({
			type : 'Pie3D',
			width : '512',
			height : 342
		});
		myChart.setJSONData(charts[i - 1]);
		myChart.render('divCharts');
	}

	$(function() {
		if ($.browser.mozilla) {//火狐浏览器下样式修订

		} else if ($.browser.msie && $.browser.version == "8.0") {//IE8浏览器下样式修订

		}
	});
</script>
</body>
</html>