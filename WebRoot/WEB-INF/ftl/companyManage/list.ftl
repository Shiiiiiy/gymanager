<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/common.ftl" >
	    <title>企业列表</title>
	    <link rel="stylesheet" href="${rc.contextPath}/plugin/ztree/css/zTreeStyle.css" type="text/css">
	    <script type="text/javascript" src="${rc.contextPath}/plugin/ztree/js/jquery.ztree.core.js"></script>
    	<script type="text/javascript" src="${rc.contextPath}/plugin/layer/layer.js"></script>
    	<script type="text/javascript" src="${rc.contextPath}/js/company.js"></script>
	</head>
	<body>
		<div class="content-box">
			<div class="page-header"><h2>企业管理<small class="font-en caps"></small></h2></div>
       	    <!--form-->
        	<form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/companyManage/list.do">
        		<div id="searchDiv">
        			<div class="form-inline search_list">
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">企业名称</span>
		                        <input type="text" class="form-control" value="${(company.cp_name)!''}" id="companyName" name="cp_name" placeholder="请输入企业名称">
		                    </div>
		                </div>
						
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">经营产品</span>
		                        <input type="text" class="form-control" value="${(product.product_name)!''}" id="productName" name="product_name" placeholder="请输入经营产品">
		                    </div>
		                </div>
						
						<div class="col-xs-6 col-sm-6 col-md-4 col-lg-3 list-search">
	                        <div class="input-group pr">
	                            <span class="input-group-addon">产品分类</span>
	                            <input type="text" id="productType" value="${PRODUCT_TYPE!''}"   name="PRODUCT_TYPE" class="form-control" readonly="readonly" placeholder="请选择产品分类"></input>
	                            <input type="hidden" id="productType01" value="${PRODUCT_TYPE1!''}"   name="PRODUCT_TYPE1" class="form-control" ></input>
	                            <button type="button" id="showTree" class="btn btn-primary pa" style="top:0;right:0;z-index:8;"><i class="iconfont">&#xe613;</i></button>
	                        </div>
	                    </div>
					</div> 
					<div class="tc">
						<a class="btn black"  href="javaScript:void(0)" onclick="formSubmit()"><i class="icon-search" ></i> 查询</a>
						<a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
					</div>  
        		</div>
				
				
        		<a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>	
        		<div class="btn-group">
					<a  class="btn green" onclick="addCompany();" target="content-box" ><i class="icon-plus"></i>&nbsp;新增</a>
				</div>	
		        <div class="btn-group">
					<a class="btn black" href="javaScript:void(0)" onclick="deleteAll()"><i class="icon-trash"></i>&nbsp;批量删除</a>
				</div>
				
				<div class="btn-group">
					<a class="btn purple" href="javaScript:void(0)" onclick="excelout()"><i class="icon-cog"></i>&nbsp;导出</a>
				</div>
				<div class="btn-group">
					<a class="btn black" href="javaScript:void(0)" onclick="excelin()"><i class="icon-cloud"></i>&nbsp;导入</a>
				</div>
				
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover mytable" style="table-layout:fixed">
						<thead>
							<tr>
								<th width="35px"><input type="checkbox" id="checkedAll" /></th>
								<th width="48px">序号</th>
								<th width="7%">企业名称</th>
								<th width="7%">经营产品描述</th>
								<th width="9%">所在地区描述</th>
								<th width="7%">官网地址</th>
								<th width="8%">法定代表人</th>
								<th width="7%">联系方式</th>
								<th width="7%">企业简介</th>
								<th width="7%">产品分类</th>
								<th width="48px">状态</th>
								<th width="246px">操作</th>
							</tr>
						</thead>
						<tbody id="hiddentable"></tbody>
							<#if page??>
								<#list page.list as company>
									<tr>	
										<td><input type="checkbox" name="checkList" value="${company.ID}" /></td>
										<td><div class="zjh">${company_index+1 }</div></td>
										<td><div class="zjh"><a href="javaScript:void(0)" style="text-decoration:none;" onclick="seeCompany('${company.ID}')">${(company.CP_NAME)!'' }</a></div></td>
										<td><div class="zjh">${(company.CP_PRODUCT)!'' }</div></td>
										<td><div class="zjh">${(company.CP_LOCATION)!'' }</div></td>
										<td><div class="zjh">${(company.CP_ADDRESS)!'' }</div></td>
										<td><div class="zjh">${(company.CP_MAN)!'' }</div></td>
										<td><div class="zjh">${(company.CP_PHONE)!'' }</div></td>
										<td><div class="zjh">${(company.CP_ABSTRACT)!'' }</div></td>
										<td><div class="zjh">${(company.COMMENTS)!'' }</div></td>
										<td>
											<#if "${(company.STATUS)!'' }" =="2" >
												<span class="label label-info">启用</span>
											<#else>
												<span class="label label-warning">禁用</span>
											</#if>
										</td>
										<td>
											<a onclick="edit('${company.ID}');" class="btn blue mini"><i class="icon-edit"></i>&nbsp;编辑</a>&nbsp;&nbsp;
											<a onclick="deleteCompany('${company.ID}');"  class="btn mini black"><i class="icon-trash"></i>&nbsp;删除</a>&nbsp;&nbsp;
											<#if "${(company.STATUS)!'' }" =="2" >
												<a class="btn yellow" href="javaScript:void(0)"  onclick="stutasNo('${company.ID}');"><i class="icon-ban-circle"></i>&nbsp;禁用</a>
											<#else>
												<a class="btn little_blue" href="javaScript:void(0)"  onclick="stutasYes('${company.ID}');"><i class="icon-ok-sign"></i>&nbsp;启用</a>	
											</#if>
										</td>
									</tr>
								</#list>
							
							</#if>
						<tfoot>
							<tr>
								<td colspan="12">
									${page.pageTool}
								</td>
							</tr>
						</tfoot>
		            </table>
				</div>
			    	
        		
        	</form>
        </div>
        <div id="tishi" style="display:none;">确认删除？</div>
	</body>
	<script>
		
		
		$("#checkedAll").click(function(){
			var flag = this.checked;
			$('input[name="checkList"]').each(function(){
				this.checked = flag;
			});
		});
		
		//批量删除
		function deleteAll(){
			var len = $('input[name="checkList"]:checked').length;
			if(len<=0){
				custom_alert('请选择要删除的企业',3,2000);
				return;
			}
			var companyId = "";
			$('input[name="checkList"]:checked').each(function(i){
				if(i==len-1){
					companyId += $(this).val();
				}else{
					companyId += $(this).val() +",";
				}
			});
			showDialog("系统提示","tishi","sure('"+companyId+"')","cancel()","close()",100);
		}
		//新增企业
		function addCompany(){
			window.location.href="${rc.contextPath}/companyManage/addCompany";
		}
		
		function edit(companyId){
			window.location.href="${rc.contextPath}/companyManage/addCompany?companyId="+companyId;
		}
		
		function deleteCompany(companyId){
			showDialog("系统提示","tishi","sure('"+companyId+"')","cancel()","close()",100);
		}
		function excelout(){
			var pageNo = $("#pageNo").val();
			var pageSize = $("select[name='pageSize']").val();
			var pageSearchType = $("#pageSearchType").val();
			var companyName = $("#companyName").val();
			var productName = $("#productName").val();
			var PRODUCT_TYPE1 = $("#productType01").val();
			window.location.href="${rc.contextPath}/companyManage/excelCompany.do"+
				"?pageNo="+pageNo+"&pageSize="+pageSize+"&pageSearchType="+pageSearchType+"&companyName="+encodeURI(encodeURI(companyName))+"&productName="+encodeURI(encodeURI(productName))+"&PRODUCT_TYPE1="+PRODUCT_TYPE1;
		}
		
		function excelin(){
			window.location.href="${rc.contextPath}/companyManage/excelInCompany";
		}
		
		//禁用
		function stutasNo(companyId){
			var companyName = $("#companyName").val();
			var productName = $("#productName").val();
			var PRODUCT_TYPE1 = $("#productType01").val();
			var pageSearchType = "2";
			var pageNo = $("#pageNo").val();
			var pageSize = $("select[name='pageSize']").val();
			window.location.href="${rc.contextPath}/companyManage/statusComapny.do?companyId="+companyId+"&status=3&tip=jinyong"+"&companyName="+encodeURI(encodeURI(companyName))+"&productName="+encodeURI(encodeURI(productName))+"&PRODUCT_TYPE1="+PRODUCT_TYPE1+
					"&pageSearchType="+pageSearchType+"&pageNo="+pageNo+"&pageSize="+pageSize;
		}
		
		//启用
		function stutasYes(companyId){
			var companyName = $("#companyName").val();
			var productName = $("#productName").val();
			var PRODUCT_TYPE1 = $("#productType01").val();
			var pageSearchType = "2";
			var pageNo = $("#pageNo").val();
			var pageSize = $("select[name='pageSize']").val();
			window.location.href="${rc.contextPath}/companyManage/statusComapny.do?companyId="+companyId+"&status=2&tip=qiyong"+"&companyName="+encodeURI(encodeURI(companyName))+"&productName="+encodeURI(encodeURI(productName))+"&PRODUCT_TYPE1="+PRODUCT_TYPE1+
					"&pageSearchType="+pageSearchType+"&pageNo="+pageNo+"&pageSize="+pageSize;
		}
		
		function sure(str){
			window.location.href="${rc.contextPath}/companyManage/deleteCompany.do?companyId="+str;
		}
		
		function cancel(){
		
		}
		//清空查询条件
		function cleanAll(){
		    $("#companyName").val("");
		    $("#productName").val(""); 
			$("#productType").val("");
			$("#productType01").val("");
		}
		//查询
		function formSubmit(){
    		$("#searchForm").submit();
    	}
    	
    	var zTreeObj;
		setting = {
		    data: {  
	                simpleData: {  
	                    enable: true  
	                }  
	        }, 
			view: {
				selectedMulti: false
			},
			callback:{
			    onClick: zTreeOnClick
			},
			async:{
			    enable:true,
			    type:"post",
			    dataType:"json",
			    autoParam:["id"],
			    url:"${rc.contextPath}/companyManage/getProductTypeTree"
			}
		}
		var zTreeNodes = [];
		
		 //点击树节点触发事件
        function zTreeOnClick(event, treeId, treeNode){
	       $("#productType").val(treeNode.name);
	       $("#productType01").val(treeNode.id);
	    }
	    
		$(function(){
			   $("#showTree").click(function(){
	            //弹出层调用
				var index=layer.open({
					  title: '选择产品类别',
					  btn:['确定','取消'],
					  cancel: function(){ 
					    //右上角关闭回调
					    $("#productType").val("");
					    $("#productType01").val("");
					  },
					  btn1:function(){
					      layer.close(index);
					  },
					  btn2:function(){
					      $("#productType").val("");
					      $("#productType01").val("");
					  },
					  content: '<div style="width:350px;height:500px;"><ul id="tree" class="ztree" style="width:340px; overflow:auto;"></ul></div>'
				}); 
				//初始化树形菜单
				zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
	        });
		
		});
		
		
		function seeCompany(companyId){
			var index=layer.open({
				  title: '查看',
				  btn:['确定','取消'],
				  area:['900px', '100%'],
				  cancel: function(){ 
				  },
				  btn1:function(){
				      layer.close(index);
				  },
				  btn2:function(){
				  },
				  content: '<div style="width:100%;height:100%;"><iframe id="myIframe" style="width:100%;height:100%;" frameborder="0" src="${rc.contextPath}/companyManage/addCompany.do?companyId='+companyId+'&flag=see"></iframe></div>'
			});
		}
		
	</script>
</html>