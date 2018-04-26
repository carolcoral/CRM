package com.hjcrm.system.service.impl;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.MD5Tools;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IUserService;


@Service
@Transactional(rollbackFor = Exception.class)
public class UserServiceImpl implements IUserService{
	
	@Autowired
	private IDataAccess<User> userDao;

	/**
	 * 保存或者修改用户信息
	 */
	@CacheEvict(value = "baseCache",key = "#e.userid")
	public void saveOrUpdate(User e) {
		if (e.getUserid()!= null) {//修改
			if(StringUtils.hasText(e.getPassword())){				
				User oldUser = (User) userDao.queryByIdentity(User.class, e.getUserid());
				if(oldUser != null && StringUtils.hasText(oldUser.getPassword())){
					if(!oldUser.getPassword().equals(e.getPassword())){
						e.setPassword(MD5Tools.encode(e.getPassword()));
					}
				}else{
					e.setPassword(MD5Tools.encode(e.getPassword()));
				}
			}
		
			e.setUpdate_time(new Timestamp(System.currentTimeMillis()));
			if((UserContext.getLoginUser() != null)){
				e.setUpdate_id(UserContext.getLoginUser().getUserid());
			}			
			userDao.update(e);
		} else {//新增
			if (StringUtils.hasText(e.getPassword())){
				e.setPassword(MD5Tools.encode(e.getPassword()));
			}else{
				e.setPassword(MD5Tools.encode("123123"));
			}
			e.setCreate_time(new Timestamp(System.currentTimeMillis()));
			if(UserContext.getLoginUser() != null){
				e.setCreate_id(UserContext.getLoginUser().getUserid());
			}
			userDao.insert(e);
		}
		
		
	}

	/**
	 * 删除用户
	 */
	
	public void delete(String ids) {
		User ue = new User();
		for (String id : ids.split(",")) {
			ue.setUserid(Long.valueOf(id));
			userDao.delete(ue);
		}
	}


	/**
	 * 获取用户列表
	 */
	
	public List<User> queryUserList(User user, PageBean pageBean) {
		Map<String , Object> param = new HashMap<String, Object>();
		if (user != null) {
			param.put("phone", user.getPhone());
			param.put("email", user.getEmail());
		}
		return userDao.queryByStatment("queryUserList", user, pageBean);
	}

	/**
	 * 根据用户ID，查询用户信息
	 */
	@Cacheable(value = "baseCache",key = "#user_id+'queryByIdentity'")
	
	public User queryByIdentity(Long user_id) {
		List<User> users = userDao.queryByStatment("queryByIdentity", user_id,null);
		if (users != null && users.size() > 0) {
			return users.get(0);
		}
		return null;
	}

	/**
	 * 验证手机号或邮箱是否存在
	 */
	
	public boolean authPhoneOrEmailIsExist(String phone, String email) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phone", phone);
		hashMap.put("eamil", email);
		List<User> list = userDao.queryByStatment("authPhoneOrEmailIsExist", hashMap, null);
		if(list.size() > 0){
			return true;
		}
		return false;
	}

	/**
	 * 根据手机号或者邮箱查询用户信息
	 */
	
	public User queryUserByPhoneOrEmail(String phone, String email) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("phone", phone);
		hashMap.put("email", email);
		List<User> list = userDao.queryByStatment("authPhoneOrEmailIsExist", hashMap, null);
		if (list != null && list.size() > 0 ) {
			return list.get(0);
		}
		return null;
	}

	/**
	 * 查询所有运营部人员 + 销售部主管人员
	 */
	
	public List<User> queryAllDirectors() {
		List<User> list = userDao.queryByStatment("queryAllDirectors", null, null);
		return list;
	}

	/**
	 * 查询所有销售 +行政+客服
	 */
	
	public List<User> queryAllusers() {
		List<User> list = userDao.queryByStatment("queryAllusers", null, null);
		return list;
	}

	
	public User queryUserByUsername(String username) {
		HashMap<String, Object> hashMap = new HashMap<String, Object>();
		hashMap.put("username", username);
		List<User> list = userDao.queryByStatment("queryUserByUsername", hashMap, null);
		if (list != null && list.size() > 0 ) {
			return list.get(0);
		}
		return null;
	}

	
	public User queryusertestByid(String id) {
		List<User> list = userDao.queryByStatment("queryUserByUsername",  null, null);
		if (list != null && list.size() > 0 ) {
			return list.get(0);
		}
		return null;
	}

}
