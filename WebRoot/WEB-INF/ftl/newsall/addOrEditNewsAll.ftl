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
        	        <#if newsAll?? >
	        			编辑
	        		<#else>
	        			新增
	        		</#if>
        	    <small class="font-en caps">
	        		
        		</small>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addOrEditNewsAll" method="post" action="${rc.contextPath}/newsall/opt-save/saveNewsAll.do">
            <input type="hidden" id="newsId" name="id" value="${(newsAll.id)!''}"/>
            <input type="hidden" name="pageNo" value="${(pageNo)!''}"/>
            <input type="hidden" name="pageSize" value="${(pageSize)!''}"/>
            <textarea id="newsContent" name="newsContent"> <#if newsAll??>${(newsAll.newsContent)!''}</#if></textarea>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题&nbsp;<span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="newsTitle" name="newsTitle" placeholder="请输入标题" style="width:600px;" value="${(newsAll.newsTitle)!''}" >
		            </div>
		        </div>
            </div>
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源&nbsp;&nbsp;</span>
		                <input type="text" maxlength="200" class="form-control" id="newsSource" name="newsSource" placeholder="请输入来源" style="width:600px;" value="${(newsAll.newsSource)!''}">
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;介&nbsp;&nbsp;</span>
		                <textarea class="form-control" maxlength="200" id="introduce" name="introduce" placeholder="请输入简介" style="width:600px;height:100px;">${(newsAll.introduce)!""}</textarea>
		            </div>
		        </div>
	        </div> 
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon" style="width:87px!important;">&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span>&nbsp;</span>
	                    <#if statusList??>
						<#list statusList as d>
		                    <label class="input-group-addon gray-bg" style="width:46px;">
			                    <#if newsAll??>
		                        	<#if  newsAll.status==d.id>
		                        		<input type="radio" aria-label="..." value="${(d.id)!""}"  checked name="status" >
		                        	<#else>
		                        		<input type="radio" aria-label="..." value="${(d.id)!""}" name="status" >
		                        	</#if>
		                        <#else>
		                        	<#if d.id==enable.id>
		                        		<input type="radio" aria-label="..." value="${(d.id)!""}"  checked name="status" >
		                        	<#else>
		                    			<input type="radio" aria-label="..." value="${(d.id)!""}" name="status" >
		                        	</#if>
		                        </#if>
		                    </label>
		                    <label class="form-control">${(d.name)!""}</label>
                        </#list> 
						</#if>
	                </div>
	            </div>
	        </div>
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-8">
	                <div class="input-group w textbox">
	                    <span class="input-group-addon" style="width:87px;height:282px;">&nbsp;&nbsp;内&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;容&nbsp;&nbsp;</span>
	                    <textarea name="content" id="content" style="height:400px;" placeholder="请输入内容" > </script></textarea>
	                </div>
	            </div>
	        </div>
	       
        	<div class="row">	
	            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	            	<a class="btn blue" href="javaScript:void(0)" onclick="save();"><i class="icon-ok"></i> 确定</a>
	       			<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
	        	</div>
        	</div>
        	
        	<div class="tc"></br>
	           
	       </div>
    
        </form>
    </div>
</body>
<script type="text/javascript">
	$(function(){
		$("#newsContent").css("display","none");
    	editorText('content');  
    	setData("content",$("#newsContent").val());
	});
	
	//取消
	function goBacks(){
		window.location.href="${rc.contextPath}/newsall/opt-query/newsAllList.do?moduleCode=${(moduleCode)!''}";
	}
	
	//提交角色信息
	function save(){
	    if(validate_form("addOrEditNewsAll")){
	    	var text= getData('content');
	    	$("#newsContent").val(text);
	        $("#addOrEditNewsAll").attr("action","${rc.contextPath}/newsall/opt-save/saveNewsAll.do?moduleCode=${(moduleCode)!''}");
            $("#addOrEditNewsAll").submit();
	    }
	}
	
	
	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkLength("newsTitle","标题",true,1,100);
    		return tt.validate();
    }
    	
</script>
</html>
