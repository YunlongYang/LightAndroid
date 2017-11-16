package online.heyworld.lightandroid.app;

import android.app.Application;

import online.heyworld.lightandroid.LightAndroidApplicationLike;

/**
 * Created by Administrator on 2017/11/16.
 */

public class MainApplication extends Application {
    LightAndroidApplicationLike lightAndroidApplicationLike;
    @Override
    public void onCreate() {
        super.onCreate();
        lightAndroidApplicationLike = new LightAndroidApplicationLike();
        lightAndroidApplicationLike.onCreate(this);
    }
}
