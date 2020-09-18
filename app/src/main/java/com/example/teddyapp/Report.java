package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.database.core.Repo;

import org.eazegraph.lib.charts.PieChart;

import java.util.zip.DataFormatException;

public class Report extends AppCompatActivity {
 public Button conbtn;
 public Spinner spinner;
 PieChart pieChart;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        spinner = (Spinner) findViewById(R.id.type);
        ArrayAdapter<String> spinerstatusdaptertype = new ArrayAdapter<>(Report.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.type));
        spinerstatusdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(spinerstatusdaptertype);

        conbtn =(Button)findViewById(R.id.conbtn);

        conbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                gettype();




            }
        });
    }
    private void gettype() {

        final String getUserEnteredtype = spinner.getSelectedItem().toString();
       DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Data");
        Query checktype = reference.orderByChild("typeofasset").equalTo(getUserEnteredtype);
checktype.addListenerForSingleValueEvent(new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        if (dataSnapshot.exists()) {
            String taggg = dataSnapshot.child("typeofasset").getValue(String.class);

            Intent intent = new Intent(Report.this, Report_of_monitor.class);
            startActivity(intent);
        }else
            Toast.makeText(Report.this,"No data found",Toast.LENGTH_SHORT).show();
    }
    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});

    }
}
