package com.mainproject.vishnu_neelancheri.lottery;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.mainproject.vishnu_neelancheri.lottery.add_view_user.AddUserActivity;
import com.mainproject.vishnu_neelancheri.lottery.home.HomeActivity;

public class LoginActivity extends AppCompatActivity implements View.OnClickListener{
    private EditText mEdtUserName, mEdtPwd;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        mEdtUserName = findViewById( R.id.edt_txt_user_name );
        mEdtPwd = findViewById( R.id.edt_txt_pwd );
        Button btnLogin = findViewById( R.id.btn_login );
        btnLogin.setOnClickListener( this );
    }
    @Override
    public void onClick( View view ){
        Intent intent = new Intent(getApplicationContext(), HomeActivity.class );
        startActivity( intent );
        /*String userName = mEdtUserName.getText().toString();
        String pwd = mEdtPwd.getText().toString();
        if ( !userName.isEmpty() ){
            if ( !pwd.isEmpty() ){
                if ( userName.equals("admin") && pwd.equals("1234") ){
                    Intent intent = new Intent(getApplicationContext(), HomeActivity.class );
                    startActivity( intent );
                }
            }
        }*/
    }
}
