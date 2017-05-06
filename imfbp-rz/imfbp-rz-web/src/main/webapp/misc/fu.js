/**
 * ajax 扩展
 */
var fu = {
	ajax : function(options) {
		var _beforeSend = options.beforeSend;
		var _complete = options.complete;
		var _success = options.success;
		var _error = options.error;


		/**
		 * 发送请求前运行的函数。
		 */
		options.beforeSend = function(xhr) {
			//alert("beforeSend");
			if (typeof (_beforeSend) == 'function') {
				_beforeSend.apply(this, arguments);
			}
		},
		options.success = function(xhr) {
			//alert("_success");
			if (typeof (_success) == 'function') {
				_success.apply(this, arguments);
			}
		}


		/**
		 * 请求完成时运行的函数（在请求成功或失败之后均调用，即在 success 和 error 函数之后）。
		 */
		options.error = function(xhr, status) {
			//alert("complete");
			if (typeof (_error) == 'function') {
				_error.apply(this, arguments);
			}
		}
		jQuery.ajax(options);
	},

	// 分页查找数据
	// options包括app,viewModel,url,currentpage,isSetPageintion,selector是#header-pagination，paras是参数
	queryDatas : function(options, selector, param) {
		var listData = options.app.dataTables['listData'];
		// 当前表格中展现的数据个数
		var rows = jQuery(selector).find(jQuery(".u-pagination .page_z")).val();
		
		if(rows==null || rows=='undefined'){
			// 展现默认展现第一页
			var page = options.currentPage;
		}
		if (param == null || param == 'undefined') {
			param = {
				rows : rows,
				page : page
			};
		} else {
			param["rows"] = rows;
			param["page"] = page;
		}

		jQuery.ajax({
			type : 'post',
			dataType : "json",
			data : param,
			url : options.url,
			success : function(data) {
				listData.pageSize(rows);
				// 记录分页信息
				options.app.getComps(selector)[0].comp.options.totalCount = data.total;
				if (data.rows.length == 0) {
					if (options.isSetPageintion) {
						listData.pageIndex(0);
					}
					if (viewModel.listData.getAllRows().length > 0) {
						listData.clear();
					}
					listData.totalRow(0);
					listData.totalPages(1);
				} else {
					if (options.isSetPageintion) {
						listData.pageIndex(1);
					}
					listData.setSimpleData(data.rows);
					listData.totalRow(data.total);
					// 计算展现多少页
					var totalpage = parseInt(data.total / rows)
							+ parseInt(data.total % rows > 0 ? 1 : 0);
					listData.totalPages(totalpage);

				}
			}
		});
	},

	// 保存
	saveData : function(options) {
		var datas = JSON.parse(JSON.stringify(options.viewModel.listEditData.getSelectedRows()[0].getSimpleData()));
		fu.ajax({
			type : 'post',
			dataType : "json",
			data : datas,
			url : options.url,
			success : function(data) {
				if(data.success){
					//在用户完成保存并且保存成功的时候触发，
					if(options.onSaveSuccess){
						options.onSaveSuccess(data); 
					}					 
					if(data.successMessage!=undefined){
						BootstrapDialog.alert({
				            title: '提示',
				            message: data.successMessage,
				            draggable: false, 
				            buttonLabel: '确定'
					    });
					}else{
						BootstrapDialog.alert({
				            title: '提示',
				            message: '保存记录成功！',
				            draggable: false, 
				            buttonLabel: '确定'
					    });
					}
					//刷新表格数据,刷新树都放在回调函数里执行
					options.afterSuccess(data);
				}else{
					//在用户完成保存并且保存失败的时候触发，
					if(options.onSaveError){
						options.onSaveError(data);
					}
					if(data.errorMessage!=undefined){
						BootstrapDialog.alert({
				            title: '提示',
				            message: data.errorMessage,
				            draggable: false, 
				            buttonLabel: '确定'
					    });
					}else{
						BootstrapDialog.alert({
				            title: '提示',
				            message: '保存记录失败！',
				            draggable: false, 
				            buttonLabel: '确定'
					    });
					}
					options.afterError(data);
				}				
				options.md.dBack();
			},
			error:function(data){
				options.afterError(data);
				options.md.dBack();
			}
		});

	},

	// 删除
	removeRow : function(options) {		
		//在用户删除之前触发
		if(options.onBeforeDelete){
			options.onBeforeDelete(data); 
		}
		//要删除的Id
		var batchId = "";
		var selectDatas = options.removeDatas; 
		if(selectDatas){

			if(selectDatas.length>0){
				var message = "请问您确定要删除这几条记录么?"	
				BootstrapDialog.confirm({
		            title: '确认',
		            message: message,		             
		            btnOKLabel: '确定',
		            btnCancelLabel: '取消',
		            callback: function(result) {
		                if(result) {
			            	jQuery.ajax({  
								type : 'post',
								dataType : "json",
								data : {
									batchId : selectDatas["0"].getSimpleData()["id"]
								},
								url : options.url,
				    			success:function(data){
				    				if(data.success){
										if(data.successMessage!=undefined){
											BootstrapDialog.alert({
									            title: '提示',
									            message: data.successMessage,
									            draggable: false, 
									            buttonLabel: '确定'
										    });
										}else{
											BootstrapDialog.alert({
									            title: '提示',
									            message: '删除记录成功！',
									            draggable: false, 
									            buttonLabel: '确定'
										    });
										}
										//刷新表格数据
										if(options.onDeleteSuccess){
				    						options.onDeleteSuccess(data); 
				    					}
									}else{
										if(options.onDeleteError){
				    						options.onDeleteError(data); 
				    					}
		    							if(data.errorMessage!=undefined){
		    								BootstrapDialog.alert({
									            title: '提示',
									            message: data.errorMessage,
									            draggable: false, 
									            buttonLabel: '确定'
										    });
		    							}else{
		    								BootstrapDialog.alert({
									            title: '提示',
									            message: '删除记录失败！',
									            draggable: false, 
									            buttonLabel: '确定'
										    });
		    							}
									}
				    			}, 
				    			error:function(data){
				    				if(options.onDeleteError){
			    						options.onDeleteError(data); 
			    					}
									if(data.errorMessage!=undefined){
										BootstrapDialog.alert({
								            title: '提示',
								            message: data.errorMessage,
								            draggable: false, 
								            buttonLabel: '确定'
									    });
									}else{
										BootstrapDialog.alert({
								            title: '提示',
								            message: '删除记录失败！',
								            draggable: false, 
								            buttonLabel: '确定'
									    });
									}
				    			}
				    		});					
		                }
		            }
		        });
			}else{
				BootstrapDialog.alert({
		            title: '提示',
		            message: '请选中您要删除的记录！',
		            draggable: false, 
		            buttonLabel: '确定'
			    });
			}
	
		}
	}
};


