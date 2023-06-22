package com.example.passwordmanager.data.model;

public class Password {
    private String domain;
    private String password;

    public Password(String domain, String password) {
        this.domain = domain;
        this.password = password;
    }

    public String getDomain() {
        return domain;
    }

    public void setDomain(String domain) {
        this.domain = domain;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}