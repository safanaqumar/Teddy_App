package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

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
        TechnicalDatabaseReference = FirebaseDatabase.getInstance().getReference("technical");
        CompilanceDatabaseReference = FirebaseDatabase.getInstance().getReference("compilance");
        firebaseAuth = FirebaseAuth.getInstance();
        ArrayAdapter<String> spinadapter = new ArrayAdapter<>(AddUser.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserGender.setAdapter(spinadapter);


        ArrayAdapter<String> spinadapter1 = new ArrayAdapter<>(AddUser.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.positions));
        spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        UserPosition.setAdapter(spinadapter1);

        registerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                final String USERNAME = UserName.getText().toString();
                final String USEREMAIL = UserEmail.getText().toString();
                final String USERCONTACT = UserContact.getText().toString();
                final String USERADDRESS = UserAddress.getText().toString();
                final String USERID = UserID.getText().toString();
                final String USERPASS = UserPass.getText().toString();
                final String USERCONPASS = UserConPass.getText().toString();
                final String USERGENDER = UserGender.getSelectedItem().toString();
                final String USERPOSITION = UserPosition.getSelectedItem().toString();
                if (USERPOSITION.equals("Admin")) {
                    check =1;
                } else if (USERPOSITION.equals("Technical Support Team")) {
                    check = 2;
                } else if (USERPOSITION.equals("Compilance")){
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
                if (USERPASS.length()< 6){
                    UserPass.setError(getString(R.string.Minpassword));
                }


                firebaseAuth.createUserWithEmailAndPassword(USEREMAIL, USERPASS)
                        .addOnCompleteListener(AddUser.this, new OnCompleteListener<AuthResult>() {
                            @Override
                            public void onComplete(@NonNull Task<AuthResult> task) {
                                if ((!task.isSuccessful()))  {

                                    if (check==1) {
                                        Admin adminuser = new Admin(USERNAME,
                                                USERGENDER,
                                                USEREMAIL,
                                                USERCONTACT,
                                                USERPOSITION,
                                                USERADDRESS,
                                                USERID
                                        );

                                        FirebaseDatabase.getInstance().getReference("admin")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(adminuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(AddUser.this, "registration complete", Toast.LENGTH_SHORT).show();
                                                //startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                                            }

                                        });
                                    } else if (check==3)
                                    {
                                        Compilance compilanceuser = new Compilance(USERNAME,
                                                USERGENDER,
                                                USEREMAIL,
                                                USERCONTACT,
                                                USERPOSITION,
                                                USERADDRESS,
                                                USERID
                                        );

                                        FirebaseDatabase.getInstance().getReference("compilance")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(compilanceuser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(AddUser.this, "registration complete", Toast.LENGTH_SHORT).show();
                                                //startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                                            }

                                        });
                                    }
                                    else if (check==2)
                                    {
                                        Technical technicaluser = new Technical(USERNAME,
                                                USERGENDER,
                                                USEREMAIL,
                                                USERCONTACT,
                                                USERPOSITION,
                                                USERADDRESS,
                                                USERID
                                        );

                                        FirebaseDatabase.getInstance().getReference("technical")
                                                .child(FirebaseAuth.getInstance().getCurrentUser().getUid())
                                                .setValue(technicaluser).addOnCompleteListener(new OnCompleteListener<Void>() {
                                            @Override
                                            public void onComplete(@NonNull Task<Void> task) {
                                                Toast.makeText(AddUser.this, "registration complete", Toast.LENGTH_SHORT).show();
                                                //startActivity(new Intent(getApplicationContext(), MapsActivity.class));

                                            }

                                        });
                                    }

                                } else {
                                    Toast.makeText(AddUser.this, "Registration failed", Toast.LENGTH_SHORT).show();
                                    finish();
                                }

                                // ...
                            }
                        });

            }
        });
    }
}