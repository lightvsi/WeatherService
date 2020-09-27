package ru.lightvsi.weatherservice.model;

public class StatusModel {
    private String service;
    private boolean online;

    public StatusModel(String service, boolean online) {
        this.service = service;
        this.online = online;
    }

    public String getService() {
        return service;
    }

    public void setService(String service) {
        this.service = service;
    }

    public boolean isOnline() {
        return online;
    }

    public void setOnline(boolean online) {
        this.online = online;
    }
}
