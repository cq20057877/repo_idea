package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

/**
 * 角色mapper
 */
public interface RoleMapper {

    /**
     * 查询所有角色/条件查询
     */
    public List<Role> findAllRole(Role role);

    /**
     * 新增
     * @param role
     */
    void saveRole(Role role);

    /**
     * 修改
     * @param role
     */
    void updateRole(Role role);

    /**
     * 清空关系
     * @param roleId
     */
    void deleteMenuRelationByRoleId(Integer roleId);

    /**
     * 保存关系
     */
    void saveMenuRelationByMenuId(Role_menu_relation role_menu_relation);

    /**
     * 根据roleId删除角色
     * @param id
     */
    void deleteRole(Integer id);

    /**
     * 根据id查询此id下所有子菜单 , 如果传-1就是查询所有一级菜单以及下面的所有子菜单
     * @return
     */
    List<Menu> findAllMenu(Integer id);

    /**
     * 根据角色id查找所有资源(去重)
     * @param roleId
     * @return
     */
    List<Resource> findAllResource(Integer roleId);

    /**
     * 根据categoryId查找ResourceCategory
     * @param categoryId
     * @return
     */
    ResourceCategory findCategoryByCategoryId(Integer categoryId);

    /**
     * 清空中间表所有关联此角色Id的关系
     * @param roleId
     */
    void deleteRoleRelationResourceByRoleId(Integer roleId);

    /**
     * 将roleId和传来的ResourceId关联
     * @param roleResourceRelation
     */
    void roleContextResource(RoleResourceRelation roleResourceRelation);
}
