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

public class TravelActivity extends AppCompatActivity {

  private EditText idEdtTravelName, idEdtTravelCost, idEdtTravelDate, idEdtTravelDescription;
  private Button idBtnAddTravel;
  private DBHandler dbHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_travel);  // Ensure you have a layout file named 'activity_second.xml'
    dbHandler = new DBHandler(this);
    ImageButton backButton = (android.widget.ImageButton) findViewById(R.id.backButton);
    idEdtTravelName = findViewById(R.id.idEdtTravelName);
    idEdtTravelCost = findViewById(R.id.idEdtTravelCost);
    idEdtTravelDate = findViewById(R.id.idEdtTravelDate);
    idEdtTravelDescription = findViewById(R.id.idEdtTravelDescription);
    idBtnAddTravel = findViewById(R.id.idBtnAddTravel);

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(TravelActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });

    idBtnAddTravel.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // below line is to get data from all edit text fields.
        String TravelName = idEdtTravelName.getText().toString();
        int TravelCost = Integer.parseInt(idEdtTravelCost.getText().toString());
        String TravelDate = idEdtTravelDate.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = null;
        try {
          date = dateFormat.parse(TravelDate);
        } catch (ParseException e) {
          // Handle the possibility that the string was not in the expected format
          e.printStackTrace();
          Toast.makeText(TravelActivity.this, "Invalid date format. Please use dd-MM-yyyy.", Toast.LENGTH_LONG).show();
        }
        String TravelDescription = idEdtTravelDescription.getText().toString();

        // validating if the text fields are empty or not.
        if (TravelName.isEmpty() && TravelDate.isEmpty() && TravelDescription.isEmpty()) {
          Toast.makeText(TravelActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
          return;
        }

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        dbHandler.addNewCol(TravelName, TravelCost, TravelDescription, "Travel", date);

        // after adding the data we are displaying a toast message.
        Toast.makeText(TravelActivity.this, "Travel has been added.", Toast.LENGTH_SHORT).show();
        idEdtTravelName.setText("");
        idEdtTravelCost.setText("");
        idEdtTravelDate.setText("");
        idEdtTravelDescription.setText("");
      }
    });

  }
}
