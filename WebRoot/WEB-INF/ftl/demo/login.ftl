
<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>CMS</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${rc.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
		<script src="${rc.contextPath}/js/jquery.min.js"></script>
		<script src="${rc.contextPath}/js/cloud.js"></script>
        <!--[if lt IE 9]>
            <script src="lib/html5shiv/html5.js"></script>
            <script src="lib/flot/excanvas.min.js"></script>
        <![endif]-->
	<script language="javascript">
		$(function(){
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
			$(window).resize(function(){  
		    $('.loginbox').css({'position':'absolute','left':($(window).width()-692)/2});
		    })  
		}); 
	</script>

    </head>
<body>

<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>
<div class="logintop"> 
	<span>登录CMS系统</span>
</div>
<div class="loginbody"> 
	<span class="systemlogo"></span>
	<form action="" method="post" id="login_form" onsubmit="return login()">
	<div class="loginbox">
	   <ul>
	      <li>
	        <input id="username" name="username" type="text" class="loginuser"   placeholder="用户名" />
	      </li>
	      <li>
	        <input name="password" id="password" type="password" class="loginpwd" placeholder="密码"/>
	      </li>
	      <li>
	        	<input id="code" name="code" type="text" class="loginyzm" onclick="JavaScript:this.value=''" placeholder="验证码"/>
	        	<label><img src="${rc.contextPath}/images/yzm_demo.png" title="图片看不清？点击重新得到验证码" style="cursor:pointer;"/></label>
	       		<label><a href="#" onclick="javascript:changeAuthCode();">换一张</a></label>
	      </li>
	      <li>
	        <input type="submit" class="loginbtn" value="登录"/>
	      </li>
	      <li class="ts" id="errorMessageLi">
	      </li>
	    </ul>
	  </div>
	 </form>
</div>
<div class="loginbm">Copyright  2004-2012 Uwaysoft 联合永道 All Rights Reserved<br />
  请使用IE8以上浏览器，分辨率最低支持1024x768</div>

</body>
</html>