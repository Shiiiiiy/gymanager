<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title></title>
<style>
.ipt{
	width:100%;
	height:50px;
	border:1px solid #cecece;
}
.itd{
	height:42px;
	text-align:center;
}
</style>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <!--div class="page-header"><h2>资源文件<small class="font-en caps"></small></h2></div -->
        <div class="tabfix">
	        <!-- Nav tabs -->
	        <ul class="nav nav-tabs" role="tablist" >
	            <li role="presentation" id="li1"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" onclick="changeTab(1)">诚信认证服务机构</a>
	            <li role="presentation" id="li2"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="changeTab(2)" >行业协会</a>
	        </ul>
            
            <form action="${rc.contextPath}/dynamic/query/list">
	            <div class="tab-content">
		                <div role="tabpanel" id="tab1" class="tab-pane">
		                	<div class="btn-group" style="margin-bottom:10px;">
				    			<a  class="btn green" onclick="add('1');" target="content-box" ><i class="icon-plus"></i> 新增</a>
					    	</div>
					    	
					    	<div class="btn-group" style="margin-bottom:10px;">
					    		<a class="btn black" href="javaScript:void(0)" onclick="batchDelete(1);"><i class="icon-trash"></i> 批量删除</a>
					    	</div>
					    	
					    	<div class="table-responsive">
					            <table class="table table-bordered table-striped table-hover mytable">
					                <thead>
					                    <tr>
					                    	<th width="5%"><input class='ckbx_all' tab='1' type='checkbox' id="chooseall" ></input></th>
					                        <th width="6%">序号</th>
					                        <th width="10%">机构名称</th>
					                        <th width="10%">主营业务</th>
					                        <th width="11%">地址</th>
					                        <th width="8%">联系人</th>
					                        <th width="10%">电话</th>
					                        <th width="10%">网址</th>
					                        <th width="20%">操作</th>
					                    </tr>
					                </thead>
					                <tbody id="integrityAgency_id">
					                	<#if integrityAgencyPage?? >
											<#list integrityAgencyPage.list as map>
												<tr>
													<td name='ckbx'><input class='ckbx' tab='1' type="checkbox" name='isSelect' value='${(map['ID'])!""}' ></input></td>
													<td name='index'>${map_index + 1}</td>
													<td name='id' style="display:none"><div class="zjh">${(map['ID'])!"" }</div></td>
													<td name='name'><div class="zjh">${(map['NAME'])!"" }</div></td>
													<td name='service'><div class="zjh">${(map['SERVICE'])!"" }</div></td>
													<td name='address'><div class="zjh">${(map['ADDRESS'])!"" }</div></td>
													<td name='linkman'><div class="zjh">${(map['LINKMAN'])!"" }</div></td>
													<td name='tel'><div class="zjh">${(map['TEL'])!"" }</div></td>
													<td name='website'><div class="zjh">${(map['WEBSITE'])!"" }</div></td>
													<td name='opt'>
														<a class="btn blue" onclick="edit('${(map['ID'])!""}',this,1);"><i class="icon-edit"></i> 编辑</a>&nbsp;&nbsp;
														<a class="btn black" onclick="deleteIA('${(map['ID'])!""}',1);"><i class="icon-trash"></i> 删除</a>&nbsp;
													</td>
												</tr>
											</#list>
										</#if>				
					                </tbody>
					                <tfoot>
					                    <tr>
					                        <td colspan="9">
					                           <#if integrityAgencyPage??> ${integrityAgencyPage.pageTool}</#if>
											</td>
					                    </tr>
					                </tfoot>
					            </table>
					    	</div>
		                </div>
		                
		                <div role="tabpanel" id="tab2" class="tab-pane"><!--- 行业协会--->
		                	<div class="btn-group" style="margin-bottom:10px;">
				    			<a  class="btn green" onclick="add('2');" target="content-box" ><i class="icon-plus"></i> 新增</a>
					    	</div>
					    	
					    	<div class="btn-group" style="margin-bottom:10px;">
					    		<a class="btn black" href="javaScript:void(0)" onclick="batchDelete(2);"><i class="icon-trash"></i> 批量删除</a>
					    	</div>
					    	
					    	<div class="table-responsive">
					            <table class="table table-bordered table-striped table-hover mytable">
					                <thead>
					                    <tr>
					                    	<th width="5%"><input class='ckbx_all' tab='2' type='checkbox' id="chooseall" ></input></th>
					                        <th width="6%">序号</th>
					                        <th width="10%">协会名称</th>
					                        <th width="10%">所属行业</th>
					                        <th width="10%">人数</th>
					                        <th width="10%">级别</th>
					                        <th width="10%">联系人</th>
					                        <th width="9%">电话</th>
					                        <th width="9%">成立时间</th>
					                        <th width="22%">操作</th>
					                    </tr>
					                </thead>
					                <tbody id="industryGuild_id">	
					                	<#if industryGuildPage?? >
											<#list industryGuildPage.list as map>
												<tr>
													<td name='ckbx'><input class='ckbx' tab='2' type="checkbox" name='isSelect' value='${(map['ID'])!""}' ></input></td>
													<td name='index'>${map_index + 1}</td>
													<td name='id' style="display:none"><div class="zjh">${(map['ID'])!"" }</div></td>
													<td name='name'><div class="zjh">${(map['NAME'])!"" }</div></td>
													<td name='belong'><div class="zjh">${(map['BELONG'])!"" }</div></td>
													<td name='peopleNO'><div class="zjh">${(map['PEOPLENO'])!"" }</div></td>
													<td name='level'><div class="zjh">${(map['LEVEL'])!"" }</div></td>
													<td name='linkman'><div class="zjh">${(map['LINKMAN'])!"" }</div></td>
													<td name='tel'><div class="zjh">${(map['TEL'])!"" }</div></td>
													<td name='startTime'><div class="zjh">${(map['START_TIME'])!"" }</div></td>
													<td name='opt'>
														<a class="btn blue" href="javascript:void(0)" onclick="edit('${(map['ID'])!""}',this,2);"><i class="icon-edit" style="cursor:hand"></i> 编辑</a> &nbsp;
														<a class="btn black" href="javascript:void(0)" onclick="deleteIA('${(map['ID'])!""}',2);"> <i class="icon-trash" style="cursor:hand"></i> 删除</a>&nbsp;
													</td>
												</tr>
											</#list>
										</#if>					
					                </tbody>
					                <tfoot>
					                    <tr>
					                        <td colspan="10">
					                           <#if industryGuildPage??> ${industryGuildPage.pageTool}</#if>
											</td>
					                    </tr>
					                </tfoot>
					            </table>
					    	</div>
		                </div>
		                <input name='tab' value='${flag}' id='tab_ipt' style='display:none;' />
	            </div>
	            </form>
	            <div id="deleteDiv" style="display:none;">确认删除？</div>
        </div>
    </div>
    
    <form id="sform" method='post' style='display:none'></form>
    
