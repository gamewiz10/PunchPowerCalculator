package com.example.testcv;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.os.Environment;
import android.provider.MediaStore;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class ImagePickerActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST_1 = 1;
    private static final int PICK_IMAGE_REQUEST_2 = 2;
    private ImageView imageView1;
    private ImageView imageView2;

    private Bitmap selectedImage1;
    private Bitmap selectedImage2;
    private Button confirmButton;
    private EditText editTextWeight;
    private EditText editTextBag;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image_picker);

        editTextWeight = findViewById(R.id.weighInput);
        editTextBag = findViewById(R.id.bagInput);
        imageView1 = findViewById(R.id.imageView1);
        imageView2 = findViewById(R.id.imageView2);
        confirmButton = findViewById(R.id.button);


        imageView1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(PICK_IMAGE_REQUEST_1);
            }
        });

        imageView2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openGallery(PICK_IMAGE_REQUEST_2);
            }
        });

        confirmButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (selectedImage1 == null || selectedImage2 == null){
                    Toast.makeText(ImagePickerActivity.this, "Please select 2 images", Toast.LENGTH_SHORT).show();
                }
                if ((editTextBag.getText().toString()).isEmpty() || (editTextWeight.getText().toString()).isEmpty()){
                    Toast.makeText(ImagePickerActivity.this, "Please input your weight and the bag weight", Toast.LENGTH_SHORT).show();
                }
                else{
                    PowerData.getInstance().setBagWeight(Integer.parseInt(editTextBag.getText().toString()));
                    PowerData.getInstance().setWeight(Integer.parseInt(editTextWeight.getText().toString()));
                    startCalculating();
                }
            }
        });
    }

    private void startCalculating(){
        Intent intent = new Intent(this, CalculatorActivity.class);
        Uri image1Uri = saveImageToTempFile(selectedImage1, "image1.jpg");
        Uri image2Uri = saveImageToTempFile(selectedImage2, "image2.jpg");

        // Pass file URIs as extras
        intent.putExtra("image1Uri", image1Uri);
        intent.putExtra("image2Uri", image2Uri);

        startActivity(intent);
    }

    private Uri saveImageToTempFile(Bitmap bitmap, String fileName) {
        // Get the directory for storing temporary files
        File tempDir = getExternalFilesDir(Environment.DIRECTORY_PICTURES);
        if (tempDir == null) {
            // Handle the case where external storage is not available
            return null;
        }

        // Create a temporary file
        File tempFile;
        try {
            tempFile = File.createTempFile(fileName, ".jpg", tempDir);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Save the Bitmap to the temporary file
        try (OutputStream os = new FileOutputStream(tempFile)) {
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, os);
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }

        // Return the URI of the saved file
        return Uri.fromFile(tempFile);
    }

    private void openGallery(int requestCode){
        Intent galleryIntent = new Intent(Intent.ACTION_PICK, MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, requestCode);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && data.getData() != null){
            Uri selectedImageUri = data.getData();
            try {
                Bitmap selectedImage01 = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);
                Bitmap selectedImage02 = MediaStore.Images.Media.getBitmap(getContentResolver(), selectedImageUri);

                // Display the selected image in the appropriate ImageView
                if (requestCode == PICK_IMAGE_REQUEST_1) {
                    selectedImage1 = selectedImage01;
                    imageView1.setImageBitmap(selectedImage1);
                } else if (requestCode == PICK_IMAGE_REQUEST_2) {
                    selectedImage2 = selectedImage02;
                    imageView2.setImageBitmap(selectedImage2);
                }

            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    public boolean saveWeightNumber(View view) {
        String numberText = editTextWeight.getText().toString();
        PowerData.getInstance().setBagWeight(Integer.parseInt(numberText));
        if (!numberText.isEmpty()) {
            try {
                int number = Integer.parseInt(numberText);
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(this, "please enter a weight in lbs", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else{return false;}
        return true;
    }

    public boolean saveNumber(View view){
        String numberText = editTextBag.getText().toString();
        PowerData.getInstance().setBagWeight(Integer.parseInt(numberText));
        if(!numberText.isEmpty()) {
            try {
                int number = Integer.parseInt(numberText);
            } catch (NumberFormatException e) {
                Toast toast = Toast.makeText(this, "please enter a bag weight in lbs", Toast.LENGTH_SHORT);
                toast.show();
            }
        }
        else{return false;}
        return true;
    }


    public Bitmap getSelectedImage1() {
        return selectedImage1;
    }

    public Bitmap getSelectedImage2() {
        return selectedImage2;
    }
}
