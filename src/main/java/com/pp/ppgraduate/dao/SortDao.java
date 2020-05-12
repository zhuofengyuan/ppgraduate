package com.pp.ppgraduate.dao;

import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.entity.SortModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface SortDao {
    public List<GoodsModel> selectSortItemGoods();

    public List<GoodsModel> selectSortItem();

    public List<GoodsModel> selectSort();
}
