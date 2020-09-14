package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Asset_update extends AppCompatActivity {
Spinner locspinner;
Button confirm_btn;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_update);

        getSupportActionBar().setTitle("Asset Update");
       locspinner = (Spinner) findViewById(R.id.Loction);

        ArrayAdapter<String> spinerstatusdapterlocation = new ArrayAdapter<>(Asset_update.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Location));
        spinerstatusdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locspinner.setAdapter(spinerstatusdapterlocation);

       /* confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                reference = FirebaseDatabase.getInstance().getReference().child("Data");


            }
        });
*/


        //reference = FirebaseDatabase.getInstance().getReference().child();

    }
}
