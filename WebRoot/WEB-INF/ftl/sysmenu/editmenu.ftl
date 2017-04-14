<div id="tdiv1" style="display:none">
	<div class=""><h2 id="thedivname">菜单</h2></div><hr>
	<form class="form-inline search_list" role="form"  id="edituser" method="post"  target="content-box">  
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon">上级菜单<span style="color:red;">&nbsp;</span></span>
                        <input type="text" class="form-control"  id="pre_name" value="" disabled style="width:200px;"/>
		                <input type="hidden" id="p_id" name="p_id" value=""/>
		                <input type="hidden" id="true_id" name="true_id" value=""/>
		                <input type="hidden" id="old_code"  value=""/>
                    </div>
                </div>
            </div>    
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon">菜单名称<span style="color:red;">*</span></span>
                        <input type="text" class="form-control"  id="title" name="title" value="" placeholder="请输入菜单名称" style="width:200px;">
                    </div>
                </div>
            </div>  
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon">菜单编码<span style="color:red;">*</span></span>
                        <input type="text" class="form-control"  id="menu_code" name="menu_code" value="" placeholder="请输入菜单编码" style="width:200px;">
                    </div>
                </div>
            </div>  
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon">菜单链接<span style="color:red;">*</span></span>
                        <input type="text" class="form-control"  id="url" name="url" value="" placeholder="请输入菜单链接" style="width:200px;">
                    </div>
                </div>
            </div>  
  
			<div class="row">
			    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon">菜单状态<span style="color:red;">*</span></span>
                        <#if Dics??>
						<#list Dics as dic>
		                    <label class="input-group-addon gray-bg" style="width:45px;">
		                        <input type="radio" aria-label="..." value="${(dic.id)!""}" name="status" <#if (dic.name)?? && (dic.name+"")=="启用">checked='true'</#if> >
		                    </label>
		                    <label class="form-control">${(dic.name)!""}</label>
                        </#list> 
						</#if>
                    </div>
                </div>
			</div>
			<div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="input-group">
                        <span class="input-group-addon">菜单备注<span style="color:red;">&nbsp;</span></span>
                        <textarea class="form-control" id="remark" name="remark" value="" placeholder="请输入备注" style="width:200px;"></textarea>
                    </div>
            	</div>
            </div> 
            <div class="row">
                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
                    <div class="input-group" >
						<a id="mysubmit" class="btn blue" href="javaScript:void(0)" onclick="ToSubmitF();"><i class="icon-ok"></i> 确定</a>&nbsp;&nbsp;&nbsp;
						<a id="myEsc" class="btn black" href="javaScript:void(0)" onclick="ToEscF();"><i class="icon-remove"></i> 取消</a>
                    </div>
            	</div>
            </div> 
	    </div>
	</form>
</div>
<script type="text/javascript">
</script>
