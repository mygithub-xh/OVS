package com.ovs.controller.web;

import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.Map;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.ovs.entity.ManipulateLog;
import com.ovs.entity.User;
import com.ovs.entity.User_admin;
import com.ovs.service.AdminService;
import com.ovs.service.ManipulateLogService;
import com.ovs.service.UserService;
import com.ovs.util.CpachaUtil;
import com.ovs.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;


/**
 * 系统主页控制器
 * @author llq
 *
 */

@RequestMapping("/system")
@Controller
public class SystemController {

	@Autowired
	private UserService userService;

	@Autowired
	private AdminService adminService;
	@Autowired
	private ManipulateLogService manipulateLogService;

	@RequestMapping(value = "/index",method=RequestMethod.GET)
	public ModelAndView index(ModelAndView model){
		model.setViewName("admin/admin_index");
		return model;
	}
	@RequestMapping(value = "/main",method=RequestMethod.GET)
	public ModelAndView main(ModelAndView model){
		model.setViewName("user/main");
		return model;
	}
	/**
	 * 登陆页面
	 * @return
	 */
	@RequestMapping(value = "/login" ,method = RequestMethod.GET)
	public String login(){
		return "system/login";
	}
	/*@RequestMapping(value = "/admin_login",method=RequestMethod.GET)
	public ModelAndView login(ModelAndView model){
		model.setViewName("system/admin_login");
		return model;
	}*/

	/**
	 * 注销登录
	 * @param request
	 * @return
	 */
	@RequestMapping(value = "/admin_login_out",method=RequestMethod.GET)
	public String loginOut(HttpServletRequest request){
		request.getSession().setAttribute("login_state", null);
		return "redirect:login";
	}

	/**
	 * 登录表单提交
	 * @return
	 */
	@RequestMapping(value = "/login",method=RequestMethod.POST)
	@ResponseBody
	public Map<String, String> login(
			@RequestParam(value="loginname",required=true) String username,
			@RequestParam(value="psw",required=true) String password,
			@RequestParam(value="vcode",required=true) String vcode,
			@RequestParam(value="type",required=true) int type,
			HttpServletRequest request
	) throws UnsupportedEncodingException, NoSuchAlgorithmException {
		Map<String, String> ret = new HashMap<String, String>();
		if(StringUtils.isEmpty(username)){
			ret.put("type", "error");
			ret.put("msg", "用户名不能为空!");
			return ret;
		}
		if(StringUtils.isEmpty(password)){
			ret.put("type", "error");
			ret.put("msg", "密码不能为空!");
			return ret;
		}
		if(StringUtils.isEmpty(vcode)){
			ret.put("type", "error");
			ret.put("msg", "验证码不能为空!");
			return ret;
		}
		String loginCpacha = (String)request.getSession().getAttribute("loginCpacha");
		if(StringUtils.isEmpty(loginCpacha)){
			ret.put("type", "error");
			ret.put("msg", "长时间未操作，会话已失效，请刷新后重试!");
			return ret;
		}
		if(!vcode.toUpperCase().equals(loginCpacha.toUpperCase())){
			ret.put("type", "error");
			ret.put("msg", "验证码错误!");
			return ret;
		}
		request.getSession().setAttribute("loginCpacha", null);
		//从数据库中去查找用户
			//管理员
		if (type==1) {
			User_admin user = adminService.login_check(new User_admin(username,password));
			if (user == null) {
				ret.put("type", "error");
				ret.put("msg", "用户名或密码错误!");
				return ret;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("user", user);
			map.put("user_type", 1);
			request.getSession().setAttribute("login_state", map);
			ret.put("type", "success");
			ret.put("msg", "登录成功!");
			manipulateLogService.add(new ManipulateLog(user.getName(), "管理员登录后台", ""));
			return ret;
		}else{
			User user = userService.login_check(new User(username, password));
			if (user == null) {
				ret.put("type", "error");
				ret.put("msg", "用户名或密码错误!");
				return ret;
			}
			Map<String, Object> map = new HashMap<>();
			map.put("user", user);
			map.put("user_type", 2);
			request.getSession().setAttribute("login_state", map);
			ret.put("type", "success");
			ret.put("msg", "登录成功!");
			manipulateLogService.add(new ManipulateLog(user.getName(), "用户登录后台", ""));
			return ret;
		}
	}


	/**
	 * 显示 验证码
	 * @param request
	 * @param vl
	 * @param w
	 * @param h
	 * @param response
	 */
	@RequestMapping(value="/get_cpacha",method=RequestMethod.GET)
	public void getCpacha(HttpServletRequest request,
						  @RequestParam(value="vl",defaultValue="4",required=false) Integer vl,
						  @RequestParam(value="w",defaultValue="98",required=false) Integer w,
						  @RequestParam(value="h",defaultValue="33",required=false) Integer h,
						  HttpServletResponse response){
		CpachaUtil cpachaUtil = new CpachaUtil(vl, w, h);
		String generatorVCode = cpachaUtil.generatorVCode();
		request.getSession().setAttribute("loginCpacha", generatorVCode);
		BufferedImage generatorRotateVCodeImage = cpachaUtil.generatorRotateVCodeImage(generatorVCode, true);
		try {
			ImageIO.write(generatorRotateVCodeImage, "gif", response.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
