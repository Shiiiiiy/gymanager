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
        <form class="form-inline search_list" role="form"  id="addOrEditModel" method="post" action="${rc.contextPath}/dynamicdata/opt-save/saveMarketCompanies.do?modelType=${modelType}">
            <input type="hidden" id="id" name="id" value="${(vo.id)!''}"/>
            <input type="hidden" name="pageNo" value="${(pageNo)!''}"/>
            <input type="hidden" name="pageSize" value="${(pageSize)!''}"/>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;企业名称<span style="color:red;">*</span>&nbsp;&nbsp;</span>
		                <input type="text" class="form-control" id="compName" name="compName" placeholder="请输入企业名称" style="width:250px;" value="${(vo.compName)!''}" >
		            </div>
		        </div>
            </div>
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;行业类别&nbsp;&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="ofIndustry" name="ofIndustry" placeholder="请输入行业类别" style="width:250px;" value="${(vo.ofIndustry)!''}">
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">主板/新三板&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="market" name="market" placeholder="请输入主板/新三板" style="width:250px;" value="${(vo.market)!''}">
		            </div>
		        </div>
	        </div> 
	        
	        <div class="row">
		        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;交&nbsp;易&nbsp;所&nbsp;&nbsp;&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="bourse" name="bourse" placeholder="请输入交易所" style="width:250px;" value="${(vo.bourse)!''}">
		            </div>
		        </div>
	        </div>
	        
	        <div class="row">
	        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group news-time">
	                    <span class="input-group-addon">&nbsp;&nbsp;&nbsp;上市时间&nbsp;&nbsp;&nbsp;</span>
	                    <div class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
	                        <input size="" type="text" value="${((vo.marketTime)?string('yyyy-MM-dd'))!''}" id="marketTime" name="marketTime" placeholder="请选择上市时间" readonly class="form-control" style="width:250px;">
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
		                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;联&nbsp;系&nbsp;人&nbsp;&nbsp;&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="linkman" name="linkman" placeholder="请输入联系人" style="width:250px;" value="${(vo.linkman)!''}">
		            </div>
		        </div>
	        </div>
	        
	        <div class="row">
	        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group news-time">
	                    <span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;话&nbsp;&nbsp;&nbsp;&nbsp;</span>
	                    <input type="text" maxlength="32" class="form-control" id="tel" name="tel" placeholder="请输入电话" style="width:250px;" value="${(vo.tel)!''}">
	                </div>
            	</div>
            	
            </div>
	        
	        <div class="row">
		        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;&nbsp;&nbsp;网&nbsp;&nbsp;&nbsp;&nbsp;址&nbsp;&nbsp;&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="webSite" name="webSite" placeholder="请输入网址" style="width:250px;" value="${(vo.webSite)!''}">
		            </div>
		        </div>
	        </div>
	        
	        
	        <div class="row">
            	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon" style="width:87px!important;">&nbsp;&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span>&nbsp;&nbsp;</span>
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
	        $("#addOrEditModel").attr("action","${rc.contextPath}/dynamicdata/opt-save/saveMarketCompanies.do?modelType=${modelType}");
            $("#addOrEditModel").submit();
	    }
	}
	
	
	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkLength("compName","企业名称",true,1,32);
    		return tt.validate();
    }
    	
</script>
</html>
