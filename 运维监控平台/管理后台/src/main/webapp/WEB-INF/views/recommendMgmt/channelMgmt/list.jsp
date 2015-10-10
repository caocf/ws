<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<c:set var="inner_title" value="渠道管理" />
<%@ include file="../../includes/t.jsp"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
<meta http-equiv="cache-control" content="no-cache, must-revalidate"> 

<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/plugins/iframeTools.js"></script>
<script type="text/javascript" src="${ctx }/static/ui/js/artDialog/artDialog.js?skin=blue"></script>

<script type="text/javascript">
function showPost(channelId){
	var imgUrl = jQuery("#img_"+channelId).get(0).src;
	if(imgUrl.indexOf('zoomin.gif')!= -1){
		jQuery("#tr_"+channelId).show();
		jQuery("#img_"+channelId).attr("src","${ctx}/static/images/zoomout.gif")
	}
	
	if(imgUrl.indexOf('zoomout.gif') !=-1){
		jQuery("#tr_"+channelId).hide();
		jQuery("#img_"+channelId).attr("src","${ctx}/static/images/zoomin.gif")
	}
}
	function doSubmit(){
		var channel_name = $('#channel_name').val().replace(/(^\s*)|(\s*$)/g, "");
		var channel_desc = $('#channel_desc').val();
		
		var is_flag = $('#is_flag').find('option:selected').val();

		if(channel_name.length>20){
			art.dialog({
				time:2,
				content:'渠道名称长度不能大于20'
				});
			return false;
		}

		if(channel_desc.length>200){
			art.dialog({
				time:2,
				content:'渠道描述长度不能大于200'
				});
			return false;
		}
		
		if(channel_name==null||channel_name==""){
			$("#repeat-error").css("background", "#DA4F49");
               $("#repeat-error2").text("渠道名称不能为空！")
               return false;
		}else{
			$.ajax({ type:"POST",
			    url : '${ctx}/recommendMgmt/channelMgmt/saveChannel',
			    dataType : "json",
			    data:{
					channel_name:channel_name,
					channel_desc:channel_desc,
					is_flag:is_flag
				},
			    success : function(msg) {
			        if (msg=='1') {
			        	var dialog = art.dialog({
			        	    content: '新增操作成功',
			        	    icon: 'succeed',
			        	    ok: function(){
			        	        myDialog.close();
			        	        return true;
			        	    }
			        	});
					        	window.location.href='${ctx}/recommendMgmt/channelMgmt/';
			        } else {
			        	var dialog = art.dialog({
			        	    content: '新增操作失败',
			        	    icon: 'error',
			        	    ok: function(){
			        	        myDialog.close();
			        	        return true;
			        	    }
			        	});
			        }
			    }
			});
		}
	}

	function doUpdateChannel(){
		var channel_id = $('#channelID').val();
		var channel_name = $('#channelName').val().replace(/(^\s*)|(\s*$)/g, "");
		var channel_desc = $('#channelDesc').val();
		var is_flag = $('#isFlag').find('option:selected').val();

		if(channel_name.length>20){
			art.dialog({
				time:2,
				content:'渠道名称长度不能大于20'
				});
			return false;
		}

		if(channel_desc.length>200){
			art.dialog({
				time:2,
				content:'渠道描述长度不能大于200'
				});
			return false;
		}
		
		if(channel_name==null||channel_name==""){
			$("#repeat-error").css("background", "#DA4F49");
               $("#repeat-error2").text("渠道名称不能为空！")
               return false;
		}else{
			$.ajax({ type:"POST",
				url : '${ctx}/recommendMgmt/channelMgmt/saveUpdateChannel',
			    dataType : "json",
			    data:{
				    channel_id:channel_id,
					channel_name:channel_name,
					channel_desc:channel_desc,
					is_flag:is_flag
				},
				cache:false,
			    success:function(msg) {
			        if (msg=='1') {
				        $("#name_"+channel_id).text('');
				        $("#name_"+channel_id).text(channel_name);
				        $("#desc_"+channel_id).text('');
				        $("#desc_"+channel_id).text(channel_desc);
				        $("#is_flag_"+channel_id).text('');
				        if(is_flag=='0'){
				        	$("#is_flag_"+channel_id).text('否');
					     }else if(is_flag=='1'){
					    	 $("#is_flag_"+channel_id).text('是');
						 }
			        	var dialog = art.dialog({
			        	    content: '修改操作成功',
			        	    icon: 'succeed',
			        	    ok: function(){
			        			channelDialog.close();
			        	        return true;
			        	    }
			        	});
			        } else {
			        	var dialog = art.dialog({
			        	    content: '修改操作失败',
			        	    icon: 'error',
			        	    ok: function(){
			        			channelDialog.close();
			        	        return true;
			        	    }
			        	});
			        }
			    }
			});
		}
	}
	
	function doCancel(){
		channelDialog.close();
	}

	var myDialog;	
	function addChannel(){
		myDialog = art.dialog({title:'添加渠道',lock:true,width:'404px',height:300,background:'#CCFFFF'});
		jQuery.ajax({
		    url: '${ctx}/recommendMgmt/channelMgmt/addChannel',
		    success: function (data) {
		        myDialog.content(data);// 填充对话框内容
		    }
		});
	}
	

	var channelDialog;
	function updateChannel(obj,channelId){
		channelDialog = art.dialog({title:'修改渠道',lock:true,width:'404px',height:300,background:'#CCFFFF'});
		jQuery.ajax({
		    url: '${ctx}/recommendMgmt/channelMgmt/updateChannel',
		    data:{
			    channelId:channelId
			},
			cache:false,
		    success: function (data) {
				channelDialog.content(data);// 填充对话框内容
		    }
		});
	}

	var postDialog;
	var channel;
	function addPost(channelId){
		channel = channelId;
		postDialog = art.dialog({title:'添加营销位',width:'550px',height:400,lock:true,background:'#CCFFFF'});
		jQuery.ajax({
			type:"POST",
			url:'${ctx}/recommendMgmt/channelMgmt/addPost',
			cache:false,
			success:function(data){
				postDialog.content(data);
			}
		});
	}


	//添加营销位取消按钮操作
 	function doShut(){
 		postDialog.close();
 	}
	
	//添加营销位确定按钮操作
	function onSubmit(){
		var post_name = $('#post_name').val();
		var percent="";
		var boxForName = document.getElementsByName("percent");
		  for(var i = 0;i < boxForName.length;i++){
		   	if(boxForName[i].type="checkbox"){
		    	if(boxForName[i].checked){
		    	 	percent += (boxForName[i].value +':1.0;');
		    	}
		   	}
		  }
	
		  
		var audit_flag ;
		if($('#audit_flag')[0].checked){
			audit_flag = $('#audit_flag')[0].value;
		}
		var filter_flag;
		if($('#filter_flag')[0].checked){
			 filter_flag = $('#filter_flag')[0].value;
		}
	
		var limit_site = $('#limit_site').find('option:selected').val();
		
		var post_desc = $('#post_desc').val();
		var content_num = $('#content_num').val();

		if(post_name.length>20){
			art.dialog({
				time:2,
				content:'营销位名称长度不能大于20'
				});
			return false;
		}

		if(post_desc.length>200){
			art.dialog({
				time:2,
				content:'营销位描述长度不能大于200'
				});
			return false;
		}

		
		if(content_num==null||(content_num-0)==""){
			art.dialog({
				time:2,
				content:'推荐营销位条数不能为空'
				});
			return false;
		}
		
		if(isNaN(content_num)||content_num.length>6){
			art.dialog({
				time:2,
				content:'推荐营销位条数必须为数字且长度不能大于6'
				});
			return false;
		}

		if(percent==null||percent.replace(/(^\s*)|(\s*$)/g, "")==""){
			art.dialog({
				time:2,
				content:'推荐引擎不能为空'
				});
			return false;
		}

		
		if(post_name==null||post_name.replace(/(^\s*)|(\s*$)/g, "")==""){
			$("#repeat-error").css("background", "#DA4F49");
               $("#repeat-error2").text("营销位名称不能为空！")
               return false;
		}else{
			$.ajax({
			    url : '${ctx}/recommendMgmt/channelMgmt/savePost',
			    type:'post',
			    dataType : "json",
			    data:{
				    channelId:channel,
				    post_name:post_name,
				    post_desc:post_desc,
				    audit_flag:audit_flag,
				    filter_flag:filter_flag,
				    limit_site:limit_site,
				    content_num:content_num,
				    percent:percent
				},
			    success : function(msg) {
			        if (msg=='1') {
			        	var dialog = art.dialog({
			        	    content: '新增操作成功',
			        	    icon: 'succeed',
			        	    ok: function(){
			        			postDialog.close();
			        	        return true;
			        	    }
			        	});
			        	window.location.href = '${ctx}/recommendMgmt/channelMgmt/';
			        } else {
			        	var dialog = art.dialog({
			        	    content: '新增操作失败',
			        	    icon: 'error',
			        	    ok: function(){
			        			postDialog.close();
			        	        return true;
			        	    }
			        	});
			        }
			    }
			});
		}
	}

	var  updatePostDialog;
	function updatePostItem(channelId,postId){
		updatePostDialog = art.dialog({title:'修改营销位',width:'550px',height:400,lock:true,background:'#CCFFFF'});
		jQuery.ajax({
			type:"POST",
			url:'${ctx}/recommendMgmt/channelMgmt/updatePost',
			data:{
				channelId:channelId,
				postId:postId
				},
			cache:false,
			success:function(data){
				updatePostDialog.content(data);
			}
		});
	}

	function deleteChannel(channelId){
		art.dialog({
		    content: '确定要删除此条数据？',
		    ok: function () {
				$.ajax({ type:"POST",
				    url : '${ctx}/recommendMgmt/channelMgmt/deleteChannel',
				    dataType : "json",
				    data:{
				    	channel_id:channelId
					},
				    success:function(msg) {
				        if (msg>='1') {
				        	$("tr[id="+channelId+"]").remove();
<%--				        	var testTable = document.getElementById("table_"+channelId);--%>
<%--				        	while(testTable.hasChildNodes()){--%>
<%--				        		testTable.removeChild(testTable.lastChild);--%>
<%--				        	}--%>

							jQuery("#tr_"+channelId).hide();
				        	var dialog = art.dialog({
				        	    content: '删除成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        }else {
				        	var dialog = art.dialog({
				        	    content: '删除失败',
				        	    icon: 'error',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        }
				    }
				});
		    	return true;
		    },
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
	}

	function onCancel(){
		updatePostDialog.close();
	}
	
	function doUpdate(){
		var post_name = $('#post_name').val();
		var channelId = $('#channel_id').val();
		var post_id = $('#post_id').val();
		var percent="";
		var post_desc = $('#post_desc').val();
		var boxForName = document.getElementsByName("percent");
		  for(var i = 0;i < boxForName.length;i++){
		   if(boxForName[i].type="checkbox"){
		   	if(boxForName[i].checked){
		    	percent += (boxForName[i].value +':1.0;');
		    }
		   }
		  }
		  var audit_flag ;
			if($('#audit_flag')[0].checked){
				audit_flag = $('#audit_flag')[0].value;
			}
			var filter_flag;
			if($('#filter_flag')[0].checked){
				 filter_flag = $('#filter_flag')[0].value;
			}

			if(post_name.length>20){
				art.dialog({
					time:2,
					content:'营销位名称长度不能大于20'
					});
				return false;
			}

			if(post_desc.length>200){
				art.dialog({
					time:2,
					content:'营销位描述长度不能大于200'
					});
				return false;
			}
			var limit_site = $('#limit_site').find('option:selected').val();
			
			

		
			
			var content_num = $('#content_num').val();

			if(isNaN(content_num)||content_num.length>6){
				art.dialog({
					time:2,
					content:'推荐营销位条数必须为数字且长度不能大于6'
					});
				return false;
			}
			if(percent==null||percent.replace(/(^\s*)|(\s*$)/g, "")==""){
				art.dialog({
					time:2,
					content:'推荐引擎不能为空'
					});
				return false;
			}

			if(content_num==null||(content_num-0)==""){
				art.dialog({
					time:2,
					content:'推荐营销位条数不能为空'
					});
				return false;
			}
			
		  if(post_name==null||post_name.replace(/(^\s*)|(\s*$)/g, "")==""){
				$("#repeat-error").css("background", "#DA4F49");
	               $("#repeat-error2").text("营销位名称不能为空！")
	               return false;
			}else{
				$.ajax({ type:"POST",
				    url : '${ctx}/recommendMgmt/channelMgmt/saveUpdatePost',
				    dataType : "json",
				    data:{
				    	channel_id:channelId,
					    post_name:post_name,
					    post_desc:post_desc,
					    audit_flag:audit_flag,
					    filter_flag:filter_flag,
					    limit_site:limit_site,
					    content_num:content_num,
					    percent:percent,
					    post_id:post_id
					},
				    success:function(msg) {
				        if (msg=='1') {
					        $("#post_name_"+post_id+'_'+channelId).text('');
					        $("#post_name_"+post_id+'_'+channelId).text(post_name);
					        $("#post_desc_"+post_id+'_'+channelId).text('');
					        $("#post_desc_"+post_id+'_'+channelId).text(post_desc);
				        	var dialog = art.dialog({
				        	    content: '修改操作成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        			updatePostDialog.close();
				        	        return true;
				        	    }
				        	});
				        } else {
				        	var dialog = art.dialog({
				        	    content: '修改操作失败',
				        	    icon: 'error',
				        	    ok: function(){
				        			updatePostDialog.close();
				        	        return true;
				        	    }
				        	});
				        }
				    }
				});
			}
	}
	
	function deletePostItem(channelId,postId){
		art.dialog({
		    content: '确定要删除此条数据？',
		    ok: function () {
				$.ajax({ type:"POST",
				    url : '${ctx}/recommendMgmt/channelMgmt/deletePost',
				    dataType : "json",
				    data:{
				    	channel_id:channelId,
					    post_id:postId
					},
					cache:false,
				    success:function(msg) {
				        if (msg=='1') {
					        $("tr[id="+postId+'_'+channelId+"]").remove();
				        	var dialog = art.dialog({
				        	    content: '删除成功',
				        	    icon: 'succeed',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        } else {
				        	var dialog = art.dialog({
				        	    content: '删除失败',
				        	    icon: 'error',
				        	    ok: function(){
				        	        return true;
				        	    }
				        	});
				        }
				    }
				});
		    	return true;
		    },
		    cancelVal: '关闭',
		    cancel: true //为true等价于function(){}
		});
	}

	$(function()
			{


			    var pageDiv1=new jsPage("list","all","p1","3"); 
			    pageMethod.call(pageDiv1);

			    var pageDiv2=new jsPage("list2","all","p2","5"); 
			    pageMethod.call(pageDiv2);
			})//$
			//jquery 页面加载结束
			//方法: pageMethod
			function pageMethod()
			{
			    var obj=this;
			    obj.resetTotal();  
			    obj.reloadpage("1",obj.list_class);
			    obj.page(); //生成页码
			    ready2go.call(obj);
			}
			//方法:ready2go
			function ready2go()
			{
			    var obj=this;
			    $("#"+obj.page_obj_id+" a").live("click",function()
			    { //点击页码的时候跳到相应页    
			    obj.target_p=parseInt($(this).attr("p"));
			    gotopage.call(obj,obj.target_p);
			    })

			     
			}
			// 方法: showClass
			function showClass(list,x,pDiv,pSize){    
			    var pObj=new jsPage(list,x,pDiv,pSize); 
			    pageMethod.call(pObj); 
			}
			// 方法: jsPage
			function jsPage(list_id,list_class,page_obj_id,pagesize)
			{
			    // list_id 结果集UL的id
			    // list_class 要显示的类别
			    // page_id 存放页码的id
			// pagesize 每页显示多少条
			// QQ群号: 29032448
			    this.list_obj_id=list_id;
			    this.list_Obj=$("#"+list_id);
			    this.li=$("#"+list_id+" li");
			    this.li.hide();
			    this.page_obj_id=page_obj_id;
			    this.page_obj=$("#"+page_obj_id); //存放页码的div
			    this.list_class=list_class; // 类别       
			    if(list_class=="all")
			    {
			        this.results=this.li.length; // 总记录数等于所有记录
			    }
			    else
			    {
			       this.results=$("#"+list_id+" li."+list_class).length; // 总记录数等于指定类别的li数  
			    }  
			    
			    this.totalpage; // 总页数
			    this.pagesize=pagesize; //每页记录数
			    this.cpage=1; //当前页,默认显示第一页
			    this.count; 
			    this.target_p;
			    this.curcount;
			    this.outstr= ""; // 输出页码html       
			}//jsPage结束
			//方法: gotopage
			function gotopage(target){      
			    this.cpage = target;        //把页面计数定位到第几页
			    this.page();
			    this.reloadpage(target,this.list_class);    
			}
			//给对象jsPage 增加函数 reloadpage
			jsPage.prototype.reloadpage=function(p,resultType)
			{
			    this.li.hide();
			    for(var i=this.pagesize*p-this.pagesize;i<this.pagesize*p;i++)
			    { 
			       if(resultType=="all")
			       {
			        this.li.eq(i).show();
			       }else
			       {
			        $("#"+this.list_obj_id+" li."+resultType).eq(i).show();
			       }
			    } 
			}
			//给对象jsPage 增加函数 resetTotal
			jsPage.prototype.resetTotal=function()
			{ 
			    if(this.results==0){
			       this.totalpage=0;
			       this.cpage=0;
			    }else if(this.results<=this.pagesize)
			    {
			       this.totalpage=1;
			    }
			    else if(parseInt(this.results/this.pagesize)==1)
			    {
			       this.totalpage=2;
			    }
			    else if(parseInt(this.results/this.pagesize)>1 && this.results%this.pagesize==0){
			       this.totalpage=this.results/this.pagesize;
			    }
			    else
			    {
			       this.totalpage=parseInt(this.results/this.pagesize)+1;
			    } 
			}//resetTotal()
			//jsPage 对象增加函数 page
			jsPage.prototype.page=function()
			{
			    if(this.totalpage<=10)
			    {        //总页数小于十页   页码以十页为单位
			        for (this.count=1;this.count<=this.totalpage;this.count++) 
			        {    
			            if(this.count!=this.cpage) 
			            {
			                this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >"+this.count+"</a>";
			            }else
			            {
			                this.outstr = this.outstr + "<span class='current' >"+this.count+"</span>";
			            }
			        }
			    }
			    if(this.totalpage>10)
			    {        //总页数大于十页
			        if(parseInt((this.cpage-1)/10) == 0) 
			        {            
			            for (this.count=1;this.count<=10;this.count++) 
			            {    
			                if(this.count!=this.cpage) 
			                {
			                    this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >"+this.count+"</a>";
			                }
			                else
			                {
			                    this.outstr = this.outstr + "<span class='current'>"+this.count+"</span>";
			                }
			            }
			            this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >&raquo;</a>";
			        }
			        else if(parseInt((this.cpage-1)/10) == parseInt(this.totalpage/10))
			        {    
			            this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+(parseInt((this.cpage-1)/10)*10)+"' >&laquo;<\/a>";
			            for (this.count=parseInt(this.totalpage/10)*10+1;this.count<=this.totalpage;this.count++)  
			             { 
			                if(this.count!=this.cpage) 
			                {
			                    this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >"+this.count+"</a>";
			                }
			                else
			                {
			                    this.outstr = this.outstr + "<span class='current'>"+this.count+"</span>";
			                }
			            }
			        } 
			        else
			        {   
			            var lastP;
			            this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+(parseInt((this.cpage-1)/10)*10)+"' >&laquo;<\/a>";
			            for (this.count=parseInt((this.cpage-1)/10)*10+1;this.count<=parseInt((this.cpage-1)/10)*10+10;this.count++)
			            {        
			                if(this.count!=this.cpage) 
			                {
			                    this.outstr = this.outstr + "<a href='javascript:void(0)' p='"+this.count+"' >"+this.count+"</a>";
			                }
			                else
			                {
			                    this.outstr = this.outstr + "<span class='current'>"+this.count+"</span>";
			                }
			                if(this.count==this.totalpage)
			                {
			                    lastP="";
			                }
			                else
			                {
			                    lastP="<a href='javascript:void(0)' p='"+(this.count+1)+"' >&raquo;</a>";
			                }
			            } 
			            this.outstr = this.outstr + lastP;
			        }
			    }    
			    $("#"+this.page_obj_id).val("<div><span id='info'>共"+this.totalpage+"页 第"+this.cpage+"页<\/span>" + this.outstr + "<\/div>");
			  //  document.getElementById(this.page_obj_id).value = "<div><span id='info'>共"+this.totalpage+"页 第"+this.cpage+"页<\/span>" + this.outstr + "<\/div>";
			    this.outstr = "";
			}

	
</script>

<body>
<div class="row-fluid">
	<div class="span12">
<%--		<div style="text-align: right; margin: 15px 0px">--%>
<%--			<a href="javascript:addChannel()" id="btn"--%>
<%--				style="padding-right: 20px"> <font style="font-weight: bold;"--%>
<%--				class="btn">添加渠道</font> </a>--%>
<%--		</div>--%>

		<c:if test="${empty channelList.data}">
			<div class="alert alert-info">
				<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
			</div>
		</c:if>

		<c:if test="${not empty channelList.data}">
			<table id="tableId" class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>
							渠道ID
						</th>
						<th>
							渠道名称
						</th>
						<th>
							描述
						</th>
						<th>
							营销位
						</th>
						<th>
							允许空
						</th>
						<th>
							创建时间
						</th>
<%--						<th>--%>
<%--							操作--%>
<%--						</th>--%>
					</tr>
				</thead>
<input type="hidden" value="${page}"/>
				<c:forEach items="${channelList.data}" var="channel">
					<tr id="${channel.channel_id}">
						<td id="id_${channel.channel_id}">
							<img id="img_${channel.channel_id}"
								onclick="showPost('${channel.channel_id}')"
								src="${ctx}/static/images/zoomin.gif" />
							${channel.channel_id}
						</td>
						<td id="name_${channel.channel_id}">
							${channel.channel_name}
						</td>
						<td id="desc_${channel.channel_id}">
							${channel.channel_desc}
						</td>
						<td>
							${channel.postNum}
						</td>
						<td id="is_flag_${channel.channel_id}">
							<c:choose>
								<c:when test="${not empty channel.is_flag && channel.is_flag == 0}">否</c:when>
								<c:otherwise>是</c:otherwise>
							</c:choose> 
						</td>
						<td>
							<ct:time source="${channel.create_time}" sfmt="yyyyMMddHHmmss"
								tfmt="yyyy-MM-dd HH:mm:ss" />
									
						</td>
<%--						<td>--%>
<%----%>
<%--							<a href="javascript:addPost('${channel.channel_id}')">添加</a>--%>
<%--							|--%>
<%--							<a on id="hrefId_${channel.channel_id }"   onclick="updateChannel(this,'${channel.channel_id }')">修改</a>--%>
<%--							|--%>
<%--							<a href="javascript:deleteChannel('${channel.channel_id}')">删除</a>--%>
<%----%>
<%--						</td>--%>
					</tr>
					<tr id="tr_${channel.channel_id }" style="display: none">
						<td colspan="7" align="center">
							<ul id="list">
							<table width="100%" id="table_${channel.channel_id }" class="table table-bordered" >
								<tr>
									<th>
										营销位ID
									</th>
									<th>
										营销位名称
									</th>
									<th >
										营销位描述
									</th>
<%--									<th>--%>
<%--										操作--%>
<%--									</th>--%>
								</tr>
								<c:forEach var="post" items="${channel.postList}">
									<c:if test="${not empty post}">
										<tr id="${post.post_id}_${channel.channel_id }">
											<td >
												${post.post_id }
											</td>
											<td id='post_name_${post.post_id}_${channel.channel_id }'>
												${post.post_name }
											</td>
											<td id="post_desc_${post.post_id}_${channel.channel_id}">
												${post.post_desc }
											</td>
<%--											<td>--%>
<%--												<a--%>
<%--													href="javascript:updatePostItem('${channel.channel_id }','${post.post_id }')">设置</a>&nbsp;--%>
<%--												|--%>
<%--												<a--%>
<%--													href="javascript:deletePostItem('${channel.channel_id }','${post.post_id }')">删除</a>&nbsp;--%>
<%--											</td>--%>
										</tr>
									</c:if>
								</c:forEach>
							</table>
							</ul>
						</td>

					</tr>
				</c:forEach>
 <div id="p1">
			    </div>
			</table>
			<ht:page pageData="${channelList}" />
		</c:if>

	</div>
</div>
</body>
</html>
<script language="javascript" src="${ctx }/static/js/abouttable.js"></script>