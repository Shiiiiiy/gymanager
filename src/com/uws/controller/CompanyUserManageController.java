package com.uws.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.uws.model.Company;
import com.uws.model.Dic;
import com.uws.model.Guser;
import com.uws.model.UserManageParam;
import com.uws.service.ICompanyManagerService;
import com.uws.service.ICompanyUserManageService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;
import com.uws.util.Util;
import com.uws.util.excel.ExcelUtil;
import com.uws.util.excel.ValidateException;
/**
 * 企业用户管理controller
 * @author hejin
 *
 */
@Controller
public class CompanyUserManageController extends BaseController{

	
	@Autowired
	private ISysDicService iSysDicService;
	
	@Autowired
	private ICompanyUserManageService companyUserManageService;
	
	@Autowired
	private ICompanyManagerService companyManagerService;
	
	
	
	/**
	 * 企业用户列表页
	 */
	@RequestMapping("/userManage/opt-query/companyList")
	public String companyList(HttpServletRequest request,UserManageParam ump,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		ump.setUsertype(Constants.USERTYPE_COMPANY);
		Page page=companyUserManageService.queryCompanyModelUserList(param, ump);
		//是否保存查询条件
		String flag=request.getParameter("flag");
	 
		if("1".equals(flag)){
			ump=(UserManageParam)session.getAttribute("ump");
		}
		
		//保存查询条件
		session.setAttribute("ump", ump);
		
		
		//启用状态列表
		List<Dic> dicList=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		//字典——启用
		model.addAttribute("dic_e", DefaultValue.DIC_ENABLE);
		//字典——禁用
		model.addAttribute("dic_d", DefaultValue.DIC_DISABLE);
		model.addAttribute("dicList", dicList);
		model.addAttribute("page", page);
	    return "userManage/companyList";  
	}
	
	
	/**
	 * 新增或编辑用户页面
	 */
	@RequestMapping("/userManage/opt-addOrEdit/addOrEditUserCompany")
	public String addOrEditUserCompany(HttpServletRequest request,Model model,HttpSession session,String pageNo,String pageSize){
	 
		//用户基础信息id
		String guserId=request.getParameter("guserId");
		
		//启用状态列表
		List<Dic> dicList=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		if(!Util.isNull(guserId)){//修改
        	Map<String,Object> userCompanyMap=companyUserManageService.queryUserCompanyModelById(guserId);
        	
        	model.addAttribute("pageNo", pageNo);
        	model.addAttribute("pageSize", pageSize);
        	model.addAttribute("userCompanyMap",userCompanyMap);
        	if(!Util.isNull((String)userCompanyMap.get("CP_PROVINCE"))){
        		String CODE=(String) companyManagerService.getDicByID((String)userCompanyMap.get("CP_PROVINCE")).get("CODE");
        		model.addAttribute("CITYlist",companyManagerService.getAreaList(CODE)  );
        	}
        	if(!Util.isNull((String)userCompanyMap.get("CP_CITY"))){
        		String CODE=(String) companyManagerService.getDicByID((String)userCompanyMap.get("CP_CITY")).get("CODE");
        		model.addAttribute("AREAlist",companyManagerService.getAreaList(CODE)  );
        	}
		}
		List<Map> provincestrlist=companyManagerService.getAreaList("PLACE");
		
		model.addAttribute("mylist",provincestrlist);
        model.addAttribute("dicList",dicList);
	    return "userManage/addOrEditUserCompany";  
	}
	
	
	/**
	 * 查看用户详情
	 */
	@RequestMapping("/userManage/opt-query/userCompanyDetail")
	public String userCompanyDetail(HttpServletRequest request,Model model,HttpSession session){
		//用户基础信息id
		String guserId=request.getParameter("guserId");
		
		//启用状态列表
		List<Dic> dicList=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
        Map<String,Object> userCompanyMap=companyUserManageService.queryUserCompanyModelById(guserId);
        	
        model.addAttribute("userCompanyMap",userCompanyMap);
		
		
        model.addAttribute("dicList",dicList);
	    return "userManage/userCompanyDetail";  
	}
	
	
	
