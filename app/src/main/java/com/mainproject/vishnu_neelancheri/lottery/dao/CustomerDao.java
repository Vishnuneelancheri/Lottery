package com.mainproject.vishnu_neelancheri.lottery.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.util.Log;
import android.widget.Toast;

import com.mainproject.vishnu_neelancheri.lottery.LotteryApplication;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.CustomerDetails;
import com.mainproject.vishnu_neelancheri.lottery.db.LotteryDb;

import java.util.ArrayList;

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
    private static final String KEY_OPENING_BALANCE = "opening_balance";

    public static final String SQL_CREATE_ENTRIES_CUSTOMER_TABLE= " CREATE TABLE IF NOT EXISTS "+ TABLE_CUSTOMER+" ( "
            +KEY_ID+ " INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL,"
            +KEY_NAME+ " varchar(200), "
            +KEY_MOBILE+ " varchar(10), "
            +KEY_EMAIL+ " varchar(200), "
            +KEY_OPENING_BALANCE + " integer, "
            +KEY_CODE+ " varchar(25) UNIQUE NOT NULL )";
    public  long customerRegistraion(CustomerDetails customerDetails, Context context ){
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
            return  LotteryDb.getInstance( context ).insertData( TABLE_CUSTOMER, contentValues );
        }
        else {
            Toast.makeText( context, "Phone number already exists", Toast.LENGTH_LONG).show();
         return 0;
        }

    }
    public ArrayList< CustomerDetails > getCustomer(Context context){
        ArrayList < CustomerDetails > customerDetailsArrayList = new ArrayList<>();
        String[] column = {KEY_ID, KEY_NAME, KEY_MOBILE, KEY_EMAIL, KEY_CODE };
        Cursor cursor = LotteryDb.getInstance(context).query( TABLE_CUSTOMER, column, null, null );
        if ( cursor != null ){

            cursor.moveToFirst();
            while ( cursor.moveToNext() ){
                CustomerDetails customerDetails = new CustomerDetails();
                try{
                    int id = Integer.parseInt(cursor.getString( cursor.getColumnIndex( KEY_ID )));
                    customerDetails.setId( id );
                    customerDetails.setName( cursor.getString( cursor.getColumnIndex( KEY_NAME )) );
                    customerDetails.setMobile( cursor.getString( cursor.getColumnIndex( KEY_MOBILE )));
                    customerDetails.setEmail( cursor.getString( cursor.getColumnIndex( KEY_EMAIL )) );
                    customerDetails.setCode( cursor.getString( cursor.getColumnIndex( KEY_CODE )));
                    customerDetailsArrayList.add( customerDetails );
                }catch (Exception e){
                    if (LotteryApplication.DEBUG ){
                        Log.e( "Db exc", e.toString() );
                    }
                }

            }
            cursor.close();
        }

        return customerDetailsArrayList;
    }

}
