package com.example.punchpowercalculator;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class PreCalculator extends AppCompatActivity {

    private EditText editText;
    private Button confirmButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pre_calculator);

        editText = findViewById(R.id.weighInput);
        confirmButton = findViewById(R.id.confirm_button);

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openCameraActivity();
            }
        });

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    private void openCameraActivity() {
        Intent intent = new Intent(this, CameraActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onSupportNavigateUp() {
        onBackPressed();
        return true;
    }
    public void saveNumber(View view){
        String numberText = editText.getText().toString();
        if(!numberText.isEmpty()) {
            try {
                int number = Integer.parseInt(numberText);
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(this, "please enter a weight in lbs", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
    }
}