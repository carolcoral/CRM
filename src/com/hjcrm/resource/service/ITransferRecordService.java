package com.hjcrm.resource.service;

import java.util.List;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.resource.entity.Transferrecord;


/**
 * 转移记录接口
 * @author likang
 * @date 2016-12-21 下午1:55:50
 */
public interface ITransferRecordService {
	
	/**
	 * 保存转移记录
	 * @param transferrecord
	 * @author likang 
	 * @date 2016-12-21 下午1:56:27
	 */
	public void saveTransferRecord(Transferrecord transferrecord);
	
	/**
	 * 查询转移记录
	 * @param phone
	 * @param tel
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-12-21 下午1:57:14
	 */
	public List<Transferrecord> queryTransferrecord(Long deptid,String phone,String tel,PageBean pageBean);

	/**
	 * 转移记录筛选
	 * @param transferrecord
	 * @param deptid
	 * @param processPageBean
	 * @return
	 * @author likang 
	 * @date 2016-12-27 上午11:01:35
	 */
	public List<Transferrecord> queryTransferRecordBysceen(Transferrecord transferrecord, Long deptid, String transferrecords,PageBean processPageBean);

}
