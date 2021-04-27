package com.lagou.service.impl;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.ResourceMapper;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ResourceServiceImpl implements ResourceService {

    @Autowired
    private ResourceMapper resourceMapper;
    /**
     * 资源分页 , 多条件查询
     *
     * @param resourceVO
     */
    @Override
    public PageInfo<Resource> findAllResourceByPage(ResourceVO resourceVO) {
        PageHelper.startPage(resourceVO.getCurrentPage() , resourceVO.getPageSize());
        List<Resource> resourceList = resourceMapper.findAllResourceByPage(resourceVO);
        return new PageInfo<>(resourceList);
    }

    /**
     * 新增
     *
     * @param resource
     */
    @Override
    public void saveResource(Resource resource) {
        Date date = new Date();
        resource.setCreatedTime(date);
        resource.setUpdatedTime(date);

        resource.setCreatedBy("test");
        resource.setUpdatedBy("test");

        resourceMapper.saveResource(resource);
    }

    /**
     * 修改
     *
     * @param resource
     */
    @Override
    public void updateResrouce(Resource resource) {
        resource.setUpdatedTime(new Date());

        resource.setUpdatedBy("test");

        resourceMapper.updateResource(resource);

    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void deleteResource(Integer id) {
        //删除此资源与角色role关联的关系
        resourceMapper.deleteRoleResourceRelationByResourceId(id);
        //删除此资源
        resourceMapper.deleteResource(id);
    }
}
