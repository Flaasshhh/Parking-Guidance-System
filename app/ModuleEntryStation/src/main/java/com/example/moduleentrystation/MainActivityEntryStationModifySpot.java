package com.example.moduleentrystation;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.example.modulecustomer.Ticket;

import java.util.List;

public class MainActivityEntryStationModifySpot extends AppCompatActivity {

    private Button AddSpotButton ; // Add spot button
    private TextView TextSPotNumber ; // text view spot number
    private EditText EditTextSpotNumber; // edit text spot number
    private Button AddButton ; // Add button




    private Button DeleteSpotButton ; // Delete Spot button
    private TextView textParkinSpot ; //parking spot text
    private Spinner spinnerFreeSpots ; //free spots spinner
    private Button DeleteButton ;

    List <String>  FreeSpotList ;

    /*@Override
    public void onBackPressed() {
        // Start the new Activity
        Intent intent = new Intent(this, MainActivityEntryStation.class);
        startActivity(intent);

        // Finish the current Activity
        finish();
    }*/
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_entry_station_modify_spot);

        // get the free spots list
        FreeSpotList = Ticket.getFreeSpotsList() ;
        // adding the list to spinner
        AddListSpotsToSpinnerfunc(FreeSpotList);




        PageVisibilityDefault() ;

        AddSpotButton = findViewById(R.id.buttonAddNewSpot);
        AddSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddSpotButtonPressed();
            }
        });

        DeleteSpotButton = findViewById(R.id.ButtonDeleteSpot);
        DeleteSpotButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteSpotButtonPressed() ;
            }
        });

        AddButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddButtonPressed(FreeSpotList , String.valueOf(EditTextSpotNumber.getText()));
            }
        });

        DeleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                // Get the selected item position
                int selectedPosition = spinnerFreeSpots.getSelectedItemPosition();

                // Get the selected item text
                String selectedText = spinnerFreeSpots.getItemAtPosition(selectedPosition).toString();
                DeleteButtonPressed(FreeSpotList ,selectedText);
            }
        });





    }

    // default visibilty option when page is opened
    public void PageVisibilityDefault(){

        TextSPotNumber = findViewById(R.id.textModifySpotSpotNumber);
        EditTextSpotNumber = findViewById(R.id.editTextSpotNumber) ;
        AddButton = findViewById(R.id.ButtonAdd) ;
        TextSPotNumber.setVisibility(View.GONE);
        EditTextSpotNumber.setVisibility(View.GONE);
        AddButton.setVisibility(View.GONE);


        textParkinSpot = findViewById(R.id.textModifySpotParkingSpot) ;
        spinnerFreeSpots = findViewById(R.id.spinnerModifySpotParkingSpot) ;
        DeleteButton = findViewById(R.id.ButtonDelete);
        textParkinSpot.setVisibility(View.GONE);
        spinnerFreeSpots.setVisibility(View.GONE);
        DeleteButton.setVisibility(View.GONE);

    }

    public void AddSpotButtonPressed(){
        TextSPotNumber = findViewById(R.id.textModifySpotSpotNumber);
        EditTextSpotNumber = findViewById(R.id.editTextSpotNumber) ;
        AddButton = findViewById(R.id.ButtonAdd) ;
        TextSPotNumber.setVisibility(View.VISIBLE);
        EditTextSpotNumber.setVisibility(View.VISIBLE);
        AddButton.setVisibility(View.VISIBLE);


        textParkinSpot = findViewById(R.id.textModifySpotParkingSpot) ;
        spinnerFreeSpots = findViewById(R.id.spinnerModifySpotParkingSpot) ;
        DeleteButton = findViewById(R.id.ButtonDelete);
        textParkinSpot.setVisibility(View.GONE);
        spinnerFreeSpots.setVisibility(View.GONE);
        DeleteButton.setVisibility(View.GONE);
    }

    public void DeleteSpotButtonPressed(){

        TextSPotNumber = findViewById(R.id.textModifySpotSpotNumber);
        EditTextSpotNumber = findViewById(R.id.editTextSpotNumber) ;
        AddButton = findViewById(R.id.ButtonAdd) ;
        TextSPotNumber.setVisibility(View.GONE);
        EditTextSpotNumber.setVisibility(View.GONE);
        AddButton.setVisibility(View.GONE);


        textParkinSpot = findViewById(R.id.textModifySpotParkingSpot) ;
        spinnerFreeSpots = findViewById(R.id.spinnerModifySpotParkingSpot) ;
        DeleteButton = findViewById(R.id.ButtonDelete);
        textParkinSpot.setVisibility(View.VISIBLE);
        spinnerFreeSpots.setVisibility(View.VISIBLE);
        DeleteButton.setVisibility(View.VISIBLE);
    }

    public void AddListSpotsToSpinnerfunc(List<String> FreeSpotsList ){
        Spinner FreeSpotsSpinner = findViewById(R.id.spinnerModifySpotParkingSpot);
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_item, FreeSpotsList);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        FreeSpotsSpinner.setAdapter(adapter);
    }

    public void AddButtonPressed(List<String> FreeSpotsList , String EditTextInput){

        int check = 0 ;
        int ListSize = FreeSpotsList.size();
        for (int i = 0 ; i<ListSize ; i++){
            if (FreeSpotsList.get(i) == EditTextInput) {
                check = 1 ;
            }
        }
        if (check == 0) {
            FreeSpotsList.add(EditTextInput);
            Ticket.setFreeSpotsList(FreeSpotsList);
        }
    }

    public void DeleteButtonPressed (List <String> FreeSpotsList ,String SpinnerText ){
        FreeSpotsList.remove(SpinnerText) ;
        Ticket.setFreeSpotsList(FreeSpotsList);

        // Get the adapter from the Spinner
        ArrayAdapter<String> adapter = (ArrayAdapter<String>) spinnerFreeSpots.getAdapter();
        // Set the adapter again to refresh the Spinner
        spinnerFreeSpots.setAdapter(adapter);







    }

}