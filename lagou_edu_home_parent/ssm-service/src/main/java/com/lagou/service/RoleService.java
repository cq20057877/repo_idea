package com.lagou.service;

import com.lagou.domain.*;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole(Role role);

    void saveRole(Role role);

    void updateRole(Role role);

    void RoleContextMenu(RoleMenuVo roleMenuVo);

    void deleteRole(Integer id);

    List<Menu> findAllMenu();

    List<ResourceCategory> findResourceListByRoleId(Integer roleId);

    /**
     * 为角色保存资源信息
     * 先清空 , 再重新保存
     * @param roleResourseVO
     */
    void roleContextResource(RoleResourseVO roleResourseVO);
}
