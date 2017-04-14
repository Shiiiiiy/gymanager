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
	</head>
	<body>
	<div class="content-box">
		<div class="page-header">
        	<h2>导入<small class="font-en caps"></small></h2>
        </div>
        <form class="form-inline search_list" role="form"  id="excelCompanyForm" method="post" action="${rc.contextPath}/companyManage/excelInshow.do">
	        <div class="row">  
	             <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
	                   <div class="input-group" id="uploadImg">
	                       <span class="input-group-addon">选择文件<span style="color:red;">*</span></span>
	                       <input type="text" class="form-control"   id="excelCompany" name="excelName" placeholder="选择文件" style="width:200px;" readonly="readonly">
	                       <input type="hidden" class="form-control" id="excelUrl" name="excelUrl" style="width:200px;">
	                       <a href="javascript:void(0);"  class="btn black" id="up_btn" ><i class="icon-cloud"></i>上传</a>&nbsp;
	                       <a class="btn black"  href="javaScript:void(0)" onclick="sureExcel()"><i class="icon-search" ></i>&nbsp;解析</a>&nbsp;
	                       <a class="btn purple" href="${rc.contextPath}/uploadFile/companyExcel.xlsx" ><i class="icon-cog"></i>&nbsp;下载模板</a>
	                   </div>
	              </div>
	         </div>
	          <#if !list?exists>
		         <div class="content-box"  style="width: 500px;height: 200px;background-color: #eee;">
		         		说明：1、导入文件格式必须是.xlsx或.xls <br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;2、文件大小不能超过30M<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;3、带*的列为必填项<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;4、表格中不允许出现空字符串<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;5、文件导入后，可在列表中查看，点击“确认”保存数据<br/>
					&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;6、数据验证错误的方格会标示红色
		         </div>
	         </#if>
	         <#if list??>
		        <div class="btn-group">
					<a  class="btn green" onclick="addCompany();" target="content-box" ><i class="icon-plus"></i>&nbsp;确定</a>&nbsp;&nbsp;
				</div>
				<div class="btn-group">	
					<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
				</div>	
			</#if>
	         <div class="table-responsive ">
				<table class="table table-bordered table-striped table-hover mytable" id="improtCompanytable" style="table-layout:fixed">
					<#if list??>
						<#list list as com>
							<#if com_index == 0>
								<thead>
									<th><div class="zjh">序号</div></th>
									<#list com as company>
										<th><div class="zjh">${(company.one)!''}</div></th>
									</#list>
									<th width="10%">
										<div class="zjh">操作</div>
									</th>
								</thead>
							<#else>	
								<tr>
									<td><div class="zjh" name="seq">${com_index}</div></td>
									<#list com as company>
										<#if (company.tow)??&&(company.tow)!="" >
											<td style=" border:2px solid ${(company.tow)!''};" tow="${(company.tow)!''}"><div class="zjh">${(company.one)!''}</div></td>
										<#else>
											<td><div class="zjh">${(company.one)!''}</div></td>	
										</#if>
									</#list>
									<td>
										<a onclick="deleteCompany(this);"  class="btn mini black"><i class="icon-trash"></i>&nbsp;删除</a>&nbsp;&nbsp;
									</td>
								</tr>	
							</#if>
						</#list>
					</#if>
				</table>
			 </div>		
         </form> 
	</div>
	<div id="tishi" style="display:none;"></div>
	</body>
	<script type="text/javascript">
		
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
					fileType:'*.xlsx;*.xls', //可选文件类型
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
		
		function addCompany(){
			var trArr = $("#improtCompanytable  tr");
			var trArrRes = [];
			var total = trArr.length - 1;
			var surNum = 0;
			trArr.each(function(i){
				var company= {};
				var flag = true;
				if(i > 0){
					$(this).children("td").each(function(){
						if($(this).attr("tow")=="red"){
							flag = false ;
						}
						
					});
					if(flag){
						$(this).children("td").children("div").each(function(i){
							if(i==1){
								company.cp_name = $(this).html();
							}
							if(i==2){
								company.cp_man = $(this).html();
							}
							if(i==3){
								company.cp_location = $(this).html();
							}
							if(i==4){
								company.cp_address = $(this).html();
							}
							if(i==5){
								company.cp_abstract = $(this).html();
							}
							if(i==6){
								company.cp_product = $(this).html();
							}
							if(i==7){
								company.cp_phone = $(this).html();
							}
							if(i==8){
								company.cp_linkman = $(this).html();
							}
							if(i==9){
								company.cp_linktel = $(this).html();
							}
							if(i==10){
								company.cp_linkemail = $(this).html();
							}
							if(i==11){
								company.cp_modelstr = $(this).html();
							}
							if(i==12){
								company.cp_marketstr = $(this).html();
							}
							if(i==13){
								company.cp_belongpartstr = $(this).html();
							}
							if(i==14){
								company.cp_scale = $(this).html();
							}
							if(i==15){
								company.cp_timestr = $(this).html();
							}
							if(i==16){
								company.cp_orgcode = $(this).html();
							}
							if(i==17){
								company.cp_socialcode = $(this).html();
							}
						});
						surNum ++;
						trArrRes.push(company);
					}
				}
			});
			$("#tishi").html("一共"+total+"条记录,符合记录的"+surNum+"条记录");
			$("#tishi").data("trArrRes",trArrRes);
			showDialog("系统提示","tishi","sure()","cancel()","close()",100);
			
		}
		
		function sure(param){
			var param = $("#tishi").data("trArrRes");
			$.ajax({
				type: "POST",
				url: "${rc.contextPath}/companyManage/excelSaveCompany.do?",
				data: {"param":JSON.stringify(param)},
				success:function(msg){
					if(msg == "success"){
						window.location.href="${rc.contextPath}/companyManage/excelCompanyTip.do";
					}
				}
				
			});
		}
		
		function goBacks(){
			window.location.href="${rc.contextPath}/companyManage/list";
		}
		function sureExcel(){
			if($("#excelUrl").val()){
				$("#excelCompanyForm").submit();
			}else{
				custom_alert("请先上传文件",3,3000);
			}
		} 
	</script>
</html>