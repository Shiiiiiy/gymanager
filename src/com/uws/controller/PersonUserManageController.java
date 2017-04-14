package com.uws.controller;

import java.io.IOException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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
import com.uws.model.Dic;
import com.uws.model.Guser;
import com.uws.model.Person;
import com.uws.model.UserManageParam;
import com.uws.service.ICompanyManagerService;
import com.uws.service.IPersonUserManageService;
import com.uws.service.ISysDicService;
import com.uws.util.Constants;
import com.uws.util.Util;
import com.uws.util.excel.ExcelUtil;
import com.uws.util.excel.ValidateException;
/**
 * 个人用户管理controller
 * @author hejin
 *
 */
@Controller
public class PersonUserManageController extends BaseController{

	
	@Autowired
	private ISysDicService iSysDicService;
	
	
	@Autowired
	private IPersonUserManageService personUserManageService;
	
	
	@Autowired
	private ICompanyManagerService companyManagerService;
	
	/**
	 * 个人用户列表页
	 */
	@RequestMapping("/userManage/opt-query/personList")
	public String personList(HttpServletRequest request,UserManageParam ump,Model model,HttpSession session){
		Map<String, Object> param = this.initParamMap(request);
		ump.setUsertype(Constants.USERTYPE_PERSON);
		Page page=personUserManageService.queryPersonUserList(param, ump);
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
	    return "userManage/personList";  
	}
	
	
	/**
	 * 新增或编辑用户页面
	 */
	@RequestMapping("/userManage/opt-addOrEdit/addOrEditUserPerson")
	public String addOrEditUserPerson(HttpServletRequest request,Model model,HttpSession session,String pageNo,String pageSize){
		//用户基础信息id
		String guserId=request.getParameter("guserId");
		
		//启用状态列表
		List<Dic> dicList=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		//性别
		List<Dic> sexList=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		if(!Util.isNull(guserId)){//修改
        	Map<String,Object> userPersonMap=personUserManageService.queryUserPserById(guserId);
        	
        	model.addAttribute("userPersonMap",userPersonMap);
        	if(!Util.isNull((String)userPersonMap.get("ADD_PROVINCE"))){
        		String CODE=(String) companyManagerService.getDicByID((String)userPersonMap.get("ADD_PROVINCE")).get("CODE");
        		model.addAttribute("CITYlist",companyManagerService.getAreaList(CODE)  );
        	}
        	if(!Util.isNull((String)userPersonMap.get("ADD_CITY"))){
        		String CODE=(String) companyManagerService.getDicByID((String)userPersonMap.get("ADD_CITY")).get("CODE");
        		model.addAttribute("AREAlist",companyManagerService.getAreaList(CODE)  );
        	}
		}
        List<Map> provincestrlist=companyManagerService.getAreaList("PLACE");
		
		model.addAttribute("mylist",provincestrlist);
        model.addAttribute("dicList",dicList);
        model.addAttribute("sexList",sexList);
        model.addAttribute("pageNo", pageNo);
  		model.addAttribute("pageSize", pageSize);
	    return "userManage/addOrEditUserPerson";  
	}
	
	
	/**
	 * 查看用户详情
	 */
	@RequestMapping("/userManage/opt-query/userPersonDetail")
	public String userPersonDetail(HttpServletRequest request,Model model,HttpSession session){
		//用户基础信息id
		String guserId=request.getParameter("guserId");
		
		//启用状态列表
		List<Dic> dicList=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
        Map<String,Object> userPersonMap=personUserManageService.queryUserPserById(guserId);
        	
        model.addAttribute("userPersonMap",userPersonMap);
		
		
        model.addAttribute("dicList",dicList);
	    return "userManage/userPersonDetail";  
	}
	
	
	
