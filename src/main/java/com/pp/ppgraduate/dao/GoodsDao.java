package com.pp.ppgraduate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.SortModel;
import org.springframework.stereotype.Repository;

import java.util.List;

public interface GoodsDao extends BaseMapper<GoodsModel> {
    public GoodsModel selectGoods(GoodsModel goodsModel);

    public List<GoodsModel> selectGoodsByWeight();

    public List<GoodsModel> selectGoodsByLike(GoodsModel goodsModel);

    public boolean updateGoodsWeight(GoodsModel goodsModel);

    public GoodsModel isCollect(GoodsModel goodsModel);
}
