package com.ferid.app.customprogressview;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.LinearLayout;

import com.ferid.lib.progressview.ProgressView;

/**
 * Created by ferid.cafer on 2/19/2018.
 */
public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        LinearLayout viewValue = findViewById(R.id.viewValue);
        LinearLayout viewPercentage = findViewById(R.id.viewPercentage);

        ProgressView progressValue = new ProgressView(this, viewValue);
        progressValue.setProgressWithUnit(0, 12, 4, "GB");

        ProgressView progressPercentage = new ProgressView(this, viewPercentage);
        progressPercentage.setColors(R.color.lightRed, R.color.red, R.color.white, 14);
        progressPercentage.setProgressWithPercentage(0, 12, 8);
    }

}