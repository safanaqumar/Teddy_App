package com.example.teddyapp;

import android.content.Intent;
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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class login_activity extends AppCompatActivity {


     EditText inputuserid,inputpassword;
     ProgressBar progressBar;
     Button btnlogin;
    public int check ;
    Spinner UserPosition;
    public String pass1;



    DatabaseReference AdminDatabaseReference;
    DatabaseReference TechnicalDatabaseReference;
    DatabaseReference CompilanceDatabaseReference;
    DatabaseReference UserDatabaseReference;
    FirebaseAuth firebaseAuth;



    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    //    getSupportActionBar().hide();
        getSupportActionBar().setTitle("Login to Asset Teddy");

        AdminDatabaseReference = FirebaseDatabase.getInstance().getReference("admin");
        UserDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        TechnicalDatabaseReference = FirebaseDatabase.getInstance().getReference("technical");
        CompilanceDatabaseReference = FirebaseDatabase.getInstance().getReference("compilance");
        firebaseAuth = FirebaseAuth.getInstance();



        // auth = FirebaseAuth.getInstance();
       //if (firebaseAuth.getCurrentUser() != null){
      //      startActivity(new Intent(login_activity.this,MainActivity.class));
       //     finish();
      //  }
        setContentView(R.layout.activity_login);
        inputuserid = (EditText)findViewById(R.id.username);
        inputpassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.loading);
        UserPosition = (Spinner) findViewById(R.id.loginuser_position);
        ArrayAdapter<String> spinadapter1 = new ArrayAdapter<>(login_activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.positions));
        spinadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserPosition.setAdapter(spinadapter1);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }
    public void login(View v) {
        final String userid = inputuserid.getText().toString().toLowerCase();
        final String password = inputpassword.getText().toString();
        final String position = UserPosition.getSelectedItem().toString();
        pass1=password;
        if (position.equals("Admin")) {
            check = 1;
        } else if (position.equals("Technical Support Team")) {
            check = 2;
        } else if (position.equals("Compilance")) {
            check = 3;
        }
        if (TextUtils.isEmpty(userid)) {
            Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }

               // progressBar.setVisibility(View.VISIBLE);


        UserDatabaseReference.child("admin").child(userid).addListenerForSingleValueEvent(valueEventListener);


        UserDatabaseReference.child("Technical Support Team").child(userid).addListenerForSingleValueEvent(valueEventListener);

        UserDatabaseReference.child("Compilance").child(userid).addListenerForSingleValueEvent(valueEventListener);






    }

    ValueEventListener valueEventListener = new ValueEventListener() {
        @Override
        public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
            if (dataSnapshot.exists())
            {
                String pass= dataSnapshot.child("password").getValue(String.class);
                if (pass.equals(pass1))
                {
                    Intent i = new Intent(login_activity.this,MainActivity.class);
                    startActivity(i);
                }else {
                Toast.makeText(getApplicationContext(), "wrong password!", Toast.LENGTH_SHORT).show();

            }

            }
            else {
                Toast.makeText(getApplicationContext(), "record not found", Toast.LENGTH_SHORT).show();


            }
        }

        @Override
        public void onCancelled(@NonNull DatabaseError databaseError) {
            Toast.makeText(getApplicationContext(), databaseError.toString(), Toast.LENGTH_SHORT).show();

        }
    };



}
