package top.genylife.weather;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import top.genylife.weather.m.forecast.Daily;

/**
 * Created by wanqi on 2016/12/15.
 *
 * @since 1.0.0
 */

public class DayWeatherView extends LinearLayout {

    Daily mDailyWeather;
    List<String> mWeeks;

    public DayWeatherView(Context context) {
        this(context, null);
    }

    public DayWeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public DayWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public DayWeatherView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(LinearLayout.HORIZONTAL);
        mWeeks = new ArrayList<>(8);
        mWeeks.add("星期天");
        mWeeks.add("星期一");
        mWeeks.add("星期二");
        mWeeks.add("星期三");
        mWeeks.add("星期四");
        mWeeks.add("星期五");
        mWeeks.add("星期六");
    }

    public void setWeather(Daily daily) {
        int d = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        if("ok".equals(daily.getStatus())) {
            mDailyWeather = daily;
            for (int i = 0; i < 5; i++) {
                ((WeatherView) getChildAt(i)).setWeather(mDailyWeather.getTemperature().get(i),
                        mDailyWeather.getSkycon().get(i),
                        mWeeks.get((d + i-1) % 7));
            }
        }
    }

}