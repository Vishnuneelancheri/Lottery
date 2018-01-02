package com.mainproject.vishnu_neelancheri.lottery.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mainproject.vishnu_neelancheri.lottery.cash_in.CashInDetails;
import com.mainproject.vishnu_neelancheri.lottery.db.LotteryDb;
import com.mainproject.vishnu_neelancheri.lottery.settings.SettingsDetails;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/30/2017.
 */

public class SettingsDao {
    private static volatile SettingsDao settingsDao;

    public static SettingsDao getInstance(){
        if ( settingsDao == null ){
            synchronized ( SettingsDao.class ){
                if ( settingsDao == null ){
                    settingsDao = new SettingsDao();
                }
            }
        }
        return settingsDao;
    }
    private static final String TABLE_SETTINGS = "settings";
    private static final String KEY_ID = "_id";
    private static final String KEY_DATE = "date";
    private static final String KEY_PRICE1 ="first_price";
    private static final String KEY_PRICE2 = "second_price";
    private static final String KEY_PRICE3 = "third_price";
    private static final String KEY_ACTIVATED = "activation_status";

    public static final String SQL_CREATE_ENTRIES_TABLE_SETTINGS = " CREATE TABLE IF NOT EXISTS "+ TABLE_SETTINGS +" ( "
            + KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_DATE + " varchar(100) UNIQUE NOT NULL, "
            + KEY_PRICE1 + " INTEGER , "
            + KEY_PRICE2 + " INTEGER, "
            + KEY_PRICE3 + " INTEGER, "
            + KEY_ACTIVATED + " INTEGER )";

    public long settingsRegistraion(SettingsDetails settingsDetails , Context context ){
        ContentValues contentValues = new ContentValues();
        contentValues.put( KEY_ACTIVATED, 0 );
        long result = LotteryDb.getInstance( context ).updateQuery( TABLE_SETTINGS, contentValues, KEY_ACTIVATED+" = ? ",
                new String[]{"0"});
        contentValues.put( KEY_DATE, settingsDetails.getDate() );
        contentValues.put( KEY_PRICE1, settingsDetails.getFirst_price() );
        contentValues.put( KEY_PRICE2, settingsDetails.getSecond_price() );
        contentValues.put( KEY_PRICE3, settingsDetails.getThird_price() );
        contentValues.put( KEY_ACTIVATED, settingsDetails.isActivated() );
        return LotteryDb.getInstance( context ).insertData( TABLE_SETTINGS, contentValues );
    }
    public Cursor checkDateAlreadyAdded( String date, Context context ){
        String[] column = {KEY_DATE};
        String selectin = KEY_DATE + " = ? ";
        String[] selectionArg = {date};
        return LotteryDb.getInstance( context ).query( TABLE_SETTINGS, column, selectin, selectionArg );
    }

    public SettingsDetails getLastSettings(Context context ){
        String[] column = { KEY_ID, KEY_PRICE1, KEY_PRICE2, KEY_PRICE3 };
        String selectin = null;
        String[] selectionArg = null;
        Cursor cursor =  LotteryDb.getInstance( context ).query( TABLE_SETTINGS, column, selectin, selectionArg );
        SettingsDetails settingsDetails = new SettingsDetails();
        if ( cursor.getCount() > 0 && cursor.moveToLast() ){
             settingsDetails.setId( cursor.getInt( cursor.getColumnIndex( KEY_ID ) ) );
             settingsDetails.setFirst_price( cursor.getInt( cursor.getColumnIndex( KEY_PRICE1 ) ) );
             settingsDetails.setSecond_price( cursor.getInt( cursor.getColumnIndex( KEY_PRICE2 ) ) );
             settingsDetails.setThird_price( cursor.getInt( cursor.getColumnIndex( KEY_PRICE3 ) ) );
        }
        return settingsDetails;
    }
}
