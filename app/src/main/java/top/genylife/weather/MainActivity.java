package top.genylife.weather;

import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import top.genylife.weather.injector.components.DaggerMainActivityComponent;
import top.genylife.weather.injector.components.MainActivityComponent;
import top.genylife.weather.injector.modules.MainActivityModule;
import top.genylife.weather.m.location.GEOLocation;
import top.genylife.weather.m.location.Location;
import top.genylife.weather.net.LocationService;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    MainActivityComponent mActivityComponent;

    FragmentsPagerAdapter pagerAdapter;

    @Inject
    LocationClient mLocationClient;
    @Inject
    LocationClientOption mLocationClientOption;

    @Inject
    Retrofit mRetrofit;
    @Inject
    Map<String, Location> mAllLocation;

    ViewPager mViewPager;
    ImageView mActionAdd;
    ImageView mActionSetting;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        initUI();
        initInjector();

        //注册监听函数
        mLocationClient.registerLocationListener(new BDLocationListener() {
            @Override
            public void onReceiveLocation(BDLocation location) {
                WeatherFragment fragment = WeatherFragment.create(new Location(location.getLongitude(), location.getLatitude())
                        , location.getDistrict());
                pagerAdapter.addFragment(fragment);
                mLocationClient.stop();
            }
        });
        mLocationClient.setLocOption(mLocationClientOption);
        mLocationClient.start();

        ArrayList<Fragment> fragments = new ArrayList<>();
        Iterator<Map.Entry<String, Location>> iterator = mAllLocation.entrySet().iterator();
        while (iterator.hasNext()) {
            Map.Entry<String, Location> next = iterator.next();
            fragments.add(WeatherFragment.create(next.getValue(), next.getKey()));
        }
        pagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(pagerAdapter);
    }

    private void initInjector() {
        mActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mActivityComponent.inject(this);
    }

    private void initUI() {
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        mViewPager = (ViewPager) findViewById(R.id.viewpager);
        mActionAdd = (ImageView) findViewById(R.id.action_add);
        mActionSetting = (ImageView) findViewById(R.id.action_setting);
        mActionAdd.setOnClickListener(this);
        mActionSetting.setOnClickListener(this);
    }

    public MainActivityComponent getComponent() {
        return mActivityComponent;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.action_add:
                actionAdd();
                break;
            case R.id.action_setting:
                actionSetting();
                break;
        }
    }

    private void actionSetting() {
        Set<? extends Map.Entry<String, ?>> entries = mAllLocation.entrySet();
    }

    private void actionAdd() {
        AddLocationDialog.create(new AddLocationDialog.CallBack() {
            @Override
            public void call(final String text) {
                mRetrofit.create(LocationService.class)
                        .getLocation(LocationService.ak, LocationService.output, text, LocationService.mcode)
                        .subscribeOn(Schedulers.io())
                        .observeOn(AndroidSchedulers.mainThread())
                        .subscribe(new Action1<GEOLocation>() {
                            @Override
                            public void call(GEOLocation geoLocation) {
                                SharedPreferences sharedPreferences = getSharedPreferences("Weather", MODE_PRIVATE);
                                SharedPreferences.Editor editor = sharedPreferences.edit();
                                Set<String> tempSet = new HashSet<>();
                                tempSet.add("" + geoLocation.getResult().getLocation().getLng());
                                tempSet.add("" + geoLocation.getResult().getLocation().getLat());
                                String s = geoLocation.getResult().getLevel();
                                String temp = "";
                                if(s.equals("城市")) temp = "市";
                                if(s.equals("区县")) temp = "区";
                                editor.putStringSet(text + temp, tempSet);
                                editor.apply();
                                pagerAdapter.addFragment(WeatherFragment.create(
                                        new Location(geoLocation.getResult().getLocation().getLng()
                                                , geoLocation.getResult().getLocation().getLat())
                                        , text + temp));
                            }
                        });
            }
        }).show(getFragmentManager(), "DIALOG");
    }
}
