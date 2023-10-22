package com.example.punchpowercalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void openCalculator(View view){
        Intent intent = new Intent(this, PreCalculator.class);
        startActivity(intent);
    }

    public void openLogbook(View view) {
        Intent intent = new Intent(this, Logbook.class);
        startActivity(intent);
    }
}