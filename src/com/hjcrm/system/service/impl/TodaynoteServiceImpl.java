package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.system.entity.Todaynote;
import com.hjcrm.system.service.ITodayNoteService;

@Service
@Transactional(rollbackFor = Exception.class)
public class TodaynoteServiceImpl implements ITodayNoteService {
	
	@Autowired
	private IDataAccess<Todaynote> todaynoteDao;

	/**
	 * 增加/修改今日目标
	 */
	
	public void saveOrUpdate(Todaynote todaynote) {
		if (todaynote != null) {
			if (todaynote.getId() != null) {
				todaynote.setUpdate_time(new Timestamp(System.currentTimeMillis()));
				todaynoteDao.update(todaynote);
			}else{
				todaynote.setCreate_time(new Timestamp(System.currentTimeMillis()));
				todaynoteDao.insert(todaynote);
			}
		}
	}

	/**
	 * 查询
	 */
	
	public List<Todaynote> queryTodaynote(Long userid) {
		if (userid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userid", userid);
			List<Todaynote> list = todaynoteDao.queryByStatment("queryTodaynote", param, null);
			return list;
		}
		return null;
	}

}
