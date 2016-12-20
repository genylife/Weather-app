package top.genylife.weather.injector.modules;

import android.support.v4.app.Fragment;

import dagger.Module;
import dagger.Provides;
import top.genylife.weather.WeatherFragment;
import top.genylife.weather.injector.PerFragment;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@Module
public class WeatherFragmentModule {

    private WeatherFragment mWeatherFragment;

    public WeatherFragmentModule(WeatherFragment weatherFragment) {
        mWeatherFragment = weatherFragment;
    }

    @Provides @PerFragment
    Fragment provideWeatherFragment(){
        return mWeatherFragment;
    }

}
