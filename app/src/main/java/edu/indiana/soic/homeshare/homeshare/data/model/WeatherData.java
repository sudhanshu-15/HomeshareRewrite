package edu.indiana.soic.homeshare.homeshare.data.model;

import com.google.gson.annotations.SerializedName;

import java.util.List;

public class WeatherData {

    @SerializedName("weather")
    private List<Weather> weather;
    private Main main;
    private String name;

    public List<Weather> getWeather() {
        return weather;
    }

    public Main getMain() {
        return main;
    }

    public String getName() {
        return name;
    }
}
