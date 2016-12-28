package top.genylife.weather.m.realtime;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
public class Wind {

    private double direction;
    private double speed;
    private String dateTime;

    public double getSpeed() {
        return speed;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getDirection() {
        return direction;
    }

    public void setDirection(double direction) {
        this.direction = direction;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }
}
