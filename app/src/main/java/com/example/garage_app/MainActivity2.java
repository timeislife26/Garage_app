package com.example.garage_app;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;

public class MainActivity2 extends AppCompatActivity implements MyRecyclerViewAdapter.ItemClickListener {

    MyRecyclerViewAdapter myRecyclerViewAdapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        Bundle extras = getIntent().getExtras();
        int vehicleType = 0;
        if (extras != null)
            vehicleType = extras.getInt("vehicle");
        ArrayList<String> vehicleNames = new ArrayList<>();
        ArrayList<Integer> vehiclePic = new ArrayList<>();
        String[] vehicleList;
        if (vehicleType == 1) {
            vehicleList = getResources().getStringArray(R.array.cars);
            vehicleNames.addAll(Arrays.asList(vehicleList));
            /*
            vehicleNames.add("Car 1");
            vehicleNames.add("Car 2");
            vehicleNames.add("Car 3");
            vehicleNames.add("Car 4");
            vehicleNames.add("Car 5");
            */
            vehiclePic.add(R.drawable.car1);
            vehiclePic.add(R.drawable.car2);
            vehiclePic.add(R.drawable.car3);
            vehiclePic.add(R.drawable.car4);
            vehiclePic.add(R.drawable.car5);

        }
        else if (vehicleType == 2){
            vehicleList = getResources().getStringArray(R.array.bikes);
            vehicleNames.addAll(Arrays.asList(vehicleList));
            vehiclePic.add(R.drawable.bike1);
            vehiclePic.add(R.drawable.bike2);
            vehiclePic.add(R.drawable.bike3);
            vehiclePic.add(R.drawable.bike4);
            vehiclePic.add(R.drawable.bike5);
        }
        else {
            vehicleList = getResources().getStringArray(R.array.other);
            vehicleNames.addAll(Arrays.asList(vehicleList));
            vehiclePic.add(R.drawable.other1);
            vehiclePic.add(R.drawable.other2);
            vehiclePic.add(R.drawable.other3);
            vehiclePic.add(R.drawable.other4);
            vehiclePic.add(R.drawable.other5);
        }
        RecyclerView recyclerView = findViewById(R.id.rcView);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        myRecyclerViewAdapter = new MyRecyclerViewAdapter(this, vehicleType, vehicleNames, vehiclePic);
        myRecyclerViewAdapter.setClickListener(this);
        recyclerView.setAdapter(myRecyclerViewAdapter);

    }

    @Override
    public void onItemClick(View view, int position) {
        Intent send = new Intent(MainActivity2.this, MainActivity3.class);
        send.putExtra("vehiclePic", myRecyclerViewAdapter.getPicture(position));
        //send.putExtra("position", myRecyclerViewAdapter.getItem(position));
        send.putExtra("position", (int)position);
        send.putExtra("vehicleType", myRecyclerViewAdapter.getVehicleType());
        startActivity(send);
        //Toast.makeText(this, "You clicked " + myRecyclerViewAdapter.getItem(position) + " on row number " + position, Toast.LENGTH_SHORT).show();
    }
}