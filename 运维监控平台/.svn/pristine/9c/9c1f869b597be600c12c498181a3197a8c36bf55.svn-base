<%@ page language="java" import="java.util.*" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %> 
<%@ include file="../../includes/if.jsp"%>
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://"
			+ request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html>
	<head>
		<base href="<%=basePath%>">
	<script type="text/javascript">
	function showPost(channelId,time){
		var imgUrl = jQuery("#img_"+channelId+"_"+time).get(0).src;
		if(imgUrl.indexOf('zoomin.gif')!= -1){
			jQuery("#tr_"+channelId+"_"+time).show();
			jQuery("#img_"+channelId+"_"+time).attr("src","${ctx}/static/images/zoomout.gif")
		}
		
		if(imgUrl.indexOf('zoomout.gif') !=-1){
			jQuery("#tr_"+channelId+"_"+time).hide();
			jQuery("#img_"+channelId+"_"+time).attr("src","${ctx}/static/images/zoomin.gif")
		}
	}

	function queryList(){
		var selectedDate = $("#date").val();
		$('#queryForm').action="${ctx}/recommendMgmt/effect/effectInfo?selectedDate="+selectedDate;
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
			    document.getElementById(this.page_obj_id).innerHTML = "<div><span id='info'>共"+this.totalpage+"页 第"+this.cpage+"页<\/span>" + this.outstr + "<\/div>";
			    this.outstr = "";
			}

	
	</script>
	</head>
	<body>
		<form class="form-horizontal" action="" name="queryForm"
			id="queryForm" method="get">
			<div class="row-fluid">
				<div class="span12">
					<div class="tab-content">
						<div class="tab-pane fade in active" id="home">
							<div class="control-group group-search" style="margin-left: 0px;">
								<label class="control-label" for="queryStartTime">
									选择时间：
								</label>
								<div class="controls controls-row">
									<div id="from" class="input-append date"
										data-date-format="yyyy-mm-dd" data-provide="datepicker-inline">
										<input id="date" name=selectedDate type="text" value="${selectedDate}">
										<span class="add-on"> <i class="icon16 i-calendar-4"></i></span>
									</div>
									<input type="radio" name="dateType" value="2"
										<c:if test="${not empty dateType && dateType == '2'}"> checked="checked"</c:if> />
									当日
									<input type="radio" name="dateType" value="0"
										<c:if test="${ empty dateType || dateType == '0'}"> checked="checked"</c:if> />
									当月
									<input type="radio" name="dateType" value="3"
										<c:if test="${not empty dateType && dateType == '3'}"> checked="checked"</c:if> />
									过去30天
									<input type="radio" name="dateType" value="1"
										<c:if test="${not empty dateType && dateType == '1'}"> checked="checked"</c:if> />
									过去6个月
									<br />
								</div>
							</div>


							<div class="control-group group-search">
								<label class="control-label" for="channel_name">
									渠道名称：
								</label>
								<div class="controls controls-row">
									<input type="text" id="channel_name" style="width: 200px" maxlength="10" name="channel_name" class="span10" value="${channel_name}" />
									&nbsp;&nbsp;&nbsp;&nbsp;
									<button  class="btn btn-primary" onclick=queryList();>
										<i class="icon16 i-search"></i> 过滤
									</button>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>
		</form>
		<div>
			<c:if test="${empty channelEffect.data}">
					<div class="alert alert-info">
						<strong> <i class="icon24 i-info"></i> 当前没有相关记录！ </strong>
					</div>
			</c:if>
			<c:if test="${not empty channelEffect.data}">
			<table class="table table-bordered table-hover table-striped">
				<thead>
					<tr>
						<th>
							渠道名称
						</th>
						<th>
							时间
						</th>
						<th>
							点击次数
						</th>
						<th>
							推荐次数
						</th>
						<th>
							推荐成功率
						</th>
						
					</tr>
				</thead>

				<c:forEach items="${channelEffect.data}" var="channel">
					<tr>
						<td>
							<img id="img_${channel.channelid}_${channel.date}"
								onclick="showPost('${channel.channelid}','${channel.date}')"
								src="${ctx}/static/images/zoomin.gif" />
							${channel.channel_name}
						</td>
						<td>
<%--						<ct:time source="${channel.date}" sfmt="yyyyMMddHHmmss" tfmt="yyyy-MM-dd HH:mm:ss" />--%>
							${channel.date}
						</td>
						<td>
							${channel.clickpv}
						</td>
						<td>
							${channel.recpv}
						</td>
						<c:if test="${channel.clickpv==0 && channel.recpv==0}">
							<td>0%</td>
						</c:if>
							<td>
								
								<c:if test="${not empty channel.recpv && channel.recpv!=0 && not empty channel.clickpv}">
										<fmt:formatNumber pattern="#.##%" value="${channel.clickpv/channel.recpv}"></fmt:formatNumber>
								</c:if>
								<c:if test="${not empty channel.recpv && channel.recpv!=0 && empty channel.clickpv}">
										<fmt:formatNumber pattern="#.##%" value="${0/channel.recpv}"></fmt:formatNumber>
								</c:if>
								<c:if test="${ empty channel.recpv}">
									∞
								</c:if>
							</td>
						
					</tr>
					<tr id="tr_${channel.channelid }_${channel.date}" style="display: none">
						<td align="center" colspan="5">
							<table width="100%" class="table table-bordered table-hover table-striped" id="list">
								<tr>
									<th>
										营销位名称
									</th>
									<th>
										时间
									</th>
									<th>
										点击次数
									</th>
									<th>
										推荐次数
									</th>
									<th>
										推荐成功率
									</th>
								</tr>
								<c:forEach var="post" items="${channel.postEffect}">
									<c:if test="${not empty post}">
										<tr>
											<td>
												${post.postName }
											</td>
											<td>
												${post.date}
											</td>
											<td>
												${post.clickpv}
											</td>
											<td>
												${post.recpv}
											</td>
											<c:if test="${post.clickpv==0 && post.recpv==0}">
												<td>0%</td>
											</c:if>
											<c:if test="${post.clickpv!=0 || post.recpv!=0}">
												<td>
													<fmt:formatNumber pattern="#.##%" value="${post.clickpv/post.recpv}"></fmt:formatNumber>
												</td>
											</c:if>
										</tr>
									</c:if>
								</c:forEach>
							</table>
						</td>
					</tr>
				</c:forEach>
			 <div id="p1">
			    </div>
			</table>
			<ht:page pageData="${channelEffect}"  />
		</c:if>
		</div>
	</body>
</html>
