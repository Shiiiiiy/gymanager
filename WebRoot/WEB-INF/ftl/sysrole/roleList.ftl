<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>角色管理</title>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>角色列表<small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/sysrole/opt-query/roleList.do">
          <div id="searchDiv">
            <div class="form-inline search_list">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                   
                    <div class="input-group">
                        <span class="input-group-addon">角色名称</span>
                        <input type="text" class="form-control" value="${(srole.roleName)!''}" id="roleName" name="roleName" placeholder="请输入角色名称">
                       
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">角色编码</span>
                        <input type="text" class="form-control" value="${(srole.roleCode)!''}" id="roleCode" name="roleCode" placeholder="请输入角色编码">
                       
                    </div>
                
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
	                    <span class="input-group-addon">&nbsp;&nbsp;状&nbsp;态&nbsp;</span>
	                    <select class="form-control" name="status" id="status" style="width:180px;">
	                        <option selected="selected" id="sel" value="0">请选择..</option>
	                        <#if dicList??>
                              <#list dicList as d>
                                 <#if srole?? && srole.status?? && d.id==srole.status>
                                    <option value="${(d.id)!''}" selected>${(d.name)!""}</option>
                                 <#else>
                                    <option value="${(d.id)!''}">${(d.name)!""}</option>
                                 </#if>
                              </#list>
                        </#if>
	                    </select>
                    </div>
                </div>
                
              </div>
            
	            <div class="tc">
		            <a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>
		            <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
		        </div> 
	        </div> 
	        <a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>
            
            <div class="btn-group">
	    		<a  class="btn green" onclick="addRole();" target="content-box" ><i class="icon-plus"></i>新增</a>
	    	</div>
	    	
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                        <th width="8%">序号</th>
                        <th width="17%">角色名称</th>
                        <th width="15%">角色编码</th>
                        <th width="11%">状态</th>
                        <th width="50%">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as role>
							<tr>
								<td>${role_index+1 }</td>
								<td><div class="zjh">${(role.ROLE_NAME)!"" }</div></td>
								<td><div class="zjh">${(role.ROLE_CODE)!"" }</div></td>
								<td>
								  <#if dicList??>
								       <#list dicList as dl>
								       
										    <#if dl.id==(role.STATUS?number) && dl.code==dic_e >
											      <span class="label label-info">${(dl.name)!""}</span>
											<#elseif dl.id==(role.STATUS?number) && dl.code==dic_d>
											      <span class="label label-warning">${(dl.name)!""}</span> 
											</#if>
											
										</#list>	
								  </#if>
								</td>
								<td>
									<a onclick="selectUser(${(role.ID)!'' },this);" class="btn green"><i class="icon-user"></i> &nbsp;&nbsp;选择用户</a>&nbsp;&nbsp;
									
									
									<#if dicList??>
								       <#list dicList as dl>
								       
										    <#if dl.id==(role.STATUS?number) && dl.code==dic_e >
										           <a class="btn yellow" href="javaScript:void(0)" name="1" onclick="changeStatus(this,${(role.ID)!'' })"><i class="icon-ban-circle"></i>禁用</a>
										    <#elseif dl.id==(role.STATUS?number) && dl.code==dic_d> 
										           <a class="btn little_blue" href="javaScript:void(0)" name="0"  onclick="changeStatus(this,${(role.ID)!'' })"><i class="icon-ok-sign"></i>启用</a>
											       
											</#if>
										</#list>	
								    </#if>
									&nbsp;&nbsp;
									<a onclick="selectMenu(${(role.ID)!'' },this);"  class="btn green"><i class="icon-cog"></i> 选择菜单</a>&nbsp;&nbsp;
									<a onclick="editRole(${(role.ID)!'' },this);" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;&nbsp;
									<a onclick="delRole(${(role.ID)!'' },this);"  class="btn black"><i class="icon-trash"></i> 删除</a>
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="8">
                           <#if page??> ${page.pageTool}</#if>
						</td>
                    </tr>
                </tfoot>
            </table>
        
       <div id="tishi" style="display:none;">确认删除？</div>
    </div>
    </form>
</body>
<script type="text/javascript">
   
	function changeStatus(obj,str){//启用禁用功能
		var sta=$(obj).attr("name")+"";
		var flag="";
		if("1" == sta){
			
			flag="A";
		}
		if("0" == sta){
			
			flag="B";
		}
		
		$.ajax({
		     url:"${rc.contextPath}/sysrole/opt-update/updateRole.do?roleId="+str+"&flag="+flag,
		     dataType:"json",
		     type:"post",
		     success:function(data){
		         
		          $("#searchForm").submit();
		     }
		
		
		});
		
		
	}
	
	//选择用户
	function selectUser(str,obj){
	   
	        window.location.href="${rc.contextPath}/sysrole/opt-query/selectUser.do?id="+str+"&myflag=ok";
	    
	}
	
	//选择菜单
	function selectMenu(str,obj){
		
	        window.location.href="${rc.contextPath}/sysrole/opt-query/selectMenu.do?roleId="+str;
	    
	}
	
	//编辑角色
	function editRole(str,obj){
		
	     window.location.href="${rc.contextPath}/sysrole/opt-addOrEdit/addOrEditRole.do?roleId="+str;
	    
		
	}
	
	
	//新增角色
	function addRole(){
		window.location.href="${rc.contextPath}/sysrole/opt-addOrEdit/addOrEditRole.do";
	   
	}
	

	//删除角色
	function delRole(str,obj){
	        showDialog("系统提示","tishi","sure("+str+")","cancel()","close()",100);
	    
	}
	function sure(str){
		window.location.href="${rc.contextPath}/sysrole/opt-del/delRole.do?roleId="+str;
	}
	function cancel(){
		
	}
	function close(){
		
	}
	
	
	
	//点击查询
	function formSubmit(){
	    $("#pageNo").val(1);
		$("#searchForm").submit();
	}
	
	//清空查询条件
	function cleanAll(){
	    $("#roleName").val("");
	    $("#roleCode").val(""); 
		$("#status option").removeAttr('selected');
	}
	
</script>
</html>
