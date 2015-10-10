/**
 *project: validate widgets
 *version: 1.0
 */

(function (win, doc, undefined) {
    var defaults = {
        id: 'form',
        infoElement: 'label',
        errorClass: 'error',
        validClass: 'valid',
        rules: {},
        onsubmit: true,
        onfocusout: true,
        onclick: true,
        focusInvalid: true,
        focusCleanup: false,
        highlight: true,
        messages: {
            required: '此项不能为空',
            remote: '请修正该字段',
            email: '您输入的邮箱格式不正确，请重新输入',
            url: '请输入合法的网址',
            date: '请输入合法的日期',
            dateISO: '请输入合法的日期(ISO).',
            number: '请输入合法的数字',
            digits: '只能输入整数',
            equalTo: '请再次输入相同的值',
            accept: '请输入拥有合法后缀名的字符串',
            maxLength: '请输入一个长度最多是{0}的字符串',
            minLength: '请输入一个长度最少是{0}的字符串',
            rangeLength: '请输入一个长度介于{0}和{1}之间的字符串',
            range: '请输入一个介于{0}和{1}之间的值',
            max: '请输入一个{0}位以下的字符串',
            min: '请输入一个{0}位以上的字符串',
			mobileno: '请输入合法的移动号码',
			hasLetter: '请输入含有特殊字符的字符串'
        },
        success: '填写正确',
        submitHandler: function (form) {
            form.submit();
        }
    };
    var Validator = function (options) {
        this.init(options);
    };
    Validator.prototype = {
        settings: null,
        init: function (options) {
            this.reset();
            this.pending = {};
            this.settings = X.extend(defaults, options);
            this.currentForm = X.$(this.settings.id);
            this.currentElements = this.getElements();
            this.bindEvent();
        },
        bindEvent: function () {
            var _this = this;
            _this.settings.onsubmit && X.addEvent(this.currentForm, 'submit', function (e) {
                X.preventDefault(e);
                _this.reset();
                _this.validate();
            });
            X.filter(this.currentElements.concat([]),function () {
                return !_this.isCheck(this);
            }).each(function () {
                    _this.settings.onfocusout && X.addEvent(this, 'blur', function (e) {
                        _this.check(e.target || win.event.srcElement);
                    });
                    _this.settings.focusCleanup && X.addEvent(this, 'focus', function (e) {
                        _this.hideInfo(e.target || win.event.srcElement);
                    });
                });
            _this.settings.onclick && X.getByAttr('type', 'radio,checkbox', this.currentForm).each(function () {
                X.addEvent(this, 'click', function (e) {
                    _this.check(e.target || win.event.srcElement);
                });
            });
        },
        validate: function () {
            if (this.checkForm()) {
                //this.settings.submitHandler && this.settings.submitHandler(this.currentForm);
            } else {
                this.settings.focusInvalid && this.errorList[0].focus();
            }
        },
        showInfo: function (element, message, type) {
            var info = this.getInfo(element.name);
            if (info.length) {
				if(type == "error") {
					info[0].className = this.settings[type + 'Class'];
					info[0].innerHTML = message;
					info[0].style.display = '';
				} else{
					info[0].style.display = 'none';
				}
            } else {
				if(type == "error") {
					info = doc.createElement(this.settings.infoElement);
					info.innerHTML = message;
					X.attr(info, {'data-role': 'validateInfo', 'for': element.name, 'class': this.settings[type + 'Class']});
					var parent = element.parentNode;
					var next = element.nextSibling;
					if (this.isCheck(element)) {
						var checks = X.getByAttr('name', element.name, this.currentForm);
						parent = parent.parentNode;
						next = checks[checks.length - 1].parentNode.nextSibling;
					}
					if (next) {
						parent.insertBefore(info, next);
					} else {
						parent.appendChild(info);
					}
				} else {}
            }
            X.delClass(element, 'error');
            X.delClass(element, 'valid');
            X.addClass(element, this.settings[type + 'Class']);
        },
        hideInfo: function (element) {
            var info = this.getInfo(element.name);
            if (info.length) {
                info[0].style.display = 'none';
            }
        },
        getInfo: function (name) {
            return X.filter(X.getByAttr('data-role', 'validateInfo', doc.body), function () {
                return X.attr(this, 'for') === name;
            });
        },
        checkForm: function () {
            for (var i = 0, len = this.currentElements.length; i < len; i++) {
                var element = this.currentElements[i];
                var result = this.check(element);
                if (!result) {
                    this.errorList.push(element);
                }
            }
            return this.errorList.length === 0;
        },
        check: function (element) {
            var rules = this.settings.rules[element.name];
            var val = this.getValue(element);
            for (var method in rules) {
                try {
                    var rule = rules[method];
                    var result = this.methods[method].call(this, val, element, rule);
                    if (!result) {
                        var message = this.getErrorMessage(element, method, rule);
                        this.showInfo(element, message, 'error');
                        return false;
                    }
                    if (result === 'pending') {
                        return true;
                    }
                } catch (ex) {
                }
            }
            this.showInfo(element, this.settings.success, 'valid');
            return true;
        },
        getErrorMessage: function (element, method, rule) {
            var message = this.settings.messages[method];
            var params = X.isArray(rule) ? rule : [rule];
            for (var i = 0, len = params.length; i < len; i++) {
                message = message.replace(new RegExp('\\{' + i + '\\}', 'g'), params[i]);
            }
            return message;
        },
        getElements: function () {
            var rulesCache = {};
            return X.filter(X.getByTag('input,select,textarea', this.currentForm), function () {
                var result = /submit|reset|image/i.test(this.type) || this.disabled || this.name in rulesCache;
                rulesCache[this.name] = true;
                return !result;
            });
        },
        getValue: function (element) {
            var val = element.value;
            if (this.isCheck(element)) {
                var checks = this.getChecks(element.name);
                val = checks.length === 0 ? 'undefined' : checks[0].value;
            }
            if (typeof val === 'string') {
                return val.replace(/\r/g, '');
            }
            return val;
        },
        isCheck: function (element) {
            return /radio|checkbox/i.test(element.type);
        },
        getChecks: function (name) {
            var checks = [];
            X.getByAttr('name', name, this.currentForm).each(function () {
                if (this.checked) {
                    checks.push(this);
                }
            });
            return checks;
        },
        getSelects: function (element) {
            var selects = [];
            X.getByTag('option', element).each(function () {
                if (this.selected) {
                    selects.push(this);
                }
            });
            return selects;
        },
        getLength: function (value, element) {
            if (X.isArray(value)) {
                return value.length;
            }
            if (typeof value === 'object') {
                var len = 0;
                for (var i in value) {
                    len++;
                }
                return len;
            }
            switch (element.nodeName.toLowerCase()) {
                case 'select':
                    return this.getSelects(element).length;
                case 'input':
                    if (this.isCheck(element)) {
                        return this.getChecks(element.name).length;
                    }
            }
            return X.trim(value).length;
        },
        submit : function(){
        	
        },
        reset: function () {
            this.errorList = [];
            this.successList = [];
        },
        methods: {
            required: function (value, element) {
                return this.getLength(value, element) > 0;
            },
            email: function (value, element) {
                return /^((([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+(\.([a-z]|\d|[!#\$%&'\*\+\-\/=\?\^_`{\|}~]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])+)*)|((\x22)((((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(([\x01-\x08\x0b\x0c\x0e-\x1f\x7f]|\x21|[\x23-\x5b]|[\x5d-\x7e]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(\\([\x01-\x09\x0b\x0c\x0d-\x7f]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF]))))*(((\x20|\x09)*(\x0d\x0a))?(\x20|\x09)+)?(\x22)))@((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))$/i.test(value);
            },
            url: function (value, element) {
                return /^(https?|s?ftp):\/\/(((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:)*@)?(((\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5])\.(\d|[1-9]\d|1\d\d|2[0-4]\d|25[0-5]))|((([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|\d|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.)+(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])*([a-z]|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])))\.?)(:\d*)?)(\/((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)+(\/(([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)*)*)?)?(\?((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|[\uE000-\uF8FF]|\/|\?)*)?(#((([a-z]|\d|-|\.|_|~|[\u00A0-\uD7FF\uF900-\uFDCF\uFDF0-\uFFEF])|(%[\da-f]{2})|[!\$&'\(\)\*\+,;=]|:|@)|\/|\?)*)?$/i.test(value);
            },
            date: function (value, element) {
                return !/Invalid|NaN/.test(new Date(value).toString());
            },
            dateISO: function (value, element) {
                return /^\d{4}[\/\-]\d{1,2}[\/\-]\d{1,2}$/.test(value);
            },
            number: function (value, element) {
                return /^-?(?:\d+|\d{1,3}(?:,\d{3})+)?(?:\.\d+)?$/.test(value);
            },
            digits: function (value, element) {
                return /^\d+$/.test(value);
            },
            minLength: function (value, element, param) {
                return this.getLength(value, element) >= param;
            },
            maxLength: function (value, element, param) {
                return this.getLength(value, element) <= param;
            },
            rangeLength: function (value, element, param) {
                var length = this.getLength(value, element);
                return length >= param[0] && length <= param[1];
            },
            min: function (value, element, param) {
                return value >= param;
            },
            max: function (value, element, param) {
                return value <= param;
            },
            range: function (value, element, param) {
                return value >= param[0] && value <= param[1];
            },
            equalTo: function (value, element, param) {
                return value === this.getValue(X.$(param));
            },
			mobileno: function (value, element) {
				return /^(((13)[5-9]{1})|((15)[0,1,2,7,8,9]{1})|((18)[2,3,4,7,8]{1})|(188))[0-9]{8}$|(^((134)[0-8]{1})[0-9]{7}$)/.test(value);
			},
			hasLetter: function (value, element) {
				return /(?!^(\d+|[a-zA-Z]+|[~!@#$%^&*?]+)$)^[\w~!@#$%\^&*?]+$/.test(value);
			},
            remote: function (value, element, param) {
                if (this.pending[element.name]) {
                    return;
                }
                this.pending[element.name] = true;
                var _this = this;
                X.ajax(X.extend({
                    url: 'http://localhost/github/_examples/php/checkName.php',
                    type: 'get',
                    dataType: 'json',
                    data: element.name + '=' + value,
                    sucFn: function (response) {
                        if (response) {
                            _this.showInfo(element, _this.settings.success, 'valid');
                        } else {
                            var message = _this.getErrorMessage(element, 'remote', param);
                            _this.showInfo(element, message, 'error');
                        }
                        _this.pending[element.name] = false;
                    }
                }, typeof param === 'object' ? param : {url: param}));
                return 'pending';
            }
        }
    };
    win['Validator'] = Validator;
})(window, document);
