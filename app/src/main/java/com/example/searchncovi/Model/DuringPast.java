package com.example.searchncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class DuringPast {
    @SerializedName("Fx")
    @Expose
    private String fx;
    @SerializedName("contact14")
    @Expose
    private boolean contact14;
    @SerializedName("goAbroadOr")
    @Expose
    private boolean goAbroadOr;
    @SerializedName("isolation")
    @Expose
    private boolean isolation;
    @SerializedName("closePeopleHasSympton")
    @Expose
    private boolean closePeopleHasSympton;
    @SerializedName("treatment")
    @Expose
    private boolean treatment;


    public DuringPast() {
    }

    public DuringPast(String fx, boolean contact14, boolean goAbroadOr, boolean isolation, boolean closePeopleHasSympton, boolean treatment) {
        this.fx = fx;
        this.contact14 = contact14;
        this.goAbroadOr = goAbroadOr;
        this.isolation = isolation;
        this.closePeopleHasSympton = closePeopleHasSympton;
        this.treatment = treatment;
    }

    public String getFx() {
        return fx;
    }

    public void setFx(String fx) {
        this.fx = fx;
    }

    public boolean isContact14() {
        return contact14;
    }

    public void setContact14(boolean contact14) {
        this.contact14 = contact14;
    }

    public boolean isGoAbroadOr() {
        return goAbroadOr;
    }

    public void setGoAbroadOr(boolean goAbroadOr) {
        this.goAbroadOr = goAbroadOr;
    }

    public boolean isIsolation() {
        return isolation;
    }

    public void setIsolation(boolean isolation) {
        this.isolation = isolation;
    }

    public boolean isClosePeopleHasSympton() {
        return closePeopleHasSympton;
    }

    public void setClosePeopleHasSympton(boolean closePeopleHasSympton) {
        this.closePeopleHasSympton = closePeopleHasSympton;
    }

    public boolean isTreatment() {
        return treatment;
    }

    public void setTreatment(boolean treatment) {
        this.treatment = treatment;
    }
}
