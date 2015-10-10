<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <head>
    <ht:head/>
    <script type="text/javascript">
    
    //按钮名称和所属的模块名称一致b_模块名称(提高js计算速度)
function setAll(){
    var obj = arguments[0];
    var objValue = obj.value;
    var p_btn = document.getElementsByName('b_'+objValue);
    
    //var p_btn = document.getElementById(objValue);
    var len = getElementLen(p_btn);
    
    selParentObj(obj);
    for(var i=0;i<document.getElementsByName("menuid").length;i++)
    {
        if(obj.value==document.getElementsByName("menuid")[i].getAttribute('pcode'))
        {
           document.getElementsByName("menuid")[i].checked = obj.checked;
            setAll(document.getElementsByName("menuid")[i]);
        }else{
            for(var j=0;j<len;j++)
            {
                if(len=='1'){
                    if(p_btn[0].getAttribute('pcode')==objValue)  //将属于菜单同一行的按钮选中
                        p_btn[0].checked = obj.checked;
                }else{
                    if(p_btn[j].getAttribute('pcode')==objValue)  //将属于菜单同一行的按钮选中
                        p_btn[j].checked = obj.checked;
                }
            }
        }
    }
}

function getElementLen(obj){

    if(!(typeof(obj)=="object"))return 0;
    if(obj==null)
        return 0;
    var len=obj.length;
    if(typeof(len)=="undefined")
    {
        len=1;
    }
    return len;
}

 function selectall() {
    with (fm) {
        for (var i=0;i<elements.length;i++) {
            if (elements[i].name=="menuid") {
                elements[i].checked = all.checked;
                //eval("p_btn = document.getElementById('b_"+elements[i].value+"')");
                var p_btn = document.getElementsByName('b_'+elements[i].value);
                var len = getElementLen(p_btn);
                for (var j=0;j<len;j++) {
                    if(len=='1')
                        p_btn[0].checked = all.checked;
                    else
                        p_btn[j].checked = all.checked;
                }
            }


        }
    }

}   
  // fixed: chengyao
// 递归调用,选中obj的所有父节点
function selParentObj(obj) {
	// 被选择
	if (obj.checked) {
		// 有上级节点
		if(obj.getAttribute('pcode')!='0') {
			// 设置父节点选中
			document.getElementById(obj.getAttribute('pcode')).checked = true;
			// 检查上级父节点的选中状态
			selParentObj(document.getElementById(obj.getAttribute('pcode')));
		}
	}
}  

function checkRrivilege(){//选择到最末级
    with (fm) {
        for (var i=0;i<elements.length;i++) {
//        	alert(elements[i].name + ":" + elements[i].getAttribute('leaf')  + ":" +  elements[i].checked);
            if (elements[i].name=="menuid"&&elements[i].getAttribute('leaf')=='1'&& elements[i].checked) {
//	        	alert(elements[i].name + ":" + elements[i].getAttribute('leaf')  + ":" +  elements[i].checked);
                return true;
            }
        }
    }

    return false;
}

function setLeafMenuCoded(){
    var menuCodes = "";
    for(var i=0;i<document.getElementsByName("menuid").length;i++)
    {
        //~ modified by Dishine
        //~ 原来只保存叶子结点，现在不论是不是叶子都保存
        // if(document.all.menuid[i].getAttribute('leaf')=='Y'&&document.all.menuid[i].checked) {
        if(document.getElementsByName("menuid")[i].checked) {
            menuCodes+=document.getElementsByName("menuid")[i].value;
            //eval("var p_btn = document.all.b_"+document.getElementsByName("menuid")[i].value);
              var p_btn = document.getElementsByName('b_'+document.getElementsByName("menuid")[i].value);
            var len = getElementLen(p_btn);
            for(var j=0;j<len;j++)
            {
                if(len==1) {
                    if(p_btn[0].getAttribute('pcode')==document.getElementsByName("menuid")[i].value&&p_btn[0].checked)  //将属于菜单同一行的按钮选中
                        menuCodes+="#"+p_btn[0].value;

                }else{
                    if(p_btn[j].getAttribute('pcode')==document.getElementsByName("menuid")[i].value&&p_btn[j].checked)  //将属于菜单同一行的按钮选中
                        menuCodes+="#"+p_btn[j].value;
                }

            }
            menuCodes+=",";

        }
    }
    return menuCodes;
}

function setParStatus(){
    var obj=arguments[0];
    var pcode=obj.getAttribute('pcode');
    if(obj.checked&&!document.getElementById(pcode).checked);
	{
    	document.getElementById(pcode).checked=true;
    	selParentObj(document.getElementById(pcode));
	}
}
// fixed: chengyao
// 递归调用,选中obj的所有父节点
function selParentObj(obj) {
	// 被选择
	if (obj.checked) {
		// 有上级节点
		if(obj.getAttribute('pcode')!='0') {
			// 设置父节点选中
			document.getElementById(obj.getAttribute('pcode')).checked = true;
			// 检查上级父节点的选中状态
			selParentObj(document.getElementById(obj.getAttribute('pcode')));
		}
	}
}

function sub(){
	//if(!checkRrivilege()) {
    //    alert("请选择权限!");
    //    return false;
  //  }
    document.getElementById("menu_privilege").value =setLeafMenuCoded();
   // alert(document.getElementById("menu_privilege").value);
    return true;
}
    </script>
 </head>
<body>


<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>修改角色</h5>
    </div>
    <!-- end box / title -->
     <form:form method="post" id="fm" commandName="role" htmlEscape="true" acceptCharset="utf-8" cssClass="required-validate" onsubmit="return sub();">
   	<input type="hidden" id="menu_privilege" name="menu_privilege" value="" />
   	<form:hidden path="id" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label noinput">ID：</div>
                    <div class="input">${role.id }</div>
                </div>

                <div class="field">
                    <div class="label">
                        <label for="roleName" class="req">名称：</label>
                    </div>
                    <div class="input">
                     <form:input path="roleName" cssClass="small required " maxlength="50" />
                    </div>
                </div>
                
                <div class="field">
                    <div class="label">
                        <label for="remark" >备注：</label>
                    </div>
                    <div class="input">
                    <form:textarea path="remark"/>
                    </div>
                </div>

                <div class="field">
                    <div class="label">
                        <label  class="req">修改权限：<br/>
                        <input name="all" type="checkbox" value="1" onclick="selectall();"/>  全选
                          <form:hidden path="menuPrivilege"/>
                         
                        </label>
                    </div>
                    <div  style="margin: 0 0 0 120px;width:66%; height:300px; overflow:auto; border: 1px solid #b3b3b3;color: #000000;">
                          ${menuPrivilege }
                       
                    </div>
                </div>
				

                <div class="buttons">
                    <div class="highlight">
                        <input type="submit" name="submit.highlight" value="提交" />
                    </div>
                    <input type="reset" name="reset" value="重置" />
                    <a href="${ctx}/sys/role/list" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
  </form:form>
</div>
<!-- end forms -->
</div>


<script type="text/javascript">
    <!--
    ajaxFormSubmit('#fm');

    //-->
</script>



</body>
</html>