package top.genylife.weather.injector.components;

import android.app.Activity;

import dagger.Component;
import top.genylife.weather.MainActivity;
import top.genylife.weather.injector.PerActivity;
import top.genylife.weather.injector.modules.MainActivityModule;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@PerActivity
@Component(modules = MainActivityModule.class, dependencies = AppComponent.class)
public interface MainActivityComponent {

    void inject(MainActivity mainActivity);

    Activity activity();
}