</body>
<script type="text/javascript">
$(function(){
	var flag = "${flag}";
	if(flag == "1"){
		$("#li2").removeClass("active");
		$("#tab2").removeClass("active");
		$("#li1").addClass("active");
		$("#tab1").addClass("active");
	}else{
		$("#li1").removeClass("active");
		$("#tab1").removeClass("active");
		$("#li2").addClass("active");
		$("#tab2").addClass("active");
	}
	
	$(".ckbx_all").click(function(){
		var isChecked = $(this).prop("checked");
		var selector = "#tab"+$(this).attr("tab")+" input[name='isSelect']";
		$(selector).prop("checked", isChecked);
	});
	
	$(".ckbx").click(function(){
		var selector_checked = "#tab"+$(this).attr('tab')+" input[name='isSelect']:checked";
		var selector_unck = "#tab"+$(this).attr('tab')+" input[name='isSelect']";
		var sor = "#tab"+$(this).attr('tab')+" .ckbx_all";
		if($(selector_checked).length == $(selector_unck).length){
			$(sor).prop("checked",true);
		}else $(sor).prop("checked",false);
	});
});

/**
* 是否有未保存的条目,有->true, 没有->false
*/
var hasUnsaveItem = function(tab){
	var selector = "#"+tab+" .ipt";
	var aipts = $(selector);
	if(aipts.length > 0)return true;
	return false;
};

