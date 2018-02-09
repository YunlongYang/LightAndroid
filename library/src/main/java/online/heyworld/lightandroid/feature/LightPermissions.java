package online.heyworld.lightandroid.feature;

import android.app.Activity;
import android.os.Build;

import com.tbruyelle.rxpermissions2.RxPermissions;

import online.heyworld.lightandroid.compat.BuildCompat;

/**
 * LightPermissions
 * 方便检查应用权限
 * @author YunlongYang
 * @version 1.0
 */

public class LightPermissions {

    /**
     * 检查应用权限
     * @param activity 当前 Activity
     * @param permissions 需要的权限集合
     * @param listener 权限检查结果回调
     */
    public static void checkPermissions(Activity activity , String[] permissions, Listener listener){
        if(Build.VERSION.SDK_INT >= BuildCompat.VERSION_CODES.M) {
            RxPermissions rxPermissions = new RxPermissions(activity);
            for (String permission : permissions) {
                if (listener.continueCheck) {
                    if (!rxPermissions.isGranted(permission)) {
                        rxPermissions.request(permission).subscribe(granted -> {
                            if (listener.continueCheck) {
                                if (granted) {
                                    listener.onGranted(permission);
                                } else {
                                    listener.onDenied(permission);
                                }
                            }
                        });
                    } else {
                        listener.onGranted(permission);
                    }
                }
            }
        }else{
            for (String permission : permissions) {
                if (listener.continueCheck) {
                    listener.onGranted(permission);
                }
            }
        }
    }

    /**
     * 权限检查结果监听器
     */
    public static abstract class Listener{
        private boolean continueCheck = true;

        /**
         * 权限被授予
         * @param permission
         */
        public abstract void onGranted(String permission);

        /**
         * 权限被拒绝
         * @param permission
         */
        public abstract void onDenied(String permission);

        /**
         * 停止检查
         */
        public void stopCheck(){
            continueCheck = false;
        }
    }
}
