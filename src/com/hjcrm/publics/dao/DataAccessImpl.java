package com.hjcrm.publics.dao;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionTemplate;

import com.hjcrm.publics.util.PageBean;
import com.hjcrm.publics.util.SqlBuilder;

/**
 * jdbc基类
 * @author likang
 * @date 2016-10-13 上午10:41:54
 * @param <T>
 */
public class DataAccessImpl<T> extends SqlSessionTemplate implements IDataAccess<T> {

	public DataAccessImpl(SqlSessionFactory sqlSessionFactory) {
		super(sqlSessionFactory);
	}
	
	public SqlSessionTemplate getSqlSessionTemplate(){
		return this;
	}
	
	/**
	 * 插入数据
	 * @param entity
	 * @throws DaoException
	 */
	public Object insert(T entity){
		insert(DataAccessImpl.class.getPackage().getName()+ "." + DaoConstants.Common_Insert , entity);
		return entity;
	}
	
	public Object insertByStatement(String statement,Object parameter){
		insert(statement, parameter);
		return parameter;
		 
	}
	
	/**
	 * 修改数据
	 * @param entity
	 * @throws DaoException
	 */
	public void update(T entity){
		update(DataAccessImpl.class.getPackage().getName()+ "." + DaoConstants.Common_Update , entity);
	}
	
	/**
	 * 物理删除
	 * @param entity
	 * @throws DaoException
	 */
	public int delete(T entity){
		return delete(DataAccessImpl.class.getPackage().getName()+ "." + DaoConstants.Common_Delete, entity);
	}
	
	/**
	 * 根据语句删除 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int deleteByStatment(String statement,Object parameter){
		return delete(statement, parameter);
	}
	
	/**
	 * 根据语句进行更新
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int updateByStatment(String statement,Object parameter){
		return update(statement, parameter);
	}
	
	
	/**
	 * 根据IDS批量删除数据
	 * IDS是以英文逗号分隔的ID字符串
	 * @param clazz
	 * @param ids
	 */
	public void deleteByIds(Class clazz,String ids){
		delete(DataAccessImpl.class.getPackage().getName()+ "." + DaoConstants.Common_Delete_By_Ids, new Object[]{clazz,ids});
	}
	
	/**
	 * 根据主键查询
	 * @param identity
	 * @return
	 */
	public Object queryByIdentity(Class entityClass,Object identity) {
		Object paramWithId =  null;
		try {
			paramWithId = entityClass.newInstance();
		} catch (Exception e){
			throw new RuntimeException(e);
		}
		SqlBuilder.setIdentityValue(paramWithId, identity);
		Map valueMap = (Map)selectOne(DataAccessImpl.class.getPackage().getName()+ "." + DaoConstants.Common_Select_By_Id, paramWithId);
		if(valueMap == null || valueMap.size() == 0)
			return null;
		SqlBuilder.setEntityValueFromMap(paramWithId,valueMap);
		return paramWithId;
	}
	
	
	/**
	 * 传入对应参数进行查询
	 * @param model
	 * @return
	 */
	public List<T> query(T model){
		return query(model,null);
	}
	
	/**
	 * 传入对应参数进行分页查询
	 * @param model
	 * @param pageBean
	 * @return
	 */
	public List query(Object model, PageBean pageBean){
		if(pageBean != null)
			PageBean.pageBeanThreadLocal.set(pageBean);
		List<Map> resultMaps = selectList(DataAccessImpl.class.getPackage().getName()+ "." + DaoConstants.Common_Select, model);
		List results = new ArrayList();
		Object returnEntity;
		for (Map oneMap : resultMaps) {
			try {
				returnEntity = model.getClass().newInstance();
			} catch (Exception e){
				throw new RuntimeException(e);
			}
			SqlBuilder.setEntityValueFromMap(returnEntity,oneMap);
			results.add(returnEntity);
		}
		return results;
	}
	/**
	 * 根据某个语句以及参数查询，支持分页
	 * @param statment
	 * @param param
	 * @param pageBean
	 * @return
	 */
	public List queryByStatment(String statment,Object param,PageBean pageBean){
		if(pageBean != null)
			PageBean.pageBeanThreadLocal.set(pageBean);
		List results = selectList(statment, param);
		return results;
	}
	
	/**
	 * 根据某个语句查询并返回Long值
	 * @param statment
	 * @param param
	 * @return
	 */
	public Long queryLongValueByStatment(String statment,Object param){
		return (Long)super.selectOne(statment, param);
	}

}
