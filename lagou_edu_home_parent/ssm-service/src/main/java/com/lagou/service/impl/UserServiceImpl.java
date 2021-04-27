package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /**
     * 用户分页和多条件查询
     *
     * @param userVO
     */
    @Override
    public PageInfo<User> findAllPageByPage(UserVO userVO) {
        PageHelper.startPage(userVO.getCurrentPage() , userVO.getPageSize());
        return new PageInfo<User>(userMapper.findAllUserByPage(userVO));
    }

    /**
     * 用户状态设置
     *
     * @param user
     */
    @Override
    public void updateUserStatus(User user) {
        user.setUpdate_time(new Date());
        userMapper.updateUserStatus(user);
    }

    /**
     * 用户登录(根据用户名查询用户信息)
     *
     * @param user
     */
    @Override
    public User login(User user) throws Exception {
        //根据登录帐号查询出此用户对象
        User loginUser = userMapper.login(user);
        //判断不为空且比对密码正确
        if (loginUser != null && Md5.verify(user.getPassword() , Md5.MD5_KEY , loginUser.getPassword())){
            //登录成功
            return loginUser;
        }else {
            return null;
        }
    }

    /**
     * 根据userId查询对应的角色role
     *
     * @param id
     * @return
     */
    @Override
    public List<Role> findUserRoleById(Integer id) {
        return userMapper.findUserRoleById(id);
    }

    /**
     * 根据userId给user重新分配角色
     *
     * @param userVO
     */
    @Override
    public void userContextRole(UserVO userVO) {
        //根据userId清空此id中间表的所有关系
        userMapper.deleteUserRoleRelationByUserId(userVO.getUserId());
        //遍历userVO中的roleIdList , 挨个添加数据进去中间表
        Date date = new Date();
        for (Integer roleId : userVO.getRoleIdList()) {
            //先创建中间表对象
            User_Role_relation relation = new User_Role_relation();
            relation.setRoleId(roleId);
            relation.setUserId(userVO.getUserId());
            relation.setCreatedTime(date);
            relation.setUpdatedTime(date);

            relation.setCreatedBy("test");
            relation.setUpdatedBy("test");

            userMapper.userContextRole(relation);
        }
    }

    /**
     * 根据userId获取菜单以及资源并封装成map返回
     *
     * @param userId
     * @return
     */
    @Override
    public Map<String, Object> getUserPermissions(Integer userId) {
        //根据userId查询该用户的角色
        List<Role> roleList = userMapper.findUserRoleById(userId);

        //将roleList里面的roleId封装成一个list
        List<Integer> rIdList = new ArrayList<>();
        for (Role role : roleList) {
            rIdList.add(role.getId());
        }

        //根据角色的id查询所有父级目录
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(rIdList);

        //根据父菜单查询所有子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByParentId(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        //查询这些角色拥有的资源列表
        List<Resource> resourceList = userMapper.findResourceByRoleId(rIdList);

        //封装返回
        Map<String , Object> map = new HashMap<>();
        map.put("menuList" , parentMenu);
        map.put("resourceList" , resourceList);
        return map;
    }
}
