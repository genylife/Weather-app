package top.genylife.weather.m.forecast;

import java.util.List;

import top.genylife.weather.m.realtime.Wind;

/**
 * Created by wanqi on 2016/12/20.
 *
 * @since 1.0.0
 */
public class Hourly {

    private String status;
    private String description;
    private List<SkyconValue> skycon;
    private List<Value> cloudrate;
    private List<Value> aqi;
    private List<Value> humidity;
    private List<Value> pm25;
    private List<Value> precipitation;
    private List<Wind> wind;
    private List<Value> temperature;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public List<SkyconValue> getSkycon() {
        return skycon;
    }

    public void setSkycon(List<SkyconValue> skycon) {
        this.skycon = skycon;
    }

    public List<Value> getCloudrate() {
        return cloudrate;
    }

    public void setCloudrate(List<Value> cloudrate) {
        this.cloudrate = cloudrate;
    }

    public List<Value> getAqi() {
        return aqi;
    }

    public void setAqi(List<Value> aqi) {
        this.aqi = aqi;
    }

    public List<Value> getHumidity() {
        return humidity;
    }

    public void setHumidity(List<Value> humidity) {
        this.humidity = humidity;
    }

    public List<Value> getPm25() {
        return pm25;
    }

    public void setPm25(List<Value> pm25) {
        this.pm25 = pm25;
    }

    public List<Value> getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(List<Value> precipitation) {
        this.precipitation = precipitation;
    }

    public List<Wind> getWind() {
        return wind;
    }

    public void setWind(List<Wind> wind) {
        this.wind = wind;
    }

    public List<Value> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Value> temperature) {
        this.temperature = temperature;
    }
}
