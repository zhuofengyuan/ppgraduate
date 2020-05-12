package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.entity.ConsigneeModel;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.service.ConsigneeService;
import com.pp.ppgraduate.utils.EmptyUtil;
import com.pp.ppgraduate.utils.MyException;
import com.pp.ppgraduate.utils.Result;
import com.pp.ppgraduate.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consignee")
@CrossOrigin
public class ConsigneeController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    ConsigneeService consigneeService;

    /*新增收货人信息*/
    @PostMapping("/insertConsignee")
    @ResponseBody
    public Result insertConsignee(@RequestBody ConsigneeModel consigneeModel){
        if(EmptyUtil.isEmpty(consigneeModel)){
            return Result.error("收货人信息为空");
        }
        try{
            boolean flag = consigneeService.insertConsignee(consigneeModel);
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
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "新增收货人信息Exception");
        }
    }

    /*删除收货人信息*/
    @PostMapping("/deleteConsignee")
    @ResponseBody
    public Result deleteConsignee(@RequestBody ConsigneeModel consigneeModel){
        if(EmptyUtil.isEmpty(consigneeModel)){
            return Result.error("收货人信息为空");
        }
        try{
            boolean flag = consigneeService.deleteConsignee(consigneeModel);
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
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "删除收货人信息Exception");
        }
    }

    /*修改收货人信息*/
    @PostMapping("/updateConsignee")
    @ResponseBody
    public Result updateConsignee(@RequestBody ConsigneeModel consigneeModel){
        if(EmptyUtil.isEmpty(consigneeModel)){
            return Result.error("收货人信息为空");
        }
        if(EmptyUtil.isEmpty(consigneeModel.getConsigneeId())){
            return Result.error("收货人id为空");
        }
        if(!EmptyUtil.isEmpty(consigneeModel.getOpenId())){
            return Result.error("用户openId不能修改");
        }
        try{
            boolean flag = consigneeService.updateConsignee(consigneeModel);
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
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "修改收货人信息Exception");
        }
    }

    /*查询收货人信息*/
    @PostMapping("/selectConsignee")
    @ResponseBody
    public Result selectConsignee(@RequestBody ConsigneeModel consigneeModel){
        if(EmptyUtil.isEmpty(consigneeModel)){
            return Result.error("收货人信息为空");
        }
        if(EmptyUtil.isEmpty(consigneeModel.getConsigneeId())){
            return Result.error("收货人id为空");
        }
        try{
            return Result.success(consigneeService.selectConsignee(consigneeModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询收货人信息Exception");
        }
    }

    /*查询所有收货人信息*/
    @PostMapping("/selectAllConsignee")
    @ResponseBody
    public Result selectAllConsignee(@RequestBody UserModel userModel){
        if(EmptyUtil.isEmpty(userModel)){
            return Result.error("收货人信息为空");
        }
        if(EmptyUtil.isEmpty(userModel.getUserId())){
            return Result.error("收货人id为空");
        }
        try{
            return Result.success(consigneeService.selectAllConsignee(userModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询收货人信息Exception");
        }
    }
}
