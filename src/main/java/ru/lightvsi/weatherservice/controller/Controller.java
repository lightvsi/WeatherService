package ru.lightvsi.weatherservice.controller;


import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.lightvsi.weatherservice.model.StatusModel;
import ru.lightvsi.weatherservice.model.WeatherModel;
import ru.lightvsi.weatherservice.service.WeatherInterface;
import ru.lightvsi.weatherservice.service.WeatherService;
import ru.lightvsi.weatherservice.service.exception.CityNotFound;
import ru.lightvsi.weatherservice.service.exception.ConnectionRefused;
import ru.lightvsi.weatherservice.service.exception.InternalException;

import java.util.ArrayList;
import java.util.List;

@RestController
public class Controller {
    private final WeatherService weatherService;
    private final Logger logger;

    @Autowired
    public Controller(WeatherService weatherService, Logger logger) {
        this.weatherService = weatherService;
        this.logger = logger;
    }

    @GetMapping("/weather")
    public ResponseEntity<List<WeatherModel>> weather(@RequestParam(name = "city") String city) {
        logger.info("Weather request: " + city);
        try {
            return new ResponseEntity<>(weatherService.weather(city), HttpStatus.OK);
        } catch (CityNotFound e) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        } catch (ConnectionRefused e) {
            return new ResponseEntity<>(HttpStatus.BAD_GATEWAY);
        } catch (InternalException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/status")
    public ResponseEntity<List<StatusModel>> status() throws Exception {
        logger.info("Status request");
        try {
            return new ResponseEntity<>(weatherService.status(), HttpStatus.OK);
        } catch (InternalException e) {
            return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}