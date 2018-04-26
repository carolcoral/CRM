package com.hjcrm.resource.service.impl;

import java.sql.Timestamp;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.constants.StateConstants;
import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.resource.entity.Dealrecord;
import com.hjcrm.resource.entity.Resource;
import com.hjcrm.resource.entity.Visitrecord;
import com.hjcrm.resource.service.IResourceService;
import com.hjcrm.system.entity.User;

/**
 * 资源接口实现
 * @author likang
 * @date 2016-10-31 上午11:52:54
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceServiceImpl implements IResourceService{
	
	@Autowired
	private IDataAccess<Resource> resourceDao;
	@Autowired
	private IDataAccess<User> userDao;
	@Autowired
	private IDataAccess<Visitrecord> visitrecordDao;
	@Autowired
	private IDataAccess<Dealrecord> dealrecordDao;
	/**
	 * 增加或者修改资源信息
	 */
	
	public void saveOrUpdate(Resource resource) {
		if (resource != null && resource.getUserid() != null) {
			if (resource.getResourceId() != null) {//修改
				resource.setUpdate_id(UserContext.getLoginUser().getUserid());
				resource.setUpdate_time(new Timestamp(System.currentTimeMillis()));
				resourceDao.update(resource);
			}else{//增加
				//resource.setCreaterName(UserContext.getLoginUser().getUsername());
     			//resource.setUserid(UserContext.getLoginUser().getUserid());
				resource.setCreate_time(new Timestamp(System.currentTimeMillis()));
				resourceDao.insert(resource);
			}
		}
	}
	

	/**
	 * 查询资源信息
	 */
	
	public List<Resource> queryResource(String deptid,String userid, String roleid,PageBean pageBean) {
		if (userid != null) {
			Map<String , Object> param = new HashMap<String, Object>();
			param.put("userid", userid);
			param.put("deptid", deptid);
			param.put("roleid", roleid);
			List<Resource> list = resourceDao.queryByStatment("queryResource", param, pageBean);
			return list;
		}
		return null;
	}
	
	/**
	 * 查询资源信息
	 */
	
	public List<Resource> queryResourceTel(String timesearch,String deptid,String userid, String roleid,PageBean pageBean) {
		if (userid != null) {
			Map<String , Object> param = new HashMap<String, Object>();
			param.put("userid", userid);
			param.put("deptid", deptid);
			param.put("roleid", roleid);
			param.put("timesearch", timesearch);
			List<Resource> list = resourceDao.queryByStatment("queryResourceTel", param, pageBean);
			return list;
		}
		return null;
	}

	/**
	 * 根据资源ID删除资源
	 */
	
	public void deleteResourceById(String resourceId) {
		resourceDao.deleteByIds(Resource.class, resourceId);
	}

	/**
	 * 查询资源管理头部统计信息
	 */
	
	public List<Resource> queryResourceCount(String userid,String deptid,String roleid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Resource> list = resourceDao.queryByStatment("queryResourceCount", param, null);
		return list;
	}

	/**
	 * 查询资源管理头部今日统计信息
	 */
	
	public List<Resource> queryResourceTodayCount(String userid,String deptid,String roleid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		param.put("userid", userid);
		List<Resource> list = resourceDao.queryByStatment("queryResourceTodayCount", param, null);
		return list;
	}
	
	/**
	 * 筛选查询资源管理头部今日统计信息
	 */
	
	public List<Resource> queryResourceTodayCount(String userid,String deptid,String roleid,Resource resource) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (resource != null) {
			param.put("createStartTime", resource.getCreateStartTime() != null && !"".equals(resource.getCreateStartTime().trim())? resource.getCreateStartTime():null);//创建时间
			param.put("createEndTime", resource.getCreateEndTime() != null && !"".equals(resource.getCreateEndTime().trim())? resource.getCreateEndTime():null);//创建时间
			param.put("state", resource.getState() != null?resource.getState():null);//资源状态
			param.put("isZhuanyi", resource.getIsZhuanyi() != null?resource.getIsZhuanyi():null);//是否转移  ng
			if(deptid!=null&&Long.parseLong(deptid)!=StateConstants.DEPT_YUNYING)
			param.put("createid", resource.getUserid() != null ?resource.getUserid():null);//创建者
			param.put("belongid", resource.getBelongid() != null ?resource.getBelongid():null);//归属者
			param.put("source", resource.getSource() != null ?resource.getSource():null);//渠道
			param.put("address", resource.getAddress() != null && !"".equals(resource.getAddress()) ? resource.getAddress():null);//地区
			param.put("resourceName", resource.getResourceName() != null && !"".equals(resource.getResourceName())?resource.getResourceName():null);//资源姓名
			param.put("phone", resource.getPhone() != null && !"".equals(resource.getPhone())?resource.getPhone():null);//手机
			param.put("tel", resource.getTel() != null && !"".equals(resource.getTel())?resource.getTel():null);//电话     ng
			param.put("courseid", resource.getCourseid() != null ?resource.getCourseid():null);//课程
			param.put("yunYingNote", resource.getYunYingNote() != null && !"".equals(resource.getYunYingNote()) ?resource.getYunYingNote():null);//运营备注
			param.put("resourceLevel", resource.getResourceLevel() != null && !"".equals(resource.getResourceLevel()) ?resource.getResourceLevel():null);//资源等级
			param.put("yunYingResourceLevel", resource.getYunYingResourceLevel() != null && !"".equals(resource.getYunYingResourceLevel()) ?resource.getYunYingResourceLevel():null);//运营资源等级   likang
			param.put("visitCount", resource.getVisitCount() != null && !"".equals(resource.getVisitCount().trim()) ?resource.getVisitCount():null);//回访次数
			param.put("visitStartTime", resource.getVisitStartTime() != null  && !"".equals(resource.getVisitStartTime().trim())?resource.getVisitStartTime():null);//创建时间
			param.put("visitEndTime", resource.getVisitEndTime() != null && !"".equals(resource.getVisitEndTime().trim())?resource.getVisitEndTime():null);//创建时间
			param.put("resourceColor", resource.getResourceColor() != null && !"".equals(resource.getResourceColor().trim())?resource.getResourceColor():null);//是否星际客户
			param.put("createrName", resource.getCreaterName() != null && !"".equals(resource.getCreaterName().trim())?resource.getCreaterName():null);//创建者

		}
		param.put("userid", userid);
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		List<Resource> list = resourceDao.queryByStatment("queryResourceTodayCountBySceen", param, null);
		return list;
	}

	/**
	 * 查询所有销售人员
	 */
	
	public List<User> queryAllXiaoShou() {
		List<User> list = userDao.queryByStatment("queryAllXiaoShou", null, null);
		return list;
	}

	/**
	 * 查询所有部门销售人员
	 */
	
	public List<User> queryXiaoShouByRoleid(String deptid) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("deptid", deptid);
		List<User> list = userDao.queryByStatment("queryXiaoShouByRoleid", param, null);
		return list;
	}

	/**
	 * 查询重复的资源(姓名)
	 * 			查询重复的手机号列表
	 * 
	 */
	
	public List<Resource> queryRepeatResource(String phones,String isInfo,PageBean pageBean) {
		List<Resource> list = new ArrayList<Resource>();
		if (isInfo != null && !"".equals(isInfo) && "false".equals(isInfo)) {
			list = resourceDao.queryByStatment("queryRepeatResourcefalse", null, pageBean);
		}else if(isInfo != null && !"".equals(isInfo) && "true".equals(isInfo)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("phones", phones);
			list = resourceDao.queryByStatment("queryRepeatResourcetrue", param, pageBean);
		}
		return list;
	}
	/**
	 * 查询重复的公司资源(姓名)
	 * 			查询重复的手机号列表
	 * 
	 */
	
	public List<Resource> queryRepeatCompanyResource(String phones,String isInfo,PageBean pageBean) {
		List<Resource> list = new ArrayList<Resource>();
		if (isInfo != null && !"".equals(isInfo) && "false".equals(isInfo)) {
			list = resourceDao.queryByStatment("queryRepeatCompanyResourcefalse", null, pageBean);
		}else if(isInfo != null && !"".equals(isInfo) && "true".equals(isInfo)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("phones", phones);
			list = resourceDao.queryByStatment("queryRepeatCompanyResourcetrue", param, pageBean);
		}
		return list;
	}
	/**
	 * 根据手机号查询资源
	 */
	
	public List<Resource> queryResourceByPhone(String phone) {
		if (phone != null && !"".equals(phone)) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("phone", phone);
			List<Resource> list =  resourceDao.queryByStatment("queryResourceByPhone", param, null);
			return list;
		}
		return null;
	}

	/**
	 * 根据条件进行查询资源--筛选
	 */
	
	public List<Resource> queryResourceBySceen(String deptid,String userid,String roleid,Resource resource,String resourceIds,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (resource != null) {
			param.put("createStartTime", resource.getCreateStartTime() != null && !"".equals(resource.getCreateStartTime().trim())? resource.getCreateStartTime():null);//创建时间
			param.put("createEndTime", resource.getCreateEndTime() != null && !"".equals(resource.getCreateEndTime().trim())? resource.getCreateEndTime():null);//创建时间
			param.put("state", resource.getState() != null?resource.getState():null);//资源状态
			param.put("isZhuanyi", resource.getIsZhuanyi() != null?resource.getIsZhuanyi():null);//是否转移  ng
			if(deptid!=null&&Long.parseLong(deptid)!=StateConstants.DEPT_YUNYING)
			param.put("createid", resource.getUserid() != null ?resource.getUserid():null);//创建者
			param.put("belongid", resource.getBelongid() != null ?resource.getBelongid():null);//归属者
			param.put("source", resource.getSource() != null ?resource.getSource():null);//渠道
			param.put("address", resource.getAddress() != null && !"".equals(resource.getAddress()) ? resource.getAddress():null);//地区
			param.put("resourceName", resource.getResourceName() != null && !"".equals(resource.getResourceName())?resource.getResourceName():null);//资源姓名
			param.put("phone", resource.getPhone() != null && !"".equals(resource.getPhone())?resource.getPhone():null);//手机
			param.put("tel", resource.getTel() != null && !"".equals(resource.getTel())?resource.getTel():null);//电话     ng
			param.put("courseid", resource.getCourseid() != null ?resource.getCourseid():null);//课程
			param.put("yunYingNote", resource.getYunYingNote() != null && !"".equals(resource.getYunYingNote()) ?resource.getYunYingNote():null);//运营备注
			param.put("visitRecord", resource.getVisitRecord() != null && !"".equals(resource.getVisitRecord()) ?resource.getVisitRecord():null);//运营备注
			param.put("resourceLevel", resource.getResourceLevel() != null && !"".equals(resource.getResourceLevel()) ?resource.getResourceLevel():null);//资源等级
			param.put("yunYingResourceLevel", resource.getYunYingResourceLevel() != null && !"".equals(resource.getYunYingResourceLevel()) ?resource.getYunYingResourceLevel():null);//运营资源等级   likang
			param.put("visitCount", resource.getVisitCount() != null && !"".equals(resource.getVisitCount().trim()) ?resource.getVisitCount():null);//回访次数
			param.put("visitStartTime", resource.getVisitStartTime() != null  && !"".equals(resource.getVisitStartTime().trim())?resource.getVisitStartTime():null);//创建时间
			param.put("visitEndTime", resource.getVisitEndTime() != null && !"".equals(resource.getVisitEndTime().trim())?resource.getVisitEndTime():null);//创建时间
			param.put("resourceColor", resource.getResourceColor() != null && !"".equals(resource.getResourceColor().trim())?resource.getResourceColor():null);//是否星际客户
			param.put("createrName", resource.getCreaterName() != null && !"".equals(resource.getCreaterName().trim())?resource.getCreaterName():null);//创建者
			param.put("isWeixin", resource.getIsWeixin() != null && !"".equals(resource.getIsWeixin().trim())?resource.getIsWeixin():null);//微信
		}
		param.put("userid", userid);
		param.put("deptid", deptid);
		param.put("roleid", roleid);
		if (resourceIds != null && !"".equals(resourceIds.trim())) {
			param.clear();
			param.put("resourceIds", resourceIds);//资源IDS
		}
		List<Resource> list =  resourceDao.queryByStatment("queryResourceBySceen", param, pageBean);
		return list;
	}

	/**
	 * 根据资源ID查询资源
	 */
	
	public List<Resource> queryResourceByresourceId(Long resourceId) {
		if (resourceId != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("resourceId", resourceId);
			List<Resource> list  = resourceDao.queryByStatment("queryResource", param, null);
			return list;
		}
		return null;
	}
	
	public void updateVisitrecordByresourceId(Long visitRecordid, Long belongid) {
		if (visitRecordid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("visitRecordid", visitRecordid);
			param.put("belongid", belongid);
			resourceDao.queryByStatment("updateVisitrecordByresourceId", param, null);
		}
	}
	/**
	 * 根据资源ID查询资源的回访记录
	 */
	
	public List<Visitrecord> queryResourceVisitrecord(Long resourceId,Long deptid,Long roleid,Long userid) {
		if (resourceId != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("resourceId", resourceId);
			param.put("deptid", deptid);
			param.put("roleid", roleid);
			param.put("userid", userid);
			List<Visitrecord> list  = visitrecordDao.queryByStatment("queryResourceVisitrecord", param, null);
			return list;
		}
		return null;
	}

	/**
	 * 增加资源的回访记录
	 */
	
	public void saveOrUpdate(Visitrecord visitrecord) {
		if (visitrecord != null) {
			if (visitrecord.getVisitRecordid() != null) {//修改
				visitrecord.setUpdate_time(new Timestamp(System.currentTimeMillis()));
				visitrecord.setUpdate_id(UserContext.getLoginUser().getUserid());
				visitrecordDao.update(visitrecord);
			}else{
				visitrecord.setCreate_time(new Timestamp(System.currentTimeMillis()));
				visitrecordDao.insert(visitrecord);
			}
		}
	}

	/**
	 * 增加/修改成交记录
	 */
	
	public void saveOrUpdate(Dealrecord dealrecord) {
		if (dealrecord != null) {
			if (dealrecord.getDealrecordId() != null) {//修改
				dealrecordDao.update(dealrecord);
			}else{//增加
				dealrecord.setCreate_time(new Timestamp(System.currentTimeMillis()));
				dealrecordDao.insert(dealrecord);
			}
		}
	}


	/**
	 * 学员分配销售人员
	 */
	
	public void updateResourceBelongid(String resourceIds, Long belongid,Integer state,Timestamp assignTime) {
		if (resourceIds != null && !"".equals(resourceIds) && belongid != null) {
			Map<String, Object> param =  new HashMap<String, Object>();
			param.put("resourceIds", resourceIds);
			param.put("belongid", belongid);
			param.put("state", state);
			param.put("assignTime", assignTime);
			resourceDao.updateByStatment("updateResourceBelongid", param);
		}
	}
	
	/**
	 * 学员转移销售人员   likang
	 */
	
	public void zhuanyiResourceBelongid(String resourceIds, Long belongid,Integer state,Timestamp assignTime) {
		if (resourceIds != null && !"".equals(resourceIds) && belongid != null) {
			Map<String, Object> param =  new HashMap<String, Object>();
			param.put("resourceIds", resourceIds);
			param.put("belongid", belongid);
			param.put("state", state);
			param.put("assignTime", assignTime);
			resourceDao.updateByStatment("zhuanyiResourceBelongid", param);
		}
	}


	/**
	 * 查询所有的创建者(资源)
	 */
	
	public List<User> queryAllCreatePerson() {
		List<User> list = userDao.queryByStatment("queryAllCreatePerson", null, null);
		return list;
	}


	/**
	 * 查询当前人员的所有下属员工
	 */
	
	public List<User> querydeptSubuser(Long deptid, Long roleid, Long userid) {
		if (userid != null && deptid != null && roleid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("deptid", deptid);
			param.put("userid", userid);
			param.put("roleid", roleid);
			List<User> list =  userDao.queryByStatment("querydeptSubuser", param, null);
			return list;
		}
		return null;
	}



	/**
	 * 删除成交记录
	 */
	
	public void deleteDealrecord(Long resourceId, Long studentId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resourceId", resourceId);
		param.put("studentId", studentId);
		dealrecordDao.deleteByStatment("deleteDealrecord", param);
	}
	
	public void deleteVisitrecord(Long resourceId) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resourceId", resourceId);
		dealrecordDao.deleteByStatment("deleteVisitrecord", param);
	}

	/**
	 * 标注或者取消资源星级客户
	 */
	
	public void resourceColorSign(String resourceIds, String resourceColor) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resourceIds", resourceIds);
		param.put("resourceColor", resourceColor);
		resourceDao.updateByStatment("resourceColorSign", param);
	}
	
	
	public void updateResourceWeixin(String resourceIds,Integer isWeixin) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("resourceIds", resourceIds);
		param.put("isWeixin", isWeixin);
		resourceDao.updateByStatment("updateResourceWeixin", param);
	}


	/**
	 * 根据资源/学员ID，查询回访记录最近统计信息
	 */
	
	public List<Visitrecord> queryVisitrecordByresourceId(Long resourceId,Long studentId) {
		if (resourceId != null || studentId != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("resourceId", resourceId);
			param.put("studentId", studentId);
			List<Visitrecord> list  = visitrecordDao.queryByStatment("queryVisitrecordByresourceId", param, null);
			return list;
		}
		return null;
	}
	
	public List<Visitrecord> queryVisitrecordsByresourceId(Long resourceId,Long studentId) {
		if (resourceId != null || studentId != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("resourceId", resourceId);
			param.put("studentId", studentId);
			List<Visitrecord> list  = visitrecordDao.queryByStatment("queryVisitrecordsByresourceId", param, null);
			return list;
		}
		return null;
	}
	
	/**
	 * 查询符合条件的数据
	 */
	
	public List<Resource> queryTodayVirecordResources(String deptid, String userid,String roleid, String nowtime,String nexttime,PageBean processPageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("nowtime", nowtime);
		param.put("nexttime", nexttime);
		param.put("deptid", deptid);
		param.put("userid", userid);
		param.put("roleid", roleid);
		List<Resource> list = resourceDao.queryByStatment("queryTodayVirecordResources", param, null);
		return list;
		
	}
	




}
