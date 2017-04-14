package com.uws.controller;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.base.model.Message;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.model.Company;
import com.uws.model.Dic;
import com.uws.model.FileInfo;
import com.uws.model.IndustryGarden;
import com.uws.model.Product;
import com.uws.model.SysUser;
import com.uws.service.IFileInfoService;
import com.uws.service.IIndustryGardenService;
import com.uws.util.Constants;
import com.uws.util.Util;

@Controller
public class AdvertisementController extends BaseController{
	@Resource
	private IFileInfoService fileService;
	
	@Resource
	private IIndustryGardenService industrGardenService;
	
	
	private Map<String,String> mapName ;
	
	
	/**
	 * 
	 * @param request
	 * @param model
	 * @param session
	 * @param code
	 * @return
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	@RequestMapping("/advertisement/list")
	public String getAdList(HttpServletRequest request,Model model,HttpSession session,String code,String title){
		Map<String, Object> param = this.initParamMap(request);
		List<Map> list  = industrGardenService.queryIndustryGardenList(null, null, true);
		mapName = DefaultValue.MAP_AD_NAME;
		for(Map map : list){
			mapName.put(map.get("ID").toString(), map.get("NAME").toString());
		}
		
		Page page  = fileService.getAdver(param,code,title);
		List<Map> listAd = page.getList();
		for(Map file : listAd){
			String file_type = file.get("FILE_TYPE")==null?"":file.get("FILE_TYPE").toString();
			String module_code = file.get("MODULE_CODE")==null?"":file.get("MODULE_CODE").toString();
			String parent_code = file.get("PARENT_CODE")==null?"":file.get("PARENT_CODE").toString();
			if(!Util.isNull(parent_code)){
				file.put("adLoacName",mapName.get(parent_code));
			}else if(Util.isNull(parent_code)&&!Util.isNull(module_code)){
				file.put("adLoacName",mapName.get(module_code));
			}else if(Util.isNull(parent_code)&&Util.isNull(module_code)&&!Util.isNull(file_type)){
				file.put("adLoacName",mapName.get(file_type));
			}
		}
		
		List<IndustryGarden> listGarden = new ArrayList<IndustryGarden>();
		for(String key : mapName.keySet()){
			IndustryGarden graden = new IndustryGarden();
			graden.setId(key);
			graden.setName(mapName.get(key));
			listGarden.add(graden);
		}
		model.addAttribute("codeId",code);
		model.addAttribute("title",title);
		model.addAttribute("page",page);
		model.addAttribute("listGarden",listGarden);
		return "advertisement/list";
	}
	
	@RequestMapping("/advertisement/initEditAd")
	public String initEditAd(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		String file_type = request.getParameter("file_type");
		String module_code = request.getParameter("module_code");
		String parent_code = request.getParameter("parent_code");
		String see = request.getParameter("see");
		String tishi = request.getParameter("tishi");
		String adLoacName =  "";
		if(!Util.isNull(parent_code)){
			adLoacName = mapName.get(parent_code);
		}else if(Util.isNull(parent_code)&&!Util.isNull(module_code)){
			adLoacName = mapName.get(module_code);
		}else if(Util.isNull(parent_code)&&Util.isNull(module_code)&&!Util.isNull(file_type)){
			adLoacName = mapName.get(file_type);
		}
		List<FileInfo> list = fileService.getAdverIamge(file_type, module_code, parent_code);
		model.addAttribute("list",list);
		model.addAttribute("adLoacName",adLoacName);
		model.addAttribute("saveLocation", Constants.FILE_PATH);
		model.addAttribute("file_type", file_type);
		model.addAttribute("module_code", module_code);
		model.addAttribute("parent_code", parent_code);
		model.addAttribute("tishi", tishi);
		if(!Util.isNull(see)&&see.equals("see")){
			return "/advertisement/adInfo";
		}else{
			return "/advertisement/edit";
		}
	}
	
	@RequestMapping("/advertisement/saveAd")
	public String saveAd(HttpServletRequest request,Model model,HttpSession session,RedirectAttributes attr) throws Exception{
		String file_type = request.getParameter("file_type");
		String module_code = request.getParameter("module_code");
		String parent_code = request.getParameter("parent_code");
		String fileName = request.getParameter("fileName");
		
		FileInfo file = new FileInfo();
		file.setFileTime(new Date());
		file.setFileType(file_type);
		file.setFileName(fileName);
		file.setSort(1);
		if(!Util.isNull(module_code)){
			file.setModuleCode(module_code);
		}
		if(!Util.isNull(parent_code)){
			file.setParentCode(parent_code);
		}
		file.setFileTitle("广告");
		fileService.saveFileInfo(file);
		Message msg = new Message("/advertisement/initEditAd?file_type="+file_type+"&module_code="+module_code+"&parent_code="+parent_code);
		msg.addParamForward("111", "111");
		msg.setTips("新增成功");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	
	@RequestMapping("/advertisement/updateAd")
	public String updateAd(HttpServletRequest request,Model model,HttpSession session) throws Exception{
		String fileId = request.getParameter("fileId");
		String fileTitle = request.getParameter("fileTitle");
		String url = request.getParameter("url");
		String sort = request.getParameter("sort");
		String file_type = request.getParameter("file_type");
		String module_code = request.getParameter("module_code");
		String parent_code = request.getParameter("parent_code");
		fileTitle = java.net.URLDecoder.decode(fileTitle,"UTF-8");
		fileService.updateFileName(fileId, fileTitle, url, null,sort);
		Message msg = new Message("/advertisement/initEditAd?file_type="+file_type+"&module_code="+module_code+"&parent_code="+parent_code);
		msg.addParamForward("111", "111");
		msg.setTips("修改成功");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	
	@RequestMapping("/advertisement/deleteAd")
	public String deleteAd(HttpServletRequest request,Model model,HttpSession session){
		String fileId = request.getParameter("fileId");
		String file_type = request.getParameter("file_type");
		String module_code = request.getParameter("module_code");
		String parent_code = request.getParameter("parent_code");
		fileService.deleteFileById(fileId);
		Message msg = new Message("/advertisement/initEditAd?file_type="+file_type+"&module_code="+module_code+"&parent_code="+parent_code);
		msg.addParamForward("111", "111");
		msg.setTips("删除成功");
		model.addAttribute(DefaultValue.MESSAGE_RE_NAME,msg);
		return "toForward";
	}
	/**--------------------------------*/
	/**
	 * banner 管理
	 * @param b_name 搜索条件
	 * @param model
	 * @param session
	 * @return
	 */
	@RequestMapping("/advertisement/bannerlist")
	public String bannerlist(HttpServletRequest request,Model model,HttpSession session,String fileTitle){
		Map<String, Object> param = this.initParamMap(request);
		//是否保存查询条件
		String flag=request.getParameter("flag");
		if("1".equals(flag)){
			param=(Map<String, Object>)session.getAttribute("tparam");
			fileTitle=(String)session.getAttribute("tfileTitle");
		}
		FileInfo file=new FileInfo();file.setFileTitle(fileTitle);
		//Page对象
		Page page=this.fileService.pageQueryFiles(param,file,"","","FILE_BANNER");
		model.addAttribute("page", page);
		//保存查询条件
		session.setAttribute("tparam", param);
		session.setAttribute("tfileTitle", fileTitle);
	    return "/advertisement/bannerlist";  
	}
	/**
	 * 更新banner图片
	 * @param id id
	 * @param fileName 文件名
	 * @return
	 */
	@RequestMapping("/advertisement/savebanner")
	@ResponseBody
	public String savebanner(HttpServletRequest request,String id,String fileName){
		boolean stat=true;
		if(!Util.isNull(id) && !Util.isNull(fileName)){
			this.fileService.updateFileNameById(id,fileName);
		}else{
			stat=false;
		}
	    return stat+"";  
	}
}
