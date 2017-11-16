package online.heyworld.lightandroid;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * LightAndroidApplicationLike
 *
 * The entry class to use Light Library.
 * Which allow user not modify their code about Application{@link android.app.Application}.
 *
 *
 *
 * 使用 Light Library 的入口类.
 * 使用者可以不需要修改Application相关的代码.
 *
 * @author YunLong
 * @version 0.1
 */

public class LightAndroidApplicationLike {

    public void onCreate(Application application){
        setupLeakCanary(application);
    }

    /**
     *
     * @param application
     * @return
     */
    protected RefWatcher setupLeakCanary(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(application);
    }
}
