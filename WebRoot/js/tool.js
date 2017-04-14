/**
* @param txt 内容
* @param type 1:成功    2：详情     3：警告           4：错误
* @param times 自动关闭时间  毫秒
* @param 使用方法 custom_alert("<strong>错误!</strong>出错了",4);
**/
function custom_alert(txt,type,times){
	var obj = $(document.body);
	var html = "";
	
	var div = $("#sys_alert_fid");
	
	if(div.length==0){
		div = $("<div></div>");
		div.attr("id","sys_alert_fid");
	}else{
		$("#sys_alert_fid").fadeIn(100);
	}
	div.addClass("alert");
	if(type){
		if(type==1){
			div.addClass("alert-success");
		}else if(type==2){
			div.addClass("alert-info");
		}else if(type==4){
			div.addClass("alert-error");
		}
	}

	html+="	<button class=\"close\" onclick=\"custom_alert_close();\"></button>";
	html+= txt;
	
	
	div.html(html);
	obj.prepend(div);
	if(times &&times!=0){
		//$("#sys_alert_fid").fadeOut(times);
		setTimeout(function(){
			custom_alert_close();
		}, times);
	}else{
//		$("#sys_alert_fid").fadeOut(5000);

		setTimeout(function(){
			custom_alert_close();
		}, 5000);
	}
}
function custom_alert_close(){
	$("#sys_alert_fid").fadeOut(100);
}




/**
 * 弹出悬浮层，将div_id内容加载至悬浮层   遮盖IFrame
 * @param title 标题   不可为NULL
 * @param div_id 目标内容DIV的ID   不可为NULL
 * @param sure_fun 确定按钮回调函数   XXX()  可为NULL
 * @param cancel_fun   取消按钮回调函数   XXX()  可为NULL
 * @param close_fun  关闭按钮回调函数   XXX()  可为NULL
 * @param marginTop  上外边距 (注意：如果在部分分辨率情况，弹出层位置需要调整则直接在此输入相应的值，无需带px，例如：300、-200)
 * @param btn_show  是否显示确定  取消 按钮           true 显示，false不显示         默认显示
 * @param btn_auto_close  点击示确定、取消按钮是否自动关闭弹出层        true 自动关闭，false手动管理         默认自动关闭
 * @param auto_close_times  弹出层自动关闭时间 (单位：毫秒)   当值为null或0时，无效        当值>0时，才有效
 */
function showDialog(title,div_id,sure_fun,cancel_fun,close_fun,marginTop,btn_show,btn_auto_close,auto_close_times){
	var ele = $("#"+div_id);
	var dialog_div = $("#selUserDislogDiv_"+div_id);
	var mt = "300";
	if(marginTop!=null && marginTop!=""){
		mt = marginTop;
	}


	var btn_sure_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100);";
	var btn_cancel_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100);";
	var btn_close_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100);";

	if(btn_auto_close==null || btn_auto_close){
		if(sure_fun!=null && sure_fun!=""){
			btn_sure_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100,function(){"+sure_fun+"});";//$(\"#con-iframe\")[0].contentWindow.
		}
		if(cancel_fun!=null && cancel_fun!=""){
			btn_cancel_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100,function(){"+cancel_fun+"});";
		}
		if(close_fun!=null && close_fun!=""){
			btn_close_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100,function(){"+close_fun+"});";
		}
	}else{
		if(sure_fun!=null && sure_fun!=""){
			btn_sure_fun = sure_fun;//$(\"#con-iframe\")[0].contentWindow.
		}
		if(cancel_fun!=null && cancel_fun!=""){
			btn_cancel_fun = cancel_fun;
		}
		if(close_fun!=null && close_fun!=""){
			btn_close_fun = close_fun;
		}
	}
	
	if(dialog_div && dialog_div.length==0){
		$(dialog_div).remove();
	}
	var html = "";
	html+="		<div class=\"modal-scrollable\" style=\"display: block; margin-top: "+mt+"px;\">";
	html+="			<div class=\"modal-header\">";
	html+="				<button type=\"button\" class=\"close\" onclick=\""+btn_close_fun+"\"></button>";
	html+="				<h3>"+title+"</h3>";
	html+="			</div>";
	html+="			<div class=\"modal-body\" id=\"selUserDislogDiv_body_"+div_id+"\">";
	html+= ele.html();
	html+="			</div>";
	
	if(btn_show==null ||(btn_show!=null && btn_show)){
		html+="			<div class=\"modal-footer\">";
		html+="				<button type=\"button\" class=\"btn\" onclick=\""+btn_cancel_fun+"\">取 消</button>";
		html+="				<button type=\"button\" class=\"btn green\" onclick=\""+btn_sure_fun+"\"\">确 定</button>";
		html+="			</div>";
	}
	html+="		</div>";
	html+="		<div class=\"modal-backdrop fade in\"></div>";


	var div = $("<div></div>");
	div.attr("id","selUserDislogDiv_"+div_id);
	div.html(html);
	$(document.body).prepend(div);
	
	if(auto_close_times && auto_close_times>0){alert(1);
		showDialog_close(div_id,auto_close_times);
	}
}
/**
 * 关闭 div_id内容悬浮层(主要用于自定义确定操作后需要关闭弹出的悬浮层)
 */
