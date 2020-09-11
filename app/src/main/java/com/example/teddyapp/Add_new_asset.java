package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;

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

public class Add_new_asset extends AppCompatActivity {
private Spinner statusspinner,location,type;
private TextInputEditText serialno,Assettag,Description,Department,Remark;
private Button Addasset,cancelasset ;

DatabaseReference databaseReference;
DatabaseReference databaseReference1;


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
        Remark = (TextInputEditText) findViewById(R.id.remark);
        Addasset = (Button) findViewById(R.id.addasset);
        cancelasset = (Button)findViewById(R.id.cancelasset);



        ArrayAdapter<String> spinerstatusdapter = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Status));
        spinerstatusdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusspinner.setAdapter(spinerstatusdapter);

        Addasset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseReference = FirebaseDatabase.getInstance().getReference("Uchild");
                databaseReference1 = FirebaseDatabase.getInstance().getReference("Users");
                //Geting all the values

                String serial_num = serialno.getText().toString();
                String asset_tag = Assettag.getText().toString();
                String description = Description.getText().toString();
                String deprt = Department.getText().toString();
                String remark = Remark.getText().toString();
String addbtn = Addasset.getText().toString();
                   String id = databaseReference1.push().getKey();
                   String userid = databaseReference.push().getKey();

                   AssetDatabase assetDatabase = new AssetDatabase(id,serial_num,asset_tag,description,deprt,remark);
                 //  databaseReference.child(deprt).setValue(assetDatabase);

                FirebaseDatabase.getInstance().getReference().child("Users").child(userid).child("Uchild").setValue(assetDatabase);


               // Toast.makeText(Add_new_asset.this,"",Toast.LENGTH_LONG).show();
             //  databaseReference.push()
//.setValue(assetDatabase);
            }
        });
    }

}
