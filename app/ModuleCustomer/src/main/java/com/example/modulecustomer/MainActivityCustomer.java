package com.example.modulecustomer;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.graphics.drawable.GradientDrawable;
import android.os.Bundle;
import android.view.KeyEvent;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.time.format.DateTimeFormatter;

public class MainActivityCustomer extends AppCompatActivity {
    Button ButtonPrintTicket;
    TextView EditTextPlateID;
    TextView TextViewResult;
    Boolean PlateEdited;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_customer);
        ButtonPrintTicket = findViewById(R.id.ButtonPrintTicket);
        EditTextPlateID = findViewById(R.id.editTextPlateID);
        TextViewResult = findViewById(R.id.textViewResult);
        EditTextPlateID.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView textView, int i, KeyEvent keyEvent) {

                return false;
            }
        });
        ButtonPrintTicket.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String PlateNUM = EditTextPlateID.getText().toString();
                if (PlateNUM.equals(""))
                    EditTextPlateID.setError("You must enter your plate number");
                else if (Ticket.isParked(PlateNUM)) {
                    TextViewResult.setText("Car With Plate: " + PlateNUM + "\nAlready Checked in with ticked ID" + Ticket.getTicket(PlateNUM).getTicketID());
                }
                else {
                    Ticket t = new Ticket(PlateNUM);
                    Ticket.addToTicketList(t);
                    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy, HH:mm:ss");
                    TextViewResult.setText("Ticket ID: " + t.getTicketID() + "\nEntry Time: " + t.getEntryTimeStamp().format(formatter).toString());
                }
            }
        });
    }
}

