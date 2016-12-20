package top.genylife.weather.injector.components;

import android.support.v4.app.Fragment;

import dagger.Component;
import top.genylife.weather.WeatherFragment;
import top.genylife.weather.injector.PerFragment;
import top.genylife.weather.injector.modules.WeatherFragmentModule;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@PerFragment
@Component(modules = WeatherFragmentModule.class, dependencies = {MainActivityComponent.class})
public interface WeatherFragmentComponent {

    void inject(WeatherFragment weatherFragment);

    Fragment fragment();
}