var add = function(str){
	if(str == '1'){
		if(hasUnsaveItem('tab1')){
			custom_alert("有未保存的信息,请先保存后重试!",5000);
			return;
		}
		var html = 
		"<tr>"+
			"<td class='itd'><i class='icon-plus'></i></td><td class='itd'><i class='icon-plus'></i></td>"+
			"<td class='itd'><input name='name' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='service' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='address' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='linkman' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='tel' maxlength='12' class='ipt' /></td>"+
			"<td class='itd'><input name='website' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><a href='javascript:void(0)' class='btn blue' onclick='save(1)'><i class='icon-ok'></i> 确定</a>&nbsp;"+
			"<a href='javascript:void(0)' class='btn black' onclick='cancel(1)'><i class='icon-remove'></i> 取消</a></td>"+
		"</tr>";
		$("#integrityAgency_id").before(html);
	}else if(str == '2'){
		if(hasUnsaveItem('tab2')){
			custom_alert("有未保存的信息,请先保存后重试!",5000);
			return;
		}
		var html = 
			"<tr>"+
			"<td class='itd'><i class='icon-plus'></i></td><td class='itd'><i class='icon-plus'></i></td>"+
			"<td class='itd'><input name='name' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='belong' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='peopleNO' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='level' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='linkman' maxlength='32' class='ipt' /></td>"+
			"<td class='itd'><input name='tel' maxlength='12' class='ipt' /></td>"+
			"<td class='itd'><input name='tstartTime' maxlength='20' class='ipt' /></td>"+
			"<td class='itd'><a href='javascript:void(0)' class='btn blue' onclick='save(2)'><i class='icon-ok'></i> 确定</a>&nbsp;"+
			"<a href='javascript:void(0)' class='btn black' onclick='cancel(2)'><i class='icon-remove'></i> 取消</a></td>"+
		"</tr>";
		$("#industryGuild_id").before(html);
	}
};

