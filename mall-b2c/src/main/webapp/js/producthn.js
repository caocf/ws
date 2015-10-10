$(document).ready(function () {
	if($("img.lazy")){
		$("img.lazy").lazyload(); 
	}
	getHnBasicInfo();
    getDistountItems();
    initFeixinList();
    $(".spec-items ul").width(56 * $(".spec-items ul li").length);
    var $spec_ul = $(".spec-items ul");
    var $spec_count = $(".spec-items ul li").length;
    var act_num = 0;
    if ($spec_count > 4) {
        $("#spec-backward").removeClass("disabled");
        $("#spec-forward").click(function () {

            if (act_num == 0) {
                return false;

            } else {
                $("#spec-backward").removeClass("disabled");
                $spec_ul.stop(false, true).animate({left: "+=" + 56 + "px"}, "slow");
                act_num--;
                if (act_num == 0) {
                    $(this).addClass("disabled");
                }
                return false;
            }
        })
        $("#spec-backward").click(function () {
            if (act_num > $spec_count - 6) {
                return false;
            } else {
                $("#spec-forward").removeClass("disabled");
                $spec_ul.stop(false, true).animate({left: "-=" + 56 + "px"}, "slow");
                act_num++;

                if (act_num > $spec_count - 6) {
                    $(this).addClass("disabled");
                }
                return false;
            }
        })
    } else {
        $(".spec-control").click(function () {
            return false;
        });
    }

    $(".jqzoom").jqueryzoom({
        xzoom: 400,
        yzoom: 400,
        offset: 10,
        position: "left",
        preload: 1,
        lens: 1
    });

    $("#spec-list li").mouseover(function () {
        var e = $(this).find("img"),
            t = e.attr("src"),
            i = $("#spec-list li").index($(this)),
            s = $(this).attr("data-video"),
            a = "http://cloud.letv.com/bcloud.html?uu=abcde12345&vu={V}&pu=12345abcde&auto_play=0&width=352&height=352";
        $("#spec-list img").removeClass("img-hover"),
            e.addClass("img-hover"),
            1 === i && s ? 1 > $("#le-video").length ? $("#preview").append('<iframe id="le-video" src="' + a.replace("{V}", s) + '" frameborder="0" scrolling="no" style="display:block; width:352px; height:352px; position:absolute; left:0; top:0; "></iframe>') : $("#le-video").show() : ($("#spec-n1 img").eq(0).attr({
                src: t.replace("/N5/", "/N1/"),
                jqimg: t.replace("/N5/", "/N0/")
            }), $("#le-video").length > 0 && $("#le-video").hide())
    });


    getBrowseHistory();
    var kucun;
});


function getBrowseHistory() {
    var historys = $.cookie('itemId');
    if (typeof(historys) != "undefined") {
        $.get(webRoot + "item/historyItem.chtml?saleIds=" + historys + "&ramdom=" + Math.random(), function (data) {
                var k = data;
                var html = '';
                var hises = new Array();
                if (historys.indexOf(',') > -1) {
                    hises = historys.split(',');
                } else {
                    hises[0] = historys;
                }
                for (var j = 0; j < hises.length; j++) {
                    for (var i = 0; i < k.historys.length; i++) {
                        var history = k.historys[i];
                        if (hises[j] == history.id.GId) {
                            var name = history.GShortName;
                            if (name == '') {
                                name = history.GName;
                            }
                            html += '<li><div class="p-img">';
                            html += '<a href="' + history.itemPath + '"><img data-img="1" width="50" height="50" src="' + history.imgPath + 'N5/' + history.GWebPath + '" class="err-product"></a></div>';
                            html += '<div class="p-name"><a title="' + history.GName + '" href="' + history.itemPath + '">' + name + '</a></div>';
                            if(history.GSource != '5'){
                            	if(history.GSource == '4'){
                            		html += '<div class="p-price">积分' + FloatMul(history.GShopPrice, 100) + '</div></li>';
                            	}else{
                            		html += '<div class="p-price">￥' + history.GShopPrice + '</div></li>';
                            	}
                            }
                            
                        }
                    }
                }
                $("#related_his").html(html);
                if (typeof(historys) == "undefined") {
                    historys = itemId;
                } else {
                    if (historys.indexOf(itemId) <= -1) {
                        historys = itemId + "," + historys;
                    }
                }
                historys = historys.toString();
                if (historys.indexOf(',') > -1) {
                    if (historys.split(',').length > 5) {
                        var x = historys.indexOf(',');
                        var sum = 0;
                        while (x != -1 && sum < 4) {
                            sum++;
                            x = historys.indexOf(',', x + 1);
                        }
                        historys = historys.substr(0, x);
                    }
                }
                $.cookie('itemId', historys);
            }
        );
    } else {
        if (typeof(historys) == "undefined") {
            historys = itemId;
        } else {
            if (historys.indexOf(itemId) <= -1) {
                historys = itemId + "," + historys;
            }
        }
        historys = historys.toString();
        if (historys.indexOf(',') > -1) {
            if (historys.split(',').length > 5) {
                var x = historys.indexOf(',');
                var sum = 0;
                while (x != -1 && sum < 4) {
                    sum++;
                    x = historys.indexOf(',', x + 1);
                }
                historys = historys.substr(0, x);
            }
        }
        $.cookie('itemId', historys);
    }
}

