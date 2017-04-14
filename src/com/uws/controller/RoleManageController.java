package com.uws.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import net.sf.json.JSONArray;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.base.model.Message;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.model.Dic;
import com.uws.model.SysRole;
import com.uws.model.SysRoleUser;
import com.uws.model.SysUser;
import com.uws.service.IIndustryGardenService;
import com.uws.service.IServiceService;
import com.uws.service.ISysDicService;
import com.uws.service.ISysRolePermissionService;
import com.uws.service.ISysRoleService;
import com.uws.service.ISysUserService;
import com.uws.util.Constants;
import com.uws.util.Util;
/**
 * 角色管理controller
 * @author hejin
 *
 */
@Controller
public class RoleManageController extends BaseController{

	
	@Autowired
	private ISysDicService iSysDicService;
	
	@Autowired
	private ISysRoleService sysRoleService;
	
	@Autowired 
	private ISysUserService sysUserService;
	
	@Autowired
	private IIndustryGardenService industryGardenService;
	
	@Autowired
	private ISysRolePermissionService sysRolePermissionService;
	
	@Autowired
	private IServiceService serviceService;
	/**
	 * 角色列表页
	 */
	@RequestMapping("/sysrole/opt-query/roleList")
	public String roleList(HttpServletRequest request,SysRole sRole,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		
		Page page=sysRoleService.queryRoleList(param,sRole);
		//是否保存查询条件
		String flag=request.getParameter("flag");
		
		if("1".equals(flag)){
			sRole=(SysRole)session.getAttribute("srole");
		}
		
		//保存查询条件
		session.setAttribute("srole", sRole);
		
		
		//启用状态列表
		List<Dic> dicList=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//字典——启用
		model.addAttribute("dic_e", DefaultValue.DIC_ENABLE);
		//字典——禁用
		model.addAttribute("dic_d", DefaultValue.DIC_DISABLE);
		
		model.addAttribute("page", page);
		model.addAttribute("dicList", dicList);
	    return "sysrole/roleList";  
	}
	
	
	/**
	 * 新增或编辑角色页面
	 */
	@RequestMapping("/sysrole/opt-addOrEdit/addOrEditRole")
	public String addOrEditRole(HttpServletRequest request,Model model,HttpSession session){
		//角色id
		String roleId=request.getParameter("roleId");
		
		//启用状态列表
		List<Dic> dicList=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		if(!Util.isNull(roleId)){//修改
        	SysRole sRole=sysRoleService.queryRoleById(Long.parseLong(roleId));
        	
        	model.addAttribute("sRole",sRole);
		}
        model.addAttribute("dicList",dicList);
	    return "sysrole/addOrEditRole";  
	}
	
	
	/**
	 *保存角色
	 * */
	@RequestMapping("/sysrole/opt-save/saveRole" )
	public String saveRole(ModelMap model,SysRole sRole, HttpServletRequest request){
		
		Message msg = new Message("/sysrole/opt-query/roleList.do");
        if(sRole.getId()!=null && sRole.getId()!=0){//修改
        	
        	SysRole sRole01=sysRoleService.queryRoleById(sRole.getId());
        	
        	sRole01.setRemarker(sRole.getRemarker());
        	sRole01.setRoleCode(sRole.getRoleCode());
        	sRole01.setRoleName(sRole.getRoleName());
        	
        	sysRoleService.saveSysRole(sRole01);
        	msg.addParamForward("flag", "1");
		}else{//新增
			
			sysRoleService.saveSysRole(sRole);
		}
        msg.setTips("保存成功");
        model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        return "toForward";
        
	}
	
	
	/**
	 *禁用或启用角色
	 * */
	@RequestMapping("/sysrole/opt-update/updateRole" )
	@ResponseBody
	public String updateRole(ModelMap model,HttpServletRequest request){
		//角色id
		String roleId=request.getParameter("roleId");
		//标识是禁用还是启用
		String flag=request.getParameter("flag");
		
		Dic dic=new Dic();
		List<Dic>  list=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
        if(!Util.isNull(roleId) && roleId!="0" && "A".equals(flag)){//禁用
        	SysRole sRole=sysRoleService.queryRoleById(Long.parseLong(roleId));
        	//字典表——禁用
    		
    		if(list!=null && list.size()>0){
    			int count=list.size();
    			for(int i=0;i<count;i++){
    				if(DefaultValue.DIC_DISABLE.equals(list.get(i).getCode())){
    					dic=list.get(i);
    				}
    				
    			}
    		}
    		
        	sRole.setStatus(dic.getId());
        	//修改启用状态字段
        	sysRoleService.saveSysRole(sRole);
        	
		}else if(!Util.isNull(roleId) && roleId!="0" && "B".equals(flag)){//启用
			SysRole sRole=sysRoleService.queryRoleById(Long.parseLong(roleId));
			
			if(list!=null && list.size()>0){
				int count=list.size();
    			for(int i=0;i<count;i++){
    				if(DefaultValue.DIC_ENABLE.equals(list.get(i).getCode())){
    					dic=list.get(i);
    				}
    				
    			}
			}
			
        	sRole.setStatus(dic.getId());
        	//修改启用状态字段
			sysRoleService.saveSysRole(sRole);
		
		}
        
        return "{\"result\":\"ok\"}";
	}
	
	
	/**
	 *删除角色
	 * */
	@RequestMapping("/sysrole/opt-del/delRole" )
	public String delRole(ModelMap model,HttpServletRequest request){
		//角色id
		String roleId=request.getParameter("roleId");
		
        if(!Util.isNull(roleId) && roleId!="0"){//修改
        	SysRole sRole=sysRoleService.queryRoleById(Long.parseLong(roleId));
        	sysRoleService.delSysRole(sRole);
        	
		}
        
        return "redirect:/sysrole/opt-query/roleList.do?flag=1";
	}
	
	
	/**
	 *校验角色编码是否已存在
	 * */
	@RequestMapping("/sysrole/opt-query/checkRoleCode" )
	@ResponseBody
	public String checkRoleCode(ModelMap model,HttpServletRequest request){
		//角色编码
		String roleCode=request.getParameter("roleCode");
		//角色id
		String roleId=request.getParameter("roleId");
		
		String result="{\"result\":\"ok\"}";
		
        if(!Util.isNull(roleCode)){
        	SysRole sRole=sysRoleService.queryRoleByCode(roleCode);
        	if(!Util.isNull(roleId)){//修改
        		
        		if(sRole!=null &&  sRole.getId()!=null && !roleId.equals(sRole.getId().toString())){
        			
            		result="{\"result\":\"no\"}";
            	}
        	}else{//新增
        	
        		if(sRole!=null &&  sRole.getId()!=null ){
        		
            		result="{\"result\":\"no\"}";
            	}
        	}
        	
        	
		}
        
        return result;
	}
	
	
	/**
	 *选择用户页面
	 * */
	@RequestMapping("/sysrole/opt-query/selectUser" )
	public String selectUser(ModelMap model,SysUser suser,HttpServletRequest request){
		//角色id
		String roleId=request.getParameter("id");
		//已选择的用户id列表
		String userIdList=request.getParameter("idList");
		//全部删除，但未提交的标识符
		String myflag=request.getParameter("myflag");
	
		
		suser.setStatus(iSysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE).getId());
		
