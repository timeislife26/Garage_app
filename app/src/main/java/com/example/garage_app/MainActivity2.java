package com.example.garage_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.google.android.material.floatingactionbutton.FloatingActionButton;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        FloatingActionButton homeBtn = findViewById(R.id.homeFAB);

        homeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent home = new Intent(MainActivity2.this, MainActivity.class);
                startActivity(home);
            }
        });

        Bundle extras = getIntent().getExtras();
        int vehicleType = 0;
        Resources res = getResources();
        if (extras != null)
            vehicleType = extras.getInt("vehicle");
        ArrayList<String> vehicleNames = new ArrayList<>();
        ArrayList<String> vehiclePrices = new ArrayList<>();
        ArrayList<Integer> vehiclePic = new ArrayList<>();
        String[] vehicleList;
        String[] vehiclePrice;
        if (vehicleType == 1) {
            vehicleList = getResources().getStringArray(R.array.cars);
            vehicleNames.addAll(Arrays.asList(vehicleList));
            vehiclePrice = getResources().getStringArray(R.array.carPrice);
            vehiclePrices.addAll(Arrays.asList(vehiclePrice));

            for (int i = 0; i < vehicleNames.size();i++){
                String imageName = getResources().getStringArray(R.array.carImages)[i];
                int resourceId = res.getIdentifier(imageName, "drawable", getPackageName());
                vehiclePic.add(resourceId);
            }

        }
        else if (vehicleType == 2){
            vehicleList = getResources().getStringArray(R.array.bikes);
            vehicleNames.addAll(Arrays.asList(vehicleList));
            vehiclePrice = getResources().getStringArray(R.array.bikePrice);
            vehiclePrices.addAll(Arrays.asList(vehiclePrice));
            for (int i = 0; i < vehicleNames.size();i++){
                String imageName = getResources().getStringArray(R.array.bikeImages)[i];
                int resourceId = res.getIdentifier(imageName, "drawable", getPackageName());
                vehiclePic.add(resourceId);
            }
        }
        else {
            vehicleList = getResources().getStringArray(R.array.other);
            vehicleNames.addAll(Arrays.asList(vehicleList));
            vehiclePrice = getResources().getStringArray(R.array.otherPrice);
            vehiclePrices.addAll(Arrays.asList(vehiclePrice));
            for (int i = 0; i < vehicleNames.size();i++){
                String imageName = getResources().getStringArray(R.array.otherImages)[i];
                int resourceId = res.getIdentifier(imageName, "drawable", getPackageName());
                vehiclePic.add(resourceId);
            }
        }
        RecyclerView recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, vehicleType, vehicleNames, vehiclePic, vehiclePrices);
        myRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent send = new Intent(MainActivity2.this, MainActivity3.class);
        send.putExtra("vehiclePic", myRecyclerViewAdapter.getPicture(position));
        send.putExtra("position", (int)position);
        send.putExtra("vehicleType", myRecyclerViewAdapter.getVehicleType());
        startActivity(send);
    }
}