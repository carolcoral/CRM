package com.hjcrm.resource.util;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 生成单据号工具类
 * 
 * @author likang
 * @date 2016-11-1 下午5:10:49
 */
public class UuidCodeUtil {

	private static String dateValue = "20161101";

	public synchronized static String getOrderIdByUUId() {
		long No = 0;
		SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
		String nowdate = sdf.format(new Date());
		No = Long.parseLong(nowdate);
		if (!(String.valueOf(No)).equals(dateValue)) {
			dateValue = String.valueOf(No);
		}
		String num = String.valueOf(No);
		int machineId = 1;// 最大支持1-9个集群部署
		int hashCodeV = UUID.randomUUID().toString().hashCode();
		if (hashCodeV < 0) {
			hashCodeV = -hashCodeV;
		}
		// 0 代表前面补充0
		// 15 代表长度
		// d 代表参数为正数型
		return num + machineId + String.format("%020d", hashCodeV);
	}

}
