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
        	<form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/service/cplist.do">
        		<div id="searchDiv">
        			<div class="row">
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
	                            <span class="input-group-addon">是否推荐</span>
	                            <select class="form-control" name="propg" id="propg" style="width:179px;">
                            		<option value="" >请选择</option>
                            		<option value="F2" <#if (company.propg)?? && company.propg=="F2">selected="selected"</#if>  >是</option> 
                            		<option value="N"  <#if (company.propg)?? && company.propg=="N">selected="selected"</#if>  >否</option>
	                            </select>
	                        </div>
	                    </div>
					</div> 
					<div class="row">
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				            <div class="input-group">
				                <span class="input-group-addon">分类名称</label></span>
				                <select class="form-control"  name="mCode" id="mCode" onchange="getNextClass(this.value);" style="width:180px;">
		                            <option value="">请选择..</option>
		                            <#if type1??>
		                            	<option value="INDUSTRY_A" <#if toActive?? && toActive=="INDUSTRY_A" >selected="selected"</#if> >工业产品</option>
		                            </#if>
		                            <#if type2??>
		                            	<option value="INDUSTRY_B" <#if toActive?? && toActive=="INDUSTRY_B" >selected="selected"</#if> >生产服务</option>
		                            </#if>
		                        </select>
				            </div>
				        </div>
						<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			                <div class="input-group">
			                    <span class="input-group-addon" id="nextServiceClass">所属分类</span>
		                        <select class="form-control"  name="parentCode" id="ServiceClass" style="width:180px;"><!--依据这个查询-->
		                        	<#if mapList??>
		                        		<option value="">请选择..</option>
				                    	<#list mapList as map>
						                    <#if toActive2??  &&  toActive2 ==map["ID"] >
					                			<option value="${map['ID']}" selected="selected">${(map["NAME"])!""}</option>
					                		<#else>
					                			<option value="${map['ID']}">${(map["NAME"])!""}</option>
					                		</#if>
				                    	</#list>
				                    <#else>	
				                    	<option value="">请选择..</option>
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
					<a class="btn purple" href="javaScript:void(0)" onclick="addTheClass();"><i class="icon-cog"></i> 添加分类</a>
				</div>
				<div class="btn-group">
					<a class="btn green" href="javaScript:void(0)" onclick="recommendAll()"><i class="icon-trash"></i> 是否推荐</a>
				</div>
				<div class="table-responsive">
					<table class="table table-bordered table-striped table-hover mytable" style="table-layout:fixed">	
						<thead>
							<tr>
								<th width="4%"><input type="checkbox" id="checkedAll" /></th>
								<th width="5%">序号</th>
								<th width="8%">企业名称</th>
								<th width="10%">经营产品描述</th>
								<th width="10%">所在地区描述</th>
								<th width="8%">官网地址</th>
								<th width="10%">法定代表人</th>
								<th width="8%">联系方式</th>
								<th width="10%">所属分类</th>
								<th width="10%">是否推荐</th>
								<th width="20%">操作</th>
							</tr>
						</thead>
						<tbody id="hiddentable"></tbody>
							<#if page??>
								<#list page.list as company>
									<tr>	
										<td><input type="checkbox" name="checkList" value="${(company.ID)!''}" /></td>
										<td><div class="zjh">${company_index+1 }</div></td>
										<td><div class="zjh"><a href="javaScript:void(0)" style="text-decoration:none;" onclick="seeCompany('${(company.ID)!""}')">${(company.CP_NAME)!'' }</a></div></td>
										<td><div class="zjh">${(company.CP_PRODUCT)!'' }</div></td>
										<td><div class="zjh">${(company.CP_LOCATION)!'' }</div></td>
										<td><div class="zjh">${(company.CP_ADDRESS)!'' }</div></td>
										<td><div class="zjh">${(company.CP_MAN)!'' }</div></td>
										<td><div class="zjh">${(company.CP_PHONE)!'' }</div></td>
										<td><div class="zjh">${(company.CP_ABSTRACT)!'' }</div></td>
										<td>
											<#if (company.COMMENTS)?? >
												<#if company.COMMENTS=="F2">
													<span class="label label-info">&nbsp;推&nbsp;荐&nbsp;</span>
												<#else>
													<span class="label label-warning">不推荐</span>
												</#if>
											<#else>
												&nbsp;
											</#if>
										</td>
										<td>
											<a onclick="deleteCompany('${(company.ID)!""}');"  class="btn mini black"><i class="icon-trash"></i> 取消</a>&nbsp;&nbsp;
											
											<#if (company.COMMENTS)?? && company.COMMENTS=="N">
												<a class="btn little_blue" onclick="recommend('${company.ID}','F2');" ><i class="icon-ok-sign"></i>&nbsp;推&nbsp;&nbsp;&nbsp;荐&nbsp;</a>&nbsp;&nbsp;
											<#else>
												<a class="btn yellow" onclick="recommend('${company.ID}','N');" ><i class="icon-ban-circle"></i>&nbsp;不推荐</a>&nbsp;&nbsp;
											</#if>
											
											
										</td>
									</tr>
								</#list>
							
							</#if>
						<tfoot>
							<tr>
								<td colspan="11">
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
                        是否推荐:&nbsp;&nbsp;&nbsp;&nbsp;
               <input type="radio" aria-label="..." value="F2" checked name="pg" >
	                推荐
               <input type="radio" aria-label="..." value="N" name="pg" >
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
		$(function () {
			$("#addCompany").company();
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
		//批量是否推荐
		function recommendAll(){
			var len = $('input[name="checkList"]:checked').length;
			if(len<=0){
				custom_alert('请选择要推荐或不推荐的企业',3,2000);
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
			showDialog("系统提示","ispg","sure1('"+companyId+"')","cancel()","close()",100);
		}
		
		function deleteCompany(propid){
			showDialog("系统提示","tishi1","sure('"+propid+"')","cancel()","close()",100);
		}
		
		function recommend(propId,propType){//单个推荐
			window.location.href="${rc.contextPath}/service/recommendCompany.do?propIds="+propId+"&propType="+propType;
		}
		
		function sure(str){//批量取消
			window.location.href="${rc.contextPath}/service/cancelCompany.do?propIds="+str;
		}
		
		function sure1(str){//批量推荐
			var value = $("input[name='pg']:checked").val();//F2 N
			window.location.href="${rc.contextPath}/service/recommendCompany.do?propIds="+str+"&propType="+value;
		}
		
		function cancel(){
		
		}
		//清空查询条件
		function cleanAll(){
		    $("#companyName").val("");
		    $("#productName").val(""); 
		    $("#propg").val(""); 
			$("#mCode option:first").prop("selected", "selected");  
		    $("#ServiceClass").empty();
		    $("#ServiceClass").append("<option value=''>请选择..</option>");  
		}
		//查询
		function formSubmit(){
			$("#pageNo").val(1);
    		$("#searchForm").submit();
    	}
	    //添加分类
	    function addTheClass(){
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
	    	showDialog("选择分类","theClass","theOK('"+companyId+"')","theclose()","theclose()",1);
	    }
		function theOK(O){//添加分类
	        var basic=$("#hidestr").val();
	        	basic = basic.substring(0, basic.length - 1);  
	        var propIds=O;
	        var propType=basic;
	        if(""==propType){location.reload(); return;}
	        //alert(propType);return;
	        window.location.href="${rc.contextPath}/service/addClassCompany.do?propIds="+propIds+"&propType="+propType;
		}
		function theclose(){
			$("#hidestr").val("");
		}
		function seeCompany(companyId){//查看企业
			//alert(companyId);
			var index=layer.open({
				  title: '查看详细',
				  btn:['确定','取消'],
				  area:"900px",
				  cancel: function(){ 
				  },
				  btn1:function(){
				      layer.close(index);
				  },
				  btn2:function(){
				  },
				  content: '<div style="width:100%;height:500px;"><iframe id="myIframe" style="width:1000px;height:500px;" frameborder="0" src="/gymanager/companyManage/addCompany.do?companyId='+companyId+'&flag=see"></iframe></div>'
			});
		}
		function callback(companyIds){//写回调函数
			//alert(companyIds);//return;
			window.location.href="${rc.contextPath}/service/saveCompany.do?companyIds="+companyIds;	
		}
		function getNextClass(O){ //查询二级 分类
	        var types=O+"";
	        if("N"==types){
	            $("#ServiceClass").empty();
	            $("#ServiceClass").append(
	                    "<option value=''>请选择..</option>"+
	                    "<option value='N'>主页新闻</option>"
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