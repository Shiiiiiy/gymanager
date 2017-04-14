<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
	<head>
		<#include "/common/common.ftl" >
	    <title>查看企业企业</title>
	</head>
	<body onload="IFrameResize()">
		<form class="form-inline" role="form"  id="companyInfo" method="post">
			 <div class="infor-con clearfix">
			 	<div class-"row clearfix">
			 		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					 	<p><label>企业名称</label><span>${(company.cp_name)!''}</span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					 	<p><label>法定代表人</label><span>${(company.cp_man)!''}</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					 	<p><label>官网地址</label><span>${(company.cp_address)!''}</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>公司联系方式</label><span>${(company.cp_phone)!''}</span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>联系人</label><span>${(company.cp_linkman)!''}</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>联系人手机号码</label><span>${(company.cp_linktel)!''}</span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>联系人邮箱 </label><span>${(company.cp_linkemail)!''}</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>营销模式</label><span><#if cp_modelList??> <#list cp_modelList as mt><#if "${(company.cp_modelstr)!''}" == "${mt.CODE}" >${(mt.NAME)!""} </#if></#list></#if></span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>客户类别</label><span><#if cp_customertypeList??> <#list cp_customertypeList as mt><#if "${(company.cp_customertypestr)!''}" == "${mt.CODE}" >${(mt.NAME)!""} </#if></#list></#if></span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>市场范围</label><span><#if cp_marketList??> <#list cp_marketList as mt><#if "${(company.cp_marketstr)!''}" == "${mt.CODE}" >${(mt.NAME)!""} </#if></#list></#if></span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>行政区划</label><span><#if cp_belongpartList??> <#list cp_belongpartList as mt><#if "${(company.cp_belongpartstr)!''}" == "${mt.CODE}" >${(mt.NAME)!""} </#if></#list></#if></span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>业绩规模</label><span>${(company.cp_scale)!''}</span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>公司创立日期 </label><span>${((company.cp_time)?string('yyyy-MM-dd'))!''}</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>组织机构代码</label><span>${(company.cp_orgcode)!''}</span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>社会信用代码 </label><span>${(company.cp_socialcode)!''}</span></p>
				 	</div>

				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>所属行业</label><span>${(company.cp_belongstr)!''}</span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>企业性质</label><span>${(company.cp_type)!''}</span></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					 	<p><label>所在区域</label><span>
					 		<#if cp_provincestrlist??> <#list cp_provincestrlist as mt><#if "${(company.cp_provincestr)!''}" == "${mt.CODE}" >${(mt.NAME)!""} </#if></#list></#if>
					 		<#if cp_citystrList??> <#list cp_citystrList as mt><#if "${(company.cp_citystr)!''}" == "${mt.CODE}" >${(mt.NAME)!""} </#if></#list></#if>
					 		<#if cp_areastrList??> <#list cp_areastrList as mt><#if "${(company.cp_areastr)!''}" == "${mt.CODE}" >${(mt.NAME)!""} </#if></#list></#if>	
					 	</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					 	<p><label>所在地区描述</label><span>${(company.cp_location)!''}</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>企业logo</label><img src="${saveLocation}/${(company.cp_logo)!''} " title="企业logo" id="cp_logoImage" style="height:100px;width:100px;" /></p>
				 	</div>
				 	<div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
					 	<p><label>企业视图 </label><span><img src="${saveLocation}/${(company.cp_view)!''}" title="企业视图" id="cp_viewImage" style="height:100px;width:100px;" /></span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
					 	<p class="clearfix"><label class="fl">公司简介</label><span class="fl" style="width:80%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 10px;">${(company.cp_abstract)!""}</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				 		<p class="clearfix"><label class="fl">经营产品描述</label><span class="fl" style="width:80%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 10px;">${(company.cp_product)!""}</span></p>
				 	</div>
				 	
				 	<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
				 		<p class="clearfix"><label class="fl">备注</label><span class="fl" style="width:80%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 10px;">${(company.comments)!""}</span></p>
				 	</div>
				 	
			 	</div>
		 	</div>
		</form>
	</body>
	<script type="text/javascript">
		function IFrameResize(){  
			 var obj = parent.document.getElementById("companyInfoIframe");  //取得父页面IFrame对象  
			 $(obj).css("height",this.document.body.scrollHeight);
		} 
	</script>
</html>	