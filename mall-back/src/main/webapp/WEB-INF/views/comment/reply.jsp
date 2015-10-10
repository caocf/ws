<%@ page language="java" pageEncoding="utf-8"%>
<%@ include file="../includes/importer.jsp"%>
<!doctype html>
<html>
<head>
    <ht:head/>
    
</head>
<body>
<div id="content">
<!-- forms -->
<div class="box">
    <!-- box / title -->
    <div >
        <h5>回复<h5>
    </div>
    <!-- end box / title -->
    <form id="fm" name="fn" class="required-validate" action="./reply" method="post" accept-charset="utf-8">
    
       <input type="hidden" name="commentId" value="${itemComment.id }" />

       <input type="hidden" name="userId" value="${itemComment.userId }" />
        <div class="form">
            <div class="fields">
                <div class="field">
                    <div class="label label-textarea">
                        <label for="advice">昵称：</label>
                    </div>
                    <div class="input">
                   <input id="coyname" type="text" name="nickName" value="${reply.nickName }" maxlength="50" />
                   &nbsp;&nbsp;<span class="errorInput" id="suserId"></span>
                    </div>
                </div>
              
                <div class="field">
                    <div class="label label-textarea">
                        <label for="advice">回复内容：</label>
                    </div>
                    <div class="input">
                    <textarea id="textcontent" class="required max-length-100" rows="8" cols="50" name="content">${reply.content }</textarea>
                    &nbsp;&nbsp;<span class="errorInput" id="stextcontent"></span>
                    </div>
                </div>
                
                <div class="buttons">
							<div class="highlight">
								<input type="submit" name="submit.highlight" value="提交" />
							</div>
								<input type="reset" name="reset" value="重置" />
                    			<input type="button" class="common_btn" onclick="history.back();" value="返回" />
							</div>
						
						
            </div>
        </div>
    </form>
</div>
<!-- end forms -->
</div>
</body>
</html>