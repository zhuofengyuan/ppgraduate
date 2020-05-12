package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.entity.SortModel;
import com.pp.ppgraduate.service.SortService;
import com.pp.ppgraduate.utils.EmptyUtil;
import com.pp.ppgraduate.utils.MyException;
import com.pp.ppgraduate.utils.Result;
import com.pp.ppgraduate.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/sort")
@CrossOrigin
public class SortController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    SortService sortService;

    /*查询分类*/
    @GetMapping("/selectSort")
    @ResponseBody
    public Result selectSortItem(){
        try{
            List<GoodsModel> sortModel = sortService.selectSort();
            List<GoodsModel> sortItemModel = sortService.selectSortItem();
            List<GoodsModel> goodsModel = sortService.selectSortItemGoods();

            sortModel.addAll(sortItemModel);
            sortModel.addAll(goodsModel);
            if (EmptyUtil.isEmpty(sortModel)){
                return Result.error("无任何分类信息");
            }
            else {
                return Result.success(sortModel);
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询小分类信息Exception");
        }
    }
}
