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

public class MiscellaneousActivity extends AppCompatActivity {
  private EditText idEdtMiscellaneousName, idEdtMiscellaneousCost, idEdtMiscellaneousDate, idEdtMiscellaneousDescription;
  private Button idBtnAddMiscellaneous;
  private DBHandler dbHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_miscellaneous);  // Ensure you have a layout file named 'activity_second.xml'
    dbHandler = new DBHandler(this);
    ImageButton backButton = (android.widget.ImageButton) findViewById(R.id.backButton);
    idEdtMiscellaneousName = findViewById(R.id.idEdtMiscellaneousName);
    idEdtMiscellaneousCost = findViewById(R.id.idEdtMiscellaneousCost);
    idEdtMiscellaneousDate = findViewById(R.id.idEdtMiscellaneousDate);
    idEdtMiscellaneousDescription = findViewById(R.id.idEdtMiscellaneousDescription);
    idBtnAddMiscellaneous = findViewById(R.id.idBtnAddMiscellaneous);

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(MiscellaneousActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });

    idBtnAddMiscellaneous.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // below line is to get data from all edit text fields.
        String MiscellaneousName = idEdtMiscellaneousName.getText().toString();
        int MiscellaneousCost = Integer.parseInt(idEdtMiscellaneousCost.getText().toString());
        String MiscellaneousDate = idEdtMiscellaneousDate.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = null;
        try {
          date = dateFormat.parse(MiscellaneousDate);
        } catch (ParseException e) {
          // Handle the possibility that the string was not in the expected format
          e.printStackTrace();
          Toast.makeText(MiscellaneousActivity.this, "Invalid date format. Please use dd-MM-yyyy.", Toast.LENGTH_LONG).show();
        }
        String MiscellaneousDescription = idEdtMiscellaneousDescription.getText().toString();

        // validating if the text fields are empty or not.
        if (MiscellaneousName.isEmpty() && MiscellaneousDate.isEmpty() && MiscellaneousDescription.isEmpty()) {
          Toast.makeText(MiscellaneousActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
          return;
        }

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        dbHandler.addNewCol(MiscellaneousName, MiscellaneousCost, MiscellaneousDescription, "Miscellaneous", date);

        // after adding the data we are displaying a toast message.
        Toast.makeText(MiscellaneousActivity.this, "Miscellaneous has been added.", Toast.LENGTH_SHORT).show();
        idEdtMiscellaneousName.setText("");
        idEdtMiscellaneousCost.setText("");
        idEdtMiscellaneousDate.setText("");
        idEdtMiscellaneousDescription.setText("");
      }
    });

  }

}
