package top.genylife.weather.m.forecast;

import java.util.List;

/**
 * Created by wanqi on 2016/12/20.
 *
 * @since 1.0.0
 */
public class Minutely {
    private String status;
    private String description;
    private List<Double> probability;
    private String datasource;
    private List<Integer> precipitation2h;
    private List<Integer> precipitation;

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

    public List<Double> getProbability() {
        return probability;
    }

    public void setProbability(List<Double> probability) {
        this.probability = probability;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }

    public List<Integer> getPrecipitation2h() {
        return precipitation2h;
    }

    public void setPrecipitation2h(List<Integer> precipitation2h) {
        this.precipitation2h = precipitation2h;
    }

    public List<Integer> getPrecipitation() {
        return precipitation;
    }

    public void setPrecipitation(List<Integer> precipitation) {
        this.precipitation = precipitation;
    }
}
