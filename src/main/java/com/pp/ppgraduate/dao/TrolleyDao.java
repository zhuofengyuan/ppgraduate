package com.pp.ppgraduate.dao;

import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.TrolleyModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TrolleyDao {
    public boolean insertTrolley (TrolleyModel trolleyModel);

    public boolean deleteTrolley (OrderModel orderModel);

    public boolean updateTrolley (TrolleyModel trolleyModel);

    public List<TrolleyModel> selectAllTrolley (TrolleyModel trolleyModel);

    public TrolleyModel selectTrolley (TrolleyModel trolleyModel);

    public TrolleyModel selectTrolleyByOAG (TrolleyModel trolleyModel);
}
