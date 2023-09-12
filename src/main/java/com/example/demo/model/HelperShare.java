package com.example.demo.model;

import javax.persistence.*;
import java.util.Objects;

@Entity
@Table(name = "helpershare")
public class HelperShare {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer helpershareid;
    @ManyToOne
    @JoinColumn(name = "helpmemberid")
    private HelperMember helpmemberid;
    private String account;
    private String username;
    private String place;
    private String storename;
    private String worktype;
    private String workdetail;
    private String grade;
    private String content;
    private String updatedate;
    private String startdate;
    private String enddate;

    public HelperShare() {
    }

    public HelperShare(Integer helpershareid, HelperMember helpmemberid, String account, String username, String startdate,
                       String enddate, String place, String storename, String worktype, String workdetail, String grade,
                       String content, String updatedate) {
        super();
        this.helpershareid = helpershareid;
        this.helpmemberid = helpmemberid;
        this.account = account;
        this.username = username;
        this.startdate = startdate;
        this.enddate = enddate;
        this.place = place;
        this.storename = storename;
        this.worktype = worktype;
        this.workdetail = workdetail;
        this.grade = grade;
        this.content = content;
        this.updatedate = updatedate;
    }

    public Integer getHelpershareid() {
        return helpershareid;
    }

    public void setHelpershareid(Integer helpershareid) {
        this.helpershareid = helpershareid;
    }

    public HelperMember getHelpmemberid() {
        return helpmemberid;
    }

    public void setHelpmemberid(HelperMember helpmemberid) {
        this.helpmemberid = helpmemberid;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getStartdate() {
        return startdate;
    }

    public void setStartdate(String startdate) {
        this.startdate = startdate;
    }

    public String getEnddate() {
        return enddate;
    }

    public void setEnddate(String enddate) {
        this.enddate = enddate;
    }

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

    public String getStorename() {
        return storename;
    }

    public void setStorename(String storename) {
        this.storename = storename;
    }

    public String getWorktype() {
        return worktype;
    }

    public void setWorktype(String worktype) {
        this.worktype = worktype;
    }

    public String getWorkdetail() {
        return workdetail;
    }

    public void setWorkdetail(String workdetail) {
        this.workdetail = workdetail;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getUpdatedate() {
        return updatedate;
    }

    public void setUpdatedate(String updatedate) {
        this.updatedate = updatedate;
    }

    @Override
    public String toString() {
        return "helperShare [helpershareid=" + helpershareid + ", helpmemberid=" + helpmemberid + ", account=" + account
                + ", username=" + username + ", startdate=" + startdate + ", enddate=" + enddate + ", place=" + place
                + ", storename=" + storename + ", worktype=" + worktype + ", workdetail=" + workdetail + ", grade="
                + grade + ", content=" + content + ", updatedate=" + updatedate + "]";
    }



}


