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
	            <li role="presentation" id="NEWS_E"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" onclick="changeTab('NEWS_E')">政策动态</a>
	            <li role="presentation" id="NEWS_F"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="changeTab('NEWS_F')" >国家政策</a>
	            <li role="presentation" id="NEWS_G"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab" onclick="changeTab('NEWS_G')">省级政策</a>
	            <li role="presentation" id="NEWS_H"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab" onclick="changeTab('NEWS_H')" >本市政策</a>
	            <li role="presentation" id="NEWS_I"><a href="#tab5" aria-controls="tab5" role="tab" data-toggle="tab" onclick="changeTab('NEWS_I')" >政策解读</a>
	        </ul>
	        <div class="tab-content" id="content">
	        	
	        </div>
    	</div>
    </div>
<script>
$(function(){
	var code = "${moduleCode!""}";
	$("ul li").removeClass("active");
	$("#"+(code == "" ? "NEWS_E" : code)).addClass("active");
	var newsTitle = "${news.newsTitle!""}";
	var newsSource = "${news.newsSource!""}";
	var newsTime = "${news.newsTime!""}";
	var pageNo = "${pageNo!""}";
	var pageSize = "${pageSize!""}";
	changeTab(code,newsTitle,newsSource,newsTime,pageNo,pageSize);
});

var changeTab = function(code,newsTitle,newsSource,newsTime,pageNo,pageSize){
	$("#content").load("${rc.contextPath}/policynews/query/single",
		{	
			moduleCode:	code,
			newsTitle :	newsTitle,
			newsSource:	newsSource,
			newsTime  : newsTime,
			pageNo	  :	pageNo,
			pageSize  : pageSize,
			pageSearchType : "lalala"
		});
}


</script>
</body>
</html>