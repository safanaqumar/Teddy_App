package com.example.teddyapp;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

import static com.example.teddyapp.Asset_update_two.itemval;
import static com.example.teddyapp.Asset_update_two.listView;


public class Asset_update_three extends AppCompatActivity {
    public static Spinner statusspinner, Location, type, Department, software_Spinner;
    public static TextInputEditText serialno, Assettag, Description, Remark, User;
    private Button Addasset, cancelasset;
    public Button qrscanner;
    List<String> softtypes = new ArrayList<>();
    public static DatabaseReference AssetsDatabaseReference;
    public static String remark1;
    public static String location1;
    public static String assettags1;
    public static String Serial_no1;
    public static String deprt1;
    public static String description1;
    public static String type1;
    public static String Status1;

    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_update_three);
        getSupportActionBar().setTitle("Asset Update");
        qrscanner = (Button) findViewById(R.id.qr);
        reference = FirebaseDatabase.getInstance().getReference("Data");
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
        cancelasset = (Button) findViewById(R.id.cancelasset);

        ArrayAdapter<String> spinerstatusdapter = new ArrayAdapter<>(Asset_update_three.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Status));
        spinerstatusdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        statusspinner.setAdapter(spinerstatusdapter);

        ArrayAdapter<String> spinerstatusdaptertype = new ArrayAdapter<>(Asset_update_three.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.type));
        spinerstatusdaptertype.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        type.setAdapter(spinerstatusdaptertype);

        ArrayAdapter<String> spinerstatusdapterlocation = new ArrayAdapter<>(Asset_update_three.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Location));
        spinerstatusdapterlocation.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Location.setAdapter(spinerstatusdapterlocation);

        ArrayAdapter<String> spinerstatusdapterdepartment = new ArrayAdapter<>(Asset_update_three.this, android.R.layout.simple_list_item_1, getResources().getStringArray(R.array.Department));
        spinerstatusdapterdepartment.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        Department.setAdapter(spinerstatusdapterdepartment);

        int status = spinerstatusdapter.getPosition(Status1);
        statusspinner.setSelection(status);

        //Getting the username
        Assettag.setText(assettags1);
        Description.setText(description1);
        //User.setText();
        Remark.setText(remark1);
        serialno.setText(Serial_no1);
        int loc = spinerstatusdapterlocation.getPosition(location1);
        Location.setSelection(loc);
        int type = spinerstatusdaptertype.getPosition(type1);
        Department.setSelection(type);


        data();


    }

    public void updated(View view) {
        if (isaseettagchanged() || isremarkchanged() || isserialnochanged() || isstatuschanged() || istypeofasset() || isdepartmentchanged() || isdescriptionchanged() || islocationchanged() ) {
            Toast.makeText(this, "Data has been Updated", Toast.LENGTH_SHORT).show();

        }
        else
            Toast.makeText(this, "Data is same cannot be Updated", Toast.LENGTH_SHORT).show();

    }
    private boolean isdepartmentchanged() {
        if (!deprt1.equals(Department.getSelectedItem().toString())) {
            reference.child("deprt").setValue(Department.getSelectedItem().toString());
            deprt1 = Department.getSelectedItem().toString();
            return true;

        } else {
            return false;
        }

    }
    private boolean islocationchanged(){
        if (!location1.equals(Location.getSelectedItem().toString())) {
            reference.child("location").setValue(Location.getSelectedItem().toString());
            location1 = Location.getSelectedItem().toString();
            return true;

        } else {
            return false;
        }

    }


    private boolean isstatuschanged(){
    if (!Status1.equals(statusspinner.getSelectedItem().toString())) {
        reference.child("statusasset").setValue(statusspinner.getSelectedItem().toString());
        Status1 = statusspinner.getSelectedItem().toString();
        return true;

    } else {
        return false;
    }

}
    private boolean istypeofasset() {
        if (!type1.equals(type.getSelectedItem().toString())) {
            reference.child("typeofasset").setValue(type.getSelectedItem().toString());
            type1 = type.getSelectedItem().toString();
            return true;

        } else {
            return false;
        }
    }
