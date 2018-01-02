package com.mainproject.vishnu_neelancheri.lottery.cash_in;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.CustomerDetails;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.ViewCustomerActivity;
import com.mainproject.vishnu_neelancheri.lottery.dao.SettingsDao;
import com.mainproject.vishnu_neelancheri.lottery.settings.SettingsDetails;

import java.util.Calendar;

public class CashInActivity extends AppCompatActivity implements View.OnClickListener{
    private final int SELECT_CUSTOMER_REQUEST = 100;
    private TextView mTxtName, mTxtPhone, mTxtShortCode;
    private EditText mEdtFirstPrizeQuantity, mEdtSecondPrizeQuantity, mEdtdThirdPrizeQuantity;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_in);

        findViewById( R.id.btn_select_cust ).setOnClickListener( this );
        findViewById( R.id.btn_choose_date ).setOnClickListener( this );
        findViewById( R.id.btn_submit ).setOnClickListener( this );

        mTxtName = findViewById( R.id.txt_cust_name );
        mTxtPhone = findViewById( R.id.txt_cust_phone );
        mTxtShortCode = findViewById( R.id.txt_cust_code );

        mEdtFirstPrizeQuantity = findViewById( R.id.edt_txt_first_prize_qty );
        mEdtSecondPrizeQuantity = findViewById( R.id.edt_txt_second_prize_qty );
        mEdtdThirdPrizeQuantity = findViewById( R.id.edt_txt_third_prize_qty );

        loadSettings();
    }
    private void loadSettings(){
        TextView txtFirstPrize = findViewById( R.id.txt_settings_first_prize );
        TextView txtSecondPrize = findViewById( R.id.txt_settings_second_prize );
        TextView txtThirdPrize = findViewById( R.id.txt_settings_third_prize );

        SettingsDetails settingsDetails = SettingsDao.getInstance().getLastSettings( this );
        txtFirstPrize.setText( Integer.toString( settingsDetails.getFirst_price() ) );
        txtSecondPrize.setText( Integer.toString( settingsDetails.getSecond_price() ) );
        txtThirdPrize.setText( Integer.toString( settingsDetails.getThird_price() ) );

    }

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
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        super.onActivityResult( requestCode, resultCode, data);
        if ( requestCode == SELECT_CUSTOMER_REQUEST ){
            if ( resultCode == RESULT_OK ){
                CustomerDetails customerDetails = data.getParcelableExtra( getString( R.string.app_name ) );
                if ( customerDetails != null ){
                    mTxtName.setText( customerDetails.getName() );
                    mTxtPhone.setText( customerDetails.getMobile() );
                    mTxtShortCode.setText( customerDetails.getCode() );
                }
            }
        }
    }
    private void selectDate(){
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(CashInActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int i, int i1, int i2) {

            }
        }, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();
    }
}
