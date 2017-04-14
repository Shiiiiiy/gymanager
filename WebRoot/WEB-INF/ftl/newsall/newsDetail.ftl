<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>新闻预览</title>
</head>
<style>
</style>
<body>
   <div class="container">
        <div class="row art-con mt20 mb20">
            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" >
                <div class="content-art white" style="">
                    <h2 class="f30 tc">${(newsAll.newsTitle)!''}</h2>
                    <p class="tc f12 xx">发布时间：<#if newsAll?? && newsAll.newsTime??>${((newsAll.newsTime)!"")?string("yyyy-MM-dd HH:mm:ss")}</#if> &nbsp;&nbsp;来源：${(newsAll.newsSource)!""}</p>
                    <div class="con">
                        ${(newsAll.newsContent)!""}
                    </div>
                </div>
            </div>
            
        </div>
    </div>
</body>
<script type="text/javascript">
	$(function(){
		//$("#newsContent").css("display","none");
    	//editorText('content');  
    	//setData("content",$("#newsContent").val());
	});
	
</script>
</html>
