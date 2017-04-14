<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<#include "/common/common.ftl" >
		<link href="${rc.contextPath}/uploadify/css/uploadify.css" rel="stylesheet" type="text/css" />  
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/jquery.uploadify.js"></script>
	<script type="text/javascript" src="${rc.contextPath}/uploadify/js/uploadindex.js"></script>  
    <title></title>
</head>
<body>
   
    <div class="content-box">
        <!--form-->
        <div class="page-header">
        	<h2>
        	        <#if userPersonMap?? && userPersonMap.GID?? >
	        			编辑
	        		<#else>
	        			新增
	        		</#if>
        	    <small class="font-en caps">
	        		
        		</small>
        	</h2>
        </div>
         <form class="form-inline search_list" role="form"  id="addOrEditUser" method="post" action="${rc.contextPath}/userManage/opt-save/saveUserPerson?pageNo=${pageNo!""}&pageSize=${pageSize!""}">
        <div class="tabfix">
            <!-- Nav tabs -->
            <ul class="nav nav-tabs" role="tablist">
                <li role="presentation" class="active" id="11"><a href="#1" aria-controls="1" role="tab" data-toggle="tab">基础信息</a>
                <li role="presentation" id="22"><a href="#2" aria-controls="2" role="tab" data-toggle="tab">详细信息</a>
            </ul>
            <!-- Tab panes -->
            <div class="tab-content">
            
                <div role="tabpanel" class="tab-pane active" id="1">
                        
		            <input type="hidden" id="guserId" name="id" value="${(userPersonMap.GID)!''}"/>
		            <input type="hidden" id="personId" name="personId" value="${(userPersonMap.ID)!''}"/>
		             <input type="hidden" id="myImg" name="image" value="${(userInstitutionMap.IMAGE1)!''}"/>
		             
		            <div class="row">
			           <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				            <div class="input-group">
				                <span class="input-group-addon">用户名称 <span style="color:red;">*</span></span>
				                <input type="text" class="form-control" id="username" name="username" placeholder="请输入用户名" style="width:200px;" value="${(userPersonMap.USERNAME)!''}" >
				            </div>
				        </div>
				    </div>   
				    <div class="row">
				        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
				            <div class="input-group">
				                <span class="input-group-addon">用户密码<span style="color:red;">*</span></span>
				                <input type="password" class="form-control" id="password" name="password" placeholder="请输入用户密码" style="width:200px;" value="<#if userPersonMap??>********</#if>" >
				            </div>
				        </div>
				    </div>  
				  <#if userPersonMap??>
				  <#else> 
				       <div class="row">  
					        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
					            <div class="input-group">
					                <span class="input-group-addon">确认密码<span style="color:red;">*</span></span>
					                <input type="password" class="form-control" id="password1" name="password1" placeholder="请再次输入密码" style="width:200px;" value="" >
					            </div>
					        </div>
				       </div>
			      </#if>
			        <div class="row">  
			            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			                <div class="input-group">
			                    <span class="input-group-addon">状&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;态 <span style="color:red;">*</span></span>
			                    <#if dicList??>
		                              <#list dicList as d>
		                                 <#if userPersonMap??>
			                                  <#if d.id==(userPersonMap.STATUS1?number)>
							                    <label class="input-group-addon gray-bg" style="width:45px;">
							                        <input type="radio" aria-label="..." value="${(d.id)!''}" checked name="status">
							                    </label>
							                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
				                              <#else>
			                                    <label class="input-group-addon gray-bg" style="width:45px;">
							                        <input type="radio" aria-label="..." value="${(d.id)!''}"  name="status">
							                    </label>
							                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
			                                  </#if>
		                                 <#else>
		                                      <#if d.code=='ENABLE'>
			                                        <label class="input-group-addon gray-bg" style="width:45px;">
								                        <input type="radio" aria-label="..." value="${(d.id)!''}" checked  name="status">
								                    </label>
								                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
							                  <#else>
							                         <label class="input-group-addon gray-bg" style="width:45px;">
							                        <input type="radio" aria-label="..." value="${(d.id)!''}"  name="status">
								                    </label>
								                    <label class="form-control" style="width:55px;">${(d.name)!""}</label>
							                  </#if>  
		                                 </#if>
		                              </#list>
		                         </#if>  
			                    
			                </div>
			              </div>
			            </div>
			            <div class="row">  
				             <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			                    <div class="input-group" id="uploadImg">
			                        <span class="input-group-addon">头&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;像&nbsp;</span>
			                        <input type="text" class="form-control"   id="image" name="image_name" placeholder="请上传头像" style="width:200px;" readonly="readonly" value="${(userPersonMap.IMAGE1)!''}">
			                        <input type="hidden" class="form-control" id="dbimage" name="image_url" style="width:200px;">
			                        <a href="javascript:void(0);"  class="btn black" id="up_btn"><i class="icon-cloud"></i>上传</a>&nbsp;
			                        <a href='javascript:void(0);' id="viewImg" class='btn green' onclick='javascript:void(0);' style="display:none;"><i class="icon-eye-open"></i>预览</a>
			                    </div>
			                    
			                </div>
			                <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12" id="touxiang" <#if userPersonMap?? && userPersonMap.IMAGE1?? && userPersonMap.IMAGE1!=""> <#else>style="display:none;"</#if> >
			                    <div class="input-group" >
			                      <#if userPersonMap?? && userPersonMap.IMAGE1?? && userPersonMap.IMAGE1!=""> 
			                        <img id="viewImage" src="/gykjy/plugins/images/allImages/${(userPersonMap.IMAGE1)!''}" style="max-width:200px;max-height:150px;" />
			                      </#if>
			                    </div>
			                    
			                </div>
			                
			           </div> 
			       <div class="row"> 
			            <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
			                <div class="input-group">
			                    <span class="input-group-addon">备&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;注&nbsp;&nbsp;</span>
			                    <textarea class="form-control" id="comment" name="comment" placeholder="请输入备注信息" style="width:450px;height:100px;">${(userPersonMap.COMMENT)!""}</textarea>
			                </div>
			            </div>
		            </div>
		            
		        
		            
                </div>
                <div role="tabpanel" class="tab-pane" id="2">
                        
                <div class="row">       
	                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
					            <div class="input-group">
					                <span class="input-group-addon">真实姓名</span>
					                <input type="text" class="form-control" id="name" name="name" placeholder="请输入真实姓名" style="width:200px;" value="${(userPersonMap.NAME)!''}" >
					            </div>
					        
	                </div>
                
	                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
					            <div class="input-group">
					                <span class="input-group-addon">&nbsp;性&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;别</span>
					                 
	                                        <label class="input-group-addon gray-bg" style="width:45px;">
						                        <input type="radio" aria-label="..." value="1"  <#if userPersonMap?? ><#if userPersonMap.GENDER?? && userPersonMap.GENDER =='1'>checked</#if> <#else>checked</#if>  name="gender">
						                    </label>
						                    <label class="form-control" style="width:55px;">男</label>
					              
					                         <label class="input-group-addon gray-bg" style="width:45px;">
					                        <input type="radio" aria-label="..." value="0" <#if userPersonMap?? && userPersonMap.GENDER?? && userPersonMap.GENDER=='0'> checked</#if>   name="gender">
						                    </label>
						                    <label class="form-control" style="width:55px;">女</label>
					                  

					            </div>
					        
	                </div>
                
	                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
					            <div class="input-group">
					                <span class="input-group-addon">手机号码</span>
					                <input type="text" class="form-control" id="tel" name="tel" placeholder="请输入手机号码" style="width:200px;" value="${(userPersonMap.TEL)!''}" >
					            </div>
					        
	                </div>
                </div>
                <div class="row">
	                 <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
					            <div class="input-group">
					                <span class="input-group-addon">座机号码</span>
					                <input type="text" class="form-control" id="phone" name="phone" placeholder="请输入座机号码" style="width:200px;" value="${(userPersonMap.PHONE)!''}" >
					            </div>
					        
	                 </div>
                
	                 
	                <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
	                    <div class="input-group">
		                    <span class="input-group-addon">证件类型</span>
		                    <select class="form-control" name="cardtype" id="cardtype" style="width:200px;">
		                        <option selected="selected" value="">请选择..</option>
		                        <option <#if userPersonMap?? && userPersonMap.CARDTYPE?? && userPersonMap.CARDTYPE=='CARD_A' >selected="selected"</#if>  value="CARD_A">身份证</option>
		                        <option <#if userPersonMap?? && userPersonMap.CARDTYPE?? && userPersonMap.CARDTYPE=='CARD_B' >selected="selected"</#if>  value="CARD_B">驾驶证</option>
		                    </select>
	                    </div>
	                </div>
	                
	                 <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
					            <div class="input-group">
					                <span class="input-group-addon">证件号码</span>
					                <input type="text" class="form-control" id="cardnum" name="cardnum" placeholder="请输入证件号码" style="width:200px;" value="${(userPersonMap.CARDNUM)!''}" >
					            </div>
					        
	                 </div>
	                
                </div>
                <div class="row">
                     
                
	                 <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
					       <div class="input-group">
							    <span class="input-group-addon">所&nbsp;在&nbsp;省&nbsp;</span>
							    <select class="form-control" name="addProvince" id="addProvince" style="width:200px;" onchange="getCityOrArea('A',this)">
			                        <option   value="">请选择..</option>
			                               <#if mylist??>
			                                       <#list mylist as mt>
			                                       		<#if userPersonMap?? && userPersonMap.ADD_PROVINCE?? && userPersonMap.ADD_PROVINCE == mt.ID >
				                                            <option value="${(mt.ID)!''}" name="${(mt.CODE)!''}" selected = "selected">${(mt.NAME)!""}</option>
			                                       		<#else>
				                                            <option value="${(mt.ID)!''}" name="${(mt.CODE)!''}" >${(mt.NAME)!""}</option>
			                                       		</#if>
			                                       </#list>
			                                </#if>
			                    </select>
			               </div>    
			         </div> 
			         <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">      
			               <div class="input-group">     
			                    <span class="input-group-addon">&nbsp;所&nbsp;在&nbsp;市&nbsp;</span>
			                    <select class="form-control" name="addCity" id="addCity"  onchange="getArea(this)"  style="width:200px;">
			                       	<option  value="">请选择..</option>
			                       	<#if CITYlist??>
			                        <#list CITYlist as mt>
			                       		<#if userPersonMap?? && userPersonMap.ADD_CITY?? && (userPersonMap.ADD_CITY+"")==(mt.ID+"") >
			                                 <option value="${(mt.ID)!''}"  name="${(mt.CODE)!''}" selected = "selected">${(mt.NAME)!""}</option>
			                            <#else>
			                                 <option value="${(mt.ID)!''}"  name="${(mt.CODE)!''}">${(mt.NAME)!""}</option>
			                        	</#if>
			                        </#list>
			                        </#if>
			                    </select>
			             </div>
			        </div> 
			        <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">             
			             <div class="input-group">    
			                    <span class="input-group-addon">&nbsp;所&nbsp;在&nbsp;区&nbsp;</span>   
			                    <select class="form-control" name="addArea" id="addArea" style="width:200px;">
			                        <option  value="">请选择..</option>
			                       	<#if AREAlist??>
			                        <#list AREAlist as mt>
			                       		<#if userPersonMap?? && userPersonMap.ADD_AREA?? && userPersonMap.ADD_AREA==mt.ID >
			                                 <option value="${(mt.ID)!''}"  name="${(mt.CODE)!''}" selected = "selected">${(mt.NAME)!""}</option>
			                            <#else>
			                                 <option value="${(mt.ID)!''}" name="${(mt.CODE)!''}" >${(mt.NAME)!""}</option>
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
					                <span class="input-group-addon">邮&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;箱&nbsp;</span>
					                <input type="text" class="form-control" id="email" name="email" placeholder="请输入邮箱" style="width:200px;" value="${(userPersonMap.EMAIL)!''}" >
					            </div>
	                </div>
                     <div class="col-xs-12 col-sm-6 col-md-4 col-lg-3">
					            <div class="input-group">
					                <span class="input-group-addon">通讯地址</span>
					                <input type="text" class="form-control" id="linkAddress" name="linkAddress" placeholder="请输入通讯地址" style="width:570px;" value="${(userPersonMap.LINK_ADDRESS)!''}" >
					            </div>
					        
	                 </div>
                 </div>
            </div>
            
               <div class="row">	
			            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
			            	<a class="btn blue" href="javaScript:void(0)" onclick="save();"><i class="icon-ok"></i> 确定</a>
			       			<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
			        	</div>
		        	</div>
            
        </div>
        
        
           <div class="tc"></br>
	           
	       </div>
    
        </form>
    </div>
