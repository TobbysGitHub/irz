
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
	 * @param {Object} param 传入的参数 {formData:formData,editType:editType{update,insert}}
	 */
	function _save(target, param) {
		var options = $.data(target, 'formPlugin').options;
		//合并查询参数
		$.extend(options, param);
		
		jQuery.ajax({
			type: 'post',
			dataType: "json",
			data: options.formData,
			url: options.url,
			success: function(data) {
				if(data.success) {
					//当saveSuccessType为true的时候自己处理保存成功的业务逻辑
					if(!options.saveSuccessType) {
						if(data.successMessage != null || data.successMessage!=undefined) {
							layer.alert(data.successMessage);
						} else {
							$("#edit_msg_out").css("display","block").html("保存成功");
							options.onSaveSuccess.call(this, data);
							setTimeout(function(){
								$("#edit_msg_out").hide();
							},2000)
						}
						if(options.editType=="edit"){
							options.gridPlugin.gridPlugin("reLoad",options.queryData);
						}else{
							options.gridPlugin.gridPlugin("load", {});
						}
					}
				
				} else {
					//当saveErrorType为true的时候自己处理保存失败的业务逻辑
					if(!options.saveErrorType) {
						if(data.errorMessage != null || data.errorMessage!=undefined) {
							layer.alert(data.errorMessage);
						} else {
							layer.alert('保存记录失败！');
						}
					}
					//在用户完成保存并且保存失败的时候触发，
					options.onSaveError.call(this, data);
				}
			},
			error: function(data) {
				//当saveErrorType为true的时候自己处理保存失败的业务逻辑
				if(!options.saveErrorType) {
					if(data.errorMessage != null || data.errorMessage!=undefined) {
						layer.alert(data.errorMessage);
					} else {
						layer.alert('保存记录失败！');
					}
				}
				//在用户完成保存并且保存失败的时候触发，
				options.onSaveError.call(this, data);
			}
		});
	}
	/**
	 * 保存提交数据数据
	 * @param {Object} target DOM对象
	 * @param {Object} param 传入的参数 {formData:formData,editType:editType{update,insert}}
	 */
	function _saveSubmit(target, param) {
		var options = $.data(target, 'formPlugin').options;
		//合并查询参数
		$.extend(options, param);
		
		jQuery.ajax({
			type: 'post',
			dataType: "json",
			data: options.formData,
			url: options.url,
			success: function(data) {
				if(data.success) {
					//当saveSuccessType为true的时候自己处理保存成功的业务逻辑
					if(!options.saveSuccessType) {
						if(data.successMessage != null || data.successMessage!=undefined) {
							layer.alert(data.successMessage);
						} 
//							$("#edit_msg_out").css("display","block").html("保存成功");
//							options.onSaveSuccess.call(this, data);
//							//保存成功，执行提交回调函数
//							setTimeout(function(){
//								$("#edit_msg_out").hide();
//							},2000)
//						}
//						if(options.editType=="edit"){
//							options.gridPlugin.gridPlugin("reLoad",options.queryData);
//						}else{
//							options.gridPlugin.gridPlugin("load", {});
//						}
						options.onSubmitData.call(this, data);
					}
					
				} else {
					//当saveErrorType为true的时候自己处理保存失败的业务逻辑
					if(!options.saveErrorType) {
						if(data.errorMessage != null || data.errorMessage!=undefined) {
							layer.alert(data.errorMessage);
						} else {
							layer.alert('保存记录失败！');
						}
					}
					//在用户完成保存并且保存失败的时候触发，
					options.onSaveError.call(this, data);
				}
			},
			error: function(data) {
				//当saveErrorType为true的时候自己处理保存失败的业务逻辑
				if(!options.saveErrorType) {
					if(data.errorMessage != null || data.errorMessage!=undefined) {
						layer.alert(data.errorMessage);
					} else {
						layer.alert('保存记录失败！');
					}
				}
				//在用户完成保存并且保存失败的时候触发，
				options.onSaveError.call(this, data);
			}
		});
	}
	
	
	function _saveMongo(target, param) {
		var options = $.data(target, 'formPlugin').options;
		//合并查询参数
		$.extend(options, param);
		var saveData = JSON.stringify(options.formData)
		
		jQuery.ajax({
			type: 'post',
			dataType: "json",
			data: {"saveData":saveData},
			url: options.url,
			success: function(data) {
				if(data.success) {
					//当saveSuccessType为true的时候自己处理保存成功的业务逻辑
					if(!options.saveSuccessType) {
						if(data.successMessage != null || data.successMessage!=undefined) {
							layer.alert(data.successMessage);
						} else {
							$("#edit_msg_out").css("display","block").html("修改成功");
							setTimeout(function(){
								$("#edit_msg_out").hide();
							},2000)
						}
						if(options.editType=="edit"){
							options.gridPlugin.gridPlugin("reLoad",options.queryData);
						}else{
							options.gridPlugin.gridPlugin("load", {});
						}
					}
					//在用户完成保存并且保存成功的时候触发，
					options.onSaveSuccess.call(this, data);
				} else {
					//当saveErrorType为true的时候自己处理保存失败的业务逻辑
					if(!options.saveErrorType) {
						if(data.errorMessage != null || data.errorMessage!=undefined) {
							layer.alert(data.errorMessage);
						} else {
							layer.alert('保存记录失败！');
						}
					}
					//在用户完成保存并且保存失败的时候触发，
					options.onSaveError.call(this, data);
				}
			},
			error: function(data) {
				//当saveErrorType为true的时候自己处理保存失败的业务逻辑
				if(!options.saveErrorType) {
					if(data.errorMessage != null || data.errorMessage!=undefined) {
						layer.alert(data.errorMessage);
					} else {
						layer.alert('保存记录失败！');
					}
				}
				//在用户完成保存并且保存失败的时候触发，
				options.onSaveError.call(this, data);
			}
		});
	}
	
	/**
	 * 删除数据
	 * @param {Object} target DOM对象
	 * @param {Object} param 要加载的数据
	 */
	function _delete(target, param){
		var options = $.data(target, 'formPlugin').options;
		var flag=false
		layer.confirm('确认删除这几条数据?', {icon: 3, title:'提示'}, function(index){
  		layer.close(index);
		jQuery.ajax({
			type : 'post',
			dataType : "json",
			data: {batchId:param.batchId},
			url : param.url,
			success:function(data){
				if(data.success) {
					//当saveSuccessType为true的时候自己处理保存成功的业务逻辑
					if(!options.deleteSuccessType) {
						if(data.successMessage != undefined) {
							alert(data.successMessage);
						} else {
							$("#edit_msg_out").css("display","block").html("删除成功");
							setTimeout(function(){
								$("#edit_msg_out").hide();
								options.gridPlugin.gridPlugin("load", {});
								//在用户完成保存并且保存成功的时候触发，
								options.onDeleteSuccess.call(this, data);
							},2000)
						}
					}
				} else {
					//当saveErrorType为true的时候自己处理保存失败的业务逻辑
					if(!options.deleteErrorType) {
						if(data.errorMessage != undefined) {
							layer.alert(data.errorMessage);
						} else {
							layer.alert('删除记录失败！');
						}
					}
					//在用户完成保存并且保存失败的时候触发，
					options.onDeleteError.call(this, data);
				}
			},
			error:function(data){
				//保存失败回掉事件
				layer.alert("删除失败");
			}
		});
		})
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
		},
		saveMongo:function(jq, param){
			return jq.each(function(){
				_saveMongo(this, param);
			});
		},
		remove:function(jq,param){
			return jq.each(function(){
				_delete(this, param);
			});
		},
		saveSubmit:function(jq,param){
			return jq.each(function(){
				_saveSubmit(this, param);
			});
		}
	};

	$.fn.formPlugin.defaults = {
		url: null,
		gridPlugin:null,
		formData:null,
		onSaveSuccess:function(data){},
		onSaveError:function(data){},
		saveSuccessType:false,//true 为自己处理保存成功的业务逻辑
		saveErrorType:false, //true 为自己处理保存失败的业务逻辑
		editType:'insert',
		onDeleteSuccess:function(data){},
		onDeleteError:function(data){},
		deleteSuccessType:false,//true 为自己处理保存成功的业务逻辑
		deleteErrorType:false //true 为自己处理保存失败的业务逻辑
	};
	
	
})(jQuery);