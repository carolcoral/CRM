package com.hjcrm.system.service;

import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.system.entity.User;


/**
 * 用户管理模块接口
 * @author likang
 * @date 2016-10-17 上午11:16:19
 */
public interface IUserService {

	/**
	 * 保存或者修改用户
	 * @param e
	 * @author likang 
	 * @date 2016-10-17 上午11:23:03
	 */
	public void saveOrUpdate(User e);
	
	/**
	 * 删除用户(可批量删除，用,隔开)
	 * @param ids
	 * @author likang 
	 * @date 2016-10-17 上午11:23:15
	 */
	public void delete(String ids);
	
	
	/**
	 * 获取用户列表
	 * @param user
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-10-17 上午11:24:54
	 */
	public List<User> queryUserList(User user,PageBean pageBean);

	/**
	 * 查询用户
	 * @param user_id
	 * @return
	 * @author likang 
	 * @date 2016-10-17 上午11:24:59
	 */
	public User queryByIdentity(Long user_id);
	
	/**
	 * 验证手机号或邮箱是否存在
	 * @param phone
	 * @param email
	 * @return
	 * @author likang 
	 * @date 2016-10-18 下午12:00:04
	 */
	public boolean authPhoneOrEmailIsExist(String phone,String email);
	
	/**
	 * 根据手机号或者邮箱查询用户信息
	 * @param phone 手机号码
	 * @param email 邮箱
	 * @return
	 * @author likang 
	 * @date 2016-10-19 上午10:42:08
	 */
	public User queryUserByPhoneOrEmail(String phone,String email);

	/**
	 * 查询所有运营部人员 + 销售部主管人员
	 * @return
	 * @author likang 
	 * @date 2016-12-27 下午2:07:54
	 */
	public List<User> queryAllDirectors();
	
	/**
	 * 查询所有销售 +行政+客服
	 * @return
	 * @author likang 
	 * @date 2016-12-27 下午2:07:54
	 */
	public List<User> queryAllusers();
	
	
	public User queryUserByUsername(String username);

	public User queryusertestByid(String id);
	
}
