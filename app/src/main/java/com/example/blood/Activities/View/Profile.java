package com.example.blood.Activities.View;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.example.blood.Activities.Controller.LoginActivity;
import com.example.blood.R;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

public class Profile extends AppCompatActivity {
    TextView profile_name,profile_gender,profile_age,profile_pass,profile_email,profile_address,profile_division,profile_phone,profile_blood;
    Button btn_update,btn_logout;
    private FirebaseUser firebaseUser;
    private String userID;
    private  Button logout;
    private  String mail;
    private  String name,gender,bloodgroup,contact,division,age,add, email,pass;


    DatabaseReference profile_ref;
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        Intent email = getIntent();
        //Changed here, retrieve the email of current user
        mail = email.getStringExtra("email");

        Log.d("Checking",mail);
        profile_name=findViewById(R.id.profile_Text_Name);
        profile_gender=findViewById(R.id.profile_text_gender);
        profile_age=findViewById(R.id.profile_text_age);
        profile_pass=findViewById(R.id.profile_text_pass);
        profile_phone=findViewById(R.id.profile_text_phone);
        profile_email=findViewById(R.id.profile_text_email);
        profile_division=findViewById(R.id.Profile_text_division);
        profile_blood=findViewById(R.id.profile_Text_BloodGroup);
        profile_address=findViewById(R.id.profile_text_address);

//        btn_update=findViewById(R.id.profile_button_update);
        logout=findViewById(R.id.profile_logout_button);
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                FirebaseAuth.getInstance().signOut();
                startActivity(new Intent(Profile.this, LoginActivity.class));
            }
        });
        firebaseUser=FirebaseAuth.getInstance().getCurrentUser();
        profile_ref=FirebaseDatabase.getInstance().getReference().child("users");
        userID=firebaseUser.getUid();
        Log.d("userID",userID);
        Log.d("Checking","2");


    }

    @Override
    protected void onStart() {
        super.onStart();


        profile_ref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                //UserData userData=dataSnapshot.getValue(UserData.class);

                for (DataSnapshot dp : dataSnapshot.getChildren()){

                    if(dp.child("email").getValue().equals(mail)){
                        Log.d("Checking","4");
                        name = (String) dp.child("name").getValue();
Log.d("NameError",name);
                        gender = (String) dp.child("gender").getValue();
                        bloodgroup = (String) dp.child("bloodGroup").getValue();
                        contact = (String) dp.child("contact").getValue();
                        division = (String) dp.child("division").getValue();
                        age = (String) dp.child("age").getValue();
                        add = (String) dp.child("address").getValue();
                        email=(String) dp.child("email").getValue();
                        pass=(String) dp.child("password").getValue();
                    }
                }
                Log.d("Checking","3");
                profile_name.setText("Name : "+name);
                profile_gender.setText("Gender : "+gender);
                profile_blood.setText("BloodGroup : "+bloodgroup);
                profile_address.setText("Address : "+add);
                profile_age.setText("Age : "+age);
                profile_division.setText("Division : "+division);
                profile_email.setText("Email"+email);
                profile_pass.setText("Password"+pass);
                profile_phone.setText("Contact No"+contact);

        /*if(userData!=null){
            String fullname = userData.getName();
            String phoneNo = userData.getContact();
            String email = userData.getEmail();
            String age=userData.getAge();
            String address=userData.getAddress();
            String division=userData.getDivision();
            String gender=userData.getGender();
            String blood_group=userData.getBloodGroup();
            String password=userData.getPassword();

            profile_name.setText(fullname);
            profile_gender.setText(gender);
            profile_address.setText(address);
            profile_phone.setText(phoneNo);
            profile_blood.setText(blood_group);
            profile_division.setText(division);
            profile_age.setText(age);
            profile_email.setText(email);
            profile_pass.setText(password);
        }*/


            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {
                Toast.makeText(Profile.this,"PLease singup again",Toast.LENGTH_LONG).show();
            }
        });



    }
}


/*



    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
        UserData userData=dataSnapshot.getValue(UserData.class);

        if(userData!=null){
            String fullname=userData.getName();
            String phoneNo=userData.getContact();
            String email=userData.getEmail();
            String age=userData.getAge();
            String address=userData.getAddress();
            String division=userData.getDivision();
            String gender=userData.getGender();
            String blood_group=userData.getBloodGroup();
            String password=userData.getPassword();

            profile_name.setText(fullname);
            profile_gender.setText(gender);
            profile_address.setText(address);
            profile_phone.setText(phoneNo);
            profile_blood.setText(blood_group);
            profile_division.setText(division);
            profile_age.setText(age);
            profile_email.setText(email);
            profile_pass.setText(password);
        }
    }

    @Override
    public void onCancelled(@NonNull DatabaseError databaseError) {
        Toast.makeText(Profile.this,"PLease singup again",Toast.LENGTH_LONG).show();
    }
});
        }
        }*/