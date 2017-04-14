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
        	        <#if gardenNews?? >
	        			编辑
	        		<#else>
	        			新增
	        		</#if>
        	    <small class="font-en caps">
        		</small>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addOrEditNews" method="post" action="${rc.contextPath}/service/opt-save/savenews.do">
            <input type="hidden" id="newsId" name="id" value="${(gardenNews.id)!''}"/>
            <textarea id="newsContent" name="newsContent"> <#if gardenNews??>${(gardenNews.newsContent)!''}</#if></textarea>
            
                
	          <div class="row">
	            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		            <div class="input-group">
		                <span class="input-group-addon">分类名称 <span style="color:red;">*</span></label></span>
	                	<select class="form-control"  name="mCode" id="mCode" onchange="getNextClass(this.value);" style="width:235px;">
	                        <option value="">请选择..</option>
	                        <#if type0??>
	                        	<option value="N" <#if toActive?? && toActive==type0>selected="selected"</#if> >主页新闻</option>
	                        </#if>
	                        <#if type1??>
	                        	<option value="INDUSTRY_A" <#if toActive?? && toActive==type1 >selected="selected"</#if> >工业产品</option>
	                        </#if>
	                        <#if type2??>
	                        	<option value="INDUSTRY_B" <#if toActive?? && toActive==type2 >selected="selected"</#if> >生产服务</option>
	                        </#if>
	                    </select>
		            </div>
		      
		            <div class="input-group" style="margin-left:33px;">
		                <span class="input-group-addon">新闻位置  <span style="color:red;">*</span></span>
	                    <select class="form-control"  name="parentCode" id="ServiceClass" style="width:235px;border-radius:0 4px 4px 0;"><!--依据这个查询-->
	                    	<#if mapList??>
	                    		<option value="">请选择..</option>
		                    	<#list mapList as map>
				                    <#if toActive2??  &&  toActive2==map["ID"] >
			                			<option value="${map['ID']}" selected="selected">${(map["NAME"])!""}</option>
			                		<#else>
			                			<option value="${map['ID']}">${(map["NAME"])!""}</option>
			                		</#if>
		                    	</#list>
		                    <#elseif toActive2?? && "N"==toActive2>
		                    	<option value="">请选择..</option>
		                    	<option value="N" selected="selected">主页新闻</option>
		                    <#else>	
		                    	<option value="">请选择..</option>
		                    </#if>
	                    </select>
		            </div>
		        </div>
		      </div>
	        
        
            
            
            
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;标&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;题&nbsp;<span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="newsTitle" name="newsTitle" placeholder="请输入新闻标题" style="width:600px;" value="${(gardenNews.newsTitle)!''}" >
		            </div>
		        </div>
            </div>
        
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;来&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;源&nbsp;&nbsp;</span>
		                <input type="text" maxlength="200" class="form-control" id="newsSource" name="newsSource" placeholder="请输入来源" style="width:600px;" value="${(gardenNews.newsSource)!''}">
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;介&nbsp;&nbsp;</span>
		                <textarea class="form-control" maxlength="200" id="introduce" name="introduce" placeholder="请输入简介" style="width:600px;height:100px;">${(gardenNews.introduce)!""}</textarea>
		            </div>
		        </div>
	        </div> 
	        
	         <div class="row">
	       	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	            <div class="input-group">
	                <span class="input-group-addon" style="width:87px!important;">&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span>&nbsp;</span>
	                <#if statusList??>
					<#list statusList as d>
	                    <label class="input-group-addon gray-bg" style="width:50px;">
		                    <#if gardenNews??>
	                        	<#if  gardenNews.status==d.id>
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
	                    <label class="form-control" style="width:67px;">${(d.name)!""}</label>
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
		//window.location.href="${rc.contextPath}/service/news.do";
		history.go(-1);
	}
	
	//提交角色信息
	function save(){
	    if(validate_form("addOrEditNews")){
	    	var text= getData('content');
	    	$("#newsContent").val(text);
	        $("#addOrEditNews").attr("action","${rc.contextPath}/service/opt-save/savenews.do")
            $("#addOrEditNews").submit();
	    }
	}
	
	
	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkLength("newsTitle","标题",true,1,100);
    		tt.checkLength("mCode","分类名称",true,1,100);
    		tt.checkLength("parentCode","新闻位置",true,1,100);
    		return tt.validate();
    }
	function getNextClass(O){ //查询二级 分类
        var types=O+"";
        if("N"==types){
            $("#ServiceClass").empty();
            $("#ServiceClass").append(
                    "<option value=''>请选择..</option>"+
                    "<option value='N'>主页新闻</option>"
            );
        	return;
        }
        $.post("${rc.contextPath}/service/getServiceList.do",{"type":types},function(obj) {
            $("#ServiceClass").empty();
            $("#ServiceClass").append(
                 "<option value=''>请选择..</option>"
            );
            $.each(obj,function(index,zhi){
            	
                $("#ServiceClass").append(
                    "<option value='"+zhi.ID+ "' >" + zhi.NAME+ "</option>"
                );
            });
        },"json");
    }	
</script>
</html>
