$(function(){
	$('#imgCode,#change_verify_code').click(function(){
	    resetVcode("imgCode", "/image/bindEmail/code.chtml?r=");
	});
	$('#imgBindMoblieCode').click(function(){
		resetVcode("imgBindMoblieCode", "/image/bindMoblie/code.chtml?r=");
	});
	$('#imgBindMoblieCode').click(function(){
		resetVcode("imgBindMoblieCode", "/image/bindMoblie/code.chtml?r=");
	});
	$('#img-edit-moblie-step1-Code').click(function(){
		resetVcode("img-edit-moblie-step1-Code", "/image/editMoblie/step1/code.chtml?r=");
	});
	$('#img-edit-moblie-step2-Code').click(function(){
		resetVcode("img-edit-moblie-step2-Code", "/image/editMoblie/step2/code.chtml?r=");
	});
});

function resetVcode(id, url) {
//    $('#imgCode').attr("src", G_CTX_ROOT + "image/code?r=" + Math.random());
    $('#'+id).attr("src", url + Math.random());
}