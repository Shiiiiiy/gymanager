(function ($) {
    $.fn.extend({
    	/**
    	 * 上传组件接口,此方法用于整个页面只有一个上传按钮的情况
    	 * 参数:callbackPerComplete -> 每个文件上传完成以后会回调此方法,也就是每个文件触发一次此方法
    	 * 参数:callbackAllComplete -> 所有文件上传完成以后,回调此方法,触发一次
    	 */
        "upload": function (callPerComplete,callbackAllComplete) {
        	if(!this.is("button"))return;
        	var uploadBox = 
        		"<div class=\"modal-scrollable\" style=\"display:none; margin-top: 100px;width:396px;\" id='file_upload_box_id'>"+
        		"	<div class=\"modal-header\">" +				
        		"		<button class=\"close\" id='close_btn'></button>" +	
        		"		<h5>文件上传</h5>"	+
        		"	</div>" +
        		"	<div class=\"modal-body\">" +
        		"		<div id='fileQueue'></div>" + 
        		"		<input type='file' name='uploadify' id='uploadify' />" +
        		"	</div>" +
        		"	<div class=\"modal-footer\">" +
        		"		<button id='start-upload' style='display:none' class=\"btn green\">开始上传</button>" +
        		"	</div>" + 
        		"</div>";
        	
        	$("body").after(uploadBox);   
        	
        	this.click(function(){
        		$("#file_upload_box_id").show();
        	});
        	
        	$('#close_btn').click(function(){
            	$("#file_upload_box_id").fadeOut(200);
            });
        	
        	$('#start-upload').click(function(){
        		$('#uploadify').uploadify('upload','*');
        		$('#start-upload').hide();
        	});
        	
        	$("#uploadify").uploadify({  
                'uploader' : '/gymanager/file/upload',
                'swf' : '../uploadify/js/uploadify.swf',
                'cancelImg' : '../uploadify/img/uploadify-cancel.png',  
                'buttonClass':'',
                'method':'post',
                'removeCompleted':true,
                //'fileObjName':'fileObj',
                //'folder' : 'uploads',//您想将文件保存到的路径  
                'queueID' : 'fileQueue',//与id对应  
                'queueSizeLimit' : 5,  //队列中最大文件数量
                'fileTypeDesc' : '任意文件',  
                'fileTypeExt' : '*.*', //控制可上传文件的扩展名，启用本项时需同时声明fileDesc  
                'auto' : false,  
                'multi' : true,  
                'simUploadLimit' : 2,  
                'buttonText' : '选择文件',
                'width':'82',
                'height':'34',
                 //当选择文件对话框打开时触发
                'onDialogOpen' : function() {  
                    // do something if need...
                },  
                //当每个文件添加至队列后触发
                'onSelect' : function(file) {  
                	//队列中有文件时,显示"开始上传"按钮
                	if($("#start-upload").is(":hidden")){
                		$("#start-upload").show();
                	}
                },  
                 //当文件选定发生错误时触发  
                'onSelectError' : function(file,errorCode,errorMsg) {
                    // do something if need...
                },  
                //当文件选择对话框关闭时触发  
                'onDialogClose' : function(swfuploadifyQueue) {
                    // do something if need... 
                },  
                //当队列中的所有文件全部完成上传时触发  
                'onQueueComplete' : function(stats) {
                	$("#file_upload_box_id").fadeOut(2000);
                	callbackAllComplete();
                },  
                //队列中的每个文件上传完成时触发一次
                'onUploadComplete' : function(file,swfuploadifyQueue) {  
                    // do something if need...
                },  
                //上传文件出错是触发（每个出错文件触发一次）
                'onUploadError' : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {  
                	// do something if need...
                },  
                //上传进度发生变更时触发
                'onUploadProgress' : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {  
                	// do something if need...
                }, 
                //上传开始时触发（每个文件触发一次）
                'onUploadStart': function(file) {  
                	// do something if need...
                },  
                //上传完成时触发（每个文件触发一次）  
                'onUploadSuccess' : function(file,data,response) {
                	if(typeof callPerComplete === "function"){
                		callPerComplete(data);
                	}
                },
                //取消任务时触发
                'onCancel' : function(){
                	// 队列中的文件都被移除后, 上传按钮隐藏
                	if($("#fileQueue .uploadify-queue-item").length == 1){
                		$("#start-upload").hide();
                	}
                },
                //清空队列时触发
                'onClearQueue':function(){
                	// do something if need...
                }
            });
        }
    });
})(jQuery);

/**
 * 命名空间,所有与文件相关的操作写在这个对象下面
 */
