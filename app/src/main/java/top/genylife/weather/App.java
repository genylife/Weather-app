package top.genylife.weather;

import android.app.Application;
import android.content.SharedPreferences;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import top.genylife.weather.injector.components.AppComponent;
import top.genylife.weather.injector.components.DaggerAppComponent;
import top.genylife.weather.injector.modules.AppModule;
import top.genylife.weather.m.location.Location;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */

public class App extends Application {

    Map<String, Location> mAllLocation;

    private AppComponent mAppComponent;

    @Override
    public void onCreate() {
        super.onCreate();
        readLocation();
        mAppComponent = DaggerAppComponent.builder()
                .appModule(new AppModule(this, mAllLocation))
                .build();
    }

    public AppComponent getAppComponent() {
        return mAppComponent;
    }

    public void readLocation() {
        SharedPreferences sharedPreferences = getSharedPreferences("Weather", MODE_PRIVATE);
        Map<String, ?> all = sharedPreferences.getAll();
        mAllLocation = new HashMap<>(all.size());
        Set<String> strings = all.keySet();
        for (String key : strings) {
            Object value = all.get(key);
            Location location = null;
            if(value instanceof HashSet) {
                Object[] array = ((HashSet) value).toArray();
                location = new Location((String) array[0], (String) array[1]);
            }
            mAllLocation.put(key, location);
        }
    }
}
