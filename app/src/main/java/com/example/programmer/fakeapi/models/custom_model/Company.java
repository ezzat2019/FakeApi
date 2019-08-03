package com.example.programmer.fakeapi.models.custom_model;

public class Company {
    private String name;
    private String catchPhrase;
    private String bs;

    public Company(String name, String catchphrase, String bs) {
        this.name = name;
        this.catchPhrase = catchphrase;
        this.bs = bs;
    }

    public Company() {
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCatchphrase() {
        return catchPhrase;
    }

    public void setCatchphrase(String catchphrase) {
        this.catchPhrase = catchphrase;
    }

    public String getBs() {
        return bs;
    }

    public void setBs(String bs) {
        this.bs = bs;
    }
}
