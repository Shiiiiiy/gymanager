<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
    <title>欢迎页面</title>
</head>
<body>
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>表单<small class="font-en caps"> form style</small></h2></div>
        <form class="form-inline search_list" role="form"  id="ssssss" method="post" action="${rc.contextPath}/main/list.do">
            <div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">字符</span>
                        <input type="text" class="form-control" id="name" placeholder="请输字符" style="width:200px;">
                        
                        <label class="input_sel_label">
	                        <a onclick="alert(123);">选择</a>&nbsp;&nbsp;&nbsp;&nbsp;
	                        <a onclick="alert(123);">选择</a>
                        </label>
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
	                    <span class="input-group-addon">数字</span>
	                    <input type="text" class="form-control" id="num" placeholder="请输入数字" style="width:300px;">
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
	                    <span class="input-group-addon">选择列表</span>
	                    <select class="form-control" name="sel" id="sel">
	                        <option selected="selected" value="">选择列表</option>
	                        <option value="1">2</option>
	                        <option value="2">3</option>
	                        <option value="3">4</option>
	                        <option value="4">5</option>
	                    </select>
                    </div>
                </div>
            </div>
             
       		<div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <label class="input-group-addon gray-bg">
                            <input type="checkbox" aria-label="..." name="chkb">
                        </label>
                        <label class="form-control">男</label>
                        <label class="input-group-addon gray-bg">
                            <input type="checkbox" aria-label="..." name="chkb">
                        </label>
                        <label class="form-control">女</label>
                        
                        
                    </div>
                </div>
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
                        <span class="input-group-addon">名称</span>
                        <label class="input-group-addon gray-bg">
                            <input type="radio" aria-label="..." name="radio" checked="checked">
                        </label>
                        <label class="form-control">男</label>
                        <label class="input-group-addon gray-bg">
                            <input type="radio" aria-label="..." name="radio">
                        </label>
                        <label class="form-control">女</label>
                        
                        
                    </div>
                </div>
                
                
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
	                    <span class="input-group-addon">选择列表</span>
	                    <select name="sel" id="sel_search">
	                        <option selected="selected" value="">选择列表</option>
	                        <option value="1">2</option>
	                        <option value="2">3</option>
	                        <option value="3">4</option>
	                        <option value="4">5</option>
	                    </select>

					</div>
                </div>
            </div>
             
       		<div class="row">
                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
                    <div class="input-group">
	                    <span class="input-group-addon">内容</span>
	                    <textarea class="form-control" id="content" name="content" placeholder="请输入名称" style="width:800px;"></textarea>
                    </div>
                </div>
            </div>
                    
                    
       		<div class="tc"></br>
       			<a class="btn blue" href="javaScript:void(0)" onclick="validate_form('ssssss');"><i class="icon-ok"></i> 确定</a>
       			<a class="btn black" href="javaScript:void(0)" onclick="validate_form('ssssss');"><i class="icon-remove"></i> 取消</a>
	       </div>
       
    </div>
</form>

<script>
$(function(){
	$('#sel_search').searchableSelect();
});
	
	function ss(){
		return "测试失败";
	}

	function validate_form(formid){
		var tt = validateFormObject();
		tt.checkLength("name","字符",true,1,1,ss);
		tt.checkNumber("num","数字",true,1,100,ss);
		//tt.checkLength("name","其他名称",true,1,1);
		//tt.checkLength("sel","选择列表",true);
		tt.validate();
		//alert(isNum("00001000"));
		//alert(isNum("sfsdf@qq.com"));
		//alert(isNum("sfsdf@qq."));
	}
</script>

</body>
</html>
