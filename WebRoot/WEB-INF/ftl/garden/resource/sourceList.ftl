<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>新闻管理</title>
    <link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>  
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>资源文件<small class="font-en caps"></small></h2></div>
        
 		  <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="">

       		<div class="row" >
	        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">园区列表</label></span>
		                <select class="form-control" name="parentCode" id="parentCode" style="width:180px;" onchange="changeCode();">
		                    <#if mapList??>
		                    	<#list mapList as map>
				                    <#if parentCode?? && parentCode ==map["ID"] >
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
  
       
        <div class="tabfix">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist" >
                <li role="presentation" id="li1"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" onclick="changeTab1()"/>轮播</a>
                <li role="presentation" id="li2"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="changeTab2()"/>运行分析图</a>
                <#if !(parentCode?? && parentCode =="IG_C000") ><!-- 不为主园区才显示后面两个tab项 -->
                <li role="presentation" id="li3"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab" onclick="changeTab3()"/>运行分析报告</a>
                <li role="presentation" id="li4"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab" onclick="changeTab4()"/>龙头企业</a>
                </#if>
            </ul>
            
            <div class="tab-content">
            	
                <div role="tabpanel" id="tab1" class="tab-pane">
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('${slider}')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file['FILE_TYPE']?? && file['FILE_TYPE'] == slider>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="/gykjy/plugins/images/allImages/${file['FILE_NAME']}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file['FILETITLE'])!''}">${(file['FILE_TITLE'])!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file['ID'])!''}','${(file['FILE_TITLE'])!''}','${(file['URL'])!''}','${(file['URL_TYPE'])!''}','${(file['SORT'])!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file['ID'])!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
								</#if>
							</#list>
						</#if>
					</div>
                </div>
                <div role="tabpanel" id="tab2" class="tab-pane">
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('${statistics}')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X315像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file['FILE_TYPE']?? && file['FILE_TYPE'] == statistics>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="/gykjy/plugins/images/allImages/${file['FILE_NAME']}" class="img-responsive" alt="" />  
										<h3 class="f14 tc" title="${(file['FILE_TITLE'])!'' }">${(file['FILE_TITLE'])!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file['ID'])!''}','${(file['FILE_TITLE'])!''}','${(file['URL'])!''}','${(file['URL_TYPE'])!''}','${(file['SORT'])!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file['ID'])!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
								</#if>
							</#list>
						</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab3" class="tab-pane">
	            	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSourceFile('${file_a}')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 请上传文件</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file['FILE_TYPE']?? && file['FILE_TYPE'] == file_a>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
								<!--	<img id="img" src="/gykjy/plugins/images/allImages/${file['FILE_NAME']}" class="img-responsive" alt="" />   -->
										<img id="img" src="/gymanager/images/files.png" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file['FILETITLE'])!''}">${(file['FILE_TITLE'])!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file['ID'])!''}','${(file['FILE_TITLE'])!''}','${(file['URL'])!''}','${(file['URL_TYPE'])!''}','${(file['SORT'])!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file['ID'])!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
								</#if>
							</#list>
						</#if>
					</div>
	            </div>
                
	            <div role="tabpanel" id="tab4" class="tab-pane">
	            	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('${comp_a}')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file['FILE_TYPE']?? && file['FILE_TYPE'] == comp_a>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="/gykjy/plugins/images/allImages/${file['FILE_NAME']}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file['FILETITLE'])!''}">${(file['FILE_TITLE'])!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file['ID'])!''}','${(file['FILE_TITLE'])!''}','${(file['URL'])!''}','${(file['URL_TYPE'])!''}','${(file['SORT'])!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file['ID'])!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
								</#if>
							</#list>
						</#if>
					</div>
	            </div>
	            
                
                
            </div>
            <div id="deleteDiv" style="display:none;">确认删除？</div>
            <div id="editDiv" style="display:none;">
            	<div class="row">
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10">
	                    <div class="input-group">
	                        <span class="input-group-addon">图片标题&nbsp; </span>
	                        <input type="text" class="form-control" id="fileTitle" placeholder="请输入图片标题" >
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">资源序号<span style="color:red;">*</span></span>
	                        <input type="text" class="form-control" id="sort" placeholder="请输入资源序号">
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:15px;" id="turl">
	                    <div class="input-group">
	                        <span class="input-group-addon">目标路径&nbsp; </span>
	                        <input type="text" class="form-control" id="url" placeholder="请输入目标路径,如https://www.baidu.com/">
	                    </div>
	                </div>
	                <!--
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">路径类型&nbsp; </span>
	                        <label class="input-group-addon gray-bg" style="width:46px;">
				            	<input type="radio" aria-label="..." value="URL_A" name="urlType" >
				            </label>
					        <label class="form-control">站内链接</label>
					        <label class="input-group-addon gray-bg" style="width:46px;">
				            	<input type="radio" aria-label="..." value="URL_B" name="urlType" >
				            </label>
					        <label class="form-control">站外链接</label>
	                    </div>
	                </div>
	                -->
	             </div>
            </div>
            </form>
        </div>
    </div>
