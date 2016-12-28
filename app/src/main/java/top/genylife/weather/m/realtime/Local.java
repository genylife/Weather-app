package top.genylife.weather.m.realtime;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
public class Local {

    private String status;
    private int intensity;
    private String datasource;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getIntensity() {
        return intensity;
    }

    public void setIntensity(int intensity) {
        this.intensity = intensity;
    }

    public String getDatasource() {
        return datasource;
    }

    public void setDatasource(String datasource) {
        this.datasource = datasource;
    }


}
