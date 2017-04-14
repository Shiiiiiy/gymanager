<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
	<#include "/common/common.ftl" >
    
    <script type="text/javascript">
    	
	    <!-- 选择下拉框 -->
		$(function(){
			$('#parentId').searchableSelect();
			$("div[name='searchEle']:eq(0)").width(200);
		});
    
    	function save(){
    		<!-- 校验通过，提交表单 -->
    		if(validate_form()){   
        		$("#addDicItemForm").submit();
    			}
    		}
    	
    	function goback(){
    		var pageSize = $("#pageSize").val();
    		var pageNo = $("#pageNo").val();
    		if(pageNo){
    		}else{
    			pageNo = 1;
    		}
    		location="${rc.contextPath}/dicItem/opt-query/list.do?pageSize="+pageSize+"&pageNo="+pageNo;
    	}

    	function validate_form(formid){
    		var tt = validateFormObject();
    		tt.checkLength("name","字典名称",true,1,20);
    		tt.checkLength("code","字典编码",true,1,20,codeCheck);
    		tt.checkLength("status","状态",true,1,1);
    		tt.checkLength("pid","字典分类",true,1,1);
    		tt.checkLength("value","字典值",false,1,20);
    		tt.checkLength("remark","备注信息",false,1,200);
    		return tt.validate();
    	}
    
    	<!-- code格式校验+唯一性校验 -->
    	function codeCheck(){
    		var code = $("#code").val();
    		var id = $("#id").val();
    		var parentId = $("#parentId").val()
    		
    		var reg =  /\w+$/;  
    		var str = "";  //保存错误提示信息
    		//选择字典分类后才会进行code唯一性校验
    		if(reg.test(code)){
        		//选择字典分类后，才会进行唯一性校验
    			if(parentId){
    				$.ajax({
  				      type:"POST",//通常会用到两种：GET,POST。默认是：GET
  				      url:"${rc.contextPath}/dic/opt-check/checkCode.do",//(默认: 当前页地址) 发送请求的地址
  				      async:false,
  				      data:{code:code,pid:parentId,id:id},
  				      success:function(data){
  				    	  if(data=="success"){
  				    	  }else{
  				    		  str = "该字典编码已存在！";
  				    	  }
  				      }
  				   });
	    		}
    		}else{
    			str = "请输入字母、数字或下划线！";
    		}
    		return str;
    	}
    	
    </script>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header">
        	<h2>
        		<#if dic?? >
        			编辑
        		<#else>
        			新增
        		</#if>
        	</h2>
        </div>
        <form class="form-inline search_list" role="form"  id="addDicItemForm" method="post" action="${rc.contextPath}/dic/opt-save/save.do">
	        <#if dic?? >
	            <input type="hidden" id="pageNo" name="pageNo" value="${pageNo}">
	    	</#if>	
	        <input type="hidden" id="pageSize" name="pageSize" value="${pageSize}">
        	<div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">字典名称 <span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="name" name="name" placeholder="请输入名称" style="width:200px;" <#if dic??>value='${(dic.name)!""}'</#if> >
		                <#if dic??>
		                <input type="hidden" value="${dic.id}" name="id" id="id" />
		                </#if>
		            </div>
		        </div>
            </div>
            
            <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">字典编码 <span style="color:red;">*</span></span>
		                <input type="text" class="form-control" id="code" name="code" placeholder="请输入编码" style="width:200px;" <#if dic??>value='${(dic.code)!""}'</#if>>
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon"> &nbsp;字&nbsp; 典&nbsp; 值&nbsp; </span>
		                <input type="text" class="form-control" id="value" name="value" placeholder="请输入字典值" style="width:200px;" <#if dic??>value='${(dic.value)!""}'</#if>>
		            </div>
		        </div>
	        </div>  
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
		            <div class="input-group">
		                <span class="input-group-addon">字典分类 <span style="color:red;">*</span></span>
		               
		                <select class="form-control" name="pid" id="parentId" style="width:200px;">
                        <option  value="">请选择..</option>
                        <#if dicList??>
                        	<#list dicList as d>
                        		<#if dic?? && dic.pid?? && dic.pid == d.id>
                        			<option value="${d.id}" selected>${(d.name)!""}</option>
                        		<#else>
                        			<option value="${d.id}">${(d.name)!""}</option>
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
	                    <span class="input-group-addon">&nbsp;&nbsp;状&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span>&nbsp;</span>
	                    <#if statusList??>
						<#list statusList as d>
		                    <label class="input-group-addon gray-bg" style="width:46px;">
			                    <#if dic??>
		                        	<#if  dic.status==d.id>
		                        		<input type="radio" aria-label="..." value="${(d.id)!""}"  checked name="status" >
		                        	<#else>
		                        		<input type="radio" aria-label="..." value="${(d.id)!""}" name="status" >
		                        	</#if>
		                        <#else>
		                        	<#if d.id==2>
		                        		<input type="radio" aria-label="..." value="${(d.id)!""}"  checked name="status" >
		                        	<#else>
		                    			<input type="radio" aria-label="..." value="${(d.id)!""}" name="status" >
		                        	</#if>
		                        </#if>
		                    </label>
		                    <label class="form-control">${(d.name)!""}</label>
                        </#list> 
						</#if>
	                </div>
	            </div>
	        </div>
	        
	        <div class="row">
	            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                <div class="input-group">
	                    <span class="input-group-addon">&nbsp;&nbsp;备&nbsp;&nbsp;&nbsp;&nbsp;注 &nbsp;&nbsp;</span>
	                    <textarea class="form-control" id="remark" name="remark" placeholder="请输入备注信息" style="width:450px;height:100px;"><#if dic??>${(dic.remark)!""}</#if></textarea>
	                </div>
	            </div>
	        </div>
	        
		    
            <div class="row">	
	            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
	            	<a class="btn blue" href="javaScript:void(0)" onclick="save('addDicItemForm');"><i class="icon-ok"></i> 确定</a>
	       			<a class="btn black" href="javaScript:void(0)" onclick="goback();"><i class="icon-remove"></i> 取消</a>
	        	</div>
        	</div> 
        	
        	
        	<div class="tc"></br>
	           
	       </div>
    
        </form>
    </div>
</body>
</html>
