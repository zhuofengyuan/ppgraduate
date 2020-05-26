package com.pp.ppgraduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("g_goods")
public class GoodsModel {
    @TableId(type = IdType.AUTO)
    private  int goodsId;
    private String goodsName;
    private double goodsPrice;
    private String goodsPhoto1;
    private String goodsPhoto2;
    private String goodsPhoto3;
    private String goodsBrand;
    private int sortId;
    private String sortName;
    private int sortItemId;
    private String sortItemName;
    private int goodsWeight;
    private String description;
    private int goodsStock;
    private int goodsSell;
    @TableField(exist = false)
    private String brandName;
}
