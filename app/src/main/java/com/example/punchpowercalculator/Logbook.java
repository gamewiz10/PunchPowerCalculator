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
    private int rowCounter = 1;

    //post recycler variables
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);

        RecyclerView recyclerView = findViewById(R.id.recyclerViewDeliveryProductList);

        TableViewAdapter adapter = new TableViewAdapter(getStatList());

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(linearLayoutManager);

        recyclerView.setAdapter(adapter);


    }

    private List<LogStats> getStatList() {
        List<LogStats> statsList = new ArrayList<>();

        statsList.add(new LogStats(150, "10/22/2023", "2:25", 300));

        return statsList;
    }

}