var imgPath = '';
function getHnBasicInfo() {
    $.get(webRoot + "item/basicInfo.chtml?saleId=" + itemId + "&ramdom=" + Math.random(), function (data) {
            if (data != null) {
            	//如果是河南的商品
            	if(data.source == "4"){
            			 $("#clicknum").html(data.clicknum);
                         if (data.stocknum == '库存-1000') {
                             $("#stocknum").html("库存不限");
                         } else {
                             $("#stocknum").html(data.stocknum);
                         }
                         $(".breadcrumb").html(data.nav);
                         $("#marketContent").html(data.marketContent);
                         if (data.itemMode === '0') {
                         } else {
                             $("#saleNum").html(data.salenum);
                             $("#comment").hide();
                         }
                         imgPath = data.imgPath;
                         $("#memPrice").html(data.memPrice);

                         kucun = data.stocknum.substring(2);

                         var btns = "";

                     if (data == null || data.isValid !== "1") {
                         btns = "<div class=\"btn\" id=\"choose-btn-overdue\" style=\"display: block;\">" +
                             "<a href=\"#\" id=\"InitCartUrl\" class=\"btn-easybuy \" title=\"\">已下架<b></b></a></div><span class=\"clr\"></span>";
                     } else {
                         btns += " <div class=\"btn\" id=\"choose-btn-easybuy\" style=\"display: block;\"><a href=\"javascript:void(0);\" id=\"InitCartUrl\" onclick=\"buyNow();return false;\" class=\"btn-easybuy \" title=\"\">立即兑换<b></b></a></div>"
                             + " <div class=\"btn\" id=\"choose-btn-divide\" style=\"display: block;\"></div>"
                             + " <div class=\"btn\" id=\"choose-btn-coll\"> <a clstag=\"shangpin|keycount|product|btn-coll\" class=\"btn-coll\" onclick=\"mark();return false;\" id=\"coll841114\" href=\"javascript:void(0);\">加关注<b></b></a> </div>"
                         if (data.clicknum !== '0') {
                             btns += " <div class=\"btn\"> 人气指数: <b id=\"clicknum\">" + data.clicknum + "</b></div>"
                         }

                         btns += " <span class=\"clr\"></span>";
                     }
                     $("#choose-btns").html(btns).after(data.share);
            		}else if(data.source == "5"){//如果是优惠券
            			$("#clicknum").html(data.clicknum);
                        $(".breadcrumb").html(data.nav);
                        $("#marketContent").html(data.marketContent);
                        if (data.itemMode === '0') {
                        } else {
                            $("#comment").hide();
                        }
                        imgPath = data.imgPath;
                        kucun = data.stocknum.substring(2);
                    
                    var btns = "";

                    if (data == null || data.isValid !== "1") {
                        btns = "<div class=\"btn\" id=\"choose-btn-overdue\" style=\"display: block;\">" +
                            "<a href=\"#\" id=\"InitCartUrl\" class=\"btn-easybuy \" title=\"\">已下架<b></b></a></div><span class=\"clr\"></span>";
                    } else {
                        btns += " <div class=\"btn\" id=\"choose-btn-easybuy\" style=\"display: block;\"><a href=\"javascript:void(0);\" id=\"InitCartUrl\" onclick=\"downNow();return false;\" class=\"btn-easybuy \" title=\"\">优惠券下载<b class=\"choose-btns-coupon-down\"></b></a></div>"
                            + " <div class=\"btn\" id=\"choose-btn-divide\" style=\"display: block;\"></div>";
                        if (data.clicknum !== '0') {
                            btns += " <div class=\"btn\"> 人气指数: <b id=\"clicknum\">" + data.clicknum + "</b></div>"
                        }

                        btns += " <span class=\"clr\"></span>";
                    }
                    $("#choose-btns").html(btns).after(data.share);
            		}
            	}
        }
        
    );
}

