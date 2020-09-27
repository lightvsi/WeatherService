package ru.lightvsi.weatherservice.model.external;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.JsonNode;

public class WeatherapiResponse {
    @JsonProperty("current")
    private Current current;

    public String getTemp() {
        return this.current.getTemp_c().asText();
    }

    public String getDesc(){
        return this.current.getCondition().getText().asText();
    }

    public Current getCurrent() {
        return current;
    }

    public void setCurrent(Current current) {
        this.current = current;
    }

}

class Current{
    private JsonNode temp_c;
    @JsonProperty("condition")
    private Condition condition;

    public JsonNode getTemp_c() {
        return temp_c;
    }

    public void setTemp_c(JsonNode temp_c) {
        this.temp_c = temp_c;
    }

    public Condition getCondition() {
        return condition;
    }

    public void setCondition(Condition condition) {
        this.condition = condition;
    }


}

class Condition{
    private JsonNode text;

    public JsonNode getText() {
        return text;
    }

    public void setText(JsonNode text) {
        this.text = text;
    }

    @Override
    public String toString() {
        return text.asText();
    }
}