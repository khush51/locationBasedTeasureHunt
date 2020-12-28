package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.ImageView;

public class Start extends AppCompatActivity {

    Button back_button,startbtn;
    Animation topAnim,bottomAnim,middleAnim;
    ImageView board,start,t1,t2,t5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        back_button=findViewById(R.id.back_buttton);
        startbtn=findViewById(R.id.startbtn);
        board=findViewById(R.id.board);
        t1=findViewById(R.id.t1);
        t2=findViewById(R.id.t2);
        t5=findViewById(R.id.t5);
        start=findViewById(R.id.start);

        //animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnim= AnimationUtils.loadAnimation(this,R.anim.middle_animation);


        //set animations
        //assign animations to image and text
        //board.setAnimation(topAnim);
        //start.setAnimation(middleAnim);
        t1.setAnimation(topAnim);
        t2.setAnimation(bottomAnim);
        t5.setAnimation(topAnim);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Start.this, "exiting..", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Start.this,MainPage.class);
                startActivity(intent);
                finish();
            }
        });


       startbtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(Start.this, "starting a game not yet done!", Toast.LENGTH_SHORT).show();
                Log.e("start",DemoData.loggedInPlayer.name);
                startActivity(new Intent(getApplicationContext() , StartNew.class));
//                finish();
            }
        });

    }
}