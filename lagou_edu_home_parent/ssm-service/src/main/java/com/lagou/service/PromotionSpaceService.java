package com.lagou.service;

import com.lagou.domain.PromotionSpace;

import java.util.List;

/**
 * 广告位service接口
 */
public interface PromotionSpaceService {

    /*
        获取所有广告位
    */
    public List<PromotionSpace> findAllPromotionSpace();

    /*
        添加广告位
    */
    public void savePromotionSpace(PromotionSpace promotionSpace);

    /*
        回显广告位
     */
    public PromotionSpace findPromotionSpaceById(Integer id);

    /*
        修改广告位
     */
    public void updatePromotionSpace(PromotionSpace promotionSpace);
}
