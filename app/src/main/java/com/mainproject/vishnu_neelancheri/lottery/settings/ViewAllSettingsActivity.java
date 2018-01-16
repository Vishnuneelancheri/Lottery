package com.mainproject.vishnu_neelancheri.lottery.settings;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.dao.SettingsDao;

import java.util.ArrayList;

public class ViewAllSettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all_settings);

        RecyclerView recyclerViewAllSettings = findViewById( R.id.recycler_view_all_settings );
        recyclerViewAllSettings.setHasFixedSize( true );
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager( this );
        recyclerViewAllSettings.setLayoutManager( layoutManager );
        ArrayList< SettingsDetails >settingsDetails = SettingsDao.getInstance().getAllSettings( this );

        ViewSettingsAdapter viewSettingsAdapter = new ViewSettingsAdapter(settingsDetails, new ViewSettingsAdapter.SelectSettings() {
            @Override
            public void select(SettingsDetails settingsDetails) {
                finishWithSettingsDetails( settingsDetails );
            }
        });
        recyclerViewAllSettings.setAdapter( viewSettingsAdapter );

    }
    private void finishWithSettingsDetails( SettingsDetails settingsDetails ){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable( getString(R.string.app_name), settingsDetails );
        intent.putExtras( bundle );
        setResult( RESULT_OK, intent );
        finish();
    }
}
