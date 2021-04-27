package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {
        return roleMapper.findAllRole(role);
    }

    @Override
    public void saveRole(Role role) {
        //补全
        Date date = new Date();
        role.setCreatedTime(date);
        role.setUpdatedTime(date);

        role.setCreatedBy("test");
        role.setUpdatedBy("test");

        roleMapper.saveRole(role);
    }

    @Override
    public void updateRole(Role role) {
        role.setUpdatedTime(new Date());

        role.setUpdatedBy("test");

        roleMapper.updateRole(role);
    }

    /**
     * 为角色分配菜单列表
     * @param roleMenuVo
     */
    @Override
    public void RoleContextMenu(RoleMenuVo roleMenuVo){

        //先清空此role_id原来所有的关联
        roleMapper.deleteMenuRelationByRoleId(roleMenuVo.getRoleId());

        //准备数据
        Date date = new Date();
        for (Integer menuId : roleMenuVo.getMenuIdList()) {
            Role_menu_relation role_menu_relation = new Role_menu_relation();

            role_menu_relation.setMenuId(menuId);
            role_menu_relation.setRoleId(roleMenuVo.getRoleId());

            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("test");
            role_menu_relation.setUpdatedBy("test");
            //添加到中间表
            roleMapper.saveMenuRelationByMenuId(role_menu_relation);
        }

    }

    /**
     * 删除角色
     * @param id
     */
    @Override
    public void deleteRole(Integer id) {
        //先删除中间表的关联
        roleMapper.deleteMenuRelationByRoleId(id);
        //再删除角色表中的数据
        roleMapper.deleteRole(id);
    }

    @Override
    public List<Menu> findAllMenu() {
        return roleMapper.findAllMenu(-1);
    }

}
