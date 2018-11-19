package com.asfour.mohamad.mypurchases;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.FloatingActionButton;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.asfour.mohamad.mypurchases.PurchasesData.PurchasesContract;
import com.asfour.mohamad.mypurchases.PurchasesData.PurchasesDbHelper;

public class MainActivity extends AppCompatActivity {


    PurchasesDbHelper dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_main );
        // To access our database, we instantiate our subclass of SQLiteOpenHelper
        // and pass the context, which is the current activity.
        dbHelper = new PurchasesDbHelper(this);

        FloatingActionButton fab = findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                insertPurchases();
                displayDbInfo();
            }
        });
        insertPurchases();
        displayDbInfo();
    }

    private void displayDbInfo() {
        // Create and/or open a database to read from it
        SQLiteDatabase db = dbHelper.getReadableDatabase();

        Cursor cursor = db.query(PurchasesContract.PurchasesEntry.TABLE_NAME, null, null,
                null, null, null, null);

        try {
            // Display the number of rows in the Cursor (which reflects the number of rows in the
            // purchases table in the database).
            TextView displayView = findViewById(R.id.text_view);
            displayView.setText(getString(R.string.purchases_contains) + cursor.getCount() + getString(R.string.purchases));

            displayView.append(PurchasesContract.PurchasesEntry._ID + " - "
                    + PurchasesContract.PurchasesEntry.COLUMN_PRODUCT_NAME + " - "
                    + PurchasesContract.PurchasesEntry.COLUMN_PRICE + " - "
                    + PurchasesContract.PurchasesEntry.COLUMN_QUANTITY + " - "
                    + PurchasesContract.PurchasesEntry.COLUMN_SUPPLIER_NAME + " - "
                    + PurchasesContract.PurchasesEntry.COLUMN_SUPPLIER_PHONE_NUMBER + "\n");

            while (cursor.moveToNext()) {

                displayView.append(cursor.getInt(cursor.getColumnIndex(PurchasesContract.PurchasesEntry._ID)) + " - "
                        + cursor.getString(cursor.getColumnIndex(PurchasesContract.PurchasesEntry.COLUMN_PRODUCT_NAME)) + " - $"
                        + cursor.getDouble(cursor.getColumnIndex(PurchasesContract.PurchasesEntry.COLUMN_PRICE)) / 100 + " - "
                        // price is divided by 100 to display proper price with cents
                        + cursor.getInt(cursor.getColumnIndex(PurchasesContract.PurchasesEntry.COLUMN_QUANTITY)) + " - "
                        + cursor.getString(cursor.getColumnIndex(PurchasesContract.PurchasesEntry.COLUMN_SUPPLIER_NAME)) + " - "
                        + cursor.getString(cursor.getColumnIndex(PurchasesContract.PurchasesEntry.COLUMN_SUPPLIER_PHONE_NUMBER))
                        + "\n");
            }

        } finally {
            // Always close the cursor when you're done reading from it. This releases all its
            // resources and makes it invalid.
            cursor.close();
        }
    }

    private void insertPurchases() {

        SQLiteDatabase db = dbHelper.getWritableDatabase();

        // Create a new map of values, where column names are the keys
        ContentValues values = new ContentValues();
        values.put(PurchasesContract.PurchasesEntry.COLUMN_PRODUCT_NAME, "Golden Ring");
        values.put(PurchasesContract.PurchasesEntry.COLUMN_PRICE, 25000);
        values.put(PurchasesContract.PurchasesEntry.COLUMN_QUANTITY, 2);
        values.put(PurchasesContract.PurchasesEntry.COLUMN_SUPPLIER_NAME, "King og the rings");
        values.put(PurchasesContract.PurchasesEntry.COLUMN_SUPPLIER_PHONE_NUMBER, "05334477597");

        // Insert the new row, returning the primary key value of the new row
        long newRowId = db.insert(PurchasesContract.PurchasesEntry.TABLE_NAME, null, values);

        Log.v("MainActivity", "new row ID: " + newRowId);
    }
}
