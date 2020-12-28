package com.example.treasurehunt;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class StartNew extends AppCompatActivity implements AsyncResponse {

    TextView location;
    Spinner s2;
    Button back_button,submit_location;
    String locality;
    ProgressDialog progressDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start_new);

        final GetGameData getGameData = new GetGameData();
        getGameData.value = this;

        progressDialog = new ProgressDialog(this);

        Intent intent = getIntent();
        Boolean cont = intent.getBooleanExtra("cont",false);
        if(cont){
            locality = DemoData.gameDetails.locality;
            Log.e("startnew",locality);
            getGameData.execute(locality);
            progressDialog.setTitle("");
            progressDialog.setMessage("Loading Game... Please wait..");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }

        locality = "Siolim";

        submit_location=findViewById(R.id.submit_location);

        back_button=findViewById(R.id.back_buttton);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
//                Toast.makeText(startNew.this, "exiting..", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(StartNew.this,Start.class);
                startActivity(intent);
//                finish();
            }
        });

        s2=(Spinner)findViewById(R.id.s2);

        ArrayAdapter<CharSequence> adapter=ArrayAdapter.createFromResource(this,R.array.spin_list,android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        s2.setAdapter(adapter);

        s2.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                locality = parent.getItemAtPosition(position).toString();

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });




        submit_location.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(StartNew.this,"location submitted", Toast.LENGTH_SHORT).show();
                Log.e("start new",DemoData.loggedInPlayer.name);
                getGameData.execute(locality);

                progressDialog.setTitle("New Game");
                progressDialog.setMessage("Loading Game... Please wait..");
                progressDialog.setCancelable(false);
                progressDialog.show();

//                Intent intent= new Intent(startNew.this,Clues.class);
//               // DemoData.selectedLocality = "Siolim";//s2.getSelectedItem().toString();
//                startActivity(intent);
//                finish();
            }
        });

    }

    @Override
    public void processFinish(String output) {
        Log.e("start new : game data",output);
        progressDialog.hide();
        Intent clueintent= new Intent(StartNew.this,Clues.class);
        clueintent.putExtra("data",output);
        clueintent.putExtra("new",true);
        startActivity(clueintent);
    }
}