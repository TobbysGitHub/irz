$(document).ready(function() {
	$('#crmVisitPlanEditFormId').bootstrapValidator({
	        /* 隐藏图
	    	feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        */
	        fields: {
	        	salesman: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            },
	            planName: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            },
	            enabledState: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            }
	        }
	    });
});