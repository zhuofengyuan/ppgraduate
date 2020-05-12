package com.pp.ppgraduate.utils;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.List;

/**
 * Created by Zero on 2016/7/19.
 */
public class Page<T> implements Serializable {

    private List<T>    data;       //数据
    private String     packData;   //压缩后得json数据
    private BigInteger total;      //总条数
    private Integer    totalPages; //总页数

    private String     nowDate;    // 数据库当前时间

    //helper function to reduce code
    public static <E> Page<E> success(List<E> data) {
        Page<E> result = new Page<E>();
        result.setData(data);
        return result;
    }

    public List<T> getData() {
        return data;
    }

    public void setData(List<T> data) {
        this.data = data;
    }

    public String getPackData() {
        return packData;
    }

    public void setPackData(String packData) {
        this.packData = packData;
    }

    public BigInteger getTotal() {
        return total;
    }

    public void setTotal(BigInteger total) {
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public String getNowDate() {
        return nowDate;
    }

    public void setNowDate(String nowDate) {
        this.nowDate = nowDate;
    }
}
