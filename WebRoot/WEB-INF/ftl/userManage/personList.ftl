<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
	<script type="text/javascript" src="${rc.contextPath}/plugin/layer/layer.js"></script>
    <title>用户管理</title>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>个人用户<small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/userManage/opt-query/personList.do">
          <div id="searchDiv">
            <div class="form-inline search_list">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                   
                    <div class="input-group">
                        <span class="input-group-addon">用户名称</span>
                        <input type="text" class="form-control" value="${(ump.username)!''}" id="username" name="username" placeholder="请输入用户名称">
                       
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">真实姓名</span>
                        <input type="text" class="form-control" value="${(ump.name)!''}" id="name" name="name" placeholder="请输入真实姓名">
                       
                    </div>
                
                </div>
                
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">手机号码</span>
                        <input type="text" class="form-control" value="${(ump.tel)!''}" id="tel" name="tel" placeholder="请输入手机号">
                       
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
	    		<a  class="btn green" onclick="addUser();" target="content-box" ><i class="icon-plus"></i> 新增</a>
	    		
	    	</div>
	    	<div class="btn-group">
	    	     <a class="btn black" onclick="delUser();"   href="javascript:void(0)"><i class="icon-trash"></i> 批量删除</a>
	    	</div>
	    	<div class="btn-group">
	    	    <a class="btn purple" onclick="exportUser();"  ><i class="icon-cog"></i> 导出</a>
	    	    
	    	</div>
	    	<div class="btn-group">
	    	     <a class="btn black"  onclick="importUser();"  href="javascript:void(0)"><i class="icon-cog"></i> 导入</a>
	    	</div>
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable" width="100%" style="table-layout:fixed">
                <thead>
                    <tr>
                        <th width="62px"><input type="checkbox" onclick="checkAll()" id="allUser"></th>
                        <th width="52px">序号</th>
                        <th width="">用户名称</th>
                        <th width="">真实姓名</th>
                        <th width="">手机号码</th>
                        <th width="">座机号码</th>
                        <th width="">邮箱</th>
                        <th width="58px">状态</th>
                        <th width="440px">操作</th>
                    </tr>
                </thead>
                <tbody id="hiddentable"></tbody>
                    <#if page??>
						<#list page.list as per>
							<tr>
							    <td><input type="checkbox" name="users"  class="${(per.GID)!''}" ><input type="hidden" name="personId" value="${(per.ID)!''}"></td>
								<td>${per_index+1 }</td>
								<td><div class="zjh">${(per.USERNAME)!"" }</div></td>
								<td><div class="zjh">${(per.NAME)!"" }</div></td>
								<td><div class="zjh">${(per.TEL)!"" }</div></td>
								<td><div class="zjh">${(per.PHONE)!"" }</div></td>
								<td><div class="zjh">${(per.EMAIL)!"" }</div></td>
								<td>
								  <#if dicList??>
								       <#list dicList as dl>
								       
										    <#if dl.id==(per.STATUS1?number) && dl.code==dic_e >
											      <span class="label label-info">${(dl.name)!""}</span>
											<#elseif dl.id==(per.STATUS1?number) && dl.code==dic_d>
											      <span class="label label-warning">${(dl.name)!""}</span> 
											</#if>
											
										</#list>	
								  </#if>
								</td>
								<td>
									<a onclick="userDtail('${(per.GID)!'' }');"  class="btn black"><i class="icon-search" ></i> 查看</a>&nbsp;
									<a onclick="resetPassword('${(per.GID)!'' }');" title="将密码重置为12345678"  class="btn mini green"><i class="icon-wrench"></i> 重置密码</a>&nbsp;
									<a onclick="editUser('${(per.GID)!'' }');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
									<a onclick="deleteFile('${(per.GID)!''}','${(per.ID)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
									<#if dicList??>
								       <#list dicList as dl>
								       
										    <#if dl.id==(per.STATUS1?number) && dl.code==dic_e >
										           <a class="btn yellow" href="javaScript:void(0)" name="1" onclick="changeStatus(this,'${(per.GID)!'' }','${(per.ID)!'' }')"><i class="icon-ban-circle"></i> 禁用</a>
										    <#elseif dl.id==(per.STATUS1?number) && dl.code==dic_d> 
										           <a class="btn little_blue" href="javaScript:void(0)" name="0"  onclick="changeStatus(this,'${(per.GID)!'' }','${(per.ID)!'' }')"><i class="icon-ok-sign"></i> 启用</a>
											       
											</#if>
										</#list>	
								    </#if>
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                <tfoot>
                    <tr>
                        <td colspan="9">
                           <#if page??> ${page.pageTool}</#if>
						</td>
                    </tr>
                </tfoot>
            </table>
        
       <div id="tishi" style="display:none;">确认删除？</div>
    </div>
    </form>
   </div>
    </div>
