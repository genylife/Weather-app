package top.genylife.weather.net;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;
import top.genylife.weather.m.RealTimeWeather;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */

public interface Weather {

    @GET("{longitude},{latitude}/realtime.json")
    Call<RealTimeWeather> realTime(@Path("longitude") double longitude, @Path("latitude") double latitude);
}
