package com.pp.ppgraduate.service.impl;

import com.pp.ppgraduate.dao.TrolleyDao;
import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.TrolleyModel;
import com.pp.ppgraduate.service.TrolleyService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TrolleyServiceImpl implements TrolleyService {

    @Autowired
    TrolleyDao trolleyDao;

    public boolean insertTrolley(TrolleyModel trolleyModel){
        return trolleyDao.insertTrolley(trolleyModel);
    }

    public boolean deleteTrolley(OrderModel orderModel){
        return trolleyDao.deleteTrolley(orderModel);
    }

    public boolean updateTrolley(TrolleyModel trolleyModel){
        return trolleyDao.updateTrolley(trolleyModel);
    }

    public List<TrolleyModel> selectAllTrolly (TrolleyModel trolleyModel){
        return trolleyDao.selectAllTrolley(trolleyModel);
    }

    public TrolleyModel selectTrolly(TrolleyModel trolleyModel){
        return trolleyDao.selectTrolley(trolleyModel);
    }

    public TrolleyModel selectTrolleyByOAG (TrolleyModel trolleyModel){
        return trolleyDao.selectTrolleyByOAG(trolleyModel);
    }
}
