<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title></title>
</head>
<body>
   
    <div class="content-box">
        <!--form-->
        <div class="page-header">
        	<h2>
        	        查看
        	    <small class="font-en caps">
	        		
        		</small>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addOrEditModel" method="post">
            <div class="infor-con">
            	<p><label>企业名称</label><span>${(vo.compName)!''}</span></p>
            	<p><label>行业类别</label><span>${(vo.ofIndustry)!''}</span></p>
            	<p><label>核心技术</label><span>${(vo.technology)!''}</span></p>
            	<p><label>投资规模</label><span>${(vo.scale)!''}<#if vo.scale?? && vo.scale!=''>元</#if></span></p>
            	<p><label>联系人</label><span>${(vo.linkman)!''}</span></p>
            	<p><label>电话</label><span>${(vo.tel)!''}</span></p>
            	<p><label>网址</label><span>${(vo.webSite)!''}</span></p>
            	<p>
            		<label>状态</label>
            		<span>
            			<#if  vo.status==enable.id>
                    		启用
                       	<#else>
                       		禁用
                       	</#if>
            		</span>
            	</p>
            </div>
        	<div class="row">	
	            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	       			<a class="btn black" href="javascript:history.go(-1)"><i class="icon-backward"></i> 返回</a>
	        	</div>
        	</div>
        	
        	<div class="tc"></br>
	           
	       </div>
    
        </form>
    </div>
</body>
<script type="text/javascript">
    	
</script>
</html>
