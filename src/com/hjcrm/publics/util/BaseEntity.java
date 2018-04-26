package com.hjcrm.publics.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hjcrm.publics.annotation.Transient;

/**
 * 实体基类
 * @author likang
 * @date 2016-10-13 上午10:27:22
 */
public abstract class BaseEntity {
	
	protected String order;
	protected String sort;
	protected Integer offset;
	protected Integer limit;
	
	@Transient
	protected String updateToNullFieldNames;// 更新时,要设置成空的字段名，以英文逗号分隔。
	
	
	@JsonIgnore
	public abstract String getIdColumnName();
	
	@JsonIgnore
	public String getTablePrefix() {
		return ContextUtil.getInitConfig("table.prefix");
	}

	public String toString() {
		StringBuffer str = new StringBuffer(this.getClass().getSimpleName() + ":[");
		Field[] fields = this.getClass().getDeclaredFields();
		String fieldName;
		String getterName ;
		Method getter;
		Object value;
		for (Field f : fields) {
		    fieldName = f.getName();
		    getterName = "get" + fieldName.substring(0, 1).toUpperCase() + fieldName.substring(1);
			try {
				 getter = this.getClass().getDeclaredMethod(getterName);
				 value = getter.invoke(this);
				str.append(fieldName).append("=").append(value).append(", ");
			} catch (Exception e) {
				continue;
			}
		}
		if (str.length() > 1) {
			str.delete(str.length() - 2, str.length());
			str.append("]");
		}
		return str.toString();
	}
	
	
	/**
	 * 获取排序字段的方法。
	 * 例如要按number字段排序，则返回" number "
	 * 如果要按number,sequeue排序，则返回" number,sequeue "
	 * 子类重新实现。
	 * @return
	 */
	@JsonIgnore
	public String getSqlOrderBy(){
		return order ==  null ? "" : order;
	}
	
	public void setUpdateToNullFieldNames(String _str){
		updateToNullFieldNames = _str;
	}
	
	@JsonIgnore
	public String getOrder() {
		return order;
	}

	public void setOrder(String order) {
		this.order = order;
	}
	
	@JsonIgnore
	public String getSort() {
		return sort;
	}

	public void setSort(String sort) {
		this.sort = sort;
	}
	
	@JsonIgnore
	public Integer getOffset() {
		return offset;
	}
	
	public void setOffset(Integer offset) {
		this.offset = offset;
	}
	
	@JsonIgnore
	public Integer getLimit() {
		return limit;
	}
	
	public void setLimit(Integer limit) {
		this.limit = limit;
	}
	
}


