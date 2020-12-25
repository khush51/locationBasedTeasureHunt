package com.example.treasurehunt;

import android.location.Location;

import java.util.ArrayList;

public class LocationClass {

    String locality;
    Double latitude;
    Double longitude;
    ArrayList<String> clues;

    LocationClass(){
        locality = null;
        latitude = 0.0;
        longitude = 0.0;
        clues = new ArrayList<String>();
    }

}
