package com.asfour.mohamad.mypurchases.PurchasesData;

import android.provider.BaseColumns;

public final class PurchasesContract {
    public static abstract class  PurchasesEntry implements BaseColumns {

        public final static String TABLE_NAME = "Purchases";

        public final static String _ID = BaseColumns._ID;
        public final static String COLUMN_PRODUCT_NAME ="product_name";
        public final static String COLUMN_PRICE = "price";
        public final static String COLUMN_QUANTITY = "quantity";
        public final static String COLUMN_SUPPLIER_NAME = "supplier_name";
        public final static String COLUMN_SUPPLIER_PHONE_NUMBER = "supplier_phone_number";

    }
}

