//初始化皮肤
window.onload=function(){
	getSkinColor();
}	

	function getSkinColor(){

	//参数1地址
		var getSkinColorUrl=homeModuleUrl+'/bossUser/getBossUserByPage';
	//参数2租户
		var users='${users}';
		if(typeof(users)!=="string"){
			var userobj = eval('("+users+")');
			console.log(userobj);
			var tenantId =null;//租户id
			var userCode =null;//登录id
			  if(userobj.length!=0&&userobj[0]!=undefined){
				  tenantId=userobj[0].tenantId;
				  userCode=userobj[0].userCode;
				 var userData={
				       "tenantId":tenantId,
				        "userCode":userCode
			        };
			}
		}else{
			 var userData=null
		};
	//发送get
		get(getSkinColorUrl,initSkin);
		function initSkin(data){//成功回调函数--修改皮肤
				var oApp = document.getElementsByClassName("app")[0];
				var oAside = document.getElementById("aside");
				var oSkinSet = document.getElementById("skinSet");
				var oSkinLi = document.getElementsByClassName("skinLi");
				var oSkinLink = document.getElementById("skinLink");
				//初始化link的href
				data=data?data:"fulldefault";
				var skinlink = '/misc/themeColor/' + data + '.css';
				var strstr = homeModuleUrl + skinlink;
				oSkinLink.href = strstr;
				//初始化app、aside类名
				if(data.charAt(0) == "l") {
					addClass(oApp, "appLight");
					oAside.className="app-aside hidden-xs bg-light";
				}
				//初始化skinSet的皮肤选中状态
				
				for(var indexli = 0; indexli< oSkinLi.length; indexli++) {
					
					if(oSkinLi[indexli].getAttribute("data-skinlink")== data) {
						addClass(oSkinLi[indexli].getElementsByClassName("backimg")[0], "imgActive");
					}
				}
		}


		//数组的indexOf方法封装
		function indexOf(arr, value, start) {
			//如果不设置start,则默认start为0
			if(arguments.length == 2) {
				start = 0;
			}
			//如果数组中存在indexOf方法，则用原生的indexOf方法
			if(arr.indexOf) {
				return arr.indexOf(value, start);
			}
			for(var i = 0; i < arr.length; i++) {
				if(arr[i] === value) {
					return i;
				}
			}
			return -1;
		}

		function noRepeat(arr) {
			var result = [];
			for(var i = 0; i < arr.length; i++) {
				if(indexOf(result, arr[i]) == -1) {
					result.push(arr[i]);
				}
			}
			return result;
		}
		//inArray方法封装
		function inArray(arr, value) {
			for(var i = 0; i < arr.length; i++) {
				if(arr[i] === value) {
					return true;
				}
			}
			return false;
		}
		//去除首尾空格函数封装
		function trim(arr) {
			var result = arr.replace(/^\s+|\s+$/g, '');
			return result;
		}
		//addclass函数封装
		function addClass(obj, classStr) {
			var array = noRepeat(trim(obj.className).split('\s+'));
			if(!inArray(array, classStr)) {
				array.push(classStr);
			}
			obj.className = array.join(' ');
			return obj;
		}
		//removeclass函数封装
		function removeClass(obj, classStr) {
			var objClassName=obj.className;
			var str="app-aside hidden-xs bg-dark";
			newStr=str.split('\s+');
			var array = noRepeat(objClassName.split('\s+'));
			var index = indexOf(array, classStr);
			if(index != -1) {
				array.splice(index, 1);
				obj.className = array.join(' ');
			}
			return obj;
		}
		function createXMLHTTPRequest() {     
                 //1.创建XMLHttpRequest对象     
                 //这是XMLHttpReuquest对象无部使用中最复杂的一步     
                 //需要针对IE和其他类型的浏览器建立这个对象的不同方式写不同的代码     
                 var xmlHttpRequest;  
                 if (window.XMLHttpRequest) {     
                     //针对FireFox，Mozillar，Opera，Safari，IE7，IE8     
                    xmlHttpRequest = new XMLHttpRequest();     
                     //针对某些特定版本的mozillar浏览器的BUG进行修正     
                     if (xmlHttpRequest.overrideMimeType) {     
                         xmlHttpRequest.overrideMimeType("text/xml");     
                     }     
                 } else if (window.ActiveXObject) {     
                     //针对IE6，IE5.5，IE5     
                     //两个可以用于创建XMLHTTPRequest对象的控件名称，保存在一个js的数组中     
                     //排在前面的版本较新     
                     var activexName = [ "MSXML2.XMLHTTP", "Microsoft.XMLHTTP" ];     
                     for ( var i = 0; i < activexName.length; i++) {     
                         try {     
                             //取出一个控件名进行创建，如果创建成功就终止循环     
                             //如果创建失败，回抛出异常，然后可以继续循环，继续尝试创建     
                            xmlHttpRequest = new ActiveXObject(activexName[i]);   
                            if(xmlHttpRequest){  
                                break;  
                            }  
                         } catch (e) {     
                         }     
                     }     
                 }     
                 return xmlHttpRequest;  
             }   
		function get(url,success){  
		    var req = createXMLHTTPRequest();;
		    if(req){  
		        req.open("POST", url, true);  
		        req.onreadystatechange = function(response){  
		            if(req.readyState == 4){  
		                if(req.status == 200){  
		                    var data = eval('(' + req.responseText + ')');
		                    
		                    	
		                    	success(data.rows[0].userSkinColor);
		                   
		                    
		                }else{  
		                    
		                }  
		            }  
		        } ; 
		        req.send(userData);
		    }  
}   
	}
		