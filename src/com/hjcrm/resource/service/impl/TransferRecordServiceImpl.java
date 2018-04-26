package com.hjcrm.resource.service.impl;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hjcrm.publics.dao.IDataAccess;
import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.resource.entity.Transferrecord;
import com.hjcrm.resource.service.ITransferRecordService;

/**
 * 转移记录
 * @author likang
 * @date 2016-12-21 下午1:58:10
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class TransferRecordServiceImpl implements ITransferRecordService {
	
	@Autowired
	private IDataAccess<Transferrecord> transferrecordDao;

	/**
	 * 保存转移记录
	 */
	
	public void saveTransferRecord(Transferrecord transferrecord) {
		if (transferrecord != null) {
			transferrecord.setOperationId(UserContext.getLoginUser().getUserid());
			transferrecord.setOperationName(UserContext.getLoginUser().getUsername());
			transferrecordDao.insert(transferrecord);
		}
	}

	/**
	 * 查询转移记录
	 */
	
	public List<Transferrecord> queryTransferrecord(Long deptid,String phone, String tel,PageBean pageBean) {
		Map<String, Object> param = new HashMap<String, Object>();
		param.put("phone", phone);
		param.put("tel", tel);
		param.put("deptid", deptid);
		List<Transferrecord> list = transferrecordDao.queryByStatment("queryTransferrecord", param, pageBean);
		return list;
	}

	/**
	 * 转移记录筛选
	 */
	
	public List<Transferrecord> queryTransferRecordBysceen(Transferrecord transferrecord, Long deptid,String transferrecords, PageBean processPageBean) {
		if (transferrecord != null) {
			Map<String, Object> param = new HashMap<String, Object>();
			param.put("operationId", transferrecord.getOperationId());
			param.put("createStarttime", transferrecord.getCreateStarttime());
			param.put("createEndtime", transferrecord.getCreateEndtime());
			param.put("transferrecords", transferrecords);
			List<Transferrecord> list = transferrecordDao.queryByStatment("queryTransferRecordBysceen", param, processPageBean);
			return list;
		}
		return null;
	}

}
