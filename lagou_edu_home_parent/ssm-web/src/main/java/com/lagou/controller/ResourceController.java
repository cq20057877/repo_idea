package com.lagou.controller;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.Resource;
import com.lagou.domain.ResourceVO;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/resource")
public class ResourceController {

    @Autowired
    private ResourceService resourceService;

    /**
     * 资源信息分页&条件查询
     * @param resourceVO
     * @return
     */
    @RequestMapping("/findAllResource")
    public ResponseResult findAllResource(@RequestBody ResourceVO resourceVO){

        PageInfo<Resource> pageInfo = resourceService.findAllResourceByPage(resourceVO);
        return new ResponseResult(true , 200 , "响应成功" , pageInfo);
    }

    /**
     * 添加&更新资源信息
     * @return
     */
    @RequestMapping("/saveOrUpdateResource")
    public ResponseResult saveOrUpdateResource(@RequestBody Resource resource){
        if (resource.getId() == null){
            //添加
            resourceService.saveResource(resource);
            return new ResponseResult(true , 200 , "添加成功" , null);
        }else {
            //修改
            resourceService.updateResrouce(resource);
            return new ResponseResult(true , 200 , "修改成功" , null);
        }
    }

    /**
     * 删除资源信息
     * @return
     */
    @RequestMapping("/deleteResource")
    public ResponseResult deleteResource(Integer id){

        resourceService.deleteResource(id);
        return new ResponseResult(true , 200 , "响应成功" , null);
    }
}
