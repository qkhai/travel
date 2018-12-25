package cn.it.controller;

import cn.it.Role;
import cn.it.RoleService;
import cn.it.UserInfo;
import cn.it.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("user")
public class UserController{

    @Autowired
    private UserService userService;

    @Autowired
    private RoleService roleService;

    @RequestMapping("findAll")
    public ModelAndView findAll() throws Exception{
        List<UserInfo> infoList = userService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.addObject("userList",infoList);
        mv.setViewName("user-list");
        return mv;
    }

    @RequestMapping("save")
    public String save(UserInfo userInfo) throws Exception{
        userService.save(userInfo);
        return "redirect:findAll.do";
    }

    @RequestMapping("findById")
    public ModelAndView findById(String id) throws Exception {
        UserInfo userInfos = userService.findById(id);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user",userInfos);
        mv.setViewName("user-show");
        return mv;
    }

    @RequestMapping("findUserByIdAndAllRole")
    public ModelAndView findUserByIdAndAllRole(@RequestParam(name = "id",required = true) String userid)throws Exception{
        UserInfo user = userService.findById(userid);
        List<Role> roleList = roleService.findOtherRole(userid);
        ModelAndView mv = new ModelAndView();
        mv.addObject("user", user);
        mv.addObject("roleList", roleList);
        mv.setViewName("user-role-add");
        return mv;
    }

    @RequestMapping("addRoleToUser")
    public String addRoleToUser(@RequestParam(name = "userId",required = true)String userId,@RequestParam(name = "ids",required = true) String[] ids){
        userService.addRoleToUser(userId,ids);
        return "redirect:findAll";
    }
}
