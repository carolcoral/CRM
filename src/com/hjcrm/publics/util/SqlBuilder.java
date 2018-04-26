package com.hjcrm.publics.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.math.BigDecimal;
import java.sql.Date;
import java.sql.Time;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.util.StringUtils;

import com.hjcrm.publics.annotation.Transient;

public class SqlBuilder {

	public static final ThreadLocal<String> countSqlThreadLocal = new ThreadLocal<String>();

	/**
	 * @author likang
	 * @param entity
	 * @param values
	 *            2016年3月16日 上午10:56:37
	 */
	public static void setEntityValueFromMap(Object entity, Map values) {
		Iterator<Map.Entry> iter = values.entrySet().iterator();
		Map.Entry oneEntry;
		String key;
		Object value;
		Field f;
		while (iter.hasNext()) {
			oneEntry = iter.next();
			key = String.valueOf(oneEntry.getKey());
			value = oneEntry.getValue();
			try {
				f = entity.getClass().getDeclaredField(key);
				if (f != null) {
					f.setAccessible(true);
					f.set(entity, value);
				}
			} catch (Exception e) {
				continue;
			}
		}
	}

	public static String getInsertSql(Object entity, List<String> fieldList) {
		StringBuffer fieldListString = new StringBuffer("");
		StringBuffer markString = new StringBuffer("");
		for (int i = 0; i < fieldList.size(); i++) {
			fieldListString.append(fieldList.get(i)).append(",");
			markString.append("#{" + fieldList.get(i) + "},");
		}
		if (fieldListString.length() > 0)
			fieldListString.deleteCharAt(fieldListString.length() - 1);
		if (markString.length() > 0)
			markString.deleteCharAt(markString.length() - 1);
		String tableName = getTableName(entity.getClass());

		/**
		 * 主键自增长方式，忽略了主键的插入。
		 */
		String sql = "insert into " + tableName + "(";
		sql += fieldListString.toString();
		sql += ") values(";
		sql += markString.toString();
		sql += ")";
		return sql;
	}

	public static String getUpdateSql(Object entity, List<String> fieldList) {
		List<String> setToNullList = getSetToNullList(entity);

		StringBuffer setString = new StringBuffer("");
		for (String f : fieldList) {
			setString.append(f).append("=#{" + f + "},");
		}
		for (String oneNull : setToNullList) {
			setString.append(oneNull).append(" = null,");
		}
		if (setString.length() > 0)
			setString.deleteCharAt(setString.length() - 1);
		String idColumnName = getIdColumnName(entity);
		String sql = "update " + getTableName(entity.getClass()) + " set "
				+ setString + " where " + idColumnName + "= #{" + idColumnName + "}";
		return sql;
	}

	public static String getDeleteSql(Object entity) {
		String idColumnName = getIdColumnName(entity);
		String _tableName = getTableName(entity.getClass());
		String sql = "delete from " + _tableName + " where " + idColumnName + "= #{" + idColumnName + "}";
		return sql;
	}

	public static String getDeleteIdsSql(Object params) {
		Object[] paramArray = (Object[]) params;
		Class clazz = (Class) paramArray[0];
		String idsStr = (String) paramArray[1];
		String idColumnName = getIdColumnName(clazz);
		String _tableName = getTableName(clazz);
		String sql = "delete from " + _tableName + " where " + idColumnName + " in (";

		String[] ids = idsStr.split(",");
		for (String oneId : ids) {
			sql += "'" + oneId + "',";
		}
		sql = sql.substring(0, sql.length() - 1);
		sql += ")";

		return sql;
	}

	public static String getSelectByIdSql(Object param) {
		String idColumnName = getIdColumnName(param);
		Class clazz = param.getClass();
		String newSql = "select * from " + getTableName(clazz) + " where " + idColumnName + "= #{" + idColumnName + "}";
		return newSql;
	}

