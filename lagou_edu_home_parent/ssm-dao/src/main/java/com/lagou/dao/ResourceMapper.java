package com.lagou.dao;

import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

/**
 * 资源接口
 */
public interface ResourceMapper {

    /**
     * 资源分页 , 多条件查询
     */
    List<Resource> findAllResourceByPage(ResourceVO resourceVO);

    /**
     * 新增
     * @param resource
     */
    void saveResource(Resource resource);

    /**
     * 修改
     * @param resource
     */
    void updateResource(Resource resource);

    /**
     * 删除此id关联的资源与角色的对应关系
     * @param id
     */
    void deleteRoleResourceRelationByResourceId(Integer id);

    /**
     * 删除此id的资源信息
     * @param id
     */
    void deleteResource(Integer id);
}
