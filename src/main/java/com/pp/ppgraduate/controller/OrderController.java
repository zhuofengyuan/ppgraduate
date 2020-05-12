package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.service.OrderService;
import com.pp.ppgraduate.utils.EmptyUtil;
import com.pp.ppgraduate.utils.MyException;
import com.pp.ppgraduate.utils.Result;
import com.pp.ppgraduate.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/order")
@CrossOrigin
public class OrderController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    OrderService orderService;

    /*新增订单*/
    @PostMapping("/insertOrder")
    @ResponseBody
    public Result insertOrder(@RequestBody OrderModel orderModel){
        if (EmptyUtil.isEmpty(orderModel)){
            return Result.error("订单信息为空");
        }
        if(EmptyUtil.isEmpty(orderModel.getOrderItemModels())){
            return Result.error("订单列表为空");
        }
        try{
            boolean flag = orderService.insertOrder(orderModel);
            if (flag==true){
                return Result.success("新增成功！");
            }
            else {
                return Result.error("新增失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "新增订单信息Exception");
        }
    }

    /*查询指定订单*/
    @PostMapping("/selectOrder")
    @ResponseBody
    public Result selectOrder(@RequestBody OrderModel orderModel){
        if (EmptyUtil.isEmpty(orderModel)){
            return Result.error("订单信息为空");
        }
        if (EmptyUtil.isEmpty(orderModel.getOrderId())){
            return Result.error("订单id为空");
        }
        try{
            return Result.success(orderService.selectOrder(orderModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询指定订单信息Exception");
        }
    }

    /*查询所有订单*/
    @PostMapping("/selectAllOrder")
    @ResponseBody
    public Result selectAllOrder(@RequestBody UserModel userModel){
        if (EmptyUtil.isEmpty(userModel)){
            return Result.error("订单信息为空");
        }
        if (EmptyUtil.isEmpty(userModel.getUserId())){
            return Result.error("用户id为空");
        }
        try{
            return Result.success(orderService.selectAllOrder(userModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询所有订单信息Exception");
        }
    }

    /*删除订单状态*/
    @PostMapping("/deleteOrder")
    @ResponseBody
    public Result deleteOrder(@RequestBody OrderModel orderModel){
        if (EmptyUtil.isEmpty(orderModel)){
            return Result.error("订单信息为空");
        }
        if (EmptyUtil.isEmpty(orderModel.getOrderId())){
            return Result.error("订单id为空");
        }
        try{
            boolean flag = orderService.deleteOrder(orderModel);
            if (flag==true){
                return Result.success("删除成功！");
            }
            else {
                return Result.error("删除失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "删除订单信息Exception");
        }
    }

    /*修改订单状态*/
    @PostMapping("/updateOrder")
    @ResponseBody
    public Result updateOrder(@RequestBody OrderModel orderModel){
        if (EmptyUtil.isEmpty(orderModel)){
            return Result.error("订单信息为空");
        }
        if(EmptyUtil.isEmpty(orderModel.getOrderStatus())){
            return Result.error("订单状态为空");
        }
        if (EmptyUtil.isEmpty(orderModel.getOrderId())){
            return Result.error("订单id为空");
        }
        try{
            boolean flag = orderService.updateOrder(orderModel);
            if (flag==true){
                return Result.success("修改成功！");
            }
            else {
                return Result.error("修改失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "修改订单信息Exception");
        }
    }

}
