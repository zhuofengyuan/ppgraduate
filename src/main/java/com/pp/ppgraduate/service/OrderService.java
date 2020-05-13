package com.pp.ppgraduate.service;

import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.UserModel;

import java.util.List;
import java.util.Map;

public interface OrderService {
    public boolean insertOrder(OrderModel orderModels);

    public OrderModel selectOrder(OrderModel orderModel);

    public List<OrderModel> selectAllOrder(UserModel userModel);

    public boolean updateOrder(OrderModel orderModel);

    public boolean deleteOrder(OrderModel orderModels);

    /**
     * 根据时间分开获取所有订单下单数量
     * @return
     */
    Map<String, Object> getBiByOrderQty();

    /**
     * 根据时间分开获取所有订单下单金额
     * @return
     */
    Map<String, Object> getBiByOrderPrice();

    /**
     * 获取首页订单数量
     * @return
     */
    Map<String, Integer> getCountByOrderStatus();
}
