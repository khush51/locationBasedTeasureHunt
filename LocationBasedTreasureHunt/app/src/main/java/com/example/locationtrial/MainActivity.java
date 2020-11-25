package com.example.locationtrial;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Bundle;
import android.view.View;
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
import java.util.List;
import java.util.Locale;

public class MainActivity extends AppCompatActivity {

    Button show , map;
    TextView text1, text2, text3, text4, text5;

    Location locationToBePassed;

    FusedLocationProviderClient fusedLocationProviderClient;

    SupportMapFragment svm;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        show = (Button) findViewById(R.id.show);
        text1 = (TextView) findViewById(R.id.text);
        text2 = (TextView) findViewById(R.id.text2);
        text3 = (TextView) findViewById(R.id.text3);
        text4 = (TextView) findViewById(R.id.text4);
        text5 = (TextView) findViewById(R.id.text5);

        svm = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.map);

        fusedLocationProviderClient = LocationServices.getFusedLocationProviderClient(this);

        show.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLocation();
                /*
                if (ActivityCompat.checkSelfPermission(MainActivity.this, Manifest.permission.ACCESS_FINE_LOCATION) == PackageManager.PERMISSION_GRANTED) {
                    getLocation();
                } else {
                    ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
                }

                 */
            }
        });
    }

    private void getLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(MainActivity.this, new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 44);
            return;
        }
        fusedLocationProviderClient.getLastLocation().addOnCompleteListener(new OnCompleteListener<Location>() {
            @Override
            public void onComplete(@NonNull Task<Location> task) {
                Location location = task.getResult();
                locationToBePassed = location;
                if (location != null) {
                    try {
                        Geocoder geocoder = new Geocoder(MainActivity.this, Locale.getDefault());

                        List<Address> addressList = geocoder.getFromLocation(
                                location.getLatitude(), location.getLongitude(), 1
                        );
                        text1.setText("latitude: " + addressList.get(0).getLatitude());
                        text2.setText("longitude: " + addressList.get(0).getLongitude());
                        text3.setText("country: " + addressList.get(0).getCountryName());
                        text4.setText("locality: " + addressList.get(0).getLocality());
                        text5.setText("address line: " + addressList.get(0).getAddressLine(0));
                    } catch (IOException e) {
                        e.printStackTrace();
                    }

                    svm.getMapAsync(new OnMapReadyCallback() {
                        @Override
                        public void onMapReady(GoogleMap googleMap) {

                            if(distancebetween(location.getLatitude() , location.getLongitude() , location.getLatitude() , location.getLongitude()) < 0.1)
                                Toast.makeText(getApplicationContext() , "done" , Toast.LENGTH_SHORT).show();
                            else
                                Toast.makeText(getApplicationContext() , "not done" , Toast.LENGTH_SHORT).show();
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