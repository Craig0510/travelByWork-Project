package com.example.demo.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="contactus")
public class ContactUs {
    @Id
    @Column(name="contactusid")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer contactUsId;
    private String name;
    private String email;
    @Column(name="contacttype")
    private String contactType;
    @Column(name="contactdate")
    private String contactDate;
    private String message;

    public ContactUs() {
    }

    public ContactUs(Integer contactUsId, String name, String email, String contactType, String contactDate, String message) {
        this.contactUsId = contactUsId;
        this.name = name;
        this.email = email;
        this.contactType = contactType;
        this.contactDate = contactDate;
        this.message = message;
    }

    public Integer getContactUsId() {
        return contactUsId;
    }

    public void setContactUsId(Integer contactUsId) {
        this.contactUsId = contactUsId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContactType() {
        return contactType;
    }

    public void setContactType(String contactType) {
        this.contactType = contactType;
    }

    public String getContactDate() {
        return contactDate;
    }

    public void setContactDate(String contactDate) {
        this.contactDate = contactDate;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "ContactUs{" +
                "contactUsId=" + contactUsId +
                ", name='" + name + '\'' +
                ", email='" + email + '\'' +
                ", contactType='" + contactType + '\'' +
                ", contactDate='" + contactDate + '\'' +
                ", message='" + message + '\'' +
                '}';
    }
}