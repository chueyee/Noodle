package com.example.chueyee.noodle;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import java.sql.Blob;
import android.hardware.usb.UsbAccessory;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Queue;

/**
 * Created by ChueYee on 5/27/2016.
 */
public class ServiceRepo {
    private ServiceDBHandler dbHandler;

    public ServiceRepo(Context context) {
        dbHandler = new ServiceDBHandler(context);
    }

    //----------------------------------------------------------------------------------
    // INSERT
    //----------------------------------------------------------------------------------
    public int insert(Service service) {
        //Open connection to write data
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(Service.COLUMN_ID, service.user_ID);
        //values.put(Service.COLUMN_SERVICE_ID, service.service_ID);
        values.put(Service.COLUMN_SERVICE_DESCRIPTION, service.service_description);
        values.put(Service.COLUMN_SERVICE_NAME, service.service_name);
        //values.put(Service.COLUMN_SERVICE_HOURS,service.service_hours);
        values.put(Service.COLUMN_SERVICE_PRICE, service.service_price);
        //values.put(Service.COLUMN_SERVICE_RATING_PRICE,service.service_rating_price);
        //values.put(Service.COLUMN_SERVICE_RATING_QUALITY,service.service_rating_quality);
        values.put(Service.COLUMN_SERVICE_SALE, service.sales_check);

        //Inserting Row

        long user_id = db.insert(Service.TABLE, null, values);
        db.close();
        return (int) user_id;
    }


    //----------------------------------------------------------------------------------
    // UPDATE
    //----------------------------------------------------------------------------------
    public void update(Service service) {
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        ContentValues values = new ContentValues();

        values.put(Service.COLUMN_ID, service.user_ID);
        //values.put(Service.COLUMN_SERVICE_ID, service.service_ID);
        values.put(Service.COLUMN_SERVICE_DESCRIPTION, service.service_description);
        values.put(Service.COLUMN_SERVICE_NAME, service.service_name);
        //values.put(Service.COLUMN_SERVICE_HOURS,service.service_hours);
        values.put(Service.COLUMN_SERVICE_PRICE, service.service_price);
        //values.put(Service.COLUMN_SERVICE_RATING_PRICE,service.service_rating_price);
        //values.put(Service.COLUMN_SERVICE_RATING_QUALITY,service.service_rating_quality);
        values.put(Service.COLUMN_SERVICE_SALE, service.sales_check);

        db.update(Service.TABLE, values, Service.COLUMN_ID + "= ?", new String[]{String.valueOf(service.user_ID)});
        db.close();
    }


