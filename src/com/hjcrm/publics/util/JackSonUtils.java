package com.hjcrm.publics.util;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JavaType;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;


public class JackSonUtils{

	/**
	 * 将json字符串转为对象。
	 * @param src
	 * @param type
	 * @return
	 */
	public static final <T> T convertStr2Obj(String src,Class<T> type){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try{
			return  mapper.readValue(src, type);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将json字符串转为对象集合。
	 * @param src
	 * @param type
	 * @return
	 */
	public static final <T> List<T> convertStr2objList(String src,Class<T> type){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonParser.Feature.ALLOW_SINGLE_QUOTES, true);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		try{
			JavaType javaType = getCollectionType(ArrayList.class, type); 
	        List<T> r =  (List<T>)mapper.readValue(src, javaType); 
			return  r;
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	/**
	 * 将对象转为json字符串。
	 * @param obj
	 * @return
	 */
	public static final String writeObj2Str(Object obj){
		ObjectMapper mapper = new ObjectMapper();
		mapper.configure(JsonGenerator.Feature.QUOTE_FIELD_NAMES , true);
		mapper.disable(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES);  
		mapper.configure(SerializationFeature.FAIL_ON_EMPTY_BEANS, false);
		mapper.setDateFormat(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss"));
		mapper.setSerializationInclusion(Include.NON_NULL);  
		try{
			return mapper.writeValueAsString(obj);
		}catch(Exception e){
			throw new RuntimeException(e);
		}
	}
	
	
	 /**   
     * 获取泛型的Collection Type  
     * @param collectionClass 泛型的Collection   
     * @param elementClasses 元素类   
     * @return JavaType Java类型   
     * @since 1.0   
     */   
	 public static JavaType getCollectionType(Class<?> collectionClass, Class<?>... elementClasses) {   
	     return new ObjectMapper().getTypeFactory().constructParametricType(collectionClass, elementClasses);   
	 }
	
	 /**
	  * json转map
	  * @author likang	
	  * @param jsonStr
	  * @return
	  * @throws Exception
	  * 2016年10月13日11:00:15
	  */
 	 public static Map jsonToObject(String jsonStr) throws Exception {
		 JSONObject jsonObj = new JSONObject(jsonStr);
		 Iterator<String> nameItr = jsonObj.keys();
		 String name;
		 Map<String, String> outMap = new HashMap<String, String>();
		 while (nameItr.hasNext()) {
			 name = nameItr.next();
			 outMap.put(name, jsonObj.getString(name));
		 }
		 return outMap;
	 }

}
