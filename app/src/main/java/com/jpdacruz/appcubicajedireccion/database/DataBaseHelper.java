package com.jpdacruz.appcubicajedireccion.database;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.util.Log;

import androidx.annotation.Nullable;

public class DataBaseHelper extends SQLiteOpenHelper {

    private static final String TAG = "DataBaseHelper";

    public static final String DATABASE_NAME = "fiscalizacion.db";

    //tablas silos
    public static final String TABLE_NAME_SILOS = "silos_table";
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

    //tabla celdas
    public static final String TABLE_NAME_CELDAS = "celdas_table";
    public static final String COL1_IDA_CELDA= "idAuto";
    public static final String COL2_IDN_CELDA = "id";
    public static final String COL3_TG_CELDA = "tipoGrano";
    public static final String COL4_PHG_CELDA = "phGrano";
    public static final String COL5_LARGO_CELDA = "largo";
    public static final String COL6_ANC_CELDA = "ancho";
    public static final String COL7_ALTGR_CELDA = "altoGrano";
    public static final String COL8_TIPO_CELDA = "tipo";
    public static final String COL9_CONO_CELDA = "cono";
    public static final String COL10_COPE_CELDA = "copete";
    public static final String COL11_VOL_CELDA = "totalm3";
    public static final String COL12_CUBC_CELDA = "totaltons";

    //tabla silob

    public static final String TABLE_NAME_SB = "sb_table";
    public static final String COL1_IDA_SB= "idAuto";
    public static final String COL2_IDN_SB = "id";
    public static final String COL3_TG_SB = "tipoGrano";
    public static final String COL4_PHG_SB = "phGrano";
    public static final String COL5_LARGO_SB= "largo";
    public static final String COL6_ANC_SB= "ancho";
    public static final String COL7_AB_SB = "altuBase";
    public static final String COL8_AP_SB= "altuPara";
    public static final String COL9_VOL_SB = "totalm3";
    public static final String COL10_CUBC_SB = "totaltons";

    public DataBaseHelper
            (@Nullable Context context) {
        super(context, DATABASE_NAME, null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {

        String createTablesb = "CREATE TABLE " + TABLE_NAME_SB
                +" (idAuto INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"id TEXT not null, "
                +"tipoGrano TEXT not null, "
                +"phGrano REAL not null, "
                +"largo REAL not null, "
                +"ancho REAL not null,"
                +"altuBase REAL not null, "
                +"altuPara REAL not null, "
                +"totalm3 REAL not null, "
                +"totaltons REAL not null)";

        String createTableCeldas = "CREATE TABLE " + TABLE_NAME_CELDAS
                +" (idAuto INTEGER PRIMARY KEY AUTOINCREMENT, "
                +"id TEXT not null, "
                +"tipoGrano TEXT not null, "
                +"phGrano REAL not null, "
                +"largo REAL not null, "
                +"ancho REAL not null,"
                +"altoGrano REAL not null, "
                +"tipo TEXT not null, "
                +"cono REAL not null, "
                +"copete REAL not null, "
                +"totalm3 REAL not null, "
                +"totaltons REAL not null)";

        String createTableSilos = "CREATE TABLE " + TABLE_NAME_SILOS
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

        db.execSQL(createTableSilos);
        db.execSQL(createTableCeldas);
        db.execSQL(createTablesb);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SILOS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_CELDAS);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME_SB);
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

        long result = db.insert(TABLE_NAME_SILOS,null,contentValues);

        if (result == -1){

            return false;

        }else {

            return true;
        }
    }

    public boolean addCelda (String id, String tipoGrano, double phGrano, double largo, double ancho, double altoGrano,
                             String tipo, double cono, double copete, double totalm3, double totaltons){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2_IDN_CELDA,id);
        contentValues.put(COL3_TG_CELDA,tipoGrano);
        contentValues.put(COL4_PHG_CELDA,phGrano);
        contentValues.put(COL5_LARGO_CELDA,largo);
        contentValues.put(COL6_ANC_CELDA,ancho);
        contentValues.put(COL7_ALTGR_CELDA,altoGrano);
        contentValues.put(COL8_TIPO_CELDA,tipo);
        contentValues.put(COL9_CONO_CELDA,cono);
        contentValues.put(COL10_COPE_CELDA,copete);
        contentValues.put(COL11_VOL_CELDA,totalm3);
        contentValues.put(COL12_CUBC_CELDA,totaltons);

        long result = db.insert(TABLE_NAME_CELDAS,null,contentValues);

