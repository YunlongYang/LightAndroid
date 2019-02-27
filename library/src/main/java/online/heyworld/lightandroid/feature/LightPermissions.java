package online.heyworld.lightandroid.feature;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.net.Uri;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * LightPermissions
 * 方便检查应用权限
 * @author YunlongYang
 * @version 1.0
 */

public class LightPermissions {

    private static final Map<String,String> sSystemPermissionMap = new HashMap<>();
    private static final PermissionCheck sPermissionCheck = new PermissionCheckTool();
    static {
        sSystemPermissionMap.put(Manifest.permission.WRITE_SETTINGS,Settings.ACTION_MANAGE_WRITE_SETTINGS);
    }

    public static PermissionSession setUp(Activity activity,List<String> permissions) {
        return new PermissionSession(activity,permissions);
    }

    public static boolean has(Activity activity,String permission){
        return ContextCompat.checkSelfPermission(activity,permission) == PackageManager.PERMISSION_GRANTED;
    }

    public static class PermissionSession{
        Activity activity;
        List<String> permissions;
        List<String> permissionsLoss;
        Runnable onUserDeny;
        Runnable onUserGrant;

        public PermissionSession(Activity activity, List<String> permissions) {
            this.activity = activity;
            this.permissions = permissions;
        }

        public PermissionSession onDeny(Runnable onUserDeny){
            this.onUserDeny = onUserDeny;
            return this;
        }
        public PermissionSession onGrant(Runnable onUserGrant){
            this.onUserGrant = onUserGrant;
            return this;
        }

        public void invokeGrant(){
            onUserGrant.run();
        }

        public boolean hadAllPermissions(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                permissionsLoss = new ArrayList<>();
                for(String p : permissions){
                    if(!sPermissionCheck.check(activity,p)){
                        permissionsLoss.add(p);
                    }
                }
            }else{

            }
            return permissions.isEmpty();
        }

        public void request(){
            if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                permissionsLoss = new ArrayList<>();
                for(String p : permissions){
                    if(!sPermissionCheck.check(activity,p)){
                        permissionsLoss.add(p);
                    }
                }
                if(permissionsLoss.isEmpty()){
                    onUserGrant.run();
                }else{
                    Set<String> systemPermissions = sSystemPermissionMap.keySet();
                    List<String> permissionsSystemLoss = new ArrayList<>();
                    for (String p:permissionsLoss){
                        if(systemPermissions.contains(p)){
                            permissionsSystemLoss.add(p);
                        }
                    }
                    permissionsLoss.removeAll(permissionsSystemLoss);
                    if(!permissionsLoss.isEmpty()){
                        ActivityCompat.requestPermissions(activity,permissions.toArray(new String[0]),requestCodeForPermissions(permissionsLoss));
                    }else{
                        if(permissionsSystemLoss.isEmpty()){
                            onUserGrant.run();
                        }else{
                            resolveSystemPermissions(permissionsSystemLoss);
                        }
                    }
                }
            }else{
                onUserGrant.run();
            }

        }

        private void resolveSystemPermissions(List<String> permissionsSystemLoss){
            Intent intent = new Intent( sSystemPermissionMap.get(permissionsSystemLoss.get(0)),
                    Uri.parse("package:" + activity.getPackageName()));
            activity.startActivity(intent);
        }


        public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults, Activity activity) {
            if(requestCode == requestCodeForPermissions(permissionsLoss)){
                if(activity == this.activity){
                    boolean userGrant = true;
                    for(int result: grantResults){
                        if(result!=PackageManager.PERMISSION_GRANTED){
                            userGrant = false;
                            break;
                        }
                    }
                    if(userGrant){
                        onUserGrant.run();
                    }else{
                        onUserDeny.run();
                    }
                }
            }
        }
    }

    private static int requestCodeForPermissions(Object obj) {
        return Math.abs(obj.hashCode()%10000);
    }

    private interface PermissionCheck{
        boolean check(Context context, String permission);
    }

    private static class PermissionCheckTool implements PermissionCheck{

        @Override
        public boolean check(Context context,String permission) {
            if(sSystemPermissionMap.containsKey(permission)){
                return systemCheck(context, permission);
            }else{
                return normalCheck(context, permission);
            }
        }

        private boolean normalCheck(Context context,String permission){
            return ContextCompat.checkSelfPermission(context,permission)== PackageManager.PERMISSION_GRANTED;
        }

        private boolean systemCheck(Context context,String permission){
            if(permission.equals(Manifest.permission.WRITE_SETTINGS)){
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                    return Settings.System.canWrite(context);
                }else {
                    return true;
                }
            }
            return true;
        }
    }
}
