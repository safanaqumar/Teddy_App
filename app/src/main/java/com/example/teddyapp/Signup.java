package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;

public class Signup extends AppCompatActivity {

private FirebaseAuth firebaseAuth;

    private Button registerbtn;
    private ProgressBar progressBar;

    private EditText regname,regemail,regcontactno,regaddress,regusername,regpas,regconpass;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_signup);

        regname = (EditText)findViewById(R.id.regname);
        regemail=(EditText) findViewById(R.id.regEmail);
        regcontactno=(EditText) findViewById(R.id.regcont1);
        regaddress = (EditText) findViewById(R.id.regaddress);
        regusername = (EditText) findViewById(R.id.regusername);
        regpas = (EditText) findViewById(R.id.regpass);
        regconpass = (EditText)findViewById(R.id.regconfirmpass);
        final Spinner spinner = (Spinner) findViewById(R.id.reggender);
        final Spinner spinner1= (Spinner) findViewById(R.id.regposition);
        progressBar = (ProgressBar) findViewById(R.id.loadingbar);


        ArrayAdapter<String> spinadapter = new ArrayAdapter<>(Signup.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.gender));
     spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     spinner.setAdapter(spinadapter);


        ArrayAdapter<String> spinadapter1 = new ArrayAdapter<>(Signup.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.positions));
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner1.setAdapter(spinadapter1);

        firebaseAuth = FirebaseAuth.getInstance();
    registerbtn = (Button) findViewById(R.id.registerbtn);

    registerbtn.setOnClickListener(new View.OnClickListener() {
        @Override
        public void onClick(View v) {

            String name = regname.getText().toString();
            String email = regemail.getText().toString().trim();
            String contactno = regcontactno.getText().toString();
            String Address = regaddress.getText().toString();
            String Userrname= regusername.getText().toString();
            String password = regpas.getText().toString();
            String Confirmpass = regconpass.getText().toString();
            final String Spinnerposition = spinner1.getSelectedItem().toString();
            final String Spinnergender = spinner.getSelectedItem().toString();

            if (TextUtils.isEmpty(Spinnerposition)){
                Toast.makeText(getApplicationContext(),"Please select your position!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Spinnergender)){
                Toast.makeText(getApplicationContext(),"Please select your Gender!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(contactno)){
                Toast.makeText(getApplicationContext(),"Enter Phone Number!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(name)){
                Toast.makeText(getApplicationContext(),"Enter Name!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(email)){
                Toast.makeText(getApplicationContext(),"Enter Email!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Address)){
                Toast.makeText(getApplicationContext(),"Enter Address!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Userrname)){
                Toast.makeText(getApplicationContext(),"Enter Username!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(password)){
                Toast.makeText(getApplicationContext(),"Enter Password!",Toast.LENGTH_SHORT).show();
                return;
            }
            if (TextUtils.isEmpty(Confirmpass)){
                Toast.makeText(getApplicationContext(),"Enter Password!",Toast.LENGTH_SHORT).show();
                return;
            }

            if (password.length()< 6){
                regpas.setError(getString(R.string.Minpassword));
            } else {
                Toast.makeText(Signup.this,"Authentication failed!",Toast.LENGTH_LONG).show();

            }
            if (password.length()< 6){
                regconpass.setError(getString(R.string.Minpassword));
            } else {
                Toast.makeText(Signup.this,"Authentication failed!",Toast.LENGTH_LONG).show();

            }

            progressBar.setVisibility(View.VISIBLE);

            if (password.equals(Confirmpass)){
                firebaseAuth.createUserWithEmailAndPassword(email, password)
                        .addOnCompleteListener(Signup.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                progressBar.setVisibility(View.GONE);
                                if (task.isSuccessful()) {
                                    // Sign in success, update UI with the signed-in user's information
                                    Toast.makeText(Signup.this,"Registration Sucessful",Toast.LENGTH_SHORT).show();

                                    Intent intent = new Intent(Signup.this,MainActivity.class);
                                    startActivity(intent);
                                    finish();
                                } else {
                                    // If sign in fails, display a message to the user.
                                  Toast.makeText(Signup.this,"Registration failed",Toast.LENGTH_SHORT).show();
                               finish();
                                }


                            }
                        });
            }

        }


    });


    }


}
