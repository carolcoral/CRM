package com.hjcrm.resource.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFDataFormat;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hjcrm.publics.constants.ReturnConstants;
import com.hjcrm.publics.util.BaseController;
import com.hjcrm.resource.entity.Dealrecord;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.resource.service.IResourceService;
import com.hjcrm.resource.service.IStudentService;
import com.hjcrm.system.entity.User;
import com.hjcrm.system.service.IUserService;

/**
 * 数据导入工具类
 * @author likang
 * @date 2016-12-29 下午5:16:30
 */
@Controller
public class ImportDataUtil extends BaseController{
	
	
	private static POIFSFileSystem fs;
	private static HSSFWorkbook wb;
	private static HSSFSheet sheet;
	private static HSSFRow row;

	@Autowired
	private IUserService userService;
	@Autowired
	private IStudentService studentService;
	@Autowired
	private IResourceService resourceService;

	
	@RequestMapping(value = "/data/jijinimportDataUtil.do",method = RequestMethod.GET)
	public @ResponseBody String excelImport(HttpServletRequest request){
			File destFile = new File("D://histroydata//duanxuejijin.xls");
			try {
				//读取excel 
				FileInputStream is = new FileInputStream(destFile); // 文件流
				Map<String,Object> returnMaps = readExcelContent(is);//读取excel
				if (returnMaps.get("listdata")!= null) {
					//写入数据库
					List<Student> listresource = new ArrayList<Student>();
					List<Map> listmap = new ArrayList<Map>();
					String str = null;
					for (int i = 0; i < returnMaps.size(); i++) {
						listmap = (List<Map>) returnMaps.get("listdata");
						for (int j = 0; j < listmap.size(); j++) {
							Student student = new Student();
							str = listmap.get(j).get("studentName")!= null && !"".equals(listmap.get(j).get("studentName"))?listmap.get(j).get("studentName").toString().trim():null;
							student.setStudentName(str);
							str = null;
							str = listmap.get(j).get("idCard")!= null && !"".equals(listmap.get(j).get("idCard"))?listmap.get(j).get("idCard").toString().trim():null;
							student.setIdCard(str);
							str = null;
							
							str = listmap.get(j).get("phone")!= null && !"".equals(listmap.get(j).get("phone"))?listmap.get(j).get("phone").toString().trim():null;
							student.setPhone(str);
							str = null;
							
							str = listmap.get(j).get("tel")!= null && !"".equals(listmap.get(j).get("tel"))?listmap.get(j).get("tel").toString().trim():null;
							student.setTel(str);
							str = null;
							
							str = listmap.get(j).get("email")!= null && !"".equals(listmap.get(j).get("email"))?listmap.get(j).get("email").toString().trim():null;
							student.setEmail(str);
							str = null;
							
							str = listmap.get(j).get("company")!= null && !"".equals(listmap.get(j).get("company"))?listmap.get(j).get("company").toString().trim():null;
							student.setCompany(str);
							str = null;
							
							str = listmap.get(j).get("companyAddr")!= null && !"".equals(listmap.get(j).get("companyAddr"))?listmap.get(j).get("companyAddr").toString().trim():null;
							student.setCompanyAddr(str);
							str = null;
							
							str = listmap.get(j).get("position")!= null && !"".equals(listmap.get(j).get("position"))?listmap.get(j).get("position").toString().trim():null;
							student.setPosition(str);
							str = null;
							
							str = listmap.get(j).get("school")!= null && !"".equals(listmap.get(j).get("school"))?listmap.get(j).get("school").toString().trim():null;
							student.setSchool(str);
							str = null;
							
							str = listmap.get(j).get("education")!= null && !"".equals(listmap.get(j).get("education"))?listmap.get(j).get("education").toString().trim():null;
							student.setEducation(str);
							str = null;
							
							str = listmap.get(j).get("nation")!= null && !"".equals(listmap.get(j).get("nation"))?listmap.get(j).get("nation").toString().trim():null;
							student.setNation(str);
							str = null;
							
							str = listmap.get(j).get("belongName")!= null && !"".equals(listmap.get(j).get("belongName"))?listmap.get(j).get("belongName").toString().trim():null;
							if (str == null || "".equals(str.trim())) {
								student.setBelongid(null);
								student.setUserid(null);
							}else{
								User user = userService.queryUserByUsername(str);
								if (user != null) {
									student.setBelongid(user.getUserid());
									student.setUserid(user.getUserid());
								}
							}
							str = null;
							
							str = listmap.get(j).get("dealprice")!= null && !"".equals(listmap.get(j).get("dealprice"))?listmap.get(j).get("dealprice").toString().trim():null;
							student.setArrive_money(str);
							student.setDealprice(str);
							str = null;
							
							str = listmap.get(j).get("arrive_time")!= null && !"".equals(listmap.get(j).get("arrive_time"))?listmap.get(j).get("arrive_time").toString().trim():null;
							student.setArrive_time(str);
							str = null;
							
							str = listmap.get(j).get("remitWay")!= null && !"".equals(listmap.get(j).get("remitWay"))?listmap.get(j).get("remitWay").toString().trim():null;
							student.setRemitWay(str);
							str = null;
							
							
							str = listmap.get(j).get("courseid")!= null && !"".equals(listmap.get(j).get("courseid"))?listmap.get(j).get("courseid").toString().trim():null;
							student.setCourseid(Integer.valueOf(str));
							str = null;
							
							str = listmap.get(j).get("jijin1")!= null && !"".equals(listmap.get(j).get("jijin1"))?listmap.get(j).get("jijin1").toString().trim():null;
							student.setJijin1(str);
							str = null;
							
							str = listmap.get(j).get("jijin1ispass")!= null && !"".equals(listmap.get(j).get("jijin1ispass"))?listmap.get(j).get("jijin1ispass").toString().trim():null;
							student.setJijin1Score(str);
							str = null;
							
							str = listmap.get(j).get("jijin2")!= null && !"".equals(listmap.get(j).get("jijin2"))?listmap.get(j).get("jijin2").toString().trim():null;
							student.setJijin2(str);
							str = null;
							
							str = listmap.get(j).get("jijin2ispass")!= null && !"".equals(listmap.get(j).get("jijin2ispass"))?listmap.get(j).get("jijin2ispass").toString().trim():null;
							student.setJijin2Score(str);
							str = null;
							
							str = listmap.get(j).get("kefuNote1")!= null && !"".equals(listmap.get(j).get("kefuNote1"))?listmap.get(j).get("kefuNote1").toString().trim():null;
							student.setKefuNote1(str);
							str = null;
							
							
							listresource.add(student);
						}
					}
					for (int i = 0; i < listresource.size(); i++) {//写入数据库
						listresource.get(i).setIsolddata(64);
						
						if (listresource.get(i).getJijin1() != null) {
							if (listresource.get(i).getJijin2() != null) {
								if (listresource.get(i).getJijin1Score() != null && listresource.get(i).getJijin2Score() != null) {
									listresource.get(i).setStudentstate(6);
									listresource.get(i).setIspass(1);
								}else{
									listresource.get(i).setStudentstate(4);
								}
							}else{
								if (listresource.get(i).getJijin1Score() != null) {
									listresource.get(i).setStudentstate(6);
									listresource.get(i).setIspass(1);
								}else{
									listresource.get(i).setStudentstate(4);
								}
							}
						}else{
							if (listresource.get(i).getJijin2() != null) {
								if (listresource.get(i).getJijin2Score() != null) {
									listresource.get(i).setStudentstate(6);
									listresource.get(i).setIspass(1);
								}else{
									listresource.get(i).setStudentstate(4);
								}
							}
						}
						listresource.get(i).setCustomerId(64L);
						studentService.saveOrUpdate(listresource.get(i));
						Student studene1 =studentService.queryStudentByphone(listresource.get(i).getPhone());
						if (listresource.get(i).getJijin1() != null) {
							Dealrecord dc = new Dealrecord();
							dc.setStudentId(studene1.getStudentId());
							dc.setCourseid(Long.valueOf(listresource.get(i).getCourseid()));
							dc.setSubjectid(Long.valueOf(listresource.get(i).getJijin1()));
							dc.setNote("客服基金历史数据导入64");
							if (listresource.get(i).getJijin1Score() != null) {
								dc.setIspass(1);
							}else{
								dc.setIspass(0);
							}
							resourceService.saveOrUpdate(dc);
						}
						if (listresource.get(i).getJijin2() != null) {
							Dealrecord dc = new Dealrecord();
							dc.setStudentId(studene1.getStudentId());
							dc.setCourseid(Long.valueOf(listresource.get(i).getCourseid()));
							dc.setSubjectid(Long.valueOf(listresource.get(i).getJijin2()));
							dc.setNote("客服基金历史数据导入64");
							if (listresource.get(i).getJijin2Score() != null) {
								dc.setIspass(1);
							}else{
								dc.setIspass(0);
							}
							resourceService.saveOrUpdate(dc);
						}
						System.out.println("导入数：" + i);
					}
					System.out.println("导入数据个数：" + listresource.size());
					return ReturnConstants.SUCCESS;
				}
			} catch (Exception e) {
				e.printStackTrace();
			} 
		return ReturnConstants.PARAM_NULL;
	}
	

