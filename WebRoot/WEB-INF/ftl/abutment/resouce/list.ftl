<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>新闻管理</title>
    <link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>  
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>资源文件<small class="font-en caps"></small></h2></div>
        <div class="tabfix">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist" >
                <li role="presentation" id="li1"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" onclick="changeTab(1)">产业研究</a>
                <li role="presentation" id="li2"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="changeTab(2)" >招聘服务</a>
                <li role="presentation" id="li3"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab" onclick="changeTab(3)">诚信认证机构推荐</a>
                <li role="presentation" id="li4"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab" onclick="changeTab(4)" >下载专区</a>
            </ul>
            <form action="">
            <div class="tab-content">
            	
                <div role="tabpanel" id="tab1" class="tab-pane">
                	<div class="row" >
			        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				            <div class="input-group">
				                <span class="input-group-addon">产业研究</label></span>
				                <select class="form-control" name="research" id="research" style="width:180px;" onchange="changeResearchType();">
				                    <option value="FILE_E1" <#if researchType == "FILE_E1">selected</#if> >产业成功案例</option>
				                    <option value="FILE_E2" <#if researchType == "FILE_E2">selected</#if> >产业研究报告</option>
				                </select>
				            </div>
				        </div>
		        	</div>
		        	<br/>
                	<div class="btn-group" style="">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSourceFile('RESEARCH','MODULE_J_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 请上传文件</span>
					<div class="row">
					<#if industryReserchList??>
						<#list industryReserchList as file>
							<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
								<div class="thumb-box">
									<img id="img" src="${rc.contextPath}/images/files.png" class="img-responsive" alt="">
									<h3 class="f14 tc" title="${(file.fileTitle)!''}">${(file.fileTitle)!"" }&nbsp;</h3>
									<p class="tc img-btn">
										<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}','${(file.sort)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
										<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
									</p>
								</div>
							</div>	
						</#list>
					</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab2" class="tab-pane">
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('FILE_ZHONGJIE','MODULE_J_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸150X90像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="${file.filePath}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file.fileTitle)!'' }">${(file.fileTitle)!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}','${(file.sort)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
							</#list>
						</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab3" class="tab-pane">
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('FILE_CHENGXIN','')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸133X90像素</span>
					<div class="row">
						<#if certificationbodys??>
							<#list certificationbodys as file>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="${file.filePath}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file.fileTitle)!'' }">${(file.fileTitle)!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}','${(file.sort)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
							</#list>
						</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab4" class="tab-pane">
                	<div class="row" >
			        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				            <div class="input-group">
				                <span class="input-group-addon">文件位置</label></span>
				                <select class="form-control" name="downloadType" id="downloadType" style="width:180px;" onchange="changeDownloadType();">
				                    <#if mapList??>
				                    	<#list mapList as map>
						                    <#if downloadType?? && downloadType == map["ID"] >
					                			<option value="${map['ID']}" selected>${(map["NAME"])!""}</option>
					                		<#else>
					                			<option value="${map['ID']}">${(map["NAME"])!""}</option>
					                		</#if>
				                    	</#list>
				                    </#if>
				                </select>
				            </div>
				        </div>
		        	</div>
		        	<br/>
                	<div class="btn-group" style="">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSourceFile('DOWNLOAD','MODULE_J_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 请上传文件</span>
					<div class="row">
						<#if downloads??>
							<#list downloads as file>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="${rc.contextPath}/images/files.png" class="img-responsive" alt="">
										<h3 class="f14 tc" title="${(file.fileTitle)!'' }">${(file.fileTitle)!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}','${(file.sort)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
							</#list>
						</#if>
					</div>
                </div>
                
            </div>
            <div id="deleteDiv" style="display:none;">确认删除？</div>
            <div id="editDiv" style="display:none;">
            	<div class="row">
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">图片标题<span style="color:red;">&nbsp;</span></span>
	                        <input type="text" class="form-control" id="fileTitle" placeholder="请输入图片标题" >
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">资源序号<span style="color:red;">*</span></span>
	                        <input type="text" class="form-control" id="sort" placeholder="请输入资源序号">
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;" id="turl">
	                    <div class="input-group">
	                        <span class="input-group-addon">目标路径<span style="color:red;">&nbsp;</span></span>
	                        <input type="text" class="form-control" id="url" placeholder="请输入目标路径,如:https://www.baidu.com">
	                    </div>
	                </div>
	                <!-- <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10">
	                    <div class="input-group">
	                        <span class="input-group-addon">路径类型</span>
	                        <label class="input-group-addon gray-bg" style="width:46px;">
				            	<input type="radio" aria-label="..." value="URL_A" name="urlType" >
				            </label>
					        <label class="form-control">站内链接</label>
					        <label class="input-group-addon gray-bg" style="width:46px;">
				            	<input type="radio" aria-label="..." value="URL_B" name="urlType" >
				            </label>
					        <label class="form-control">站外链接</label>
	                    </div>
	                </div> -->
	             </div>
            </div>
            </form>
        </div>
    </div>
</body>
<script type="text/javascript">
	var type = "";
	var parentCode = "";
	var dtype = "";
	var rtype = "";
	var flag = "${flag}";
	function uploadSource(t,p){
		if(t == "DOWNLOAD"){
			type = $("#downloadType").val();
			dtype = type;
		}else if (t == "RESEARCH"){
			type = $("#research").val();
			rtype = type;
		}else type = t;
		parentCode = p;
		
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:'*.jpg;*.png;*.gif;*.bmp', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			saveLocation:'${saveLocation}', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
		}); 
	}
	function uploadSourceFile(t,p){
		if(t == "DOWNLOAD"){
			type = $("#downloadType").val();
			dtype = type;
		}else if (t == "RESEARCH"){
			type = $("#research").val();
			rtype = type;
		}else type = t;
		parentCode = p;
		
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:'*.pdf;*.word;*.ppt;*.doc;*.wordx;*.pptx;*.docx', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			saveLocation:'/gykjy/plugins/images/allFiles', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
		}); 
	}
	function callPerComplete(obj,data){
		var id = "";
		var name = "";
		eval('var json = ' + data);
		if(json.result){
			//将返回的地址信息存入数据库
			//window.location.href="${rc.contextPath}/resouce/save/saveresouce?fileName="+json.fileNameInServer+"&fileType="+type+"&flag="+flag+"&parentCode="+parentCode;
			$.ajax({
				url	 :"${rc.contextPath}/resouce/save/saveresouce",
				type :"post",
				async:false,
				data :{
					fileName : json.fileNameInServer,
					fileType : type,
					flag	 : flag,
					parentCode : parentCode,
					//fileTitle  : json.originalfileName,
					
				},
				success:function(data){
					//do nothing
				}
			});
		}
	}
	function callbackAllComplete(obj){
		window.location.href="${rc.contextPath}/resouce/query/list?flag="+flag+"&downloadType="+dtype+"&researchType="+rtype;
	}
	
	function editFile(fileId,fileTitle,url,urlType,sort){
		
		$("input[name='urlType'][value='" + urlType + "']").attr("checked", true);
		showDialog("系统提示","editDiv","sure('"+fileId+"')","cancel()","closeDialog()",100,true);
		if(flag=="1"  || flag=="4"){
			$("#turl").css("display","none");
		}
		$("#fileTitle").attr("value",fileTitle);
		$("#url").attr("value",url);
		$("#sort").attr("value",sort);
	}
	
	function deleteFile(fileId){
		showDialog("系统提示","deleteDiv","sure1('"+fileId+"')","cancel()","closeDialog()",100,true);
	}
	
	function sure(fileId){
	
		var tt = validateFormObject();
		tt.checkNumber("sort","资源序号",true,1,999);
		
		if(tt.validate()){
			var urlType = $("input[name='urlType']:checked").val();
			if(urlType == undefined || urlType == "undefined" || urlType == "null" || urlType == "NULL"){
				urlType = "";
			}
			$.ajax({
				url:"${rc.contextPath}/resouce/opt/updateresource",
				type:"post",
				data : {
					fileId : fileId,
					fileTitle : $("#fileTitle").val(),
					url : $("#url").val(),
					urlType : urlType,
					sort : $("#sort").val()
				},
				async:false,
				success:function(){
					dtype = $("#downloadType").val();
					rtype = $("#research").val();
					window.location.href="${rc.contextPath}/resouce/query/list?flag="+flag+"&downloadType="+dtype+"&researchType="+rtype; 
				}
			});
		}else{
			$("#selUserDislogDiv_editDiv").fadeIn();
		}
	}
	
	function sure1(fileId){
		dtype = $("#downloadType").val();
		rtype = $("#research").val();
		window.location.href="${rc.contextPath}/resouce/opt/deletefile?fileId="+fileId+"&flag="+flag+"&downloadType="+dtype+"&researchType="+rtype;
	}
	
	function cancel(){
		$("input[name='urlType']").removeAttr("checked");
	}
	
	function closeDialog(){
		$("input[name='urlType']").removeAttr("checked");
	}
	
	//定位当前操作的tab页
	$(function () {
		var flag = "${flag}";
		
		if(flag == "1"){
			$("#li1").addClass("active");
			$("#tab1").addClass("active");
		}else{
			$("#li1").removeClass("active");
			$("#tab1").removeClass("active");
		}
		if(flag == "2"){
			$("#li2").addClass("active");
			$("#tab2").addClass("active");
		}else{
			$("#li2").removeClass("active");
			$("#tab2").removeClass("active");
		}
		if(flag == "3"){
			$("#li3").addClass("active");
			$("#tab3").addClass("active");
		}else{
			$("#li3").removeClass("active");
			$("#tab3").removeClass("active");
		}
		if(flag == "4"){
			$("#li4").addClass("active");
			$("#tab4").addClass("active");
		}else{
			$("#li4").removeClass("active");
			$("#tab4").removeClass("active");
		}
	});
	
	function changeTab(t){
		flag = t;
	}
	
	var changeDownloadType = function(){
		var downloadType = $("#downloadType").val();
		window.location.href="${rc.contextPath}/resouce/query/list?flag="+flag+"&downloadType=" + downloadType;
	}
	
	var changeResearchType = function(){
		var researchType = $("#research").val();
		window.location.href="${rc.contextPath}/resouce/query/list?flag="+flag+"&researchType=" + researchType;
	}
</script>
</html>
