package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private static int SPLASH_SCREEN=8000; //5sec

    //variables
    Animation topAnim,bottomAnim,middleAnim;
    ImageView treasure,watch,ladder,key,board;
    TextView tagline;

   // DataBaseClient dataBaseClient;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN,WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

      //  dataBaseClient = new DataBaseClient(this);

        //animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnim= AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        treasure=findViewById(R.id.treasure);
        watch=findViewById(R.id.watch);
        ladder=findViewById(R.id.ladder);
        key=findViewById(R.id.key);
        board=findViewById(R.id.board);
        tagline=findViewById(R.id.tagline);

//        LocationClass l1 = new LocationClass();
//        l1.latitude = 15.623296757118968;
//        l1.longitude = 73.78052718937397;
//        l1.locality = "Siolim";
//        l1.clues.add("One of the oldest temple in Siolim");
//        l1.clues.add("A holy place near a ground");
//        l1.clues.add("Wanna have a wedding at a temple? This is the place!");
//
//        LocationClass l2 = new LocationClass();
//        l2.latitude = 15.630635886433438;
//        l2.longitude = 73.76542568206787;
//        l2.locality = "Siolim";
//        l2.clues.add("Most famous dining place of siolim");
//        l2.clues.add("Wanna give a treat to your friends? You're welcome here");
//        l2.clues.add("A place famous for its sea food");
//
//        LocationClass l3 = new LocationClass();
//        l3.latitude = 15.630051477941423;
//        l3.longitude = 73.77859264612198;
//        l3.locality = "Siolim";
//        l3.clues.add("A holy place in a beautiful area");
//        l3.clues.add("A place close to every siolkar's heart");
//        l3.clues.add("Get a great view from here");
//
//        DemoData.locations.add(l1);
//        DemoData.locations.add(l2);
//        DemoData.locations.add(l3);
//
//        PlayerClass p1 = new PlayerClass();
//        p1.username = "witty";
//        p1.name = "Emmanuel";
//        p1.no_complete_sets = 2;
//        p1.points = 10;
//        p1.password = "witty";
//
//        PlayerClass p2 = new PlayerClass();
//        p2.name = "Shabri";
//        p2.username = "arya";
//        p2.password = "arya";
//        p2.points = 30;
//        p2.no_complete_sets = 3;
//
//        DemoData.players.add(p1);
//        DemoData.players.add(p2);
//
//        GameDetails g1 = new GameDetails();
//        g1.username = p1.username;
//        g1.locality = l1.locality;
//        g1.no_of_location = p1.no_complete_sets;
//        g1.cur_clue = 1;
//
//
//        GameDetails g2 = new GameDetails();
//        g2.username = p2.username;
//        g2.locality = l1.locality;
//        g2.cur_clue = 2;
//        g2.no_of_location = p2.no_complete_sets;

        //assign animations to image and text
        board.setAnimation(topAnim);
        watch.setAnimation(bottomAnim);
        treasure.setAnimation(topAnim);
        ladder.setAnimation(middleAnim);
        key.setAnimation(middleAnim);
        tagline.setAnimation(bottomAnim);

        board.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(new Intent(MainActivity.this , Homepage.class));
            }
        });

//        new Handler().postDelayed(new Runnable() {
//            @Override
//            public void run() {
//                Intent intent= new Intent(MainActivity.this,Homepage.class);
//                startActivity(intent);
//                //finish(); //if pressed back goes back of application
//            }
//        },SPLASH_SCREEN);


    }
}