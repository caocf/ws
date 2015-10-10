$(document).ready(function() {
	$("[type='checkbox'], [type='radio'], [type='file'], select").not('.toggle, .select2, .multiselect').uniform();




    var osObject = "";
    if(navigator.userAgent.indexOf("MSIE") > 0) {
        $('.ie8div').show();
        if(navigator.userAgent.indexOf("MSIE 6.0")>0) {
            osObject = "IE 6";
            $("body").prepend("<div class='explore-upgrade-box'><div class='explore-upgrade-word'>本系统目前支持的浏览器chrome、firfox、IE8、IE9、IE10等，您使用的是"+osObject+"浏览器，版本过低，不能使用本系统，建议你<a href='/data/chrome/ChromeStandaloneSetup.exe'>点击下载</a>chrome浏览器安装后使用。</div></div>");
            return;
        }
        if(navigator.userAgent.indexOf("MSIE 7.0")>0) {
            osObject = "IE 7";
            $("body").prepend("<div class='explore-upgrade-box'><div class='explore-upgrade-word'>本系统目前支持的浏览器chrome、firfox、IE8、IE9、IE10等，您使用的是"+osObject+"浏览器，版本过低，不能使用本系统，建议你<a href='/data/chrome/ChromeStandaloneSetup.exe'>点击下载</a>chrome浏览器安装后使用。</div></div>");
            return;
        }
    }
});