package com.pp.ppgraduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("g_collect")
public class CollectModel {

    @TableId(type = IdType.AUTO)
    private int collectId;
    private String openId;
    private int goodsId;
    private String goodsName;
    private String goodsPhoto;
}
