/**
 * ajax 扩展
 */
var fu = {
	ajax : function(options) {
		var _beforeSend = options.beforeSend;
		var _complete = options.complete;
		var _success = options.success;
		var _error = options.error;


		/**
		 * 发送请求前运行的函数。
		 */
		options.beforeSend = function(xhr) {
			//alert("beforeSend");
			if (typeof (_beforeSend) == 'function') {
				_beforeSend.apply(this, arguments);
			}
		},
		options.success = function(xhr) {
			//alert("_success");
			if (typeof (_success) == 'function') {
				_success.apply(this, arguments);
			}
		}


		/**
		 * 请求完成时运行的函数（在请求成功或失败之后均调用，即在 success 和 error 函数之后）。
		 */
		options.error = function(xhr, status) {
			//alert("complete");
			if (typeof (_error) == 'function') {
				_error.apply(this, arguments);
			}
		}
		jQuery.ajax(options);
	},

};

/**
 * 表格插件依赖于iuap的dataGrid控件，可以选择是否依赖 fu-pagination 控件
 * 如果不依赖fu-pagination控件  pageSize pageNumber 不能为空
 */
(function($){

	/**
	 * iuap的表格插件，用于简化iuap的表格的使用
	 * @param {Object} options 当调用时方法是option是方法名称，如果是初始化控件options是控件的初始化参数
	 * @param {Object} param 但调用时方法时候param 为方法的参数
	 */
	$.fn.formPlugin = function(options, param){
		if (typeof options == 'string'){
			return $.fn.formPlugin.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'formPlugin');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'formPlugin', {
					options: $.extend({}, $.fn.formPlugin.defaults,  options)
				});
			}
			_init(this,param)
		});
	};
	
	/**
	 * 初始化加载
	 * @param {Object} target DOM对象
	 * @param {Object} param
	 */
	function _init(target, param){
		var options = $.data(target, 'formPlugin').options;
		
	}
	
	/**
	 * 加载数据
	 * @param {Object} target DOM对象
	 * @param {Object} param 要加载的数据
	 */
	function _load(target, param){
		var options = $.data(target, 'formPlugin').options;
		
		
	}
	
	/**
	 * 保存数据
	 * @param {Object} target DOM对象
	 */
	function _save(target, param){
		var options = $.data(target, 'formPlugin').options;
		
	}
	
	$.fn.formPlugin.methods = {
		options: function(jq, param){
			return $.data(jq[0], 'formPlugin').options;
		},
		load: function(jq, param){
			return jq.each(function(){
				_load(this, param);
			});
		},
		save:function(jq, param){
			return jq.each(function(){
				_save(this, param);
			});
		}
	};

	$.fn.formPlugin.defaults = {
		url: null,
		gridObj:null,
		formData:null,
		onSaveSuccess:function(data){},
		onSaveError:function(){},
	};
	
})(jQuery);


/**
 * 表格插件依赖于iuap的dataGrid控件，可以选择是否依赖 fu-pagination 控件
 * 如果不依赖fu-pagination控件  pageSize pageNumber 不能为空
 */
(function($){

	/**
	 * iuap的表格插件，用于简化iuap的表格的使用
	 * @param {Object} options 当调用时方法是option是方法名称，如果是初始化控件options是控件的初始化参数
	 * @param {Object} param 但调用时方法时候param 为方法的参数
	 */
	$.fn.gridPlugin = function(options, param){
		if (typeof options == 'string'){
			return $.fn.gridPlugin.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'gridPlugin');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'gridPlugin', {
					options: $.extend({}, $.fn.gridPlugin.defaults,  options)
				});
			}
			_init(this,param)
		});
	};
	
	/**
	 * 初始化加载
	 * @param {Object} target DOM对象
	 * @param {Object} param
	 */
	function _init(target, param){
		
		var options = $.data(target, 'gridPlugin').options;
		//分页控件对象
		var paaginationObj = options.paginationObj;
		//分页控件对象的参数
		var popts = paaginationObj.pagination("options");
		//调用查询方法
		_query(options,{rows:popts.pageSize,page:popts.pageNumber},"init");
	}
	
	/**
	 * 加载数据
	 * @param {Object} target DOM对象
	 * @param {Object} param 要加载的数据
	 */
	function _load(target, param){
		
		var options = $.data(target, 'gridPlugin').options;
		//分页控件
		var paaginationObj = options.paginationObj;
		//分页控件参数
		var popts = paaginationObj.pagination("options");
		//设置分页参数为 1
		popts.pageNumber = 1;
		//如果参数为空
		param = param || {}
		//合并参数
		$.extend(param, {rows:popts.pageSize,page:popts.pageNumber});
		//调用查询方法
		_query(options,param,"_load");
	}
	
	/**
	 * 带参数刷新
	 * @param {Object} target DOM对象
	 */
	function _reLoad(target, param){
		var options = $.data(target, 'gridPlugin').options;
		_query(options,options.queryParam,"_reLoad");
	}
	
	/**
	 * 设置数据源
	 * @param {Object} target
	 * @param {Object} param
	 */
	function _setDataSource(target, param){
		var options = $.data(target, 'gridPlugin').options;
		options.gridObj.setDataSource(d);
	}
	
	/**
	 * 得到iuap的表格对象
	 * @param {Object} target
	 * @param {Object} param
	 */
	function _getGrid(target, param){
		var options = $.data(target, 'gridPlugin').options;
		return options.gridObj;
	}
	
	/**
	 * 查找数据
	 * @param {Object} options 表格参数
	 * @param {Object} queryParam 查询参数
	 * @param {Object} fun 谁调用的
	 */
	function _query(options,queryParam,fun){
		//合并查询参数
		$.extend(options.queryParam, queryParam);

		jQuery.ajax({
			type : 'post',
			dataType : "json",
			data:options.queryParam,
			url : options.url,
			success:function(data1){
				var d = {} ;
				d.values = data1.rows;
				options.gridObj.setDataSource(d);
				//保存成功回掉事件
				options.onDataSuccess.call(this,data1,fun);
				//分页控件对象
				var paaginationObj = options.paginationObj;
				//分页控件对象的参数
				var popts = paaginationObj.pagination("options");
				//刷新分页控件
				paaginationObj.pagination('refresh',{pageSize:popts.pageSize,total:data1.total});
			},
			error:function(data){
				//保存失败回掉事件
				options.onDataError.call(this,data1,fun);
				console.log(data);
			}
		});
	}
	
	$.fn.gridPlugin.methods = {
		options: function(jq, param){
			return $.data(jq[0], 'gridPlugin').options;
		},
		load: function(jq, param){
			return jq.each(function(){
				_load(this, param);
			});
		},
		reLoad:function(jq, param){
			return jq.each(function(){
				_reLoad(this, param);
			});
		},
		getGrid:function(jq, param){
			return jq.each(function(){
				_getGrid(this, param);
			});
		},
		queryParam:function(jq, param){
			return jq.each(function(){
				_queryParam(this,param);
			});
		},
		setDataSource:function(jq,param){
			return jq.each(function(){
				_setDataSource(this, param);
			});
		},
	};

	$.fn.gridPlugin.defaults = {
		url: null,
		gridObj:null,
		dataSource:null,
		queryParam:null,
		pageSize: 10,//每页显示多少条
		pageNumber: 1,//当前页
		pagination:false,//分页
		paginationObj:null,//分页对象
		onDataSuccess:function(data){},
		onDataError:function(){},
	};
	
})(jQuery);





