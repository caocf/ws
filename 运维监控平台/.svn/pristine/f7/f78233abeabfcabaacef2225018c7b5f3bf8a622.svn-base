 	
	// 树节点1初始化
	function initTree(id,name,pid,pname){
			// 创建父节点
			var lpli="<li id='l"+pid+"'>";
				lpli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
				lpli+="<a href='#' ondblclick=\"nodeOnclick('"+pid+"','"+pname+"','treeUl2');\">"+pname+"</a>";
				lpli+="<ul id='"+id+"' name='"+pname+"'>";

			var	lcli="<li class=\"Child\" id='"+id+"' name='"+name+"' pid='"+pid+"'>";
				lcli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
				lcli+="<a href='#' ondblclick=\"nodeOnclick2('"+id+"','"+name+"','"+pid+"','"+pname+"');\">"+name+"</a>";
				lcli+="</li>"; 
	 
		
	}
	// 节点1被双击
	function nodeOnclick(id,name,pid,pname){
		nodeAdd(id,name,pid,pname);
	}
	
	// 第2个树点击事件删除
	function nodeOnclick2(id,name,pid){
		// 如果是父节点删除
		if(pid=="treeUl2"){
			var pUid=document.getElementById(pid).childNodes;
			var lliHtml="";
			for (var i=0;i<pUid.length;i++){
				if(pUid[i].id!="ll"+id){
					lliHtml+="<li id='"+pUid[i].id+"'>"+pUid[i].innerHTML+"</li>";
				}
			}
			document.getElementById("treeUl2").innerHTML=lliHtml;
		 
		}else{

			var pUid=document.getElementById(pid).childNodes;
			
			// 如果子节点为最后一个,连带删除父节点
			if(pUid.length==1){
				var treeUl2=document.getElementById("treeUl2").childNodes;
				var pul=document.getElementById(pid);
				var lliHtml="";
				for(var i=0;i<treeUl2.length;i++){
					if(treeUl2[i].id!=pul.parentElement.id){
						lliHtml+="<li id='"+treeUl2[i].id+"'>"+treeUl2[i].innerHTML+"</li>";
					}

				}
				// 重绘
				document.getElementById("treeUl2").innerHTML=lliHtml;
				
				// 删除父节点后程序截止
				return;				 
			}
			
			var lliHtml="";
			for (var i=0;i<pUid.length;i++){
				if(pUid[i].id!=id){
					lliHtml+="<li class=\"Child\" id='"+pUid[i].id+"' name='"+pUid[i].name+"' pid='"+pUid[i].pid+"'>"+pUid[i].innerHTML+"</li>";
				}
			}
			document.getElementById(pid).innerHTML=lliHtml;
		
		}
	 
 
	}

	// 添加节点到第2个树
	function nodeAdd(id,name,pid,pname){
		var treeUl2Child=document.getElementById("treeUl2").childNodes;
		if(treeUl2Child.length!=0){
			 // 如果不为空点击父节点
			if(pid=="treeUl"){ 
				//判断是否存在， 
				var isNext=0;
				for(var i=0;i<treeUl2Child.length;i++){
					if(treeUl2Child[i].id=="ll"+id){
						 isNext=1;
					}
				}
				// 如果父节点存在则覆盖
				if(isNext==1){
						//document.getElementById("ll"+id).innerHTML="";
						// 获得当前ID的父节点
						var pNode=document.getElementById(id).childNodes;

						// 创建一个li到tree2
						var pli="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/../static/js/tree/css/s.gif\">";
							pli+="<a href='#' ondblclick=\"nodeOnclick2('"+id+"','"+name+"','treeUl2');\">"+name+"</a>";
							pli+="<ul id='u"+id+"' name='"+name+"'>";
				 
						 // 遍历父节点下所有子节点数据进行拼接创建到treeUl2
						 var cli="";
						 for(var i=0;i<pNode.length;i++){
							 cli+="<li class=\"Child\" id='"+pNode[i].id+"' name='"+pNode[i].name+"' pid='u"+id+"'>";
							 cli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
							 cli+="<a href='#' ondblclick=\"nodeOnclick2('"+pNode[i].id+"','"+pNode[i].name+"','u"+id+"');\">"+pNode[i].name+"</a>";
							 cli+="</li>"; 
						 }
						 // 覆盖到当前li
				
						 document.getElementById("ll"+id).innerHTML=pli+cli+"</ul>";
				
				}else{

						//如果不存在则创建

						 // 备份原先的li
						 var lliHtml="";
						 for(var i=0;i<treeUl2Child.length;i++){
							  lliHtml+="<li id='"+treeUl2Child[i].id+"'>"+document.getElementById(treeUl2Child[i].id).innerHTML+"</li>";
							   
						  }
						 // 获得当前ID的父节点
						var pNode=document.getElementById(id).childNodes;
						
						 // 获得treeUl2
						var treeUl2 = document.getElementById("treeUl2");

						// 创建一个li父节点到tree2
						var pli="<li id='ll"+id+"'>";
							pli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
							pli+="<a href='#' ondblclick=\"nodeOnclick2('"+id+"','"+name+"','treeUl2');\">"+name+"</a>";
							pli+="<ul id='u"+id+"' name='"+name+"'>";
				 
						 // 遍历父节点下所有子节点数据进行拼接创建到treeUl2
						 var cli="";
						 for(var i=0;i<pNode.length;i++){
							 cli+="<li class=\"Child\" id='"+pNode[i].id+"' name='"+pNode[i].name+"' pid='u"+id+"'>";
							 cli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
							 cli+="<a href='#' ondblclick=\"nodeOnclick2('"+pNode[i].id+"','"+pNode[i].name+"','u"+id+"');\">"+pNode[i].name+"</a>";
							 cli+="</li>"; 
						 }
						 // 加上备份的一起写入到页面
						 treeUl2.innerHTML=lliHtml+pli+cli+"</ul></li>";
				
				}
				 
			}else{
				// 如果不为空添加子节点

				//判断是否存在，父节点
				var isNext=0;
				for(var i=0;i<treeUl2Child.length;i++){
					if(treeUl2Child[i].id=="ll"+pid){
						 isNext=1;
					}
				}
				// 如果存在父节点
				if(isNext==1){
					 
					 
					 var pul=document.getElementById("u"+pid).childNodes;
					 var isUl=0;
					 for(var i=0;i<pul.length;i++){
						   if(pul[i].id==id){
							isUl=1;
						   }
						    
					  }
					  // 如果没有则添加,防重复
					  if(isUl==0){
						   // 备份原先的ul
						   var ulHtml="";
						   for(var i=0;i<pul.length;i++){
								ulHtml+="<li shopIds='"+pul[i].id+"'  class=\"Child\" id='"+pul[i].id+"' name='"+pul[i].name+"' pid='u"+pid+"'>";
								ulHtml+=document.getElementById(pul[i].id).innerHTML+"</li>";
						   }

						    var cli="";
						    cli+="<li class=\"Child\" id='"+id+"' name='"+name+"' pid='u"+pid+"'>";
						    cli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
							cli+="<a href='#' ondblclick=\"nodeOnclick2('"+id+"','"+name+"','u"+pid+"');\">"+name+"</a>";
							cli+="</li>"; 
							
							document.getElementById("u"+pid).innerHTML=ulHtml+cli;
					  
					  }
					  
					 
				
				}else{
					// 创建一个li父节点到tree2
					var pli="<li id='ll"+pid+"'>";
						pli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
						pli+="<a href='#' ondblclick=\"nodeOnclick2('"+pid+"','"+pname+"','treeUl2');\">"+pname+"</a>";
						pli+="<ul id='u"+pid+"' name='"+pname+"'>";
					
					// 创建当前子节点到tree2
					var  cli="<li class=\"Child\" id='"+id+"' name='"+name+"' pid='"+pid+"'>";
						 cli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
						 cli+="<a href='#' ondblclick=\"nodeOnclick2('"+id+"','"+name+"','u"+pid+"');\">"+name+"</a>";
						 cli+="</li>"; 

					 // 备份原先的li
					 var lliHtml="";
					 for(var i=0;i<treeUl2Child.length;i++){
						  lliHtml+="<li id='"+treeUl2Child[i].id+"'>"+document.getElementById(treeUl2Child[i].id).innerHTML+"</li>";
						   
					  }
					 
					 // 带备份一起写入到页面
					 document.getElementById("treeUl2").innerHTML=lliHtml+pli+cli+"</ul></li>";
				
				}
				 
				 
			}
		}else{
			// 如果初始化为空并且点击父节点
			if(pid=="treeUl"){
				// 获得当前ID的父节点
				var pNode=document.getElementById(id).childNodes;
				
				 // 获得treeUl2
				var treeUl2 = document.getElementById("treeUl2");

				// 创建一个li父节点到tree2
				var pli="<li id='ll"+id+"'>";
					pli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
					pli+="<a href='#' ondblclick=\"nodeOnclick2('"+id+"','"+name+"','treeUl2');\">"+name+"</a>";
					pli+="<ul id='u"+id+"' name='"+name+"'>";
		 
				 // 遍历父节点下所有子节点数据进行拼接创建到treeUl2
				 var cli="";
				 for(var i=0;i<pNode.length;i++){
					 cli+="<li class=\"Child\" id='"+pNode[i].id+"' name='"+pNode[i].name+"' pid='"+id+"'>";
					 cli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
					 cli+="<a href='#' ondblclick=\"nodeOnclick2('"+pNode[i].id+"','"+pNode[i].name+"','u"+id+"');\">"+pNode[i].name+"</a>";
					 cli+="</li>"; 
				 }
				 // 写入到页面
				 treeUl2.innerHTML=pli+cli+"</ul></li>";
				  
			 
			}else{
				// 如果treeUl2为空,则添加子节点

				// 创建一个li父节点到tree2
				var pli="<li id='ll"+pid+"'>";
					pli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
					pli+="<a href='#' ondblclick=\"nodeOnclick2('"+pid+"','"+pname+"','treeUl2');\">"+pname+"</a>";
					pli+="<ul id='u"+pid+"' name='"+pname+"'>";
				
				// 创建当前子节点到tree2
				var  cli="<li class=\"Child\" id='"+id+"' name='"+name+"' pid='"+pid+"'>";
					 cli+="<img class=s onclick=\"ExCls(this,'Opened','Closed',1);\" alt=展开/折叠 src=\"../static/js/tree/css/s.gif\">";
					 cli+="<a href='#' ondblclick=\"nodeOnclick2('"+id+"','"+name+"','u"+pid+"');\">"+name+"</a>";
					 cli+="</li>"; 
			     
				 // 写入到页面
				 document.getElementById("treeUl2").innerHTML=pli+cli+"</ul></li>";

					 
			}

		}
		 
 
	
	}
 
 