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
        <form class="form-inline search_list" role="form"  id="addOrEditModel" method="post" action="${rc.contextPath}/dynamicdata/opt-save/saveInnovBusiness.do?modelType=${modelType}">
            <input type="hidden" id="id" name="id" value="${(vo.id)!''}"/>
            <input type="hidden" name="pageNo" value="${(pageNo)!''}"/>
            <input type="hidden" name="pageSize" value="${(pageSize)!''}"/>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;企业名称<span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="name" name="name" placeholder="请输入企业名称" style="width:300px;" value="${(vo.name)!''}" >
		            </div>
		        </div>
            </div>
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;行业类别<span style="color:red;">&nbsp;</span></span>
		                <input type="text" maxlength="32" class="form-control" id="belong" name="belong" placeholder="请输入行业类别" style="width:300px;" value="${(vo.belong)!''}">
		            </div>
		        </div>
	        </div>  
	        

	        
	        <div class="row">
		        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;业绩规模&nbsp;</span>
		                <input type="text" maxlength="15" class="form-control" id="scale" name="scale" placeholder="请输入业绩规模(元人民币)" style="width:300px;" value="${(vo.scale)!''}">
		            </div>
		        </div>
	        </div>
	        
	        <div class="row">
	        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group news-time">
	                    <span class="input-group-addon">&nbsp;创立时间&nbsp;</span>
	                    <div class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
	                        <input size="" type="text" value="${((vo.startTime)?string('yyyy-MM-dd'))!''}" id="startTime" name="startTime" placeholder="请选择创立时间" readonly class="form-control" style="width:300px;">
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
		                <span class="input-group-addon">&nbsp;&nbsp;联&nbsp;系&nbsp;人&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="linkman" name="linkman" placeholder="请输入联系人" style="width:300px;" value="${(vo.linkman)!''}">
		            </div>
		        </div>
	        </div>  
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话&nbsp;&nbsp;</span>
		                <input type="text" maxlength="11" class="form-control" id="phone" name="phone" placeholder="请输入电话" style="width:300px;" value="${(vo.phone)!''}">
		            </div>
		        </div>
	        </div>  
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;网&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;址&nbsp;&nbsp;</span>
		                <input type="text" maxlength="50" class="form-control" id="url" name="url" placeholder="请输入网址" style="width:300px;" value="${(vo.url)!''}">
		            </div>
		        </div>
	        </div>  
	        <div class="row">
            	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon" style="width:87px!important;">&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span></span>
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
	    	if($("#scale").val()){
	    		$("#scale").val(Number($("#scale").val()).toFixed(0));
	    	}
	        $("#addOrEditModel").attr("action","${rc.contextPath}/dynamicdata/opt-save/saveInnovBusiness.do?modelType=${modelType}");
            $("#addOrEditModel").submit();
	    }
	}
	
	
	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkNumber("scale","业绩规模",false,0);
    		tt.checkLength("name","企业名称",true,1,32);
    		return tt.validate();
    }
    	
</script>
</html>
