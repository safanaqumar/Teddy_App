package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.webkit.WebView;
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
    Button generate,monitor, cpu,mouse,keyboard,phone,headset,projector,tv,modem,printer;
    public String assetType;


    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_report);
        generate=(Button)findViewById(R.id.generate);
        cpu=(Button)findViewById(R.id.cpu);
        mouse=(Button)findViewById(R.id.mouse);
        keyboard=(Button)findViewById(R.id.keyboard);
        phone=(Button)findViewById(R.id.phone);
        headset=(Button)findViewById(R.id.headset);
        projector=(Button)findViewById(R.id.projector);
        tv=(Button)findViewById(R.id.tv);
        modem=(Button)findViewById(R.id.modem);
        printer=(Button)findViewById(R.id.printer);
        monitor=(Button)findViewById(R.id.monitor);

        monitor.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="MONITOR";
                monitor.setBackgroundColor(Color.LTGRAY);
                cpu.setEnabled(false);
                mouse.setEnabled(false);
                keyboard.setEnabled(false);
                phone.setEnabled(false);
                headset.setEnabled(false);
                projector.setEnabled(false);
                tv.setEnabled(false);
                printer.setEnabled(false);
                modem.setEnabled(false);


            }
        });

        cpu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="CPU";
                cpu.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                mouse.setEnabled(false);
                keyboard.setEnabled(false);
                phone.setEnabled(false);
                headset.setEnabled(false);
                projector.setEnabled(false);
                tv.setEnabled(false);
                printer.setEnabled(false);
                modem.setEnabled(false);



            }
        });
        mouse.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="MOUSE";
                mouse.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                cpu.setEnabled(false);
                keyboard.setEnabled(false);
                phone.setEnabled(false);
                headset.setEnabled(false);
                projector.setEnabled(false);
                tv.setEnabled(false);
                printer.setEnabled(false);
                modem.setEnabled(false);

            }
        });
        keyboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="KEYBOARD";
                keyboard.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                cpu.setEnabled(false);
                mouse.setEnabled(false);
                phone.setEnabled(false);
                headset.setEnabled(false);
                projector.setEnabled(false);
                tv.setEnabled(false);
                printer.setEnabled(false);
                modem.setEnabled(false);
            }
        });
        phone.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="PHONE";
                phone.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                cpu.setEnabled(false);
                mouse.setEnabled(false);
                keyboard.setEnabled(false);
                headset.setEnabled(false);
                projector.setEnabled(false);
                tv.setEnabled(false);
                modem.setEnabled(false);
                printer.setEnabled(false);
            }
        });
        headset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="HEADSET";
                headset.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                cpu.setEnabled(false);
                mouse.setEnabled(false);
                keyboard.setEnabled(false);
                phone.setEnabled(false);
                projector.setEnabled(false);
                tv.setEnabled(false);
                printer.setEnabled(false);
                modem.setEnabled(false);
            }
        });
        projector.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="PROJECTOR";
                projector.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                cpu.setEnabled(false);
                mouse.setEnabled(false);
                keyboard.setEnabled(false);
                phone.setEnabled(false);
                headset.setEnabled(false);
                tv.setEnabled(false);
                printer.setEnabled(false);
                modem.setEnabled(false);
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="TV";
                tv.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                cpu.setEnabled(false);
                mouse.setEnabled(false);
                keyboard.setEnabled(false);
                phone.setEnabled(false);
                headset.setEnabled(false);
                projector.setEnabled(false);
                printer.setEnabled(false);
                modem.setEnabled(false);
            }
        });
        modem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="MODEM";
                modem.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                cpu.setEnabled(false);
                mouse.setEnabled(false);
                keyboard.setEnabled(false);
                phone.setEnabled(false);
                headset.setEnabled(false);
                projector.setEnabled(false);
                printer.setEnabled(false);
            }
        });
        printer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assetType="PRINTER";
                printer.setBackgroundColor(Color.LTGRAY);
                monitor.setEnabled(false);
                cpu.setEnabled(false);
                mouse.setEnabled(false);
                keyboard.setEnabled(false);
                phone.setEnabled(false);
                headset.setEnabled(false);
                projector.setEnabled(false);
                tv.setEnabled(false);
                modem.setEnabled(false);
            }
        });
        generate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });






    }


    }

