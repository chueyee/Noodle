package com.example.chueyee.noodle;

import java.sql.Blob;

/**
 * Created by ChueYee on 5/27/2016.
 */
public class Service {
    //Table Name
    public static final String TABLE = "service";

    //Table Columns
    public static final String COLUMN_ID = "user_id";
    public static final String COLUMN_SERVICE_ID = "service_id";
    public static final String COLUMN_SERVICE_NAME = "name";
    public static final String COLUMN_SERVICE_PRICE = "price";
    public static final String COLUMN_SERVICE_HOURS = "hours";
    public static final String COLUMN_SERVICE_DESCRIPTION = "description";
    public static final String COLUMN_SERVICE_RATING_PRICE = "price_rating";
    public static final String COLUMN_SERVICE_RATING_QUALITY = "quality_rating";
    public static final String COLUMN_SERVICE_SALE = "sale_check";

    //Data Storage
    public int service_ID;
    public int user_ID;
    public String service_name;
    public String service_price;
    public int service_hours;
    public String service_description;
    public int service_rating_price;
    public int service_rating_quality;
    public boolean sales_check;

    private int sID, uID, sHours, sRatingPrice, sRatingQuality;
    private String sPrice;
    private String sName, sDescription;
    private boolean sSales;
}
