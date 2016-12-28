package top.genylife.weather.net;

import retrofit2.http.GET;
import retrofit2.http.Path;
import rx.Observable;
import top.genylife.weather.m.forecast.ForeCastWeather;
import top.genylife.weather.m.realtime.RealTimeWeather;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */

public interface WeatherService {

    @GET("{longitude},{latitude}/realtime.json")
    Observable<RealTimeWeather> realTime(@Path("longitude") double longitude, @Path("latitude") double latitude);

    @GET("{longitude},{latitude}/forecast.json")
    Observable<ForeCastWeather> foreCast(@Path("longitude") double longitude, @Path("latitude") double latitude);
}
