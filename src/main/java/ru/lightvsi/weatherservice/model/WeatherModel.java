package ru.lightvsi.weatherservice.model;

public class WeatherModel {
    private String service;
    private String temp;
    private String desc;

    public WeatherModel(String service, String temp, String desc) {
        this.service = service;
        this.temp = temp;
        this.desc = desc;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public String getTemp() {
        return temp;
    }

    public void setTemp(String temp) {
        this.temp = temp;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
