package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.eazegraph.lib.charts.PieChart;

public class Report_of_monitor extends AppCompatActivity {
PieChart pieChart;
TextView total,Dispose,Faulty,Available,Assigned;
Button backbtn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report_of_monitor);

       total =(TextView) findViewById(R.id.total);
       Dispose = (TextView) findViewById(R.id.dispose);
       Faulty = (TextView) findViewById(R.id.Faulty);
       Available = (TextView) findViewById(R.id.Available);
       Assigned = (TextView) findViewById(R.id.Assigned);

backbtn = (Button) findViewById(R.id.backbtn);
backbtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {

        Intent intent = new Intent(Report_of_monitor.this,Report.class);
        startActivity(intent);
    }
});
    }


}
