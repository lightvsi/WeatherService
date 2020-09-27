package ru.lightvsi.weatherservice.model.external;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

import java.util.List;
@JsonIgnoreProperties(ignoreUnknown = true)
public class WeatherbitResponse {
    @JsonProperty("data")
    private List<Data> list;

    public String getTemp() {
        return this.list.get(0).getTemp().asText();
    }

    public String getDesc(){
        return this.list.get(0).getWeatherData().getDescription().asText();
    }

    public List<Data> getList() {
        return list;
    }

    public void setList(List<Data> list) {
        this.list = list;
    }

}
@JsonIgnoreProperties(ignoreUnknown = true)
class Data{
    private JsonNode temp;
    @JsonProperty("weather")
    private WeatherData weatherData;

    public JsonNode getTemp() {
        return temp;
    }

    public void setTemp(JsonNode temp) {
        this.temp = temp;
    }

    public WeatherData getWeatherData() {
        return weatherData;
    }

    public void setWeatherData(WeatherData weatherData) {
        this.weatherData = weatherData;
    }

}
@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherData{
    private JsonNode description;

    public JsonNode getDescription() {
        return description;
    }

    public void setDescription(JsonNode description) {
        this.description = description;
    }

}