<%@ page language="java" pageEncoding="utf-8"%>
<script>


/** 
 * 标准曲线图 
 * @param json
 * @param divId
 * @param paramWidth
 * @param paramHeight
 * {"attribute":{"id":"container","titleY":"Y轴标题","title":"标题"},"colsTitleList":["2008年","2009年","2010年","2011年","2012年","2013年","2014年"],"seriesData":[{"name":"语文","data":[1,2,3,4,5,6]},{"name":"数学","data":[1,2,3,4,5,6]}]}
 */
 function curveChart(jsonData,divId,paramWidth,paramHeight){
	 curveChart2(jsonData,divId,paramWidth,paramHeight,"","");
 }
function curveChart2(jsonData,divId,paramWidth,paramHeight,unitLeft,unitRight){
	var chart = new Highcharts.Chart({
		chart: {
			width:paramWidth,
			height:paramHeight,
			renderTo: divId, 	//显示DIV的ID
			defaultSeriesType: 'spline',
			margin: [50, 100, 90, 80]
		},
		title: {
			text: jsonData.attribute.title,	//标题
			style: {
				margin: '30px 100px 0 0' // center it
			}
		},
		scrollbar:{ 
			enabled:true //是否产生滚动条 
			
		},
		xAxis: {
			categories: jsonData.colsTitleList,  // 列标题
			max: jsonData.colsTitleList.length > 20 ? 20 : null ,//设置x轴的宽度 
			//tickInterval: 5, //间隔显示
			//min: 1 , //每行显示
			labels:{   
				margin: 60,
				x:-20,//调节x偏移            
				y:40,//调节y偏移     
				rotation:-55//调节倾斜角度偏移              
			 
			}
		},
 
		yAxis: [{ // Primary yAxis
			labels: {
				formatter: function() {
					return this.value+" "+unitLeft;  //可拼尺码标题  
				},
				style: {
					color: '#89A54E'
				}
			},
			title: {
				text: jsonData.attribute.titleY,
				margin: 10,
				style: {
					color: '#000033'
				},
			}
		}, 
		{ // Secondary yAxis
			title: {
				text: '',
				margin: 10,
				style: {
					color: '#4572A7'
				}
			},
			labels: {
				formatter: function() {
					return this.value+" "+unitRight; // 可拼尺码标题
				},
				style: {
					color: '#4572A7'
				}
			},
			opposite: true
		}],
		
		tooltip: {
			formatter: function() {
	                return '<b>'+ this.series.name +'</b><br/>'+this.x +': '+ this.y;  //可拼接鼠标经过提示
			}
		},
		legend: {
			layout: 'horizontal',
			style: {
				left: 'auto',
				bottom: 'auto',
				right: '1px',
				height: 300,
				top: '100px',
			}
		},
		// 不显示作者
		credits: {
		     enabled: false
		},
		series:jsonData.colsContentList
		/*
		   series: [{
		        type: 'column',
		        yAxis: 0,
		        name: ' Volume',
		        data: [11,47,651,200,100,18,9,21,10,51] 
		    },{ 
		        type: 'spline',
		        yAxis: 1,
		        name: 'AAPL Stock Volume',
		        data:  [11,47,651,200,100,18,9,21,10,51,11,47,651,200,100,18,9,21,10,51,11,47,651,200,100,18,9,21,10,51] 
		    }]
		*/
	});
}
 
 
 /** 
  * 柱状曲线图 
  * @param json
  * @param divId
  * @param paramWidth
  * @param paramHeight
 
 function columnCurveChart(jsonData,divId,paramWidth,paramHeight,leftName,rightName){
	 chart = new Highcharts.Chart({
			chart: {
				width:paramWidth,
				height:paramHeight,
				renderTo: divId,
				margin: [150, 100, 90, 100],
				zoomType: 'xy'
			},
			title: {
				text: jsonData.attribute.title,  // 标题
				style: {
					margin: '10px 0 0 0' // center it
				}
			},
			xAxis: [{
				categories:  jsonData.colsTitleList,  // 列标题
				 labels:{           
					x:-20,//调节x偏移            
					y:55,//调节y偏移     
					rotation:-55//调节倾斜角度偏移              
				}
			}],
			yAxis: [{ // Primary yAxis
				labels: {
					formatter: function() {
						return this.value+" "+leftName;  // 可拼尺码单位
					},
					style: {
						color: '#89A54E'
					}
				},
				title: {
					text: jsonData.attribute.titleY	,  // Y轴标题
					style: {
						color: '#000033'
					},
					margin: 60
				}
			}, 
			{ // Secondary yAxis
				title: {
					text: '',
					margin: 70,
					style: {
						color: '#4572A7'
					}
				},
				labels: {
					formatter: function() {
						return this.value+" "+rightName; // 可拼尺码单位
					},
					style: {
						color: '#4572A7'
					}
				},
				opposite: true
			}],
			tooltip: {
				formatter: function() {
					return '<b>'+ this.series.name +'</b><br/>'+
						this.x +': '+ this.y +
						(this.series.name == 'Rainfall' ? '' : ''); // 逗号里面拼单位
				}
			},
			legend: {
				layout: 'vertical',
				style: {
					left: '120px',
					bottom: 'auto',
					right: 'auto',
					top: '100px'
				},
				backgroundColor: '#FFFFFF'
			},
			series: jsonData.colsContentList
		});
	 
 }  */
 
 function compareObject(o1,o2){
	  if(typeof o1 != typeof o2)return false;
	  if(typeof o1 == 'object'){
	    for(var o in o1){
	      if(typeof o2[o] == 'undefined')return false;
	      if(!compareObject(o1[o],o2[o]))return false;
	    }
	    return true;
	  }else{
	    return o1 === o2;
	  }
	}
</script>