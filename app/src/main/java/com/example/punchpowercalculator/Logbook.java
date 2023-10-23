package com.example.punchpowercalculator;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

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

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);
        data = getStatList();
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
            }
        });
    }


    private List<LogStats> getStatList() {
        List<LogStats> statsList = new ArrayList<>();
        return statsList;
    }

}