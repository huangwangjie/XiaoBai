package com.example.administrator.weather.pojo;

/**
 * Created by Jason on 2015-11-7.
 */
public class Weather {
    private String cityName;
    private String lastUpdate;
    private String day;
    private String high;
    private String low;
    private String cop;
    private String wind;
    private String text;

    public Weather() {
    }

    public Weather(String cityName, String lastUpdate, String day, String high, String low, String cop, String wind,String text) {
        this.cityName = cityName;
        this.lastUpdate = lastUpdate;
        this.day = day;
        this.high = high;
        this.low = low;
        this.cop = cop;
        this.wind = wind;
        this.text=text;
    }

    public String getCityName() {
        return cityName;
    }

    public void setCityName(String cityName) {
        this.cityName = cityName;
    }

    public String getLastUpdate() {
        return lastUpdate;
    }

    public void setLastUpdate(String lastUpdate) {
        this.lastUpdate = lastUpdate;
    }

    public String getDay() {
        return day;
    }

    public void setDay(String day) {
        this.day = day;
    }

    public String getHigh() {
        return high;
    }

    public void setHigh(String high) {
        this.high = high;
    }

    public String getWind() {
        return wind;
    }

    public void setWind(String wind) {
        this.wind = wind;
    }

    public String getCop() {
        return cop;
    }

    public void setCop(String cop) {
        this.cop = cop;
    }

    public String getLow() {
        return low;
    }

    public void setLow(String low) {
        this.low = low;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}