(function($) {

	//修改布局
	function _resize(jq, param) {
		var options = jQuery.data(target, 'fuTab').options;
	}
	function _add (target,param) {
		//var options = jQuery.data(target, 'fuTab').options;
		_addTabs(param);
	}
	function _close (jq,param) {
		var options = jQuery.data(target, 'fuTab').options;

	}
	function _getTabIndex(jq,param){
		var options = jQuery.data(target, 'fuTab').options;
	}
	
	function _getSelected(jq,param){
		var options =  jQuery.data(target, 'fuTab').options;
	}

	function _setSize(target, param) {
		var options =  jQuery.data(target, 'fuTab').options;
	}

	function _createTab(target,param) {
		var options =  jQuery.data(target, 'fuTab').options;
		_addTabs(options);
	}
	/**
	 * id:页签id，close：true
	 * 
	 * 
	 * */
	function _addTabs(options) {
		  var tabs=$(".tab-pane");
		  if(tabs!=null && tabs.length==10){
			  BootstrapDialog.alert("窗口打开个数不能超过10个！","提示");
			  return;
		  }
		  id = "tab_" + options.id;
		  $(".active").removeClass("active");
		  //如果TAB不存在，创建一个新的TAB
		  if (!$("#" + id)[0]) {
			  //固定TAB中IFRAME高度
			  mainHeight = $(document.body).height() - 90;
			  //创建新TAB的title
			  title = '<li role="presentation" id="tab_' + id + '"><a href="#' + id + '" aria-controls="' + id + '" role="tab" data-toggle="tab">' + options.title;
			  //是否允许关闭
//			  if (options.showClose) {
				  title += ' <i class="glyphicon glyphicon-remove" tabclose="' + id + '"></i>';
//			  }
			  title += '</a></li>';
			  //iframe高度设置
			  var height=$(document.body).height();
			  if(options.topSpace!=undefined && options.topSpace!=null){
				  height=height-options.topSpace;
			  }
			  if(options.bottomSpace!=undefined && options.bottomSpace!=null){
				  height=height-options.bottomSpace;
			  }
			  //是否指定TAB内容
			  if (options.content) {
				  content = '<div role="tabpanel" class="tab-pane" id="' + id + '">' + options.content + '</div>';
			  } else {//没有内容，使用IFRAME打开链接
				  content = '<div role="tabpanel" class="tab-pane" style="height:'+height+'px" id="' + id + '"><iframe src="' + options.url + '" width="100%" height="'+height+'px" frameborder="no" border="0" marginwidth="0" marginheight="0" scrolling="yes" allowtransparency="yes"></iframe></div>';
			  }
			  //加入TABS
			  $(".nav-tabs").append(title);
			  $(".tab-content").append(content);
		  }
		  //激活TAB
		  $("#" + id).addClass('active');
		  $("#tab_" + id).addClass("active");
		};
		
	//页签关闭
	var closeTab = function (id) {
	  //如果关闭的是当前激活的TAB，激活他的前一个TAB
	  if ($("li.active").attr('id') == "tab_" + id) {
	    $("#tab_" + id).prev().addClass('active');
	    $("#" + id).prev().addClass('active');
	  }
	  //关闭TAB
	  $("#tab_" + id).remove();
	  $("#" + id).remove();
	};
	//iframe 高度计算
	$(function () {
	  mainHeight = $(document.body).height() - 45;
	  $('.main-left,.main-right').height(mainHeight);
	  $("[addtabs]").click(function () {
	    addTabs({ id: $(this).attr("id"), title: $(this).attr('title'), close: true });
	  });
	 
	  $(".nav-tabs").on("click", "[tabclose]", function (e) {
	    id = $(this).attr("tabclose");
	    closeTab(id);
	  });
	});
	function setSelected(target, selected) {
		var options = jQuery.data(target, 'fuTab').options;
	}

	function _setSize(target, param){
		var options = jQuery.data(target, 'fuTab').options;
	}
	
	function _setSelectedSize(target,param){
		var options = jQuery.data(target, 'fuTab').options;
	}
	
	

	jQuery.fn.fuTab = function(options, param) {
		if (typeof options == 'string') {
			jQuery.data(this, 'fuTab', {
				param : jQuery.extend({}, jQuery.fn.fuTab.defaults, param)
			});
			return jQuery.fn.fuTab.methods[options](this, param);
		}

		options = options || {};
		return this.each(function() {
			var state = jQuery.data(this, 'fuTab');
			if (state) {
				jQuery.extend(state.options, options);
			} else {
				jQuery.data(this, 'fuTab', {
					options : jQuery.extend({}, jQuery.fn.fuTab.defaults, options)
				});
				jQuery(this).removeAttr('disabled');
				jQuery(this).bind('_resize', function(e, force) {
					alert('_resize');
				});
			}

			//_createTab(this);
		});
	};

	jQuery.fn.fuTab.methods = {
		options : function(jq) {
			return jQuery.data(jq[0], 'fuTab').options;
		},
		resize : function(jq, param) {
			return jq.each(function() {
				_setSize(this, param);
			});
		},
		add : function(jq,param) {
			return jq.each(function() {
				_add(this, param);
			});
		},
		close : function(jq,param) {
			return jq.each(function() {
				close(this, param);
			});
		},
		getTabIndex:function(jq,param){
			return jq.each(function(){
				_getTabIndex(this,param);
			})
		},
		getSelected:function(jq,param){
			return jq.each(function(){
				getSelected(this,param);
			})
		}
	};

	jQuery.fn.fuTab.defaults = {
		width: 'auto',
		height: 'auto',
		headerWidth: 150,	// the tab header width, it is valid only when tabPosition set to 'left' or 'right' 
		tabWidth: 'auto',	// the tab width
		tabHeight: 27,		// the tab height
		selected: 0,		// the initialized selected tab index
		showHeader: true,
		showClose:true,//是否显示关闭按钮，默认显示
		plain: false,
		fit: false,
		border: true,
		content:null,//内容，若为false，则展示ifarame,否则展示content内容
		url:null,
		showContent:true,//是否展示标签内容，
		tools: null,
		toolPosition: 'right',	// left,right
		tabPosition: 'top',		// possible values: top,bottom
		scrollIncrement: 100,
		scrollDuration: 400,
		onLoad: function(panel){},
		onSelect: function(title, index){},
		onUnselect: function(title, index){},
		onBeforeClose: function(title, index){},
		onClose: function(title, index){},
		onAdd: function(title, index){},
		onUpdate: function(title, index){},
		onContextMenu: function(e, title, index){}
	};

})(jQuery);


