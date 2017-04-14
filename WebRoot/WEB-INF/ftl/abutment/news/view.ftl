<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title></title>
    <script src="${rc.contextPath}/js/ckeditor/ckeditor.js"></script>
	<script src="${rc.contextPath}/js/ckeditor/tool.js"></script>
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
       			查看
        	    <small class="font-en caps">
	        		
        		</small>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addOrEditNewsAll" method="post" action="${rc.contextPath}/newsall/opt-save/saveNewsAll.do">
	        <div class="infor-con">
	            	<p class="clearfix"><label class="fl">标题</label><span class="fl" style="width:90%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 18px;">${(newsAll.newsTitle)!''}</span></p>
	            	<p class="clearfix"><label class="fl">来源</label><span class="fl" style="width:90%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 18px;">${(newsAll.newsSource)!''}</span></p>
	            	<p class="clearfix"><label class="fl">简介</label><span class="fl" style="width:90%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 18px;">${(newsAll.introduce)!""}</span></p>
	            	<p><label>内容</label>
	            	<span>
	            	<a target="_blank" href="${rc.contextPath}/newsall/opt-query/newsAllDetail.do?id=${(newsAll.id)!''}" >预览</a>
	            	</span></p>
	            	<p>
	            		<label>状态</label>
	            		<span>
	            			<#if  newsAll.status==enable.id>
	                    		启用
	                       	<#else>
	                       		禁用
	                       	</#if>
	            		</span>
	            	</p>
	            </div>
	            
        	<div class="row">	
	            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	       			<a class="btn black" href="javascript:history.go(-1)"><i class="icon-backward"></i> 返回</a>
	        	</div>
        	</div>
        	
        </form>
    </div>
    <div class="con" id="yulan" style="display:none;width:500px;height:300px;overflow:hidden;overflow-y:auto;">
    	${(newsAll.newsContent)!''}
	</div>
</body>
<script type="text/javascript">
</script>
</html>
