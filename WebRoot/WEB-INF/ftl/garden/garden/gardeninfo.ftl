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
        <form class="form-inline search_list" role="form"  id="addOrEditgardenNews" method="post" action="${rc.contextPath}/gardenNews/opt-save/savegardenNews.do">
	        <div class="infor-con">
	            	<p><label>园区名称</label><span>${(garden.name)!''}</span></p>
	            	<p><label>简介内容</label><span><a href="javascript:" onclick="yulan()">预览</a></span></p>
	            	<p><label>简介图片</label>
	            	<#if  (garden.introducePic)?? && ((garden.introducePic)+"")!="">
		            	<img src="/gykjy/plugins/images/allImages/${(garden.introducePic)!''}"  width="700px;" heigth="300px;" />
		            <#else>
						&nbsp;
					</#if>
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
    	${(garden.introduce)!''}
	</div>
</body>
<script type="text/javascript">
	$(function(){
		//$("#newsContent").css("display","none");
    	//editorText('content');  
    	//setData("content",$("#newsContent").val());
	});
	
	function yulan(){
		showDialogFullHD("内容预览","yulan","sure()","cancel()","close()",100);
	}
	function sure(){}
	function cancel(){}
	function close(){}
</script>
</html>
