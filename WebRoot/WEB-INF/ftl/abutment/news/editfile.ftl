<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title></title>
    <script src="${rc.contextPath}/js/ckeditor/ckeditor.js"></script>
	<script src="${rc.contextPath}/js/ckeditor/tool.js"></script>
</head>
<style>
.cke_reset{
	width:900px!important;
}
.textbox div{
	margin-bottom:0!important;
}
</style>
<body>
   
    <div class="content-box">
        <!--form-->
        <div class="page-header">
        	<h2>
        			<#if file?? >
	        				编辑
	        		<#else>
	        				新增
	        		</#if>
        	    <small class="font-en caps">
        		</small>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addOrEditNewsAll" method="post" action="${rc.contextPath}/abutmentfile/opt/save">
            <input type="hidden" id="newsId" name="id" value="${(file.id)!''}"/>
            <input type="hidden" id="pageNo" name="pageNo" value="${pageNo!""}" />
            <input type="hidden" id="pageSize" name="pageSize" value="${pageSize!""}" />
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题&nbsp;<span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="fileTitle"  maxlength="20"  name="fileTitle" placeholder="请输入标题名称" style="width:600px;" value="${(file.fileTitle)!""}" >
		            </div>
		        </div>
            </div>
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;链&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;接&nbsp;<span style="color:red;">*</span></span>
		                <input type="text" maxlength="200" class="form-control" id="url" name="url" placeholder="请输入链接地址" style="width:600px;" value="${(file.url)!''}">
		            </div>
		        </div>
	        </div>  
	        
        	<div class="row">	
	            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	            	<a class="btn blue" href="javaScript:void(0)" onclick="save();"><i class="icon-ok"></i> 确认</a>
	       			<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
	        	</div>
        	</div>
        	
        	<div class="tc"></br>
	           
	       </div>
    
        </form>
    </div>
</body>
<script type="text/javascript">

//提交
function save(){
    if(validate_form("addOrEditNewsAll")){
    	var text= getData('content');
    	$("#newsContent").val(text);
        //$("#addOrEditNewsAll").attr("action","${rc.contextPath}/abutmentfile/opt/save");
        $("#addOrEditNewsAll").submit();
    }
}

//取消
function goBacks(){
	window.location.href="${rc.contextPath}/abutment/query/index?tab=1&moduleCode=${(moduleCode)!''}&pageNo=${pageNo!""}&pageSize=${pageSize!""}";
}

function validate_form(formid){
		var tt = validateFormObject();
		tt.checkLength("fileTitle","标题",true,1,100);
		tt.checkLength("url","链接",true,1,200);
		return tt.validate();
}
    	
</script>
</html>
