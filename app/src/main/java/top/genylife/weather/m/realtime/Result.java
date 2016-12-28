package top.genylife.weather.m.realtime;

import top.genylife.weather.m.Skycon;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
public class Result {

    private String status;
    private double temperature;
    private Skycon skycon;
    private double cloudrate;
    private int aqi;
    private double humidity;
    private int pm25;
    private Precipitation precipitation;
    private Wind wind;


    public String getTemperatureString() {
        return (int) temperature + "°";
    }

    public Skycon getSkycon() {
        return skycon;
    }

    public void setSkycon(Skycon skycon) {
        this.skycon = skycon;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getTemperature() {
        return temperature;
    }

    public void setTemperature(double temperature) {
        this.temperature = temperature;
    }

    public double getCloudrate() {
        return cloudrate;
    }

    public void setCloudrate(double cloudrate) {
        this.cloudrate = cloudrate;
    }

    public int getAqi() {
        return aqi;
    }

    public void setAqi(int aqi) {
        this.aqi = aqi;
    }

    public double getHumidity() {
        return humidity;
    }

    public void setHumidity(double humidity) {
        this.humidity = humidity;
    }

    public int getPm25() {
        return pm25;
    }

    public void setPm25(int pm25) {
        this.pm25 = pm25;
    }

    public Precipitation getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(Precipitation precipitation) {
        this.precipitation = precipitation;
    }

    public Wind getWind() {
        return wind;
    }

    public void setWind(Wind wind) {
        this.wind = wind;
    }

}
