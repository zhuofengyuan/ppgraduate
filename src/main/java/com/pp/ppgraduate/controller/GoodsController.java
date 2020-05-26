package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.RecommendModel;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.service.GoodsService;
import com.pp.ppgraduate.service.SortService;
import com.pp.ppgraduate.utils.*;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/goods")
@CrossOrigin
public class GoodsController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    SortService sortService;
    @Autowired
    GoodsService goodsService;

    @GetMapping("/recommendGoods")
    public Result recommendGoods(String userId){
        String rs = "";
        try {
            rs = HttpUtil.httpGet("http://localhost:5000/collaborative/" + userId);
        } catch (Exception e) {
            e.printStackTrace();
        }
        List<GoodsModel> recommendBooks = new ArrayList<>();
        String[] ids = rs.split(",");
        int length = ids.length;
        if(ids.length > 4) {
            length = 4;
        }

        for(int i = 0; i < length; i++) {
            recommendBooks.add(this.goodsService.getById(Integer.parseInt(ids[i])));
        }
        return Result.success(recommendBooks);
    }

    /*查询指定商品*/
    @PostMapping("/selectGoods")
    @ResponseBody
    public Result selectGoods(@RequestBody GoodsModel goodsModel){
        //System.out.println(sortModel.getSortId());
        if(EmptyUtil.isEmpty(goodsModel)){
            return Result.error("小分类信息为空");
        }
        if (EmptyUtil.isEmpty(goodsModel.getSortId())){
            return Result.error("小分类id为空");
        }
        try{
            return Result.success(goodsService.selectGoods(goodsModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询指定商品信息Exception");
        }
    }

    /*查询推荐商品集*/
    @GetMapping("/selectGoodsByWeight")
    @ResponseBody
    public Result selectGoodsByWeight(){
        try{
            return Result.success(goodsService.selectGoodsByWeight());
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询推荐商品信息Exception");
        }
    }

    /*模糊搜索商品*/
    @PostMapping("/selectGoodsByLike")
    @ResponseBody
    public Result selectGoodsByLike(@RequestBody GoodsModel goodsModel){
        if(EmptyUtil.isEmpty(goodsModel)){
            return Result.error("商品信息为空");
        }
        if(EmptyUtil.isEmpty(goodsModel.getGoodsName()) && StringUtils.isEmpty(goodsModel.getBrandName())){
            return Result.error("待查询商品名为空");
        }
        try{
            return Result.success(goodsService.selectGoodsByLike(goodsModel));
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "模糊搜索Exception");
        }
    }

}
