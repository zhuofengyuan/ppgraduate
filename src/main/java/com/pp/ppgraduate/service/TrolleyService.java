package com.pp.ppgraduate.service;

import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.TrolleyModel;

import java.util.List;

public interface TrolleyService {
    public boolean insertTrolley(TrolleyModel trolleyModel);

    public boolean deleteTrolley(OrderModel orderModel);

    public boolean updateTrolley(TrolleyModel trolleyModel);

    public List<TrolleyModel> selectAllTrolly (TrolleyModel trolleyModel);

    public TrolleyModel selectTrolly(TrolleyModel trolleyModel);

    public TrolleyModel selectTrolleyByOAG (TrolleyModel trolleyModel);
}
