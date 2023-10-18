package com.example.punchpowercalculator;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;
import androidx.appcompat.app.AppCompatActivity;
import jxl.Workbook;
import jxl.WorkbookSettings;
import jxl.write.Label;
import jxl.write.WritableSheet;
import jxl.write.WritableWorkbook;
import java.io.File;
import java.util.Locale;

public class Logbook extends AppCompatActivity {

    private TableLayout tableLayout;
    private Button addRowButton;
    private int rowCounter = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
//        createExcelSpreadsheet();
        tableLayout = findViewById(R.id.tableLayout);
        addRowButton = findViewById(R.id.addRowButton);
        addRowButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addRow("weight", "date", "time", "energy"); // Call a method to add a new row
            }
        });
    }

    private void addRow(String column1Text, String column2Text, String column3Text, String column4Text) {
        TableRow newRow = new TableRow(this);
        newRow.setBackgroundResource(R.drawable.white);

        TextView cell1 = new TextView(this);
        cell1.setText(column1Text);
        cell1.setPadding(5, 5, 5, 5);

        TextView cell2 = new TextView(this);
        cell2.setText(column2Text);
        cell2.setPadding(5, 5, 5, 5);

        TextView cell3 = new TextView(this);
        cell3.setText(column3Text);
        cell3.setPadding(5, 5, 5, 5);

        TextView cell4 = new TextView(this);
        cell4.setText(column4Text);
        cell4.setPadding(5, 5, 5, 5);

        newRow.addView(cell1);
        newRow.addView(cell2);
        newRow.addView(cell3);
        newRow.addView(cell4);

        tableLayout.addView(newRow);
        rowCounter++;
    }

    private void createExcelSpreadsheet() {
        try {
            // Create a WorkbookSettings object
            WorkbookSettings ws = new WorkbookSettings();
            ws.setLocale(new Locale("en", "EN"));

            // Create a new Excel file
            File file = new File(getExternalFilesDir(null), "Logbook.xls");
            WritableWorkbook workbook = Workbook.createWorkbook(file, ws);

            // Create a new sheet
            WritableSheet sheet = workbook.createSheet("Logbook Sheet", 0);

            // Add data to the sheet
            Label label = new Label(0, 0, "Hello Excel!");
            sheet.addCell(label);

            // Write the workbook to the file
            workbook.write();
            workbook.close();

            Toast.makeText(this, "Excel spreadsheet created successfully", Toast.LENGTH_SHORT).show();
        } catch (Exception e) {
            e.printStackTrace();
            Toast.makeText(this, "Error creating Excel spreadsheet", Toast.LENGTH_SHORT).show();
        }
    }
}