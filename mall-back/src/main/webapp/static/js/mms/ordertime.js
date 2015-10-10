/**
 author：Apr 29, 2010 10:18:29 PM majk@c-platform.com
 说明：定时发送动态生成下拉时间以及设置当前时间
*/
//对定时发送的时间进行初奴化

var openFrame;//定时设置时弹出的窗口
function initTime()
{
  
    //日期对象
    var curDate = new Date();
    //获得年
    var curYear = curDate.getFullYear();
    //获得月
    var curMonth = curDate.getMonth() + 1;
    //获得天
    var curDay = curDate.getDate();
    //获得小时
    var curHour = curDate.getHours();
    //获得分
    var curMinute = curDate.getMinutes();
    
    //获得当月的最后一天使用
    var tmpDate = new Date(curYear, curMonth, 0);
    //当月最大的天数
    var curMaxDay = tmpDate.getDate();
   
    //年下拉对象
    var yearObj = document.getElementById("yearSelect");
    //月下拉对象
    var monthObj = document.getElementById("monthSelect");
    //日下拉对象
    var dayObj = document.getElementById("daySelect");
    //时下拉对象
    var hourObj = document.getElementById("hourSelect");
    //分下拉对象
    var minuteObj = document.getElementById("minuteSelect");
   
    
    /**
    *对年进行循环操作，下拉框默认加载当前年以及下一年
    */
    for(var i = curYear; i <= curYear + 1; i++) {
      addOptionObj(yearObj,i);
        
    }
    
    /**
    *对月进行循环操作，下拉框加载所有的月份
    */
    for(var j = 1; j <= 12; j++) {
        addOptionObj(monthObj,j);
    }
    /**
    *对日进行循环操作，下拉框加载所有的天数，天数应该根据月份来控制多少天
    */    
    for(var k = 1; k <= curMaxDay; k++) {
        
        
        addOptionObj(dayObj,k);
    }
    /**
    *对小时进行循环操作，下拉框加载一天的小时数
    */
    for(var l = 0; l <= 23; l++) {
      addOptionObj(hourObj,l);
    }
    /**
    *对分进行循环操作，下拉框加载60分钟
    */
    for(var m = 0; m < 60; m++) {
      addOptionObj(minuteObj,m);
    }
    
  
    
   
    
    
}
/**
*添加下拉框的值
*/
function addOptionObj(obj,value) {
    
    
    if(value<10) {
        value = "0" + value;
    }
    var optionProperty = document.createElement("option");
    optionProperty.text = value;     
    optionProperty.value = value;
       
    obj.add(optionProperty); 
}

/**
*当选择月的时候，重新加载月的天数
*/
function freshDay(obj)
{
   //年下拉对象
    var yearObj = openFrame.document.all("yearSelect");
    var yearValue = yearObj.value;
    
    //获得当月的最后一天使用
    var tmpDate = new Date(yearValue, obj.value, 0);
    //当月最大的天数
    var curMaxDay = tmpDate.getDate();
    
    
    //天下拉对象
    var dayObj = openFrame.document.all("daySelect");
    
    var len = dayObj.options.length
    //移除天的值
    for(var j = len; j >= 0; j--) {
        dayObj.options.remove(j);
    }
    
    
    
     /**
    *对日进行循环操作，下拉框加载所有的天数，天数应该根据月份来控制多少天
    */    
    for(var i = 1; i <= curMaxDay; i++) {
        
        
        addOptionObj(dayObj,i);
    }
    
    
    
}


this.setDefaultDate = function(obj) {
 
     //日期对象
    var curDate = new Date();
    //获得年
    var curYear = curDate.getFullYear();
    //获得月
    var curMonth = curDate.getMonth() + 1;
    //获得天
    var curDay = curDate.getDate();
    //获得小时
    var curHour = curDate.getHours();
    //获得分
    var curMinute = curDate.getMinutes();
   
     //年下拉对象
    var yearObj = obj.document.all("yearSelect");
    //月下拉对象
    var monthObj = obj.document.all("monthSelect");
    //日下拉对象
    var dayObj = obj.document.all("daySelect");
    //时下拉对象
    var hourObj = obj.document.all("hourSelect");
    //分下拉对象
    var minuteObj = obj.document.all("minuteSelect");
     
    defaultValue(yearObj,curYear);
    
    defaultValue(monthObj,curMonth);
    defaultValue(dayObj,curDay);
    defaultValue(hourObj,curHour);
    defaultValue(minuteObj,curMinute);
    
 }
 
 /**
 *显示当天的默认时间
*/
function defaultValue(obj,value) {
 
    if(value<10) {
        value="0" + value;
    }
    
    obj.value = value;
    
}
/**
*获得弹出的窗口
*/
function dateDiv(obj) {
   if(obj.checked) {
       var dateHtml = document.getElementById("dateSelect").innerHTML;  
       openFrame = openDivHtml(dateHtml,'确定','取消','350','150','设置定时发送时间','parent.getDate222()','parent.cancelDate()');
       /**
       *对下拉的时间设置当前默认时间
       */
       setDefaultDate(openFrame);
   } else {
      document.getElementById("orderValue").value='';
   }
   
  
}
/**
*获取确定的日期
*/
function getDivDate333() {
    //年下拉值
    var year = openFrame.document.all("yearSelect").value;
    //月下拉值
    var month = openFrame.document.all("monthSelect").value;
    //日下拉值
    var day = openFrame.document.all("daySelect").value;
    //时下拉值
    var hour = openFrame.document.all("hourSelect").value;
    //分下拉值
    var minute = openFrame.document.all("minuteSelect").value;
   
   //对日期进行拼接
    var resultDate = year+"-"+month+"-"+day+" "+hour+":"+minute;
    
    return resultDate;
}
/**
*得到时间，在文本中进行赋值
*/
function getDate222(){
    /**
     从弹出窗口中返回时间
    */
    var value  = getDivDate333();
    /**
    *判断时间是否为空，为空就不选中定时发送
    */
    if(value==null) {
        document.getElementById("orderTime").checked = false;
    }
    
    document.getElementById("orderValue").value = value;
    
    /**
    *获得弹出窗口对象，并关闭
    */
    var obj = new parent.dialog();
    obj.reset();
 
}

/**
*点击取消按钮时，定时发送复选框不选中，并关闭弹出窗口
*/
function cancelDate() {
   
   document.getElementById("orderTime").checked = false;
   document.getElementById("orderValue").value='';
   var obj = new parent.dialog();
   obj.reset();
}

/**
*获得实时当前时间
*/
function getCurTime() {
    //日期对象
    var curDate = new Date();
    //获得年
    var curYear = padValue(curDate.getFullYear());
    //获得月
    var curMonth = padValue(curDate.getMonth() + 1);
    //获得天
    var curDay = padValue(curDate.getDate());
    //获得小时
    var curHour = padValue(curDate.getHours());
    //获得分
    var curMinute = padValue(curDate.getMinutes());
     //对日期进行拼接
    var resultDate = curYear+"-"+curMonth+"-"+curDay+" "+curHour+":"+curMinute;
    return resultDate;
}

/**
 *时间不足两位补0显示
*/
function padValue(value) {
 
    if(value<10) {
        value="0" + value;
    }
    
   return value;
    
}