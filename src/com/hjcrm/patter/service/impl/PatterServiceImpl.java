package com.hjcrm.patter.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.patter.entity.Patter;
import com.hjcrm.patter.entity.Pattertype;
import com.hjcrm.patter.service.IPatterService;
import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.Course;

@Service
@Transactional(rollbackFor = Exception.class)
public class PatterServiceImpl implements IPatterService{
	
	@Autowired
	private IDataAccess<Course> courseDao;//查询课程和课程场景类别
	@Autowired
	private IDataAccess<Patter> patterDao;//话术内容
	@Autowired
	private IDataAccess<Pattertype> pattertypeDao;//话术场景
 
	/**
	 * 保存或者修改话术内容
	 */
	public void saveOrUpdate(Patter patter) {
		if (patter != null && patter.getPatterid() != null) {
			patter.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			patter.setUpdate_id(UserContext.getLoginUser().getUserid());
			patterDao.update(patter);
		}else if (patter != null && patter.getPatterid() == null) {
			patter.setCreate_time(new Timestamp(System.currentTimeMillis()));
			patterDao.insert(patter);
		}
	}

	/**
	 * 删除话术内容
	 */
	public void deletePatter(String patterids) {
		patterDao.deleteByIds(Patter.class, patterids);
	}

	public void updatePatterIsShare(Long patterid, Integer isshare) {
		if (patterid != null && isshare != null) {
			Map<String , Object> param = new HashMap<String, Object>();
			param.put("patterid", patterid);
			param.put("isshare", isshare);
			patterDao.updateByStatment("updatePatterIsShare", param);
		}
	}

	/**
	 * 查询课程和课程下的场景
	 */
	public List<Pattertype> queryPattertype(Long roleid, Long userid,Long teacherid, String courseid,PageBean pageBean) {
		Map<String,Object> paramMap = new HashMap<String, Object>();
		paramMap.put("courseid", courseid);
		List<Pattertype> childrenlist = pattertypeDao.queryByStatment("queryPattertype", paramMap, null);
		return childrenlist;
	}

	/**
	 * 查询话术内容
	 */
	public List<Patter> queryPatter(Long roleid, Long userid, Long patterTypeId, Long courseid,PageBean pageBean) {
		if (patterTypeId != null) {
			Map<String , Object> param = new HashMap<String, Object>();
			param.put("patterTypeId", patterTypeId);
			param.put("userid", userid);
			List<Patter> list = patterDao.queryByStatment("queryPatter", param, pageBean);
			return list;
		}
		return null;
	}

}
