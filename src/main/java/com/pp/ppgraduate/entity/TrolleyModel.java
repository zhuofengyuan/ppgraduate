package com.pp.ppgraduate.entity;

public class TrolleyModel {
    private int trolleyId;
    private String openId;
    private int goodsId;
    private int consigneeId;
    private int goodsNum;
    private double price;
    private String goodsName;
    private String goodsPhoto;
    private int goodsStock;

    public int getTrolleyId() {
        return trolleyId;
    }

    public void setTrolleyId(int trolleyId) {
        this.trolleyId = trolleyId;
    }

    public String getOpenId() {
        return openId;
    }

    public void setOpenId(String openId) {
        this.openId = openId;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getConsigneeId() {
        return consigneeId;
    }

    public void setConsigneeId(int consigneeId) {
        this.consigneeId = consigneeId;
    }

    public int getGoodsNum() {
        return goodsNum;
    }

    public void setGoodsNum(int goodsNum) {
        this.goodsNum = goodsNum;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public String getGoodsName() {
        return goodsName;
    }

    public void setGoodsName(String goodsName) {
        this.goodsName = goodsName;
    }

    public String getGoodsPhoto() {
        return goodsPhoto;
    }

    public void setGoodsPhoto(String goodsPhoto) {
        this.goodsPhoto = goodsPhoto;
    }

    public int getGoodsStock() {
        return goodsStock;
    }

    public void setGoodsStock(int goodsStock) {
        this.goodsStock = goodsStock;
    }
}
