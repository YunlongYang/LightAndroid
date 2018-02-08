package online.heyworld.lightandroid.util.debug;

import java.util.HashMap;
import java.util.Map;

/**
 *
 * 所有代码的调试开关
 * The Debug switch for all.
 * @author YunlongYang
 * @date 2018/2/8
 */

public class Debug {

    /**
     * 强制调试，所有受控模块均开启调试模式
     */
    private static boolean sForceDebug = false;

    /**
     * 模块调试，关闭时，所有模块均不允许调试
     */
    private static boolean sModuleDebug = true;

    private static final Map<String,Boolean> sModuleDebugMap = new HashMap<>();

    /**
     * 开启强制调试
     */
    public static void forceDebug(){
        sForceDebug = true;
    }

    /**
     * 初始化调试状态
     * @param moduleName
     * @param initStatus
     */
    public static boolean initModule(String moduleName,boolean initStatus){
        sModuleDebugMap.put(moduleName,initStatus);
        return initStatus;
    }

    /**
     * 是否为调试模式
     * @param moduleName
     * @return
     */
    public static boolean canDebug(String moduleName){
        if(sForceDebug){
            return true;
        }
        return sModuleDebug && sModuleDebugMap.get(moduleName);
    }
}
