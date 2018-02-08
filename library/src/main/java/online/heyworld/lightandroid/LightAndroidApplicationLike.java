package online.heyworld.lightandroid;

import android.app.Application;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;

import com.squareup.leakcanary.LeakCanary;
import com.squareup.leakcanary.RefWatcher;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import online.heyworld.lightandroid.feature.LightLeakCanary;

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

    private static final Logger log = LoggerFactory.getLogger(LightAndroidApplicationLike.class);

    public void onCreate(Application application){
        init(application);
    }

    private void init(Application application){
        log.debug("LightAndroid start install");
        checkLog(application);
        LightLeakCanary.start(application);
    }





    protected void checkLog(Application application){
        log.debug("AppLabel:\t{}",application.getResources().getString(application.getApplicationInfo().labelRes));
        log.debug("Package:\t{}",application.getPackageName());
        try {
            PackageInfo packageInfo = application.getPackageManager().getPackageInfo(application.getPackageName(),0);
            log.debug("Version:\t{}",packageInfo.versionCode);
            log.debug("VerName:\t{}",packageInfo.versionName);
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
    }
}
