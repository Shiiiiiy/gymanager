<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>横幅管理</title>
    <link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script> 
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>横幅管理<small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/advertisement/bannerlist.do">
        <div id="searchDiv">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                   
                    <div class="input-group">
                        <span class="input-group-addon">横幅位置</span>
                        <input type="text" class="form-control" value="${(tfileTitle)!''}" id="fileTitle" name="fileTitle" placeholder="请输入横幅位置">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
			            <a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>&nbsp;&nbsp;&nbsp;
			            <a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
                    </div>
                </div>
           </div>
        </div>

	        
        <a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>	 
        <span style="color:red;" class="thehint">* 横幅最佳尺寸为1920X100像素</span><br></br>
        <div class="table-responsive">
            <table class="table table-bordered table-striped table-hover mytable">
                <thead>
                    <tr>
                        <th width="48px">序号</th>
                        <th width="222px">横幅位置</th>
                        <th width="">缩略图</th>
                        <th width="100px">操作</th>
                    </tr>
                </thead>
                <tbody id="Searchresult"></tbody>
                <tbody id="hiddentable">
                    <#if page??>
						<#list page.list as news>
							<tr>
								<td><div class="zjh">${news_index+1 }</div></td>
								<td><div class="zjh"><a id="${'IMG2'+news.ID}" href="/gykjy/plugins/images/allImages/${news.FILE_NAME}" target="_blank">${(news.FILE_TITLE)!"" }</a></div></td>
								<td><div class="zjh"><img id="${'IMG'+news.ID}" width="550px" heighth="20px" src="/gykjy/plugins/images/allImages/${news.FILE_NAME}"/></div></td>
								<td>
									<a class="btn black" href="javaScript:void(0)" onclick="uploadSource('${news.ID}')"><i class="icon-cloud"></i> 上传</a>&nbsp;
								</td>
							</tr>
						</#list> 
					</#if>
										
										
                </tbody>
                <tfoot>
                    <tr>
                        <td colspan="5">
                           <#if page??> ${page.pageTool}</#if>
						</td>
                    </tr>
                </tfoot>
            </table>
        
       <div id="newsall" style="display:none;">确认删除？</div>
       <div id="newsstatus" style="display:none;">
       	   是否<span id="newsstatus_span"></span>该项？
	   </div>
    </div>
    </form>
    </div>
</body>
<script type="text/javascript">
	$(function () {
		/*选择器*/
		$('.form_datetime').datetimepicker({
		    language: 'zh-CN',
		    weekStart: 1,
		    todayBtn:  1,
			autoclose: 1,
			todayHighlight: 1,
			startView: 2,
			minView: 2,
			forceParse: 0
		});
		var X = $('.news-time').offset().top;
		$('.datetimepicker').css("top", X);
	});
	function uploadSource(t){
		type = t;
		file.upload({
			contextPath:"${rc.contextPath}", //固定写法
			obj:this, //固定写法
			callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
			callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
			maxFileNum:1, //队列中最大文件数量
			fileType:'*.jpg', //可选文件类型
			fileTypeDesc:'所有文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
			saveLocation:'/gykjy/plugins/images/allImages', //可选参数,指定保存路径(一个TOMCAT下跑两个项目互访文件时用,请以 "/项目名"开头)			
		}); 
	}
	
	function callPerComplete(obj,data){//保存资源操作
		var id = "";
		var name = "";
		eval('var json = ' + data);
		if(json.result){ //&& json.pictureFile)
			//将返回的地址信息存入数据库 异步
			$.ajax({
		        url:"${rc.contextPath}/advertisement/savebanner.do",
		        type:"post",
		        async:false,
		        data:{"id":type,"fileName":json.fileNameInServer},
		        dataType:"text",
		        success : function(obj){
		            if("true" == obj){//保存成功
						//alert("OK");
		            }
		        }
		    });
			//页面显示
			$("#IMG"+type).attr("src","/gykjy/plugins/images/allImages/"+json.fileNameInServer);
			$("#IMG2"+type).attr("href","/gykjy/plugins/images/allImages/"+json.fileNameInServer);
		}
	}
	function callbackAllComplete(obj){
	}
	//点击查询
	function formSubmit(){
		$("#pageNo").val(1);
		$("#searchForm").submit();
	}
	
	//清空查询条件
	function cleanAll(){
	    $("#fileTitle").val("");
	}

</script>
</html>
