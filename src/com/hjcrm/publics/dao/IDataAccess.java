package com.hjcrm.publics.dao;

import java.util.List;

import org.mybatis.spring.SqlSessionTemplate;

import com.hjcrm.publics.util.PageBean;

/**
 * jdbc基类接口
 * @author likang
 * @date 2016-10-13 上午10:42:13
 * @param <T>
 */
public interface IDataAccess<T> {

	public SqlSessionTemplate getSqlSessionTemplate();

	/**
	 * 插入数据
	 * 
	 * @param entity
	 * @throws DaoException
	 */
	public Object insert(T entity);

	public Object insertByStatement(String statement, Object parameter);

	/**
	 * 修改数据
	 * 
	 * @param entity
	 * @throws DaoException
	 */
	public void update(T entity);

	/**
	 * 物理删除
	 * 
	 * @param entity
	 * @throws DaoException
	 */
	public int delete(T entity);

	/**
	 * 根据语句删除
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int deleteByStatment(String statement, Object parameter);

	/**
	 * 根据语句进行更新
	 * 
	 * @param statement
	 * @param parameter
	 * @return
	 */
	public int updateByStatment(String statement, Object parameter);

	/**
	 * 根据IDS批量删除数据 IDS是以英文逗号分隔的ID字符串
	 * 
	 * @param clazz
	 * @param ids
	 */
	public void deleteByIds(Class clazz, String ids);

	/**
	 * 根据主键查询
	 * 
	 * @param identity
	 * @return
	 */
	public Object queryByIdentity(Class entityClass, Object identity);

	/**
	 * 传入对应参数进行查询
	 * 
	 * @param model
	 * @return
	 */
	public List<T> query(T model);

	/**
	 * 传入对应参数进行分页查询
	 * 
	 * @param model
	 * @param pageBean
	 * @return
	 */
	public List<T> query(T model, PageBean pageBean);

	/**
	 * 根据某个语句以及参数查询，支持分页
	 * 
	 * @param statment
	 * @param param
	 * @param pageBean
	 * @return
	 */
	public List queryByStatment(String statment, Object param, PageBean pageBean);

	/**
	 * 根据某个语句查询并返回Long值
	 * 
	 * @param statment
	 * @param param
	 * @return
	 */
	public Long queryLongValueByStatment(String statment, Object param);

}