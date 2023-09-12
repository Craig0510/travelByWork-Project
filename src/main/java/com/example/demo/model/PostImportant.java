package com.example.demo.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "postimportant")
public class PostImportant {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer postimportantid;
    private String postwhat;
    private String words;
    private String username;
    private String postdate;
    private Integer adminmemberid; 

    public PostImportant() {
        super();
    }
    public PostImportant(Integer postimportantid, String postwhat, String words, String username, String postdate,
			Integer adminmemberid) {
		super();
		this.postimportantid = postimportantid;
		this.postwhat = postwhat;
		this.words = words;
		this.username = username;
		this.postdate = postdate;
		this.adminmemberid = adminmemberid;
	}

	public String getPostwhat() {
		return postwhat;
	}
	public void setPostwhat(String postwhat) {
		this.postwhat = postwhat;
	}
	public Integer getPostimportantid() {
        return postimportantid;
    }

    public void setPostimportantid(Integer postimportantid) {
        this.postimportantid = postimportantid;
    }

    public String getWords() {
        return words;
    }

    public void setWords(String words) {
        this.words = words;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPostdate() {
        return postdate;
    }

    public void setPostdate(String postdate) {
        this.postdate = postdate;
    }

    public Integer getAdminmemberid() {
        return adminmemberid;
    }

    public void setAdminmemberid(Integer adminmemberid) {
        this.adminmemberid = adminmemberid;
    }
}

