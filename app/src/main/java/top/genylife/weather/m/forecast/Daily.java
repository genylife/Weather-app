package top.genylife.weather.m.forecast;

import java.util.List;

import top.genylife.weather.m.realtime.Wind;

/**
 * Created by wanqi on 2016/12/20.
 *
 * @since 1.0.0
 */
public class Daily {
    private String status;
    private List<Index> coldrisk;
    private List<Range> temperature;
    private List<SkyconValue> skycon;
    private List<Range> cloudrate;
    private List<Range> aqi;
    private List<Range> humidity;
    private List<Astro> astro;
    private List<Index> ultraviolet;
    private List<Range> pm25;
    private List<Index> dressing;
    private List<Index> carwashing;
    private List<Range> precipitation;
    private List<Wind> wind;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public List<Index> getColdrisk() {
        return coldrisk;
    }

    public void setColdrisk(List<Index> coldrisk) {
        this.coldrisk = coldrisk;
    }

    public List<Range> getTemperature() {
        return temperature;
    }

    public void setTemperature(List<Range> temperature) {
        this.temperature = temperature;
    }

    public List<SkyconValue> getSkycon() {
        return skycon;
    }

    public void setSkycon(List<SkyconValue> skycon) {
        this.skycon = skycon;
    }

    public List<Range> getCloudrate() {
        return cloudrate;
    }

    public void setCloudrate(List<Range> cloudrate) {
        this.cloudrate = cloudrate;
    }

    public List<Range> getAqi() {
        return aqi;
    }

    public void setAqi(List<Range> aqi) {
        this.aqi = aqi;
    }

    public List<Range> getHumidity() {
        return humidity;
    }

    public void setHumidity(List<Range> humidity) {
        this.humidity = humidity;
    }

    public List<Astro> getAstro() {
        return astro;
    }

    public void setAstro(List<Astro> astro) {
        this.astro = astro;
    }

    public List<Index> getUltraviolet() {
        return ultraviolet;
    }

    public void setUltraviolet(List<Index> ultraviolet) {
        this.ultraviolet = ultraviolet;
    }

    public List<Range> getPm25() {
        return pm25;
    }

    public void setPm25(List<Range> pm25) {
        this.pm25 = pm25;
    }

    public List<Index> getDressing() {
        return dressing;
    }

    public void setDressing(List<Index> dressing) {
        this.dressing = dressing;
    }

    public List<Index> getCarwashing() {
        return carwashing;
    }

    public void setCarwashing(List<Index> carwashing) {
        this.carwashing = carwashing;
    }

    public List<Range> getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(List<Range> precipitation) {
        this.precipitation = precipitation;
    }

    public List<Wind> getWind() {
        return wind;
    }

    public void setWind(List<Wind> wind) {
        this.wind = wind;
    }
}
