package com.pp.ppgraduate.service;

import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.UserModel;

import java.util.List;

public interface OrderService {
    public boolean insertOrder(OrderModel orderModels);

    public OrderModel selectOrder(OrderModel orderModel);

    public List<OrderModel> selectAllOrder(UserModel userModel);

    public boolean updateOrder(OrderModel orderModel);

    public boolean deleteOrder(OrderModel orderModels);
}
