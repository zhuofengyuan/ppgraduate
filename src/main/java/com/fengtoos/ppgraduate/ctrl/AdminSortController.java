package com.fengtoos.ppgraduate.ctrl;

import com.fengtoos.ppgraduate.auth.resp.RestResponseBo;
import com.pp.ppgraduate.entity.SortItemModel;
import com.pp.ppgraduate.entity.SortModel;
import com.pp.ppgraduate.service.SortItemService;
import com.pp.ppgraduate.service.SortService;
import org.apache.commons.lang.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/sort")
@CrossOrigin
public class AdminSortController {

    private Logger logger = Logger.getLogger(this.getClass());

    @Autowired
    SortService sortService;
    @Autowired
    SortItemService sortItemService;


    @GetMapping("/tree")
    public RestResponseBo findTree(String parent){
        List<?> list = null;
        if(StringUtils.isEmpty(parent)){
            list = this.sortService.list();
        } else {
            list = this.sortService.selectSortItemBySortId(parent);
        }
        return RestResponseBo.ok(list, 200);
    }

    @PostMapping("/add")
    public RestResponseBo add(@RequestBody SortModel entity){
        return RestResponseBo.normal(this.sortService.saveOrUpdate(entity));
    }

    @PostMapping("/addItem")
    public RestResponseBo add(@RequestBody SortItemModel entity){
        return RestResponseBo.normal(this.sortItemService.saveOrUpdate(entity));
    }

    @DeleteMapping("/{id}")
    public RestResponseBo delete(@PathVariable String id){
        return RestResponseBo.normal(this.sortService.removeById(id));
    }

    @DeleteMapping("/item/{id}")
    public RestResponseBo deleteItem(@PathVariable String id){
        return RestResponseBo.normal(this.sortItemService.removeById(id));
    }
}
