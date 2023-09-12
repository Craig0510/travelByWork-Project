package com.example.demo.dto;

public class VerifyCodeText {
    private String verifyCode;

    public VerifyCodeText() {
    }

    public VerifyCodeText(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    public String getVerifyCode() {
        return verifyCode;
    }

    public void setVerifyCode(String verifyCode) {
        this.verifyCode = verifyCode;
    }

    @Override
    public String toString() {
        return "VerifyCodeText{" +
                "verifyCode='" + verifyCode + '\'' +
                '}';
    }
}
