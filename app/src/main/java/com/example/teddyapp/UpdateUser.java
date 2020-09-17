package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teddyapp.usersdatabase.Admin;
import com.example.teddyapp.usersdatabase.Technical;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.security.Key;
import java.util.ArrayList;

import static com.google.firebase.database.FirebaseDatabase.getInstance;

public class UpdateUser extends AppCompatActivity {


    public Button registerButton;
    public ProgressBar progressBar;
    public Spinner UserGender, UserPosition;
    public EditText UserName, UserEmail, UserContact, UserAddress, UserID, UserPass, UserConPass;
    public String cuuid;


    DatabaseReference AdminDatabaseReference;
    DatabaseReference TechnicalDatabaseReference;
    DatabaseReference CompilanceDatabaseReference;
    DatabaseReference UserDatabaseReference;
   // DatabaseReference refernce;
    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        UserName = (EditText) findViewById(R.id.updateusername);
        UserEmail = (EditText) findViewById(R.id.updateuser_email);
        UserContact = (EditText) findViewById(R.id.updateuser_contact);
        UserAddress = (EditText) findViewById(R.id.updateuser_address);
        UserID = (EditText) findViewById(R.id.updateuser_id);
        UserPass = (EditText) findViewById(R.id.updateuser_password);
        UserConPass = (EditText) findViewById(R.id.updateuser_confirmpass);
        UserGender = (Spinner) findViewById(R.id.user_gender);
        UserPosition = (Spinner) findViewById(R.id.updateuser_position);
        progressBar = (ProgressBar) findViewById(R.id.loadingbar);
        registerButton = (Button) findViewById(R.id.updateuser_btn);
        Intent i = getIntent();
        final String cuid=i.getStringExtra("current_user_id");
        cuuid=cuid;
        UserID.setText(cuuid);




        final String cpos=i.getStringExtra("current_user_position");
        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showuserdata(view);


            }
        });
        }


          public void showuserdata(View view) {
                final Admin admin = new Admin();
                admin.getUserid();
                mAuthListener = new FirebaseAuth.AuthStateListener() {
                    @Override
                    public void onAuthStateChanged(@NonNull FirebaseAuth firebaseAuth) {
                        FirebaseUser currentUser = FirebaseAuth.getInstance().getCurrentUser();
                        final FirebaseUser user = firebaseAuth.getCurrentUser();
                        if (user != null) {
                            Query refernce= UserDatabaseReference.orderByChild("userid").equalTo(cuuid);
                            refernce.addListenerForSingleValueEvent(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    if (dataSnapshot.exists()) {
                                        for (DataSnapshot datas : dataSnapshot.getChildren()) {
                                            String EMAIL = datas.child("email").getValue(String.class);
                                            UserEmail.setText(EMAIL);
                                        }
                                    } else {
                                        Toast.makeText(getApplicationContext(), "nope!", Toast.LENGTH_SHORT).show();
                                    }


                                }


                                @Override
                                public void onCancelled(@NonNull DatabaseError databaseError) {

                                }
                            });
                            Toast.makeText(getApplicationContext(), "Enter email address!" + user.getEmail(), Toast.LENGTH_SHORT).show();
                        } else {
                            //   Log.d(TAG, "onAuthStateChanged:signed_out");
                            Toast.makeText(getApplicationContext(), "Successfuly signed out", Toast.LENGTH_SHORT).show();
                        }
                    }
                };
            }

    }