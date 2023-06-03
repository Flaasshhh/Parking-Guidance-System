package com.example.moduleentrystation;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.PopupWindow;
import android.widget.Spinner;
import android.widget.TextView;
import android.app.Activity;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import java.io.Console;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class MainActivityEntryStation extends AppCompatActivity {

    // this will be used for adding list items to spinner
    private Spinner ParkinSpotSpinner ;
    Button ButtonAddSpot;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry_station);


        // Initiallize free spots & store it in a list
        List<String> FreeSpotsListReturn = IntializeFreeSpotsfunc();

        // Pass the List to SuggestFreeSpotfunc to suggest the nearest spot on screen
        SuggestFreeSpotfunc(FreeSpotsListReturn);

        //Adding Intialized Free spots to the parking spot drop down box
        AddListSpotsToSpinnerfunc(FreeSpotsListReturn);
        ButtonAddSpot = findViewById(R.id.ButtonAddSpot);
        ButtonAddSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PopupWindow hi = new PopupWindow();
            }
        });


    }

    //This function is to add the initial free spots in app
    public List <String> IntializeFreeSpotsfunc (){

        List<String> FreeSpotsList = new ArrayList<>();
        FreeSpotsList.add("1");
        FreeSpotsList.add("2");
        FreeSpotsList.add("5");
        FreeSpotsList.add("6");
        FreeSpotsList.add("7");
        FreeSpotsList.add("8");

        return FreeSpotsList ;
    }

    // This function is to suggest The nearest parking spot then appear it on text view on screen
    public void SuggestFreeSpotfunc (List<String> FreeSpotsList ){

        TextView SuggestedSpotTextView = findViewById(R.id.textEntryStationSugesstedSpotOut) ;
        SuggestedSpotTextView.setText(FreeSpotsList.get(0));
    }

    // This function will be used to add free spots to the parking spot drop down box
    public void AddListSpotsToSpinnerfunc(List<String> FreeSpotsList ){
        Spinner FreeSpotsSpinner = findViewById(R.id.spinnerParkingSpot);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FreeSpotsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FreeSpotsSpinner.setAdapter(adapter);
    }

}