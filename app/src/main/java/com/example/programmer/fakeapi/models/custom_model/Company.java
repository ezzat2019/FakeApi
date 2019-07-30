package com.example.programmer.fakeapi.models.custom_model;

public class Company {
    private String name;
    private String catchphrase;
    private String bs;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchphrase() {
        return catchphrase;
    }

    public void setCatchphrase(String catchphrase) {
        this.catchphrase = catchphrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }

    public Company(String name, String catchphrase, String bs) {
        this.name = name;
        this.catchphrase = catchphrase;
        this.bs = bs;
    }

    public Company() {
    }
}