function getPurchaseRecords() {
    $.get(webRoot + "item/purchaseRecords.chtml?saleId=" + itemId + "&curpage=" + arguments[0] + "&ramdom=" + Math.random(), function (data) {
            //var k = eval('(' + data + ')');
            var k = data;
            var records = k.purchases;
            var recordsHtml = '暂无商品成交记录';
            if (records != '') {
                recordsHtml = '<table width="100%"><tbody><tr><th>买家</th><th>成交金额</th><th>成交时间</th><th>购买数量</th><th>状态</th></tr>';
                for (var i = 0; i < records.length; i++) {
                    recordsHtml += '<tr><td>' + records[i][0] + '</td><td><span>' + records[i][1]
                        + '</span></td><td>' + records[i][2] + '</td><td>' + records[i][3]
                        + '</td><td>' + records[i][4] + '</td></tr>';
                }
                recordsHtml += '</tbody></table>';
                recordsHtml += k.scrip;
            }
            $("#info4").html(recordsHtml);
        }
    );
}

function NagivatePage() {
    var curpage = arguments[0];
    var type = arguments[1];
    if (type == 1) {
        getItemComments(curpage);
    } else if (type == 2) {
        getPurchaseRecords(curpage);
    } else if (type == 3) {
        getConsults(curpage);
    }
}

