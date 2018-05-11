package edu.indiana.soic.homeshare.homeshare.data.model;

import android.arch.persistence.room.Entity;
import android.arch.persistence.room.PrimaryKey;

@Entity(tableName = "weather")
public class WeatherInfo {
    @PrimaryKey(autoGenerate = true)
    private int id;
    private String condition;
    private String description;
    private int icon;
    private String city;
    private double temp;
    private double tempMin;
    private double tempMax;
    private long dateCreated;

    public WeatherInfo(String condition, String description, int icon, double temp, double tempMin, double tempMax, long dateCreated, String city) {
        this.condition = condition;
        this.description = description;
        this.icon = icon;
        this.temp = temp;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
        this.dateCreated = dateCreated;
        this.city = city;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
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

    public int getIcon() {
        return icon;
    }

    public void setIcon(int icon) {
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
