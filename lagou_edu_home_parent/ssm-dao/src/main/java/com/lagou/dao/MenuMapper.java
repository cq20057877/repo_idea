package com.lagou.dao;

import com.lagou.domain.Menu;

import java.util.List;

public interface MenuMapper {

    /**
     * 根据角色ID查询关联菜单ID
     * @param roleId
     * @return
     */
    List<String> findMenuByRoleId(Integer roleId);

    /**
     * 查询所有
     * select * from menu
     * @return
     */
    List<Menu> findAllMenu();

    /**
     * 根据menuId查询menu
     * @param id
     * @return
     */
    Menu findMenuInfoById(Integer id);

    /**
     * 新增
     * @param menu
     */
    void saveMenu(Menu menu);

    /**
     * 修改
     * @param menu
     */
    void updateMenu(Menu menu);
}
