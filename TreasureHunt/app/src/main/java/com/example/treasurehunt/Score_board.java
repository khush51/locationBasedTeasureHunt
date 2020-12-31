package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class Score_board extends AppCompatActivity {

    ListView listView;

    Button back_button;




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_score_board);

        listView = (ListView) findViewById(R.id.listView);

        back_button=findViewById(R.id.back_buttton);

        Intent intent = getIntent();
        String data = intent.getStringExtra("datascore");
        Log.e("big",data);
//        startActivity(intent);

        int i, j = 0 , k=0, l;
        String scoredata[] = new String[4];
        for( i = 0 ; i < data.length() ; i++)
        {
            if(data.charAt(i) == '$'){

                scoredata[j] = data.substring(k , i);
//                Log.i("loop" , scoredata[j]);
                j++;
                k = i+1;
            }
        }
//        scoredata[j] = data.substring(k , data.length());

//        Log.e("llll","goooooo");
        i = 0;
        j = 0;
        k = 0;
        l = 0;

        ArrayList<Scores> gamescore = new ArrayList<Scores>();

        String[] temp = new String[15];

        while(scoredata[i] != null){
            Scores game = new Scores();
            k = 0;
            l = 0;

//            Log.e("score 222",scoredata[i]);
            for(j = 0 ; j < scoredata[i].length() ; j++)
            {
                if(scoredata[i].charAt(j) == '/'){

                    temp[l] = scoredata[i].substring(k , j);
//                    Log.i("loop" , temp[l]);
                    l++;
                    k = j+1;
                }
            }
            temp[l] = scoredata[i].substring(k , scoredata[i].length());
//            Log.e("score 333",temp[i]);

            game.username = temp[0];
            game.level = temp[1];
            game.score = temp[2];
//            Log.e("score 333",temp[2]);
            gamescore.add(game);
//            Log.e("ganescore",gamescore.get(i).username);

            i++;

        }

//        MyAdapter myAdapter = new MyAdapter(this , android.R.layout.simple_list_item_1 , game);
//
//        for(i=0;i<gamescore.size();i++)
//            Log.e("gamecheck",gamescore.get(i).username);


        MyAdapter myAdapter = new MyAdapter(this , android.R.layout.simple_list_item_1 , gamescore);

        listView.setAdapter(myAdapter);


        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Score_board.this, "exiting..", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Score_board.this,MainPage.class);
                startActivity(intent);
                //finish();
            }
        });



        //recyclerView=findViewById(R.id.recyclerView);


        //recyclerView.setItemAnimator(new DefaultItemAnimator());
        //recyclerView.setLayoutManager(new LinearLayoutManager(this,RecyclerView.VERTICAL,false));

    }

    private class MyAdapter extends ArrayAdapter<Scores> {

        ArrayList<Scores> game;
        Context context;

        public MyAdapter(Context context, int simple_list_item_1, ArrayList<Scores> game) {

//            Log.e("score444",game[0].username);

            super(context , simple_list_item_1 , game);
            this.context = context;
            this.game = game;

        }

        @Override
        public View getView(int position , View convertView , ViewGroup parent){
            ViewHolder temp = null;
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Activity.LAYOUT_INFLATER_SERVICE);
            if(convertView == null)
                convertView = inflater.inflate(R.layout.eachuser_score , null);

//            NumbersView currentNumberPosition = getItem(position);

            ViewHolder viewHolder = new ViewHolder();

            viewHolder.username = (TextView) convertView.findViewById(R.id.scusername);
            viewHolder.scores = (TextView) convertView.findViewById(R.id.scscore);
            viewHolder.level = (TextView) convertView.findViewById(R.id.sclevels);

            Log.i("position", game.get(position).username);

            viewHolder.username.setText(game.get(position).username);
            viewHolder.level.setText(game.get(position).level);
            viewHolder.scores.setText(game.get(position).score);

            return convertView;

        }

    }

    public class ViewHolder{

        TextView username , level , scores;

    }

}