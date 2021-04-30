package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.MenuMapper;
import com.lagou.domain.Menu;
import com.lagou.domain.MenuVo;
import com.lagou.service.MenuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class MenuServiceImpl implements MenuService {

    @Autowired
    private MenuMapper menuMapper;

    @Override
    public List<String> findMenuByRoleId(Integer roleId) {
        return menuMapper.findMenuByRoleId(roleId);
    }

    @Override
    public PageInfo<Menu> findAllMenu(MenuVo menuVo) {
        PageHelper.startPage(menuVo.getCurrentPage() , menuVo.getPageSize());
        List<Menu> menuList = menuMapper.findAllMenu();
        return new PageInfo<>(menuList);
    }

    @Override
    public Menu findMenuInfoById(Integer id) {
        return menuMapper.findMenuInfoById(id);
    }

    @Override
    public void saveMenu(Menu menu) {
        Date date = new Date();
        menu.setCreatedTime(date);
        menu.setUpdatedTime(date);
        menu.setCreatedBy("test");
        menu.setUpdatedBy("test");
        if (menu.getParentId() == -1){
            menu.setLevel(0);
        }

        menuMapper.saveMenu(menu);
    }

    @Override
    public void updateMenu(Menu menu) {
        menu.setUpdatedTime(new Date());
        menu.setUpdatedBy("test");
        if (menu.getParentId() == -1){
            menu.setLevel(0);
        }
        menuMapper.updateMenu(menu);
    }

}
