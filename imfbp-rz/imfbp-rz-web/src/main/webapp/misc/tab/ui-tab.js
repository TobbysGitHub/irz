var num = 0,
	oUl = jQuery("#tabs2 .tab-ul"),
	hide_nav = jQuery("#tabNav"),
	tabCounter = 2;

/*设置内容区height*/
function setHeight() {

	var height = $(document.body).height() - 50;
	jQuery("#tabs2").height(height);
	jQuery("#tab-content").css("margin-top", 9).height(height-50);
	jQuery("#tab-content .auto-content-height").each(function() {
		jQuery(this).css('padding', 0).height(height - 54);
	});
}
/*获取顶部选项卡总长度*/
function tabNavallwidth() {
	var taballwidth = 0,
		$tabNav = hide_nav.find(".ui-tabs-nav"), //ul
		$tabNavWp = hide_nav.find("#tabs2"), //ul 父级容器
		$tabNavitem = hide_nav.find(".ui-tabs-nav li"), // ul  li
		$tabNavmore = hide_nav.find(".Hui-tabNav-more"); // 更多 < >
	if(!$tabNav[0]) {
		return
	}
	$tabNavitem.each(function(index, element) {

		taballwidth += Number(parseFloat($(this).width()))
	});
	$tabNav.width(taballwidth + 5);
	var w = $tabNavWp.width();
	if(taballwidth + 5 > w) {
		$tabNavmore.show()
	} else {
		$tabNavmore.hide();
		$tabNav.css({
			left: 0
		})
	}
}

function Hui_admin_tab(obj) {
	if(jQuery(obj).attr('module_url') || obj.module_url) {
		var bStop = false;
		var bStopIndex = 0;
		var _href = $(obj).attr('module_url') || obj.module_url;
		var _value = $(obj).attr('module_value') || obj.module_value;
		var _title = $(obj).find('span').html() || obj.title_name;
		var _param = obj.param;
		var _titleName = jQuery.trim(_title);
		var topWindow = $(window.parent.document);
		var show_navLi = topWindow.find(".ui-tabs-nav li");
		show_navLi.each(function() {
			if(jQuery(this).find('a').html() == _titleName) {
				bStop = true;
				bStopIndex = show_navLi.index($(this));
				return false;
			}
		});
		if(!bStop) {
			creatIframe(_href, _titleName, _value, _param);
		} else {
			jQuery("#tabs2").tabs("option", "active", bStopIndex);
			//			var l = 'iframeCenterName_'+obj.module_value;
			//			alert(l.href);
		}
	}

}

