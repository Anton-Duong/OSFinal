package com.example.appf;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class GroceriesActivity extends AppCompatActivity {

  private EditText idEdtGroceriesName, idEdtGroceriesCost, idEdtGroceriesDate, idEdtGroceriesDescription;
  private Button idBtnAddGroceries;
  private DBHandler dbHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_groceries);  // Ensure you have a layout file named 'activity_second.xml'
    dbHandler = new DBHandler(this);
    ImageButton backButton = (android.widget.ImageButton) findViewById(R.id.backButton);
    idEdtGroceriesName = findViewById(R.id.idEdtGroceriesName);
    idEdtGroceriesCost = findViewById(R.id.idEdtGroceriesCost);
    idEdtGroceriesDate = findViewById(R.id.idEdtGroceriesDate);
    idEdtGroceriesDescription = findViewById(R.id.idEdtGroceriesDescription);
    idBtnAddGroceries = findViewById(R.id.idBtnAddGroceries);

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(GroceriesActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });

    idBtnAddGroceries.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // below line is to get data from all edit text fields.
        String GroceriesName = idEdtGroceriesName.getText().toString();
        int GroceriesCost = Integer.parseInt(idEdtGroceriesCost.getText().toString());
        String GroceriesDate = idEdtGroceriesDate.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = null;
        try {
          date = dateFormat.parse(GroceriesDate);
        } catch (ParseException e) {
          // Handle the possibility that the string was not in the expected format
          e.printStackTrace();
          Toast.makeText(GroceriesActivity.this, "Invalid date format. Please use dd-MM-yyyy.", Toast.LENGTH_LONG).show();
        }
        String GroceriesDescription = idEdtGroceriesDescription.getText().toString();

        // validating if the text fields are empty or not.
        if (GroceriesName.isEmpty() && GroceriesDate.isEmpty() && GroceriesDescription.isEmpty()) {
          Toast.makeText(GroceriesActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
          return;
        }

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        dbHandler.addNewCol(GroceriesName, GroceriesCost, GroceriesDescription, "Groceries", date);

        // after adding the data we are displaying a toast message.
        Toast.makeText(GroceriesActivity.this, "Groceries has been added.", Toast.LENGTH_SHORT).show();
        idEdtGroceriesName.setText("");
        idEdtGroceriesCost.setText("");
        idEdtGroceriesDate.setText("");
        idEdtGroceriesDescription.setText("");
      }
    });

  }
}