</body>
<script type="text/javascript">

	
	$(function(){
		var zhi="${rc.contextPath}";
		var nowproj=zhi.substring(1,zhi.length);
		$('#up_btn').click(function(){
			file.upload({
				contextPath:"${rc.contextPath}", //固定写法
				obj:this, //固定写法
				callPerComplete:callPerComplete, //回调函数 ,可以写成 callPerComplete:function(){...}的形式
				callbackAllComplete:callbackAllComplete,//回调函数, 可以写成 callPerComplete:function(){...}的形式
				maxFileNum:1, //队列中最大文件数量
				fileType:'*.jpg;*.png', //可选文件类型
				fileTypeDesc:'图片文件', 	//可选文件类型描述,部分浏览器不会显示,但不影响校验
				///cms/WebRoot/images
				saveLocation:'/gykjy/plugins/images/allImages', //指定保存路径(一个TOMCAT下跑两个项目互访文件时用)
			
				}); 
		});
	});
	function callPerComplete(obj,data){
		eval('var json = ' + data);
		var fileName=json.fileNameInServer;
		
		$("#myImg").val(fileName);
	    $("#image").val(fileName);
		var str='<img  src="/gykjy/plugins/images/allImages/'+fileName+'" style="max-width:200px;max-height:150px;"/>';
		$("#touxiang").children().html(str);
		$("#touxiang").show();
	}
	
	function callbackAllComplete(obj){
	
	}
	
	
	//取消
	function goBacks(){
		window.location.href="${rc.contextPath}/userManage/opt-query/personList.do?flag=1";
	}
	
	//提交用户信息
	function save(){
		$("#11").addClass("active");
		$("#1").addClass("active");
		$("#22").removeClass("active");
		$("#2").removeClass("active");
	    if(validate_form("addOrEditUser")){
           $("#addOrEditUser").submit();
		
	    }
	}
	
	//获取市或区
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
			            oj=$("select[name='addCity']");
			            $("select[name='addCity']").html("<option value=''>请选择..</option>");
			        	$("select[name='addArea']").html("<option value=''>请选择..</option>");
			        }else if(str=="B"){
			            oj=$("select[name='addArea']");
			        }
			        $(oj).html("<option value=''>请选择..</option>");
			         if(msg!="nomore"){
				         $.each(msg,function(index,value){
			                $(oj).append(
			                	"<option value='"+value.ID+"' name='"+value.CODE+"'>"+value.NAME+"</option>"
			                );
			             });
		            } 
			    }
		});
    }
	
	
	
	function validate_form(formid){
	
    		var tt = validateFormObject();
    		var guserId=$("#guserId").val();
    		var stat=0;
    		
    		tt.checkLength("username","用户名",true,2,20,checkUsername);
    		//tt.checkLength("username","用户名",true,2,20);
    		tt.checkLength("password","用户密码",true,6,16);
    		if(guserId=="" ||　guserId==null || guserId==undefined ){
    		  tt.checkLength("password1","用户密码",true,6,16,checkPassword);
    		}
    		tt.checkLength("status","状态",true,1,1);
    		tt.checkLength("comment","备注信息",false,1,200);
    		
    		if(!tt.validate()){
    		    stat=1;
    		}
    		
    		
    		tt.checkLength("name","真实姓名",false,1,20);
    		tt.checkLength("gender","性别",false,1,1);
    		tt.checkLength("tel","手机号码",false,1,11,isTheTel);
    		tt.checkLength("phone","座机号码",false,1,13,isPhone);
    		tt.checkLength("cardnum","证件号码",false,1,40);
    		tt.checkLength("email","邮箱",false,1,20,isTheEmail);
    		tt.checkLength("linkAddress","通讯地址",false,1,100);
    		
    		if(!tt.validate() && stat==0 ){
    		     $("#1").removeClass("active");
			     $("#11").removeClass("active");
			     $("#2").addClass("active");
			     $("#22").addClass("active");
    		}
    		
    		return tt.validate();
    }
    
    
    //座机号码验证
    function isPhone(){
		var obj=$('#tel').val()+"";
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
		var obj=$('#tel').val()+"";
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
		var obj=$('#email').val()+"";
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
    
    //校验再次输入密码是否正确
    function checkPassword(){
           var str="";
           if($("#password").val()!=$("#password1").val()){
                str="两次密码输入不一致，请重试！";
           }
           
           return str;
    }
    
    	
    function  checkUsername(){
    		var username =$("#username").val();
    		
    		var regStr =  /\w+$/;  
    		//错误提示语
    		var str = "";  
    		if(true || regStr.test(username)){
        		$.ajax({
				     url:"${rc.contextPath}/userManage/opt-query/checkUsername.do?username="+encodeURI(encodeURI($("#username").val()))+"&userType=PERSON&guserId="+$("#guserId").val(),
				     dataType:"json",
				     async:false,
				     type:"POST",
				     success:function(data){
				        if(data.result=='ok'){
				            
				        }else{
				            str="该用户名已存在，请换一个试试！";
				        }  
				     }
				
				
				});
    		}else{
    			str = "请输入字母、数字或下划线！";
    		}
    		
    		return str;
     }
	
	
</script>
</html>
