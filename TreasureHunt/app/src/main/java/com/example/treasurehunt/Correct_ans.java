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
import android.widget.TextView;
import android.widget.Toast;


public class Correct_ans extends AppCompatActivity implements AsyncResponse {

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

        back_button.setVisibility(View.GONE);

        final UpdateScore updateScore = new UpdateScore(this);

        updateScore.score = this;

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
                DemoData.loggedInPlayer.no_complete_sets = String.valueOf(Integer.valueOf(DemoData.loggedInPlayer.no_complete_sets) + 1);
                DemoData.loggedInPlayer.points = String.valueOf(Integer.valueOf(DemoData.loggedInPlayer.no_complete_sets) * 50);

                Log.e("hellllo",DemoData.loggedInPlayer.name);
                updateScore.execute(DemoData.loggedInPlayer.name,
                        DemoData.gameDetails.locality,
                        String.valueOf(DemoData.gameDetails.latitude),
                        String.valueOf(DemoData.gameDetails.longitude),
                        String.valueOf(DemoData.loggedInPlayer.points),
                        String.valueOf(DemoData.loggedInPlayer.no_complete_sets));

            }
        });

    }

    @Override
    public void processFinish(String output) {
        Log.e("Correct output",output);
//        Toast.makeText(this, output, Toast.LENGTH_SHORT).show();

        if(Integer.valueOf(DemoData.loggedInPlayer.no_complete_sets) == 3){
            Toast.makeText(this, "You have already explored this locality... May choose some other location!", Toast.LENGTH_SHORT).show();
            Intent intent= new Intent(Correct_ans.this,StartNew.class);
//        intent.putExtra("data" , DemoData.loggedInPlayer.name+"$"+DemoData.loggedInPlayer.username+"$"+DemoData.loggedInPlayer.no_complete_sets+"$"+DemoData.loggedInPlayer.points+"$");
            intent.putExtra("new",false);
            startActivity(intent);

        }
        Intent intent= new Intent(Correct_ans.this,Clues.class);
//        intent.putExtra("data" , DemoData.loggedInPlayer.name+"$"+DemoData.loggedInPlayer.username+"$"+DemoData.loggedInPlayer.no_complete_sets+"$"+DemoData.loggedInPlayer.points+"$");
        intent.putExtra("new",false);
        startActivity(intent);
//        finish();
    }
}