    //----------------------------------------------------------------------------------
    // Service List
    //----------------------------------------------------------------------------------
    public ArrayList<HashMap<String, String>> getServiceList() {
        //Open connection to read
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        String query = "SELECT " +
                //Service.COLUMN_ID + ", " +
                //Service.COLUMN_SERVICE_ID + ", " +
                Service.COLUMN_SERVICE_DESCRIPTION + ", " +
                //Service.COLUMN_SERVICE_NAME + ", " +
                //Service.COLUMN_SERVICE_HOURS + ", " +
                //Service.COLUMN_SERVICE_PRICE + ", " +
                //Service.COLUMN_SERVICE_RATING_PRICE + ", " +
                //Service.COLUMN_SERVICE_RATING_QUALITY +

                Service.COLUMN_SERVICE_NAME + " , " +
                Service.COLUMN_SERVICE_PRICE +
                " FROM " + Service.TABLE +
                " WHERE " + Service.COLUMN_SERVICE_SALE +
                " = " + 0;

        ArrayList<HashMap<String, String>> serviceList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(query, null);

        //loop through all rows and adding

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> service = new HashMap<String, String>();
                //service.put("id", cursor.getString(cursor.getColumnIndex(Service.COLUMN_ID)));
                //service.put("service id", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_ID)));
                service.put("service description", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_DESCRIPTION)));
                service.put("service name", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_NAME)));
                //service.put("service hours", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_HOURS)));
                service.put("service price", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_PRICE)));
                //service.put("service rating price", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_PRICE)));
                //service.put("service rating quality", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_QUALITY)));
                serviceList.add(service);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return serviceList;
    }


    //----------------------------------------------------------------------------------
    // Sales List
    //----------------------------------------------------------------------------------
    public ArrayList<HashMap<String, String>> getSalesList() {
        //Open connection to read
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        String query = "SELECT " +
                //Service.COLUMN_ID + ", " +
                //Service.COLUMN_SERVICE_ID + ", " +
                Service.COLUMN_SERVICE_DESCRIPTION + ", " +
                //Service.COLUMN_SERVICE_NAME + ", " +
                //Service.COLUMN_SERVICE_HOURS + ", " +
                //Service.COLUMN_SERVICE_PRICE + ", " +
                //Service.COLUMN_SERVICE_RATING_PRICE + ", " +
                //Service.COLUMN_SERVICE_RATING_QUALITY +

                Service.COLUMN_SERVICE_NAME + " , " +
                Service.COLUMN_SERVICE_PRICE +
                " FROM " + Service.TABLE +
                " WHERE " + Service.COLUMN_SERVICE_SALE +
                " = " + 1;

        ArrayList<HashMap<String, String>> serviceList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(query, null);

        //loop through all rows and adding

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> service = new HashMap<String, String>();
                //service.put("id", cursor.getString(cursor.getColumnIndex(Service.COLUMN_ID)));
                //service.put("service id", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_ID)));
                service.put("service description", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_DESCRIPTION)));
                service.put("service name", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_NAME)));
                //service.put("service hours", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_HOURS)));
                service.put("service price", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_PRICE)));
                //service.put("service rating price", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_PRICE)));
                //service.put("service rating quality", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_QUALITY)));
                serviceList.add(service);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return serviceList;
    }


    //----------------------------------------------------------------------------------
    // My Services/Sales List
    //----------------------------------------------------------------------------------
    public ArrayList<HashMap<String, String>> getMyServiceList(int id) {
        //Open connection to read
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        String query = "SELECT " +
                //Service.COLUMN_ID + ", " +
                //Service.COLUMN_SERVICE_ID + ", " +
                Service.COLUMN_SERVICE_DESCRIPTION + ", " +
                //Service.COLUMN_SERVICE_NAME + ", " +
                //Service.COLUMN_SERVICE_HOURS + ", " +
                //Service.COLUMN_SERVICE_PRICE + ", " +
                //Service.COLUMN_SERVICE_RATING_PRICE + ", " +
                //Service.COLUMN_SERVICE_RATING_QUALITY +

                Service.COLUMN_SERVICE_NAME + " , " +
                Service.COLUMN_SERVICE_PRICE +
                " FROM " + Service.TABLE +
                " WHERE " + Service.COLUMN_ID +
                " =? ";

        ArrayList<HashMap<String, String>> serviceList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(query, null);

        //loop through all rows and adding

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> service = new HashMap<String, String>();
                //service.put("id", cursor.getString(cursor.getColumnIndex(Service.COLUMN_ID)));
                //service.put("service id", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_ID)));
                service.put("service description", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_DESCRIPTION)));
                service.put("service name", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_NAME)));
                //service.put("service hours", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_HOURS)));
                service.put("service price", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_PRICE)));
                //service.put("service rating price", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_PRICE)));
                //service.put("service rating quality", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_QUALITY)));
                serviceList.add(service);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return serviceList;
    }


    //----------------------------------------------------------------------------------
    // Search
    //----------------------------------------------------------------------------------
    public ArrayList<HashMap<String, String>> searchServiceName(String name){
        SQLiteDatabase db = dbHandler.getWritableDatabase();
        String query = "SELECT " +
                //Service.COLUMN_ID + ", " +
                //Service.COLUMN_SERVICE_ID + ", " +
                Service.COLUMN_SERVICE_DESCRIPTION + ", " +
                //Service.COLUMN_SERVICE_NAME + ", " +
                //Service.COLUMN_SERVICE_HOURS + ", " +
                //Service.COLUMN_SERVICE_PRICE + ", " +
                //Service.COLUMN_SERVICE_RATING_PRICE + ", " +
                //Service.COLUMN_SERVICE_RATING_QUALITY +

                Service.COLUMN_SERVICE_NAME + " , " +
                Service.COLUMN_SERVICE_PRICE +
                " FROM " + Service.TABLE +
                " WHERE " + Service.COLUMN_SERVICE_SALE +
                " = " + 0 +
                " AND " + Service.COLUMN_SERVICE_NAME +
                " =?";

        ArrayList<HashMap<String, String>> searchList = new ArrayList<HashMap<String, String>>();

        Cursor cursor = db.rawQuery(query, null);

        //loop through all rows and adding

        if (cursor.moveToFirst()) {
            do {
                HashMap<String, String> service = new HashMap<String, String>();
                //service.put("id", cursor.getString(cursor.getColumnIndex(Service.COLUMN_ID)));
                //service.put("service id", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_ID)));
                service.put("service description", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_DESCRIPTION)));
                service.put("service name", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_NAME)));
                //service.put("service hours", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_HOURS)));
                service.put("service price", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_PRICE)));
                //service.put("service rating price", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_PRICE)));
                //service.put("service rating quality", cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_QUALITY)));
                searchList.add(service);
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return searchList;
    }


    //----------------------------------------------------------------------------------
    // Search By Name
    //----------------------------------------------------------------------------------
    public Service getServiceByName(String name) {

        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT " +
                Service.COLUMN_ID + ", " +
                Service.COLUMN_SERVICE_ID + ", " +
                Service.COLUMN_SERVICE_NAME +
                " FROM " + Service.TABLE +
                " WHERE " + Service.COLUMN_SERVICE_NAME + "=?";

        Service service = new Service();
        Cursor cursor = db.rawQuery(query, new String[]{name});

        if (cursor.moveToFirst()) {
            do {
                service.user_ID = cursor.getInt(cursor.getColumnIndex(Service.COLUMN_ID));
                service.service_ID = cursor.getInt(cursor.getColumnIndex(Service.COLUMN_SERVICE_ID));
                service.service_name = cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_NAME));
            } while (cursor.moveToNext());
        }
        cursor.close();
        db.close();
        return service;
    }


    //----------------------------------------------------------------------------------
    // Check If Service Exists
    //----------------------------------------------------------------------------------
    public boolean checkServiceExists(String serviceName) {

        SQLiteDatabase db = dbHandler.getReadableDatabase();
        Cursor cursor = db.rawQuery(String.format("SELECT %s FROM %s WHERE %s = ? LIMIT 1",
                        Service.COLUMN_SERVICE_NAME, Service.TABLE, Service.COLUMN_SERVICE_NAME),
                new String[]{serviceName});
        return cursor.moveToFirst();
    }

    public Service getServiceById(int serviceID) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT * FROM " +
                Service.TABLE + " WHERE " +
                Service.COLUMN_SERVICE_ID + " =?";

        Service service = new Service();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(serviceID)});

        if (cursor.moveToFirst()) {
            do {
                service.user_ID = cursor.getInt(cursor.getColumnIndex(Service.COLUMN_ID));
                service.service_ID = cursor.getInt(cursor.getColumnIndex(Service.COLUMN_SERVICE_ID));
                service.service_name = cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_NAME));
                service.service_description = cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_DESCRIPTION));
                service.service_price = cursor.getString(cursor.getColumnIndex(Service.COLUMN_SERVICE_PRICE));
                service.service_rating_price = cursor.getInt(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_PRICE));
                service.service_rating_quality = cursor.getInt(cursor.getColumnIndex(Service.COLUMN_SERVICE_RATING_QUALITY));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return service;
    }


    //----------------------------------------------------------------------------------
    // Get UserID By Service
    //----------------------------------------------------------------------------------
    public User getUserByService(int userID) {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT " +
                User.COLUMN_EMAIL + " FROM " +
                User.TABLE + " INNER JOIN " +
                Service.TABLE + " ON " +
                Service.COLUMN_ID + " =? AND " +
                User.COLUMN_ID + " = " +
                Service.COLUMN_ID;

        User user = new User();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(userID)});

        if (cursor.moveToFirst()) {
            do {
                user.user_ID = cursor.getInt(cursor.getColumnIndex(User.COLUMN_ID));
                user.email = cursor.getString(cursor.getColumnIndex(User.COLUMN_EMAIL));
            } while (cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return user;
    }


    //----------------------------------------------------------------------------------
    // Get Service Name By Service ID
    //----------------------------------------------------------------------------------
    public Service getServiceName(int service_id)
    {
        SQLiteDatabase db = dbHandler.getReadableDatabase();
        String query = "SELECT " +
                Service.COLUMN_SERVICE_NAME + " FROM " +
                Service.TABLE + " WHERE " +
                Service.COLUMN_SERVICE_ID + " = " +
                service_id;

        Service service = new Service();
        Cursor cursor = db.rawQuery(query, new String[]{String.valueOf(service_id)});

        if(cursor.moveToFirst())
        {
            do
            {
                service.service_ID = cursor.getInt(cursor.getColumnIndex(Service.COLUMN_SERVICE_ID));
            }while(cursor.moveToNext());
        }

        cursor.close();
        db.close();
        return service;

    }

}
