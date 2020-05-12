package com.pp.ppgraduate.service;

import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.entity.SortModel;

import java.util.List;

public interface SortService {
    public List<GoodsModel> selectSortItemGoods();

    public List<GoodsModel> selectSortItem();

    public List<GoodsModel> selectSort();
}