(function($){
	
	$.fn.dataGrid = function(options, param){
		if (typeof options == 'string'){
			return $.fn.dataGrid.methods[options](this, param);
		}
		
		options = options || {};
		return this.each(function(){
			var state = $.data(this, 'dataGrid');
			if (state){
				$.extend(state.options, options);
			} else {
				$.data(this, 'dataGrid', {
					options: $.extend({}, $.fn.dataGrid.defaults,  options)
				});
			}
			_loadData(this,param)
		});
	};
	
	
	function _loadData(target, param){
		
		var options = $.data(target, 'dataGrid').options;
		options = $.extend({},$.fn.dataGrid.defaults,options,param);

		var queryParam = options.queryParam;
		var listData = options.data;
		// 当前表格中展现的数据个数
		var rows = jQuery(options.pageSelector).find(jQuery(".u-pagination .page_z")).val();
		
		// 展现默认展现第一页
		var page = options.currentPage;
		if(param){
			page = param.currentPage;
		}
		if (queryParam) {
			queryParam["rows"] = rows;
			queryParam["page"] = page;
		} else {
			queryParam = {
				rows : rows,
				page : page
			};
		}

		//如果加载数据之前的方法不等于空
		if(options.onBeforeLoad){
			//如果加载数据之前回掉
			options.onBeforeLoad(target,param);
		}
		
		if(options.url){
			fu.ajax({
				type : 'post',
				dataType : "json",
				data : queryParam,
				url : options.url,
				success : function(data) {
					listData.pageSize(rows);
					// 记录分页信息
					options.app.getComps(options.pageSelector)[0].comp.options.totalCount = data.total;
					if (data.rows.length == 0) {
						if (options.isSetPageintion) {
							listData.pageIndex(0);
						}
						if (listData.getAllRows().length > 0) {
							listData.clear();
						}
						listData.totalRow(0);
						listData.totalPages(1);
					} else {
						if (options.isSetPageintion) {
							listData.pageIndex(1);
						}
						listData.setSimpleData(data.rows);
						listData.totalRow(data.total);
						// 计算展现多少页
						var totalpage = parseInt(data.total / rows) + parseInt(data.total % rows > 0 ? 1 : 0);
						listData.totalPages(totalpage);

					}
					
					//如果加载数据成功之后的方法不等于空
					if(options.onLoadSuccess){
						//如果加载数据成功回掉
						options.onLoadSuccess(data);
					}
					
				},
				error:function(data){
					options.onLoadError(data);
				}
			});	
		}
	}
	
	
	
	function _refresh(target, param){
		_loadData(target, param);
	}
	
	function _queryData(target, param){
		_loadData(target, param);
	}
	
	function _paging(target, param){
		_loadData(target, param);
	}
	
	$.fn.dataGrid.methods = {
		options: function(jq){
			return $.data(jq[0], 'dataGrid').options;
		},
		loadData: function(jq, param){
			return jq.each(function(){
				_loadData(this, param);
			});
		},
		refresh:function(jq, param){
			return jq.each(function(){
				_refresh(this, param);
			});
		},
		queryData:function(jq, param){
			return jq.each(function(){
				_queryData(this, param);
			});
		}
	};
	
	$.fn.dataGrid.defaults = {
		url: null,
		pageSelector:null,
		listDataName:null,
		afterSuccess:null,
		afterError:null,
		currentPage:1,
		isSetPageintion:true,
		queryParam:null,
		viewModel:null,
		onLoadSuccess:null,
		onLoadError:null,
		onBeforeLoad:null
	};
	
})(jQuery);


