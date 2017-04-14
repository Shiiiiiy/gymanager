<style>
	.xz-list{float:left;border:#ddd solid 1px;width:40%;padding:15px;min-height:355px;}
	.xz-list li{cursor:pointer; padding-left:20px;}
	.xz-list2{float:left;border:#ddd solid 0px;width:20%;padding:24px;min-height:355px;}
</style>
<div class="clearfix">
<input type="hidden" id="hidestr" value="" ></input>
<#if ClassA??>
	<#assign tempx = ClassA?size >
</#if>
<ul id="sp001" style="cursor:pointer;" class="xz-list">
	<#if ClassA?? && ClassA?size gt 0>
	<b style="cursor:text;">工业产品</b>
	</#if>
	<#if ClassA??>
	<#list ClassA as aa>
		<li><a value="${(aa.ID)!'' }" name="${aa_index }">${(aa.NAME)!'' }</a></li>
	</#list>
	</#if>
	<#if ClassB?? && ClassB?size gt 0>
    <b style="cursor:text;">生产服务</b>
    </#if>
	<#if ClassB??>
	<#list ClassB as bb>
		<li><a value="${(bb.ID)!'' }" name="${bb_index+tempx}">${(bb.NAME)!'' }</a></li>
	</#list>
	</#if>
</ul>
<div class="xz-list2">
	<div style="margin-top:150px;">
		<hr>
		<button onclick="toEsc();" class="btn">清空</button>
	</div>
</div>
<ul id="sp002" style="float:right;" class="xz-list">
	<#if ClassA??>
	<#list ClassA as aa>
		<li><a value="${(aa.ID)!'' }" name="${aa_index }">${(aa.NAME)!'' }</a></li>
	</#list>
	</#if>
	<#if ClassB??>
	<#list ClassB as bb>
		<li><a value="${(bb.ID)!'' }" name="${bb_index+tempx}">${(bb.NAME)!'' }</a></li>
	</#list>
	</#if>
</ul>

</div>
<script>
    $(function(){
        $("#sp002 a").hide();
        $("#sp002 a").bind('click',function(){
        	var zhi=Number($(this).attr("name"));
            $("#sp001 a").eq(zhi).toggle();
            $(this).toggle();
            getValuesMe();
        });
        $("#sp001 a").bind('click',function(){
            var zhi=Number($(this).attr("name"));
            $("#sp002 a").eq(zhi).toggle();
            $(this).toggle();
            getValuesMe();
        });
        var UlHeight=$("#sp001").height();
        $("#sp002").height(UlHeight);
    });
    function getValuesMe(){//赋值
        var str="";
        for (var i=0;i<$("#sp002 a").length;i++)
        {
            if(!$("#sp002 a").eq(i).is(':hidden')){
                str=str+$("#sp002 a").eq(i).attr("value")+",";
            }
        }
        $("#hidestr").val(str);
    }
    function toEsc(){//清空
        $("#sp002 a").hide();
        $("#sp001 a").show();
        $("#hidestr").val("");
    }
</script>