package online.heyworld.lightandroid.feature.impl;

import android.app.Application;

import com.squareup.leakcanary.RefWatcher;

/**
 * Created by admin on 2018/2/8.
 */

public class LeakCanaryImpl {
    /**
     *
     * @param application
     * @return
     */
    public static RefWatcher setupLeakCanary(Application application) {

        if (com.squareup.leakcanary.LeakCanary.isInAnalyzerProcess(application)) {
            return RefWatcher.DISABLED;
        }
        RefWatcher refWatcher =  com.squareup.leakcanary.LeakCanary.install(application);

        return refWatcher;
    }
}
