package com.example.appf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;


public class MainActivity extends AppCompatActivity {


  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    EdgeToEdge.enable(this);
    setContentView(R.layout.activity_main);
    ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
      Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
      v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
      return insets;
    });
    ImageButton rentButton = (android.widget.ImageButton) findViewById(R.id.rentButton);
    rentButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, BillsActivity.class);
        startActivity(intent);
      }
    });

    ImageButton travelButton = (android.widget.ImageButton) findViewById(R.id.travelButton);
    travelButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, TravelActivity.class);
        startActivity(intent);
      }
    });

    ImageButton savingButton = (android.widget.ImageButton) findViewById(R.id.savingsButton);
    savingButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, SavingsActivity.class);
        startActivity(intent);
      }
    });

    ImageButton groceryButton = (android.widget.ImageButton) findViewById(R.id.groceryButton);
    groceryButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, GroceriesActivity.class);
        startActivity(intent);
      }
    });

    ImageButton miscButton = (android.widget.ImageButton) findViewById(R.id.goingOutButton);
    miscButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MainActivity.this, MiscellaneousActivity.class);
        startActivity(intent);
      }
    });


  }
}
