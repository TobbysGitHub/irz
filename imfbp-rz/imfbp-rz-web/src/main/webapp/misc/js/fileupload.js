{
	
	window.FileUpLoad = {};
	
	function clickPath(){
	    	document.getElementById("path").click();
	    	$("#path").change(function(){
	    		$("#myfile").val($(this).val());
	    	});
	    };
	//url代表后台接受路径， callFunction 附件上传后的回调方法
	FileUpLoad.show = function(url, callFunction){
	    var content = $("<div id='updateload_dialog'></div>");
	    content.append(
	    			'<div class="u-msg-content" >'+
	    			'<lable class="form-horizontal upload_form"  method="post" enctype="multipart/form-data">'+
	    				'<div class="col-xs-16" style="margin-left: 70px;">'+
	    					'<div class="row">'+
	    						'<div class="form-group ">'+
	    							'<div class="col-xs-8 padding-left-5" style="font-size: 14px;"'+
	    								'calss="control-label">请选择要导入的Excel文件</div>'+
	    						'</div>'+

	    						'<div class="form-group ">'+
	    						'<div class="col-xs-8 padding-left-5"  style="font-size: 14px;">'+
	    							'<input class="form-control" type="text" size="20" name="upfile" id="myfile" > ' +
	    						'</div>'+
	    						'<div class="col-xs-2 padding-left-5">'+
	    							'<input type="button" class="btn btn-small btn-action" value="浏览" onclick="clickPath()"> '+ 
	    						'</div>'+
	    							'<input type="file" name="path" id="path" style="display:none">'+
	    						'</div>'+
	    						'<div class="form-group">'+
	    							'<div class="col-xs-10 "'+
	    								'style="background-color: #FDF8E1; border-color: #FFFAEBCC;">'+
	    								'温馨提示<br> 重复数据将不会导入，导入前请检查数据有效性！<br><br> <br>'+
	    							'</div>'+
	    						'</div>'+
	    						'<div class="form-group">'+
	    							'<div class="col-xs-10 padding-left-5">'+
	    								'<button class="btn btn-out btn-small cancel-btn">取消</button>&nbsp;&nbsp;'+
	    								'<button class="btn btn-small btn-action uploadfile-btn">导入</button>'+
	    							'</div>'+
	    						'</div>'+
	    						
	    					'</div>'+
	    				'</div>'+
	    			'</lable>'+
	    		'</div>'
		
	    );
		var tDialog = BootstrapDialog.show({
	            title: '导入',
	            message: content,
	            buttons: [],
				onshown:function(dialog){
					//点击取消按钮事件
					$("#updateload_dialog .cancel-btn").click(function(){
						$("#myfile").val(null);
						$("#path").val(null);
					});
					//点击导入按钮事件
					$("#updateload_dialog .uploadfile-btn").click(function(){
		            	$.ajaxFileUpload({  
		    		        url : url,  
		    		        secureuri : false,  
		    		        fileElementId : 'path',  //文件上传域的ID
		    		        data : {},
		    		        dataType : 'json',
		    		        async:false,  
		    		        success : function(data) { 
		    		        	if(data.success==true){
		    		        		dialog.close();
		    		        	}
								callFunction(data);
		    		        } 
		    		    });  
					});
				}
		});
		tDialog.getModalDialog().css({width:'60%'});
	}
	
}