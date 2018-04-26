package com.hjcrm.system.service;

import com.hjcrm.system.entity.User;


/**
 * CRM系统用户登录接口
 * @author likang
 * @date 2016-10-13 上午11:09:39
 */
public interface IUserLoginService {

	/**
	 * 验证用户手机号/邮箱是否存在
	 * @param phone
	 * @return
	 * @author likang 
	 * @date 2016-10-13 上午11:11:29
	 */
	public User authLoginNameIsExist(String email,String phone); 
	
	/**
	 * 查询密码是否匹配。
	 */
	public boolean isPasswordMatch(Long userid,String password);
	
	
}