(function($) {

	$.fn.pagination = function(options, param) {
		if(typeof options == 'string') {
			return $.fn.pagination.methods[options](this, param);
		}
		options = options || {};
		return this.each(function() {
			var state = $.data(this, 'pagination');
			if(state) {
				$.extend(state.options, options);
			} else {
				$.data(this, 'pagination', {
					options: $.extend({}, $.fn.pagination.defaults, options)
				});
			}
			_init(this, param);
		});	
	};

	function _init(target, param) {
		setpage(target, param);//加载分页页面
		$(target).find('li a').bind('click',{"target_param":target},function(event){
			var currentPageNumber = 1;//当前页号
			var options_param = $.data(event.data.target_param,"pagination").options;
			var count_param = $(this).text().replace(/(^\s*)|(\s*$)/g, ""); //当前页面值
			var precount_param = options_param.preCurrentPageNumber; //上一次页号
			var pagesize_param = options_param.pageSize;  //分页号
			var countPage = options_param.countPage; //总页数
		
			if(countPage==0){
				currentPageNumber = 0;
			}
			
			var firstpstyle = "首页",lastpstyle = "末页",nextpstyle = "下一页",previouspstyle = "上一页";
			var firstPageStyle = options_param.firstPageStyle;
			var nextPageStyle = options_param.nextPageStyle;
		
			if(parseInt(nextPageStyle) == parseInt(1)){
				nextpstyle = "下一页";
				previouspstyle = "上一页";
			} else if(parseInt(nextPageStyle) == parseInt(2)){
				nextpstyle = ">>";
				previouspstyle = "<<";
			}
			
			if(parseInt(firstPageStyle) == parseInt(1)){
				firstpstyle = "首页";
				lastpstyle = "末页";
			} else if(parseInt(firstPageStyle) == parseInt(2)){
				firstpstyle = "<";
				lastpstyle = ">";
			}
			
			if(count_param == previouspstyle){		
				currentPageNumber = parseInt(precount_param) - parseInt(1);
				if(parseInt(currentPageNumber) < parseInt(1)){
					currentPageNumber = parseInt(1);
				}
			} else if(count_param == nextpstyle){
				currentPageNumber = parseInt(precount_param)+parseInt(1);
				if(parseInt(currentPageNumber) > parseInt(countPage)){
					currentPageNumber = parseInt(countPage);
				}
			} else if(count_param == firstpstyle){
				currentPageNumber = parseInt(1);
			} else if(count_param == lastpstyle){
				currentPageNumber = parseInt(countPage);
			} else {
				currentPageNumber=parseInt(count_param);
			}
			
			options_param.pageNumber = currentPageNumber; //重置当前页号
			_init(target, param);
				
			options_param.preCurrentPageNumber = currentPageNumber; //重置上一页号
			
			$(target).children("li").each(function() { //添加样式
				if ($(this).children("a").text() == currentPageNumber) {
					$(target).children("li").removeClass('active');
					$(this).addClass("active");
				}
			});
			
			options_param.onSelectPage(currentPageNumber,pagesize_param); //调用选中页面
		});
	}
	
	function setpage(target, param){
		var options = $.data(target, 'pagination').options;
		var totalpage= options.total;//总页数
		var pageSize = options.pageSize;//每一页显示页数
		//总页数
	    var countPage = (totalpage % pageSize == 0 ? totalpage / pageSize : Math.ceil(totalpage / pageSize));
	    options.countPage=countPage; //设置当前总页数
	   
		var totalRecordNum = options.countPage;//总页数
		
		var outstr,cpage=options.pageNumber;
		options.preCountPage = totalRecordNum;
		options.preTotal = totalpage;
		
		var stylemode = options.styleMode;//样式模式
		
		var firstpstyle = "首页",lastpstyle = "末页",nextpstyle = "下一页",previouspstyle = "上一页";
		var nextPageStyle = options.nextPageStyle;
		var firstPageStyle = options.firstPageStyle;
	
		if(parseInt(nextPageStyle) == parseInt(1)){
		    nextpstyle = "下一页";
		    previouspstyle = "上一页";
		} else if(parseInt(nextPageStyle) == parseInt(2)){
		    nextpstyle = ">>";
		    previouspstyle = "<<";
		}
		
		if(parseInt(firstPageStyle) == parseInt(1)){
		    firstpstyle = "首页";
		    lastpstyle = "末页";
		} else if(parseInt(firstPageStyle) == parseInt(2)){
		    firstpstyle = "<";
		    lastpstyle = ">";
		}
		
		outstr = ""; 
		if($(target).find("li").length > 0){
			$(target).children("li").remove();
		}
		outstr += paginationHead(cpage,firstpstyle,previouspstyle); //加载首页
		//处理分页body
		if(parseInt(stylemode) == parseInt(1)){
			outstr += paginationBodyOne(totalRecordNum,pageSize,cpage);
		} else {
			outstr += paginationBodyTwo(totalRecordNum,pageSize,cpage);
		}
		outstr += paginationtail(cpage,totalRecordNum,nextpstyle,lastpstyle);//加载末页
		$(target).append(outstr);
		outstr = "";
		
		var currentPageNumber = options.pageNumber;
		$(target).children("li").each(function() { //添加样式
			if ($(this).children("a").text() == currentPageNumber) {
				$(target).children("li").removeClass('active');
				$(this).addClass("active");
			}
		});
	}
	
	function paginationHead(cpage,firstpstyle,previouspstyle){
		var outstr= "" ;
		if(parseInt(cpage) == parseInt(1)){
			outstr = outstr + "<li><a href='javascript:void(0)' style='background-color:#EEE; color:#000;' disabled>"+firstpstyle+"</a></li>"; 
			outstr = outstr + "<li><a href='javascript:void(0)' style='background-color:#EEE; color:#000;' disabled>"+previouspstyle+"</a></li>";
		} else {
			outstr = outstr + "<li><a href='javascript:void(0)'>"+firstpstyle+"</a></li>"; 
			outstr = outstr + "<li><a href='javascript:void(0)'>"+previouspstyle+"</a></li>";
		}
		return outstr;
	}
	
	function paginationBodyOne(totalRecordNum,pageSize,cpage){
		var outstr = "";
		var count = 1;
		if(totalRecordNum<=pageSize){  //总页数小于分页数
			for (count=1;count<=totalRecordNum;count++) 
			{   
				if(count==1){
					outstr = outstr + "<li class='active'><a href='javascript:void(0)'>"+count+"</a></li>"; 
				} else {
					outstr = outstr + "<li><a href='javascript:void(0)'>"+count+"</a></li>"; 
				}
			}
		}
	
		if(totalRecordNum>pageSize){        //总页数大于分页数 
			if(parseInt((cpage-1)/pageSize) == 0)
			{             
				for (count=1;count<=pageSize;count++) 
				{   
					if(count==1){
						outstr = outstr + "<li class='active'><a href='javascript:void(0)'>"+count+"</a></li>";
					} else {
						outstr = outstr + "<li><a href='javascript:void(0)'>"+count+"</a></li>"; 
					}
				} 
			} 
			else if(parseInt((cpage-1)/pageSize) == parseInt(totalRecordNum/pageSize)) 
			{     
				for (count=parseInt(totalRecordNum/pageSize)*pageSize+1;count<=totalRecordNum;count++) 
				{    
					outstr = outstr + "<li><a href='javascript:void(0)'>"+count+"</a></li>"; 
				} 
			} 
			else 
			{    
				for (count=parseInt((cpage-1)/pageSize)*pageSize+1;count<=parseInt((cpage-1)/pageSize)*pageSize+pageSize;count++) 
				{
					outstr = outstr + "<li><a href='javascript:void(0)'>"+count+"</a></li>"; 
				}
			}
		}
		return 	outstr;	
	}
	
	function paginationBodyTwo(totalRecordNum,pageSize,cpage){
		var outstr = "";
		var count = 1;
		var dot = '<li><span>...</span></li>';
		if(totalRecordNum <= 7){
			for(var count=1;count<=totalRecordNum;count++){
				if(count==1){
					outstr = outstr + "<li class='active'><a href='javascript:void(0)'>"+count+"</a></li>"; 
				} else {
					outstr = outstr + "<li><a href='javascript:void(0)'>"+count+"</a></li>"; 
				}
			}
		}else{
			if(cpage <= 5){
				for(var count=1;count<=6;count++){
					if(count==1){
						outstr = outstr + "<li class='active'><a href='javascript:void(0)'>"+count+"</a></li>"; 
					} else {
						outstr = outstr + "<li><a href='javascript:void(0)'>"+count+"</a></li>"; 
					}
				}
				outstr += dot;
				outstr = outstr + "<li><a href='javascript:void(0)'>"+totalRecordNum+"</a></li>"; 
			}else{
			
				for(var count=1;count<=1;count++){
					if(count==1){
						outstr = outstr + "<li class='active'><a href='javascript:void(0)'>"+count+"</a></li>"; 
					} else {
						outstr = outstr + "<li><a href='javascript:void(0)'>"+count+"</a></li>"; 
					}
				}
				outstr += dot;
				
				var begin = cpage - 2;
				var end = cpage + 3;
				if(end > totalRecordNum){
					end = totalRecordNum;
					begin = end - 5;
					if(cpage - begin < 1){
						begin = begin-1;
					}
				}
				for(var count=begin;count<end;count++){
					outstr = outstr + "<li><a href='javascript:void(0)'>"+count+"</a></li>"; 
				}
				if(end != totalRecordNum){
					outstr += dot;
				}
				
				outstr = outstr + "<li><a href='javascript:void(0)'>"+totalRecordNum+"</a></li>"; 
			}
		}
		return outstr;
	}
	
	function paginationtail(cpage,totalRecordNum,nextpstyle,lastpstyle){
		var outstr = "";
		if(parseInt(cpage) == parseInt(totalRecordNum)){
			outstr = outstr + "<li><a href='javascript:void(0)' style='background-color:#EEE; color:#000;' disabled> "+nextpstyle+" </a></li>"; 
			outstr = outstr + "<li><a href='javascript:void(0)' style='background-color:#EEE; color:#000;' disabled> "+lastpstyle+" </a></li>";
		} else {
			outstr = outstr + "<li><a href='javascript:void(0)'> "+nextpstyle+" </a></li>"; 
			outstr = outstr + "<li><a href='javascript:void(0)'> "+lastpstyle+" </a></li>";
		}
		return outstr;
	}
	
	/**
	 * 刷新 回到当前页面
	 * @param {Object} total
	 * @param {Object} pageSize{total:100,pageSize:10}
	 */
	function _refresh(target,param) {
		var options = $.data(target, 'pagination').options;
		options.total = param.total;
		//options.pageNumber = param.pageNumber;
		options.pageSize = param.pageSize;
		_init(target, param);
	}
	
	/**
	 * 修改页数
	 * @param {Object} target
	 * @param {Object} param
	 */
	function _changePageSize(target, param) {
	}
	
	$.fn.pagination.methods = {
		options: function(jq) {
			return $.data(jq[0], 'pagination').options;
		},
		init: function(jq, param) {
			return jq.each(function() {
				_init(this, param);
			});
		},
		selectPage: function(jq, param) {
			return jq.each(function() {
				_selectPage(this, param);
			});
		},
		refresh: function(jq, param) {
			return jq.each(function() {
				_refresh(this, param);
			});
		},
		changePageSize:function(jq, param){
			return jq.each(function() {
				_changePageSize(this, param);
			});
		}
	};

	$.fn.pagination.defaults = {
		total: 1,//总条数
		preTotal:1,//上次总条数
		pageSize: 10,//每页显示多少条
		pageNumber: 1,//当前页
		preCurrentPageNumber:1,//上次当前页
		countPage:1,//总页数
		preCountPage:1,//上一次总页数
		styleMode:1,//样式模式
		firstPageStyle:1,//首末页图标样式
		nextPageStyle:1, //上下页图标样式
		pageList: [10, 20, 30, 50],//list列表
		showPageList: true,//定义是否显示页面导航列表
		showRefresh: true,//定义是否显示刷新按钮。
		onSelectPage: function(pageNumber, pageSize) {},//用户选择一个新页面的时候触发
		onBeforeRefresh: function(pageNumber, pageSize) {},//在点击刷新按钮刷新之前触发，返回false可以取消刷新动作。
		onRefresh: function(pageNumber, pageSize) {},//刷新之后触发。
		onChangePageSize: function(pageSize) {},//在页面更改页面大小的时候触发。
	};

})(jQuery);



