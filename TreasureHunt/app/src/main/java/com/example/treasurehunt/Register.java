package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

import java.util.ArrayList;

public class Register extends AppCompatActivity {

    Animation middleAnim;
    ImageView board;

//    DatabaseClient dbclient;
    //DataBaseHelper db;

    TextInputEditText name , username , pass1 , pass2;

    Button back_button,register;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


/*
        dbclient = new DatabaseClient(this);
        dbclient.open();
        dbclient.insertInPlayers(player);
      //  db = new DataBaseHelper(this);
*/
        name = (TextInputEditText) findViewById(R.id.fullName);
        username = (TextInputEditText) findViewById(R.id.username);
        pass1 = (TextInputEditText) findViewById(R.id.pass1);
        pass2 = (TextInputEditText) findViewById(R.id.pass2);

        register=findViewById(R.id.register);
        back_button=findViewById(R.id.back_buttton);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Register.this,Homepage.class);
                startActivity(intent);
                finish(); //if pressed back goes back of application
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String fname = name.getText().toString();
                String uname = username.getText().toString();
                String password1 = pass1.getText().toString();
                String password2 = pass2.getText().toString();

                BackgroundWorker backgroundWorker =  new BackgroundWorker(getApplicationContext());
                Toast.makeText(getApplicationContext() , "background..." , Toast.LENGTH_SHORT).show();
//
                if(password1.equals(password2) == false)
                {
                    Toast.makeText(getApplicationContext() , "Passwords do not match!!" , Toast.LENGTH_SHORT).show();
                }
                else
                {
                    backgroundWorker.execute("register" , fname , uname , password1, password2);
//                    if(DemoData.flag)
//                        startActivity(new Intent(getApplicationContext() , Login.class));
                }

                finish(); //if pressed back goes back of application
            }
        });

    }
}