function getDistountItems() {
    $.get(webRoot + "item/distountItems.chtml?saleId=" + itemId + "&ramdom=" + Math.random(), function (data) {
            var group = data.group;
            if (typeof(group) != "undefined" && group.length > 0) {
                var itemhtml = '';
                itemhtml += '<div class="product_detail" id="recommend">';
                itemhtml += '<div class="product_tab">';
                itemhtml += '<div class="product_tab_nav">';
                itemhtml += '<ul class="tab">';
                itemhtml += '<li class="curr"><a href="#pj">优惠套餐</a></li>';
                itemhtml += '</ul>';

                var count = 0;
                var recoHtml = '';
                if (group.length > 1) {
                    recoHtml += '<ol class="tab-pagination" id="J_ComboPage">';
                    recoHtml += '<li><a class="tb-prev J_Prev" href="#">上一页</a></li>';
                }
                var html = '';
                for (var i = 0; i < group.length; i++) {
                    var groupItem = group[i].groupItem;
                    html += '<div class="product_tab_cont">';
                    html += '<div class="master"><s></s><div class="p-img"> <a target="_blank" href="' + groupItem.itemPath + '"><img width="100" height="100" src="' + groupItem.imgPath + 'N5/' + imgPath + '"></a> </div>';
                    html += '<div class="p-name"> <a target="_blank" href="' + groupItem.itemPath + '">' + itemName + '</a> </div></div>';
                    html += '<div style="" data-count="28" class="suits"><ul style="width:640px" class="lh">'
                    for (var j = 0; j < group[i].items.length; j++) {
                        var info = group[i].items[j];
                        if (info.id != itemId) {
                            html += '<li data-cat="851" class="">';
                            if (j < group[i].items.length - 1) {
                                html += '<s></s>';
                            }
                            html += '<div class="p-img"> <a target="_blank" href="' + info.itemPath + '"><img height="100" width="100" alt="" src="' + info.imgPath + '"></a> </div>';
                            html += '<div class="p-name"> <a target="_blank" href="' + info.itemPath + '">' + info.shortName + '</a> </div>'
                            html += '<div class="choose"><label class="p-price" for="inp_702800"> <strong>￥' + info.shopPrice + '</strong> </label></div>';
                            html += '</li>';
                        }
                    }

                    html += '</ul></div>';
                    html += '<div class="infos"><s></s><div class="p-price">搭&nbsp;&nbsp;配&nbsp;&nbsp;价：<br /><strong class="res-jdprice">￥ ' + groupItem.shopPrice + '</strong> </div>';
                    html += '<div class="p-saving">获得优惠：<br /><span class="res-totalprice">￥ ' + groupItem.discountPrice + '</span> </div>';
                    html += '<div class="btns"> <a href="javascript:void(0);" onclick="buyNow(1,' + groupItem.id + ')" class="btn-buy">立即购买</a></div>';
                    html += '</div><span class="clr"></span> </div>';
                    if (group.length > 1) {
                        recoHtml += '<li class="un_btn"><a data-index="' + count + '" class="" href="#">' + (count + 1) + '</a></li>';
                    }
                    count++;
                }
                if (group.length > 1) {
                    recoHtml += '<li><a class="tb-next J_Next" href="#">下一页</a></li>';
                    itemhtml += '</ol>';
                }
                itemhtml += recoHtml;
                itemhtml += '</div><div class="product_tab_wrap" id="tab_pro">';
                itemhtml += html;
                itemhtml += '</div>';
                itemhtml += '</div>';
                itemhtml += '</div>';
                $("#rr").html(itemhtml);
                //$("#tab_pro").html(html);
                //$("#J_ComboPage").html(recoHtml);
            }

            if ($("#rr").html != '') {//功能条跟随
                var $product_menu_top = $("#product_menu").offset().top;
                $(window).scroll(function () {

                    if ($product_menu_top - $(document).scrollTop() > 0) {
                        $("#product_menu").removeClass("nav-fixed");
                    } else {
                        $("#product_menu").addClass("nav-fixed");
                    }
                    ;
                })
            } else {
                var $product_menu_top = $("#product_menu").offset().top;
                $(window).scroll(function () {
                    if ($product_menu_top - $(document).scrollTop() > -244) {
                        $("#product_menu").removeClass("nav-fixed");
                    } else {
                        $("#product_menu").addClass("nav-fixed");
                    }
                    ;
                })

            }

            $(".suits").each(function () {
                var ll = $(this).children("ul").children("li").length;
                $(this).find("ul.lh").width(160 * ll);
            });
            //点击显示隐藏层
            $(".product_tab").each(function () {
                $(this).children(".product_tab_wrap").children(".product_tab_cont:first").show();
            })

            $("#recommend .tab-pagination li").eq(1).addClass("active");
            $("#tab_detail .tab li a").click(function () {
                $(this).parent("li").addClass("curr").siblings().removeClass("curr");
                $(this).parents(".#tab_detail").find(".product_tab_cont").hide().filter($(this).attr("href")).show();
                return false;
            });
            //换一套推荐产品

            var paginatio_num = 0;
            var $paginatio_count = $("#tab_pro .product_tab_cont").length;
            $(".tab-pagination .un_btn a").live("click", function () {
                var ti = $(this).parent("li").index() - 1;

                $("#tab_pro .product_tab_cont").stop(false, true).fadeOut('slow').filter($("#tab_pro .product_tab_cont").eq($(this).parent("li").index())).fadeIn('slow');
                $("#recommend .tab-pagination li").removeClass("active").filter($(this).parent("li")).addClass("active");

                $(this).parent("li").addClass("curr").siblings().removeClass("curr");
                $(this).parents(".product_tab").find(".product_tab_cont").hide().filter($("#tab_pro .product_tab_cont").eq(ti)).show();
                return false;
            })
            $(".tab-pagination .tb-prev").live("click", function () {

                if (paginatio_num == 0) {
                    paginatio_num = $paginatio_count - 1;
                    $("#tab_pro .product_tab_cont").stop(false, true).fadeOut('slow').filter($("#tab_pro .product_tab_cont:last")).fadeIn('slow');
                    $("#recommend .tab-pagination li").removeClass("active").filter($("#recommend .tab-pagination li").eq(paginatio_num + 1)).addClass(("active"));
                    return false;
                } else {
                    paginatio_num--;
                    $("#tab_pro .product_tab_cont").stop(false, true).fadeOut('slow').filter($("#tab_pro .product_tab_cont").eq(paginatio_num)).fadeIn('slow');
                    $("#recommend .tab-pagination li").removeClass("active").filter($("#recommend .tab-pagination li").eq(paginatio_num + 1)).addClass(("active"));
                    return false;
                }
            })
            $(".tab-pagination .tb-next").live("click", function () {
                if (paginatio_num == $paginatio_count - 1) {
                    paginatio_num = 0;
                    $("#tab_pro .product_tab_cont").stop(false, true).fadeOut('slow').filter($("#tab_pro .product_tab_cont:first")).fadeIn('slow');
                    $("#recommend .tab-pagination .un_btn").removeClass("active").filter($("#recommend .tab-pagination li").eq(paginatio_num + 1)).addClass(("active"));
                    return false;
                } else {
                    paginatio_num++;
                    $("#tab_pro .product_tab_cont").stop(false, true).fadeOut('slow').filter($("#tab_pro .product_tab_cont").eq(paginatio_num)).fadeIn('slow');
                    $("#recommend .tab-pagination .un_btn").removeClass("active").filter($("#recommend .tab-pagination li").eq(paginatio_num + 1)).addClass(("active"));
                    return false;
                }
            });
        }
    );
}

