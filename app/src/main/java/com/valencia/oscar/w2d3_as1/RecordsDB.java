package com.valencia.oscar.w2d3_as1;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class RecordsDB extends SQLiteOpenHelper {
    private final String TABLE_NAME = "records";
    private final String COLUMN_1 = "id";
    private final String COLUMN_2 = "name";
    private final String COLUMN_3 = "email";
    private final String COLUMN_4 = "age";
    private final String COLUMN_5 = "imagepath";
    private String sqlCreate =
            "CREATE TABLE IF NOT EXISTS "+TABLE_NAME+" ("+COLUMN_1+
                    " INTEGER PRIMARY KEY AUTOINCREMENT, "+COLUMN_2+
                    " VARCHAR NOT NULL, "+COLUMN_3+
                    " INTEGER NOT NULL, "+COLUMN_4+
                    " VARCHAR,"+COLUMN_5+
                    " VARCHAR)";
    private String sqlDrop = "DROP TABLE IF EXISTS "+TABLE_NAME;

    public RecordsDB(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, name, factory, version);
    }

    public String getTABLE_NAME() {
        return TABLE_NAME;
    }

    public String getCOLUMN_1() {
        return COLUMN_1;
    }

    public String getCOLUMN_2() {
        return COLUMN_2;
    }

    public String getCOLUMN_3() {
        return COLUMN_3;
    }

    public String getCOLUMN_4() {
        return COLUMN_4;
    }

    public String getCOLUMN_5() {
        return COLUMN_5;
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(sqlCreate);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(sqlDrop);
        db.execSQL(sqlCreate);
    }
}
