package top.genylife.weather.m.forecast;

import top.genylife.weather.m.Skycon;

/**
 * Created by wanqi on 2016/12/28.
 *
 * @since 1.0.0
 */

public class SkyconValue {
    private Skycon value;
    private String date;

    public Skycon getValue() {
        return value;
    }

    public void setValue(Skycon value) {
        this.value = value;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }
}
