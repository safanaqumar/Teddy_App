package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.annotation.VisibleForTesting;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.provider.SearchRecentSuggestions;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

public class Asset_update_two extends AppCompatActivity {
    DatabaseReference reference;
    String[] mString;
ListView listView;
//String [] name1 = {};
    ArrayList<String> name = new ArrayList<String>();

ArrayAdapter<String> string_Adapter;
TextView textView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_update_two);

        string_Adapter = new ArrayAdapter<String>(Asset_update_two.this, android.R.layout.simple_list_item_1, name);
        listView = (ListView) findViewById(R.id.listvviewtwo);
//name1 = new String[]{"asset_tag"};

        //reference = FirebaseDatabase.getInstance().getReference("Data");

        final Intent intent = getIntent();

        final String tagg = intent.getStringExtra("asset_tag");


      /* for (int i = 0 ; i < name.size() ; i++){
           tagg[i]
       }*/

        //String
      //  name.add(tagg);
        // String tagg1 = tagg.getString();

        //textView.setText(tagg);
        //   textView.findViewById(R.id.testing);

        listView.setAdapter(string_Adapter);

        // string_Adapter.notifyDataSetChanged();

        //String
        name.add(tagg);

        listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Asset_update_two.this,Asset_update_three.class);
          startActivity(intent1);
            }
        });
        /*reference.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
      //  String value = dataSnapshot.getValue(Asset_update.class).toString();
        name.add(tagg);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        string_Adapter.notifyDataSetChanged();

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
       *//*  String taggg =  intent.getStringExtra("asset_tag");
        String name2 = String.valueOf(taggg);
        name.add(name2);*//*



    }

 *//*   @Override
    protected void onResume() {
        super.onResume();
    }*//*

         *//* public void showAllassetData () {
            Intent intent = getIntent();
            String taggg = intent.getStringExtra("asset_tag");

            arrayList.add(taggg);

            string_Adapter.notifyDataSetChanged();


    }*/

    }
}
