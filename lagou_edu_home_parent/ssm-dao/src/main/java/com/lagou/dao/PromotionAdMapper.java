package com.lagou.dao;

import com.lagou.domain.PromotionAd;

import java.util.List;

/**
 * 广告信息接口
 */
public interface PromotionAdMapper {

    /**
     * 分页查询广告信息
     * @return
     */
    public List<PromotionAd> findAllPromotionAdByPage();

    /**
     * 新增广告信息
     * @param promotionAd
     */
    public void savePromotionAd(PromotionAd promotionAd);

    /**
     * 修改广告信息
     * @param promotionAd
     */
    public void updatePromotionAd(PromotionAd promotionAd);

    /**
     * 根据id查询广告信息
     * @param id
     * @return
     */
    public PromotionAd findPromotionAdById(Integer id);

    /**
     * 广告动态上下线
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
