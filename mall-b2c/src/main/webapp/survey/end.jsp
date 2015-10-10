<%@ page language="java" import="java.util.*" pageEncoding="utf-8"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
 <head>
<title>调查表</title>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<link href="css/base.css" rel="stylesheet" type="text/css" />
<link href="css/layout.css" rel="stylesheet" type="text/css" />
<link href="css/survey.css" rel="stylesheet" type="text/css" />
<script type="text/javascript" src="js/jquery.min.js"></script>
<script type="text/javascript" src="js/global.js"></script>

<script>

var webRoot = '${webRoot}';

$(function(){

	$(".checkbox_show").click(function(){
		if(this.checked){
			$(this).parent().next().show();
		}else{
			$(this).parent().next().hide();
			$(".survey_textarea").val("");
			}
	});
	$("#survey_end_btn").click(function(){  
   		window.location.href=webRoot; 
	}); 
})


</script>


</head>

<body id="survey">
	<div id="survey_wrapper">
		<div id="survey_title">
           <img src="img/survey.jpg" /> 
        </div>
		<form id="form" action="" method="post" name="form1">
			<div id="survey_content">
            	<div id="survey_end">
					<img src="img/icon_sure.jpg" />
                    <h1>提交成功</h1>
                    <p>我们将认真查看您的反馈并对不足之处进行改进，<br />感谢您对移动商城的支持！</p>
                    <p><a class="col_link" href="javascript:void();" id="survey_end_btn">回首页</a></p>
                </div>
            </div>	
		</form>
	</div>
</body>
</html>
