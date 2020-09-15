package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class Track_asset extends AppCompatActivity {
    private Spinner statusspinner,Location,type,Department, software_Spinner;
    TextInputEditText Assettag;
    Button qrscanner;
    private static final int REQUEST_CAMERA = 1;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_track_asset);
        getSupportActionBar().setTitle("Assets Tracking");
        Department = (Spinner) findViewById(R.id.dept);
        Assettag = (TextInputEditText) findViewById(R.id.AssetTag);
        Location = (Spinner) findViewById(R.id.loction);
        type = (Spinner) findViewById(R.id.type);
        qrscanner = (Button) findViewById(R.id.qr);
        ArrayAdapter<String> spinerstatusdaptertype = new ArrayAdapter<>(Track_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        spinerstatusdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(spinerstatusdaptertype);


        ArrayAdapter<String> spinerstatusdapterlocation = new ArrayAdapter<>(Track_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Location));
        spinerstatusdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Location.setAdapter(spinerstatusdapterlocation);

        ArrayAdapter<String> spinerstatusdapterdepartment = new ArrayAdapter<>(Track_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Department));
        spinerstatusdapterdepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Department.setAdapter(spinerstatusdapterdepartment);
        qrscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Track_asset.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Track_asset.this,
                            new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                } else {
                    startActivity(new Intent(Track_asset.this,QRreader.class));

                }
            }
        });


    }
}
