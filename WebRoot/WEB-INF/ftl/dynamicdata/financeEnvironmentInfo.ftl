<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title></title>
<style type="text/css">
.infor-con label { min-width:150px;}
</style>
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
            	<p><label>机构名称</label><span>${(vo.financeName)!''}</span></p>
            	<p><label>本地是否设立分支机构</label><span>${(vo.isBranch)!''}</span></p>
            	<p><label>本地累计投资规模</label><span>${(vo.scale)!'0'}<#if vo.scale?? && vo.scale!=''>元</#if></span></p>
            	<p><label>联系人</label><span>${(vo.linkman)!''}</span></p>
            	<p><label>电话</label><span>${(vo.tel)!''}</span></p>
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
