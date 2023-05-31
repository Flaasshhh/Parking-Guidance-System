package com.example.parkingguidancesystem;

import java.util.ArrayList;
import java.util.List;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import com.example.modulecustomer.MainActivityCustomer;
import com.example.modulecustomer.Ticket;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
        private Button ButtonCustomer;
        private Button ButtonEntryStation;
        private Button ButtonExitStation;
        private Button ButtonAdmin;



    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButtonCustomer = findViewById(R.id.ButtonCustomerModule);
        ButtonEntryStation = findViewById(R.id.ButtonEntryStationModule);
        ButtonExitStation = findViewById(R.id.ButtonExitStationModule);
        ButtonAdmin = findViewById(R.id.ButtonAdminModule);


        ButtonCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchModule(1);
            }
        });
        ButtonEntryStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchModule(2);
            }
        });

        ButtonExitStation.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchModule(3);
            }
        });






    }



    public void LaunchModule(int choice){
        Intent intent1 = new Intent(this, MainActivityCustomer.class);
        Intent intent2 = new Intent(this, com.example.moduleentrystation.MainActivityEntryStation.class);
        Intent intent3 = new Intent(this, com.example.moduleexitstation.MainActivityExitStation.class);
        /*         Intent intent4 = new Intent(this, com.example.ModuleName.Activity.class);
 */
        switch (choice) {
            case 1:
                startActivity(intent1);
                break;
            case 2:
                startActivity(intent2);
                break;
           case 3:
                startActivity(intent3);
                break;
  /*           case 4:
                startActivity(intent4);
                break;
 */
            default:

                }

        }

    }









