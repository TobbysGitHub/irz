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
						alert(data.successMessage);
					}else{
						alert('保存记录成功！');
					}
					//刷新表格数据,刷新树都放在回调函数里执行
					options.afterSuccess(data);
				}else{
					//在用户完成保存并且保存失败的时候触发，
					if(options.onSaveError){
						options.onSaveError(data);
					}
					if(data.errorMessage!=undefined){
						alert(data.errorMessage);
					}else{
						alert('保存记录失败！');
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
				var r=confirm(message);
				if(r){
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
									alert(data.successMessage);
								}else{
									alert('删除记录成功！');
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
    								alert(data.errorMessage);
    							}else{
    								alert('删除记录失败！');
    							}
							}
		    			}, 
		    			error:function(data){
		    				if(options.onDeleteError){
	    						options.onDeleteError(data); 
	    					}
							if(data.errorMessage!=undefined){
								alert(data.errorMessage);
							}else{
								alert('删除记录失败！');
							}
		    			}
		    		});				
				}
			}else{
				alert('请选中您要删除的记录！');
			}
		}else{
			alert('请选中您要删除的记录！');
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

