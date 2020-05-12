package com.pp.ppgraduate.service.impl;

import com.pp.ppgraduate.dao.RecommendDao;
import com.pp.ppgraduate.entity.RecommendModel;
import com.pp.ppgraduate.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class RecommendServiceImpl implements RecommendService {

    @Autowired
    RecommendDao recommendDao;

    public boolean insertRecommend(RecommendModel recommendModel){
        return recommendDao.insertRecommend(recommendModel);
    }

    public boolean deleteRecommend(RecommendModel recommendModel){
        return recommendDao.deleteRecommend(recommendModel);
    }

    public RecommendModel isRecommend(RecommendModel recommendModel){
        return recommendDao.isRecommend(recommendModel);
    }
}
