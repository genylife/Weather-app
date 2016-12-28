package top.genylife.weather.m.realtime;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */
public  class Precipitation {

    private  Nearest nearest;
    private  Local local;

    public Nearest getNearest() {
        return nearest;
    }

    public void setNearest(Nearest nearest) {
        this.nearest = nearest;
    }

    public Local getLocal() {
        return local;
    }

    public void setLocal(Local local) {
        this.local = local;
    }
}
