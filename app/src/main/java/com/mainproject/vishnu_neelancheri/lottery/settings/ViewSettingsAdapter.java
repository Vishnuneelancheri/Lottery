package com.mainproject.vishnu_neelancheri.lottery.settings;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.add_view_user.ViewCustomerAdapter;

import java.util.ArrayList;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 1/12/2018.
 */

public class ViewSettingsAdapter extends RecyclerView.Adapter< ViewSettingsAdapter.SettingsDataHolder >{
    private ArrayList< SettingsDetails > mSettingsDetailsList;
    private SelectSettings mSelectSettings;
    public ViewSettingsAdapter( ArrayList< SettingsDetails > settingsDetails, SelectSettings selectSettings ){
        mSelectSettings = selectSettings;
        mSettingsDetailsList = settingsDetails;
    }
    public class SettingsDataHolder extends RecyclerView.ViewHolder{
        private TextView mTxtFirstPrice, mTxtSecondPrice, mTxtThirdPrice;
        private LinearLayout mLinearParent;
        public SettingsDataHolder(View view ){
            super( view );
            mTxtFirstPrice = view.findViewById( R.id.txt_first_price );
            mTxtSecondPrice = view.findViewById( R.id.txt_second_price );
            mTxtThirdPrice = view.findViewById( R.id.txt_third_price );
            mLinearParent = view.findViewById( R.id.linear_parent );
        }
    }
    @Override
    public int getItemCount( ){
        return mSettingsDetailsList.size();
    }
    @Override
    public ViewSettingsAdapter.SettingsDataHolder onCreateViewHolder(ViewGroup parent, int pos){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recyler_show_settings, parent, false);
        return new ViewSettingsAdapter.SettingsDataHolder( view );
    }
    @Override
    public void onBindViewHolder( ViewSettingsAdapter.SettingsDataHolder dataHolder, int position ){
        int pos = dataHolder.getAdapterPosition();
        final SettingsDetails settingsDetails = mSettingsDetailsList.get( pos );
        dataHolder.mTxtFirstPrice.setText( String.valueOf( settingsDetails.getFirst_price() ) );
        dataHolder.mTxtSecondPrice.setText( String.valueOf( settingsDetails.getSecond_price() ) );
        dataHolder.mTxtThirdPrice.setText( String.valueOf( settingsDetails.getThird_price() ) );
        dataHolder.mLinearParent.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mSelectSettings.select( settingsDetails );
            }
        });
    }

    public interface SelectSettings{
        void select( SettingsDetails settingsDetails );
    }

}
