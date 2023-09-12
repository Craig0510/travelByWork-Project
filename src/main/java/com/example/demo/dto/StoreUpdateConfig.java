package com.example.demo.dto;

public class StoreUpdateConfig {
    private Integer storememberid;
    private String ownername;
    private String address;
    private String phone;
    private String mobile;

    public StoreUpdateConfig() {
    }

    public StoreUpdateConfig(Integer storememberid, String ownername, String address, String phone, String mobile) {
        this.storememberid = storememberid;
        this.ownername = ownername;
        this.address = address;
        this.phone = phone;
        this.mobile = mobile;
    }

    public String getOwnername() {
        return ownername;
    }

    public void setOwnername(String ownername) {
        this.ownername = ownername;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public Integer getStorememberid() {
        return storememberid;
    }

    public void setStorememberid(Integer storememberid) {
        this.storememberid = storememberid;
    }

    @Override
    public String toString() {
        return "StoreUpdateConfig{" +
                "storememberid=" + storememberid +
                ", ownername='" + ownername + '\'' +
                ", address='" + address + '\'' +
                ", phone='" + phone + '\'' +
                ", mobile='" + mobile + '\'' +
                '}';
    }
}