function showDialog_close(div_id,times){
	var time = 100;
	if(times){
		time = times;
	}
	$("#selUserDislogDiv_"+div_id).fadeOut(time);
}

/**
 * 通过函数名调用相应的函数
 * @param fun
 */
function frameCallFun(fun){
	eval(fun);
}


/****************************************************************垮IFrame调用版本  弹出层遮盖全屏 Begin************************************************************************/
/**
 * 弹出悬浮层，将div_id内容加载至悬浮层  遮盖全屏
 * @param title 标题   不可为NULL
 * @param div_id 目标内容DIV的ID   不可为NULL
 * @param div_html 目标内容 可为NULL 如果为NULL时则通过div_id取内容，反之不再通过div_id取内容
 * @param sure_fun 确定按钮回调函数   XXX()  可为NULL
 * @param cancel_fun   取消按钮回调函数   XXX()  可为NULL
 * @param close_fun  关闭按钮回调函数   XXX()  可为NULL
 * @param marginTop  上外边距 (注意：如果在部分分辨率情况，弹出层位置需要调整则直接在此输入相应的值，无需带px，例如：300、-200)
 * @param btn_show  是否显示确定  取消 按钮           true 显示，false不显示         默认显示
 * @param btn_auto_close  点击示确定、取消按钮是否自动关闭弹出层        true 自动关闭，false手动管理         默认自动关闭
 * @param auto_close_times  弹出层自动关闭时间 (单位：毫秒)   当值为null或0时，无效        当值>0时，才有效
 * @param auto_close_fun  弹出层自动关闭回调函数XXX()  可为NULL
 */
function showDialogFullHD(title,div_id,sure_fun,cancel_fun,close_fun,marginTop,btn_show,btn_auto_close,auto_close_times,auto_close_fun){
	var obj = window.parent;
	while($(obj.document).contents().find("#con-iframe").length==0){
		obj = obj.parent;
	}
	obj.showDialogIframe(title,div_id,sure_fun,cancel_fun,close_fun,marginTop,btn_show,btn_auto_close,auto_close_times,auto_close_fun);
}


/****************************************************************垮IFrame调用版本 END************************************************************************/



