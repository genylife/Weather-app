package top.genylife.weather;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by wanqi on 2016/12/15.
 *
 * @since 1.0.0
 */

public class WeatherView extends FrameLayout {

    private TextView mDayOfWeek;
    private TextView mTemperature;
    private ImageView mWeatherImage;

    public WeatherView(Context context) {
        this(context, null);
    }

    public WeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);

    }

    public WeatherView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        View view = LayoutInflater.from(context).inflate(R.layout.view_weather, this);
        mDayOfWeek = (TextView) view.findViewById(R.id.day_of_week);
        mTemperature = (TextView) view.findViewById(R.id.temperature);
        mWeatherImage = (ImageView) view.findViewById(R.id.weather_image);
    }

}
