package com.example.garage_app;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

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


        //extra Types
        Bundle extras = getIntent().getExtras();
        int vehiclePic = 0;
        int vehicleType = 0;
        String[] vehicleList;

        if (extras != null) {
            vehiclePic = extras.getInt("vehiclePic");
            vehicleImage.setImageResource(extras.getInt("vehiclePic"));
            //vehicleName = extras.getString("position");
            int position = (int)extras.get("position");
            vehicleType = extras.getInt("vehicleType");
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
                    break;
                    case 2:
                    vehicleName.setText(getResources().getStringArray(R.array.bikes)[position]);
                    vehiclePrice.setText(getResources().getStringArray(R.array.bikePrice)[position]);
                    vehicleDescription.setText(getResources().getStringArray(R.array.bikeDescription)[position]);
                    break;
                    case 3:
                    vehicleName.setText(getResources().getStringArray(R.array.other)[position]);
                    vehiclePrice.setText(getResources().getStringArray(R.array.otherPrice)[position]);
                    vehicleDescription.setText(getResources().getStringArray(R.array.otherDescription)[position]);
                    break;
            }

            /*This works but I am trying to reduce the amount
            TextView vehicleName = findViewById(R.id.vehicleName);
            vehicleName.setText(extras.getString("position"));
            ImageView vehicleImage = findViewById(R.id.vehicleImage);
            vehicleImage.setImageResource(extras.getInt("vehiclePic"));
            TextView vehiclePrice = findViewById(R.id.vehiclePrice);
            */


        }
    }
}