	public static String getSelectSql(Object entity, String databaseType) {

		String newSql = "select * from " + getTableName(entity.getClass()) + " where 1=1 and";
		StringBuilder whereBuilder = new StringBuilder();

		Map<String, Object> entityValueMap = getEntityOnlyValueMap(entity, null, true);
		Iterator<Map.Entry<String, Object>> iter = entityValueMap.entrySet().iterator();
		Map.Entry<String, Object> oneEntry;
		String key;
		while (iter.hasNext()) {
			oneEntry = iter.next();
			key = oneEntry.getKey();
			whereBuilder.append(" " + key + " = #{" + key + "} and");
		}
		newSql += whereBuilder;
		newSql = newSql.substring(0, newSql.length() - 3);
		PageBean pageBean = PageBean.pageBeanThreadLocal.get();
		if (pageBean != null)
			newSql = getPageSql(pageBean, newSql, databaseType);

		try {
			String orderBy = (String) ReflectUtil.getFieldValue(entity, "sort");
			if (StringUtils.hasText(orderBy))
				newSql += " order by " + orderBy;
		} catch (Exception e) {
		}
		try {
			String orderType = (String) ReflectUtil.getFieldValue(entity, "order");
			if (StringUtils.hasText(orderType))
				newSql += " " + orderType;
		} catch (Exception e) {
		}

		return newSql;
	}

	/**
	 * 判断一个Class的类型是否是基础类型。
	 * 
	 * @return
	 */
	public static boolean isBaseType(Class clazz) {
		if (Integer.class == clazz || Double.class == clazz
				|| Float.class == clazz || Boolean.class == clazz
				|| BigDecimal.class == clazz || String.class == clazz
				|| Long.class == clazz || Byte.class == clazz
				|| Character.class == clazz || Date.class == clazz
				|| Timestamp.class == clazz || Time.class == clazz)
			return true;
		else
			return false;
	}

	/**
	 * 获取表名的方法。支持驼峰式命名法.
	 * 
	 * @param arg
	 * @return
	 */
	public static String getTableName(Class entityClass) {
		String arg = entityClass.getSimpleName();

		// 获取表的前缀
		String tablePrefix = getTablePrefix(entityClass);
		StringBuffer result = new StringBuffer(tablePrefix);

		String s;
		String pattern;
		for (int i = 0; i < arg.length(); i++) {
			s = arg.charAt(i) + "";
			pattern = "[A-Z]";
			boolean flag;
			if (i == 0) {
				flag = s.matches(pattern) && !(arg.charAt(i + 1) + "").matches(pattern);
			} else if (i + 1 < arg.length()) {
				flag = s.matches(pattern) && !(arg.charAt(i - 1) + "").matches(pattern) && !(arg.charAt(i + 1) + "").matches(pattern);
			} else {
				flag = s.matches(pattern) && !(arg.charAt(i - 1) + "").matches(pattern);
			}
			if (flag) {
				if (i == 0)
					result.append(s.toLowerCase());
				else
					result.append("_" + s.toLowerCase());
			} else {
				result.append(s);
			}
		}
		// end 重构代码段
		return result.toString();
	}

	/**
	 * 获取在更新时，需要设置为空的字段名。
	 * 
	 * @param entity
	 * @return
	 */
	public static List<String> getSetToNullList(Object entity) {
		Class entityClass = entity.getClass();
		Field field = null;
		try {
			try {
				field = entityClass.getDeclaredField("updateToNullFieldNames");
			} catch (NoSuchFieldException ex) {
				field = entityClass.getSuperclass().getDeclaredField( "updateToNullFieldNames");
			}
			field.setAccessible(true);
			String setToNulls = (String) field.get(entity);
			List<String> nullList = new ArrayList<String>();
			if (StringUtils.hasText(setToNulls)) {
				String[] nullFieldNames = setToNulls.split(",");
				for (int i = 0; i < nullFieldNames.length; i++) {
					nullList.add(nullFieldNames[i]);
				}
			}

			return nullList;
		} catch (Exception e) {
			throw new RuntimeException("要使用本类的函数来操作数据库，实体类：[" + entityClass.getName()
					+ "]，中必须包含updateToNullFieldNames属性，建议继承BaseEntity！");
		}
	}