function getItemComments() {
    $.get(webRoot + "item/comments.chtml?saleId=" + itemId + "&curpage=" + arguments[0] + "&ramdom=" + Math.random(), function (data) {
            var comments = data.co;
            var html = '';
            if (typeof(comments) == "undefined" || comments.length == 0) {
                html += '<div id="i-comment"><div class="t_title">商品评分<br />目前还没有评价！</div>';
                html += '<div class="rate"><strong class="rl">0<span>分</span></strong><span class="star sa0"></span></div>';
                //html += '<div class="rate_btn"> 您可对已购商品进行评价<br /><a href=""></a> </div></div>';
                html += '</div>';
            } else {
                var item = data.item;
                html += '<div id="i-comment"><div class="t_title">商品评分<br />已收到 <strong>' + item[0][0] + '</strong> 条评价</div>';
                html += '<div class="rate"><strong class="rl">' + item[0][1] + '<span>分</span></strong><span class="star sa' + item[0][2] + '"></span></div>';
                //html += '<div class="rate_btn"> 您可对已购商品进行评价<br /><a href=""></a> </div>';
                html += '</div><div class="rate_comments">';
                for (var i = 0; i < comments.length; i++) {
                    html += '<div class="item">';
                    html += '<div class="user">';
                    html += '<div class="u-icon">  <img width="50" height="50" alt="jd_李露19283" src="http://misc.360buyimg.com/lib/img/u/56.gif" upin="achen0212"> </div>';
                    html += '<div class="u-name"> ' + (comments[i][2] == null ? "" : comments[i][2]) + ' </div>';
                    html += '<span class="u-level"></span> </div>';
                    html += '<div data-guid="9ac660f0-744d-48fc-8709-cea0e0419450" class="i-item">';
                    var rank = comments[i][3] + '';
                    if (rank.indexOf('.') > -1) {
                        rank = rank.replace('.', '');
                    }
                    html += '<div class="o-topic"><span class="star sa' + rank + '"></span><span><em class="fr"></em></span> </div>';
                    html += '<div class="comment-content"><p>' + comments[i][4] + '</p>';
                    html += '</div>';
                    html += '<div class="btns">' + comments[i][5];//+'<a href="javascript:;" data-id="154654940" class="btn-reply btn-toggle fr" onclick="isUse('+comments[i][0]+',0);">没用(<em id="noUse'+comments[i][0]+'">'+(comments[i][7]==null?"":comments[i][7])+'</em>)</a>';
                    //html += '<div  class="useful fr"> <a href="javascript:;" title="0" class="btn-agree" name="agree" onclick="isUse('+comments[i][0]+',1);">有用(<em id="isUse'+comments[i][0]+'">'+(comments[i][7]==null?"":comments[i][6])+'</em>)</a></div>';
                    html += '</div></div><div class="corner tl"></div></div>';
                }
                html += "</div>";
                html += data.scrip;
            }
            $("#info5").html(html);
        }
    );
}

function isUse() {
    var isUse = arguments[1];
    var commentId = arguments[0];
    $.get(webRoot + "item/isUse.chtml?commentId=" + commentId + "&isUse=" + isUse + "&ramdom=" + Math.random(), function (data) {
            if (data.flag == '1') {
                if (isUse == '0') {
                    var count = parseInt($("#noUse" + commentId).html());
                    $("#noUse" + commentId).html(count + 1);
                } else {
                    var count = parseInt($("#isUse" + commentId).html());
                    $("#isUse" + commentId).html(count + 1);
                }
            }
        }
    );
}

