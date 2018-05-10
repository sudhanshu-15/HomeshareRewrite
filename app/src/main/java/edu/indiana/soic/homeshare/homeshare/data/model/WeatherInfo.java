package edu.indiana.soic.homeshare.homeshare.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "weather")
public class WeatherInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String condition;
    private String description;
    private String icon;
    private double temp;
    private double tempMin;
    private double tempMax;
    private long dateCreated;

    public WeatherInfo(String condition, String description, String icon, double temp, double tempMin, double tempMax, long dateCreated) {
        this.condition = condition;
        this.description = description;
        this.icon = icon;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.dateCreated = dateCreated;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCondition() {
        return condition;
    }

    public void setCondition(String condition) {
        this.condition = condition;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getIcon() {
        return icon;
    }

    public void setIcon(String icon) {
        this.icon = icon;
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
