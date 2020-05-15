package com.imooc.ad.service;

import com.imooc.ad.entity.AdPlan;
import com.imooc.ad.exception.AdException;
import com.imooc.ad.vo.AdPlanGetRequest;
import com.imooc.ad.vo.AdPlanRequest;
import com.imooc.ad.vo.AdPlanResponse;

import java.util.List;

public interface IAdPlanService {
    /**
     * Create Ad Plan*/
    AdPlanResponse createAdPlan(AdPlanRequest request) throws AdException;
    /**
     * Get Ad Plan*/
    List<AdPlan> getAdPlanByIds(AdPlanGetRequest request) throws AdException;
    /**
     * Update Ad Plan*/
    AdPlanResponse updateAdPlan(AdPlanRequest request) throws AdException;
    /**
     * Delete Ad Plan*/
    void deleteAdPlan(AdPlanRequest request) throws AdException;
}
