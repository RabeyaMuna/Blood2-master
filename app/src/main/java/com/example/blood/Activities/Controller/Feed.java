package com.example.blood.Activities.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;

import com.example.blood.Activities.Model.UserData;
import com.example.blood.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Map;

public class Feed extends AppCompatActivity {

    private RecyclerView feed_Recylerview;
   private FeedAdapter feed_adapter;
    DatabaseReference feed_Ref;
    ArrayList<UserData> feed_items = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_feed);
        feed_Recylerview = findViewById(R.id.feedRecycler);
        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(Feed.this);
        feed_Recylerview.setLayoutManager(linearLayoutManager);
        feed_adapter = new FeedAdapter(Feed.this, feed_items);
        feed_Ref = FirebaseDatabase.getInstance().getReference().child("pots");
        feed_Ref.keepSynced(true);
        feed_Ref.addListenerForSingleValueEvent(new ValueEventListener() {
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
            feed_items.add(new UserData(String.valueOf(singleData.get("name")), String.valueOf(singleData.get("contact")),String.valueOf(singleData.get("bloodGroup")),String.valueOf(singleData.get("address")),String.valueOf(singleData.get("division"))));

            //            Log.d("DEBUGLOG", String.valueOf(singleData.get("Name")));
            //            Log.d("DEBUGLOG", String.valueOf(singleData.get("phone")));
        }
        feed_Recylerview.setAdapter(feed_adapter);
    }



}