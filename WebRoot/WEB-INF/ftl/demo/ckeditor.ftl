<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>欢迎页面</title>
	 <script src="${rc.contextPath}/js/ckeditor/ckeditor.js"></script>
	 <script src="${rc.contextPath}/js/ckeditor/tool.js"></script>
     <script type="text/javascript">
   	   
    	$(function(){
        	editorText('content');  
 
    	});
    $(function(){
       $("#save").click(function(){
       var text= getData('content');
       alert(text);
     		
		});
    });
 
</script>
 
</head>
<body>
	<form id="from_ckeditor" action="${rc.contextPath}/ckeditor" method="post">
   		<textarea name="content" id="content" cols="12" rows="5"   > </textarea>
    	<input type="button" value="保存" id="save"  />
   	</from>
</body>
</html>