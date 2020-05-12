package com.pp.ppgraduate.utils;

import java.io.Serializable;
import java.math.BigInteger;

public class PageModel implements Serializable {

    private Integer page; // 页码
    private Integer start; // 起始位置
    private Integer length; // 每页记录数
    private BigInteger total; // 总记录数
    private Integer totalPages; // 总页数
    private Integer returned = 0; // 返回值
    private String startDate;
    private String endDate;
    private String dateStartSuffix = " 00:00:00";	//日期起始时间后缀
    private String dateEndSuffix = " 23:59:59";		//日期终止时间后缀

    private String orderBy; // 查询排序

    public String getDateEndSuffix() {
        return dateEndSuffix;
    }

    public void setDateEndSuffix(String dateEndSuffix) {
        this.dateEndSuffix = dateEndSuffix;
    }

    public String getDateStartSuffix() {
        return dateStartSuffix;
    }

    public void setDateStartSuffix(String dateStartSuffix) {
        this.dateStartSuffix = dateStartSuffix;
    }

    public Integer getStart() {
        return start;
    }

    public void setStart(Integer start) {
        this.start = start;
    }

    public BigInteger getTotal() {
        return total;
    }

    public Integer getPage() {
        return page;
    }

    public void setPage(Integer page) {
        this.page = page;
    }

    public Integer getLength() {
        return length;
    }

    public void setLength(Integer length) {
        this.length = length;
    }

    public void setTotal(BigInteger total) {
        if (!EmptyUtil.isEmpty(total)){
            if(page==null || page==0){
                page=1;
            }
            if (length == null || length.equals(0)) {
                length = 30;
            }
            this.totalPages = total.remainder(BigInteger.valueOf(length)).equals(BigInteger.ZERO)
                    ? total.divide(BigInteger.valueOf(length)).intValue()
                    : total.divide(BigInteger.valueOf(length)).add(BigInteger.ONE).intValue();
            this.start = (page - 1) * length;
        }
        this.total = total;
    }

    public Integer getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        this.totalPages = totalPages;
    }

    public Integer getReturned() {
        return returned;
    }

    public void setReturned(Integer returned) {
        if (returned != null) {
            this.returned = returned;
        } else {
            this.returned = 0;
        }
    }


    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

    public String getOrderBy() {
        return orderBy;
    }

    public void setOrderBy(String orderBy) {
        this.orderBy = orderBy;
    }
}
