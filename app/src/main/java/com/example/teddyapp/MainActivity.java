package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.os.Bundle;



import android.view.View;
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

public class MainActivity extends AppCompatActivity {


    public  CardView logout_cv,Add_asset_cv,update_cv,Track_cv,Report_cv,registeruser_cv,user_profile_cv,latest_activities_cv;
    public TextView textviewwelcome ;
    DatabaseReference UserDatabaseReference;
    SharedPreferences sharedPreferences;
    public String position,name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textviewwelcome=(TextView)findViewById(R.id.welcometxt);


      sharedPreferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        UserDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
       name= sharedPreferences.getString("user_name", "");
        position= sharedPreferences.getString("user_position", "");
        textviewwelcome.setText(name);












      //  isDisplay();


        Add_asset_cv = (CardView) findViewById(R.id.Add_asset_cv);
        update_cv = (CardView) findViewById(R.id.update_cv);
        Track_cv = (CardView) findViewById(R.id.track_cv);
        Report_cv = (CardView) findViewById(R.id.report_cv);
        registeruser_cv = (CardView) findViewById(R.id.register_cv);
        user_profile_cv = (CardView) findViewById(R.id.profile_cv);
        latest_activities_cv = (CardView) findViewById(R.id.latest_actvites_cv);
        logout_cv =(CardView) findViewById(R.id.logout_cv);
        if (position.equals("Compilance"))
        {
              registeruser_cv.setBackgroundColor(Color.LTGRAY);
              registeruser_cv.setEnabled(false);
            user_profile_cv.setBackgroundColor(Color.LTGRAY);
            user_profile_cv.setEnabled(false);
            latest_activities_cv.setBackgroundColor(Color.LTGRAY);
            latest_activities_cv.setEnabled(false);
            Add_asset_cv.setBackgroundColor(Color.LTGRAY);
            Add_asset_cv.setEnabled(false);
            update_cv.setBackgroundColor(Color.LTGRAY);
            update_cv.setEnabled(false);


        }else if (position.equals("Technical Support Team"))
        {

            registeruser_cv.setBackgroundColor(Color.LTGRAY);
            registeruser_cv.setEnabled(false);
            user_profile_cv.setBackgroundColor(Color.LTGRAY);
            user_profile_cv.setEnabled(false);
            latest_activities_cv.setBackgroundColor(Color.LTGRAY);
            latest_activities_cv.setEnabled(false);
            Report_cv.setBackgroundColor(Color.LTGRAY);
            Report_cv.setEnabled(false);

        }



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

                startActivity(i);
            }
        });


    }

    @Override
    public void onBackPressed() {
        Intent startMain = new Intent(Intent.ACTION_MAIN);
        startMain.addCategory(Intent.CATEGORY_HOME);
        startMain.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        startActivity(startMain);
    }
    /*  public void isDisplay() {
        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        String uid = user.getUid();
        Query query = UserDatabaseReference.child(position).child(uid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                if (dataSnapshot.exists()) {

                    String username = dataSnapshot.child("name").getValue(String.class);
                   //textviewwelcome.setText(username+position);



                } else {
                  //  Toast.makeText(MainActivity.this, "No data found", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(MainActivity.this, "DB not found", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }*/

    }


