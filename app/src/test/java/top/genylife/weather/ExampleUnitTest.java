package top.genylife.weather;

import org.junit.Test;

import java.util.Calendar;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {

    @Test
    public void addition_isCorrect() throws Exception {
        int i = Calendar.getInstance().get(Calendar.DAY_OF_WEEK);
        System.out.print(i);
    }
}