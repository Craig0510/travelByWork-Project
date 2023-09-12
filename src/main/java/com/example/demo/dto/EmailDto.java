package com.example.demo.dto;

public class EmailDto {
    private  String toEmail;

    @Override
    public String toString() {
        return "emailDto{" +
                "toEmail='" + toEmail + '\'' +
                '}';
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }
}
