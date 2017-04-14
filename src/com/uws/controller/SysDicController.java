package com.uws.controller;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.base.controller.BaseController;
import com.base.dao.Page;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.model.Dic;
import com.uws.service.ISysDicService;
/**
 * @ClassName: SysDicController    系统字典controller
 * @author: 石焱 
 * @date: 2017-2-15 下午3:38:46
 */
@Controller
public class SysDicController extends BaseController {

	@Resource
	private ISysDicService sysDicService;
	
	
	/**
	 *字典分类列表页
	 */
	@RequestMapping("/dicCategory/opt-query/list")
	public String dicCategoryList(HttpServletRequest request,Model model,Dic dic){
		
		Map<String, Object> param = this.initParamMap(request);
	
		Page page = this.sysDicService.search(param,dic);
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		
		
		model.addAttribute("page", page);
		model.addAttribute("dic", dic);
		
		return "sysdic/category/list";  
	}
	
	
	/**
	 *字典项列表页
	 */
	@RequestMapping("/dicItem/opt-query/list")
	public String dicItemList(HttpServletRequest request,Model model,String parentId,String status){
		
		Dic dic = new Dic();
		dic.setStatus( (long) -1);//表示为空
		
		//获取查询条件   
		if(DataUtil.isNotNull(parentId)){
			dic.setPid(Long.parseLong(parentId));
		}
		if(DataUtil.isNotNull(status)){
			dic.setStatus(Long.parseLong(status));
		}
		
		//启用状态字典
		Dic enable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
		
		//禁用状态字典
		Dic disable = this.sysDicService.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
		
		model.addAttribute("enable",enable);
		model.addAttribute("disable", disable);
		
		
		Map<String, Object> param = this.initParamMap(request);
		
		Page page = this.sysDicService.searchItem(param,dic);
		
		//获取字典分类集合
		List<Dic> dicList = this.sysDicService.getDicListByCode(null);
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		model.addAttribute("page", page);
		model.addAttribute("dic", dic);
		model.addAttribute("dicList", dicList);
		model.addAttribute("statusList", statusList);
		
		return "sysdic/item/list";  
	}
	
	
	
	/**
	 * 新增,编辑字典分类
	 */
	@RequestMapping(value={"/dicCategory/opt-add/add","/dicCategory/opt-edit/edit"})
	public String addDicCategory(HttpServletRequest request,Model model,String id,String pageNo,String pageSize){
		
		//根据是否有id 决定新增还是编辑
		if(DataUtil.isNotNull(id)){
			Dic dic = this.sysDicService.findById(id);
			model.addAttribute("dic",dic);
		}
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
		
		model.addAttribute("statusList", statusList);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNo", pageNo);
		
		return "sysdic/category/add";
	}
	
	/**
	 * 新增,编辑字典项
	 * @return
	 */
	@RequestMapping(value={"/dicItem/opt-add/add","/dicItem/opt-edit/edit"})
	public String addDicItem(HttpServletRequest request,Model model,String id,String pageNo,String pageSize){
		
		//根据是否有id 决定新增还是编辑
		if(DataUtil.isNotNull(id)){
			Dic dic = this.sysDicService.findById(id);
			model.addAttribute("dic",dic);
		}
		
		//获取字典分类集合
		List<Dic> dicList = this.sysDicService.getDicListByCode(null);
		
		//启用状态字典集合
		List<Dic> statusList = this.sysDicService.getDicListByCode(DefaultValue.ENABLE_DISABLE);
				
		model.addAttribute("statusList", statusList);
		model.addAttribute("dicList", dicList);
		model.addAttribute("pageSize", pageSize);
		model.addAttribute("pageNo", pageNo);
		
		return "sysdic/item/add";
	}
	
	
	
	/**
	 * 保存字典
	 * @return
	 */
	@RequestMapping("/dic/opt-save/save")
	public String saveDicCategory(Dic dic,String pageNo,String pageSize,String status){
		
		this.sysDicService.save(dic);
		
		if(DataUtil.isNotNull(pageNo)){
		}else{
			pageNo = "1";
		}
		
		//判断保存的是字典分类还是字典项，然后跳转到响应的方法
		if(DataUtil.isNotNull(dic.getPid())){
			return "redirect:/dicItem/opt-query/list?pageNo="+pageNo+"&pageSize="+pageSize;
		}else{
			return "redirect:/dicCategory/opt-query/list?pageNo="+pageNo+"&pageSize="+pageSize;
		}
		
	}
	
	/**
	 * 删除字典
	 * @return
	 */
	@RequestMapping("/dic/opt-delete/delete")
	public String deleteDicCategory(String id,String pageSize){
		
		//删除操作的同时，返回一个boolean，true代表删除的是字典项，false代表删的是字典分类
		boolean isItem =  this.sysDicService.deleteById(id);
		if(isItem){
			return "redirect:/dicItem/opt-query/list?pageSize="+pageSize;
		}else{
			return "redirect:/dicCategory/opt-query/list?pageSize="+pageSize;
		}
		
		
	}
	
	/**
	 * 更改字典的启用状态
	 * @param id
	 * @return
	 */
	@RequestMapping(value={"/dic/opt-update/changeStatus"})
	public String changeStatus(String id,String pageNo,String pageSize,int type){
		
		boolean isItem = this.sysDicService.changeStatus(id,type);
		if(isItem){
			return "redirect:/dicItem/opt-query/list?pageNo="+pageNo+"&pageSize="+pageSize;
		}else{
			return "redirect:/dicCategory/opt-query/list?pageNo="+pageNo+"&pageSize="+pageSize;
		}
	}
	
	/**
	 * 字典code唯一性校验
	 * @param code
	 * @return
	 */
	@ResponseBody
	@RequestMapping(value={"/dic/opt-check/checkCode"},produces={"text/plain;charset=UTF-8"})
	public String checkCode(String code,String pid,String id){
		
		boolean flag = this.sysDicService.checkCode(code, pid,id);
		if(flag){
			return "success";
		}else{
			return "failed";
		}
	}
	
	
	
	
	
}
