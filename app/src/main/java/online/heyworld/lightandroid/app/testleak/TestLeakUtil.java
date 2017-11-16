package online.heyworld.lightandroid.app.testleak;

import java.util.ArrayList;
import java.util.List;

import online.heyworld.lightandroid.app.TestSwitch;

/**
 * Created by Administrator on 2017/11/16.
 */

public class TestLeakUtil {

    private static final boolean SWITCH_ENABLE = true;

    private static final List<Object> testLeakList = new ArrayList<>();

    public static boolean add(Object o) {
        return testLeakList.add(o);
    }

    public static boolean remove(Object o) {
        return testLeakList.remove(o);
    }


    public static boolean testing(){
        return TestSwitch.SWITCH_ENABLE && SWITCH_ENABLE;
    }

}
