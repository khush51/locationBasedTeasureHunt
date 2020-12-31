package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
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

    ProgressDialog progressDialog;

    Button about,back_button,help,profile,newgame,cont , scoreboard , logout;
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

        logout = (Button) findViewById(R.id.logout);

//        logout.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                DemoData.loggedInPlayer.username = "";
//                DemoData.loggedInPlayer.points = 0;
//                DemoData.loggedInPlayer.no_complete_sets = 0;
//                startActivity(new Intent(getApplicationContext() , Homepage.class));
//            }
//        });

        progressDialog = new ProgressDialog(this);

        final GetScore getScore = new GetScore(this);

        getScore.score = this;

        Intent intent = getIntent();
        boolean check = intent.getBooleanExtra("new",false);
        String userData = intent.getStringExtra("data");
//        Log.e("Main Page",userData);

//        String reg = "$";
//        String dataarray[] = userData.split(reg);

        if(check) {
            int i, j = 0, k = 0;
            String dataarray[] = new String[15];
            for (i = 0; i < userData.length(); i++) {
                if (userData.charAt(i) == '$') {
//                Log.i("loop" , userData.substring(k , i));
                    dataarray[j] = userData.substring(k, i);
                    j++;
                    k = i + 1;
                }
            }

            DemoData.loggedInPlayer.name = dataarray[0];
            DemoData.loggedInPlayer.username = dataarray[1];
            DemoData.loggedInPlayer.no_complete_sets = (dataarray[2]);
            DemoData.loggedInPlayer.points = (dataarray[3]);

            Log.e("fvsvsv", String.valueOf(DemoData.loggedInPlayer.points));

//        dataarray[j] = userData.substring(k , userData.length());

//        for(int i = 0 ; i < 4 ; i++)
//            Log.e("Main", dataarray[i]);

            Log.e("k ", String.valueOf(k));

            if (userData.length() > k) {

                i = 0;
                String[] temp = new String[10];

                for (j = k; j < userData.length(); j++) {
//                Log.e("j ", String.valueOf(i)+" length"+userData.length());
                    if (userData.charAt(j) == '/') {

                        temp[i] = userData.substring(k, j);
//                    Log.i("loop" , temp[i]);
                        i++;
                        k = j + 1;
                    }
                }

                DemoData.gameDetails.locality = temp[0];
                DemoData.gameDetails.username = dataarray[1];
                DemoData.gameDetails.no_of_location = Integer.parseInt(temp[4]);
                DemoData.gameDetails.latitude = Double.valueOf(temp[1]);
                DemoData.gameDetails.longitude = Double.valueOf(temp[2]);
                DemoData.gameDetails.score = Long.parseLong(temp[3]);


            } else {
                DemoData.gameDetails.username = "null";
            }

        }

//        Log.e("kkkkkkkk",DemoData.loggedInPlayer.name);


        if(DemoData.loggedInPlayer.name.equals("Guest"))
        {
            cont.setVisibility(View.GONE);
            profile.setVisibility(View.GONE);
            username.setVisibility(View.VISIBLE);
//            score.setText(dataarray[3]);
//            level.setText(dataarray[2]);
        }
        else {
            cont.setVisibility(View.VISIBLE);
            profile.setVisibility(View.VISIBLE);
//            username.setText(user);
//            DemoData.loggedInPlayer.name = userName;
            username.setVisibility(View.VISIBLE);

//            int temp = (DemoData.loggedInPlayer.no_complete_sets + 1);

        }

        username.setText(DemoData.loggedInPlayer.name);
        level.setText(DemoData.loggedInPlayer.no_complete_sets);
        score.setText(DemoData.loggedInPlayer.points);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

//                Toast.makeText(getApplicationContext() , "Logging out..." , Toast.LENGTH_SHORT).show();
//                DemoData.loggedInPlayer.username = "";
//                DemoData.loggedInPlayer.no_complete_sets = "0";
//                DemoData.loggedInPlayer.points = "0";
//                DemoData.loggedInPlayer.name = "";
//                DemoData.loggedInPlayer = null;
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
//                Toast.makeText(MainPage.this, "Go to user profile", Toast.LENGTH_SHORT).show();
                openDialog();       //for dialogue popup logout button
            }
        });

        Log.e("sets and points", DemoData.loggedInPlayer.no_complete_sets+" "+DemoData.loggedInPlayer.points);

        newgame.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainPage.this, "New game", Toast.LENGTH_SHORT).show();
                Log.e("main page",DemoData.loggedInPlayer.name);
                DemoData.loggedInPlayer.no_complete_sets = String.valueOf(0);
                DemoData.loggedInPlayer.points = String.valueOf(0);
                Intent intent =new Intent(MainPage.this,Start.class);
                startActivity(intent);
//                finish(); //if pressed back goes back of application
            }
        });

        if(DemoData.gameDetails.username.equals("null")){
            Toast.makeText(MainPage.this, "No Saved Game Exist. Please start a New Game.", Toast.LENGTH_SHORT).show();

            cont.setVisibility(View.GONE);
        }

        cont.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(MainPage.this, "Continue playing", Toast.LENGTH_SHORT).show();

//                if(DemoData.gameDetails.username.equals("null")){
//                    Toast.makeText(MainPage.this, "No Saved Game Exist. Please start a New Game.", Toast.LENGTH_SHORT).show();
//                }
//                else {
                Intent intent =new Intent(MainPage.this,StartNew.class);
//                intent.putExtra("new" , false);
                intent.putExtra("cont",true);
                startActivity(intent);
//                finish(); //if pressed back goes back of application
//                }
            }
        });

        scoreboard=findViewById(R.id.scoreboard);

        scoreboard.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                getScore.execute();
                progressDialog.setTitle("Loading..");
                progressDialog.setMessage("Getting data... please wait..");
                progressDialog.setCancelable(false);
                progressDialog.show();


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
        progressDialog.hide();
        Intent intent2 = new Intent(MainPage.this,Score_board.class);
//        Log.e("ggggggg",output);
        intent2.putExtra("datascore" , output);
        startActivity(intent2);


    }

}