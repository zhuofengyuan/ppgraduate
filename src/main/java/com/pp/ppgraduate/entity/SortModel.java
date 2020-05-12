package com.pp.ppgraduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("g_sort")
public class SortModel {

    @TableId(type = IdType.AUTO)
    private int sortId;
    private String sortName;
}
