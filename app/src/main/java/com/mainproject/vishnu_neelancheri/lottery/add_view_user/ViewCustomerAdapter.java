package com.mainproject.vishnu_neelancheri.lottery.add_view_user;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.mainproject.vishnu_neelancheri.lottery.R;

import java.util.ArrayList;

/**
 * Created by Vishnu Neelancheri, email: vishnuvishnuneelan@gmail.com on 12/27/2017.
 */

public class ViewCustomerAdapter extends RecyclerView.Adapter<ViewCustomerAdapter.DataHolder>{
    private ArrayList< CustomerDetails > mCustomerDetailsList;
    private Context context;
    private ItemSelection mItemSelection;
    public ViewCustomerAdapter (ArrayList <CustomerDetails> customerList, Context context, ItemSelection itemSelection ){
        mCustomerDetailsList = customerList;
        this.context = context;
        mItemSelection = itemSelection;
    }
    public class DataHolder extends RecyclerView.ViewHolder{
        TextView txtViewName, txtViewMobile, txtViewCode, txtViewEmail;
        public DataHolder( View view ){
            super( view );
            txtViewName =   view.findViewById( R.id.txt_recycler_name );
            txtViewMobile = view.findViewById( R.id.txt_recycler_mob );
            txtViewCode = view.findViewById( R.id.txt_recycler_code );
            txtViewEmail = view.findViewById( R.id.txt_recycler_email );
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
        int pos = dataHolder.getAdapterPosition();
        CustomerDetails customerDetails = mCustomerDetailsList.get( pos );
        dataHolder.txtViewName.setText( customerDetails.getName() );
        dataHolder.txtViewMobile.setText( customerDetails.getMobile() );
        dataHolder.txtViewCode.setText( customerDetails.getCode() );
        dataHolder.txtViewEmail.setText( customerDetails.getEmail() );
    }
    public interface ItemSelection{
        void delete( int pos );
        void select( int pos );
    }
}
