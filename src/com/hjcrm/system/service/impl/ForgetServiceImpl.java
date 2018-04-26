package com.hjcrm.system.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.system.entity.Forget;
import com.hjcrm.system.service.IForgetService;

/**
 * 忘记密码接口实现类
 * @author likang
 * @date 2016-10-19 下午2:30:18
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ForgetServiceImpl implements IForgetService{
	
	@Autowired
	private IDataAccess<Forget> forgetDao;

	/**
	 * 保存忘记密码邮件链接信息
	 */
	
	public void saveForget(Forget forget) {
		forgetDao.insert(forget);
	}

	/**
	 * 修改忘记密码邮件链接信息
	 */
	
	public void updateForget(Forget forget) {
		forgetDao.update(forget);
	}

	/**
	 * 通过用户id或者邮箱查询用户忘记密码链接匹配信息
	 */
	
	public Forget queryForget(Long userid, String email) {
		if (userid != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("userid", userid);
			param.put("email", email);
			List<Forget> list = forgetDao.queryByStatment("queryForget", param, null);
			if (list != null && list.size() > 0) {
				return list.get(0);
			}
		}
		return null;
	}

}
