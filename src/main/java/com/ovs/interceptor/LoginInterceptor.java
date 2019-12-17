package com.ovs.interceptor;

import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.tags.EscapeBodyTag;


/**
 * 登录过滤拦截器
 * @author llq
 *
 */
public class LoginInterceptor  implements HandlerInterceptor{

	@Override
	public void afterCompletion(HttpServletRequest arg0,
								HttpServletResponse arg1, Object arg2, Exception arg3)
			throws Exception {

	}

	@Override
	public void postHandle(HttpServletRequest arg0, HttpServletResponse arg1,
						   Object arg2, ModelAndView arg3) throws Exception {
	}

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response,
							 Object arg2) throws Exception {
		String url = request.getRequestURI();
		//System.out.println("进入拦截器，url = " + url);
		Map<String, Object> user = (Map<String, Object>)request.getSession().getAttribute("login_state");
		if(user == null){
			//表示未登录或者登录状态失效
			System.out.println("未登录或登录失效，url = " + url);
			if("XMLHttpRequest".equals(request.getHeader("X-Requested-With"))){
				//ajax请求
				Map<String, String> ret = new HashMap<String, String>();
				ret.put("type", "error");
				ret.put("msg", "登录状态已失效，请重新去登录!");
				ObjectMapper objectMapper = new ObjectMapper();
				response.getWriter().write(objectMapper.writeValueAsString(ret));
				return false;
			}
				response.sendRedirect(request.getContextPath() + "/system/login");
			return false;
		}else {
			Integer user_type=(Integer) user.get("user_type");
			if (user_type==2&&url.contains("/admin_user/")) {
				response.sendRedirect(request.getContextPath() + "/system/login");
				return false;
			}
		}

		return true;
	}

}
