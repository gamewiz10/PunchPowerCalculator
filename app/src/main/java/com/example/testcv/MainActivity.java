package com.example.testcv;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import org.opencv.core.Core;
import androidx.appcompat.app.AppCompatActivity;

import org.opencv.android.OpenCVLoader;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(OpenCVLoader.initDebug()) {
            Log.d("OpenCV Test", "OpenCV is integrated successfully!");
        }
    }

    public void openCalculator(View view){
        Intent intent = new Intent(this, ImagePickerActivity.class);
        startActivity(intent);
    }


    public void openLogbook(View view) {
        Intent intent = new Intent(this, Logbook.class);
        startActivity(intent);
    }

    static{

        if(OpenCVLoader.initDebug()){

            Log.d("Check","OpenCv configured successfully");

        } else{

            Log.d("Check","OpenCv doesn’t configured successfully");

        }

    }
}