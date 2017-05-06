
//参照数据转换器，在U-GRID中使用，在列表界面对参照进行翻译
function imfbpRefTransform(oThis){
    debugger
    //值
	var value = oThis.value;
	//字段
	var field = oThis.gridCompColumn.options.field;
/*	//字段对应的元数据
	var meta = oThis.gridObj.dataTable.meta;*/
	//参照
	var ref = oThis.gridCompColumn.options.imfbpRefType;
	var refData = refManager.queryData(ref,value,false);
	//设置参照数据
	oThis.element.innerHTML = refData;
	$(oThis.element).attr('title', refData);
	
	
}

function clearValidateAreaTooltip(elementId){
    debugger
	$(elementId+" input").tooltip("destroy");
}

/**
	验证参照是否已经录入数据，根据元数据中的绑定进行验证
**/
function validateArea(elementId, isOpen, app, datatable){
    debugger
	if(isOpen == true){
		if($.type(app) === 'object'){
		
			var result = app.compsValidateMultiParam({element:document.querySelector(elementId), showMsg: true});
			if($.type(datatable) === 'object'){
				
				//根据元数据验证 参照是否填写
				var meta = datatable.meta;
				var returnValue = false;
				if($.isPlainObject(meta)){
					
					for(var field in meta){
						var refType = meta[field].imfbpRefType;
						var required = meta[field].required;
						var msg = meta[field].errorMsg;
						var isShow = false;
						if($.type(refType) === 'string' && required){
							//参照只进行是否录入判断
							//获取参照对应的图标
							
							var datas = datatable.getSelectedRows()[0].getSimpleData();
							
							if(datas != null){
							
								var value = datas[field];
								
								if(value == null || value =='' || $.type(value) == 'undefined'){
									isShow = true;
								}else{
									var refObj = $(elementId+" [name='"+field+"'][data-imfbp-ref-name='"+refType+"']");
								
									if(refObj.length > 0){
										$(refObj[0].parentNode.children[0]).tooltip("destroy");
									}
								}
								
							}else{
							
								return true;
							}
							
							if(isShow){
								var refObj = $(elementId +" [name='"+field+"'][data-imfbp-ref-name='"+refType+"']");
								
								if(refObj.length > 0){
									
									if($.type(msg) != 'string' && msg != ""){
										msg = "此项必须录入";
									}
									returnValue = true;
									$(refObj[0].parentNode.children[0]).tooltip({title:msg,placement:'top'}).tooltip("show");
								}
							}	
						}
					}
						
				}
				return !returnValue && result.passed;
			}
			
			if (!result.passed){
				return false;
			}
		}
	}
	return true;

}



/*
对数据集中的参照进行统一翻译  此处的翻译是根据元数据所提供的参照进行翻译的
dataTable 数据集， datas 需要翻译的参照  isRelateRef  
*/
function imfbpDataTableTranform(dataTable, sdatas, callFillData){
	debugger
	var columnArr = dataTable.gridCompColumnArr;
	var reqParas = {};
	var isReq = false;
	var reqParas = {};
	
	for (var index =0;index<columnArr.length;index++) {
		
		var field = columnArr[index].options.field;
		/*	//字段对应的元数据
			var meta = oThis.gridObj.dataTable.meta;*/
			var reqData = [];
			//参照
			var refType = columnArr[index].options.imfbpRefType;
			var refNameArray = reqParas[refType];
			if($.type(refNameArray) === 'array'){
				reqData = refNameArray;
				
			}
			
			if(typeof refType != 'undefined'){
				isReq = true;
				for(var data in sdatas){
					var value = sdatas[data][field];
					var tValue = refManager.queryData(refType,value,true);
					if(typeof value !='undefined' && value != '' && value != null && tValue == "" ){
						reqData.push(value);
					}
				}
				if(reqData.length>0){
					reqParas[refType] = reqData;
				}
			}
			
			
		
	}
	///var meta = dataTable.meta;

/*	if($.isPlainObject(meta)){
		for(var field in meta){
			var refType = meta[field].imfbpRefType;
			var reqData = [];
			var refNameArray = reqParas[refType];
			if($.type(refNameArray) === 'array'){
				reqData = refNameArray;
				
			}
			
			if(typeof refType != 'undefined'){
				isReq = true;
				for(var data in sdatas){
					var value = sdatas[data][field];
					var tValue = refManager.queryData(refType,value,true);
					if(typeof value !='undefined' && value != '' && value != null && tValue == "" ){
						reqData.push(value);
					}
				}
				if(reqData.length>0){
					reqParas[refType] = reqData;
				}
			}
		}*/
		//请求参照数据
	    if(isReq){
			//此处采用同步请求的方式
			//ref/query
			
			refManager.ajaxRefData(true,reqParas,function(){
				
				if($.type(callFillData) == 'function'){
					callFillData();
				}
			});
		}else{
			if($.type(callFillData) == 'function'){
					callFillData();
			}
		}
	
}


