package com.example.searchncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Declarations {
    @SerializedName("_id")
    @Expose
    private String id;

    @SerializedName("symptons")
    @Expose
    private Sympton sympton;

    @SerializedName("duringpast")
    @Expose
    private DuringPast duringPast;

    @SerializedName("account")
    @Expose
    private Account account;


    @SerializedName("createdAt")
    @Expose
    private String createdAt;

    public Declarations() {
    }

    public Declarations(String id, Sympton sympton, DuringPast duringPast, Account account,   String createdAt) {
        this.id = id;
        this.sympton = sympton;
        this.duringPast = duringPast;
        this.account = account;
        this.createdAt = createdAt;
    }



    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
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

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
    }

    public String getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(String createdAt) {
        this.createdAt = createdAt;
    }
}
