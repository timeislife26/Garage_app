package com.example.garage_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.Arrays;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Items in the layout
        TextView vehicleName = findViewById(R.id.vehicleName);
        ImageView vehicleImage = findViewById(R.id.vehicleImage);
        TextView vehiclePrice = findViewById(R.id.vehiclePrice);
        TextView vehicleDescription = findViewById(R.id.vehicleDescription);
        FloatingActionButton homeFAB = findViewById(R.id.homeFAB);
        FloatingActionButton emailFAB = findViewById(R.id.emailFAB);
        FloatingActionButton phoneFAB = findViewById(R.id.phoneFAB);
        FloatingActionButton mapFAB = findViewById(R.id.mapFAB);




        //extra Types
        Bundle extras = getIntent().getExtras();
        int vehiclePic = 0;
        int vehicleType = 0;
        String[] vehicleList;


        homeFAB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(home);
            }
        });





        if (extras != null) {
            vehiclePic = extras.getInt("vehiclePic");
            vehicleImage.setImageResource(extras.getInt("vehiclePic"));
            //vehicleName = extras.getString("position");
            int position = (int)extras.get("position");
            vehicleType = extras.getInt("vehicleType");
            String quote = "";
            switch (vehicleType){
                case 0:
                    vehicleName.setText(getString(R.string.errorCase));
                    vehiclePrice.setText(getString(R.string.errorCase));
                    vehicleDescription.setText(getString(R.string.errorCase));
                    break;
                case 1:
                    vehicleName.setText(getResources().getStringArray(R.array.cars)[position]);
                    vehiclePrice.setText(getResources().getStringArray(R.array.carPrice)[position]);
                    vehicleDescription.setText(getResources().getStringArray(R.array.carDescription)[position]);
                    quote = getResources().getStringArray(R.array.carQuote)[position];
                    break;
                case 2:
                    vehicleName.setText(getResources().getStringArray(R.array.bikes)[position]);
                    vehiclePrice.setText(getResources().getStringArray(R.array.bikePrice)[position]);
                    vehicleDescription.setText(getResources().getStringArray(R.array.bikeDescription)[position]);
                    quote = getResources().getStringArray(R.array.bikeQuote)[position];
                    break;
                case 3:
                    vehicleName.setText(getResources().getStringArray(R.array.other)[position]);
                    vehiclePrice.setText(getResources().getStringArray(R.array.otherPrice)[position]);
                    vehicleDescription.setText(getResources().getStringArray(R.array.otherDescription)[position]);
                    quote = getResources().getStringArray(R.array.other)[position];
                    break;
            }

            /*This works but I am trying to reduce the amount
            TextView vehicleName = findViewById(R.id.vehicleName);
            vehicleName.setText(extras.getString("position"));
            ImageView vehicleImage = findViewById(R.id.vehicleImage);
            vehicleImage.setImageResource(extras.getInt("vehiclePic"));
            TextView vehiclePrice = findViewById(R.id.vehiclePrice);
            */
            String finalQuote = quote;
            emailFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    composeEmail("Quote:" + finalQuote);
                }
            });
            phoneFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    action_dial(view);
                }
            });
            mapFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_map(view);
                }
            });

        }
    }

    public void action_dial(View view){
        Intent intent1 = new Intent(Intent.ACTION_DIAL);
        intent1.setData(Uri.parse("tel:+353123456789"));
        try {
            startActivity(intent1);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }
    }

    public void open_map(View view){
        Uri location = Uri.parse("geo:0,0?q=1600+Amphitheatre+Parkway,+Mountain+View,+California");
        Intent mapIntent = new Intent(Intent.ACTION_VIEW, location);
        mapIntent.setPackage("com.google.android.apps.maps");
        try {
            startActivity(mapIntent);
        } catch (ActivityNotFoundException e) {
            // Define what your app should do if no activity can handle the intent.
        }
    }


    private void composeEmail(String subject) {
        Intent emailIntent = new Intent(Intent.ACTION_SEND);
        emailIntent.setType("text/plain");
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"Garage@Garage.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        } catch (ActivityNotFoundException e) {
            // Handle if no email app is available
        }
    }
}