package com.hjcrm.resource.util;

import java.io.IOException;
import java.io.OutputStream;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFClientAnchor;
import org.apache.poi.hssf.usermodel.HSSFComment;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFPatriarch;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;

import com.hjcrm.resource.entity.Student;

public class PublicExcelExportUtil {

	
	
	public static void exportExcelStudent(String menuCode,String title, String[] headers, List<Student> refer, OutputStream out) {
		// 声明一个工作薄
		HSSFWorkbook workbook = new HSSFWorkbook();
		// 生成一个表格
		HSSFSheet sheet = workbook.createSheet(title);
		// 设置表格默认列宽度为15个字节
		sheet.setDefaultColumnWidth((short) 20);
		// 生成一个样式
		HSSFCellStyle style = workbook.createCellStyle();
		// 设置这些样式
		style.setFillForegroundColor(HSSFColor.BRIGHT_GREEN.index);
		style.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		// 生成一个字体
		HSSFFont font = workbook.createFont();
		font.setColor(HSSFColor.VIOLET.index);
		font.setFontHeightInPoints((short) 12);
		font.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
		// 把字体应用到当前的样式
		style.setFont(font);
		// 生成并设置另一个样式
		HSSFCellStyle style2 = workbook.createCellStyle();
		style2.setFillForegroundColor(HSSFColor.WHITE.index);
		style2.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
		style2.setBorderBottom(HSSFCellStyle.BORDER_THIN);
		style2.setBorderLeft(HSSFCellStyle.BORDER_THIN);
		style2.setBorderRight(HSSFCellStyle.BORDER_THIN);
		style2.setBorderTop(HSSFCellStyle.BORDER_THIN);
		style2.setAlignment(HSSFCellStyle.ALIGN_CENTER);
		style2.setVerticalAlignment(HSSFCellStyle.VERTICAL_CENTER);
		// 生成另一个字体
		HSSFFont font2 = workbook.createFont();
		font2.setBoldweight(HSSFFont.BOLDWEIGHT_NORMAL);
		// 把字体应用到当前的样式
		style2.setFont(font2);

		// 声明一个画图的顶级管理器
		HSSFPatriarch patriarch = sheet.createDrawingPatriarch();
		// 定义注释的大小和位置,详见文档
		HSSFComment comment = patriarch.createComment(new HSSFClientAnchor(0, 0, 0, 0, (short) 4, 2, (short) 6, 5));
		// 设置注释内容
		comment.setString(new HSSFRichTextString("可以在POI中添加注释！"));
		// 设置注释作者，当鼠标移动到单元格上是可以在状态栏中看到该内容.
//		comment.setAuthor("leno");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<Student> it = refer.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Student t = (Student) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields1 = t.getClass().getDeclaredFields();
			Field[] fields = new Field[headers.length];
			
			for (int i = 0; i < fields1.length; i++) {
				Field field = fields1[i];
				String fieldName = field.getName();
				if (fieldName != null && !"".equals(fieldName)) {//
					if ("AFPtotal".equals(menuCode)) {//AFP总表导出
						if ("studentName".equals(fieldName)) {//姓名
							fields[0] = fields1[i];
							continue;
						} else if ("idCard".equals(fieldName)) {//身份证号
							fields[1] = fields1[i];
							continue;
						} else if ("phone".equals(fieldName)) {//手机
							fields[2] = fields1[i];
							continue;
						} else if ("tel".equals(fieldName)) {//固定电话
							fields[3] = fields1[i];
							continue;
						} else if ("email".equals(fieldName)) {//邮箱
							fields[4] = fields1[i];
							continue;
						} else if ("company".equals(fieldName)) {//单位
							fields[5] = fields1[i];
							continue;
						} else if ("companyAddr".equals(fieldName)) {//单位地址
							fields[6] = fields1[i];
							continue;
						} else if ("position".equals(fieldName)) {//职务
							fields[7] = fields1[i];
							continue;
						} else if ("school".equals(fieldName)) {//毕业院校
							fields[8] = fields1[i];
							continue;
						} else if ("education".equals(fieldName)) {//学历
							fields[9] = fields1[i];
							continue;
						} else if ("belongName".equals(fieldName)) {//招生老师
							fields[10] = fields1[i];
							continue;
						} else if ("arrive_money".equals(fieldName)) {//收款金额
							fields[11] = fields1[i];
							continue;
						} else if ("arrive_time".equals(fieldName)) {//收款日期
							fields[12] = fields1[i];
							continue;
						} else if ("remitWay".equals(fieldName)) {//汇款方式
							fields[13] = fields1[i];
							continue;
						} else if ("LCWname".equals(fieldName)) {//理财网用户名
							fields[14] = fields1[i];
							continue;
						} else if ("LCWpassword".equals(fieldName)) {//理财网密码
							fields[15] = fields1[i];
							continue;
						} else if ("courseversion".equals(fieldName)) {//课件
							fields[16] = fields1[i];
							continue;
						} else if ("invoiceinfo".equals(fieldName)) {//发票情况
							fields[17] = fields1[i];
							continue;
						} else if ("baokaopassword".equals(fieldName)) {//报考密码
							fields[18] = fields1[i];
							continue;
						} else if ("scoretime".equals(fieldName)) {//考试日期
							fields[19] = fields1[i];
							continue;
						} else if ("ispass".equals(fieldName)) {//通过情况
							fields[20] = fields1[i];
							continue;
						} else if ("isSignAgreement".equals(fieldName)) {//协议
							fields[21] = fields1[i];
							continue;
						} else if ("banci".equals(fieldName)) {//班次
							fields[22] = fields1[i];
							continue;
						} else if ("kefuNote1".equals(fieldName)) {//备注
							fields[23] = fields1[i];
							continue;
						} 
					}else if ("AFPhf".equals(menuCode)) {//APF回访导出
						if ("banci".equals(fieldName)) {//班次
							fields[0] = fields1[i];
							continue;
						} else if ("studentName".equals(fieldName)) {//姓名
							fields[1] = fields1[i];
							continue;
						} else if ("idCard".equals(fieldName)) {//身份证号
							fields[2] = fields1[i];
							continue;
						} else if ("phone".equals(fieldName)) {//手机号
							fields[3] = fields1[i];
							continue;
						} else if ("tel".equals(fieldName)) {//固定电话
							fields[4] = fields1[i];
							continue;
						} else if ("email".equals(fieldName)) {//邮箱
							fields[5] = fields1[i];
							continue;
						} else if ("company".equals(fieldName)) {//单位
							fields[6] = fields1[i];
							continue;
						} else if ("baokaopassword".equals(fieldName)) {//报考密码
							fields[7] = fields1[i];
							continue;
						} else if ("scoretime".equals(fieldName)) {//考试日期
							fields[8] = fields1[i];
							continue;
						} else if ("ispass".equals(fieldName)) {//通过情况
							fields[9] = fields1[i];
							continue;
						} else if ("belongName".equals(fieldName)) {//招生老师
							fields[10] = fields1[i];
							continue;
						} else if ("LCWoutTime".equals(fieldName)) {//理财网过期时间
							fields[11] = fields1[i];
							continue;
						} else if ("isSignAgreement".equals(fieldName)) {//协议
							fields[12] = fields1[i];
							continue;
						} else if ("weixin".equals(fieldName)) {//微信
							fields[13] = fields1[i];
							continue;
						} else if ("arrive_time".equals(fieldName)) {//到账日期
							fields[14] = fields1[i];
							continue;
						} else if ("classNum".equals(fieldName)) {//上课班号
							fields[15] = fields1[i];
							continue;
						} else if ("courseversion".equals(fieldName)) {//课件版本
							fields[16] = fields1[i];
							continue;
						} else if ("mailTime".equals(fieldName)) {//邮寄时间
							fields[17] = fields1[i];
							continue;
						} else if ("kefuNote1".equals(fieldName)) {//备注
							fields[18] = fields1[i];
							continue;
						}
					}else if("jijintotal".equals(menuCode) || "yincongtotal".equals(menuCode) || "zhongjitotal".equals(menuCode) 
							|| "zhengquantotal".equals(menuCode) || "qihuototal".equals(menuCode) || "kuaijitotal".equals(menuCode)|| "chujitotal".equals(menuCode)){
						if ("headmaster".equals(fieldName)) {//班主任
							fields[0] = fields1[i];
							continue;
						} else if ("customer_time".equals(fieldName)) {//资源分配时间
							fields[1] = fields1[i];
							continue;
						}else if ("studentName".equals(fieldName)) {//姓名
							fields[2] = fields1[i];
							continue;
						}else if ("idCard".equals(fieldName)) {//身份证
							fields[3] = fields1[i];
							continue;
						}else if ("phone".equals(fieldName)) {//手机号
							fields[4] = fields1[i];
							continue;
						}else if ("tel".equals(fieldName)) {//固定电话
							fields[5] = fields1[i];
							continue;
						}else if ("email".equals(fieldName)) {//邮箱
							fields[6] = fields1[i];
							continue;
						}else if ("company".equals(fieldName)) {//单位
							fields[7] = fields1[i];
							continue;
						}else if ("companyAddr".equals(fieldName)) {//地址
							fields[8] = fields1[i];
							continue;
						}else if ("position".equals(fieldName)) {//职务
							fields[9] = fields1[i];
							continue;
						}else if ("school".equals(fieldName)) {//毕业院校
							fields[10] = fields1[i];
							continue;
						}else if ("education".equals(fieldName)) {//学历
							fields[11] = fields1[i];
							continue;
						}else if ("nation".equals(fieldName)) {//民族
							fields[12] = fields1[i];
							continue;
						}else if ("belongName".equals(fieldName)) {//招生老师
							fields[13] = fields1[i];
							continue;
						}else if ("arrive_money".equals(fieldName)) {//收款金额
							fields[14] = fields1[i];
							continue;
						}else if ("arrive_time".equals(fieldName)) {//收款日期
							fields[15] = fields1[i];
							continue;
						}else if ("remitWay".equals(fieldName)) {//汇款方式
							fields[16] = fields1[i];
							continue;
						}else if ("subjectname".equals(fieldName)) {//科目
							fields[17] = fields1[i];
							continue;
						}
					}else if("jijinhf".equals(menuCode) || "yinconghf".equals(menuCode) || "zhongjihf".equals(menuCode) 
							|| "zhengquanhf".equals(menuCode) || "qihuohf".equals(menuCode) || "chujihf".equals(menuCode)){
						if ("address".equals(fieldName)) {//地区
							fields[0] = fields1[i];
						}else if ("studentName".equals(fieldName)) {//姓名
							fields[1] = fields1[i];
						}else if ("idCard".equals(fieldName)) {//身份证
							fields[2] = fields1[i];
						}else if ("phone".equals(fieldName)) {//手机号
							fields[3] = fields1[i];
						}else if ("subjectname".equals(fieldName)) {//所报科目
							fields[4] = fields1[i];
						}
					}
					if("jijinhf".equals(menuCode)){//基金回访
						if ("jijin1".equals(fieldName)) {//基金1
							fields[5] = fields1[i];
							continue;
						}else if ("jijin1Score".equals(fieldName)) {//考试成绩
							fields[6] = fields1[i];
							continue;
						}else if ("jijin2".equals(fieldName)) {//基金2
							fields[7] = fields1[i];
							continue;
						}else if ("jijin2Score".equals(fieldName)) {//考试成绩
							fields[8] = fields1[i];
							continue;
						}
					}else if("yinconghf".equals(menuCode)){//银从回访
						if ("flfg".equals(fieldName)) {//法律法规与综合能力
							fields[5] = fields1[i];
							continue;
						}else if ("flfgScore".equals(fieldName)) {//考试成绩
							fields[6] = fields1[i];
							continue;
						}else if ("grlc".equals(fieldName)) {//个人理财
							fields[7] = fields1[i];
							continue;
						}else if ("grlcScore".equals(fieldName)) {//考试成绩
							fields[8] = fields1[i];
							continue;
						}else if ("fxgl".equals(fieldName)) {//风险管理
							fields[9] = fields1[i];
							continue;
						}else if ("fxglScore".equals(fieldName)) {//考试成绩
							fields[10] = fields1[i];
							continue;
						}else if ("gsxd".equals(fieldName)) {//公司信贷
							fields[11] = fields1[i];
							continue;
						}else if ("gsxdScore".equals(fieldName)) {//考试成绩
							fields[12] = fields1[i];
							continue;
						}else if ("grdk".equals(fieldName)) {//个人贷款
							fields[13] = fields1[i];
							continue;
						}else if ("grdkScore".equals(fieldName)) {//考试成绩
							fields[14] = fields1[i];
							continue;
						}
					}else if("zhongjihf".equals(menuCode)){//中级回访
						if ("jjjczd".equals(fieldName)) {//经济基础知识
							fields[5] = fields1[i];
							continue;
						}else if ("jjjczdScore".equals(fieldName)) {//考试成绩
							fields[6] = fields1[i];
							continue;
						}else if ("jrzy".equals(fieldName)) {//金融专业知识与实务
							fields[7] = fields1[i];
							continue;
						}else if ("jrzyScore".equals(fieldName)) {//考试成绩
							fields[8] = fields1[i];
							continue;
						}else if ("gsgl".equals(fieldName)) {//工商管理专业知识与实务
							fields[9] = fields1[i];
							continue;
						}else if ("gsglScore".equals(fieldName)) {//考试成绩
							fields[10] = fields1[i];
							continue;
						}else if ("czss".equals(fieldName)) {//财政税收专业知识与实务
							fields[11] = fields1[i];
							continue;
						}else if ("czssScore".equals(fieldName)) {//考试成绩
							fields[12] = fields1[i];
							continue;
						}
					}else if("zhengquanhf".equals(menuCode)){//证券回访
						if ("zqsc".equals(fieldName)) {//证券市场基本法律法规
							fields[5] = fields1[i];
							continue;
						}else if ("zqscScore".equals(fieldName)) {//考试成绩
							fields[6] = fields1[i];
							continue;
						}else if ("jrsc".equals(fieldName)) {//金融市场基础知识
							fields[7] = fields1[i];
							continue;
						}else if ("jrscScore".equals(fieldName)) {//考试成绩
							fields[8] = fields1[i];
							continue;
						}
					}else if("qihuohf".equals(menuCode)){//期货回访
						if ("qhflfg".equals(fieldName)) {//期货法律法规
							fields[5] = fields1[i];
							continue;
						}else if ("qhflfgScore".equals(fieldName)) {//考试成绩
							fields[6] = fields1[i];
							continue;
						}else if ("qhjczs".equals(fieldName)) {//期货基础知识
							fields[7] = fields1[i];
							continue;
						}else if ("qhjczsScore".equals(fieldName)) {//考试成绩
							fields[8] = fields1[i];
							continue;
						}
					}else if("kuaijihf".equals(menuCode)){//会计从业回访
						if ("headmaster".equals(fieldName)) {//班主任
							fields[0] = fields1[i];
						}else if ("address".equals(fieldName)) {//地区
							fields[1] = fields1[i];
						}else if ("studentName".equals(fieldName)) {//姓名
							fields[2] = fields1[i];
						}else if ("idCard".equals(fieldName)) {//身份证
							fields[3] = fields1[i];
						}else if ("phone".equals(fieldName)) {//手机号
							fields[4] = fields1[i];
						}else if ("email".equals(fieldName)) {//邮箱
							fields[5] = fields1[i];
						}else if ("belongName".equals(fieldName)) {//招生老师
							fields[6] = fields1[i];
						}else if ("baokaopassword".equals(fieldName)) {//报考密码
							fields[7] = fields1[i];
						}else if ("weixin".equals(fieldName)) {//是否微信
							fields[8] = fields1[i];
						}else if ("courseversion".equals(fieldName)) {//课件打印
							fields[9] = fields1[i];
						}else if ("arrive_money".equals(fieldName)) {//收款金额
							fields[10] = fields1[i];
						}else if ("arrive_time".equals(fieldName)) {//收款日期
							fields[11] = fields1[i];
						}else if ("scoretime".equals(fieldName)) {//考试日期
							fields[12] = fields1[i];
						}else if ("subjectname".equals(fieldName)) {//所报科目
							fields[13] = fields1[i];
						}else if ("cjfg".equals(fieldName)) {//财经法规与会计职业道德
							fields[14] = fields1[i];
							continue;
						}else if ("cjfgScore".equals(fieldName)) {//考试成绩
							fields[15] = fields1[i];
							continue;
						}else if ("kjjc".equals(fieldName)) {//会计基础
							fields[16] = fields1[i];
							continue;
						}else if ("kjjcScore".equals(fieldName)) {//考试成绩
							fields[17] = fields1[i];
							continue;
						}
					}else if("chujihf".equals(menuCode)){//初级会计回访
						if ("cjkj".equals(fieldName)) {//初级会计实务
							fields[5] = fields1[i];
							continue;
						}else if ("cjkjScore".equals(fieldName)) {//考试成绩
							fields[6] = fields1[i];
							continue;
						}else if ("jjfjc".equals(fieldName)) {//经济法基础
							fields[7] = fields1[i];
							continue;
						}else if ("jjfjcScore".equals(fieldName)) {//考试成绩
							fields[8] = fields1[i];
							continue;
						}
					}else if("CFPtotal".equals(menuCode)){//CFP总表导出
						if ("headmaster".equals(fieldName)) {//班主任
							fields[0] = fields1[i];
							continue;
						}else if ("studentName".equals(fieldName)) {//姓名
							fields[1] = fields1[i];
							continue;
						}else if ("idCard".equals(fieldName)) {//身份证
							fields[2] = fields1[i];
							continue;
						}else if ("phone".equals(fieldName)) {//手机
							fields[3] = fields1[i];
							continue;
						}else if ("tel".equals(fieldName)) {//固定电话
							fields[4] = fields1[i];
							continue;
						}else if ("email".equals(fieldName)) {//邮箱
							fields[5] = fields1[i];
							continue;
						}else if ("company".equals(fieldName)) {//单位
							fields[6] = fields1[i];
							continue;
						}else if ("companyAddr".equals(fieldName)) {//地址
							fields[7] = fields1[i];
							continue;
						}else if ("position".equals(fieldName)) {//职务
							fields[8] = fields1[i];
							continue;
						}else if ("subjectname".equals(fieldName)) {//科目
							fields[9] = fields1[i];
							continue;
						}else if ("touzi".equals(fieldName)) {//投资
							fields[10] = fields1[i];
							continue;
						}else if ("shuiwu".equals(fieldName)) {//税务
							fields[11] = fields1[i];
							continue;
						}else if ("fuli".equals(fieldName)) {//福利
							fields[12] = fields1[i];
							continue;
						}else if ("baoxian".equals(fieldName)) {//保险
							fields[13] = fields1[i];
							continue;
						}else if ("zonghe".equals(fieldName)) {//综合
							fields[14] = fields1[i];
							continue;
						}else if ("arrive_money".equals(fieldName)) {//收款金额
							fields[15] = fields1[i];
							continue;
						}else if ("arrive_time".equals(fieldName)) {//收款日期
							fields[16] = fields1[i];
							continue;
						}else if ("remitWay".equals(fieldName)) {//汇款方式
							fields[17] = fields1[i];
							continue;
						}else if ("LCWname".equals(fieldName)) {//LCW用户名
							fields[18] = fields1[i];
							continue;
						}else if ("LCWpassword".equals(fieldName)) {//LCW密码
							fields[19] = fields1[i];
							continue;
						}else if ("belongName".equals(fieldName)) {//招生老师
							fields[20] = fields1[i];
							continue;
						}else if ("isAllPass".equals(fieldName)) {//是否全科通过
							fields[21] = fields1[i];
							continue;
						}else if ("baokaopassword".equals(fieldName)) {//报考密码
							fields[22] = fields1[i];
							continue;
						}else if ("banci".equals(fieldName)) {//班级
							fields[23] = fields1[i];
							continue;
						}else if ("customer_time".equals(fieldName)) {//分配时间
							fields[24] = fields1[i];
							continue;
						}else if ("isjieye".equals(fieldName)) {//是否协助结业
							fields[25] = fields1[i];
							continue;
						}
					}else if("CFPhf".equals(menuCode)){//CFP回访导出
						if ("banci".equals(fieldName)) {//班级
							fields[0] = fields1[i];
							continue;
						}else if ("studentName".equals(fieldName)) {//姓名
							fields[1] = fields1[i];
							continue;
						}else if ("idCard".equals(fieldName)) {//身份证
							fields[2] = fields1[i];
							continue;
						}else if ("phone".equals(fieldName)) {//手机
							fields[3] = fields1[i];
							continue;
						}else if ("tel".equals(fieldName)) {//固定电话
							fields[4] = fields1[i];
							continue;
						}else if ("email".equals(fieldName)) {//邮箱
							fields[5] = fields1[i];
							continue;
						}else if ("company".equals(fieldName)) {//单位
							fields[6] = fields1[i];
							continue;
						}else if ("belongName".equals(fieldName)) {//招生老师
							fields[7] = fields1[i];
							continue;
						}else if ("baokaopassword".equals(fieldName)) {//报名密码
							fields[8] = fields1[i];
							continue;
						}else if ("subjectname".equals(fieldName)) {//所报科目
							fields[9] = fields1[i];
							continue;
						}else if ("touzi".equals(fieldName)) {//投资
							fields[10] = fields1[i];
							continue;
						}else if ("touziExamDate".equals(fieldName)) {//投资考试日期
							fields[11] = fields1[i];
							continue;
						}else if ("shuiwu".equals(fieldName)) {//税务
							fields[12] = fields1[i];
							continue;
						}else if ("shuiwuExamDate".equals(fieldName)) {//税务考试日期
							fields[13] = fields1[i];
							continue;
						}else if ("fuli".equals(fieldName)) {//福利
							fields[14] = fields1[i];
							continue;
						}else if ("fuliExamDate".equals(fieldName)) {//福利考试日期
							fields[15] = fields1[i];
							continue;
						}else if ("baoxian".equals(fieldName)) {//保险
							fields[16] = fields1[i];
							continue;
						}else if ("baoxianExamDate".equals(fieldName)) {//保险考试日期
							fields[17] = fields1[i];
							continue;
						}else if ("zonghe".equals(fieldName)) {//综合
							fields[18] = fields1[i];
							continue;
						}else if ("zongheExamDate".equals(fieldName)) {//综合考试日期
							fields[19] = fields1[i];
							continue;
						}else if ("arrive_money".equals(fieldName)) {//收款金额
							fields[20] = fields1[i];
							continue;
						}else if ("arrive_time".equals(fieldName)) {//收款日期
							fields[21] = fields1[i];
							continue;
						}else if ("courseversion".equals(fieldName)) {//课件版本
							fields[22] = fields1[i];
							continue;
						}else if ("mailTime".equals(fieldName)) {//邮寄时间
							fields[23] = fields1[i];
							continue;
						}else if ("LCWoutTime".equals(fieldName)) {//理财网过期时间
							fields[24] = fields1[i];
							continue;
						}else if ("weixin".equals(fieldName)) {//微信
							fields[25] = fields1[i];
							continue;
						}
					}
				}
			}
			
			for (short i = 0; i < fields.length; i++) {
				HSSFCell cell = row.createCell(i);
				cell.setCellStyle(style2);
				Field field = fields[i];
				String fieldName = field.getName();
				String getMethodName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
				try {
					Class tCls = t.getClass();
					Method getMethod = tCls.getMethod(getMethodName, new Class[] {});
					Object value = getMethod.invoke(t, new Object[] {});
					// 判断值的类型后进行强制类型转换
					String textValue = null;
					if (fieldName.equals("source")) {
						if (value != null && !"".equals(value)) {
							if (value.equals(0)) {
								value = "自建";
							}else if (value.equals(1)) {
								value = "课程注册";
							}else if (value.equals(2)) {
								value = "在线注册";
							}else if (value.equals(3)) {
								value = "app下载注册";
							}else if (value.equals(4)) {
								value = "电话咨询";
							}else if (value.equals(5)) {
								value = "金考网注册用户";
							}else if (value.equals(6)) {
								value = "线上渠道";
							}else if (value.equals(7)) {
								value = "在线咨询";
							}else if (value.equals(8)) {
								value = "大库资源";
							}else if (value.equals(9)) {
								value = "在线购买";
							}
						}
					}
					if (fieldName.equals("state")) {
						if (value != null && !"".equals(value)) {
							if (value.equals(0)) {
								value = "未分配";
							}else if (value.equals(1)) {
								value = "已分配";
							}else if (value.equals(2)) {
								value = "未处理";
							}else if (value.equals(3)) {
								value = "已处理";
							}
						}
					}
					if (fieldName.equals("sex")) {
						if (value != null && !"".equals(value)) {
							if (value.equals(0)) {
								value = "男";
							}else if (value.equals(1)) {
								value = "女";
							}
						}
					}
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
						textValue = sdf.format(date);
					} else if (value instanceof byte[]) {
						// 有图片时，设置行高为60px;
						row.setHeightInPoints(60);
						// 设置图片所在列宽度为80px,注意这里单位的一个换算
						sheet.setColumnWidth(i, (short) (35.7 * 80));
						// sheet.autoSizeColumn(i);
						byte[] bsValue = (byte[]) value;
						HSSFClientAnchor anchor = new HSSFClientAnchor(0, 0, 1023, 255, (short) 6, index, (short) 6,
								index);
						anchor.setAnchorType(2);
						patriarch.createPicture(anchor, workbook.addPicture(bsValue, HSSFWorkbook.PICTURE_TYPE_JPEG));
					} else {
						if (value == null || "".equals(value)) {
							textValue = "";
						} else {
							// 其它数据类型都当作字符串简单处理
							textValue = value.toString();
						}
					}
					// 如果不是图片数据，就利用正则表达式判断textValue是否全部由数字组成
					if (textValue != null) {
						Pattern p = Pattern.compile("^//d+(//.//d+)?$");
						Matcher matcher = p.matcher(textValue);
						if (matcher.matches()) {
							// 是数字当作double处理
							cell.setCellValue(Double.parseDouble(textValue));
						} else {
							HSSFRichTextString richString = new HSSFRichTextString(textValue);
							cell.setCellValue(richString);
						}
					}
				} catch (SecurityException e) {
					e.printStackTrace();
				} catch (NoSuchMethodException e) {
					e.printStackTrace();
				} catch (IllegalArgumentException e) {
					e.printStackTrace();
				} catch (IllegalAccessException e) {
					e.printStackTrace();
				} catch (InvocationTargetException e) {
					e.printStackTrace();
				} finally {
					// 清理资源
				}
			}
		}
		try {
			workbook.write(out);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	
}