var save = function(index){
	$("#sform input").remove();
	if(index == 1){
		var aInput = $("#tab1 .ipt");
		var pass = true;
		$.each(aInput,function(i,o){
			if($.trim($(o).val()) == ""){
				custom_alert("您填写的信息不完整,请补充完整后重试!",5000);
				pass = false;
			}
			$("#sform").append($(o).clone());
		});
		if(!pass)return;
		else {
			$("#sform").append("<input name='flag' value='1' />");
			$("#sform").attr('action','${rc.contextPath}/dynamic/opt/save');
			$("#sform").submit();
		}
	}else if(index == 2){
		var aInput = $("#tab2 .ipt");
		var pass = true;
		$.each(aInput,function(i,o){
			if($.trim($(o).val()) == ""){
				custom_alert("您填写的信息不完整,请补充完整后重试!",5000);
				pass = false;
			}else if($(o).attr("name")=="tstartTime"){
				var zhi=$(o).val();
				if(!IsDate(zhi)){
				    custom_alert("您填写的日期格式不正确:格式2016-01-01",5000);
				    pass = false;
				}
			}
			$("#sform").append($(o).clone());
		});
		if(!pass)return;
		else {
			$("#sform").append("<input name='flag' value='2' />");
			$("#sform").attr('action','${rc.contextPath}/dynamic/opt/saveig');
			$("#sform").submit();
		}
	}
};
//判断日期类型是否为YYYY-MM-DD格式的类型    
function IsDate(str){        
    if(str.length!=0){    
        var reg = /^(\d{1,4})(-|\/)(\d{1,2})\2(\d{1,2})$/;     
        var r = str.match(reg);     
        if(r!=null){return true;}//类型正确    
    } 
    return false;   
}     
var edit = function(id,obj,j){
	
	if(hasUnsaveItem('tab'+j)){
		custom_alert("有未保存的信息,请先保存后重试!",5000);
		return;
	}
	var atds = $(obj).parent().parent().find("td");
	$.post("${rc.contextPath}/dynamic/query/one",{id:id,tab:j},function(data){
		$.each(atds,function(i,o){
			var name = $(o).attr('name');
			if(name == 'ckbx' ){
				$(o).find("input").attr('disabled','disabled');
			}else if(name == 'opt'){
				$(o).html("<a href='javascript:void(0)' class='btn blue'  onclick='save("+j+")'><i class='icon-ok'></i> 确定</a> &nbsp;" +
					"<a href='javascript:void(0)' class='btn black' onclick='cancel("+j+")'><i class='icon-remove'></i> 取消</a>");
			}else{
				for(var index in data){
					if(index == name){
						$(o).css("padding","0").css("margin","0").css("height","38px");
						//alert(index);
						if(index == "startTime"){//特例
							$(o).html("<input name='t"+index+"' maxlength='32' class='ipt' value='"+data.comments+"' />");
						}else{
							$(o).html("<input name='"+index+"' maxlength='32' class='ipt' value='"+data[index]+"' />");
						}
					}
				}
			}
		});
	},"json");
};

var deleteIA = function(id,i){
	if(hasUnsaveItem('tab'+i)){
		custom_alert("有未保存的信息,请先保存后重试!",5000);
		return;
	}
	showDialog("系统提示","deleteDiv","sure('"+id+"',"+i+")","cancel()","close()",100);
};

var sure = function(id,i){
	if(i == 1){
		window.location.href="${rc.contextPath}/dynamic/opt/deleteia?ids="+id;
	}else{
		window.location.href="${rc.contextPath}/dynamic/opt/deleteig?ids="+id;
	}
};

var cancel = function(i){
	if(i){
		window.location.href="${rc.contextPath}/dynamic/query/list?flag="+i;
	}
};

var close = function(){};

var batchDelete = function(i){
	if(i == 1){
		if(hasUnsaveItem('tab1')){
			custom_alert("有未保存的信息,请先保存后重试!",5000);
			return;
		}
		var a = $("#tab1 input[name='isSelect']:checked");
		if(a.length == 0){
			custom_alert("请选择要删除的条目,再进行操作!",5000);
			return;
		}
	}else{
		if(hasUnsaveItem('tab2')){
			custom_alert("有未保存的信息,请先保存后重试!",5000);
			return;
		}
		var a = $("#tab2 input[name='isSelect']:checked");
		if(a.length == 0){
			custom_alert("请选择要删除的条目,再进行操作!",5000);
			return;
		}
	}
	showDialog("系统提示","deleteDiv","sure2("+i+")","cancel()","close()",100);
};

var sure2 = function(i){
	if(i == 1){
		var ids = "";
		var selector = "#tab1 input[name='isSelect']:checked";
		var a = $(selector);
		a.each(function(i,o){ids += $(o).val() + ",";});
		window.location.href="${rc.contextPath}/dynamic/opt/deleteia?ids="+ids;
	}else{
		var ids = "";
		var selector = "#tab2 input[name='isSelect']:checked";
		var a = $(selector);
		a.each(function(i,o){ids += $(o).val() + ",";});
		window.location.href="${rc.contextPath}/dynamic/opt/deleteig?ids="+ids;
	}
};

var changeTab = function(i){
	$("#tab_ipt").val(i);
};
</script>
</html>
 