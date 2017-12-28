package com.mainproject.vishnu_neelancheri.lottery.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.widget.Toast;

import com.mainproject.vishnu_neelancheri.lottery.add_view_user.CustomerDetails;
import com.mainproject.vishnu_neelancheri.lottery.db.LotteryDb;

/**
 * Created by Vishnu Neelancheri 9633647027 on 12/27/2017.
 */

public class CustomerDao {
    private static volatile  CustomerDao customerDao;
    public static CustomerDao getInstance(){
        if ( customerDao == null ){
            synchronized ( CustomerDao.class ){
                if ( customerDao == null ){
                    customerDao = new CustomerDao();
                }
            }
        }
        return customerDao;
    }
    private static final String TABLE_CUSTOMER = "customer";
    private static final String KEY_ID = "_id";
    private static final String KEY_NAME = "name";
    private static final String KEY_MOBILE = "mobile";
    private static final String KEY_EMAIL = "email";
    private static final String KEY_CODE = "customer_code";

    public static final String SQL_CREATE_ENTRIES_CUSTOMER_TABLE= " CREATE TABLE IF NOT EXISTS "+ TABLE_CUSTOMER+" ( "
            +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            +KEY_NAME+ " varchar(200), "
            +KEY_MOBILE+ " varchar(10), "
            +KEY_EMAIL+ " varchar(200), "
            +KEY_CODE+ " varchar(25) UNIQUE NOT NULL )";
    public   long customerRegistraion(CustomerDetails customerDetails, Context context ){
        String[] column = {KEY_ID};
        String selection = KEY_MOBILE + " = ? ";
        String[] selectionArg = { customerDetails.getMobile() };
        Cursor cursor = LotteryDb.getInstance( context ).query( TABLE_CUSTOMER, column, selection, selectionArg );
        if ( cursor.getCount() == 0){
            ContentValues contentValues = new ContentValues();
            contentValues.put( KEY_NAME, customerDetails.getName() );
            contentValues.put( KEY_MOBILE, customerDetails.getMobile() );
            contentValues.put( KEY_EMAIL, customerDetails.getEmail() );
            contentValues.put( KEY_CODE, customerDetails.getCode() );
            long result =  LotteryDb.getInstance( context ).insertData( TABLE_CUSTOMER, contentValues );
            return result;
        }
        else {
            Toast.makeText( context, "Phone number already exists", Toast.LENGTH_LONG).show();
         return 0;
        }

    }

}
