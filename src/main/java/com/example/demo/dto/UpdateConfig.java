package com.example.demo.dto;

public class UpdateConfig {
    private Integer helpermemberid;
    private String birth;
    private String sexual;
    private String country;
    private String username;
    private String mobile;

    public UpdateConfig(Integer helpermemberid, String birth, String sexual, String country, String username, String mobile) {
        this.helpermemberid = helpermemberid;
        this.birth = birth;
        this.sexual = sexual;
        this.country = country;
        this.username = username;
        this.mobile = mobile;
    }

    public UpdateConfig() {
    }

    public Integer getHelpermemberid() {
        return helpermemberid;
    }

    public void setHelpermemberid(Integer helpermemberid) {
        this.helpermemberid = helpermemberid;
    }

    public String getBirth() {
        return birth;
    }

    public void setBirth(String birth) {
        this.birth = birth;
    }

    public String getSexual() {
        return sexual;
    }

    public void setSexual(String sexual) {
        this.sexual = sexual;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

}
