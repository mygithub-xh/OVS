package com.ovs.controller.web;


import com.ovs.entity.ManipulateLog;
import com.ovs.entity.Page;
import com.ovs.entity.User;
import com.ovs.entity.User_admin;
import com.ovs.service.AdminService;
import com.ovs.service.ManipulateLogService;
import com.ovs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RequestMapping("/admin_user")
@Controller
public class AdminUserController {

    @Autowired
    public AdminService adminService;
    @Autowired
    public ManipulateLogService manipulateLogService;
    /**
     * 用户管理列表页
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("admin/adminuser_list");
        return model;
    }

    /**
     * 获取用户列表
     * @param username
     * @param page
     * @return
     */
    @RequestMapping(value="/get_list",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, Object> getList(
            @RequestParam(value="username",required=false,defaultValue="") String username,
            Page page
    ){
        Map<String, Object> ret = new HashMap<>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("name", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", adminService.findList(queryMap));
        ret.put("total", adminService.getTotal(queryMap));
        return ret;
    }

    /**
     * 编辑用户操作
     * @param
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete( HttpSession session,
            @RequestParam(value="ids[]",required=true) Long[] ids
    ){
        Map<String, String> ret = new HashMap<String, String>();
        if(ids == null){
            ret.put("type", "error");
            ret.put("msg", "请选择要删除的数据!");
            return ret;
        }
        String idsString = "";
        for(Long id:ids){
            idsString += id + ",";
        }
        idsString = idsString.substring(0,idsString.length()-1);
        if(adminService.delete(idsString) <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        Map<String, Object> state= (Map<String, Object>) session.getAttribute("login_state");
        manipulateLogService.add(new ManipulateLog( ((User_admin)state.get("user")).getName(),"管理员删除了管理员用户:"+idsString,""));
        return ret;
    }

    /**
     * 编辑用户操作
     * @param user
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(HttpSession session,User_admin user){
        Map<String, String> ret = new HashMap<String, String>();
        if(user == null){
            ret.put("type", "error");
            ret.put("msg", "数据绑定出错，请联系开发作者!");
            return ret;
        }
        if(StringUtils.isEmpty(user.getLoginname())||StringUtils.isEmpty(user.getName())){
            ret.put("type", "error");
            ret.put("msg", "用户名或登录名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(user.getPsw())){
            ret.put("type", "error");
            ret.put("msg", "密码不能为空!");
            return ret;
        }
        User_admin existUser = adminService.findByUserName(user.getLoginname());
        if(existUser != null){
            if(user.getId() != existUser.getId()){
                ret.put("type", "error");
                ret.put("msg", "该用户已经存在!");
                return ret;
            }

        }
        if(adminService.edit(user) <= 0){
            ret.put("type", "error");
            ret.put("msg", "修改失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        Map<String, Object> state= (Map<String, Object>) session.getAttribute("login_state");
        manipulateLogService.add(new ManipulateLog( ((User_admin)state.get("user")).getName(),"管理员修改了管理员用户:"+user.getName(),""));
        return ret;
    }


    /**
     * 添加用户操作
     * @param user
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(HttpSession session,User_admin user){
        Map<String, String> ret = new HashMap<>();
        if(user == null){
            ret.put("type", "error");
            ret.put("msg", "数据绑定出错，请联系开发作者!");
            return ret;
        }
        if(StringUtils.isEmpty(user.getLoginname())||StringUtils.isEmpty(user.getName())){
            ret.put("type", "error");
            ret.put("msg", "用户名或登录名不能为空!");
            return ret;
        }
        if(StringUtils.isEmpty(user.getPsw())){
            ret.put("type", "error");
            ret.put("msg", "密码不能为空!");
            return ret;
        }
        User_admin existUser = adminService.findByUserName(user.getLoginname());
        if(existUser != null){
            ret.put("type", "error");
            ret.put("msg", "该用户已经存在!");
            return ret;
        }
        if(adminService.add(user) <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        Map<String, Object> state= (Map<String, Object>) session.getAttribute("login_state");
        manipulateLogService.add(new ManipulateLog( ((User_admin)state.get("user")).getName(),"管理员添加了管理员用户:"+user.getName(),""));
        return ret;
    }
}
