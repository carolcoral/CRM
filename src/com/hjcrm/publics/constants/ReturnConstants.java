package com.hjcrm.publics.constants;

/**
 * 常量返回值
 * @author likang
 * @date 2016-10-13 上午11:35:54
 */
public class ReturnConstants {
	
	public static final String SUCCESS = "success";//请求成功返回字符标志
	
	public static final String FAILED = "failed";//请求失败返回字符标志
	
	public static final String ERROR = "error";//程序错误
	
	public static final String 	OLION_NULL = "-1";//参数为空
	
	public static final String PARAM_NULL = "0";//参数为空
	
	public static final String USER_NOT_EXIST = "1";//用户不存在
	
	public static final String PASSWORD_ERROR = "2";//密码错误
	
	public static final String OLD_PASSWORD_NOT_SAME = "3";//旧密码不匹配
	
	public static final String USER_NO_LOGIN = "4";//用户未登录
	
	public static final String USER_YES_EXIST = "5";//用户已经存在
	
	public static final String lOGINUSER_NO_CREATERUSER = "6";//当前登录用户不是数据创建人

	public static final String RESOURCE_DELETE = "7";//已成交资源不能删除
	
	public static final String USER_NO_POWER = "8";//用户没有权限操作
	
	public static final String EXCEL_NULL = "9";//导入文件的内容为空
	
	public static final String STUDENT_DELETE = "10";//无删除学员权限
	
	public static final String STUDENT_NO_COMMIT = "11";//学员状态存在非已到账状态，不能提交客服//学员状态存在非已成交和已退回，不能提交行政//不能转入回访表
	
	public static final String NO_EDIT = "12";//不能修改
	
	public static final String NO_MATCHINFO = "13";//成交金额+网络培训费 != 收款金额，不能进行确认对账
	
	public static final String REMIT_USER = "14";//代汇款人为空
	
	public static final String MATCHINFO_AND_STUDENT = "15";//所选数据未有匹配
	
	public static final String RESOURCE_STUDENT_NO_FENPEI = "16";//资源或者学员存在已分配数据，不能进行分配
	
	public static final String STUDENT_COURSEID_COMMIT = "17";//不能分配客服
	
	public static final String STUDENT_COURSE_COMSTER_NOT = "18";//分配学员课程和分配客服人员不匹配
	
	public static final String USER_NO_OPEN = "19";//用户无权限操作并打开，如果需要此权限，请联系运营部
	
}
