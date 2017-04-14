<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#include "/common/common.ftl" >
    
	<link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>  
</head>
<body>
<div class="content-box">
    <!--form-->
	<div class="page-header"><h2>编辑用户<small class="font-en caps"></small></h2></div>
	<form class="form-inline search_list" role="form"  id="theform" method="post">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <input type="hidden" id="old_user_no" value="${(UserOne.user_no)!''}"/>
                        <input type="hidden" id="old_id"  name="id" value="${(UserOne.id)!''}"/>
                        <input type="hidden" id="old_password"  value="${(UserOne.pwd)!''}"/>
                        <input type="hidden" id="notchangepwd" name="notchangepwd" value="true"/>
                        <span class="input-group-addon">帐&nbsp;&nbsp;&nbsp;号<span style="color:red;">*</span></span>
                        <input type="text" class="form-control" value="${(UserOne.user_no)!''}" id="user_no" name="user_no" placeholder="请输入帐号" style="width:200px;">
                    </div>
                </div>
            </div>  
            <div class="row">  
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">用户名<span style="color:red;">*</span></span>
                        <input type="text" class="form-control" value="${(UserOne.name)!''}" id="name" name="name" placeholder="请输入用户名" style="width:200px;">
                    </div>
                </div>
            </div>
            <div class="row">    
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">密&nbsp;&nbsp;&nbsp;码<span style="color:red;">*</span></span>
                        <input type="password" class="form-control" value="${(UserOne.pwd)!''}" id="pwd" name="pwd" placeholder="请输入密码" style="width:200px;">
                    </div>
                </div>
            </div>
             
       		<div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">手机号<span >&nbsp;</span></span>
                        <input type="text" class="form-control" value="${(UserOne.tel_no)!''}" id="tel_no" name="tel_no"  placeholder="请输入手机号" style="width:200px;">
                    </div>
                </div>
            </div>    
            <div class="row">
       			<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">邮&nbsp;&nbsp;&nbsp;&nbsp;箱</span>
                        <input type="text" class="form-control" value="${(UserOne.email)!''}" id="email" name="email"  placeholder="请输入邮箱" style="width:200px;">
                    </div>
                </div>
			</div>
			<div class="row">
			    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">状&nbsp;&nbsp;态<span style="color:red;">*</span></span>
                        <#if Dics??>
						<#list Dics as dic>
		                    <label class="input-group-addon gray-bg" style="width:45px;">
		                        <input type="radio" aria-label="..." value="${(dic.id)!""}" name="status" <#if (dic.id)?? && ((UserOne.status)+"")==((dic.id)+"")>checked</#if> >
		                    </label>
		                    <label class="form-control">${(dic.name)!""}</label>
                        </#list> 
						</#if>
                    </div>
                </div>
			</div>
              <!----->
	          <div class="row">      
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	                    <div class="input-group" id="uploadImg">
	                        <span class="input-group-addon">头&nbsp;&nbsp;&nbsp;&nbsp;像<span style="color:red;"></span></span>
	                        <input type="text" class="form-control"   id="image_name" name="image_name" value="${(UserOne.image_name)!''}" placeholder="请上传头像" style="width:200px;" readonly="readonly"/>
	                        <input type="hidden" class="form-control" id="dbimage" name="image_url"  value="${(UserOne.image_url)!''}" style="width:200px;" />
	                        <a href="javascript:void(0);"  class="btn green" id="up_btn"><i class="icon-folder-open"></i>上传</a>&nbsp;
	                        <a href='javascript:void(0);' id="viewImg" class='btn green' onclick='javascript:void(0);' style="display:none;"><i class="icon-eye-open"></i>预览</a>
	                    </div>
	                </div>
			  </div>   
			  
			  <div class="row" id="showimg" 
			  <#if (UserOne.image_url)?? && ((UserOne.image_url)+"")!="">
			  style="display:block;"
			  <#else>
			  style="display:none;"
			  </#if> >  
	               <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			  			<img src="${rc.contextPath+(UserOne.image_url)!''}" id="theimg" class="mr10 fl" width="100px;" heigth="100px;"/>
			  	   </div>
		      </div>
		      	
		                     
			<div class="row">
			    <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
       			<a class="btn blue" href="javaScript:void(0)" onclick="save('theform');"><i class="icon-ok"></i> 确定</a>
       			<a class="btn black" href="javaScript:void(0)" onclick="ToBack();"><i class="icon-remove"></i> 取消</a>
	       		</div>
	    	</div>
	</form>
