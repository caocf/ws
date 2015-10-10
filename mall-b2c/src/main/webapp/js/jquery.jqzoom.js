(function($) {
    $.fn.jqueryzoom = function(t) {
        var i = {
            xzoom: 200,
            yzoom: 200,
            offset: 10,
            position: "right",
            lens: 1,
            preload: 1
        };
        t && $.extend(i, t);
        var s = "";

        $(this).hover(function() {
            function t(e) {
                this.x = e.pageX,
                this.y = e.pageY
            }
            var a = $(this).offset().left,
            o = $(this).offset().top,
            n = $(this).find("img").get(0).offsetWidth,
            r = $(this).find("img").get(0).offsetHeight;
            s = $(this).find("img").attr("alt");
            var c = $(this).find("img").attr("jqimg");
            $(this).find("img").attr("alt", ""),
            0 == $("div.zoomdiv").get().length && ($(this).after("<div class='zoomdiv'><img class='bigimg' src='" + c + "'/></div>"), $(this).append("<div class='jqZoomPup'>&nbsp;</div>")),
            $("div.zoomdiv").width(i.xzoom),
            $("div.zoomdiv").height(i.yzoom),
            $("div.zoomdiv").show(),
            i.lens || $(this).css("cursor", "crosshair"),
            $(document.body).mousemove(function(s) {
                mouse = new t(s);
                var c = $(".bigimg").get(0).offsetWidth,
                d = $(".bigimg").get(0).offsetHeight,
                l = "x",
                p = "y";
                if (isNaN(p) | isNaN(l)) {
                    var p = c / n,
                    l = d / r;
                    $("div.jqZoomPup").width(i.xzoom / (1 * p)),
                    $("div.jqZoomPup").height(i.yzoom / (1 * l)),
                    i.lens && $("div.jqZoomPup").css("visibility", "visible")
                }
                xpos = mouse.x - $("div.jqZoomPup").width() / 2 - a,
                ypos = mouse.y - $("div.jqZoomPup").height() / 2 - o,
                i.lens && (xpos = a > mouse.x - $("div.jqZoomPup").width() / 2 ? 0: mouse.x + $("div.jqZoomPup").width() / 2 > n + a ? n - $("div.jqZoomPup").width() - 2: xpos, ypos = o > mouse.y - $("div.jqZoomPup").height() / 2 ? 0: mouse.y + $("div.jqZoomPup").height() / 2 > r + o ? r - $("div.jqZoomPup").height() - 2: ypos),
                i.lens && $("div.jqZoomPup").css({
                    top: ypos,
                    left: xpos
                }),
                scrolly = ypos,
                $("div.zoomdiv").get(0).scrollTop = scrolly * l,
                scrollx = xpos,
                $("div.zoomdiv").get(0).scrollLeft = scrollx * p
            })
        },
        function() {
            $(this).children("img").attr("alt", s),
            $(document.body).unbind("mousemove"),
            i.lens && $("div.jqZoomPup").remove(),
            $("div.zoomdiv").remove()
        }),
        count = 0,
        i.preload && ($("body").append("<div style='display:none;' class='jqPreload" + count + "'>360buy</div>"), $(this).each(function() {
            var t = $(this).children("img").attr("jqimg"),
            i = jQuery("div.jqPreload" + count).html();
            jQuery("div.jqPreload" + count).html(i + '<img src="' + t + '">')
        }))
    }


})(jQuery);