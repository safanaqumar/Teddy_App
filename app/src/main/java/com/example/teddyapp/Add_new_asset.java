package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;
import androidx.core.content.ContextCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Camera;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.teddyapp.AssetDatabase.AssetDatabase;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.teddyapp.Signup.regusername;

public class Add_new_asset extends AppCompatActivity  {
private Spinner statusspinner,Location,type,Department, software_Spinner;
public static TextInputEditText serialno,Assettag,Description,Remark,User;
private Button Addasset,cancelasset ;
private Button qrscanner;
List<String> softtypes = new ArrayList<>( );
  public static   DatabaseReference AssetsDatabaseReference;;
  private static final int REQUEST_CAMERA = 1;
  int nextid = 0;
  public int check;
  public String asset_type_name;
  public int check1;
  public String asset_assign_name;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_new_asset);
        qrscanner = (Button) findViewById(R.id.qr);

        getSupportActionBar().setTitle("Add New Asset");
        statusspinner = (Spinner) findViewById(R.id.status);
        Location = (Spinner) findViewById(R.id.loction);
        type = (Spinner) findViewById(R.id.type);
        serialno = (TextInputEditText) findViewById(R.id.SerialNo);
        Assettag = (TextInputEditText) findViewById(R.id.AssetTag);
        Description = (TextInputEditText) findViewById(R.id.desc);
        Department = (Spinner) findViewById(R.id.dept);
        User = (TextInputEditText) findViewById(R.id.username);
        Remark = (TextInputEditText) findViewById(R.id.remark);
        software_Spinner = (Spinner) findViewById(R.id.softwarespinner);
        Addasset = (Button) findViewById(R.id.addasset);
        cancelasset = (Button)findViewById(R.id.cancelasset);

