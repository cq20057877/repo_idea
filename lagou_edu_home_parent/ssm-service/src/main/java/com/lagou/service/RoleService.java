package com.lagou.service;

import com.lagou.domain.Menu;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVo;

import java.util.List;

public interface RoleService {

    List<Role> findAllRole(Role role);

    void saveRole(Role role);

    void updateRole(Role role);

    void RoleContextMenu(RoleMenuVo roleMenuVo);

    void deleteRole(Integer id);

    List<Menu> findAllMenu();
}
