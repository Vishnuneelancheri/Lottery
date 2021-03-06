package com.mainproject.vishnu_neelancheri.lottery.add_view_user;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.lottery.R;

import java.util.ArrayList;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/27/2017
 */

public class ViewCustomerAdapter extends RecyclerView.Adapter<ViewCustomerAdapter.DataHolder>{
    private ArrayList< CustomerDetails > mCustomerDetailsList;
    private ItemSelection mItemSelection;
    public ViewCustomerAdapter (ArrayList <CustomerDetails> customerList , ItemSelection itemSelection ){
        mCustomerDetailsList = customerList;
        mItemSelection = itemSelection;
    }
    public class DataHolder extends RecyclerView.ViewHolder{
        TextView txtViewName, txtViewMobile, txtViewCode, txtViewEmail;
        Button btnSelect;
        public DataHolder( View view ){
            super( view );
            txtViewName =   view.findViewById( R.id.txt_recycler_name );
            txtViewMobile = view.findViewById( R.id.txt_recycler_mob );
            txtViewCode = view.findViewById( R.id.txt_recycler_code );
            txtViewEmail = view.findViewById( R.id.txt_recycler_email );
            btnSelect = view.findViewById( R.id.btn_recycle_select );
        }
    }

    @Override
    public int getItemCount(){
        return mCustomerDetailsList.size();
    }

    @Override
    public DataHolder onCreateViewHolder(ViewGroup parent, int pos){
        View view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.recycler_show_customer, parent, false);
        return new DataHolder( view );
    }
    @Override
    public void onBindViewHolder( DataHolder dataHolder, int position ){
        final int pos = dataHolder.getAdapterPosition();
        CustomerDetails customerDetails = mCustomerDetailsList.get( pos );
        dataHolder.txtViewName.setText( customerDetails.getName() );
        dataHolder.txtViewMobile.setText( customerDetails.getMobile() );
        dataHolder.txtViewCode.setText( customerDetails.getCode() );
        dataHolder.txtViewEmail.setText( customerDetails.getEmail() );
        dataHolder.btnSelect.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                mItemSelection.select( pos );
            }
        });
    }
    public interface ItemSelection{
        void delete( int pos );
        void select( int pos );
    }
}
