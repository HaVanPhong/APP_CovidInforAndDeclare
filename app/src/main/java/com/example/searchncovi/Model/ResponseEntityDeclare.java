package com.example.searchncovi.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

public class ResponseEntityDeclare {
    @SerializedName("status")
    @Expose
    private int status;
    @SerializedName("message")
    @Expose
    private String message;
    @SerializedName("data")
    @Expose
    private List<Declarations> data;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Declarations> getData() {
        return data;
    }

    public void setData(List<Declarations> data) {
        this.data = data;
    }

    public ResponseEntityDeclare() {
    }

    public ResponseEntityDeclare(int status, String message, List<Declarations> data) {
        this.status = status;
        this.message = message;
        this.data = data;
    }
}
