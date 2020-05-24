package com.pp.ppgraduate.controller;

import com.pp.ppgraduate.service.BrandService;
import com.pp.ppgraduate.utils.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/brand")
@RestController
public class BrandController {

    @Autowired
    BrandService brandService;

    @GetMapping("/selectAllBrands")
    public Result selectAllBrands(@RequestParam(name = "limit", defaultValue = "4") Integer limit){
        return Result.success(this.brandService.list().subList(0, limit));
    }
}
