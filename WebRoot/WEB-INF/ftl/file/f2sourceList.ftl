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
        
 		  <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="">

       		<div class="row" >
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				    <div class="input-group">
				        <span class="input-group-addon">分类名称</label></span>
				        <select class="form-control"  name="mCode" id="moduleType" onchange="getNextClass(this.value);" style="width:180px;">
                            <option value="">请选择..</option>
                            <#if type0??>
                            	<option value="N" <#if toActive?? && toActive==type0>selected="selected"</#if> >主页资源</option>
                            </#if>
                            <#if type1??>
                            	<option value="INDUSTRY_A" <#if toActive?? && toActive==type1 >selected="selected"</#if> >工业产品</option>
                            </#if>
                            <#if type2??>
                            	<option value="INDUSTRY_B" <#if toActive?? && toActive==type2 >selected="selected"</#if> >生产服务</option>
                            </#if>
				        </select>
				    </div>
				</div>
				
				<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				    <div class="input-group">
				        <span class="input-group-addon" id="nextServiceClass">资源位置</span>
				        <select class="form-control"  name="parentCode" id="ServiceClass" style="width:180px;" onchange="changeCode(this.value)"><!--依据这个查询-->
                        	<#if mapList??>
                        		<option value="">请选择..</option>
		                    	<#list mapList as map>
				                    <#if toActive2??  &&  toActive2 ==map["ID"] >
			                			<option value="${map['ID']}" selected>${(map["NAME"])!""}</option>
			                		<#else>
			                			<option value="${map['ID']}">${(map["NAME"])!""}</option>
			                		</#if>
		                    	</#list>
		                    <#elseif toActive2?? && "N"==toActive2>
		                    	<option value="">请选择..</option>
		                    	<option value="N" selected>主页资源</option>
		                    <#else>	
		                    	<option value="">请选择..</option>
		                    </#if>
				        </select>
				    </div>
				</div>
		        
        	</div>
  
       
        <div class="tabfix">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist" >
                <li role="presentation" id="li1"><a href="#tab1" aria-controls="tab1" role="tab" data-toggle="tab" onclick="changeTab1()">轮播</a>
                <li role="presentation" id="li2"><a href="#tab2" aria-controls="tab2" role="tab" data-toggle="tab" onclick="changeTab2()" >运行分析图</a>
            </ul>
            
            <div class="tab-content">
            	
                <div role="tabpanel" id="tab1" class="tab-pane">
                	<div class="btn-group">
						<a id="hideUpload1" class="btn black" href="javaScript:void(0)" onclick="uploadSource('${slider!""}')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X338像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file['FILE_TYPE']?? && file['FILE_TYPE'] == slider>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="/gykjy/plugins/images/allImages/${file['FILE_NAME']}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file['FILETITLE'])!''}">${(file['FILE_TITLE'])!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file.ID)!''}','${(file.FILE_TITLE)!''}','${(file.URL)!''}','${(file.URL_TYPE)!''}','${(file.SORT)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file['ID'])!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
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
						<a id="hideUpload2"  class="btn black" href="javaScript:void(0)" onclick="uploadSource('${statistics!""}')"><i class="icon-cloud"></i> 上传</a>
					</div>
					<span style="color:red;" class="thehint">* 最佳尺寸570X315像素</span>
					<div class="row">
						<#if fileList??>
							<#list fileList as file>
								<#if file['FILE_TYPE']?? && file['FILE_TYPE'] == statistics>
								<div class="col-xs-12 col-sm-6 col-md-3 col-lg-3">
									<div class="thumb-box">
										<img id="img" src="/gykjy/plugins/images/allImages/${file['FILE_NAME']}" class="img-responsive" alt="" />
										<h3 class="f14 tc" title="${(file['FILE_TITLE'])!'' }">${(file['FILE_TITLE'])!"" }&nbsp;</h3>
										<p class="tc img-btn">
											<a onclick="editFile('${(file['ID'])!''}','${(file['FILE_TITLE'])!''}','${(file['URL'])!''}','${(file['URL_TYPE'])!''}','${(file.SORT)!''}');" class="btn blue"><i class="icon-edit"></i> 编辑</a>&nbsp;
											<a onclick="deleteFile('${(file['ID'])!''}');"  class="btn black"><i class="icon-trash"></i> 删除</a>&nbsp;
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
	var flag = "${flag!''}";
	$(function () {
		var flag = "${flag!''}";
		if(flag == "1"){
			$("#li1").addClass("active");
			$("#li2").removeClass("active");
			$("#tab1").addClass("active");
			$("#tab2").removeClass("active");
		}else{
			$("#li1").removeClass("active");
			$("#li2").addClass("active");
			$("#tab1").removeClass("active");
			$("#tab2").addClass("active");
		}
		var zhi1=$("#ServiceClass").val();
		var zhi2=$("#mCode").val();
		if((""==zhi1||undefined==zhi1) && (""==zhi2||undefined==zhi2)){
			$("#hideUpload1").css("display","none");
			$("#hideUpload2").css("display","none");
			$(".thehint").css("display","none");
		}//隐藏上传
	});
	
	function changeTab2(){
		flag = "2";
	}
	
	function changeTab1(){
		flag = "1";
	}
		
	function uploadSource(t){
		type = t;
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:5, //队列中最大文件数量
			fileType:'*.jpg;*.png;*.gif;*.bmp', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			saveLocation:'/gykjy/plugins/images/allImages', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
		}); 
	}
	
	function callPerComplete(obj,data){//保存资源操作
		var id = "";
		var name = "";
		eval('var json = ' + data);
		if(json.result){ //&& json.pictureFile)
			//将返回的地址信息存入数据库
			//alert($("#ServiceClass").val()+" "+$("#moduleType").val());
			var aa="${rc.contextPath}/service/opt-save/saveresource?fileName="+json.fileNameInServer+"&fileType="+type+"&flag="+flag+"&parentCode="+$("#ServiceClass").val()+"&mCode="+$("#moduleType").val();
			window.location.href=aa;
		}
	}
	function callbackAllComplete(obj){
	}
	
	function editFile(fileId,fileTitle,url,urlType,sort){
		$("input[name='urlType'][value='" + urlType + "']").attr("checked", true);
		showDialog("系统提示","editDiv","sure('"+fileId+"')","cancel()","closeDialog()",100,true);
		if(flag=="2"){//运行分析图 
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
		//
		if(tt.validate()){
			var urlType = $("input[name='urlType']:checked").val();
			if(urlType == undefined || urlType == "undefined" || urlType == "null" || urlType == "NULL"){
				urlType = "";
			}
			window.location.href="${rc.contextPath}/service/opt-update/updateFileTitle.do?fileId="+fileId+"&fileTitle="+encodeURI(encodeURI($("#fileTitle").val()))+"&url="+encodeURI(encodeURI($("#url").val()))+"&urlType="+urlType+"&sort="+$("#sort").val()+"&flag="+flag+"&parentCode="+$("#ServiceClass").val()+"&mCode="+$("#moduleType").val();
		}else{
			$("#selUserDislogDiv_editDiv").show();
		}
	}
	function sure1(fileId){//删除操作 ok
		window.location.href="${rc.contextPath}/service/opt-delete/deleteFile.do?fileId="+fileId+"&flag="+flag+"&parentCode="+$("#ServiceClass").val()+"&mCode="+$("#moduleType").val();
	}
	
	function cancel(){
		$("input[name='urlType']").removeAttr("checked");
	}
	
	function closeDialog(){
		$("input[name='urlType']").removeAttr("checked");
	}
	
	
	function changeCode(O){//list 页面
		if(O!=""){
			window.location.href="${rc.contextPath}/service/opt-query/resource.do?parentCode="+$("#ServiceClass").val()+"&mCode="+$("#moduleType").val();
		}	
	}
	function getNextClass(O){ //查询二级 分类
        var types=O+"";
        if("N"==types){
            $("#ServiceClass").empty();
            $("#ServiceClass").append(
                    "<option value=''>请选择..</option>"+
                    "<option value='N'>主页资源</option>"
            );
        	return;
        }
        $.post("${rc.contextPath}/service/getServiceList.do",{"type":types},function(obj) {
            $("#ServiceClass").empty();
            $("#ServiceClass").append(
                 "<option value=''>请选择..</option>"
            );
            $.each(obj,function(index,zhi){
            	
                $("#ServiceClass").append(
                    "<option value='"+zhi.ID+ "' >" + zhi.NAME+ "</option>"
                );
            });
        },"json");
    }
	
</script>
</html>
