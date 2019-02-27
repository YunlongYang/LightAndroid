package online.heyworld.lightandroid.app;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.TextView;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import online.heyworld.lightandroid.app.test.permissions.LightPermissionActivity;
import online.heyworld.lightandroid.app.testleak.LeakActivity;
import online.heyworld.lightandroid.feature.LightPermissions;

public class MainActivity extends AppCompatActivity {

    private static final Logger log = LoggerFactory.getLogger(MainActivity.class);


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        log.debug("onCreate");
        setContentView(R.layout.activity_main);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        showFeatures();
    }

    private void showFeatures(){
        TextView tv = findViewById(R.id.sample_text);
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append(getResources().getString(online.heyworld.lightandroid.R.string.support_feature)).append("\n");
        String[] features = getResources().getStringArray(online.heyworld.lightandroid.R.array.support_feature);
        for(int i=0;i<features.length;i++){
            stringBuilder.append(i+1).append(". ").append(features[i]).append("\n");
            log.info("Features:{}",features[i]);
        }
        tv.setText(stringBuilder.toString());
    }

    @Override
    protected void onResume() {
        super.onResume();
        log.debug("onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();
        log.debug("onPause");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        log.debug("onDestroy");
    }


    public void testLeak(View view) {
        startActivity(new Intent(MainActivity.this, LeakActivity.class));
    }

    public void testPermissions(View view) {
        startActivity(new Intent(MainActivity.this, LightPermissionActivity.class));
    }
}