//Getting the username
//        User.setText(Signup.regusername.getText());
cancelasset.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        startActivity(new Intent(getApplicationContext(),MainActivity.class));
    }
});

        qrscanner.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                if (ContextCompat.checkSelfPermission(Add_new_asset.this, Manifest.permission.CAMERA)
                        != PackageManager.PERMISSION_GRANTED) {
                    ActivityCompat.requestPermissions(Add_new_asset.this,
                            new String[]{Manifest.permission.CAMERA},REQUEST_CAMERA);
                } else {
                    startActivity(new Intent(getApplicationContext(),QRreader.class));

                }
            }
        });

       // FirebaseDatabase.getInstance().getReference().child("Add_Asset");
        // FirebaseDatabase.getInstance().getReference().child("Child").child("Aseetdata").setValue("data");

        ArrayAdapter<String> spinerstatusdapter = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Status));
        spinerstatusdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusspinner.setAdapter(spinerstatusdapter);
        final List<String> typess = new  ArrayList<>();
        typess.add("MONITOR");
        typess.add("CPU");
        typess.add("KEYBOARD");
        typess.add("HEADSET");
        typess.add("TV");
        typess.add("MODEM");
        typess.add("PROJECTOR");
        typess.add("MOUSE");
        typess.add("PHONE");
        typess.add("PRINTER");

        ArrayAdapter<String> spinerstatusdaptertype = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,typess);
        spinerstatusdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                type.setAdapter(spinerstatusdaptertype);


      ArrayAdapter<String> spinerstatusdapterlocation = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Location));
        spinerstatusdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Location.setAdapter(spinerstatusdapterlocation);


        type.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
                                                   @Override
                                                   public void onItemSelected(AdapterView<?> a, View v, int pos, long id) {

                                                       //Harcoding Brands:

                                      if (a.getItemAtPosition(pos).equals("CPU"))
                                      {
                                          software_Spinner.setVisibility(View.VISIBLE);
                                          softtypes.clear();
                                          softtypes.add("firefox");
                                          softtypes.add("Microsoft Office");
                                          softtypes.add("Microsoft dynamics");
                                          softtypes.add("Windows Explorer");
                                          softtypes.add("Firefox");
                                          softtypes.add("Chrome");
                                          softtypes.add("SAP");
                                          softtypes.add("FreshDesk");
                                          softtypes.add("ZenDesk");
                                          softtypes.add("ZoboDesk");
                                          softtypes.add("Hub Spot");
                                          fillspinner();
                                      }

                                                   }

                                                   @Override
                                                   public void onNothingSelected(AdapterView<?> a) {}
                                               }

        );

        ArrayAdapter<String> spinerstatusdapterdepartment = new ArrayAdapter<>(Add_new_asset.this,android.R.layout.simple_list_item_1,getResources().getStringArray(R.array.Department));
        spinerstatusdapterdepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Department.setAdapter(spinerstatusdapterdepartment);
        //reference = FirebaseDatabase.getInstance().getReference("Data");

        Addasset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                  String serial_num = serialno.getText().toString();
                  String asset_tag = Assettag.getText().toString();
                  String typeofasset = type.getSelectedItem().toString();
                  String username =User.getText().toString();
                  String description = Description.getText().toString();
                  String location = Location. getSelectedItem().toString();
                  String deprt = Department.getSelectedItem().toString();
                  String statusasset= statusspinner.getSelectedItem().toString();
                  String remark = Remark.getText().toString();
                  if(typeofasset.equals("MONITOR"))
                  {
                      check=1;
                      asset_type_name="MONITOR";
                  }
                  else if(typeofasset.equals("CPU"))
                {
                    check=2;
                    asset_type_name="CPU";
                }
                else if(typeofasset.equals("MOUSE"))
                {
                    check=3;
                    asset_type_name="MOUSE";
                }
                else if(typeofasset.equals("KEYBOARD"))
                {
                    check=4;
                    asset_type_name="KEYBOARD";
                }
                else if(typeofasset.equals("PHONE"))
                {
                    check=5;
                    asset_type_name="PHONE";
                }
                else if(typeofasset.equals("HEADSET"))
                {
                    check=6;
                    asset_type_name="HEADSET";
                }
                else if(typeofasset.equals("PROJECTOR"))
                {
                    check=7;
                    asset_type_name="PROJECTOR";
                }
                else if(typeofasset.equals("TV"))
                {
                    check=8;
                    asset_type_name="TV";
                }
                else if(typeofasset.equals("MODEM"))
                {
                    check=9;
                    asset_type_name="MODEM";
                }
                  else if(typeofasset.equals("PRINTER"))
                  {
                      check=10;
                      asset_type_name="PRINTER";
                  }

                  if (statusasset.equals("assign"))
                  {
                      check1=1;
                      asset_type_name="assign";

                  }else if (statusasset.equals("unassign"))
                  {
                      check1=1;
                      asset_type_name="unassign";
                  }
                  else if (statusasset.equals("faulty"))
                  {
                      check1=1;
                      asset_type_name="faulty";
                  }
                  else if (statusasset.equals("dispose"))
                  {
                      check1=1;
                      asset_type_name="dispose";
                  }

















                if (TextUtils.isEmpty(serial_num)) {
                    Toast.makeText(Add_new_asset.this, " ENTER SERIAL NO", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(asset_tag)) {
                    Toast.makeText(Add_new_asset.this, " ENTER ASSET TAG", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(typeofasset)) {
                    Toast.makeText(Add_new_asset.this, " ENTER TYPE OF ASSET", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(description)) {
                    Toast.makeText(Add_new_asset.this, " ENTER DESCRIPTION", Toast.LENGTH_SHORT).show();
                    return;
                }
                if (TextUtils.isEmpty(location)) {
                    Toast.makeText(Add_new_asset.this, " ENTER LOCATION", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(remark)) {
                    Toast.makeText(Add_new_asset.this, " ENTER REMARK", Toast.LENGTH_SHORT).show();
                    return;
                }

                if (TextUtils.isEmpty(username)) {
                    Toast.makeText(Add_new_asset.this, " ENTER USERNAME", Toast.LENGTH_SHORT).show();
                    return;
                }

                  String reader = location.concat(typeofasset);
                  AssetDatabase assetDatabase = new AssetDatabase(serial_num,asset_tag,typeofasset,description,location,deprt,statusasset,remark,reader);

                //AssetsDatabaseReference = FirebaseDatabase.getInstance().getReference("Data").child(FirebaseAuth.getInstance().getCurrentUser().getUid());
                AssetsDatabaseReference = FirebaseDatabase.getInstance().getReference("Data");


                // reference.setValue(assetDatabase);
            // DatabaseReference ref = AssetsDatabaseReference.child("assets").push();

                AssetsDatabaseReference.child(asset_type_name).child("status").child(asset_type_name).setValue(assetDatabase);
                //AssetsDatabaseReference.child("assets").getKey();
                //reference.child(serial_num).setValue(assetDatabase);
                // Generate a reference to a new location and add some data using push()

                Toast.makeText(Add_new_asset.this,reader,Toast.LENGTH_LONG).show();
                Intent intent = new Intent(Add_new_asset.this,MainActivity.class);
                startActivity(intent);

            }
        });
    }
 public  void fillspinner()
 {

     ArrayAdapter<String> adapter_2 = new ArrayAdapter<>(this,
             android.R.layout.simple_list_item_1,softtypes);
     adapter_2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
     software_Spinner.setAdapter(adapter_2);
 }

}
