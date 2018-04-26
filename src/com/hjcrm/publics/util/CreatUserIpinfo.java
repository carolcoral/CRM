package com.hjcrm.publics.util;

import java.io.UnsupportedEncodingException;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import com.hjcrm.publics.logs.entity.Userlogs;



/**
 * 组装IP工具类
 * @author likang 
 * 2016年10月12日10:28:49
 */
public class CreatUserIpinfo {

	public static Userlogs createUserLogsInfo(HttpServletRequest request, Long userid,String nickname) {
		String country = "";
		String area = "";
		String region = "";
		String county = "";
		String isp = "";
		String city = "";
		Userlogs userLogs = new Userlogs();
		String ip = CusAccessObjectUtil.getIpAddress(request);
		if (ip != null && !"".equals(ip)) {
			String address = "";
			try {
				address = AddressUtils.getAddresses("ip=" + ip, "utf-8");
			} catch (UnsupportedEncodingException e1) {
				e1.printStackTrace();
			}
			if (address != null && !"".equals(address)) {
				try {
					Map<String, Object> mapio = JackSonUtils.jsonToObject(address);
					if (mapio != null) {
						String date = (String) mapio.get("data");
						Map<String, Object> mapinfo = JackSonUtils.jsonToObject(date);
						if (mapinfo != null) {
							country = mapinfo.get("country").toString();
							area = mapinfo.get("area").toString();
							region = mapinfo.get("region").toString();
							county = mapinfo.get("county").toString();
							isp = mapinfo.get("isp").toString();
							city = mapinfo.get("city").toString();
							userLogs.setIpcountry(country);
							userLogs.setIparea(area);
							userLogs.setIpregion(region);
							userLogs.setIpcity(city);
							userLogs.setIpcounty(county);
							userLogs.setIpisp(isp);
							return userLogs;
						}
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		return null;
	}
	
}
