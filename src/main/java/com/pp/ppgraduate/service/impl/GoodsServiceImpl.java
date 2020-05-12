package com.pp.ppgraduate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pp.ppgraduate.dao.GoodsDao;
import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class GoodsServiceImpl extends ServiceImpl<GoodsDao, GoodsModel> implements GoodsService {
    @Autowired
    GoodsDao goodsDao;

    public GoodsModel selectGoods(GoodsModel goodsModel){
        return goodsDao.selectGoods(goodsModel);
    }

    public List<GoodsModel> selectGoodsByWeight(){
        return goodsDao.selectGoodsByWeight();
    }

    public List<GoodsModel> selectGoodsByLike(GoodsModel goodsModel) {
        goodsModel.setGoodsName("%" + goodsModel.getGoodsName() + "%");
        return goodsDao.selectGoodsByLike(goodsModel);
    }

    public boolean updateGoodsWeight(GoodsModel goodsModel){
        return goodsDao.updateGoodsWeight(goodsModel);
    }
}
