package com.pp.ppgraduate.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("g_sort_item")
public class SortItemModel {

    @TableId(type = IdType.AUTO)
    private int sortItemId;
    private String sortItemName;
    private int sortId;
    private String sortItemPhoto;
}
