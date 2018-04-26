package com.hjcrm.publics.util;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 字符转换工具类
 * @author likang
 * @date 2016-10-25 下午4:14:44
 */
public class ZgUtil {
	
	
	/**  
     * 基本功能：过滤所有以"<"开头以">"结尾的标签  
     * @param str  
     * @return String  
     */  
	private final static String regxpForHtml = "<([^>]*)>"; // 过滤所有以<开头以>结尾的标签  
    public static String filterHtml(String str) {   
        Pattern pattern = Pattern.compile(regxpForHtml);   
        Matcher matcher = pattern.matcher(str);   
        StringBuffer sb = new StringBuffer();   
        boolean result1 = matcher.find();   
        while (result1) {   
            matcher.appendReplacement(sb, "");   
            result1 = matcher.find();   
        }   
        matcher.appendTail(sb);   
        return sb.toString();   
    }
    
   
}
