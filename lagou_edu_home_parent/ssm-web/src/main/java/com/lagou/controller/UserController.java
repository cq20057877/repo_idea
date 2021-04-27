package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.User;
import com.lagou.domain.UserVO;
import com.lagou.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private UserService userService;

    /**
     * 用户分页和多条件查询
     */
    @RequestMapping("/findAllUserByPage")
    public ResponseResult findAllUserByPage(@RequestBody UserVO userVO){
        PageInfo<User> pageInfo = userService.findAllPageByPage(userVO);
        return new ResponseResult(true , 200 , "响应成功" , pageInfo);
    }

    /**
     * 修改用户状态
     * @return
     */
    @RequestMapping("/updateUserStatus")
    public ResponseResult updateUserStatus(User user){
        userService.updateUserStatus(user);
        return new ResponseResult(true , 200 , "响应成功" , user.getStatus());
    }

    /**
     * 用户登录
     * @return
     */
    @RequestMapping("/login")
    public ResponseResult login(User user , HttpServletRequest request) throws Exception {
        User login = userService.login(user);
        if (login != null){
            //登录成功
            HttpSession session = request.getSession();

            //准备要用的数据
            String access_token = UUID.randomUUID().toString();
            Integer userId = login.getId();

            //保存用户id和access_token到session
            session.setAttribute("access_token" , access_token);
            session.setAttribute("user_id" , userId);

            //将查询出来的信息返回
            Map<String , Object> map = new HashMap<>();
            map.put("access_token" , access_token);
            map.put("user_id" , userId);
            //user也要返回给前端
            map.put("user" , login);
            return new ResponseResult(true , 1 , "登陆成功" , map);
        }else {
            //登录失败
            return new ResponseResult(true , 400 , "用户名或密码错误" , null);
        }
    }

    /**
     * 获取用户拥有的菜单权限
     * @return
     */
    @RequestMapping("/findUserRoleById")
    public ResponseResult findUserRoleById(Integer id){
        List<Role> roleList = userService.findUserRoleById(id);
        return new ResponseResult(true , 200 , "响应成功" , roleList);
    }

    /**
     * 分配角色
     * @return
     */
    @RequestMapping("/userContextRole")
    public ResponseResult userContextRole(@RequestBody UserVO userVO){

        userService.userContextRole(userVO);
        return new ResponseResult(true , 200 , "响应成功" , null);
    }

    /**
     * 获取用户拥有的菜单权限
     * @return
     */
    @RequestMapping("/getUserPermissions")
    public ResponseResult getUserPermissions(HttpServletRequest request){
        //从请求头得到token
        String request_token = request.getHeader("Authorization");
        //与session中的token进行比较
        HttpSession session = request.getSession();
        String session_token = session.getAttribute("access_token").toString();

        if (request_token.equals(session_token)){
            //验证通过
            Map<String, Object> permissions = userService.getUserPermissions(Integer.parseInt(session.getAttribute("user_id").toString()));
            return new ResponseResult(true , 200 , "获取用户权限信息成功" , permissions);
        }else {
            //验证不通过
            return new ResponseResult(false , 400 , "获取用户权限信息失败" , null);
        }


    }
}
