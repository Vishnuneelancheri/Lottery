package com.mainproject.vishnu_neelancheri.lottery.cash_withdrawal;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.mainproject.vishnu_neelancheri.lottery.LotteryApplication;
import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.CustomerDetails;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.ViewCustomerActivity;
import com.mainproject.vishnu_neelancheri.lottery.dao.CashWithdrawalDao;
import com.mainproject.vishnu_neelancheri.lottery.dao.CustomerCashInDao;
import com.mainproject.vishnu_neelancheri.lottery.home.HomeActivity;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

public class CashWithdrawalActivity extends AppCompatActivity implements View.OnClickListener{
    private int SELECT_CUSTOMER_REQUEST = 100;
    private CustomerDetails mCustomerDetails;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cash_withdrawal);
        findViewById( R.id.btn_select_customer ).setOnClickListener( this );
        findViewById( R.id.btn_withdraw ).setOnClickListener( this );
    }
    @Override
    public void onClick( View view ){
        switch ( view.getId() ){
            case R.id.btn_withdraw:{
                withdraw();
            }
            break;
            case R.id.btn_select_customer:{
                Intent intent = new Intent( CashWithdrawalActivity.this, ViewCustomerActivity.class );
                startActivityForResult( intent, SELECT_CUSTOMER_REQUEST );

            }
        }
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data){
        if ( requestCode == SELECT_CUSTOMER_REQUEST && resultCode == RESULT_OK ){
            mCustomerDetails = data.getParcelableExtra( getString( R.string.app_name ) );

            if ( mCustomerDetails != null ){

                TextView txtName = findViewById( R.id.txt_cust_name );
                TextView txtPhone = findViewById( R.id.txt_cust_phone );
                TextView txtShortCode = findViewById( R.id.txt_cust_short_code );
                TextView txtTotalBalance = findViewById( R.id.txt_show_balance );

                txtName.setText( mCustomerDetails.getName() );
                txtPhone.setText( mCustomerDetails.getMobile() );
                txtShortCode.setText( mCustomerDetails.getCode() );

                txtTotalBalance.setText( String.valueOf(CustomerCashInDao.getInstance().totalCashIn( mCustomerDetails.getId(), this )));
                long custCredit = CustomerCashInDao.getInstance().totalCashIn( mCustomerDetails.getId(), this );
                long custDebt = CashWithdrawalDao.getInstance().getTotalWithDrawalAmount( mCustomerDetails.getId(), this );
                long balance = custCredit - custDebt;
                String tempBalanceDisplay = String.valueOf( custCredit )+" - "+ String.valueOf( custDebt )+" = "+ String.valueOf( balance );
                txtTotalBalance.setText( tempBalanceDisplay );
            }
        }
    }
    private void withdraw( ){
        if ( mCustomerDetails == null ){
            Toast.makeText( this, "Please select customer", Toast.LENGTH_SHORT ).show();
            return;
        }

        EditText edtTxtWithDrawAmount = findViewById( R.id.edt_withdrawal_amount );

        CashWithdrawalDetails cashWithdrawalDetails = new CashWithdrawalDetails();

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("dd-MMM-yyyy" , Locale.ENGLISH);

        cashWithdrawalDetails.setDate( simpleDateFormat.format( calendar.getTime() ));

        try {
            int tempAmt = Integer.parseInt( edtTxtWithDrawAmount.getText().toString() );
            cashWithdrawalDetails.setAmount( tempAmt );
            cashWithdrawalDetails.setCustomer_id( mCustomerDetails.getId() );

            long result = CashWithdrawalDao.getInstance().withdrawAmount( cashWithdrawalDetails, CashWithdrawalActivity.this );
            if ( result > 0 ){
                Toast.makeText( this, "Withdraw successfully", Toast.LENGTH_SHORT ).show();
                Intent intent = new Intent( this, HomeActivity.class );
                startActivity( intent );
                finish();
            }else
                Toast.makeText( this, "Can't withdraw", Toast.LENGTH_SHORT ).show();
        }catch ( Exception e ){
            if (LotteryApplication.DEBUG ){
                Log.e( "exc", e.toString() );

            }
            Toast.makeText( this, e.toString(), Toast.LENGTH_SHORT ).show();
        }
    }

}
