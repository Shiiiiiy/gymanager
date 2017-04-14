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
    			<li><a href="javascript:;" class="fb" onclick="toPage('INNOVATE_PLATFORM')">创新创业平台</a></li>
    			<li><a href="javascript:;" class="fb" onclick="toPage('FINANCE_ENVIRONMENT')">产业金融环境</a></li>
    			<li><a href="javascript:;" class="fb" onclick="toPage('INNOV_BUSINESS')">创新创业示范企业</a></li>
    			<li>
    				<a href="javascript:;" class="fb" onclick="toPage('NEW_COMPANIES')">创新创业成果</a>
    				<ul class="submenu-list">
    					<li title="年度新增创新企业名录"><a href="javascript:;" onclick="toPage('NEW_COMPANIES')">&gt;年度新增创新企...</a></li>
    					<li title="年度成功引入风险投资企业名录"><a href="javascript:;" onclick="toPage('INVEST_COMPANIES')">&gt;年度成功引入风...</a></li>
    					<li title="年度新增上市企业名录"><a href="javascript:;" onclick="toPage('MARKET_COMPANIES')">&gt;年度新增上市企...</a></li>
    				</ul>
    			</li>
    		</ul>
    	</div>
    	<div id="ddiv" class="col-xs-12 col-sm-9 col-md-10 col-lg-10 iframe-box no-padding">
    		<iframe name="" id="frm" style="width:100%;height:803px;" overflow:auto; src="${rc.contextPath}/dynamicdata/opt-query/modelList.do?modelType=INNOVATE_PLATFORM"></iframe>
    	</div>
    </div>
</body>
<script type="text/javascript">
	function toPage(type){
		$("#frm").attr("src","${rc.contextPath}/dynamicdata/opt-query/modelList.do?modelType="+type);
	}
</script>
</html>
