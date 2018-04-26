package com.hjcrm.publics.interceptorfiles;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.hjcrm.publics.util.UserContext;
import com.hjcrm.system.entity.User;


/**
 * 后台登陆拦截器  拦截.do结尾的请求
 * @author likang
 * @date 2016-10-13 上午10:09:26
 */
public class BackgroundLoginInterceptor implements HandlerInterceptor {
	
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,Object arg2) throws Exception {
		String servletPath = request.getServletPath();
		 User loginUser = UserContext.getLoginUser();
			//如果登录成功
		if(loginUser != null){
			return true;
		}else{
			if("/login.do".equals(servletPath)){
				return true;
			}else if ("/system/test.do".equals(servletPath) || "/queryUserById.do".equals(servletPath) || "/forget/indexEmail.do".equals(servletPath) || "/forget/sendEmail.do".equals(servletPath) 
					|| "/forget/resetLink.do".equals(servletPath) || "/forget/resetPassword.do".equals(servletPath) 
					|| "/report/queryPerformanceAll.do".equals(servletPath)|| "/report/queryPerformanceTodayMoth.do".equals(servletPath)
					|| "/report/queryPerformanceCourse.do".equals(servletPath) || "/report/queryPerformanceMoth.do".equals(servletPath)
					|| "/report/queryPerformanceTodayWeek.do".equals(servletPath) || "/report/queryPerformanceYestoday.do".equals(servletPath) 
					|| "/report/queryPerformanceAllnotAC.do".equals(servletPath)|| "/report/queryPerformanceTodayMothnotAC.do".equals(servletPath)
					|| "/report/queryPerformanceTodayWeeknotAC.do".equals(servletPath) || "/report/queryPerformanceYestodaynotAC.do".equals(servletPath) ){
				return true;
			}else{
				response.sendRedirect(request.getContextPath()+"/login.do");
				return false;
			}
		}
	}
	
	public void afterCompletion(HttpServletRequest request,HttpServletResponse response, Object arg2, Exception arg3)throws Exception {

	}

	public void postHandle(HttpServletRequest request, HttpServletResponse response,Object arg2, ModelAndView arg3) throws Exception {

	}
}
