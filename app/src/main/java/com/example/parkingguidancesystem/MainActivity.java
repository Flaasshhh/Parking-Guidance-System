package com.example.parkingguidancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
        private Button CustomerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomerButton = findViewById(R.id.CustomerButton);
        CustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchModule(1);
            }
        });

    }



    public void LaunchModule(int choice){
        /*Intent intent1 = new Intent(this, )
        switch (choice) {
            case 1:
                startActivity(intent1);
                break;
            // Add additional cases if needed
            default:
                // Handle default case if necessary
                */
        }

    }
