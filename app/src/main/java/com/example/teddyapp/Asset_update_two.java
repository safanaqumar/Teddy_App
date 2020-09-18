package com.example.teddyapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DatabaseReference;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class Asset_update_two extends AppCompatActivity {
    DatabaseReference reference;
  // public String[] mString = new String[]{"1122934","399X12","26543A21","12233XX1","12353XXdd1"};
ListView listView;
//String[] name1 = {};
final List<String> name = new ArrayList();
TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_asset_update_two);
        listView = (ListView) findViewById(R.id.listvviewtwo);
        final Intent intent = getIntent();
        String tagg = intent.getStringExtra("asset_tag");
        String months  = tagg;
        name.add(months);

        //ArrayAdapter String_Adapter= new ArrayAdapter(this,R.layout.asset_data,name);
        ArrayAdapter stringArrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1,name);
        listView.setAdapter(stringArrayAdapter);

//name1 = new String[]{"asset_tag"};



//Object obj  = tagg;
        //int  asaa = String_Adapter.getPosition(tagg);
      //  String sccd = String_Adapter.getItem(tagg)

         //name.get(asaa);

/*
listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Intent intent1 = new Intent(Asset_update_two.this,Asset_update_three.class);
        startActivity(intent1);
    }
});
*/



      /* for (int i = 0 ; i < name.size() ; i++){
           tagg[i]
       }*/

        //String
      //  name.add(tagg);
        // String tagg1 = tagg.getString();

        //textView.setText(tagg);
        //   textView.findViewById(R.id.testing);

       /// listView.setAdapter(String_Adapter);

        // String_Adapter.notifyDataSetChanged();

        //String
       // /name.add(tagg);


      /*  listView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent1 = new Intent(Asset_update_two.this,Asset_update_three.class);
          startActivity(intent1);
            }
        });*/
        /*reference.addChildEventListener(new ChildEventListener() {
    @Override
    public void onChildAdded(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
      //  String value = dataSnapshot.getValue(Asset_update.class).toString();
        name.add(tagg);
    }

    @Override
    public void onChildChanged(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {
        String_Adapter.notifyDataSetChanged();

    }

    @Override
    public void onChildRemoved(@NonNull DataSnapshot dataSnapshot) {

    }

    @Override
    public void onChildMoved(@NonNull DataSnapshot dataSnapshot, @Nullable String s) {

    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
});
       *//*  String taggg =  intent.getStringExtra("asset_tag");
        String name2 = String.valueOf(taggg);
        name.add(name2);*//*



    }

 *//*   @Override
    protected void onResume() {
        super.onResume();
    }*//*

         *//* public void showAllassetData () {
            Intent intent = getIntent();
            String taggg = intent.getStringExtra("asset_tag");

            arrayList.add(taggg);

            String_Adapter.notifyDataSetChanged();


    }*/

    }


}
