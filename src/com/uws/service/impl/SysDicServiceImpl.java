package com.uws.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.base.util.DataUtil;
import com.base.util.DefaultValue;
import com.uws.dao.ISysDicDao;
import com.uws.model.Dic;
import com.uws.service.ISysDicService;
/**
 * @ClassName: SysDicServiceImpl 
 * @author: 石焱 
 * @date: 2017-2-15 下午3:40:44
 */
@Service("sysDicService")
public class SysDicServiceImpl implements ISysDicService{
	
	@Resource
	private  ISysDicDao dicDao;

	//更新 或 新增 一个dic
	@Override
	public void save(Dic dic) {
		//id 为long型，初始值为 0
		if(DataUtil.isNotNull(dic.getId()) && dic.getId()>0){
			Dic d = this.findById(dic.getId().toString());
			//更新dic
			d.setCode(dic.getCode());
			d.setName(dic.getName());
			d.setStatus(dic.getStatus());
			d.setPid(dic.getPid());
			d.setRemark(dic.getRemark());
			d.setValue(dic.getValue());
			dicDao.update(d);
		}else{
			//新增
			dicDao.save(dic);
		}
		
	}

	/**
	 * 查询字典分类详细信息
	 */
	@Override
	public Page search(Map<String, Object> param,Dic dic) {
		return this.dicDao.search(param,dic);
	}

	/**
	 * 根据id获取字典对象
	 * @param id
	 * @return
	 */
	@Override
	public Dic findById(String id) {
		return this.dicDao.findById(id);
	}
	
	/**
	 * 根据id删除字典对象
	 */
	@Override
	public boolean deleteById(String id) {
		//flag 记录删除的是字典分类，还是字典项 ,true 代表删的是字典项，false 删的是字典分类 
		boolean isItem = true;
		Dic dic = dicDao.findById(id);
		
		//如果该字典没有上级，说明该字典是字典分类，删除之前先删掉分类下的所有字典项
		if(!DataUtil.isNotNull(dic.getPid())){
			List<Dic> itemList = this.getDicListByCode(dic.getCode());
			for (Dic dic2 : itemList) {
				this.dicDao.delete(dic2);
			}
			isItem = false;
		}
		this.dicDao.delete(dic);
		return isItem;
	}

	/**
	 * 根据分类code获 下级取字典项，如果code为null，查询字典分类集合
	 */
	@Override
	public List<Dic> getDicListByCode(String code) {
		
		return this.dicDao.getDicListByCode(code);
	}
	
	/**
	 * 更改字典的启用禁用状态
	 */
	@Override
	public boolean changeStatus(String id,int type) {
		
		boolean isItem = false;
		
		Dic dic = this.dicDao.findById(id);
		
		//当 type==0时，禁用字典，为1 时启用字典
		if(type==0){
			//禁用状态字典
			Dic disable = this.getDicByCodes(DefaultValue.ENABLE_DISABLE, DefaultValue.DIC_DISABLE);
			dic.setStatus(disable.getId());
		}else{
			//启用状态字典
			Dic enable = this.getDicByCodes(DefaultValue.ENABLE_DISABLE,DefaultValue.DIC_ENABLE);
			dic.setStatus(enable.getId());
		}
		
		this.dicDao.update(dic);
		
		//判断更改的是字典项  还是字典分类
		if(DataUtil.isNotNull(dic.getPid())){
			isItem = true;
		}
		
		return isItem;
		
	}

	/**
	 * 查询字典项分页详细信息
	 */
	@Override
	public Page searchItem(Map<String, Object> param, Dic dic) {
		return this.dicDao.searchItem(param, dic);
	}

	/**
	 * 根据字典分类code和字典项code查询字典对象
	 */
	@Override
	public Dic getDicByCodes(String parentCode, String code) {
		return this.dicDao.getDicByCodes(parentCode, code);
	}

	/**
	 * 字典code唯一性校验
	 */
	@Override
	public boolean checkCode(String code, String pid,String id) {
		
		boolean flag = false;
		
		@SuppressWarnings("rawtypes")
		List list  = this.dicDao.queryDicByCode(code, pid,id);
		
		if(!DataUtil.isNotNull(list) || list.size()==0){
			flag = true;
		}
			
		return flag;
	
	}
	
	
	
}
