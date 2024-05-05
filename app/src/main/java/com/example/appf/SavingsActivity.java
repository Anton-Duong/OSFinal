package com.example.appf;

import android.annotation.SuppressLint;
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

public class SavingsActivity extends AppCompatActivity {

  private EditText idEdtSavingsName, idEdtSavingsCost, idEdtSavingsDate, idEdtSavingsDescription;
  private Button idBtnAddSavings;
  private DBHandler dbHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_savings);  // Ensure you have a layout file named 'activity_second.xml'
    dbHandler = new DBHandler(this);
    ImageButton backButton = (android.widget.ImageButton) findViewById(R.id.backButton);
    idEdtSavingsName = findViewById(R.id.idEdtSavingsName);
    idEdtSavingsCost = findViewById(R.id.idEdtSavingsCost);
    idEdtSavingsDate = findViewById(R.id.idEdtSavingsDate);
    idEdtSavingsDescription = findViewById(R.id.idEdtSavingsDescription);
    idBtnAddSavings = findViewById(R.id.idBtnAddSavings);

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(SavingsActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });

    idBtnAddSavings.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // below line is to get data from all edit text fields.
        String SavingsName = idEdtSavingsName.getText().toString();
        int SavingsCost = Integer.parseInt(idEdtSavingsCost.getText().toString());
        String SavingsDate = idEdtSavingsDate.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = null;
        try {
          date = dateFormat.parse(SavingsDate);
        } catch (ParseException e) {
          // Handle the possibility that the string was not in the expected format
          e.printStackTrace();
          Toast.makeText(SavingsActivity.this, "Invalid date format. Please use dd-MM-yyyy.", Toast.LENGTH_LONG).show();
        }
        String SavingsDescription = idEdtSavingsDescription.getText().toString();

        // validating if the text fields are empty or not.
        if (SavingsName.isEmpty() && SavingsDate.isEmpty() && SavingsDescription.isEmpty()) {
          Toast.makeText(SavingsActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
          return;
        }

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        dbHandler.addNewCol(SavingsName, SavingsCost, SavingsDescription, "Savings", date);

        // after adding the data we are displaying a toast message.
        Toast.makeText(SavingsActivity.this, "Savings has been added.", Toast.LENGTH_SHORT).show();
        idEdtSavingsName.setText("");
        idEdtSavingsCost.setText("");
        idEdtSavingsDate.setText("");
        idEdtSavingsDescription.setText("");
      }
    });

  }
}
