package top.genylife.weather.net;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;
import top.genylife.weather.m.location.GEOAddress;
import top.genylife.weather.m.location.GEOLocation;

/**
 * Created by wanqi on 2016/12/27.
 *
 * @since 1.0.0
 */

public interface LocationService {

    String ak = "jfwiXIIpPA7YWSAzQCrEif8fbAWRSgCp";
    String location = "";
    String output = "json";
    String mcode = "0E:BD:CD:5A:3B:FA:73:48:69:9D:7F:50:D3:F5:DF:7E:FE:74:90:C5;top.genylife.weather";

    @GET("http://api.map.baidu.com/geocoder/v2/")
    Observable<GEOAddress> getAddress(@Query("ak") String ak, @Query("output") String output
            , @Query("location") String location, @Query("mcode") String mcode);

    @GET("http://api.map.baidu.com/geocoder/v2/")
    Observable<GEOLocation> getLocation(@Query("ak") String ak, @Query("output") String output
            , @Query("address") String address, @Query("mcode") String mcode);
}
