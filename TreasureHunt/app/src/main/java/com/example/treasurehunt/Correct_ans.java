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


public class Correct_ans extends AppCompatActivity {

    Button back_button,cont;
    Animation topAnim,bottomAnim,middleAnim;
    TextView congotxt,correcttxt;
    ImageView correctimg,celebration;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_correct_ans);

        back_button=findViewById(R.id.back_buttton);
        cont=findViewById(R.id.cont);
        congotxt=findViewById(R.id.congotxt);
        correcttxt=findViewById(R.id.correcttxt);
        correctimg=findViewById(R.id.correctimg);
        celebration=findViewById(R.id.celebration);

        //animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnim= AnimationUtils.loadAnimation(this,R.anim.middle_animation);


        //set animations
        //assign animations to image and text
        //board.setAnimation(topAnim);
        //start.setAnimation(middleAnim);

        congotxt.setAnimation(topAnim);
        celebration.setAnimation(bottomAnim);
        correcttxt.setAnimation(middleAnim);
        correctimg.setAnimation(middleAnim);


        //back button get to Clue page for next clue
        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Correct_ans.this, "exiting..", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Correct_ans.this,Clues.class);
                startActivity(intent);
                finish();
            }
        });


        //the continue button linking to Clue page for next clue
        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Correct_ans.this, "Continue..,", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Correct_ans.this,Clues.class);
                startActivity(intent);
                finish();
            }
        });

    }
}