<div id="companyInfo"  style="display:none;">
	<#include "userPersonDetail.ftl">
</div>
</body>
<script type="text/javascript">
   //查看用户信息
   function userDtail(companyId){
   		$("#companyInfo").load("${rc.contextPath}/userManage/opt-query/userPersonDetail?guserId="+companyId,function(){
   			showDialogFullHD("查看",  "companyInfo",null,null,null,null,1,false,false,null,null);
   		});
	}
  
	 
   
	function changeStatus(obj,str,str1){//启用禁用功能
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val(); 
		var sta=$(obj).attr("name")+"";
		var flag="";
		if("1" == sta){
			
			flag="A";
		}
		if("0" == sta){
			
			flag="B";
		}
		window.location.href="${rc.contextPath}/userManage/opt-update/updateUserPerson.do?guserId="+str+"&flag="+flag+"&personId="+str1+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	//全选
	function checkAll(){
            var all = document.getElementById("allUser");
	        var ch = document.getElementsByName("users");
	        for(var i=0;i<ch.length;i++) {
	            ch[i].checked = all.checked;
	        }
    }
	
	
	//编辑用户
	function editUser(str){
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val(); 
		window.location.href="${rc.contextPath}/userManage/opt-addOrEdit/addOrEditUserPerson.do?guserId="+str+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	//重置密码
	function resetPassword(str){
		var pageNo = $("#pageNo").val();
		var pageSize = $("*[name='pageSize']").val(); 
	    window.location.href="${rc.contextPath}/userManage/opt-update/resetPassword.do?guserId="+str+"&pageNo="+pageNo+"&pageSize="+pageSize;
	}
	
	//新增用户
	function addUser(){
		window.location.href="${rc.contextPath}/userManage/opt-addOrEdit/addOrEditUserPerson.do";
	   
	}
	
	function deleteFile(guserIdList,companyIdList){
		showDialog("系统提示","tishi","sure('"+guserIdList+"','"+companyIdList+"')","cancel()","close()",100,true);
	}

	//删除用户
	function delUser(){
	    var objs=$("input[name='users']");
	    var guserIdList="";
	    var personIdList="";
	    //获取所有已被选中的记录id    
        $(objs).each(function(){
            if($(this).is(':checked')){
			  guserIdList=guserIdList+$(this).attr("class")+",";
			  personIdList=personIdList+$(this).next().val()+",";
			}  
	    });
	    if(guserIdList.length==0){
	        custom_alert('请选择要删除的用户',3,3000);
	        return;
	    }
		showDialog("系统提示","tishi","sure('"+guserIdList+"','"+personIdList+"')",null,null,100);
	}
	
	function sure(guserIdList,personIdList){
	
	   
		window.location.href="${rc.contextPath}/userManage/opt-del/delUserPerson.do?guserIdList="+guserIdList+"&personIdList="+personIdList;
		
	}
	 
	//点击查询
	function formSubmit(){
	    $("#pageNo").val(1);
	    $("#searchForm").attr('action','${rc.contextPath}/userManage/opt-query/personList.do');
		$("#searchForm").submit();
	}
	
	//清空查询条件
	function cleanAll(){
	    $("#username").val("");
	    $("#name").val(""); 
	    $("#tel").val(""); 
	}
	
	function importUser(){
		window.location.href="${rc.contextPath}/userManage/excelPersonUser";
	}
	 
	function exportUser(){
		$("#searchForm").attr('action','${rc.contextPath}/userManage/opt-update/exportPersonList.do');
		$("#searchForm").submit();
	    $("#searchForm").attr('action','${rc.contextPath}/userManage/opt-query/personList.do');
	} 
</script>
</html>
