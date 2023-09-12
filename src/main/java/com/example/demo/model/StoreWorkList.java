package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Date;
import java.util.Set;

import javax.persistence.*;

@Entity
@Table(name = "storeworklist")
public class StoreWorkList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "storeworklistid")
    private Integer storeWorkListId;
    private String location;
    @Temporal(TemporalType.TIMESTAMP)
    private Date workupdatetime;
    private String workage;
    private String worksexual;
    private String howtoapply;
    private String others;
    private String changedatebegin;
    private String changedateeend;
    private String aleastdays;
    private String breaktime;
    private String workhours;
    private String atwhattime;
    private String workdetails;
    private String scooters;
    private String meals;
    private String money;
    private String workbonus;
    @ManyToOne
    @JoinColumn(name = "storememberid")
    private StoreMember storememberid;
    @Column(name = "storename")
    private String storeName;
    @Column(name = "ownername")
    private String ownerName;
    private String email;
    private String mobile;
    private String phone;
    private String address;
    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "helperjob",
            joinColumns = @JoinColumn(name = "storeworklistid"),
            inverseJoinColumns = @JoinColumn(name = "helpermemberid"))
    private Set<HelperMember> helperMembers;


    public StoreWorkList() {
        super();
        // TODO Auto-generated constructor stub
    }

    public StoreWorkList(Integer storeWorkListId, String location, Date workupdatetime, String workage, String worksexual, String howtoapply, String others, String changedatebegin, String changedateeend, String aleastdays, String breaktime, String workhours, String atwhattime, String workdetails, String scooters, String meals, String money, String workbonus, StoreMember storememberid, String storeName, String ownerName, String email, String mobile, String phone, String address, Set<HelperMember> helperMembers) {
        this.storeWorkListId = storeWorkListId;
        this.location = location;
        this.workupdatetime = workupdatetime;
        this.workage = workage;
        this.worksexual = worksexual;
        this.howtoapply = howtoapply;
        this.others = others;
        this.changedatebegin = changedatebegin;
        this.changedateeend = changedateeend;
        this.aleastdays = aleastdays;
        this.breaktime = breaktime;
        this.workhours = workhours;
        this.atwhattime = atwhattime;
        this.workdetails = workdetails;
        this.scooters = scooters;
        this.meals = meals;
        this.money = money;
        this.workbonus = workbonus;
        this.storememberid = storememberid;
        this.storeName = storeName;
        this.ownerName = ownerName;
        this.email = email;
        this.mobile = mobile;
        this.phone = phone;
        this.address = address;
        this.helperMembers = helperMembers;
    }

    public StoreMember getStorememberid() {
        return storememberid;
    }

    public void setStorememberid(StoreMember storememberid) {
        this.storememberid = storememberid;
    }

    public String getStoreName() {
        return storeName;
    }

    public void setStoreName(String storeName) {
        this.storeName = storeName;
    }

    public String getOwnerName() {
        return ownerName;
    }

    public void setOwnerName(String ownerName) {
        this.ownerName = ownerName;
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

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Integer getStoreWorkListId() {
        return storeWorkListId;
    }

    public void setStoreWorkListId(Integer storeWorkListId) {
        this.storeWorkListId = storeWorkListId;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public Date getWorkupdatetime() {
        return workupdatetime;
    }

    public void setWorkupdatetime(Date workupdatetime) {
        this.workupdatetime = workupdatetime;
    }

    public String getWorkage() {
        return workage;
    }

    public void setWorkage(String workage) {
        this.workage = workage;
    }

    public String getWorksexual() {
        return worksexual;
    }

    public void setWorksexual(String worksexual) {
        this.worksexual = worksexual;
    }

    public String getHowtoapply() {
        return howtoapply;
    }

    public void setHowtoapply(String howtoapply) {
        this.howtoapply = howtoapply;
    }

    public String getOthers() {
        return others;
    }

    public void setOthers(String others) {
        this.others = others;
    }

    public String getChangedatebegin() {
        return changedatebegin;
    }

    public void setChangedatebegin(String changedatebegin) {
        this.changedatebegin = changedatebegin;
    }

    public String getChangedateeend() {
        return changedateeend;
    }

    public void setChangedateeend(String changedateeend) {
        this.changedateeend = changedateeend;
    }

    public String getAleastdays() {
        return aleastdays;
    }

    public void setAleastdays(String aleastdays) {
        this.aleastdays = aleastdays;
    }

    public String getBreaktime() {
        return breaktime;
    }

    public void setBreaktime(String breaktime) {
        this.breaktime = breaktime;
    }

    public String getWorkhours() {
        return workhours;
    }

    public void setWorkhours(String workhours) {
        this.workhours = workhours;
    }

    public String getAtwhattime() {
        return atwhattime;
    }

    public void setAtwhattime(String atwhattime) {
        this.atwhattime = atwhattime;
    }

    public String getWorkdetails() {
        return workdetails;
    }

    public void setWorkdetails(String workdetails) {
        this.workdetails = workdetails;
    }

    public String getScooters() {
        return scooters;
    }

    public void setScooters(String scooters) {
        this.scooters = scooters;
    }

    public String getMeals() {
        return meals;
    }

    public void setMeals(String meals) {
        this.meals = meals;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getWorkbonus() {
        return workbonus;
    }

    public void setWorkbonus(String workbonus) {
        this.workbonus = workbonus;
    }

    public Set<HelperMember> getHelperMembers() {
        return helperMembers;
    }

    public void setHelperMembers(Set<HelperMember> helperMembers) {
        this.helperMembers = helperMembers;
    }
}