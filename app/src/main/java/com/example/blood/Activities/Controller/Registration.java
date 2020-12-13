package com.example.blood.Activities.Controller;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.example.blood.Activities.Model.DonorItems;
import com.example.blood.Activities.Model.UserData;
import com.example.blood.R;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class Registration extends AppCompatActivity {
    private static final String TAG = Registration.class.getSimpleName();
    private EditText inputemail, inputpassword, fullName, address, contact, age;
    private FirebaseAuth mAuth;
    private Button btnSignup;
    private Spinner gender, bloodgroup, division;
    private CheckBox isDonor;
    private boolean isUpdate = false;
    private static final String USER = "user";
    private UserData userData;
    private DonorItems donorItems;
    private DatabaseReference db_ref, divison_ref;

    // private FirebaseDatabase db_User;
    /*private String userId;*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_registration);
        inputemail = findViewById(R.id.input_userEmail);
        inputpassword = findViewById(R.id.input_password);
        fullName = findViewById(R.id.input_fullName1);
        gender = findViewById(R.id.gender);
        address = findViewById(R.id.inputAddress);
        division = findViewById(R.id.inputDivision);
        bloodgroup = findViewById(R.id.inputBloodGroup);
        contact = findViewById(R.id.inputMobile);
        age = findViewById(R.id.Age);
        mAuth=FirebaseAuth.getInstance();
        divison_ref = FirebaseDatabase.getInstance().getReference("Division");
        btnSignup=findViewById(R.id.button_register);
        btnSignup.setOnClickListener(
                new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        add_blood_division();
                        add_registration();

                    }
                });
    }

    public void add_blood_division() {
        String name = fullName.getText().toString();
        String emailID = inputemail.getText().toString();
        String passw = inputpassword.getText().toString();
        String Address = address.getText().toString();
        String phoneNo = contact.getText().toString();
        String Gender = gender.getSelectedItem().toString();
        String BloodGroup = bloodgroup.getSelectedItem().toString();
        String Division = division.getSelectedItem().toString();
        String Age = age.getText().toString();
        if (!(TextUtils.isEmpty(name))) {
            String id = divison_ref.push().getKey();
            UserData division_userdata = new UserData(name, emailID, passw, Age, Address, phoneNo, Gender, BloodGroup, Division);
            divison_ref.child(id).setValue(division_userdata);
        } else {
            Toast.makeText(this, "enter", Toast.LENGTH_LONG).show();
        }
    }

    public void add_registration() {
        String email = inputemail.getText().toString();
        String password = inputpassword.getText().toString();
        if (TextUtils.isEmpty(email) || TextUtils.isEmpty(password)) {
            Toast.makeText(getApplicationContext(), "Email address and password", Toast.LENGTH_LONG).show();
            return;
        }
        String name = fullName.getText().toString();
        String emailID = inputemail.getText().toString();
        String passw = inputpassword.getText().toString();
        String Address = address.getText().toString();
        String phoneNo = contact.getText().toString();
        String Gender = gender.getSelectedItem().toString();
        String BloodGroup = bloodgroup.getSelectedItem().toString();
        String Division = division.getSelectedItem().toString();
        String Age = age.getText().toString();
        userData = new UserData(name, emailID, passw, Age, Address, phoneNo, Gender, BloodGroup, Division);

        registerUser(emailID, passw);
    }
    public void registerUser(String email, String password) {
        //updateUI();
        mAuth.createUserWithEmailAndPassword(email, password)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if (task.isSuccessful()) {
                            // Sign in success, update UI with the signed-in user's information
                            Log.d(TAG, "createUserWithEmail:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                            updateUI();
                        } else {

                            Log.w(TAG, "createUserWithEmail:failure", task.getException());
                            Toast.makeText(Registration.this, "Authentication failed.",
                                    Toast.LENGTH_SHORT).show();

                        }


                    }
                });
    }
    public void updateUI() {
        // String UserID = "something";//db_ref.push().getKey();
        DatabaseReference db_ref = FirebaseDatabase.getInstance().getReference();
        String UserID =db_ref.push().getKey();//db_ref.push().getKey();
        //UserData userData = new UserData("some name ", "muna18@gmail.com", "123456", "22", "some address", "7129833", 1, 2, 3);

        db_ref.child("users").child(UserID).setValue(userData);
        db_ref.child("blood_group").child(userData.getBloodGroup()).child(userData.getDivision()).child(UserID).setValue(userData);
        db_ref.child("division").child(userData.getDivision()).child(userData.getBloodGroup()).child(UserID).setValue(userData);
        Toast.makeText(Registration.this,UserID,Toast.LENGTH_LONG).show();
        Intent logIntent = new Intent(Registration.this, MainActivity.class);
        startActivity(logIntent);
    }

}