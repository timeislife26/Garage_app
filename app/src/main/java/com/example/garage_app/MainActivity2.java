package com.example.garage_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity2 extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        int vehicle = 0;
        if (extras != null)
            vehicle = extras.getInt("vehicle");
        ArrayList<String> vehicleNames = new ArrayList<>();
        ArrayList<Integer> vehiclePic = new ArrayList<>();
        if (vehicle == 1) {
            vehicleNames.add("Car 1");
            vehicleNames.add("Car 2");
            vehicleNames.add("Car 3");
            vehicleNames.add("Car 4");
            vehicleNames.add("Car 5");
            vehiclePic.add(R.drawable.car1);
            vehiclePic.add(R.drawable.car2);
            vehiclePic.add(R.drawable.car3);
            vehiclePic.add(R.drawable.car4);
            vehiclePic.add(R.drawable.car5);
        }
        else if (vehicle == 2){
            vehicleNames.add("Bike 1");
            vehicleNames.add("Bike 2");
            vehicleNames.add("Bike 3");
            vehicleNames.add("Bike 4");
            vehicleNames.add("Bike 5");
            vehiclePic.add(R.drawable.bike1);
            vehiclePic.add(R.drawable.bike2);
            vehiclePic.add(R.drawable.bike3);
            vehiclePic.add(R.drawable.bike4);
            vehiclePic.add(R.drawable.bike5);
        }
        else {
            vehicleNames.add("Other 1");
            vehicleNames.add("Other 2");
            vehicleNames.add("Other 3");
            vehicleNames.add("Other 4");
            vehicleNames.add("Other 5");
            vehiclePic.add(R.drawable.other1);
            vehiclePic.add(R.drawable.other2);
            vehiclePic.add(R.drawable.other3);
            vehiclePic.add(R.drawable.other4);
            vehiclePic.add(R.drawable.other5);
        }
        RecyclerView recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, vehicleNames, vehiclePic);
        myRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Toast.makeText(this, "You clicked " + myRecyclerViewAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}