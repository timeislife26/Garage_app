package com.example.garage_app;

import androidx.appcompat.app.AppCompatActivity;


import android.content.ActivityNotFoundException;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.request.RequestOptions;
import com.google.android.material.bottomnavigation.BottomNavigationView;
import com.google.android.material.floatingactionbutton.FloatingActionButton;

public class MainActivity3 extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        //Items in the layout
        TextView vehicleName = findViewById(R.id.vehicleName);
        ImageView vehicleImage = findViewById(R.id.vehicleImage);
        TextView vehiclePrice = findViewById(R.id.vehiclePrice);
        TextView vehicleYear = findViewById(R.id.vehicleYear);
        TextView vehicleQuote = findViewById(R.id.vehicleQuote);
        FloatingActionButton browserFAB = findViewById(R.id.BrowserFAB);
        FloatingActionButton emailFAB = findViewById(R.id.emailFAB);
        FloatingActionButton phoneFAB = findViewById(R.id.phoneFAB);
        FloatingActionButton mapFAB = findViewById(R.id.mapFAB);

        BottomNavigationView bottomNavigationView = findViewById(R.id.bottomNavigationView);
        bottomNavigationView.setOnItemSelectedListener(item -> {
            Intent navIntent = null;
            if (item.getItemId() == R.id.navHome) {
                navIntent = new Intent(MainActivity3.this, MainActivity.class);
                startActivity(navIntent);
            }
            else
                finish();
            return true;
        });


        //extra Types
        Bundle extras = getIntent().getExtras();
        int vehiclePic = 0;
        int vehicleType = 0;
        String[] vehicleList;

        if (extras != null) {
            vehiclePic = extras.getInt("vehiclePic");
            Glide.with(this)
                    .load(vehiclePic)
                    .fitCenter()
                    .apply(new RequestOptions().override(1500, 800))
                    .into(vehicleImage);



            int position = (int)extras.get("position");
            vehicleType = extras.getInt("vehicleType");
            String quote = "";
            String website = "";
            switch (vehicleType){
                case 0:
                    vehicleName.setText(getString(R.string.errorCase));
                    vehiclePrice.setText(getString(R.string.errorCase));
                    vehicleYear.setText(getString(R.string.errorCase));
                    break;
                case 1:
                    vehicleName.setText("Name: " + getResources().getStringArray(R.array.cars)[position]);
                    vehiclePrice.setText("Price: " + getResources().getStringArray(R.array.carPrice)[position]);
                    vehicleYear.setText("Year: " + getResources().getStringArray(R.array.carYear)[position]);
                    quote = getResources().getStringArray(R.array.carQuote)[position];
                    vehicleQuote.setText("Quote: " + quote);
                    website = getResources().getStringArray(R.array.carLink)[position];
                    break;
                case 2:
                    vehicleName.setText("Name: " + getResources().getStringArray(R.array.bikes)[position]);
                    vehiclePrice.setText("Price: " + getResources().getStringArray(R.array.bikePrice)[position]);
                    vehicleYear.setText("Year: " + getResources().getStringArray(R.array.bikeYear)[position]);
                    quote = getResources().getStringArray(R.array.bikeQuote)[position];
                    vehicleQuote.setText("Quote: " + quote);
                    website = getResources().getStringArray(R.array.bikeLink)[position];

                    break;
                case 3:
                    vehicleName.setText("Name: " + getResources().getStringArray(R.array.other)[position]);
                    vehiclePrice.setText("Price: " + getResources().getStringArray(R.array.otherPrice)[position]);
                    vehicleYear.setText("Year: " + getResources().getStringArray(R.array.otherYear)[position]);
                    quote = getResources().getStringArray(R.array.otherQuote)[position];
                    vehicleQuote.setText("Quote: " + quote);
                    website = getResources().getStringArray(R.array.otherLink)[position];
                    break;
            }

            String finalWebsite = website;
            browserFAB.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    open_Browser(view, finalWebsite);
                }
            });

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
    public void open_Browser(View view, String website){
        Uri uri = Uri.parse(website);
        Intent intent = new Intent(Intent.ACTION_VIEW,uri);
        try {
            startActivity(intent);
        } catch (ActivityNotFoundException e){

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
        Intent emailIntent = new Intent(Intent.ACTION_SENDTO);
        emailIntent.setData(Uri.parse("mailto:"));
        emailIntent.putExtra(Intent.EXTRA_EMAIL, new String[] {"Garage@Garage.com"});
        emailIntent.putExtra(Intent.EXTRA_SUBJECT, subject);

        try {
            startActivity(Intent.createChooser(emailIntent, "Send Email"));
        } catch (ActivityNotFoundException e) {
            // Handle if no email app is available
        }
    }
}