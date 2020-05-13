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

import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

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

    @Override
    public Map<String, Object> getBiByOrderQty() {
        List<OrderModel> data = this.orderDao.getBiByOrderQty();

        Map<String, Object> rs = new HashMap<>();
        List<String> days = DateUtil.getDays(7);
        List<Integer> values = new ArrayList<>();
        for(String day : days){
            List<OrderModel> s = data.stream().filter(o -> o.getTime().equals(day)).collect(Collectors.toList());
            if(s.size() > 0){
                values.add(s.get(0).getOrderNum());
            } else {
                values.add(0);
            }
        }
        rs.put("days", days);
        rs.put("values", values);
        return rs;
    }

    @Override
    public Map<String, Object> getBiByOrderPrice() {
        List<OrderModel> data = this.orderDao.getBiByOrderPrice();
        Map<String, Object> rs = new HashMap<>();
        List<String> days = DateUtil.getDays(7);
        List<Double> values = new ArrayList<>();
        for(String day : days){
            List<OrderModel> s = data.stream().filter(o -> o.getTime().equals(day)).collect(Collectors.toList());
            if(s.size() > 0){
                values.add(s.get(0).getPrice());
            } else {
                values.add(Double.valueOf("0"));
            }
        }
        rs.put("days", days);
        rs.put("values", values);
        return rs;
    }

    @Override
    public Map<String, Integer> getCountByOrderStatus() {
        List<OrderModel> list = this.orderDao.getCountByOrderStatus();

        Map<String, Integer> values = new HashMap<>();
        String status = null;
        Integer sum = 0;
        for(OrderModel item : list){
            status = item.getOrderStatus();
            if("未支付".equals(status)){
                values.put("unpay", item.getOrderNum());
                sum += item.getOrderNum();
            } else if("已支付".equals(status)){
                values.put("payed", item.getOrderNum());
                sum += item.getOrderNum();
            } else {
                values.put("shipped", item.getOrderNum());
                sum += item.getOrderNum();
            }
        }
        values.put("all", sum);
        return values;
    }
}
