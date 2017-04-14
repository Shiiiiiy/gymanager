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
            	<p><label>名称</label><span>${(vo.platName)!''}</span></p>
            	<p><label>类型</label><span>${(vo.platType)!''}</span></p>
            	<p><label>投资主体</label><span>${(vo.investor)!''}</span></p>
            	<p><label>级别</label><span>${(vo.platLevel)!''}</span></p>
            	<p><label>成立时间</label><span>${((vo.platTime)?string('yyyy-MM-dd'))!''}</span></p>
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
