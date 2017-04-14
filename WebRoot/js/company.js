(function($){
	$.fn.extend({
		company:function(){
			$(this).click(function(){
				var $obj = $(this);
				var href =  window.document.location.href;
				var pathName = window.document.location.pathname;
				var pos = href.indexOf(pathName);
				var localhostPath = href.substring(0, pos);
				var projectName = pathName.substring(0, pathName.substr(1).indexOf('/') + 1);
				var src =  localhostPath+projectName;
				var index=layer.open({
				  title: '产品信息',
				  btn:['确定','取消'],
				  area:'900px',
				  cancel: function(){ 
					  
				  },
				  btn1:function(){
					  var companyIds = $("#myIframe")[0].contentWindow.getChecked();
					  if(companyIds==""){
						  if($("#myIframe")[0].contentWindow.isInfoShow()){
							  $("#myIframe")[0].contentWindow.show();
						  }else{
							  $("#myIframe")[0].contentWindow.changeDiv();
						  }
					  }else {
						  if($("#myIframe")[0].contentWindow.isInfoShow()){
							  callback(companyIds);
							  layer.close(index);
						  }else{
							  $("#myIframe")[0].contentWindow.changeDiv();
						  }
					  }
				  },
				  btn2:function(){
					  
				  },
				  content: '<div style="height:600px;width:100%"><iframe id="myIframe" style="width:100%;height:600px;" frameborder="0" src="'+src+'/companyManage/list.do?param=layer"></iframe></div>'
				});	/**修改了一下高度*/
			});
			
			
		}
	
	});
})(jQuery);