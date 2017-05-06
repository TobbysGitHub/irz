
//提交方法
function submit(datas,moduleValue,gridObj,rowIndex,refArea){
	BillWF.submit(JSON.stringify(datas),moduleValue,function(result){
		if(result.success){
			commonFunction(result,gridObj,rowIndex);
			//成功提示
			$("#edit_msg_out").css("display","block").html("提交成功");
			setTimeout(function(){
				$("#edit_msg_out").hide();
			},2000);
		}else{
			layer.alert(result.errorMessage);
		}
    });
}
function saveSubmit(datas,moduleValue,gridObj,rowIndex,refArea){
	BillWF.submit(JSON.stringify(datas),moduleValue,function(result){
		if(result.success){
			gridObj.gridPlugin("load",ko.mapping.toJS(viewModel.queryData));
			buttonManager.permission(datas.approvestatus);
			//成功提示
			$("#edit_msg_out").css("display","block").html("保存提交成功");
			setTimeout(function(){
				$("#edit_msg_out").hide();
			},2000);
		}else{
			layer.alert(result.errorMessage);
		}
	});
}
//收回
function callback(datas,moduleValue,gridObj,rowIndex,refArea){
	BillWF.callback(JSON.stringify(datas),moduleValue,function(result){
		if(result.success){
			commonFunction(result,gridObj,rowIndex);
			//成功提示
			$("#edit_msg_out").css("display","block").html("收回成功");
			setTimeout(function(){
				$("#edit_msg_out").hide();
			},2000);
		}else{
			layer.alert(result.errorMessage);
		}
	});
}
//审批
function approve(datas,moduleValue,gridObj,rowIndex,refArea){
	BillWF.approve(JSON.stringify(datas), moduleValue,function(result){
		if(result.success){
			var resultdata = commonFunction(result,gridObj,rowIndex);
			if(refArea != null && refArea != ""){
				//审批人
				var approveid = resultdata.approveid;
				//审批人参照翻译
				var approveI = $("."+refArea).find("i[name='approveid']")[0];
				refManager.fillRefInputData("."+refArea, approveI, approveid, true);
			}
			

			
			//成功提示
			$("#edit_msg_out").css("display","block").html("审批成功");
			setTimeout(function(){
				$("#edit_msg_out").hide();
			},2000);
		}else{
			layer.alert(result.errorMessage);
		}
	});
}
//取消审批
function unApprove(datas,moduleValue,gridObj,rowIndex,refArea){
	BillWF.unApprove(JSON.stringify(datas),moduleValue,function(result){
		if(result.success){
			commonFunction(result,gridObj,rowIndex);
			if(refArea != null && refArea != ""){
				viewModel.editData.approveid("");
				//审批人清空
				var approveI = $("."+refArea).find("i[name='approveid']")[0];
				refManager.fillRefInputData("."+refArea, approveI, "", true);
			}
			//成功提示
			$("#edit_msg_out").css("display","block").html("取消审批成功");
			setTimeout(function(){
				$("#edit_msg_out").hide();
			},2000);
		}else{
			layer.alert(result.errorMessage);
		}
	});
}
function linkApprove(flowinstanceid){
	query_string1 = {
     		'processDefinitionId':"fulqprocess:3:e076538b-1c24-11e6-933a-184f3202904",
     		'processInstanceId': flowinstanceid,
	}
	BillWF.linkApprove(query_string1,function(result){
		if(result.success){
			//成功提示
			$("#edit_msg_out").css("display","block").html("联查成功");
			setTimeout(function(){
				$("#edit_msg_out").hide();
			},2000);
		}
	});
}

function commonFunction(result,gridObj,rowIndex){
	var resultdata = result.map.datas;
	if(resultdata){
		//卡片页面数据更新
		ko.mapping.fromJS(resultdata, viewModel.editData);
		//设置翻译
		data = [resultdata];
		imfbpDataTableTranform(gridObj,data,function(){
			for(var filed in data[0]){
				gridObj.updateValueAt(rowIndex,filed,data[0][filed]);
			}
		});
	}
	//在列表界面：情况edit值
	if(viewModel.pageState()=="list"){
		ko.mapping.fromJS(emptyData,viewModel.editData);
		
	}
	//更新按钮状态
	buttonManager.permission(resultdata.approvestatus);
	return resultdata;
}
	

