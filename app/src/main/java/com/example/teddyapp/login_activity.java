package com.example.teddyapp;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class login_activity extends AppCompatActivity {
    EditText inputuserid, inputpassword;
    ProgressBar progressBar;
    Button btnlogin;
    public static String userid1;
    public String username,position;
    DatabaseReference UserDatabaseReference;
    FirebaseAuth firebaseAuth;
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        //    getSupportActionBar().hide();
        getSupportActionBar().setTitle("Login to Asset Teddy");
        UserDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        firebaseAuth = FirebaseAuth.getInstance();
        if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(login_activity.this,MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);
        inputuserid = (EditText) findViewById(R.id.loginemail);
        inputpassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    public void login(View v) {
        final  String userid = inputuserid.getText().toString();
        userid1 = userid;
        final String password = inputpassword.getText().toString();
        if (TextUtils.isEmpty(userid)) {
            Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        isShow();
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(userid, password)
                .addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);

                            startActivity(new Intent(getApplicationContext(), MainActivity.class));

                            Toast.makeText(login_activity.this,"Success!",Toast.LENGTH_LONG).show();

                            } else
                                {
                            Toast.makeText(login_activity.this, "email or password incorrect", Toast.LENGTH_LONG).show();


                            }


                    }
                });


    }
    public void isShow()
    {
        final String email =inputuserid.getText().toString();

        Query query = UserDatabaseReference.orderByChild("email").equalTo(email);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (dataSnapshot.exists()) {
                    for (DataSnapshot datas : dataSnapshot.getChildren()) {
                         username = datas.child("name").getValue(String.class);
                        position  = datas.child("position").getValue(String.class);
                        Toast.makeText(login_activity.this, username, Toast.LENGTH_SHORT).show();
                        Toast.makeText(login_activity.this, position, Toast.LENGTH_SHORT).show();
                        SharedPreferences sharedPreferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
                        sharedPreferences.edit().putString("user_name", username).apply();
                        sharedPreferences.edit().putString("user_position", position).apply();


//
                    }






                } else {

                     Toast.makeText(login_activity.this, "Welcome", Toast.LENGTH_SHORT).show();


                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
              Toast.makeText(login_activity.this, "DB not found", Toast.LENGTH_SHORT).show();
                finish();
            }
        });
    }


}
