package com.example.appf;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;


import androidx.appcompat.app.AppCompatActivity;

public class BillsActivity extends AppCompatActivity {
  private EditText idEdtBillName, idEdtBillCost, idEdtBillDate, idEdtBillDescription;
  private Button idBtnAddBill;
  private DBHandler dbHandler;

  @Override
  protected void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
    setContentView(R.layout.activity_bills);  // Ensure you have a layout file named 'activity_second.xml'
    dbHandler = new DBHandler(this);
    ImageButton backButton = (android.widget.ImageButton) findViewById(R.id.backButton);
    idEdtBillName = findViewById(R.id.idEdtBillName);
    idEdtBillCost = findViewById(R.id.idEdtBillCost);
    idEdtBillDate = findViewById(R.id.idEdtBillDate);
    idEdtBillDescription = findViewById(R.id.idEdtBillDescription);
    idBtnAddBill = findViewById(R.id.idBtnAddBill);

    backButton.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        Intent intent = new Intent(BillsActivity.this, MainActivity.class);
        startActivity(intent);
      }
    });

    idBtnAddBill.setOnClickListener(new View.OnClickListener() {
      @Override
      public void onClick(View v) {
        // below line is to get data from all edit text fields.
        String billName = idEdtBillName.getText().toString();
        int billCost = Integer.parseInt(idEdtBillCost.getText().toString());
        String billDate = idEdtBillDate.getText().toString();
        SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy", Locale.getDefault());
        Date date = null;
        try {
          date = dateFormat.parse(billDate);
        } catch (ParseException e) {
          // Handle the possibility that the string was not in the expected format
          e.printStackTrace();
          Toast.makeText(BillsActivity.this, "Invalid date format. Please use dd-MM-yyyy.", Toast.LENGTH_LONG).show();
        }
        String billDescription = idEdtBillDescription.getText().toString();

        // validating if the text fields are empty or not.
        if (billName.isEmpty() && billDate.isEmpty() && billDescription.isEmpty()) {
          Toast.makeText(BillsActivity.this, "Please enter all the data..", Toast.LENGTH_SHORT).show();
          return;
        }

        // on below line we are calling a method to add new
        // course to sqlite data and pass all our values to it.
        dbHandler.addNewCol(billName, billCost, billDescription, "bill", date);

        // after adding the data we are displaying a toast message.
        Toast.makeText(BillsActivity.this, "Bill has been added.", Toast.LENGTH_SHORT).show();
        idEdtBillName.setText("");
        idEdtBillCost.setText("");
        idEdtBillDate.setText("");
        idEdtBillDescription.setText("");
      }
    });

  }


}
