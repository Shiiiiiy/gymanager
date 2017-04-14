<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>欢迎页面</title>
    
	<link rel="StyleSheet" href="${rc.contextPath}/js/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="${rc.contextPath}/js/dtree/dtree.js"></script>
</head>
<body>

	<script type="text/javascript">
		<!--

		d = new dTree('d');
		//设置状态栏
		d.config.useStatusText=true;
		//设置是不是关闭同一层的其他节点
		d.config.closeSameLevel=false;
		//是不是可以使用cookie
		d.config.useCookies=true;
		
		d.config.useLines = true;//选中状态

		
		d.add(0,-1,'根节点');

		<#list list as a>
			d.add(${(a.ID)!""},${(a.P_ID)!""},'${(a.TITLE)!""}','javaScript:alert(${(a.ID)!""});');
		</#list> 
		document.write(d);

		//-->
	</script>
</body>
</html>
