package top.genylife.weather.m.location;

/**
 * Created by wanqi on 2016/12/27.
 *
 * @since 1.0.0
 */

public class GEOAddress {

    private int status;
    private AddressResult result;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public AddressResult getResult() {
        return result;
    }

    public void setResult(AddressResult result) {
        this.result = result;
    }
}
