package com.pp.ppgraduate.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.entity.SortModel;

import java.util.List;

public interface SortDao extends BaseMapper<SortModel> {
    public List<GoodsModel> selectSortItemGoods();

    public List<GoodsModel> selectSortItem();

    public List<GoodsModel> selectSort();

    //-------------- 新增的 -----------------
    List<SortItemModel> selectSortItemBySortId(String id);
}
