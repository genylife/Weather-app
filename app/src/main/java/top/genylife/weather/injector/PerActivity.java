package top.genylife.weather.injector;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;

import javax.inject.Scope;

/**
 * Created by wanqi on 2016/12/19.
 *
 * @since 1.0.0
 */

@Scope
@Retention(RetentionPolicy.RUNTIME)
public @interface PerActivity {

}
