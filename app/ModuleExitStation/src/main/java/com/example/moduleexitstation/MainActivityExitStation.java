package com.example.moduleexitstation;

import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.modulecustomer.Ticket;

import java.time.Duration;

public class MainActivityExitStation extends AppCompatActivity {
    Button buttonExitView;
    TextView textViewResultParkingHours;
    TextView textViewResultToPay;
    TextView textViewResultMinuteRate;
    TextView editTextTicketID;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_exit_station);
        buttonExitView = findViewById(R.id.buttonExitView);
        textViewResultParkingHours = findViewById(R.id.textViewResultParkingHours);
        textViewResultToPay = findViewById(R.id.textViewResultToPay);
        textViewResultMinuteRate = findViewById(R.id.textViewResultMinuteRate);
        editTextTicketID = findViewById(R.id.editTextTicketID);

        buttonExitView.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.O)
            @Override
            public void onClick(View view) {
                int TicketID;
                try {
                    TicketID = Integer.parseInt(editTextTicketID.getText().toString());
                } catch (NumberFormatException e) {
                    TicketID = -1;
                }
                if (TicketID == -1)
                    editTextTicketID.setError("You must enter a ticket number");
                else if (!Ticket.isCheckedIn(TicketID)){
                    editTextTicketID.setError("Ticket ID Invalid");
                }
                else
                {
                    Ticket t = Ticket.getTicket(TicketID);
                    Duration TimeParked = t.TimeParked();
                    textViewResultParkingHours.setText(Long.toString(TimeParked.toMinutes()));
                    textViewResultMinuteRate.setText(Integer.toString(t.getMinuteRate()));
                    textViewResultToPay.setText(Long.toString(TimeParked.toMinutes()*t.getMinuteRate()));

                }
            }
        });
    }
}