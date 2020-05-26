package com.pp.ppgraduate.controller;
import	java.net.Authenticator;
import java.util.List;

import com.pp.ppgraduate.entity.CollectModel;
import com.pp.ppgraduate.entity.GoodsModel;
import com.pp.ppgraduate.entity.WeChatModel;
import com.pp.ppgraduate.service.CollectService;
import com.pp.ppgraduate.service.GoodsService;
import com.pp.ppgraduate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/test")
@RestController
public class TestController {

    @Autowired
    UserService userService;
    @Autowired
    CollectService collectService;
    @Autowired
    GoodsService goodsService;

    @GetMapping("/addUsers")
    public String addUsers(){
        for(int i = 5; i < 1000; i++){
            WeChatModel m = new WeChatModel();
            m.setOpenId("" + i);
            m.setNickName("testUser" + i);
            m.setAvatarUrl("testIcon" + i);
            this.userService.insertUser(m);
        }
        return "success";
    }

    @GetMapping("/addCollect")
    public String addCollect(){
        for(int i = 0; i < 1000; i++){
            CollectModel m = new CollectModel();
            m.setOpenId(String.valueOf((int)(Math.random()*1000)));
            m.setGoodsId((int)(Math.random()*100)/2);
            this.collectService.insertCollect(m);
        }
        return "success";
    }

    @GetMapping("/addGoods")
    public String addGoods(){
        List<GoodsModel> list = this.goodsService.list();
        for(GoodsModel item : list){
            item.setGoodsId(item.getGoodsId() + 50);
        }
        this.goodsService.saveBatch(list);
        return "success";
    }
}
