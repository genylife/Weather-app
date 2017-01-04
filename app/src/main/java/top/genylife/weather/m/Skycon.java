package top.genylife.weather.m;

/**
 * Created by wanqi on 2016/12/28.
 *
 * @since 1.0.0
 */

public enum Skycon {
    CLEAR_DAY("晴天", 1),//晴天
    CLEAR_NIGHT("晴夜", 2),//晴夜
    PARTLY_CLOUDY_DAY("多云", 3),//多云
    PARTLY_CLOUDY_NIGHT("多云", 4),//多云
    CLOUDY("阴", 5),//阴
    RAIN("雨", 6),// 雨
    SNOW("雪", 7),//雪
    WIND("风", 8),//风
    FOG("雾", 9),//雾
    HAZE("霾",10),//霾
    SLEET("冻雨",11);//冻雨

    private String mValue;
    private int mIndex;

    Skycon(String s, int i) {
        mValue = s;
        mIndex = i;
    }

    public String getValue() {
        return mValue;
    }

    public void setValue(String value) {
        mValue = value;
    }

    public int getIndex() {
        return mIndex;
    }

    public void setIndex(int index) {
        this.mIndex = index;
    }
}
