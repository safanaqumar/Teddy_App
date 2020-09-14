package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teddyapp.AssetDatabase.AssetDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Add_new_asset extends AppCompatActivity  {
private Spinner statusspinner,Location,type;
public static TextInputEditText serialno,Assettag,Description,Department,Remark,User;
private Button Addasset,cancelasset ;
private Button qrscanner;
    private static final int REQUEST_CAMERA = 1;


    DatabaseReference AssetsDatabaseReference;;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_asset);
        qrscanner = (Button) findViewById(R.id.qr);

        getSupportActionBar().setTitle("Add New Asset");
        statusspinner = (Spinner) findViewById(R.id.status);
        Location = (Spinner) findViewById(R.id.loction);
        type = (Spinner) findViewById(R.id.type);
        serialno = (TextInputEditText) findViewById(R.id.SerialNo);
        Assettag = (TextInputEditText) findViewById(R.id.AssetTag);
        Description = (TextInputEditText) findViewById(R.id.desc);
        Department = (TextInputEditText) findViewById(R.id.dept);
        User = (TextInputEditText) findViewById(R.id.username);
        Remark = (TextInputEditText) findViewById(R.id.remark);
        Addasset = (Button) findViewById(R.id.addasset);
        cancelasset = (Button)findViewById(R.id.cancelasset);
        AssetsDatabaseReference = FirebaseDatabase.getInstance().getReference("assets");



        qrscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Add_new_asset.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Add_new_asset.this,
                            new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                } else {
                    startActivity(new Intent(getApplicationContext(),QRreader.class));

                }
            }
        });

       // FirebaseDatabase.getInstance().getReference().child("Add_Asset");
        // FirebaseDatabase.getInstance().getReference().child("Child").child("Aseetdata").setValue("data");

        ArrayAdapter<String> spinerstatusdapter = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Status));
        spinerstatusdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusspinner.setAdapter(spinerstatusdapter);
        ArrayAdapter<String> spinerstatusdaptertype = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        spinerstatusdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(spinerstatusdaptertype);
        ArrayAdapter<String> spinerstatusdapterlocation = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Location));
        spinerstatusdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Location.setAdapter(spinerstatusdapterlocation);
        Addasset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String serial_num = serialno.getText().toString();
                  String asset_tag = Assettag.getText().toString();
                  String typeofasset = type.getSelectedItem().toString();

                  String description = Description.getText().toString();
                  String location = Location.getSelectedItem().toString();
                  String deprt = Department.getText().toString();
                  String statusasset= statusspinner.getSelectedItem().toString();
                  String remark = Remark.getText().toString();
                  AssetDatabase assetDatabase = new AssetDatabase(serial_num,asset_tag,typeofasset,description,location,deprt,statusasset,remark);
                AssetsDatabaseReference.child(serial_num).setValue(assetDatabase);
                Toast.makeText(Add_new_asset.this,"Added",Toast.LENGTH_LONG).show();

            }
        });
    }


}
