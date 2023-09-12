package com.example.demo.dto;

public class ReplyMail {
    private String toEmail;
    private String title;
    private String subject;

    public ReplyMail() {
    }

    public ReplyMail(String toEmail, String title, String subject) {
        this.toEmail = toEmail;
        this.title = title;
        this.subject = subject;
    }

    public String getToEmail() {
        return toEmail;
    }

    public void setToEmail(String toEmail) {
        this.toEmail = toEmail;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSubject() {
        return subject;
    }

    public void setSubject(String subject) {
        this.subject = subject;
    }

    @Override
    public String toString() {
        return "ReplyMail{" +
                "toEmail='" + toEmail + '\'' +
                ", title='" + title + '\'' +
                ", subject='" + subject + '\'' +
                '}';
    }
}
