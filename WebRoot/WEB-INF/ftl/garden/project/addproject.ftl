<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#include "/common/common.ftl" >
    
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header">
        	<h2>
        		<#if project?? >
        			编辑
        		<#else>
        			新增
        		</#if>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="editProectForm" method="post" action="${rc.contextPath}/industrygarden/opt-save/saveproject.do">
        	<input type="hidden" value="${pageSize!''}" name="pageSize"/>
        	<input type="hidden" value="${pageNo!''}" name="pageNo"/>
        	<div class="row">
	        	 <div class="col-xs-12 col-sm-6 col-md-5 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">园区列表 <span style="color:red;">*</span></label></span>
		                <select class="form-control" name="ofGarden" id="ofGarden" style="width:280px;">
		                    <option value="">请选择..</option>
		                    <#if mapList??>
		                    	<#list mapList as map>
				                    <#if project?? && project.ofGarden??  &&  project.ofGarden ==map["ID"] >
			                			<option value="${map['ID']}" selected>${(map["NAME"])!""}</option>
			                		<#else>
			                			<option value="${map['ID']}">${(map["NAME"])!""}</option>
			                		</#if>
		                    	</#list>
		                    </#if>
	                 </select>
		            </div>
		        </div>
		      </div>
		      
		      <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-5 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">项目类型 <span style="color:red;">*</span></span>
		                <select class="form-control" id="projectType" name="projectType" style="width:280px;">
		                    <option value="">请选择..</option>
		                    <#if projectTypelist??>
							<#list projectTypelist as type>
								<#if project?? && project.projectType?? && project.projectType == type.code>
		                    	<option value="${(type.code)!'' }" selected="selected" >${(type.name)!''}</option>
		                    	<#else>
		                    	<option value="${(type.code)!'' }" >${(type.name)!''}</option>
		                    	</#if>
		                    </#list> 
							</#if>
		                </select>
		            </div>
		        </div>
		      </div>
		      
			  <div class="row">     
	            <div class="col-xs-12 col-sm-6 col-md-5 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">项目名称 <span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="projectName" name="projectName" placeholder="请输项目名称" style="width:280px;" <#if project??>value='${(project.projectName)!""}'</#if>>
		                <#if project??>
		                	<input type="hidden" value="${project.id}" name="id" >
		                </#if>
		            </div>
		        </div>
	        </div>  
	        
           
			  <div class="row"> 
	            <div class="col-xs-12 col-sm-6 col-md-5 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon">项目状态 <span style="color:red;">*</span></span>
	                    <#if projectStatuslist??>
						<#list projectStatuslist as d>
		                    <label class="input-group-addon gray-bg" style="width:38px;">
	                    		<#if project ?? && project.projectStatus ?? && project.projectStatus == d.code>
		                    		<input type="radio" aria-label="..." value="${(d.code)!""}" checked name="projectStatus" >
		                    	<#else>
	                    			<#if planStruct ?? && d.code==planStruct.code>
		                    			<input type="radio" aria-label="..." value="${(d.code)!""}" checked name="projectStatus" >
	                    			<#else>
	                    				<input type="radio" aria-label="..." value="${(d.code)!""}"  name="projectStatus" >
	                    			</#if>
	                    		</#if>
		                    </label>
		                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
	                    </#list> 
						</#if>
	                </div>
	            </div>
	        </div>

	        
	        <div class="row">
		        <div class="col-xs-12 col-sm-6 col-md-5 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">启动时间 &nbsp;</span>
		                <div  class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" id="startTime" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
		                    <input size="" type="text" value="${((project.projectTime)?string('yyyy-MM-dd'))!''}"  id="projectTime" name="projectTime"  placeholder="请选择启动时间" readonly class="form-control" style="width:280px;">
		                    <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
		                    <span class="add-on"><i class="icon-th iconfont"></i></span>
		                </div>
		               
		            </div>
		        </div>
		      </div>
		      <div class="row">  
		        <div class="col-xs-12 col-sm-6 col-md-5 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">结束时间 &nbsp;</span>
		                <div  class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" id="endTime" data-date-format="yyyy-MM-dd" data-link-field="dtp_input2">
		                    <input size="" type="text" value="${((project.projectOvertime)?string('yyyy-MM-dd'))!''}"  id="projectOvertime" name="projectOvertime" placeholder="请选择结束时间" readonly class="form-control" style="width:280px;">
		                    <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
		                    <span class="add-on"><i class="icon-th iconfont"></i></span>
		                </div>
		               
		            </div>
		        </div>
	        </div> 
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-5 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">项目金额 <span style="color:red;">*</span></span>
		                <input type="text" class="form-control" maxlength="15" id="projectInvest" name="projectInvest" placeholder="请输入项目金额 (万元人民币)" style="width:280px;" <#if project??>value='${(project.projectInvest)!""}'</#if>>
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-10 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon">项目内容 <span style="color:red;">*</span></span>
	                    <textarea class="form-control" id="projectContent" name="projectContent" placeholder="请输入项目内容" style="width:450px;height:100px;"><#if project??>${(project.projectContent)!""}</#if></textarea>
	                </div>
	            </div>
	        </div>
	        
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-10 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon">&nbsp;&nbsp;备&nbsp;&nbsp;&nbsp;&nbsp;注 &nbsp;&nbsp;</span>
	                    <textarea class="form-control" id="comments" name="comments" placeholder="请输入备注信息" style="width:450px;height:100px;"><#if project??>${(project.comments)!""}</#if></textarea>
	                </div>
	            </div>
	        </div>
	       
	      
	        
	        
            <div class="row">	
	            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	            	<a class="btn blue" href="javaScript:void(0)" onclick="submitForm();"><i class="icon-ok"></i> 确定</a>
	       			<a class="btn black" href="javaScript:void(0)" onclick="goback();"><i class="icon-remove"></i> 取消</a>
	        	</div>
        	</div> 
        	
        	<div class="tc"></br>
	           
	       </div>
    
        </form>
    </div>
</body>
<script type="text/javascript">



function submitForm(){
	if(validate_form()){
		$("#projectInvest").val(Number($("#projectInvest").val()).toFixed(2));
		$("#editProectForm").submit();
	}
}

function goback(){
	//location="${rc.contextPath}/industrygarden/opt-query/projectlist.do";
	window.history.go(-1);
}

function validate_form(formid){
	var tt = validateFormObject();
	tt.checkLength("projectName","项目名称",true,1,40);
	tt.checkLength("ofGarden","所属园区",true,1,1);
	tt.checkLength("projectType","项目类型",true,1,1);
	tt.checkLength("projectStatus","项目状态",true,1,1);
//	tt.checkLength("projectTime","启动时间",true,1,40);
//	tt.checkLength("projectOvertime","结束时间",true,1,40);
//	tt.checkLength("projectInvest","项目金额",true,1,40);
	tt.checkNumber("projectInvest","项目金额",true,0);
	tt.checkLength("projectContent","项目内容",true,1,500);
	tt.checkLength("comments","备注信息",false,1,500);
	return tt.validate();
}

$(function () {
	/*选择器*/
	$('.form_datetime').datetimepicker({
	    language: 'zh-CN',
	    weekStart: 1,
	    todayBtn:  1,
		autoclose: 1,
		todayHighlight: 1,
		startView: 2,
		minView: 2,
		forceParse: 0
	});

	$("#startTime").click(function(){
		$('#startTime').datetimepicker('setEndDate',$("#projectOvertime").val());
	});

	$("#endTime").click(function(){
		$('#endTime').datetimepicker('setStartDate',$("#projectTime").val());
	});
	
	
});

</script>

</html>
