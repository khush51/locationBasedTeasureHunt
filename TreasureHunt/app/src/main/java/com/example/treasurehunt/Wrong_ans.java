package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class Wrong_ans extends AppCompatActivity {

    Button back_button,exit;
    Animation topAnim,bottomAnim,middleAnim;
    TextView oppstxt,wrongtxt;
    ImageView wrongimg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_wrong_ans);

        back_button=findViewById(R.id.back_buttton);
        exit=findViewById(R.id.exit);
        oppstxt=findViewById(R.id.oppstxt);
        wrongtxt=findViewById(R.id.wrongtxt);
        wrongimg=findViewById(R.id.wrongimg);

        //animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnim= AnimationUtils.loadAnimation(this,R.anim.middle_animation);


        //set animations
        //assign animations to image and text
        //board.setAnimation(topAnim);
        //start.setAnimation(middleAnim);

        oppstxt.setAnimation(topAnim);
        wrongimg.setAnimation(middleAnim);
        wrongtxt.setAnimation(bottomAnim);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Wrong_ans.this, "exiting..", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Wrong_ans.this,MainPage.class);
                intent.putExtra("player" , false);
                startActivity(intent);
                finish();
            }
        });


        exit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Wrong_ans.this, "Exiting...", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Wrong_ans.this,MainPage.class);
                intent.putExtra("player" , false);
                startActivity(intent);
                finish();
            }
        });
    }
}