function creatIframe(href, titleName, moduleValue, param) {
	var topWindow = jQuery(window.parent.document);
	var show_nav = topWindow.find('.ui-tabs-nav');
	var iframe_box = topWindow.find('#iframe_box');
	var tabTemplate = "<li><a href='#{href}'>#{label}</a> <span class='ui-icon ui-icon-close'>Remove Tab</span></li>";
	var label = titleName,
		id = "tabs-" + tabCounter,
		li = $(tabTemplate.replace(/#\{href\}/g, "#" + id).replace(/#\{label\}/g, label));
	show_nav.append(li);
	var taballwidth = 58, //首页的宽度
		$tabNav = topWindow.find(".ui-tabs-nav"),
		$tabNavWp = topWindow.find("#tabs2"),
		$tabNavitem = topWindow.find(".ui-tabs-nav li"),
		$tabNavmore = topWindow.find(".Hui-tabNav-more");
	if(!$tabNav[0]) {
		return
	}
	$tabNavitem.each(function(index, element) {
		taballwidth += Number(parseFloat($(this).width()))
	});
	$tabNav.width(taballwidth + 5);
	var w = $tabNavWp.width();
	if(taballwidth + 5 > w) {
		$tabNavmore.show()
	} else {
		$tabNavmore.hide();
		$tabNav.css({
			left: 0
		})
	}

	var url = href + "?moduleValue=" + moduleValue + "&rt=" + randomStr(16);

	if(param != undefined && param != "") {
		url = url + param;
	}
	//  $("#tab-content").css('padding-bottom',15);
	jQuery("#tab-content").append('<div id=' + id + ' class="auto-content-height" style=""><iframe width="100%" height="100%" scrolling="yes" frameborder="0" src=' + url + ' name="iframeCenterName_' + moduleValue + '" ></iframe></div>');

	jQuery("#tabs2").tabs("refresh");

	setHeight();
	//打开新增tab页
	var ref = "#" + id;
	var idx = jQuery("#tabs2").find('a[href="' + ref + '"]').parent().index();
	jQuery("#tabs2").tabs("option", "active", idx);
	tabNavallwidth();
	++tabCounter;

	createCentexMenu();

}

jQuery(function() {
	/*当调整浏览器窗口的大小时事件*/
	jQuery(window).resize(function() {
		tabNavallwidth();
		setHeight();
		// console.log("浏览器app-content-full:"+$(".app-content-full").height());
		//  console.log("浏览器tabs2 ul:"+$("#tabs2 ul").height());
	});

	/*当调整浏览器窗口的大小时事件*/
//	jQuery(window).resize(function() {
//		tabNavallwidth();
//		setHeight();
//		// console.log("浏览器app-content-full:"+$(".app-content-full").height());
//		//  console.log("浏览器tabs2 ul:"+$("#tabs2 ul").height());
//	});

	/*运行jquery-ui tab*/
	var tabs = jQuery("#tabs2").tabs();

	/*选项卡导航*/
	jQuery(".navi.clearfix").on("click", ".nav ul li a", function() {
		Hui_admin_tab(this);
	});

	//tab删除
	tabs.on("click", 'span.ui-icon-close', function() {
		closeTab(tabs, this);
	});

	//初始化
	tabNavallwidth();

	setHeight();

	jQuery('#js-tabNav-next').click(function() {
		num == oUl.find('li').length - 1 ? num = oUl.find('li').length - 1 : num++;
		toNavPos();
	});
	jQuery('#js-tabNav-prev').click(function() {
		num == 0 ? num = 0 : num--
			toNavPos();
	});

	function toNavPos() {
		oUl.stop().animate({
			'left': -num * 100
		}, 100);
	}

});

function createCentexMenu() {
	/*运行jquery-ui tab*/
	$('#tabs2 li').contextmenu({
		target: '#context-menu',
		onItem: function(context, e) {

			console.log($(e.target).attr("contextValue"));
			var itemName = $(e.target).attr("contextValue");

			//如果是关闭当前页签
			if(itemName == "closeThis") {
				closeTab(jQuery("#tabs2").tabs(), jQuery(context).find("span.ui-icon-close"));
			}
			//如果关闭其他页签
			else if(itemName == "closeOther") {
				closeOtherTab(jQuery("#tabs2").tabs(), jQuery(context).find("span.ui-icon-close"));
			}
			//如果关闭右侧所有页签
			else if(itemName == "closeRight") {
				closeRightTab(jQuery("#tabs2").tabs(), jQuery(context).find("span.ui-icon-close"));
			}
			//var pid = jQuery(context).find("span.ui-icon-close").closest('li').remove().attr("aria-controls");

		}
	});
}

function closeRightTab(tabs, liObj) {

	var panelId = jQuery(liObj).closest('li').attr("aria-controls");
	var flat = false;
	jQuery(tabs.find("span.ui-icon-close")).each(function() {

		if(jQuery(this).closest('li').attr("aria-controls") == panelId) {
			flat = true;
		}

		if(flat && jQuery(this).closest('li').attr("aria-controls") != panelId) {

			var liId = jQuery(this).closest('li').remove().attr("aria-controls");
			jQuery("#" + liId).remove();

			tabs.tabs("refresh");
			num == 0 ? num = 0 : num--;
			tabNavallwidth();

		}

	});
}

function closeOtherTab(tabs, liObj) {

	var panelId = jQuery(liObj).closest('li').attr("aria-controls");

	jQuery(tabs.find("span.ui-icon-close")).each(function() {

		if(jQuery(this).closest('li').attr("aria-controls") != panelId) {

			var liId = jQuery(this).closest('li').remove().attr("aria-controls");
			jQuery("#" + liId).remove();

			tabs.tabs("refresh");
			num == 0 ? num = 0 : num--;
			tabNavallwidth();
		}

	});
}

function closeTab(tabs, liObj) {
	var panelId = jQuery(liObj).closest('li').remove().attr("aria-controls");
	jQuery("#" + panelId).remove();
	tabs.tabs("refresh");
	num == 0 ? num = 0 : num--;
	tabNavallwidth();
}

function randomStr() {
	len = 32;
	var $chars = 'ABCDEFGHJKMNPQRSTWXYZabcdefhijkmnprstwxyz2345678'; /** **默认去掉了容易混淆的字符oOLl,9gq,Vv,Uu,I1*** */
	var maxPos = $chars.length;
	var pwd = '';
	for(i = 0; i < len; i++) {
		pwd += $chars.charAt(Math.floor(Math.random() * maxPos));
	}
	return pwd;
}