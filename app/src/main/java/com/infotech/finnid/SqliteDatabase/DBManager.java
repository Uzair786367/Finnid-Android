package com.infotech.finnid.SqliteDatabase;

/**
 * Created by anupamchugh on 19/10/15.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

public class DBManager {

    private DatabaseHelper dbHelper;

    private Context context;

    private SQLiteDatabase database;

    public DBManager(Context c) {
        context = c;
    }

    public DBManager open() throws SQLException {
        dbHelper = new DatabaseHelper(context);
        database = dbHelper.getWritableDatabase();
        return this;
    }

    public void close() {
        dbHelper.close();
    }


    public void insert(String item,String UOM,  String qty ,String Rate , String Disc ,String transaction_value , String gst,  String gst_amt ,String Cess ,
                       String cess_amt ,String _Amount ,String item_HsnCode ,String item_BarCode ,String itemGroupCode,String item_id_id  ) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.item, item);
        contentValue.put(DatabaseHelper.UOM, UOM);
        contentValue.put(DatabaseHelper.qty, qty);
        contentValue.put(DatabaseHelper.Rate, Rate);
        contentValue.put(DatabaseHelper.Disc, Disc);
        contentValue.put(DatabaseHelper.transaction_value, transaction_value);
        contentValue.put(DatabaseHelper.gst, gst);
        contentValue.put(DatabaseHelper.gst_amt, gst_amt);
        contentValue.put(DatabaseHelper.Cess, Cess);
        contentValue.put(DatabaseHelper.cess_amt, cess_amt);
        contentValue.put(DatabaseHelper.Amount, _Amount);
        contentValue.put(DatabaseHelper.item_HsnCode, item_HsnCode);
        contentValue.put(DatabaseHelper.item_BarCode, item_BarCode);
        contentValue.put(DatabaseHelper.itemGroupCode, itemGroupCode);
        contentValue.put(DatabaseHelper.item_id_id, item_id_id);
         database.insert(DatabaseHelper.TABLE_NAME, null, contentValue);


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

        String[] columns = new String[] { DatabaseHelper._ID, DatabaseHelper.item,DatabaseHelper.UOM,  DatabaseHelper.qty ,
                DatabaseHelper.Rate , DatabaseHelper.Disc ,DatabaseHelper.transaction_value , DatabaseHelper.gst,
                DatabaseHelper.gst_amt ,DatabaseHelper.Cess , DatabaseHelper.cess_amt ,DatabaseHelper.Amount,
                DatabaseHelper.item_HsnCode,DatabaseHelper.item_BarCode,DatabaseHelper.itemGroupCode,DatabaseHelper.item_id_id };

        Cursor cursor = database.query(DatabaseHelper.TABLE_NAME, columns, null, null, null, null, null);
        if (cursor != null) {
            cursor.moveToFirst();
        }
        return cursor;
    }

    public int update(long _id, String name, String desc) {
        ContentValues contentValue = new ContentValues();
        contentValue.put(DatabaseHelper.item, name);
        contentValue.put(DatabaseHelper.UOM, name);
        contentValue.put(DatabaseHelper.qty, name);
        contentValue.put(DatabaseHelper.Rate, name);
        contentValue.put(DatabaseHelper.Disc, name);
        contentValue.put(DatabaseHelper.transaction_value, name);
        contentValue.put(DatabaseHelper.gst, name);
        contentValue.put(DatabaseHelper.gst_amt, name);
        contentValue.put(DatabaseHelper.Cess, name);
        contentValue.put(DatabaseHelper.cess_amt, name);
        contentValue.put(DatabaseHelper.Amount, name);
        int i = database.update(DatabaseHelper.TABLE_NAME, contentValue, DatabaseHelper._ID + " = " + _id, null);
        return i;
    }

    public void delete(long _id) {
        database.delete(DatabaseHelper.TABLE_NAME, DatabaseHelper._ID + "=" + _id, null);
    }


    public Cursor getListItem() {

       dbHelper = new DatabaseHelper(context);
//        database = dbHelper.getWritableDatabase();
        String selectQuery = "SELECT  * FROM " + DatabaseHelper.TABLE_NAME;

        SQLiteDatabase db = dbHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery(selectQuery, null);

        return cursor;
    }


   /* public void delete(String tableName, Object o, Object o1) {


       SQLiteDatabase db = dbHelper.getWritableDatabase();
          db.delete(DatabaseHelper.TABLE_NAME,null,null);
       //  db.execSQL(tableName + DatabaseHelper.TABLE_NAME);
        db.close();


    }*/
}
