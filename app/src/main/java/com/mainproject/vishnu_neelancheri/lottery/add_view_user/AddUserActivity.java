package com.mainproject.vishnu_neelancheri.lottery.add_view_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.dao.CustomerDao;

public class AddUserActivity extends AppCompatActivity implements View.OnClickListener {
    private EditText mEdtTxtName, mEdtTxtPhone, mEdtTxtEmail, mEdtTxtOpeningBalance;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);

        mEdtTxtName = findViewById( R.id.edt_txt_name );
        mEdtTxtEmail = findViewById( R.id.edt_txt_email );
        mEdtTxtPhone = findViewById( R.id.edt_txt_phone );
        mEdtTxtOpeningBalance = findViewById( R.id.edt_txt_opening_balance );

        Button btnSubmit = findViewById( R.id.btn_submit );
        btnSubmit.setOnClickListener( this );
    }

    @Override
    public void onClick( View view ){
        switch ( view.getId() ){
            case R.id.btn_submit:{
                submit();
            }
            break;
        }
    }
    public void submit(){
        String name = mEdtTxtName.getText().toString();
        String mobile = mEdtTxtPhone.getText().toString();
        String email = mEdtTxtEmail.getText().toString();
        String openingBalance = mEdtTxtOpeningBalance.getText().toString();
        int openingBalanceInt;
        try {
            openingBalanceInt = Integer.parseInt( openingBalance );
            if ( openingBalanceInt < 1 ){
                Toast.makeText( this, "Enter valid amount ", Toast.LENGTH_LONG ).show();
                return;
            }
        }catch ( Exception e ){
            Toast.makeText( this, "Enter valid amount ", Toast.LENGTH_LONG ).show();
            return;
        }

        if ( name.length() > 2 ){
            if ( mobile.length() == 10){

                String code = name.substring( 0, 2 ) + mobile.substring( mobile.length() - 2 );
                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setCode( code );
                customerDetails.setName( name );
                customerDetails.setMobile( mobile );
                customerDetails.setEmail( email );
                customerDetails.setOpeningBalance( openingBalanceInt );

                long result = CustomerDao.getInstance().customerRegistraion( customerDetails, this );
                if ( result > 0 ){
                    Intent intent = new Intent( this, ViewCustomerActivity.class );
                    startActivity( intent );
                }
            }else {
                Toast.makeText( this, "Mobile number must be 10 digits", Toast.LENGTH_LONG).show();
            }
        }
        else {
            Toast.makeText( this, "Name should contain atleast 2 charracters ", Toast.LENGTH_LONG).show();
        }
    }
}
