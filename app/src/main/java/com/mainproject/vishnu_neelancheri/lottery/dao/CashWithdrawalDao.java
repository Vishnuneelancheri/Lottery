package com.mainproject.vishnu_neelancheri.lottery.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.mainproject.vishnu_neelancheri.lottery.cash_withdrawal.CashWithdrawalDetails;
import com.mainproject.vishnu_neelancheri.lottery.db.LotteryDb;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 1/16/2018
 */

public class CashWithdrawalDao {
    private static volatile CashWithdrawalDao cashWithdrawalDao;
    public  static CashWithdrawalDao getInstance(){
        if ( cashWithdrawalDao == null ){
            synchronized ( CashWithdrawalDao.class ){
                if ( cashWithdrawalDao == null ){
                    cashWithdrawalDao = new CashWithdrawalDao();
                }
            }
        }
        return cashWithdrawalDao;
    }
    private static final String TABLE_CUSTOMER_CASH_WITHDRAWAL = "customer_cash_withdrawal";
    private static final String KEY_ID = "_id";
    private static final String KEY_CUST_ID = "customer_id";
    private static final String KEY_AMOUNT = "amount";
    private static final String KEY_DATE = "date";

    public static final String SQL_CREATE_ENTRIES_TABLE_CUSTOMER_CASH_WITHDRAWAL =
            " CREATE TABLE IF NOT EXISTS " + TABLE_CUSTOMER_CASH_WITHDRAWAL + " ( " +
                    KEY_ID + " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, "+
                    KEY_CUST_ID + " integer, "+
                    KEY_AMOUNT + " integer, " +
                    KEY_DATE + " varchar(100) )";

    public long withdrawAmount(CashWithdrawalDetails cashWithdrawalDetails , Context context ){
        Long credit = CustomerCashInDao.getInstance().totalCashIn( cashWithdrawalDetails.getCustomer_id(), context );
        Long debt = getTotalWithDrawalAmount( cashWithdrawalDetails.getCustomer_id(), context );
        long availableBalance = credit - debt;
        if ( availableBalance <= 0 ){
            Toast.makeText( context, " No money for you ", Toast.LENGTH_SHORT ).show();
            return 0L;
        }
        if ( availableBalance < cashWithdrawalDetails.getAmount() ){
            Toast.makeText( context, " Insufficient balance ", Toast.LENGTH_SHORT ).show();
            return 0L;
        }
        ContentValues contentValues = new ContentValues();
        contentValues.put( KEY_CUST_ID, cashWithdrawalDetails.getCustomer_id() );
        contentValues.put( KEY_AMOUNT, cashWithdrawalDetails.getAmount() );
        contentValues.put( KEY_DATE, cashWithdrawalDetails.getDate() );
        return LotteryDb.getInstance( context ).insertData( TABLE_CUSTOMER_CASH_WITHDRAWAL, contentValues );
    }

    public long getTotalWithDrawalAmount(int custId, Context context ){
        String[] column = { KEY_AMOUNT };
        String[] args = { String.valueOf( custId ) };
        Long totalCash = 0L;
        Cursor cursor =  LotteryDb.getInstance( context ).query( TABLE_CUSTOMER_CASH_WITHDRAWAL, column, KEY_CUST_ID +" = ?", args);
        while ( cursor.moveToNext()){
            totalCash += Long.parseLong( cursor.getString( cursor.getColumnIndex( KEY_AMOUNT ) ) );
        }
        return totalCash;
    }

}
