package com.uws.service;

import java.util.List;
import java.util.Map;

import com.base.dao.Page;
import com.uws.model.SysMenu;
import com.uws.model.SysUser;

/**
 * 菜单管理 业务接口
 * @author wangjun
 *
 */
public interface ISysMenuService {
	/**
	 * 通过菜单父code  返回其节点列表
	 * @param parentCode  菜单父id
	 * @param 
	 */
	public List<Map> getMenuListByParentCode(String parentCode);
	/**
	 * 通过菜单父id  返回其节点列表
	 * @param id  父单菜id
	 * @param 
	 */
	public List<SysMenu> getMenuListById(String id);
	/**
	 * 获取根菜单实体
	 * @param 
	 */
	public SysMenu getRootMenu();
	/**
	 * 通过自身id  获取自身实体类
	 * @param id  自身id
	 * @param 
	 */
	public SysMenu searchById(String id);
	/**
	 * 通过复数 id  返回 菜单集合
	 * @param id  自身id
	 * @param 
	 */
	public List<Map> searchByIds(List<Long> ids);
	/**
	 * 改变指定菜单 启用禁用状态
	 * @param id 菜单表id
     * @return
	 */
	public Boolean updateStatus(String id);
	/**
	 * 删除指定菜单  通过菜单id
	 * @param id 菜单表id
     * @return
	 */
	public Boolean deleteMenuById(String id);
	/**
	 * 保存一个系统菜单 新增实体
	 * @param M 系统菜单对象
     * @return
	 */
	public SysMenu save(SysMenu M);
	/**
	 * 保存一个系统菜单 修改的实体
	 * @param M 系统菜单对象
     * @return
	 */
	public void update(SysMenu M);
	/**
	 * 判断数据库是否已经存在此  MenuCode
	 * @param code 菜单表code
     * @return true 有重名
	 */
	public Boolean checkDupliMenuCode(String code);
	/**
	 * 从dic获取  菜单树形结构 dtree
	 * @return 
	 * */
	public List<Map> Dtree();
	/**
	 * 判断当前菜单是否是root下 的2级菜单
	 * @param id 菜单表id
	 * @return 
	 * */
	public Boolean isLastMenu(String id);

}
