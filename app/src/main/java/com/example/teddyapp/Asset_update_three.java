package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;
import java.util.List;

public class Asset_update_three extends AppCompatActivity {
    private Spinner statusspinner,Location,type,Department, software_Spinner;
    public static TextInputEditText serialno,Assettag,Description,Remark,User;
    private Button Addasset,cancelasset ;
    private Button qrscanner;
    List<String> softtypes = new ArrayList<>( );
    public static DatabaseReference AssetsDatabaseReference;;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_update_three);
        getSupportActionBar().setTitle("Asset Update");
        qrscanner = (Button) findViewById(R.id.qr);

        getSupportActionBar().setTitle("Add New Asset");
        statusspinner = (Spinner) findViewById(R.id.status);
        Location = (Spinner) findViewById(R.id.loction);
        type = (Spinner) findViewById(R.id.type);
        serialno = (TextInputEditText) findViewById(R.id.SerialNo);
        Assettag = (TextInputEditText) findViewById(R.id.AssetTag);
        Description = (TextInputEditText) findViewById(R.id.desc);
        Department = (Spinner) findViewById(R.id.dept);
        User = (TextInputEditText) findViewById(R.id.username);
        Remark = (TextInputEditText) findViewById(R.id.remark);
        software_Spinner = (Spinner) findViewById(R.id.softwarespinner);
        Addasset = (Button) findViewById(R.id.addasset);
        cancelasset = (Button)findViewById(R.id.cancelasset);



//Getting the username



    }
}
