<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
    <title>角色管理</title>
	<#include "/common/common.ftl" >
	<link rel="stylesheet" href="${rc.contextPath}/plugin/ztree/css/zTreeStyle.css" type="text/css">
	<script src="${rc.contextPath}/plugin/ztree/js/jquery.ztree.core.js"></script>
	<script src="${rc.contextPath}/plugin/ztree/js/jquery.ztree.excheck.js"></script>
</head>
<body style="overflow-x: hidden">
    <div class="content-box">
        <!--form-->
        <div class="page-header"><h2>选择菜单<small class="font-en caps"></small></h2></div>
        <form class="form-inline search_list" role="form"  id="searchForm" method="post" action="${rc.contextPath}/sysrole/opt-query/selectMenu.do">
        <div class="row" style="display:none;">
            <input type="hidden" id="roleId" name="id" value="${(roleId)!'0'}"/>
            
            <div class="btn-group" style="margin-left:15px;">
    		     <a  class="btn green" onclick="noSelectAll();" target="content-box" ><i class="icon-search" ></i>反选</a>
    	    </div>
       </div>
        <div class="row" style="margin-left:10px;float:left;"> 
             <h3>系统菜单</h3><br/>
             <div id="menus" style="height:400px;width:230px;border:1px gray solid;overflow:scroll;">
                <ul id="tree" class="ztree"></ul>
             </div>
        </div>   
        <div class="row" style="margin-left:30px;float:left;display:block;"> 
             <h3>产品服务</h3><br/>
             <div id="menus1" style="height:400px;width:230px;border:1px gray solid;overflow:scroll;">
                <ul id="tree1" class="ztree"></ul>
             </div>
        </div> 
        <div class="row" style="margin-left:30px;float:left;display:block;"> 
             <h3>支柱产业</h3><br/>
             <div id="menus2" style="height:400px;width:230px;border:1px gray solid;overflow:scroll;">
                <ul id="tree2" class="ztree"></ul>
             </div>
        </div> 
        <div class="row" style="margin-left:30px;float:left;display:block;"> 
             <h3>产业园区</h3><br/>
             <div id="menus3" style="height:400px;width:230px;border:1px gray solid;overflow:scroll;">
                <ul id="tree3" class="ztree"></ul>
             </div>
        </div> 
         <input type="hidden" id="idList" name="idList"   value="<#if selectedList??><#list selectedList as sl><#if selectedList?size gt sl_index+1>${(sl.ID)!""},<#else>${(sl.ID)!""}</#if></#list></#if>" />
         <input type="hidden" id="idList1" value="${(idList1)!''}"/>
         <input type="hidden" id="idList2" value="<#if selectedList??><#list selectedIndustryList as sl><#if selectedIndustryList?size gt sl_index+1>${(sl.ID)!""},<#else>${(sl.ID)!""}</#if></#list></#if>"/>
         <input type="hidden" id="idList3" value="<#if selectedList??><#list selectedGardenList as sl><#if selectedGardenList?size gt sl_index+1>${(sl.ID)!""},<#else>${(sl.ID)!""}</#if></#list></#if>"/>
       </form>
       <div class="row" style="float:none;">	
            <div class="form-group col-xs-12 col-sm-6 col-md-4 col-lg-3">
            	<a class="btn blue" href="javaScript:void(0)" onclick="subMenu(${(roleId)!'0'})"><i class="icon-ok"></i> 确定</a>
       			<a class="btn black" href="javaScript:void(0)" onclick="goBacks();"><i class="icon-remove"></i> 取消</a>
        	</div>
       </div>
    </div>
  
