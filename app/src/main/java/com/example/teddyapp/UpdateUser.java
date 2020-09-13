package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.example.teddyapp.usersdatabase.Technical;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class UpdateUser extends AppCompatActivity {

    ListView mylistView;
    DatabaseReference DATABASEREF;
    ArrayList<String> myArrayList= new ArrayList<>();
    DatabaseReference Technical;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);
        mylistView = findViewById(R.id.listview_id);
        final ArrayAdapter myArrayAdapter = new ArrayAdapter<String>(UpdateUser.this, android.R.layout.simple_list_item_1, myArrayList);
        Technical=getInstance().getReference().child("admin");
        Query query = Technical.orderByChild("name");
        mylistView.setAdapter(myArrayAdapter);
        query.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                String value = dataSnapshot.getValue(String.class);
                myArrayList.add(value);
                myArrayAdapter.notifyDataSetChanged();


            }

            @Override
            public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
                myArrayAdapter.notifyDataSetChanged();

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

    }