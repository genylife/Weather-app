package top.genylife.weather.m.realtime;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
public  class Nearest {

    private  String status;
    private  double distance;
    private  double intensity;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public double getDistance() {
        return distance;
    }

    public void setDistance(double distance) {
        this.distance = distance;
    }

    public double getIntensity() {
        return intensity;
    }

    public void setIntensity(double intensity) {
        this.intensity = intensity;
    }
}
