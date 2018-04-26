package com.hjcrm.publics.constants;

/**
 * 单据状态值
 * @author likang
 * @date 2016-11-1 下午5:19:53
 */
public class StateConstants {
	
	/******************************资源/学员状态信息***************************************************/
	public static final Integer state0 = 0;//0资源未分配 
	
	public static final Integer state1 = 1;//1资源已分配(未处理)
	
	public static final Integer state2 = 2;//2资源已处理
	
	public static final Integer studentstate0 = 0;//学员新增
	
	public static final Integer studentstate1 = 1;//学员已成交(销售填写成交记录)
	
	public static final Integer studentstate2 = 2;//学员已提交(销售提交行政)
	
	public static final Integer studentstate3 = 3;//学员已到账(行政确认到账)
	
	public static final Integer studentstate4 = 4;//学员已分配(行政分配客服)
	
	public static final Integer studentstate5 = 5;//学员已转入(客服从总表转入回访表)
	
	public static final Integer studentstate6 = 6;//学员已通过考试(客服)
	
	public static final Integer studentstate7 = 7;//学员已关课(客服)
	
	public static final Integer studentstate8 = 8;//学员已退回(行政退回销售)
	
	/******************************部门信息***************************************************/
	public static final Long DEPT_ZONGBU = 1L;//总部
	
	public static final Long DEPT_YUNYING = 2L;//运营部
	
	public static final Long DEPT_CAIWU = 3L;//财务部
	
	public static final Long DEPT_XINGZHENG = 4L;//行政部
	
	public static final Long DEPT_XIAOSHOU = 5L;//销售部
	
	public static final Long DEPT_KEFU = 6L;//客服部
	
	public static final Long DEPT_AC = 7L;//A/C项目部
	public static final Long DEPT_FAC = 8L;//非A/C项目部
	public static final Long DEPT_CHAOJIZD = 9L;//超级战队
	public static final Long DEPT_WUDIZD = 10L;//无敌战队
	public static final Long DEPT_LEITINGZD = 11L;//雷霆战队
	public static final Long DEPT_TONGLUZD = 12L;//同路人战队
	public static final Long DEPT_PHONEZD = 13L;//电话销售组
	public static final Long DEPT_XXRZD = 14L;//小鲜肉组
	public static final Long DEPT_HJJZD = 15L;//郝建军战队
	public static final Long DEPT_CLZD = 16L;//陈鑫战队
	public static final Long DEPT_GSZY = 30L;//华金金考-公司资源管理
	
	public static final Long DEPT_AFPZLH = 17L;//AFP邹林花
	public static final Long DEPT_AFPXC = 18L;//AFP熊婵
	public static final Long DEPT_CFPWYL = 19L;//CFP王瑜玲
	public static final Long DEPT_FACLWH = 20L;//非AC李卫华
	
	public static final Long DEPT_JGKHZ = 21L;//机构客户部
	
	public static final Long DEPT_NJDL = 22L;//南京代理
	public static final Long DEPT_SZDL = 23L;//苏州代理
	public static final Long DEPT_XADL = 24L;//西安代理
	public static final Long DEPT_CQDL = 25L;//重庆代理
	public static final Long DEPT_XJDL = 26L;//新疆代理
	public static final Long DEPT_TZDL = 27L;//泰州代理
	public static final Long DEPT_DL = 28L;//代理部
	
	public static final Long DEPT_GXKFB = 29L;//关系客户部


}
