package com.lagou.service.impl;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

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

    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {
        //根据roleId去重查出此角色关联的所有resource信息
        List<Resource> resourceList = roleMapper.findAllResource(roleId);

        //将resourceList的categoryId取出 , 去重放到集合中 , 利用set集合不可重复的特性
        Set<Integer> set = new HashSet<>();
        for (Resource resource : resourceList) {
            set.add(resource.getCategoryId());
        }

        //将封装好的categoryId遍历 , 依次获得ResourceCategory对象 , 封装到list
        List<ResourceCategory> categoryList = new ArrayList<>();
        for (Integer categoryId : set) {
            ResourceCategory resourceCategory = roleMapper.findCategoryByCategoryId(categoryId);
            categoryList.add(resourceCategory);
        }

        //将装满了ResourceCategory的集合遍历 , 如果resourceList中的CategoryId与ResourceCategory的id相同就放到一个集合
        for (ResourceCategory resourceCategory : categoryList) {
            for (Resource resource : resourceList) {
                if (resource.getCategoryId().equals(resourceCategory.getId())){
                    resourceCategory.getResourceList().add(resource);
                }
            }
        }
        return categoryList;
    }

    /**
     * 为角色保存资源信息
     * 先清空 , 再重新保存
     *
     * @param roleResourseVO
     */
    @Override
    public void roleContextResource(RoleResourseVO roleResourseVO) {
        //清空中间表所有关联此角色Id的关系
        roleMapper.deleteRoleRelationResourceByRoleId(roleResourseVO.getRoleId());

        //依次将封装类中的Id集合添加 , 需要new一个中间表对象作为封装载体
        Date date = new Date();
        for (Integer resourceId : roleResourseVO.getResourceIdList()) {
            RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
            roleResourceRelation.setResourceId(resourceId);
            roleResourceRelation.setRoleId(roleResourseVO.getRoleId());
            roleResourceRelation.setCreatedTime(date);
            roleResourceRelation.setUpdatedTime(date);

            roleResourceRelation.setCreatedBy("test");
            roleResourceRelation.setUpdatedBy("test");

            roleMapper.roleContextResource(roleResourceRelation);
        }
    }
}
