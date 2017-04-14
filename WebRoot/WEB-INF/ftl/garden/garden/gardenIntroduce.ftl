<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#include "/common/common.ftl" >
	<script src="${rc.contextPath}/js/ckeditor/ckeditor.js"></script>
	<script src="${rc.contextPath}/js/ckeditor/tool.js"></script>
	
	<link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>
	
	<style>
	.cke_reset{
		width:900px!important;
	}
	.textbox div{
		margin-bottom:0!important;
	}
	</style>
	
    <script type="text/javascript">
    	
//    window.onload = function(){
//    	CKEDITOR.replace('content');	
//    	CKEDITOR.config.width = 900;
//		CKEDITOR.config.height = 318;
//		CKEDITOR.config.skin = 'v2';
//		  
//		}
    

    $(function(){
    	editorText('content');  
		var zhi="${rc.contextPath}";
		var nowproj=zhi.substring(1,zhi.length);
		$('#up_btn').click(function(){
			file.upload({
				contextPath:"${rc.contextPath}", //固定写法
				obj:this, //固定写法
				callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
				callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
				maxFileNum:1, //队列中最大文件数量
				fileType:'*.jpg;*.png', //可选文件类型
				fileTypeDesc:'图片文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
				
				saveLocation:'/gykjy/plugins/images/allImages', //指定保存路径(一个TOMCAT下跑两个项目互访文件时用)
				replaceStr:'gykjy' //另一个项目的跟目录名
				}); 
		});

	});
	function callPerComplete(obj,data){
		var id = "";
		eval('var json = ' + data);
		if(json.result){ //&& json.pictureFile)
			if(json.pictureFile){
				//$("#image").attr("value",json.originalfileName);//上传到数据库的  图片名
				$("#image").attr("value",json.fileNameInServer);//上传到数据库的  图片名
				//alert(json.fileNameInServer);
				var address=json.fileRelativePath;
				var zhi="${rc.contextPath}";
		        $("#dbimage").val(address.substring(zhi.length)); //上传到数据库的  图片地址
		        $("#showimg").css("display","block");
				$("#theimg").attr("src",json.fileRelativePath); //预览功能
			}
		}
	}
	function callbackAllComplete(obj){
	
	}
    

    function save(){
    	var text= getData('content');
		//alert(text);
		$("#introduce").val(text);
		
		$("#editGardenIntroduceForm").submit();
    }
    
    function goback(){
    	//self.location=document.referrer;
		//location="${rc.contextPath}/industrygarden/opt-query/gardenlist";
    	window.history.go(-1);
    }
    	
    </script>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>编辑</h2></div>
        <form class="form-inline search_list" role="form"  id="editGardenIntroduceForm" method="post" action="${rc.contextPath}/industrygarden/opt-save/savegarden.do">
        <input type="hidden" value="${pageSize!''}" name="pageSize"/>
    	<input type="hidden" value="${pageNo!''}" name="pageNo"/>
        <div class="input-group">
	        <span class="input-group-addon">园区列表</label></span>
	        <select class="form-control"  style="width:200px;" disabled>
	            <option value="">请选择..</option>
	            <#if mapList??>
	            	<#list mapList as map>
	                    <#if garden?? && garden.id??  &&  garden.id==map["ID"] >
	            			<option value="${map['ID']}" selected>${(map["NAME"])!""}</option>
	            		<#else>
	            			<option value="${map['ID']}">${(map["NAME"])!""}</option>
	            		</#if>
	            	</#list>
	            </#if>
	        </select>
	    </div>
        
	    <div class="row">
	        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	            <div class="input-group w textbox">
	                <span class="input-group-addon">简介内容</span>
	               
	                <textarea  cols="12" rows="15"  class="form-control" id="content"  name="content" placeholder="请输入简介内容" >${garden.introduce!""}</textarea>
	            </div>
	        </div>
	    </div>
	    
        <input type="hidden" value="${garden.id}" name="id">
        <input type="hidden" name="introduce" id="introduce">
        
        <div class="row">      
	        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	            <div class="input-group" id="uploadImg">
	                <span class="input-group-addon">简介图片<span style="color:red;"></span></span>
	                <input type="text" class="form-control"   id="image" name="introducePic" value="${garden.introducePic!''}" placeholder="请上传图片" style="width:200px;" readonly="readonly" >
	                <input type="hidden" class="form-control" id="dbimage" name="image_url" style="width:200px;">
	                <a href="javascript:void(0);"  class="btn green" id="up_btn"><i class="icon-folder-open"></i>上传</a>&nbsp;<span style="color:red" ><#if (garden.id)?? && "IG_C000"== garden.id >* 最佳尺寸1200X450像素<#else>* 最佳尺寸330X330像素</#if></span>
	                <a href='javascript:void(0);' id="viewImg" class='btn green' onclick='javascript:void(0);' style="display:none;"><i class="icon-eye-open"></i>预览</a>
	            </div>
	            
	        </div>
	  </div>
        
	  
	  <div class="row" id="showimg" 
	  <#if  (garden.introducePic)?? && ((garden.introducePic)+"")!="">
	  style="display:block;"
	  <#else>
	  style="display:none;"
	  </#if> >  
           <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                <div style="max-height:500px;overflow:hidden;/*overflow-y:auto;*/"><img src="/gykjy/plugins/images/allImages/${(garden.introducePic)!''}" id="theimg" alt="" class="" width="40%" /></div>
	  		</div>
      </div>
        
    	<div class="row">	
	        <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	        	<a class="btn blue" href="javaScript:void(0)" onclick="save();"><i class="icon-ok"></i> 确定</a>
	   			<a class="btn black" href="javaScript:void(0)" onclick="goback();"><i class="icon-remove"></i> 取消</a>
	    	</div>
		</div> 
    	
        
        </form>
    </div>
</body>
</html>
