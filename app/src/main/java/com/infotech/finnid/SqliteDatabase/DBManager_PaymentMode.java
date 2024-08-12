package com.infotech.finnid.SqliteDatabase;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager_PaymentMode {

    private DatabaseHelper_PaymentMode dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager_PaymentMode(Context c) {
        context = c;
    }

    public DBManager_PaymentMode open() throws SQLException {
        dbHelper = new DatabaseHelper_PaymentMode(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }


    public void insert_payment_mode(String payment_mode,String Paid,  String Balance ,String ed_payment_Amount,
                                    int payment_mode_id ,int Sub_payment_mode_id  ) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper_PaymentMode.payment_mode, payment_mode);
        contentValue.put(DatabaseHelper_PaymentMode.Paid, Paid);
        contentValue.put(DatabaseHelper_PaymentMode.Balance, Balance);
        contentValue.put(DatabaseHelper_PaymentMode.payment_Amount, ed_payment_Amount);
        contentValue.put(DatabaseHelper_PaymentMode.payment_mode_id, payment_mode_id);
        contentValue.put(DatabaseHelper_PaymentMode.Sub_payment_mode_id, Sub_payment_mode_id);
        database.insert(DatabaseHelper_PaymentMode.TABLE_NAME_pm, null, contentValue);


    }


   /* public void insert(String name, String desc, String UOM) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.SUBJECT, name);
        contentValue.put(DatabaseHelper.DESC, desc);
        contentValue.put(DatabaseHelper.UOM, UOM);
        database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);
    }*/

    public Cursor fetch() {
       // String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.SUBJECT, DatabaseHelper.DESC ,DatabaseHelper.UOM };

        String[] columns = new String[] {
                DatabaseHelper_PaymentMode._ID,
                DatabaseHelper_PaymentMode.payment_mode,
                DatabaseHelper_PaymentMode.Paid,
                DatabaseHelper_PaymentMode.Balance ,
                DatabaseHelper_PaymentMode.payment_Amount,
                DatabaseHelper_PaymentMode.payment_mode_id,
                DatabaseHelper_PaymentMode.Sub_payment_mode_id  };

        Cursor cursor = database.query(DatabaseHelper_PaymentMode.TABLE_NAME_pm, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper_PaymentMode.item, name);
        contentValue.put(DatabaseHelper_PaymentMode.UOM, name);
        contentValue.put(DatabaseHelper_PaymentMode.qty, name);
        contentValue.put(DatabaseHelper_PaymentMode.Rate, name);
        contentValue.put(DatabaseHelper_PaymentMode.Disc, name);
        contentValue.put(DatabaseHelper_PaymentMode.transaction_value, name);
        contentValue.put(DatabaseHelper_PaymentMode.gst, name);
        contentValue.put(DatabaseHelper_PaymentMode.gst_amt, name);
        contentValue.put(DatabaseHelper_PaymentMode.Cess, name);
        contentValue.put(DatabaseHelper_PaymentMode.cess_amt, name);
        contentValue.put(DatabaseHelper_PaymentMode.Amount, name);
        int i = database.update(DatabaseHelper_PaymentMode.TABLE_NAME_pm, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper_PaymentMode.TABLE_NAME_pm, DatabaseHelper_PaymentMode._ID + "=" + _id, null);
    }


    public Cursor getListItem() {

       dbHelper = new DatabaseHelper_PaymentMode(context);
//        database = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DatabaseHelper_PaymentMode.TABLE_NAME_pm;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }


   /* public void delete(String tableName, Object o, Object o1) {


       SQLiteDatabase db = dbHelper.getWritableDatabase();
          db.delete(DatabaseHelper_PaymentMode.TABLE_NAME_pm,null,null);
       //  db.execSQL(tableName + DatabaseHelper_PaymentMode.TABLE_NAME_pm);
        db.close();


    }*/
}