function getConsults() {
    $.get(webRoot + "item/consults.chtml?saleId=" + itemId + "&curpage=" + arguments[0] + "&ramdom=" + Math.random(), function (data) {
            var consults = data.consults;
            var html = ' <div class="cont"> <b>温馨提示：</b> 因厂家会在没有任何提前通知的情况下更改产品包装、产地或者更换随机附件，且每位咨询者购买情况、咨询时间等不同，为此以下回复仅对咨询者有效，时效为3天，其他网友仅供参考！若由此给您带来不便请多多谅解，谢谢！ </div><div id="consult">';
            if (consults.length == 0) {
                html += '<div class="item">暂无任何咨询！</div>';
            } else {
                for (var i = 0; i < consults.length; i++) {
                    var nickname = consults[i][1];
                    if (nickname == null) {
                        nickname = '';
                    }
                    html += '<div class="item">';
                    html += '<div class="user"><span class="u-name">网&#12288;&#12288;友：' + nickname + '</span>';
                    html += '<span class="u-level"></span>  </div>';
                    html += '<dl class="ask"><dt><b></b>咨询内容：</dt>';
                    html += '<dd>' + consults[i][2] + '<span class="date-ask">' + consults[i][3] + '</span></dd></dl>';
                    var reply = consults[i][5];
                    if (reply != null) {
                        html += '<dl class="answer"><dt> <b></b>商家回复：</dt><dd><div class="content">' + reply + '</div>';
                        html += '<div class="date-answer">' + consults[i][7] + '</div></dd></dl>';
                    }
                    html += '</div>';
                }
                html += data.scrip;
            }
            html += '<div class="tr"> <a href="" id="zx">我要咨询</a></div>';
            html += '<div class="none">';
            html += '<h3>我要咨询： <strong></strong></h3>';
            html += '<div class="form_line_div"> <strong>咨询类型： </strong><span>';
            html += '<input type="radio" value="1" name="post" checked/>';
            html += '商品咨询</span> <span>';
            html += '<input type="radio" value="2" name="post"/>';
            html += '促销活动咨询</span> <span>';
            html += '<input type="radio" value="3" name="post"/>';
            html += '库存及物流咨询 <span>';
            html += '<input type="radio" value="4" name="post"/>';
            html += '售后咨询 </span> </div>';
            html += '<textarea id="message" name="message" onblur="if(value==\'\'){value=\'请在此处填写您要咨询的内容\'}" onfocus="if(value==\'请在此处填写您要咨询的内容\'){value=\'\'}" >请在此处填写您要咨询的内容</textarea>';
            html += '<input type="button" value="提交" title="提交" class="dBtn-btn dBtn-blue" onclick="insertUserMessage(1);" id="insertMessageBtn">';
            html += '<input type="hidden" value="' + storeId + '" id="shopId"/></div>';
            $("#info6").html(html);

            $("#info6 a#zx").click(function () {
                $("#info6 .none").show();
                return false;
            });
        }
    );
}

function insertUserMessage() {
    var radio = $('input[name="post"]').filter(':checked');
    if (radio.length) {
    } else {
        alert('请选择咨询类型！');
        return;
    }
    var con = $("#message").val();
    if (con == '' || con == '请在此处填写您要咨询的内容') {
        alert("请输入咨询内容！");
        $("#message").focus();
        return;
    } else if (con.length > 100) {
        alert("咨询内容过长！");
        $("#message").focus();
        return;
    }

    var shopId = $("#shopId").val();
    $.get(webRoot + "item/saveConsults.chtml?saleId=" + itemId + "&post=" + $('input[name="post"]:checked').val() + "&content=" + encodeURI(con) + "&shopId=" + shopId + "&random=" + Math.random(), function (data) {
            if (data.flag == '1') {
                alert('咨询成功，请稍候查看回复！');
                getConsults(1);
            } else {
                alert('请先登录！');
            }
        }
    );
}

function upNum() {
    var wantBuy = $("#buy-num").val() - (-1);
    if (wantBuy > 200) {
        $("#kucunTips").html("<div class='clearfix new_goutip'><div class='inew_goutip'><span></span>您所填写的数量超出最大购买限制</div></div>");
        return;
    }
    if (kucun != "-1000" && kucun < wantBuy) {
        $("#kucunTips").html("<div class='clearfix new_goutip'><div class='inew_goutip'><span></span>您所填写的数量超出商品库存的数量!</div></div>");
    } else {
        $("#buy-num").val(wantBuy);
        $("#kucunTips").html("");
    }
}
function downNum() {
    var wantBuy = $("#buy-num").val() - 1;
    if (wantBuy < 1) {
        $("#kucunTips").html("<div class='clearfix new_goutip'><div class='inew_goutip'><span></span>您所填写的数量不能小于1!</div></div>");
    } else {
        $("#buy-num").val(wantBuy);
        $("#kucunTips").html("");
    }
}
function numcheck() {
    var wantBuy = $("#buy-num").val();
    if (parseInt(wantBuy) > 200) {
        $("#buy-num").val("200");
        return;
    }
    if (wantBuy < 1) {
        $("#buy-num").val("1");
        //$("#kucunTips").html("<div class='clearfix new_goutip'><div class='inew_goutip'><span></span>您所填写的数量不能小于1!</div></div>");
    } else if (parseInt(kucun) != "-1000" && parseInt(kucun) < parseInt(wantBuy)) {
        $("#buy-num").val(kucun);
        //$("#kucunTips").html("<div class='clearfix new_goutip'><div class='inew_goutip'><span></span>您所填写的数量超出商品库存的数量!</div></div>");
    } else {
        $("#kucunTips").html("");
    }
}

