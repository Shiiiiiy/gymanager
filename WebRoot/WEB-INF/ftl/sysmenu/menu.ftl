<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
</head>
<body>
	<div class="menuview mytable" id="mytable" <#if no_next??>style="display:none;"</#if>  >
        <!--form-->
          <form class="form-inline search_list" role="form" >
            <div class="row">
                <div class="form-group col-xs-12 col-sm-12 col-md-12 col-lg-12">
					<span onclick="NewMenu(${(MenuO.id)!""});" target="content-box" class="btn green"><i class="icon-plus"></i> 新增</span>	
                </div>
            </div>
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover">
                <thead>
                    <tr>
                        <th width="7%">序号</th>
                        <th width="16%">菜单名称</th>
                        <th width="16%">菜单编码</th>
                        <th width="16%">菜单链接</th>
                        <th width="5%">状态</th>
                        <th width="40%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable" >
                    <#if ListMenu??>
					<#list ListMenu as ll>
                	<tr id="${ll_index+'aa' }">
                		<input type="hidden" value="${(ll.id)!"" }"/>
                		<input type="hidden" value="${(ll.remark)!"" }"/>
						<td><div class="zjh">${ll_index+1 }</div></td>
						<td><div class="zjh">${(ll.title)!"" }</div></td>
						<td><div class="zjh">${(ll.menu_code)!"" }</div></td>
						<td><div class="zjh">${(ll.url)!"" }</div></td>
						<td>
                        	<#if Dics??>
							<#list Dics as dic>
								<#if (ll.status)?? && (ll.status+"") == (dic.id+"") && (dic.name+"") =="禁用">
								<span class="label label-warning">禁用</span>
	                        	</#if>
	                        	<#if (ll.status)?? && (ll.status+"") == (dic.id+"") && (dic.name+"") =="启用" >
	                        	<span class="label label-info">启用</span>
	                        	</#if>
	                        </#list> 
							</#if>
						</td>
                        <td>                        	
                			<#if Dics??>
							<#list Dics as dic>
								<#if (ll.status)?? && (ll.status+"") == (dic.id+"") && (dic.name+"") =="禁用">
	                        	<a class="btn little_blue" value='${(ll.status)!""}' onclick="changeSTATUS('${(ll.id)!''}');" ><i class="icon-ok-sign"></i>启用</a>
	                        	</#if>
	                        	<#if (ll.status)?? && (ll.status+"") == (dic.id+"") && (dic.name+"") =="启用" >
	                        	<a class="btn yellow" value='${(ll.status)!""}' onclick="changeSTATUS('${(ll.id)!''}');" ><i class="icon-ban-circle"></i>禁用</a>
	                        	</#if>
	                        </#list> 
							</#if>
	                     	&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="EditMenu(${ll_index});" value="${(ll.id)!''}" class="btn blue mini"><i class="icon-edit"></i> 编辑</a>
							&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="DelMenu1(this);" value="${(ll.id)!''}" class="btn mini black"  ><i class="icon-trash"></i> 删除</a>
                        </td>
                    </tr>
                    </#list> 
					</#if>
                </tbody>			
                <tfoot>
                </tfoot>
            </table>
        </div>
    </div>
    </form>
	</div>
	<#include "editmenu.ftl">
