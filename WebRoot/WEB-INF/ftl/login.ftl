<!DOCTYPE html>
<html>
    <head>
        <meta charset="utf-8" />
        <meta name="viewport" content="width=device-width, initial-scale=1.0" />
        <title>贵阳科技园后台管理系统</title>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
		<link href="${rc.contextPath}/css/login.css" rel="stylesheet" type="text/css" />
		<link href="${rc.contextPath}/images/favicon.ico" rel="shortcut icon"/>
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
	<style>
	.warning{
		border: 1px solid red;
		border-radius: 4px;
	}
	
	.tips{
		text-align: center;
		color: red;
		position: relative;
		top: 75px;
		left: 285px;
		display: none;
		width: 343px;
		font-size:13px;
	}
	</style>
    </head>
<body>

<div id="mainBody">
  <div id="cloud1" class="cloud"></div>
  <div id="cloud2" class="cloud"></div>
</div>
<div class="logintop"> 
	<span>登录贵阳科技园云平台后台管理系统</span>
</div>
<div class="loginbody"> 
	<span class="systemlogo"></span>
	<form action="" method="post" id="login_form">
	<div class="loginbox">
	   <ul>
	      <li>
	        <input id="username" name="username" type="text" class="loginuser"  placeholder="用户名" />
	      </li>
	      <li>
	        <input name="userpassword" id="pwd" type="password" class="loginpwd" placeholder="密码"/>
	      </li>
	      <li id="code_li" style="display:none;">
	        	<input id="v_code" maxlength="4" name="verifycode" type="text" class="loginyzm" onclick="JavaScript:this.value=''" placeholder="验证码"/>
	        	<label><img src="${rc.contextPath}/verify" id="v_img" title="图片看不清？点击重新得到验证码" onclick="javascript:changeAuthCode();" style="cursor:pointer;margin-top:0;" /></label>
	       		<label><a href="#" onclick="javascript:changeAuthCode();">换一张</a></label>
	      </li>
	      <li style="clear:both">
	        <input type="submit" class="loginbtn" style="margin-top:0" id="login_btn" value="登录"/>
	      </li>
	      <li class="ts" id="errorMessageLi">
	      </li>
	    </ul>
	  </div>
	 </form>
</div>
<div class="loginbm">Copyright  2004-2012 Uwaysoft 联合永道 All Rights Reserved<br />
  请使用IE8以上浏览器，分辨率最低支持1024x768</div>
<script type="text/javascript">




$(function(){
	var isMuitlLogin=${isMultiLogin?c};
	if(isMuitlLogin){
		$("#code_li").show();
		$("#login_btn").css("margin-top","10px");
	}
	
	$("#login_form").submit(function(e){
		e.preventDefault();
		login();
	});
	
	$("#username,#pwd,#v_code").focus(function(){
		$(this).removeClass("warning");
		$("#errorMessageLi").text("");
	});
	
	$("body").keyup(function(e){
		if(e.keyCode==13)login();
	});
	
	$("#username").blur(function(){
		$(this).val($.trim($(this).val()));
	});
});

function login(){
	if($.trim($("#username").val()) == ""){
		$("#errorMessageLi").text("请填写用户名！");
		$("#username").addClass("warning");
		return;
	}
	
	if($.trim($("#pwd").val()) == ""){
		$("#errorMessageLi").text("请填写密码！");
		$("#pwd").addClass("warning");
		return;
	}
		
	if(!$("#code_li").is(":hidden")){
		if($.trim($("#v_code").val())==""){
			$("#errorMessageLi").text("请填写验证码！");
			$("#v_code").addClass("warning");
			return ;
		}
	}
	
	$.ajax({
			url:"${rc.contextPath}/dologin",
			type:"post",
			dataType:"json",
			data:""+$("#login_form").serialize(),
			async:true,
			success:function(data){
				if(data.showCode){
					$("#code_li").show();
					$("#login_btn").css("margin-top","10px");
				}
				
				if(data.success){
					window.location.href="${rc.contextPath}/main/index"
				}else if(data.invalidCode){
					$("#errorMessageLi").text("验证码错误！");
					$("#v_img").attr("src","${rc.contextPath}/verify?random="+Math.random());
				}else if(data.valid){
					$("#errorMessageLi").text("用户已被禁用！");
				}else if(data.invalidNameOrPwd){
					$("#errorMessageLi").text("帐号与密码不匹配！");
					$("#v_img").attr("src","${rc.contextPath}/verify?random="+Math.random());
				}
			}
		});
}

function changeAuthCode(){
	$("#v_img").attr("src","${rc.contextPath}/verify?random="+Math.random());
}
</script>
</body>
</html>