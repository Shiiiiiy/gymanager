<!DOCTYPE html>
<html>
	<head>
		<#include "/common/common.ftl" >
	    <title>新增产品</title>
	    <link rel="stylesheet" href="${rc.contextPath}/plugin/ztree/css/zTreeStyle.css" type="text/css">
	    <script type="text/javascript" src="${rc.contextPath}/plugin/ztree/js/jquery.ztree.core.js"></script>
	    <script type="text/javascript" src="${rc.contextPath}/plugin/ztree/js/jquery.ztree.excheck.js"></script>
	</head>
	<body>
		<div class="content-box">
			<div class="page-header">
	        	<h2>产品新增<small class="font-en caps"></small></h2>
	        </div>
	        <form class="form-inline search_list" role="form"  id="addProduct" method="post" >
		        <div class="row">
		        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			            <div class="input-group">
			                <span class="input-group-addon" style="width:105px;">产品名称 <span style="color:red;">*</span></span>
			                <input type="text" class="form-control" id="product_name" name="product_name" placeholder="请输入产品名称" style="width:200px;" value="${(product.product_name)!''}" >
			            </div>
			        </div>
		        </div>
		        
		        <div class="row">
		        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			            <div class="input-group">
			                <span class="input-group-addon" style="width:105px;">产品分类 <span style="color:red;">*</span></span>
			                <input type="hidden" id="product_type" value="${(product.product_type)!''}"   name="product_type" class="form-control" ></input>
			                <input type="text" readonly="readonly" class="form-control" onclick="getproduct_type()" id="product_typeName" name="product_typeName" placeholder="请输入产品分类" style="width:200px;" value="${(product.product_typeName)!''}" >
			            </div>
			        </div>
		        </div>
		        
		        <div class="row">
		        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			            <div class="input-group">
			                <span class="input-group-addon" style="width:105px;">批准文号</span>
			                <input type="text" class="form-control" id="product_num" name="product_num" placeholder="请输入批准文号" style="width:200px;" value="${(product.product_num)!''}" >
			            </div>
			        </div>
		        </div>
		        
		        <div class="row">
		        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			            <div class="input-group news-time">
			                <span class="input-group-addon" style="width:105px;">上市时间</span>
			                <div class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
		                        <input size="" type="text" style="width:200px;" value="${((product.product_time)?string('yyyy-MM-dd'))!''}" id="product_time" name="product_time" placeholder="请选上市时间" readonly class="form-control">
		                        <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
		                        <span class="add-on"><i class="icon-th iconfont"></i></span>
		                    </div>
		                    <input type="hidden" id="dtp_input1" value="" /><br />			
			            </div>
			        </div>
		        </div>
		        
		        <div class="row">
		        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			            <div class="input-group">
			                <span class="input-group-addon" style="width:105px;">产品功能<span style="color:red;">*</span></span>
			                <textarea class="form-control"  id="product_capacity" name="product_capacity" placeholder="请输入产品功能" style="width:200px;height:100px;">${(product.product_capacity)!""}</textarea>
			            </div>
			        </div>
		        </div>
		        
		        <div class="row">
		        	<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			            <div class="input-group">
			                <span class="input-group-addon" style="width:105px;">备注</span></span>
			                <textarea class="form-control"  id="comments" name="comments" placeholder="备注" style="width:200px;height:100px;">${(product.comments)!""}</textarea>
			            </div>
			        </div>
		        </div>
	        </form>
		</div>
	</body>
	<div class="input-group-addon"  style="width:350px;height:340px;display: none;z-index: 10000;position: absolute;" id="showDivtree">
		<a class="btn blue" href="javaScript:void(0)" onclick="saveTreeNode();"><i class="icon-ok"></i> 确定</a>
		<a class="btn black" href="javaScript:void(0)" onclick="TreeNodeEsc();"><i class="icon-remove"></i> 取消</a>
		<ul id="tree" class="ztree" style="width:340px;height:280px; overflow:auto;"></ul>
	</div>
	<script type="text/javascript">
		
		//基础信息的验证
		function validate_form_product(){
			var tt = validateFormObject();
			tt.checkLength("product_name","产品名称",true,1,20);
			tt.checkLength("product_typeName","产品分类",true);
			tt.checkLength("product_capacity","产品功能",true,1,100);
			return tt.validate();
		}
	
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
			
		function getproduct_type(){
			var x = $("#product_typeName").offset().top + $("#product_typeName").height()+10; 
			var y = $("#product_typeName").offset().left; 
			$("#showDivtree").offset({top:x,left:y}).show(100);
			zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
			var ids = $("#product_type").val();
			
		}
		
		var zTreeObj;
		setting = {
		    data: {  
	                simpleData: {  
	                    enable: true  
	                }  
	        }, 
	        /*check: {
				enable: true,
				chkStyle: "radio",
				chkboxType: { "Y": "s", "N": "ps" }
			},*/	
			view: {
				selectedMulti: false
			},
			async:{
			    enable:true,
			    type:"post",
			    dataType:"json",
			    autoParam:["id"],
			    url:"${rc.contextPath}/companyManage/getProductTypeTree"
			},
			callback:{
				onAsyncSuccess: zTreeOnAsyncSuccess
			}
			
		};
		
		var zTreeNodes = [];
		
		 //点击树节点触发事件
        function saveTreeNode(event, treeId, treeNode){
        	 var nodes = $.fn.zTree.getZTreeObj("tree").getSelectedNodes();
        	 var names="";
        	 var ids="";
        	 for(var i=0;i<nodes.length;i++){
        		 if(i<nodes.length-1){
	                 names+=nodes[i].name + ",";
	                 ids +=nodes[i].id + ",";
        		 }else{
        			 names+=nodes[i].name;
        			 ids +=nodes[i].id ;
        		 }
             }
	       	 $("#product_typeName").val(names);
	       	 $("#product_type").val(ids);
	       	 $("#showDivtree").offset({top:0,left:0}).hide();
	    }
		 
        function zTreeOnAsyncSuccess1(event, treeId, treeNode, msg){
        	var ids = $("#product_type").val();
        	var tree = $.fn.zTree.getZTreeObj("tree");
        	if(ids!=null&&ids!=""){
        		var idArr = ids.split(",");
        		for(var i = 0 ; i < idArr.length; i ++ ) { 
        		    tree.checkNode( tree.getNodeByParam( "id",idArr[i] ), true ); 
        		}
        	}
        }
        function TreeNodeEsc(){
        	$("#showDivtree").offset({top:0,left:0}).hide();
        }
		function zTreeOnAsyncSuccess(event, treeId, treeNode){
		   //alert(treeNode.name);
	       //$("#productType01").val(treeNode.name);
	    } 
		 
	</script>
</html>