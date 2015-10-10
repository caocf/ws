/**
 * Count Analytics
 * 统计js,请求后台脚本统计点击数量
 */
var CA = {
	hasInit : 0,// 是否初始化页面标签data-ca属性
	arrImg : [],// 用于工作的Image对象以及状态
	maxLength : 4,// 同时工作的Image对象最大个数
	taskQueue : [],// 需要排队等候的任务
	// 初始化：监听body的mousedown事件，发送符合规则的埋点信息
	// 仅作用于一个带有data-ca属性的节点（被点击的节点或者最靠近其的父节点）。
	init : function(mobile, ssoid) {
		var me = this;
		if (me.hasInit || $("body").size() == 0)
			return;
		$("body").bind("mousedown", function(e) {
			var o = $(e.target);
			while (o.length > 0) {
				if (o[0] == $("body")[0])
					break;
				// 点击object标签会引发异常
				try {
					var name = o.attr("data-ca");
					if (name) {
						me.log(name, mobile, ssoid);
						return true;
					}
				} catch (err) {
				}
				o = o.parent();
			}
		});
		me.hasInit = 1;
	},
	/**
	 * 记录统计数据——发CA,复杂的埋点统计方式
	 * 
	 * @param {String}posi
	 *            位置
	 * @param {String}mobile
	 *            手机号
	 * @param {String}ssoid
	 *            登录id
	 * @param {String}serverUrl(opt)
	 *            统计服务器地址, 必选
	 */
	log : function(posi, mobile, ssoid, serverUrl) {
		var me = this, mobile, ssoid, dateid, logUrl = (serverUrl || "http://u.12580life.com:8080/count-analytics/CountHitsServlet");
		if ((mobile == "") && (ssoid == "")) {
			dateid = (new Date().getTime());
		}
		me.send(logUrl + "?itemid=" + posi + "&mobile=" + mobile + "&ssoid="
				+ ssoid + "&dateid=" + dateid);
	},
	send : function(url) {
		// 如果参数为空，则不处理
		if (typeof (url) == "undefined" || url == "")
			return;
		var me = this, img, imgHandler, arrImg, len = 0, index = -1;
		arrImg = me.arrImg;
		len = arrImg.length;
		// 查询可用的Image对象
		for ( var i = 0; i < len; i++) {
			if (arrImg[i].f == 0) {
				index = i;
				break;
			}
		}
		// 取出或者生成Image对象
		if (index == -1) {
			if (len == me.maxLength) {
				me.taskQueue.push(url);
				return;
			}
			img = $(new Image());
			arrImg.push({
				f : 1,
				img : img
			});
			index = (len == 0 ? 0 : len);
		} else {
			img = arrImg[index].img;
		}
		// 标记Image对象为正在使用状态
		arrImg[index].f = 1;
		// 记录所使用的Image对象的位置
		img.data("vid", index);
		imgHandler = function() {
			var vid = $(this).data("vid");
			if (vid >= 0) {
				arrImg[vid].f = 0;
			}
			if (me.taskQueue.length > 0) {
				me.send(me.taskQueue.shift());
			}
		};
		img.unbind().load(imgHandler).error(imgHandler);
		$(img).attr("src", url);
	}
};
