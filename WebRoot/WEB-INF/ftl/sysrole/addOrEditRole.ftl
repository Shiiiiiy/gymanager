<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title></title>
</head>
<body>
   
    <div class="content-box">
        <!--form-->
        <div class="page-header">
        	<h2>
        	        <#if sRole?? >
	        			编辑角色
	        		<#else>
	        			新增角色
	        		</#if>
        	    <small class="font-en caps">
	        		
        		</small>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addOrEditRole" method="post" action="${rc.contextPath}/sysrole/opt-save/saveRole.do">
            <input type="hidden" id="roleId" name="id" value="${(sRole.id)!'0'}"/>
           
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">角色名称 <span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="roleName" name="roleName" placeholder="请输入角色名称" style="width:200px;" value="${(sRole.roleName)!''}" >
		            </div>
		        </div>
            </div>
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">角色编码 <span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="roleCode" name="roleCode" placeholder="请输入角色编码" style="width:200px;" value="${(sRole.roleCode)!''}">
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span></span>
	                    <#if dicList??>
                              <#list dicList as d>
                                 <#if sRole??>
	                                  <#if d.id==sRole.status>
					                    <label class="input-group-addon gray-bg" style="width:45px;">
					                        <input type="radio" aria-label="..." value="${(d.id)!''}" checked name="status">
					                    </label>
					                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
		                              <#else>
	                                    <label class="input-group-addon gray-bg" style="width:45px;">
					                        <input type="radio" aria-label="..." value="${(d.id)!''}"  name="status">
					                    </label>
					                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
	                                  </#if>
                                 <#else>
                                      <#if d.code=='ENABLE'>
	                                        <label class="input-group-addon gray-bg" style="width:45px;">
						                        <input type="radio" aria-label="..." value="${(d.id)!''}" checked  name="status">
						                    </label>
						                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
					                  <#else>
					                         <label class="input-group-addon gray-bg" style="width:45px;">
					                        <input type="radio" aria-label="..." value="${(d.id)!''}"  name="status">
						                    </label>
						                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
					                  </#if>  
                                 </#if>
                              </#list>
                         </#if>  
	                    
	                </div>
	            </div>
	        </div>
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;</span>
	                    <textarea class="form-control" id="remarker" name="remarker" placeholder="请输入备注信息" style="width:450px;height:100px;">${(sRole.remarker)!""}</textarea>
	                </div>
	            </div>
	        </div>
	       
        	<div class="row">	
	            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	            	<a class="btn blue" href="javaScript:void(0)" onclick="save('addOrEditRole');"><i class="icon-ok"></i> 确定</a>
	       			<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
	        	</div>
        	</div>
        	
        	<div class="tc"></br>
	           
	       </div>
    
        </form>
    </div>
</body>
<script type="text/javascript">

	
	//取消
	function goBacks(){
		window.location.href="${rc.contextPath}/sysrole/opt-query/roleList.do";
	}
	
	//提交角色信息
	function save(str){
	    if(validate_form("addOrEditRole")){
           $("#addOrEditRole").submit();
		
	    }
	}
	
	
	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkLength("roleName","角色名称",true,1,20);
    		tt.checkLength("roleCode","角色编码",true,1,20,checkCode);
    		tt.checkLength("status","当前状态",true,1,1);
    		tt.checkLength("remarker","备注信息",false,1,200);
    		return tt.validate();
    }
    	
    function  checkCode(){
    		var roleCode =$("#roleCode").val()
    		
    		var regStr =  /\w+$/;  
    		//错误提示语
    		var str = "";  
    		if(regStr.test(roleCode)){
        		$.ajax({
				     url:"${rc.contextPath}/sysrole/opt-query/checkRoleCode.do?roleCode="+$("#roleCode").val()+"&roleId="+$("#roleId").val(),
				     dataType:"json",
				     async:false,
				     type:"POST",
				     success:function(data){
				        if(data.result=='ok'){
				            
				        }else{
				            str="该角色编码已存在，请换一个试试！";
				        }  
				     }
				
				
				});
    		}else{
    			str = "请输入字母、数字或下划线！";
    		}
    		
    		return str;
     }
	
	
</script>
</html>
