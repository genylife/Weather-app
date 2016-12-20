package top.genylife.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;

import java.util.ArrayList;

import top.genylife.weather.injector.components.DaggerMainActivityComponent;
import top.genylife.weather.injector.components.MainActivityComponent;
import top.genylife.weather.injector.modules.MainActivityModule;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    MainActivityComponent mActivityComponent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mActivityComponent= DaggerMainActivityComponent.builder()
                .appComponent(((App)getApplication()).getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mActivityComponent.inject(this);

        ArrayList<Fragment> fragments = new ArrayList<>();
        fragments.add(new WeatherFragment());
        fragments.add(new WeatherFragment());
        fragments.add(new WeatherFragment());
        mViewPager.setAdapter(new FragmentsPagerAdapter(getSupportFragmentManager(), fragments));
    }

    public MainActivityComponent getComponent() {
        return mActivityComponent;
    }
}
