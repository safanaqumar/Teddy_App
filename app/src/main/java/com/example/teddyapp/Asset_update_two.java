package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;

public class Asset_update_two extends AppCompatActivity {
    DatabaseReference reference;
ListView listView;
ArrayList<String> arrayList = new ArrayList<>();
ArrayAdapter<String> string_Adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_update_two);
        reference = FirebaseDatabase.getInstance().getReference("Data");
        listView  = (ListView) findViewById(R.id.listvviewtwo);

        string_Adapter = new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,arrayList);
         listView.setAdapter(string_Adapter);
          reference.addChildEventListener(new ChildEventListener() {
              @Override
              public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                  showAllassetData();
              }

              @Override
              public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

              }

              @Override
              public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

              }

              @Override
              public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

              }

              @Override
              public void onCancelled(@NonNull DatabaseError databaseError) {

              }
          });

    }

    private void showAllassetData() {
        Intent intent = getIntent();
        String asset_Tagg = intent.getStringExtra("asset_tag");
        arrayList.add(asset_Tagg);



    }
}
