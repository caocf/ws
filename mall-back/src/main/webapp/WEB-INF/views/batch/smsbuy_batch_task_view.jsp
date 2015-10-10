<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
     <script type="text/javascript">
	$().ready(function() {
		$("#auditId").click(function() {
			showDialog("SMS群发任务审核", "${ctx}/batch/audit/"+${bt.id},null,{"Width":700,"Height":600,"ShowButtonRow" : false});
		});
	});
</script>
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div class="title">
        <h5>短信购群发任务查看<h5>
    </div>
    <!-- end box / title -->
        <div class="form">
            <div class="fields">
               <div class="field">
                  <div class="label">
                      <label for="taskName" >任务名称：</label>
                  </div>
                  <div class="input">
                  	 ${bt.taskName }
                  </div>
              </div>
              <div class="field">
                  <div class="label">
                      <label for="taskName" >商品ID：</label>
                  </div>
                  <div class="input">
                  	 ${bt.itemId }
                  </div>
              </div>
              <div class="field">
                  <div class="label">
                      <label for="taskName" >商品名称：</label>
                  </div>
                  <div class="input">
                  	 ${bt.itemName }
                  </div>
              </div>
               <div class="field">
                  <div class="label">
                      <label for="taskName" >群发状态：</label>
                  </div>
                  <div class="input">
                  	已配置
                  </div>
              </div>
              <div class="field">
                  <div class="label">
                      <label for="taskName" >任务状态：</label>
                  </div>
                  <div class="input">
                   ${bt.statusName }
                  </div>
              </div>
              <div class="field">
                    <div class="label">
                        <label for="startTime">群发开始时间:</label>
                    </div>
                    <div class="input">
                        <ct:time source="${bt.startTime}"/>
                    </div>
                </div>
                <div class="field">
                    <div class="label">
                        <label for="stopTime">群发结束时间:</label>
                    </div>
                    <div class="input">
                    <ct:time source="${bt.stopTime}"/>
                    </div>
                </div>
                <div class="field">
                  <div class="label">
                        <label for="spCode" >特服号：</label>
                    </div>
                    <div class="input">
                    ${bt.spCode}
                    </div>
              </div>
              <div class="field">
                  <div class="label">
                        <label for="spCode" >下发用户数：</label>
                    </div>
                    <div class="input">
                    ${bt.submitCnt}
                    </div>
              </div>
              <div class="field">
                  <div class="label">
                        <label for="spCode" >发送成功数：</label>
                    </div>
                    <div class="input">
                    ${bt.successCnt}
                    </div>
              </div>
              <div class="field">
                  <div class="label">
                        <label for="spCode" >发送失败数：</label>
                    </div>
                    <div class="input">
                    ${bt.failCnt}
                    </div>
              </div>
                 <div class="field">
                    <div class="label label-textarea">
                        <label for="title">短信内容：</label>
                    </div>
                    <div class="input">
                    ${bt.title}
                    </div>
                </div>
                <div class="buttons">
                    <a href="javascript:history.go(-1);" class="btnAnchor">返回</a>
                </div>
            </div>
        </div>
</div>
<!-- end forms -->
</div>

</body>
</html>