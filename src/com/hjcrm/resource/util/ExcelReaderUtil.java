package com.hjcrm.resource.util;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFDateUtil;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.poifs.filesystem.POIFSFileSystem;

/**
 * 解析Excel工具类
 * 
 * @author likang
 * @date 2016-11-7 上午9:57:53
 */
public class ExcelReaderUtil {

	private static POIFSFileSystem fs;
	private static HSSFWorkbook wb;
	private static HSSFSheet sheet;
	private static HSSFRow row;

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

	/**
	 * 根据HSSFCell类型设置数据
	 * 
	 * @param cell
	 * @return
	 * @author likang
	 * @date 2016-11-7 上午9:59:28
	 */
	private static String getCellFormatValue(HSSFCell cell) {
		String cellvalue = "";
		if (cell != null) {
			switch (cell.getCellType()) {// 判断当前Cell的Type
			case HSSFCell.CELL_TYPE_NUMERIC:// 如果当前Cell的Type为NUMERIC
			case HSSFCell.CELL_TYPE_FORMULA: {
				if (HSSFDateUtil.isCellDateFormatted(cell)) {// 判断当前的cell是否为Date
					// 如果是Date类型则，转化为Data格式
					// 方法1：这样子的data格式是带时分秒的：2011-10-12 0:00:00
					// cellvalue = cell.getDateCellValue().toLocaleString();
					// 方法2：这样子的data格式是不带带时分秒的：2011-10-12
					Date date = cell.getDateCellValue();
					SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
					cellvalue = sdf.format(date);
				} else {
					// cellvalue = String.valueOf(cell.getNumericCellValue());//
					// 如果是纯数字,取得当前Cell的数值
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

	/**
	 * 读取Excel数据内容(资源管理导入专用)
	 * 
	 * @param is
	 * @return
	 * @author likang
	 * @date 2016-11-7 上午10:03:28
	 */
	public static Map<String,Object> readExcelContent(Long deptid,InputStream is) {
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
					if(deptid != null && deptid.longValue() == 2 || deptid.longValue() == 30){
						if (j == 0) {
							content.put("userid", str);//创建者
						}else if(j == 1){
							content.put("createrName", str);//创建者姓名
						}else if(j == 2){
							content.put("source", str);//渠道
						}else if(j == 3){
							content.put("address", str);//地域
						}else if(j == 4){
							content.put("resourceName", str);//姓名
						}else if(j == 5){
							content.put("phone", str);//手机
						}else if(j == 6){
							content.put("courseid", str);//咨询课程
						}else if(j == 7){
							content.put("yunYingNote", str);//运营咨询备注
						}else if(j == 8){
							content.put("belongid", str);//所属人
						}
						j++;
						str = null;
					}else {
						if (j == 0) {
							content.put("resourceName", str);// 姓名
						} else if (j == 1) {
							content.put("phone", str);// 手机
						} else if (j == 2) {
							content.put("address", str);// 地域
						} else if (j == 3) {
							content.put("resourceLevel", str);// 姓名
						}
						j++;
						str = null;
					}
					
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
	
	/**
	 * 读取Excel数据内容(资源管理导入专用)
	 * 
	 * @param is
	 * @return
	 * @author 倪广
	 * @date 2017-2-20 16:19:22
	 */
	public static Map<String,Object> readExcelContentOlionBuy(InputStream is) {
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
						if(j == 0){
							content.put("userName", str);
						}else if(j == 1){
							content.put("IDcard", str);
						}else if(j == 2){
							content.put("phone", str);
						}else if(j == 3){
							content.put("email", str);
						}else if(j == 4){
							content.put("company", str);
						}else if(j == 5){
							content.put("address", str);
						}else if(j == 6){
							content.put("arrive_money", str);
						}else if(j == 7){
							content.put("arrive_time", str);
						}else if(j == 8){
							content.put("remitWay", str);
						}else if(j == 9){
							content.put("courseId", str);
						}else if(j == 10){
							content.put("subjectId", str);
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
