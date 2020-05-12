package com.pp.ppgraduate.service;

import com.pp.ppgraduate.entity.RecommendModel;

public interface RecommendService {
    public boolean insertRecommend(RecommendModel recommendModel);

    public boolean deleteRecommend(RecommendModel recommendModel);

    public RecommendModel isRecommend(RecommendModel recommendModel);
}
