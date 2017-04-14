<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8" />
		<title>导入</title>
		<#include "/common/common.ftl" >
		<link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
		<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
		<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script> 
		<script type="text/javascript" src="${rc.contextPath}/js/json2.js"></script>  
		<script src="${rc.contextPath}/js/custom.validate.js"></script>
		<style>
		.error_style{
			border:2px solid #F00!important;
		}
		.company{
			margin:0;
			padding:0;
			width:100%;
			height:100%;
		}
		</style>
	</head>
	<body>
	<div class="content-box">
		<div class="page-header">
        	<h2>导入<small class="font-en caps"></small></h2>
        </div>
        <form class="form-inline search_list" role="form"  id="excelCompanyForm" method="post" action="${rc.contextPath}${requestPath}">
	        <div class="row">  
	             <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	                   <div class="input-group" id="uploadImg">
	                       <span class="input-group-addon">选择文件<span style="color:red;">*</span></span>
	                       <input type="text" class="form-control"   id="excelCompany" name="excelName" placeholder="选择文件" style="width:200px;" readonly="readonly">
	                       <input type="hidden" class="form-control" id="excelUrl" name="excelUrl" style="width:200px;">
	                       <a href="javascript:void(0);"  class="btn black" id="up_btn" ><i class="icon-cloud"></i>上传</a>&nbsp;
	                       <a class="btn black"  href="javaScript:void(0)" onclick="sureExcel()"><i class="icon-search" ></i>&nbsp;解析</a>&nbsp;
	                       <a class="btn purple" href="${rc.contextPath}/uploadFile/institutionUser.xls" ><i class="icon-cog"></i>&nbsp;下载模板</a>
	                   </div>
	              </div>
	         </div>
	          <#if !list?exists>
		         <div class="content-box"  style="width: 500px;height: 200px;background-color: #eee;">
		         		说明&nbsp;:&nbsp;1、导入文件格式必须是.xls或.xlsx <br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、文件大小不能超过30M<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、带*的列为必填项<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、文件导入后，可在列表中查看，双击进行编辑，点击“确认”保存数据<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、数据验证错误的方格会标示红色
		         </div>
		         <div class="btn-group">	
					<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-backward"></i> 返回</a>
				</div>
	         </#if>
	         <#if list??>
		         <div class="btn-group">
					<a  class="btn green" onclick="sure();" target="content-box" ><i class="icon-plus"></i>&nbsp;确定</a>&nbsp;&nbsp;
				</div>
				<div class="btn-group">	
					<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
				</div>	
			</#if>
	         <div class="table-responsive ">
	         <#if list??>
				<table class="table table-bordered table-striped table-hover mytable" id="improtCompanytable" style="table-layout:fixed">
						<thead>
							<tr>
								<th><div class="zjh">序号</div></th>
								<th><div class="zjh">用户名称</div></th>
								<th><div class="zjh">用户密码</div></th>
								<th><div class="zjh">机构名称</div></th>
								<th><div class="zjh">联系人</div></th>
								<th><div class="zjh">邮箱</div></th>
								<th><div class="zjh">手机号码</div></th>
								<th><div class="zjh">座机号码</div></th>
								<th><div class="zjh">组织机构代码</div></th>
								<th><div class="zjh">社会信用代码</div></th>
								<th><div class="zjh">机构所在地</div></th>
								<th><div class="zjh">备注</div></th>
								<th width="10%"><div class="">操作</div></th>
							</tr>
						</thead>
						<tbody> 
							<#list list as company>
								<tr  class="companys">
										<td><div class="zjh" name="seq">${company_index+1}</div></td>
										<td class="dbclick"><div class="zjh company" name="username">${company.username!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="password">${company.password!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="nameIn">${company.nameIn!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="linkMan">${company.linkMan!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="emailIn">${company.emailIn!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="telIn">${company.telIn!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="phoneIn">${company.phoneIn!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="orgCode">${company.orgCode!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="socialCode">${company.socialCode!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="address">${company.address!''}</div></td>
										<td class="dbclick"><div class="zjh company" name="comment">${company.comment!''}</div></td>
								 		<td><a onclick="deleteCompany(this);"  class="btn mini black"><i class="icon-trash"></i>&nbsp;删除</a>&nbsp;&nbsp;</td>
								</tr>	
							</#list>
						 </tbody>	
					</table>
				</#if>
			 </div>		
         </form> 
	</div>
	<div id="tishi" style="display:none;"></div>
	</body>
	<script type="text/javascript">
	//td转换input
		$(function(){
			$(".dbclick").dblclick(function(){
				$(this).find("div").find("span").remove();
				$(this).removeClass("error_style");
				if(!$(this).find("div").find("input").val()){
					var res = $(this).find("div").text();
					$(this).find("div").text("");
					$(this).find("div").append("<input type='text' style='margin:0;padding:0;width:100%;height:100%;border:0'  value='"+res+"'/>");	
				}		
				$(this).find("div").find("input").focus();
				$(".company input").blur(function(){
					$(this).parent().text($(this).val());
					$(this).remove();
				});	
			});
		});
 
		$(function(){
			var zhi="${rc.contextPath}";
			$('#up_btn').click(function(){
				file.upload({
					contextPath:"${rc.contextPath}", //固定写法
					obj:this, //固定写法
					callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
					callbackAllComplete:function(){
					},//回调函数, 可以写成 callPerComplete:function(){...}的形式
					maxFileNum:1, //队列中最大文件数量
					fileType:'*.xlsx;*.xls;', //可选文件类型
					fileTypeDesc:'excel文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
					saveLocation:'/gykjy/plugins/images/allImages', //指定保存路径(一个TOMCAT下跑两个项目互访文件时用)
					}); 
			});
		});
		
		function deleteCompany(obj){
		
			//获取删除行的序号
			var index = $(obj).parent().parent().find("div[name='seq']").text();
			$(obj).parent().parent().remove();
			
			var divs = $("div[name='seq']");
			
			if(divs && divs.length > 0 ){
				$.each(divs,function(i,d){
					if(parseInt($(d).text())>parseInt(index)){
						$(d).text($(d).text()-1);
					}
				});
			}
		}
		
		function callPerComplete(obj,data){
			eval('var json = ' + data);
			var fileName=json.fileNameInServer;
			var excelUrl = json.location;
			$("#excelCompany").val(fileName);
			$("#excelUrl").val(excelUrl);
		}
		
		//提交数据
		function sure(){
			var divs = $(".company");
			$.each(divs,function(){
				$(this).find("span").remove();
				$(this).parent().removeClass("error_style");
		 	});
		 	//数据校验
		 	
		  	if(form_validate()){ //前台校验
		  	 	if(checkUserName()){//后台校验
		  		var str = groupData();
		  		if( str != "[]" || str.length > 2 ){
		  		$.ajax({
					  type: "POST",
					  url: "${rc.contextPath}/userManage/opt-save/saveInstitutionUser.do",
					  data: {jsonStr:str},
					  dataType: "text",
					  success:function(msg){
					  		if(msg){
					  			window.location.href="${rc.contextPath}/userManage/opt-query/institutionList?infoType=import";
					  		}else{
					  			custom_alert('导入失败,请联系管理员',4,2000);
					  		}
					  }
				  });
				  
				  }else{
					custom_alert('未获取到需要保存的信息',4,2000);
					return false;
				  }
				}
		  	}
		}
		
			//组装数据
		function groupData(){
			var trs = $(".companys");
			var str = "[";
			if(trs && trs.length > 0 ){
				$.each(trs,function(i,tds){
					var na = $(tds).find("div[name!='seq']");
						str +="{";
						$.each(na,function(){
					 		str += " \"" + $(this).attr("name") + "\":\""+ $(this).text() +"\",";
						});
						str = str.substring(0,str.length-1);
						str += "},";
				});
				str = str.substring(0,str.length-1);
			}
			str +="]";
			return str;
		}
		
		
		//自定义校验  
		function form_validate(){
			var objs = $(".company");
			var tt = validateFormObject_t();
			//校验数据长度
		 	$.each(objs,function(index,obj){
					if($(this).attr("name") == "username"){
						tt.checkLength(obj,"用户名",true,2,20);
					}	
					if($(this).attr("name") == "password"){
						tt.checkLength(obj,"用户密码",true,6,16);
					}
					if($(this).attr("name") == "nameIn"){
						tt.checkLength(obj,"机构名称",false,1,30);
					}
					if($(this).attr("name") == "linkMan"){
						tt.checkLength(obj,"联系人",false,1,32); 
					}
					if($(this).attr("name") == "telIn"){
						tt.checkLength(obj,"手机号码",false,1,11); 
					}
					if($(this).attr("name") == "phoneIn"){
						tt.checkLength(obj,"座机号码",false,1,13); 
					}
					if($(this).attr("name") == "orgCode"){
						tt.checkLength(obj,"组织机构代码",false,1,40);
					}
					if($(this).attr("name") == "socialCode"){
						tt.checkLength(obj,"社会信用代码",false,1,40);
					}
					if($(this).attr("name") == "emailIn"){
						tt.checkLength(obj,"邮箱",false,1,20);
					}	
					if($(this).attr("name") == "address"){
						tt.checkLength(obj,"联系地址",false,1,100);
					}
				});
				var flag = true;	
				var userNames = $("div[name='username']");
				
			//校验页面是否有重复数据
			if(userNames && userNames.length > 0 ){
				var users = "";
				$.each(userNames,function(){
		 			 if(users.indexOf($.trim($(this).text()))>-1){
		 				 $(this).find("span").remove();
		 				 $(this).parent().removeClass("error_style");
		 				 addErrorStyle($(this),"该用户名 \""+$(this).text()+"\" 已存在"); 
		 				 flag = false;
		 			 }
		 			 users	+= $.trim($(this).text());
			 	});
			}
			return tt.validate() && flag;
		}
		
		//校验用户名是否重复
		function checkUserName(){
			var flag = true;
			var userNames = $("div[name='username']");
			//校验页面是否有重复数据
			var str = "[";
			if(userNames && userNames.length > 0 ){
			 	$.each(userNames,function(){
			 			str += "{\"username\":\""+$(this).text()+"\"},";
			 		});
				str = str.substring(0,str.length-1);
			}
			str += "]";
			
			if( str.length > 2 ){
			var type = "INSTITUTION";
			//校验数据库中是否已存在
			$.ajax({
				type:"POST",
				url:"${rc.contextPath}/userManage/opt-query/checkUserNames",
				data:{jsonStr:str,userType:type},	
				dataType: "JSON",	 
				async:false,
				success:function(data){
						if(data && data.length > 0){  
					        for(var d in data ){
						        $.each(userNames,function(i,obj){
						        	if( $.trim($(obj).text()) ==  $.trim(data[d].username)){  
						        		addErrorStyle($(obj),"该用户名 \""+data[d].username+"\" 已被注册"); 
						        		flag = false;
						        	}
						        });
					         }
					      }  
					   }
				});
			}
			return flag;
		}
		
		  
		
		//添加错误样式
		function addErrorStyle(obj,error){
			var div = $(obj);
			div.parent().addClass("error");
			div.parent().addClass("error_style");
			var p = $("<span style='display:block;'></span>");
			p.text(error);
			div.find("span").remove();
			div.append(p);
		}
		
		function goBacks(){
			window.location.href="${rc.contextPath}/userManage/opt-query/institutionList";
		}
		function sureExcel(){
			if($("#excelUrl").val()){
				$("#excelCompanyForm").submit();
			}else{
				custom_alert("请先上传文件",3,3000);
			}
		} 
		$(function(){
			<#if error_flag??>
				custom_alert('数据读取失败，请确保导入的excel与模板excel结构一致',4,3000);
			</#if>
		});
	</script>
</html>