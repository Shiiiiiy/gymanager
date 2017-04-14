package com.uws.controller;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.codehaus.jackson.type.TypeReference;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.base.model.Message;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.model.Company;
import com.uws.model.FileInfo;
import com.uws.model.Product;
import com.uws.model.UserManageParam;
import com.uws.service.ICompanyManagerService;
import com.uws.service.IFileInfoService;
import com.uws.util.Constants;
import com.uws.util.Util;
import com.uws.util.excel.ExcelUtil;
import com.uws.util.excel.ValidateException;

@Controller
public class CompanyManageController extends BaseController{
	
	@Autowired
	private ICompanyManagerService companyManagerService;
	
	@Resource
	private IFileInfoService fileService;
	/**
	 * 企业管理列表
	 * @param request
	 * @param model
	 * @param company
	 * @param product
	 * @param PRODUCT_TYPE1
	 * @param PRODUCT_TYPE
	 * @param session
	 * @return
	 */
	@RequestMapping("/companyManage/list")
	public String CompanyList(HttpServletRequest request,Model model,Company company,
			Product product,String PRODUCT_TYPE1,String PRODUCT_TYPE, HttpSession session) {
		String layer = request.getParameter("param");
		Map<String, Object> param = this.initParamMap(request);
		if(!Util.isNull(PRODUCT_TYPE1)){
			product.setProduct_type(PRODUCT_TYPE1);
		}
		

		model.addAttribute("company",company);
		model.addAttribute("product",product);
		model.addAttribute("PRODUCT_TYPE",PRODUCT_TYPE);
		model.addAttribute("PRODUCT_TYPE1",PRODUCT_TYPE1);
		if(!Util.isNull(layer)){//查询组件
			Page page = companyManagerService.searchComponent(param,company);
			model.addAttribute("page", page);
			return "companyManage/companyList";
		}else{//正常list
			Page page = companyManagerService.search(param,company,product);
			model.addAttribute("page", page);
			return "companyManage/list";
		}
	}
	
	
	
