<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
	<link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>  
    <title></title>
</head>
<body>
   
    <div class="content-box">
        <!--form-->
        <div class="page-header">
        	<h2>
        	        <#if person?? >
	        			编辑
	        		<#else>
	        			新增
	        		</#if>
        	    <small class="font-en caps">
	        		
        		</small>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addOrEditPerson" method="post" action="${rc.contextPath}/ilperson/opt-save/saveIlPerson.do">
            <input type="hidden" id="personId" name="id" value="${(person.id)!''}"/>
            <input type="hidden" name="pageNo" value="${(pageNo)!''}"/>
            <input type="hidden" name="pageSize" value="${(pageSize)!''}"/>
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;姓&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;名&nbsp;<span style="color:red;">*</span></span>
		                <input type="text" maxlength="32" class="form-control" id="psName" name="psName" placeholder="请输入人物名称" style="width:300px;" value="${(person.psName)!''}" >
		            </div>
		        </div>
            </div>
            
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;电&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;话&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="psTel" name="psTel" placeholder="请输入联系电话" style="width:300px;" value="${(person.psTel)!''}" >
		            </div>
		        </div>
            </div>
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;职&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;务&nbsp;&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="psJob" name="psJob" placeholder="请输入职务" style="width:300px;" value="${(person.psJob)!''}">
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;所在单位&nbsp;</span>
		                <input type="text" maxlength="32" class="form-control" id="psBelong" name="psBelong" placeholder="请输入所在单位" style="width:300px;" value="${(person.psBelong)!''}">
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">&nbsp;&nbsp;简&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;介&nbsp;&nbsp;</span>
		                <textarea class="form-control" maxlength="2000" id="psIntroduce" name="psIntroduce" placeholder="请输入简介" style="width:600px;height:100px;">${(person.psIntroduce)!""}</textarea>
		            </div>
		        </div>
	        </div> 
	        
	        <div class="row">      
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	                    <div class="input-group" id="uploadImg">
	                        <span class="input-group-addon">&nbsp;&nbsp;头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像&nbsp;&nbsp;</span>
	                        <input type="text" class="form-control"   id="spsImage" name="spsImage" value="${(person.comments)!''}" placeholder="请上传头像" style="width:230px;" readonly="readonly"/>
	                        <input type="hidden" class="form-control" id="psImage" name="psImage"  value="${(person.psImage)!''}"  />
	                        <input type="hidden" class="form-control" id="comments" name="comments"  value="${(person.comments)!''}"  />
	                        <a href="javascript:void(0);"  class="btn green" onclick="uploadSource()"><i class="icon-folder-open"></i>上传</a>&nbsp;<span style="color:red" >* 最佳尺寸130*177像素</span>
	                    </div>
	                </div>
			  </div> 
			  
			  <div class="row" id="showimg" 
			  <#if (person.imageUrl)?? && ((person.imageUrl)+"")!="">
			  style="display:block;"
			  <#else>
			  style="display:none;"
			  </#if> >  
	               <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			  			<img src="${(person.imageUrl)!''}" id="theimg" class="mr10 fl" width="98px;" heigth="130px;"/>
			  	   </div>
		      </div>
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon" style="width:87px!important;">&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span>&nbsp;</span>
	                    <#if statusList??>
						<#list statusList as d>
		                    <label class="input-group-addon gray-bg" style="width:46px;">
			                    <#if person??>
		                        	<#if  person.status==d.id>
		                        		<input type="radio" aria-label="..." value="${(d.id)!''}"  checked name="status" >
		                        	<#else>
		                        		<input type="radio" aria-label="..." value="${(d.id)!''}" name="status" >
		                        	</#if>
		                        <#else>
		                        	<#if d.id==enable.id>
		                        		<input type="radio" aria-label="..." value="${(d.id)!''}"  checked name="status" >
		                        	<#else>
		                    			<input type="radio" aria-label="..." value="${(d.id)!''}"  name="status" >
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
	
	//取消
	function goBacks(){
		window.location.href="${rc.contextPath}/ilperson/opt-query/ilList.do?type=${(type)!''}";
	}
	
	//提交角色信息
	function save(){
	    if(validate_form("addOrEditPerson")){
	        $("#addOrEditPerson").attr("action","${rc.contextPath}/ilperson/opt-save/saveIlPerson.do?type=${(type)!''}")
            $("#addOrEditPerson").submit();
	    }
	}
	
	
	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkLength("psName","人物名称",true,1,32);
    		return tt.validate();
    }
	
	function uploadSource(){
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:'*.jpg;*.png;*.gif;*.bmp', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			saveLocation:'${saveLocation}', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
		}); 
	}
	
	function callPerComplete(obj,data){
		var id = "";
		var name = "";
		eval('var json = ' + data);
		if(json.result){ //&& json.pictureFile)
			//将返回的地址信息存入数据库
			$("#spsImage").val(json.originalfileName);
			$("#comments").val(json.originalfileName);
			$("#psImage").val(json.fileNameInServer);
			$("#theimg").attr("src",json.fileRelativePath);
			$("#showimg").css("display","block");
		}
	}
	function callbackAllComplete(obj){
	}
    	
</script>
</html>
