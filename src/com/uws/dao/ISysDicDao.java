package com.uws.dao;

import java.util.List;
import java.util.Map;

import com.base.dao.IBaseDAO;
import com.base.dao.Page;
import com.uws.model.Dic;
/**
 * @ClassName: ISysDicDao 
 * @author: 石焱 
 * @date: 2017-2-15 下午3:44:54
 */
public interface ISysDicDao extends IBaseDAO {

	/**
	 * 查询字典分类信息详情
	 * */
	public Page search(Map<String, Object> param,Dic dic);

	
	/**
	 * 根据id查询数据
	 * @param id
	 * @return
	 */
	public Dic findById(String id);
	
	/**
	 * 根据字典分类code获取字典项集合
	 * @param code
	 * @return
	 */
	public List<Dic> getDicListByCode(String code);
	
	/**
	 * 查询字典项分页列表
	 * @param param
	 * @param dic
	 * @return
	 */
	public Page searchItem(Map<String, Object> param,Dic dic);
	
	/**
	 * 根据字典分类 code 和字典项code查询字典对象
	 * @param parentCode
	 * @param code
	 * @return Dic
	 */
	public Dic getDicByCodes(String parentCode, String code);
	
	
	/**
	 * 校验字典code是否唯一，pid为null校验 字典分类，pid不为空校验字典项   
	 * @param code   字典code
	 * @param pid    字典分类 id 允许为空
	 * @return 
	 */
	@SuppressWarnings("rawtypes")
	public List queryDicByCode(String code,String pid,String id);
	
}
