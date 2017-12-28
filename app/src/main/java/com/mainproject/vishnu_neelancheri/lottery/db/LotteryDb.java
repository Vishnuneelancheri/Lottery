package com.mainproject.vishnu_neelancheri.lottery.db;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.mainproject.vishnu_neelancheri.lottery.dao.CustomerDao;

/**
 * Created by Vishnu Neelancheri 9633647027 on 12/23/2017.
 */

public class LotteryDb extends SQLiteOpenHelper {
    private static final String DATABASE_NAME = "Lottery.db";
    private static final int DATABASE_VERSION = 1;
    private static volatile LotteryDb lotteryDb;
    @Override
    public void onCreate( SQLiteDatabase db ){
        db.execSQL( CustomerDao.SQL_CREATE_ENTRIES_CUSTOMER_TABLE );
    }
    public LotteryDb( Context context ){
        super(context, DATABASE_NAME, null, DATABASE_VERSION);
    }
    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int oldVersion, int newVersion ){

    }
    public static LotteryDb getInstance( Context context ){
        if ( lotteryDb == null ){
            synchronized ( LotteryDb.class ){
                if ( lotteryDb == null ){
                    lotteryDb = new LotteryDb( context );
                }
            }
        }
        return lotteryDb;
    }
    public long insertData(String tableName, ContentValues contentValues ){
        SQLiteDatabase db = lotteryDb.getWritableDatabase();
        long result = db.insert( tableName, null, contentValues );
        return result;
    }

    public Cursor query(String tableName, String[] column, String selection, String[] selectionArgs){
        SQLiteDatabase db = lotteryDb.getReadableDatabase();
        return db.query( tableName, column, selection, selectionArgs, null, null, null, null);
    }
}