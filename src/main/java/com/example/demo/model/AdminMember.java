package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="adminmember")
public class AdminMember {

	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Integer adminmemberid;
	private String name;
	private String username;
	private String mobile;
	private String account;
	private String password;
	private String createtime;
	
	public AdminMember() {}

	public AdminMember(Integer adminmemberid, String name, String username, String mobile, String account,
					   String password, String createtime) {
		super();
		this.adminmemberid = adminmemberid;
		this.name = name;
		this.username = username;
		this.mobile = mobile;
		this.account = account;
		this.password = password;
		this.createtime = createtime;
	}

	public Integer getAdminmemberid() {
		return adminmemberid;
	}

	public void setAdminmemberid(Integer adminmemberid) {
		this.adminmemberid = adminmemberid;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
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

	public String getCreatetime() {
		return createtime;
	}

	public void setCreatetime(String createtime) {
		this.createtime = createtime;
	}

	@Override
	public String toString() {
		return "adminMember [adminmemberid=" + adminmemberid + ", name=" + name + ", username=" + username + ", mobile="
				+ mobile + ", account=" + account + ", password=" + password + ", createtime=" + createtime + "]";
	}

}