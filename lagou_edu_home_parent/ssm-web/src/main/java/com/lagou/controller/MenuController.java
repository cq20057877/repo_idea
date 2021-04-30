package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import com.lagou.domain.ResponseResult;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/menu")
public class MenuController {

    @Autowired
    private MenuService menuService;
    @Autowired
    private RoleService roleService;

    /**
     * 就是查询所有 , 接口文档有分页 , 视频没有 ,先根据视频来 , 分页数据在service注释了
     * @param menuVo
     * @return
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findAllMenu(MenuVo menuVo){

        PageInfo<Menu> menuPageInfo = menuService.findAllMenu(menuVo);
        return new ResponseResult(true , 200 , "响应成功" , menuPageInfo);
    }

    /**
     * 根据菜单ID 查询菜单信息
     * 如果是新增 , id = -1 即不查询关联的menu , 如果是修改 , 根据传来的id查询该id对应的menu信息
     * @param id
     * @return
     */
    @RequestMapping("/findMenuInfoById")
    public ResponseResult findMenuInfoById(Integer id){
        Map<String , Object> map = new HashMap<>();
        if (id != -1){
            //修改
            map.put("menuInfo" , menuService.findMenuInfoById(id));
        }else {
            map.put("menuInfo" , null);
        }
        map.put("parentMenuList" , roleService.findAllMenu());
        return new ResponseResult(true , 200 , "响应成功" , map);
    }


    @RequestMapping("/saveOrUpdateMenu")
    public ResponseResult saveOrUpdateMenu(@RequestBody Menu menu){
        if (menu.getId() == null){
            //新增
            menuService.saveMenu(menu);
        }else {
            //修改
            menuService.updateMenu(menu);
        }

        return new ResponseResult(true , 200 , "响应成功" , null);
    }
}
