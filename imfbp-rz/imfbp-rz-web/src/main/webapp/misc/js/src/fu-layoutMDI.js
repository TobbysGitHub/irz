(function($) {

	/**
	 * 这个控制用于静态页面之间的跳转如前进与后退
	 * @param {Object} options
	 * @param {Object} param
	 */
	$.fn.layoutMDI = function(options, param) {
		if(typeof options == 'string') {
			return $.fn.layoutMDI.methods[options](this, param);
		}

		options = options || {};
		return this.each(function() {
			var state = $.data(this, 'layoutMDI');
			if(state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'layoutMDI', {
					options: $.extend({}, $.fn.layoutMDI.defaults, options)
				});
			}
			_init(this, param)
		});
	};

	/**
	 * 初始化方法
	 * @param {Object} target html元素
	 * @param {Object} param 参数 String 或 {name='a',age=2}
	 */
	function _init(target, param) {
		//得到控件的配置参数
		var opts = $.data(target, 'layoutMDI').options;
	}
	/**
	 * 后退
	 * @param {Object} target html元素
	 * @param {Object} 参数 String 要后退的页面的选择器
	 */
	function _back(target, param) {
		//得到控件的配置参数
		var opts = $.data(target, 'layoutMDI').options;
		//拿到上一页的集合
		var previousPage = opts.previousPage;
		//判断上一页是否为空，如果为空说明没有可以退回的页面了
		if(previousPage != null && previousPage.length > 0) {
			var showPage = previousPage.pop();
			//显示页面上一页
			_showPage(target, showPage, 'back');
			//隐藏当前页面
			_hidePage(target, opts.currentPage, 'back');
			//将当前更改为要先上一页
			opts.currentPage = showPage;
			//当页面后退时候触发的事件
			opts.onBackPage.call(this,target, showPage);
		}
	}
	/**
	 * 向前
	 * @param {Object} target html元素
	 * @param {Object} param 参数 String 要向前的页面的选择器
	 */
	function _go(target, param) {
		//得到控件的配置参数
		var opts = $.data(target, 'layoutMDI').options;
		if(opts.currentPage == null) {
			opts.currentPage = ".startPage";
		}
		//如果上一页的集合为null，将其赋值
		if(opts.previousPage == null) {
			opts.previousPage = [];
		}
		//向上一页的数组中加入页面
		opts.previousPage.push(opts.currentPage);
		//显示传入的页面
		_showPage(target, param, 'go');
		//隐藏当前页面
		_hidePage(target, opts.currentPage, 'go');
		opts.currentPage = param;
		//当页前进退时候触发的事件
		opts.onGoPage.call(this,target, opts.currentPage);
	}

	/**
	 * 隐藏页面
	 * @param {Object} target html元素
	 * @param {Object} param 参数 String (String) 要显示的对象的选择器
	 */
	function _hidePage(target, param, state) {
		//得到控件的配置参数
		var opts = $.data(target, 'layoutMDI').options;
		$(target).find(param).hide();
		//注册当页面隐藏时事件
		opts.onHidePage.call(this, target, state);
	}
	/**
	 * 显示页面
	 * @param {Object} target html元素
	 * @param {Object} param 参数 String (String) 要显示的对象的选择器
	 */
	function _showPage(target, param, state) {
		//得到控件的配置参数
		var opts = $.data(target, 'layoutMDI').options;

		if(state == "go") {
			$(target).find(param).removeClass('animated fadeInRight');
			$(target).find(param).addClass('animated fadeInLeft');
		} else {
			$(target).find(param).removeClass('animated fadeInLeft');
			$(target).find(param).addClass('animated fadeInRight');
		}

		$(target).find(param).show();
		//注册当页面显示时事件
		opts.onShowPage.call(this, target, opts, state);
	}
	/**
	 * 得到下一个页面
	 * @param {Object} target html元素
	 * @param {Object} param 参数 String 或 {name='a',age=2}格式的对象(目前不需要)
	 */
	function _getCurrentPage(target, param) {
		//得到控件的配置参数
		var opts = $.data(target, 'layoutMDI').options;

	}
	/**
	 * 得到上一个页面
	 * @param {Object} target html元素
	 * @param {Object} param 参数 String 或 {name='a',age=2}
	 */
	function _getPreviousPage(target, param) {
		//得到控件的配置参数
		var opts = $.data(target, 'layoutMDI').options;
		if(opts.previousPage != null && opts.previousPage.lenght > 0) {
			return opts.previousPage[0];
		}
	}
	/**
	 * 得到所有上一个页面
	 * @param {Object} target html元素
	 * @param {Object} param 参数 String 或 {name='a',age=2}
	 */
	function _getPreviousPages(target, param) {
		//得到控件的配置参数
		var opts = $.data(target, 'layoutMDI').options;
		return opts.previousPage;
	}

	/**
	 * 方法
	 */
	$.fn.layoutMDI.methods = {
		options: function(jq) {
			return $.data(jq[0], 'layoutMDI').options;
		},
		/**
		 * 后退
		 * @param {Object} html元素
		 * @param {Object} param param 参数 String 或 {name='a',age=2}
		 */
		back: function(jq, param) {
			return jq.each(function() {
				_back(this, param);
			});
		},
		/**
		 * 前进
		 * @param {Object} jq
		 * @param {Object} param
		 */
		go: function(jq, param) {
			return jq.each(function() {
				_go(this, param);
			});
		},
		/**
		 * 得到当前页
		 * @param {Object} jq
		 * @param {Object} param
		 */
		getCurrentPage: function(jq, param) {
			return jq.each(function() {
				_getCurrentPage(this, param);
			});
		},
		/**
		 * 得到上一页
		 * @param {Object} jq
		 * @param {Object} param
		 */
		getPreviousPage: function(jq, param) {
			return jq.each(function() {
				_getPreviousPage(this, param);
			});
		},
		/**
		 * 得到所有上一页
		 * @param {Object} jq
		 * @param {Object} param
		 */
		getPreviousPages: function(jq, param) {
			return jq.each(function() {
				_getPreviousPages(this, param);
			});
		}
	};

	/*
	 * 默认配置参数
	 */
	$.fn.layoutMDI.defaults = {
		currentPage: null, //当前页面
		previousPage: null, //上一页
		onHidePage: function(target, options, state) {}, //当页面隐藏的时候的事件 state(’go‘，back)
		onShowPage: function(target, options, state) {}, //当页面显示的时候的事件 state(’go‘，back)
		onGoPage: function(target, options) {}, //当页面向前时候触发的事件
		onBackPage: function(target, options) {} //当页面后退时候触发的事件
	};

})(jQuery);