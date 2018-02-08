package online.heyworld.lightandroid.app.testleak;

import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;

import online.heyworld.lightandroid.app.R;
import online.heyworld.lightandroid.util.debug.Debug;

public class LeakActivity extends AppCompatActivity{

    private static final boolean debug = Debug.initModule(LeakActivity.class.getName(),true);

    private static final Logger logger = LoggerFactory.getLogger(LeakActivity.class);

    private static final List<LeakActivity> sLeakTestList = new ArrayList<>();

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
                Snackbar.make(view, getTitle(), Snackbar.LENGTH_LONG)
                        .setAction(getString(R.string.touch_to_quit), (v)->finish()).show();
            }
        });

        if(Debug.canDebug(LeakActivity.class.getName())){
            logger.debug(" I am in debug mode,you will see leak later");
            sLeakTestList.add(this);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
    }
}
