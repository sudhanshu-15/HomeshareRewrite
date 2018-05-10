package edu.indiana.soic.homeshare.homeshare.data.model;

public class Weather {

    private int idWeather;
    private int id;
    private String main;
    private String description;
    private String weatherIcon;
    private long dateCreated;

    public Weather(int idWeather, int id, String main, String description) {
        this.idWeather = idWeather;
        this.id = id;
        this.main = main;
        this.description = description;
        this.weatherIcon = getIcon(id);
    }

    public int getIdWeather() {
        return idWeather;
    }

    public void setIdWeather(int idWeather) {
        this.idWeather = idWeather;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getMain() {
        return main;
    }

    public void setMain(String main) {
        this.main = main;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getWeatherIcon() {
        return weatherIcon;
    }

    public void setWeatherIcon(String weatherIcon) {
        this.weatherIcon = weatherIcon;
    }

    public long getDateCreated() {
        return dateCreated;
    }

    public void setDateCreated(long dateCreated) {
        this.dateCreated = dateCreated;
    }

    private String getIcon(int actualId) {
        int id = actualId/100;
        String icon = "";
        if(actualId == 800){
            icon = "&#xf00d;";
        }else{
            switch (id){
                case 2:
                    icon = "&#xf01e;";
                    break;
                case 3:
                    icon = "&#xf01c;";
                    break;
                case 5:
                    icon = "&#xf008;";
                    break;
                case 6:
                    icon = "&#xf01b;";
                    break;
                case 7:
                    icon = "&#xf014;";
                    break;
                case 8:
                    icon = "&#xf013;";
                    break;
            }
        }
        return icon;
    }
}
