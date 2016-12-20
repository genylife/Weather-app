package top.genylife.weather.m;

import com.google.auto.value.AutoValue;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@AutoValue
public abstract class Wind {

    abstract double direction();

    abstract double speed();
}
