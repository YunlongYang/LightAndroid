package online.heyworld.lightandroid.app;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.widget.Toast;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(view -> startActivity(new Intent(MainActivity.this, LeakActivity.class)));
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
        checkPermissions();
    }

    public void checkPermissions(){
        LightPermissions.checkPermissions(this, new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE}, new LightPermissions.Listener() {
            @Override
            public void onGranted(String permission) {

            }

            @Override
            public void onDenied(String permission) {
                stopCheck();
                Toast.makeText(MainActivity.this,"权限不足，将退出",Toast.LENGTH_SHORT).show();
                finish();
            }
        });

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

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }


}
