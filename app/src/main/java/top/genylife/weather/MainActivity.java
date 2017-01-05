package top.genylife.weather;

import android.content.DialogInterface;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.BDLocationListener;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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
                pagerAdapter.addItem(location.getDistrict(), new Location(location.getLongitude(), location.getLatitude()));
                mLocationClient.stop();
            }
        });
        mLocationClient.setLocOption(mLocationClientOption);
        mLocationClient.start();

        ArrayList<Fragment> fragments = new ArrayList<>();
        List<String> districts = new ArrayList<>();
        List<Location> locations = new ArrayList<>();
        for (Map.Entry<String, Location> next : mAllLocation.entrySet()) {
            districts.add(next.getKey());
            locations.add(next.getValue());
        }
        pagerAdapter = new FragmentsPagerAdapter(getSupportFragmentManager(), districts, locations);
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
        final LinearLayout layout = new LinearLayout(this);
        layout.setOrientation(LinearLayout.VERTICAL);
        for (Map.Entry<String, Location> next : mAllLocation.entrySet()) {
            CheckBox box = new CheckBox(this);
            box.setText(next.getKey());
            layout.addView(box);
        }
        new AlertDialog.Builder(this)
                .setTitle("移除位置")
                .setView(layout)
                .setPositiveButton("移除", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        SharedPreferences sharedPreferences = getSharedPreferences("Weather", MODE_PRIVATE);
                        SharedPreferences.Editor editor = sharedPreferences.edit();
                        for (int i = 0; i < layout.getChildCount(); i++) {
                            CheckBox checkBox = (CheckBox) layout.getChildAt(i);
                            if(checkBox.isChecked()) {
                                editor.remove(checkBox.getText().toString());
                                mAllLocation.remove(checkBox.getText().toString());
                                pagerAdapter.removeItem(checkBox.getText().toString());
                            }
                        }
                        editor.apply();
                        dialog.dismiss();
                    }
                })
                .show();

    }

    private void actionAdd() {
        AddLocationDialog.create(new AddLocationDialog.CallBack() {
            @Override
            public void call(final String text) {
                for (Map.Entry<String, Location> next : mAllLocation.entrySet()) {
                    if(next.getKey().contains(text)) return;
                }
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
                                Location location = new Location(geoLocation.getResult().getLocation().getLng()
                                        , geoLocation.getResult().getLocation().getLat());
                                pagerAdapter.addItem(text + temp, location);

                                mAllLocation.put(text + temp, location);
                            }
                        });
            }
        }).show(getFragmentManager(), "DIALOG");
    }
}
