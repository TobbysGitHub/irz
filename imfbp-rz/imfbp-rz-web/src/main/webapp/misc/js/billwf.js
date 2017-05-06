{
	
	//URL地址链表
	function linkNode(){
		this.parentNode = null;
		this.nextNode = null;
		this.title = "";
		this.setUrl = function(url){
			this.url = url;
		}
		
		this.setTitle = function(title){
			this.title = title;
		}
		
		this.setParent = function(parentNode){
			this.parentNode = parentNode;
			
		}
		this.setNext = function(nextNode){
			
			this.nextNode = nextNode;
		}
	}
	
	//链接管理器
	function LinkNodeManager(){
		
		this.currentNode = null;
		this.currentDialog = null;
		
		this.isHasNext = function(){
			
			if(this.currentNode != null && this.currentNode.nextNode != null){
				return true;
			}
			
			return false;
		}
		
		this.setDialog = function(dialog){
			
			this.currentDialog = dialog;
		}
		
		this.isHasParent = function(){
			
			if(this.currentNode != null && this.currentNode.parentNode != null){
				return true;
			}
			
			return false;
		}
		
		this.nextNode = function(){
			
			this.currentNode = this.currentNode.nextNode;
			this.dumpUrl();
		}
		
		this.parentNode = function(){
			this.currentNode= this.currentNode.parentNode;
			this.dumpUrl();
		}
		
		this.addNode = function(node){
			
			if(this.currentNode != null){
				this.currentNode.setNext(node);
				node.setParent(this.currentNode);
			}
			
			this.currentNode = node;
		}
		
		this.dumpUrl = function(){
			
			if(this.currentNode != null && this.currentDialog != null){
				
				$("#linkquery").attr("src",this.currentNode.url);
			}
			
		}
		
		this.controlDialogBtn = function(){
			
			if(this.isHasNext()){
				
				$("#dialogRightBtn").removeAttr("disabled");
			}else{
				
				$("#dialogRightBtn").attr("disabled","disabled");
			}
			
			if(this.isHasParent()){
				$("#dialogLeftBtn").removeAttr("disabled");
			}else{
				
				$("#dialogLeftBtn").attr("disabled","disabled");
			}
			
			if(this.currentDialog != null)
			this.currentDialog.getModalDialog().find('.bootstrap-dialog-title').text(this.currentNode.title);
		}
	}
	
	
	window.linkNodeManager = new LinkNodeManager();
	
	// 对Date的扩展，将 Date 转化为指定格式的String  
	Date.prototype.Format = function(fmt) { 
	  var o = { 
	    "M+" : this.getMonth()+1,                 //月份 
	    "d+" : this.getDate(),                    //日 
	    "h+" : this.getHours(),                   //小时 
	    "m+" : this.getMinutes(),                 //分 
	    "s+" : this.getSeconds(),                 //秒 
	    "q+" : Math.floor((this.getMonth()+3)/3), //季度 
	    "S"  : this.getMilliseconds()             //毫秒 
	  }; 
	  if(/(y+)/.test(fmt)) 
	    fmt=fmt.replace(RegExp.$1, (this.getFullYear()+"").substr(4 - RegExp.$1.length)); 
	  for(var k in o) 
	    if(new RegExp("("+ k +")").test(fmt)) 
	  fmt = fmt.replace(RegExp.$1, (RegExp.$1.length==1) ? (o[k]) : (("00"+ o[k]).substr((""+ o[k]).length))); 
	  return fmt; 
	}
	
	
	window.BillWF = {};
	

	BillWF.callback = function($datastr,nodecode,callFunction){
		$.post(homeModuleUrl + "/imfbpm/callBack",{datastr:$datastr,nodecode:nodecode},
	        	function(data){
					//处理返回值
					callFunction(data);
	        	});
	}
	
	BillWF.submit = function($datastr,nodecode,callFunction,assignstr,commentsPara,auditResultPara){
		if(assignstr == null || assignstr == undefined){
			assignstr = "";
		}
		$.post(homeModuleUrl + "/imfbpm/startProcess",{datastr:$datastr,nodecode:nodecode,assignitems:assignstr},
	        	function(data){
					if(data.map.assign){
						//处理指派
						BillWF.assignCheck(data.map.assignees,$datastr,nodecode,callFunction,BillWF.submit);
					} else {
						//处理返回值
						callFunction(data);
					}
	        	});
	}
	
	BillWF.assignCheck = function(assignees,$datastr,nodecode,basecallFunction,callFunction,commentsPara,auditResultPara){
		
		var titlestr = "<div class='row'><div class='col-xs-5'><select id='multiselect' multiple='multiple' class='form-control' size='8' >";
		for (var int = 0; int < assignees["participants"].length; int++) {
			 var assignpsn = assignees["participants"][int];
			 titlestr = titlestr +"<option value='"+assignpsn["id"]+"'>"+assignpsn["name"]+"</option>";
		 }
		titlestr = titlestr + "</select></div><div class='col-xs-2'>"
				+"<button type='button' class='btn btn-block' id='multiselect_rightAll'><i class='glyphicon glyphicon-forward'></i></button>"
				+"<button type='button' class='btn btn-block' id='multiselect_rightSelected'><i class='glyphicon glyphicon-chevron-right'></i></button>"
				+"<button type='button' class='btn btn-block' id='multiselect_leftSelected'><i class='glyphicon glyphicon-chevron-left'></i></button>"
				+"<button type='button' class='btn btn-block' id='multiselect_leftAll'><i class='glyphicon glyphicon-backward'></i></button>"
				+"</div><div class='col-xs-5'><select id='multiselect_to'  multiple='multiple' class='form-control' size='8'></select></div></div>" 
				
		 var content = $(titlestr);
		  BootstrapDialog.show({
	            title: '指派',
	            message: content,
	            buttons: [{
	                label: '确定',
	                action: function(dialog) {
	                
	                	var selectedAreaArray = [];
	                	 $("#multiselect_to option").each(function(i){
	                		 selectedAreaArray[i] = $(this).val();
	                	 });
	                	 
	                	 if(selectedAreaArray.length <= 0 ){
	                		 return;
	                	 }
	                	 dialog.close();
	                	 var selectedassignees = new Array();
	                	 for (var int = 0; int < assignees["participants"].length; int++) {
	            			 var assignpsn = assignees["participants"][int];
	            			 var psnid = assignpsn["id"];
	            			 
	            			 for (var j = 0; j < selectedAreaArray.length; j++) {
								if(selectedAreaArray[j] == psnid){
									selectedassignees[j] = assignpsn;
								}
							}
	            		 }
	                	 assignees.participants = selectedassignees;
	                	var assignstr = JSON.stringify(assignees);
	                	
	                	callFunction($datastr,nodecode,basecallFunction,assignstr,commentsPara,auditResultPara);
	                }
	            },{
	                label: '取消',
	                action: function(dialog) {
	                    dialog.close();
	                }
	            	
	            }],
	            onshown: function(dialogRef){
	            	 var j_all_area = $("#multiselect"), j_selected_areas = $("#multiselect_to");
	            	    $("#multiselect_rightAll").click(function(){
	            	        j_all_area.find("option").each(function(){
	            	            $(this).appendTo(j_selected_areas);
	            	        });
	            	        return false;
	            	    });
	            	    $("#multiselect_rightSelected").click(function(){
	            	        
	            	        j_all_area.find("option:selected").each(function(){
	            	            $(this).appendTo(j_selected_areas);
	            	        });
	            	        return false;
	            	    });
	            	    $("#multiselect_leftSelected").click(function(){
	            	        j_selected_areas.find("option:selected").each(function(){
	            	            $(this).appendTo(j_all_area);
	            	        });
	            	        return false;
	            	    });
	            	    $("#multiselect_leftAll").click(function(){
	            	        j_selected_areas.find("option").each(function(){
	            	            $(this).appendTo(j_all_area);
	            	        });
	            	        j_selected_areas.find("option").each(function(){
	            	            $(this).appendTo(j_all_area);
	            	        });
	            	        return false;
	            	    });
	            	    j_all_area.find("option").on("dblclick", function(){
	            	        if ($(this).closest("select").is(j_all_area)) {
	            	            $("#multiselect_rightSelected").click();
	            	        }
	            	        else {
	            	            $("#multiselect_leftSelected").click();
	            	        }
	            	        
	            	        return false;
	            	    });
	            	    j_selected_areas.find("option").on("dblclick", function(){
	            	        if ($(this).closest("select").is(j_all_area)) {
	            	            $("#multiselect_rightSelected").click();
	            	        }
	            	        else {
	            	            $("#multiselect_leftSelected").click();
	            	        }
	            	        return false;
	            	    });
	            }
		  });
	}
	
	
	
	//提交
	BillWF.approve = function($datastr,nodecode,callFunction,assignstr,commentsPara,auditResultPara){
		
	if($datastr == null || typeof $datastr == 'undefined'){
		BootstrapDialog.alert({
			title : '提示',
			message : '请选择数据'
		});

		return;
	}
	if(assignstr == null || assignstr == undefined){
		assignstr = "";
	}
	var dataobj = JSON.parse($datastr);
	var flowinstanceid = dataobj["flowinstanceid"];
	
	if(flowinstanceid == null || flowinstanceid == "" || flowinstanceid == undefined){
	    $.post(homeModuleUrl +"/imfbpm/auditTask",{datastr:$datastr,nodecode:nodecode,assignitems:assignstr,commentsPara:"同意",auditResultPara:"tongyi"},
	        	function(data){
	        	
	        		if(typeof callFunction === 'function'){
	        			callFunction(data);
	        		}
	        	});
	} else {
		if(assignstr == ""){
			 var content = $("<div id='billwf'></div>");
			    content.append(" <div id='agree' class='row' align='center'>"+
			    	       " <input name='agreeRadio' type='radio'  value='tongyi' checked> 同意&nbsp;&nbsp;&nbsp;&nbsp; "+
			    	       " <input name='agreeRadio' type='radio'  value='butongyi'> 不同意&nbsp;&nbsp;&nbsp;&nbsp;" +
			    	       " <input name='agreeRadio' type='radio'  value='bohui'> 驳回制单人  </div>");
			    
			    content.append("<div class='panel panel-success' style='margin-top:5px'><div class='panel-heading'>意见</div>"+
			    		
			    "<div  class='panel-body'><textarea id='agreeComment'  class='form-control' rows='4' maxlength='100' /></div></div>");
			    	       
			    	
				BootstrapDialog.show({
			            title: '审批',
			            message: content,
			            buttons: [{
			                label: '确定',
			                action: function(dialog) {
								var agreeType = $('#billwf #agree input[name="agreeRadio"]:checked ').val();
								var comment = $('#billwf #agreeComment').val();
								//$datastr["commentsPara"] = comment;
								//$datastr["auditResultPara"] =  agreeType;
						        $.post(homeModuleUrl +"/imfbpm/auditTask",{datastr:$datastr,nodecode:nodecode,assignitems:assignstr,commentsPara:comment,auditResultPara:agreeType},
						        	function(data){
						        		dialog.close();
						        		if(data.map.assign){
						        			//处理指派
						        			BillWF.assignCheck(data.map.assignees,$datastr,nodecode,callFunction,BillWF.approve,comment,agreeType);
						        		} else {
							        		if(typeof callFunction === 'function'){
							        			callFunction(data);
							        		}
						        		} 
						        		
						        	});
			                }
			            }, {
			                label: '取消',
			                action: function(dialog) {
			                    dialog.close();
			                }
			            }],
						onshown:function(){
							
							$('#billwf #agree input').iCheck({
								checkboxClass: 'icheckbox_square',
								radioClass: 'iradio_square',
								increaseArea: '20%' // optional
							});
						}
			        });
			} else {
				$.post(homeModuleUrl +"/imfbpm/auditTask",{datastr:$datastr,nodecode:nodecode,assignitems:assignstr,commentsPara:commentsPara,auditResultPara:auditResultPara},
			        	function(data){
			        		if(typeof callFunction === 'function'){
			        			callFunction(data);
			        		}
			        	});
			}
		}
	};
	
	//弃审
	BillWF.unApprove = function($datastr,nodecode, callFunction){
		if($datastr == null || typeof $datastr == 'undefined'){
			BootstrapDialog.alert({
				title : '提示',
				message : '请选择数据'
			});
			return;
		}
		
		var data = JSON.parse($datastr);
		
		var flowinstanceid = data["flowinstanceid"];
		var approvestatus = data["approvestatus"];
		if ((flowinstanceid==null||flowinstanceid=="") && approvestatus!=null && approvestatus=="3"){
				  $.post(homeModuleUrl +"/imfbpm/withDrawTaskDirect",{datastr:$datastr,nodecode:nodecode},
				        	function(data){
					  
					  			if(typeof callFunction === 'function'){
					  				callFunction(data);
					  			}
				        	}
				  
				  );
				  
				  return;
			}
		
		$.post(homeModuleUrl +"/imfbpm/withDrawTask",{datastr:$datastr,nodecode:nodecode},
				function(data){
					dialog.close();
					if(typeof callFunction === 'function'){
						callFunction(data);
					}
					
				});
	};
	
	function dateTimeFormatter(value) {
		var formatTime = new Date(value).Format("yyyy-MM-dd hh:mm:ss");
		if(formatTime =='1970-01-01 08:00:00'){
			return;
		} else {
			return formatTime;
		}
	}
	
	function deleteReasonFormatter(value) {
		var deleteReason ="";
		if(value=='completed'){
			deleteReason ='完成';
		} else if(value=='deleted'){
			deleteReason ='删除';
		} else if(value=='withdraw'){
			deleteReason ='驳回';
		}
		return deleteReason;
	}
	
	//联查审批
	BillWF.linkApprove = function($data, callFunction){
		
		var content = $("<div id='billwf'></div>");
		var processDefinitionId = $data["processDefinitionId"];
		var processInstanceId = $data["processInstanceId"]; 
		var diagramUrl = homeModuleUrl+"bpm/diagram-viewer/index.html?processDefinitionId="+processDefinitionId+"&processInstanceId="+processInstanceId+"&random="+Math.random();
		content.append("<div class='panel panel-success' >"+
		"<div  class='panel-body'><iframe id='historyGraph'  src='"+diagramUrl+"' width='100%' height='300px' style='border: 0px;' ></iframe></div></div>");
		
		 
        
		var table = "<table id='billwftask' data-toggle='table' data-search='false' data-show-refresh='false' data-show-toggle='false'"+
		" data-show-columns='false' data-height='200' data-side-pagination = 'client' >"+
		
		
		"<thead> <th data-field='name' data-halign='center' data-align='center'>活动名称</th>" +
		"<th data-field='assignee' data-halign='center' data-align='center'>执行者</th>"+
		"<th data-field='startTime' data-halign='center'   data-formatter='dateTimeFormatter' data-align='center'>开始时间</th>" +
		"<th data-field='endTime'   data-halign='center'   data-formatter='dateTimeFormatter' data-align='center'>结束时间</th>"+
		"<th data-field='taskComments' data-halign='center' data-align='center'>审核批语</th>" +
		"<th data-field='deleteReason' data-halign='center' data-formatter='deleteReasonFormatter' data-align='center'>任务状态</th>" +
		"</thead></table>";
	
    
		content.append("<div class='panel panel-success' ><div class='panel-heading'>历史任务</div>"+table+"</div>");
		
	
		
		var tDialog =  BootstrapDialog.show({
            title: '历史审批记录',
            message: content,
            buttons: [],
			onshown : function(){
				
			var $table = $('#billwf #billwftask');
			//初始化表
			$table.bootstrapTable({data:[]});
			
			//设置表格事件
			$table.on('click-row.bs.table', function (e, row, $element) {
	    		$('.success').removeClass('success');
	    		$($element).addClass('success');
			});
			
			$.post(homeModuleUrl+"/imfbpm/queryInstanceAllHistoryTaskRecordList",$data,
		        	function(data){
					$table.bootstrapTable('load',data);
					if(typeof callFunction === 'function'){
		  				callFunction(data);
		  			}
		       });
			
			//加载表格数据
			/*
			var datas = [{name:'活动一',startTime:'2015-10-12 10:11:12', endTime:'2015-10-12 10:11:45',comment:'同意'},
			{name:'活动二',startTime:'2015-10-12 10:11:12', endTime:'2015-10-12 10:11:45',comment:'同意'},
			{name:'活动三',startTime:'2015-10-12 10:11:12', endTime:'2015-10-12 10:11:45',comment:'同意'},
			{name:'活动四',startTime:'2015-10-12 10:11:12', endTime:'2015-10-12 10:11:45',comment:'同意'}];
			//加载表数据
			$table.bootstrapTable('load',datas);
			*/
			}
        });
		tDialog.getModalDialog().css({width:'80%'});
		
	};
	
	
	
	//联查
	BillWF.linkQuery = function($data, callFunction){
		var moduleValue = $data["moduleValue"];
		var billid = $data["billid"]; 
		var url = $data["url"];
		var title = $data["title"];
		var diagramUrl = homeModuleUrl+url+"?moduleValue="+moduleValue+"&billid="+billid+"&random="+Math.random();
		if(title == "放款审批" || title == "贷款合同"){
			//创建新页签
			var obj = {};
			obj.module_url = homeModuleUrl + url;
			obj.module_value = moduleValue;
			obj.title_name = title
			obj.param = "&billid="+billid
			window.parent.Hui_admin_tab(obj);
		}else{
			var node = new linkNode();
			node.setUrl(diagramUrl);
			node.setTitle(title);
			
			linkNodeManager.addNode(node);
			
			if(typeof window.parent === 'object' && window.parent != top.window){
				
				if(typeof window.parent.linkNodeManager === 'object'){
					
					window.parent.linkNodeManager.addNode(node);
					window.parent.linkNodeManager.controlDialogBtn();
					location.href = window.parent.linkNodeManager.currentNode.url;
					return;
				}
				
			}
			
			var content = $("<div id='billwf'></div>");
			
			content.append("<div class='panel panel-success' >"+
					"<div  class='panel-body'><iframe id='linkquery'  src='"+diagramUrl+"' width='100%' height='400px' style='border: 0px;' ></iframe></div></div>");
			
			var tDialog =  BootstrapDialog.show({
				title: title,
				message: content,
				buttons: [],
				onshown : function(){
					
					linkNodeManager.controlDialogBtn();
					
				},
				onhide: function(){
					linkNodeManager = new LinkNodeManager();
				}
			});
			linkNodeManager.setDialog(tDialog);
			tDialog.getModalDialog().css({width:'80%'});
			
			var back = $("<button class='btn btn-info btn-lg' id='dialogLeftBtn' style='position:absolute;left:-70px;top:250px;z-index:1061;-moz-border-radius: 50%;-webkit-border-radius: 50%; border-radius: 50%;'><span class='glyphicon glyphicon-chevron-left'></span></button>");
			var next = $("<button class='btn btn-info btn-lg' id='dialogRightBtn' style='position:absolute;right:-70px;top:250px;z-index:1061;-moz-border-radius: 50%;-webkit-border-radius: 50%; border-radius: 50%;'><span class='glyphicon glyphicon-chevron-right'></span></button>");
			
			tDialog.getModalDialog().append(back);
			tDialog.getModalDialog().append(next);
			
			back.click(function(){
				linkNodeManager.parentNode();
				linkNodeManager.controlDialogBtn();
				
			});
			
			next.click(function(){
				linkNodeManager.nextNode();
				linkNodeManager.controlDialogBtn();
				
			});
		}
		
		
		
	};
	
}