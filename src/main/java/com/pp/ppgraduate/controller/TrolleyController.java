package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.entity.OrderModel;
import com.pp.ppgraduate.entity.TrolleyModel;
import com.pp.ppgraduate.service.TrolleyService;
import com.pp.ppgraduate.utils.EmptyUtil;
import com.pp.ppgraduate.utils.MyException;
import com.pp.ppgraduate.utils.Result;
import com.pp.ppgraduate.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/trolley")
@CrossOrigin
public class TrolleyController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    TrolleyService trolleyService;

    /*新增购物车信息*/
    @PostMapping("/insertTrolley")
    @ResponseBody
    public Result insertTrolley(@RequestBody TrolleyModel trolleyModel){
        if(EmptyUtil.isEmpty(trolleyModel)){
            return Result.error("购物车内容为空");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getGoodsId())){
            return Result.error("商品id为空");
        }
        if (EmptyUtil.isEmpty(trolleyModel.getOpenId())){
            return Result.error("用户openId为空");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getGoodsNum())){
            return Result.error("商品数量为空");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getPrice())){
            return Result.error("商品价格为空");
        }
        try{
            TrolleyModel item = trolleyService.selectTrolleyByOAG(trolleyModel);
            if (EmptyUtil.isEmpty(item)){
                return Result.success(trolleyService.insertTrolley(trolleyModel));
            }
            else {
                item.setGoodsNum(item.getGoodsNum() + 1);
                return Result.success(trolleyService.updateTrolley(item));
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "新增购物车信息Exception");
        }
    }

    /*删除购物车信息*/
    @PostMapping("/deleteTrolley")
    @ResponseBody
    public Result deleteTrolley(@RequestBody OrderModel orderModel){
        if(EmptyUtil.isEmpty(orderModel)){
            return Result.error("购物车内容为空");
        }
        if(EmptyUtil.isEmpty(orderModel.getTrolleyId())){
            return Result.error("购物车id为空");
        }

        try{
            boolean flag = trolleyService.deleteTrolley(orderModel);
            if(flag) {
                return Result.success("删除成功");
            }
            else {
                return Result.error("删除失败");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "删除购物车信息Exception");
        }
    }

    /*修改购物车信息*/
    @PostMapping("/updateTrolley")
    @ResponseBody
    public Result updateTrolley(@RequestBody TrolleyModel trolleyModel){
        if(EmptyUtil.isEmpty(trolleyModel)){
            return Result.error("购物车内容为空");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getTrolleyId())){
            return Result.error("购物车id为空");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getOpenId())){
            return Result.error("不能修改用户openId");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getGoodsId())){
            return Result.error("不能修改商品id");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getGoodsNum())){
            return Result.error("商品数量为空");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getPrice())){
            return Result.error("价格为空");
        }

        try{
            boolean flag = trolleyService.updateTrolley(trolleyModel);
            if(flag) {
                return Result.success("修改成功");
            }
            else {
                return Result.error("修改失败");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "修改购物车信息Exception");
        }
    }

    /*查询具体购物车信息*/
    @PostMapping("/selectTrolley")
    @ResponseBody
    public Result selectTrolley(@RequestBody TrolleyModel trolleyModel){
        if(EmptyUtil.isEmpty(trolleyModel)){
            return Result.error("购物车内容为空");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getTrolleyId())){
            return Result.error("购物车id为空");
        }

        try{
            return Result.success(trolleyService.selectTrolly(trolleyModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询具体购物车信息Exception");
        }
    }

    /*查询指定用户的购物车信息*/
    @PostMapping("/selectAllTrolley")
    @ResponseBody
    public Result selectAllTrolley(@RequestBody TrolleyModel trolleyModel){
        if(EmptyUtil.isEmpty(trolleyModel)){
            return Result.error("购物车内容为空");
        }
        if(EmptyUtil.isEmpty(trolleyModel.getOpenId())){
            return Result.error("用户openId为空");
        }

        try{
            return Result.success(trolleyService.selectAllTrolly(trolleyModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询指定用户的购物车信息Exception");
        }
    }
}
