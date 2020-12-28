package com.example.treasurehunt;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static androidx.constraintlayout.motion.widget.Debug.getLocation;

public class Clues extends AppCompatActivity {

    Button back_button , next , show , confirm;
    Animation topAnim,bottomAnim,middleAnim;
    TextView clue;
    FusedLocationProviderClient fusedLocationProviderClient;

    SupportMapFragment svm;

    String username, locality;

    Boolean correct = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_clues);


        clue= findViewById(R.id.clue);
        show = (Button) findViewById(R.id.show);
        confirm = (Button) findViewById(R.id.confirm);
        confirm.setVisibility(View.GONE);

        //animations
        topAnim= AnimationUtils.loadAnimation(this,R.anim.top_animation);
        bottomAnim= AnimationUtils.loadAnimation(this,R.anim.bottom_animation);
        middleAnim= AnimationUtils.loadAnimation(this,R.anim.middle_animation);

        svm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        clue.setAnimation(topAnim); //animation for clue
        //end of animation
/*
        if(DemoData.loggedInPlayer != null)
        {
            for(int i = 0 ; i < DemoData.gameDetails.size() ; i++){

            }
        }

 */
        Log.e("clue",DemoData.loggedInPlayer.name);

        Intent getintent = getIntent();
        String data = getintent.getStringExtra("data");
        boolean ifnew = getintent.getBooleanExtra("new",false);

        if(ifnew) {


            int i, j = 0, k = 0, l, flag = 0;
            String gamedata[] = new String[10];
            for (i = 0; i < data.length(); i++) {
                if (data.charAt(i) == '$') {

                    gamedata[j] = data.substring(k, i);
                    Log.i("loop", gamedata[j]);
                    j++;
                    k = i + 1;
                }
            }
//        scoredata[j] = data.substring(k , data.length());

            Log.e("llll", "goooooo");
            i = 0;
            j = 0;
            k = 0;
            l = 0;

            ArrayList<LocationClass> locationclue = new ArrayList<LocationClass>();

            String[] temp = new String[4];

            while (gamedata[i] != null) {
                LocationClass locationClass = new LocationClass();
                k = 0;
                l = 0;

                Log.e("score 222", gamedata[i]);
                for (j = 0; j < gamedata[i].length(); j++) {
                    if (gamedata[i].charAt(j) == '/') {

                        temp[l] = gamedata[i].substring(k, j);
                        Log.i("loop", temp[l]);
                        l++;
                        k = j + 1;
                    }
                }
                temp[l] = gamedata[i].substring(k, gamedata[i].length());
                Log.e("score 333", temp[l]);

                DemoData.gameDetails.locality = temp[0];

                locationClass.locality = temp[0];
                locationClass.longitude = Double.valueOf(temp[1]);
                locationClass.latitude = Double.valueOf(temp[2]);
                locationClass.clues.add(temp[3]);
//            Log.e("score 333",temp[2]);
                flag++;
                if (flag % 3 == 0) {
                    locationclue.add(locationClass);
                    DemoData.locations.add(locationClass);
                }

                Log.e("ganescore", locationClass.locality + "" + locationClass.clues.get(0));

                i++;

            }
        }

//        Log.i("check", String.valueOf(DemoData.loggedInPlayer.no_complete_sets));



        Log.e("continuekhatir" , String.valueOf(DemoData.locations.size()) + " " + DemoData.loggedInPlayer.no_complete_sets);

        if(DemoData.locations.size() <= Integer.parseInt(DemoData.loggedInPlayer.no_complete_sets)) {
            clue.setText(DemoData.locations.get(0).clues.get(0));
            DemoData.gameDetails.locality = DemoData.locations.get(0).locality;
            DemoData.gameDetails.latitude = DemoData.locations.get(0).latitude;
            DemoData.gameDetails.longitude = DemoData.locations.get(0).longitude;
        }
        else {
            clue.setText(DemoData.locations.get(Integer.parseInt(DemoData.loggedInPlayer.no_complete_sets) - 1).clues.get(0));
            DemoData.gameDetails.locality = DemoData.locations.get(Integer.parseInt(DemoData.loggedInPlayer.no_complete_sets)-1).locality;
            DemoData.gameDetails.latitude = DemoData.locations.get(Integer.parseInt(DemoData.loggedInPlayer.no_complete_sets)-1).latitude;
            DemoData.gameDetails.longitude = DemoData.locations.get(Integer.parseInt(DemoData.loggedInPlayer.no_complete_sets)-1).longitude;
        }


        back_button=findViewById(R.id.back_buttton);

        back_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(Clues.this, "exiting..", Toast.LENGTH_SHORT).show();
                Intent intent= new Intent(Clues.this , MainPage.class);
                startActivity(intent);
                finish();
            }
        });

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getCurrentLocation();
                confirm.setVisibility(View.VISIBLE);
            }
        });

        confirm.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Log.e("clue",DemoData.loggedInPlayer.name);
                if(correct == true)
                {
                    startActivity(new Intent(getApplicationContext() , Correct_ans.class));
                }
                else
                {
                    startActivity(new Intent(getApplicationContext() , Wrong_ans.class));
                }
            }
        });

    }
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(Clues.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                final Location location = task.getResult();
                LocationClass temp = new LocationClass();
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(Clues.this, Locale.getDefault());

                        List<Address> addressList = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );

                        temp.latitude = addressList.get(0).getLatitude();
                        temp.longitude = addressList.get(0).getLongitude();
                        temp.locality = addressList.get(0).getLocality();

                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    svm.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {
                            if(distancebetween(location.getLatitude() , location.getLongitude() , DemoData.locations.get(Integer.parseInt(DemoData.loggedInPlayer.no_complete_sets)-2).latitude , DemoData.locations.get(Integer.parseInt(DemoData.loggedInPlayer.no_complete_sets)-1).longitude) < 0.01)
                            {
//                                Toast.makeText(getApplicationContext() , "done" , Toast.LENGTH_SHORT).show();

                                correct = true;
                            }
                            else
                            {
//                                Toast.makeText(getApplicationContext() , "not done" , Toast.LENGTH_SHORT).show();
                                correct = false;
                            }

                            LatLng latLng = new LatLng(location.getLatitude() , location.getLongitude());
                            MarkerOptions options = new MarkerOptions().position(latLng).title("current");
                            googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng , 17));
                            googleMap.addMarker(options);

                        }
                    });

                }
            }

            private double distancebetween(double latitude, double longitude, double v, double v1) {

                double earthRadius = 3958.75; // in miles, change to 6371 for kilometer output

                double dLat = Math.toRadians(v-latitude);
                double dLng = Math.toRadians(v1-longitude);

                double sindLat = Math.sin(dLat / 2);
                double sindLng = Math.sin(dLng / 2);

                double a = Math.pow(sindLat, 2) + Math.pow(sindLng, 2)
                        * Math.cos(Math.toRadians(latitude)) * Math.cos(Math.toRadians(longitude));

                double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1-a));

                double dist = earthRadius * c;

                return dist; // output distance, in MILES

            }
        });
    }
}

