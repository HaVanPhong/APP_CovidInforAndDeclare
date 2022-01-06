package com.example.searchncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class Sympton {

    @SerializedName("fever")
    @Expose
    private boolean fever;
    @SerializedName("cough")
    @Expose
    private boolean cough;
    @SerializedName("soreThroat")
    @Expose
    private boolean soreThroat;
    @SerializedName("loseOfTaste")
    @Expose
    private boolean loseOfTaste;
    @SerializedName("tired")
    @Expose
    private boolean tired;
    @SerializedName("stuffy")
    @Expose
    private boolean stuffy;

    public Sympton() {
    }

    public Sympton(boolean fever, boolean cough, boolean soreThroat, boolean loseOfTaste, boolean tired, boolean stuffy) {
        this.fever = fever;
        this.cough = cough;
        this.soreThroat = soreThroat;
        this.loseOfTaste = loseOfTaste;
        this.tired = tired;
        this.stuffy = stuffy;
    }

    public boolean isFever() {
        return fever;
    }

    public void setFever(boolean fever) {
        this.fever = fever;
    }

    public boolean isCough() {
        return cough;
    }

    public void setCough(boolean cough) {
        this.cough = cough;
    }

    public boolean isSoreThroat() {
        return soreThroat;
    }

    public void setSoreThroat(boolean soreThroat) {
        this.soreThroat = soreThroat;
    }

    public boolean isLoseOfTaste() {
        return loseOfTaste;
    }

    public void setLoseOfTaste(boolean loseOfTaste) {
        this.loseOfTaste = loseOfTaste;
    }

    public boolean isTired() {
        return tired;
    }

    public void setTired(boolean tired) {
        this.tired = tired;
    }

    public boolean isStuffy() {
        return stuffy;
    }

    public void setStuffy(boolean stuffy) {
        this.stuffy = stuffy;
    }
}
