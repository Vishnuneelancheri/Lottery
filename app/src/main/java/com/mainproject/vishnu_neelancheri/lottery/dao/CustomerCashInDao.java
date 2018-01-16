package com.mainproject.vishnu_neelancheri.lottery.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;

import com.mainproject.vishnu_neelancheri.lottery.cash_in.CashInDetails;
import com.mainproject.vishnu_neelancheri.lottery.db.LotteryDb;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 1/1/2018
 */

public class CustomerCashInDao {
    private static volatile CustomerCashInDao customerCashInDao;

    public static CustomerCashInDao getInstance(){
        if ( customerCashInDao == null ){
            synchronized ( CustomerCashInDao.class ){
                if ( customerCashInDao == null ){
                    customerCashInDao = new CustomerCashInDao();
                }
            }
        }
        return customerCashInDao;
    }

    private static final String TABLE_CUSTOMER_CASH_IN = "customer_cash_in";
    private static final String KEY_ID = "_id";
    private static final String KEY_CUST_ID = "customer_id";
    private static final String KEY_SETTTINGS_ID = "settings_id";
    private static final String KEY_FIRST_PRIZE_QTY = "first_price_qty";
    private static final String KEY_SECOND_PRIZE_QTY = "second_price_qty";
    private static final String KEY_THIRD_PRIZE_QTY = "third_price_qty";
    private static final String KEY_TOTAL_PRIZE = "total_price";
    private static final String KEY_DATE = "date";

    public static final String SQL_CREATE_ENTRIES_TABLE_CUSTOMER_CASH_IN = " CREATE TABLE IF NOT EXISTS " + TABLE_CUSTOMER_CASH_IN + " ( "
            +KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "
            + KEY_CUST_ID + " integer, "
            +KEY_SETTTINGS_ID + " integer, "
            +KEY_FIRST_PRIZE_QTY + " integer, "
            +KEY_SECOND_PRIZE_QTY + " integer, "
            +KEY_THIRD_PRIZE_QTY + " integer, "
            + KEY_TOTAL_PRIZE + " integer,"
            +KEY_DATE + " varchar(15) )";

    public long insertDetails(CashInDetails cashInDetails, Context context ){
        ContentValues contentValues = new ContentValues();
        contentValues.put( KEY_CUST_ID, cashInDetails.getCustomerId() );
        contentValues.put( KEY_SETTTINGS_ID, cashInDetails.getSettingsId() );
        contentValues.put( KEY_FIRST_PRIZE_QTY, cashInDetails.getFirstPrizeQty() );
        contentValues.put( KEY_SECOND_PRIZE_QTY, cashInDetails.getSecondPrizeQty() );
        contentValues.put( KEY_THIRD_PRIZE_QTY, cashInDetails.getThirdPriceQty() );
        contentValues.put( KEY_DATE, cashInDetails.getDate() );
        contentValues.put( KEY_TOTAL_PRIZE, cashInDetails.getTotalAmount() );
        return LotteryDb.getInstance( context ).insertData( TABLE_CUSTOMER_CASH_IN, contentValues );

    }

    public long totalCashIn ( int custId , Context context ){

        String[] column = { KEY_TOTAL_PRIZE };
        String[] args = { String.valueOf( custId )};
        Cursor cursor = LotteryDb.getInstance( context ).query( TABLE_CUSTOMER_CASH_IN, column, KEY_CUST_ID+" = ? ", args );
        Long totalCash = 0L;
        while ( cursor.moveToNext() ){
            totalCash += Long.parseLong( cursor.getString( cursor.getColumnIndex( KEY_TOTAL_PRIZE ) ) );
        }
        totalCash += CustomerDao.getInstance().getOpeningBalance( custId, context );
        return totalCash;
    }

}
