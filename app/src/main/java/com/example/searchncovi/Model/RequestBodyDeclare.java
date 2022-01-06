package com.example.searchncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class RequestBodyDeclare {
    @SerializedName("symptons")
    @Expose
    private Sympton sympton;

    @SerializedName("duringpast")
    @Expose
    private DuringPast duringPast;

    @SerializedName("account")
    @Expose
    private String account;

    public RequestBodyDeclare() {
    }

    public RequestBodyDeclare(Sympton sympton, DuringPast duringPast, String account) {
        this.sympton = sympton;
        this.duringPast = duringPast;
        this.account = account;
    }

    public Sympton getSympton() {
        return sympton;
    }

    public void setSympton(Sympton sympton) {
        this.sympton = sympton;
    }

    public DuringPast getDuringPast() {
        return duringPast;
    }

    public void setDuringPast(DuringPast duringPast) {
        this.duringPast = duringPast;
    }

    public String getAccount() {
        return account;
    }

    public void setAccount(String account) {
        this.account = account;
    }
}
