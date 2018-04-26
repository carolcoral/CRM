package com.hjcrm.resource.controller;

import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.OutputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
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
import com.hjcrm.resource.entity.Matchinfo;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.resource.service.IMatchinfoService;
import com.hjcrm.resource.service.IReportService;
import com.hjcrm.resource.service.IStudentService;
import com.hjcrm.resource.util.ExcelExportUtil;
import com.hjcrm.resource.util.ExcelReaderByCaiWuUtil;
import com.hjcrm.system.entity.User;

/**
 * 到账信息
 * @author likang
 * @date 2016-11-10 上午10:51:41
 */
@Controller
public class MatchController extends BaseController{
	
	@Autowired
	private IMatchinfoService matchinfoService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IReportService reportService;
	
	
	
	/**
	 * 跳转到账信息界面（财务部）
	 * 
	 * @param model
	 * @return
	 * @author likang
	 * @date 2016-10-27 下午3:06:37
	 */
	@RequestMapping(value = "/matchinfo/macthinfor.do", method = RequestMethod.GET)
	public String macthinfor(Model model) {
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/matchinfo/macthinfor.do");
			if (isopen) {
				return JumpViewConstants.MATCHINFO;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}

	/**
	 * 跳转网络培训费界面（财务部）
	 * @param model
	 * @return
	 * @author likang 
	 * @date 2016-11-18 上午9:44:50
	 */
	@RequestMapping(value = "/matchinfo/networkTrain.do", method = RequestMethod.GET)
	public String networkTrainIndex(Model model){
		User user = UserContext.getLoginUser();
		if (user != null) {
			Long roleid = user.getRoleid();
			boolean isopen = isSholdOpenMenu(roleid,"/matchinfo/networkTrain.do");
			if (isopen) {
				return JumpViewConstants.FINANCE_NETWORKTRAIN;
			}else{
				return ReturnConstants.USER_NO_OPEN;//用户无权限操作并打开，如果需要此权限，请联系运营部
			}
		}
		return ReturnConstants.USER_NO_LOGIN;//用户未登录，请刷新
	}
	
	

	/**
	 * 查询到账信息/筛选
	 * @param request
	 * @param matchinfo
	 * @return
	 * @author likang 
	 * @date 2016-11-10 上午10:53:33
	 */
	@RequestMapping(value = "/matchinfo/queryMatchinfo.do",method = RequestMethod.GET)
	public @ResponseBody String queryMatchinfo(HttpServletRequest request,Matchinfo matchinfo,Integer pageSize, Integer currentPage){
		List<Matchinfo> list = matchinfoService.queryMatchinfo(matchinfo, processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 导入到账信息
	 * @param request
	 * @param matchinfoFile
	 * @param deptid
	 * @return
	 * @author likang 
	 * @date 2016-11-10 上午11:22:39
	 */
	@RequestMapping(value = "/matchinfo/excelImport.do",method = RequestMethod.POST)
	public @ResponseBody String excelImport(HttpServletRequest request,@RequestParam("matchinfoFile")MultipartFile matchinfoFile,Long deptid){
		if (matchinfoFile != null && deptid != null && deptid.longValue() == StateConstants.DEPT_CAIWU.longValue()) {
			//存放路径
			String realPath = request.getRealPath("");
			if(!realPath.endsWith(File.separator)){
				realPath += File.separator;
			}
			String originalFilename = matchinfoFile.getOriginalFilename();// 获取原始文件名
			//年月日文件夹
			String yearDir = new SimpleDateFormat("yyyyMMdd").format(new Date());
			//文件名称
			String fileName = new SimpleDateFormat("yyyyMMddHHmmssS").format(new Date())+originalFilename;
			String filePath = "upload"+File.separator+"matchinfoFile"+File.separator+yearDir+File.separator+fileName;;
			File destFile = new File(realPath+filePath);
			if(!destFile.getParentFile().exists()){
				destFile.getParentFile().mkdirs();
			}
			try {
				matchinfoFile.transferTo(destFile);//写入服务器
			} catch (Exception e) {
				e.printStackTrace();
			} 
			try {
				//读取excel 
				FileInputStream is = new FileInputStream(destFile); // 文件流
				Map<String,Object> returnMaps = ExcelReaderByCaiWuUtil.readExcelContent(is);
				if (returnMaps.get("listdata")!= null) {
					//写入数据库
					List<Matchinfo> listmatchinfo = new ArrayList<Matchinfo>();
					List<Map> listmap = new ArrayList<Map>();
					String str = null;
					for (int i = 0; i < returnMaps.size(); i++) {
						listmap = (List<Map>) returnMaps.get("listdata");
						for (int j = 0; j < listmap.size(); j++) {
							Matchinfo matchinfo = new Matchinfo();
							
							str = listmap.get(j).get("matchname")!= null && !"".equals(listmap.get(j).get("matchname").toString().trim())?listmap.get(j).get("matchname").toString().trim():null;//姓名
							matchinfo.setMatchname(str);
							str = null;
							
							str = listmap.get(j).get("payMoney")!= null && !"".equals(listmap.get(j).get("payMoney").toString().trim())?listmap.get(j).get("payMoney").toString().trim():null;//收款金额 
							matchinfo.setPayMoney(str);
							str = null;
							
							str = listmap.get(j).get("payType")!= null && !"".equals(listmap.get(j).get("payType").toString().trim())?listmap.get(j).get("payType").toString().trim():null;//汇款方式
							matchinfo.setPayType(str);
							str = null;
							
							str = listmap.get(j).get("receiveTime")!= null && !"".equals(listmap.get(j).get("receiveTime").toString().trim())?listmap.get(j).get("receiveTime").toString().trim():null;//收款日期
							matchinfo.setReceiveTime(java.sql.Date.valueOf(str));
							str = null;
							
							str = listmap.get(j).get("matchnote")!= null && !"".equals(listmap.get(j).get("matchnote").toString().trim())?listmap.get(j).get("matchnote").toString().trim():null;//备注 
							matchinfo.setMatchnote(str);
							str = null;
							
							listmatchinfo.add(matchinfo);
						}
					}
					for (int i = 0; i < listmatchinfo.size(); i++) {//写入数据库
						listmatchinfo.get(i).setIsmatch(Matchinfo.IS_MATCH_NO);//未匹配
						matchinfoService.saveOrUpdateMatchinfo(listmatchinfo.get(i));
					}
					Map<String, Object> map = new HashMap<String, Object>();
					map.put("state", ReturnConstants.SUCCESS);//导入成功状态
					map.put("total", listmatchinfo.size());//导入条数
					return json(map);
				}
				return ReturnConstants.EXCEL_NULL;//文件内容为空
				
			}catch (Exception e) {
				e.printStackTrace();
			} 
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	/**
	 * 到账查重
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-10 下午3:02:05
	 */
	@RequestMapping(value = "/matchinfo/queryRepeatMatchinfo.do",method = RequestMethod.GET)
	public @ResponseBody String queryRepeatMatchinfo(HttpServletRequest request,Integer pageSize, Integer currentPage){
		List<Matchinfo> list = matchinfoService.queryRepeatMatchinfo(null, "false", null);////查询重复的姓名
		if (list != null && list.size() > 0) {
			String matchname = "";
			for (int i = 0; i < list.size(); i++) {
				if (i == list.size() - 1) {
					matchname = matchname + "'"+list.get(i).getMatchname()+"'";
				}else{
					matchname = matchname + "'"+list.get(i).getMatchname()+"'" + ",";
				}
			}
			List<Matchinfo> listmatchinfo = matchinfoService.queryRepeatMatchinfo(matchname, "true", processPageBean(pageSize, currentPage));////查询重复的姓名
			return jsonToPage(listmatchinfo);
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	/**
	 * 获取导出头部信息
	 * @return
	 * @author likang 
	 * @date 2016-11-17 上午11:58:32
	 */
	public static String[] getHeaders(){
		String[] header = new String[]{"资源分配时间","确认到账时间","姓名","身份证","电话","固定电话","邮箱","单位","地址","职务","毕业院校","学历","民族","招生老师","代汇款人"
				,"收款金额","收款日期","汇款方式","LCW用户名","LCW密码","课件版本","发票情况","报考密码","协议","班次","期次","优惠信息","课程","科目"
				,"AFP是否通过","投资","保险","税务","福利","综合","我司收入","退费","退费日期","网络培训费","支付日期","审核问题","财务备注"} ;
		return header ;
	}
	
	/**
	 * 财务导出（所有已确认到账学员之后状态的数据）
	 * @param request
	 * @param response
	 * @return
	 * @author likang 
	 * @date 2016-11-17 上午10:09:30
	 */
	@RequestMapping(value = "/matchinfo/excelExport.do",method = RequestMethod.POST)
	public @ResponseBody String excelExport(HttpServletRequest request, HttpServletResponse response,Student student,String studentIds,Long roleid,Long userid,Long deptid){
		String[] header = getHeaders();//表头
		//获取导出数据
		student.setIssign("true");
		List<Student> list =  reportService.queryStudentscaiwu(student,roleid, studentIds, deptid, userid, null);
		//写入到excel
		String separator = File.separator;
		String dir = request.getRealPath(separator + "upload");
		try {
			OutputStream out = new FileOutputStream(dir + separator + "财务学员导出信息.xls");
			ExcelExportUtil.exportExcelStudent(deptid,"财务学员导出信息", header, list, out);
			// 下载到本地
			out.close();
			String path = request.getSession().getServletContext().getRealPath(separator + "upload" + separator + "财务学员导出信息.xls");
			response.setContentType("text/html;charset=utf-8");
			request.setCharacterEncoding("UTF-8");
			java.io.BufferedInputStream bis = null;
			java.io.BufferedOutputStream bos = null;
			String downLoadPath = path;
			try {
				long fileLength = new File(downLoadPath).length();
				response.setContentType("application/x-msdownload;");
				response.setHeader("Content-disposition","attachment; filename=" + new String("财务学员导出信息.xls".getBytes("utf-8"),"ISO8859-1"));
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
	 * 财务部查询网络培训费列表
	 * @param request
	 * @param pageSize
	 * @param currentPage
	 * @return
	 * @author likang 
	 * @date 2016-11-21 下午3:42:24
	 */
	@RequestMapping(value = "/matchinfo/queryNetworkEduMoneyBycaiwu.do",method = RequestMethod.GET)
	public @ResponseBody String queryNetworkEduMoneyBycaiwu(HttpServletRequest request,String phone,Integer pageSize, Integer currentPage){
		List<Student> list = matchinfoService.queryNetworkEduMoneyBycaiwu(phone,processPageBean(pageSize, currentPage));
		return jsonToPage(list);
	}
	
	/**
	 * 财务部
	 * 	修改总表的学员收款方式和收款日期
	 * @param request
	 * @param jsonlist
	 * @return
	 * @author likang 
	 * @date 2016-11-17 下午2:44:59
	 */
	@RequestMapping(value = "/matchinfo/caiwuAllStudents.do",method = RequestMethod.POST)
	public @ResponseBody String caiwuAllStudents(HttpServletRequest request, String jsonlist){
		if (jsonlist != null && !"".equals(jsonlist.trim())) {
			 List<Student> resultList = new ArrayList<Student>();
			 resultList = JackSonUtils.convertStr2objList(jsonlist,Student.class);
			 for (int i = 0; i < resultList.size(); i++) {
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
	 * 确认支付日期
	 * @param request
	 * @param jsonlist
	 * @return
	 * @author likang 
	 * @date 2016-11-17 下午2:44:59
	 */
	@RequestMapping(value = "/matchinfo/agreePayDate.do",method = RequestMethod.POST)
	public @ResponseBody String agreePayDate(HttpServletRequest request, String jsonlist){
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
			 sendmessage(WebSocketNeedBean.OBJ_TYPE_LIVE, null,String.valueOf(StateConstants.DEPT_XINGZHENG), null, null, "财务部确认支付日期学员成功，请去【网络培训费】查看");
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
	/**
	 * 到账学员删除(未匹配)
	 * @param request
	 * @param userid
	 * @param matchInfoIds
	 * @return
	 * @author likang 
	 * @date 2016-12-6 下午4:52:19
	 */
	@RequestMapping(value = "/matchinfo/deleteMatchinfos.do",method = RequestMethod.POST)
	public @ResponseBody String deleteMatchinfos(HttpServletRequest request,Long userid, String matchInfoIds){
		if (userid != null && matchInfoIds != null && !"".equals(matchInfoIds)) {
			matchinfoService.deleteMatchinfos(userid,matchInfoIds);
			return ReturnConstants.SUCCESS;
		}
		return ReturnConstants.PARAM_NULL;
	}
	
	
	
}
