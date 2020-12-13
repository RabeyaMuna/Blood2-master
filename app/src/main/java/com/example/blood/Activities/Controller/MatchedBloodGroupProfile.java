

package com.example.blood.Activities.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;

import com.example.blood.Activities.Model.Blood_Group_profile_items;
import com.example.blood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class MatchedBloodGroupProfile extends AppCompatActivity {
    String intent_division, intent_blood_group;
    private RecyclerView bloodBankRecyclerView;
    String data = null;

    private ArrayList<Blood_Group_profile_items> bloodItems = new ArrayList<>();
    private Adapter_of_matched_bloodGroup_profile bloodAdapter;
    DatabaseReference reference;
    String name = null;
    String age = null;
    String address = null;
//TextView name,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_blood_group_profile);
        Intent intent = getIntent();

        Log.d("Checking","1");
        bloodBankRecyclerView = findViewById(R.id.BloodGroup_profile_Recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(MatchedBloodGroupProfile.this);
        bloodBankRecyclerView.setLayoutManager(linearLayoutManager);
        bloodAdapter = new Adapter_of_matched_bloodGroup_profile(MatchedBloodGroupProfile.this, bloodItems);
        Log.d("Checking","2");
        reference = FirebaseDatabase.getInstance().getReference().child("division");
        //reference.addValueEventListener(valueEventListener);
        reference.keepSynced(true);
       /* name=findViewById(R.id.prof_text11);
        id=findViewById(R.id.prof_div11);*/
        Log.d("Checking","3");
        intent_division= intent.getStringExtra("Division");
        intent_blood_group = intent.getStringExtra("Blood_Group");
        //Toast.makeText(getApplicationContext(),intent_blood_group,Toast.LENGTH_LONG).show();

        // Toast.makeText(getApplicationContext(),intent_blood_group+" "+intent_division,Toast.LENGTH_LONG).show();
        Query query = reference.child(intent_division).child(intent_blood_group);
        Log.d("Checking","4");
        reference.child(intent_division).child(intent_blood_group).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                Log.d("Checking","5");

                collectFirebaseData((Map<String , Object>) dataSnapshot.getValue());
                /*for(DataSnapshot dp : dataSnapshot.getChildren()){
                    Log.d("Checking","7");
                    Toast.makeText(getApplicationContext(), (String) dp.child("Dhaka").child("AB+").child("name").getValue(),Toast.LENGTH_LONG).show();

                }*/
                int n = 0;

                /*for (DataSnapshot dp : dataSnapshot.getChildren()){





                }*/

                //Toast.makeText(getApplicationContext(),dataSnapshot.child("name").getValue().toString(),Toast.LENGTH_LONG).show();
                   /* for (DataSnapshot dp : dataSnapshot.getChildren()){

                        name = dp.child("name").getValue().toString();
                        age = dp.child("age").getValue().toString();
                        address = dp.child("address").getValue().toString();
                    }
                    Toast.makeText(getApplicationContext(),name,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),age,Toast.LENGTH_LONG).show();
                    Toast.makeText(getApplicationContext(),address,Toast.LENGTH_LONG).show();*/


                Log.d("Checking","6");
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        /*name.setText(intent_blood_group);
        id.setText(intent_division);*/
        /* reference.addListenerForSingleValueEvent(valueEventListener);*/
    }


    private void collectFirebaseData(Map<String, Object> value) {
        for(Map.Entry<String, Object> entry : value.entrySet()){
            Map singleData = (Map) entry.getValue();
            bloodItems.add(new Blood_Group_profile_items(String.valueOf(singleData.get("name")), String.valueOf(singleData.get("contact")),String.valueOf(singleData.get("bloodGroup")),String.valueOf(singleData.get("division"))));

        }
        bloodBankRecyclerView.setAdapter(bloodAdapter);
    }
/*ValueEventListener valueEventListener=new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//if (dataSnapshot.exists()){
   /// for (DataSnapshot snapshot : dataSnapshot.getChildren()){
        //Blood_Group_profile_items blood_group_profile_items=snapshot.getValue(Blood_Group_profile_items.class);
        //bloodItems.add(blood_group_profile_items);
        collectFirebaseData((Map<String , Object>) dataSnapshot.getValue());
    //}
}

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
};*/

}

/*
package com.example.blood.Activities;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.blood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Matched_bloodGroup_profile extends AppCompatActivity {
    String intent_division, intent_blood_group;
    private RecyclerView bloodBankRecyclerView;

    private ArrayList<Blood_Group_profile_items> bloodItems = new ArrayList<>();
    private Adapter_of_matched_bloodGroup_profile bloodAdapter;
    DatabaseReference reference;
//TextView name,id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_matched_blood_group_profile);
        bloodBankRecyclerView = findViewById(R.id.BloodGroup_profile_Recycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Matched_bloodGroup_profile.this);
        bloodBankRecyclerView.setLayoutManager(linearLayoutManager);
        bloodAdapter = new Adapter_of_matched_bloodGroup_profile(Matched_bloodGroup_profile.this, bloodItems);
        reference = FirebaseDatabase.getInstance().getReference("division");
        //reference.addValueEventListener(valueEventListener);
        reference.keepSynced(true);
       */
/* name=findViewById(R.id.prof_text11);
        id=findViewById(R.id.prof_div11);*//*

        intent_blood_group = getIntent().getStringExtra("Division");
        intent_division = getIntent().getStringExtra("Blood_Group");
        Query query = reference.child(intent_division).child(intent_blood_group);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectFirebaseData((Map<String , Object>) dataSnapshot.getValue());
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
        */
/*name.setText(intent_blood_group);
        id.setText(intent_division);*//*

       */
/* reference.addListenerForSingleValueEvent(valueEventListener);*//*

    }


    private void collectFirebaseData(Map<String, Object> value) {
        for(Map.Entry<String, Object> entry : value.entrySet()){
            Map singleData = (Map) entry.getValue();
            bloodItems.add(new Blood_Group_profile_items(String.valueOf(singleData.get("name")), String.valueOf(singleData.get("contact")),String.valueOf(singleData.get("bloodGroup")),String.valueOf(singleData.get("division"))));

        }
        bloodBankRecyclerView.setAdapter(bloodAdapter);
    }
*/
/*ValueEventListener valueEventListener=new ValueEventListener() {
    @Override
    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//if (dataSnapshot.exists()){
   /// for (DataSnapshot snapshot : dataSnapshot.getChildren()){
        //Blood_Group_profile_items blood_group_profile_items=snapshot.getValue(Blood_Group_profile_items.class);
        //bloodItems.add(blood_group_profile_items);
        collectFirebaseData((Map<String , Object>) dataSnapshot.getValue());
    //}
}

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {

    }
};*//*


}*/
