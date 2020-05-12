package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.RecommendModel;
import com.pp.ppgraduate.service.GoodsService;
import com.pp.ppgraduate.service.RecommendService;
import com.pp.ppgraduate.service.SortService;
import com.pp.ppgraduate.utils.EmptyUtil;
import com.pp.ppgraduate.utils.MyException;
import com.pp.ppgraduate.utils.Result;
import com.pp.ppgraduate.utils.ResultEnum;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/recommend")
@CrossOrigin
public class RecommendController {
    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    GoodsService goodsService;

    @Autowired
    RecommendService recommendService;

    /*增加商品权重*/
    @PostMapping("/addWeight")
    @ResponseBody
    public Result updateGoodsWeight(@RequestBody RecommendModel recommendModel){
        if(EmptyUtil.isEmpty(recommendModel)){
            return Result.error("推荐信息为空");
        }
        if(EmptyUtil.isEmpty(recommendModel.getOpenId())||EmptyUtil.isEmpty(recommendModel.getGoodsId())){
            return Result.error("商品id或者用户id为空");
        }
        try{
            boolean flag = recommendService.insertRecommend(recommendModel);
            if(flag == true){
                GoodsModel item = new GoodsModel();
                item.setGoodsId(recommendModel.getGoodsId());
                GoodsModel goodsModel = goodsService.selectGoods(item);
                goodsModel.setGoodsWeight(goodsModel.getGoodsWeight() + 1);
                return Result.success(goodsService.updateGoodsWeight(goodsModel));
            }
            else {
                return Result.error("推荐信息添加失败");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "增加商品权重Exception");
        }
    }

    @ResponseBody
    @PostMapping("/isRecommend")
    public Result isRecommend(@RequestBody RecommendModel recommendModel){
        if (EmptyUtil.isEmpty(recommendModel)){
            return Result.error("商品信息为空");
        }
        if (EmptyUtil.isEmpty(recommendModel.getOpenId())||EmptyUtil.isEmpty(recommendModel.getGoodsId())){
            return Result.error("待查询用户id或商品id为空");
        }
        try{
            RecommendModel item = recommendService.isRecommend(recommendModel);
            if (EmptyUtil.isEmpty(item)){
                return Result.error("该商品未被推荐");
            }
            else {
                return Result.success("该商品已被推荐");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "查询推荐信息Exception");
        }
    }

    @ResponseBody
    @PostMapping("/deleteRecommend")
    public Result deleteRecommend(@RequestBody RecommendModel recommendModel){
        if (EmptyUtil.isEmpty(recommendModel)){
            return Result.error("推荐list为空");
        }
        if (EmptyUtil.isEmpty(recommendModel.getGoodsId())||EmptyUtil.isEmpty(recommendModel.getOpenId())){
            return Result.error("用户或商品id为空");
        }
        try{
            boolean flag = recommendService.deleteRecommend(recommendModel);
            if(flag == true){
                GoodsModel item = new GoodsModel();
                item.setGoodsId(recommendModel.getGoodsId());
                GoodsModel goodsModel = goodsService.selectGoods(item);
                goodsModel.setGoodsWeight(goodsModel.getGoodsWeight() - 1);
                return Result.success(goodsService.updateGoodsWeight(goodsModel));
            }
            else {
                return Result.error("删除推荐失败");
            }
        } catch (MyException e) {
            return Result.error(e.getCode(), e.getMessage());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            e.printStackTrace();
            return Result.error(ResultEnum.UNKONW_ERROR.getCode(), "删除推荐信息Exception");
        }
    }
}
