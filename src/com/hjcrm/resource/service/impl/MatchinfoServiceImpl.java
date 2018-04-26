package com.hjcrm.resource.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.resource.entity.Matchinfo;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.resource.service.IMatchinfoService;

/**
 * 到账信息接口实现类
 * @author likang
 * @date 2016-11-10 上午10:58:30
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class MatchinfoServiceImpl implements IMatchinfoService {
	
	@Autowired
	private IDataAccess<Matchinfo> matchinfoDao;
	@Autowired
	private IDataAccess<Student> studentDao;

	/**
	 * 查询到账信息
	 */
	public List<Matchinfo> queryMatchinfo(Matchinfo matchinfo,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		if (matchinfo != null) {
			param.put("matchInfoId", matchinfo.getMatchInfoId());
			param.put("matchname", matchinfo.getMatchname());
			param.put("payMoney", matchinfo.getPayMoney());
			param.put("payType", matchinfo.getPayType());
			param.put("receiveTime", matchinfo.getReceiveTime());
			param.put("matchnote", matchinfo.getMatchnote());
			param.put("ismatch", matchinfo.getIsmatch());
			param.put("receiveStartTime", matchinfo.getReceiveStartTime());
			param.put("receiveEndTime", matchinfo.getReceiveEndTime());
		}
		List<Matchinfo> list = matchinfoDao.queryByStatment("queryMatchinfo", param, pageBean);
		return list;
	}

	/**
	 * 查询到账信息(行政专用)
	 */
	public List<Matchinfo> queryMatchinfo(String matchname,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("iscommit", "false");
		param.put("matchname", matchname);
		List<Matchinfo> list = matchinfoDao.queryByStatment("queryMatchinfo", param, pageBean);
		return list;
	}

	/**
	 * 保存到账信息
	 */
	public void saveOrUpdateMatchinfo(Matchinfo matchinfo) {
		if (matchinfo != null) {
			if (matchinfo.getMatchInfoId() != null) {//修改
				matchinfoDao.update(matchinfo);
			}else{//增加
				matchinfoDao.insert(matchinfo);
			}
		}
	}

	/**
	 * 修改到账信息的匹配状态
	 */
	public void updateIsmatch(String matchinfoIds, Integer ismatch) {
		if (matchinfoIds != null && !"".equals(matchinfoIds.trim()) && ismatch != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("matchinfoIds", matchinfoIds);
			param.put("ismatch", ismatch);
			matchinfoDao.updateByStatment("updateIsmatch", param);
		}

	}

	/**
	 * 到账信息查重
	 */
	public List<Matchinfo> queryRepeatMatchinfo(String matchnames,String isInfo, PageBean pageBean) {
		List<Matchinfo> list = new ArrayList<Matchinfo>();
		if (isInfo != null && !"".equals(isInfo) && "false".equals(isInfo)) {
			list = matchinfoDao.queryByStatment("queryRepeatMatchinfofalse", null, pageBean);
		}else if(isInfo != null && !"".equals(isInfo) && "true".equals(isInfo)){
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("matchnames", matchnames);
			list = matchinfoDao.queryByStatment("queryRepeatMatchinfotrue", param, pageBean);
		}
		return list;
	}

	/**
	 * 根据姓名查询到账信息
	 */
	public List<Matchinfo> queryMatchinfoBymname(String matchname) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("matchname", matchname);
		List<Matchinfo> list = matchinfoDao.queryByStatment("queryMatchinfoBymname", param, null);
		return list;
	}

	/**
	 * 查询有网络培训费的学员列表
	 */
	public List<Student> queryNetworkEduMoneyBycaiwu(String phone,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		List<Student> list = studentDao.queryByStatment("queryNetworkEduMoneyBycaiwu", param, pageBean);
		return list;
	}

	/**
	 * 到账学员删除(未匹配)
	 */
	public void deleteMatchinfos(Long userid, String matchInfoIds) {
		studentDao.deleteByIds(Matchinfo.class, matchInfoIds);
	}


}
