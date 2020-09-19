package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Asset_update_two extends AppCompatActivity {
    DatabaseReference reference;

    public static String itemval;


  public static ListView listView;
    TextView textView;
    public  static Object o;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_asset_update_two);
        listView = (ListView) findViewById(R.id.listvviewtwo);
        final ArrayList<String> myList = (ArrayList<String>) getIntent().getSerializableExtra("mylist");

        final ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, myList);
        listView.setAdapter(stringArrayAdapter);
        stringArrayAdapter.notifyDataSetChanged();
listView.setOnItemClickListener(new ListView.OnItemClickListener() {
    @Override

    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

        itemval = (String) listView.getItemAtPosition( position );
        Intent intent1= new Intent(Asset_update_two.this,Asset_update_three.class);

        //intent1.putExtra("",itemValue);
        //Toast.makeText(Asset_update_two.this,Toast.LENGTH_LONG).show();

        startActivity(intent1);
        } });
}
    }

