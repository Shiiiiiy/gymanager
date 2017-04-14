 		 <div class="infor-con clearfix">
			 	<div class-"row clearfix" >
			 		<div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
		            	<p><label>用户名称</label><span  style="word-break:break-all;" >${(userPersonMap.USERNAME)!''}</span></p>
		            </div>
		            <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			            	<p><label>真实姓名</label><span  style="word-break:break-all;" >${(userPersonMap.NAME)!''}</span></p>
			        </div>
				    <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
		            	 <p><label>状态 </label><span title="状态" style="word-break:break-all;" >
		            	   <#if dicList??>
		                          <#if userPersonMap??>
		                              <#list dicList as d>
			                                  <#if (d.id)==(userPersonMap.STATUS1?number)>
			                                  	${(d.name)!""}
			                                  </#if>
		                          	  </#list>
		            	     	 </#if>
		                   </#if>  
		            	 </span></p>
		            </div>
		            <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			           		<p><label>手机号码</label><span  style="word-break:break-all;" >${(userPersonMap.TEL)!''}</span></p> 
			        </div>  
			        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			            	<p><label>性别</label><span title="性别" style="word-break:break-all;" >
								 <#if userPersonMap?? && userPersonMap.GENDER?? && userPersonMap.GENDER =='0'>
								 	女
								 <#else>
								 	男
								 </#if>
							
							</span></p>
			        </div>


				     <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6">
			           		<p><label>座机号码</label><span  style="word-break:break-all;" >${(userPersonMap.PHONE)!''}</span></p>
			        </div>
			        <div class="col-xs-12 col-sm-6 col-md-6 col-lg-6" >
		          			<p><label>证件类型</label><span title="证件类型" style="word-break:break-all;" >
		          		 	<#if userPersonMap?? && userPersonMap.CARDTYPE?? && userPersonMap.CARDTYPE=='CARD_B' >
			          				驾驶证
		          			</#if>
		          			<#if userPersonMap?? && userPersonMap.CARDTYPE?? && userPersonMap.CARDTYPE=='CARD_A' >
			          				身份证
		          			</#if>
		          			</span></p>
		            </div>
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			            	<p><label>证件号码</label><span  style="word-break:break-all;" >${(userPersonMap.CARDNUM)!''}</span></p>
			        </div>
			         <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			          		<p><label>邮箱</label><span  style="word-break:break-all;" >${(userPersonMap.EMAIL)!''}</span></p>
			        </div>

			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			           		<p><label>所在省市区</label><span title="所在省市区" style="word-break:break-all;" >${(userPersonMap.PROVINCE_NAME)!""} ${(userPersonMap.CITY_NAME)!''} ${(userPersonMap.AREA_NAME)!""}</span></p>
			        </div>  
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			           	   	<p class="clearfix"><label class="fl">通讯地址</label><span class="fl row-view1"  style="word-break:break-all;" >${(userPersonMap.LINK_ADDRESS)!''}</span></p>
			        </div>   
			        <div class="col-xs-12 col-sm-12 col-md-12 col-lg-12">
			           		<p class="clearfix"><label class="fl">备注</label><span  class="fl row-view1" style="word-break:break-all;" >${(userPersonMap.COMMENT)!""}</span></p>
			        </div>  
       			 </div>
	       </div>