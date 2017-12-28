package com.mainproject.vishnu_neelancheri.lottery.add_view_user;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import com.mainproject.vishnu_neelancheri.lottery.R;

public class ViewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_user);
        RecyclerView recyclerView = findViewById( R.id.recycler_show_user );

    }
}

