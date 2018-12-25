package cn.it.controller;


import cn.it.Permission;
import cn.it.PermissionService;
import cn.it.Role;
import cn.it.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;

@Controller
@RequestMapping("role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private PermissionService permissionService;

    @RequestMapping("findAll")
    public ModelAndView findAll() throws Exception{
        List<Role> roles = roleService.findAll();
        ModelAndView mv = new ModelAndView();
        mv.setViewName("role-list");
        mv.addObject("roleList",roles);
        return mv;
    }

    @RequestMapping("save")
    public String  save(Role role)throws Exception{
        roleService.save(role);
        return "redirect:findAll";
    }



    @RequestMapping("findRoleByIdAndAllPermission")
    public ModelAndView findRoleByIdAndAllPermission(@RequestParam(name = "id",required = true) String id) throws Exception{
        ModelAndView mv = new ModelAndView();
        Role roleById = roleService.findRoleById(id);
        List<Permission> otherPermission = permissionService.findOtherPermission(id);
        mv.addObject("role",roleById);
        mv.addObject("permissionList",otherPermission);
        mv.setViewName("role-permission-add");
        return mv;
    }

    @RequestMapping("addPermissionToRole")
    public String addPermissionToRole(@RequestParam(name = "roleId",required = true)String roleId,@RequestParam(name = "ids",required = true)String[] ids){
        roleService.addPermissionToRole(roleId,ids);
        return "redirect:findAll";
    }
}
