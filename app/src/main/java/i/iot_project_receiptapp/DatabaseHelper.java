package i.iot_project_receiptapp;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;
import java.util.ArrayList;
/**
 * Created by tsaiyunyun on 2017/5/11.
 */
public class DatabaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DBhelper";
    public static final String DATABASE_NAME = "ReceiptList.db";
    public static final String TABLE_NAME = "ReceiptData";
    public static final String KEY_ID = "_id";
    public static final String COL1 = "JAN";
    public static final String COL2 = "FEB";
    public static final String COL3 = "MAR";
    public static final String COL4 = "APR";
    public static final String COL5 = "MAY";
    public static final String COL6 = "JUN";
    public static final String COL7 = "JUL";
    public static final String COL8 = "AUG";
    public static final String COL9 = "SEP";
    public static final String COL10 = "OCT";
    public static final String COL11 = "NOV";
    public static final String COL12 = "DEC";

    private static final int VERSION = 4;

    private SQLiteDatabase db;


    public DatabaseHelper (Context context)
    {
        super(context, DATABASE_NAME , null, VERSION);
    }

    /*public static final String CREATE_TABLE = "CREATE TABLE "
            + TABLE_NAME + " ("
            + COL1 + " TEXT NOT NULL,"
            + COL2 + " TEXT NOT NULL,"
            + COL3 + " TEXT NOT NULL,"
            + COL4 + " TEXT NOT NULL,"
            + COL5 + " TEXT NOT NULL,"
            + COL6 + " TEXT NOT NULL,"
            + COL7 + " TEXT NOT NULL,"
            + COL8 + " TEXT NOT NULL,"
            + COL9 + " TEXT NOT NULL,"
            + COL10 + " TEXT NOT NULL,"
            + COL11 + " TEXT NOT NULL,"// id (same stop can be added multiple times with different filters)
            + COL12 + " TEXT NOT NULL)";*/
    @Override
    public void onCreate(SQLiteDatabase db) {
        String createTable = "CREATE TABLE "+TABLE_NAME
                +" (JAN TEXT,FEB TEXT,MAR TEXT,APR TEXT,MAY TEXT,JUN TEXT,JUL TEXT,AUG TEXT,SEP TEXT,OCT TEXT,NOV TEXT,DEC TEXT)";
        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub
        // db.execSQL(" ALTER TABLE person ADD phone VARCHAR(12) NULL ");
        db.execSQL("DROP TABLE IF EXISTS table_Name");
        onCreate(db);
    }


    public int addReceipt(Receipt receipt)
    {
        //int m = Integer.parseInt(month);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        switch(receipt.getMonth()) {
            case 1:
                contentValues.put(COL1, receipt.getNumber());
                break;
            case 2:
                contentValues.put(COL2, receipt.getNumber());
                break;
            case 3:
                contentValues.put(COL3, receipt.getNumber());
                break;
            case 4:
                contentValues.put(COL4, receipt.getNumber());
                break;
            case 5:
                contentValues.put(COL5, receipt.getNumber());
                break;
            case 6:
                contentValues.put(COL6, receipt.getNumber());
                break;
            case 7:
                contentValues.put(COL7, receipt.getNumber());
                break;
            case 8:
                contentValues.put(COL8, receipt.getNumber());
                break;
            case 9:
                contentValues.put(COL9, receipt.getNumber());
                break;
            case 10:
                contentValues.put(COL10, receipt.getNumber());
                break;
            case 11:
                contentValues.put(COL11, receipt.getNumber());
                break;
            case 12:
                contentValues.put(COL12, receipt.getNumber());
                break;
            default:
                break;
        }

        long id = db.insert(TABLE_NAME, null, contentValues);
        Log.e(TAG,"Add receipt succeccfully");
        db.close();
        return (int)id;

    }

    public boolean delete(long id){
        // 設定條件為編號，格式為「欄位名稱=資料」
        String where = KEY_ID + "=" + id;
        // 刪除指定編號資料並回傳刪除是否成功
        return db.delete(TABLE_NAME, where , null) > 0;
    }

    // 修改參數指定的物件
    public boolean update(Receipt receipt) {
        // 建立準備修改資料的ContentValues物件
        ContentValues contentValues = new ContentValues();
        switch(receipt.getMonth()) {
            case 1:
                contentValues.put(COL1, receipt.getNumber());
                break;
            case 2:
                contentValues.put(COL2, receipt.getNumber());
                break;
            case 3:
                contentValues.put(COL3, receipt.getNumber());
                break;
            case 4:
                contentValues.put(COL4, receipt.getNumber());
                break;
            case 5:
                contentValues.put(COL5, receipt.getNumber());
                break;
            case 6:
                contentValues.put(COL6, receipt.getNumber());
                break;
            case 7:
                contentValues.put(COL7, receipt.getNumber());
                break;
            case 8:
                contentValues.put(COL8, receipt.getNumber());
                break;
            case 9:
                contentValues.put(COL9, receipt.getNumber());
                break;
            case 10:
                contentValues.put(COL10, receipt.getNumber());
                break;
            case 11:
                contentValues.put(COL11, receipt.getNumber());
                break;
            case 12:
                contentValues.put(COL12, receipt.getNumber());
                break;
            default:
                break;
        }
        String where = KEY_ID + "=" + receipt.getId();

        // 執行修改資料並回傳修改的資料數量是否成功
        return db.update(TABLE_NAME, contentValues, where, null) > 0;
    }



    public Receipt getReceipt(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor =  db.rawQuery( "select * from " + TABLE_NAME + " where id="+id, null);
        cursor.moveToFirst();

        Receipt receipt = new Receipt();
        receipt.setMonth((int)cursor.getLong(0));
        receipt.setNumber(cursor.getString(1));

        cursor.close();
        return receipt;
    }

    // 讀取所有記事資料
    public ArrayList<Receipt> getAll() {
        ArrayList<Receipt> result = new ArrayList<>();
        Cursor cursor = db.query(
                TABLE_NAME, null, null, null, null, null, null, null);

        while (cursor.moveToNext()) {
            result.add(getRecord(cursor));
        }

        cursor.close();
        return result;
    }
    // 把Cursor目前的資料包裝為物件
    public Receipt getRecord(Cursor cursor) {
        // 準備回傳結果用的物件
        Receipt result = new Receipt();

        result.setNumber(cursor.getString(1));


        // 回傳結果
        return result;
    }

    // 取得資料數量
    public int getCount() {
        int result = 0;
        Cursor cursor = db.rawQuery("SELECT COUNT(*) FROM " + TABLE_NAME, null);

        if (cursor.moveToNext()) {
            result = cursor.getInt(0);
        }

        return result;
    }

    private Cursor getCursorById(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        return db.rawQuery( "select * from contacts where id="+id, null );
    }
    public Cursor getListContent(){
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor data = db.rawQuery("Select * From "+TABLE_NAME,null);
        return data;
    }

}



