package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.content.SharedPreferences;
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


    public Button updateButton,removeButton;
    public ProgressBar progressBar;
    public Spinner UserGender, UserPosition;
    public EditText UserName, UserEmail, UserContact, UserAddress, UserID, UserPass, UserConPass;
    public String cuuid;
    SharedPreferences sharedPreferences;
    public String position;
    public int genderposition;
    public int designationposition;
    public String name;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
   public String uid = user.getUid();



    DatabaseReference UserDatabaseReference;

    FirebaseAuth firebaseAuth;
    FirebaseAuth.AuthStateListener mAuthListener;
    String updateusername;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_user);

        sharedPreferences= getApplicationContext().getSharedPreferences("MyPref", MODE_PRIVATE);
        UserDatabaseReference = FirebaseDatabase.getInstance().getReference("users");
        position= sharedPreferences.getString("user_position", "");


      //  position= sharedPreferences.getString("user_position", "");
       // UserDatabaseReference=FirebaseDatabase.getInstance().getReference("users");
      // Toast.makeText(getApplicationContext(),position,Toast.LENGTH_SHORT).show();

        UserName = (EditText) findViewById(R.id.updateusername);
        UserEmail = (EditText) findViewById(R.id.updateuser_email);
        UserContact = (EditText) findViewById(R.id.updateuser_contact);
        UserAddress = (EditText) findViewById(R.id.updateuser_address);
        UserID = (EditText) findViewById(R.id.updateuser_id);
        UserPass = (EditText) findViewById(R.id.updateuser_password);
        UserConPass = (EditText) findViewById(R.id.updateuser_confirmpass);
        UserGender = (Spinner) findViewById(R.id.updateuser_gender);
        UserPosition = (Spinner) findViewById(R.id.updateuser_position);
        progressBar = (ProgressBar) findViewById(R.id.loadingbar);
        updateButton = (Button) findViewById(R.id.updateuser_btn);
        removeButton=(Button) findViewById(R.id.updateuser_btn3);
        removeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(),"removed",Toast.LENGTH_LONG).show();
            }
        });

        showuserdata();





        updateButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateusername= UserName.getText().toString();
                updateData();




            }
        });
        }


          public void showuserdata() {



              Query query = UserDatabaseReference.child(uid);
              query.addListenerForSingleValueEvent(new ValueEventListener() {
                  @Override
                  public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                      if (dataSnapshot.exists()) {

                          name = dataSnapshot.child("name").getValue(String.class);
                          String gender = dataSnapshot.child("gender").getValue(String.class);
                          String email = dataSnapshot.child("email").getValue(String.class);
                          String contact = dataSnapshot.child("contactnumber").getValue(String.class);
                          String positiom = dataSnapshot.child("position").getValue(String.class);
                          String address = dataSnapshot.child("address").getValue(String.class);
                          String username = dataSnapshot.child("userid").getValue(String.class);
                          String psssword = dataSnapshot.child("password").getValue(String.class);

                          UserName.setText(name);
                          UserEmail.setText(email);
                          UserContact.setText(contact);
                          UserAddress.setText(address);
                          UserID.setText(username);
                          UserPass.setText(psssword);
                          UserConPass.setText(psssword);
                          if (gender.equals("Male"))
                          {
                              genderposition=0;
                          }
                          else {
                              genderposition=1;
                          }
                          if (positiom.equals("Admin"))
                          {
                              designationposition=0;
                          }else if(positiom.equals("Technical Support Team")){
                              designationposition=1;
                          }else
                          {
                              designationposition=2;
                          }


                          ArrayAdapter<String> spinadapter1 = new ArrayAdapter<>(UpdateUser.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.positions));
                          spinadapter1.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                          UserPosition.setAdapter(spinadapter1);
                          UserPosition.setSelection(designationposition);


                          ArrayAdapter<String> spinadapter = new ArrayAdapter<>(UpdateUser.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.gender));

                          spinadapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                          UserGender.setAdapter(spinadapter);
                          UserGender.setSelection(genderposition);






                      } else {
                          Toast.makeText(UpdateUser.this, "No data found", Toast.LENGTH_SHORT).show();


                      }

                  }

                  @Override
                  public void onCancelled(@NonNull DatabaseError databaseError) {
                      Toast.makeText(UpdateUser.this, "DB not found", Toast.LENGTH_SHORT).show();

                  }
              });
            }
            public  void updateData()
            {
                if (isUserNameChanged() )
                {
                    Toast.makeText(UpdateUser.this, "Updated", Toast.LENGTH_LONG).show();
                }
                else
                {
                    Toast.makeText(UpdateUser.this, "Data is same and cannot be changed", Toast.LENGTH_LONG).show();
                }
            }
            private boolean isUserNameChanged()
            {
               if(!name.equals(updateusername))
               {
           UserDatabaseReference.child(position).child(uid).child("name").setValue(UserName.getText().toString());
           return true;
               }else
               {
                   return false;
               }
            }



}