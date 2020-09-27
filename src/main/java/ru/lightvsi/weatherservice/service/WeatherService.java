package ru.lightvsi.weatherservice.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import ru.lightvsi.weatherservice.model.StatusModel;
import ru.lightvsi.weatherservice.model.WeatherModel;
import ru.lightvsi.weatherservice.service.exception.CityNotFound;
import ru.lightvsi.weatherservice.service.exception.ConnectionRefused;
import ru.lightvsi.weatherservice.service.exception.InternalException;

import java.util.ArrayList;
import java.util.List;

@Service
public class WeatherService {
    private final List<WeatherInterface> services;

    @Autowired
    public WeatherService(List<WeatherInterface> services) {
        this.services = services;
    }

    public List<WeatherModel> weather(String city) throws CityNotFound, ConnectionRefused, InternalException {
        List<WeatherModel> result = new ArrayList<>();
        boolean cityNotFound = false;
        boolean connectionRefused = false;
        for (WeatherInterface service : services) {
            try {
                result.add(service.weather(city));
            } catch (CityNotFound e) {
                cityNotFound = true;
            } catch (ConnectionRefused e) {
                connectionRefused = true;
            } catch (InternalException e) {
                throw new InternalException();
            }
        }
        if (result.isEmpty() && cityNotFound)
            throw new CityNotFound();
        if (result.isEmpty() && connectionRefused)
            throw new ConnectionRefused();
        if (result.isEmpty())
            throw new InternalException();
        return result;
    }
    public List<StatusModel> status() throws InternalException{
        List<StatusModel> result = new ArrayList<>();
        for (WeatherInterface service : services) {
            try {
                result.add(service.healthCheck());
            } catch (InternalException e) {
                throw new InternalException();
            }
        }
        return result;
    }
}
