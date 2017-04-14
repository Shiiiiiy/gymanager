<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>资源管理</title>
    <link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>  
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>资源文件<small class="font-en caps"></small></h2></div>
        <div class="tabfix" style="border-top:0px solid #cecece">
	    	<div class="btn-group" style="">
				<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('FILE_SLIDER','MODULE_I')"><i class="icon-cloud"></i> 上传</a>
			</div>
			<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
			<div class="row">
			<#if fileList??>
				<#list fileList as file>
					<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
						<div class="thumb-box">
							<img id="img" src="${file.filePath}" class="img-responsive" alt="" />
							<h3 class="f14 tc" title="${(file.fileTitle)!''}">${(file.fileTitle)!"" }&nbsp;</h3>
							<p class="tc img-btn">
								<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}','${(file.sort)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
								<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
							</p>
						</div>
					</div>	
				</#list>
			</#if>
			</div>
            
            <div id="deleteDiv" style="display:none;">确认删除？</div>
            <div id="editDiv" style="display:none;">
            	<div class="row">
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">图片标题<span style="color:red;">&nbsp;</span></span>
	                        <input type="text" class="form-control" id="fileTitle" placeholder="请输入图片标题">
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">资源序号<span style="color:red;">*</span></span>
	                        <input type="text" class="form-control" id="sort" placeholder="请输入资源序号">
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;" id="turl">
	                    <div class="input-group">
	                        <span class="input-group-addon">目标路径<span style="color:red;">&nbsp;</span></span>
	                        <input type="text" class="form-control" id="url" placeholder="请输入目标路径,如https://www.baidu.com/">
	                    </div>
	                </div>
	                <!--
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10">
	                    <div class="input-group">
	                        <span class="input-group-addon">路径类型</span>
	                        <label class="input-group-addon gray-bg" style="width:46px;">
				            	<input type="radio" aria-label="..." value="URL_A" name="urlType" >
				            </label>
					        <label class="form-control">站内链接</label>
					        <label class="input-group-addon gray-bg" style="width:46px;">
				            	<input type="radio" aria-label="..." value="URL_B" name="urlType" >
				            </label>
					        <label class="form-control">站外链接</label>
	                    </div>
	                </div>-->
	             </div>
            </div>
            
        </div>
    </div>
</body>
<script type="text/javascript">

	function uploadSource(t,p){

		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:"*.jpg;*.jpeg;*.png", //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			saveLocation:'${saveLocation}', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
		}); 
	}
	
	function callPerComplete(obj,data){
		var id = "";
		var name = "";
		eval('var json = ' + data);
		if(json.result){
			//将返回的地址信息存入数据库
			$.ajax({
				url	 :"${rc.contextPath}/policyresource/opt/save",
				type :"post",
				async:false,
				data :{
					fileName : json.fileNameInServer
				},
				success:function(data){
					//do nothing
				}
			});
		}
	}
	
	function callbackAllComplete(obj){
		window.location.href="${rc.contextPath}/policyresource/query/list";
	}
	
	function editFile(fileId,fileTitle,url,urlType,sort){
		$("input[name='urlType'][value='" + urlType + "']").attr("checked", true);
		showDialog("系统提示","editDiv","sure('"+fileId+"')","cancel()","closeDialog()",100,true);
		$("#fileTitle").attr("value",fileTitle);
		$("#url").attr("value",url);
		$("#sort").attr("value",sort);
	}
	
	function deleteFile(fileId){
		showDialog("系统提示","deleteDiv","sure1('"+fileId+"')","cancel()","closeDialog()",100,true);
	}
	
	function sure(fileId){
		var urlType = $("input[name='urlType']:checked").val();
		if(urlType == undefined || urlType == "undefined" || urlType == "null" || urlType == "NULL"){
			urlType = "";
		}
		var tt = validateFormObject();
		tt.checkNumber("sort","资源序号",true,1,999);
		tt.checkLength("fileTitle","图片标题",false,1,20); 
		tt.checkLength("url","目标路径",false,1,500);
		if(tt.validate()){
			$.ajax({
				url:"${rc.contextPath}/policyresource/opt/update",
				type:"post",
				data : {
					fileId : fileId,
					fileTitle : $("#fileTitle").val(),
					url : $("#url").val(),
					sort: $("#sort").val(),
					urlType : urlType
				},
				async:false,
				success:function(){
					window.location.href="${rc.contextPath}/policyresource/query/list";
				}
			});
			showDialog_close("editDiv");
		}
	}
	
	function sure1(fileId){
		window.location.href="${rc.contextPath}/policyresource/opt/delete?id="+fileId;
	}
	
	function cancel(){
		$("input[name='urlType']").removeAttr("checked");
	}
	
	function closeDialog(){
		$("input[name='urlType']").removeAttr("checked");
	}

</script>
</html>
