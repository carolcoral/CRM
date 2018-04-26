package com.hjcrm.publics.util;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;


/**
 * 利用反射进行操作的一个工具类
 * @author likang
 * @date 2016-10-13 上午10:43:21
 */
public class ReflectUtil {
   /**
    * 利用反射获取指定对象的指定属性
    * @param obj 目标对象
    * @param fieldName 目标属性
    * @return 目标属性的值
    */
   public static Object getFieldValue(Object obj, String fieldName) {
       Object result = null;
       Field field = ReflectUtil.getField(obj, fieldName);
       if (field != null) {
          field.setAccessible(true);
          try {
              result = field.get(obj);
          } catch (IllegalArgumentException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (IllegalAccessException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
       }
       return result;
   }
  
   /**
    * 利用反射获取指定对象里面的指定属性
    * @param obj 目标对象
    * @param fieldName 目标属性
    * @return 目标字段
    */
   private static Field getField(Object obj, String fieldName) {
       Field field = null;
       for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
          try {
              field = clazz.getDeclaredField(fieldName);
              break;
          } catch (NoSuchFieldException e) {
              //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
          }
       }
       return field;
   }
   
   private static Method getMethod(Object obj,String methodName) {
	   Method method = null;
	   
	   for (Class<?> clazz=obj.getClass(); clazz != Object.class; clazz=clazz.getSuperclass()) {
          try {
        	  method = clazz.getDeclaredMethod(methodName);
              break;
          } catch (NoSuchMethodException e) {
              //这里不用做处理，子类没有该字段可能对应的父类有，都没有就返回null。
          }
       }
	   
	   return method;
   }

   /**
    * 利用反射设置指定对象的指定属性为指定的值
    * @param obj 目标对象
    * @param fieldName 目标属性
     * @param fieldValue 目标值
    */
   public static void setFieldValue(Object obj, String fieldName,
          Object fieldValue) {
       Field field = ReflectUtil.getField(obj, fieldName);
       if (field != null) {
          try {
              field.setAccessible(true);
              field.set(obj, fieldValue);
          } catch (IllegalArgumentException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          } catch (IllegalAccessException e) {
              // TODO Auto-generated catch block
              e.printStackTrace();
          }
       }
    }
   
   
   public static void invokeMethod(Object obj,String methodName,Object methodArgs) {
	   Method method =  getMethod(obj, methodName);
	   if(method != null)  {
		    try {
			    method.setAccessible(true);
				method.invoke(obj, methodArgs);
			} catch (IllegalArgumentException e) {
				e.printStackTrace();
			} catch (IllegalAccessException e) {
				e.printStackTrace();
			} catch (InvocationTargetException e) {
				e.printStackTrace();
			}
	   }
   }
}