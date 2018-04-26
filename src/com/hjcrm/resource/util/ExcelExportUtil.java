package com.hjcrm.resource.util;

import java.io.File;
import java.io.FileOutputStream;
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

import com.hjcrm.publics.constants.StateConstants;
import com.hjcrm.resource.entity.Resource;
import com.hjcrm.resource.entity.Student;
import com.hjcrm.resource.entity.Transferrecord;

public class ExcelExportUtil {

	
	public static void exportExcel(String title, String[] headers, List<Resource> refer, OutputStream out) {
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
//		comment.setAuthor("likang");
		// 产生表格标题行
		HSSFRow row = sheet.createRow(0);
		for (short i = 0; i < headers.length; i++) {
			HSSFCell cell = row.createCell(i);
			cell.setCellStyle(style);
			HSSFRichTextString text = new HSSFRichTextString(headers[i]);
			cell.setCellValue(text);
		}
		// 遍历集合数据，产生数据行
		Iterator<Resource> it = refer.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Resource t = (Resource) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields1 = t.getClass().getDeclaredFields();
			Field[] fields = new Field[headers.length];
			for (int i = 0; i < fields1.length; i++) {
				Field field = fields1[i];
				String fieldName = field.getName();
				if (fieldName != null && !"".equals(fieldName)) {
					if ("create_time".equals(fieldName)) {//创建时间
						fields[0] = fields1[i];
						continue;
					}else if ("state".equals(fieldName)) {//资源状态  0未分配 1已分配(未处理) 2已处理
						fields[1] = fields1[i];
						continue;
					}else if ("createrName".equals(fieldName)) {//创建人id
						fields[2] = fields1[i];
						continue;
					}else if ("belongName".equals(fieldName)) {//分配销售人员ID
						fields[3] = fields1[i];
						continue;
					}else if ("source".equals(fieldName)) {//渠道: 0自建  1课程注册  2在线注册  3app下载注册   4电话咨询   5金考网注册用户   6线上渠道  7在线咨询  8大库资源
						fields[4] = fields1[i];
						continue;
					}else if ("address".equals(fieldName)) {//地区
						fields[5] = fields1[i];
						continue;
					}else if ("resourceName".equals(fieldName)) {//姓名
						fields[6] = fields1[i];
						continue;
					}else if ("phone".equals(fieldName)) {//手机
						fields[7] = fields1[i];
						continue;
					}else if ("courseName".equals(fieldName)) {//课程
						fields[8] = fields1[i];
						continue;
					}else if ("yunYingNote".equals(fieldName)) {//运营备注
						fields[9] = fields1[i];
						continue;
					}else if ("firstVisitTime".equals(fieldName)) {//第一次回访时间
						fields[10] = fields1[i];
						continue;
					}else if ("resourceLevel".equals(fieldName)) {//资源等级 A B C D
						fields[11] = fields1[i];
						continue;
					}else if ("visitRecord".equals(fieldName)) {//最近回访记录
						fields[12] = fields1[i];
						continue;
					}else if ("visitCount".equals(fieldName)) {//咨询次数
						fields[13] = fields1[i];
						continue;
					}else if ("tel".equals(fieldName)) {//座机
						fields[14] = fields1[i];
						continue;
					}else if ("weixin".equals(fieldName)) {//微信
						fields[15] = fields1[i];
						continue;
					}else if ("qq".equals(fieldName)) {//QQ
						fields[16] = fields1[i];
						continue;
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
							}else if (value.equals(10)) {
								value = "继续教育";
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
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
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
	
	
	public static void exportExcelStudent(Long deptid,String title, String[] headers, List<Student> refer, OutputStream out) {
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
				if (fieldName != null && !"".equals(fieldName)) {
					if (deptid.longValue() == StateConstants.DEPT_YUNYING.longValue()) {//运营部学员管理
						if ("dealtime".equals(fieldName)) {//成交时间
							fields[0] = fields1[i];
							continue;
						}else if ("arrive_time".equals(fieldName)) {//到账时间
							fields[1] = fields1[i];
							continue;
						}else if ("belongName".equals(fieldName)) {//所属销售人
							fields[2] = fields1[i];
							continue;
						}else if ("studentName".equals(fieldName)) {//学员姓名
							fields[3] = fields1[i];
							continue;
						}else if ("source".equals(fieldName)) {//渠道
							fields[4] = fields1[i];
							continue;
						}else if ("phone".equals(fieldName)) {//客户手机
							fields[5] = fields1[i];
							continue;
						}else if ("courseName".equals(fieldName)) {//报名课程
							fields[6] = fields1[i];
							continue;
						}else if ("subjectname".equals(fieldName)) {//科目
							fields[7] = fields1[i];
							continue;
						}else if ("dealprice".equals(fieldName)) {//成交金额
							fields[8] = fields1[i];
							continue;
						}
					}
					if (deptid.longValue() == StateConstants.DEPT_XINGZHENG.longValue() || deptid.longValue() == StateConstants.DEPT_CAIWU.longValue()) {//到账信息(行政部,财务部)
						if ("customer_time".equals(fieldName)) {//分配客服时间
							fields[0] = fields1[i];
							continue;
						}else if ("matchinfo_time".equals(fieldName)) {//确认到账时间
							fields[1] = fields1[i];
							continue;
						}else if ("studentName".equals(fieldName)) {//姓名
							fields[2] = fields1[i];
							continue;
						}else if ("idCard".equals(fieldName)) {//身份证
							fields[3] = fields1[i];
							continue;
						}else if ("phone".equals(fieldName)) {//手机
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
						}else if ("isjieye".equals(fieldName)) {//民族
							fields[12] = fields1[i];
							continue;
						}else if ("belongName".equals(fieldName)) {//招生老师
							fields[13] = fields1[i];
							continue;
						}else if ("remituser".equals(fieldName)) {//代汇款人
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
						}else if ("courseversion".equals(fieldName)) {//课件版本
							fields[20] = fields1[i];
							continue;
						}else if ("invoiceinfo".equals(fieldName)) {//发票情况
							fields[21] = fields1[i];
							continue;
						}else if ("baokaopassword".equals(fieldName)) {//报考密码
							fields[22] = fields1[i];
							continue;
						}else if ("isSignAgreement".equals(fieldName)) {//协议
							fields[23] = fields1[i];
							continue;
						}else if ("banci".equals(fieldName)) {//班次
							fields[24] = fields1[i];
							continue;
						}else if ("qici".equals(fieldName)) {//期次
							fields[25] = fields1[i];
							continue;
						}else if ("preferinfo".equals(fieldName)) {//优惠信息
							fields[26] = fields1[i];
							continue;
						}else if ("courseName".equals(fieldName)) {//课程
							fields[27] = fields1[i];
							continue;
						}else if ("subjectname".equals(fieldName)) {//科目
							fields[28] = fields1[i];
							continue;
						}else if ("afp".equals(fieldName)) {//AFP是否通过
							fields[29] = fields1[i];
							continue;
						}else if ("touzi".equals(fieldName)) {//投资
							fields[30] = fields1[i];
							continue;
						}else if ("baoxian".equals(fieldName)) {//保险
							fields[31] = fields1[i];
							continue;
						}else if ("shuiwu".equals(fieldName)) {//税务
							fields[32] = fields1[i];
							continue;
						}else if ("fuli".equals(fieldName)) {//福利
							fields[33] = fields1[i];
							continue;
						}else if ("zonghe".equals(fieldName)) {//综合
							fields[34] = fields1[i];
							continue;
						}else if ("dealprice".equals(fieldName)) {//我司收入
							fields[35] = fields1[i];
							continue;
						}else if ("tuifei".equals(fieldName)) {//退费
							fields[36] = fields1[i];
							continue;
						}else if ("tuifeitime".equals(fieldName)) {//退费日期
							fields[37] = fields1[i];
							continue;
						}else if ("netedumoney".equals(fieldName)) {//网络培训费
							fields[38] = fields1[i];
							continue;
						}else if ("paytime".equals(fieldName)) {//支付日期
							fields[39] = fields1[i];
							continue;
						}else if ("shenhe".equals(fieldName)) {//审核问题
							fields[40] = fields1[i];
							continue;
						}else if ("caiwunote".equals(fieldName)) {//财务备注
							fields[41] = fields1[i];
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
					if (fieldName.equals("isjieye")) {
						if (value != null && !"".equals(value)) {
							if (value.equals(0)) {
								value = "否";
							}else if (value.equals(1)) {
								value = "是";
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
						if(deptid != null && deptid.longValue() == StateConstants.DEPT_YUNYING.longValue()){
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
							textValue = sdf.format(date);
						}else{
							SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
							textValue = sdf.format(date);
						}
						
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
	
	public static void netWorkEduMoneyExport(Long deptid,String title, String[] headers, List<Student> refer, OutputStream out) {
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
				if (fieldName != null && !"".equals(fieldName)) {
					if (deptid.longValue() == StateConstants.DEPT_XINGZHENG.longValue()) {//网络培训费导出(行政部)
						if ("matchinfo_time".equals(fieldName)) {//确认到账时间 
							fields[0] = fields1[i];
							continue;
						}else if ("studentName".equals(fieldName)) {//姓名
							fields[1] = fields1[i];
							continue;
						}else if ("arrive_money".equals(fieldName)) {//收款金额
							fields[2] = fields1[i];
							continue;
						}else if ("remitWay".equals(fieldName)) {//汇款方式
							fields[3] = fields1[i];
							continue;
						}else if ("arrive_time".equals(fieldName)) {//收款日期
							fields[4] = fields1[i];
							continue;
						}else if ("idCard".equals(fieldName)) {//身份证
							fields[5] = fields1[i];
							continue;
						}else if ("phone".equals(fieldName)) {//手机
							fields[6] = fields1[i];
							continue;
						}else if ("LCWname".equals(fieldName)) {//LCW用户名
							fields[7] = fields1[i];
							continue;
						}else if ("LCWpassword".equals(fieldName)) {//LCW密码
							fields[8] = fields1[i];
							continue;
						}else if ("netedumoney".equals(fieldName)) {//网络培训费
							fields[9] = fields1[i];
							continue;
						}else if ("banci".equals(fieldName)) {//班级
							fields[10] = fields1[i];
							continue;
						}else if ("paytime".equals(fieldName)) {//支付日期
							fields[11] = fields1[i];
							continue;
						}else if ("xingzhengNote".equals(fieldName)) {//备注
							fields[12] = fields1[i];
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
	
	
	
	public static void exceltransferrecordExport(String title, String[] headers, List<Transferrecord> refer, OutputStream out) {
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
		Iterator<Transferrecord> it = refer.iterator();
		int index = 0;
		while (it.hasNext()) {
			index++;
			row = sheet.createRow(index);
			Transferrecord t = (Transferrecord) it.next();
			// 利用反射，根据javabean属性的先后顺序，动态调用getXxx()方法得到属性值
			Field[] fields1 = t.getClass().getDeclaredFields();
			Field[] fields = new Field[headers.length];
			for (int i = 0; i < fields1.length; i++) {
				Field field = fields1[i];
				String fieldName = field.getName();
				if (fieldName != null && !"".equals(fieldName)) {
					if ("create_time".equals(fieldName)) {//转移时间
						fields[0] = fields1[i];
						continue;
					}else if("operationName".equals(fieldName)){//操作人
						fields[1] = fields1[i];
						continue;
					}else if("phone".equals(fieldName)){//手机
						fields[2] = fields1[i];
						continue;
					}else if("tel".equals(fieldName)){// 座机
						fields[3] = fields1[i];
						continue;
					}else if("sourceName".equals(fieldName)){//转移前归属者
						fields[4] = fields1[i];
						continue;
					}else if("belongName".equals(fieldName)){//转移后归属者
						fields[5] = fields1[i];
						continue;
					}else if("state".equals(fieldName)){//销售界面资源状态
						fields[6] = fields1[i];
						continue;
					}else if("resourceLevelBefore".equals(fieldName)){//转移前资源等级
						fields[7] = fields1[i];
						continue;
					}else if("resourceLevelAfter".equals(fieldName)){//转移后资源等级
						fields[8] = fields1[i];
						continue;
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
					if (value instanceof Date) {
						Date date = (Date) value;
						SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
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