		Map<String, Object> param = this.initParamMap(request);
        if(!Util.isNull(roleId) && roleId!="0"){
        	
        	Page page=sysRoleService.getUserPage(param, suser);
        	List<Map> selectedList=new ArrayList();
        	
        	//某个角色的用户列表
    		if(!Util.isNull(userIdList)){
    			selectedList=sysRoleService.getMyUserList(userIdList);
    		}else{
    			
    			if("ok".equals(myflag)){
        		     selectedList=sysRoleService.getSelectedUserList(roleId);
    			}
    		}
    		
    		//角色列表分页数据
    		List<Map> userList=page.getList();
    		

    		//标记哪些用户是该角色已经拥有的
    		if(selectedList!=null && selectedList.size()>0){
    			for(Map mr:selectedList){
    				for(Map mu:userList){
    				   if(mr.get("ID")!=null){	
    						if(mr.get("ID").equals(mu.get("ID"))){
    							mu.put("PWD", "ok");
    							break;
    						}
    				   }
    				}
    				
    			}
    		}
    		
    		//保存查询条件
    		model.addAttribute("myuser",suser);
    		
    		model.addAttribute("page",page);
    		model.addAttribute("roleId",roleId);
    		model.addAttribute("selectedList",selectedList);
		}
        
        
        
