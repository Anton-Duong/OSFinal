package com.example.appf;
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class DBHandler extends SQLiteOpenHelper  {
  private static final String DB_NAME = "budgetdb";

  private static final int DB_VERSION = 1;

  private static final String TABLE_NAME = "budgetTable";

  // below variable is for our id column.
  private static final String ID_COL = "id";

  // below variable is for our bill name column
  private static final String NAME_COL = "name";


  // below variable for our bill description column.
  private static final String DESCRIPTION_COL = "description";

  // below variable is for our course tracks column.
  private static final String COST_COL = "cost";
  private static final String DATE_COL = "date";
  private static final String CATEGORY_COL = "category";

  public DBHandler(Context context) {
    super(context, DB_NAME, null, DB_VERSION);
  }

  @Override
  public void onCreate(SQLiteDatabase db) {

    String query = "CREATE TABLE " + TABLE_NAME + " ("
      + ID_COL + " INTEGER PRIMARY KEY AUTOINCREMENT, "
      + NAME_COL + " TEXT,"
      + COST_COL + " INT,"
      + DESCRIPTION_COL + " TEXT,"
      + CATEGORY_COL + " TEXT, "
      + DATE_COL + " TEXT)";

    // at last we are calling a exec sql
    // method to execute above sql query
    db.execSQL(query);
  }

  public void addNewCol(String Name, int Cost, String Description, String Category, String Date ) {

    // on below line we are creating a variable for
    // our sqlite database and calling writable method
    // as we are writing data in our database.
    SQLiteDatabase db = this.getWritableDatabase();

    // on below line we are creating a
    // variable for content values.
    ContentValues values = new ContentValues();

    // on below line we are passing all values
    // along with its key and value pair.
    values.put(NAME_COL, Name);
    values.put(COST_COL, Cost);
    values.put(DESCRIPTION_COL, Description);
    values.put(CATEGORY_COL, Category);
    values.put(DATE_COL, Date);

    // after adding all values we are passing
    // content values to our table.
    db.insert(TABLE_NAME, null, values);

    // at last we are closing our
    // database after adding database.
    db.close();
  }


  @Override
  public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    // this method is called to check if the table exists already.
    db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    onCreate(db);
  }

  public List<String> getPurchasesByCategory(String category) {
    List<String> results = new ArrayList<>();
    SQLiteDatabase db = this.getReadableDatabase();
    Cursor cursor;
    if (category.equals("All")) {
      cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME, null);
    } else {
      cursor = db.rawQuery("SELECT * FROM " + TABLE_NAME + " WHERE " + CATEGORY_COL + "=?", new String[]{category});
    }
    while (cursor.moveToNext()) {
      results.add(cursor.getString(cursor.getColumnIndex(NAME_COL)) + " - $" +
        cursor.getInt(cursor.getColumnIndex(COST_COL)) + " Description-" +
        cursor.getString(cursor.getColumnIndex(DESCRIPTION_COL)) + " -" +
        cursor.getString(cursor.getColumnIndex(DATE_COL)));
    }
    cursor.close();
    db.close();
    return results;
  }

}
