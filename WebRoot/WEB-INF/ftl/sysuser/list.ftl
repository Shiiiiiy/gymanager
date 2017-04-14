<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>用户管理</title>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>用户管理<small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/sysuser/list.do">
        <div id="searchDiv">
            <div class="form-inline search_list">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                	<div class="input-group">
                        <span class="input-group-addon">帐号</span>
                        <input type="text" class="form-control" id="user_no" name="user_no" value="${(UserSearch.user_no)!''}" placeholder="请输入帐号">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                	<div class="input-group">
                        <span class="input-group-addon">用户名</span>
                        <input type="text" class="form-control" id="tname" name="name" value="${(UserSearch.name)!''}" placeholder="请输入用户名">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">状&nbsp;&nbsp;态</span>
                        <select class="form-control" id="tselect" name="status">
                            <option value="-1">请选择..&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</option>
	                        <#if Dics??>
							<#list Dics as dic>
								<#if UserSearch?? &&(UserSearch.status)?? && UserSearch.status == dic.id>
	                        	<option value="${(dic.id)!'' }" selected="selected" >${(dic.name)!''}</option>
	                        	<#else>
	                        	<option value="${(dic.id)!'' }" >${(dic.name)!''}</option>
	                        	</#if>
	                        </#list> 
							</#if>
                        </select>
                    </div>
                </div>
            </div>
            <div class="tc">
	            <a class="btn black"  onclick="formSubmit();"><i class="icon-search"></i> 查询</a>
	            <a class="btn black"  onclick="cleanAll();"><i class="icon-trash"></i> 清空</a>
	        </div>    
		</div>
		<a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>
        <div class="btn-group">
			<a class="btn green" onclick="NewUser();" target="content-box"><i class="icon-plus"></i> 新增</a>
		</div>
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable" id="mytable" >
                <thead>
                    <tr>
                        <th width="5%">序号</th>
                        <th width="10%">帐号</th>
                        <th width="10%">用户名</th>
                        <th width="10%">手机号</th>
                        <th width="10%">邮箱</th>
                        <th width="5%">状态</th>
                        <th width="40%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as user>
							<tr>
								<td><div class="zjh">${user_index+1 }</div></td>
								<td><div class="zjh">${(user.USER_NO)!"" }</div></td>
								<td><div class="zjh">${(user.NAME)!"" }</div></td>
								<td><div class="zjh">${(user.TEL_NO)!"" }</div></td>
								<td><div class="zjh">${(user.EMAIL)!""}</div></td>
								<td><#if Dics??>
									<#list Dics as dic>
										<#if (user.STATUS)?? && (user.STATUS+"") == (dic.id+"") && (dic.name+"") =="禁用">
										<span class="label label-warning">禁用</span>
			                        	</#if>
			                        	<#if (user.STATUS)?? && (user.STATUS+"") == (dic.id+"") && (dic.name+"") =="启用" >
			                        	<span class="label label-info">启用</span>
			                        	</#if>
			                        </#list> 
									</#if>
								</td>
								<td>
									<#if Dics??>
									<#list Dics as dic>
										<#if (user.STATUS)?? && (user.STATUS+"") == (dic.id+"") && (dic.name+"") =="禁用">
			                        	<a class="btn little_blue" value='${(user.ID)!""}' onclick="changeSTATUS(this);"><i class="icon-ok-sign"></i>启用</a>
			                        	</#if>
			                        	<#if (user.STATUS)?? && (user.STATUS+"") == (dic.id+"") && (dic.name+"") =="启用" >
			                        	<a class="btn yellow" value='${(user.ID)!""}' onclick="changeSTATUS(this);"><i class="icon-ban-circle"></i>禁用</a>
			                        	</#if>
			                        </#list> 
									</#if>
									&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="EditUser(${(user.ID)!'' });" class="btn blue mini"><i class="icon-edit"></i> 编辑</a>
									&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="DelUser(this);" value="${(user.ID)!'' }" class="btn mini black"><i class="icon-trash"></i> 删除</a>
									&nbsp;&nbsp;&nbsp;&nbsp;<a onclick="ReSetPwd(this);" value="${(user.ID)!'' }" class="btn mini green"><i class="icon-wrench"></i> 重置密码</a>
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="7">
                            ${(page.pageTool)!"" }
						</td>
                    </tr>
                </tfoot>
            </table>
        </div>
       
    </div>
    </form>
    <div id="ddd" style="display:none;">
    	是否删除该项	
	</div>
	<div id="ddd2" style="display:none;">
    	是否将密码初始化为系统默认密码?	
	</div>
</body>
<script type="text/javascript">
	function changeSTATUS(O){//启用禁用功能
		var id=$(O).attr("value")+"";
		self.location.href="${rc.contextPath}/sysuser/changestatus.do?id="+id;
	}
	function EditUser(O){//编辑用户  用户ID跳转
		var id=O+"";
		location="${rc.contextPath}/sysuser/edituser.do?id="+id;
	}
	function NewUser(){//新增用户
		location="${rc.contextPath}/sysuser/newuser.do";
	}
	function formSubmit(){
		$("#pageNo").val(1);
		$("#searchForm").attr("action","${rc.contextPath}/sysuser/list.do");
		$("#searchForm").submit();
	}
	function cleanAll(){//清空按钮
		$("#tname").val('');
		$("#user_no").val('');
		$("#tselect").children("option").removeAttr('selected');
	}
	<!-- 删除操作 -->
	function DelUser(O){
		var id=$(O).attr("value");
		showDialog("系统提示","ddd","a("+id+")","b()","b()",1);
	}
	function a(id){
		//alert("确定");
		self.location.href="${rc.contextPath}/sysuser/deluser.do?id="+id;
	}
	function b(){}
	<!-- 重置密码操作 -->
	function ReSetPwd(O){
		var id=$(O).attr("value");
		showDialog("系统提示","ddd2","aa("+id+")","bb()","bb()",1);
	}
	function aa(id){
		self.location.href="${rc.contextPath}/sysuser/resetpwd.do?id="+id;
	}
	function bb(){}
</script>
</html>
