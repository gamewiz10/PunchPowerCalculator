package com.example.testcv;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import java.io.FileDescriptor;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

public class CalculatorActivity extends AppCompatActivity {
    private TextView resultTextView;

    private Bitmap selectedImage1;
    private Bitmap selectedImage2;
    Logbook logbook = new Logbook();
    private TableViewAdapter adapter;
    private List<LogStats> data = new ArrayList<>();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_calculator);


        resultTextView = findViewById(R.id.resultTextView);

        // Get selected images from ImagePickerActivity
        Uri image1Uri = getIntent().getParcelableExtra("image1Uri");
        Uri image2Uri = getIntent().getParcelableExtra("image2Uri");

        // Load the images from file URIs
        selectedImage1 = loadImageFromUri(image1Uri);
        selectedImage2 = loadImageFromUri(image2Uri);

        int heightChange = Math.abs(calculateHeightChange(selectedImage1, selectedImage2));
        int mass = 34;
        double gravity = 9.81;
        double power = (mass *gravity * heightChange);
        PowerData.getInstance().setPower(power);
        LogStats newStats = new LogStats(165, getCurrentDate(), getCurrentTime(), power);
//        logbook.addLogStats(newStats);
//        adapter = new TableViewAdapter(data);
//        data.add(newStats);
//        adapter.notifyItemInserted(data.size());
//        logbook.SaveData();



        // Display the result in the TextView
        resultTextView.setText("power output: " + power + " Joules");
    }

    private Bitmap loadImageFromUri(Uri uri) {
        if (uri == null) {
            return null;
        }

        try {
            // Get a file descriptor from the URI
            ParcelFileDescriptor parcelFileDescriptor =
                    getContentResolver().openFileDescriptor(uri, "r");

            if (parcelFileDescriptor != null) {
                // Convert the file descriptor to a file descriptor
                FileDescriptor fileDescriptor = parcelFileDescriptor.getFileDescriptor();

                // Decode the file descriptor into a Bitmap
                Bitmap bitmap = BitmapFactory.decodeFileDescriptor(fileDescriptor);

                // Close the file descriptor
                parcelFileDescriptor.close();

                return bitmap;
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    private String getCurrentTime() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat timeFormat = new SimpleDateFormat("h:mm a", Locale.getDefault());

        String currentTime = timeFormat.format(calendar.getTime());
        return currentTime;
    }
    private String getCurrentDate() {
        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("MM/dd/yyyy", Locale.getDefault());

        String currentDate = dateFormat.format(calendar.getTime());
        return currentDate;
    }


    private int calculateHeightChange(Bitmap image1, Bitmap image2) {
        int[][] frame1 = convertToGrayscale(image1);
        int[][] frame2 = convertToGrayscale(image2);

        // Identify green dot in frame 1
        Point greenDotFrame1 = identifyGreenDot(frame1);

        // Identify green dot in frame 2
        Point greenDotFrame2 = identifyGreenDot(frame2);

        // Calculate height change
        return greenDotFrame2.y - greenDotFrame1.y;
    }

    private int[][] convertToGrayscale(Bitmap image) {
        int width = image.getWidth();
        int height = image.getHeight();
        int[][] grayscaleArray = new int[width][height];

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                int pixel = image.getPixel(x, y);
                int red = (pixel >> 16) & 0xff;
                int green = (pixel >> 8) & 0xff;
                int blue = pixel & 0xff;

                int grayscale = (red + green + blue) / 3;

                grayscaleArray[x][y] = grayscale;
            }
        }

        return grayscaleArray;
    }

    private Point identifyGreenDot(int[][] grayscaleArray) {

        int width = grayscaleArray.length;
        int height = grayscaleArray[0].length;
        int greenThreshold = 200;

        for (int x = 0; x < width; x++) {
            for (int y = 0; y < height; y++) {
                // Example: Check if the pixel is bright green
                if (grayscaleArray[x][y] > greenThreshold) {
                    return new Point(x, y);
                }
            }
        }

        // If no green dot is found, return a default point
        return new Point(-1, -1);
    }

    private static class Point {
        int x;
        int y;

        Point(int x, int y) {
            this.x = x;
            this.y = y;
        }
    }
}
