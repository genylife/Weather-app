package top.genylife.weather.injector.modules;

import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.inject.Singleton;

import dagger.Module;
import dagger.Provides;
import okhttp3.OkHttpClient;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.fastjson.FastJsonConverterFactory;
import top.genylife.weather.App;
import top.genylife.weather.m.location.Location;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@Module
public class AppModule {

    private final App application;
    private Map<String, Location> mAllLocation;

    public AppModule(App application, Map<String, Location> map) {
        this.application = application;
        mAllLocation = map;
    }

    @Provides
    @Singleton
    Map<String, Location> provideAllLocation() {
        return mAllLocation;
    }

    @Provides
    @Singleton
    App provideApplicationContext() {
        return this.application;
    }

    @Provides
    @Singleton
    OkHttpClient provideHttpClient() {
        return new OkHttpClient.Builder()
                .retryOnConnectionFailure(true)
                .connectTimeout(10, TimeUnit.SECONDS)
                .addInterceptor(new HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
                .build();
    }

    @Provides
    @Singleton
    Retrofit provideRetrofitForWeather(OkHttpClient client) {
        return new Retrofit.Builder()
                .baseUrl("https://api.caiyunapp.com/v2/A=lIEM0fjcKdQHAZ/")
                .client(client)
                .addConverterFactory(FastJsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .build();
    }
}
