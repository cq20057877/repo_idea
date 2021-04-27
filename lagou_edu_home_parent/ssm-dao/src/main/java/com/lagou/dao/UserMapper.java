package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface UserMapper {

    /**
     * 用户分页和多条件组合查询
     */
    public List<User> findAllUserByPage(UserVO userVO);

    /**
     * 用户状态设置
     * @param user
     */
    public void updateUserStatus(User user);

    /**
     * 用户登录(根据用户名查询用户信息)
     */
    public User login(User user);


    /**
     * 根据userId清空此id中间表的所有关系
     * @param userId
     */
    void deleteUserRoleRelationByUserId(Integer userId);

    /**
     * 根据userId给user重新分配角色
     * @param relation
     */
    void userContextRole(User_Role_relation relation);

    /**
     * 根据userId查询role
     * @param id
     * @return
     */
    List<Role> findUserRoleById(Integer id);

    /**
     * 根据角色id , 查询所有parent_id = -1的菜单
     * @param roleIds
     * @return
     */
    List<Menu> findParentMenuByRoleId(List<Integer> roleIds);

    /**
     * 根据父级菜单id查询子菜单
     * @param parentId
     * @return
     */
    List<Menu> findSubMenuByParentId(Integer parentId);

    /**
     * 根据角色id获取对应的资源信息
     * @param roleIds
     * @return
     */
    List<Resource> findResourceByRoleId(List<Integer> roleIds);
}
