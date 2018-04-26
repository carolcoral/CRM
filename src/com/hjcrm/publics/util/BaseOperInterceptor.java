package com.hjcrm.publics.util;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Properties;

import org.apache.ibatis.builder.SqlSourceBuilder;
import org.apache.ibatis.executor.keygen.Jdbc3KeyGenerator;
import org.apache.ibatis.executor.parameter.ParameterHandler;
import org.apache.ibatis.executor.statement.StatementHandler;
import org.apache.ibatis.mapping.BoundSql;
import org.apache.ibatis.mapping.MappedStatement;
import org.apache.ibatis.mapping.ParameterMapping;
import org.apache.ibatis.mapping.SqlSource;
import org.apache.ibatis.plugin.Interceptor;
import org.apache.ibatis.plugin.Intercepts;
import org.apache.ibatis.plugin.Invocation;
import org.apache.ibatis.plugin.Plugin;
import org.apache.ibatis.plugin.Signature;
import org.apache.ibatis.reflection.DefaultReflectorFactory;
import org.apache.ibatis.reflection.MetaObject;
import org.apache.ibatis.reflection.ReflectorFactory;
import org.apache.ibatis.reflection.factory.DefaultObjectFactory;
import org.apache.ibatis.reflection.factory.ObjectFactory;
import org.apache.ibatis.reflection.wrapper.DefaultObjectWrapperFactory;
import org.apache.ibatis.reflection.wrapper.ObjectWrapperFactory;
import org.apache.ibatis.scripting.defaults.DefaultParameterHandler;
import org.apache.ibatis.session.Configuration;

import com.hjcrm.publics.dao.DaoConstants;

/**
 * mybatis  --- plugins
 * @author likang
 * @date 2016-10-13 上午10:44:29
 */
@Intercepts({ @Signature(method = "prepare", type = StatementHandler.class, args = { Connection.class }) })
public class BaseOperInterceptor implements Interceptor {
	
	private String databaseType;// 数据库类型，不同的数据库有不同的分页方法
	private static final ObjectFactory DEFAULT_OBJECT_FACTORY = new DefaultObjectFactory();
    private static final ObjectWrapperFactory DEFAULT_OBJECT_WRAPPER_FACTORY = new DefaultObjectWrapperFactory();
    private static final ReflectorFactory DEFAULT_REFLECTOR_FACTORY = new DefaultReflectorFactory();
    
	public Object intercept(Invocation invocation) throws Throwable {
		StatementHandler statementHandler = (StatementHandler) invocation.getTarget();
		MetaObject metaStatementHandler = MetaObject.forObject(statementHandler, DEFAULT_OBJECT_FACTORY,
                DEFAULT_OBJECT_WRAPPER_FACTORY,DEFAULT_REFLECTOR_FACTORY);
		Connection connection = (Connection)invocation.getArgs()[0];
		
		String originalSql = (String) metaStatementHandler.getValue("delegate.boundSql.sql");
        
        Configuration configuration = (Configuration) metaStatementHandler.getValue("delegate.configuration");
        Object parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject");
        
        if (null == originalSql || "".equals(originalSql)) {
            String newSql = "";
            MappedStatement mappedStatement = (MappedStatement) metaStatementHandler.getValue("delegate.mappedStatement");
            // 根据ID生成相应类型的sql语句（id需剔除namespace信息）
            String id = mappedStatement.getId();
            
            id = id.substring(id.lastIndexOf(".") + 1);
            List fieldList = SqlBuilder.getEntityOnlyFieldList(parameterObject, true);
            if(DaoConstants.Common_Insert.equals(id)) {
            	newSql = SqlBuilder.getInsertSql(parameterObject,fieldList);
            	
            	Object idObjValue = ReflectUtil.getFieldValue(parameterObject, SqlBuilder.getIdColumnName(parameterObject));
            	if(idObjValue == null){   
            		//如果ID的值为空,则认为是由数据库表自动生成ID,此时设置一些参数,在执行完insert后能够自动拿到生成的ID值赋值到对象里。
            		ReflectUtil.setFieldValue(mappedStatement,"keyGenerator",new Jdbc3KeyGenerator());
            		ReflectUtil.setFieldValue(mappedStatement, "keyProperties", new String[]{SqlBuilder.getIdColumnName(parameterObject)});
            	}
            	
            }else if(DaoConstants.Common_Update.equals(id)){
            	newSql = SqlBuilder.getUpdateSql(parameterObject, fieldList);
            }else if(DaoConstants.Common_Delete.equals(id)){
            	newSql = SqlBuilder.getDeleteSql(parameterObject);
            }else if(DaoConstants.Common_Delete_By_Ids.equals(id)) {
            	parameterObject = metaStatementHandler.getValue("delegate.boundSql.parameterObject.array");
            	newSql = SqlBuilder.getDeleteIdsSql(parameterObject);
            }else if(DaoConstants.Common_Select_By_Id.equals(id)){
            	newSql = SqlBuilder.getSelectByIdSql(parameterObject);
            }else if(DaoConstants.Common_Select.equals(id)){
            	newSql = SqlBuilder.getSelectSql(parameterObject,databaseType);
            }
            
           // logger.info("替换后的SQL为:" + newSql);
           
            SqlSource sqlSource = buildSqlSource(configuration, newSql, parameterObject.getClass());
            List<ParameterMapping> parameterMappings = sqlSource.getBoundSql(parameterObject).getParameterMappings();
            metaStatementHandler.setValue("delegate.boundSql.sql", sqlSource.getBoundSql(parameterObject).getSql());
            metaStatementHandler.setValue("delegate.boundSql.parameterMappings", parameterMappings);
        }else{
           PageBean page = PageBean.pageBeanThreadLocal.get();
		   if(page  != null&&originalSql.trim().toUpperCase().indexOf("UPDATE")!=0) {
			    StatementHandler delegate = (StatementHandler)ReflectUtil.getFieldValue(statementHandler, "delegate");
		        //通过反射获取delegate父类BaseStatementHandler的mappedStatement属性
				MappedStatement mappedStatement = (MappedStatement)ReflectUtil.getFieldValue(delegate, "mappedStatement");
				//拦截到的prepare方法参数是一个Connection对象
				
				//获取当前要执行的Sql语句，也就是我们直接在Mapper映射语句中写的Sql语句
				String sql = originalSql;
				//给当前的page参数对象设置总记录数
				this.setTotalRecord(parameterObject, page, mappedStatement, connection);
				//获取分页Sql语句
				String pageSql = SqlBuilder.getPageSql(page, sql,databaseType);
				
				//利用反射设置当前BoundSql对应的sql属性为我们建立好的分页Sql语句
				metaStatementHandler.setValue("delegate.boundSql.sql", pageSql);
				PageBean.pageBeanThreadLocal.remove();
		   }
        }
        
        // 调用原始statementHandler的prepare方法
        statementHandler = (StatementHandler) metaStatementHandler.getOriginalObject();
        statementHandler.prepare((Connection) invocation.getArgs()[0]);
        // 传递给下一个拦截器处理
        return invocation.proceed();
	}

