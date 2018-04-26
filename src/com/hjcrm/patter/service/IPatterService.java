package com.hjcrm.patter.service;

import java.util.List;

import com.hjcrm.patter.entity.Patter;
import com.hjcrm.patter.entity.Pattertype;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.Course;

/**
 * 话术接口
 * @author likang
 * @date 2016-10-27 下午3:27:27
 */
public interface IPatterService {
	
	/**
	 * 保存或者修改话术内容
	 * @param patter
	 * @author likang 
	 * @date 2016-10-27 下午3:29:53
	 */
	public void saveOrUpdate(Patter patter);
	
	/**
	 * 删除话术内容
	 * @param patterids
	 * @author likang 
	 * @date 2016-10-27 下午3:31:17
	 */
	public void deletePatter(String patterids);
	
	/**
	 * 是否共享 
	 * @param patterid
	 * @param isshare 是否共享 0不共享  1共享 
	 * @author likang 
	 * @date 2016-10-27 下午3:33:34
	 */
	public void updatePatterIsShare(Long patterid,Integer isshare);
	
	/**
	 * 查询课程和课程下的场景
	 * @param roleid
	 * @param userid
	 * @param teacherid
	 * @param courseName
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午3:42:16
	 */
	public List<Pattertype> queryPattertype(Long roleid,Long userid,Long teacherid,String courseid,PageBean pageBean);
	
	/**
	 * 查询话术内容
	 * @param roleid
	 * @return
	 * @author likang 
	 * @date 2016-10-27 下午3:35:06
	 */
	public List<Patter> queryPatter(Long roleid,Long userid,Long patterTypeId,Long courseid,PageBean pageBean);
	
	
	
	
}
