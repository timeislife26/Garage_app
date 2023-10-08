package com.example.garage_app;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RadioButton;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        RadioButton car = findViewById(R.id.carRB);
        RadioButton bike = findViewById(R.id.bikeRB);
        RadioButton other = findViewById(R.id.otherRB);
        Button submit = findViewById(R.id.submitBtn);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int option = 0;
                Intent send = new Intent(MainActivity.this, MainActivity2.class);
                option = (car.isChecked())?1:(bike.isChecked())?2:other.isChecked()?3:0;
                if (option == 0) {
                    Toast.makeText(getApplicationContext(), R.string.unselected, Toast.LENGTH_SHORT).show();
                }
                else {
                    send.putExtra("vehicle", option);
                    startActivity(send);
                }
            }
        });
    }
}