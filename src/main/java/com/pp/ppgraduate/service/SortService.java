package com.pp.ppgraduate.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.entity.SortModel;

import java.util.List;

public interface SortService extends IService<SortModel> {
    public List<GoodsModel> selectSortItemGoods();

    public List<GoodsModel> selectSortItem();

    public List<GoodsModel> selectSort();

    //-------------- 新增的 -----------------
    List<SortItemModel> selectSortItemBySortId(String parent);
}