var file = {};

/**
 * 上传组件接口,此方法用于页面存在列表上传的情况
 * 参数:obj(dom) -> 绑定事件的对象, 一般传入this即可
 * 参数:callbackPerComplete(function) -> 每个文件上传完成以后会回调此方法,也就是每个文件触发一次此方法
 * 参数:callbackAllComplete(function) -> 所有文件上传完成以后,回调此方法,触发一次
 * 参数:maxFileNum (number)-> 可以选择的文件个数
 * 参数:fileType (string)-> 限制文件类型如 "*.jpg",多数类型以";"隔开
 * 参数:fileTypeDesc(string0 -> 限制性说明 如"图片文件"
 * ->obj,callPerComplete,callbackAllComplete,maxFileNum,fileType,fileTypeDesc,saveLocation
 */
file.upload = function(jsonObj){
	
	var uploadBox = 
		"<div class=\"modal-scrollable\" style=\"display:none; margin-top: 100px;width:396px;\" id='file_upload_box_id'>"+
		"	<div class=\"modal-header\">" +				
		"		<button class=\"close\" id='close_btn'></button>" +	
		"		<h5>文件上传</h5>"	+
		"	</div>" +
		"	<div class=\"modal-body\">" +
		"		<div id='fileQueue'></div>" + 
		"		<input type='file' name='uploadify' id='uploadify' />" +
		"	</div>" +
		"	<div class=\"modal-footer\">" +
		"		<button id='start-upload' style='display:none' class=\"btn green\">开始上传</button>" +
		"	</div>" + 
		"</div>";
	
    if($("#file_upload_box_id").length == 0){
    	$("body").after(uploadBox);
    	$('#start-upload').click(function(){
    		$('#uploadify').uploadify('upload','*');
    		$(this).hide();
    	});
        
        $('#close_btn').click(function(){
        	$("#file_upload_box_id").fadeOut(100);
        	//清空队列
        	$("#uploadify").uploadify('cancel', '*');
        	$("#start-upload").hide();
        });
    }
       
    if(typeof file.obj === 'undefined'){
		file.obj = jsonObj.obj;
		file.uploadInit(jsonObj);
	}
    
    if(file.obj != jsonObj.obj){
    	//点击的是不同的上传按钮,重新初始化
    	file.uploadInit(jsonObj);
    }
    
    file.obj = jsonObj.obj; //记录当前点击的对象
    
    $("#file_upload_box_id").css("display","block");
};

file.uploadInit = function(jsonObj){
	
	jsonObj.maxFileNum = typeof jsonObj.maxFileNum === 'number' ? jsonObj.maxFileNum : 5;
	jsonObj.fileType = typeof jsonObj.fileType === 'string' ? jsonObj.fileType : '*.*';
	jsonObj.fileTypeDesc = typeof jsonObj.fileTypeDesc === 'string' ? jsonObj.fileTypeDesc : '所有文件';
	jsonObj.saveLocation = typeof jsonObj.saveLocation === 'string' ? jsonObj.saveLocation : "";
    
    $("#uploadify").uploadify({  
        uploader   : jsonObj.contextPath + '/file/upload',
        swf        : jsonObj.contextPath + '/uploadify/js/uploadify.swf',
        cancelImg  : jsonObj.contextPath + '/uploadify/img/uploadify-cancel.png',  
        buttonClass:'',
        method     :'get',
        removeCompleted :true,
        //'fileObjName':'fileObj',
        //'folder' : 'uploads',//您想将文件保存到的路径  
        queueID    : 'fileQueue',//与id对应  
        queueSizeLimit  : jsonObj.maxFileNum,  //队列中最大文件数量
        fileTypeDesc    : jsonObj.fileTypeDesc,  
        fileTypeExts    : jsonObj.fileType, //控制可上传文件的扩展名，启用本项时需同时声明fileDesc  
        formData: {
            location   : jsonObj.saveLocation || "" //,
            //replaceStr : jsonObj.replaceStr || ""
        },
        auto    : false,  
        multi   : true,  
        simUploadLimit : 2,  
        buttonText     : '选择文件',
        width          :'82',
        height    	   :'34',
         //当选择文件对话框打开时触发
        onDialogOpen : function() {  
            // do something if need...
        },  
        //当每个文件添加至队列后触发
        onSelect : function(file) {  
        	//队列中有文件时,显示"开始上传"按钮
        	if($("#start-upload").is(":hidden")){
        		$("#start-upload").show();
        	}
        	var curNum = $("#fileQueue .uploadify-queue-item").length;
        	if(curNum == jsonObj.maxFileNum){
        		$("#uploadify-button").prop("disabled",true);
        	}
        },
         //当文件选定发生错误时触发  
        onSelectError : function(file,errorCode,errorMsg) {
            // do something if need...
        },  
        //当文件选择对话框关闭时触发  
        onDialogClose : function(swfuploadifyQueue) {
            // do something if need... 
        },  
        //当队列中的所有文件全部完成上传时触发  
        onQueueComplete : function(stats) {
        	$("#file_upload_box_id").fadeOut(2000);
        	if(typeof jsonObj.callbackAllComplete === "function"){
        		jsonObj.callbackAllComplete(jsonObj.obj);
        	}
        },  
        //队列中的每个文件上传完成时触发一次
        onUploadComplete : function(file,swfuploadifyQueue) {  
            // do something if need...
        },  
        //上传文件出错是触发（每个出错文件触发一次）
        onUploadError : function(file,errorCode,errorMsg,errorString,swfuploadifyQueue) {  
        	// do something if need...
        },  
        //上传进度发生变更时触发
        onUploadProgress : function(file,fileBytesLoaded,fileTotalBytes,queueBytesLoaded,swfuploadifyQueueUploadSize) {  
        	// do something if need...
        }, 
        //上传开始时触发（每个文件触发一次）
        onUploadStart: function(file) {  
        	// do something if need...
        	//$("#uploadify").uploadify("settings", "formData", { "key": fileTypeDesc });
        },  
        //上传完成时触发（每个文件触发一次）  
        onUploadSuccess : function(file,data,response) {
        	if(typeof jsonObj.callPerComplete === "function"){
        		jsonObj.callPerComplete(jsonObj.obj,data);
        	}
        },
        //取消任务时触发
        onCancel : function(){
        	// 队列中的文件都被移除后, 上传按钮隐藏
        	if($("#fileQueue .uploadify-queue-item").length == 1){
        		$("#start-upload").hide();
        	}
        },
        //清空队列时触发
        onClearQueue :function(){
        	// do something if need...
        }
    });
};

