package com.example.demo.dto;

public class GoogleLoginPrincipal {
    private String name;
    private String email;
    private String locale;

    public GoogleLoginPrincipal() {
    }

    public GoogleLoginPrincipal(String name, String email, String locale) {
        this.name = name;
        this.email = email;
        this.locale = locale;
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

    public String getLocale() {
        return locale;
    }

    public void setLocale(String locale) {
        this.locale = locale;
    }
}
