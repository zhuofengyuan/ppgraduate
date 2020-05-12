package com.pp.ppgraduate.controller;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.entity.User;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.pp.ppgraduate.entity.CollectModel;
import com.pp.ppgraduate.service.CollectService;
import com.pp.ppgraduate.utils.EmptyUtil;
import com.pp.ppgraduate.utils.MyException;
import com.pp.ppgraduate.utils.Result;
import com.pp.ppgraduate.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/collect")
@CrossOrigin
public class CollectController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    CollectService collectService;

    @ResponseBody
    @PostMapping("/insertCollect")
    public Result insertCollect(@RequestBody CollectModel collectModel){
        if (EmptyUtil.isEmpty(collectModel)){
            return Result.error("收藏list为空");
        }
        if (EmptyUtil.isEmpty(collectModel.getGoodsId())||EmptyUtil.isEmpty(collectModel.getOpenId())){
            return Result.error("用户id或商品id为空");
        }
        try{
            boolean flag = collectService.insertCollect(collectModel);
            if (flag == true){
                return Result.success("新增收藏成功！");
            }
            else {
                return Result.error("新增收藏失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "新增收藏信息Exception");
        }
    }

    @ResponseBody
    @PostMapping("/deleteCollect")
    public Result deleteCollect(@RequestBody CollectModel collectModel){
        if (EmptyUtil.isEmpty(collectModel)){
            return Result.error("收藏list为空");
        }
        if (EmptyUtil.isEmpty(collectModel.getGoodsId())||EmptyUtil.isEmpty(collectModel.getOpenId())){
            return Result.error("用户或商品id为空");
        }
        try{
            boolean flag = collectService.deleteCollect(collectModel);
            if (flag == true){
                return Result.success("删除收藏成功！");
            }
            else {
                return Result.error("删除收藏失败！");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "删除收藏信息Exception");
        }
    }

    @ResponseBody
    @PostMapping("/selectAllCollect")
    public Result selectAllCollect(@RequestBody CollectModel collectModel){
        if (EmptyUtil.isEmpty(collectModel)){
            return Result.error("收藏list为空");
        }
        if (EmptyUtil.isEmpty(collectModel.getOpenId())){
            return Result.error("待查询用户id为空");
        }
        try{
             return Result.success(collectService.selectAllCollect(collectModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询收藏信息Exception");
        }
    }

    @ResponseBody
    @PostMapping("/isCollect")
    public Result isCollect(@RequestBody CollectModel collectModel){
        if (EmptyUtil.isEmpty(collectModel)){
            return Result.error("收藏list为空");
        }
        if (EmptyUtil.isEmpty(collectModel.getOpenId())||EmptyUtil.isEmpty(collectModel.getGoodsId())){
            return Result.error("待查询用户id或商品id为空");
        }
        try{
            CollectModel item = collectService.isCollect(collectModel);
            if (EmptyUtil.isEmpty(item)){
                return Result.error("该商品未被收藏");
            }
            else {
                return Result.success("该商品已被收藏");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询收藏信息Exception");
        }
    }
}
