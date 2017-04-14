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
        	        <#if vo?? >
	        			编辑
	        		<#else>
	        			新增
	        		</#if>
        	    <small class="font-en caps">
	        		
        		</small>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addOrEditModel" method="post" action="${rc.contextPath}/dynamicdata/opt-save/saveInnovatePlatForm.do?modelType=${modelType}">
            <input type="hidden" id="id" name="id" value="${(vo.id)!''}"/>
            <input type="hidden" name="pageNo" value="${(pageNo)!''}"/>
            <input type="hidden" name="pageSize" value="${(pageSize)!''}"/>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;平台名称<span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="platName" name="platName" placeholder="请输入平台名称" style="width:300px;" value="${(vo.platName)!''}" >
		            </div>
		        </div>
            </div>
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;平台类型&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="platType" name="platType" placeholder="请输入平台类型" style="width:300px;" value="${(vo.platType)!''}">
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;投资主体&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="investor" name="investor" placeholder="请输入投资主体" style="width:250px;" value="${(vo.investor)!''}">
		            </div>
		        </div>
	        </div> 
	        
	        <div class="row">
		        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;平台级别&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="platLevel" name="platLevel" placeholder="请输入平台级别" style="width:250px;" value="${(vo.platLevel)!''}">
		            </div>
		        </div>
	        </div>
	        
	        <div class="row">
	        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group news-time">
	                    <span class="input-group-addon">&nbsp;&nbsp;成立时间&nbsp;&nbsp;</span>
	                    <div class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
	                        <input size="" type="text" value="${((vo.platTime)?string('yyyy-MM-dd'))!''}" id="platTime" name="platTime" placeholder="请选择成立时间" readonly class="form-control" style="width:250px;">
	                        <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
	                        <span class="add-on"><i class="icon-th iconfont"></i></span>
	                    </div>
	                    <input type="hidden" id="dtp_input1" value="" /><br />
	                </div>
            	</div>
	        </div>
	        
	        <div class="row">
            	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon" style="width:87px!important;">&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span>&nbsp;</span>
	                    <#if statusList??>
						<#list statusList as d>
		                    <label class="input-group-addon gray-bg" style="width:46px;">
			                    <#if vo??>
		                        	<#if  vo.status==d.id>
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
	var X = $('.news-time').offset().top;
	$('.datetimepicker').css("top", X);
});
	
	//取消
	function goBacks(){
		window.location.href="${rc.contextPath}/dynamicdata/opt-query/modelList.do?modelType=${modelType}";
	}
	
	//提交角色信息
	function save(){
	    if(validate_form("addOrEditModel")){
	        $("#addOrEditModel").attr("action","${rc.contextPath}/dynamicdata/opt-save/saveInnovatePlatForm.do?modelType=${modelType}");
            $("#addOrEditModel").submit();
	    }
	}
	
	
	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkLength("platName","平台名称",true,1,32);
    		return tt.validate();
    }
    	
</script>
</html>
