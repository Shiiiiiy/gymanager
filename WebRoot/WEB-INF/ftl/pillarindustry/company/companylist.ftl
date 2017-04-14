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
			<div class="page-header"><h2>企业列表<small class="font-en caps"></small></h2></div>
       	    <!--form-->
        	<form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/pillarindustry/opt-query/companylist.do">
        		<div id="searchDiv">
        			<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">企业名称</span>
		                        <input type="text" class="form-control" value="${(comp.cp_name)!''}" id="companyName" name="cp_name" placeholder="请输入企业名称">
		                    </div>
		                </div>
						
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		                    <div class="input-group">
		                        <span class="input-group-addon">经营产品</span>
		                        <input type="text" class="form-control" value="${(comp.cp_product)!''}" id="productName" name="cp_product" placeholder="请输入经营产品">
		                    </div>
		                </div>
						
		                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				            <div class="input-group">
				                <span class="input-group-addon">产业分类</label></span>
				                <select class="form-control" name="cp_belongstr" id="cp_belongstr" style="width:179px;">
				                    <option value="">请选择..</option>
				                    <#if mapList??>
				                    	<#list mapList as map>
						                    <#if comp?? && comp.cp_belongstr??  &&  comp.cp_belongstr ==map["ID"] >
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
					
					<div class="row" >
			        	
				        
				        <div class="col-xs-6 col-sm-6 col-md-4 col-lg-3 list-search">
	                        <div class="input-group pr">
	                            <span class="input-group-addon">企业性质</span>
	                            <select class="form-control" name="cp_modelstr" id="cp_modelstr"  style="width:179px;">
	                        		<option value="" >请选择</option>
	                        		<#if comp.cp_modelstr?? && comp.cp_modelstr == "YES">
	                        			<option value="YES" selected="selected">优质企业</option>
	                        			<#else>
	                        			<option value="YES" >优质企业</option>
	                        		</#if>
	                        		<#if comp.cp_modelstr?? && comp.cp_modelstr == "NO">
	                        			<option value="NO" selected="selected">普通企业</option>
	                        			<#else>
	                        			<option value="NO" >普通企业</option>
	                        		</#if>
	                            </select>
	                        </div>
	                    </div>
	                    <div class="col-xs-6 col-sm-6 col-md-4 col-lg-3 list-search">
	                        <div class="input-group pr">
	                            <span class="input-group-addon">是否推荐</span>
	                            <select class="form-control" name="propg" id="propg"  style="width:179px;">
	                        		<option value="" >请选择</option>
	                        		<#if comp.propg?? && comp.propg == "YES">
	                        			<option value="YES" selected="selected">是</option>
	                        			<#else>
	                        			<option value="YES" >是</option>
	                        		</#if>
	                        		<#if comp.propg?? && comp.propg == "NO">
	                        			<option value="NO" selected="selected">否</option>
	                        			<#else>
	                        			<option value="NO" >否</option>
	                        		</#if>
	                            </select>
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
					<a  class="btn green" id="addCompany" target="content-box" ><i class="icon-plus"></i> 选择</a>
				</div>	
		        <div class="btn-group">
					<a class="btn black" href="javaScript:void(0)" onclick="deleteAll()"><i class="icon-trash"></i> 批量取消</a>
				</div>
				<div class="btn-group">
					<a class="btn green" href="javaScript:void(0)" onclick="highquality(1)"><i class="icon-cog"></i> 企业性质</a>
				</div>
				<div class="btn-group">
					<a class="btn green" href="javaScript:void(0)" onclick="highquality(2)"><i class="icon-cog"></i> 是否推荐</a>
				</div>
				
				<div class="btn-group">
					<a class="btn purple" href="javaScript:void(0)" onclick="assort()"><i class="icon-cog"></i> 产业分类</a>
				</div>
				
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover mytable" style="table-layout:fixed">
						<thead>
							<tr>
								<th width="3%"><input type="checkbox" id="checkedAll" /></th>
								<th width="5%">序号</th>
								<th width="7%">企业名称</th>
								<th width="7%">经营产品</th>
								<th width="7%">产业分类</th>	
								<th width="7%">所在地区</th>
								<th width="7%">官网地址</th>
								<th width="9%">法定代表人</th>
								<th width="7%">联系方式</th>
								<th width="7%">企业性质</th>
								<th width="7%">是否推荐</th>
								<th width="27%">操作</th>
							</tr>
						</thead>
						<tbody id="hiddentable"></tbody>
							<#if page??>
								<#list page.list as company>
									<tr>	
										<td><input type="checkbox" name="checkList" value="${company.CID}" /></td>
										<td><div class="zjh">${company_index+1 }</div></td>
										<td><div class="zjh"><a href="javascript:" onclick="seeCompany('${company.CID}')">${company.CP_NAME }</a></div></td>
										<td><div class="zjh">${company.CP_PRODUCT }</div></td>
										<td><div class="zjh">${company.CLASSNAME }</div></td>
										<td><div class="zjh">${company.CP_LOCATION }</div></td>
										<td><div class="zjh">${company.CP_ADDRESS }</div></td>
										<td><div class="zjh">${company.CP_MAN }</div></td>
										<td><div class="zjh">${company.CP_PHONE }</div></td>
										<td>
											<#if company.HQC ?? && company.HQC=="YES">
												<span class="label label-info">优质企业</span>
											<#else>
												<span class="label label-warning">普通企业</span>
											</#if>
										</td>
										<td>
											<#if company.RC ?? && company.RC=="YES">
												<span class="label label-info">推&nbsp;&nbsp;荐&nbsp;</span>
											<#else>
												<span class="label label-warning">不推荐</span>
											</#if>
										</td>
										<td>
											<a onclick="deleteCompany('${company.CID}');"  class="btn mini black"><i class="icon-trash"></i> 取消</a>&nbsp;
											<#if company.HQC ?? && company.HQC=="YES">
												<a class="btn yellow" onclick="recommend('${company.CID}','NO',1);" ><i class="icon-ban-circle"></i>&nbsp;普通企业</a>
											<#else>
												<a class="btn little_blue" onclick="recommend('${company.CID}','YES',1);" ><i class="icon-ok-sign"></i>&nbsp;优质企业</a>
											</#if>
											<#if company.RC ?? && company.RC=="YES">
												<a class="btn yellow" onclick="recommend('${company.CID}','NO',2);" ><i class="icon-ban-circle"></i>&nbsp;不推荐</a>
											<#else>
												<a class="btn little_blue" onclick="recommend('${company.CID}','YES',2);" ><i class="icon-ok-sign"></i>&nbsp;推&nbsp;荐&nbsp;</a>
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
        <div id="tishi" style="display:none;">确认移除选中的企业？</div>
        
        <div id="tishi1" style="display:none;">确认移除该企业？</div>
        
        <div id="ispg" style="display:none;">
        	企业性质:&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="radio" aria-label="..." value="YES" checked name="pg" >
	                优质企业
               <input type="radio" aria-label="..." value="NO" name="pg" >
	                普通企业
		</div>
        <div id="isrc" style="display:none;">
	    	是否推荐:&nbsp;&nbsp;&nbsp;&nbsp;
	           <input type="radio" aria-label="..." value="YES" checked name="rc" >
	                推荐
	           <input type="radio" aria-label="..." value="NO" name="rc" >
	                不推荐
		</div> 
	                
	                
        <div id="theClass" style="display:none;">
			<#include "addClass.ftl" >
		</div>
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
				custom_alert('请选择要移除的企业',3,2000);
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
		//批量设置企业性质
		function highquality(type){
			var len = $('input[name="checkList"]:checked').length;
			if(len<=0){
				custom_alert('请先选择要操作的企业',3,2000);
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
			if(type==1){
				showDialog("系统提示","ispg","sure1('"+companyId+"',1)","cancel()","close()",100);
			}else{
				showDialog("系统提示","isrc","sure1('"+companyId+"',2)","cancel()","close()",100);
			}
		}
		
		//添加分类
	    function assort(){
	    	var len = $('input[name="checkList"]:checked').length;
			if(len<=0){
				custom_alert('请选择要添加分类的企业',3,2000);
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
	    	showDialog("选择分类","theClass","theOK('"+companyId+"')","theclose()","theclose()",50);
	    }
		function theOK(compIds){
			var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val(); 
			
	        var basic=$("#hidestr").val();
	        
	        basic = basic.substring(0, basic.length - 1);  
	        
	        var propType=basic;
	   
	     //   alert(basic);
	        window.location.href="${rc.contextPath}/pillarindustry/opt-edit/setClass.do?compIds="+compIds+"&industryIds="+basic+"&pageSize="+pageSize+"&pageNo="+pageNo;
		}
		function theclose(){
			$("#hidestr").val('');
		}
		
		
		
		function deleteCompany(propid){
			showDialog("系统提示","tishi1","sure('"+propid+"')","cancel()","close()",100);
		}
		
		function recommend(propId,type,changeType){
			var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val(); 
			window.location.href="${rc.contextPath}/pillarindustry/opt-edit/changeCompanyHQ.do?compIds="+propId+"&type="+type+"&pageSize="+pageSize+"&pageNo="+pageNo+"&changeType="+changeType;
		}
		
		function sure(str){
			window.location.href="${rc.contextPath}/pillarindustry/opt-del/delcompany.do?compIds="+str;
		}
		
		function sure1(str,type){
			var pageNo = $("#pageNo").val();
    		var pageSize = $("*[name='pageSize']").val();
    		if(type==1){
    			var value = $("input[name='pg']:checked").val();
    		}else{
    			var value = $("input[name='rc']:checked").val();
    		}
			window.location.href="${rc.contextPath}/pillarindustry/opt-edit/changeCompanyHQ.do?compIds="+str+"&type="+value+"&pageSize="+pageSize+"&pageNo="+pageNo+"&changeType="+type;
		}
		
		function cancel(){
		
		}
		//清空查询条件
		function cleanAll(){
		    $("#companyName").val("");
		    $("#productName").val(""); 
		    $("#propg").val(""); 
		    $("#cp_belongstr").val("");
		    $("#cp_modelstr").val("");
		}
		//查询
		function formSubmit(){
			$("#pageNo").val(1);
    		$("#searchForm").submit();
    	}
		
		function callback(companyIds){
			window.location.href="${rc.contextPath}/pillarindustry/opt-add/addCompany.do?compIds="+companyIds;	
		//	alert(companyIds);
		}
	    
		$(function () {
			$("#addCompany").company();
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
				  content: '<div style="width:100%;height:100%;"><iframe id="myIframe" style="width:100%;height:100%;" frameborder="0" src="/gymanager/companyManage/addCompany.do?companyId='+companyId+'&flag=see"></iframe></div>'
			});
		}
		
	</script>
</html>