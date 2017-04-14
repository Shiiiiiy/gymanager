<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title></title>
    <script src="${rc.contextPath}/js/ckeditor/ckeditor.js"></script>
	<script src="${rc.contextPath}/js/ckeditor/tool.js"></script>
	
	<link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>
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
        <form class="form-inline search_list" role="form"  id="addOrEditNews" method="post" action="${rc.contextPath}/industrygarden/opt-save/savenews.do">
            <input type="hidden" id="newsId" name="id" value="${(gardenNews.id)!''}"/>
            <input type="hidden" value="${pageSize!''}" name="pageSize"/>
            <input type="hidden" value="${pageNo!''}" name="pageNo"/>
            <textarea id="newsContent" name="newsContent"> <#if gardenNews??>${(gardenNews.newsContent)!''}</#if></textarea>
            
            
            <div class="row">
	            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		            <div class="input-group">
		                <span class="input-group-addon">园区列表 <span style="color:red;">*</span></label></span>
		                <select class="form-control" onchange="changeGarden();" name="parentCode" id="parentCode" style="width:235px;">
		                    <option value="">请选择..</option>
		                    <#if mapList??>
		                    	<#list mapList as map>
				                    <#if gardenNews?? && gardenNews.parentCode??  &&  gardenNews.parentCode ==map["ID"] >
			                			<option value="${map['ID']}" selected>${(map["NAME"])!""}</option>
			                		<#else>
			                			<option value="${map['ID']}">${(map["NAME"])!""}</option>
			                		</#if>
		                    	</#list>
		                    </#if>
	                 </select>
		            </div>
		            
		            <div class="input-group" style="margin-left:33px;">
		                <span class="input-group-addon">新闻类型 <span style="color:red;">*</span></span>
		                <select class="form-control" id="moduleType"  name="mType"  onchange="changeType();" style="width:235px;border-radius:0 4px 4px 0;"> 
		                    <option value="">请选择..</option>
		                    <#if newsType??>
							<#list newsType as type>
								<#if gardenNews?? && gardenNews.moduleType?? && gardenNews.moduleType == type.code>
		                    	<option value="${(type.code)!'' }" selected="selected" >${(type.name)!''}</option>
		                    	<#else>
		                    	<option value="${(type.code)!'' }" >${(type.name)!''}</option>
		                    	</#if>
		                    </#list> 
							</#if>
		                </select>
		                <input type="hidden" name="moduleType" id="hiddenModule" />
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
	       
	        <div class="row" id="fileUploadDiv" style="display:none;">      
		        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		            <div class="input-group" id="uploadImg">
		                <span class="input-group-addon">&nbsp;&nbsp;附&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;件&nbsp;&nbsp;<span style="color:red;"></span></span>
		                <a href="javascript:void(0);"  class="btn green" id="up_btn"><i class="icon-folder-open"></i>上传</a>&nbsp;
		            </div>
		        </div>
		  </div>
	        
	        
        	<div class="row" >	
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
    	
    	changeGarden();
    	
    	$('#up_btn').click(function(){
			file.upload({
				contextPath:"${rc.contextPath}", //固定写法
				obj:this, //固定写法
				callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
				callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
				maxFileNum:5, //队列中最大文件数量
				fileType:'*.pdf;*.doc;*.docx', //可选文件类型
				fileTypeDesc:'附件文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
				
				saveLocation:'/gykjy/plugins/images/allFiles', //指定保存路径(一个TOMCAT下跑两个项目互访文件时用)
				replaceStr:'gykjy' //另一个项目的跟目录名
				}); 
		});
    	
    	
	});
	
	function callPerComplete(obj,data){
		
		var id = "";
		eval('var json = ' + data);
		if(json.result){ //&& json.pictureFile)
			if(!json.pictureFile){
				//$("#image").attr("value",json.originalfileName);//上传到数据库的  图片名
				//$("#image").attr("value",json.fileNameInServer);//上传到数据库的  图片名
				//alert(json.fileNameInServer);
				var address=json.fileRelativePath;
			    var name = json.originalfileName;
			    var url = "<a href="+address+" target='_blank'>"+name+"</a>"; 
			    setData("content",getData('content')+url);
			//	var zhi="${rc.contextPath}";
			//  $("#dbimage").val(address.substring(zhi.length)); //上传到数据库的  图片地址
		    //  $("#showimg").css("display","block");
			//	$("#theimg").attr("src",json.fileRelativePath); //预览功能
			}
		}else{
		}
	}
	function callbackAllComplete(obj){
		
	}
	
	function changeType(){
	    var type = $("#moduleType").find("option:selected").text();
		if(type.indexOf("政策")>=0){
			$("#fileUploadDiv").css('display','block');
		}else{
			$("#fileUploadDiv").css('display','none');
		}
		
	}
	function changeGarden(){
		 var garden = $("#parentCode").val();
		 if(garden=='IG_C000'){
			 $("#moduleType").val("GARDEN_A");    //如果为主园区，只能是园区动态
			 $('#moduleType').attr("disabled","disabled");
		 }else{
			 $('#moduleType').removeAttr("disabled");
		 }
		 changeType();
	}
	
	
	
	
	
	
	//取消
	function goBacks(){
		//window.location.href="${rc.contextPath}/industrygarden/opt-query/newslist.do";
		window.history.go(-1);
	}
	
	//提交角色信息
	function save(){
	    if(validate_form("addOrEditNews")){
	    	$("#hiddenModule").val($("#moduleType").val());
	    	var text= getData('content');
	    	$("#newsContent").val(text);
	        $("#addOrEditNews").attr("action","${rc.contextPath}/industrygarden/opt-save/savenews.do")
            $("#addOrEditNews").submit();
	    }
	}
	
	
	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkLength("newsTitle","标题",true,1,100);
    		tt.checkLength("parentCode","所属园区",true,1,1);
    		tt.checkLength("mType","新闻类型",true,1,1);
    		return tt.validate();
    }
	
    	
</script>
</html>
