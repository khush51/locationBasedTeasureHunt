package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.Toast;

public class Homepage extends AppCompatActivity {

    ImageView board;
    Button register,login,guest,about;
    Animation topAnim,bottomAnim,middleAnim;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_homepage);


        register=findViewById(R.id.register);
        login=findViewById(R.id.login);
        guest=findViewById(R.id.guest);
        board=findViewById(R.id.board);
        about=findViewById(R.id.about);

        //animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnim= AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        register.setAnimation(topAnim);
        login.setAnimation(middleAnim);
        guest.setAnimation(bottomAnim);
        board.setAnimation(topAnim);

        guest.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent= new Intent(Homepage.this,MainPage.class);
                intent.putExtra("data" , "Guest$NULL$0$0");
                startActivity(intent);
                finish();
            }
        });

        login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Homepage.this, "login with credentials", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Homepage.this,Login.class);
                startActivity(intent);
                finish();
            }
        });

        register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Homepage.this, "register ..", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Homepage.this,Register.class);
                startActivity(intent);
                finish();
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(Homepage.this,Aboutus.class);
                startActivity(intent);
                finish(); //if pressed back goes back of application
            }
        });

    }
}