/****************************************************************数据更新中转信息提示 BEGIN************************************************************************/
/**
 * 弹出悬浮层，将div_id内容加载至悬浮层  遮盖全屏---------------------------forward中转信息提示专用
 * @param title 标题   不可为NULL
 * @param div_id 目标内容DIV的ID   不可为NULL
 * @param div_html 目标内容 可为NULL 如果为NULL时则通过div_id取内容，反之不再通过div_id取内容
 * @param sure_fun 确定按钮回调函数   XXX()  可为NULL
 * @param cancel_fun   取消按钮回调函数   XXX()  可为NULL
 * @param close_fun  关闭按钮回调函数   XXX()  可为NULL
 * @param marginTop  上外边距 (注意：如果在部分分辨率情况，弹出层位置需要调整则直接在此输入相应的值，无需带px，例如：300、-200)
 * @param btn_show  是否显示确定  取消 按钮           true 显示，false不显示         默认显示
 * @param btn_auto_close  点击示确定、取消按钮是否自动关闭弹出层        true 自动关闭，false手动管理         默认自动关闭
 * @param auto_close_times  弹出层自动关闭时间 (单位：毫秒)   当值为null或0时，无效        当值>0时，才有效
 * @param auto_close_fun  弹出层自动关闭回调函数XXX()  可为NULL
 */
function forward_showDialogFullHD(title,div_id,div_html,sure_fun,cancel_fun,close_fun,marginTop,btn_show,btn_auto_close,auto_close_times,auto_close_fun){
	var obj = window.parent;
	while($(obj.document).contents().find("#con-iframe").length==0){
		obj = obj.parent;
	}
	obj.showDialogIframe(title,div_id,div_html,null,null,null,marginTop,btn_show,null,auto_close_times,null);
	setTimeout("forward_submit('"+div_id+"')", auto_close_times);
}

function forward_submit(div_id){
	$("#frm").submit();
}


/****************************************************************数据更新中转信息提示 END************************************************************************/
/**
*	邮箱校验
**/
function isEmail(obj){   
	var reg=/^\w{3,}@\w+(\.\w+)+$/;   
	return reg.test(obj);  
}


/**
 * 自定义验证框架对象
 * @returns {___anonymous3595_3597}
 */
