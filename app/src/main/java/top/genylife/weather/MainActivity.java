package top.genylife.weather;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Toast;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.List;

import top.genylife.weather.injector.components.DaggerMainActivityComponent;
import top.genylife.weather.injector.components.MainActivityComponent;
import top.genylife.weather.injector.modules.MainActivityModule;
import top.genylife.weather.m.location.Location;

public class MainActivity extends AppCompatActivity {

    ViewPager mViewPager;
    MainActivityComponent mActivityComponent;

    List<Location> mLocationList;

    FragmentsPagerAdapter pagerAdapter;

    public LocationClient mLocationClient = null;
    public BDLocationListener myListener = new MyLocationListener();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Window window = getWindow();
        window.getDecorView().setSystemUiVisibility(View.SYSTEM_UI_FLAG_LAYOUT_FULLSCREEN);
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.setStatusBarColor(Color.TRANSPARENT);
        setContentView(R.layout.activity_main);

        mLocationClient = new LocationClient(getApplicationContext());     //声明LocationClient类
        initLocation();
        mLocationClient.registerLocationListener(myListener);    //注册监听函数
        mLocationClient.start();

        mViewPager = (ViewPager) findViewById(R.id.viewpager);

        mLocationList = new ArrayList<>();
        mLocationList.add(new Location(121.483, 31.2333));

        mActivityComponent = DaggerMainActivityComponent.builder()
                .appComponent(((App) getApplication()).getAppComponent())
                .mainActivityModule(new MainActivityModule(this))
                .build();
        mActivityComponent.inject(this);

        ArrayList<Fragment> fragments = new ArrayList<>();
//        fragments.add(WeatherFragment.create(mLocationList.get(0)));
        pagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager(), fragments);
        mViewPager.setAdapter(pagerAdapter);
    }

    private void initLocation() {
        LocationClientOption option = new LocationClientOption();
        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);//可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
        option.setCoorType("bd09ll");//可选，默认gcj02，设置返回的定位结果坐标系
        option.setScanSpan(0);//可选，默认0，即仅定位一次，设置发起定位请求的间隔需要大于等于1000ms才是有效的
        option.setIsNeedAddress(true);//可选，设置是否需要地址信息，默认不需要
        option.setOpenGps(true);//可选，默认false,设置是否使用gps
        option.setLocationNotify(true);//可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
        option.setIsNeedLocationDescribe(true);//可选，默认false，设置是否需要位置语义化结果，可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
        option.setIsNeedLocationPoiList(false);//可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
        option.setIgnoreKillProcess(false);//可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
        option.SetIgnoreCacheException(false);//可选，默认false，设置是否收集CRASH信息，默认收集
        option.setEnableSimulateGps(false);//可选，默认false，设置是否需要过滤GPS仿真结果，默认需要
        mLocationClient.setLocOption(option);
    }

    class MyLocationListener implements BDLocationListener {

        @Override
        public void onReceiveLocation(BDLocation location) {
            Toast.makeText(MainActivity.this, location.getCity(), Toast.LENGTH_SHORT).show();
            WeatherFragment fragment = WeatherFragment.create(new Location(location.getLongitude(), location.getLatitude()));
            pagerAdapter.addFragment(fragment);
        }
    }

    public MainActivityComponent getComponent() {
        return mActivityComponent;
    }
}
