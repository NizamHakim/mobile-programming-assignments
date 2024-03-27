package com.example.tugas4_crud;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class DBHelper extends SQLiteOpenHelper {
    public DBHelper(Context context) {
        super(context, "Mhs.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase DB) {
        DB.execSQL("CREATE TABLE mahasiswa (NRP TEXT PRIMARY KEY, Nama TEXT)");
    }

    @Override
    public void onUpgrade(SQLiteDatabase DB, int oldVersion, int newVersion) {
        DB.execSQL("DROP TABLE IF EXISTS mahasiswa");
        onCreate(DB);
    }

    public Boolean createMhs(String NRP, String Nama){
        SQLiteDatabase DB = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put("NRP", NRP);
        contentValues.put("Nama", Nama);
        long result = DB.insert("mahasiswa", null, contentValues);
        return result != -1;
    }

    public Cursor readMhs(String NRP){
        SQLiteDatabase DB = this.getReadableDatabase();
        Cursor cursor = DB.rawQuery("SELECT * FROM mahasiswa WHERE NRP=?", new String[]{NRP});

        if (cursor != null && cursor.getCount() > 0) {
            cursor.moveToFirst();
            return cursor;
        } else {
            if (cursor != null) {
                cursor.close();
            }
            return null;
        }
    }

    public Boolean updateMhs(String oldNRP, String newNRP, String newNama){
        Cursor cursor = readMhs(oldNRP);
        if (cursor != null){
            SQLiteDatabase DB = this.getWritableDatabase();
            ContentValues contentValues = new ContentValues();
            contentValues.put("NRP", newNRP);
            contentValues.put("Nama", newNama);
            long result = DB.update("mahasiswa", contentValues, "NRP=?", new String[]{oldNRP});
            cursor.close();
            return result != -1;
        } else {
            return false;
        }
    }

    public Boolean deleteMhs(String NRP){
        Cursor cursor = readMhs(NRP);
        if (cursor != null){
            SQLiteDatabase DB = this.getWritableDatabase();
            long result = DB.delete("mahasiswa", "NRP=?", new String[]{NRP});
            cursor.close();
            return result != -1;
        } else {
            return false;
        }
    }
}
