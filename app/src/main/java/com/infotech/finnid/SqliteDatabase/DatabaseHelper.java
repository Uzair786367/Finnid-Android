package com.infotech.finnid.SqliteDatabase;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DatabaseHelper extends SQLiteOpenHelper {

    // Table Name
    public static final String TABLE_NAME = "COUNTRIES";

    // Table columns
    public static final String _ID = "_id";
    public static final String SUBJECT = "subject";
    public static final String DESC = "description";
    public static final String UOM = "UOM";

    public static final String item = "item";
     public static final String qty = "qty";
    public static final String Rate = "Rate";
    public static final String Disc = "Disc";
    public static final String transaction_value = "transaction_value";
    public static final String gst = "gst";
    public static final String gst_amt = "gst_amt";
    public static final String Cess = "Cess";
    public static final String cess_amt = "cess_amt";
    public static final String Amount = "Amount";
    public static final String item_HsnCode = "item_HsnCode";
    public static final String item_BarCode = "item_BarCode";
    public static final String itemGroupCode = "itemGroupCode";
    public static final String item_id_id = "item_id_id";

    // Database Information
    static final String DB_NAME = "JOURNALDEV_COUNTRIES.DB";

    // database version
    static final int DB_VERSION = 1;

    // Creating table query
    /*private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT, " + SUBJECT + " TEXT NOT NULL, " + DESC + " TEXT ," + UOM + " TEXT);";
*/
    private static final String CREATE_TABLE = "create table " + TABLE_NAME + "(" + _ID
            + " INTEGER PRIMARY KEY AUTOINCREMENT,  " + item + " TEXT ," + UOM + " TEXT ," + qty + " TEXT ," + Rate +
            " TEXT ," + Disc + " TEXT ," + transaction_value + " TEXT ," + gst + " TEXT ," + gst_amt + " TEXT ," + Cess + " TEXT," + cess_amt + " TEXT ," +
            Amount + " TEXT ," + item_HsnCode + " TEXT ," + item_BarCode + " TEXT ," + itemGroupCode + " TEXT ," + item_id_id + " TEXT  );";



    public DatabaseHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }
}
