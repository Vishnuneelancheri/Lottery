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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_user);



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

        EditText edtTxtName, edtTxtPhone, edtTxtEmail, edtTxtOpeningBalance, edtTxtShortCode;

        edtTxtName = findViewById( R.id.edt_txt_name );
        edtTxtEmail = findViewById( R.id.edt_txt_email );
        edtTxtPhone = findViewById( R.id.edt_txt_phone );
        edtTxtOpeningBalance = findViewById( R.id.edt_txt_opening_balance );
        edtTxtShortCode = findViewById( R.id.edt_txt_short_code );

        String name = edtTxtName.getText().toString();
        String mobile = edtTxtPhone.getText().toString();
        String email = edtTxtEmail.getText().toString();
        String openingBalance = edtTxtOpeningBalance.getText().toString();
        String code = edtTxtShortCode.getText().toString();
        int openingBalanceInt;
        try {
            openingBalanceInt = Integer.parseInt( openingBalance );
            if ( openingBalanceInt < 1 ){
                Toast.makeText( this, "Enter valid amount ", Toast.LENGTH_LONG ).show();
                return;
            }
        }catch ( Exception e ){

            return;
        }

        if ( name.length() > 2 ){
            if ( mobile.length() == 10){

                if ( code.isEmpty() ){
                    Toast.makeText( AddUserActivity.this, "Please enter short code", Toast.LENGTH_LONG ).show();
                    return;
                }

                CustomerDetails customerDetails = new CustomerDetails();
                customerDetails.setCode( code );
                customerDetails.setName( name );
                customerDetails.setMobile( mobile );
                customerDetails.setEmail( email );
                customerDetails.setOpeningBalance( openingBalanceInt );

                long result = CustomerDao.getInstance().customerRegistraion( customerDetails, this );
                if ( result > 0 ){
                    Toast.makeText( this, "Customer added successfully", Toast.LENGTH_LONG).show();
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
