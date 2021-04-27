package com.lagou.controller;


import com.lagou.domain.*;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;
    @Autowired
    private MenuService menuServicel;

    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){
        List<Role> allRole = roleService.findAllRole(role);
        return new ResponseResult(true , 200 , "响应成功" , allRole);
    }

    @RequestMapping("/saveOrUpdateRole")
    public ResponseResult saveOrUpdateRole(@RequestBody Role role){
        if (role.getId() == null){
            //新增
            roleService.saveRole(role);
            return new  ResponseResult(true , 200 , "新增成功" ,null);
        }else {
            //修改
            roleService.updateRole(role);
            return new  ResponseResult(true , 200 , "修改成功" ,null);
        }

    }

    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(){
        List<Menu> allMenu = roleService.findAllMenu();
        Map<String , Object> map = new HashMap<>();
        map.put("parentMenuList" , allMenu);
        return new  ResponseResult(true , 200 , "响应成功" , map);
    }

    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId){

        List<String> list = menuServicel.findMenuByRoleId(roleId);
        return new ResponseResult(true , 200 , "响应成功" , list);
    }

    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVo roleMenuVo){

        roleService.RoleContextMenu(roleMenuVo);
        return new ResponseResult(true , 200 , "响应成功" , null);
    }

    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id){
        roleService.deleteRole(id);
        return new ResponseResult(true , 200 , "响应成功" , null);
    }
}
