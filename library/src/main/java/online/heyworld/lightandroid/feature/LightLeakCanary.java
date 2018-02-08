package online.heyworld.lightandroid.feature;

import android.app.Application;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import online.heyworld.lightandroid.feature.impl.LeakCanaryImpl;

/**
 * LightLeakCanary
 *
 * @author YunlongYang
 * @version 1.0
 */

public class LightLeakCanary {

    private static boolean sEnable = true;

    private static final Logger log = LoggerFactory.getLogger(LightLeakCanary.class);


    public static void start(Application application){
        if(sEnable){
            log.debug("setupLeakCanary start");
            LeakCanaryImpl.setupLeakCanary(application);
            log.debug("setupLeakCanary done");
        }else {
            log.info("setupLeakCanary not start for user turn off.");
        }
    }

}
