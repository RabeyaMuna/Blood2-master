package com.example.blood.Activities.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.example.blood.Activities.Model.ChatMessage;
import com.example.blood.R;
import com.google.android.material.floatingactionbutton.FloatingActionButton;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.FirebaseDatabase;

public class ChatActivity extends AppCompatActivity {
    private String loggedInUserName = "";
    ChatAdapter adapter;
    ListView listView;
    EditText input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat);

        FloatingActionButton fab=(FloatingActionButton) findViewById(R.id.fab);
        listView=findViewById(R.id.list);
        input=findViewById(R.id.input);

        if (FirebaseAuth.getInstance().getCurrentUser()==null){
            Intent intent=new Intent(this, Registration.class);
            startActivity(intent);

        } else {
            showAllOldMessages();
        }

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String msg = input.getText().toString();
                FirebaseUser fUser = FirebaseAuth.getInstance().getCurrentUser();

                if (msg.trim().equals("")) {
                    Toast.makeText(ChatActivity.this, "Please enter some texts!", Toast.LENGTH_SHORT).show();
                } else {
                    FirebaseDatabase.getInstance()
                            .getReference()
                            .push()
                            .setValue(new ChatMessage(msg,
                                    fUser.getDisplayName(),
                                    fUser.getUid())
                            );
                    input.setText("");

                    // TODO add items to listview
                }
            }
        });
    }

    private void showAllOldMessages() {
        loggedInUserName = FirebaseAuth.getInstance().getCurrentUser().getUid();
        Log.d("Main", "user id: " + loggedInUserName);

        adapter = new ChatAdapter(this, ChatMessage.class, R.layout.chat_item_left,
                FirebaseDatabase.getInstance().getReference());
        listView.setAdapter(adapter);
    }
    public String getLoggedInUserName() {
        return loggedInUserName;
    }
}