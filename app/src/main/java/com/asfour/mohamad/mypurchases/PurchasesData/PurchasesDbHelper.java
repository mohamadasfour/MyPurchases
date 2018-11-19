package com.asfour.mohamad.mypurchases.PurchasesData;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

public class PurchasesDbHelper extends SQLiteOpenHelper {

    private final static String DB_NAME = "mypurchases.db";
    private final static int DB_VERSION = 1;


    public PurchasesDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDb) {
        String SQL_CREATE_PURCHASES_TABLE = "CREATE TABLE " + PurchasesContract.PurchasesEntry.TABLE_NAME + " ("
                + PurchasesContract.PurchasesEntry._ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "
                + PurchasesContract.PurchasesEntry.COLUMN_PRODUCT_NAME + " TEXT NOT NULL, "
                + PurchasesContract.PurchasesEntry.COLUMN_PRICE + " INTEGER NOT NULL, "
                // price is saved as an integer, when displaying should be divided by 100
                + PurchasesContract.PurchasesEntry.COLUMN_QUANTITY + " INTEGER NOT NULL, "
                + PurchasesContract.PurchasesEntry.COLUMN_SUPPLIER_NAME + " TEXT, "
                + PurchasesContract.PurchasesEntry.COLUMN_SUPPLIER_PHONE_NUMBER + " TEXT);";

        // Execute the SQL statement
        sqLiteDb.execSQL(SQL_CREATE_PURCHASES_TABLE);

        Log.v("PurchasesDbHelper", "create statement" + SQL_CREATE_PURCHASES_TABLE);

    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {

    }
}
