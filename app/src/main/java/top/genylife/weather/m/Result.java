package top.genylife.weather.m;

import com.google.auto.value.AutoValue;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@AutoValue
public abstract class Result {

    abstract String status();

    abstract double temperature();

    abstract String skycon();

    abstract double cloudrate();

    abstract int aqi();

    abstract double humidity();

    abstract int pm25();

    abstract Precipitation precipitation();

    abstract Wind wind();
}