	/**
	 *保存个人用户
	 * */
	@RequestMapping("/userManage/opt-save/saveUserPerson" )
	public String saveUserPerson(ModelMap model,UserManageParam ump, HttpServletRequest request){
		Message msg = new Message("/userManage/opt-query/personList");
		
        if(!Util.isNull(ump.getId())){//修改
        	
        	Guser guser=personUserManageService.getGuserById(ump.getId());
        	Person person=personUserManageService.getPersonById(ump.getPersonId());
        	
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
        	
        	if(person!=null){
	        	person.setName(ump.getName());
	        	person.setGender(ump.getGender());
	        	person.setPhone(ump.getPhone());
	        	person.setTel(ump.getTel());
	        	person.setCardtype(ump.getCardtype());
	        	person.setCardnum(ump.getCardnum());
	        	person.setProvince(ump.getAddProvince());
	        	person.setCity(ump.getAddCity());
	        	person.setArea(ump.getAddArea());
	        	person.setLinkaddress(ump.getLinkAddress());
	        	person.setStatus(ump.getStatus());
	        	person.setEmail(ump.getEmail());
        	}
        	try {
        		msg.setTips("编辑成功");
        		msg.addParamForward("pageNo", request.getParameter("pageNo"));
	    		msg.addParamForward("pageSize", request.getParameter("pageSize"));
	    		msg.addParamForward("pageSearchType", request.getParameter("pageNo"));
        		personUserManageService.savePersonUser(guser, person);
			} catch (Exception e) {
				msg.setTips("编辑失败");
			}
		}else{//新增
			  msg.setTips("新增成功");
	        	try {
	        		personUserManageService.addPersonUser(ump);
				} catch (Exception e) {
					msg.setTips("新增失败");
				}
			}
	        	model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
		  return "toForward";
	}
	
	
	/**
	 *禁用或启用个人用户
	 * */
	@RequestMapping("/userManage/opt-update/updateUserPerson" )
	public String updateUserPerson(ModelMap model,HttpServletRequest request){
		Message msg = new Message("/userManage/opt-query/personList");
		//用户基础信息id
		String guserId=request.getParameter("guserId");
		//用户详细信息id
	    String personId=request.getParameter("personId");
		//标识是禁用还是启用
		String flag=request.getParameter("flag");
		
		Dic dic=new Dic();
		List<Dic>  list=iSysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
        if(!Util.isNull(guserId) && guserId!="0" && "A".equals(flag)){//禁用
        	Guser guser=personUserManageService.getGuserById(guserId);
        	Person person=personUserManageService.getPersonById(personId);
        	
        	//字典表——禁用
    		
    		if(list!=null && list.size()>0){
    			int count=list.size();
    			for(int i=0;i<count;i++){
    				if(DefaultValue.DIC_DISABLE.equals(list.get(i).getCode())){
    					dic=list.get(i);
    				}
    				
    			}
    		}
    		person.setStatus(dic.getId());
    		guser.setStatus(dic.getId());
        	//修改启用状态字段
    		  msg.setTips("禁用成功");
          	try {
          		personUserManageService.savePersonUser(guser, person);
  			} catch (Exception e) {
  				msg.setTips("禁用失败");
  			}
		}else if(!Util.isNull(guserId) && guserId!="0" && "B".equals(flag)){//启用
			Guser guser=personUserManageService.getGuserById(guserId);
        	Person person=personUserManageService.getPersonById(personId);
			
			if(list!=null && list.size()>0){
				int count=list.size();
    			for(int i=0;i<count;i++){
    				if(DefaultValue.DIC_ENABLE.equals(list.get(i).getCode())){
    					dic=list.get(i);
    				}
    				
    			}
			}
			person.setStatus(dic.getId());
    		guser.setStatus(dic.getId());
    		//修改启用状态字段
    		personUserManageService.savePersonUser(guser, person);
    		msg.setTips("启用成功");
        	try {
        		personUserManageService.savePersonUser(guser, person);
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
	 *批量删除用户
	 * */
	@RequestMapping("/userManage/opt-del/delUserPerson" )
	public String delUserPerson(ModelMap model,HttpServletRequest request){
		Message msg = new Message("/userManage/opt-query/personList");
		//用户基础信息id
		String guserIdList=request.getParameter("guserIdList");
		//用户详细信息id
	    String personIdList=request.getParameter("personIdList");
		
        if(!Util.isNull(personIdList) && !Util.isNull(guserIdList)){//修改
        	msg.setTips("删除成功");
		 try {
			 personUserManageService.delPersonUser(personIdList, guserIdList);
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
	 *重置密码
	 * */
	@RequestMapping("/userManage/opt-update/resetPassword" )
	public String resetPassword(ModelMap model,HttpServletRequest request){
		Message msg = new Message("/userManage/opt-query/personList");
		//角色id
		String guserId=request.getParameter("guserId");
		Guser guser=personUserManageService.getGuserById(guserId);
		guser.setPassword(Util.encryptMD5(DefaultValue.DEFAULT_PASSWORD));
		guser.setUpdateTime(new Date());
		msg.setTips("已成功将密码重置为12345678");
    	try {
    		personUserManageService.savePersonUser(guser, null);
		} catch (Exception e) {
			msg.setTips("密码重置失败");
		}
    		msg.addParamForward("pageNo", request.getParameter("pageNo"));
    		msg.addParamForward("pageSize", request.getParameter("pageSize"));
    		msg.addParamForward("pageSearchType", request.getParameter("pageNo"));
    		model.addAttribute(DefaultValue.MESSAGE_RE_NAME, msg);
    	return "toForward";
	}
	
	//导出个人用户列表
	@RequestMapping("/userManage/opt-update/exportPersonList" )
	public void exportPersonList(HttpServletResponse response,UserManageParam ump){
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=personUserList.xls");
		try {
			ExcelUtil.write(
					new String[]{
						"用户名","用户类型","状态","真实姓名","性别","手机号码"
						,"座机号码","邮箱","证件类型","证件号码","所在省","所在市","所在区","联系地址"
					}, 
					this.personUserManageService.queryAllUserPersonList(ump),
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
		}
	}
	
	/**
	 * 跳转  导入
	 * @param request
	 * @param sessio
	 * @param response
	 * @return
	 */
	@RequestMapping("/userManage/excelPersonUser")
	public String excelPersonUser(HttpServletRequest request, Model model, HttpSession sessio,HttpServletResponse response){
			model.addAttribute("requestPath", "/userManage/excelPersonUserData");
		return "userManage/importPersonUser";	
	}
	
	/**
	 * 将模板的数据显示到table
	 * @param request
	 * @param model
	 * @param sessio
	 * @param response
	 * @return
	 */
	@RequestMapping("/userManage/excelPersonUserData")
	public String excelPersonUserData(HttpServletRequest request, Model model, HttpSession sessio,HttpServletResponse response){
		String excelUrl = request.getParameter("excelUrl");
		model.addAttribute("requestPath", "/userManage/excelPersonUserData");
		List<Map<Integer, Object>> listCompany = new ArrayList<Map<Integer,Object>>();
		if(!Util.isNull(excelUrl)){
			try {
				listCompany = ExcelUtil.read(excelUrl);
				//表头对应的属性
				String[] header = new String[]{"username","password","name","gender","email","tel","phone","cardtype","cardnum","linkAddress","comment"};
				//去掉表头
				listCompany.remove(0);
				//数据绑定
				List<UserManageParam> userManage = dataConvert(UserManageParam.class, header, listCompany);
				model.addAttribute("list", userManage);
			} catch (Exception e) {
			//	e.printStackTrace();
				model.addAttribute("list", new ArrayList<UserManageParam>());
				model.addAttribute("error_flag","Y");
			}
		}
		return "userManage/importPersonUser";
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
				int i = 0;
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
	@RequestMapping("/userManage/opt-save/savePersonUser")
	@ResponseBody
	public boolean savePersonUser(HttpServletRequest request, Model model, HttpSession sessio,HttpServletResponse response) throws JsonParseException, JsonMappingException, IOException{
		String jsonStr = request.getParameter("jsonStr");
		ObjectMapper mapper = new ObjectMapper();
		List<UserManageParam> lendReco = mapper.readValue(jsonStr,new TypeReference<List<UserManageParam>>() { });
		boolean flag = true;
		try {
			personUserManageService.addPersonUsers(addSql(lendReco));
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
					+ " VALUES ('"+userId+"','"+user.getUsername()+"','"+Util.encryptMD5(user.getPassword())+"','"+Constants.USERTYPE_PERSON+"','"+sdf.format(new Date())+"','"+sdf.format(new Date())+"','"+(user.getComment()!= null ? user.getComment() : ' ')+"','"+stuteDic+"')";
			list.add(userStr);
			 
			String cardType = "";
			if("身份证".equals(user.getCardtype().trim())){
				cardType = "CARD_A";
			}else if("驾驶证".equals(user.getCardtype().trim())){
				cardType = "CARD_B";
			}
			String companyStr = "insert into person (ID,NAME,GENDER,TEL,PHONE,EMAIL,CARDTYPE,CARDNUM,LINK_ADDRESS,USERID)"
					+ " VALUES ('"+Util.UUId()+"','"+(user.getName() != null ? user.getName() : ' ') +"','"+((user.getGender() != null && !user.getGender().equals("女")) ? 1 : 0) +"','"+(user.getTel() != null  ? user.getTel() : ' ') +"','"+(user.getPhone() != null ? user.getPhone() : ' ') +"','"+(user.getEmail() != null ? user.getEmail() : ' ') +"','"+cardType+"','"+(user.getCardnum() != null ? user.getCardnum() : ' ')  +"','"+(user.getLinkAddress()!= null ? user.getLinkAddress() : ' ') +"','"+userId+"')";
			list.add(companyStr);
		}
		
		return list;
	}
}
