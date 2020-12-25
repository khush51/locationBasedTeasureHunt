package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class MainPage extends AppCompatActivity implements AsyncResponse{

    Button about,back_button,help,profile,newgame,cont , scoreboard;
    TextView username , score , level;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_page);

        about=findViewById(R.id.about);
        help=findViewById(R.id.help);
        back_button=findViewById(R.id.back_buttton);
        profile=findViewById(R.id.profile);
        newgame=findViewById(R.id.newgame);
        cont=findViewById(R.id.cont);
        username = (TextView) findViewById(R.id.username);
        score = (TextView) findViewById(R.id.score);
        level = (TextView) findViewById(R.id.level);

        final GetScore getScore = new GetScore(this);

        getScore.score = this;

        Intent intent = getIntent();
        String userData = intent.getStringExtra("data");
//        Log.e("Main Page",userData);

//        String reg = "$";
//        String dataarray[] = userData.split(reg);
        int j = 0 , k=0;
        String dataarray[] = new String[4];
        for(int i = 0 ; i < userData.length() ; i++)
        {
            if(userData.charAt(i) == '$'){
//                Log.i("loop" , userData.substring(k , i));
                dataarray[j] = userData.substring(k , i);
                j++;
                k = i+1;
            }
        }
//        dataarray[j] = userData.substring(k , userData.length());

//        for(int i = 0 ; i < 4 ; i++)
//            Log.e("Main", dataarray[i]);


        DemoData.loggedInPlayer.name = dataarray[0];
        DemoData.loggedInPlayer.username = dataarray[1];
        DemoData.loggedInPlayer.no_complete_sets = Integer.parseInt(dataarray[2]);
        DemoData.loggedInPlayer.points = Long.parseLong(dataarray[3]);

        if(DemoData.loggedInPlayer.name.equals("Guest"))
        {
            cont.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            username.setVisibility(View.GONE);
            score.setText(dataarray[3]);
            level.setText(dataarray[2]);
        }
        else {
            cont.setVisibility(View.VISIBLE);
            profile.setVisibility(View.VISIBLE);
//            username.setText(user);
//            DemoData.loggedInPlayer.name = userName;
            username.setVisibility(View.VISIBLE);
            username.setText(DemoData.loggedInPlayer.name);
//            int temp = (DemoData.loggedInPlayer.no_complete_sets + 1);
            level.setText(dataarray[2]);
//            long temp2 = DemoData.loggedInPlayer.points;
//
//            GetScore getScore = new GetScore(this);
//            getScore.execute(userName);


            score.setText(dataarray[3]);
        }

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent=new Intent(MainPage.this,Homepage.class);
                startActivity(intent);
                finish(); //if pressed back goes back of application
            }
        });

        about.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainPage.this,Aboutus.class);
                startActivity(intent);
//                finish(); //if pressed back goes back of application
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent =new Intent(MainPage.this,Help.class);
                startActivity(intent);
//                finish(); //if pressed back goes back of application
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this, "Go to user profile", Toast.LENGTH_SHORT).show();
                openDialog();       //for dialogue popup logout button
            }
        });

        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this, "New game", Toast.LENGTH_SHORT).show();

                Intent intent =new Intent(MainPage.this,Start.class);
                startActivity(intent);
                finish(); //if pressed back goes back of application
            }
        });

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainPage.this, "Continue playing", Toast.LENGTH_SHORT).show();
//                Intent intent =new Intent(MainPage.this,Start.class);
//                startActivity(intent);
//                finish(); //if pressed back goes back of application
            }
        });

        scoreboard=findViewById(R.id.scoreboard);

        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getScore.execute();


//                finish(); //if pressed back goes back of application
            }
        });

    }

    private void openDialog() {
        ExampleDialog exampleDialog = new ExampleDialog();
        exampleDialog.show(getSupportFragmentManager(),"exampledialog");
    }

    @Override
    public void processFinish(String output) {
//        Toast.makeText(this, "scoreboard"+output, Toast.LENGTH_SHORT).show();

        Intent intent2 = new Intent(MainPage.this,Score_board.class);
        Log.e("ggggggg",output);
        intent2.putExtra("datascore" , output);
        startActivity(intent2);


    }

}