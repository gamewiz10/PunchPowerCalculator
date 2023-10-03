package com.example.punchpowercalculator;

import android.os.Bundle;
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

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_logbook);
        createExcelSpreadsheet();
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