package com.example.testcv;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.util.ArrayList;
import java.util.List;

public class Logbook extends AppCompatActivity {

    //pre made variables
    private TableLayout tableLayout;
    private Button addRowButton;

    //post recycler variables
    private RecyclerView recyclerView;
    private TableViewAdapter adapter;
    private List<LogStats> data = new ArrayList<>();
    double power;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
        power = PowerData.getInstance().getPower();

        loadSavedData();

        Toolbar toolbar1 = findViewById(R.id.toolbar1);
        setSupportActionBar(toolbar1);


        ActionBar actionBar = getSupportActionBar();
        if (actionBar != null) {
            actionBar.setDisplayHomeAsUpEnabled(true);
        }

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);

        adapter = new TableViewAdapter(data);


        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);
        recyclerView.setAdapter(adapter);


        addRowButton = findViewById(R.id.addRowButton);
        Button removeRowButton = findViewById(R.id.removeRowButton);

        addRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                LogStats newStats = new LogStats(167, "11/29/2023", "15:13", 220);
                data.add(newStats);
                adapter.notifyItemInserted(data.size());
                SaveData();
            }
        });

        removeRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (data.size() > 1) {
                    data.remove(data.size() - 1);
                    adapter.notifyItemRemoved(data.size());
                    SaveData();
                }
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

    public void SaveData() {
        SharedPreferences sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();


        String dataJson = toJson(data);

        editor.putString("data", dataJson);
        editor.apply();
    }

    public List<LogStats> getData() {
        return data;
    }

    public void addLogStats(LogStats logStats) {
        data.add(logStats);
    }
    public int getsize() {
        return data.size();
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
        statsList.add(new LogStats(150, "10/22/2023", "2:25", 300));
        return statsList;
    }

}