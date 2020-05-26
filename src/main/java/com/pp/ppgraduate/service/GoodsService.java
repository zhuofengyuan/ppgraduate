package com.pp.ppgraduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pp.ppgraduate.entity.GoodsModel;

import java.util.List;

public interface GoodsService extends IService<GoodsModel> {
    public GoodsModel selectGoods(GoodsModel goodsModel);

    public List<GoodsModel> selectGoodsByWeight();

    public List<GoodsModel> selectGoodsByLike(GoodsModel goodsModel);

    public boolean updateGoodsWeight(GoodsModel goodsModel);
}
