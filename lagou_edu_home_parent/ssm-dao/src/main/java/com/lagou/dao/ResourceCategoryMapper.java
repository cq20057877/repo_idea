package com.lagou.dao;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryMapper {
    List<ResourceCategory> findAllResourceCategory();

    void saveResourceCategory(ResourceCategory resourceCategory);

    void updateResourceCategory(ResourceCategory resourceCategory);

    void deleteResourceCategory(Integer id);
}