	/**
	 *保存企业用户
	 * */
	@RequestMapping("/userManage/opt-save/saveUserCompany" )
	public String saveUserCompany(ModelMap model,UserManageParam ump, HttpServletRequest request){
		 
		 Message msg = new Message("/userManage/opt-query/companyList");
		
        if(!Util.isNull(ump.getId())){//修改
        	
        	Guser guser=companyUserManageService.getGuserById(ump.getId());
        	Company cm=companyUserManageService.getCompanyModelById(ump.getCompanyId());
        	
        	guser.setUpdateTime(new Date());
        	if(DataUtil.isNotNull(ump.getImage())){
        		guser.setImage(ump.getImage());
        	}
        	if(!"********".equals(ump.getPassword())){
        	   guser.setPassword(Util.encryptMD5(ump.getPassword()));
        	}
        	guser.setStatus(ump.getStatus());
        	guser.setUsername(ump.getUsername());
        	guser.setComments(ump.getComment());
        	if(DataUtil.isNotNull(ump.getImage())){
        		guser.setImage(ump.getImage());
        	}
        	if(cm!=null){
	        	cm.setCp_name(ump.getCp_name());
	        	cm.setCp_linkman(ump.getCpLink());
	        	cm.setCp_linkemail(ump.getCpLinkEmail());
	        	cm.setCp_linktel(ump.getCpLinkTel());
	        	cm.setCp_phone(ump.getCpPhone());
	        	cm.setCp_orgcode(ump.getCpOrgCode());
	        	cm.setCp_socialcode(ump.getCpSocialCode());
	        	cm.setCp_location(ump.getCpLocation());
	        	cm.setCp_provincestr(ump.getCpProvince());
	        	cm.setCp_citystr(ump.getCpCity());
	        	cm.setCp_areastr(ump.getCpArea());
	        	cm.setUpdate_time(new Date());
        	}
        	msg.setTips("编辑成功");
        	try {
        		companyUserManageService.saveCompanyModelUser(guser, cm);
        		msg.addParamForward("pageNo", request.getParameter("pageNo"));
        		msg.addParamForward("pageSize", request.getParameter("pageSize"));
        		msg.addParamForward("pageSearchType", request.getParameter("pageNo"));
			} catch (Exception e) {
				msg.setTips("编辑失败");
			}
		}else{//新增
			 msg.setTips("新增成功");
			try {
				companyUserManageService.addCompanyModelUser(ump);
			} catch (Exception e) {
				 msg.setTips("新增失败");
			}
		}
        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
        return "toForward";
        
	}
	
	
	/**
	 *禁用或启用企业用户
	 * */
	@RequestMapping("/userManage/opt-update/updateUserCompany" )
	public String updateUserCompany(ModelMap model,HttpServletRequest request){
		Message msg = new Message("/userManage/opt-query/companyList");
		//用户基础信息id
		String guserId=request.getParameter("guserId");
		//用户详细信息id
	    String companyId=request.getParameter("companyId");
		//标识是禁用还是启用
		String flag=request.getParameter("flag");
		
		Dic dic=new Dic();
		List<Dic>  list=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
        if(!Util.isNull(guserId) && guserId!="0" && "A".equals(flag)){//禁用
        	msg.setTips("禁用成功");
        	Guser guser=companyUserManageService.getGuserById(guserId);
        	Company cm=companyUserManageService.getCompanyModelById(companyId);
        	
        	//字典表——禁用
    		
    		if(list!=null && list.size()>0){
    			int count=list.size();
    			for(int i=0;i<count;i++){
    				if(DefaultValue.DIC_DISABLE.equals(list.get(i).getCode())){
    					dic=list.get(i);
    				}
    				
    			}
    		}
    		//cm.setStatus(dic.getId());
    		guser.setStatus(dic.getId());
        	//修改启用状态字段
    		try {
    			companyUserManageService.saveCompanyModelUser(guser, cm);
			} catch (Exception e) {
				msg.setTips("禁用失败");
			}
        	
		}else if(!Util.isNull(guserId) && guserId!="0" && "B".equals(flag)){//启用
			msg.setTips("启用成功");
			Guser guser=companyUserManageService.getGuserById(guserId);
			Company cm=companyUserManageService.getCompanyModelById(companyId);
			
			if(list!=null && list.size()>0){
				int count=list.size();
    			for(int i=0;i<count;i++){
    				if(DefaultValue.DIC_ENABLE.equals(list.get(i).getCode())){
    					dic=list.get(i);
    				}
    				
    			}
			}
			
			//cm.setStatus(dic.getId());
    		guser.setStatus(dic.getId());
    		//修改启用状态字段
    		try{
    		companyUserManageService.saveCompanyModelUser(guser, cm);
			} catch (Exception e) {
				msg.setTips("启用失败");
			}
    	 
		}
	    	msg.addParamForward("pageNo", request.getParameter("pageNo"));
			msg.addParamForward("pageSize", request.getParameter("pageSize"));
			msg.addParamForward("pageSearchType", request.getParameter("pageNo"));
			model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		 return "toForward";
	}
	
	
	/**
	 *删除用户
	 * */
	@RequestMapping("/userManage/opt-del/delUserCompany" )
	public String delUserCompany(ModelMap model,HttpServletRequest request){
		Message msg = new Message("/userManage/opt-query/companyList");
		//用户基础信息id
		String guserIdList=request.getParameter("guserIdList");
		//用户详细信息id
	    String companyIdList=request.getParameter("companyIdList");
	    if(!Util.isNull(companyIdList) && !Util.isNull(guserIdList)){//修改
				 msg.setTips("删除成功");
				 try {
		        	companyUserManageService.delCompanyModelUser(companyIdList, guserIdList);
				 } catch (Exception e) {
					 msg.setTips("删除失败");
			}
		}else{
			 msg.setTips("未获取删除的信息");
		}
        model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		return "toForward";
	}
	
	
	/**
	 *校验用户名是否已存在
	 * */
	@RequestMapping("/userManage/opt-query/checkUsername" )
	@ResponseBody
	public String checkCompanyUsername(ModelMap model,HttpServletRequest request){
		//用户名
		String username=request.getParameter("username");
		
		try {
			username = URLDecoder.decode(username, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
			
		//用户id
		String guserId=request.getParameter("guserId");
		//用户类型
		String userType = request.getParameter("userType");
		String result="{\"result\":\"ok\"}";
        if(!Util.isNull(username)){
        	if(!Util.isNull(guserId)){//修改
        		List guser=companyUserManageService.checkCompanyUsername(guserId,username,userType);
        		if(guser != null &&  guser.size() > 0 ){
            		result="{\"result\":\"no\"}";
            	}
        	}else{//新增
        		List guser=companyUserManageService.checkCompanyUsername(null,username,userType);
        		if(guser!=null && guser.size() > 0  ){
            		result="{\"result\":\"no\"}";
            	}
        	}
		}
        return result;
	}
	
	
	
	
	
	/**
	 *重置密码
	 * */
	@RequestMapping("/userManage/opt-update/resetCompanyPassword" )
	public String resetCompanyPassword(ModelMap model,HttpServletRequest request){
		Message msg = new Message("/userManage/opt-query/companyList");
		//用户id
		String guserId=request.getParameter("guserId");
		Guser guser=companyUserManageService.getGuserById(guserId);
		guser.setPassword(Util.encryptMD5(DefaultValue.DEFAULT_PASSWORD));
		guser.setUpdateTime(new Date());
		try {
			msg.setTips("已成功将密码重置为12345678");
			msg.addParamForward("pageNo", request.getParameter("pageNo"));
			msg.addParamForward("pageSize", request.getParameter("pageSize"));
			msg.addParamForward("pageSearchType", request.getParameter("pageNo"));
			companyUserManageService.saveCompanyModelUser(guser, null);
		} catch (Exception e) {
			msg.setTips("重置密码失败,可能是运气不太好");
		}
        model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		return "toForward";
	}
	
	
	//导出企业用户列表
	@RequestMapping("/userManage/opt-update/exportCompanyList" )
	public void exportCompanyList(HttpServletResponse response,UserManageParam ump){
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=companyUserList.xls");
		try {
			ExcelUtil.write(
					new String[]{
						"用户名","用户类型","状态","企业名称","企业联系人","手机号码"
						,"座机号码","联系人邮箱","注册地(省)","注册地(市)","注册地(区)","组织企业代码","社会信用代码","所在地"
					}, 
					this.companyUserManageService.queryAllUserCompanyList(ump),
					response.getOutputStream()
		    );
		} catch (ValidateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				response.getOutputStream().close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	
	
	/**
	 * 跳转  导入
	 * @param request
	 * @param sessio
	 * @param response
	 * @return
	 */
	@RequestMapping("/userManage/excelInCompanyUser")
	public String excelInCompanyUser(HttpServletRequest request, Model model, HttpSession sessio,HttpServletResponse response){
			model.addAttribute("requestPath", "/userManage/excelCompanyUserData");
		return "userManage/importCompanyUser";	
	}
	
	/**
	 * 将模板的数据显示到table
	 * @param request
	 * @param model
	 * @param sessio
	 * @param response
	 * @return
	 */
	@RequestMapping("/userManage/excelCompanyUserData")
	public String excelCompanyUserData(HttpServletRequest request, Model model, HttpSession sessio,HttpServletResponse response){
		String excelUrl = request.getParameter("excelUrl");
		model.addAttribute("requestPath", "/userManage/excelCompanyUserData");
		List<Map<Integer, Object>> listCompany = new ArrayList<Map<Integer,Object>>();
		if(!Util.isNull(excelUrl)){
			try {
				listCompany = ExcelUtil.read(excelUrl);
				//表头对应的属性
				String[] header = new String[]{"username","password","cp_name","cpLink","cpLinkEmail","cpLinkTel","cpPhone","cpOrgCode","cpSocialCode","cpLocation","comment"};
				//去掉表头
				listCompany.remove(0);
				//数据绑定
				List<UserManageParam> userManage = dataConvert(UserManageParam.class, header, listCompany);
				model.addAttribute("list", userManage);
			} catch (Exception e) {
				model.addAttribute("error_flag","Y");
				model.addAttribute("list", new ArrayList<UserManageParam>());
			}
		}
		return "userManage/importCompanyUser";
	}
	
	/**
	 * 将导入的数据进行对象绑定
	 * @param t		类
	 * @param per 	对应属性
	 * @param list 	Excel导入的集合
	 * @return
	 * @throws Exception
	 */
	public <T> List<T>  dataConvert(Class<T> t, String[] per,List<Map<Integer, Object>> list) throws  Exception {
		List<T> resources = new ArrayList<T>();
		try {
			for (Map<Integer, Object> map : list) {
				T newT = t.newInstance();
				int i=0;
					for (Integer m : map.keySet()) {
						i++;
						if(per.length >= i){
						String setMethodName = "set" + per[m].substring(0, 1).toUpperCase() + per[m].substring(1);
						Method[] methods = t.getDeclaredMethods();
						for (Method method : methods) {
							if(method.getName().equals(setMethodName)){
								method.invoke(newT, map.get(m));
							}
						}
					}
				}
				resources.add(newT);
			}
		} catch (InstantiationException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalAccessException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return resources;
	}
 
	/**
	 * 保存导入的数据
	 * @param request
	 * @param model
	 * @param sessio
	 * @param response
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/userManage/opt-save/saveCompanUser")
	@ResponseBody
	public boolean saveCompanUser(HttpServletRequest request, Model model, HttpSession sessio,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException{
		Long l = System.currentTimeMillis();
		System.out.println(l);
		String jsonStr = request.getParameter("jsonStr");
		ObjectMapper mapper = new ObjectMapper();
		List<UserManageParam> lendReco = mapper.readValue(jsonStr,new TypeReference<List<UserManageParam>>() { });
		boolean flag = true;
		try {
			companyUserManageService.addCompanyModelUsers(addSql(lendReco));
			System.out.println(System.currentTimeMillis()-l);
		} catch (Exception e) {
			 flag = false;
		}
		return flag;
	}
	
	/**
	 * 暂存需保存的sql
	 * @param userManage
	 * @return
	 */
	public List<String> addSql(List<UserManageParam> userManage){
		List<String> list= new ArrayList<String>();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Long stuteDic = this.iSysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_ENABLE).getId();
		for (UserManageParam user : userManage) {
			String userId = Util.UUId();
			
			String userStr = "INSERT INTO g_user (ID,USERNAME,PASSWORD,USERTYPE,CREATETIME,UPDATETIME,COMMENT,STATUS ) "
					+ " VALUES ('"+userId+"','"+user.getUsername()+"','"+Util.encryptMD5(user.getPassword())+"','"+Constants.USERTYPE_COMPANY+"','"+sdf.format(new Date())+"','"+sdf.format(new Date())+"','"+(user.getComment()!= null ? user.getComment() : ' ')+"','"+stuteDic+"')";
			list.add(userStr);
			 
			String companyStr = "insert into company_info (id,cp_name,cp_linkman,cp_linkEmail,cp_linkTel,cp_phone,cp_orgCode,cp_socialCode,cp_location,USERID)"
					+ " VALUES ('"+Util.UUId()+"','"+(user.getCp_name() != null ? user.getCp_name() : ' ') +"','"+(user.getCpLink() != null ? user.getCpLink() : ' ') +"','"+(user.getCpLinkEmail() != null ? user.getCpLinkEmail() : ' ') +"','"+(user.getCpLinkTel() != null ? user.getCpLinkTel() : ' ') +"','"+(user.getCpPhone() != null ? user.getCpPhone() : ' ') +"','"+(user.getCpOrgCode() != null ? user.getCpOrgCode() : ' ') +"','"+(user.getCpSocialCode() != null ? user.getCpSocialCode() : ' ')  +"','"+(user.getCpLocation()!= null ? user.getCpLocation() : ' ') +"','"+userId+"')";
			list.add(companyStr);
		}
		
		return list;
	}
	
	/**
	 * 校验导入的数据是否重复
	 * @param request
	 * @param response
	 * @return
	 * @throws JsonParseException
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/userManage/opt-query/checkUserNames")
	@ResponseBody
	public String checkUserNames(HttpServletRequest request,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException{
		String jsonStr =request.getParameter("jsonStr");
		
		String userType = request.getParameter("userType");
		
		ObjectMapper mapper = new ObjectMapper();
		List<UserManageParam> lendReco = mapper.readValue(jsonStr,new TypeReference<List<UserManageParam>>(){});
		List<String> userlist = new ArrayList<String>();
		for (UserManageParam userManageParam : lendReco) {
			userlist.add(userManageParam.getUsername());
		}
		List<Map> list = this.companyUserManageService.checkCompanyUsernames(userlist,userType);
		String str = "[";
		if(list != null && list.size() > 0){
			for (Map<String, String> map : list) {
				for (Map.Entry<String, String> m : map.entrySet()) {
					str += "{\"username\":\""+m.getValue()+"\"},";
				}
			}
			str = str.substring(0, str.length()-1);
		}
		str += "]";
		return str;
	}
 	
//	public static void main(String[] args) throws Exception {
//		List<Map<Integer, Object>> list = new ArrayList<Map<Integer,Object>>();
//		Map<Integer, Object> map = new HashMap<Integer, Object>();
//		map.put(0, "zxc");
//		map.put(1, "qwe");
//		list.add(map);
//		Map<Integer, Object> map1 = new HashMap<Integer, Object>();
//		map1.put(0, "zxc1");
//		map1.put(1, "qwe1");
//		list.add(map1);
//	 List<Dic> d =  new CompanyUserManageController().dataConvert(Dic.class,new String[]{"name","code"}, list);
//	 d.remove(0);
//	 System.out.println(d.toString());
//	}
 
}