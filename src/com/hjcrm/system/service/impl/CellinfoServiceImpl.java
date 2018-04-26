package com.hjcrm.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.system.entity.Cellinfo;
import com.hjcrm.system.service.ICellinfoService;


@Service
@Transactional(rollbackFor = Exception.class)
public class CellinfoServiceImpl implements ICellinfoService {
	
	@Autowired
	private IDataAccess<Cellinfo> cellinfoDao;

	/**
	 * 保存/修改列名
	 */
	
	public void saveOrUpdate(Cellinfo cellinfo) {
		if (cellinfo != null) {
			cellinfoDao.insert(cellinfo);
		}
	}
	

	/**
	 * 删除用户的菜单选择列
	 */
	
	public void deleteCellinfo(Long userid, String cellmenu) {
		if (userid != null && cellmenu != null && !"".equals(cellmenu)) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userid", userid);
			param.put("cellmenu", cellmenu);
			cellinfoDao.deleteByStatment("deleteCellinfo", param);
		}
	}

	/**
	 * 查询用户的菜单列名
	 */
	
	public List<Cellinfo> queryCellinfo(Long userid, String cellmenu) {
		if (userid != null && cellmenu != null && !"".equals(cellmenu)) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userid", userid);
			param.put("cellmenu", cellmenu);
			List<Cellinfo> list = cellinfoDao.queryByStatment("queryCellinfo", param, null);
			return list;
		}
		return null;
	}



}
