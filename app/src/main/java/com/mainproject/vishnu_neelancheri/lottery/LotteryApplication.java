package com.mainproject.vishnu_neelancheri.lottery;

import android.app.Application;

import com.mainproject.vishnu_neelancheri.lottery.db.LotteryDb;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/28/2017.
 */

public class LotteryApplication extends Application {
    public final static  boolean DEBUG = true;
    @Override
    public void onCreate() {
        super.onCreate();
        LotteryDb.initDb( this );
    }
}
