package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private  CardView logout_cv,Add_asset_cv,update_cv,Track_cv,Report_cv,registeruser_cv,user_profile_cv,latest_activities_cv;
    private TextView textviewwelcome;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Intent i = getIntent();
        final String cuid=i.getStringExtra("current_user_id");
        final String cpos=i.getStringExtra("current_user_position");



textviewwelcome = (TextView) findViewById(R.id.welcometxt);

       textviewwelcome.setText("Welcome Back!" +  cuid + cpos);
        Add_asset_cv = (CardView) findViewById(R.id.Add_asset_cv);
        update_cv = (CardView) findViewById(R.id.update_cv);
        Track_cv = (CardView) findViewById(R.id.track_cv);
        Report_cv = (CardView) findViewById(R.id.report_cv);
        registeruser_cv = (CardView) findViewById(R.id.register_cv);
        user_profile_cv = (CardView) findViewById(R.id.profile_cv);
        latest_activities_cv = (CardView) findViewById(R.id.latest_actvites_cv);
        logout_cv =(CardView) findViewById(R.id.logout_cv);
        update_cv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        Intent intent3 = new Intent(MainActivity.this,Asset_update.class);
        startActivity(intent3);
        }
      });

        Track_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent2 = new Intent(MainActivity.this,Track_asset.class);
                startActivity(intent2);
            }
        });


        Report_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(MainActivity.this,Report.class);
                startActivity(intent1);
            }
        });


        Add_asset_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent4 = new Intent(MainActivity.this,Add_new_asset.class);
                startActivity(intent4);
            }
        });

        logout_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseAuth.getInstance().signOut();
                Intent intent = new Intent(MainActivity.this,login_activity.class);
                startActivity(intent);


            }
        });
        latest_activities_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent5 = new Intent(MainActivity.this, UpdateUser.class);
                startActivity(intent5);
            }
        });
        registeruser_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddUser.class);
                startActivity(intent);
            }
        });


        user_profile_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),UpdateUser.class);
                i.putExtra("current_user_id",cuid);
                i.putExtra("current_user_position",cpos);
                startActivity(i);
            }
        });

    }
}