</body>
<script type="text/javascript">
var type = "";
var flag = "${flag}";

	$(function () {
		for(var i =1;i<=4;i++){
			if(flag == i){
				$("#li"+i).addClass("active");
				$("#tab"+i).addClass("active");
			}else{
				$("#li"+i).removeClass("active");
				$("#tab"+i).removeClass("active");
			}
		}
		
	});
	
	function changeTab2(){
		flag = "2";
	}
	
	function changeTab1(){
		flag = "1";
	}
	function changeTab3(){
		flag = "3";
	}
	function changeTab4(){
		flag = "4";
	}
		
	function uploadSource(t){//上传图片
		type = t;
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:'*.jpg;*.png;*.gif;*.bmp', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			saveLocation:'/gykjy/plugins/images/allImages', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
		}); 
	}
	function uploadSourceFile(t){//上传文件
		type = t;
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:'*.pdf;*.word;*.ppt;*.doc;*.wordx;*.pptx;*.docx', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			saveLocation:'/gykjy/plugins/images/allFiles', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
		}); 
	}
	
	function callPerComplete(obj,data){
		var id = "";
		var name = "";
		eval('var json = ' + data);
		if(json.result){ //&& json.pictureFile)
			//将返回的地址信息存入数据库
			window.location.href="${rc.contextPath}/industrygarden/opt-save/saveresource?fileName="+json.fileNameInServer+"&fileType="+type+"&flag="+flag+"&parentCode="+$("#parentCode").val();
		}
	}
	function callbackAllComplete(obj){
	}
	
	function editFile(fileId,fileTitle,url,urlType,sort){
		$("input[name='urlType'][value='" + urlType + "']").attr("checked", true);
		showDialog("系统提示","editDiv","sure('"+fileId+"')","cancel()","closeDialog()",100,true);
		var zhi=$("#parentCode").val();
		if(flag=="2" && zhi=="IG_C000"){//运行分析图 
			$("#turl").css("display","none");
		}
		if(flag=="3" && zhi!="IG_C000"){//运行分析报告 
			$("#turl").css("display","none");
		}
		$("#fileTitle").attr("value",fileTitle);
		$("#url").attr("value",url);
		$("#sort").attr("value",sort);
	}
	
	function deleteFile(fileId){
		showDialog("系统提示","deleteDiv","sure1('"+fileId+"')","cancel()","closeDialog()",100,true);
	}
	
	function sure(fileId){
		var tt = validateFormObject();
		tt.checkNumber("sort","资源序号",true,1,999);
		tt.checkLength("fileTitle","图片标题",false,1,20); tt.checkLength("url","目标路径",false,1,500);
		if(tt.validate()){
			var urlType = $("input[name='urlType']:checked").val();
			if(urlType == undefined || urlType == "undefined" || urlType == "null" || urlType == "NULL"){
				urlType = "";
			}
			window.location.href="${rc.contextPath}/industrygarden/opt-update/updateFileTitle.do?fileId="+fileId+"&fileTitle="+encodeURI(encodeURI($("#fileTitle").val()))+"&url="+encodeURI(encodeURI($("#url").val()))+"&urlType="+urlType+"&flag="+flag+"&parentCode="+$("#parentCode").val()+"&sort="+$("#sort").val();
		}else{
			$("#selUserDislogDiv_editDiv").fadeIn();
		}
		
		

	}
	
	function sure1(fileId){
		window.location.href="${rc.contextPath}/industrygarden/opt-delete/deleteFile.do?fileId="+fileId+"&flag="+flag+"&parentCode="+$("#parentCode").val();
	}
	
	function cancel(){
		$("input[name='urlType']").removeAttr("checked");
	}
	
	function closeDialog(){
		$("input[name='urlType']").removeAttr("checked");
	}
	
	
	function changeCode(){
		//切换模块刷新页面
		window.location.href="${rc.contextPath}/industrygarden/opt-query/resource.do?parentCode="+$("#parentCode").val()
	}
	
	
</script>
</html>
