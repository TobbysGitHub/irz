{
	window.loanAjax = function(){};
	
	
	loanAjax.prototype.post = function(url,postdata,callfunc){
		this.openWaitDialog();
		var obj = this;
        $.post(url,postdata,function(data){
        	obj.closeWaitDialog();
        	if(typeof callfunc === 'function'){
			callfunc(data);
		}
        });
	}
	loanAjax.prototype.openWaitDialog = function(){
	
		if(this.dialog == null){
			var dialog = BootstrapDialog.show({
				            message: '<div class="u-loading is-active u-loading-single-color"></div>',
				            size:BootstrapDialog.SIZE_SMALL,
				            closable: true,
				            closeByBackdrop: false,
				            closeByKeyboard: false,
				            buttons: []
			});
			dialog.getModalHeader().hide();
	        dialog.getModalFooter().hide();
	        this.dialog = dialog;
        }else{
        	this.dialog.open();
        }
	}
	
	
	loanAjax.prototype.closeWaitDialog = function(){
		if(this.dialog != null){
			this.dialog.close();
		}
	}
}