	/**
	 * 读取Excel表格表头的内容
	 * 
	 * @param is
	 * @return 表头内容的数组String类型
	 * @author likang
	 * @date 2016-11-7 上午9:58:13
	 */
	public String[] readExcelTitle(InputStream is) {
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		sheet = wb.getSheetAt(0);
		row = sheet.getRow(0);
		// 标题总列数
		int colNum = row.getPhysicalNumberOfCells();
		String[] title = new String[colNum];
		for (int i = 0; i < colNum; i++) {
			title[i] = getCellFormatValue(row.getCell((short) i));
		}
		return title;
	}
	private static String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {// 判断当前Cell的Type
			case HSSFCell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_FORMULA: {
				cell.getDateCellValue();
				boolean istrue = HSSFDateUtil.isCellDateFormatted(cell);
				if (istrue) {// 判断当前的cell是否为Date
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else {
					DecimalFormat df = new DecimalFormat("0");
					cellvalue = df.format(cell.getNumericCellValue());
				}
				break;
			}
			case HSSFCell.CELL_TYPE_STRING:// 如果当前Cell的Type为STRIN
				cellvalue = cell.getRichStringCellValue().getString();// 取得当前的Cell字符串
				break;
			default:
				cellvalue = " ";// 默认的Cell值
			}
		} else {
			cellvalue = "";
		}
		return cellvalue;
	}

	public static Map<String,Object> readExcelContent(InputStream is) {
		Map<String, Object> maps = new HashMap<String, Object>();
		try {
			fs = new POIFSFileSystem(is);
			wb = new HSSFWorkbook(fs);
		} catch (IOException e) {
			e.printStackTrace();
		}
		List<Map> list = new ArrayList<Map>();
		sheet = wb.getSheetAt(0);
		int rowNum = sheet.getLastRowNum();// 总行数
		row = sheet.getRow(0);
		if (row != null) {
			String str = null;
			int colNum = row.getPhysicalNumberOfCells();//总列数
			int j = 0;
			for (int i = 1; i <= rowNum; i++) {// 正文内容应该从第二行开始,第一行为表头的标题
				Map<String, String> content = new HashMap<String, String>();
				row = sheet.getRow(i);
				while (j < colNum) {
					str = getCellFormatValue(row.getCell((short) j)).trim();
					if (j == 0) {
						content.put("studentName", str);//姓名
					}else if(j == 1){
						content.put("idCard", str);//身份证
					}else if(j == 2){
						content.put("phone", str);//电话
					}else if(j == 3){
						content.put("tel", str);//固定电话
					}else if(j == 4){
						content.put("email", str);//邮箱
					}else if(j == 5){
						content.put("company", str);//单位
					}else if(j == 6){
						content.put("companyAddr", str);//地址
					}else if(j == 7){
						content.put("position", str);//职务
					}else if(j == 8){
						content.put("school", str);//毕业院校
					}else if(j == 9){
						content.put("education", str);//学历
					}else if(j == 10){
						content.put("nation", str);//民族
					}else if(j == 11){
						content.put("belongName", str);//招生老师
					}else if(j == 12){
						content.put("dealprice", str);//成交金额
					}else if(j == 13){
						content.put("arrive_time", str);//到账日期
					}else if(j == 14){
						content.put("remitWay", str);//支付方式
					}else if(j == 15){
						content.put("courseid", str);//课程
					}else if(j == 16){
						content.put("jijin1", str);//基金1
					}else if(j == 17){
						content.put("jijin1ispass", str);//是否通过
					}else if(j == 18){
						content.put("jijin2", str);//基金2
					}else if(j == 19){
						content.put("jijin2ispass", str);//是否通过
					}else if(j == 20){
						content.put("kefuNote1", str);//客服备注
					}
					j++;
					str = null;
				}
				j=0;
				list.add(content);
			}
			if (list.size() > 0) {
				maps.put("listdata", list);
			}else{
				maps.put("listdata", null);
			}
		}
		return maps;
	}
	
	
}
