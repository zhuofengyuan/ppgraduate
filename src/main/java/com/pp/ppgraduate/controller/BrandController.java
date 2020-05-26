package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.service.BrandService;
import com.pp.ppgraduate.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequestMapping("/brand")
@RestController
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/selectAllBrands")
    public Result selectAllBrands(@RequestParam(name = "limit", defaultValue = "4") Integer limit){
        List<?> list = this.brandService.list();
        if(limit > list.size()){
            limit = list.size();
        }
        return Result.success(list.subList(0, limit));
    }
}
