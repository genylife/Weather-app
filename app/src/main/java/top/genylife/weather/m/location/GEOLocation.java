package top.genylife.weather.m.location;

/**
 * Created by wanqi on 2017/1/4.
 *
 * @since 1.0.0
 */

public class GEOLocation {

    private int status;
    private LocationResult result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public LocationResult getResult() {
        return result;
    }

    public void setResult(LocationResult result) {
        this.result = result;
    }
}
