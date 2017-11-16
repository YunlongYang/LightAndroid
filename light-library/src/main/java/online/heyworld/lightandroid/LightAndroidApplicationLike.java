package online.heyworld.lightandroid;

import android.app.Application;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

/**
 * Created by Administrator on 2017/11/16.
 */

public class LightAndroidApplicationLike {

    public void onCreate(Application application){
        setupLeakCanary(application);
    }

    protected RefWatcher setupLeakCanary(Application application) {
        if (LeakCanary.isInAnalyzerProcess(application)) {
            return RefWatcher.DISABLED;
        }
        return LeakCanary.install(application);
    }
}