</body>
<script type="text/javascript">
       var zTreeObj;
	   var zTreeNodes;
	   //树形菜单配置
		setting = {
		    data: {
				simpleData: {
					enable: true,
					idKey: "id",
					pIdKey: "pId",
					rootPId: null  
				}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "s" }
			},
			async:{
			    enable:false
			},
			callback:{
			     //onCheck:zTreeOnCheck
			}
		}
		setting1 = {
		    data: {
				simpleData: {
					enable: true,
					idKey: "ID",
					pIdKey: "PID",
					rootPId: null  
				}
			},
			check: {
				enable: true,
				chkStyle: "checkbox",
				chkboxType: { "Y": "ps", "N": "s" }
			},
			async:{
			    enable:false
			    
			},
			callback:{
			     //onCheck:zTreeOnCheck
			}
		}
		gardenSetting = {
			    data: {
					simpleData: {
						enable: true,
						idKey: "ID",
						pIdKey: "TYPE",
						rootPId: null  
					},
					key : {
						name : "NAME"
					}
				},
				check: {
					enable: true,
					chkStyle: "checkbox",
					chkboxType: { "Y": "ps", "N": "ps" }
				},
				async:{
				    enable:false
				},
				callback:{
				     //onCheck:zTreeOnCheck
				}
			}
		
      $(function(){
            zTreeObj1=$.fn.zTree.init($("#tree1"), setting1, eval(${(menu1Data)!''}));//
            zTreeObj2=$.fn.zTree.init($("#tree2"), gardenSetting, eval(${(menu2Data)!''}));//
            zTreeObj3=$.fn.zTree.init($("#tree3"), gardenSetting, eval(${(menu3Data)!''}));//
            $.ajax({
                 url:"${rc.contextPath}/sysrole/opt-query/getMenus.do",
                 type:"post",
                 dataType:"text",
                 data:{"roleId":$("#roleId").val()},
                 success:function(data){
                      var zNodes =eval(data);
                      var rootId;
                      //剔除根节点，只展示有用菜单；
                      for(var j=0;j<zNodes.length;j++){
                           if(zNodes[j].pId==null){
                                rootId=zNodes[j].id;
                                zNodes.splice(j,1);
                           }
                      
                      }
                     
                      for(var i=0;i<zNodes.length;i++){
                          if(zNodes[i].pId==rootId){
                               zNodes[i].pId=0;
                               
                          }
                      
                      }
                      
                      //初始化树形菜单
	                  zTreeObj = $.fn.zTree.init($("#tree"), setting, zNodes);
                    
                 }
                 
            });
            
       
      });



	//提交
	    function subMenu(str){
        var idList=$("#idList").val();//页面存储最初已选的目录 a,b,c
        var sNodes = zTreeObj.getCheckedNodes(true);//获取当前已选中节点
        var arr=[];//所有目前已经选中的
        for(var i=0;i<sNodes.length;i++){
            arr.push(sNodes[i].id);
        }
        var idsAdd="";//将要新增的菜单
        var idsDel="";//将要删除的菜单
        var arr1=$("#idList").val().split(",");//页面存储最初已选的目录 a,b,c
        
        var zhi=clcIds(arr,arr1,idsAdd,idsDel);
        var idsDel=zhi[1];
        var idsAdd=zhi[0];
        
        /*************************园区***********************/
        var gardenArr =$("#idList3").val().split(",");
        var gardenNodes = zTreeObj3.getCheckedNodes(true);
        var selectGarden =[];//所有目前已经选中的
        for(var i=0;i<gardenNodes.length;i++){
        	selectGarden.push(gardenNodes[i].ID);
        }
        var gardenResult=clcIds(selectGarden,gardenArr,"","");
        /****************************************************/
        
        /*************************支柱产业***********************/
        var industryArr =$("#idList2").val().split(",");
        var industryNodes = zTreeObj2.getCheckedNodes(true);
        var selectIndustry =[];//所有目前已经选中的
        for(var i=0;i<industryNodes.length;i++){
        	selectIndustry.push(industryNodes[i].ID);
        }
        var industryResult=clcIds(selectIndustry,industryArr,"","");
        /************************产品服务********************/
        var idList_1=$("#idList1").val();
		var sNodes_1 = zTreeObj1.getCheckedNodes(true);//获取当前已选中节点
		var arr_1=[];//所有目前已经选中的
		for(var i=0;i<sNodes_1.length;i++){
            arr_1.push(sNodes_1[i].ID);
        }
        var idsAdd_1="";//将要新增的菜单
        var idsDel_1="";//将要删除的菜单
        var arr1_1=idList_1.split(",");//页面存储
        var zhi_1=clcIds(arr_1,arr1_1,idsAdd_1,idsDel_1);
        idsAdd_1=zhi_1[0];
        idsDel_1=zhi_1[1];
        outzhiA=removeMainMenu(idsAdd_1);
        outzhiD=removeMainMenu(idsDel_1);
        var idsAdd_1A=outzhiA[0];
        var idsAdd_1B=outzhiA[1];
        var idsAdd_1AB=outzhiA[2];
        var idsDel_1A=outzhiD[0];
        var idsDel_1B=outzhiD[1];
        var idsDel_1AB=outzhiD[2];
        window.location.href="${rc.contextPath}/sysrole/opt-update/allotMenu.do?roleId="+str+"&idsDel="+idsDel+"&idsAdd="+idsAdd+"&gardenAdd="+gardenResult[0]+"&gardenDel="+gardenResult[1]+"&industryAdd="+industryResult[0]+"&industryDel="+industryResult[1]
        	+"&idsDel_1A="+idsDel_1A+"&idsAdd_1A="+idsAdd_1A
        	+"&idsDel_1B="+idsDel_1B+"&idsAdd_1B="+idsAdd_1B
        	+"&idsDel_1AB="+idsDel_1AB+"&idsAdd_1AB="+idsAdd_1AB;;
    }
    function clcIds(arr,arr1,idsAdd,idsDel){
        if(arr.length==0 && arr1.length>0){//全部清空已选菜单
            idsDel=arr1.toString();
        }else if(arr1.length==0 && arr.length>0){//全部为新增菜单
            idsAdd=arr.toString();
        }else if(arr1.length>0 && arr.length>0 ){//既有新增菜单，又有剔除菜单
            for(var i=0;i<arr1.length;i++){
                for(var j=0;j<arr.length;j++){
                    if(arr1[i]==arr[j]){
                        //如果直接使用arr.splice(j,1)的话，会改变数组的长度，遍历容易出现漏网之鱼
                        arr.splice(j,1,"");
                        arr1.splice(i,1,"");
                    }
                }
            }
            if(arr.length==0 && arr1.length>0){//全部清空已选菜单
                idsDel=arr1.toString();
            }else if(arr1.length==0 && arr.length>0){//全部为新增菜单
                idsAdd=arr.toString();
            }else if(arr1.length>0 && arr.length>0 ){//既有新增菜单，又有剔除菜单
                idsAdd=arr.toString();
                idsDel=arr1.toString();
            }
        }
        return [idsAdd,idsDel];
    }
    function removeMainMenu(str){//移除INDUSTRY_A 和 INDUSTRY_B  同时进行INDUSTRY_A,INDUSTRY_B,INDUSTRY_ABMAIN的分类
    	var arr=str.split(","); 
    	var str2="";
        for(var j=0;j<arr.length;j++){
            if(arr[j]!="INDUSTRY_A" && arr[j]!="INDUSTRY_B"){
				str2=str2+arr[j]+",";
            }
        }
        var arr2=str2.split(",");
        var cA="";
        var cB="";
        var cAB="";
        for(var j=0;j<arr2.length;j++){
            if( arr2[j].indexOf("_A") >= 0 ){
				cA=cA+arr2[j]+",";
            }else if( arr2[j].indexOf("_B") >= 0 ){
            	cB=cB+arr2[j]+",";
            }else if( arr2[j].indexOf("N") >= 0 ){
            	cAB=cAB+arr2[j]+",";
            }
        }
        return [cA,cB,cAB];
    }
    //取消
	function goBacks(){
		window.location.href="${rc.contextPath}/sysrole/opt-query/roleList.do";
	}
	
	
</script>
</html>
