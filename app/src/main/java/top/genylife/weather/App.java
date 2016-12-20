package top.genylife.weather;

import android.app.Application;

import top.genylife.weather.injector.components.AppComponent;
import top.genylife.weather.injector.components.DaggerAppComponent;
import top.genylife.weather.injector.modules.AppModule;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */

public class App extends Application {


    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

}
