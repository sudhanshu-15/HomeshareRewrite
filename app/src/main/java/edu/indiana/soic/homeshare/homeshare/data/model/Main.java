package edu.indiana.soic.homeshare.homeshare.data.model;


import com.google.gson.annotations.SerializedName;

public class Main {
    private int id;
    private double temp;
    @SerializedName("temp_min")
    private double tempMin;
    @SerializedName("temp_max")
    private double tempMax;
    private long dateCreated;


    public Main(int id, double temp, double tempMin, double tempMax) {
        this.id = id;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp = temp;
    }

    public double getTempMin() {
        return tempMin;
    }

    public void setTempMin(double tempMin) {
        this.tempMin = tempMin;
    }

    public double getTempMax() {
        return tempMax;
    }

    public void setTempMax(double tempMax) {
        this.tempMax = tempMax;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }
}