</div>
</body>
<script type="text/javascript">
	$(function(){
		var zhi="${rc.contextPath}";
		var nowproj=zhi.substring(1,zhi.length);
		$('#up_btn').click(function(){
			file.upload({
				contextPath:"${rc.contextPath}", //固定写法
				obj:this, //固定写法
				callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
				callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
				maxFileNum:1, //队列中最大文件数量
				fileType:'*.jpg;*.png', //可选文件类型
				fileTypeDesc:'图片文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
				
				//saveLocation:'/cms/resource/images', //指定保存路径(一个TOMCAT下跑两个项目互访文件时用)
				//replaceStr:'gykjy' //另一个项目的跟目录名
				}); 
		});
	});
	function callPerComplete(obj,data){//上传回调
		var id = "";
		eval('var json = ' + data);
		if(json.result){ //&& json.pictureFile)
			if(json.pictureFile){			
				$("#image_name").attr("value",json.originalfileName);//上传到数据库的  图片名
				var address=json.fileRelativePath;
				var zhi="${rc.contextPath}";
		        $("#dbimage").val(address.substring(zhi.length)); //上传到数据库的  图片地址
		        $("#showimg").css("display","block");
				$("#theimg").attr("src",json.fileRelativePath); //预览功能
			}
		}
	}
	function callbackAllComplete(obj){
	
	}
	function ToBack(){//返回按钮
		location="${rc.contextPath}/sysuser/list.do";
	}
	function save(formid){
		if( validate_form(formid)  ){ 
			var oldpwd=$('#old_password').val()+"";
			var newpwd=$('#pwd').val()+"";
			var notchangepwd="";
			if(  (oldpwd.length)==(newpwd.length) && oldpwd==newpwd ){//表示密码没有修改
				notchangepwd="true";//需要处理
		    }else{  notchangepwd="false";  }//不用处理
		    $('#notchangepwd').val(notchangepwd);
			$("#theform").attr("action","${rc.contextPath}/sysuser/update.do");
    		$("#theform").submit();
		}
	}
	function validate_form(formid){
		var tt = validateFormObject();
		tt.checkLength("user_no","帐号",true,1,40,checkDupliUser);
		tt.checkLength("name","用户名",true,1,30);
		tt.checkLength("pwd","密码",true,1,32);
		tt.checkLength("tel_no","手机号",false,1,11,isTheTel);
		tt.checkLength("email","邮箱",false,1,50,isTheEmail);
		return tt.validate();
	}

	function isTheTel(){//手机号码验证
		var obj=$('#tel_no').val()+"";
		var length = obj.length;
		if(""==obj){return "";}
		var reg=/^[1][3-8]+\d{9}$/;
		if(  length == 11 && reg.test(obj)  ){
			return "";
		}else{return "手机号码格式不正确";}
	}
	function isTheEmail(){//邮箱号码验证  
		var obj=$('#email').val()+"";
		if(""==obj){return "";}
		var reg=/^\w{3,}@\w+(\.\w+)+$/;   
		if(reg.test(obj)){
			return "";
		}else{return "邮箱格式不正确";}
	}
	function checkPwd2(){//原密码  确认验证  
		var oldpwd=$('#old_password').val()+"";
		var newpwd=$('#pwd').val()+"";
		if(  (oldpwd.length)!=(newpwd.length)  ){
			alert(oldpwd.length+" "+newpwd.length)
	  		var id=$('#old_id').val()+"";
	  		var str="";
			$.ajax({
		        url:"${rc.contextPath}/sysuser/checkPwd.do",
		        type:"post",
		        async:false,
		        data:{"pwd":newpwd,"id":id},
		        dataType:"text",
		        success : function(obj){
		            if("false" == obj){//原密码错误
						str="原密码输入错误";
		            }
		        }
		    });
	    }
		return str;
	}
	function checkDupliUser(){//异步检查帐号是否重名
		var user_no=$("#user_no").val()+"";
		var old_user_no=$("#old_user_no").val()+"";
		var str="";
		if(user_no != old_user_no){   //名称变了需要校验
			var reg = /^[A-Za-z0-9]+$/;  //帐号只能包含字母和数字
			if(reg.test(user_no)){   //格式正确
				$.ajax({
			        url:"${rc.contextPath}/sysuser/checkDupliUser.do",
			        type:"post",
			        async:false,
			        data:{"user_no":user_no},
			        dataType:"text",
			        success : function(obj){
			            if("true" == obj){//有重名
							str="该帐号已经存在,请换一个试试";
			            }
			        }
			    });
	        }else{
	        	str="帐号只能包含字母和数字";
	        }
	    }
	    return str; 
	}
</script>
</html>