	/**
	 * 导出
	 * @param request
	 * @param model
	 * @param company
	 * @param product
	 * @param PRODUCT_TYPE1
	 * @param PRODUCT_TYPE
	 * @param sessio
	 * @param response
	 * @throws ValidateException
	 * @throws IOException
	 * @throws Exception
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/companyManage/excelCompany")
	public void excelCompany(HttpServletRequest request,Model model,Company company,
			Product product, HttpSession sessio,HttpServletResponse response) throws ValidateException, IOException, Exception{
		response.setContentType("text/html;charset=utf-8");
		response.setHeader("Content-Disposition", "attachment;filename=zxc.xls");
		Map<String, Object> param = new HashMap<String, Object>();
	//	int pageNo = request.getParameter("pageNo") != null?Integer.parseInt(request.getParameter("pageNo")):1;
	//	int pageSize = request.getParameter("pageSize") != null?Integer.parseInt(request.getParameter("pageSize")):10;
	
		int pageNo = 1;
		int pageSize = 9999;
		
		param.put("pageNo",pageNo);
		param.put("pageSize",pageSize);
		
		String companyName = request.getParameter("companyName") ;
		companyName = java.net.URLDecoder.decode(companyName,"UTF-8");
		String productName = request.getParameter("productName") ;
		productName = java.net.URLDecoder.decode(productName,"UTF-8");
		String PRODUCT_TYPE1 = request.getParameter("PRODUCT_TYPE1") ;
		
		if(!Util.isNull(PRODUCT_TYPE1)){
			product.setProduct_type(PRODUCT_TYPE1);
		}
		company.setCp_name(companyName);
		product.setProduct_name(productName);
		Page page = companyManagerService.search(param,company,product);
		List<Map> cp_modelList = companyManagerService.getAreaList("CP_MODEL");
		List<Map> cp_marketList = companyManagerService.getAreaList("CP_MARKET");
		List<Map> cp_belongpartList = companyManagerService.getAreaList("CP_BELONGPART");
		Map<String,String> cp_modelMap =  new HashMap<String, String>();
		Map<String,String> cp_marketMap =  new HashMap<String, String>();
		Map<String,String> cp_belongpartMap =  new HashMap<String, String>();
		for(Map map:cp_modelList){
			cp_modelMap.put(map.get("CODE").toString(), map.get("NAME").toString());
		}
		for(Map map:cp_marketList){
			cp_marketMap.put(map.get("CODE").toString(), map.get("NAME").toString());
		}	
		for(Map map:cp_belongpartList){
			cp_belongpartMap.put(map.get("CODE").toString(), map.get("NAME").toString());
		}
		List<Map> list = page.getList();
		List<Map<Integer,Object>> listresult = new ArrayList<Map<Integer,Object>>();
		for(Map map : list){
			Map<Integer,Object> mapresult = new HashMap<Integer,Object>();
			mapresult.put(0,map.get("CP_NAME"));
			mapresult.put(1,map.get("CP_MAN"));
			mapresult.put(2,map.get("CP_LOCATION"));
			mapresult.put(3,map.get("CP_ADDRESS"));
			mapresult.put(4,map.get("CP_ABSTRACT"));
			mapresult.put(5,map.get("CP_PRODUCT"));
			mapresult.put(6,map.get("CP_PHONE"));
			mapresult.put(7,map.get("CP_LINKMAN"));
			mapresult.put(8,map.get("CP_LINKTEL"));
			mapresult.put(9,map.get("CP_LINKEMAIL"));
			mapresult.put(10, cp_modelMap.get(map.get("CP_MODEL")));//营销模式
			mapresult.put(11,cp_marketMap.get(map.get("CP_MARKET")));//市场范围
			mapresult.put(12,cp_belongpartMap.get(map.get("CP_BELONGPART")));//行政区划
			mapresult.put(13,map.get("CP_SCALE"));
			mapresult.put(14,map.get("CP_TIME"));
			mapresult.put(15,map.get("CP_ORGCODE"));
			mapresult.put(16,map.get("CP_SOCIALCODE"));
			
			listresult.add(mapresult);
		}
		
		String[] headers = new String[]{"企业名称","法定代表人","所在地区描述","官网地址","公司简介","经营产品描述","公司联系方式","联系人","	联系人手机号码","联系人邮箱","	营销模式","市场范围","行政区划","业绩规模","公司创立日期","组织机构代码","	社会信用代码"};
		ExcelUtil.write(headers, listresult, response.getOutputStream());
	}
	
	/**
	 * 跳转  导入
	 * @param request
	 * @param sessio
	 * @param response
	 * @return
	 */
	@RequestMapping("/companyManage/excelInCompany")
	public String excelInCompany(HttpServletRequest request, Model model, HttpSession sessio,HttpServletResponse response){
		String param = request.getParameter("param");
		String companyId = request.getParameter("companyId");
		if(!Util.isNull(param)&&param.equals("product")){
			model.addAttribute("companyId", companyId);
			return "companyManage/improtProduct";	
		}else{
			return "companyManage/improtCompany";
		}
	}
	/**
	 * 解析excle
	 * 
	 * @param request
	 * @param model
	 * @param excelUrl
	 * @param sessio
	 * @param response
	 * @return
	 * @throws ValidateException
	 * @throws Exception
	 */
	@RequestMapping("/companyManage/excelInshow")
	public String excelInshow(HttpServletRequest request,Model model, String excelUrl,String product_comp, HttpSession sessio,HttpServletResponse response) throws ValidateException, Exception{
		String param = request.getParameter("param");
		int colnum;    //列数
		if(!Util.isNull(excelUrl)){
			List<Map<Integer,Object>> listCompany =  ExcelUtil.read(excelUrl);
			String companyArr = "";
			String dateStr = "";
			if(!Util.isNull(param)&&param.equals("product")){
				companyArr = "0,1,4";
				dateStr  ="3";
				colnum = 5;
			}else{
				companyArr = "0,1,2,3,4,5";
				dateStr  ="14";
				colnum = 17;
			}
			//保存解析结果
			List<List<Map<String,String>>> listre = new ArrayList<List<Map<String,String>>>();
			
			
			
			for(Map<Integer,Object> lineMap : listCompany){
				List<Map<String,String>> tdList = new ArrayList<Map<String,String>>();
				for(int index=0;index<colnum;index++){
					
					Map<String,String> map = new HashMap<String, String>();
					map.put("one", lineMap.get(index)==null?"":lineMap.get(index).toString());

					if(companyArr.contains(String.valueOf(index))){
						if(lineMap.get(index)==null||lineMap.get(index).toString().equals("")){
							map.put("tow", "red");
						}else{
							map.put("tow", "");
						}
					}else if(dateStr.contains(String.valueOf(index))){
						if(lineMap.get(index)!=null&&!Util.isValidDate(lineMap.get(index).toString())&&!lineMap.get(index).toString().equals("")){
							map.put("tow", "red");
						}else{
							map.put("tow", "");
						}
					}else{
						map.put("tow", "");
					}
					tdList.add(map);
					
				}

				listre.add(tdList);
			}
			model.addAttribute("list", listre);
		}
		if(!Util.isNull(param)&&param.equals("product")){
			model.addAttribute("companyId", product_comp);
			return "companyManage/improtProduct";	
		}else{
			return "companyManage/improtCompany";
		}
	}

	
	/**
	 * 导入   企业保存
	 * @param request
	 * @param model
	 * @param sessio
	 * @param response
	 * @throws Exception
	 * @throws JsonMappingException
	 * @throws IOException
	 */
	@RequestMapping("/companyManage/excelSaveCompany")
	public void excelSaveCompany(HttpServletRequest request,Model model,HttpSession sessio,HttpServletResponse response) throws Exception{
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String companyArrStr = request.getParameter("param");
		ObjectMapper mapper= new ObjectMapper();
		List<Company> companyList = mapper.readValue(companyArrStr,new TypeReference<List<Company>>(){ });
		for(Company com : companyList){
			if(!Util.isNull(com.getCp_timestr())){
				com.setCp_time(sm.parse(com.getCp_timestr()));
			}
			com.setCreate_time(new Date());
			com.setStatus("2");
			String CP_MODEL = com.getCp_modelstr() ;
			String CP_MARKET = com.getCp_marketstr() ;
			String CP_BELONGPART = com.getCp_belongpartstr() ;
			com.setCp_modelstr(companyManagerService.getDic("CP_MODEL", CP_MODEL));
			com.setCp_marketstr(companyManagerService.getDic("CP_MARKET", CP_MARKET));
			com.setCp_belongpartstr(companyManagerService.getDic("CP_BELONGPART", CP_BELONGPART));
		    companyManagerService.saveCompany(com);
		    
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	/**
	 * 导入  保存产品信息
	 * @param request
	 * @param model
	 * @param sessio
	 * @param response
	 * @throws Exception
	 */
	@RequestMapping("/companyManage/excelSaveProduct")
	public void excelSaveProduct(HttpServletRequest request,Model model,HttpSession sessio,HttpServletResponse response) throws Exception{
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String companyArrStr = request.getParameter("param");
		ObjectMapper mapper= new ObjectMapper();
		List<Product> productList = mapper.readValue(companyArrStr,new TypeReference<List<Product>>(){ });
		for(Product pro : productList){
			if(!Util.isNull(pro.getProduct_timestr())){
				pro.setProduct_time(sm.parse(pro.getProduct_timestr()));
			}
			pro.setCreate_time(new Date());
			String product_type = "";
			if(!Util.isNull(pro.getProduct_typeName())){
				String[] product_typeNameArr = pro.getProduct_typeName().split(",");
				for(int i = 0 ; i < product_typeNameArr.length ; i++){
					String code = companyManagerService.getDic("MENUTREE", product_typeNameArr[i]);
					if(i == product_typeNameArr.length-1){
						product_type += code;
					}else{
						product_type += code+",";
					}
				}
			}
			pro.setProduct_type(product_type);
			companyManagerService.saveProduct(pro);
		}
		response.setCharacterEncoding("UTF-8");
		try {
			response.getWriter().write("success");
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
	/**
	 * 初始新增或修改企业基本信息页面
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/companyManage/addCompany")
	public String addCompany(HttpServletRequest request,Model model,HttpSession session){
		String companyId =  request.getParameter("companyId");
		String see =  request.getParameter("flag");
		Company company = null; 
		if(!Util.isNull(companyId)){
			company = companyManagerService.getCompanyModel(companyId);
		}
		List<Map> cp_provincestrlist=companyManagerService.getAreaList("PLACE");
		List<Map> cp_modelList = companyManagerService.getAreaList("CP_MODEL");
		List<Map> cp_customertypeList = companyManagerService.getAreaList("CP_CUSTOMERTYPE");
		List<Map> cp_marketList = companyManagerService.getAreaList("CP_MARKET");
		List<Map> cp_belongpartList = companyManagerService.getAreaList("CP_BELONGPART");
		model.addAttribute("cp_provincestrlist", cp_provincestrlist);
		model.addAttribute("cp_modelList", cp_modelList);
		model.addAttribute("cp_customertypeList", cp_customertypeList);
		model.addAttribute("cp_marketList", cp_marketList);
		model.addAttribute("cp_belongpartList", cp_belongpartList);
		if(company!=null){
			if(!Util.isNull(company.getCp_provincestr())){
				List<Map> cp_citystrList=companyManagerService.getAreaList(company.getCp_provincestr());
				model.addAttribute("cp_citystrList", cp_citystrList);
			}
			if(!Util.isNull(company.getCp_citystr())){
				List<Map> cp_areastrList=companyManagerService.getAreaList(company.getCp_citystr());
				model.addAttribute("cp_areastrList", cp_areastrList);
			}
			model.addAttribute("company", company);
			model.addAttribute("flag","edit");
		}
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		if("see".equals(see)){
			return "companyManage/companyInfo";
		}else if("improt".equals(see)){
			model.addAttribute("improt", "improt");
			return "companyManage/addCompany";
		}else{
			return "companyManage/addCompany";
		}
	}
	
	/**
	 * 企业  启用  禁用
	 * @param request
	 * @param model
	 * @param session
	 * @throws UnsupportedEncodingException 
	 */
	@RequestMapping("/companyManage/statusComapny")
	public String statusComapny(HttpServletRequest request,Model model,HttpSession session,HttpServletResponse response) throws UnsupportedEncodingException{
		String companyId = request.getParameter("companyId");
		String status = request.getParameter("status");
		String tip = request.getParameter("tip");
		String companyName = request.getParameter("companyName") ;
		companyName = java.net.URLDecoder.decode(companyName,"UTF-8");
		String productName = request.getParameter("productName") ;
		productName = java.net.URLDecoder.decode(productName,"UTF-8");
		String PRODUCT_TYPE1 = request.getParameter("PRODUCT_TYPE1") ;
		String pageNo = request.getParameter("pageNo");
		String pageSize = request.getParameter("pageSize");
		String pageSearchType = request.getParameter("pageSearchType");
		Company company = companyManagerService.getCompanyModel(companyId);
		if(!Util.isNull(status)){
			company.setStatus(status);
			companyManagerService.updateCompany(company);
		}
		Message msg = new Message("/companyManage/list?pageSearchType="+pageSearchType+"&pageNo="+pageNo+"&pageSize="+pageSize);
		msg.addParamForward("cp_name", companyName);
		msg.addParamForward("product_name", productName);
		msg.addParamForward("PRODUCT_TYPE1", PRODUCT_TYPE1);
		if("jinyong".equals(tip)){
			msg.setTips("禁用成功");
		}else{
			msg.setTips("启用成功");
		}
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	
	/**
	 * 增加或和修改企业基本信息
	 * 
	 * @param request
	 * @param company
	 * @param cp_timestr
	 * @param model
	 * @param session
	 * @return
	 * @throws ParseException 
	 */
	@RequestMapping("/companyManage/saveCompany")
	public String saveCompany(HttpServletRequest request,Company company,String cp_timestr,String tcreate_time,Model model, HttpSession session) throws ParseException{
		String companyId = company.getId();
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat sm2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Message msg = new Message("");
		msg.addParamForward("111", "111");
		
		if(!Util.isNull(cp_timestr)){
			company.setCp_time(sm.parse(cp_timestr));
		}

		if(!Util.isNull(companyId)){
			company.setUpdate_time(new Date());
			if(!Util.isNull(tcreate_time)){
				company.setCreate_time(sm2.parse(tcreate_time));
			}else{company.setCreate_time(new Date());}
			companyManagerService.updateCompany(company);
			msg.setTips("修改成功");
			msg.setUrl("/companyManage/addCompany?companyId="+companyId);
		}else{
			company.setCreate_time(new Date());
			String id = companyManagerService.saveCompany(company);
			msg.setTips("新增成功");
			company.setId(id);
			msg.setUrl("/companyManage/addCompany?companyId="+id);
		}
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	
	
	/**
	 * 删除企业基本信息
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/companyManage/deleteCompany")
	public String deleteCompany(HttpServletRequest request,Model model, HttpSession session){
		Company company = new Company();
		String companyId = request.getParameter("companyId");
		String[] companyIdList = companyId.split(",");
		for(String companyIdo :companyIdList){
			if(!Util.isNull(companyIdo)){
				company.setId(companyIdo);
			}
			companyManagerService.deleteCompany(company);
		}
		Message msg = new Message("/companyManage/list.do");
		msg.setTips("删除成功");
		msg.addParamForward("111", "111");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
		
	}
	
	/**
	 * 初始化产品图片列表
	 * @param request
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/companyManage/companyImageInit")
	public String companyImageInit(HttpServletRequest request,Model model, HttpSession session){
		String companyId = request.getParameter("companyId");
		List<FileInfo> fileList = fileService.queryFileInfoListForCompany(Constants.FILE_PRO, companyId);
		model.addAttribute("fileList", fileList);
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		model.addAttribute("companyId", companyId);
		return "companyManage/companyImage"; 
	}
	/**
	 * 保存基本信息图片
	 * @param request
	 * @param companyId
	 * @param fileName
	 * @param fileType
	 * @param model
	 * @return
	 */
	@RequestMapping("/companyManage/saveImageCompany")
	public String saveImageCompany(HttpServletRequest request,String companyId,String fileName,Model model){
		FileInfo file = new FileInfo();
		file.setFileName(fileName);
		file.setFileTime(new Date());
		file.setFileType(Constants.FILE_PRO);
		file.setParentCode(companyId);
		file.setSort(1);
		fileService.saveFileInfo(file);
		Message msg = new Message("/companyManage/companyImageInit?companyId="+companyId);
		msg.addParamForward("111", "111");
		msg.setTips("新增成功");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	
	/**
	 * 修改图片的名字
	 * @param request
	 * @param companyId
	 * @param fileName
	 * @param model
	 * @return
	 * @throws Exception 
	 */
	@RequestMapping("/companyManage/updateImageCompany")
	public String updateImageCompany(HttpServletRequest request,String companyId ,String fileTitle,String fileId,String url,String urlType,Model model) throws Exception{
		fileTitle = java.net.URLDecoder.decode(fileTitle,"UTF-8");
		url = java.net.URLDecoder.decode(url,"UTF-8");
		fileService.updateFileName(fileId, fileTitle, url, urlType,"0");
		Message msg = new Message("/companyManage/companyImageInit?companyId="+companyId);
		msg.addParamForward("111", "111");
		msg.setTips("修改成功");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	} 
	
	/**
	 * 删除选中图片
	 */
	@RequestMapping("/companyManage/deleteFile")
	public String deleteFile(HttpServletRequest request,String fileId,String companyId,Model model){
		fileService.deleteFileById(fileId);
		Message msg = new Message("/companyManage/companyImageInit?companyId="+companyId);
		msg.addParamForward("111", "111");
		msg.setTips("删除成功");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	
	/**
	 * 产品列表
	 * @param request
	 * @param model
	 * @param product
	 * @param session
	 * @param response
	 */
	@RequestMapping("/companyManage/productList")
	public void productList(HttpServletRequest request,Model model,Product product, HttpSession session,HttpServletResponse response){
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String product_comp = request.getParameter("product_comp");
		String product_name = request.getParameter("product_name");
		String product_capacity = request.getParameter("product_capacity");
		Product pro = new Product();
		pro.setProduct_capacity(product_capacity);
		pro.setProduct_comp(product_comp);
		pro.setProduct_name(product_name);
		List<Product> list = companyManagerService.getProductList(product);
		for(Product productModel : list){
			if(productModel.getProduct_time()!=null){
				productModel.setProduct_timestr(sm.format(productModel.getProduct_time()));
			}
		}
		ObjectMapper mapper= new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		try {
			 response.getWriter().write(mapper.writeValueAsString(list));
		}catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * 删除产品
	 */
	@RequestMapping("/companyManage/deleteProduct")
	public void deleteProduct(HttpServletRequest request,Model model, HttpSession session,HttpServletResponse response){
		String id = request.getParameter("productId");
		Product pro = new Product();
		pro.setId(id);
		companyManagerService.deleteProductList(pro);
	}
	/**
	 * 新增产品
	 * @param request
	 * @param model
	 * @param session
	 * @param response
	 * @return
	 */
	@RequestMapping("/companyManage/addProduct")
	public String addProduct(HttpServletRequest request,Model model, HttpSession session,HttpServletResponse response){
		String productId = request.getParameter("productId");
		if(!Util.isNull(productId)){
			Product pro = companyManagerService.getProductModel(productId);
			model.addAttribute("product", pro);
		}
		
		return "companyManage/addProduct";
	}
	
	/**
	 * 保存产品
	 * @param request
	 * @param model
	 * @param session
	 * @param response
	 */
	@RequestMapping("/companyManage/saveProduct")
	public void saveProduct(HttpServletRequest request,Model model, HttpSession session,HttpServletResponse response){
		SimpleDateFormat sm = new SimpleDateFormat("yyyy-MM-dd");
		String product_name =  request.getParameter("product_name");
		String product_typeName =  request.getParameter("product_typeName");
		String product_type =  request.getParameter("product_type");
		String product_num =  request.getParameter("product_num");
		String product_time =  request.getParameter("product_time");
		String product_capacity =  request.getParameter("product_capacity");
		String comments =  request.getParameter("comments");
		String product_comp = request.getParameter("product_comp");
		String productId = request.getParameter("id");
		Product pro = new Product();
		pro.setComments(comments);
		pro.setProduct_capacity(product_capacity);
		pro.setProduct_comp(product_comp);
		pro.setProduct_num(product_num);
		pro.setProduct_name(product_name);
		pro.setProduct_typeName(product_typeName);
		pro.setProduct_type(product_type);
		pro.setStatusstr("1");
		ObjectMapper mapper= new ObjectMapper();
		response.setCharacterEncoding("UTF-8");
		try {
			if(!Util.isNull(product_time)){
				pro.setProduct_time(sm.parse(product_time));
			}
			if(!Util.isNull(productId)){
				pro.setId(productId);
				companyManagerService.updateProduct(pro);
			}else{
				companyManagerService.saveProduct(pro);
			}
			response.getWriter().write(mapper.writeValueAsString("success"));
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 获取市/区列表
	 * @param model
	 * @param request
	 * @return
	 */
	@SuppressWarnings("rawtypes")
	@RequestMapping("/companyManage/getCityOrArea")
	public void getCityOrArea(HttpServletRequest request,HttpServletResponse resp){
		String parentCode=request.getParameter("parentCode");
		List<Map> mylist=companyManagerService.getAreaList(parentCode);
		ObjectMapper mapper= new ObjectMapper();
		resp.setCharacterEncoding("UTF-8");
		boolean stat=true;
		if(mylist==null||mylist.size()<=0){
			stat=false;
		}
		try {
			if(stat){
				resp.getWriter().write(mapper.writeValueAsString(mylist));
			}else{
				resp.getWriter().write(mapper.writeValueAsString("nomore"));
			}
		}catch (IOException e) {
			e.printStackTrace();
		}
		
		
	}
	
	/**
	 * 查询菜单节点列表
	 * @param request
	 * @param model
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/companyManage/getProductTypeTree")
	public void getProductTypeTree(HttpServletRequest request,HttpServletResponse response){
		String parentCode=request.getParameter("id");
		List<Map> list=null;
		//菜单节点列表
		List<Map> menuList=companyManagerService.getDicListByParentCode(parentCode==null?"MENUTREE":parentCode);
		if(menuList!=null && menuList.size()>0){
			for(int i=0;i<menuList.size();i++){
				list=companyManagerService.getDicListByParentCode((String)menuList.get(i).get("id"));
				if(list!=null && list.size()>0){
					menuList.get(i).put("isParent", true);
				}else{
					menuList.get(i).put("isParent", false);
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
	/**
	 * 查询所属行业菜单节点列表
	 * @param request
	 * @param model
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/companyManage/getCPBelongTree")
	public void getCPBelongTree(HttpServletRequest request,HttpServletResponse response){
		String parentCode=request.getParameter("id");
		List<Map> list=null;
		//菜单节点列表
		List<Map> menuList=companyManagerService.getDicListByParentCode(parentCode==null?"BELONGTREE":parentCode);
		if(menuList!=null && menuList.size()>0){
			for(int i=0;i<menuList.size();i++){
				list=companyManagerService.getDicListByParentCode((String)menuList.get(i).get("id"));
				if(list!=null && list.size()>0){
					menuList.get(i).put("isParent", true);
				}else{
					menuList.get(i).put("isParent", false);
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
	/**
	 * 导入成功  跳转
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/companyManage/excelCompanyTip")
	public String excelCompanyTip(HttpServletRequest request,HttpServletResponse response,Model model){
		Message msg = new Message("/companyManage/list");
		msg.addParamForward("111", "111");
		msg.setTips("导入成功");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	
	/**
	 * 
	 * @param request
	 * @param response
	 * @param model
	 * @return
	 */
	@RequestMapping("/companyManage/tipProduct")
	public String tipProduct(HttpServletRequest request,HttpServletResponse response,Model model){
		String companyId = request.getParameter("companyId");
		String tishi = request.getParameter("tishi");
		Message msg = new Message("/companyManage/addCompany?flag=improt&companyId="+companyId);
		msg.addParamForward("111", "111");
		if("xinzeng".equals(tishi)){
			msg.setTips("新增成功");
		}else if("xiugai".equals(tishi)){
			msg.setTips("修改成功");
		}else if("shanchu".equals(tishi)){
			msg.setTips("删除成功");
		}else{
			msg.setTips("导入成功");
		}
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	
}
