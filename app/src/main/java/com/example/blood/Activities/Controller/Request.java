package com.example.blood.Activities.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.blood.Activities.Model.UserData;
import com.example.blood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.Calendar;

public class Request extends AppCompatActivity {
    EditText req_name, req_phone, req_Location;
    Spinner spinner_blood, spinner_division;
    Button btnpost;

    FirebaseDatabase rdb;
    DatabaseReference r_ref;
    FirebaseAuth rAuth;

    Calendar cal;
    String uid;
    String Time, Date;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_request);
        req_name = findViewById(R.id.Req_name);
        req_phone = findViewById(R.id.Req_tMobile);
        spinner_blood = findViewById(R.id.Req_SpinnerBlood);
        spinner_division = findViewById(R.id.REq_SpinnerDivision);
        btnpost = findViewById(R.id.postbtn);
        req_Location = findViewById(R.id.Req_Location);
        r_ref=FirebaseDatabase.getInstance().getReference("pots");
        btnpost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                add_post();
                Intent intent=new Intent(Request.this, Feed.class);
                startActivity(intent);
            }

        });

    }
    private  void  add_post(){
        String name=req_name.getText().toString().trim();
        String phone=req_phone.getText().toString().trim();
        String locaton=req_Location.getText().toString().trim();
        String blood=spinner_blood.getSelectedItem().toString();
        String division=spinner_division.getSelectedItem().toString();
        if (!TextUtils.isEmpty(name)){
            String id=r_ref.push().getKey();
UserData post=new UserData(id,name,phone,locaton,blood,division);
r_ref.child(id).setValue(post);
            Toast.makeText(this,"Post added",Toast.LENGTH_LONG).show();
    }
        else {
            Toast.makeText(this,"You should enter a name",Toast.LENGTH_LONG).show();
        }
}
/*        cal = Calendar.getInstance();

        int day = cal.get(Calendar.DAY_OF_MONTH);
        int month = cal.get(Calendar.MONTH);
        int year = cal.get(Calendar.YEAR);
        int hour = cal.get(Calendar.HOUR);
        int min = cal.get(Calendar.MINUTE);
        month+=1;
        Time = "";
        Date = "";
        String ampm="AM";

        if(cal.get(Calendar.AM_PM) ==1)
        {
            ampm = "PM";
        }

        if(hour<10)
        {
            Time += "0";
        }
        Time += hour;
        Time +=":";

        if(min<10) {
            Time += "0";
        }

        Time +=min;
        Time +=(" "+ampm);

        Date = day+"/"+month+"/"+year;
        FirebaseUser current_user=rAuth.getInstance().getCurrentUser();
        if (current_user==null){
            startActivity(new Intent(Request.this,LoginActivity.class));
        }
        else {
            uid = current_user.getUid();
        }
rAuth=FirebaseAuth.getInstance();
            rdb=FirebaseDatabase.getInstance();
            r_ref=rdb.getReference("posts");
        try {
            btnpost.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    final Query findname = rdb.getReference("users").child(uid);

                    if(req_name.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter your Full name!",
                                Toast.LENGTH_LONG).show();
                    }
                    if(req_phone.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter your contect number!",
                                Toast.LENGTH_LONG).show();
                    }
                    else if(req_Location.getText().length() == 0)
                    {
                        Toast.makeText(getApplicationContext(), "Enter your location!",
                                Toast.LENGTH_LONG).show();
                    }
                    else {
                        findname.addListenerForSingleValueEvent(new ValueEventListener() {
                            @Override
                            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {

                                if (dataSnapshot.exists()) {
                                    r_ref.child(uid).child("name").setValue(dataSnapshot.getValue(UserData.class).getName());
                                    r_ref.child(uid).child("name").setValue(req_name.getText().toString());
                                    r_ref.child(uid).child("contact").setValue(req_phone.getText().toString());
                                    r_ref.child(uid).child("Address").setValue(req_Location.getText().toString());
                                    r_ref.child(uid).child("Division").setValue(spinner_division.getSelectedItem().toString());
                                    r_ref.child(uid).child("BloodGroup").setValue(spinner_blood.getSelectedItem().toString());
                                    r_ref.child(uid).child("Time").setValue(Time);
                                    r_ref.child(uid).child("Date").setValue(Date);
                                    Toast.makeText(Request.this, "Your post has been created successfully",
                                            Toast.LENGTH_LONG).show();
                                    startActivity(new Intent(Request.this, Feed.class));

                                } else {
                                    Toast.makeText(getApplicationContext(), "Database error occured.",
                                            Toast.LENGTH_LONG).show();
                                }

                            }

                            @Override
                            public void onCancelled(@NonNull DatabaseError databaseError) {
                                Log.d("users", databaseError.getMessage());

                            }
                        });
                    }
                }
            });
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }

    }*/
        }

