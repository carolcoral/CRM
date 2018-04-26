package com.hjcrm.resource.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ProportionConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.resource.service.IReportService;
import com.hjcrm.resource.util.ReportExcelExportUtil;
import com.hjcrm.system.entity.Course;
import com.hjcrm.system.entity.Subject;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.ICourseService;
import com.hjcrm.system.service.IUserService;
@Controller
public class ReportController extends BaseController{
	
	@Autowired
	private IReportService reportService;
	@Autowired
	private IUserService userService;
	@Autowired
	private ICourseService courseService;

	/**
	 * 总表学员管理(财务部)
	 * 
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/finance/financeStudentMang.do", method = RequestMethod.GET)
	public String financeindex(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/finance/financeStudentMang.do");
			if (isopen) {
				return JumpViewConstants.FINANCE_STUDENT;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 查询总表学员数据--财务部
	 * @param request
	 * @param studentIds
	 * @param roleid
	 * @param deptid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-30 上午9:51:02
	 */
	@RequestMapping(value = "/report/queryStudentscaiwu.do",method = RequestMethod.GET)
	public @ResponseBody String queryStudentscaiwu(HttpServletRequest request,Student student,String studentIds,Long roleid,Long deptid,Long userid,Integer pageSize,Integer currentPage){
		List<Student> list = reportService.queryStudentscaiwu(student,roleid, studentIds, deptid, userid, processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 查询总表学员数据--财务部导出
	 * @param request
	 * @param studentIds
	 * @param roleid
	 * @param deptid
	 * @param userid
	 * @param pageBean
	 * @return
	 * @author likang 
	 * @date 2016-11-30 上午9:51:02
	 */
	@RequestMapping(value = "/report/queryExportStudentscaiwu.do",method = RequestMethod.GET)
	public @ResponseBody String queryExportStudentscaiwu(HttpServletRequest request,Student student,String studentIds,Long roleid,Long deptid,Long userid,Integer pageSize,Integer currentPage){
		List<Student> list = reportService.queryStudentscaiwu(student,roleid, studentIds, deptid, userid, null);
		return jsonToPage(list);
	}
	
	
	/**
	 * 业绩统计
	 * 
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/report/performance.do", method = RequestMethod.GET)
	public String performance(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/report/performance.do");
			if (isopen) {
				return JumpViewConstants.REPORT_PERFORMANCE;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 用户录入统计界面
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/report/userCount.do", method = RequestMethod.GET)
	public String userCount(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/report/userCount.do");
			if (isopen) {
				return JumpViewConstants.REPORT_USERCOUNT;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	
	/**
	 * 查询业绩统计
	 * 	全部业绩
	 * @param request
	 * @param moth 月份数字
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午2:23:29
	 */
	@RequestMapping(value = "/report/queryPerformanceAll.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceAll(HttpServletRequest request,String deptid,String roleid,String userid,Integer pageSize,Integer currentPage ){
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> listall = reportService.queryPerformanceAll();
		map.put("listall", listall);
		return jsonToPage(map);
	}
	
	/**
	 * 查询业绩统计
	 * 	非AC全部业绩
	 * @param request
	 * @param moth 月份数字
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2017年1月9日14:29:13
	 */
	@RequestMapping(value = "/report/queryPerformanceAllnotAC.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceAllnotAC(HttpServletRequest request,String deptid,String roleid,String userid,Integer pageSize,Integer currentPage ){
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> listall = reportService.queryPerformanceAllnotAC();
		map.put("listallnotAC", listall);
		return jsonToPage(map);
	}
	
	/**
	 * 查询业绩统计
	 * 	本月业绩
	 * @param request
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午2:23:29
	 */
	@RequestMapping(value = "/report/queryPerformanceTodayMoth.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceTodayMoth(HttpServletRequest request,String deptid,String roleid,String userid,Integer pageSize,Integer currentPage ){
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> listtodaymoth = reportService.queryPerformanceTodayMoth();
		map.put("listtodaymoth", listtodaymoth);
		return jsonToPage(map);
	}
	
	/**
	 * 查询业绩统计
	 * 	非AC本月业绩
	 * @param request
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang
	 * @date 2017年1月9日14:32:40
	 */
	@RequestMapping(value = "/report/queryPerformanceTodayMothnotAC.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceTodayMothnotAC(HttpServletRequest request,String deptid,String roleid,String userid,Integer pageSize,Integer currentPage ){
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> listtodaymoth = reportService.queryPerformanceTodayMothnotAC();
		map.put("listtodaymothnotAC", listtodaymoth);
		return jsonToPage(map);
	}
	
	/**
	 * 查询业绩统计
	 * 	本周业绩
	 * @param request
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang
	 * @date 2017年1月4日10:01:47
	 */
	@RequestMapping(value = "/report/queryPerformanceTodayWeek.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceTodayWeek(HttpServletRequest request,String deptid,String roleid,String userid,Integer pageSize,Integer currentPage ){
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> listtodayweek = reportService.queryPerformanceTodayWeek();
		map.put("listtodayweek", listtodayweek);
		return jsonToPage(map);
	}
	
	/**
	 * 查询业绩统计
	 * 	非AC本周业绩
	 * @param request
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang
	 * @date 2017年1月4日10:01:47
	 */
	@RequestMapping(value = "/report/queryPerformanceTodayWeeknotAC.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceTodayWeeknotAC(HttpServletRequest request,String deptid,String roleid,String userid,Integer pageSize,Integer currentPage ){
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> listtodayweek = reportService.queryPerformanceTodayWeeknotAC();
		map.put("listtodayweeknotAC", listtodayweek);
		return jsonToPage(map);
	}
	
	/**
	 * 查询业绩统计
	 * 	昨日业绩
	 * @param request
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang
	 * @date 2017年1月4日10:01:47
	 */
	@RequestMapping(value = "/report/queryPerformanceYestoday.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceYestoday(HttpServletRequest request,String deptid,String roleid,String userid,Integer pageSize,Integer currentPage ){
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> listyestoday = reportService.queryPerformanceYestoday();
		map.put("listyestoday", listyestoday);
		return jsonToPage(map);
	}
	
	/**
	 * 查询业绩统计
	 * 	非AC昨日业绩
	 * @param request
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang
	 * @date 2017年1月4日10:01:47
	 */
	@RequestMapping(value = "/report/queryPerformanceYestodaynotAC.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceYestodaynotAC(HttpServletRequest request,String deptid,String roleid,String userid,Integer pageSize,Integer currentPage ){
		Map<String, Object> map = new HashMap<String, Object>();
		List<User> listyestoday = reportService.queryPerformanceYestodaynotAC();
		map.put("listyestodaynotAC", listyestoday);
		return jsonToPage(map);
	}
	
	/**
	 * 查询业绩统计
	 * 	课程业绩
	 * @param request
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午2:23:29
	 */
	@RequestMapping(value = "/report/queryPerformanceCourse.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceCourse(HttpServletRequest request,String courseid,Integer pageSize,Integer currentPage ){
		if (courseid != null && !"".equals(courseid.trim())) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<User> listcourse = reportService.queryPerformanceCourse(courseid);
			map.put("listcourse", listcourse);
			return jsonToPage(map);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 查询业绩统计
	 * 	月份筛选业绩
	 * @param request
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-1 下午2:23:29
	 */
	@RequestMapping(value = "/report/queryPerformanceMoth.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceMoth(HttpServletRequest request,String moth,Integer pageSize,Integer currentPage ){
		if (moth != null && !"".equals(moth.trim())) {
			Map<String, Object> map = new HashMap<String, Object>();
			List<User> listmoth = reportService.queryPerformanceMoth(moth);
			map.put("listmoth", listmoth);
			return jsonToPage(map);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 业绩明细
	 * @param request
	 * @param userName 招生老师姓名
	 * @param userid 招生老师ID
	 * @param startTime 开始成交时间   yyyy-MM-dd
	 * @param endTime 成交结束时间 	 yyyy-MM-dd
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-29 下午4:01:27
	 */
	@RequestMapping(value = "/report/queryPerformanceDetail.do",method = RequestMethod.GET)
	public @ResponseBody String queryPerformanceDetail(HttpServletRequest request,String userName,String userid,String startTime,String endTime,Integer pageSize,Integer currentPage){
		List<User> list = reportService.queryPerformanceDetail(userName,userid,startTime,endTime,null);
		return jsonToPage(list);
	}
	
	
	/**
	 * 业绩明细-导出
	 * @param request
	 * @param userName 招生老师姓名
	 * @param userid 招生老师ID
	 * @param startTime 开始成交时间   yyyy-MM-dd
	 * @param endTime 成交结束时间 	 yyyy-MM-dd
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-29 下午4:01:27
	 */
	@RequestMapping(value = "/report/exportPerformanceDetail.do",method = RequestMethod.POST)
	public @ResponseBody String exportPerformanceDetail(HttpServletRequest request, HttpServletResponse response,String userName,String userid,String startTime,String endTime){

		String[] header =  new String[]{"姓名","提成基础","A面","A网","C面","C网","银行从业","基金串讲班","基金保过班","基金退费班","基金后付费班","基金题库班","中级经济师","会计从业","初级会计","期货从业","证券从业","A真题实战班"} ;
		List<User> list = reportService.queryPerformanceDetail(userName,userid,startTime,endTime,null);
		//写入到excel
		String separator = File.separator;
		String dir = request.getRealPath(separator + "upload");
		try {
			OutputStream out = new FileOutputStream(dir + separator + "业绩排行榜明细情况.xls");
			ReportExcelExportUtil.exportPerformanceDetail("业绩排行榜明细情况", header, list, out);
			// 下载到本地
			out.close();
			String path = request.getSession().getServletContext().getRealPath(separator + "upload" + separator + "业绩排行榜明细情况.xls");
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			java.io.BufferedInputStream bis = null;
			java.io.BufferedOutputStream bos = null;
			String downLoadPath = path;
			try {
				long fileLength = new File(downLoadPath).length();
				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-disposition","attachment; filename=" + new String("业绩排行榜明细情况.xls".getBytes("utf-8"),"ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ReturnConstants.SUCCESS;
	}
	
	/**
	 * 用户录入统计
	 * 全部
	 * @param request
	 * @param countTime
	 * @param userid
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-12-12 上午9:38:43
	 */
	@RequestMapping(value = "/report/queryUserAddCountAll.do",method = RequestMethod.GET)
	public @ResponseBody String queryUserAddCountAll(HttpServletRequest request,String countTime,Long userid,Long deptid,Integer pageSize,Integer currentPage){
		if (userid != null && deptid != null) {
			List<User> listall = reportService.queryUserAddCountAll(processPageBean(pageSize, currentPage));//全部
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listall", listall);
			return jsonToPage(map);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 用户录入统计
	 * 本月
	 * @param request
	 * @param countTime
	 * @param userid
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-12-12 上午9:38:43
	 */
	@RequestMapping(value = "/report/queryUserAddCountMoth.do",method = RequestMethod.GET)
	public @ResponseBody String queryUserAddCountMoth(HttpServletRequest request,String countTime,Long userid,Long deptid,Integer pageSize,Integer currentPage){
		if (userid != null && deptid != null) {
			List<User> listmoth = reportService.queryUserAddCountMoth(processPageBean(pageSize, currentPage));//本月
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listmoth", listmoth);
			return jsonToPage(map);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 用户录入统计
	 * 本周
	 * @param request
	 * @param countTime
	 * @param userid
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-12-12 上午9:38:43
	 */
	@RequestMapping(value = "/report/queryUserAddCountWeek.do",method = RequestMethod.GET)
	public @ResponseBody String queryUserAddCountWeek(HttpServletRequest request,Long userid,Long deptid,Integer pageSize,Integer currentPage){
		if (userid != null && deptid != null) {
			List<User> listWeek = reportService.queryUserAddCountWeek(processPageBean(pageSize, currentPage));//本周
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listWeek", listWeek);
			return jsonToPage(map);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 用户录入统计
	 * 今日
	 * @param request
	 * @param countTime
	 * @param userid
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-12-12 上午9:38:43
	 */
	@RequestMapping(value = "/report/queryUserAddCountToday.do",method = RequestMethod.GET)
	public @ResponseBody String queryUserAddCountToday(HttpServletRequest request,String countTime,Long userid,Long deptid,Integer pageSize,Integer currentPage){
		if (userid != null && deptid != null) {
			List<User> listtoday = reportService.queryUserAddCountToday(countTime,processPageBean(pageSize, currentPage));//今日
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listtoday", listtoday);
			return jsonToPage(map);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 用户录入统计
	 * 按照时间条件进行选取
	 * @param request
	 * @param countTime
	 * @param userid
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-12-12 上午9:38:43
	 */
	@RequestMapping(value = "/report/queryUserAddCountTime.do",method = RequestMethod.GET)
	public @ResponseBody String queryUserAddCountTime(HttpServletRequest request,String countTime,Long userid,Long deptid,Integer pageSize,Integer currentPage){
		if (userid != null && deptid != null && countTime != null && !"".equals(countTime.trim())) {
			List<User> listtoday = reportService.queryUserAddCountTime(countTime,processPageBean(pageSize, currentPage));//按照时间条件进行选取
			Map<String, Object> map = new HashMap<String, Object>();
			map.put("listtime", listtoday);
			return jsonToPage(map);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	public static String[] getHeaders(){
		String[] header =  new String[]{"团队","姓名","录入情况"} ;
		return header;
	}
	
	/**用户录入情况统计
	 * 导出
	 * @param request
	 * @param response
	 * @param sign 0 全部导出 1本月 2今日 3按照时间筛选 4本周
	 * @param countTime 时间筛选
	 * @return
	 * @author likang 
	 * @date 2016-12-13 下午4:20:53
	 */
	@RequestMapping(value = "/report/excelExportReportAddCount.do",method = RequestMethod.POST)
	public @ResponseBody String excelExportReportAddCount(HttpServletRequest request, HttpServletResponse response,String sign,String countTime){
		String[] header = getHeaders();//表头
		List<User> list = null;
		if (sign != null && !"".equals(sign.trim())&&"0".equals(sign.trim())) {
			list = reportService.queryUserAddCountAll(null);//全部
		}else if(sign != null && !"".equals(sign.trim())&&"1".equals(sign.trim())){
			list = reportService.queryUserAddCountMoth(null);//本月
		}else if(sign != null && !"".equals(sign.trim())&&"2".equals(sign.trim())){
			list = reportService.queryUserAddCountToday(null,null);//今日
		}else if(sign != null && !"".equals(sign.trim())&&"3".equals(sign.trim())){
			list = reportService.queryUserAddCountTime(countTime,null);//按照时间条件进行选取
		}else if(sign != null && !"".equals(sign.trim())&&"4".equals(sign.trim())){
			list =  reportService.queryUserAddCountWeek(null);//本周
		}
		//写入到excel
		String separator = File.separator;
		String dir = request.getRealPath(separator + "upload");
		try {
			OutputStream out = new FileOutputStream(dir + separator + "用户录入情况统计.xls");
			ReportExcelExportUtil.exportExcel("用户录入情况统计", header, list, out);
			// 下载到本地
			out.close();
			String path = request.getSession().getServletContext().getRealPath(separator + "upload" + separator + "用户录入情况统计.xls");
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			java.io.BufferedInputStream bis = null;
			java.io.BufferedOutputStream bos = null;
			String downLoadPath = path;
			try {
				long fileLength = new File(downLoadPath).length();
				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-disposition","attachment; filename=" + new String("用户录入情况统计.xls".getBytes("utf-8"),"ISO8859-1"));
				response.setHeader("Content-Length", String.valueOf(fileLength));
				bis = new BufferedInputStream(new FileInputStream(downLoadPath));
				bos = new BufferedOutputStream(response.getOutputStream());
				byte[] buff = new byte[2048];
				int bytesRead;
				while (-1 != (bytesRead = bis.read(buff, 0, buff.length))) {
					bos.write(buff, 0, bytesRead);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				if (bis != null)
					bis.close();
				if (bos != null)
					bos.close();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return ReturnConstants.SUCCESS;
		
	}
	
	
	
	/**
	 * 提成总表跳转界面
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/commission/commissionAll.do", method = RequestMethod.GET)
	public String commission(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/commission/commissionAll.do");
			if (isopen) {
				return JumpViewConstants.REPORT_COMMISSION_ALL;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 提成计算【基金-客服】跳转界面
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/commission/commissionJJKF.do", method = RequestMethod.GET)
	public String commissionJJKF(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/commission/commissionJJKF.do");
			if (isopen) {
				return JumpViewConstants.REPORT_COMMISSION_JJKF;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 提成计算【市场-机构】跳转界面
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/commission/commissionSCJG.do", method = RequestMethod.GET)
	public String commissionSCJG(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/commission/commissionSCJG.do");
			if (isopen) {
				return JumpViewConstants.REPORT_COMMISSION_SCJG;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 提成总表查询数据
	 * @param request
	 * @param userid 当前登录用户id
	 * @param deptgroup 当前登录用户部门大类
	 * @param roleid 角色ID
	 * @param dealStartTime 成交开始日期
	 * @param dealEndTime 成交结束日期
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-19 下午4:13:54
	 */
	@RequestMapping(value = "/commission/queryCommissionAll.do",method =RequestMethod.GET)
	public @ResponseBody String queryCommissionAll(HttpServletRequest request,Long userid,Long deptgroup,Long roleid,String dealStartTime,String dealEndTime,Integer pageSize, Integer currentPage){
		if (userid != null && roleid != null && deptgroup != null) {
			List<Student> list = reportService.queryCommissionAll(userid, deptgroup, roleid, dealStartTime, dealEndTime, processPageBean(pageSize, currentPage));
			
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Integer courseid = list.get(i).getCourseid();//课程ID
					double dealpriceSum = 0;//成交金额
					double dealprice = new Double((list.get(i).getDealprice() != null && !"".equals(list.get(i).getDealprice().trim()))?list.get(i).getDealprice():"0");//成交金额
					if (courseid != null) {
						Subject subject = courseService.querySubjectByCourseId(courseid);
						if (subject != null) {
							list.get(i).setCourseName(subject.getCoursename());
							list.get(i).setSubjectname(subject.getSubjectname());
						}
					}
					if (courseid != null && list.get(i).getDeptgroupName() != null) {//部门大类：0市场部 、1基金销售、 2机构客户部、 3重庆代理1、4重庆代理2 、5西安代理 、6南京代理1、 7南京代理2、8苏州代理、9泰州代理、10长春代理 、11太原代理
						List<Student> dealpricelist = reportService.querydealpriceSum(list.get(i).getDeptgroupName(),list.get(i).getBelongid(), courseid);
						if (dealpricelist != null && dealpricelist.size() > 0) {
							dealpriceSum = dealpricelist.get(0).getDealpriceSum();
						}
						if ("0".equals(list.get(i).getDeptgroupName())) {
							list.get(i).setDeptgroupName("市场部");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								if (dealpriceSum < 40000) {//小于4万
									list.get(i).setProportion(ProportionConstants.SCB_0_4_CFP_OR_OTHER);
								}else if(dealpriceSum >= 40000 && dealpriceSum < 80000){//4万-8万
									list.get(i).setProportion(ProportionConstants.SCB_4_8_CFP_OR_OTHER);
								}else if(dealpriceSum >= 80000){//大于等于8万
									list.get(i).setProportion(ProportionConstants.SCB_8_MORE_CFP_OR_OTHER);
								}
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									if (dealpriceSum < 40000) {//小于4万
										list.get(i).setProportion(ProportionConstants.SCB_0_4_AFP_PASSBEFORE);
									}else if(dealpriceSum >= 40000 && dealpriceSum < 80000){//4万-8万
										list.get(i).setProportion(ProportionConstants.SCB_4_8_AFP_PASSBEFORE);
									}else if(dealpriceSum >= 80000){//大于等于8万
										list.get(i).setProportion(ProportionConstants.SCB_8_MORE_AFP_PASSBEFORE);
									}
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									if (dealpriceSum < 40000) {//小于4万
										list.get(i).setProportion(ProportionConstants.SCB_0_4_AFP_PASSAFTER);
									}else if(dealpriceSum >= 40000 && dealpriceSum < 80000){//4万-8万
										list.get(i).setProportion(ProportionConstants.SCB_4_8_AFP_PASSAFTER);
									}else if(dealpriceSum >= 80000){//大于等于8万
										list.get(i).setProportion(ProportionConstants.SCB_8_MORE_AFP_PASSAFTER);
									}
								}
							}	
						}else if("1".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("基金销售");
							if (courseid != null && courseid > 4) {//非AC项目
								if (dealpriceSum < 30000) {//X＜3万
									list.get(i).setProportion(ProportionConstants.JJKF_0_3_FAC);
								}else if(dealpriceSum >= 30000 && dealpriceSum < 60000){//3万≤X＜6万
									list.get(i).setProportion(ProportionConstants.JJKF_3_6_FAC);
								}else if(dealpriceSum >= 60000 && dealpriceSum < 90000){//6万≤X＜9万
									list.get(i).setProportion(ProportionConstants.JJKF_6_9_FAC);
								}else if(dealpriceSum >= 90000){//X≥9万
									list.get(i).setProportion(ProportionConstants.JJKF_9_MORE_FAC);
								}
							}else if(courseid != null && (courseid == 2 || courseid == 3 || courseid == 4 )){//CFP+AFP网络
								if (dealpriceSum < 30000) {//X＜3万
									list.get(i).setProportion(ProportionConstants.JJKF_0_3_CFP_AFPW);
								}else if(dealpriceSum >= 30000 && dealpriceSum < 60000){//3万≤X＜6万
									list.get(i).setProportion(ProportionConstants.JJKF_3_6_CFP_AFPW);
								}else if(dealpriceSum >= 60000 && dealpriceSum < 90000){//6万≤X＜9万
									list.get(i).setProportion(ProportionConstants.JJKF_6_9_CFP_AFPW);
								}else if(dealpriceSum >= 90000){//X≥9万
									list.get(i).setProportion(ProportionConstants.JJKF_9_MORE_CFP_AFPW);
								}
								
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									if (dealpriceSum < 30000) {//X＜3万
										list.get(i).setProportion(ProportionConstants.JJKF_0_3_AFPM_PASSBEFORE);
 									}else if(dealpriceSum >= 30000 && dealpriceSum < 60000){//3万≤X＜6万
 										list.get(i).setProportion(ProportionConstants.JJKF_3_6_AFPM_PASSBEFORE);
 									}else if(dealpriceSum >= 60000 && dealpriceSum < 90000){//6万≤X＜9万
 										list.get(i).setProportion(ProportionConstants.JJKF_6_9_AFPM_PASSBEFORE);
 									}else if(dealpriceSum >= 90000){//X≥9万
 										list.get(i).setProportion(ProportionConstants.JJKF_9_MORE_AFPM_PASSBEFORE);
 									}
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									if (dealpriceSum < 30000) {//X＜3万
										list.get(i).setProportion(ProportionConstants.JJKF_0_3_AFPM_PASSAFTER);
 									}else if(dealpriceSum >= 30000 && dealpriceSum < 60000){//3万≤X＜6万
 										list.get(i).setProportion(ProportionConstants.JJKF_3_6_AFPM_PASSAFTER);
 									}else if(dealpriceSum >= 60000 && dealpriceSum < 90000){//6万≤X＜9万
 										list.get(i).setProportion(ProportionConstants.JJKF_6_9_AFPM_PASSAFTER);
 									}else if(dealpriceSum >= 90000){//X≥9万
 										list.get(i).setProportion(ProportionConstants.JJKF_9_MORE_AFPM_PASSAFTER);
 									}
								}
							}
							
						}else if("2".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("机构客户部");
						}else if("3".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("重庆代理1");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_CHONGQING1_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_CHONGQING1_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_CHONGQING1_AFPM_PASSAFTER);
								}
							}
						}else if("4".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("重庆代理2");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_CHONGQING2_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_CHONGQING2_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_CHONGQING2_AFPM_PASSAFTER);
								}
							}
						}else if("5".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("西安代理");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_XIAN_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_XIAN_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_XIAN_AFPM_PASSAFTER);
								}
							}
						}else if("6".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("南京代理1");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_NANJING1_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_NANJING1_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_NANJING1_AFPM_PASSAFTER);
								}
							}
						}else if("7".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("南京代理2");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_NANJING2_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_NANJING2_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_NANJING2_AFPM_PASSAFTER);
								}
							}
						}else if("8".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("苏州代理");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_SUZHOU_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_SUZHOU_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_SUZHOU_AFPM_PASSAFTER);
								}
							}
						}else if("9".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("泰州代理");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_TAIZHOU_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_TAIZHOU_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_TAIZHOU_AFPM_PASSAFTER);
								}
							}
						}else if("10".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("长春代理");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_CHANGCHUN_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_CHANGCHUN_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_CHANGCHUN_AFPM_PASSAFTER);
								}
							}
						}else if("11".equals(list.get(i).getDeptgroupName())){
							list.get(i).setDeptgroupName("太原代理");
							if (courseid != null && courseid > 1 ) {//CFP及其他科目
								list.get(i).setProportion(ProportionConstants.DL_TAIYUAN_CFP_OR_OTHER);
							}else if(courseid != null && courseid == 1){//AFP面授
								if (list.get(i).getIspass() != null && ( list.get(i).getIspass() == 0 ||  list.get(i).getIspass() == 1)) {//AFP面授---通过前
									list.get(i).setProportion(ProportionConstants.DL_TAIYUAN_AFPM_PASSBEFORE);
								}else if(list.get(i).getIspass() != null && list.get(i).getIspass() == 2){//AFP面授---通过后
									list.get(i).setProportion(ProportionConstants.DL_TAIYUAN_AFPM_PASSAFTER);
								}
							}
						}
						 DecimalFormat df1 = new DecimalFormat("##.00");
						list.get(i).setShouldpayMoney(new Double(df1.format(dealprice*list.get(i).getProportion())));//应发提成金额 = 我司收入*提成比例
						if (list.get(i).getUserstate() != null && list.get(i).getUserstate() == 0) {
							list.get(i).setActualpayMoney(list.get(i).getShouldpayMoney());//实发提成金额:1、正常=应发金额
						}else{
							list.get(i).setActualpayMoney(0);//实发提成金额:2、离职员工此处显示为0
						}
					}
				}
			}
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 提成计算【市场-机构】查询数据
	 * @param request
	 * @param userid 当前登录用户id
	 * @param deptgroup 当前登录用户部门大类
	 * @param roleid 角色ID
	 * @param dealStartTime 成交开始日期
	 * @param dealEndTime 成交结束日期
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-19 下午4:13:54
	 */
	@RequestMapping(value = "/commission/queryCommissionscjg.do",method =RequestMethod.GET)
	public @ResponseBody String queryCommissionscjg(HttpServletRequest request,Long userid,Long deptgroup,Long roleid,String dealStartTime,String dealEndTime,Integer pageSize, Integer currentPage){
		if (userid != null && roleid != null && deptgroup != null) {
			
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 提成计算【基金-客服】查询数据
	 * @param request
	 * @param userid 当前登录用户id
	 * @param deptgroup 当前登录用户部门大类
	 * @param roleid 角色ID
	 * @param dealStartTime 成交开始日期
	 * @param dealEndTime 成交结束日期
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-12-19 下午4:13:54
	 */
	@RequestMapping(value = "/commission/queryCommissionjjkf.do",method =RequestMethod.GET)
	public @ResponseBody String queryCommissionjjkf(HttpServletRequest request,Long userid,Long deptgroup,Long roleid,String dealStartTime,String dealEndTime,Integer pageSize, Integer currentPage){
		if (userid != null && roleid != null && deptgroup != null) {
			
		}
		return ReturnConstants.PARAM_NULL;
	}

	
	
	
	
	
	
	
}
