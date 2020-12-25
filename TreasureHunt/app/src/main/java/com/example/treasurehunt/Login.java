package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.material.textfield.TextInputEditText;

public class Login extends AppCompatActivity implements AsyncResponse{


    Animation middleAnim;
    ImageView board;

    TextInputEditText username , password;

    Button back_button,login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        final BackgroundWorker backgroundWorker = new BackgroundWorker(getApplicationContext());

        username = (TextInputEditText) findViewById(R.id.loginUsername);
        password = (TextInputEditText) findViewById(R.id.loginPassword);

        back_button=findViewById(R.id.back_buttton);
        board=findViewById(R.id.board);
        login=findViewById(R.id.login);

        backgroundWorker.value = this;

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(Login.this,Homepage.class);
                startActivity(intent);
                finish(); //if pressed back goes back of application
            }
        });


        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String usernm = username.getText().toString();
                String pass = password.getText().toString();

                Boolean validUser = false;
                int i = 0;



//                Toast.makeText(getApplicationContext() , "background..." , Toast.LENGTH_SHORT).show();
//                backgroundWorker.value = this;
                backgroundWorker.execute("login" , usernm , pass);
//                Toast.makeText(getApplicationContext() , "executed..." , Toast.LENGTH_SHORT).show();

//                Log.i("LOGIN", String.valueOf(userdetails));



//                finish(); //if pressed back goes back of application
            }
        });
    }

    @Override
    public void processFinish(String output) {
//        Log.i("Login",output);

        if(output.equals("NULL") ){
            Toast.makeText(getApplicationContext(), "Incorrect Login Details.. Try again", Toast.LENGTH_SHORT).show();
        }
        else{
            Intent intent= new Intent(getApplicationContext(),MainPage.class);
            intent.putExtra("data" , output);
            startActivity(intent);
            finish();
        }
    }
}