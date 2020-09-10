package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.google.firebase.auth.FirebaseAuth;

public class MainActivity extends AppCompatActivity {


    private  CardView logout_cv,Add_asset_cv,update_cv,Track_cv,Report_cv,registeruser_cv,user_profile_cv,latest_activities_cv;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

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
        Intent intent = new Intent(MainActivity.this,Asset_update.class);
        startActivity(intent);
    }
});

        Add_asset_cv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Add_new_asset.class);
                startActivity(intent);
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
    }
}
