package com.pp.ppgraduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("g_user")
public class UserModel {
    @TableId(type = IdType.AUTO)
    private int userId;
    private String userName;
    private String gender;
    private String avatar;
    private String openId;

    @TableField(exist = false)
    private String goodsName;
}
