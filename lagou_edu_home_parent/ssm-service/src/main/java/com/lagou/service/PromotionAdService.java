package com.lagou.service;

import com.github.pagehelper.PageInfo;
import com.lagou.domain.PromotionAd;
import com.lagou.domain.PromotionAdVO;

/**
 * 广告管理接口
 */
public interface PromotionAdService {

    /**
     *
     * @param promotionAdVO
     * @return
     */
    public PageInfo<PromotionAd> findAllPromotionAdByPage(PromotionAdVO promotionAdVO);

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
     * 广告动态 上下线
     * @param promotionAd
     */
    public void updatePromotionAdStatus(PromotionAd promotionAd);
}
