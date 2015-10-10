<%@ page language="java" import="java.util.*" pageEncoding="ISO-8859-1"%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
  <head>
    
    <title>My JSP 'test.jsp' starting page</title>
    
	<meta http-equiv="pragma" content="no-cache">
	<meta http-equiv="cache-control" content="no-cache">
	<meta http-equiv="expires" content="0">    
	<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
	<meta http-equiv="description" content="This is my page">
	<!--
	<link rel="stylesheet" type="text/css" href="styles.css">
	-->
	<script language="javascript" src="../js/jquery.min.js"></script>
	
	<script language="javascript">
		$(document).ready(function() {
			$.get("http://localhost:8080/mall-b2c/center/region.chtml?ramdom="+Math.random(),function(data){
					$("#show").html(data.flag);
				}
			);
		});
	</script>
  </head>
  
  <body>
    <div id="show">
    	<img src="../img/btns.gif"/>
    </div>
  </body>
</html>
