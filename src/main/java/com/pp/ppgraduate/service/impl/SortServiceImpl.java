package com.pp.ppgraduate.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.pp.ppgraduate.dao.SortDao;
import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.entity.SortModel;
import com.pp.ppgraduate.service.SortService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


@Service
@Transactional
public class SortServiceImpl extends ServiceImpl<SortDao, SortModel> implements SortService {
    @Autowired
    SortDao sortDao;

    public List<GoodsModel> selectSortItemGoods(){
        return sortDao.selectSortItemGoods();
    }

    public List<GoodsModel> selectSortItem(){
        return sortDao.selectSortItem();
    }

    public List<GoodsModel> selectSort(){
        return sortDao.selectSort();
    }

    @Override
    public List<SortItemModel> selectSortItemBySortId(String parent) {
        return this.sortDao.selectSortItemBySortId(parent);
    }
}
