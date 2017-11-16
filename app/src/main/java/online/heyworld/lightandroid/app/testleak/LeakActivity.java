package online.heyworld.lightandroid.app.testleak;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import online.heyworld.lightandroid.app.R;

public class LeakActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_leak);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Leak Activity Test", Snackbar.LENGTH_LONG)
                        .setAction("Touch to quit.点击退出", (v)->finish()).show();
            }
        });

        TestLeakUtil.add(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if(!TestLeakUtil.testing()){
            TestLeakUtil.remove(this);
        }
    }
}
