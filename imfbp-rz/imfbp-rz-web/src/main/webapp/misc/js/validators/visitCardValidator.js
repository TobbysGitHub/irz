$(document).ready(function() {
	visitCardFormValidator();
});

function resetVisitcardFormValidator(){
	$("#crmVisitCardModule .crmVisitCardEditForm").data('bootstrapValidator').destroy();
	$('#crmVisitCardModule .crmVisitCardEditForm').data('bootstrapValidator', null);
	
	visitCardFormValidator();
}

function visitCardFormValidator(){
	 $('#crmVisitCardModule .crmVisitCardEditForm').bootstrapValidator({
	        /* 隐藏图
	    	feedbackIcons: {
	            valid: 'glyphicon glyphicon-ok',
	            invalid: 'glyphicon glyphicon-remove',
	            validating: 'glyphicon glyphicon-refresh'
	        },
	        */
	        fields: {
	        	visitName: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            },
	            visitObject: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            },
	            visitContent: {
	                validators: {
	                	notEmpty: {
	                        message: '必填项'
	                    }
	                }
	            }
	        }
	    });
};