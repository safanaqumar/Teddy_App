package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teddyapp.AssetDatabase.AssetDatabase;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

public class Asset_update extends AppCompatActivity {
Spinner locspinner, type_up;
Button confirm_btn;
public  DatabaseReference reference;
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
             isUser();

            }
        });



        //reference = FirebaseDatabase.getInstance().getReference().child();

    }

    public void isUser() {
     //   final  String reader1 =
        final String getUserEnteredlocspinners = locspinner.getSelectedItem().toString();
        final String getEnteredassetTypes = type_up.getSelectedItem().toString();
        final String concat = getUserEnteredlocspinners.concat(getEnteredassetTypes);
        reference = FirebaseDatabase.getInstance().getReference("Data");
       Query checkdlocation = reference.orderByChild("reader").equalTo(concat);


        checkdlocation.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
              //  if (dataSnapshot.hasChild("asset_tag")) {
                for (DataSnapshot dataSnapshot1 : dataSnapshot.getChildren()) {
                 String getting_tag = dataSnapshot1.child("asset_tag").getValue(String.class);

         //String taggg = dataSnapshot.child(concat).child("asset_tag").getValue(String.class);
                      Toast.makeText(Asset_update.this,"asset",Toast.LENGTH_LONG).show();
                        // run some code
                    Intent intent = new Intent(getApplicationContext(), Asset_update_two.class);

                    intent.putExtra("asset_tag",getting_tag);

                    startActivity(intent);
                }


                        //run some code

                   // }
                ///else {
                  ////  Toast.makeText(Asset_update.this,"qeree",Toast.LENGTH_LONG).show();
              //  }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
     /*   final String getUserEnteredlocspinners = locspinner.getSelectedItem().toString();
        final String getEnteredassetTypes = type_up.getSelectedItem().toString();

        reference = FirebaseDatabase.getInstance().getReference("Data");
        Query checkdlocation = reference.orderByChild("location").equalTo(getUserEnteredlocspinners);
     // Query checktype = reference.orderByChild("typeofasset").equalTo(getEnteredassetTypes);

        checkdlocation.addListenerForSingleValueEvent(new  ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {


                    String assettags = dataSnapshot.child(getUserEnteredlocspinners).child("typeofasset").getValue(String.class);

                    if (assettags.equals(getEnteredassetTypes)) {
                        String getting_tag = dataSnapshot.child(getUserEnteredlocspinners).child("asset_tag").getValue(String.class);
                        Intent intent = new Intent(getApplicationContext(), Asset_update_two.class);

                        intent.putExtra("asset_tag", getting_tag);

                        startActivity(intent);

                    } else
                    {
                        Toast.makeText(Asset_update.this,"Registration failed",Toast.LENGTH_SHORT).show();
                        finish();
                    }
                    }


            else {
                    Toast.makeText(Asset_update.this,"No data found",Toast.LENGTH_SHORT).show();
                    finish();

                    }

                }
            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Asset_update.this,"DB not found",Toast.LENGTH_SHORT).show();
                finish();
            }
        });*/

    }

    }
