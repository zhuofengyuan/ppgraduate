package com.fengtoos.ppgraduate.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.service.UserService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/member")
public class AdminUserController {

    @Autowired
    UserService userService;

    @GetMapping("/{id}")
    public RestResponseBo findOne(@PathVariable String id){
        return RestResponseBo.ok(this.userService.getOne(new QueryWrapper<UserModel>().eq("open_id", id)));
    }

    @GetMapping("/list")
    public RestResponseBo list(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                               @RequestParam(name = "limit", defaultValue = "10") Integer pageSize,
                               UserModel model){
        Page<UserModel> page = new Page<>(pageNumber, pageSize);
        page.setOrders(Arrays.asList(OrderItem.desc("user_id")));
        Map<String, Object> params = new HashMap<>();
        QueryWrapper qw = new QueryWrapper<UserModel>();
        if(StringUtils.isNotEmpty(model.getOpenId())){
            qw.eq("open_id", model.getOpenId());
        }
        if(StringUtils.isNotEmpty(model.getUserName())){
            qw.like("user_name", model.getUserName());
        }
        return RestResponseBo.ok(this.userService.page(page, qw), 0);
    }
}
