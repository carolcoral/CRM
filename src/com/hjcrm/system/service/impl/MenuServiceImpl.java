package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.exception.BusinessException;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Menu;
import com.hjcrm.system.service.IMenuService;

/**
 * 菜单功能接口实现类
 * 
 * @author likang
 * @date 2016-10-17 上午9:11:50
 */
@Service
public class MenuServiceImpl implements IMenuService {

	@Autowired
	private IDataAccess<Menu> menuDao;

	/**
	 * 保存/修改菜单
	 */
	
	public void saveOrUpdate(Menu menu) {
		if (menu.getMenuid() != null) {
			menu.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			menu.setUpdate_id(UserContext.getLoginUser().getUserid());
			menuDao.update(menu);
		} else {
			menu.setCreate_time(new Timestamp(System.currentTimeMillis()));
			menu.setCreate_id(UserContext.getLoginUser().getUserid());
			menuDao.insert(menu);
		}
	}

	/**
	 * 根据用户ID，查询用户权限的菜单
	 */
	
	public List<Menu> queryMenuByUserid(Long userid) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("queryParent", "true");
		paramMap.put("userid", userid);
		List<Menu> parentMenu = menuDao.queryByStatment("queryMenuByUserid", paramMap, null);
		for(Menu parent : parentMenu){
			Long menuid = parent.getMenuid();
			paramMap.clear();
			paramMap.put("parentId", menuid);
			paramMap.put("userid", userid);
			List<Menu> childrenMenu = menuDao.queryByStatment("queryMenuByUserid", paramMap, null);
			parent.setChildren(childrenMenu);
		}
		return parentMenu;
	}
	

	/**
	 * 查询所有的分级别的菜单
	 */
	
	public List<Menu> queryMenuList(Long roleid) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("queryParent", "true");
		paramMap.put("roleid", roleid);
		List<Menu> parentMenu = menuDao.queryByStatment("queryMenuList", paramMap, null);
		for(Menu parent : parentMenu){
			Long menuid = parent.getMenuid();
			paramMap.clear();
			paramMap.put("roleid", roleid);
			paramMap.put("parentId", menuid);
			List<Menu> childrenMenu = menuDao.queryByStatment("queryMenuList", paramMap, null);
			parent.setChildren(childrenMenu);
		}
		return parentMenu;
	}

	/**
	 * 是否存在下级菜单
	 * 
	 * @param id
	 * @return
	 */
	
	public boolean hasChild(Long id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuid", id);
		Long count = menuDao.queryLongValueByStatment("hasChild", param);
		return count > 0 ? true : false;
	}

	/**
	 * 根据主键查询菜单
	 * @param menuid
	 * @return
	 * @author likang 
	 * @date 2016-10-20 上午9:58:07
	 */
	
	public Menu queryById(Long menuid) {
		return (Menu) menuDao.queryByIdentity(Menu.class, menuid);
	}

	/**
	 * 是否存在角色关联
	 */
	
	public boolean hasLinked4Role(Long id) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("menuid", id);
		Long count = (Long) menuDao.queryLongValueByStatment("hasLinked4Role",param);
		return count > 0 ? true : false;
	}

	
	public void delete(String ids) throws BusinessException {
		for (String id : ids.split(",")) {
			menuDao.deleteByIds(Menu.class, id);
		}
	}

	/**
	 * 查询加载全部菜单
	 */
	
	public List<Menu> queryAllMenu() {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("queryParent", "true");
		List<Menu> parentMenu = menuDao.queryByStatment("queryAllMenu", paramMap, null);
		for(Menu parent : parentMenu){
			Long menuid = parent.getMenuid();
			paramMap.clear();
			paramMap.put("parentId", menuid);
			List<Menu> childrenMenu = menuDao.queryByStatment("queryAllMenu", paramMap, null);
			parent.setChildren(childrenMenu);
		}
		return parentMenu;
	}

	/**
	 * 根据角色id，查询角色权限的菜单
	 */
	
	public List<Menu> queryMenuByRoleid(Long roleid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("roleid", roleid);
		List<Menu> list = menuDao.queryByStatment("queryMenuByRoleid", param, null);
		return list;
	}

	/**
	 * 获取所有菜单，不划分主子菜单
	 */
	
	public List<Menu> queryMenus() {
		List<Menu> list = menuDao.queryByStatment("queryMenus", null, null);
		return list;
	}



}
