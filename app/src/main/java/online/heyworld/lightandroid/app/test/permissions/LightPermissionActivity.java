package online.heyworld.lightandroid.app.test.permissions;

import android.Manifest;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

import java.util.Arrays;

import online.heyworld.lightandroid.app.MainActivity;
import online.heyworld.lightandroid.app.R;
import online.heyworld.lightandroid.feature.LightPermissions;

public class LightPermissionActivity extends AppCompatActivity {
    LightPermissions.PermissionSession permissionSession;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_light_permission);
        init();
    }
    private void init() {
        permissionSession = LightPermissions.setUp(this, Arrays.asList(Manifest.permission.WRITE_EXTERNAL_STORAGE))
                .onDeny(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LightPermissionActivity.this,"权限不足，将退出",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                }).onGrant(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(LightPermissionActivity.this,"权限授予成功",Toast.LENGTH_SHORT).show();
                    }
                });
        if(permissionSession.hadAllPermissions()){
            permissionSession.invokeGrant();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        checkPermissions();
    }

    public void checkPermissions(){
        if(!permissionSession.hadAllPermissions()){
            permissionSession.request();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        permissionSession.onRequestPermissionsResult(requestCode,permissions,grantResults,this);
    }

}
