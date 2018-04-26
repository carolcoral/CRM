package com.hjcrm.system.service;

import java.util.List;
import java.util.Map;

import com.hjcrm.publics.exception.BusinessException;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Menu;

/**
 * 菜单功能接口
 * @author likang
 * @date 2016-10-17 上午8:58:23
 */
public interface IMenuService {

	/**
	 * 保存/修改菜单
	 * @param menu
	 * @author likang 
	 * @date 2016-10-17 上午8:58:54
	 */
	public void saveOrUpdate(Menu menu);
	
	/**
	 * 根据用户ID，查询用户权限的菜单
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-10-17 上午8:59:33
	 */
	public List<Menu> queryMenuByUserid(Long userid);
	
	/**
	 * 查询所有的分级别的菜单
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-10-17 上午8:59:33
	 */
	public List<Menu> queryMenuList(Long roleid);
	
	/**
	 * 根据角色id，查询角色权限的菜单
	 * @param roleid
	 * @return
	 * @author likang 
	 * @date 2016-10-17 上午8:59:33
	 */
	public List<Menu> queryMenuByRoleid(Long roleid);
	
	/**
	 * 获取所有菜单，不划分主子菜单
	 * @return
	 * @author likang 
	 * @date 2016-10-21 下午3:30:30
	 */
	public List<Menu> queryMenus();
	
	/**
	 * 删除菜单，如果存在下级菜单则不允许删除
	 * @param id
	 * @throws DaoException
	 */
	public void delete(String id) throws BusinessException;
	
	/**
	 * 查询加载全部菜单
	 * @return
	 * @author likang 
	 * @date 2016-10-20 上午9:37:07
	 */
	public List<Menu> queryAllMenu();
	
	/**
	 * 是否存在下级菜单
	 * @param id
	 * @return
	 * @author likang 
	 * @date 2016-10-20 上午9:55:09
	 */
	public boolean hasChild(Long id);
	
	/**
	 * 是否存在角色关联
	 * @param id
	 * @return
	 * @author likang 
	 * @date 2016-10-20 上午9:55:36
	 */
	public boolean hasLinked4Role(Long id);
	
	/**
	 * 根据主键查询菜单
	 * @param menuid
	 * @return
	 * @author likang 
	 * @date 2016-10-20 上午9:58:31
	 */
	public Menu queryById(Long menuid);
	
	
}
