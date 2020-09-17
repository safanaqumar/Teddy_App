package com.example.teddyapp;

import androidx.annotation.NonNull;
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
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Track_asset extends AppCompatActivity {
    private Spinner statusspinner,Location,type,Department, software_Spinner;
    TextInputEditText Assettag;
    Button qrscanner, searchasset;
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
        searchasset = (Button) findViewById(R.id.searchasset);

        ArrayAdapter<String> spinerstatusdaptertype = new ArrayAdapter<>(Track_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        spinerstatusdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(spinerstatusdaptertype);


        ArrayAdapter<String> spinerstatusdapterlocation = new ArrayAdapter<>(Track_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Location));
        spinerstatusdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Location.setAdapter(spinerstatusdapterlocation);

        ArrayAdapter<String> spinerstatusdapterdepartment = new ArrayAdapter<>(Track_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Department));
        spinerstatusdapterdepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Department.setAdapter(spinerstatusdapterdepartment);
        Intent intent = getIntent();

        String tagging = intent.getStringExtra("deprt");
        int tagg= spinerstatusdapterdepartment.getPosition(tagging);
     Department.setSelection(tagg);
     //   Department.setSelection(((spinerstatusdapterdepartment)Department.getAdapter()).getPosition(tagging));

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



        searchasset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                searchasset();
            }
        });
    }

    private void searchasset() {

        final String getlocation = Location.getSelectedItem().toString();
      DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Data");
        Query checkdlocation = reference.orderByChild("location").equalTo(getlocation);
        checkdlocation.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                    String getting_tag = dataSnapshot1.child("asset_tag").getValue(String.class);
                    String getting_type = dataSnapshot1.child("typeofasset").getValue(String.class);
                    String getting_dept = dataSnapshot1.child("deprt").getValue(String.class);

                    //String taggg = dataSnapshot.child(concat).child("asset_tag").getValue(String.class);
                    Toast.makeText(Track_asset.this,"Searched",Toast.LENGTH_LONG).show();
                    // run some code
                    Intent intent = new Intent(getApplicationContext(), Track_asset.class);
String tag =  intent.getStringExtra(getting_tag);
                   // intent.putExtra("asset_tag",getting_tag);
                    intent.putExtra("typeofasset",getting_type);
                    intent.putExtra("deprt",getting_dept);

                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });

    }
}
