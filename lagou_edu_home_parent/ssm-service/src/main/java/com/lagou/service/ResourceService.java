package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;

import java.util.List;

public interface ResourceService {

    /**
     * 资源分页 , 多条件查询
     */
    PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO);

    /**
     * 新增
     * @param resource
     */
    void saveResource(Resource resource);

    /**
     * 修改
     * @param resource
     */
    void updateResrouce(Resource resource);

    /**
     * 删除
     * @param id
     */
    void deleteResource(Integer id);
}
