package com.pp.ppgraduate.service.impl;

import com.pp.ppgraduate.dao.OrderDao;
import com.pp.ppgraduate.dao.OrderItemDao;
import com.pp.ppgraduate.dao.TrolleyDao;
import com.pp.ppgraduate.entity.OrderItemModel;
import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.service.OrderService;
import com.pp.ppgraduate.utils.DateUtil;
import com.pp.ppgraduate.utils.EmptyUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class OrderServiceImpl implements OrderService {

    @Autowired
    OrderDao orderDao;

    @Autowired
    OrderItemDao orderItemDao;

    @Autowired
    TrolleyDao trolleyDao;

    public boolean insertOrder(OrderModel orderModel){
        if(orderModel.getTrolleyId()!=0){
            boolean flag = trolleyDao.deleteTrolley(orderModel);
            if(flag == false) {
                return false;
            }
        }

        orderModel.setOrderTime(DateUtil.getNowDate());
        boolean flag1 = orderDao.insertOrder(orderModel);
        if (flag1 == false){
            return false;
        }
        int orderId = orderDao.selectOrder(orderModel).getOrderId();
        for(OrderItemModel orderItemModel:orderModel.getOrderItemModels()){
            orderItemModel.setOrderId(orderId);
        }
        return orderItemDao.insertOrderItem(orderModel);
    }

    public OrderModel selectOrder(OrderModel orderModel){
        return orderDao.selectOrder(orderModel);
    }

    public List<OrderModel> selectAllOrder(UserModel userModel){
        List<OrderModel> orderModels = orderDao.selectAllOrder(userModel);
        for (OrderModel orderModel: orderModels){
            orderModel.setOrderItemModels(orderItemDao.selectOrderItem(orderModel));
        }
        return orderModels;
    }

    public boolean updateOrder(OrderModel orderModel){
        return orderDao.updateOrder(orderModel);
    }

    public boolean deleteOrder(OrderModel orderModel){
        return orderDao.deleteOrder(orderModel);
    }
}