	/**
	 * 获取实体ID列名的方法。
	 * 
	 * @param entity
	 * @return
	 */
	public static String getIdColumnName(Object entity) {
		Class entityClass = entity.getClass();
		Method mehtod = null;
		try {
			mehtod = entityClass.getMethod("getIdColumnName");
			Object newEntity = null;
			if (entity != null)
				newEntity = entity;
			else
				newEntity = entityClass.newInstance();
			return (String) mehtod.invoke(newEntity);
		} catch (Exception e) {
			throw new RuntimeException("要使用本类的函数来操作数据库，实体类：[" + entityClass.getName()
					+ "]，中必须包含getIdColumnName方法，建议继承BaseEntity！");
		}
	}

	public static String getIdColumnName(Class clazz) {
		try {
			return getIdColumnName(clazz.newInstance());
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
	}

	public static String getTablePrefix(Class entityClass) {
		Method mehtod = null;
		try {
			mehtod = entityClass.getMethod("getTablePrefix");
			Object entity = entityClass.newInstance();
			return (String) mehtod.invoke(entity);
		} catch (Exception e) {
			throw new RuntimeException("要使用本类的函数来操作数据库，实体类：[" + entityClass.getName()
					+ "]，中必须包含getTablePrefix方法，建议继承BaseEntity！");
		}
	}

	public static List<String> getEntityOnlyFieldList(Object entity,
			boolean ignoreIdColumn) {
		List<String> fieldList = new ArrayList<String>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field f : fields) {

			// 如果是静态或者final字段，则跳过。
			if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers()))
				continue;

			if (!isBaseType(f.getType()))
				continue;
			if (ignoreIdColumn) {
				if (getIdColumnName(entity).equalsIgnoreCase(f.getName()))
					continue;
			}

			Transient ta = f.getAnnotation(Transient.class);
			if (ta != null)
				continue;

			f.setAccessible(true);
			Object value = null;
			try {
				value = f.get(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (value == null)
				continue;
			fieldList.add(f.getName());
		}
		return fieldList;
	}

	public static Map<String, Object> getEntityOnlyValueMap(Object entity, List<String> likeFieldList, boolean ignoreIdColumn) {
		Map<String, Object> valueMap = new HashMap<String, Object>();
		Field[] fields = entity.getClass().getDeclaredFields();
		for (Field f : fields) {

			// 如果是静态或者final字段，则跳过。
			if (Modifier.isStatic(f.getModifiers()) || Modifier.isFinal(f.getModifiers()))
				continue;

			if (!isBaseType(f.getType()))
				continue;
			if (ignoreIdColumn) {
				if (getIdColumnName(entity).equalsIgnoreCase(f.getName()))
					continue;
			}

			Transient ta = f.getAnnotation(Transient.class);
			if (ta != null)
				continue;

			f.setAccessible(true);
			Object value = null;
			try {
				value = f.get(entity);
			} catch (Exception e) {
				e.printStackTrace();
			}
			if (value == null)
				continue;
			if ("".equals(value.toString()))
				value = null;
			if (likeFieldList != null && likeFieldList.contains(f.getName())) {
				valueMap.put(f.getName(), "%" + value + "%");
			} else {
				valueMap.put(f.getName(), value);
			}
		}
		return valueMap;
	}

