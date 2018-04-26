package com.hjcrm.resource.service;

import java.sql.Timestamp;
import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.resource.entity.Dealrecord;
import com.hjcrm.resource.entity.Resource;
import com.hjcrm.resource.entity.Visitrecord;
import com.hjcrm.system.entity.User;

/**
 * 资源信息接口
 * @author likang
 * @date 2016-10-31 上午11:50:23
 */
public interface IResourceService {
	
	/**
	 * 增加或者修改资源信息
	 * @param resource
	 * @author likang 
	 * @date 2016-10-31 下午12:00:37
	 */
	public void saveOrUpdate(Resource resource);
	

	/**
	 * 查询资源信息
	 * @param userid 用户id
	 * @param roleid 用户角色id
	 * @param pageBean 分页
	 * @return
	 * @author likang 
	 * @date 2016-10-31 上午11:51:22
	 */
	public List<Resource> queryResource(String deptid,String userid,String roleid,PageBean pageBean);
	
	/**
	 * 查询电话量
	 * @param userid 用户id
	 * @param roleid 用户角色id
	 * @param pageBean 分页
	 * @return
	 * @author likang
	 * @param timesearch 
	 * @date 2017年1月5日15:17:01
	 */
	public List<Resource> queryResourceTel(String timesearch,String deptid,String userid,String roleid, PageBean pageBean);
	
	/**
	 * 查询资源管理头部统计信息
	 * @return
	 * @author likang 
	 * @date 2016-11-2 下午5:41:59
	 */
	public List<Resource> queryResourceCount(String userid,String deptid,String roleid);
	
	/**
	 * 查询资源管理头部今日统计信息
	 * @return
	 * @author likang 
	 * @date 2016-11-2 下午5:41:59
	 */
	public List<Resource> queryResourceTodayCount(String userid,String deptid,String roleid);
	
	/**
	 * 筛选查询资源管理头部今日统计信息
	 * @return
	 * @author likang 
	 * @date 2016-11-2 下午5:41:59
	 */
	public List<Resource> queryResourceTodayCount(String userid,String deptid,String roleid,Resource resource);
	
	/**
	 * 根据资源ID删除资源
	 * @param resourceId
	 * @author likang 
	 * @date 2016-10-31 上午11:57:19
	 */
	public void deleteResourceById(String resourceId);
	
	/**
	 * 查询所有销售人员
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午3:53:24
	 */
	public List<User> queryAllXiaoShou();
	
	/**
	 * 查询所有的创建者(资源)
	 * @return
	 * @author likang 
	 * @date 2016-11-10 下午2:52:04
	 */
	public List<User> queryAllCreatePerson();
	
	/**
	 * 查询当前人员的所有下属员工
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-11-15 上午10:57:20
	 */
	public List<User> querydeptSubuser(Long deptid,Long roleid,Long userid);
	
	/**
	 * 查询重复的资源(姓名)
	 * 			查询重复的手机号列表
	 * @param pageBean
	 * @param phones 手机号列表
	 * @param isInfo true查详细信息  false查重复手机号
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午4:00:07
	 */
	public List<Resource> queryRepeatResource(String phones,String isInfo,PageBean pageBean);
	
	/**
	 * 根据手机号查询资源
	 * @param phone
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:20:10
	 */
	public List<Resource> queryResourceByPhone(String phone);
	
	/**
	 * 根据资源ID查询资源
	 * @param resourceId
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:20:10
	 */
	public List<Resource> queryResourceByresourceId(Long resourceId);
	
	/**
	 * 根据资源ID查询资源的回访记录
	 * @param resourceId
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:20:10
	 */
	public List<Visitrecord> queryResourceVisitrecord(Long resourceId,Long deptid,Long roleid,Long userid);
	
	/**
	 * 根据条件进行查询资源--筛选
	 * @param resource
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:29:09
	 */
	public List<Resource> queryResourceBySceen(String deptid,String userid,String roleid,Resource resource,String resourceIds,PageBean pageBean);
	
	/**
	 * 增加资源的回访记录
	 * @param visitrecord
	 * @author likang 
	 * @date 2016-11-8 上午9:28:15
	 */
	public void saveOrUpdate(Visitrecord visitrecord);
	
	/**
	 * 增加/修改成交记录
	 * @param dealrecord
	 * @author likang 
	 * @date 2016-11-8 上午10:40:40
	 */
	public void saveOrUpdate(Dealrecord dealrecord);
	
	/**
	 * 删除成交记录
	 * @param resourceId
	 * @param studentId
	 * @author likang 
	 * @date 2016-11-16 上午11:45:43
	 */
	public void deleteDealrecord(Long resourceId,Long studentId );
	
	
	
	/**
	 * 资源分配销售人员
	 * @param resourceIds
	 * @param belongid
	 * @author likang 
	 * @date 2016-11-8 下午3:41:52
	 */
	public void updateResourceBelongid(String resourceIds,Long belongid,Integer state,Timestamp assignTime);

	/**
	 * 资源转移销售人员
	 * @param resourceIds
	 * @param belongid
	 * @author likang
	 * @date 2016-11-8 下午3:41:52
	 */
	public void zhuanyiResourceBelongid(String resourceIds,Long belongid,Integer state,Timestamp assignTime);
	
	/**
	 * 标注或者取消资源星级客户
	 * @param resourceIds
	 * @param resourceColor
	 * @author likang 
	 * @date 2016-12-7 上午10:43:42
	 */
	public void resourceColorSign(String resourceIds, String resourceColor);

	/**
	 * 根据资源/学员ID，查询回访记录最近统计信息
	 * @param resourceId
	 * @param studentId
	 * @return
	 * @author likang 
	 * @date 2016-12-14 下午12:26:23
	 */
	List<Visitrecord> queryVisitrecordByresourceId(Long resourceId, Long studentId);
	List<Visitrecord> queryVisitrecordsByresourceId(Long resourceId, Long studentId);

	/**
	 * 查询重复的公司资源(姓名)
	 * 			查询重复的手机号列表
	 */
	List<Resource> queryRepeatCompanyResource(String phones, String isInfo, PageBean pageBean);

	/**
	 * 查询部门销售人员
	 * @param deptidId
	 * @return
	 * @author likang 
	 * @date 2016-12-15 15:04:17
	 */
	public List<User> queryXiaoShouByRoleid(String deptid);
	
	/**
	 * 今日需要回访的资源
	 * @param deptid
	 * @param userid
	 * @param roleid
	 * @param processPageBean
	 * @return
	 * @author likang 
	 * @date 2016-12-26 上午11:51:44
	 */
	public List<Resource> queryTodayVirecordResources(String deptid, String userid,String roleid, String nowtime,String nexttime,PageBean processPageBean);


	public void updateVisitrecordByresourceId(Long visitRecordid, Long belongid);


	public void deleteVisitrecord(Long resourceId);


	public void updateResourceWeixin(String resourceIds, Integer isWeixin);


	
}
