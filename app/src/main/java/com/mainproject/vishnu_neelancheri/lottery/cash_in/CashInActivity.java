package com.mainproject.vishnu_neelancheri.lottery.cash_in;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mainproject.vishnu_neelancheri.lottery.LotteryApplication;
import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.CustomerDetails;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.ViewCustomerActivity;
import com.mainproject.vishnu_neelancheri.lottery.dao.CustomerCashInDao;
import com.mainproject.vishnu_neelancheri.lottery.settings.SettingsDetails;
import com.mainproject.vishnu_neelancheri.lottery.settings.ViewAllSettingsActivity;

import java.util.Calendar;

public class CashInActivity extends AppCompatActivity implements View.OnClickListener{
    private final int SELECT_CUSTOMER_REQUEST = 100;
    private final int SELECT_SETTINGS_REQUEST = 200;
    private TextView mTxtName, mTxtPhone, mTxtShortCode, mTxtSelectedDate ;
    private EditText mEdtFirstPrizeQuantity, mEdtSecondPrizeQuantity, mEdtdThirdPrizeQuantity;
    private String mSelectedDateString;
    private SettingsDetails mSettingsDetails;
    private CustomerDetails mCustomerDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in);

        findViewById( R.id.btn_select_cust ).setOnClickListener( this );
        findViewById( R.id.btn_choose_date ).setOnClickListener( this );
        findViewById( R.id.btn_submit ).setOnClickListener( this );
        findViewById( R.id.btn_total ).setOnClickListener( this );
        findViewById( R.id.btn_select_settings ).setOnClickListener( this );

        mTxtName = findViewById( R.id.txt_cust_name );
        mTxtPhone = findViewById( R.id.txt_cust_phone );
        mTxtShortCode = findViewById( R.id.txt_cust_code );
        mTxtSelectedDate = findViewById( R.id.txt_selected_date );

        mEdtFirstPrizeQuantity = findViewById( R.id.edt_txt_first_prize_qty );
        mEdtSecondPrizeQuantity = findViewById( R.id.edt_txt_second_prize_qty );
        mEdtdThirdPrizeQuantity = findViewById( R.id.edt_txt_third_prize_qty );

        /*loadSettings();*/
    }
    /*private void loadSettings(){
        TextView txtFirstPrize = findViewById( R.id.txt_settings_first_prize );
        TextView txtSecondPrize = findViewById( R.id.txt_settings_second_prize );
        TextView txtThirdPrize = findViewById( R.id.txt_settings_third_prize );

        mSettingsDetails = SettingsDao.getInstance().getLastSettings( this );
        txtFirstPrize.setText( Integer.toString( mSettingsDetails.getFirst_price() ) );
        txtSecondPrize.setText( Integer.toString( mSettingsDetails.getSecond_price() ) );
        txtThirdPrize.setText( Integer.toString( mSettingsDetails.getThird_price() ) );

    }*/

    @Override
    public void onClick( View view ){
        switch ( view.getId() ){
            case R.id.btn_select_cust:{
                Intent intent = new Intent( CashInActivity.this, ViewCustomerActivity.class );
                Bundle bundle = new Bundle();
                bundle.putBoolean( getString( R.string.app_name ), true );
                intent.putExtras( bundle );
                startActivityForResult( intent, SELECT_CUSTOMER_REQUEST );
            }
            break;
            case R.id.btn_choose_date:{
                selectDate();
            }
            break;
            case R.id.btn_total:{
                submit(1);
            }
            break;
            case R.id.btn_submit:{
                submit(2);
            }
            break;
            case R.id.btn_select_settings:{
                Intent intent = new Intent( CashInActivity.this, ViewAllSettingsActivity.class );
                startActivityForResult( intent, SELECT_SETTINGS_REQUEST);
            }
            break;
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult( requestCode, resultCode, data);
        if ( requestCode == SELECT_CUSTOMER_REQUEST ){
            if ( resultCode == RESULT_OK ){
                mCustomerDetails = data.getParcelableExtra( getString( R.string.app_name ) );
                if ( mCustomerDetails != null ){
                    mTxtName.setText( mCustomerDetails.getName() );
                    mTxtPhone.setText( mCustomerDetails.getMobile() );
                    mTxtShortCode.setText( mCustomerDetails.getCode() );
                }
            }
        }
        else if ( requestCode == SELECT_SETTINGS_REQUEST  && resultCode == RESULT_OK ){
            mSettingsDetails = data.getParcelableExtra( getString( R.string.app_name ) );
            if ( mSettingsDetails != null ){
                TextView txtFirstPrize = findViewById( R.id.txt_settings_first_prize );
                TextView txtSecondPrize = findViewById( R.id.txt_settings_second_prize );
                TextView txtThirdPrize = findViewById( R.id.txt_settings_third_prize );

                txtFirstPrize.setText( String.valueOf( mSettingsDetails.getFirst_price() ) );
                txtSecondPrize.setText( String.valueOf( mSettingsDetails.getSecond_price() ) );
                txtThirdPrize.setText( String.valueOf( mSettingsDetails.getThird_price() ) );
            }
        }
    }
    private void selectDate(){
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(CashInActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {
                String tempDate = i2+"/"+(i1+1)+"/"+i;
                mTxtSelectedDate.setText( tempDate );
                mSelectedDateString = tempDate;
            }
        }, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }

    private void submit(int condition){
        if ( mSettingsDetails == null ){
            Toast.makeText( this, "Please select settings ", Toast.LENGTH_LONG ).show();
            return;
        }
        if ( mSelectedDateString == null ){
            Toast.makeText( this, "Please select date ", Toast.LENGTH_LONG ).show();
            return;
        }
        String firstPriceQuantity = mEdtFirstPrizeQuantity.getText().toString();
        String secondPriceQuantity = mEdtSecondPrizeQuantity.getText().toString();
        String thirdPriceQuantity = mEdtdThirdPrizeQuantity.getText().toString();
        if ( firstPriceQuantity.isEmpty() )
            firstPriceQuantity = "0";
        if ( secondPriceQuantity.isEmpty() )
            secondPriceQuantity = "0";
        if ( thirdPriceQuantity.isEmpty() )
            thirdPriceQuantity = "0";

        TextView txtViewTotal = findViewById( R.id.txt_total );
        try{
            int total;
            int firstQty = Integer.parseInt( firstPriceQuantity );
            int secndQty = Integer.parseInt( secondPriceQuantity );
            int thirdQty = Integer.parseInt( thirdPriceQuantity );
            if ( mSettingsDetails != null ){
                total = ( firstQty * mSettingsDetails.getFirst_price() ) + ( secndQty * mSettingsDetails.getSecond_price() ) +
                        ( thirdQty  * mSettingsDetails.getThird_price() );
                txtViewTotal.setText( String.valueOf( total ));
                if ( condition == 2 ){
                    if ( mCustomerDetails == null ){
                        Toast.makeText( this, "Please select customer", Toast.LENGTH_LONG).show();
                        return;
                    }
                    CashInDetails cashInDetails = new CashInDetails();
                    cashInDetails.setSettingsId( mSettingsDetails.getSettingsId() );
                    cashInDetails.setFirstPrizeQty( firstQty );
                    cashInDetails.setSecondPrizeQty( secndQty );
                    cashInDetails.setThirdPriceQty( thirdQty );
                    cashInDetails.setCustomerId( mCustomerDetails.getId() );
                    cashInDetails.setDate( mSelectedDateString );
                    cashInDetails.setTotalAmount( total );
                    long result = CustomerCashInDao.getInstance().insertDetails( cashInDetails, this );
                    if ( result > 0 ){
                        Toast.makeText( this, "Data inserted successfully ", Toast.LENGTH_LONG).show();
                        finish();
                    }else {
                        Toast.makeText( this, " Can't insert data ", Toast.LENGTH_LONG).show();
                    }
                }
            }
            else {
                finish();
            }


        }catch ( Exception e ){
            if ( LotteryApplication.DEBUG  ){
                Log.e("Excption", e.toString() );
            }
        }

    }



}
