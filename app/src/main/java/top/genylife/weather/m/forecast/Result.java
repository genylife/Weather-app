package top.genylife.weather.m.forecast;

/**
 * Created by wanqi on 2016/12/20.
 *
 * @since 1.0.0
 */
public class Result {

    /**
     * 小时级预报，涵盖从当前开始的未来 48 小时
     */
    private Hourly hourly;
    /**
     * 分钟级预报
     */
    private Minutely minutely;
    /**
     * // 天级别的预报，给出最近 5 天的预报
     */
    private Daily daily;
    private int primary;

    public Hourly getHourly() {
        return hourly;
    }

    public void setHourly(Hourly hourly) {
        this.hourly = hourly;
    }

    public Minutely getMinutely() {
        return minutely;
    }

    public void setMinutely(Minutely minutely) {
        this.minutely = minutely;
    }

    public Daily getDaily() {
        return daily;
    }

    public void setDaily(Daily daily) {
        this.daily = daily;
    }

    public int getPrimary() {
        return primary;
    }

    public void setPrimary(int primary) {
        this.primary = primary;
    }

}
