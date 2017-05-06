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


