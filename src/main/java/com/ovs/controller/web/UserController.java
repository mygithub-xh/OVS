package com.ovs.controller.web;

import com.ovs.entity.*;
import com.ovs.service.ManipulateLogService;
import com.ovs.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {
    @Autowired
    public UserService userService;
    @Autowired
    public ManipulateLogService manipulateLogService;
    /**
     * 用户管理列表页
     * @param model
     * @return
     */
    @RequestMapping(value="/list",method= RequestMethod.GET)
    public ModelAndView list(ModelAndView model){
        model.setViewName("admin/user_list");
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
        Map<String, Object> ret = new HashMap<String, Object>();
        Map<String, Object> queryMap = new HashMap<String, Object>();
        queryMap.put("username", "%"+username+"%");
        queryMap.put("offset", page.getOffset());
        queryMap.put("pageSize", page.getRows());
        ret.put("rows", userService.findList(queryMap));
        ret.put("total", userService.getTotal(queryMap));
        return ret;
    }

    /**
     * 编辑用户操作
     * @param
     * @return
     */
    @RequestMapping(value="/delete",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> delete(HttpSession session,
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
        if(userService.delete(idsString) <= 0){
            ret.put("type", "error");
            ret.put("msg", "删除失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        Map<String, Object> state=((Map)session.getAttribute("login_state"));
        if (((Integer)state.get("user_type"))==1)
            manipulateLogService.add(new ManipulateLog(((User_admin)state.get("user")).getName(),"管理员删除用户id:"+idsString,""));
        else
            manipulateLogService.add(new ManipulateLog(((User)state.get("user")).getName(),"用户注销id:"+idsString,""));
        return ret;
    }

    /**
     * 编辑用户操作
     * @param user
     * @return
     */
    @RequestMapping(value="/edit",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> edit(HttpSession session,User user){
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
        if(StringUtils.isEmpty(user.getPassword())){
            ret.put("type", "error");
            ret.put("msg", "密码不能为空!");
            return ret;
        }
        User existUser = userService.findByUserName(user.getLoginname());
        if(existUser != null){
            if(user.getId() != existUser.getId()){
                ret.put("type", "error");
                ret.put("msg", "该用户名已经存在!");
                return ret;
            }

        }
        if(userService.edit(user) <= 0){
            ret.put("type", "error");
            ret.put("msg", "修改失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "修改成功!");
        Map<String, Object> state=((Map)session.getAttribute("login_state"));
        if (((Integer)state.get("user_type"))==1)
            manipulateLogService.add(new ManipulateLog(((User_admin)state.get("user")).getName(),"管理员修改用户:"+user.getName(),""));
        else
            manipulateLogService.add(new ManipulateLog(((User)state.get("user")).getName(),"用户修改个人信息",""));
        return ret;
    }


    /**
     * 添加用户操作
     * @param user
     * @return
     */
    @RequestMapping(value="/add",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> add(HttpSession session,User user){
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
        if(StringUtils.isEmpty(user.getPassword())){
            ret.put("type", "error");
            ret.put("msg", "密码不能为空!");
            return ret;
        }
        User existUser = userService.findByUserName(user.getLoginname());
        if(existUser != null){
            ret.put("type", "error");
            ret.put("msg", "该用户已经存在!");
            return ret;
        }
        if(userService.add(user) <= 0){
            ret.put("type", "error");
            ret.put("msg", "添加失败!");
            return ret;
        }
        ret.put("type", "success");
        ret.put("msg", "添加成功!");
        Map<String, Object> state=((Map)session.getAttribute("login_state"));
        if (((Integer)state.get("user_type"))==1)
            manipulateLogService.add(new ManipulateLog(((User_admin) state.get("user")).getName(),"管理员添加用户:"+user.getName(),""));
        else
            manipulateLogService.add(new ManipulateLog(((User) state.get("user")).getName(),"注册用户:"+user.getName(),""));
        return ret;
    }

    /**
     * 上传用户头像图片
     * @param photo
     * @param request
     * @param response
     * @return
     * @throws IOException
     */
    @RequestMapping(value="/upload_photo",method=RequestMethod.POST)
    @ResponseBody
    public Map<String, String> uploadPhoto(MultipartFile photo,
                                           HttpServletRequest request,
                                           HttpServletResponse response) throws IOException {
        Map<String, String> ret = new HashMap<String, String>();
        if(photo == null){
            //文件没有选择
            ret.put("type", "error");
            ret.put("msg", "请选择文件！");
            return ret;
        }
        if(photo.getSize() > 10485760){
            //文件没有选择
            ret.put("type", "error");
            ret.put("msg", "文件大小超过10M，请上传小于10M的图片！");
            return ret;
        }
        String suffix = photo.getOriginalFilename().substring(photo.getOriginalFilename().lastIndexOf(".") + 1);
        if(!"jpg,png,gif,jpeg".contains(suffix.toLowerCase())){
            ret.put("type", "error");
            ret.put("msg", "文件格式不正确，请上传jpg,png,gif,jpeg格式的图片！");
            return ret;
        }
        String savePath = request.getServletContext().getRealPath("/") + "\\headimgs\\";
        System.out.println(savePath);
        File savePathFile = new File(savePath);
        if(!savePathFile.exists()){
            savePathFile.mkdir();//如果不存在，则创建一个文件夹upload
        }
        //把文件转存到这个文件夹下
        String filename = new Date().getTime() + "." + suffix;
        photo.transferTo(new File(savePath + filename ));
        ret.put("type", "success");
        ret.put("msg", "图片上传成功！");
        ret.put("src", request.getServletContext().getContextPath() + "/headimgs/" + filename);
        Map<String, Object> state=((Map)request.getSession().getAttribute("login_state"));
        if (((Integer)state.get("user_type"))==1)
            manipulateLogService.add(new ManipulateLog(((User_admin) state.get("user")).getName(),"管理员上传头像",""));
        else
            manipulateLogService.add(new ManipulateLog(((User) state.get("user")).getName(),"用户上传头像",""));
        return ret;
    }

}
