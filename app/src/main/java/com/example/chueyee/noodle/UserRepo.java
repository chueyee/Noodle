package com.example.chueyee.noodle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by ChueYee on 5/26/2016.
 */
public class UserRepo {
    private UserDBHandler dbHandler;

    public UserRepo(Context context)
    {
        dbHandler = new UserDBHandler(context);
    }

    public int insert(User user)
    {
        //Open connection to write data
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(User.COLUMN_EMAIL, user.email);
        values.put(User.COLUMN_PASSWORD, user.password);

        //Inserting Row
        long user_id = db.insert(User.TABLE, null, values);
        db.close();
        return (int) user_id;
    }

    public void update(User user)
    {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(User.COLUMN_ID, user.user_ID);
        values.put(User.COLUMN_EMAIL, user.email);
        values.put(User.COLUMN_PASSWORD, user.password);

        db.update(User.TABLE, values, User.COLUMN_ID + "= ?", new String[]{String.valueOf(user.user_ID)});
        db.close();
    }

    public ArrayList<HashMap<String, String>> getUserList()
    {
        //Open connection to read
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        String query = "SELECT " +
                User.COLUMN_ID + ", " +
                User.COLUMN_EMAIL + ", " +
                User.COLUMN_PASSWORD +
                " FROM " + User.TABLE;

        ArrayList<HashMap<String, String>> userList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(query, null);

        //loop through all rows and adding

        if(cursor.moveToFirst())
        {
            do{
                HashMap<String, String> user = new HashMap<String, String>();
                user.put("id", cursor.getString(cursor.getColumnIndex(User.COLUMN_ID)));
                user.put("email", cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL)));
                userList.add(user);
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return userList;
    }

    public int getUserByEmail(String email)
    {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT " + User.COLUMN_ID +
                " FROM " + User.TABLE +
                " WHERE " + User.COLUMN_EMAIL + "=?";

        User user = new User();
        Cursor cursor = db.rawQuery(query, new String[] {String.valueOf(email)});

        cursor.moveToFirst();
        int userID= cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID));
        cursor.close();
        return userID;
    }

    public boolean checkEmailExists(String email)
    {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT %s FROM %s WHERE %s = ? LIMIT 1",
                        User.COLUMN_EMAIL, User.TABLE, User.COLUMN_EMAIL),
                new String[]{email});

        return cursor.moveToFirst();
    }

    public String checkPassword(String email)
    {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT " + User.COLUMN_PASSWORD +
                " FROM " + User.TABLE +
                " WHERE " + User.COLUMN_EMAIL + "=?";

        Cursor cursor = db.rawQuery(query, new String[] {email});

        cursor.moveToFirst();
        String pass = cursor.getString(cursor.getColumnIndex(User.COLUMN_PASSWORD));
        cursor.close();
        return pass;

    }

}
