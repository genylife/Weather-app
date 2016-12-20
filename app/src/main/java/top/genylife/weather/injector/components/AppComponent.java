package top.genylife.weather.injector.components;

import javax.inject.Singleton;

import dagger.Component;
import retrofit2.Retrofit;
import top.genylife.weather.App;
import top.genylife.weather.injector.modules.AppModule;

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
}
