<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <link href="${rc.contextPath}/css/base.css" rel="stylesheet" />
    <link href="${rc.contextPath}/css/layout.css" rel="stylesheet" />
    <link href="${rc.contextPath}/css/content.css" rel="stylesheet" />
    <script src="${rc.contextPath}/js/jquery.min.js"></script>
    <script src="${rc.contextPath}/js/layout.js"></script>
    <script src="${rc.contextPath}/js/main.js"></script>
    <script src="${rc.contextPath}/js/jquery.nicescroll.js"></script>
    <link href="${rc.contextPath}/css/bootstrap.min.css" rel="stylesheet" />
    <link href="${rc.contextPath}/css/buttons.css" rel="stylesheet" />
    <link href="${rc.contextPath}/images/favicon.ico" rel="shortcut icon"/><!--logo 图-->
    <title>贵阳科技园云平台后台管理系统</title>
</head>
<body>
    <#include "/main/top.ftl"/>
    <#include "/main/left.ftl"/>
    <div class="content">
        <iframe frameborder="0" id="con-iframe" style="height:100%; width:100%" name="content-box" src="${rc.contextPath}/main/list.do" scrolling="auto"></iframe>
    </div>
</body>
</html>
