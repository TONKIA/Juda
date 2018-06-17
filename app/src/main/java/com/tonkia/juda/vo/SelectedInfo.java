package com.tonkia.juda.vo;

/**
 * Created by TONKIA on 2018/4/20.
 */

public class SelectedInfo {
    private int img;
    private String name;
    private String desc;
    private float price;
    private int account;

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public SelectedInfo(int img, String name, String desc, float price) {
        this.img = img;
        this.name = name;
        this.desc = desc;
        this.price = price;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public int getImg() {
        return img;
    }

    public void setImg(int img) {
        this.img = img;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
