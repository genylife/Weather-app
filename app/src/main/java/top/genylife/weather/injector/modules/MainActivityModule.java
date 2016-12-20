package top.genylife.weather.injector.modules;

import android.app.Activity;

import dagger.Module;
import dagger.Provides;
import top.genylife.weather.MainActivity;
import top.genylife.weather.injector.PerActivity;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
@Module
public class MainActivityModule {

    private final MainActivity mMainActivity;

    public MainActivityModule(MainActivity activity) {
        mMainActivity = activity;
    }

    @Provides @PerActivity
    Activity provideActivity() {
        return mMainActivity;
    }
}
