package com.pp.ppgraduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("g_brand")
public class BrandModel {
    @TableId(type = IdType.AUTO)
    private Integer id;
    private String name;
    private String image;
    private Integer sort;
}
