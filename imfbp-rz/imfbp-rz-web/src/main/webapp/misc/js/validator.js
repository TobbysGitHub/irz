(function() {
	$.fn.initValidator = function(group) {
		var _form = this;
		$($(this)[0].elements).each(function() {
			//转换成jquery对象
			var _this = $(this);
			//得到校验规则信息
			var validatorsStr = _this.attr("validators")
				//判断元素上是否有校验规则
			if(validatorsStr) {
				//转换成js对象
				validators = (new Function('return ' + validatorsStr))();
				_form.bootstrapValidator('addField', _this.attr("name"), {
					group: group,
					validators: validators
				});
			}
		});
	}
})()
		