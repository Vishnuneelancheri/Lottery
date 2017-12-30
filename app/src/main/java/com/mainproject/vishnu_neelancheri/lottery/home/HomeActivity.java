package com.mainproject.vishnu_neelancheri.lottery.home;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.AddUserActivity;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.ViewCustomerActivity;
import com.mainproject.vishnu_neelancheri.lottery.settings.NewSettingsActivity;

public class HomeActivity extends AppCompatActivity implements View.OnClickListener{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        findViewById( R.id.btn_add_new_cust ).setOnClickListener( this );
        findViewById( R.id.btn_view_cust ).setOnClickListener( this );
        findViewById( R.id.btn_add_settings ).setOnClickListener( this );
    }
    @Override
    public void onClick( View view ){
        switch ( view.getId() ){
            case R.id.btn_add_new_cust:{
                Intent intent = new Intent( HomeActivity.this, AddUserActivity.class );
                startActivity( intent );
            }
            break;
            case R.id.btn_view_cust:{
                Intent intent = new Intent( HomeActivity.this, ViewCustomerActivity.class );
                startActivity( intent );
            }
            break;
            case R.id.btn_add_settings:{
                Intent intent = new Intent( HomeActivity.this, NewSettingsActivity.class );
                startActivity( intent );
            }
            break;

        }

    }
}
