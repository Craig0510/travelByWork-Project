package com.example.demo.model;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "helpercv")
public class HelperCv implements Serializable {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Integer helpercvid;
	@ManyToOne
	@JoinColumn(name = "helpermemberid")
	private HelperMember helpermemberid;
	private String username;
	@Column(columnDefinition = "varchar(512)")
	private String helperphoto;
	@Column(columnDefinition = "varchar(512)")
	private String helpercvlink;
	private String account;

	public HelperCv() {
	}

	public String getAccount() {
		return account;
	}

	public void setAccount(String account) {
		this.account = account;
	}

	public HelperCv(Integer helpercvid, HelperMember helpermemberid, String username, String helperphoto, String helpercvlink, String account) {
		this.helpercvid = helpercvid;
		this.helpermemberid = helpermemberid;
		this.username = username;
		this.helperphoto = helperphoto;
		this.helpercvlink = helpercvlink;
		this.account = account;
	}

	public Integer getHelpercvid() {
		return helpercvid;
	}

	public void setHelpercvid(Integer helpercvid) {
		this.helpercvid = helpercvid;
	}

	public String getHelpercvlink() {
		return helpercvlink;
	}

	public void setHelpercvlink(String helpercvlink) {
		this.helpercvlink = helpercvlink;
	}

	public HelperMember getHelpermemberid() {
		return helpermemberid;
	}

	public void setHelpermemberid(HelperMember helpermemberid) {
		this.helpermemberid = helpermemberid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getHelperphoto() {
		return helperphoto;
	}

	public void setHelperphoto(String helperphoto) {
		this.helperphoto = helperphoto;
	}

}
