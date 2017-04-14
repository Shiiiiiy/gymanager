<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title></title>
</head>
<body>
    <div class="content-box">
    	<div class="tabfix">
    		<ul class="nav nav-tabs" role="tablist" >
	            <li role="presentation" id="1"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" onclick="changeTab('1')">线上培训</a>
	            <li role="presentation" id="2"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="changeTab('2')" >线下培训</a>
	        </ul>
	        <div class="tab-content" id="content">
	        	
	        </div>
    	</div>
    </div>
<script>
$(function(){
	var tab = "${tab!""}";
	$("ul li").removeClass("active");
	$("#"+(tab == "" ? "1" : tab)).addClass("active");
	var newsTitle = "${news.newsTitle!""}";
	var newsSource = "${news.newsSource!""}";
	var newsTime = "${news.newsTime!""}";
	var fileTitle = "${file.fileTitle!""}";
	var fileTime = "${file.fileTime!""}";
	var pageNo = "${pageNo!""}";
	var pageSize = "${pageSize!""}";
	changeTab(tab,newsTitle,newsSource,newsTime,fileTitle,fileTime,pageNo,pageSize);
});

var changeTab = function(tab,newsTitle,newsSource,newsTime,fileTitle,fileTime,pageNo,pageSize){
	if(tab == "2"){
		$("#content").load("${rc.contextPath}/abutmentnews/query/list",
			{	
				newsTitle :	newsTitle,
				newsSource:	newsSource,
				newsTime  : newsTime,
				pageNo	  :	pageNo,
				pageSize  : pageSize,
				pageSearchType : "lalala"
			});
	}else{
		$("#content").load("${rc.contextPath}/abutmentfile/query/list",
			{	
				fileTitle : fileTitle,
				fileTime  : fileTime == "" ? null : fileTime,
				pageNo	  :	pageNo,
				pageSize  : pageSize,
				pageSearchType : "lalala"
			});
	}
}
</script>
</body>
</html>