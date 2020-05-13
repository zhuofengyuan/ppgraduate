package com.pp.ppgraduate.dao;

import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.UserModel;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderDao {
    public boolean insertOrder(OrderModel orderModel);

    public OrderModel selectOrder(OrderModel orderModel);

    public List<OrderModel> selectAllOrder(UserModel userModel);

    public boolean updateOrder(OrderModel orderModel);

    public boolean deleteOrder(OrderModel orderModel);

    //-------------- BI数据接口 -------------
    /**
     * 根据时间分开获取所有订单下单数量
     * @return
     */
    List<OrderModel> getBiByOrderQty();

    /**
     * 根据时间分开获取所有订单下单金额
     * @return
     */
    List<OrderModel> getBiByOrderPrice();

    /**
     *
     * @return
     */
    List<OrderModel> getCountByOrderStatus();
}
