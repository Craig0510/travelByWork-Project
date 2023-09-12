package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "helpermember")
public class HelperMember implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "helpermemberid")
    private Integer helperMemberId;
    private String username;
    private String name;
    private String account;
    private String password;
    private String email;
    private String mobile;
    private String country;
    private String birth;
    private String sexual;
    private Date createtime;
    private String role;
    @JsonIgnore
    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "helperjob",
    joinColumns = @JoinColumn(name = "helpermemberid"),
    inverseJoinColumns = @JoinColumn(name = "storeworklistid"))
    private Set<StoreWorkList> storeWorkLists;

    @JsonIgnore
    @OneToMany(mappedBy = "helpermemberid", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<HelperCv> cvSet;

    @JsonIgnore
    @OneToMany(mappedBy = "helpmemberid", cascade = {CascadeType.PERSIST, CascadeType.REMOVE}, fetch = FetchType.EAGER)
    private Set<HelperShare> shareSet;


    public HelperMember() {
    }

    public HelperMember(Integer helperMemberId, String username, String name, String account, String password, String email, String mobile, String country, String birth, String sexual, Date createtime, String role, Set<StoreWorkList> storeWorkLists, Set<HelperCv> cvSet, Set<HelperShare> shareSet) {
        this.helperMemberId = helperMemberId;
        this.username = username;
        this.name = name;
        this.account = account;
        this.password = password;
        this.email = email;
        this.mobile = mobile;
        this.country = country;
        this.birth = birth;
        this.sexual = sexual;
        this.createtime = createtime;
        this.role = role;
        this.storeWorkLists = storeWorkLists;
        this.cvSet = cvSet;
        this.shareSet = shareSet;
    }

    public Integer getHelperMemberId() {
        return helperMemberId;
    }

    public void setHelperMemberId(Integer helperMemberId) {
        this.helperMemberId = helperMemberId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
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

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }

    public Set<HelperShare> getShareSet() {
        return shareSet;
    }

    public void setShareSet(Set<HelperShare> shareSet) {
        this.shareSet = shareSet;
    }

    public Set<HelperCv> getCvSet() {
        return cvSet;
    }

    public void setCvSet(Set<HelperCv> cvSet) {
        this.cvSet = cvSet;
    }

    public Set<StoreWorkList> getStoreWorkLists() {
        return storeWorkLists;
    }

    public void setStoreWorkLists(Set<StoreWorkList> storeWorkLists) {
        this.storeWorkLists = storeWorkLists;
    }
}
