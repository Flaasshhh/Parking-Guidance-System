package com.example.moduleentrystation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.ArrayAdapter;

import com.example.modulecustomer.Ticket;

import java.util.ArrayList;
import java.util.List;


public class MainActivityEntryStation extends AppCompatActivity {

    // this will be used for adding list items to spinner
    private Spinner ParkinSpotSpinner ;

    private Button ModifySpotButton ;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry_station);



        if (!Ticket.isIsGenratedFreeSpots()) {
            Ticket.GenerateDataFreeSpotsList(); // this will be moved to the Admin module
            Ticket.setIsGenratedFreeSpots(true);
            }

        List<String> FreeSpotsListReturn = Ticket.getFreeSpotsList() ;

        // Pass the List to SuggestFreeSpotfunc to suggest the nearest spot on screen
        SuggestFreeSpotfunc(FreeSpotsListReturn);

        //Adding Intialized Free spots to the parking spot drop down box
        AddListSpotsToSpinnerfunc(FreeSpotsListReturn);

        ModifySpotButton = findViewById(R.id.ButtonModifySpot) ;
        Intent ModifySpotActivity = new Intent(this , MainActivityEntryStationModifySpot.class) ;

        ModifySpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                startActivity(ModifySpotActivity);
            }
        });




    }

    @Override
    protected void onStart() {
        super.onStart();
        List<String> FreeSpotsListReturn = Ticket.getFreeSpotsList() ;

        // Pass the List to SuggestFreeSpotfunc to suggest the nearest spot on screen
        SuggestFreeSpotfunc(FreeSpotsListReturn);

        //Adding Intialized Free spots to the parking spot drop down box
        AddListSpotsToSpinnerfunc(FreeSpotsListReturn);

    }


    // This function is to suggest The nearest parking spot then appear it on text view on screen
    public void SuggestFreeSpotfunc (List<String> FreeSpotsList ){

        TextView SuggestedSpotTextView = findViewById(R.id.textEntryStationSugesstedSpotOut) ;
        SuggestedSpotTextView.setText(Ticket.getFreeSpotsList().get(0));
    }

    // This function will be used to add free spots to the parking spot drop down box
    public void AddListSpotsToSpinnerfunc(List<String> FreeSpotsList ){
        Spinner FreeSpotsSpinner = findViewById(R.id.spinnerParkingSpot);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FreeSpotsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FreeSpotsSpinner.setAdapter(adapter);

    }

}