{	
    window.buttonManager = new ButtonManager();
	function ButtonManager(){
	//默认按钮  all 代表什么情况下都可用   default 代表默认情况下可用  1 自由状态 
		this.defaultButton = [
		{button:".add-btn", permission:['all','default']},  //添加
		{button:".edit-btn",permission:['0']},   //列表界面编辑
		{button:".remove-btn",permission:['0']},   //删除（列表卡片）
		{button:".submit-btn",permission:['0']},   //提交（列表卡片）
		{button:".callback-btn",permission:['1']},   //收回（列表卡片）
		{button:".audit-btn",permission:['1','2']},		
		{button:".unapprove-btn",permission:['2']},		//取消审批（列表卡片）
		{button:".approve-btn",permission:['1','2']},	//审批（列表卡片）
		{button:".linkapprove-btn",permission:['1','2','3','4']},  //联查审批（列表卡片）
		{button:".file-btn",permission:['0','1','2','3','4']},
		{button:".generate-btn",permission:['3']},
		{button:".update-btn",permission:['0']},		//修改  卡片界面：查看状态到修改状态
		{button:".sub-remove-btn",permission:['0']},	//卡片界面删除
		{button:".sub-submit-btn",permission:['0']},	//提交审批（卡片界面）
		{button:".sub-callback-btn",permission:['1']},
		{button:".sub-audit-btn",permission:['1','2']},
		{button:".sub-unapprove-btn",permission:['2','3']},
		{button:".sub-linkapprove-btn",permission:['1','2','3','4']},
		{button:".sub-file-btn",permission:['0','1','2','3','4']},
		{button:".simple-btn",permission:['0']}				//自由态可用按键局部控制预留
		];
		this.buttons = {};
		this.defaultPermission = "defaultPermission";
		this.all = "all";
		this.default = "default";
	}
	
	/*
	
		btn 的格式为 {button:'.class', permission:[]}     button 指的是按钮的样式   permission指的是按钮的权限，权限一般根据单据状态
	*/
	ButtonManager.prototype.addBtn = function(btn){
		this.defaultButton.push(btn);
		this.initButton(btn);
	}
	
	ButtonManager.prototype.initDefault = function(){
		for(var index=0; index<this.defaultButton.length; index++){
			this.initButton(this.defaultButton[index]);
		}
	}
	
	ButtonManager.prototype.initButton = function(button){
		var manager = this;
		$(button.button).each(
				function(){
					/*var obj = $(this);
					if (manager.buttons[button.button]){
						manager.buttons[button.button].push(obj)
					}else{
						var arr=new Array()
						arr.push(obj);
						manager.buttons[button.button]=arr;
					}*/
					//manager的buttons属性可以不用	zhangjing9
					manager.isPermission(manager.defaultPermission, button);
				}
		);
	}
	
	/**
	设置按钮状态
	*/
	ButtonManager.prototype.permission = function(status){
		for(var index=0; index<this.defaultButton.length; index++){
			this.isPermission(status, this.defaultButton[index]);
		}
	}
	
	ButtonManager.prototype.isPermission  = function(status, button){
		var permission = button.permission;
		if(this.defaultPermission === status){
					for(var index=0; index<permission.length; index++){
						var per = permission[index];
						if(per == this.all || per == this.default){
							this.setDisabled(button,false);
							break;
						}else{
							this.setDisabled(button,true);
						}	
					}
		}else{
				for(var index=0; index<permission.length; index++){
						var per = permission[index];
						if(per == this.all || per == status){
						   this.setDisabled(button,false);
							break;
						}else{
							this.setDisabled(button,true);
						}
					}	
				
		}
	}
	
	ButtonManager.prototype.setDisabled = function(btn, isDisabled){
		
		/*var btnarry = this.buttons[btn.button];
		//如果是数据，按数组处理
		if (Object.prototype.toString.call(btnarry) === '[object Array]'){
			for(var index in btnarry){
				var btn = btnarry[index];
				if($.type(btn) === 'object'){
					btn.attr("disabled",isDisabled); 
					if(isDisabled){
						btn.removeClass("btn-action");
					}else{
						btn.addClass("btn-action");
					}
				}
			}
		}else{
			//不是数组，直接赋值
			if($.type(btnarry) === 'object'){
				btn.attr("disabled",isDisabled); 
				if(isDisabled){
					btn.removeClass("btn-action");
				}else{
					btn.addClass("btn-action");
				}
			}
		}*/
		// 根据样式选择器,循环设置按钮disabled属性	zhangjing9
		$(btn.button).each(function(){
			$(this).attr("disabled", isDisabled);
		});
	}
	
	
	
	
	
    //参照数据管理器
	window.refManager = new RefManager();
	
	function RefManager(){
		//数据管理器
		this.datasManager = {};
		//参照展现类型
		this.defaultList={tree:'tree', list:'list'};
		//参照区域
		this.refArea = null;
		//默认情况下不起开参照关联参照策略
		this.isRelateRef = false;
		
		this.defaultField = {
			//参照名称 对应的一个参照  比如： 人员参照为  personRef  只能在参照上使用  那么这里就应该配置 data-imfbp-ref-name = 'personRef'
			refName:'data-imfbp-ref-name',
			//参照对应的字段
			field:'name',
			//在选择参照数据并单击了确定按钮后触发此函数
			refFun: 'data-imfbp-ref-fun', 
			//表示此处需要绑定参照
			ref: 'data-imfbp-ref',
			refUse : 'data-imfbp-ref-use', // true 可用 false 不可用
			
			//依赖绑定 此字段的值设定需要和绑定参照的对象属性NAME的值一样。
			refBind:'data-imfbp-ref-bind',
			//依赖绑定函数， 在设置依赖绑定数据后调用此函数
			refBindFunction:'data-imfbp-ref-bind-function',
			//依赖绑定字段，此字段确定应该从选中的参照中获取那个字段的数据
			refBindField:'data-imfbp-ref-bind-field'
			
			
			
		};
		
		//默认参照可以编辑
		this.isEdit = true;
		
		
	}
	//设置区域ID
	RefManager.prototype.setRefArea = function(id){
		debugger	
		this.refArea = id;
		//clearValidateAreaTooltip(id);
	};
	
	//是否启用影响关联参照
	RefManager.prototype.isRelateRef = function(relateRef){
		debugger
		this.isRelateRef = relateRef;
	};
	
	//设置参照是否可以编辑
	RefManager.prototype.setEdit = function(edit){
		debugger
		this.isEdit = edit;
	}
	
	
	RefManager.prototype.ajaxRefData = function(async, reqData, callFunction){
		debugger
		var self = this;
		var url = homeModuleUrl+"/refData/getRefTranslateDatas";
		//默认为异步访问
		if($.type(async) != 'boolean' ){
			async = true;
			
		}
			$.ajax(
				{
					type:'post',
					url:url,
					data:reqData,
					dataType:'json',
					async:async,
					success:function(reqdata){
						for(var index=0; index<reqdata.length; index++){
							var datas = reqdata[index].datas;
							var meta = reqdata[index].refMetaDataBean;
							self.dataManager(meta.refKey, datas, meta.idItem, meta.mainShowItem,meta.refType);
						}
						if(typeof callFunction === 'function' ){
							callFunction(reqdata);
						}
					}
				}
			);
	}
	
	
	//id为参照区域的ID，此方法会对area区域中所有的参照进行数据翻译 或采用标准的JQUERY查询对象的方式进行值输入
	RefManager.prototype.initAreaLoadData = function(area, getDataFunc){
		//clearValidateAreaTooltip(area);
		debugger
		var self = this;
		$(area + " ["+this.defaultField.ref+"='ref']").each(
			function(){
				var obj = $(this);
				var pk = null;
				if(typeof getDataFunc === 'function'){
					pk = getDataFunc(obj[0].attributes['name'].value);
				}
				//var value = self.queryData(obj[0].attributes[this.defaultField.refName].value,pk,true);
				if(pk == null || $.type(pk) === 'undefined'){
					pk = "";
				}
				self.fillRefInputData(area,obj[0],pk, this.isRelateRef);
			}
		);
	}
	
	//pk 业务主键
	RefManager.prototype.fillRefInputData = function(area, objElement, pk, isRelateRef){
		debugger
		var self = this;
		var name = objElement.attributes['name'].value;
		
		$(area + " ["+self.defaultField.refBind+"='"+name+"']").each(
		
			function(){
				var obj = $(this);
				var refBindField = obj[0].attributes[self.defaultField.refBindField].value;
				var isDefault = true;
				if($.type(refBindField) == 'undefined' || refBindField == '' || refBindField == null){
					isDefault = false;
				}
				var value = self.queryData(objElement.attributes[self.defaultField.refName].value,pk,isDefault,refBindField);
				var callFunction = obj[0].attributes[self.defaultField.refBindFunction];
				
				if($.type(callFunction) == 'object'){
					
					callFunction = callFunction.value;
					if($.type(callFunction) === 'function'){
					value = callFunction();
					}
				}
				
				var refName = obj[0].attributes[self.defaultField.refName];
				
				//设置参照关联
				if($.type(refName) === 'object' && value !=null && value != "" && isRelateRef && pk != ""){
					
					var req = {};
					req[refName.value] = [value];
					
					var tValue = self.queryData(refName,value,true);
					
					if(tValue != '' && tValue != null && $.type(tValue) != 'undefined'){
						self.fillRefInputData(area,obj[0],value,isRelateRef);
					}else{
						
						self.ajaxRefData(true, req, function(){
							self.fillRefInputData(area,obj[0],value,isRelateRef);
						});
					}
				}else if(pk == "" && $.type(refName) === 'object'){
					self.fillRefInputData(area,obj[0],"",isRelateRef);
				}else{
					obj.val(value);
				}
			}
		);
	};
	
	/**
		查询集合中的数据, refKey 参照定义的key, refpk 参照数据的主键  isDefault, 表示是否根据默认显示获取值， 
		field在isDefault 在true时起作用 获取某个字段的值
	**/
	RefManager.prototype.queryData = function(refKey, refPk, isDefault, field){
		var tObj = this.datasManager[refKey];
		if(typeof tObj === 'undefined' || tObj == null){
			return "";
		}
		
		if(!isDefault){
			field = tObj.name;
		}
		
		var datas = tObj.datas;
		if($.isPlainObject(datas)){
			var obj = datas[refPk+''];
			if($.isPlainObject(obj)){
				
				var value = "";
				
				if(tObj.type === refManager.defaultList.tree){
					value = obj.data[field]
				}else{
					value = obj[field];
				}
				if(typeof value === 'undefined' || value == null){
					return "";
				}
				return value;
			}
		}
		return "";
	};
	
	RefManager.prototype.queryDataSrc = function(refKey, refPk, isDefault, field){
		debugger
		var tObj = this.datasManager[refKey];
		if(typeof tObj === 'undefined' || tObj == null){
			return "";
		}
		
		if(!isDefault){
			field = tObj.name;
		}
		
		var datas = tObj.datas;
		if($.isPlainObject(datas)){
			var obj = datas[refPk+''];
			if($.isPlainObject(obj)){
				
				var value = "";
				
				if(tObj.type === refManager.defaultList.tree){
					return obj.data[field]
				}else{
					return obj;
				}
			}
		}
		return null;
	};
	
	/*
		ref 参照数据管理对象， datas 树形数据 ， id 主键字段  此方法把树形数据转换为列表数据
	*/
	RefManager.prototype.fillTreeData = function(ref,datas,id){
		debugger
		if(datas != null && datas.length>0){
			for(var data in datas){
				ref.datas[datas[data][id]] = datas[data];
				
				var nodes = datas[data]["nodes"];
				if(nodes !=null && typeof nodes != 'undefined' && nodes.length>0){
					 var value = this.fillTreeData(ref,nodes,id);
				}
			}
		}
	};
	
	
	
	/**
		参照数据管理的数据结构
		
		{
			参照定义的KEY：{
				//每条数据的主键字段
				id:id,
				//参照名称
				name: name,
				//参照类型 树形，列表   list,tree
				type: type,
				datas:{
					
					'123':{},
					'234':{}
				}
				
			}
			
		}
	
	refKey 参照名称 datas 参照数据   id 参照每条数据的主键字段，  展现的名称 name， type 列表或树
	*/
	RefManager.prototype.dataManager = function(refKey, datas, id, name,type){
		debugger
		var tObj = this.datasManager[refKey];
		if(typeof tObj === 'undefined'){
			 this.datasManager[refKey] = {id:id,name:name,type:type,datas:{}};
		}
		var ref = this.datasManager[refKey];
		var tdatas = ref.datas;
		//设置树形数据
		if(type === refManager.defaultList.tree){
			this.fillTreeData(ref,datas,id);
		}else{
			//设置列表数据
			for(var data in datas){
					tdatas[''+datas[data][id]] = datas[data];
			}
		}
		console.log(this.datasManager[refKey]);
		
	}
	//参照只能绑定到input上
	//input 必须设置data-imfbp-ref-name='参照名称' 才能绑定到具体的事件上
	//data-imfbp-ref-type  代表 列表或树形界面
	//data-imfbp-ref-fun   回调方法 在选中数据并返回后调用
	
	 $(document).ready(function() {
	 debugger
     $("["+refManager.defaultField.ref+"='ref']").bind('click',function(event) {
        //event.target.attributes['data-imfbp-ref'].value
			
			if($.type(refManager.isEdit) === 'boolean' && refManager.isEdit){
				
				var  refObj = $(this);
				var refuser = refObj.attr(refManager.defaultField.refUse);
				
				if(refuser === 'false'){
					event.stopPropagation();
					return;
				}
				var ref = new imfbpRef();
				ref.show(event);	
			}
			
		    event.stopPropagation();
         }).each(function(i){
		   
		   var  refObj = $(this);
		   var refuser = refObj.attr(refManager.defaultField.refUse);
		   if(refuser === 'false'){
			   this.style.cursor = "not-allowed";
		   }else{
			   
			    this.style.cursor = "pointer";
		   }

		  
		   
	   });
	   
	   
				buttonManager.initDefault();
       });
       
	 function imfbpRef(){
	 };
	 
	 imfbpRef.prototype.setValue =function (pk){
		debugger
		refManager.fillRefInputData(refManager.refArea,this.eleObj,pk, true);
		 
	 }
	 //展现参照
	 imfbpRef.prototype.show = function(event){
	 	debugger
		this.eleObj = event.target;
		this.callFun  = null;
		this.name = null;
		var showType =  null;
		
		try{
			
			this.name = this.eleObj.attributes[refManager.defaultField.refName].value;
			//回调方法
			this.callFun = this.eleObj.attributes[refManager.defaultField.refFun].value;
			
		}catch(e){
			console.log(e);
		}
			//参照查询条件
			var obj = $(this.eleObj);
			var query = obj.attr("data-imfbp-ref-query");
			
		    var resultData = null;
			var reqParas = {};
			var pageNumber = 10;
			var self = this;
			reqParas["typeKey"] = this.name;
			reqParas["page"] = 1;
			reqParas["rows"] = pageNumber;
			if(query != undefined && query != ""){
				reqParas["queryCondition"] = query + "='"+$(refManager.refArea).find("i[name='"+query+"']").attr('data-imfbp-ref-condition')+"'";
			}
			var url = homeModuleUrl+"/refData/getRefDatas";
			$.ajax(
				{
					type:'post',
					url:url,
					data:reqParas,
					dataType:'json',
					async:false,
					success:function(reqdata){
						if($.isPlainObject(reqdata)){
						    resultData = reqdata;
							var datas = reqdata.datas;
							var meta = reqdata.refMetaDataBean;
							refManager.dataManager(self.name, datas, meta.idItem, meta.mainShowItem,meta.refType);
						}
					}
				}
			);
		
		if(resultData.refMetaDataBean.refType === refManager.defaultList.list){
			this.showList(resultData,reqParas,pageNumber);
		}else{
			this.showTree(resultData);
		}
	 };
	 
	
	 imfbpRef.prototype.transformColumnFiled = function(showItemsMap){
		 debugger
		 var columns = [];
		 for(var field in showItemsMap){
			 var column = {};
			 column["align"] = "center";
			 column["valign"] = "center";
			 column["field"] = field;
			 column["title"] = showItemsMap[field]; 
			 columns.push(column);
		 }
		 
		 return columns;
	 }
	 
	 
	 imfbpRef.prototype.returnValue = function(dialog, data,datameta) {
	 		debugger
		 	var self = this;
			try{
				if(data != null && $.type(data) == 'object'){
					
					//设置数据中的值
					self.setValue(data[datameta.primary]);
					
						var callFun = eval(self.callFun);
						//调用回调方法
						callFun(self.eleObj, data, self.eleObj.attributes['name'].value, datameta.primary);
					
				}else{
					self.setValue("");
					if($.type(self.callFun) === 'string'){
						var callFun = eval(self.callFun);
						callFun(self.eleObj, data, self.eleObj.attributes['name'].value, null);
					}
				}
			}catch(e){
					console.log(e);
				}finally{
					dialog.close();
				}
			
        };
	 
	 imfbpRef.prototype.showList = function(resultData,reqParas,pageNumber){
	 	debugger
		var self = this;
		//基本信息
		var datameta = {
			//对话框TITLE
			title:resultData.refMetaDataBean.title,
			//表头
			columns: self.transformColumnFiled(resultData.refMetaDataBean.showItemsMap),
			//多少数据
			rows:resultData.refBasePage.totalRows,
			//每页展现数据条数
			//主键   
			primary: resultData.refMetaDataBean.idItem,
			name: resultData.refMetaDataBean.mainShowItem	
		};
		var pagination = resultData.refMetaDataBean.pagination;
	    var content = $("<div id='imfbpref'></div>");
		var queryInput = "<div class='row col-sm-12' ><div class='col-sm-9'><div class='col-sm-12'>"+
						"<input placeholder='请输入查询条件' class='form-control'></div></div>"+
						"<div class='col-sm-3'><button class='btn btn-primary' ><i class='icon-search'> </i>查询</button></div></div>";
		var queryArea = "<div class='panel panel-success'  ><div class='panel-body' style='height:60px'>"+queryInput+"</div></div>";
		
		//content.append(queryArea);
	    var table = "<table id='list' data-pagination='true' data-toggle='table' data-search='false' data-show-refresh='false' data-show-toggle='false'"+
		" data-show-columns='false' data-height='400'  ></table>";
		content.append("<div class='panel panel-success' style='margin-top:5px'>"+table+"</div>");
		BootstrapDialog.show({
	            title: datameta.title,
	            message: content,
	            buttons: [{
                label: '确定',
                action: function(dialog) {
                	var $table = $('#imfbpref #list');
        			var index = $table.find('tr.success').data('index');
        			var data = null;
        			if(index >= 0){
        				
        				data = $table.bootstrapTable('getData')[index];
        			}
                	self.returnValue(dialog,data,datameta);
                }
            }],
				onshown:function(dialog){
					
					var $table = $('#imfbpref #list');
					$table.bootstrapTable(
							{idField:datameta.primary,
							uniqueId:datameta.primary,
							columns:datameta.columns,
							pagination:pagination,
							sidePagination:'server',
							//当前展现页
							pageNumber:1,
							//当前页所展现的数据个数
							pageSize:pageNumber,
							pageList:[pageNumber],
							onPageChange:function(number, size){
								$table.bootstrapTable("showLoading");
								reqParas["page"] = number;
								$.ajax(
										{
											type:'post',
											url:homeModuleUrl+"/refData/getRefDatas",
											data:reqParas,
											dataType:'json',
											async:true,
											success:function(reqdata){
												$table.bootstrapTable("hideLoading");
												if($.isPlainObject(reqdata)){
													var datas = reqdata.datas;
													var meta = reqdata.refMetaDataBean;
													refManager.dataManager(self.name, datas, meta.idItem, meta.mainShowItem,meta.refType);
													$table.bootstrapTable("load", {total:resultData.refBasePage.totalRows, rows:datas});
												}
											}
										}
									);	
								}
							}
					);
				
					$table.bootstrapTable("load", {total:resultData.refBasePage.totalRows,
						
						rows:resultData.datas});
					//设置表格事件
					$table.on('click-row.bs.table', function (e, row, $element) {
						$('.success').removeClass('success');
						$($element).addClass('success');
					});
					
					$table.on('dbl-click-row.bs.table',function(row, $element){
						var index = $table.find('tr.success').data('index');
	        			var data = null;
	        			
	        			if(index >= 0 ){
	        				data = $table.bootstrapTable('getData')[index];
	        			}
						self.returnValue(dialog,data,datameta);
					});
				  }
				}
	        );
	 };
	 
	 imfbpRef.prototype.returnTreeValue = function(dialog,datameta,selectedNodes) {
	 	debugger
		 var self = this;
			try{
//				 var selectedNodes = $('#imfbpref #tree').treeview('getSelected');
				 if(selectedNodes != null && selectedNodes.length>0){
					 self.setValue(selectedNodes[0].data[datameta.primary]);
					 
							var callFun = eval(self.callFun);
							//调用回调方法
							callFun(self.eleObj, selectedNodes[0], self.eleObj.attributes['name'].value, datameta.primary);
						
				 }else{
					  self.setValue('');
					  callFun(self.eleObj, selectedNodes[0], self.eleObj.attributes['name'].value, null);
					  
				 }
			 }catch(e){
						console.log(e);
			}
			dialog.close();
     
	 }
	 
	 imfbpRef.prototype.showTree = function(resultData){
	 	debugger
		 var  self = this;
		//基本信息
		var datameta = {
			//对话框TITLE
			title:resultData.refMetaDataBean.title,
			//多少数据
			rows:5,
			//每页展现数据条数
			//主键   
			primary: resultData.refMetaDataBean.idItem,
			name: resultData.refMetaDataBean.mainShowItem
		};
		var datas = resultData.datas;
		refManager.dataManager(this.name, datas, datameta.primary, datameta.name,refManager.defaultList.tree);
	    var content = $("<div id='imfbpref' ></div>");
		var queryInput = "<div class='row col-sm-12' ><div class='col-sm-12'><div class='col-sm-12'>"+
						"<input id='querytree' placeholder='请输入查询条件' class='form-control'></div></div>"+
						"</div>";
		var queryArea = "<div class='panel panel-success'  ><div class='panel-body' style='height:60px'>"+queryInput+"</div></div>";
		
		content.append(queryArea);
		content.append("<div class='panel panel-success' style='margin-top:5px;' ><div id='tree'></div></div>");
		BootstrapDialog.show({
	            title: datameta.title,
	            message: content,
	            buttons: [{
                label: '确定',
                action: function(dialog) {
                	var selectedNodes = $('#imfbpref #tree').treeview('getSelected');
                	self.returnTreeValue(dialog,datameta,selectedNodes);
                }
            }],
				onshown:function(dialog){
					
					var  tree = $('#imfbpref #tree');
					tree.treeview({data:datas});
					
					$('#imfbpref #querytree').keyup(
						function(){
							
							var pattern = $(this).val();
							  var options = {
								ignoreCase: true,
								exactMatch: false,
								revealResults: false
							  };
							  
							 var results  =  tree.treeview('search', [ pattern, options ]);
							 
							 if(results.length == 0){
								 tree.treeview("collapseAll");
							 }else{
								tree.treeview('expandNode', [results]);
							 }
							
						}
					);
					//最后一次触发节点Id
				    var lastSelectedNodeId = null;
				    //最后一次触发时间
				    var lastSelectTime = null;
				    var selectedNodes=null;
					tree.on('nodeSelected', function(event, data) {
						 selectedNodes = $('#imfbpref #tree').treeview('getSelected');
					        lastSelectedNodeId = data.nodeId;
					        lastSelectTime = new Date().getTime();
					});
					tree.on('nodeUnselected', function(event, data) {
						 if (lastSelectedNodeId != null && lastSelectTime != null) {
					            var time = new Date().getTime();
					            var t = time - lastSelectTime;
					            if (lastSelectedNodeId == data.nodeId && t < 500) {
					                self.returnTreeValue(dialog,datameta,selectedNodes);
					            }
					        }
					        lastSelectedNodeId = data.nodeId;
					        lastSelectTime = new Date().getTime();
					});
					
				  }
				}
	        );
	 };
}