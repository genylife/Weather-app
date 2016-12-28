package top.genylife.weather.injector.components;

import dagger.Subcomponent;
import top.genylife.weather.WeatherFragment;
import top.genylife.weather.injector.PerFragment;
import top.genylife.weather.injector.modules.WeatherFragmentModule;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@PerFragment
@Subcomponent(modules = WeatherFragmentModule.class)
public interface WeatherFragmentComponent {

    void inject(WeatherFragment weatherFragment);

}
