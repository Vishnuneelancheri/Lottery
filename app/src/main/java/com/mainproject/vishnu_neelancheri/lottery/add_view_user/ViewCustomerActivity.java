package com.mainproject.vishnu_neelancheri.lottery.add_view_user;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.Toast;

import com.mainproject.vishnu_neelancheri.lottery.R;
import com.mainproject.vishnu_neelancheri.lottery.cash_in.CashInActivity;
import com.mainproject.vishnu_neelancheri.lottery.dao.CustomerDao;

import java.util.ArrayList;

public class ViewCustomerActivity extends AppCompatActivity {
    private int isForSelection;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_customer);




        RecyclerView recyclerView = findViewById( R.id.recycler_show_customer);
        recyclerView.setHasFixedSize(true);
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this );
        recyclerView.setLayoutManager( layoutManager );
        final ArrayList< CustomerDetails > customerDetailsArrayList =  CustomerDao.getInstance().getCustomer(this);
        ViewCustomerAdapter viewCustomerAdapter = new ViewCustomerAdapter(customerDetailsArrayList ,
                new ViewCustomerAdapter.ItemSelection() {
                    @Override
                    public void delete(int pos) {

                    }

                    @Override
                    public void select(int pos) {
                        finishWithCustomerDetails( customerDetailsArrayList.get(pos));
                    }
                });
        recyclerView.setAdapter( viewCustomerAdapter );

    }
    private void finishWithCustomerDetails( CustomerDetails customerDetails ){
        Intent intent = new Intent();
        Bundle bundle = new Bundle();
        bundle.putParcelable(getString(R.string.app_name), customerDetails );
        intent.putExtras(bundle);
        setResult( RESULT_OK, intent);
        finish();
    }
}

