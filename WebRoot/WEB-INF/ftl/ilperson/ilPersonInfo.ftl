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
        <form class="form-inline search_list" role="form"  id="addOrEditPerson" method="post" action="${rc.contextPath}/ilperson/opt-save/saveIlPerson.do">
        	<div class="infor-con">
            	<p><label>姓名</label><span>${(person.psName)!''}</span></p>
            	<p><label>电话</label><span>${(person.psTel)!''}</span></p>
            	<p><label>职务</label><span>${(person.psJob)!''}</span></p>
            	<p><label>所在单位</label><span>${(person.psBelong)!''}</span></p>
            	<p class="clearfix"><label class="fl">简介</label><span class="fl" style="width:90%;border-left:#ddd solid 1px; margin-left:-21px;padding:0 18px;">${(person.psIntroduce)!""}</span></p>
            	<p>
            		<label>状态</label>
            		<span>
            			<#if  person.status==enable.id>
                    		启用
                       	<#else>
                       		禁用
                       	</#if>
            		</span>
            	</p>
            	<p><label>头像</label>
            		<#if (person.imageUrl)?? && ((person.imageUrl)+"")!="">
		            	<img src="${(person.imageUrl)!''} "  id="cp_logoImage" style="height:130px;width:98px;padding:5px;" />
					<#else>
						&nbsp;
					</#if>
            	</p>
            </div>
            
	        
        	<div class="row">	
	            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	       			<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-backward"></i> 返回</a>
	        	</div>
        	</div>
        	
        	<div class="tc"></br>
	           
	       </div>
    
        </form>
    </div>
</body>
<script type="text/javascript">
	
	//取消
	function goBacks(){
		window.location.href="${rc.contextPath}/ilperson/opt-query/ilList.do?type=${(type)!''}";
	}
	
</script>
</html>
