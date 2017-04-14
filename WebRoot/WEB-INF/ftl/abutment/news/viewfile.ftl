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
        <div class="page-header">
        	<h2>
       			查看
        	    <small class="font-en caps">
	        		
        		</small>
        	</h2>
        </div>
        <div class="infor-con">
        	<p class="clearfix"><label class="fl">标题</label><span class="fl" style="width:90%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 18px;">${(file.fileTitle)!''}</span></p>
        	<p class="clearfix"><label class="fl">链接</label>
        	<span class="fl" style="width:90%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 18px;">
        	<a target="_blank" href="${(file.url)!"#"}" >${(file.url)!""}</a>
        	</span></p>
        </div>
            
    	<div class="row">	
            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
       			<a class="btn black" href="javascript:history.go(-1)"><i class="icon-backward"></i> 返回</a>
        	</div>
    	</div>
    </div>
</body>
<script type="text/javascript">
</script>
</html>
