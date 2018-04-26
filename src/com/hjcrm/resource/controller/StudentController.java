package com.hjcrm.resource.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hjcrm.publics.constants.JumpViewConstants;
import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.constants.StateConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.publics.util.JackSonUtils;
import com.hjcrm.publics.util.UserContext;
import com.hjcrm.publics.websocket.entity.WebSocketNeedBean;
import com.hjcrm.resource.entity.Dealrecord;
import com.hjcrm.resource.entity.Matchinfo;
import com.hjcrm.resource.entity.Resource;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.resource.entity.Visitrecord;
import com.hjcrm.resource.service.IMatchinfoService;
import com.hjcrm.resource.service.IResourceService;
import com.hjcrm.resource.service.IStudentService;
import com.hjcrm.resource.util.ExcelExportUtil;
import com.hjcrm.resource.util.ExcelReaderUtil;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IUserService;

/**
 * 学员管理
 * @author likang
 * @date 2016-11-7 下午3:34:41
 */
@Controller
public class StudentController extends BaseController {
	
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IMatchinfoService matchinfoService;
	@Autowired
	private IResourceService resourceService;
	@Autowired
	private IUserService userService;
	
	
	/**
	 * 跳转学员管理(运营部)
	 * 
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/student/studentMang.do", method = RequestMethod.GET)
	public String index(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/student/studentMang.do");
			if (isopen) {
				return JumpViewConstants.STUDENT_YUNYING;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}

	/**
	 * 跳转学员管理(销售部)
	 * 
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/sales/studentMang.do", method = RequestMethod.GET)
	public String salesindex(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/sales/studentMang.do");
			if (isopen) {
				return JumpViewConstants.STUDENT_XIAOSHOU;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 跳转学员管理（行政部）
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-11-18 上午9:44:50
	 */
	@RequestMapping(value = "/executive/studentMang.do", method = RequestMethod.GET)
	public String executiveIndex(Model model){
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/executive/studentMang.do");
			if (isopen) {
				return JumpViewConstants.EXECUTIVE;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 跳转匹配到账界面（行政部）
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-11-18 上午9:44:50
	 */
	@RequestMapping(value = "/executive/matchMoney.do", method = RequestMethod.GET)
	public String matchMoneyIndex(Model model){
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/executive/matchMoney.do");
			if (isopen) {
				return JumpViewConstants.EXECUTIVE_MATCHMONEY;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 跳转到账学员总表界面（行政部）
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-11-18 上午9:44:50
	 */
	@RequestMapping(value = "/executive/accountStudent.do", method = RequestMethod.GET)
	public String accountStudentIndex(Model model){
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/executive/accountStudent.do");
			if (isopen) {
				return JumpViewConstants.EXECUTIVE_ACCOUNTSTUDENT;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 跳转网络培训费界面（行政部）
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-11-18 上午9:44:50
	 */
	@RequestMapping(value = "/executive/networkTrain.do", method = RequestMethod.GET)
	public String networkTrainIndex(Model model){
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/executive/networkTrain.do");
			if (isopen) {
				return JumpViewConstants.EXECUTIVE_NETWORKTRAIN;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 跳转学员关课界面（行政部）
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-11-18 上午9:44:50
	 */
	@RequestMapping(value = "/executive/colseCourse.do", method = RequestMethod.GET)
	public String colseCourseIndex(Model model){
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/executive/colseCourse.do");
			if (isopen) {
				return JumpViewConstants.EXECUTIVE_COLSECOURSE;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	/**
	 * 跳转在线购买界面（行政部）
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-11-18 上午9:44:50
	 */
	@RequestMapping(value = "/executive/buyOnline.do", method = RequestMethod.GET)
	public String buyOnlineIndex(Model model){
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/executive/buyOnline.do");
			if (isopen) {
				return JumpViewConstants.EXECUTIVE_BUYONLINE;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	

	/**
	 * 学员详细信息
	 * @return
	 * @author likang 
	 * @date 2016-11-16 上午11:24:57
	 */
	@RequestMapping(value = "/student/studentDetails.do", method = RequestMethod.GET)
	public String executiveDetails(Model model,Long studentId,Long deptid,Long roleid,Long userid){
		if (studentId != null) {
			List<Student> liststudent = studentService.queryStudents(null,String.valueOf(studentId), deptid,roleid,userid, null);//查询学员信息-回访信息-成交记录信息
			if (liststudent != null && liststudent.size() > 0) {
				model.addAttribute("liststudent", liststudent);
				model.addAttribute("record", liststudent.get(0).getVisitrecord());//回访记录信息;
				model.addAttribute("dealrecord", liststudent.get(0).getDealrecord());//成交记录信息;
			}else{
				model.addAttribute("liststudent", null);
				model.addAttribute("record", null);
				model.addAttribute("dealrecord", null);
			}
		}
		if (deptid != null && (deptid.longValue() == StateConstants.DEPT_XIAOSHOU.longValue()||deptid.longValue() == StateConstants.DEPT_AC.longValue()
				||deptid.longValue() == StateConstants.DEPT_FAC.longValue()||deptid.longValue() == StateConstants.DEPT_CHAOJIZD.longValue()
				||deptid.longValue() == StateConstants.DEPT_WUDIZD.longValue()||deptid.longValue() == StateConstants.DEPT_LEITINGZD.longValue()
				||deptid.longValue() == StateConstants.DEPT_TONGLUZD.longValue()||deptid.longValue() == StateConstants.DEPT_PHONEZD.longValue()
				||deptid.longValue() == StateConstants.DEPT_XXRZD.longValue()||deptid.longValue() == StateConstants.DEPT_HJJZD.longValue()
				||deptid.longValue() == StateConstants.DEPT_CLZD.longValue())
				|| deptid.longValue() == StateConstants.DEPT_NJDL.longValue()|| deptid.longValue() == StateConstants.DEPT_SZDL.longValue()
					|| deptid.longValue() == StateConstants.DEPT_XADL.longValue()|| deptid.longValue() == StateConstants.DEPT_CQDL.longValue()
					|| deptid.longValue() == StateConstants.DEPT_XJDL.longValue()|| deptid.longValue() == StateConstants.DEPT_TZDL.longValue()
					|| deptid.longValue() == StateConstants.DEPT_GXKFB.longValue()|| deptid.longValue() == StateConstants.DEPT_JGKHZ || deptid.longValue() == StateConstants.DEPT_ZONGBU
				) {//销售
			return JumpViewConstants.STUDENT_DETAIL;
		}else if(deptid != null && deptid.longValue() == StateConstants.DEPT_XINGZHENG.longValue()|| deptid.longValue() == StateConstants.DEPT_ZONGBU.longValue()){//行政
			return JumpViewConstants.STUDENT_EXEXUTIVE;
		}else if(deptid != null && (deptid.longValue() == StateConstants.DEPT_KEFU.longValue() || deptid.longValue() == StateConstants.DEPT_AFPZLH.longValue()
				 || deptid.longValue() == StateConstants.DEPT_AFPXC.longValue() || deptid.longValue() == StateConstants.DEPT_CFPWYL.longValue()
						 || deptid.longValue() == StateConstants.DEPT_FACLWH.longValue())){//客服
			return JumpViewConstants.STUDENT_ADDDETAILS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 行政部-到账学员总表详情页
	 * @param model
	 * @param studentId
	 * @param deptid
	 * @param roleid
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-11-29 下午5:57:03
	 */
	@RequestMapping(value = "/student/accountDetails.do", method = RequestMethod.GET)
	public String accountDetails(Model model,Long studentId,Long deptid,Long roleid,Long userid){
		if (studentId != null) {
			List<Student> liststudent = studentService.queryStudents(null,String.valueOf(studentId), roleid,userid,null, null);//查询学员信息-回访信息-成交记录信息
			if (liststudent != null && liststudent.size() > 0) {
				model.addAttribute("liststudent", liststudent);
				model.addAttribute("record", liststudent.get(0).getVisitrecord());//回访记录信息;
				model.addAttribute("dealrecord", liststudent.get(0).getDealrecord());//成交记录信息;
			}else{
				model.addAttribute("liststudent", null);
				model.addAttribute("record", null);
				model.addAttribute("dealrecord", null);
			}
		}
		if(deptid != null && deptid.longValue() == StateConstants.DEPT_XINGZHENG.longValue()|| deptid.longValue() == StateConstants.DEPT_ZONGBU.longValue()){//行政
			return JumpViewConstants.EXECUTIVE_ACCOUNTDETAILS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 查询学员信息(部门通用)
	 * @param request
	 * @param deptid
	 * @param userid
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-7 下午4:14:54
	 */
	@RequestMapping(value = "/student/queryStudents.do",method = RequestMethod.GET)
	public @ResponseBody String queryStudents(HttpServletRequest request,Long deptid,Long roleid,Long userid,Integer pageSize, Integer currentPage){
		if (deptid != null) {
			List<Student> list = null;
			if (deptid.longValue() == StateConstants.DEPT_YUNYING.longValue() || deptid.longValue() == StateConstants.DEPT_XINGZHENG.longValue() || deptid.longValue() == StateConstants.DEPT_ZONGBU.longValue()) {//运营、行政
				list = studentService.queryStudentlist(null,null,deptid, null, processPageBean(pageSize, currentPage));
			}else if(deptid.longValue() == StateConstants.DEPT_XIAOSHOU.longValue()||deptid.longValue() == StateConstants.DEPT_AC.longValue()
					||deptid.longValue() == StateConstants.DEPT_FAC.longValue()||deptid.longValue() == StateConstants.DEPT_CHAOJIZD.longValue()
					||deptid.longValue() == StateConstants.DEPT_WUDIZD.longValue()||deptid.longValue() == StateConstants.DEPT_LEITINGZD.longValue()
					||deptid.longValue() == StateConstants.DEPT_TONGLUZD.longValue()||deptid.longValue() == StateConstants.DEPT_PHONEZD.longValue()
					||deptid.longValue() == StateConstants.DEPT_XXRZD.longValue()||deptid.longValue() == StateConstants.DEPT_HJJZD.longValue()
					||deptid.longValue() == StateConstants.DEPT_CLZD.longValue() || deptid.longValue() == StateConstants.DEPT_KEFU.longValue()
					|| deptid.longValue() == StateConstants.DEPT_AFPZLH.longValue()|| deptid.longValue() == StateConstants.DEPT_AFPXC.longValue()
					|| deptid.longValue() == StateConstants.DEPT_CFPWYL.longValue()|| deptid.longValue() == StateConstants.DEPT_FACLWH.longValue()
					
					|| deptid.longValue() == StateConstants.DEPT_NJDL.longValue()|| deptid.longValue() == StateConstants.DEPT_SZDL.longValue()
					|| deptid.longValue() == StateConstants.DEPT_XADL.longValue()|| deptid.longValue() == StateConstants.DEPT_CQDL.longValue()
					|| deptid.longValue() == StateConstants.DEPT_XJDL.longValue()|| deptid.longValue() == StateConstants.DEPT_TZDL.longValue()
					|| deptid.longValue() == StateConstants.DEPT_GXKFB.longValue() || deptid.longValue() == StateConstants.DEPT_JGKHZ){//销售、客服、代理,机构
				list = studentService.queryStudentlist(roleid,null,deptid, userid, processPageBean(pageSize, currentPage));
			} 
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 根据条件进行查询学员--筛选
	 * @param request
	 * @param resource
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:30:46
	 */
	@RequestMapping(value = "/student/queryStudentBySceen.do",method = RequestMethod.GET)
	public @ResponseBody String queryStudentBySceen(HttpServletRequest request,Long deptid,Long roleid,Long userid,Student student,Integer pageSize,Integer currentPage){
		if (student != null) {
			List<Student> list = studentService.queryStudentBySceen(null,deptid,roleid,userid,student, processPageBean(pageSize, currentPage));
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 获取导出头部信息
	 * @return
	 * @author likang 
	 * @date 2016-11-7 上午9:55:24
	 */
	public static String[] getHeaders(Long deptid){
		String[] header = null;
		if (deptid != null && deptid.longValue() == StateConstants.DEPT_XINGZHENG.longValue()|| deptid.longValue() == StateConstants.DEPT_ZONGBU.longValue()) {//行政部-到账信息
			header = new String[]{"资源分配时间","确认到账时间","姓名","身份证","电话","固定电话","邮箱","单位","地址","职务","毕业院校","学历","是否协助结业","招生老师","代汇款人"
					,"收款金额","收款日期","汇款方式","LCW用户名","LCW密码","课件版本","发票情况","报考密码","协议","班次","期次","优惠信息","课程","科目"//总表导出：期次 后面加一个优惠信息字段
					,"AFP是否通过","投资","保险","税务","福利","综合","我司收入","退费","退费日期","网络培训费","支付日期","审核问题","财务备注"} ;
			
		}else if(deptid != null && deptid.longValue() == StateConstants.DEPT_YUNYING.longValue()){//运营部/学员管理
			header = new String[]{"成交时间","到账时间","所属销售人","学员姓名","渠道","客户手机","报名课程","科目","成交金额"} ;
		}
		return header;
	}
	
	public static String[] getxzHeaders(Long deptid){
		String[] header = null;
		if (deptid != null && deptid.longValue() == StateConstants.DEPT_XINGZHENG.longValue()|| deptid.longValue() == StateConstants.DEPT_ZONGBU.longValue()) {//行政部/网络培训费
			header = new String[]{"确认到账时间","姓名","收款金额","汇款方式","收款日期","身份证","手机","LCW用户名","LCW密码","网络培训费","班级","支付日期","备注"} ;
		}
		return header;
	}
	
	/**
	 * 根据条件进行查询学员--筛选导出
	 * @param request
	 * @param resource
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:30:46
	 */
	@RequestMapping(value = "/student/queryExportStudentBySceen.do",method = RequestMethod.GET)
	public @ResponseBody String queryExportStudentBySceen(HttpServletRequest request,Long deptid,Long roleid,Long userid,Student student,Integer pageSize,Integer currentPage){
		if (student != null) {
			List<Student> list = studentService.queryStudentBySceen(null,deptid,roleid,userid,student, null);
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 学员管理导出
	 * @param request
	 * @param response
	 * @param student
	 * @param studentIds
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-11-11 上午11:00:24
	 */
	@RequestMapping(value = "/student/excelExportStudent.do",method = RequestMethod.POST)
	public @ResponseBody String excelExportStudent(HttpServletRequest request, HttpServletResponse response,Student student,String studentIds,Long roleid,Long userid,Long deptid){
		if (deptid != null) {
			String[] header = getHeaders(deptid);//表头
			//获取导出数据
			List<Student> list = null;
			if (deptid != null && deptid.longValue() == StateConstants.DEPT_YUNYING.longValue()) {
				list = studentService.queryStudentlist(roleid, studentIds, deptid, userid, null);
			}else if(deptid != null && deptid.longValue() == StateConstants.DEPT_XINGZHENG.longValue()){
				list = studentService.queryStudentsExport(roleid, studentIds, deptid, userid, null);
			}
			//写入到excel
			String separator = File.separator;
			String dir = request.getRealPath(separator + "upload");
			try {
				OutputStream out = new FileOutputStream(dir + separator + "学员信息.xls");
				ExcelExportUtil.exportExcelStudent(deptid,"学员信息", header, list, out);
				// 下载到本地
				out.close();
				String path = request.getSession().getServletContext().getRealPath(separator + "upload" + separator + "学员信息.xls");
				response.setContentType("text/html;charset=utf-8");
				request.setCharacterEncoding("UTF-8");
				java.io.BufferedInputStream bis = null;
				java.io.BufferedOutputStream bos = null;
				String downLoadPath = path;
				try {
					long fileLength = new File(downLoadPath).length();
					response.setContentType("application/x-msdownload;");
					response.setHeader("Content-disposition","attachment; filename=" + new String("学员信息.xls".getBytes("utf-8"),"ISO8859-1"));
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
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 学员提交行政部
	 * @param request
	 * @param studentId
	 * @return
	 * @author likang 
	 * @date 2016-11-8 上午9:00:38
	 */
	@RequestMapping(value = "/student/studentCommit.do",method = RequestMethod.POST)
	public @ResponseBody String studentCommit(HttpServletRequest request,String studentIds,String resourceIds){
		if (studentIds != null && !"".equals(studentIds.trim())) {
			//查询学员信息
			List<Student> list = studentService.queryStudents(resourceIds,studentIds, null,null, null, null);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Student student = list.get(i);
					if (student != null) {//判断学员状态是否为已成交，只有已成交和已退回的才可以提交
						if (student.getStudentstate() != StateConstants.studentstate1 && student.getStudentstate() != StateConstants.studentstate8 ) {
							return ReturnConstants.STUDENT_NO_COMMIT;//学员状态存在非已成交和已退回，不能提交行政
						}
					}
				}
				for (int i = 0; i < list.size(); i++) {
					Student student = list.get(i);
					if (student != null) {//判断学员状态是否为已成交，只有已成交和已退回的才可以提交
						if (student.getStudentstate() == StateConstants.studentstate1 || student.getStudentstate() == StateConstants.studentstate8 ) {
							student.setStudentstate(StateConstants.studentstate2);//学员已提交(提交行政)
							student.setCommit_time(new Timestamp(System.currentTimeMillis()));//提交日期
							studentService.saveOrUpdate(student);
						}
					}
				}
				if (resourceIds != null && !"".equals(resourceIds.trim())) {
					studentService.updateResourceStudentstate(resourceIds, StateConstants.studentstate2);
				}
				/*****************************消息推送**************************************/
				sendmessage(WebSocketNeedBean.OBJ_TYPE_LIVE, null,String.valueOf(StateConstants.DEPT_XINGZHENG), resourceIds, null, "销售部有提交给您的学员，请去【学员管理】查看");
				return ReturnConstants.SUCCESS;
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 增加成交记录
	 *  只能是新增的状态，才可以增加成交记录
	 * @param request
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-11-8 上午10:06:04
	 */
	@RequestMapping(value="/student/addDealrecord.do",method = RequestMethod.POST)
	public @ResponseBody String addDealrecord(HttpServletRequest request,String userid,Dealrecord dealrecord,String subjects,String studentId,Student student){
		List<Student> list = studentService.queryStudents(null,studentId, null,null,null, null);
		if (studentId != null && !"".equals(studentId.trim())&&student.getIsOnlineBuy()!=1) {
			if (list != null && list.size() > 0) {
				Integer studentstate = list.get(0).getStudentstate();
				//1：如果是新增，修改学员状态为已成交
				if (studentstate == StateConstants.studentstate0) {//0新增
					list.get(0).setStudentstate(StateConstants.studentstate1);
					list.get(0).setDealprice(student.getDealprice());//成交金额
					list.get(0).setDealtime(student.getDealtime());//成交时间
					list.get(0).setIshavenetedu(student.getIshavenetedu());//是否有网络培训费
					list.get(0).setNetedumoney(student.getNetedumoney());//网络培训费金额
					//list.get(0).setIsOnlineBuy(student.getIsOnlineBuy());//是否在线购买
					list.get(0).setCourseid(student.getCourseid());//课程
					list.get(0).setRemituser(student.getRemituser());//代汇款人
					list.get(0).setPreferinfo(student.getPreferinfo());//优惠信息
					studentService.saveOrUpdate(list.get(0));
					if (subjects != null && !"".equals(subjects.trim())) {
						String[] subject = subjects.split(",");
						for (int i = 0; i < subject.length; i++) {
							//2：增加学员的成交记录（弹框填写成交信息和学员信息）
							Dealrecord dealrecordafter = new Dealrecord();
							dealrecordafter.setCourseid(dealrecord.getCourseid());
							dealrecordafter.setSubjectid(new Long(subject[i]));
							dealrecordafter.setStudentId(new Long(studentId));
							resourceService.saveOrUpdate(dealrecordafter);
						}
					}
					return ReturnConstants.SUCCESS;//已成功成交学员
				}
				return ReturnConstants.USER_NO_POWER;//非新增或已退回状态的学员，不能添加成交记录
			}
		}else{
			List<Student> liststudent = studentService.queryOnLineStudents(list.get(0).getPhone(),null, null);
			      if (liststudent != null && liststudent.size() > 0) {
			    	  outterLoop:for (Student onlionStudent : liststudent) {                
							if(onlionStudent.getCourseid()==Integer.parseInt(String.valueOf(dealrecord.getCourseid()))){
								if(subjects!=null&&subjects.split(",").length==onlionStudent.getSubjectids().split(",").length){
									for (String s : subjects.split(",")) {
										if(onlionStudent.getSubjectids().contains(s)){
											continue;
										}else{
											return ReturnConstants.OLION_NULL;
										}
									}
								}else{
									continue outterLoop;
								}
								onlionStudent.setBelongid(list.get(0).getBelongid());
								//onlionStudent.setResourceId(listresource.get(0).getResourceId());
								if(onlionStudent.getStudentName()==null)
								onlionStudent.setStudentName(list.get(0).getStudentName());
								if(onlionStudent.getAddress()==null)
								onlionStudent.setAddress(list.get(0).getAddress());
								onlionStudent.setSource(list.get(0).getSource());
								if(onlionStudent.getEmail()==null)
								onlionStudent.setEmail(list.get(0).getEmail());
								onlionStudent.setBanci(list.get(0).getBanci());
								onlionStudent.setRemituser(student.getRemituser());
								onlionStudent.setTel(list.get(0).getTel());
								if(onlionStudent.getCompany()==null)
								onlionStudent.setCompany(list.get(0).getCompany());
								onlionStudent.setPreferinfo(student.getPreferinfo());
								onlionStudent.setIshavenetedu(student.getIshavenetedu());//是否有网络培训费
								onlionStudent.setNetedumoney(student.getNetedumoney());//网络培训费金额
							//	onlionStudent.setStudentstate(StateConstants.studentstate3);
								studentService.saveOrUpdate(onlionStudent);
								List<Visitrecord> listRecords = resourceService.queryVisitrecordsByresourceId(null, list.get(0).getStudentId());
								for (Visitrecord v : listRecords) {
									v.setStudentId(onlionStudent.getStudentId());
									resourceService.saveOrUpdate(v);
								}
								studentService.deleteStudent(String.valueOf(list.get(0).getStudentId()));
							}
						}
						}else{
							return ReturnConstants.OLION_NULL;
						}
			      return ReturnConstants.SUCCESS;//已成功成交学员
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 修改成交记录
	 * 		已成交，已退回，状态的学员---可以修改成交记录
	 * @param request
	 * @param userid
	 * @param dealrecord
	 * @param subjects
	 * @param studentId
	 * @param student
	 * @return
	 * @author likang 
	 * @date 2016-11-16 上午11:21:28
	 */
	@RequestMapping(value="/student/editDealrecord.do",method = RequestMethod.POST)
	public @ResponseBody String editDealrecord(HttpServletRequest request,String userid,Dealrecord dealrecord,String subjects,String studentId,Student student){
		if (studentId != null && !"".equals(studentId.trim())) {
			List<Student> list = studentService.queryStudents(null,studentId, null,null, null, null);
			if (list != null && list.size() > 0) {
				Integer studentstate = list.get(0).getStudentstate();
				Long resourceId = list.get(0).getResourceId();
				Long deptid = UserContext.getLoginUser().getDeptid();
				if (studentstate == StateConstants.studentstate1 || studentstate == StateConstants.studentstate8 || (deptid == StateConstants.DEPT_XINGZHENG)) {//1已成交    8已退回
					if (subjects != null && !"".equals(subjects.trim())) {
						String[] subject = subjects.split(",");
						if (resourceId != null) {
							resourceService.deleteDealrecord(resourceId, null);
						}else{
							resourceService.deleteDealrecord(null, new Long(studentId));
						}
						for (int i = 0; i < subject.length; i++) {
							//2：增加学员的成交记录（弹框填写成交信息）
							Dealrecord dealrecordafter = new Dealrecord();
							dealrecordafter.setCourseid(dealrecord.getCourseid());
							dealrecordafter.setSubjectid(new Long(subject[i]));
							if (resourceId != null) {
								dealrecordafter.setResourceId(resourceId);
							}else{
								dealrecordafter.setStudentId(new Long(studentId));
							}
							resourceService.saveOrUpdate(dealrecordafter);
						}
					}
					list.get(0).setDealprice(student.getDealprice());//成交金额
					list.get(0).setDealtime(student.getDealtime());//成交时间
					list.get(0).setIshavenetedu(student.getIshavenetedu());//是否有网络培训费
					list.get(0).setIsOnlineBuy(student.getIsOnlineBuy());//是否在线购买
					list.get(0).setNetedumoney(student.getNetedumoney());//网络培训费金额
					list.get(0).setCourseid(student.getCourseid());//课程
					list.get(0).setPreferinfo(student.getPreferinfo());//优惠信息
					list.get(0).setRemituser(student.getRemituser());//代汇款人
					studentService.saveOrUpdate(list.get(0));
					return ReturnConstants.SUCCESS;//已成功修改成交记录
				}
				return ReturnConstants.USER_NO_POWER;//非已成交或已退回状态的学员，不能修改成交记录
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 增加学员回访记录
	 * @param request
	 * @param studentId
	 * @param userid
	 * @return
	 * @author likang 
	 * @date 2016-11-8 上午9:25:48
	 */
	@RequestMapping(value = "/student/addStudentVisitrecord.do",method = RequestMethod.POST)
	public @ResponseBody String addStudentVisitrecord(HttpServletRequest request,String studentId,Long userid,Visitrecord visitrecord){
		if (studentId != null && !"".equals(studentId) && userid != null) {
			userid = UserContext.getLoginUser().getUserid();
			List<Student> list = studentService.queryStudents(null,studentId, null,null,null, null);
			if (list != null && list.size() > 0) {
				Long resourceId = list.get(0).getResourceId();
				if (resourceId != null) {
					visitrecord.setUserid(userid);
					visitrecord.setResourceId(resourceId);
					resourceService.saveOrUpdate(visitrecord);
				}else{
					visitrecord.setUserid(userid);
					visitrecord.setStudentId(new Long(studentId));
					resourceService.saveOrUpdate(visitrecord);
				}
				return ReturnConstants.SUCCESS;
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 删除学员(新增状态)
	 * @param request
	 * @param studentId
	 * @return
	 * @author likang 
	 * @date 2016-11-8 下午12:05:09
	 */
	@RequestMapping(value = "/student/deleteStudent.do",method = RequestMethod.POST)
	public @ResponseBody String deleteStudent(HttpServletRequest request,String studentId,String resourceId){
		if (studentId != null && !"".equals(studentId)) {
			List<Student> liststudent = studentService.queryStudents(resourceId,studentId, null,null,null, null);
			if (liststudent != null && liststudent.size() > 0) {
				 if (liststudent.get(0).getBelongid() == UserContext.getLoginUser().getUserid()) {
					 if (liststudent.get(0).getStudentstate() == StateConstants.studentstate0) {
						 studentService.deleteStudent(studentId);
						 return ReturnConstants.SUCCESS;
					}
					 return ReturnConstants.STUDENT_DELETE;
				}
				 return ReturnConstants.USER_NO_POWER;
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 增加学员
	 * @param request
	 * @param student
	 * @return
	 * @author likang 
	 * @date 2016-11-8 下午1:51:20
	 */
	@RequestMapping(value = "/student/addStudent.do",method = RequestMethod.POST)
	public @ResponseBody String addStudent(HttpServletRequest request,Student student){
		if (student != null) {
			if (student.getStudentId() != null) {
				studentService.saveOrUpdate(student);
			}else{
				//start likang 2016-12-21 9:32:59  ==TODO 学员管理新建学员时在资源管理中判断是否该手机号已存在，若存在，则提示用户在资源管理中成交。
				List<Resource> listphone =  resourceService.queryResourceByPhone(student.getPhone());
				if (listphone != null && listphone.size() > 0 ) {
					for (int i = 0; i < listphone.size(); i++) {
						Long userphoneid = listphone.get(i).getUserid();
						Long userphonebelongid = listphone.get(i).getBelongid();
						System.out.println(UserContext.getLoginUser().getUserid());
						if (userphoneid == UserContext.getLoginUser().getUserid() || (userphonebelongid !=null && userphonebelongid == UserContext.getLoginUser().getUserid()) ) {
							if (listphone.get(i).getIsStudent() != null && listphone.get(i).getIsStudent() == 0) {
								return ReturnConstants.USER_YES_EXIST;
							}
						}
					}
				}
				//end likang 2016-12-21 9:32:59  ==TODO 
				student.setBelongid(UserContext.getLoginUser().getUserid());
				student.setUserid(UserContext.getLoginUser().getUserid());
				student.setStudentstate(StateConstants.studentstate0);//新增
				studentService.saveOrUpdate(student);
			}
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 退回学员(已提交状态下-->已退回)
	 * 	行政退回学员给销售(批量)
	 * @param request
	 * @param studentIds
	 * @return
	 * @author likang 
	 * @date 2016-11-9 下午6:21:10
	 */
	@RequestMapping(value = "/student/returnStudent.do",method = RequestMethod.POST)
	public @ResponseBody String returnStudent(HttpServletRequest request,String studentIds,String resourceIds,String userid,String returnNote){
		if (studentIds != null && !"".equals(studentIds.trim()) && userid != null && returnNote != null && !"".equals(returnNote.trim())) {
			studentService.updateStudentstate(studentIds, StateConstants.studentstate8,new Timestamp(System.currentTimeMillis()),userid,returnNote);//已退回
			if (resourceIds != null && !"".equals(resourceIds.trim())) {
				studentService.updateResourceStudentstate(resourceIds, StateConstants.studentstate8);//修改资源的学员状态
			}
			/*****************************消息推送**************************************/
			List<Student> list = studentService.queryStudents(null, studentIds, null, null, null, null);
			if (list != null && list.size() > 0) {
				for (int i = 0; i < list.size(); i++) {
					Long belongid = list.get(i).getBelongid();
					sendmessage(WebSocketNeedBean.OBJ_TYPE_LIVE, String.valueOf(belongid),null, resourceIds, null, "行政部有退回给您的学员，请去【学员管理】查看");
				}
			}
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 匹配到账
	 * @param request
	 * @param resourceIds 资源ID，用逗号隔开
	 * @param studentIds 学员ID，用逗号隔开
	 * @param matchInfoIds 财务到账数据ID，用逗号隔开
	 * @param student 
	 * @param studentNames  代汇款人，用逗号隔开
	 * @param matchinfoNames 财务到账数据姓名，用逗号隔开
	 * @return
	 * @author likang 
	 * @date 2016-11-21 下午1:44:09
	 */
	@RequestMapping(value = "/student/matchMoney.do",method = RequestMethod.POST)
	public @ResponseBody String matchMoney(HttpServletRequest request,String resourceIds,String studentIds,String matchInfoIds,Student student,String remitusers,String matchinfoNames){
		if (studentIds != null && !"".equals(studentIds) && matchInfoIds != null && !"".equals(matchInfoIds)) {
			if (!(remitusers != null && !"".equals(remitusers.trim()) && matchinfoNames != null && !"".equals(matchinfoNames.trim()))) {
				return ReturnConstants.REMIT_USER;//代汇款人为空，不能进行匹配到账
			}
			//学员-确认到账时间，收款金额，收款时间，汇款方式（姓名重复的学员，需要单个去确认提交，非重复姓名的学员，可批量并按照姓名对应确认）
			if (remitusers != null && !"".equals(remitusers.trim()) && matchinfoNames != null && !"".equals(matchinfoNames.trim())) {
				String[] remituser = remitusers.split(",");
				String[] matchinfoName = matchinfoNames.split(",");
				int ishavename = 0;
				/**
				 *支持1对1，支持1对多，支持多对多(无重复姓名)
				 **/
				for (int i = 0; i < remituser.length; i++) {
					String sname = remituser[i];
					List<Student> SumPayMoney = null;
					if (remituser.length > 1 && matchinfoName.length == 1) {//多对1
						SumPayMoney = studentService.queryStudentSumPayMoney(sname);
					}else if(remituser.length == 1 && matchinfoName.length == 1){//1对1
						SumPayMoney = studentService.queryStudentSumPayMoney(sname);
					}else if(remituser.length == 1 && matchinfoName.length > 1){//1对多
						SumPayMoney = studentService.queryStudentSumPayMoney(sname);
					}
					for (int j = 0; j < matchinfoName.length; j++) {
						String mname = matchinfoName[j];
						List<Student> studentlist = studentService.queryStudentBysname(sname);//学员信息
						if (studentlist != null && studentlist.size() > 0) {
							if (sname.trim().equals(mname.trim())) {//匹配成功
								//取财务表的收款时间，汇款方式，收款金额，对应到学员表
								List<Matchinfo> matchinfolist = null;
								if(remituser.length == 1 && matchinfoName.length == 1){//1对1
									Matchinfo matchinfo = new Matchinfo();matchinfo.setMatchInfoId(Long.valueOf(matchInfoIds));
									matchinfolist = matchinfoService.queryMatchinfo(matchinfo, null);
								}else{
									matchinfolist = matchinfoService.queryMatchinfoBymname(mname);
								}
								if (matchinfolist != null && matchinfolist.size() > 0) {
									/**
									 * 多对1:校验：成交金额+网络培训费金额 =？ 财务的收款金额	，如果相等则继续确认，如果不想等，则不能确认
									 */
									if ((remituser.length > 1 && matchinfoName.length == 1) || (remituser.length == 1 && matchinfoName.length == 1)) {//多对1，1对1
										if (SumPayMoney != null && SumPayMoney.size() > 0 && Double.valueOf(SumPayMoney.get(0).getSumPayMoney()).longValue() == Double.valueOf(matchinfolist.get(0).getPayMoney()).longValue()) {//相等
											for (int k = 0; k < studentlist.size(); k++) {
												Double arrviemoneysum = Double.valueOf(studentlist.get(k).getDealprice()) + Double.valueOf(studentlist.get(k).getNetedumoney()== null || "".equals(studentlist.get(k).getNetedumoney())?"0":studentlist.get(k).getNetedumoney());
												studentlist.get(k).setArrive_money(String.valueOf(arrviemoneysum));//收款金额 = 成交金额 + 网络培训费 
												studentlist.get(k).setArrive_time(String.valueOf(matchinfolist.get(0).getReceiveTime()));//收款时间
												studentlist.get(k).setRemitWay(matchinfolist.get(0).getPayType());//汇款方式
											}
										}else{
											return ReturnConstants.NO_MATCHINFO;//成交金额+网络培训费 != 收款金额，不能进行确认对账
										}
									}else{
										for (int k = 0; k < studentlist.size(); k++) {
											studentlist.get(k).setArrive_money(matchinfolist.get(0).getPayMoney());//收款金额
											studentlist.get(k).setArrive_time(String.valueOf(matchinfolist.get(0).getReceiveTime()));//收款时间
											studentlist.get(k).setRemitWay(matchinfolist.get(0).getPayType());//汇款方式
										}
									}
									for (int k = 0; k < studentlist.size(); k++) {
										studentlist.get(k).setMatchinfo_time(new Timestamp(System.currentTimeMillis()));//确认到账时间
										studentlist.get(k).setStudentstate(StateConstants.studentstate3);//修改学员状态为“已到账”
										studentService.saveOrUpdate(studentlist.get(k));
									}
									//修改资源状态为“已到账”
									resourceIds = String.valueOf(studentlist.get(0).getResourceId());
									if (resourceIds != null && !"".equals(resourceIds.trim())) {
										studentService.updateResourceStudentstate(resourceIds, StateConstants.studentstate3);
									}
									//修改财务导入的数据状态为“已匹配”
									if (matchInfoIds != null && !"".equals(matchInfoIds.trim())) {
										matchinfoService.updateIsmatch(matchInfoIds, Matchinfo.IS_MATCH_YES);
									}
									ishavename++;
									break;
								} 
							}
						}
					}
				}
				if (ishavename != 0) {
					sendmessage(WebSocketNeedBean.OBJ_TYPE_LIVE, null,String.valueOf(StateConstants.DEPT_CAIWU), resourceIds, null, "行政部对学员匹配到账成功，请去【到账信息】查看");
					return ReturnConstants.SUCCESS;
				}else{
					return ReturnConstants.MATCHINFO_AND_STUDENT;//两边未有匹配成功学员数据
				}
			}
			return ReturnConstants.REMIT_USER;//代汇款人为空，不能进行匹配到账
		}
		return ReturnConstants.PARAM_NULL;
	}
	
		
	/**
	 * 查询财务到账信息
	 * @param request
	 * @return
	 * @author likang 
	 * @date 2016-11-10 上午10:53:33
	 */
	@RequestMapping(value = "/student/queryMatchinfo.do",method = RequestMethod.GET)
	public @ResponseBody String queryMatchinfo(HttpServletRequest request,String matchname,Integer pageSize, Integer currentPage){
		List<Matchinfo> list = matchinfoService.queryMatchinfo(matchname,processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 查询销售已提交信息
	 * @param request
	 * @return
	 * @author likang 
	 * @date 2016-11-10 上午10:53:33
	 */
	@RequestMapping(value = "/student/queryStudnetMatchs.do",method = RequestMethod.GET)
	public @ResponseBody String queryStudnetMatchs(HttpServletRequest request, String studentName,Integer pageSize, Integer currentPage){
		List<Student> list = studentService.queryStudnetMatchs(studentName,processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 查询有网络培训费的学员列表
	 *		查询 
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-11 上午10:51:57
	 */
	@RequestMapping(value = "/student/queryNetworkEduMoney.do",method = RequestMethod.GET)
	public @ResponseBody String queryNetworkEduMoney(HttpServletRequest request,Long deptid,Long userid,Long roleid,Integer pageSize, Integer currentPage){
		if (deptid != null && !"".equals(deptid) && userid != null && !"".equals(userid) && roleid != null && !"".equals(roleid)) {
			List<Student> list = studentService.queryNetworkEduMoney(null,deptid,userid,roleid, processPageBean(pageSize, currentPage));
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 行政-网络培训费学员-提交财务
	 * @param request
	 * @param resourceIds
	 * @param studentIds
	 * @return
	 * @author likang 
	 * @date 2016-11-21 下午3:22:37
	 */
	@RequestMapping(value = "/student/commit_caiwu.do",method = RequestMethod.POST)
	public @ResponseBody String commit_caiwu(HttpServletRequest request,String resourceIds,String studentIds){
		if (studentIds != null && !"".equals(studentIds.trim())) {
			studentService.commitTocaiwu(resourceIds, studentIds);
			sendmessage(WebSocketNeedBean.OBJ_TYPE_LIVE, null,String.valueOf(StateConstants.DEPT_CAIWU), resourceIds, null, "行政部提交存在网络培训费的学员成功，请去【网络培训费】查看");
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 网络培训费导出(行政部)
	 * @param request
	 * @param response
	 * @param student
	 * @param studentIds
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-11-21 下午6:32:32
	 */
	@RequestMapping(value = "/student/netWorkEduMoneyExport.do",method = RequestMethod.POST)
	public @ResponseBody String netWorkEduMoneyExport(HttpServletRequest request, HttpServletResponse response,Student student,String studentIds,Long roleid,Long userid,Long deptid){
		if (deptid != null) {
			String[] header = getxzHeaders(deptid);
			//获取要导出的数据
			List<Student> liststudent = null;
			if (studentIds != null && !"".equals(studentIds.trim())) {
				liststudent = studentService.queryNetworkEduMoney(studentIds,deptid, userid, roleid, null);
			}
			if (liststudent != null && liststudent.size() > 0) {
				//写入到excel
				String separator = File.separator;
				String dir = request.getRealPath(separator + "upload");
				try {
					OutputStream out = new FileOutputStream(dir + separator + "行政部网络培训费.xls");
					ExcelExportUtil.netWorkEduMoneyExport(deptid,"行政部网络培训费", header, liststudent, out);
					//下载到本地
					out.close();
					String path = request.getSession().getServletContext().getRealPath(separator + "upload" + separator + "行政部网络培训费.xls");
					response.setContentType("text/html;charset=utf-8");
					request.setCharacterEncoding("UTF-8");
					java.io.BufferedInputStream bis = null;
					java.io.BufferedOutputStream bos = null;
					String downLoadPath = path;
					try {
						long fileLength = new File(downLoadPath).length();
						response.setContentType("application/x-msdownload;");
						response.setHeader("Content-disposition","attachment; filename=" + new String("行政部网络培训费.xls".getBytes("utf-8"), "ISO8859-1"));
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
			return ReturnConstants.PARAM_NULL;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 网络培训费查重（身份证号码）
	 * @param request
	 * @return
	 * @author likang 
	 * @date 2016-11-21 下午9:17:22
	 */
	@RequestMapping(value = "/student/cheakRepeatNetWorkStudents.do",method = RequestMethod.GET)
	public @ResponseBody String cheakRepeatNetWorkStudents(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<Student> list = studentService.queryRepeatStudents(null,"false",null);//查询重复的身份证号列表
		if (list != null && list.size() > 0) {
			String idCards = "";
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					idCards = idCards + list.get(i).getIdCard();
				}else{
					idCards = idCards + list.get(i).getIdCard() + ",";
				}
			}
			List<Student> listend = studentService.queryRepeatStudents(idCards,"true",processPageBean(pageSize, currentPage));//查询所有重复身份证号的详细信息
			return jsonToPage(listend);
		}
		
		return null;
	}
	
	/**
	 * 保存网络培训费填写内容
	 * @param request
	 * @param jsonlist
	 * @return
	 * @author likang 
	 * @date 2016-11-22 上午8:52:32
	 */
	@RequestMapping(value = "/student/saveNetWorkStudents.do",method = RequestMethod.POST)
	public @ResponseBody String saveNetWorkStudents(HttpServletRequest request,String jsonlist){
		
		if (jsonlist != null && !"".equals(jsonlist.trim())) {
			 List<Student> resultList = new ArrayList<Student>();
			 resultList = JackSonUtils.convertStr2objList(jsonlist,Student.class);
			 for (int i = 0; i < resultList.size(); i++) {
				//保存
				 Long studentid = resultList.get(i).getStudentId();
				 if (studentid != null) {
					studentService.saveOrUpdate(resultList.get(i));
				}
			}
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 网络培训费-筛选
	 * @param request
	 * @param student
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-22 上午9:38:32
	 */
	@RequestMapping(value = "/student/queryNetWorkStudentsBySceen.do",method = RequestMethod.GET)
	public @ResponseBody String queryNetWorkStudentsBySceen(HttpServletRequest request,Student student,Integer pageSize,Integer currentPage){
		if (student != null) {
			List<Student> list = studentService.queryStudentBySceen("true",null, null, null, student, processPageBean(pageSize, currentPage));
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 网络培训费-筛选导出
	 * @param request
	 * @param student
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-22 上午9:38:32
	 */
	@RequestMapping(value = "/student/queryExportNetWorkStudentsBySceen.do",method = RequestMethod.GET)
	public @ResponseBody String queryExportNetWorkStudentsBySceen(HttpServletRequest request,Student student,Integer pageSize,Integer currentPage){
		if (student != null) {
			List<Student> list = studentService.queryStudentBySceen("true",null, null, null, student, null);
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 到账学员总表
	 * 		查询
	 * @param request
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午8:43:31
	 */
	@RequestMapping(value = "/student/queryYesStudentMatchinfo.do",method = RequestMethod.GET)
	public @ResponseBody String queryYesStudentMatchinfo(HttpServletRequest request,String deptid,Integer pageSize,Integer currentPage){
		if (deptid != null && !"".equals(deptid)) {
			List<Student> list = studentService.queryYesStudentMatchinfo(deptid, processPageBean(pageSize, currentPage));
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 根据条件进行查询学员--筛选
	 * @param request
	 * @param resource
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:30:46
	 */
	@RequestMapping(value = "/student/queryYesStudentMatchinfoBySceen.do",method = RequestMethod.GET)
	public @ResponseBody String queryYesStudentMatchinfoBySceen(HttpServletRequest request,Long deptid,Long roleid,Long userid,Student student,Integer pageSize,Integer currentPage){
		if (student != null) {
			List<Student> list = studentService.queryStudentBySceen("false",deptid,roleid,userid,student, processPageBean(pageSize, currentPage));
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 根据条件进行查询学员--筛选导出
	 * @param request
	 * @param resource
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-4 下午5:30:46
	 */
	@RequestMapping(value = "/student/queryExportYesStudentMatchinfoBySceen.do",method = RequestMethod.GET)
	public @ResponseBody String queryExportYesStudentMatchinfoBySceen(HttpServletRequest request,Long deptid,Long roleid,Long userid,Student student,Integer pageSize,Integer currentPage){
		if (student != null) {
			List<Student> list = studentService.queryStudentBySceen("false",deptid,roleid,userid,student, null);
			return jsonToPage(list);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 分配客服
	 * 		已到账-->已分配
	 * @param request
	 * @param resourceIds 资源ID（批量）
	 * @param studentIds 学员ID（批量）
	 * @param customerId 客服ID
	 * @param courseids 课程IDs
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午10:55:12
	 */
	@RequestMapping(value = "/student/assignStudent.do",method = RequestMethod.POST)
	public @ResponseBody String assignStudent(HttpServletRequest request,String resourceIds,String studentIds,String customerId,String studentstates,String courseids){
		if (studentIds != null && !"".equals(studentIds.trim()) && customerId != null && !"".equals(customerId.trim()) && studentstates != null && !"".equals(studentstates.trim()) && courseids != null && !"".equals(courseids.trim())) {
			 String[] courseid = courseids.split(",");
			 int issameAFP = 0;
			 int issameCFP = 0;
			 int issameFAC = 0;
			 for (int i = 0; i < courseid.length; i++) {
				if ("1".equals(courseid[i]) || "2".equals(courseid[i]) || "17".equals(courseid[i])) {//AFP
					issameAFP++;
				}
				if ("3".equals(courseid[i]) || "4".equals(courseid[i]) || "18".equals(courseid[i])) {//CFP
					issameCFP++;
				}
				if ("5".equals(courseid[i]) || "6".equals(courseid[i]) || "7".equals(courseid[i])
						|| "8".equals(courseid[i])|| "9".equals(courseid[i])|| "10".equals(courseid[i])|| "11".equals(courseid[i])
						|| "12".equals(courseid[i])|| "13".equals(courseid[i])|| "14".equals(courseid[i])|| "20".equals(courseid[i])) {//非A/C
					issameFAC++;
				}
			}
			 
			if (!((issameAFP == courseid.length && issameCFP == 0 && issameFAC == 0) || (issameCFP == courseid.length && issameAFP == 0 && issameFAC == 0)
					|| (issameFAC == courseid.length && issameAFP == 0 && issameCFP == 0)) ) {
				return ReturnConstants.STUDENT_COURSEID_COMMIT;//存在非同类课程的学员，不能分配客服
			}
			 
			User user = userService.queryByIdentity(new Long(customerId));
			Long roleid = user.getRoleid();
			boolean issamerole = false;
			if (issameAFP == courseid.length) {//AFP
				if (roleid.longValue() == 20 || roleid.longValue() == 21 || roleid.longValue() == 22 || roleid.longValue() == 23) {
					issamerole = true;
				}
			}else if(issameCFP == courseid.length){
				if (roleid.longValue() == 24 || roleid.longValue() == 25 ) {
					issamerole = true;
				}
			}else if(issameFAC == courseid.length){
				if (roleid.longValue() == 26) {
					issamerole = true;
				}
			}
			 
			if (issamerole) {
				String[] states = studentstates.split(",");
				String s = null;
				for (int i = 0; i < states.length; i++) {
					s = states[i];
					if (s != null && !"3".equals(s)) {
						return ReturnConstants.STUDENT_NO_COMMIT;// 学员状态存在非已到账状态，不能提交客服
					}
				}
				// 学员已到账-->已分配
				studentService.assignStudent(resourceIds, studentIds, customerId,user.getUsername(),StateConstants.studentstate4,new Timestamp(System.currentTimeMillis()));
				if (resourceIds != null && !"".equals(resourceIds.trim())) {
					studentService.updateResourceStudentstate(resourceIds,StateConstants.studentstate4);
				}
				sendmessage(WebSocketNeedBean.OBJ_TYPE_LIVE, customerId,null, resourceIds, null, "行政部分配给您学员成功，请去【总表学员】查看");
				return ReturnConstants.SUCCESS;
			}else{
				return ReturnConstants.STUDENT_COURSE_COMSTER_NOT;//分配的学员课程和分配客服人员职责不匹配
			}
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 行政转移客服
	 * @param request
	 * @param resourceIds
	 * @param studentIds
	 * @param customerId
	 * @param studentstates
	 * @return
	 * @author likang 
	 * @date 2016-11-21 下午2:29:21
	 */
	@RequestMapping(value = "/student/transferassignStudent.do",method = RequestMethod.POST)
	public @ResponseBody String transferassignStudent(HttpServletRequest request,String resourceIds,String studentIds,String customerId,String studentstates){
		if (studentIds != null && !"".equals(studentIds.trim()) && customerId != null && !"".equals(customerId.trim()) && studentstates != null && !"".equals(studentstates.trim())) {
			//star likang 2017-1-17 17:59:50 ==TODO 行政部需要所有权限去转移学员到对应的客服
			/*String[] states = studentstates.split(",");
			String s = null;
			for (int i = 0; i < states.length; i++) {
				s= states[i];
				if (s != null && !"4".equals(s)) {
					return ReturnConstants.STUDENT_NO_COMMIT;//学员状态存在非已分配状态，不能转移客服
				}
			}*/
			//end
			//学员已到账-->已分配
			User user = userService.queryByIdentity(new Long(customerId));
			studentService.assignStudent(resourceIds, studentIds, customerId,user.getUsername(), StateConstants.studentstate4, new Timestamp(System.currentTimeMillis()));
			sendmessage(WebSocketNeedBean.OBJ_TYPE_LIVE, customerId,null, resourceIds, null, "行政部转移给您学员成功，请去【总表学员】查看");
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 查询已通过学员列表
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午11:43:02
	 */
	@RequestMapping(value = "/student/queryPassStudents.do",method = RequestMethod.GET)
	public @ResponseBody String queryPassStudents(HttpServletRequest request,String phone,Integer pageSize, Integer currentPage){
		List<Student> list = studentService.queryPassStudents(phone,processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 已通过学员进行关课
	 * @param request
	 * @param resourceIds 资源ID(批量)
	 * @param studentIds 学员ID(批量)
	 * @param courseId 课程ID
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午11:48:26
	 */
	@RequestMapping(value = "/student/closeCources.do",method = RequestMethod.POST)
	public @ResponseBody String closeCources(HttpServletRequest request,String resourceIds,String studentIds,String courseId){
		if (studentIds != null && !"".equals(studentIds.trim())) {
			studentService.updateStudentstate(studentIds,StateConstants.studentstate7,null,null,null);
			studentService.updateResourceStudentstate(resourceIds, StateConstants.studentstate7);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 在线购买
	 * 		查询
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-14 上午11:54:44
	 */
	@RequestMapping(value = "/student/queryOnLineStudents.do",method = RequestMethod.GET)
	public @ResponseBody String queryOnLineStudents(HttpServletRequest request,String phone,String courseid,Integer pageSize, Integer currentPage){
		List<Student> list = studentService.queryOnLineStudents(phone,courseid,processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 查询所有客服人员
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-22 下午5:14:54
	 */
	@RequestMapping(value = "/custom/queryAllCustoms.do",method = RequestMethod.GET)
	public @ResponseBody String queryAllCustoms(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<User> list = studentService.queryAllCustoms();
		return jsonToPage(list);
	}
	
	
	
	/**
	 * 查询客服人员（行政部专用）
	 * AFP---AFP主管和组员
	 * CFP---CFP主管
	 * 基金 ---非A/C主管
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-22 下午5:14:54
	 */
	@RequestMapping(value = "/custom/queryxzCustoms.do",method = RequestMethod.GET)
	public @ResponseBody String queryxzCustoms(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<User> list = studentService.queryxzCustoms();
		return jsonToPage(list);
	}
	
	
	/**
	 * 在线购买学员批量导入
	 * @param request
	 * @param file
	 * @return
	 * @author 倪广
	 * @date 2017-2-20 16:14:19
	 */
//	@CacheEvict(value = "baseCache")
	@RequestMapping(value = "/student/excelImportOnlionBuy.do",method = RequestMethod.POST)
	public @ResponseBody String excelImportOnlionBuy(HttpServletRequest request,@RequestParam("resourceFile")MultipartFile resourceFile){
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy/MM/dd HH:mm:ss");
		if (resourceFile != null) {
			//存放路径
			String realPath = request.getRealPath("");
			if(!realPath.endsWith(File.separator)){
				realPath += File.separator;
			}
			String originalFilename = resourceFile.getOriginalFilename();// 获取原始文件名
			//年月日文件夹
			String yearDir = new SimpleDateFormat("yyyyMMdd").format(new Date());
			//文件名称
			String fileName = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+originalFilename;
			String filePath = "upload"+File.separator+"resourcefile"+File.separator+yearDir+File.separator+fileName;;
			File destFile = new File(realPath+filePath);
			if(!destFile.getParentFile().exists()){
				destFile.getParentFile().mkdirs();
			}
			try {
				resourceFile.transferTo(destFile);//写入服务器
			} catch (Exception e) {
				e.printStackTrace();
			} 
			try {
				//读取excel 
				FileInputStream is = new FileInputStream(destFile); // 文件流
				Map<String,Object> returnMaps = ExcelReaderUtil.readExcelContentOlionBuy(is);//读取excel
				if (returnMaps.get("listdata")!= null) {
					//写入数据库
					List<Student> listresource = new ArrayList<Student>();
					List<Map> listmap = new ArrayList<Map>();
					String str = null;
					for (int i = 0; i < returnMaps.size(); i++) {
						listmap = (List<Map>) returnMaps.get("listdata");
						for (int j = 0; j < listmap.size(); j++) {
							Student onlionBuy = new Student();
								str = listmap.get(j).get("userName")!= null && !"".equals(listmap.get(j).get("userName"))?listmap.get(j).get("userName").toString().trim():null;//创建者
								onlionBuy.setStudentName(str!=null?str:null);
								str = null;
								
								str = listmap.get(j).get("IDcard")!=null && !"".equals(listmap.get(j).get("IDcard"))?listmap.get(j).get("IDcard").toString().trim():null;//渠道
								onlionBuy.setIdCard(str!=null?str:null);
								str = null;
								
								str = listmap.get(j).get("phone")!=null && !"".equals(listmap.get(j).get("phone"))?listmap.get(j).get("phone").toString().trim():null;//地域
								onlionBuy.setPhone(str!=null?str:null);
								str = null;
								
								str = listmap.get(j).get("email")!=null && !"".equals(listmap.get(j).get("email"))?listmap.get(j).get("email").toString().trim():null;//姓名
								onlionBuy.setEmail(str!=null?str:null);
								str = null;
								
								str = listmap.get(j).get("company")!=null && !"".equals(listmap.get(j).get("company"))?listmap.get(j).get("company").toString().trim():null;//手机
								onlionBuy.setCompany(str!=null?str:null);
								str = null;
								
								str = listmap.get(j).get("address")!=null && !"".equals(listmap.get(j).get("address"))?listmap.get(j).get("address").toString().trim():null;//手机
								onlionBuy.setAddress(str!=null?str:null);
								str = null;
								
								str = listmap.get(j).get("arrive_money")!=null && !"".equals(listmap.get(j).get("arrive_money"))?listmap.get(j).get("arrive_money").toString().trim():null;//运营咨询备注
								onlionBuy.setArrive_money(str!=null?str:null);
								onlionBuy.setDealprice(str!=null?str:null);
								str = null;
								
								SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
								str = listmap.get(j).get("arrive_time")!=null && !"".equals(listmap.get(j).get("arrive_time"))?listmap.get(j).get("arrive_time").toString().trim():null;//所属人
								onlionBuy.setArrive_time(str!=null?str:null);
								onlionBuy.setDealtime(str!=null?new Timestamp(sdf.parse(str).getTime()):null);
								str = null;
								
								str = listmap.get(j).get("remitWay")!=null && !"".equals(listmap.get(j).get("remitWay"))?listmap.get(j).get("remitWay").toString().trim():null;//所属人
								onlionBuy.setRemitWay(str!=null?str:null);
								str = null;
								
								str = listmap.get(j).get("courseId")!=null && !"".equals(listmap.get(j).get("courseId"))?listmap.get(j).get("courseId").toString().trim():null;//咨询课程
								onlionBuy.setCourseid(str!=null?new Integer(str):null);
								str = null;
								
								str = listmap.get(j).get("subjectId")!=null && !"".equals(listmap.get(j).get("subjectId"))?listmap.get(j).get("subjectId").toString().trim():null;//咨询课程
								onlionBuy.setSubjectids(str!=null?str:null);
								str = null;
							
							listresource.add(onlionBuy);
						}
					}
					for (int i = 0; i < listresource.size(); i++) {//写入数据库
							listresource.get(i).setDr(0);
							listresource.get(i).setIsOnlineBuy(1);//是否在线购买
							listresource.get(i).setCreate_time(new Timestamp(System.currentTimeMillis()));
							listresource.get(i).setStudentstate(3);
							listresource.get(i).setUserid(UserContext.getLoginUser().getUserid());
						    studentService.saveOrUpdate(listresource.get(i));
						    if (listresource.get(i).getSubjectids() != null && !"".equals(listresource.get(i).getSubjectids().trim())) {
								String[] subject = listresource.get(i).getSubjectids().split(",");
								for (int j = 0; j < subject.length; j++) {
									//2：增加学员的成交记录（弹框填写成交信息和学员信息）
									Dealrecord dealrecordafter = new Dealrecord();
									dealrecordafter.setStudentId(listresource.get(i).getStudentId());
									dealrecordafter.setCourseid(new Long(listresource.get(i).getCourseid()));
									dealrecordafter.setSubjectid(new Long(subject[j]));
									resourceService.saveOrUpdate(dealrecordafter);
								}
							}
					}
					return ReturnConstants.SUCCESS;
				}
				return ReturnConstants.EXCEL_NULL;
			} catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
}
