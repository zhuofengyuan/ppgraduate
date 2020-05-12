package com.pp.ppgraduate.dao;

import com.pp.ppgraduate.entity.RecommendModel;
import org.springframework.stereotype.Repository;

@Repository
public interface RecommendDao {

    public boolean insertRecommend(RecommendModel recommendModel);

    public boolean deleteRecommend(RecommendModel recommendModel);

    public RecommendModel isRecommend(RecommendModel recommendModel);
}