        if (result == -1){

            return false;

        }else {

            return true;
        }
    }

    public boolean addSb (String id, String tipoGrano, double phGrano, double largo, double ancho, double altuBase,
                             double altuPara, double totalm3, double totaltons){

        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2_IDN_SB,id);
        contentValues.put(COL3_TG_SB,tipoGrano);
        contentValues.put(COL4_PHG_SB,phGrano);
        contentValues.put(COL5_LARGO_SB,largo);
        contentValues.put(COL6_ANC_SB,ancho);
        contentValues.put(COL7_AB_SB,altuBase);
        contentValues.put(COL8_AP_SB,altuPara);
        contentValues.put(COL9_VOL_SB,totalm3);
        contentValues.put(COL10_CUBC_SB,totaltons);

        String b = id +" " +tipoGrano +" "+ phGrano +" "+ largo +" "+ ancho
                +" "+ altuBase +" "+ altuPara +" "+ totalm3 +" "+totaltons;

        Log.i(TAG,b);

        long result = db.insert(TABLE_NAME_SB,null,contentValues);

        if (result == -1){

            return false;

        }else {

            return true;
        }
    }

    public Integer deleteSilo (int idAuto){

        String idAutoString = String.valueOf(idAuto);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_SILOS, "idAuto = ?", new String[]{idAutoString});
    }

    public Integer deleteCelda (int idAuto){

        String idAutoString = String.valueOf(idAuto);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_CELDAS, "idAuto = ?", new String[]{idAutoString});
    }

    public Integer deleteSb (int idAuto){

        String idAutoString = String.valueOf(idAuto);
        SQLiteDatabase db = this.getWritableDatabase();
        return db.delete(TABLE_NAME_SB, "idAuto = ?", new String[]{idAutoString});
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

        long result = db.update(TABLE_NAME_SILOS,contentValues,"idAuto = ?", new String[]{idAutoString});

        if (result == -1){

            return false;

        }else {

            return true;
        }
    }

    public boolean upDateCelda (int idAuto, String id, String tipoGrano, double phGrano, double largo, double ancho,
                                 double altoGrano, String tipo, double cono, double copete, double totalm3, double totaltons){

        String idAutoString = String.valueOf(idAuto);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2_IDN_CELDA,id);
        contentValues.put(COL3_TG_CELDA,tipoGrano);
        contentValues.put(COL4_PHG_CELDA,phGrano);
        contentValues.put(COL5_LARGO_CELDA,largo);
        contentValues.put(COL6_ANC_CELDA,ancho);
        contentValues.put(COL7_ALTGR_CELDA,altoGrano);
        contentValues.put(COL8_TIPO_CELDA,tipo);
        contentValues.put(COL9_CONO_CELDA,cono);
        contentValues.put(COL10_COPE_CELDA,copete);
        contentValues.put(COL11_VOL_CELDA,totalm3);
        contentValues.put(COL12_CUBC_CELDA,totaltons);

        long result = db.update(TABLE_NAME_CELDAS,contentValues,"idAuto = ?", new String[]{idAutoString});

        if (result == -1){

            return false;

        }else {

            return true;
        }
    }

    public boolean upDateSb (int idAuto, String id, String tipoGrano, double phGrano, double largo, double ancho,
                             double altuBase,double altuPara, double totalm3, double totaltons){

        String idAutoString = String.valueOf(idAuto);
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues contentValues = new ContentValues();

        contentValues.put(COL2_IDN_SB,id);
        contentValues.put(COL3_TG_SB,tipoGrano);
        contentValues.put(COL4_PHG_SB,phGrano);
        contentValues.put(COL5_LARGO_SB,largo);
        contentValues.put(COL6_ANC_SB,ancho);
        contentValues.put(COL7_AB_SB,altuBase);
        contentValues.put(COL8_AP_SB,altuPara);
        contentValues.put(COL9_VOL_SB,totalm3);
        contentValues.put(COL10_CUBC_SB,totaltons);

        long result = db.update(TABLE_NAME_SB,contentValues,"idAuto = ?", new String[]{idAutoString});

        if (result == -1){

            return false;

        }else {

            return true;
        }
    }

    public Cursor showSilos(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME_SILOS, null);

        return data;
    }

    public Cursor showCeldas(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME_CELDAS, null);

        return data;
    }

    public Cursor showSb(){

        SQLiteDatabase db = this.getReadableDatabase();

        Cursor data = db.rawQuery("SELECT * FROM "+ TABLE_NAME_SB, null);

        return data;
    }
}
