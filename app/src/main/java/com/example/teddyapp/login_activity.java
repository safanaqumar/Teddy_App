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
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.EventListener;

public class login_activity extends AppCompatActivity {


    EditText inputuserid, inputpassword;
    ProgressBar progressBar;
    Button btnlogin;
    public int check;
  public static   Spinner UserPosition;
    public String pass1;
    public static String userid1;
    public String position1;
    public String usertype;


    DatabaseReference AdminDatabaseReference;
    DatabaseReference TechnicalDatabaseReference;
    DatabaseReference CompilanceDatabaseReference;
    DatabaseReference UserDatabaseReference;
    FirebaseAuth firebaseAuth;
    SharedPreferences sharedPreferences;
    public String position;


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
        // auth = FirebaseAuth.getInstance();
        //if (firebaseAuth.getCurrentUser() != null){
        //      startActivity(new Intent(login_activity.this,MainActivity.class));
        //     finish();
        //  }
        setContentView(R.layout.activity_login);
        inputuserid = (EditText) findViewById(R.id.loginemail);
        inputpassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.loading);

     //   position= sharedPreferences.getString("user_position", "");
      //  UserPosition = (Spinner) findViewById(R.id.loginuser_position);
      //  ArrayAdapter<String> spinadapter1 = new ArrayAdapter<>(login_activity.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.positions));
      //  spinadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
      //  UserPosition.setAdapter(spinadapter1);
        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                login(v);
            }
        });
    }

    public void login(View v) {
        final  String userid = inputuserid.getText().toString().toLowerCase().trim();
        userid1 = userid;
        final String password = inputpassword.getText().toString();

      /*  final String position = UserPosition.getSelectedItem().toString();
        position1 = position;
        pass1 = password;
        if (position.equals("Admin")) {
            check = 1;
            usertype = "Admin";
        } else if (position.equals("Technical Support Team")) {
            check = 2;
            usertype = "Technical Support Team";
        } else if (position.equals("Compilance")) {
            check = 3;
            usertype = "Compilance";
        }*/
        if (TextUtils.isEmpty(userid)) {
            Toast.makeText(getApplicationContext(), "Enter Username!", Toast.LENGTH_SHORT).show();
            return;
        }
        if (TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Enter password!", Toast.LENGTH_SHORT).show();
            return;
        }
        progressBar.setVisibility(View.VISIBLE);
        firebaseAuth.signInWithEmailAndPassword(userid, password)
                .addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (!task.isSuccessful()) {
                            progressBar.setVisibility(View.GONE);
                            startActivity(new Intent(getApplicationContext(), MainActivity.class));
                            Toast.makeText(login_activity.this, "Authentication failed", Toast.LENGTH_SHORT);
                            if (password.length()< 6){
                                inputpassword.setError(getString(R.string.Minpassword));
                            } else {
                                Toast.makeText(login_activity.this,"Sucess!",Toast.LENGTH_LONG).show();

                            }

                        } else {

                            Intent intent = new Intent(login_activity.this,MainActivity.class);
                            intent.putExtra("Welcome username",userid1);
                            startActivity(intent);
                            finish();

                            Toast.makeText(login_activity.this, "email or password incorrect", Toast.LENGTH_SHORT);

                        }
                    }
                });

    }
}
