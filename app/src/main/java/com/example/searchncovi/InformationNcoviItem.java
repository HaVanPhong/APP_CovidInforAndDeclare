package com.example.searchncovi;

import android.os.Parcel;
import android.os.Parcelable;

public class InformationNcoviItem implements Parcelable {
    private String Country_Region;
    private long Confirmed;
    private long Deaths;
    private long Recovered;
    private long treating;
    private long casesToday;



    public InformationNcoviItem() {
    }

    public long getTreating() {
        return treating;
    }

    public void setTreating(long treating) {
        this.treating = treating;
    }

    public long getCasesToday() {
        return casesToday;
    }

    public void setCasesToday(long casesToday) {
        this.casesToday = casesToday;
    }

    public static Creator<InformationNcoviItem> getCREATOR() {
        return CREATOR;
    }

    public InformationNcoviItem(String country_Region, long confirmed, long deaths, long recovered, long treating, long casesToday) {
        Country_Region = country_Region;
        Confirmed = confirmed;
        Deaths = deaths;
        Recovered = recovered;
        this.treating = treating;
        this.casesToday = casesToday;
    }

    protected InformationNcoviItem(Parcel in) {
        Country_Region = in.readString();
        Confirmed = in.readLong();
        Deaths = in.readLong();
        Recovered = in.readLong();
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(Country_Region);
        dest.writeLong(Confirmed);
        dest.writeLong(Deaths);
        dest.writeLong(Recovered);
    }

    @Override
    public int describeContents() {
        return 0;
    }

    public static final Creator<InformationNcoviItem> CREATOR = new Creator<InformationNcoviItem>() {
        @Override
        public InformationNcoviItem createFromParcel(Parcel in) {
            return new InformationNcoviItem(in);
        }

        @Override
        public InformationNcoviItem[] newArray(int size) {
            return new InformationNcoviItem[size];
        }
    };

    public String getCountry_Region() {
        return Country_Region;
    }

    public void setCountry_Region(String country_Region) {
        Country_Region = country_Region;
    }

    public long getConfirmed() {
        return Confirmed;
    }

    public void setConfirmed(long confirmed) {
        Confirmed = confirmed;
    }

    public long getDeaths() {
        return Deaths;
    }

    public void setDeaths(long deaths) {
        Deaths = deaths;
    }

    public long getRecovered() {
        return Recovered;
    }

    public void setRecovered(long recovered) {
        Recovered = recovered;
    }

    @Override
    public String toString() {
        return "InformationNcoviItem{" +
                "Country_Region='" + Country_Region + '\'' +
                ", Confirmed='" + Confirmed + '\'' +
                ", Deaths='" + Deaths + '\'' +
                ", Recovered='" + Recovered + '\'' +
                '}';
    }

}