	public Object plugin(Object target) {
		return Plugin.wrap(target, this);
	}

	public void setProperties(Properties properties) {
		this.databaseType = properties.getProperty("databaseType");
	}
	
	private SqlSource buildSqlSource(Configuration configuration, String originalSql, Class<?> parameterType) {
        SqlSourceBuilder builder = new SqlSourceBuilder(configuration);
        return builder.parse(originalSql, parameterType, null);
    }
	
	
    /**
     * 给当前的参数对象page设置总记录数
     *
     * @param page Mapper映射语句对应的参数对象
     * @param mappedStatement Mapper映射语句
     * @param connection 当前的数据库连接
     */
    private void setTotalRecord(Object paramObj ,PageBean page,  MappedStatement mappedStatement, Connection connection) {
       //获取对应的BoundSql，这个BoundSql其实跟我们利用StatementHandler获取到的BoundSql是同一个对象。
       //delegate里面的boundSql也是通过mappedStatement.getBoundSql(paramObj)方法获取到的。
       BoundSql boundSql = mappedStatement.getBoundSql(paramObj);
       //获取到我们自己写在Mapper映射语句中对应的Sql语句
       String sql = boundSql.getSql();
       //通过查询Sql语句获取到对应的计算总记录数的sql语句
       String countSql = SqlBuilder.getCountSql(sql);
       //通过BoundSql获取对应的参数映射
       List<ParameterMapping> parameterMappings = boundSql.getParameterMappings();
       //利用Configuration、查询记录数的Sql语句countSql、参数映射关系parameterMappings和参数对象page建立查询记录数对应的BoundSql对象。
       BoundSql countBoundSql = new BoundSql(mappedStatement.getConfiguration(), countSql, parameterMappings, paramObj);
       
       //BUG修正，增加拷贝参数的代码。
       String prop;
       for (ParameterMapping mapping : boundSql.getParameterMappings()) {
            prop = mapping.getProperty();
           if (boundSql.hasAdditionalParameter(prop)) {
        	   countBoundSql.setAdditionalParameter(prop, boundSql.getAdditionalParameter(prop));
           }
       }
       
       //通过mappedStatement、参数对象page和BoundSql对象countBoundSql建立一个用于设定参数的ParameterHandler对象
       ParameterHandler parameterHandler = new DefaultParameterHandler(mappedStatement, paramObj, countBoundSql);
       //通过connection建立一个countSql对应的PreparedStatement对象。
       PreparedStatement pstmt = null;
       ResultSet rs = null;
       try {
           pstmt = connection.prepareStatement(countSql);
           //通过parameterHandler给PreparedStatement对象设置参数
           parameterHandler.setParameters(pstmt);
           //之后就是执行获取总记录数的Sql语句和获取结果了。
           rs = pstmt.executeQuery();
           if (rs.next()) {
              int totalRecord = rs.getInt(1);
              //给当前的参数page对象设置总记录数
              page.setCountResult(totalRecord);
           }
       } catch (SQLException e) {
           e.printStackTrace();
       } finally {
           try {
              if (rs != null)
                  rs.close();
               if (pstmt != null)
                  pstmt.close();
           } catch (SQLException e) {
              e.printStackTrace();
           }
       }
    }
}
