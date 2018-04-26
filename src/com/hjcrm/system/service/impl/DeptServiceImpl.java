package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Dept;
import com.hjcrm.system.entity.Role;
import com.hjcrm.system.service.IDeptService;

/**
 * 部门管理接口类
 * @author likang
 * @date 2016-10-18 下午4:09:54
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class DeptServiceImpl implements IDeptService {

	@Autowired
	private IDataAccess<Dept> deptDao;
	
	
	/**
	 * 保存或者修改部门
	 */
	
	public void saveOrUpdate(Dept dept) {
		if (dept != null && dept.getDeptid() != null) {
			dept.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			dept.setUpdate_id(UserContext.getLoginUser().getUserid());
			deptDao.update(dept);
		}else{
			dept.setCreate_time(new Timestamp(System.currentTimeMillis()));
			dept.setCreate_id(UserContext.getLoginUser().getUserid());
			deptDao.insert(dept);
		}
		
	}

	/**
	 * 删除部门
	 */
	
	public void delete(String ids) {
		for (String id : ids.split(",")) {
			deptDao.deleteByIds(Dept.class, id);
		}	
	}

	/**
	 * 查询所有部门
	 */
	
	//@Cacheable(value = "baseCache",key = "1" )
	public List<Dept> queryDept(PageBean pageBean) {
		List<Dept> list = deptDao.queryByStatment("queryDept", null, pageBean);
		return list;
	}
	
	
	
	

}
