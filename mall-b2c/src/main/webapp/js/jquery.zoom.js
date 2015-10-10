function(e) {
    e.fn.jqueryzoom = function(t) {
        var i = {
            xzoom: 200,
            yzoom: 200,
            offset: 10,
            position: "right",
            lens: 1,
            preload: 1
        };
        t && e.extend(i, t);
        var s = "";
		alert("in0");
        e(this).hover(function() {
			alert("in");
            function t(e) {
                this.x = e.pageX,
                this.y = e.pageY
            }
            var a = e(this).offset().left,
            o = e(this).offset().top,
            n = e(this).find("img").get(0).offsetWidth,
            r = e(this).find("img").get(0).offsetHeight;
            s = e(this).find("img").attr("alt");
            var c = e(this).find("img").attr("jqimg");
            e(this).find("img").attr("alt", ""),
            0 == e("div.zoomdiv").get().length && (e(this).after("<div class='zoomdiv'><img class='bigimg' src='" + c + "'/></div>"), e(this).append("<div class='jqZoomPup'>&nbsp;</div>")),
            e("div.zoomdiv").width(i.xzoom),
            e("div.zoomdiv").height(i.yzoom),
            e("div.zoomdiv").show(),
            i.lens || e(this).css("cursor", "crosshair"),
            e(document.body).mousemove(function(s) {
                mouse = new t(s);
                var c = e(".bigimg").get(0).offsetWidth,
                d = e(".bigimg").get(0).offsetHeight,
                l = "x",
                p = "y";
                if (isNaN(p) | isNaN(l)) {
                    var p = c / n,
                    l = d / r;
                    e("div.jqZoomPup").width(i.xzoom / (1 * p)),
                    e("div.jqZoomPup").height(i.yzoom / (1 * l)),
                    i.lens && e("div.jqZoomPup").css("visibility", "visible")
                }
                xpos = mouse.x - e("div.jqZoomPup").width() / 2 - a,
                ypos = mouse.y - e("div.jqZoomPup").height() / 2 - o,
                i.lens && (xpos = a > mouse.x - e("div.jqZoomPup").width() / 2 ? 0: mouse.x + e("div.jqZoomPup").width() / 2 > n + a ? n - e("div.jqZoomPup").width() - 2: xpos, ypos = o > mouse.y - e("div.jqZoomPup").height() / 2 ? 0: mouse.y + e("div.jqZoomPup").height() / 2 > r + o ? r - e("div.jqZoomPup").height() - 2: ypos),
                i.lens && e("div.jqZoomPup").css({
                    top: ypos,
                    left: xpos
                }),
                scrolly = ypos,
                e("div.zoomdiv").get(0).scrollTop = scrolly * l,
                scrollx = xpos,
                e("div.zoomdiv").get(0).scrollLeft = scrollx * p
            })
        },
        function() {
            e(this).children("img").attr("alt", s),
            e(document.body).unbind("mousemove"),
            i.lens && e("div.jqZoomPup").remove(),
            e("div.zoomdiv").remove()
        }),
        count = 0,
        i.preload && (e("body").append("<div style='display:none;' class='jqPreload" + count + "'>360buy</div>"), e(this).each(function() {
            var t = e(this).children("img").attr("jqimg"),
            i = jQuery("div.jqPreload" + count).html();
            jQuery("div.jqPreload" + count).html(i + '<img src="' + t + '">')
        }))
    }
} (jQuery)