package com.lagou.service.impl;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;
import java.util.List;

@Service
@Transactional
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    @Override
    public List<ResourceCategory> findAllResourceCategory() {
        return resourceCategoryMapper.findAllResourceCategory();
    }

    /**
     * 新增
     * @param resourceCategory
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {
        //补全数据
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("test");
        resourceCategory.setUpdatedBy("test");
        resourceCategoryMapper.saveResourceCategory(resourceCategory);
    }

    /**
     * 根据id修改
     * @param resourceCategory
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {
        resourceCategory.setUpdatedTime(new Date());
        resourceCategory.setUpdatedBy("test");
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /**
     * 删除
     *
     * @param id
     */
    @Override
    public void deleteResourceCategory(Integer id) {
        resourceCategoryMapper.deleteResourceCategory(id);
    }
}
