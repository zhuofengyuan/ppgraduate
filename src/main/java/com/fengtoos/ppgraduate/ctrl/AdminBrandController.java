package com.fengtoos.ppgraduate.ctrl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.pp.ppgraduate.entity.BrandModel;
import com.pp.ppgraduate.entity.UserModel;
import com.pp.ppgraduate.service.BrandService;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("/admin/brand")
public class AdminBrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/{id}")
    public RestResponseBo findOne(@PathVariable String id){
        return RestResponseBo.ok(this.brandService.getById(id));
    }

    @GetMapping("/list")
    public RestResponseBo list(@RequestParam(name = "page", defaultValue = "0") Integer pageNumber,
                               @RequestParam(name = "limit", defaultValue = "10") Integer pageSize,
                               String name){
        Page<BrandModel> page = new Page<>(pageNumber, pageSize);
        page.setOrders(Arrays.asList(OrderItem.asc("sort")));
        Map<String, Object> params = new HashMap<>();
        QueryWrapper qw = new QueryWrapper<UserModel>();
        if(StringUtils.isNotEmpty(name)){
            qw.like("name", name);
        }
        return RestResponseBo.ok(this.brandService.page(page, qw), 0);
    }

    @PostMapping("/add")
    public RestResponseBo add(@RequestBody BrandModel entity){
        if(entity.getSort() == null){
            entity.setSort(999);
        }
        return RestResponseBo.normal(this.brandService.saveOrUpdate(entity));
    }

    @DeleteMapping("/{id}")
    public RestResponseBo delete(@PathVariable String id){
        return RestResponseBo.normal(this.brandService.removeById(id));
    }
}
