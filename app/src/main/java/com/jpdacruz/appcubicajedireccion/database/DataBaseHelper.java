package com.jpdacruz.appcubicajedireccion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    public static final String DATABASE_NAME = "fiscalizacion.db";
    public static final String TABLE_NAME = "silos_table";
    public static final String COL1_IDA= "idAuto";
    public static final String COL2_IDN = "id";
    public static final String COL3_TG = "tipoGrano";
    public static final String COL4_PHG = "phGrano";
    public static final String COL5_DIA = "diametro";
    public static final String COL6_ALT = "altoGrano";
    public static final String COL7_CONO = "cono";
    public static final String COL8_COPE = "copete";
    public static final String COL9_VOL = "totalm3";
    public static final String COL10_CUBC = "totaltons";

    public DataBaseHelper
            (@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTable = "CREATE TABLE " + TABLE_NAME
                +" (idAuto INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"id TEXT not null, "
                +"tipoGrano TEXT not null, "
                +"phGrano REAL not null, "
                +"diametro REAL not null, "
                +"altoGrano REAL not null, "
                +"cono REAL not null, "
                +"copete REAL not null, "
                +"totalm3 REAL not null, "
                +"totaltons REAL not null)";

        db.execSQL(createTable);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
        onCreate(db);
    }

    public boolean addSilo (String id, String tipoGrano, double phGrano, double diametro, double altoGrano,
                            double cono, double copete, double totalm3, double totaltons){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2_IDN,id);
        contentValues.put(COL3_TG,tipoGrano);
        contentValues.put(COL4_PHG,phGrano);
        contentValues.put(COL5_DIA,diametro);
        contentValues.put(COL6_ALT,altoGrano);
        contentValues.put(COL7_CONO,cono);
        contentValues.put(COL8_COPE,copete);
        contentValues.put(COL9_VOL,totalm3);
        contentValues.put(COL10_CUBC,totaltons);

        long result = db.insert(TABLE_NAME,null,contentValues);

        if (result == -1){

            return false;

        }else {

            return true;
        }
    }

    public Integer deleteSilo (int idAuto){

        String idAutoString = String.valueOf(idAuto);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME, "idAuto = ?", new String[]{idAutoString});
    }

    public boolean upDateSilo (int idAuto, String id, String tipoGrano, double phGrano, double diametro, double altoGrano,
                            double cono, double copete, double totalm3, double totaltons){

        String idAutoString = String.valueOf(idAuto);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2_IDN,id);
        contentValues.put(COL3_TG,tipoGrano);
        contentValues.put(COL4_PHG,phGrano);
        contentValues.put(COL5_DIA,diametro);
        contentValues.put(COL6_ALT,altoGrano);
        contentValues.put(COL7_CONO,cono);
        contentValues.put(COL8_COPE,copete);
        contentValues.put(COL9_VOL,totalm3);
        contentValues.put(COL10_CUBC,totaltons);

        long result = db.update(TABLE_NAME,contentValues,"idAuto = ?", new String[]{idAutoString});

        if (result == -1){

            return false;

        }else {

            return true;
        }
    }

    public Cursor showSilos(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME, null);

        return data;
    }
}
