package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompatSideChannelService;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teddyapp.usersdatabase.Admin;
import com.example.teddyapp.usersdatabase.Compilance;
import com.example.teddyapp.usersdatabase.Technical;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class AddUser extends AppCompatActivity {

    public Button registerButton;
    public ProgressBar progressBar;
    public Spinner UserGender, UserPosition;
    public EditText UserName, UserEmail, UserContact, UserAddress, UserID, UserPass, UserConPass;

    DatabaseReference AdminDatabaseReference;
    DatabaseReference TechnicalDatabaseReference;
    DatabaseReference CompilanceDatabaseReference;
    DatabaseReference UserDatabaseReference;
    FirebaseAuth firebaseAuth;
    public int check ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        UserName = (EditText) findViewById(R.id.username);
        UserEmail = (EditText) findViewById(R.id.user_email);
        UserContact = (EditText) findViewById(R.id.user_contact);
        UserAddress = (EditText) findViewById(R.id.user_address);
        UserID = (EditText) findViewById(R.id.user_id);
        UserPass = (EditText) findViewById(R.id.user_password);
        UserConPass = (EditText) findViewById(R.id.user_confirmpass);
        UserGender = (Spinner) findViewById(R.id.user_gender);
        UserPosition = (Spinner) findViewById(R.id.user_position);
        progressBar = (ProgressBar) findViewById(R.id.loadingbar);
        registerButton = (Button) findViewById(R.id.user_btn);

        AdminDatabaseReference = FirebaseDatabase.getInstance().getReference("admin");
        UserDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        TechnicalDatabaseReference = FirebaseDatabase.getInstance().getReference("technical");
        CompilanceDatabaseReference = FirebaseDatabase.getInstance().getReference("compilance");
        firebaseAuth = FirebaseAuth.getInstance();
        ArrayAdapter<String> spinadapter = new ArrayAdapter<>(AddUser.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserGender.setAdapter(spinadapter);


        ArrayAdapter<String> spinadapter1 = new ArrayAdapter<>(AddUser.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.positions));
        spinadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserPosition.setAdapter(spinadapter1);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String USERNAME = UserName.getText().toString();

                final String USEREMAIL = UserEmail.getText().toString();
                final String USERCONTACT = UserContact.getText().toString();
                final String USERADDRESS = UserAddress.getText().toString();
                final String USERID = UserID.getText().toString().toLowerCase();
                final String USERPASS = UserPass.getText().toString();
                final String USERCONPASS = UserConPass.getText().toString();
                final String USERGENDER = UserGender.getSelectedItem().toString();
                final String USERPOSITION = UserPosition.getSelectedItem().toString();
                if (USERPOSITION.equals("Admin")) {
                    check = 1;
                } else if (USERPOSITION.equals("Technical Support Team")) {
                    check = 2;
                } else if (USERPOSITION.equals("Compilance")) {
                    check = 3;
                }


                if (TextUtils.isEmpty(USERNAME)) {
                    Toast.makeText(AddUser.this, " ENTER NAME", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(USEREMAIL)) {
                    Toast.makeText(AddUser.this, " ENTER EMAIL", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(USERCONTACT)) {
                    Toast.makeText(AddUser.this, " ENTER CONTACT", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(USERADDRESS)) {
                    Toast.makeText(AddUser.this, " ENTER ADDRESS", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(USERID)) {
                    Toast.makeText(AddUser.this, " ENTER USERID", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(USERPASS)) {
                    Toast.makeText(AddUser.this, " ENTER PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(USERCONPASS)) {
                    Toast.makeText(AddUser.this, " ENTER CONFIRM PASSWORD", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (USERPASS.length() < 6) {
                    UserPass.setError("Password should be of minimum 6 characters long");
                }


                if (USERPASS.equals(USERCONPASS))
                {
                    if (check == 1) {
                        Admin adminuser = new Admin(USERNAME,
                                USERPASS,
                                USERGENDER,
                                USEREMAIL,
                                USERCONTACT,
                                USERPOSITION,
                                USERADDRESS,
                                USERID
                        );

                        FirebaseDatabase.getInstance().getReference("users").child("admin")
                                .child(USERID)
                                .setValue(adminuser).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddUser.this, "registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddUser.this, "registration complete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddUser.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });


                    } else if (check == 3) {
                        Compilance compilanceuser = new Compilance(USERNAME,
                                USERPASS,
                                USERGENDER,
                                USEREMAIL,
                                USERCONTACT,
                                USERPOSITION,
                                USERADDRESS,
                                USERID
                        );

                        FirebaseDatabase.getInstance().getReference("compilance")
                                .child(USERID)
                                .setValue(compilanceuser).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddUser.this, "registration failed", Toast.LENGTH_SHORT).show();

                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddUser.this, "registration complete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddUser.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    } else if (check == 2) {
                        Technical technicaluser = new Technical(USERNAME,
                                USERPASS,
                                USERGENDER,
                                USEREMAIL,
                                USERCONTACT,
                                USERPOSITION,
                                USERADDRESS,
                                USERID
                        );

                        FirebaseDatabase.getInstance().getReference("technical")
                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                .setValue(technicaluser).addOnFailureListener(new OnFailureListener() {
                            @Override
                            public void onFailure(@NonNull Exception e) {
                                Toast.makeText(AddUser.this, "registration failed", Toast.LENGTH_SHORT).show();
                            }
                        }).addOnSuccessListener(new OnSuccessListener<Void>() {
                            @Override
                            public void onSuccess(Void aVoid) {
                                Toast.makeText(AddUser.this, "registration complete", Toast.LENGTH_SHORT).show();
                                Intent intent = new Intent(AddUser.this, MainActivity.class);
                                startActivity(intent);
                            }
                        });
                    }

                }else{
                    UserConPass.setError("Incorrect Password");
                }
            }



                                // ...




        });
    }
}