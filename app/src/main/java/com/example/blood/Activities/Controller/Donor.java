package com.example.blood.Activities.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.blood.Activities.Model.DonorItems;
import com.example.blood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Donor extends AppCompatActivity {
    private RecyclerView donoRecylerview;
    private ArrayList<DonorItems> donorItems = new ArrayList<>();
    private DonorAdapter donorAdapter;
    DatabaseReference reference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_donor);
        donoRecylerview=findViewById(R.id.donorRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Donor.this);
        donoRecylerview.setLayoutManager(linearLayoutManager);
        donorAdapter = new DonorAdapter(Donor.this, donorItems);
        reference = FirebaseDatabase.getInstance().getReference().child("users");
        reference.keepSynced(true);
        reference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                collectFirebaseData((Map<String , Object>) dataSnapshot.getValue());

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
    private void collectFirebaseData(Map<String, Object> value) {




        for(Map.Entry<String, Object> entry : value.entrySet()){
            Map singleData = (Map) entry.getValue();
            donorItems.add(new DonorItems(String.valueOf(singleData.get("name")), String.valueOf(singleData.get("contact")),String.valueOf(singleData.get("bloodGroup")),String.valueOf(singleData.get("division"))));

            //            Log.d("DEBUGLOG", String.valueOf(singleData.get("Name")));
            //            Log.d("DEBUGLOG", String.valueOf(singleData.get("phone")));
        }
        donoRecylerview.setAdapter(donorAdapter);
    }



}
//String.valueOf(singleData.get("Name")), String.valueOf(singleData.get("phone"))