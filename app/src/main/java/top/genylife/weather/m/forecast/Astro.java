package top.genylife.weather.m.forecast;

/**
 * Created by wanqi on 2016/12/20.
 *
 * @since 1.0.0
 */
public class Astro {

    private String date;
    private SunTime sunSet;
    private SunTime sunRise;

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public SunTime getSunSet() {
        return sunSet;
    }

    public void setSunSet(SunTime sunSet) {
        this.sunSet = sunSet;
    }

    public SunTime getSunRise() {
        return sunRise;
    }

    public void setSunRise(SunTime sunRise) {
        this.sunRise = sunRise;
    }

    public static class SunTime {

        private String time;

        public String getTime() {
            return time;
        }

        public void setTime(String time) {
            this.time = time;
        }
    }
}
