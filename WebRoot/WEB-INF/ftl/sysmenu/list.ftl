<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>菜单管理</title>
    <script type="text/javascript" src="${rc.contextPath}/js/jquery.tree.core.js"></script>
    <link rel="StyleSheet" href="${rc.contextPath}/js/dtree/dtree.css" type="text/css" />
	<script type="text/javascript" src="${rc.contextPath}/js/dtree/dtree.js"></script>
	<style>
	body, html{height:100%;min-height:100%;}
	</style>
</head>
<body>

    <div class="content-box" style="height:100%;min-height:100%;">
    
        <div class="page-header"><h2>菜单管理<small class="font-en caps"></small></h2></div>
        <div style="float:left;background:white;width:20%;border-right:1px;">
        	<div style="overflow:auto;">
        	<script type="text/javascript">
        		function changeSrc(id){
        			$("#testIframe").attr("src","${rc.contextPath}/sysmenu/menu.do?id="+id);
        		}
        	</script>
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
				d.add(1,-1,'目录','javaScript:changeSrc(1);');
		        <#list list as a>
					d.add(${(a.ID)!""},${(a.P_ID)!""},'${(a.TITLE)!""}','javaScript:changeSrc(${(a.ID)!""});');
				</#list> 
				document.write(d);
				//-->
			</script>
        	
        	</div>
        </div><!-- ifream 框架-->
        <div style="float:left;background:white;width:80%;height:80%;min-height:80%;">
        	<IFRAME ID="testIframe" Name="testIframe" FRAMEBORDER=0 SCROLLING="auto" width=100% style="height:100%;min-height:100%;" SRC="${rc.contextPath}/sysmenu/menu.do?id=${(parentid)!''}"></IFRAME>
		</div>
    </div>
    <div id="dddd" style="display:none;">
    	是否删除该项	
	</div>
	
</body>
<script type="text/javascript">
	//ztree 函数
	/*var zTree;
	var demoIframe;
	var setting = {};
	zNodes = [];
	$(document).ready(function(){
		var t = $("#tree");
		t = $.fn.zTree.init(t, setting, zNodes);
		demoIframe = $("#testIframe");
		demoIframe.bind("load", loadReady);
		var zTree = $.fn.zTree.getZTreeObj("tree");
	});
	function loadReady() {
		var bodyH = demoIframe.contents().find("body").get(0).scrollHeight,
		htmlH = demoIframe.contents().find("html").get(0).scrollHeight,
		maxH = Math.max(bodyH, htmlH), minH = Math.min(bodyH, htmlH),
		h = demoIframe.height() >= maxH ? minH:maxH ;
		if (h < 530) h = 530;
		demoIframe.height(h);
	}*/
	<!-- 是否删除提示框   -->
	function DelMenu(id){//是否删除提示框
		showDialog("系统提示","dddd","aa("+id+")","bb()","bb()",1);
	}
	function aa(id){  location="${rc.contextPath}/sysmenu/delmenu.do?id="+id;  }
	function bb(){}
</script>
</html>
