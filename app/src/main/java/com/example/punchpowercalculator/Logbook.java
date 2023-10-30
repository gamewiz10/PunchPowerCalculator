package com.example.punchpowercalculator;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;


import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

public class Logbook extends AppCompatActivity {

    //pre made variables
    private TableLayout tableLayout;
    private Button addRowButton;

    //post recycler variables
    private RecyclerView recyclerView;
    private TableViewAdapter adapter;
    private List<LogStats> data;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        loadSavedData();

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);
//        data = getStatList();
        adapter = new TableViewAdapter(data);

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);

        addRowButton = findViewById(R.id.addRowButton);

        addRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogStats newStats = new LogStats(150, "10/20/2022", "3:00", 120);
                data.add(newStats);
                adapter.notifyItemInserted(data.size());
                SaveData();
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == android.R.id.home) {
            onBackPressed();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    private void SaveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String dataJson = toJson(data);

        // Save the JSON string in SharedPreferences
        editor.putString("data", dataJson);
        editor.apply();
    }

    private void loadSavedData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        String savedDataJson = sharedPreferences.getString("data", null);

        if (savedDataJson != null) {
            data = fromJson(savedDataJson);
        }
    }

    private List<LogStats> fromJson(String json) {
        Gson gson = new Gson();

        java.lang.reflect.Type type = new TypeToken<List<LogStats>>(){}.getType();

        return gson.fromJson(json,type);
    }


    private String toJson(List<LogStats> data) {
        Gson gson = new Gson();
        return gson.toJson(data);
    }


    private List<LogStats> getStatList() {
        List<LogStats> statsList = new ArrayList<>();
        return statsList;
    }

}