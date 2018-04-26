package com.hjcrm.publics.constants;

/**
 * 提成比例常量
 * @author likang
 * @date 2016-12-19 下午2:31:42
 */
public class ProportionConstants {
	/**
	 * 都不包括网络培训费
	 * 部门大类：0市场部 、1基金销售、 2机构客户部、 3重庆代理1、4重庆代理2 、5西安代理 、6南京代理1、 7南京代理2、8苏州代理、9泰州代理、10长春代理 、11太原代理
	 */

	/*********************************市场部******************************************************/
	public static final double SCB_0_4_CFP_OR_OTHER = 0.07;//市场部-小于4万-CFP及其他科目-7.00%
	public static final double SCB_0_4_AFP_PASSBEFORE = 0.05;//市场部-小于4万-AFP面授-通过前-5.00%
	public static final double SCB_0_4_AFP_PASSAFTER = 0.02;//市场部-小于4万-AFP面授-通过后-2.00%
	
	public static final double SCB_4_8_CFP_OR_OTHER = 0.1;//市场部-4万到8万-CFP及其他科目-10.00%
	public static final double SCB_4_8_AFP_PASSBEFORE = 0.07;//市场部-4万到8万-AFP面授-通过前-7.00%
	public static final double SCB_4_8_AFP_PASSAFTER = 0.03;//市场部-4万到8万-AFP面授-通过后-3.00%
	
	public static final double SCB_8_MORE_CFP_OR_OTHER = 0.12;//市场部-大于等于8万-CFP及其他科目-12.00%
	public static final double SCB_8_MORE_AFP_PASSBEFORE = 0.09;//市场部-大于等于8万-AFP面授-通过前-9.00%
	public static final double SCB_8_MORE_AFP_PASSAFTER = 0.03;//市场部-大于等于8万-AFP面授-通过后-3.00%
	
	/*********************************机构客户部******************************************************/
	
	/*********************************基金客服及销售******************************************************/
	public static final double JJKF_0_3_FAC = 0.07;//基金-客服-X＜3万-非AC项目-7.00%;
	public static final double JJKF_0_3_CFP_AFPW = 0.07;//基金-客服-X＜3万-CFP+AFP网络 -7.00%;
	public static final double JJKF_0_3_AFPM_PASSBEFORE = 0.05;//基金-客服-X＜3万-AFP面授-通过前- 5.00%;
	public static final double JJKF_0_3_AFPM_PASSAFTER = 0.02;//基金-客服-X＜3万-AFP面授-通过后- 2.00%;
	
	public static final double JJKF_3_6_FAC = 0.1;//基金-客服-3万≤X＜6万-非AC项目-10.00%;
	public static final double JJKF_3_6_CFP_AFPW = 0.07;//基金-客服-3万≤X＜6万-CFP+AFP网络 -7.00%;
	public static final double JJKF_3_6_AFPM_PASSBEFORE = 0.05;//基金-客服-3万≤X＜6万-AFP面授-通过前- 5.00%;
	public static final double JJKF_3_6_AFPM_PASSAFTER = 0.02;//基金-客服-3万≤X＜6万-AFP面授-通过后- 2.00%;
	
	public static final double JJKF_6_9_FAC = 0.15;//基金-客服-6万≤X＜9万-非AC项目-15.00%;
	public static final double JJKF_6_9_CFP_AFPW = 0.07;//基金-客服-6万≤X＜9万-CFP+AFP网络 -7.00%;
	public static final double JJKF_6_9_AFPM_PASSBEFORE = 0.05;//基金-客服-6万≤X＜9万-AFP面授-通过前- 5.00%;
	public static final double JJKF_6_9_AFPM_PASSAFTER = 0.02;//基金-客服-6万≤X＜9万-AFP面授-通过后- 2.00%;
	
	public static final double JJKF_9_MORE_FAC = 0.2;//基金-客服-X≥9万-非AC项目-20.00%;
	public static final double JJKF_9_MORE_CFP_AFPW = 0.07;//基金-客服-X≥9万-CFP+AFP网络 -7.00%;
	public static final double JJKF_9_MORE_AFPM_PASSBEFORE = 0.05;//基金-客服-X≥9万-AFP面授-通过前- 5.00%;
	public static final double JJKF_9_MORE_AFPM_PASSAFTER = 0.02;//基金-客服-X≥9万-AFP面授-通过后- 2.00%;
	
