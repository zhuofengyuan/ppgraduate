package com.fengtoos.ppgraduate.ctrl;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.service.GoodsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 *  商品控制器
 * </p>
 *
 * @author fengtoos
 * @since 2020-04-07
 */
@RestController
@RequestMapping("/admin/goods")
public class AdminGoodsController {

    @Autowired
    GoodsService goodsService;

    @GetMapping("/{id}")
    public RestResponseBo findOne(@PathVariable String id){
        return RestResponseBo.ok(this.goodsService.getById(id));
    }

    @GetMapping("/list")
    public RestResponseBo list(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                               @RequestParam(name = "limit", defaultValue = "10") Integer pageSize,
                               GoodsModel model){
        Page<GoodsModel> page = new Page<>(pageNumber, pageSize);
        Map<String, Object> params = new HashMap<>();
        if(model.getSortId() != 0){
            params.put("sort_id", model.getSortId());
        }
        if(model.getSortItemId() != 0){
            params.put("sort_item_id", model.getSortItemId());
        }
        return RestResponseBo.ok(this.goodsService.page(page, new QueryWrapper<GoodsModel>().allEq(params)), 0);
    }

    @PostMapping("/add")
    public RestResponseBo add(@RequestBody GoodsModel entity){
        return RestResponseBo.normal(this.goodsService.saveOrUpdate(entity));
    }

    @DeleteMapping("/{id}")
    public RestResponseBo delete(@PathVariable String id){
        return RestResponseBo.normal(this.goodsService.removeById(id));
    }
}
