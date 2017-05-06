$(document).ready(function() {
	$('#crmVisitScheduleEditFormId').bootstrapValidator({
	        /* 隐藏图
	    	feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        */
	        fields: {
	        	visitDate: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            },
	            salesman: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            },
	            visitCustomer: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            }
	        }
	    });
});