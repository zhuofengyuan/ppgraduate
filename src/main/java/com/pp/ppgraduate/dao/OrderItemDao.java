package com.pp.ppgraduate.dao;

import com.pp.ppgraduate.entity.OrderItemModel;
import com.pp.ppgraduate.entity.OrderModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderItemDao {
    public boolean insertOrderItem(OrderModel orderModels);

    public List<OrderItemModel> selectOrderItem(OrderModel orderModel);
}
