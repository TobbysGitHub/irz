
var num=0,oUl=$("#tabs2 .tab-ul"),hide_nav=$("#tabNav"),tabCounter = 2;

/*设置内容区height*/
function setHeight(){
	var hei = $(".app-content-full").height() - $("#tabs2 ul").height();
	$("#tab-content").find('div').each(function(){
		
		$(this).css('padding',0).height(hei+7);
	});
}
/*获取顶部选项卡总长度*/
function tabNavallwidth(){
	var taballwidth=0,
		$tabNav = hide_nav.find(".ui-tabs-nav"),  //ul
		$tabNavWp = hide_nav.find("#tabs2"),      //ul 父级容器
		$tabNavitem = hide_nav.find(".ui-tabs-nav li"),  // ul  li
		$tabNavmore =hide_nav.find(".Hui-tabNav-more"); // 更多 < >
	if (!$tabNav[0]){return}
	$tabNavitem.each(function(index, element) {
		
        taballwidth+=Number(parseFloat($(this).width()))
    });
	$tabNav.width(taballwidth+5);
	var w = $tabNavWp.width();
	if(taballwidth+5>w){
		$tabNavmore.show()}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0})
	}
}


function Hui_admin_tab(obj){
	if($(obj).attr('_href')){
		var bStop=false;
		var bStopIndex=0;
		var _href=$(obj).attr('_href');
		var _titleName=$(obj).find('span').html();
		var topWindow=$(window.parent.document);
		var show_navLi=topWindow.find(".ui-tabs-nav li");
		show_navLi.each(function() {
			if($(this).find('a').html() == _titleName){
				bStop=true;
				bStopIndex=show_navLi.index($(this));
				return false;
			}
		});
		if(!bStop){
			creatIframe(_href,_titleName);
		}
		else{
			
			$("#tabs2").tabs( "option", "active", bStopIndex );
		}
	}

}


function creatIframe(href,titleName){
	var topWindow=$(window.parent.document);
	var show_nav=topWindow.find('.ui-tabs-nav');
	var iframe_box=topWindow.find('#iframe_box');
	var tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>";
	var label = titleName,
			id = "tabs-" + tabCounter,
			li = $(tabTemplate.replace(/#\{href\}/g, "#" + id).replace(/#\{label\}/g, label));
			
	show_nav.append(li);
	var taballwidth=0,
		$tabNav = topWindow.find(".ui-tabs-nav"),
		$tabNavWp = topWindow.find("#tabs2"),
		$tabNavitem = topWindow.find(".ui-tabs-nav li"),
		$tabNavmore =topWindow.find(".Hui-tabNav-more");
	if (!$tabNav[0]){return}
	$tabNavitem.each(function(index, element) {
        taballwidth+=Number(parseFloat($(this).width()))
    });
	$tabNav.width(taballwidth+5);
	var w = $tabNavWp.width();
	if(taballwidth+5>w){
		$tabNavmore.show()}
	else{
		$tabNavmore.hide();
		$tabNav.css({left:0})
	}
        
	   
        $("#tab-content").append('<div id=' + id + '><iframe width="100%" height="100%" frameborder="0" src='+href+'></iframe></div>');
				
		$("#tabs2").tabs("refresh");
		//内容区设置height
		var hei = $(".app-content-full").height() - $("#tabs2 ul").height();
		$("#"+id).css('padding',0).height(hei+7);
		//打开新增tab页
		var ref = "#"+id;
		var idx = $("#tabs2").find('a[href="'+ref+'"]').parent().index();	
		$("#tabs2").tabs( "option", "active", idx );
		
		tabCounter++;
}

$(function(){
	/*当调整浏览器窗口的大小时事件*/
	$(window).resize(function(){
		
		tabNavallwidth();
		setHeight();
		// console.log("浏览器app-content-full:"+$(".app-content-full").height());
	   //  console.log("浏览器tabs2 ul:"+$("#tabs2 ul").height());
	});
	
	
	/*运行jquery-ui tab*/
	
	var tabs = $("#tabs2").tabs();
	
	/*选项卡导航*/

	$(".navi.clearfix").on("click",".nav ul li a",function(){
		Hui_admin_tab(this);
	});
		
	//tab删除
	tabs.on("click", 'span.ui-icon-close', function() {

			var panelId = $(this).closest("li").remove().attr("aria-controls");
			$("#" + panelId).remove();
			tabs.tabs("refresh");
			
			num==0?num=0:num--;
		    tabNavallwidth();
		});
	
	//初始化
	tabNavallwidth();
	
	setHeight();
	
	$('#js-tabNav-next').click(function(){
		num==oUl.find('li').length-1?num=oUl.find('li').length-1:num++;
		toNavPos();
	});
	$('#js-tabNav-prev').click(function(){
		num==0?num=0:num--
		toNavPos();
	});
	
	function toNavPos(){
		oUl.stop().animate({'left':-num*100},100);
	}
	

}); 
