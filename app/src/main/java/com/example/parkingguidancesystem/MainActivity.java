package com.example.parkingguidancesystem;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.example.modulecustomer.MainActivityCustomer;

public class MainActivity extends AppCompatActivity {
        private Button CustomerButton;
    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        CustomerButton = findViewById(R.id.ButtonCustomerModule);
        CustomerButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                LaunchModule(1);
            }
        });

    }



    public void LaunchModule(int choice){
        Intent intent1 = new Intent(this, MainActivityCustomer.class);
/*      Intent intent2 = new Intent(this, com.example.ModuleName.Activity.class);
        Intent intent3 = new Intent(this, com.example.ModuleName.Activity.class);
        Intent intent4 = new Intent(this, com.example.ModuleName.Activity.class);
 */
        switch (choice) {
            case 1:
                startActivity(intent1);
                break;
/*            case 2:
                startActivity(intent2);
                break;
            case 3:
                startActivity(intent3);
                break;
            case 4:
                startActivity(intent4);
                break;
 */
            default:

                }

        }

    }









