package top.genylife.weather.injector.components;

import java.util.Map;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import top.genylife.weather.App;
import top.genylife.weather.injector.modules.AppModule;
import top.genylife.weather.m.location.Location;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@Singleton
@Component(modules = AppModule.class)
public interface AppComponent {

    App app();

    Retrofit retrofit();
    
    Map<String,Location> allLocation();
}