	public static String setIdentityValue(Object entity, Object id) {
		Method setMethod = null;
		String idColumnName = getIdColumnName(entity);
		try {
			setMethod = entity.getClass().getDeclaredMethod( getSetterName(idColumnName), id.getClass());
			setMethod.invoke(entity, id);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return idColumnName;
	}

	public static String getSetterName(String field) {
		String setter = "set" + field.substring(0, 1).toUpperCase() + field.substring(1);
		return setter;
	}

	/**
	 * 根据page对象获取对应的分页查询Sql语句，这里只做了两种数据库类型，Mysql和Oracle 其它的数据库都 没有进行分页
	 * 
	 * @param page
	 *            分页对象
	 * @param sql
	 *            原sql语句
	 * @return
	 */
	public static String getPageSql(PageBean page, String sql,
			String databaseType) {
		StringBuffer sqlBuffer = new StringBuffer(sql);
		if ("mysql".equalsIgnoreCase(databaseType)) {
			return getMysqlPageSql(page, sqlBuffer);
		} else if ("oracle".equalsIgnoreCase(databaseType)) {
			return getOraclePageSql(page, sqlBuffer);
		}
		return sqlBuffer.toString();
	}

	/**
	 * 获取Mysql数据库的分页查询语句
	 * 
	 * @param page
	 *            分页对象
	 * @param sqlBuffer
	 *            包含原sql语句的StringBuffer对象
	 * @return Mysql数据库分页语句
	 */
	public static String getMysqlPageSql(PageBean page, StringBuffer sqlBuffer) {
		// 计算第一条记录的位置，Mysql中记录的位置是从0开始的。
		int offset = (page.getCurrentPage() - 1) * page.getPageSize();
		sqlBuffer.append(" limit ").append(offset).append(",") .append(page.getPageSize());
		return sqlBuffer.toString();
	}

	/**
	 * 获取Oracle数据库的分页查询语句
	 * 
	 * @param page
	 *            分页对象
	 * @param sqlBuffer
	 *            包含原sql语句的StringBuffer对象
	 * @return Oracle数据库的分页查询语句
	 */
	public static String getOraclePageSql(PageBean page, StringBuffer sqlBuffer) {
		// 计算第一条记录的位置，Oracle分页是通过rownum进行的，而rownum是从1开始的
		int offset = (page.getCurrentPage() - 1) * page.getPageSize() + 1;
		sqlBuffer.insert(0, "select u.*, rownum r from (") .append(") u where rownum < ") .append(offset + page.getPageSize());
		sqlBuffer.insert(0, "select * from (").append(") where r >= ") .append(offset);
		// 上面的Sql语句拼接之后大概是这个样子：
		// select * from (select u.*, rownum r from (select * from t_user) u
		// where rownum < 31) where r >= 16
		return sqlBuffer.toString();
	}

	/**
	 * 根据原Sql语句获取对应的查询总记录数的Sql语句
	 * 
	 * @param sql
	 * @return
	 */
	public static String getCountSql(String sql) {
		String countSql = "";
		countSql = countSqlThreadLocal.get();
		if (countSql != null && countSql != "") {
			return countSql;
		} else {
			int totalIndex = 0;
			int tempIndex = 0;
			tempIndex = totalIndex;
			String temp = sql.substring(tempIndex);
			String newTemp = repalceFromString(temp);

			// 截取from前面的字符串。
			totalIndex += newTemp.toLowerCase().indexOf("from") + 5;
			temp = sql.substring(tempIndex, totalIndex);

			// 如果查询子句里有2个以上select,表示有子查询存在。还要继续截取。
			/*
			 * if (temp.toLowerCase().split("select").length > 2 ) totalIndex =
			 * getCountSqlIndex(totalIndex, tempIndex, sql);
			 */

			countSql = sql.substring(totalIndex);
			if (countSql.toUpperCase().contains("GROUP")) {
				Pattern pattern = Pattern.compile("(?<=\\()[^\\)]+");
				String upperCountSql = countSql.toUpperCase();
				Matcher matcher = pattern.matcher(upperCountSql);
				boolean isreturn = false;
				String content;// 重构
				while (matcher.find()) {
					content = matcher.group();
					if (StringUtils.hasText(content)) {
						if (content.contains("GROUP")) {
							isreturn = true;
							return "select count(*) from " + countSql;
						}
					}
				}
				if (!isreturn) {
					return "select count(1) from ( select count(*) from " + countSql + " ) innertable";
				}
			}
			return "select count(*) from " + countSql;
		}
	}

	private static String repalceFromString(String temp1) {
		String temp = temp1;
		// Pattern p = Pattern.compile("\\(.+\\)");
		Pattern p = Pattern.compile("(?<=\\()[^\\)]+");
		Matcher m = p.matcher(temp);
		String g;
		String new_g;
		while (m.find()) {
			g = m.group();
			new_g = g.toLowerCase().replaceAll("from", "xxxx");
			temp = temp.replace(g, new_g);
		}
		String[] tempList = temp.toLowerCase().split("from");
		if (tempList.length > 2) {
			Pattern p2 = Pattern.compile("\\(.+\\)");
			Matcher m2 = p2.matcher(temp);
			String g2;
			String new_g2;
			while (m2.find()) {
				g2 = m2.group();
				new_g2 = g2.toLowerCase().replaceAll("from", "xxxx");
				temp = temp.replace(g2, new_g2);
			}
		}
		return temp;
	}

}
