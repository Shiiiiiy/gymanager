package com.uws.service.impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.base.dao.Page;
import com.uws.dao.ISysMenuDAO;
import com.uws.dao.ISysUserDAO;
import com.uws.model.SysMenu;
import com.uws.model.SysUser;
import com.uws.service.ISysMenuService;
import com.uws.service.ISysUserService;
/**
 * 用户管理 业务接口实现类
 * @author wangjun
 *
 */
@Service
public class SysMenuServiceImpl implements ISysMenuService {
	/**系统菜单 dao层*/
	@Resource
	private ISysMenuDAO sysMenuDAO;
	/**
	 * 通过自身id  获取自身实体类
	 * @param id  自身id
	 * @param 
	 */
	@Override
	public SysMenu searchById(String id) {
		return sysMenuDAO.searchById(id);
	}
	@Override
	/**
	 * 通过菜单父code  返回其节点列表
	 * @param parentCode  菜单父
	 * @return 
	 */
	public List<Map> getMenuListByParentCode(String parentCode) {
		return sysMenuDAO.getMenuListByParentCode(parentCode);
	}
	/**
	 * 通过菜单父id  返回其节点列表
	 * @param id  父单菜id
	 * @param 
	 */
	@Override
	public List<SysMenu> getMenuListById(String id) {
		return sysMenuDAO.getMenuListById(id);
	}
	/**
	 * 通过复数 id  返回 菜单集合
	 * @param id  自身id
	 * @param 
	 */
	@Override
	public List<Map> searchByIds(List<Long> ids){
		return sysMenuDAO.searchByIds(ids);
	}
	/**
	 * 改变指定菜单 启用禁用状态
	 * @param id 菜单表id
     * @return
	 */
	@Override
	public Boolean updateStatus(String id) {
		return sysMenuDAO.updateStatus(id);
	}
	/**
	 * 删除指定菜单  通过菜单id
	 * @param id 菜单表id
     * @return
	 */
	@Override
	public Boolean deleteMenuById(String id) {
		return sysMenuDAO.deleteMenuById(id);
	}
	/**
	 * 保存一个系统菜单 实体
	 * @param M 系统菜单对象
     * @return
	 */
	@Override
	public SysMenu save(SysMenu M) {
		return sysMenuDAO.saveMenu(M);
	}
	/**
	 * 保存一个系统菜单 修改的实体
	 * @param M 系统菜单对象
     * @return
	 */
	@Override
	public void update(SysMenu M) {
		sysMenuDAO.updateMenu(M);
	}
	/**
	 * 获取根菜单实体
	 * @param 
	 */
	@Override
	public SysMenu getRootMenu() {
		return sysMenuDAO.getRootMenu();
	}
	/**
	 * 判断数据库是否已经存在此  MenuCode
	 * @param code 菜单表code
     * @return true 有重名
	 */
	@Override
	public Boolean checkDupliMenuCode(String code) {
		return sysMenuDAO.checkDupliMenuCode(code);
	}
	/**
	 * 从dic获取  菜单树形结构 dtree
	 * @return 
	 * */
	@Override
	public List<Map> Dtree() {
		return sysMenuDAO.Dtree();
	}
	/**
	 * 判断当前菜单是否是root下 的2级菜单
	 * @param id 菜单表id
	 * @return 
	 * */
	@Override
	public Boolean isLastMenu(String id) {
		return sysMenuDAO.isLastMenu(id);
	}
	
}
