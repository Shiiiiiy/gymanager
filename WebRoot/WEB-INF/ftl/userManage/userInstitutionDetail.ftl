 
			 <div class="infor-con clearfix">
			 	<div class-"row clearfix">
			 		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		            	<p><label>用户名称</label><span>${(userInstitutionMap.USERNAME)!''}</span></p>
		            </div>
				    <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		            	 <p><label>状态 </label><span>
		            	   <#if dicList??>
		                          <#if userInstitutionMap??>
		                              <#list dicList as d>
			                                <#if (d.id)==(userInstitutionMap.STATUS1?number)>
			                                  	${(d.name)!""}
			                                  </#if>
		                          	  </#list>
		            	     	 </#if>
		                   </#if>  
		            	 </span></p>
		            </div>

			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		          			<p><label>机构名称</label><span>${(userInstitutionMap.NAME)!''}</span></p>
		            </div>
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			            	<p><label>联系人</label><span>${(userInstitutionMap.LINKMAN)!''}</span></p>
			        </div>
			        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			           		<p><label>手机号码</label><span>${(userInstitutionMap.TEL)!''}</span></p> 
			        </div>  
				     <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			           		<p><label>座机号码</label><span>${(userInstitutionMap.PHONE)!''}</span></p>
			        </div>
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		          			<p><label>组织机构代码</label><span>${(userInstitutionMap.ORGCODE)!''}</span></p>
		            </div>
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			            	<p><label>社会信用代码</label><span>${(userInstitutionMap.SOCIALCODE)!''}</span></p>
			        </div>
			         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			          		<p><label>邮箱</label><span>${(userInstitutionMap.EMAIL)!''}</span></p>
			        </div>
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			           		<p><label>所在省市区</label><span>${(userInstitutionMap.PROVINCE_NAME)!""} ${(userInstitutionMap.CITY_NAME)!""} ${(userInstitutionMap.AREA_NAME)!""}</span></p>
			        </div>  
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			           	   	<p class="clearfix"><label class="fl">所在地</label><span class="fl row-view1">${(userInstitutionMap.ADDRESS)!""}</span></p>
			        </div>   
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			           		<p class="clearfix"><label class="fl">备注</label><span class="fl row-view1">${(userInstitutionMap.COMMENT)!""}</span></p>
			        </div>  
       			 </div>
	       </div>
    
 
