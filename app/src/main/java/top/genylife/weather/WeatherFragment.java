package top.genylife.weather;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import top.genylife.weather.m.RealTimeWeather;
import top.genylife.weather.net.Weather;

/**
 * Created by wanqi on 2016/12/15.
 *
 * @since 1.0.0
 */

public class WeatherFragment extends Fragment {


    @Inject
    Retrofit mRetrofit;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        DaggerWeatherFragmentComponent.builder()
//                .weatherFragmentModule(new WeatherFragmentModule(this))
//                .mainActivityComponent(((MainActivity) getActivity()).getComponent())
//                .build()
//                .inject(this);
        mRetrofit.create(Weather.class).realTime(121, 25).enqueue(new Callback<RealTimeWeather>() {
            @Override
            public void onResponse(Call<RealTimeWeather> call, Response<RealTimeWeather> response) {
                Log.i("asd", response.body().toString());
            }

            @Override
            public void onFailure(Call<RealTimeWeather> call, Throwable t) {

            }
        });
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_weather, container, false);
        return view;
    }
}
