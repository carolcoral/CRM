package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Role;
import com.hjcrm.system.entity.Role_menu;
import com.hjcrm.system.service.IRoleService;

/**
 * 角色接口实现类
 * @author likang
 * @date 2016-10-18 下午4:08:36
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class RoleServiceImpl implements IRoleService {
	
	@Autowired
	private IDataAccess<Role> roleDao;
	@Autowired
	private IDataAccess<Role_menu> roleMenuDao;

	
	public void saveOrUpdate(Role role) {
		if (role.getRoleid() != null) {
			role.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			role.setUpdate_id(UserContext.getLoginUser().getUserid());
			roleDao.update(role);
		} else {
			role.setCreate_time(new Timestamp(System.currentTimeMillis()));
			role.setCreate_id(UserContext.getLoginUser().getUserid());
			roleDao.insert(role);
		}
	}

	
	public void delete(String ids) {
		for (String id : ids.split(",")) {
			roleDao.deleteByIds(Role.class, id);
		}		
	}

	/**
	 * 根据部门ID，查询部门下的所有角色
	 */
	
	public List<Role> queryRoleByDeptid(Long deptid) {
		if (deptid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deptid", deptid);
			List<Role> list = roleDao.queryByStatment("queryRoleByDeptid", param, null);
			return list;
		}
		return null;
	}

	/**
	 * 修改角色对应的菜单，删除对应关系
	 */
	
	public void deleteRoleMenu(Long roleid) {
		if (roleid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("roleid", roleid);
			roleMenuDao.deleteByStatment("deleteRoleMenu", param);
		}
	}

	/**
	 * 保存角色-菜单对应关系
	 */
	
	public void saveRoleMenu(Role_menu roleMenu) {
		roleMenuDao.insert(roleMenu);
	}

	/**
	 * 查询所有角色列表
	 */
	@Cacheable(value = "baseCache",key = "'123'")
	
	public List<Role> queryAllRole(PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		List<Role> list = roleDao.queryByStatment("queryAllRole", param, pageBean);
		return list;
	}

	
	
	
	
}
