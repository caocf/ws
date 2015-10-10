$(function() {

    var plugins={
        map:{c:'btnMap',t:'插入Google地图',e:function(){
            var _this=this;
            _this.saveBookmark();
            _this.showIframeModal('Google 地图',G_CTX_ROOT + '/static/js/xheditor/googlemap/googlemap.html',function(v){
                _this.loadBookmark();
                _this.pasteHTML('<img src="'+v+'" />');
            },538,404);
        }}
    };

    $('.cxheditorprev').each(function() {

        var shtml = '<html><head><meta http-equiv="Content-Type" content="text/html; charset=utf-8" />' +
            '<link rel="stylesheet" href="' + G_CTX_ROOT + '/static/js/xheditor/xheditor_skin/default/iframe.css"/><title>预览</title></head><body>' +
            this.value +
            '</body></html>';

        var iFrame = $('<iframe frameborder="0"/>');
        iFrame.css('border', '1px solid black');
        iFrame.css('width', $(this).css('width'));
        iFrame.css('height', $(this).css('height'));
        iFrame.insertAfter($(this));
        var iFrameDoc = iFrame[0].contentDocument || iFrame[0].contentWindow.document;
        iFrameDoc.write(shtml);
        iFrameDoc.close();

        $(this).hide();
    });




/*
    var beforeSetSource=settings.beforeSetSource,sContent=_this.getSource();
    if(beforeSetSource)sContent=beforeSetSource(sContent);
    var sHTML='<html><head>'+headHTML+'<title>{#Preview}</title>'+(urlBase?'<base href="'+urlBase+'"/>':'')+'</head><body>' + sContent + '</body></html>';
    var screen=window.screen,oWindow=window.open('', 'xhePreview', 'toolbar=yes,location=no,status=yes,menubar=yes,scrollbars=yes,resizable=yes,width='+Math.round(screen.width*0.9)+',height='+Math.round(screen.height*0.8)+',left='+Math.round(screen.width*0.05)),oDoc=oWindow.document;
    oDoc.open();
    oDoc.write(getLang(sHTML));
    oDoc.close();
    oWindow.focus();
*/

    $('.cxheditor').xheditor({
        plugins:plugins
        ,tools:'Cut,Copy,Paste,Pastetext,|,Blocktag,Fontface,FontSize,Bold,Italic,Underline,FontColor,BackColor,SelectAll,Removeformat,|,' +
            'Align,List,|,Link,Unlink,Img,,Emot,Table,map,|,Code,Source,Preview,Fullscreen'
        ,skin:'default'
        ,showBlocktag:false
        ,internalScript:false
        ,internalStyle:false
        ,inlineScript:false
        ,linkTag:false
        ,height:250
        ,forcePtag:false
        ,upLinkUrl: G_CTX_ROOT + "/xheditor/upload/link"
        ,upLinkExt: XH_EXT
        ,upImgUrl: G_CTX_ROOT + "/xheditor/upload/img"
        ,upImgExt: XH_IMG_EXT
        ,remoteImgSaveUrl: G_CTX_ROOT + "/xheditor/upload/remote"
        ,localUrlTest: new RegExp("^https?:\/\/[^\/]*?(" + XH_DOMAIN + ")\/","i")
    });

});