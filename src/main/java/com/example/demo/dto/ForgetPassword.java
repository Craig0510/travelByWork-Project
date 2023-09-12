package com.example.demo.dto;

public class ForgetPassword {
    private String account;
    private String verify;
    private String password;
    private String email;

    public ForgetPassword() {
    }

    public ForgetPassword(String verify, String password,String email,String account) {
        this.account=account;
        this.verify = verify;
        this.password = password;
        this.email=email;
    }

    public String getVerify() {
        return verify;
    }

    public void setVerify(String verify) {
        this.verify = verify;
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

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    @Override
    public String toString() {
        return "ForgetPassword{" +
                "account='" + account + '\'' +
                ", verify='" + verify + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}