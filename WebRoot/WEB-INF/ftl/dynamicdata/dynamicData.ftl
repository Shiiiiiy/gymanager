<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>动态数据管理</title>
    <style>
    	ul.con-left-nav{background:#f2f2f2;padding:0;margin:40px 0 0 20px;width:100%;}
    	ul.submenu-list{margin-left:20px;}
    	ul.con-left-nav li {line-height:40px;}
    	ul.con-left-nav li a {display:block; width:100%;height:40px;padding:0 10px;border-left:#5bc0de solid 4px;background:#1d943b;color:#fff;}
    	ul.con-left-nav li a:hover{background:#29333e;}
    	ul.submenu-list li a{ background:none;border-left:none;border-bottom:#eaeaea solid 1px;color:#777;}
    	ul.submenu-list li a:hover{background:none;}
    	body, html, .dtsj-box, .iframe-box, .con-left-bg, ul.con-left-nav{height:100%;min-height:100%;}
    	.dtsj-box{margin:0;height:710px;}
    	.dtsj-box .no-padding{padding:0;}
    </style>
</head>
<body>
    <div class="row dtsj-box">
    	<div class="col-xs-12 col-sm-3 col-md-2 col-lg-2 no-padding con-left-bg">
    		<ul class="con-left-nav">
    			<li><a href="javascript:;" class="fb" onclick="toPage('TYPE_C')">技术创新平台</a></li>
    			<li>
    				<a href="javascript:;" class="fb" onclick="toPage('TYPE_A')">技术创新成果</a>
    				<ul class="submenu-list">
    					<li title="贵阳市专利申请明显企业"><a href="javascript:;" onclick="toPage('TYPE_A')">&gt;贵阳市专利申请...</a></li>
    					<li title="贵阳市知识产权优势培育企业"><a href="javascript:;" onclick="toPage('TYPE_B')">&gt;贵阳市知识产权...</a></li>
    				</ul>
    			</li>
    		</ul>
    	</div>
    	<div class="col-xs-12 col-sm-9 col-md-10 col-lg-10 iframe-box no-padding">
    		<iframe name="" id="frm" style="width:100%;height:803px;" overflow:auto;  src="${rc.contextPath}/dynamicdata/opt-query/techInnovationList.do"></iframe>
    	</div>
    </div>
</body>
<script type="text/javascript">
	function toPage(type){
		if(type =="TYPE_C"){
			$("#frm").attr("src","${rc.contextPath}/dynamicdata/opt-query/techInnovationList.do");
		}else{
			$("#frm").attr("src","${rc.contextPath}/dynamicdata/opt-query/techAchievList.do?type="+type);
		}
	}
</script>
</html>