function mark() {
    $.get(webRoot + "item/updateCollect.chtml?saleId=" + itemId + "&ramdom=" + Math.random(), function (data) {
            if (data.flag == '1') {
                $("#favoritetips").html("收藏成功！");
            } else if (data.flag == '2') {
                $("#favoritetips").html("您已收藏过该商品！");
            } else if (data.flag == '3') {
                $("#favoritetips").html("请先登录再收藏！");
            } else {
                $("#favoritetips").html("收藏失败，请稍候再试！");
            }
            $('.alert_windows').show();
            //setTimeout(function(){$("#kucunTips").html("");},2000);
        }
    );
}

function addCart() {
	//插码：“加入购物车”事件码
	if(typeof(_tag)!='undefined'){
		_tag.dcsMultiTrack('WT.shopping_n',itemId, 'WT.shopping_x','20');
	}
    if (String(kucun) != '-1000') {
        if (parseInt(kucun) <= 0) {
            alert('库存为0，请选择其他商品！');
            return;
        }
    }
    var info = {
        "itemId": itemId,
        "quantity": $('#buy-num').val()};
    $.ajax({
        url: webRoot + "cart/additem.chtml",
        type: "POST",
        dataType: 'json',
        data: $.param(info, true),
        success: function (data, stats) {
            if (data.success) {
                $('#alert').show();
            } else {
                alert(data.msg);
            }
        },
        error: function (data) {
            alert("请求失败");
        }
    });
}

function downNow(){
    var quantity = 1;
    var itemId1 = itemId;

    $.ajax({
        url: webRoot + "coupon/download.chtml",
        type: "POST",
        dataType: 'json',
        data: {itemId: itemId1, quantity: quantity},
        success: function (data, stats) {
//{"success":true,"createOrder":{"EXPRESS_COST":0,"ORDER_ID":37509938,"MSG":"success","TOTAL_PAY_AMOUNT":1,"FLAG":"0"}}
            if (data.success && data.createOrder.MSG == 'success') {
             alert("下载成功");
            }else if (data.msg == '-2') {
               alert("请先登录");
            }else if (data.msg == '-1') {
                $("#alert_mobile").show();
            } else {
				//过滤器
				if(data.isLogin == '0' && data.url){
					location.href = data.url;
				}else{
					alert(data.msg);
				}
            }
        },
        error: function (data) {
            alert("请求失败");
        }
    });
}
function buyNow() {
	//插码：“立即购买”事件码
	if(typeof(_tag)!='undefined'){
		_tag.dcsMultiTrack('WT.shopping_n',itemId, 'WT.shopping_x','31');
	}
    if (String(kucun) != '-1000') {
        if (parseInt(kucun) <= 0) {
            alert('库存为0，请选择其他商品！');
            return;
        }
    }
    var flag = arguments[0];
    var quantity = 1;
    var itemId1 = itemId;
    if (flag != 1) {
        itemId1 = itemId;
        quantity = $('#buy-num').val();
    } else {
        itemId1 = arguments[1];
    }

    $.ajax({
        url: webRoot + "cart/buyitem.chtml",
        type: "POST",
        dataType: 'json',
        data: {itemId: itemId1, quantity: quantity},
        success: function (data, stats) {
            if (data.success) {
                $.cookie('confirm_itemId', itemId1, { path: '/' });
                $.cookie('confirm_quantity', quantity, { path: '/' });
                $.cookie('confirm_businessId', "", { path: '/' });
                $.cookie('confirm_type', "", { path: '/' });
                window.location = webRoot + 'order/buynow.chtml';
            } else {
                alert(data.msg);
            }
        },
        error: function (data) {
            alert("请求失败");
        }
    });


    /*
     var form = $('<form id="buynowform" method="POST" action="'+webRoot+'order/buynow.chtml' + '"></form>');
     var itemId2 = $('<input type="hidden" name="itemId"/>').appendTo(form).val(itemId1);
     var ys = selProps.split(',');
     for (var i = 0; i < ys.length; i++) {
     var selProps = $('<input type="hidden" name="selProps"/>').appendTo(form).val(ys[i]);
     }
     var quantity = $('<input type="hidden" name="quantity"/>').appendTo(form).val(quantity);
     $('body').append(form);
     form.submit();*/
}

var initFeixinList = function () {
    $.ajax({
        url: webRoot + "shop/getfexinlist.chtml",
        data: "shopId=" + storeId + "&shopClass=2",
        type: "post",
        dataType: "json",
        cache: false,
        success: function (r) {
            var html = '<div class="side_slide mb10">';
            html += '<div class="related-title">';
            html += '    <h3><a class="slide_up">飞信客服</a></h3>';
            html += '</div>';
            html += '<div class="related-content ">';
            html += ' <ul>';
            var hasFlag = false;
            for (var i in r.data) {
                hasFlag = true;
                html += '<li class="mb5">' + r.data[i].nickName + '：' + r.data[i].code + '</li>';
            }
            html += '</ul></div></div>';
            if (hasFlag == true) {
                $("#sidebar_shop").after(html);
            }
        }
    });
}

