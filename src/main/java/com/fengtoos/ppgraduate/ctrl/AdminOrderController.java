package com.fengtoos.ppgraduate.ctrl;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
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

import java.util.List;

@RestController
@RequestMapping("/admin/order")
@CrossOrigin
public class AdminOrderController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    OrderService orderService;

    /*查询指定订单*/
    @PostMapping("/selectOrder")
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

        int length = records.size(),
            begin = (pageNumber - 1)*pageSize>length?0:(pageNumber - 1)*pageSize,
            end = pageNumber*pageSize>records.size()?records.size():pageNumber*pageSize;

        page.setTotal(length);
        page.setRecords(records.subList(begin, end));
//        Map<String, Object> params = new HashMap<>();
//        if(model.getSortId() != 0){
//            params.put("sort_id", model.getSortId());
//        }
//        if(model.getSortItemId() != 0){
//            params.put("sort_item_id", model.getSortItemId());
//        }
        return RestResponseBo.ok(page, 0);
    }

    @GetMapping("/bi/qty")
    public RestResponseBo bi2qty(){
        return RestResponseBo.ok(this.orderService.getBiByOrderQty());
    }

    @GetMapping("/bi/price")
    public RestResponseBo bi2price(){
        return RestResponseBo.ok(this.orderService.getBiByOrderPrice());
    }

    @GetMapping("/count")
    public RestResponseBo count(){
        return RestResponseBo.ok(this.orderService.getCountByOrderStatus());
    }
}
