package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.example.teddyapp.AssetDatabase.AssetDatabase;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Asset_update extends AppCompatActivity {
Spinner locspinner, type_up;
Button confirm_btn;
DatabaseReference reference;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_update);
confirm_btn =(Button)findViewById(R.id.confirmbtn);
        getSupportActionBar().setTitle("Asset Update");
       locspinner = (Spinner) findViewById(R.id.Loction);
type_up = (Spinner) findViewById(R.id.type);

        ArrayAdapter<String> spinerstatusdapterlocation = new ArrayAdapter<>(Asset_update.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Location));
        spinerstatusdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        locspinner.setAdapter(spinerstatusdapterlocation);


        ArrayAdapter<String> spinerstatusdaptertype = new ArrayAdapter<>(Asset_update.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        spinerstatusdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type_up.setAdapter(spinerstatusdaptertype);

        confirm_btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                        Intent intent = new Intent(Asset_update.this,Asset_update_two.class);
                        startActivity(intent);
                        finish();
            }
        });



        //reference = FirebaseDatabase.getInstance().getReference().child();

    }
}
