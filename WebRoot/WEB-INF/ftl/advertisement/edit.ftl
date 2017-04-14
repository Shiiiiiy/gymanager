<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/common.ftl" >
		 <title>广告列表</title>
		 <link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
		 <script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
		 <script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>
		 <style>
		 	.thumb-box .img-btn{bottom:70px;}
		 </style> 
	</head>
	<body>
		<div class="content-box">
			<div class="page-header">
	        	<h2>广告编辑<small class="font-en caps"></small></h2>
	        </div>
	        <form class="form-inline search_list" role="form"  id="editAd" method="post">
	        	<input type="hidden" id="file_type" value="${(file_type)!''}"   name="file_type" class="form-control" ></input>
	        	<input type="hidden" id="module_code" value="${(module_code)!''}"   name="module_code" class="form-control" ></input>
	        	<input type="hidden" id="parent_code" value="${(parent_code)!''}"   name="id" class="form-control" ></input>
	        	<div class="row">
		        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			            <div class="input-group">
			                <span class="input-group-addon" style="width:105px;">广告位置 </span>
			                <input type="text" readonly="readonly" class="form-control" id="adLoacName" name="adLoacName"  style="width:200px;" value="${(adLoacName)!''}" >
			            </div>
			        </div>
		        </div>
	        	<div class="btn-group">
					<a class="btn black" href="javaScript:void(0)" onclick="uploadSource()"><i class="icon-cloud"></i> 上传</a>
				</div>
				
				<div class="btn-group">
					<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-backward"></i>&nbsp;返回</a>
				</div>
				<div class="btn-group">
					<span style="color:red;" class="thehint">* 最佳尺寸224X224像素</span>
				</div>
				
		        <div class="row">
					<#if list??>
						<#list list as file>
							<#if file.fileType?? >
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="${file.filePath}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file.fileTitle)!''}">${(file.fileTitle)!"" }&nbsp;</h3>
										<h3 class="f14 tc" title="${(file.url)!''}">${(file.url)!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}','${(file.sort)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
							</#if>
						</#list>
					</#if>
		        </div>
	        </form>
		</div>
		
		<div id="editDiv" style="display:none;">
           	<div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10">
                    <div class="input-group">
                        <span class="input-group-addon">图片标题&nbsp;</span>
                        <input type="text" class="form-control" id="fileTitle" placeholder="请输入图片标题" >
                    </div>
                </div>

                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
                    <div class="input-group">
                        <span class="input-group-addon">资源序号<span style="color:red;">*</span></span>
                        <input type="text" class="form-control" id="sort" placeholder="请输入资源序号" >
                    </div>
                </div>
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
                    <div class="input-group">
                        <span class="input-group-addon">目标路径&nbsp;</span>
                        <input type="text" class="form-control" id="url" placeholder="请输入目标路径,如https://www.baidu.com/">
                    </div>
                </div>
             </div>
         </div>
         <div id="deleteDiv" style="display:none;">确认删除？</div>
	</body>
	<script type="text/javascript">
		$(function(){ 
 			var zhi=$("#file_type").val();//广告11的提示不同
 			if("FILE__AD11"==zhi){
 				$(".thehint").html("* 最佳尺寸1140X100像素 至少4张");
 			}
    	});
		function uploadSource(t){
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
		function goBacks(){
			window.location.href="${rc.contextPath}/advertisement/list.do";
		}
		
		
		function callPerComplete(obj,data){
			eval('var json = ' + data);
			var file_type = $("#file_type").val();
			var module_code = $("#module_code").val();
			var parent_code = $("#parent_code").val();
			if(json.result){ 
				window.location.href="${rc.contextPath}/advertisement/saveAd?fileName="+json.fileNameInServer+"&file_type="+file_type+"&module_code="+module_code+"&parent_code="+parent_code;
			}
		}
		
		function callbackAllComplete(obj){
			
		}
			
		
		function editFile(fileId,fileTitle,url,urlType,sort){
			showDialog("系统提示","editDiv","sure('"+fileId+"')","cancel()","closeDialog()",100,true,false);
			$("#fileTitle").val(fileTitle);
			$("#url").val(url);
			$("#sort").val(sort);
		}
		
		function sure(fileId){
			var tt = validateFormObject();
			tt.checkNumber("sort","资源序号",true,1,999);
			tt.checkLength("fileTitle","图片标题",false,1,20); 
			tt.checkLength("url","目标路径",false,1,500);
			if(tt.validate()){
				var file_type = $("#file_type").val();
				var module_code = $("#module_code").val();
				var parent_code = $("#parent_code").val();
				var sort = $("#sort").val();
				window.location.href="${rc.contextPath}/advertisement/updateAd.do?fileId="+fileId+"&fileTitle="+encodeURI(encodeURI($("#fileTitle").val()))+"&url="+$("#url").val()+"&file_type="+file_type+"&module_code="+module_code+"&parent_code="+parent_code+"&sort="+sort;
				showDialog_close("editDiv");
			}
		}
		
		
		function deleteFile(fileId){
			var len = $("img").length;
			if(len <= 1){
				custom_alert('不能删除最后一张图',3,2000);
				return ;
			}else{
				showDialog("系统提示","deleteDiv","sure1('"+fileId+"')","cancel()","closeDialog()",100,true);
			}
		}
		
		function sure1(fileId){
			var file_type = $("#file_type").val();
			var module_code = $("#module_code").val();
			var parent_code = $("#parent_code").val();
			window.location.href="${rc.contextPath}/advertisement/deleteAd.do?fileId="+fileId+"&file_type="+file_type+"&module_code="+module_code+"&parent_code="+parent_code;
		}
		function cancel(){
			showDialog_close("editDiv");
		}
	</script>
</html>