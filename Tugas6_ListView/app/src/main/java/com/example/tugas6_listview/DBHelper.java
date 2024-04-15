package com.example.tugas6_listview;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {

    public DBHelper(Context context){
        super(context, "ListView.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE mahasiswa (NRP TEXT PRIMARY KEY, Nama TEXT, NoHP TEXT, Image TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(db);
    }

    public Boolean createMhs(String NRP, String Nama, String NoHP, String Image){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put("NRP", NRP);
        values.put("Nama", Nama);
        values.put("NoHP", NoHP);
        values.put("Image", Image);
        long result = DB.insert("mahasiswa", null, values);
        return result != -1;
    }

    public Cursor readAll(){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM mahasiswa", null);
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor;
        } else {
            if (cursor != null){
                cursor.close();
            }
            return null;
        }
    }

    public Cursor readSingle(String NRP){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM mahasiswa WHERE NRP=?", new String[]{NRP});
        if (cursor != null && cursor.getCount() > 0){
            cursor.moveToFirst();
            return cursor;
        } else {
            if (cursor != null){
                cursor.close();
            }
            return null;
        }
    }

    public Boolean updateMhs(String identifier, String[] newData){
        Cursor cursor = readSingle(identifier);
        if (cursor != null){
            SQLiteDatabase DB = getWritableDatabase();
            ContentValues values = new ContentValues();
            values.put("NRP", newData[0]);
            values.put("Nama", newData[1]);
            values.put("NoHP", newData[2]);
            values.put("Image", newData[3]);
            long result = DB.update("mahasiswa", values, "NRP=?", new String[]{identifier});
            cursor.close();
            return result != -1;
        } else {
            return false;
        }
    }

    public Boolean deleteMhs(String identifier){
        Cursor cursor =readSingle(identifier);
        if (cursor != null){
            SQLiteDatabase DB = this.getWritableDatabase();
            long result = DB.delete("mahasiswa", "NRP=?", new String[]{identifier});
            cursor.close();
            return result != -1;
        } else {
            return false;
        }
    }
}
