package top.genylife.weather;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.LinearLayout;

/**
 * Created by wanqi on 2016/12/15.
 *
 * @since 1.0.0
 */

public class SevenDayWeatherView extends LinearLayout {

    public SevenDayWeatherView(Context context) {
        this(context, null);
    }

    public SevenDayWeatherView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SevenDayWeatherView(Context context, AttributeSet attrs, int defStyleAttr) {
        this(context, attrs, defStyleAttr, 0);
    }

    public SevenDayWeatherView(Context context, AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
        setOrientation(LinearLayout.HORIZONTAL);
    }

}