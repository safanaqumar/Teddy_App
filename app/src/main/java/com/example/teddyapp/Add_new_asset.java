package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;

import com.google.android.material.textfield.TextInputEditText;

public class Add_new_asset extends AppCompatActivity {
private Spinner statusspinner,location,type;
private TextInputEditText serialno,Assettag,Description,Department,remark;
private Button Addasset,cancelasset;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_asset);
        getSupportActionBar().setTitle("Add New Asset");
        statusspinner = (Spinner) findViewById(R.id.status);
        location = (Spinner) findViewById(R.id.loction);
        type = (Spinner) findViewById(R.id.type);
        serialno = (TextInputEditText) findViewById(R.id.SerialNo);
        Assettag = (TextInputEditText) findViewById(R.id.AssetTag);
        Description = (TextInputEditText) findViewById(R.id.desc);
        Department = (TextInputEditText) findViewById(R.id.dept);
        remark = (TextInputEditText) findViewById(R.id.remark);
        Addasset = (Button) findViewById(R.id.addasset);
        cancelasset = (Button)findViewById(R.id.cancelasset);

        ArrayAdapter<String> spinerstatusdapter = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Status));
        spinerstatusdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusspinner.setAdapter(spinerstatusdapter);

        Addasset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

            }
        });
    }

}