        return "sysrole/selectUser";
	}
	
	

	
	
	/**
	 *将选中的用户分配给指定角色
	 * */
	@RequestMapping("/sysrole/opt-update/allotUser" )
	public String allotUser(ModelMap model,HttpServletRequest request){
		//角色id
		String roleId=request.getParameter("roleId");
		//选中的用户id列表
		String idList=request.getParameter("idList");
		
        if(!Util.isNull(roleId) && roleId!="0"){
        	sysRoleService.allotUser(idList, roleId);
        	
		}
        
        return "redirect:/sysrole/opt-query/roleList.do?flag=1";
	}
	
	
	/**
	 *选择菜单页面
	 * */
	@RequestMapping("/sysrole/opt-query/selectMenu" )
	public String selectMenu(ModelMap model,HttpServletRequest request){
		//角色id
		String roleId=request.getParameter("roleId");
			
        if(!Util.isNull(roleId) && roleId!="0"){
        	List<Map> selectedList=sysRoleService.getMenuList(roleId);
        	model.addAttribute("selectedList",selectedList);
        	model.addAttribute("roleId",roleId);
        	
        	
        	List<Map> selectedGardenList=sysRolePermissionService.queryPerMapListByRole(roleId, Constants.ROLE_PER_GARDEN);
        	model.addAttribute("selectedGardenList", selectedGardenList);
		
        	List<Map> selectedIndustryList=sysRolePermissionService.queryPerMapListByRole(roleId, Constants.ROLE_PER_INDUSTRY);
        	model.addAttribute("selectedIndustryList", selectedIndustryList);
        	
	        try {
	            //初始化产品服务菜单1
	            model.addAttribute("menu1Data",initmenu1(roleId,model) );
		        //初始化支柱产业菜单2
		        model.addAttribute("menu2Data",initmenu2(roleId,selectedIndustryList));
	        	//初始化产业园区菜单3
				model.addAttribute("menu3Data",initmenu3(roleId,selectedGardenList));
			} catch (JsonGenerationException e) {
				e.printStackTrace();
			} catch (JsonMappingException e) {
				e.printStackTrace();
			} catch (IOException e) {
				e.printStackTrace();
			}
        }
        return "sysrole/selectMenu";
	}
	
	/**
	 * 初始化产品服务菜单
	 * json 字符串
	 * */
	public String initmenu1(String roleId,ModelMap model) {
		String str="";
		List<Map> menuList1=serviceService.getIndustryList(Constants.INDUSTRY_A);//工业产品
		List<Map> menuList2=serviceService.getIndustryList(Constants.INDUSTRY_B);//生产服务
		List<Map> menuList3=new ArrayList();//产品服务主页
		Map a0=new HashMap();a0.put("ID", "INDUSTRY_A");a0.put("PID", "MAIN");a0.put("name", "工业产品");a0.put("open", "true");
		Map a1=new HashMap();a1.put("ID", "INDUSTRY_B");a1.put("PID", "MAIN");a1.put("name", "生产服务");a1.put("open", "true");
		Map a3=new HashMap();a3.put("ID", "N");a3.put("PID", "MAIN");a3.put("name", "产品服务主页");a3.put("open", "true");
		//数据库查询roleId 对应的权限 的菜单  通过对比
		List<Map> chooseedList1=sysRolePermissionService.queryPerMapListByRole(roleId, Constants.ROLE_PER_INDUSTRY_A);
		List<Map> chooseedList2=sysRolePermissionService.queryPerMapListByRole(roleId, Constants.ROLE_PER_INDUSTRY_B);
		List<Map> chooseedList3=sysRolePermissionService.queryPerMapListByRole(roleId, Constants.ROLE_PER_INDUSTRY_M);//AB主页
		if(chooseedList1!=null && chooseedList1.size()>0){a0.put("checked", "true");}menuList1.add(a0);
		if(chooseedList2!=null && chooseedList2.size()>0){a1.put("checked", "true");}menuList2.add(a1);
		if(chooseedList3!=null && chooseedList3.size()>0){a3.put("checked", "true");}menuList3.add(a3);
		//
		if(chooseedList1!=null && chooseedList1.size()>0 && menuList1.size()>0){
			for (int i = 0; i < menuList1.size(); i++) {
				Map aa=menuList1.get(i);
				for (int j = 0; j < chooseedList1.size(); j++) {
					Map bb=chooseedList1.get(j);
					if(bb.get("ID").equals(aa.get("ID"))){
						menuList1.get(i).put("checked", "true");
					}
				}
			}
		}
		if(chooseedList2!=null && chooseedList2.size()>0 && menuList2.size()>0){
			for (int i = 0; i < menuList2.size(); i++) {
				Map aa=menuList2.get(i);
				for (int j = 0; j < chooseedList2.size(); j++) {
					Map bb=chooseedList2.get(j);
					if(bb.get("ID").equals(aa.get("ID"))){
						menuList2.get(i).put("checked", "true");
					}
				}
			}
		}
		menuList1.addAll(menuList2);
		menuList1.addAll(menuList3);
		String idList1="";
		for (int i = 0; i < menuList1.size(); i++) {
			if("true".equals((String)menuList1.get(i).get("checked"))){
				idList1=idList1+menuList1.get(i).get("ID")+",";
			}
		}
		if(idList1.length()>0){ idList1 = idList1.substring(0,idList1.length()-1);}
		model.addAttribute("idList1",idList1);
		try {
			str=new ObjectMapper().writeValueAsString(menuList1);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return str;
	}
	/**
	 * 初始化支柱产业服务菜单
	 * json 字符串
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String initmenu2(String roleId,List<Map> selectedIndustryList) throws JsonGenerationException, JsonMappingException, IOException {
		
		List<String> idList = new ArrayList<String>();
		for (Map selectedMap : selectedIndustryList) {
			idList.add(selectedMap.get("ID").toString());
		}
		//所有支柱产业map集合
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(Constants.INDUSTRY_D, Constants.INDUSTRY_TYPE,true);
		
		Map<String,String> map = new HashMap<String, String>();
		map.put("ID","MODULE_E_MAIN");
		map.put("NAME","主模块");
		map.put("TYPE",Constants.ROLE_PER_INDUSTRY);
		mapList.add(map);
		
		for (Map map1 : mapList) {
			map1.put("open", true);
			if(idList.contains(map1.get("ID"))){
				map1.put("checked", true);
			}
		}
		
		Map parentNode = new HashMap();
		parentNode.put("ID", Constants.ROLE_PER_INDUSTRY);
		parentNode.put("TYPE", null);
		parentNode.put("NAME", "支柱产业");
		parentNode.put("open", true);
		if(selectedIndustryList != null && selectedIndustryList.size()>0){
			parentNode.put("checked", true);
		}
		mapList.add(parentNode);
		
		
		return new ObjectMapper().writeValueAsString(mapList);
	}
	/**
	 * 初始化产业园区服务菜单
	 * json 字符串
	 * @throws IOException 
	 * @throws JsonMappingException 
	 * @throws JsonGenerationException 
	 * */
	@SuppressWarnings({ "unchecked", "rawtypes" })
	public String initmenu3(String roleId,List<Map> selectedGardenList) throws JsonGenerationException, JsonMappingException, IOException {
		
		List<String> idList = new ArrayList<String>();
		for (Map selectedMap : selectedGardenList) {
			idList.add(selectedMap.get("ID").toString());
		}
		//所有产业园区map集合
		List<Map> mapList = this.industryGardenService.queryIndustryGardenList(null, Constants.GARDEN_TYPE,true);
		
		for (Map map : mapList) {
			map.put("open", true);
			if(idList.contains(map.get("ID"))){
				map.put("checked", true);
			}
		}
		Map parentNode = new HashMap();
		parentNode.put("ID", Constants.ROLE_PER_GARDEN);
		parentNode.put("TYPE", null);
		parentNode.put("NAME", "园区列表");
		parentNode.put("open", true);
		if(selectedGardenList != null && selectedGardenList.size()>0){
			parentNode.put("checked", true);
		}
		mapList.add(parentNode);
		
		return new ObjectMapper().writeValueAsString(mapList);
	}
	/**
	 *获取菜单数据
	 * */
	@RequestMapping("/sysrole/opt-query/getMenus" )
	public void getMenus(ModelMap model,HttpServletRequest request,HttpServletResponse response){
		//角色id
		String roleId=request.getParameter("roleId");
		//菜单列表
		List<Map> menuList=sysRoleService.getAllMenuList();
	
		
		//删除菜单列表中没有父节点的菜单
		getMyMenu(menuList,4);
		
		
		//向菜单列表中添加checked属性，以表示该菜单已选中,open属性为菜单默认展开
        if(!Util.isNull(roleId) && roleId!="0"){
        	List<Map> selectedList=sysRoleService.getMenuList(roleId);
        	
        	int count1=selectedList.size();
        	int count2=menuList.size();
        	if(selectedList!=null && selectedList.size()>0){
	        	for(int i=0;i<count2;i++){
	        		menuList.get(i).put("open", true);
	        		int temp=0;
	        		for(int j=0;j<count1;j++){
	        			
	            		if(menuList.get(i).get("id").equals(selectedList.get(j).get("ID"))){
	            			menuList.get(i).put("checked", true);
	            			temp++;
	            		}
	            		
	            	}
	        		
	        		if(temp==0){
	        			menuList.get(i).put("checked", false);
	        		}
	        		
	        		
	        	}
        	}else{
        		for(int j=0;j<count2;j++){
        			menuList.get(j).put("open", true);
        			menuList.get(j).put("checked", false);
            		
            	}
        		
        	}
        	
		}
        
        ObjectMapper mapper= new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		try {
			 response.getWriter().write(mapper.writeValueAsString(menuList));
		}catch (IOException e) {
			 e.printStackTrace();
		}
        
        
	}
	
	//递归删除菜单列表中没有父节点的菜单
	public void getMyMenu(List<Map> list,int i){
		if(i>1){//递归出口
			
			//递归体
			getMyMenu(list,i-1);
			
			List<Map> list01=new ArrayList();
			list01.addAll(list);
			int total=list01.size();
	        Iterator<Map> it=list.iterator();
	        Map map=null;
	        
	        //剔除菜单列表中没有父节点的菜单
	        while(it.hasNext()){
	        		map=new HashMap();
	        		map=it.next();
					int temp=0;
					for(int j=0;j<total;j++){
						String pid=map.get("pId")==null?null:map.get("pId").toString();
						String id=list01.get(j).get("id")==null?null:list01.get(j).get("id").toString();
						if(pid==null || id.equals(pid)){
							
							temp++;
							break;
						}
						
					}
					if(temp==0){
						it.remove();
					}
	        }
			
			
		}
		
		
	}
	
	
	
	
	/**
	 *将选中的菜单分配给指定角色
	 * */
	@RequestMapping("/sysrole/opt-update/allotMenu" )
	public String allotMenu(ModelMap model,HttpServletRequest request){
		//角色id
		String roleId=request.getParameter("roleId");
		//将要删除的菜单与角色关系——菜单id列表
		String idsDel=request.getParameter("idsDel");
		//将要新增的菜单与角色关系——菜单id列表
		String idsAdd=request.getParameter("idsAdd");
		//产品服务
		//将要删除
		String idsDel_1A=request.getParameter("idsDel_1A");
		String idsDel_1B=request.getParameter("idsDel_1B");
		String idsDel_1AB=request.getParameter("idsDel_1AB");
		//将要新增
		String idsAdd_1A=request.getParameter("idsAdd_1A");
		String idsAdd_1B=request.getParameter("idsAdd_1B");
		String idsAdd_1AB=request.getParameter("idsAdd_1AB");
		
		String gardenAdd = request.getParameter("gardenAdd").replace(Constants.ROLE_PER_GARDEN,"");
		
		String gardenDel = request.getParameter("gardenDel");
		
		String industryAdd = request.getParameter("industryAdd").replace(Constants.ROLE_PER_INDUSTRY, "");
		
		String industryDel = request.getParameter("industryDel");
		
        if(!Util.isNull(roleId) && roleId!="0"){
        	
    		//将所选的菜单分配给指定角色
        	sysRoleService.updateRoleMenu(idsDel, idsAdd, roleId);
        	
        	sysRolePermissionService.updatePerByRole(roleId, Constants.ROLE_PER_GARDEN, gardenAdd, gardenDel);
        	
        	sysRolePermissionService.updatePerByRole(roleId, Constants.ROLE_PER_INDUSTRY, industryAdd, industryDel);
        	//产品服务
        	sysRolePermissionService.saveRolePer(roleId, "INDUSTRY_A", idsAdd_1A);
        	sysRolePermissionService.saveRolePer(roleId, "INDUSTRY_B", idsAdd_1B);
        	sysRolePermissionService.saveRolePer(roleId, "INDUSTRY_ABMAIN", idsAdd_1AB);
        	sysRolePermissionService.delRolePer(roleId, "INDUSTRY_A", idsDel_1A);
        	sysRolePermissionService.delRolePer(roleId, "INDUSTRY_B", idsDel_1B);
        	sysRolePermissionService.delRolePer(roleId, "INDUSTRY_ABMAIN", idsDel_1AB);
		}
        
        return "redirect:/sysrole/opt-query/roleList.do?flag=1";
	}
	
}
