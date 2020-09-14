package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class Asset_update extends AppCompatActivity {
Spinner locspinner;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_update);

        getSupportActionBar().setTitle("Asset Update");
locspinner = (Spinner) findViewById(R.id.Loction);

        ArrayAdapter<String> spinerstatusdapterlocation = new ArrayAdapter<>(Asset_update.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Location));
        spinerstatusdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locspinner.setAdapter(spinerstatusdapterlocation);

    }
}
