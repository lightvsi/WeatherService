package ru.lightvsi.weatherservice.service;

import org.apache.http.client.utils.URIBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.HttpServerErrorException;
import org.springframework.web.client.ResourceAccessException;
import org.springframework.web.client.RestTemplate;
import ru.lightvsi.weatherservice.model.StatusModel;
import ru.lightvsi.weatherservice.model.WeatherModel;
import ru.lightvsi.weatherservice.model.external.WeatherapiResponse;
import ru.lightvsi.weatherservice.service.exception.CityNotFound;
import ru.lightvsi.weatherservice.service.exception.ConnectionRefused;
import ru.lightvsi.weatherservice.service.exception.InternalException;

@Service
public class WeatherapiService implements WeatherInterface {
    @Autowired
    private RestTemplate restTemplate;
    @Value("${WEATHERAPI_ADDRESS}")
    private String address;
    @Value("${WEATHERAPI_KEY}")
    private String key;
    private final String NAME = "weatherapi";

    public WeatherModel weather(String city) throws CityNotFound, ConnectionRefused, InternalException {
        try {
            URIBuilder builder = new URIBuilder(address);
            builder.setParameter("q", city).setParameter("key", key);
            ResponseEntity<WeatherapiResponse> response = this.restTemplate.getForEntity(builder.build(), WeatherapiResponse.class);
            return new WeatherModel(NAME, response.getBody().getTemp(), response.getBody().getDesc());
        } catch (ResourceAccessException | HttpServerErrorException e) {
            throw new ConnectionRefused();
        } catch (HttpClientErrorException e) {
            throw new CityNotFound();
        } catch (Exception e) {
            throw new InternalException();
        }
    }

    @Override
    public StatusModel healthCheck() throws InternalException {
        try {
            URIBuilder builder = new URIBuilder(address);
            builder.setParameter("q", "london").setParameter("key", key);
            ResponseEntity<WeatherapiResponse> response = this.restTemplate.getForEntity(builder.build(), WeatherapiResponse.class);
        } catch (ResourceAccessException | HttpServerErrorException e) {
            return new StatusModel(NAME, false);
        } catch (Exception e) {
            throw new InternalException();
        }
        return new StatusModel(NAME, true);
    }
}
