package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Dept;

/**
 * 部门管理接口类
 * @author likang
 * @date 2016-10-18 下午4:08:58
 */
public interface IDeptService {

	/**
	 * 保存或者修改部门
	 * @param dept
	 * @author likang 
	 * @date 2016-10-18 下午4:41:34
	 */
	public void saveOrUpdate(Dept dept);
	
	/**
	 * 删除部门
	 * @param ids
	 * @author likang 
	 * @date 2016-10-18 下午4:45:42
	 */
	public void delete(String ids);
	
	/**
	 * 查询所有部门
	 * @return
	 * @author likang 
	 * @date 2016-10-18 下午4:46:57
	 */
	public List<Dept> queryDept(PageBean pageBean);
	
}
