package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    /**
     * 查询所有
     * @return
     */
    List<ResourceCategory> findAllResourceCategory();

    void saveResourceCategory(ResourceCategory resourceCategory);

    void updateResourceCategory(ResourceCategory resourceCategory);

    /**
     * 删除
     * @param id
     */
    void deleteResourceCategory(Integer id);
}
