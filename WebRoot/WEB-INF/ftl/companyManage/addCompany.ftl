<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/common.ftl" >
	    <title>新增企业</title>
	    <link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />
	    <script type="text/javascript" src="${rc.contextPath}/plugin/layer/layer.js"></script>
	    <script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
		<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>
	    <link rel="stylesheet" href="${rc.contextPath}/plugin/ztree/css/zTreeStyle.css" type="text/css">
	    <script type="text/javascript" src="${rc.contextPath}/plugin/ztree/js/jquery.ztree.core.js"></script>
	    <script type="text/javascript" src="${rc.contextPath}/plugin/ztree/js/jquery.ztree.excheck.js"></script>
	</head>
	<body>
		<div class="content-box" >
			<div class="page-header">
	        	<h2><#if flag??>修改<#else>新增</#if><small class="font-en caps"></small></h2>
	        </div>
	        
			<div class="tabfix" >
	            <!-- Nav tabs -->
	            <ul class="nav nav-tabs" role="tablist" >
	                <li role="presentation"  class="<#if !improt?exists>active</#if>"   ><a href="#1" aria-controls="1" role="tab" data-toggle="tab">基本信息</a>
	                <li role="presentation" class="<#if improt??>active</#if>" ><a href="#2" aria-controls="2" role="tab" data-toggle="tab" onclick="productTab()" >产品信息</a>
	                <li role="presentation"><a href="#3" aria-controls="3" role="tab" data-toggle="tab" onclick="productTabImage()">产品图片</a>
	            </ul>
	            
	            <div class="tab-content" >
	            	<!--                                  基础信息                                                                                        -->
	                <div role="tabpanel" class="tab-pane <#if !improt?exists>active</#if>" id="1" >
	                	<form class="form-inline search_list" role="form"  id="addCompany" method="post" action="${rc.contextPath}/companyManage/saveCompany.do">
	                		 <input type="hidden" id="companyId" value="${(company.id)!''}"   name="id" class="form-control" ></input>
	                		 <input type="hidden" value="${(company.create_time)!''}"   name="tcreate_time" class="form-control" ></input>
	                		 <input type="hidden" value="${(company.status)!'2'}"   name="status" class="form-control" ></input>
	                		 <div class="row">
				                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">企业名称 <span style="color:red;">*</span></span>
						                <input type="text" class="form-control" id="cp_name" name="cp_name" placeholder="请输入企业名称" style="width:200px;" value="${(company.cp_name)!''}" >
						            </div>
						        </div>
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:108px;">法定代表人<span style="color:red;">*</span></span>
						                <input type="text" class="form-control" id="cp_man" name="cp_man" placeholder="请输入法定代表人" style="width:200px;" value="${(company.cp_man)!''}">
						            </div>
						        </div>
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:116px;">所在地区描述<span style="color:red;">*</span> </span>
						                <input type="text" class="form-control" id="cp_location" name="cp_location" placeholder="请输入所在地区描述" style="width:200px;" value="${(company.cp_location)!''}">
						            </div>
						        </div>
				            </div>
				            
				            <div class="row">
					            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">官网地址<span style="color:red;">*</span> </span>
						                <input type="text" class="form-control" id="cp_address" name="cp_address" placeholder="请输入官网地址" style="width:200px;" value="${(company.cp_address)!''}">
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:108px;">公司联系方式 </span>
						                <input type="text" class="form-control" id="cp_phone" name="cp_phone" placeholder="请输入公司联系方式" style="width:200px;" value="${(company.cp_phone)!''}">
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:116px;">联系人</span>
						                <input type="text" class="form-control" id="cp_linkman" name="cp_linkman" placeholder="请输入联系人" style="width:200px;" value="${(company.cp_linkman)!''}">
						            </div>
						        </div>
					        </div> 
					        
					        <div class="row">
					            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">联系人手机号码</span>
						                <input type="text" class="form-control" id="cp_linktel" name="cp_linktel" placeholder="请输入联系人手机号码" style="width:200px;" value="${(company.cp_linktel)!''}">
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:108px;">联系人邮箱 </span>
						                <input type="text" class="form-control" id="cp_linkemail" name="cp_linkemail" placeholder="请输入联系人邮箱" style="width:200px;" value="${(company.cp_linkemail)!''}">
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:116px;">营销模式</span>
						                <select class="form-control" style="width:200px;" name="cp_modelstr">
			                                <option value="">--请选择-- </option>
			                                <#if cp_modelList??>
			                                       <#list cp_modelList as mt>
			                                       		<#if "${(company.cp_modelstr)!''}" == "${mt.CODE}" >
			                                            	<option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" selected = "selected" >${(mt.NAME)!""}</option>
			                                            <#else>
			                                            	<option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}"  >${(mt.NAME)!""}</option>
			                                            </#if>
			                                       </#list>
			                                </#if>
			                            </select>
						            </div>
						        </div>
					        </div>
					        
					        <div class="row">
					            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">客户类别</span>
						                <select class="form-control" style="width:200px;" name="cp_customertypestr">
			                                <option value="">--请选择-- </option>
			                                <#if cp_customertypeList??>
			                                       <#list cp_customertypeList as mt>
			                                       		<#if "${(company.cp_customertypestr)!''}" == "${mt.CODE}" >
			                                            	<option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" selected = "selected" >${(mt.NAME)!""}</option>
			                                            <#else>
			                                            	<option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}"  >${(mt.NAME)!""}</option>
			                                            </#if>
			                                       </#list>
			                                </#if>
			                            </select>
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:108px;">市场范围 </span>
						                <select class="form-control" style="width:200px;" name="cp_marketstr">
			                                <option value="">--请选择-- </option>
			                                <#if cp_marketList??>
			                                       <#list cp_marketList as mt>
			                                       		<#if "${(company.cp_marketstr)!''}" == "${mt.CODE}" >
			                                            	<option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" selected = "selected" >${(mt.NAME)!""}</option>
			                                            <#else>
			                                            	<option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}"  >${(mt.NAME)!""}</option>
			                                            </#if>	
			                                       </#list>
			                                </#if>
			                            </select>
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:116px;">行政区划</span>
						                <select class="form-control" style="width:200px;" name="cp_belongpartstr">
			                                <option value="">--请选择-- </option>
			                                <#if cp_belongpartList??>
			                                       <#list cp_belongpartList as mt>
			                                       		<#if "${(company.cp_belongpartstr)!''}" == "${mt.CODE}" >
			                                            	<option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" selected = "selected" >${(mt.NAME)!""}</option>
			                                            <#else>
			                                            	<option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}"  >${(mt.NAME)!""}</option>
			                                            	
			                                            </#if>	
			                                       </#list>
			                                </#if>
			                            </select>
						            </div>
						        </div>
					        </div>
					        
					        <div class="row">
					            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">业绩规模</span>
						                <input type="text" class="form-control" id="cp_scale" name="cp_scale" placeholder="请输入业绩规模" style="width:200px;" value="${(company.cp_scale)!''}">
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group news-time">
						                <span class="input-group-addon" style="width:108px;">公司创立日期 </span>
						                <div class="controls input-append date form_datetime no-margin timebox" data-date="${.now}" data-date-format="yyyy-MM-dd" data-link-field="dtp_input1">
					                        <input size="" style="width:200px;" type="text" value="${((company.cp_time)?string('yyyy-MM-dd'))!''}" id="cp_timestr" name="cp_timestr" placeholder="请选择公司创立时间" readonly class="form-control">
					                        <span class="add-on rem"><i class="icon-remove iconfont"></i></span>
					                        <span class="add-on"><i class="icon-th iconfont"></i></span>
					                    </div>
					                    <input type="hidden" id="dtp_input1" value="" /><br />	
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:116px;">组织机构代码</span>
						                <input type="text" class="form-control" id="cp_orgcode" name="cp_orgcode" placeholder="请输入组织机构代码" style="width:200px;" value="${(company.cp_orgcode)!''}">
						            </div>
						        </div>
					        </div>
					        
					        
					        <div class="row">
					            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">社会信用代码</span>
						                <input type="text" class="form-control" id="cp_socialcode" name="cp_socialcode" placeholder="请输入社会信用代码" style="width:200px;" value="${(company.cp_socialcode)!''}">
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-8 col-lg-5" style="height:50px;">
			                        <div class="input-group w">
			                            <span class="input-group-addon" style="width:108px;">所在区域</span>
			                            <select class="form-control" style="width:32.3%;" id="cp_provincestr_id" name="cp_provincestr" onchange="getCityOrArea('A',this)">
			                                <option value="">--请选择-- </option>
			                                <#if cp_provincestrlist??>
			                                       <#list cp_provincestrlist as mt>
			                                       		<#if "${(company.cp_provincestr)!''}" == "${mt.CODE}" >
				                                            <option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" selected = "selected">${(mt.NAME)!""}</option>
			                                       		<#else>
				                                            <option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" >${(mt.NAME)!""}</option>
			                                       		</#if>
			                                       </#list>
			                                </#if>
			                            </select>
			                            <select class="form-control" style="width:32.3%;" id="cp_citystr_id" onchange="getArea(this)"  name="cp_citystr">
			                                <option value="">--请选择-- </option>
			                                <#if cp_citystrList??>
			                                       <#list cp_citystrList as mt>
			                                       		<#if "${(company.cp_citystr)!''}" == "${mt.CODE}" >
				                                            <option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" selected = "selected">${(mt.NAME)!""}</option>
			                                       		<#else>
				                                            <option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" >${(mt.NAME)!""}</option>
			                                       		</#if>
			                                       </#list>
			                                </#if>
			                            </select>
			                            <select class="form-control" style="width:32.3%;" id="cp_areastr_id" name="cp_areastr">
			                                <option value="">--请选择-- </option>
			                                <#if cp_areastrList??>
			                                       <#list cp_areastrList as mt>
			                                       		<#if "${(company.cp_areastr)!''}" == "${mt.CODE}" >
				                                            <option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" selected = "selected">${(mt.NAME)!""}</option>
			                                       		<#else>
				                                            <option value="${(mt.CODE)!''}" name="${(mt.CODE)!''}" >${(mt.NAME)!""}</option>
			                                       		</#if>
			                                       </#list>
			                                </#if>
			                            </select>
			                        </div>
			                    </div>
					        </div>
					        <div class="row">
					            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">企业性质</span>
						                <input type="text" class="form-control" id="cp_type" name="cp_type" placeholder="请输入企业性质" style="width:200px;" value="${(company.cp_type)!''}">
						            </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3" ">
			                         <div class="input-group">
						                <span class="input-group-addon" style="width:105px;">所属行业</span>
						                <input type="text" class="form-control" id="cp_belongstr" name="cp_belongstr" placeholder="请输入所属行业" style="width:200px;" value="${(company.cp_belongstr)!''}" readonly="readonly">
				                        <input type="hidden" id="productType01"   name="" class="form-control">
				                        <button type="button" id="showTree" class="btn btn-primary pa" style="top:0;right:0;z-index:8;"><i class="iconfont">&#xe613;</i></button>
						            </div>
			                    </div>
					        </div>
					        <div class="row">
					        	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
						            <div class="input-group" id="uploadImg">
				                        <span class="input-group-addon" style="width:125px;">企业logo</span>
				                        <input type="text" class="form-control"   id="cp_logo" name="cp_logo" value="${(company.cp_logo)!''}" placeholder="企业logo" style="width:200px;" readonly="readonly"/>
				                        <a href="javascript:void(0);"  class="btn black" id="up_btn" onclick="uploadSource('cp_logo')"><i class="icon-cloud"></i>上传</a>&nbsp;<span style="color:red;">* 最佳133X90像素</span>
				                    </div>
						        </div>
						        
						        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
						            <div class="input-group" id="uploadImg">
				                        <span class="input-group-addon" style="width:125px;">企业视图</span>
				                        <input type="text" class="form-control"   id="cp_view" name="cp_view" value="${(company.cp_view)!''}" placeholder="企业视图" style="width:200px;" readonly="readonly"/>
				                        <a href="javascript:void(0);"  class="btn black" id="up_btn" onclick="uploadSource('cp_view')"><i class="icon-cloud"></i>上传</a>&nbsp;<span style="color:red;">* 最佳490X280像素</span>
				                    </div>
						        </div>
					        </div>
					        
					        
					        <div class="row" id="showimg" 
								  <#if (company.cp_logo)??&&(company.cp_view)??>
								  	style="display:block;"
								  <#else>
								 	 style="display:none;"
								  </#if> >  
					               <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
							  			<img src="${saveLocation}/${(company.cp_logo)!''} " id="cp_logoImage" class="mr10 fl" width="100px;" heigth="100px;"/>
							  	   </div>
							  	   
							  	   <div class="col-xs-12 col-sm-6 col-md-6 col-lg-3">
							  			<img src="${saveLocation}/${(company.cp_view)!''}" id="cp_viewImage" class="mr10 fl" width="100px;" heigth="100px;"/>
							  	   </div>
						      </div>
					        
					        
					        <div class="row">
					        	<div class="col-xs-12 col-sm-6 col-md-12 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">公司简介<span style="color:red;">*</span> </span>
						                <textarea class="form-control" id="cp_abstract" name="cp_abstract" placeholder="公司简介" style="width:920px;height:100px;">${(company.cp_abstract)!""}</textarea>
						            </div>
						        </div>
						    </div>
						    <div class="row">    
						        <div class="col-xs-12 col-sm-6 col-md-12 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">经营产品描述<span style="color:red;">*</span> </span>
						                <textarea class="form-control" id="cp_product" name="cp_product" placeholder="请输入经营产品描述" style="width:920px;height:100px;">${(company.cp_product)!""}</textarea>
						            </div>
						        </div>
						    </div>
						    <div class="row">     
						        <div class="col-xs-12 col-sm-6 col-md-12 col-lg-3">
						            <div class="input-group">
						                <span class="input-group-addon" style="width:125px;">备注</span>
						                <textarea class="form-control" id="comments" name="comments" placeholder="请输入备注信息" style="width:920px;height:100px;">${(company.comments)!""}</textarea>
						            </div>
						        </div>
					        </div>
	                	</form>
	                	<div class="tc">	
					        <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
					        	<#if flag?? >
					        		<a class="btn blue" href="javaScript:void(0)" onclick="edit();"><i class="icon-ok"></i> 修改</a>
					        	<#else > 
					        		<a class="btn blue" href="javaScript:void(0)" onclick="save();"><i class="icon-ok"></i> 确定</a>
					        	</#if>
					       		<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
					        </div>
			            </div>
	                </div>
	                <!--                          产品信息                                                              -->
	                <div role="tabpanel" class="tab-pane <#if improt??>active</#if>" id="2" >
	                	<form class="form-inline search_list" role="form"  id="addProduct" method="post" action="${rc.contextPath}/companyManage/productList.do">
	                		<input type="hidden" id="product_comp" value="${(company.id)!''}"   name="product_comp" class="form-control" ></input>
		                	<div id="searchDiv">
		                		<div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				                    <div class="input-group">
				                        <span class="input-group-addon">产品名称</span>
				                        <input type="text" class="form-control"  id="product_name" name="product_name" placeholder="请输入产品名称">
				                    </div>
				                </div>
				                
				                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				                    <div class="input-group">
				                        <span class="input-group-addon">产品功能</span>
				                        <input type="text" class="form-control"  id="product_capacity" name="product_capacity" placeholder="请输入产品功能">
				                    </div>
				                </div>
				                <div class="tc">
									<a class="btn black"  href="javaScript:void(0)" onclick="productQuery()"><i class="icon-search" ></i> 查询</a>
									<a class="btn black"  href="javaScript:void(0)" onclick="cleanAll();"><i class="icon-trash" ></i> 清空</a>
								</div> 
		                	</div>
							
		                	<a href="javascript:void(0);" onclick="searchDivShowOrClose('searchDiv',this);" class="expend"><span class="triangle-up"></span>收缩</a>	
		                	<div class="btn-group">
								<a  class="btn green" onclick="addProduct();" target="content-box" ><i class="icon-plus"></i>新增</a>
							</div>
							<div class="btn-group">
								<a class="btn black" href="javaScript:void(0)" onclick="excelin()"><i class="icon-cloud"></i>&nbsp;导入</a>
							</div>
							<div class="table-responsive">
								<table class="table table-bordered table-striped table-hover mytable" id="productTable" style="table-layout:fixed">
									<thead>
										<tr>
											<th width="4%">序号</th>
											<th width="8%">产品名称</th>
											<th width="10%">产品分类</th>
											<th width="10%">批准文号</th>
											<th width="10%">上市时间</th>
											<th width="15%">产品功能</th>
											<th width="15%">备注</th>
											<th width="25%">操作</th>
										</tr>
									</thead>
									<tbody id="hiddentable"></tbody>
								</table>
							</div>
	                	</form>
	                	
	                	
	                </div>
	                <!--                          产品图片                                                             -->
	                <div role="tabpanel" class="tab-pane" id="3" >
	                	<iframe style="width:100%;height: 500px" id="companyImageIframe" frameborder="0" src=""></iframe>
	                </div>
	            </div>
	        </div>
		</div>
		<div id="tishi" style="display:none;">确认删除？</div>
		<div id="xitongtishi_div" style="display:none;"></div>
	</body>
	<script type="text/javascript">
		var type = "";
		//结点树初始化
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
			    url:"${rc.contextPath}/companyManage/getCPBelongTree.do"
			}/**这里需要修改*/
		}
		var zTreeNodes = [];
		
		
		$(function () {
			var flag = "${(improt)!''}";
			
			
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
			var Xsssssssssyyy = $('.news-time').offset().top;
			$('.datetimepicker').css("top", Xsssssssssyyy);
			
			
			if(flag!=null&&flag=="improt"){
				productTab();
			}
			//节点树
            $("#showTree").click(function(){
	            //弹出层调用
				var index=layer.open({
					  title: '选择行业类别',
					  btn:['确定','取消'],
					  cancel: function(){ 
					    //右上角关闭回调
					    //$("#cp_belongstr").val("");
					    //$("#productType01").val("");
					  },
					  btn1:function(){
					  	  $("#cp_belongstr").val($("#productType01").val());
					      layer.close(index);
					  },
					  btn2:function(){
					      //$("#cp_belongstr").val("");
					      //$("#productType01").val("");
					  },
					  content: '<div style="width:350px;height:500px;"><ul id="tree" class="ztree" style="width:340px; overflow:auto;"></ul></div>'
				}); 
				//初始化树形菜单
				zTreeObj = $.fn.zTree.init($("#tree"), setting, zTreeNodes);
            });
		});
		//点击树节点触发事件
        function zTreeOnClick(event, treeId, treeNode){
	       $("#productType01").val(treeNode.name);
	    }
		function uploadSource(t){
			type = t ;
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
		
		
	
		function callPerComplete(obj,data){
			eval('var json = ' + data);
			if(json.result){ //&& json.pictureFile
				if(json.pictureFile){	
					if(type =="cp_logo"){
						$("#cp_logo").attr("value",json.fileNameInServer);//上传到数据库的  图片名
						$("#showimg").css("display","block");
						$("#cp_logoImage").attr("src",json.fileRelativePath); //预览功能
					}else{
						$("#cp_view").attr("value",json.fileNameInServer);//上传到数据库的  图片名
						$("#showimg").css("display","block");
						$("#cp_viewImage").attr("src",json.fileRelativePath); //预览功能
					}
				}
			}
		}
		
		function callbackAllComplete(obj){
			
		}
		
		
		function productTabImage(){
			var companyId = $("#product_comp").val() ;
			if(companyId==""||companyId==null){
				custom_alert('请选择先保存基本信息',3,2000);
			}
			$("#companyImageIframe").attr("src","${rc.contextPath}/companyManage/companyImageInit.do?companyId="+$("#product_comp").val());
		}
		
		
		
		
		
		function excelin(){
			window.location.href="${rc.contextPath}/companyManage/excelInCompany?param=product&companyId="+$("#product_comp").val();
		}
		
		function addProduct(){
			//弹出层调用
			var index=layer.open({
				  title: '新增',
				  btn:['确定','取消'],
				  area:['600px','100%'],
				  cancel: function(){ 
					  
				  },
				  btn1:function(){
					  var product_comp = $("#product_comp").val();
					  if(product_comp==null||product_comp==""){
						  custom_alert('请选择先保存基本信息',3,2000);
						  layer.close(index);
						  return ;
					  }
					  if($("#addProductIframe")[0].contentWindow.validate_form_product()){
						  var product_name =  $("#addProductIframe").contents().find("#product_name").val();
						  var product_type =  $("#addProductIframe").contents().find("#product_type").val();
						  var product_num =  $("#addProductIframe").contents().find("#product_num").val();
						  var product_time =  $("#addProductIframe").contents().find("#product_time").val();
						  var product_capacity =  $("#addProductIframe").contents().find("#product_capacity").val();
						  var product_typeName =  $("#addProductIframe").contents().find("#product_typeName").val();
						  var comments =  $("#addProductIframe").contents().find("#comments").val();
						 
						  var param = {"product_name":product_name,"product_type":product_type,"product_num":product_num,"product_time":product_time,
								       "product_capacity":product_capacity,"comments":comments,"product_comp":product_comp,"product_typeName":product_typeName};
						  $.ajax({
							  type: "POST",
							  url: "${rc.contextPath}/companyManage/saveProduct.do",
							  data: param,
							  success:function(msg){
								  var companyId = $("#product_comp").val(); 
								  window.location.href="${rc.contextPath}/companyManage/tipProduct?companyId="+companyId+"&tishi=xinzeng";
							  }
							  
						  });
					      layer.close(index);
					  }
				  },
				  btn2:function(){
					  
				  },
				  content: '<div style="width:100%;height:100%;"><iframe id="addProductIframe" style="width:100%;height:100%;" frameborder="0" src="${rc.contextPath}/companyManage/addProduct.do"></iframe></div>'
			});	
		}
		
		
		function productTab(){
			var product_comp = $("#product_comp").val();
			if(product_comp==null||product_comp==""){
				custom_alert('请选择先保存基本信息',3,2000);
				return;
			}
			$("tr[name='productnametr']").remove();
			ajaxProduct();
		}
		
		function ajaxProduct(){
			var param = {"product_comp":$("#product_comp").val(),"product_name":$("#product_name").val(),"product_capacity":$("#product_capacity").val()};
			$.ajax({
				type: "POST",
				dataType: "JSON",
				url: "${rc.contextPath}/companyManage/productList.do",
				data: param,
				success: function(msg){
					var $table = $("#productTable");
					for(var i = 0 ; i < msg.length ; i++){
						
						var $td = $("<td></td>").append(
						  		$("<a></a>").addClass("btn blue mini").attr("onclick","editProuct('"+msg[i].id+"')").append($("<i></i>").addClass("icon-edit")).html("编辑")
						  ).append(
								"&nbsp;&nbsp;"	  
						  ).append(
								$("<a></a>").addClass("btn mini black").attr("onclick","deleteProduct('"+msg[i].id+"')").append($("<i></i>").addClass("icon-trash")).html("删除")	  
						  );
						var $tr = $("<tr name='productnametr'></tr>").append(
										$("<td></td>").append($("<div class='zjh'></div>").html(i+1))
								).append(
										$("<td></td>").append($("<div></div>").addClass("zjh").html(msg[i].product_name))
								).append(
										$("<td></td>").append($("<div></div>").addClass("zjh").html(msg[i].product_typeName))
								).append(
										$("<td></td>").append($("<div></div>").addClass("zjh").html(msg[i].product_num))
								).append(
										$("<td></td>").append($("<div></div>").addClass("zjh").html(msg[i].product_timestr))
								).append(
										$("<td></td>").append($("<div></div>").addClass("zjh").html(msg[i].product_capacity))
								).append(
										$("<td></td>").append($("<div></div>").addClass("zjh").html(msg[i].comments))
								).append($td);
						
						$table.append($tr);
					}
				}
			});
		}
		
		
		//产品查询
		function productQuery(){
			$("tr[name='productnametr']").remove();
			ajaxProduct();
		}
		
		//修改产品
		function editProuct(str){
			var index=layer.open({
				  title: '新增产品信息',
				  btn:['确定','取消'],
				  area:"600px",
				  cancel: function(){ 
				   
				  },
				  btn1:function(){
					  if($("#addProductIframe")[0].contentWindow.validate_form_product()){
						  var product_name =  $("#addProductIframe").contents().find("#product_name").val();
						  var product_type =  $("#addProductIframe").contents().find("#product_type").val();
						  var product_typeName =  $("#addProductIframe").contents().find("#product_typeName").val();
						  var product_num =  $("#addProductIframe").contents().find("#product_num").val();
						  var product_time =  $("#addProductIframe").contents().find("#product_time").val();
						  var product_capacity =  $("#addProductIframe").contents().find("#product_capacity").val();
						  var comments =  $("#addProductIframe").contents().find("#comments").val();
						  var product_comp = $("#product_comp").val();
						  var param = {"product_name":product_name,"product_type":product_type,"product_num":product_num,"product_time":product_time,
								       "product_capacity":product_capacity,"comments":comments,"product_comp":product_comp,"id":str,"product_typeName":product_typeName};
						  $.ajax({
							  type: "POST",
							  url: "${rc.contextPath}/companyManage/saveProduct.do",
							  data: param,
							  success:function(msg){
								  var companyId = $("#product_comp").val(); 
									window.location.href="${rc.contextPath}/companyManage/tipProduct?companyId="+companyId+"&tishi=xiugai";
								  
							  }
							  
						  });
					      layer.close(index);
					  };
				  },
				  btn2:function(){
					  
				  },
				  content: '<div style="width:600px;height:500px;"><iframe id="addProductIframe" style="width:600px;height:500px;" frameborder="0" src="${rc.contextPath}/companyManage/addProduct.do?productId='+str+'"></iframe></div>'
			});	
		}
		
		//删除产品
		function deleteProduct(str){
			showDialog("系统提示","tishi","sureProduct('"+str+"')","cancel()","close()",100);
		}
		
		function sureProduct(str){
			$.ajax({
				type: "POST",
				url: "${rc.contextPath}/companyManage/deleteProduct.do",
				data: {"productId":str},
				success:function(){
					var companyId = $("#product_comp").val(); 
					window.location.href="${rc.contextPath}/companyManage/tipProduct?companyId="+companyId+"&tishi=shanchu";
				}
			});
			
		}
		
		
		//产品信息的清空
		function cleanAll(){
			$("#product_name").val("");
		    $("#product_capacity").val(""); 
		}
		
		
		//基础信息取消
		function goBacks(){
			window.location.href="${rc.contextPath}/companyManage/list";
		}
		//基础信息的保存
		function save(){
			if(validate_form()){
				$("#addCompany").submit();
			}
		}
		//基本信息的修改
		function edit(){
			if(validate_form()){
				$("#addCompany").submit();
			}
		}
		
		//基础信息的验证
		function validate_form(){
    		var tt = validateFormObject();
    		//必填
    		tt.checkLength("cp_name","企业名称",true,1,40);
    		tt.checkLength("cp_location","所在地区描述",true,1,100);
    		tt.checkLength("cp_address","官网地址",true,1,200);
    		tt.checkLength("cp_abstract","公司简介",true,1,3000);
    		tt.checkLength("cp_product","经营产品描述",true,1,100);
    		//非必填
    		tt.checkLength("cp_man","法定代表人",true,1,100);
    		tt.checkLength("cp_phone","公司联系方式",false,1,32);
    		tt.checkLength("cp_linkman","联系人",false,1,32);
    		tt.checkLength("cp_linktel","联系人手机号码",false,1,11,isTheTel);
    		tt.checkLength("cp_linkemail","联系人邮箱",false,1,20,isTheEmail);
    		tt.checkLength("cp_scale","业绩规模",false,1,32);
    		tt.checkLength("cp_orgcode","组织机构代码",false,1,40);
    		tt.checkLength("cp_socialcode","社会信用代码",false,1,40);
    		tt.checkLength("comments","备注",false,1,500);

    		
    		
    		return tt.validate();
    	}
		
		function getArea(obj){
            getCityOrArea("B",obj);
        }
        function getCityOrArea(str,objs){
            var parentCode=$(objs).find("option:selected").attr("name");
            $.ajax({
			        type:"post",
			        dataType: "json",
			        url:"${rc.contextPath}/companyManage/getCityOrArea?parentCode="+parentCode,
				    success: function(msg) {
				        var oj;
				        if(str=="A"){
				            oj=$("select[name='cp_citystr']");
				            $("select[name='cp_area']").html("<option value=''>--请选择--</option>");
				        }else if(str=="B"){
				            oj=$("select[name='cp_areastr']");
				        }
				        $(oj).html("<option value=''>--请选择--</option>");
				         if(msg!="nomore"){
					         $.each(msg,function(index,value){
				                $(oj).append(
				                	"<option value='"+value.CODE+"' name='"+value.CODE+"'>"+value.NAME+"</option>"
				                );
				             });
			            } 
				    }
			});
        }
        //座机号码验证
	    function isPhone(){
			var obj=$('#cpPhone').val()+"";
			var length = obj.length;
			if(""==obj){
			    return "";
			}
			var reg = /^\d{3,4}-?\d{7,9}$/;
			
			if(  length < 12 && reg.test(obj)  ){
				return "";
			}else{
			    return "座机号码格式不正确";
			}
		}
	    
	    //手机号码验证
	    function isTheTel(){
			var obj=$('#cp_linktel').val()+"";
			var length = obj.length;
			if(""==obj){
			    return "";
			}
			var reg=/^[1][3-8]+\d{9}$/;
			
			if(  length == 11 && reg.test(obj)  ){
				return "";
			}else{
			    return "手机号码格式不正确";
			}
		}
		
		//邮箱号码验证  
		function isTheEmail(){
			var obj=$('#cp_linkemail').val()+"";
			if(""==obj){
			    return "";
			}
			var reg=/^\w{3,}@\w+(\.\w+)+$/;   
			if(reg.test(obj)){
				return "";
			}else{
			    return "邮箱格式不正确";
			}
		}
	</script>
</html>