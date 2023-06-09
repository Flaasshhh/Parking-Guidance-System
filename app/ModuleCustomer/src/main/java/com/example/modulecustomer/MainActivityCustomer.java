package com.example.modulecustomer;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;

import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Time;
import java.time.Duration;
import java.time.format.DateTimeFormatter;

public class MainActivityCustomer extends AppCompatActivity {
    Button ButtonPrintTicket;
    Button ButtonCalculatePrice;
    TextView EditTextPlateID;
    TextView EditTextTicketID;
    TextView TextViewResultCheckIn;
    TextView TextViewResultCheckOut;
    Button ButtonCheckIn;
    Button ButtonCheckOut;

    Button ButtonClearCheckIn;
    ConstraintLayout ConstraintLayoutCheckIn;
    ConstraintLayout ConstraintLayoutCheckOut;

    Button ButtonCheckSpot;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);
        Ticket ticket;

        // Declarations
        ConstraintLayoutCheckIn = findViewById(R.id.LayoutCheckIn);
        ConstraintLayoutCheckOut = findViewById(R.id.LayoutCheckOut);

        ButtonPrintTicket = findViewById(R.id.ButtonPrintTicket);
        ButtonCalculatePrice = findViewById(R.id.ButtonCalculatePrice);

        EditTextPlateID = findViewById(R.id.editTextPlateID);
        EditTextTicketID = findViewById(R.id.editTextTicketID);

        TextViewResultCheckIn = findViewById(R.id.textViewResultCheckIn);
        TextViewResultCheckOut = findViewById(R.id.textViewResultCheckOut);

        ButtonClearCheckIn = findViewById(R.id.ButtonClearCheckIn);
        ButtonCheckIn = findViewById(R.id.ButtonCheckIn);
        ButtonCheckOut = findViewById(R.id.ButtonCheckOut);
        ButtonCheckSpot = findViewById(R.id.ButtonCheckSpot);


        // Initializations
        ConstraintLayoutCheckIn.setVisibility(View.GONE);
        ConstraintLayoutCheckOut.setVisibility(View.GONE);
        ButtonCheckSpot.setEnabled(false);
        ButtonCheckSpot.setBackgroundColor(android.graphics.Color.parseColor("#445c5f60"));

        ButtonCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = getWindow().getDecorView().getRootView();

                // Get the InputMethodManager instance
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);

                // Hide the soft keyboard
                imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
                ConstraintLayoutCheckIn.setVisibility(View.VISIBLE);
                ConstraintLayoutCheckOut.setVisibility(View.GONE);
            }
        });

        ButtonCheckOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                View view1 = getWindow().getDecorView().getRootView();
                // Get the InputMethodManager instance
                InputMethodManager imm = (InputMethodManager) getSystemService(Context.INPUT_METHOD_SERVICE);
                // Hide the soft keyboard
                imm.hideSoftInputFromWindow(view1.getWindowToken(), 0);
                ConstraintLayoutCheckOut.setVisibility(View.VISIBLE);
                ConstraintLayoutCheckIn.setVisibility(View.GONE);
            }
        });

        ButtonPrintTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PlateNUM = EditTextPlateID.getText().toString();
                if (PlateNUM.equals(""))
                    EditTextPlateID.setError("You must enter your plate number");
                else if (Ticket.isCheckedIn(PlateNUM)) {
                    TextViewResultCheckIn.setText("Car With Plate: " + PlateNUM + "\nAlready Checked in \nwith ticket ID: " + Ticket.getTicket(PlateNUM).getTicketID());
                    ButtonCheckSpot.setEnabled(true);
                    ButtonCheckSpot.setBackgroundColor(android.graphics.Color.parseColor("#ff5c5f60"));
                }
                else {
                    Ticket t = new Ticket(PlateNUM);
                    Ticket.addToTicketList(t);
                    Ticket.setCurrentT(t);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm a");
                    TextViewResultCheckIn.setText("Ticket ID: " + t.getTicketID() + "\nEntry Time: " + t.getEntryTimeStamp().format(formatter).toString());
                    ButtonCheckSpot.setEnabled(true);
                    ButtonCheckSpot.setBackgroundColor(android.graphics.Color.parseColor("#ff5c5f60"));
                     EditTextPlateID.setText("");
                }
            }
        });
        ButtonCalculatePrice.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int TicketID;
                try {
                    TicketID = Integer.parseInt(EditTextTicketID.getText().toString());
                } catch (NumberFormatException e) {
                    TicketID = -1;
                }

                if (TicketID == -1)
                    EditTextTicketID.setError("You must enter your ticket number");
                else if (!Ticket.isCheckedIn(TicketID)){
                    TextViewResultCheckOut.setText("Ticket ID Invalid");}
                else
                {
                    Ticket t = Ticket.getTicket(TicketID);
                    Duration TimeParked = t.TimeParked();
                    TextViewResultCheckOut.setText("Minutes Parked: " + TimeParked.toMinutes() + "\nMinute Rate: EGP " + t.getMinuteRate() +
                            "\nPlease pay EGP " + TimeParked.toMinutes()*t.getMinuteRate() + " to the exit station operator");
                }
            }
        });
        ButtonCheckSpot.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                    Ticket t = Ticket.getCurrentT();
                    if(t.getParkingSpot() == 0)
                        showToast(MainActivityCustomer.this, "Spot Not Assigned Yet, Please provide \nTicket ID to Entry Station Operator");
                    else
                        showToast(MainActivityCustomer.this, "Parking Spot " + t.getParkingSpot() + " is reserved for you");
                }
        });
        ButtonClearCheckIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                EditTextPlateID.setText("");
                TextViewResultCheckIn.setText("");
                ButtonCheckSpot.setEnabled(false);
                ButtonCheckSpot.setBackgroundColor(android.graphics.Color.parseColor("#445c5f60"));
            }
        });
    }
    private void showToast(Context context, String message){
        Toast.makeText(context, message, Toast.LENGTH_SHORT).show();
    }
}

