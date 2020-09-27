package ru.lightvsi.weatherservice.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class OpenweatherResponse {
    @JsonProperty("weather")
    private List<WeatherInfo> list;
    @JsonProperty("main")
    private Temperature temperature;

    public String getTemp() {
        return this.temperature.getTemp().asText();
    }

    public String getDesc(){
        return this.list.get(0).getMain().asText();
    }

    public void setTemperature(Temperature temperature) {
        this.temperature = temperature;
    }

    public List<WeatherInfo> getList() {
        return list;
    }

    public void setList(List<WeatherInfo> list) {
        this.list = list;
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherInfo {
    private JsonNode main;

    public JsonNode getMain() {
        return main;
    }

    public void setMain(JsonNode main) {
        this.main = main;
    }

}

@JsonIgnoreProperties(ignoreUnknown = true)
class Temperature {
    private JsonNode temp;

    public JsonNode getTemp() {
        return temp;
    }

    public void setTemp(JsonNode temp) {
        this.temp = temp;
    }

}