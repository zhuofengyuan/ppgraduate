package com.fengtoos.ppgraduate.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.pp.ppgraduate.entity.GoodsModel;
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

import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/admin/order")
@CrossOrigin
public class AdminOrderController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    OrderService orderService;

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

    @GetMapping("/list")
    public RestResponseBo list(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                               @RequestParam(name = "limit", defaultValue = "10") Integer pageSize,
                               UserModel model){
        Page<OrderModel> page = new Page<>(pageNumber, pageSize);
        List<OrderModel> records = this.orderService.selectAllOrder(model);
        page.setTotal(records.size());
        page.setRecords(records.subList((pageNumber - 1)*pageSize, pageNumber*pageSize>records.size()?records.size():pageNumber*pageSize));
//        Map<String, Object> params = new HashMap<>();
//        if(model.getSortId() != 0){
//            params.put("sort_id", model.getSortId());
//        }
//        if(model.getSortItemId() != 0){
//            params.put("sort_item_id", model.getSortItemId());
//        }
        return RestResponseBo.ok(page, 0);
    }
}
