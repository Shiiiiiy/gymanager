<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/common.ftl" >
	    <title>企业图片</title>
	    <link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
		<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
		<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>  
	</head>
	<body onload="IFrameResize()">
		<div class="content-box">
			<form action="">
				<div class="btn-group">
					<a class="btn black" href="javaScript:void(0)" onclick="uploadSource()"><i class="icon-cloud"></i> 上传</a>
				</div>
				<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
				<input type="hidden" id="companyId" value="${(companyId)!''}"   name="id" class="form-control" ></input>
				<div class="row">
					<#if fileList??>
						<#list fileList as file>
							<#if file.fileType?? >
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<div class="thumb-box">
									<img id="img" src="${file.filePath}" class="img-responsive" alt="" />
									<h3 class="f14 tc" title="${(file.fileTitle)!''}">${(file.fileTitle)!"" }&nbsp;</h3>
									<p class="tc img-btn">
										<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
										<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
									</p>
								</div>
							</div>	
							</#if>
						</#list>
					</#if>
				</div>
				<div id="deleteDiv" style="display:none;">确认删除？</div>
				<div id="editDiv" style="display:none;">
            	<div class="row">
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">图片标题</span>
	                        <input type="text" class="form-control" id="fileTitle" maxlength="10" placeholder="请输入图片标题" >
	                    </div>
	                </div>
	                <!-- <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">目标路径</span>
	                        <input type="text" class="form-control" id="url" maxlength="500" placeholder="请输入目标路径,如https://www.baidu.com/">
	                    </div>
	                </div> -->
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
			</form>
		</div>
	</body>
	<script type="text/javascript">
	
		function uploadSource(t){
			var companyId =  $("#companyId").val();
			if(companyId==""||companyId==null){
				custom_alert('请选择先保存基本信息',3,2000);
				return ;
			}
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
			eval('var json = ' + data);
			if(json.result){ 
				window.location.href="${rc.contextPath}/companyManage/saveImageCompany?fileName="+json.fileNameInServer+"&companyId="+$("#companyId").val();
			}
		}
		function callbackAllComplete(obj){
			
		}
		
		function editFile(fileId,fileTitle,url,urlType){
			$("input[name='urlType'][value='" + urlType + "']").attr("checked", true);
			showDialog("系统提示","editDiv","sure('"+fileId+"')","cancel()","closeDialog()",100,true);
			$("#fileTitle").attr("value",fileTitle);
			$("#url").attr("value",url);
		}
		
		function deleteFile(fileId){
			showDialog("系统提示","deleteDiv","sure1('"+fileId+"')","cancel()","closeDialog()",100,true);
		}
		
		function sure(fileId){
			var urlType = $("input[name='urlType']:checked").val();
			if(urlType == undefined || urlType == "undefined" || urlType == "null" || urlType == "NULL"){
				urlType = "";
			}
			window.location.href="${rc.contextPath}/companyManage/updateImageCompany.do?fileId="+fileId+"&fileTitle="+encodeURI(encodeURI($("#fileTitle").val()))+"&url="+encodeURI(encodeURI($("#url").val()))+"&urlType="+urlType+"&companyId="+$("#companyId").val();
		}
		
		function sure1(fileId){
			window.location.href="${rc.contextPath}/companyManage/deleteFile.do?fileId="+fileId+"&companyId="+$("#companyId").val();
		}
		
		
		function IFrameResize(){  
			 var obj = parent.document.getElementById("companyImageIframe");  //取得父页面IFrame对象  
			 obj.height = this.document.body.scrollHeight;  //调整父页面中IFrame的高度为此页面的高度  
		} 
	</script>
</html>