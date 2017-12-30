package com.mainproject.vishnu_neelancheri.lottery.settings;

import android.app.DatePickerDialog;
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
import com.mainproject.vishnu_neelancheri.lottery.dao.SettingsDao;

import java.util.Calendar;

public class NewSettingsActivity extends AppCompatActivity implements View.OnClickListener {
    private String selectedDdate;
    private EditText mEdtTxtFirstPrice, mEdtTxtSecondPrice, mEdtTxtThirdPrice;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate( savedInstanceState );
        setContentView( R.layout.activity_new_settings );

        mEdtTxtFirstPrice = findViewById( R.id.edt_txt_first_price);
        mEdtTxtSecondPrice = findViewById( R.id.edt_txt_second_price);
        mEdtTxtThirdPrice = findViewById( R.id.edt_txt_third_price);
        findViewById( R.id.btn_submit ).setOnClickListener( this );
        findViewById( R.id.btn_choose_date ).setOnClickListener( this );
    }

    @Override
    public void onClick( View view ){
        switch ( view.getId() ){
            case R.id.btn_submit:
                submit();
                break;
            case R.id.btn_choose_date:
                chooseDate();
                break;
        }
    }
    private void chooseDate(){
        Calendar calendar = Calendar.getInstance();
        new DatePickerDialog(NewSettingsActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker datePicker, int year, int month, int date) {
                selectedDdate = date +"/" + ( month+1 ) + "/" + year;
                try {
                    if ( SettingsDao.getInstance().checkDateAlreadyAdded( selectedDdate, NewSettingsActivity.this ).getCount() > 0 ){
                        Toast.makeText( NewSettingsActivity.this, "Date already choosed", Toast.LENGTH_SHORT).show();
                    }
                    else{
                        TextView txtSelectedDate = findViewById( R.id.txt_selected_date );
                        txtSelectedDate.setText( selectedDdate );
                    }
                }catch ( Exception e ){
                    if ( LotteryApplication.DEBUG )
                        Log.e( "Date Ex", e.toString() );
                }

            }
        }, calendar
                .get(Calendar.YEAR), calendar.get(Calendar.MONTH),
                calendar.get(Calendar.DAY_OF_MONTH)).show();

    }
    private void submit(){
        if ( selectedDdate == null || selectedDdate.isEmpty() ){
            Toast.makeText(this, "Please select date", Toast.LENGTH_SHORT).show();
            return;
        }
        try {
            int firstPrice = Integer.parseInt( mEdtTxtFirstPrice.getText().toString() );
            int secondPrice = Integer.parseInt( mEdtTxtSecondPrice.getText().toString() );
            int thirdPrice = Integer.parseInt( mEdtTxtThirdPrice.getText().toString() );
            SettingsDetails settingsDetails = new SettingsDetails();
            settingsDetails.setActivated( 1 );
            settingsDetails.setDate( selectedDdate );
            settingsDetails.setFirst_price( firstPrice );
            settingsDetails.setSecond_price( secondPrice );
            settingsDetails.setThird_price( thirdPrice );
            long result = SettingsDao.getInstance().settingsRegistraion( settingsDetails, this );
            result++;
        }catch ( Exception e ){
            if ( LotteryApplication.DEBUG ){
                Log.e("ParseException", e.toString() );
            }
        }
    }

}
