package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;

import java.util.List;
import java.util.Map;

public interface UserService {
    /**
     * 用户分页和多条件查询
     */
    public PageInfo<User> findAllPageByPage(UserVO userVO);

    /**
     * 用户状态设置
     * @param user
     */
    public void updateUserStatus(User user);

    /**
     * 用户登录(根据用户名查询用户信息)
     */
    public User login(User user) throws Exception;

    /**
     * 根据userId查询对应的角色role
     * @param id
     * @return
     */
    List<Role> findUserRoleById(Integer id);

    /**
     * 根据userId给user重新分配角色
     * @param userVO
     */
    void userContextRole(UserVO userVO);

    /**
     * 根据userId获取菜单以及资源并封装成map返回
     * @param userId
     * @return
     */
    Map<String , Object> getUserPermissions(Integer userId);
}
