package top.genylife.weather;

import android.databinding.DataBindingUtil;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.github.mikephil.charting.components.Description;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IFillFormatter;
import com.github.mikephil.charting.formatter.IValueFormatter;
import com.github.mikephil.charting.interfaces.dataprovider.LineDataProvider;
import com.github.mikephil.charting.interfaces.datasets.ILineDataSet;
import com.github.mikephil.charting.utils.ViewPortHandler;

import java.util.ArrayList;
import java.util.List;

import javax.inject.Inject;

import retrofit2.Retrofit;
import rx.android.schedulers.AndroidSchedulers;
import rx.functions.Action1;
import rx.schedulers.Schedulers;
import top.genylife.weather.databinding.FragmentWeatherBinding;
import top.genylife.weather.m.forecast.ForeCastWeather;
import top.genylife.weather.m.location.GEOAddress;
import top.genylife.weather.m.location.Location;
import top.genylife.weather.m.realtime.RealTimeWeather;
import top.genylife.weather.net.LocationService;
import top.genylife.weather.net.WeatherService;

/**
 * Created by wanqi on 2016/12/15.
 *
 * @since 1.0.0
 */

public class WeatherFragment extends Fragment {


    @Inject
    Retrofit mRetrofit;

    FragmentWeatherBinding mBinding;

    ArrayList<Entry> ymVals;
    ArrayList<Entry> ynVals;

    private Location mLocation;
    private String mDistrict;

    public static WeatherFragment create(Location location, String district) {
        Bundle bundle = new Bundle();
        bundle.putParcelable("Location", location);
        if(!TextUtils.isEmpty(district))
            bundle.putString("District", district);
        WeatherFragment fragment = new WeatherFragment();
        fragment.setArguments(bundle);
        return fragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ((MainActivity) getActivity()).getComponent().subComponent().inject(this);
        mLocation = getArguments().getParcelable("Location");
        mDistrict = getArguments().getString("District", "");
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        mBinding = DataBindingUtil.inflate(inflater, R.layout.fragment_weather, container, false);
        Action1<Throwable> onError = new Action1<Throwable>() {

            @Override
            public void call(Throwable throwable) {
                Toast.makeText(getContext(), throwable.getMessage(), Toast.LENGTH_SHORT).show();
            }
        };
        if(!TextUtils.isEmpty(mDistrict)) {
            mBinding.setLocation(mDistrict);
        } else {
            mRetrofit.create(LocationService.class)
                    .getAddress(LocationService.ak, LocationService.output, mLocation.urlFormat(), LocationService.mcode)
                    .subscribeOn(Schedulers.io())
                    .observeOn(AndroidSchedulers.mainThread())
                    .subscribe(new Action1<GEOAddress>() {
                        @Override
                        public void call(GEOAddress geoLocation) {
                            mBinding.setLocation(geoLocation.getResult().getAddressComponent().getDistrict());
                        }
                    }, onError);
        }

        mRetrofit.create(WeatherService.class)
                .realTime(mLocation.getLng(), mLocation.getLat())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<RealTimeWeather>() {
                    @Override
                    public void call(RealTimeWeather realTimeWeather) {
                        mBinding.setRealTime(realTimeWeather);
                    }
                }, onError);

        mRetrofit.create(WeatherService.class)
                .foreCast(mLocation.getLng(), mLocation.getLat())
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Action1<ForeCastWeather>() {
                    @Override
                    public void call(ForeCastWeather foreCastWeather) {
                        initChart(foreCastWeather);
                        mBinding.viewFiveDay.setWeather(foreCastWeather.getResult().getDaily());
                        mBinding.setTemperature(foreCastWeather.getResult().getDaily().getTemperature().get(0)
                                .getTemperatureValue());
                    }
                }, onError);
        ymVals = new ArrayList<>();
        ynVals = new ArrayList<>();
        return mBinding.getRoot();
    }

    private void initChart(ForeCastWeather foreCastWeather) {
        for (int i = 0; i < 5; i++) {
            ymVals.add(new Entry(i, (float) foreCastWeather.getResult().getDaily().getTemperature().get(i).getMax()));
            ynVals.add(new Entry(i, (float) foreCastWeather.getResult().getDaily().getTemperature().get(i).getMin()));
        }
        LineDataSet dataSetm = createDataSet(ymVals, "m", Color.rgb(214, 127, 111), Color.rgb(214, 127, 111), Color.WHITE);
        LineDataSet dataSetn = createDataSet(ynVals, "n", Color.rgb(247, 181, 66), Color.rgb(247, 181, 66), Color.WHITE);

        LineData data = new LineData();
        data.addDataSet(dataSetn);
        data.addDataSet(dataSetm);
        mBinding.chart.setData(data);

        mBinding.chart.getXAxis().setDrawAxisLine(true);
        mBinding.chart.getXAxis().setTextSize(10f);
        Description description = new Description();
        description.setText("温度走势图");
        mBinding.chart.getXAxis().setTextColor(Color.BLACK);
        mBinding.chart.setDescription(description);
        mBinding.chart.getLegend().setEnabled(false);
        mBinding.chart.getXAxis().setEnabled(false);
        mBinding.chart.getAxisLeft().setDrawAxisLine(false);
        mBinding.chart.getAxisLeft().setEnabled(false);
        mBinding.chart.getAxisRight().setDrawAxisLine(false);
        mBinding.chart.getAxisRight().setEnabled(false);
        mBinding.chart.setTouchEnabled(false);
        mBinding.chart.invalidate();
    }

    private LineDataSet createDataSet(List<Entry> entries, String label, int lineColor, int fillColor, int textColor) {
        LineDataSet dataSet = new LineDataSet(entries, label);
        dataSet.setMode(LineDataSet.Mode.CUBIC_BEZIER);

        dataSet.setValueTextSize(15f);
        dataSet.setValueTextColor(textColor);

        dataSet.setColor(lineColor);

        dataSet.setFillAlpha(200);
        dataSet.setFillFormatter(new IFillFormatter() {
            @Override
            public float getFillLinePosition(ILineDataSet dataSet, LineDataProvider dataProvider) {
                return dataProvider.getYChartMin();
            }
        });
        dataSet.setFillColor(fillColor);

        dataSet.setValueFormatter(new IValueFormatter() {
            @Override
            public String getFormattedValue(float value, Entry entry, int dataSetIndex, ViewPortHandler viewPortHandler) {
                return (int) value + "°";
            }
        });
        dataSet.setDrawFilled(true);
        return dataSet;
    }

}
