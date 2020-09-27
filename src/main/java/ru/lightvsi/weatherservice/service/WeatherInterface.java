package ru.lightvsi.weatherservice.service;

import ru.lightvsi.weatherservice.model.StatusModel;
import ru.lightvsi.weatherservice.model.WeatherModel;
import ru.lightvsi.weatherservice.service.exception.CityNotFound;
import ru.lightvsi.weatherservice.service.exception.ConnectionRefused;
import ru.lightvsi.weatherservice.service.exception.InternalException;

public interface WeatherInterface {
    public WeatherModel weather(String city) throws CityNotFound, ConnectionRefused, InternalException;
    public StatusModel healthCheck() throws InternalException;
}
