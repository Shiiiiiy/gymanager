/****************************************************************垮IFrame调用版本  弹出层遮盖全屏 Begin************************************************************************/
/**
 * 弹出悬浮层，将div_id内容加载至悬浮层
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
function showDialogIframe(title,div_id,div_html,sure_fun,cancel_fun,close_fun,marginTop,btn_show,btn_auto_close,auto_close_times,auto_close_fun){
	
	
	var dialog_div = $("#selUserDislogDiv_"+div_id);
	var mt = "300";
	if(marginTop!=null && marginTop!=""){
		mt = marginTop;
	}
	var btn_sure_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100);";
	var btn_cancel_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100);";
	var btn_close_fun = "$('#selUserDislogDiv_"+div_id+"').fadeOut(100);";

	if(sure_fun!=null && sure_fun!=""){
		btn_sure_fun = "showDialog_close('"+div_id+"','"+sure_fun+"')";
	}
	if(cancel_fun!=null && cancel_fun!=""){
		btn_cancel_fun = "showDialog_close('"+div_id+"','"+cancel_fun+"')";
	}
	if(close_fun!=null && close_fun!=""){
		btn_close_fun = "showDialog_close('"+div_id+"','"+close_fun+"')";
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
	if(div_html==null){
		var ele = $("#con-iframe").contents().find("#"+div_id);
		html+= ele.html();
	}else{
		html+= div_html;
	}
	
	
	
	
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
		
	if(auto_close_times && auto_close_times>0){
		setTimeout("showDialog_close('"+div_id+"','"+auto_close_fun+"')", auto_close_times);
	}
}


/**
 * 关闭 div_id内容悬浮层(主要用于自定义确定操作后需要关闭弹出的悬浮层)
 */
function showDialog_close(div_id,fun,times){
	if(fun && fun!=null){
		$("#con-iframe")[0].contentWindow.frameCallFun(fun);
	}
	var fadeTime = 100;
	if(times && times!=null){
		fadeTime = times;
	}
	$("#selUserDislogDiv_"+div_id).fadeOut(fadeTime);
}


/****************************************************************垮IFrame调用版本 END************************************************************************/