function validateFormObject(){ 
	var obj = new Object();
	//this.color = color;
	
	var errorCount = 0;
	/*** 校验字符串长度以及是否为空
	*	@param ele_id 输入原色ID
	*	@param title  标题
	*	@param isNull 是否必填   true必填                false非必填
	*	@param minLength  最小长度
	*	@param maxLength  最大长度
	*	@param fun  回调函数
	*
	*/
	obj.checkLength = function(ele_id,title,isNull,minLength,maxLength,fun) { 
		var obj = $("#"+ele_id);
		if(obj.length==0){
			obj = $("[name='"+ele_id+"']");
		}
		var tagName = obj[0].tagName.toLocaleUpperCase();
		var div_group = obj;

		while(div_group && !div_group.is("DIV")){
			div_group = div_group.parent();
		}
		var div = div_group.parent();
		var error = "";
		if(tagName=="INPUT"){
			var type = obj.attr("TYPE").toLocaleUpperCase();
			if(type=="TEXT" || type=="PASSWORD"){
				var val = $.trim(obj.val());
				obj.val(val);

				if(isNull && val.length==0){
					error = title+"不能为空";
				}else if(minLength && isNull && minLength >0 && val.length<minLength){
					error = "输入的"+title+"长度不能低于 "+minLength+"位";
				}else if(maxLength && maxLength >0 && val.length>maxLength){
					error = "输入的"+title+"长度不能超过 "+maxLength+"位";
				}
			}else if(type=="CHECKBOX"){
				var selCount = 0;
				for(var i=0;i<obj.lengh;i++){
					var chk = obj[i];
					if($(chk).is(':checked')){
						selCount ++;
					}
				}
				
				if(minLength && minLength >0 && selCount < minLength){
					error = title+"至少勾选 "+minLength+"项";
				}else if(maxLength && maxLength >0 && selCount > maxLength){
					error = title+"最多勾选 "+maxLength+"项";
				}
			}
		}else if(tagName=="TEXTAREA"){
			var val = $.trim(obj.val());
			obj.val(val);
			if(isNull && val.length==0){
				error = title+"不能为空";
			}else if(minLength && isNull && minLength >0 && val.length<minLength){
				error = "输入的"+title+"长度不能低于 "+minLength+"位";
			}else if(maxLength && maxLength >0 && val.length>maxLength){
				error = "输入的"+title+"长度不能超过 "+maxLength+"位";
			}
		}else if(tagName=="SELECT"){
			var val = $.trim(obj.val());
			obj.val(val);
			if(isNull && val.length==0){
				error = title+"不能为空";
			}
		}
		if(error=="" && fun &&fun!=""){
			error  = fun();
		}
		if(error!=""){
			errorCount ++;
			this.addError(div,div_group,error);
		}else{
			this.removeError(div,div_group);
		}
		
	};

	/*** 检验数字大小范围以及是否为空
	*	@param ele_id 输入原色ID
	*	@param title  标题
	*	@param isNull 是否必填   true必填                false非必填
	*	@param minLength  最小长度
	*	@param maxLength  最大长度
	*	@param fun  回调函数
	*
	*/
	obj.checkNumber = function(ele_id,title,isNull,minLength,maxLength,fun) { 
		var obj = $("#"+ele_id);
		if(obj.length==0){
			obj = $("[name='"+ele_id+"']");
		}
		var div_group = obj;

		while(div_group && !div_group.is("DIV")){
			div_group = div_group.parent();
		}
		var div = div_group.parent();
		var error = "";
		
		var val = $.trim(obj.val());
		obj.val(val);

		if(isNull && val.length==0){
			error = title+"不能为空";
		} 
		
		if(error == ""){
			var isNumber = !isNaN(val);//判断是否为数字
			if(isNumber){
				var temp = val;
				val =  Number(val);
				if(temp.length>1 && val>1 && temp.indexOf("0")==0){
					error = "输入的"+title+"格式有误";
				}else if(minLength && isNull && minLength >0 && val<minLength){
					error = "输入的"+title+"值不能小于 "+minLength;
				}else if(maxLength && maxLength >0 && val>maxLength){
					error = "输入的"+title+"值不能大于 "+maxLength;
				}else if(fun &&fun!=""){
					error  = fun();
				}
			}else{
				error = "输入的"+title+"格式有误";
			}
		}
		if(error!=""){
			errorCount ++;
			this.addError(div,div_group,error);
		}else{
			this.removeError(div,div_group);
		}
		
	};

	
	/**
	*	追加显示错误信息
	*/
	obj.addError = function(div,div_group,error){
		this.removeError(div,div_group);
		div.addClass("error");
		var p = $("<p></p>");
		p.text(error);
		div_group.append(p);
	};

	
	/**
	*	删除错误信息
	*/
	obj.removeError = function(div,div_group){
		div.removeClass("error");
		div_group.children("p").remove();
	};


	/**
	*	获取表单验证的结果       true验证通过         false验证未完全通过
	*/
	obj.validate = function(){
		if(errorCount==0){
			return true;
		}else{
			return false;
		}
	}; 
	return obj;
}


/**
 * 列表页面搜索显示隐藏控制
 * @param divId 搜索所在div的id
 * @param obj a标签的Object
 */
function searchDivShowOrClose(divId,obj) {
    var divObj = $("#"+divId);
    if(divObj && divObj.length>0){
    	
        var spanAry = $(obj).children("span");
        var span = spanAry[0];
        span = $(span);
        var classStr = span.attr("class");
        
        
        if(classStr=="triangle-up"){
        	span.attr("class","triangle-down");
        	$(obj).html("<span class=\"triangle-down\"></span>展开");
        	divObj.fadeOut(100);
        }else{
        	span.attr("class","triangle-up");
        	divObj.fadeIn(100);
        	$(obj).html("<span class=\"triangle-up\"></span>收缩");
        }
        
    }
}
$(function () {/*list页面 hover效果*/
	 $(document).on("mouseover", ".mytable .zjh", function () {
         $(this).addClass("zd");
     });
     $(document).on("mouseout", ".mytable .zjh", function () {
         $(this).removeClass("zd");
     });

   //图片上面按钮显示与隐藏
     $("div.thumb-box").hover(function(){
	 		$(this).find("p.img-btn").show();
	 	},function(){
	 		$(this).find("p.img-btn").hide();
	 	});
});
