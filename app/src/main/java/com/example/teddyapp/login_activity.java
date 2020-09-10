package com.example.teddyapp;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;

public class login_activity extends AppCompatActivity {


    private EditText inputemail,inputpassword;
    private ProgressBar progressBar;
    private Button btnlogin;

private FirebaseAuth firebaseAuth;

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
    int id = item.getItemId();
    switch (id){
    case R.id.sign:
Intent intent = new Intent(this,Signup.class);
this.startActivity(intent);
        break;
        default:
            return super.onOptionsItemSelected(item);
}
return true;
    }

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    //    getSupportActionBar().hide();
        getSupportActionBar().setTitle("Login to Asset Teddy");



        //Get firebase auth ins
       firebaseAuth = FirebaseAuth.getInstance();
        // auth = FirebaseAuth.getInstance();
       if (firebaseAuth.getCurrentUser() != null){
            startActivity(new Intent(login_activity.this,MainActivity.class));
            finish();
        }
        setContentView(R.layout.activity_login);

        inputemail = (EditText)findViewById(R.id.username);
        inputpassword = (EditText) findViewById(R.id.password);
        btnlogin = (Button) findViewById(R.id.login);
        progressBar = (ProgressBar) findViewById(R.id.loading);


        btnlogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                final String email= inputemail.getText().toString();
                final String password = inputpassword.getText().toString();
                if (TextUtils.isEmpty(email)){
                    Toast.makeText(getApplicationContext(),"Enter Username!",Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(password)){
                    Toast.makeText(getApplicationContext(),"Enter password!",Toast.LENGTH_SHORT).show();
                    return;
                }

                progressBar.setVisibility(View.VISIBLE);



                //authenticate user
                firebaseAuth.signInWithEmailAndPassword(email,password)
                .addOnCompleteListener(login_activity.this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        //if signin fails, display a msg to the user
                        //
                        progressBar.setVisibility(View.GONE);
                        if (!task.isSuccessful()){
                            //if there was an error
                            if (password.length()< 6){
                                inputpassword.setError(getString(R.string.Minpassword));
                            } else {
                                Toast.makeText(login_activity.this,"Authentication failed!",Toast.LENGTH_LONG).show();

                            }
                        }else {

                            Intent intent = new Intent(login_activity.this,MainActivity.class);
                            intent.putExtra("Welcome username",email);
                            startActivity(intent);
                            finish();
                        }
                    }
                });
            }
        });
    }
}