</body>
<script type="text/javascript">
	function changeSTATUS(O){//启用禁用功能
		var id=O+"";
		parent.location.href="${rc.contextPath}/sysmenu/changestatus.do?id="+id;
	}
	function NewMenu(O){//新增菜单
		//显示层
		$("#mytable").css("display","none");
		$("#tdiv1").css("display","block");
		//初始化数据
		ClearFormS();
		$("#thedivname").html("新增菜单");
		$("#pre_name").val('${(MenuO.title)!""}');//上级菜单名称
		$("#p_id").val('${(MenuO.id)!""}');//上级菜单id 隐藏
		//
		var arrChk=$("#edituser input[type='radio']");
		arrChk[0].checked=true;
	}
	function EditMenu(O){//编辑菜单
		var true_id=$("#"+O+"aa").children("input").eq(0).val();
		var title=$("#"+O+"aa").children("td").eq(1).children("div").html();//
		var menu_code=$("#"+O+"aa").children("td").eq(2).children("div").html();//
		var url=$("#"+O+"aa").children("td").eq(3).children("div").html();//
		var remark=$("#"+O+"aa").children("input").eq(1).val();
		var t_status=$("#"+O+"aa").children("td").eq(5).children("a").eq(0).attr("value");
		//显示层
		$("#mytable").css("display","none");
		$("#tdiv1").css("display","block");
		//初始化数据
		ClearFormS();
		$("#thedivname").html("编辑菜单");
		$("#pre_name").val('${(MenuO.title)!""}');//上级菜单名称
		$("#p_id").val('${(MenuO.id)!""}');//上级菜单id 隐藏
		//其他数据
		$("#true_id").val(true_id);//其实就是id
		$("#title").val(title);
		$("#menu_code").val(menu_code);	
		$("#url").val(url);
		$("#remark").html(remark);
		var arrChk=$("#edituser input[type='radio']");
        for (var i=0;i<arrChk.length;i++){
            if(  (arrChk[i].value+"") == (t_status+"")  ){
                arrChk[i].checked=true;
            }else{
                arrChk[i].checked=false;
            }
        }
		$("#old_code").val(menu_code);
	}
	function ToEscF(){
		$("#mytable").css("display","block");
		$("#tdiv1").css("display","none");
		ClearFormS();
	}
	function ToSubmitF(){
		if("新增菜单" == ($("#thedivname").html()+"")){
			if(validate_form_New()){
				$('#edituser').attr("action","${rc.contextPath}/sysmenu/savemenu.do");
				$('#edituser').submit();
			}
		}else if("编辑菜单" == ($("#thedivname").html()+"")){
			if(validate_form_Edit()){
				$('#edituser').attr("action","${rc.contextPath}/sysmenu/editmenu.do?tid="+$("#true_id").val());
				$('#edituser').submit();
			}
		}
	}
	function ClearFormS(){
		$("#thedivname").html("");
		$("#pre_name").val("");
		$("#p_id").val("");
		$("#true_id").val("");//其实就是id
		$("#title").val("");
		$("#menu_code").val("");	
		$("#url").val("");
		$("#remark").html("");
		$("#old_code").val("");
		var arrChk=$("#edituser input[type='radio']");
        for (var i=0;i<arrChk.length;i++){arrChk[i].checked=false;}//清空选中状态
	}
	function validate_form_New(formid){//新增时  校验函数
		var tt = validateFormObject();
		tt.checkLength("title","菜单名称",true,1,10);
		tt.checkLength("menu_code","菜单编码",true,1,20,checkDupliMenuCode);
		tt.checkLength("url","菜单链接",true,1,100);
		tt.checkLength("remark","菜单备注",false,1,200);
		return tt.validate();
	}
	function validate_form_Edit(formid){//新增时  校验函数
		var tt = validateFormObject();
		tt.checkLength("title","菜单名称",true,1,10);
		tt.checkLength("menu_code","菜单编码",true,1,20,checkDupliMenuCode);
		tt.checkLength("url","菜单链接",true,1,100);
		tt.checkLength("remark","菜单备注",false,1,200);
		return tt.validate();
	}
	//
	function DelMenu1(O){//删除菜单
		var id=$(O).attr("value");
		window.parent.DelMenu(id);
	}
	function checkDupliMenuCode(){//异步CODE 是否重名
		var code=$("#menu_code").val()+"";//code新
		var old_code=$("#old_code").val()+"";//code旧
		var str="";
		if("编辑菜单" == ($("#thedivname").html()+"")  &&  code == old_code){
			//
		}else{//用户改动了 那么检查
			$.ajax({
		        url:"${rc.contextPath}/sysmenu/checkDupliMenuCode.do",
		        type:"post",
		        async:false,
		        data:{"code":code},
		        dataType:"text",
		        success : function(obj){
		            if("true" == obj){//有重名编码
						str="该菜单编码已经存在,请换一个试试";
		            }
		        }
		    });
	    }
	    return str; 
	}
</script>
</html>