<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
<#include "/common/common.ftl" >
<title>文件管理页面</title>
<link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>   
<style>

</style>
</head>
<body>
<button class="btn green">Upload</button>
<table class="table table-hover" style="border:1px solid #cecece">
	<thead>
    	<th style="width:200px;text-align:left;">名称</th>
        <th style="width:200px;text-align:left;">xxx</th>
        <th style="width:200px;text-align:left;">操作</th>
    </thead>
    <tbody>
    	<tr>
            <td>项目名称1</td>
            <td>xxx</td>
            <td><a href="#" id='up_btn'>upload(jpg)</a></td>
        </tr>
        <tr>
            <td>项目名称2</td>
            <td>xxx</td>
            <td><a href="#" id='up_btn2' onclick="">upload(xls)</a></td>
        </tr>
    </tbody>
</table>

<table id='result' border='1'>

</table>
	
<script>
$(function(){
	$('#up_btn').click(function(){
	
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:'*.jpg', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			
			saveLocation:'/gykjy/resource/images', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
			}); 
	});
	
	$('#up_btn2').click(function(){
	
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:'*.xls', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			
			//saveLocation:'/gykjy/resource/images', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
			}); 
	});
});

function callPerComplete(obj,data){
	var id = "";
	var name = "";
	eval('var json = ' + data);
	if(json.result){ //&& json.pictureFile)
		var str = "";
		for(var key in json){
			str += "<tr><td>&nbsp;</td><td>"+ key +"</td><td>" + json[key] + "</td></tr>"
		}
	
		$("#result").append(str);
		
		//将返回的地址信息存入数据库
		$.ajax({
			url:"${rc.contextPath}/file/savepath",
			dataType:"json",
			data:{location:json.location,fileName:json.originalfileName},
			type:"post",
			async:false,
			success:function(data){
				id = data.id;
				name = data.fileName;
			}
		});
		
		file.previewUrl = {url:"${rc.contextPath}/file/preview?id="+id}; //调用都自定义url,此controller里面,应提供文件绝对地址和原文件名
		file.downloadUrl = {url:"${rc.contextPath}/file/download?id="+id};
		
		if(json.pictureFile){
			$(obj).parent().append("&nbsp;<a href='javascript:void(0);' onclick='file.pictureView(file.previewUrl)'>Preview</a>");
		}
		$(obj).parent().append("&nbsp;<a href='javascript:void(0);' onclick='file.download(file.downloadUrl)'>download</a>");
	}
}
function callbackAllComplete(obj){

}
</script>
</body>
</html>