	/*********************************代理******************************************************/
	public static final double DL_CHONGQING1_CFP_OR_OTHER = 0.3;//代理-重庆1-CFP及其他科目-30.00%
	public static final double DL_CHONGQING1_AFPM_PASSBEFORE = 0.3;//代理-重庆1-AFP面授-通过前-30.00%
	public static final double DL_CHONGQING1_AFPM_PASSAFTER = 0.05;//代理-重庆1-AFP面授-通过后-5.00%
	
	public static final double DL_CHONGQING2_CFP_OR_OTHER = 0.3;//代理-重庆2-CFP及其他科目-30.00%
	public static final double DL_CHONGQING2_AFPM_PASSBEFORE = 0.3;//代理-重庆2-AFP面授-通过前-30.00%
	public static final double DL_CHONGQING2_AFPM_PASSAFTER = 0.05;//代理-重庆2-AFP面授-通过后-5.00%
	
	public static final double DL_XIAN_CFP_OR_OTHER = 0.35;//代理-西安-CFP及其他科目-35.00%
	public static final double DL_XIAN_AFPM_PASSBEFORE = 0.3;//代理-西安-AFP面授-通过前-30.00%
	public static final double DL_XIAN_AFPM_PASSAFTER = 0.05;//代理-西安-AFP面授-通过后-5.00%
	
	public static final double DL_NANJING1_CFP_OR_OTHER = 0.3;//代理-南京1-CFP及其他科目-30.00%
	public static final double DL_NANJING1_AFPM_PASSBEFORE = 0.3;//代理-南京1-AFP面授-通过前-30.00%
	public static final double DL_NANJING1_AFPM_PASSAFTER = 0.05;//代理-南京1-AFP面授-通过后-5.00%
	
	public static final double DL_NANJING2_CFP_OR_OTHER = 0.35;//代理-南京2-CFP及其他科目-35.00%
	public static final double DL_NANJING2_AFPM_PASSBEFORE = 0.3;//代理-南京2-AFP面授-通过前-30.00%
	public static final double DL_NANJING2_AFPM_PASSAFTER = 0.05;//代理-南京2-AFP面授-通过后-5.00%
	
	public static final double DL_SUZHOU_CFP_OR_OTHER = 0.3;//代理-苏州-CFP及其他科目-30.00%
	public static final double DL_SUZHOU_AFPM_PASSBEFORE = 0.3;//代理-苏州-AFP面授-通过前-30.00%
	public static final double DL_SUZHOU_AFPM_PASSAFTER = 0.05;//代理-苏州-AFP面授-通过后-5.00%
	
	public static final double DL_CHANGCHUN_CFP_OR_OTHER = 0.35;//代理-长春-CFP及其他科目-35.00%
	public static final double DL_CHANGCHUN_AFPM_PASSBEFORE = 0.3;//代理-长春-AFP面授-通过前-30.00%
	public static final double DL_CHANGCHUN_AFPM_PASSAFTER = 0.05;//代理-长春-AFP面授-通过后-5.00%
	
	public static final double DL_TAIYUAN_CFP_OR_OTHER = 0.3;//代理-太原-CFP及其他科目-30.00%
	public static final double DL_TAIYUAN_AFPM_PASSBEFORE = 0.3;//代理-太原-AFP面授-通过前-30.00%
	public static final double DL_TAIYUAN_AFPM_PASSAFTER = 0.05;//代理-太原-AFP面授-通过后-5.00%
	
	public static final double DL_TAIZHOU_CFP_OR_OTHER = 0.3;//代理-泰州-CFP及其他科目-30.00%
	public static final double DL_TAIZHOU_AFPM_PASSBEFORE = 0.3;//代理-泰州-AFP面授-通过前-30.00%
	public static final double DL_TAIZHOU_AFPM_PASSAFTER = 0.05;//代理-泰州-AFP面授-通过后-5.00%
	
	
	
}
