package top.genylife.weather.m.forecast;

/**
 * Created by wanqi on 2016/12/20.
 *
 * @since 1.0.0
 */

public class Range {

    private String date;
    private double max;
    private double avg;
    private double min;

    public String getValue() {
        String max = "" + ((Double) getMax()).intValue();
        String min = "" + ((Double) getMin()).intValue();
        return min + "/" + max;
    }

    public String getTemperatureValue() {
        return (int) min + "°" + "/" + (int) max + "°";
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public double getMax() {
        return max;
    }

    public void setMax(double max) {
        this.max = max;
    }

    public double getAvg() {
        return avg;
    }

    public void setAvg(double avg) {
        this.avg = avg;
    }

    public double getMin() {
        return min;
    }

    public void setMin(double min) {
        this.min = min;
    }
}
