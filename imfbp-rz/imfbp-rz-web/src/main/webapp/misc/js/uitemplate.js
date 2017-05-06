/**
 * 
 * 本文件是基于jQuery1.9和bootstrap样式的实现 是自定义模板功能解析固定JSON格式的定义和解析器
 * 
 * @author 兵
 */

(function($) {

	$.fn.iui = function(options, param){
		
		if (typeof options == 'string'){
			return $.fn.iui.methods[options](this, param);
		}
		
		options = options || {};
		
		var html = $("<div></div>");
		
		_buildHTML(html, options, param);
		
		this.append(html);
	}
	
	$.fn.iform = function(options, editable){
		
		var form = $('<form></form>');
		
		form.attr("layoutType", options.layoutType);
		
		if(options.id)
		{
			form.prop("id", options.id);
		}
		
		if(options.name)
		{
			form.prop("name", options.name);
		}
		
		if(options.layoutDetail)
		{
			$.each(options.layoutDetail,function(i, d){
				
				_buildHTML(form, d, editable);
			});
		}
		
		if(options.clazz)
		{
			this.addClass(options.clazz);
		}
		
		if(options.dataBind)
		{
			this.attr("data-bind", options.dataBind);
		}
		
		this.append(form);
	}
	
	$.fn.icolumnpanel = function(options, editable){
		
		var colomPanel = $("<div class='row panel'></div>");
		
		var _colId = "_iui_col_" + getUUID();
		
		if(options.label)
		{
			var columnLabel = $('<div class="col-xs-12 panel-heading" data-toggle="collapse" data-target="#' + _colId + '"><h5>' + options.label + '</h5></div>');
			colomPanel.append(columnLabel);
		}
		
		var columnBody = $('<div class="col-xs-12 panel-body collapse in" id="' + _colId + '"></div>');
		
		if(options.clazz)
		{
			columnBody.addClass(options.clazz);
		}
		
		if(options.dataBind)
		{
			columnBody.attr("data-bind", options.dataBind);
		}
		
		if(options.layoutDetail)
		{
			$.each(options.layoutDetail,function(i, d){
				
				var column = $("<div></div>");
				if(options.columnClazz)
				{
					column.addClass(options.columnClazz);
				}
				_buildHTML(column, d, editable);
				columnBody.append(column);
			});
		}
		
		colomPanel.append(columnBody);
		this.append(colomPanel);
	}
	
	$.fn.itext = function(options, editable){
		
		if(options.isDisplay)
		{
			var text = $('<div><input type="text"/></div>');
			
			if(options.clazz)
			{
				text.addClass(options.clazz);
			}
			
			if(!editable)
			{
				text.find(":first-child").prop("readOnly", true);
			}
			
			if(options.updatable === false)
			{
				text.find(":first-child").prop("disabled", true);
			}
			
			if(options.id)
			{
				text.find(":first-child").prop("id", options.id);
			}
			
			if(options.name)
			{
				text.find(":first-child").prop("name", options.name);
			}
			
			if(options.describe || options.label)
			{
				text.find(":first-child").prop("title", options.describe || options.label);
			}
			
			if(options.dataBind)
			{
				text.find(":first-child").attr("data-bind", options.dataBind);
			}
			
			if(options.validators)
			{
				text.find(":first-child").attr("validators", options.validators);
			}
			
			if(options.label && options.isLabelDisplay)
			{
				var label = $('<label>' + options.label + '</label>');
				
				if(options.required)
				{
					label.prepend('<span>* </span>');
				}
				
				if(options.labelClazz)
				{
					label.addClass(options.labelClazz);
				}
				
				this.append(label);
			}
			
			this.append(text);
		}
	}
	
	
	$.fn.itextarea = function(options, editable){
		
		if(options.isDisplay)
		{
			var textarea = $('<textarea></textarea>');
			
			if(options.clazz)
			{
				textarea.addClass(options.clazz);
			}
			
			if(!editable)
			{
				textarea.find(":first-child").prop("readOnly", true);
			}
			
			if(options.updatable === false)
			{
				textarea.find(":first-child").prop("disabled", true);
			}
			
			if(options.id)
			{
				textarea.prop("id", options.id);
			}
			
			if(options.name)
			{
				textarea.prop("name", options.name);
			}
			
			if(options.describe || options.label)
			{
				textarea.prop("title", options.describe || options.label);
			}
			
			if(options.dataBind)
			{
				textarea.attr("data-bind", options.dataBind);
			}
			
			if(options.validators)
			{
				textarea.attr("validators", options.validators);
			}
			
			if(options.label && options.isLabelDisplay)
			{
				var label = $('<label>' + options.label + '</label>');
				
				if(options.required)
				{
					label.prepend('<span>* </span>');
				}
				
				if(options.labelClazz)
				{
					label.addClass(options.labelClazz);
				}
				
				this.append(label);
			}
			
			this.append(textarea);
		}
	}
	
	$.fn.icombobox = function(options, editable){
		
		if(options.isDisplay)
		{
			var text;
			
			if(options.sync)
			{
				text = $('<div><input type="text"/></div>');
				text.append('<div class="icon-wrap"><i class="' + (options.iconClazz ? options.iconClazz : "glyphicon glyphicon-chevron-down") + '"></i></div>');
				
			}
			else
			{
				text = $('<div><select></select></div>');
			}
			
			if(options.clazz)
			{
				text.addClass(options.clazz);
			}
			
			if(!editable)
			{
				text.find(":first-child").prop("readOnly", true);
			}
			
			if(options.updatable === false)
			{
				text.find(":first-child").prop("disabled", true);
			}
			
			if(options.id)
			{
				text.find(":first-child").prop("id", options.id);
			}
			
			if(options.name)
			{
				text.find(":first-child").prop("name", options.name);
			}
			
			if(options.describe || options.label)
			{
				text.find(":first-child").prop("title", options.describe || options.label);
			}
			
			if(options.dataBind)
			{
				text.find(":first-child").attr("data-bind", options.dataBind);
			}
			
			if(options.validators)
			{
				text.find(":first-child").attr("validators", options.validators);
			}
			
			if(options.change)
			{
				text.find(":first-child").on("change",options.change);
			}
			
			if(options.label && options.isLabelDisplay)
			{
				var label = $('<label>' + options.label + '</label>');
				
				if(options.required)
				{
					label.prepend('<span>* </span>');
				}
				
				if(options.labelClazz)
				{
					label.addClass(options.labelClazz);
				}
				
				this.append(label);
			}
			
			this.append(text);
		}
	}
	
	$.fn.ireference = function(options, editable){
		
		if(options.isDisplay)
		{
			var text = $('<div><input type="text"/></div>');
			
			if(options.clazz)
			{
				text.addClass(options.clazz);
			}
			
			
			if(options.updatable === false)
			{
				text.find(":first-child").prop("disabled", true);
			}
			
			if(options.id)
			{
				text.find(":first-child").prop("id", options.id);
			}
			
			if(options.name)
			{
				text.find(":first-child").prop("name", options.name);
			}
			
			if(options.describe || options.label)
			{
				text.find(":first-child").prop("title", options.describe || options.label);
			}
			
			if(options.dataBind)
			{
				text.find(":first-child").attr("data-bind", options.dataBind);
			}
			
			if(options.targetKey)
			{
				text.find(":first-child").attr("target-key", options.targetKey);
			}
			
			if(options.selectcol)
			{
				text.find(":first-child").attr("selectcol", options.selectcol);
			}
			
			if(options.validators)
			{
				text.find(":first-child").attr("validators", options.validators);
			}
			
			if(options.change)
			{
				text.find(":first-child").on("change",options.change);
			}
			
			if(!editable)
			{
				text.find(":first-child").prop("readOnly", true);
			}
			else
			{
				if(options.updatable !== false)
				{
					text.append('<div class="icon-wrap"><i class="' + (options.iconClazz ? options.iconClazz : "glyphicon glyphicon-th-list") + '" data-imfbp-ref="ref"></i></div>');
				
					if(options.dataImfbpRefName)
					{
						text.find("i").attr("data-imfbp-ref-name", options.dataImfbpRefName);
					}
					
					if(options.dataImfbpRefFun)
					{
						text.find("i").attr("data-imfbp-ref-fun", options.dataImfbpRefFun);
					}
					
					if(options.refName)
					{
						text.find("i").attr("name", options.refName);
					}
				}
			}
			
			if(options.label && options.isLabelDisplay)
			{
				var label = $('<label>' + options.label + '</label>');
				
				if(options.required)
				{
					label.prepend('<span>* </span>');
				}
				
				if(options.labelClazz)
				{
					label.addClass(options.labelClazz);
				}
				
				this.append(label);
			}
			
			this.append(text);
		}
	}
	
	
	
	//默认基础类
	$.fn.iui.defaults = {
		layoutDetail : [],
		label : "",
		labelLayout : "",
		size : "normal",
		width : "",
		height : "",
		clazz : "",
		dataBind : ""
	};

	//Form描述
	$.fn.iform.defaults = $.extend({}, $.fn.iui.defaults, {
		componentKey : "Form",
		id : "",
		layoutType : "pc",
		name : ""
	});

	//列面板描述
	$.fn.icolumnpanel.defaults = $.extend({}, $.fn.iui.defaults, {
		componentKey : "ColumnPanel",
		columnClazz : ""
	});

	//文本输入框描述
	$.fn.itext.defaults = $.extend({}, $.fn.iui.defaults, {
		componentKey : "Text",
		id : "",
		name : "",
		describe : "",
		updatable : true,
		required : false,
		isDisplay : true,
		isLabelDisplay : true,
		labelClazz : "",
		isBusinessObj : false,
		validators : ""
	});
	
	//文本域描述
	$.fn.itextarea.defaults = $.extend({}, $.fn.itext.defaults, {
		componentKey : "TextArea"
	});
	
	//下拉框描述，使用layoutDetail属性添加选项
	$.fn.icombobox.defaults = $.extend({}, $.fn.itext.defaults, {
		componentKey : "ComboBox",
		sync : false,
		iconClazz : "",
		change : function(){
			
		}
	});
	
	//参照描述
	$.fn.ireference.defaults = $.extend({}, $.fn.itext.defaults, {
		componentKey : "Reference",
		iconClazz : "",
		targetKey : "",
		selectcol : "",
		dataImfbpRefName : "",
		dataImfbpRefFun : "",
		click : function(){
			
		},
		callback : function(){
			
		}
	});
	
	
	function _buildHTML(target, options, editable){
		
		if(typeof options !== "object")
		{
			return target;
		}
	
		switch (options.componentKey) {
		case "Form":
				
			target.iform(options, editable);
			break;
		case "ColumnPanel":
			
			target.icolumnpanel(options, editable);
			break;
		case "Text":
			
			target.itext(options, editable);
			break;
		case "TextArea":
			
			target.itextarea(options, editable);
			break;
		case "ComboBox":
			
			target.icombobox(options, editable);
			break;
		case "Reference":
		
			target.ireference(options, editable);
			break;
		default:
			
			break;
		}
		
		return target;
	
	}
	
	
	
	$.fn.iui.methods = {
			options : function(jq){
				return $.data(jq[0], 'iui').options;
			},
			loadData: function(jq, param){
				
				return jq.each(function(){
					
					var _this = $(this);
					param = param || {};
					
					if(!_this.children().length)
					{
						jQuery.ajax({
							type: 'post',
							dataType: "json",
							data: param.data,
							url: param.url,
							success: function(data) {
								//console.log(data.formContent);
								var json = jQuery.parseJSON(data.formContent);
						        _this.iui(json, param.editable);
								
								if(param.callback)
								{
									param.callback(true);
								}
							}
						});
					}
					else
					{
						if(param.callback)
						{
							param.callback(false);
						}
					}
				});
			},
			valid : function(jq, param){
				
				return jq.find("input").each(function(){
					
					if(this.onValid)
					{
						return this.onValid.call();
					}
					return true;
				});
			}
		};
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	function getUUID() 
	{  
        var id = setTimeout('0');  
        clearTimeout(id);  
        return id;  
	};  
	
	
	
	
	/*
	 * @description 根据某个字段实现对json数组的排序 
	 * @param array 要排序的json数组对象 
	 * @param field 排序字段（此参数必须为字符串） 
	 * @param reverse 是否倒序（默认为false） 
	 * @return array 返回排序后的json数组
	 */
	function jsonSort(array, field, reverse) {
		// 数组长度小于2 或 没有指定排序字段 或 不是json格式数据
		if (array.length < 2 || !field || typeof array[0] !== "object")
			return array;
		// 数字类型排序
		if (typeof array[0][field] === "number") {
			array.sort(function(x, y) {
				return x[field] - y[field]
			});
		}
		// 字符串类型排序
		if (typeof array[0][field] === "string") {
			array.sort(function(x, y) {
				return x[field].localeCompare(y[field])
			});
		}
		// 倒序
		if (reverse) {
			array.reverse();
		}
		return array;
	}
})(jQuery);