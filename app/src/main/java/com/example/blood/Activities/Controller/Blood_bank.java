package com.example.blood.Activities.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;

import com.example.blood.R;

public class Blood_bank extends AppCompatActivity {
Spinner div,blood;
Button search;
String division;
String blood_group;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blood_bank);
        div=findViewById(R.id.blood_bank_divison);
        blood=findViewById(R.id.blood_bank_bloodGroup);
search=findViewById(R.id.btn_blood_bank);
search.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View v) {
        division=div.getSelectedItem().toString();
        blood_group=blood.getSelectedItem().toString();
        Intent intent=new Intent(Blood_bank.this, MatchedBloodGroupProfile.class);
        intent.putExtra("Division",division);
        intent.putExtra("Blood_Group",blood_group);
        startActivity(intent);
    }
});
    }
}