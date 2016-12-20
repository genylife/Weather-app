package top.genylife.weather.m;

import com.google.auto.value.AutoValue;

import java.util.List;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@AutoValue
public abstract class RealTimeWeather {

    abstract String status();

    abstract String lang();

    abstract long serverTime();

    abstract int tzshift();

    abstract List<Double> location();

    abstract String unit();

    abstract Result result();
}