String key = reference.getKey();
    private boolean isdescriptionchanged(){
        if (!description1.equals(Description.getText().toString())) {
            reference.child(key).child("description").setValue(Description.getText().toString());
            description1 = Description.getText().toString();
            return true;

        } else {
            return false;
        }

    }


    private boolean isserialnochanged() {
        if (!Serial_no1.equals(serialno.getText().toString())) {
            reference.child("serial_num").setValue(serialno.getText().toString());
            Serial_no1 = serialno.getText().toString();
            return true;

        } else {
            return false;
        }
    }

    private boolean isremarkchanged() {
        if (!remark1.equals(Remark.getText().toString())) {
            reference.child("remark").setValue(Remark.getText().toString());
            remark1 = Remark.getText().toString();
            return true;

        } else {
            return false;
        }

    }

    private boolean isaseettagchanged() {

        if (!assettags1.equals(Assettag.getText().toString())) {
            reference.child("asset_tag").setValue(Assettag.getText().toString());
            assettags1 = Assettag.getText().toString();
            return true;


        } else {
            return false;
        }


    }


public void data() {

/*        Intent intent = getIntent();
        String at = intent.getStringExtra("asset_tag");
        String sn = intent.getStringExtra("serial_num");
        String ty = intent.getStringExtra("typeofstring");
        String dsc = intent.getStringExtra("description");
        String dpt = intent.getStringExtra("deprt");
        String sta = intent.getStringExtra("status");
        String rmk = intent.getStringExtra("remark");
        serialno.setText(sn);
        Remark.setText(rmk);
        Description.setText(dsc);*/


            //String  selectedval= listView.posi;
            //   if (!(selectedval == null));
            //  final Intent intent1 = getIntent();
       /* Intent intent1 = new Intent(Asset_update_two.this,Asset_update_three.class);
        startActivity(intent1);*/
            DatabaseReference reference = FirebaseDatabase.getInstance().getReference("Data");
            // final String selectedval = (String) listView.getSelectedItem();
//        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            //String val = intent.getStringExtra("typeofasset");
            //String val2 = intent.getStringExtra("location");

            //String blah = val.concat(val2);
            // String  TOTAL = blah.concat(selectedval);
            Query data = reference.orderByChild("asset_tag").equalTo(itemval);
            data.addListenerForSingleValueEvent(new ValueEventListener() {
                @Override
                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                    if (dataSnapshot.exists()) {

                        for (DataSnapshot datas : dataSnapshot.getChildren()) {
                            assettags1 = datas.child("asset_tag").getValue(String.class);
                            Serial_no1 = datas.child("serial_num").getValue(String.class);
                            type1 = datas.child("typeofasset").getValue(String.class);
                            description1 = datas.child("description").getValue(String.class);
                            location1 = datas.child("location").getValue(String.class);
                            deprt1 = datas.child("deprt").getValue(String.class);
                            Status1 = datas.child("statusasset").getValue(String.class);
                            remark1 = datas.child("remark").getValue(String.class);
                            //User1 = datas.child("").getValue(String.class)
//
                        }

                        //Intent intent = new Intent(getApplicationContext(), Asset_update_three.class);

                  /*  intent.putExtra("typeofasset", type1);
                    intent.putExtra("location", location1);
                    intent.putExtra("deprt", deprt1);
                    intent.putExtra("statusasset", Status1);
                    intent.putExtra("asset_tag", assettags1);
                    intent.putExtra("serial_num", Serial_no1);
                    intent.putExtra("remark", remark1);
                    intent.putExtra("description", description1);
*/

                    } else {
                        // Toast.makeText(Asset_update_three.this,"data not found",Toast.LENGTH_LONG).show();

                    }
                }

                @Override
                public void onCancelled(@NonNull DatabaseError databaseError) {

                }
            });
        }
    @Override
    protected void onDestroy() {
        super.onDestroy();
    }


}
