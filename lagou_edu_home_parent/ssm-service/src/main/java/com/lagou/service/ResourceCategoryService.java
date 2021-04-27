package com.lagou.service;

import com.lagou.domain.ResourceCategory;

import java.util.List;

public interface ResourceCategoryService {
    /**
     * 查询所有
     * @return
     */
    List<ResourceCategory> findAllResourceCategory();
}