function waitFor60s() {
    //发送验证码
    var count = $("#bind_mobile_btn").attr("count");
    $("#bind_mobile_btn").val("发送验证码" + count);
    count--;
    $("#bind_mobile_btn").attr("count", count);
    if (count <= 0) {
        $("#bind_mobile_btn").attr("disabled", false);
        $("#bind_mobile_btn").val("发送验证码");
        return;
    }
    setTimeout("waitFor60s()", 1000);
}

function verifyBindCode() {
    //判断手机号
    var mobile = $("#bind_mobile").val();
    var patrn = /^(1)\d{10}$/;
    if (!patrn.test(mobile)) {
        alert("手机格式有误");
        return;
    }
    var code = $("#bind_code").val();
    if (code == "") {
        alert("请输入您获得的验证码");
    }
    $.ajax({
        url: webRoot + "user/verifyBindSmsCode.chtml",
        data: "mobile=" + mobile + "&code=" + code,
        type: "post",
        dataType: "json",
        cache: false,
        success: function (data) {
            if (data.success) {
                alert("您已经成功绑定");
                $("#alert_mobile").hide();
            } else {
                alert(data.msg);
            }
        }
    });
}

function sendBindSmsCode() {
    //判断手机号
    var mobile = $("#bind_mobile").val();
    var patrn = /^(1)\d{10}$/;
    if (!patrn.test(mobile)) {
        alert("手机格式有误");
        return;
    }
    $("#bind_mobile_btn").attr("disabled", true);

    $.ajax({
        url: webRoot + "user/sendBindSmsCode.chtml",
        data: "mobile=" + mobile,
        type: "post",
        dataType: "json",
        cache: false,
        success: function (data) {
            if (data.success) {
                $("#bind_mobile_btn").attr("count", 60);
                //$("#bind_mobile_btn").attr("disabled", true);
                waitFor60s();
                alert(data.msg);
            } else {
                alert(data.msg);
                $("#bind_mobile_btn").attr("disabled", false);
            }
        }
    });
}
function getLimitTime(limitTime) {
    var timeOffsetToServer = 0;
    var timeNowFromServer = 0;

    synTimeFromServer();
    var offsetTimer=setInterval(function () {
        timeOffsetToServer++;
    }, 1000);

    var timer = null;
    timer = setInterval(updateTime, 1000);

    function fillZero(num, digit) {
        var str = '' + num;

        while (str.length < digit) {
            str = '0' + str;
        }
        return str;
    }

    function updateTime() {
        var oDateEnd = new Date(parseFloat(limitTime));
        var oDateNow = now();

        var iRemain = (oDateEnd.getTime() - oDateNow.getTime()) / 1000;

        if (iRemain <= 0) {
            clearInterval(timer);
            clearInterval(offsetTimer);
            iRemain = 0;
            alert('活动已结束');
        }

        var iDay = parseInt(iRemain / 86400);
        iRemain %= 86400;

        var iHour = parseInt(iRemain / 3600);
        iRemain %= 3600;

        var iMin = parseInt(iRemain / 60);

        $("#mo-day").html(fillZero(iDay, 2));
        $("#mo-hour").html(fillZero(iHour, 2));
        $("#mo-min").html(fillZero(iMin, 2));
    }
    function updateNow(timeInMili) {
        timeNowFromServer = timeInMili;
        timeOffsetToServer = 0;
    }

    function getTimeStamp() {
        var date = new Date();
        return date.getTime();
    }

    function synTimeFromServer() {
        var url = "item/json/now.chtml?t=" + getTimeStamp();
        $.get(webRoot + url, function (data) {
            if (data.now != undefined) {
                updateNow(data.now);
            }
        });
    }
    function now() {
        var nowMil = timeNowFromServer || new Date().getTime();
        nowMil = parseFloat(nowMil) + timeOffsetToServer * 1000;
        return new Date(nowMil);
    }
}


function FloatMul(arg1,arg2)
{
	var m=0,s1=arg1.toString(),s2=arg2.toString();
	try{m+=s1.split(".")[1].length}catch(e){}
	try{m+=s2.split(".")[1].length}catch(e){}
	return Number(s1.replace(".",""))*Number(s2.replace(".",""))/Math.pow(10,m)
}