/**
 * 文件下载的前端JS方法
 * 参数:只需一个参数,string类型,文件下载的请求地址
 * 说明:本次封装的JS以及后台的部分方法不包括文件保存后的路径存入数据库功能,所以此JS方法的请求地址为用户自定的,
 * 此请求的后台应该从数据库查出对应的文件保存地址,
 * 并调用 FileUtils.download(String filePath,HttpServletResponse response)即可实现下载.
 */
file.download = function(obj){
	var downloadIframe = "<iframe id=\"file_download_iframe_id\" style=\"display:none\"></iframe>";
	var aIfram = $("#file_download_iframe_id");
	if(aIfram.length == 0){
		$("body").after(downloadIframe);
	}
	aIfram = $("#file_download_iframe_id");
	var hasFile = false;
	$.ajax({
        type: "post",
        dataType: "json",
        async:false,
        url: obj.url,
        data: {"validate":true},
        success: function(data) {
        	if(data.exists){
        		hasFile = true;
        	}
        }
	});
	
	if(hasFile){
		obj.url = obj.url.indexOf("?") == -1 
		? (obj.url += "?hasFile=true") : (obj.url += "&hasFile=true");
		obj.url += ("&n="+Math.random()); //防止缓存
		aIfram.attr("src",obj.url);
	}else{
		alert("无效的请求或文件不存在!");
	}
};

/**
 * 
 * 图片预览的方法,只需一个参数,string类型,图片预览的请求地址
 * 同样,在此url对应的后台,应查询出图片的绝对地址
 */
file.pictureView = function(obj){
	
	var viewBox = 
		"<div class=\"modal-scrollable\" style=\"display:none; margin-top: 50px;\" id='file_picture_view_id'>"+
		"	<div class=\"modal-header\">" +				
		"		<button class=\"close\" id='close_btn'></button>" +	
		"		<h5>图片查看</h5>"	+
		"	</div>" +
		"	<div class=\"modal-body\">" +
		"		<iframe id='viewarea' style=\"width:528px;height:338px;border:1px solid transparent;\"></iframe>" +
		"	</div>" +
		"</div>";
	
	if($("#file_picture_view_id").length == 0){
		$("body").after(viewBox);
	}
	
	$('#close_btn').click(function(){
    	$("#file_picture_view_id").fadeOut(200);
    });
	
	if($("#file_picture_view_id").is(":hidden")){
		$("#viewarea").attr("src",obj.url);
		$('#file_picture_view_id').show();
	}
};
