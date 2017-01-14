package com.example.chueyee.noodle;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by ChueYee on 5/26/2016.
 */
public class UserDBHandler extends SQLiteOpenHelper{

    //DATABASE VERSION
    private static final int DATABASE_VERSION = 1;

    //DATABASE NAME
    public static final String DATABASE_NAME = "NoodleUser.db";

    public UserDBHandler(Context context)
    {
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }


    @Override
    public void onCreate(SQLiteDatabase db)
    {
        String CREATE_UserDB_TABLE = "CREATE TABLE " + User.TABLE + "("
                + User.COLUMN_ID + " INTEGER PRIMARY KEY AUTOINCREMENT ,"
                + User.COLUMN_EMAIL + " TEXT, "
                + User.COLUMN_PASSWORD + " TEXT)";

        db.execSQL(CREATE_UserDB_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion)
    {
        db.execSQL("DROP TABLE IF EXISTS " + User.TABLE);

        //Creates Table Again
        onCreate(db);
    }

}
