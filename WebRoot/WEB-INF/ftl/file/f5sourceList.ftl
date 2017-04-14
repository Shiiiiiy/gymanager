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
                <li role="presentation" id="li1"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" onclick="changeTab(1)">轮播</a>
                <li role="presentation" id="li2"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="changeTab(2)" >创新成果</a>
                <li role="presentation" id="li3"><a href="#tab3" aria-controls="tab3" role="tab" data-toggle="tab" onclick="changeTab(3)">创新平台</a>
                <li role="presentation" id="li4"><a href="#tab4" aria-controls="tab4" role="tab" data-toggle="tab" onclick="changeTab(4)" >产学研合作图</a>
                <li role="presentation" id="li5"><a href="#tab5" aria-controls="tab5" role="tab" data-toggle="tab" onclick="changeTab(5)">产学研示范工程</a>
                <li role="presentation" id="li6"><a href="#tab6" aria-controls="tab6" role="tab" data-toggle="tab" onclick="changeTab(6)" >运行分析图</a>
            </ul>
            <form action="">
            <div class="tab-content">
            	
                <div role="tabpanel" id="tab1" class="tab-pane">
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('${slider}','MODULE_G_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file.fileType?? && file.fileType == slider>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="${file.filePath}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file.fileTitle)!''}">${(file.fileTitle)!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}','${(file.sort)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
								</#if>
							</#list>
						</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab2" class="tab-pane">
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('${result}','MODULE_G_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file.fileType?? && file.fileType == result>
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
								</#if>
							</#list>
						</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab3" class="tab-pane">
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('${stage}','MODULE_G_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file.fileType?? && file.fileType == stage>
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
								</#if>
							</#list>
						</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab4" class="tab-pane"><!--产学研合作图--->
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('FILE_COOPERA','MODULE_G_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file.fileType?? && file.fileType == "FILE_COOPERA">
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
								</#if>
							</#list>
						</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab5" class="tab-pane"><!--产学研示范工程-->
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSourceFile('FILE_B','MODULE_G_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 请上传文件</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file.fileType?? && file.fileType == "FILE_B">
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="/gymanager/images/files.png" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file.fileTitle)!'' }">${(file.fileTitle)!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file.id)!''}','${(file.fileTitle)!''}','${(file.url)!''}','${(file.urlType)!''}','${(file.sort)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file.id)!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
										</p>
									</div>
								</div>	
								</#if>
							</#list>
						</#if>
					</div>
                </div>
                
                <div role="tabpanel" id="tab6" class="tab-pane">
                	<div class="btn-group">
						<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('${statistics}','MODULE_G_MAIN')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X315像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file.fileType?? && file.fileType == statistics && file.parentCode?? && file.parentCode=="MODULE_G_MAIN">
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
								</#if>
							</#list>
						</#if>
					</div>
                </div>
            </div>
            <div id="deleteDiv" style="display:none;">确认删除？</div>
            <div id="editDiv" style="display:none;">
            	<div class="row">
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10">
	                    <div class="input-group">
	                        <span class="input-group-addon">图片标题&nbsp;</span>
	                        <input type="text" class="form-control" id="fileTitle" placeholder="请输入图片标题" >
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">资源序号<span style="color:red;">*</span></span>
	                        <input type="text" class="form-control" id="sort" placeholder="请输入资源序号">
	                    </div>
	                </div>
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:15px;" id="turl">
	                    <div class="input-group">
	                        <span class="input-group-addon">目标路径&nbsp;</span>
	                        <input type="text" class="form-control" id="url" placeholder="请输入目标路径,如https://www.baidu.com/">
	                    </div>
	                </div>
	                <!--
	                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12 mb10" style="margin-top:12px;">
	                    <div class="input-group">
	                        <span class="input-group-addon">路径类型&nbsp;</span>
	                        <label class="input-group-addon gray-bg" style="width:46px;">
				            	<input type="radio" aria-label="..." value="URL_A" name="urlType" >
				            </label>
					        <label class="form-control">站内链接</label>
					        <label class="input-group-addon gray-bg" style="width:46px;">
				            	<input type="radio" aria-label="..." value="URL_B" name="urlType" >
				            </label>
					        <label class="form-control">站外链接</label>
	                    </div>
	                </div>
	                -->
	             </div>
            </div>
            </form>
        </div>
    </div>
</body>
<script type="text/javascript">
var type = "";
var parentCode = "";
var flag = "${flag}";
	function uploadSource(t,p){
		type = t;
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
	function uploadSourceFile(t,p){//上传文件
		type = t;
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
		if(json.result){ //&& json.pictureFile)
			//将返回的地址信息存入数据库
			window.location.href="${rc.contextPath}/f5source/opt-add/saveF5source?fileName="+json.fileNameInServer+"&fileType="+type+"&flag="+flag+"&parentCode="+parentCode;
		}
	}
	function callbackAllComplete(obj){
	}
	
	function editFile(fileId,fileTitle,url,urlType,sort){
		$("input[name='urlType'][value='" + urlType + "']").attr("checked", true);
		showDialog("系统提示","editDiv","sure('"+fileId+"')","cancel()","closeDialog()",100,true,false);
		if(flag=="2" || flag=="3" || flag=="5"  || flag=="6" ){//创新成果图 + 技术创新平台 + 示范工程  + 统计图
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
		tt.checkLength("fileTitle","图片标题",false,1,20); 
		tt.checkLength("url","目标路径",false,1,500);
		
		if(tt.validate()){
			var urlType = $("input[name='urlType']:checked").val();
			if(urlType == undefined || urlType == "undefined" || urlType == "null" || urlType == "NULL"){
				urlType = "";
			}
			window.location.href="${rc.contextPath}/f5source/opt-update/updateFileTitle5.do?fileId="+fileId+"&fileTitle="+encodeURI(encodeURI($("#fileTitle").val()))+"&url="+encodeURI(encodeURI($("#url").val()))+"&urlType="+urlType+"&sort="+$("#sort").val()+"&flag="+flag;
			showDialog_close("editDiv");
		}
	}
	
	function sure1(fileId){
		window.location.href="${rc.contextPath}/f5source/opt-delete/deleteFile5.do?fileId="+fileId+"&flag="+flag;
	}
	
	function cancel(){
		$("input[name='urlType']").removeAttr("checked");
		showDialog_close("editDiv");
	}
	
	function closeDialog(){
		$("input[name='urlType']").removeAttr("checked");
		showDialog_close("editDiv");
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
		if(flag == "5"){
			$("#li5").addClass("active");
			$("#tab5").addClass("active");
		}else{
			$("#li5").removeClass("active");
			$("#tab5").removeClass("active");
		}
		if(flag == "6"){
			$("#li6").addClass("active");
			$("#tab6").addClass("active");
		}else{
			$("#li6").removeClass("active");
			$("#tab6").removeClass("active");
		}
		
	});
	
	function changeTab(t){
		flag = t;
	}
	
</script>
</html>
