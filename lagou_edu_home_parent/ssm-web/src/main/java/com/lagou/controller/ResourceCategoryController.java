package com.lagou.controller;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory(){
        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();
        return new ResponseResult(true , 200 , "响应成功" , list);
    }


    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){
        if (resourceCategory.getId() == null){
            //新增
            resourceCategoryService.saveResourceCategory(resourceCategory);
            return new ResponseResult(true , 200 , "新增成功" , null);
        }else {
            //修改
            resourceCategoryService.updateResourceCategory(resourceCategory);
            return new ResponseResult(true , 200 , "修改成功" , null);
        }
    }

    /**
     * 删除
     * @return
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){
        resourceCategoryService.deleteResourceCategory(id);
        return new ResponseResult(true , 200 , "响应成功" , null);
    }
}
