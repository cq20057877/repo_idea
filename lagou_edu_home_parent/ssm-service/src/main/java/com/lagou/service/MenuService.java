package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;

import java.util.List;

/**
 * 菜单接口
 */
public interface MenuService {

    List<String> findMenuByRoleId(Integer roleId);

    PageInfo<Menu> findAllMenu(MenuVo menuVo);

    Menu findMenuInfoById(Integer id);

    void saveMenu(Menu menu);

    void updateMenu(Menu menu);
}
